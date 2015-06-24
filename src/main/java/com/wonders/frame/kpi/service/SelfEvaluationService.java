package com.wonders.frame.kpi.service;

import com.wonders.frame.kpi.model.bo.SelfEvaluation;
import com.wonders.frame.kpi.model.vo.KpiAssessGatherVo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2014/12/7.
 */
public interface SelfEvaluationService {

    public void save(List<SelfEvaluation> selfEvaluationList);
//    public List<KpiAssessGatherVo> getSelfEvaluation(Integer year,String assessedDeptId);
    public List<KpiAssessGatherVo> getSelfEvaluation(HashMap queryParams);
    
    

}

