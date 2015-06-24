package com.wonders.frame.kpi.service;

import com.wonders.frame.kpi.model.bo.KpiAssess;
import com.wonders.frame.kpi.model.bo.KpiDataInfo;
import com.wonders.frame.kpi.model.bo.SelfEvaluation;
import com.wonders.frame.kpi.model.vo.KpiAssessGatherVo;
import com.wonders.frame.kpi.model.vo.KpiDataInfoListVo;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2014/12/4.
 */
public interface KpiDataInfoService {
    public void save(List<KpiDataInfo> kpiDataInfos);
//    public List<KpiAssessGatherVo> getKpiDataInfo(HashMap queryParams);
    public List<KpiAssessGatherVo> getKpiDataInfo(HashMap queryParams,boolean isAll);
    public List<KpiAssessGatherVo> getKpiDataInfo(Integer year,String assessedDeptId,String assessDeptId);
    
    public List<KpiDataInfo> getKpiDataInfo(Integer year,String assessedDeptId);
    public List<KpiAssessGatherVo> getKpiDataInfo(HashMap queryParams);
    
    public List<KpiAssessGatherVo> getKpiAssessGatherVo(List<KpiDataInfo> kpiDataInfoList, List<KpiAssess> kpiAssessList,List<SelfEvaluation> selfEvaluationList);
}
