package com.wonders.frame.kpi.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.wonders.frame.core.model.vo.SimplePage;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wonders.frame.kpi.dao.KpiAssessDao;
import com.wonders.frame.kpi.dao.KpiAssessReviseDao;
import com.wonders.frame.kpi.model.bo.KpiAssess;
import com.wonders.frame.kpi.model.bo.KpiAssessRevise;
import com.wonders.frame.kpi.model.bo.Page;

import com.wonders.frame.kpi.model.bo.KpiInfo;

import com.wonders.frame.kpi.service.KpiAssessService;

/**
 * Created by Administrator on 2014/12/3.
 */
@Service
public class KpiAssessServiceImpl implements KpiAssessService {

    @Autowired
    private KpiAssessDao kpiAssessDao;
    
    @Autowired
    private KpiAssessReviseDao kpiAssessReviseDao;

    @Override
    public void save(List<KpiAssess> kpiAssesses) {
        kpiAssessDao.save(kpiAssesses);
    }
        
    @Override
	public void saveRevise(List<KpiAssess> kpiAssesses) {
		List<KpiAssessRevise> assessRevise = new ArrayList<KpiAssessRevise>();
		for(KpiAssess kpiAssess:kpiAssesses){
			KpiAssessRevise kpiAssessRevise = new KpiAssessRevise(); 			
			if(StringUtils.isNotBlank(kpiAssess.getKpiAssessId())){				

				kpiAssessRevise.setMark(kpiAssess.getMark());
				kpiAssessRevise.setMarkStandard(kpiAssess.getMarkStandard());
				kpiAssessRevise.setBasicValueRange(kpiAssess.getBasicValueRange());
				kpiAssessRevise.setAssessDept(kpiAssess.getAssessDept());
				kpiAssessRevise.setReward(kpiAssess.getReward());
				kpiAssessRevise.setKpiName(kpiAssess.getKpiInfo().getKpiName());
				
				kpiAssessRevise.setKpiAssess(new KpiAssess());
				kpiAssessRevise.getKpiAssess().setKpiAssessId(kpiAssess.getKpiAssessId());
				
			}
			kpiAssessRevise.setUpdateTime(new Date());
			kpiAssessRevise.setReviseTime(new Date());
			assessRevise.add(kpiAssessRevise);
		}
		kpiAssessReviseDao.save(assessRevise);
	    
	}
      
    @Override
    public void saveAssess(KpiAssess kpiAssess){
    	kpiAssessDao.updateAssess(kpiAssess.getAssessReason(), kpiAssess.getKpiAssessId());
    }

//    @Override
//    public List<KpiAssess> getAssesses(Integer year, String assessedDeptId,String assessDeptId) {
//        HashMap<String,Object> queryParams = new HashMap<String, Object>();
//        LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
//
//
//        queryParams.put("year",year);
//        queryParams.put("assessedDeptId",assessedDeptId);
//        if(StringUtils.isNotBlank(assessDeptId)){
//            queryParams.put("assessDeptId",assessDeptId);
//        }
//        orderby.put("updateTime","desc");
//        return kpiAssessDao.findAll(queryParams,orderby);
//    }

    @Override
    public SimplePage<KpiAssess> getAssessesIndex(HashMap queryParams,Integer pageNum,Integer pageSize) {
       String sql = "select year,assessed_Dept,assessed_Dept_Id from k_Kpi_Assess  where removed = 0";
        if(queryParams.containsKey("year"))
            sql += " and year = :year";
        if(queryParams.containsKey("assessedDeptId") )
            sql += " and assessed_Dept_Id like :assessedDeptId";
       sql += " group by (year,assessed_Dept_Id,assessed_Dept) order by year desc ";

       SimplePage page = kpiAssessDao.findByPageWithSql(sql, queryParams, pageSize, pageNum);
        List<KpiAssess> list = new ArrayList<KpiAssess>();
        for (Object o : page.getContent()) {
            Object[] objects = (Object[])o;
            KpiAssess kpiAssess = new KpiAssess();
if( objects[0]!=null)
            kpiAssess.setYear(((BigDecimal) objects[0]).intValue());
            kpiAssess.setAssessedDept((String)objects[1]);
            kpiAssess.setAssessedDeptId((String)objects[2]);
            list.add(kpiAssess);
        }
        page.setContent(list);
        return page;
    }


//    @Override
//	public List<KpiAssess> getAssess() {
//		List<Object[]> assesslist =  (List<Object[]>) kpiAssessDao.findByYearAndDeptId();
//		List<KpiAssess> list = new ArrayList<KpiAssess>();
//
//		for(Object[] object:assesslist){
//			KpiAssess kpiAssess = new KpiAssess();
//
//			kpiAssess.setYear(((Integer)object[0]));
//			kpiAssess.setAssessedDept((String)object[1]);
//			kpiAssess.setAssessedDeptId((String)object[2]);
//			list.add(kpiAssess);
//		}
//		return list;
//	}
//
//	@Override
//	public List<KpiAssess> getAssess(Integer year, String assessedDeptId) {
//		List<Object[]> assesslist = kpiAssessDao.findYearAndDeptId(year, assessedDeptId);
//		List<KpiAssess> list = new ArrayList<KpiAssess>();
//
//		for(Object[] object:assesslist){
//			KpiAssess kpiAssess = new KpiAssess();
//
//			kpiAssess.setYear(((Integer)object[0]));
//			kpiAssess.setAssessedDept((String)object[1]);
//			kpiAssess.setAssessedDeptId((String)object[2]);
//			list.add(kpiAssess);
//		}
//		return list;
//	}
//
//	@Override
//	public List<KpiAssess> getAssess(Integer year) {
//		List<Object[]> assesslist = kpiAssessDao.findByYear(year);
//		List<KpiAssess> list = new ArrayList<KpiAssess>();
//
//		for(Object[] object:assesslist){
//			KpiAssess kpiAssess = new KpiAssess();
//
//			kpiAssess.setYear(((Integer)object[0]));
//			kpiAssess.setAssessedDept((String)object[1]);
//			kpiAssess.setAssessedDeptId((String)object[2]);
//			list.add(kpiAssess);
//		}
//		return list;
//
//	}
//
//	@Override
//	public List<KpiAssess> getAssess(String assessedDeptId) {
//		HashMap<String,Object> queryParams = new HashMap<String, Object>();
//
//	    queryParams.put("assessedDeptId",assessedDeptId);
//		List<Object[]> assesslist = kpiAssessDao.findByDeptId(assessedDeptId);
//		List<KpiAssess> list = new ArrayList<KpiAssess>();
//
//		for(Object[] object:assesslist){
//			KpiAssess kpiAssess = new KpiAssess();
//
//			kpiAssess.setYear(((Integer)object[0]));
//			kpiAssess.setAssessedDept((String)object[1]);
//			kpiAssess.setAssessedDeptId((String)object[2]);
//			list.add(kpiAssess);
//		}
//		return list;
//	}

    @Override
    public List<KpiAssess> getAssesses(HashMap queryParams) {
        LinkedHashMap orderby = new LinkedHashMap();
        orderby.put("kpiInfo.kpiId","asc");
        orderby.put("kpiInfo.businessType","desc");
        kpiAssessDao.findByPage(queryParams,orderby,Integer.MAX_VALUE,1);
        return  kpiAssessDao.findByPage(queryParams,orderby,Integer.MAX_VALUE,1).getContent();
    }

	@Override
	public List<KpiAssess> getAssessandKpiInfo(KpiAssess kpiAssess,Page assessPage) {
		HashMap queryParams = new HashMap();
		String sql= "select a.assessed_dept,a.year,a.mark,a.basic_value,a.mark_standard," +
				"a.assess_dept,k.unit,k.business_type,k.kpi_type,k.kpi_name " +
				"from k_kpi_assess a,k_kpi_info k where a.removed = '0' and k.kpi_id =a.kpi_id";	
		if (kpiAssess.getKpiInfo()!=null&&StringUtils.isNotBlank(kpiAssess.getKpiInfo().getKpiId())) {
            sql += " and k.kpi_id like :kpiId";		
            queryParams.put("kpiId", kpiAssess.getKpiInfo().getKpiId());
		}
        if (StringUtils.isNotBlank(kpiAssess.getAssessedDeptId()))  {
            sql += " and a.assessed_Dept_Id like :assessedDeptId";
            queryParams.put("assessedDeptId", kpiAssess.getAssessedDeptId());
        }
        if (kpiAssess.getKpiInfo()!=null&&StringUtils.isNotBlank(kpiAssess.getKpiInfo().getBusinessType())) {
            sql += " and k.business_Type like :businessType";
            queryParams.put("businessType", kpiAssess.getKpiInfo().getBusinessType());
        }
        if(kpiAssess.getKpiInfo()!=null&&StringUtils.isNotBlank(kpiAssess.getKpiInfo().getKpiType())) {
        	sql += " and k.kpi_type like :kpiType";  
        	queryParams.put("kpiType", kpiAssess.getKpiInfo().getKpiType());
        }      
        if (kpiAssess.getKpiInfo()!=null&&StringUtils.isNotBlank(kpiAssess.getKpiInfo().getKpiName()))  {  
            sql += " and k.kpi_name like :kpiName";
            queryParams.put("kpiName", kpiAssess.getKpiInfo().getKpiName());
        }
        if (kpiAssess.getYear()!=null)  {  
            sql += " and a.year like :year";
            queryParams.put("year", kpiAssess.getYear());
        }
       
        assessPage.setRows(kpiAssessDao.findByPageWithSql(sql, queryParams,Integer.MAX_VALUE,1).getPageInfo().getTotalRecord());     
        SimplePage page = kpiAssessDao.findByPageWithSql(sql, queryParams,assessPage.getPageSize(),assessPage.getCurrentPage());
        List<KpiAssess> list = new ArrayList<KpiAssess>();  
        for (Object o : page.getContent()) {
            Object[] objects = (Object[]) o;
            KpiAssess assess = new KpiAssess();
            assess.setAssessedDept((String) objects[0]);  
            if (objects[1] != null)
            	assess.setYear(((BigDecimal) objects[1]).intValue());
            assess.setMark(((BigDecimal) objects[2]).intValue());
            assess.setBasicValue((String) objects[3]);
            assess.setMarkStandard((String) objects[4]);
            assess.setAssessDept((String) objects[5]);  
            
            assess.setKpiInfo(new KpiInfo());
            assess.getKpiInfo().setUnit((String) objects[6]);
            assess.getKpiInfo().setBusinessType((String) objects[7]);
            assess.getKpiInfo().setKpiType((String) objects[8]);
            assess.getKpiInfo().setKpiName((String) objects[9]);
          
            list.add(assess);
        }
        page.setContent(list);
        if(list ==null ||list.size() < 1)  
        {
            return null;
        }else{
           return list;  
        }
     
	}


	@Override
	public List<Object> getKpiAssessRevise(String kpiAssessId) {
//		List<KpiAssessRevise> list = kpiAssessReviseDao.findAll();
		return kpiAssessReviseDao.findBykpiAssessId(kpiAssessId);
	}

	@Override
	public List<KpiAssess> findAssess(KpiAssess kpiAssess) {
		return kpiAssessDao.findByYearandAssessedDeptId(kpiAssess.getYear(), kpiAssess.getAssessedDeptId());  
	}


}
		
	

	