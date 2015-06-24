package com.wonders.frame.kpi.service;

import com.wonders.frame.core.model.vo.SimplePage;
import com.wonders.frame.kpi.model.bo.KpiAssess;
import com.wonders.frame.kpi.model.bo.KpiAssessRevise;
import com.wonders.frame.kpi.model.bo.Page;


import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2014/12/3.
 */
public interface KpiAssessService {
    public void save(List<KpiAssess> kpiAssesses);
    public void saveAssess(KpiAssess kpiAssess);
    public void saveRevise(List<KpiAssess> kpiAssesses);
   
//  public List<KpiAssess> getAssesses(Integer year, String assessedDeptId, String assessDeptId);


    public SimplePage<KpiAssess> getAssessesIndex(HashMap queryParams, Integer pageNum, Integer pageSize);

    public List<Object> getKpiAssessRevise(String kpiAssessId);
//    public List<KpiAssess> getAssess();

    public List<KpiAssess> getAssesses(HashMap queryParams);
    
    public List<KpiAssess> getAssessandKpiInfo(KpiAssess kpiAssess,Page page); 
    public List<KpiAssess> findAssess(KpiAssess kpiAssess);

    

//    public List<KpiAssess> getAssess(Integer year, String assessedDeptId);

//    public List<KpiAssess> getAssess(Integer year);

//    public List<KpiAssess> getAssess(String assessedDeptId);
    
    

}
