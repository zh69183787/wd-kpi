package com.wonders.frame.kpi.service;

import java.util.HashMap;
import java.util.List;

import com.wonders.frame.kpi.model.bo.KpiAssess;
import com.wonders.frame.kpi.model.bo.KpiInfo;
import com.wonders.frame.kpi.model.bo.KpiInfoHistory;

/**
 * Created by Administrator on 2014/12/4.
 */
public interface KpiInfoService {

    KpiInfo getKpiInfo(String kpiId);
    
    public void save(List<KpiInfo> kpiInfos);
  
}
