/** 
* @Title: BasicCOntroller.java 
* @Package com.wonders.frame.core.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author lushuaifeng
* @version V1.0 
*/
package com.wonders.frame.kpi.controller;

import com.wonders.frame.core.controller.AbstractSimpleCrudController;
import com.wonders.frame.core.service.GenericService;
import com.wonders.frame.core.utils.JacksonMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

/** 
 * @ClassName: BasicCrudController 
 * @Description: 提供基本的增删改查的controller 
 */
@Controller
@RequestMapping("/data")
public class BasicCrudController extends AbstractSimpleCrudController{
    @Resource
    GenericService genericService;
	//@Override
	public String convertExcelExportData(String type,String fieldName,String cellValue,HashMap<String, LinkedHashMap<String, String>> hmFieldOption){
LinkedHashMap<String,String> hmOption=hmFieldOption.get(fieldName);
		
		StringBuffer newValue=new StringBuffer("");
		
		if(fieldName.equalsIgnoreCase("line") && !cellValue.equals("")){				
			
			String[] values=cellValue.split(",");
			for(String value:values){
				newValue.append(hmOption.get(value)).append("/");
			}
			
			cellValue=newValue.toString().substring(0,newValue.length()-1);
			
		}else if(fieldName.equalsIgnoreCase("resourceRequirement") && !cellValue.equals("")){

			HashMap<String,String> cellValueMap  = (HashMap<String,String>)JacksonMapper.readValue(cellValue, HashMap.class);
			
			Set<String> keySet = cellValueMap.keySet();
			
			for(Iterator iterator = keySet.iterator();iterator.hasNext();){
			
				String key = (String)iterator.next();
				String value = (String) cellValueMap.get(key);
				
				HashMap<String,String> subValueMap  = (HashMap<String,String>)JacksonMapper.readValue(value, HashMap.class);
				newValue.append(hmOption.get(key)).append(":");
				String reason = subValueMap.get("reason");
				if(subValueMap.containsKey("num")){
					newValue.append(subValueMap.get("num"));
					newValue.append("[").append(reason).append("]").append("/");
				}else{
					newValue.append(reason).append("/");
				}		
				
			}
			
			cellValue=newValue.toString().substring(0,newValue.length()-1);

		}else if(fieldName.equalsIgnoreCase("equipmentNum") && !cellValue.equals("")){
			return cellValue;
		}else if(hmOption!=null && !cellValue.equals("")){
			
			cellValue = hmOption.get(cellValue);
		}
		if(fieldName.equalsIgnoreCase("status") && !cellValue.equals("")){
			if(cellValue.equals("0")){
				cellValue = "已停用";
			}else{
				cellValue = "正常使用";
			}
		}
		if(fieldName.equalsIgnoreCase("isAssess") && !cellValue.equals("")){
			if(cellValue.equals("0")){  
				cellValue = "不考核";
			}else{
				cellValue = "考核";
			}
		}
		
		
		return cellValue;
	}

	@Override
	public String getExcelExportField(String type) {
		if(type!=null && !type.equals("")){
			if(type.equalsIgnoreCase("kpiInfo")){
				return "businessType,kpiType,kpiName,kpiCode,isAssess,year,status,unit,kpiDefinition,kpiAlgorithm";
			}
		}else{
			return "year,projectName,mainContent,yearTarget,totalInvestEstimate,projectType,resourceRequirement,securityLevel,planStartDate,planEndDate,fundPlan1,fundPlan2,fundPlan3,fundPlan4,remarks";
		}
		return null;
	}

    @Override
    public String convertExcelExportData(String type, String fieldName, String cellValue, HashMap<String, LinkedHashMap<String, String>> hmFieldOption, HashMap<String, HashMap<String, LinkedHashMap<String, String>>> hmFieldCodes) {
        return null;
    }

    @Override
    public String convertExcelImportData(String type, String fieldName, String fieldValue, LinkedHashMap<String, String> option, HashMap<String, LinkedHashMap<String, String>> code) {
        return null;
    }


}
	
