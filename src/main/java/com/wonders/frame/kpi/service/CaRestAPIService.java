package com.wonders.frame.kpi.service;



//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
import java.util.*;
//import java.util.Date;
//import java.util.Iterator;
//import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wonders.frame.kpi.model.vo.Department;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wonders.frame.core.utils.EncryptUtil;
import com.wonders.frame.core.utils.JacksonMapper;
import com.wonders.frame.core.utils.RestAPIUtil;
import com.wonders.frame.core.utils.SpringBeanUtil;
import com.wonders.frame.kpi.model.vo.CurrentLogin;


//CA验证
public class CaRestAPIService {
	private static Logger logger=LoggerFactory.getLogger(CaRestAPIService.class);
	private static HashMap<String,String> cookies=new HashMap<String,String>();
	private static void init() {
		
	}
	
	static {
		init();
	}		

	
	public static String getDataFromCa(HttpServletRequest request,HttpServletResponse response,String method,String paramsXml) {
		
		String apiUrl="";
		HashMap<String,String> hm=new HashMap<String,String>();
		hm.put("method", method);
		hm.put("dataType", "json");

		try {
			Properties properties =SpringBeanUtil.getProperties("classpath:kpiConfig.properties");
			String token=cookies.get("token");	
			String sign=EncryptUtil.getMD5(properties.getProperty("appName")+token+method+properties.getProperty("secret"));
			hm.put("appName", properties.getProperty("appName"));
			hm.put("sign", sign);
			hm.put("token", token);
			hm.put("dataParams", "<?xml version=\"1.0\" encoding=\"utf-8\"?><params>"+paramsXml+"</params>");
			apiUrl=properties.getProperty("urlCa")+properties.getProperty("serverPath")+"/"+properties.getProperty("apiName");

		}catch (Exception e) {
			logger.error("Exception Throwable", e);
		}

		return RestAPIUtil.getData(apiUrl, hm);
	
	}

    public static List<Department> getDepartment(HttpServletRequest request,HttpServletResponse response){
        String deptOptions= getDataFromCa(request,response,"getChildNodes","<id>2500</id>");
        List<Department> deptList = new ArrayList<Department>();
        HashMap<String,Object> hm=JacksonMapper.readValue(deptOptions, HashMap.class);
        if(hm.containsKey("params")){
            List<HashMap<String,String>> paramList=(List<HashMap<String,String>>)hm.get("params");
            for(HashMap<String,String> hmParam:paramList){
                Department dept = new Department();
                dept.setId(hmParam.get("id"));
                dept.setName(hmParam.get("name"));
                dept.setDescription(hmParam.get("description"));
                dept.setLevels(hmParam.get("levels"));
                dept.setOrders(hmParam.get("orders"));
                dept.setPid(hmParam.get("pid"));
                dept.setTypeId(hmParam.get("typeId"));
                deptList.add(dept);
            }

        }
        return deptList;
    }
	
	public static CurrentLogin getCurrentLoginInfoFromCa(HttpServletRequest request,HttpServletResponse response){
		String[] cookieNames={"kpi_token","kpi_deptId","kpi_deptName","kpi_userName","kpi_loginName"};
		cookies=getCookieValue(request,Arrays.asList(cookieNames));
		CurrentLogin currentLogin=new CurrentLogin();
		currentLogin.setCookies(cookies);
		String params="<id>"+cookies.get("deptId")+"</id>";

		//get dept leaders
		String deptLeaderOptions= getDataFromCa(request,response,"getDeptLeaders",params);
		logger.debug("the return deptLeaderOptions from ca Api:"+deptLeaderOptions);
		HashMap<String,Object> hm=JacksonMapper.readValue(deptLeaderOptions, HashMap.class);
		if(hm.containsKey("params")){
			List<HashMap<String,String>> paramList=(List<HashMap<String,String>>)hm.get("params");
			HashMap<String,String> deptLeaders=new HashMap<String,String>();
			for(HashMap<String,String> hmParam:paramList){
				deptLeaders.put(hmParam.get("loginName").toString(), hmParam.get("name").toString());
			}
			
			currentLogin.setDeptLeaders(deptLeaders);
		}

        currentLogin.setLoginName(cookies.get("loginName"));
        String dept1Options= getDataFromCa(request,response,"getChildNodes","<id>2500</id>");
        List<Department> deptList = new ArrayList<Department>();
        HashMap<String,Object> hmx=JacksonMapper.readValue(dept1Options, HashMap.class);
        if(hmx.containsKey("params")){
            List<HashMap<String,String>> paramList=(List<HashMap<String,String>>)hmx.get("params");
            for(HashMap<String,String> hmParam:paramList){
                Department dept = new Department();
                dept.setId(hmParam.get("id"));
                dept.setName(hmParam.get("name"));
                dept.setDescription(hmParam.get("description"));
                dept.setLevels(hmParam.get("levels"));
                dept.setOrders(hmParam.get("orders"));
                dept.setPid(hmParam.get("pid"));
                dept.setTypeId(hmParam.get("typeId"));
                deptList.add(dept);
            }
            currentLogin.setDepts(deptList);
        }
		
		String deptUserOptions= getDataFromCa(request,response,"getDeptUsers",params);
		logger.debug("the return deptLeaderOptions from ca Api:"+deptLeaderOptions);
		HashMap<String,Object> hm3=JacksonMapper.readValue(deptUserOptions, HashMap.class);
		if(hm3.containsKey("params")){
			List<HashMap<String,String>> paramList=(List<HashMap<String,String>>)hm3.get("params");
			HashMap<String,String> deptUsers=new HashMap<String,String>();
			for(HashMap<String,String> hmParam:paramList){
				deptUsers.put(hmParam.get("loginName").toString(), hmParam.get("name").toString());
			}
			
			currentLogin.setDeptUsers(deptUsers);
		}
		
		//get company id
		String deptOptions= getDataFromCa(request,response,"getRelatedNodes",params);
		logger.debug("the return deptOptions from ca Api:"+ deptOptions);
		HashMap<String,Object> hm1=JacksonMapper.readValue(deptOptions, HashMap.class);
		
		if(hm1.containsKey("params")){
			List<HashMap<String,String>> paramList=(List<HashMap<String,String>>)hm1.get("params");
			for(HashMap<String,String> hmParam:paramList){
				if(hmParam.get("pid")!=null &&hmParam.get("pid").toString().equals("2500")){
//					currentLogin.setCompanyId("2540");
                    currentLogin.setCompanyId(hmParam.get("id").toString());
					currentLogin.setCompanyName(hmParam.get("description").toString());
					break;
				}else{
					continue;
				}
			}
		}
		//get company leaders
		params="<id>"+currentLogin.getCompanyId()+"</id>";
		String leaderOptions= getDataFromCa(request,response,"getDeptLeaders",params);
		logger.debug("the return leaderOptions from ca Api:"+leaderOptions);
		HashMap<String,Object> hm2=JacksonMapper.readValue(leaderOptions, HashMap.class);
		if(hm2.containsKey("params")){
			List<HashMap<String,String>> paramList=(List<HashMap<String,String>>)hm2.get("params");
			HashMap<String,String> leaders=new HashMap<String,String>();
			for(HashMap<String,String> hmParam:paramList){
				leaders.put(hmParam.get("loginName").toString(), hmParam.get("name").toString());
			}
			
			currentLogin.setLeaders(leaders);
		}
		return currentLogin;
	}
	
	


	private static HashMap<String,String> getCookieValue(HttpServletRequest request,List<String> cookieName){
		Cookie[] cookies = request.getCookies();
		HashMap<String,String> hmCookies=new HashMap<String,String>();
		if(cookies !=null){
			int j=0;
			for(int i=0;i<cookies.length;i++){
				if(j>=cookieName.size()){
					break;
				}
				Cookie cookie = cookies[i];				
				if(cookieName.contains(cookie.getName())){
					j++;
					try{
						hmCookies.put(cookie.getName().replace("kpi_", ""), java.net.URLDecoder.decode(cookie.getValue(),"utf-8"));
					} catch (UnsupportedEncodingException e) {
						logger.error("Exception Throwable", e);
					}
					
					
				}						
			}
		}
		
		return hmCookies;
	}
	
}


