package com.wonders.frame.kpi.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.wonders.frame.core.model.vo.SimplePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wonders.frame.kpi.dao.SelfEvaluationDao;
import com.wonders.frame.kpi.model.bo.KpiAssess;
import com.wonders.frame.kpi.model.bo.SelfEvaluation;
import com.wonders.frame.kpi.model.vo.KpiAssessGatherVo;
import com.wonders.frame.kpi.service.KpiAssessService;
import com.wonders.frame.kpi.service.KpiDataInfoService;
import com.wonders.frame.kpi.service.SelfEvaluationService;

/**
 * Created by Administrator on 2014/12/7.
 */

@Service
public class SelfEvaluationServiceImpl implements SelfEvaluationService {
    @Autowired
    private SelfEvaluationDao selfEvaluationDao;

    @Autowired
    private KpiAssessService kpiAssessService;


    @Autowired
    private KpiDataInfoService kpiDataInfoService;

    @Override
    public void save(List<SelfEvaluation> selfEvaluationList) {
        selfEvaluationDao.save(selfEvaluationList);
    }

//    @Override
//    public List<KpiAssessGatherVo> getSelfEvaluation(Integer year, String assessedDeptId) {
//
//        return getSelfEvaluation(year, assessedDeptId, null);
//    }

    @Override
    public List<KpiAssessGatherVo> getSelfEvaluation(HashMap queryParams) {
        String sql = "select e.mark,e.completion,e.status,e.removed,e.self_evaluation_id,e.kpi_assess_id,check_value from k_self_evaluation e,k_kpi_assess a where a.kpi_assess_id=e.kpi_assess_id and e.removed='0'";

        sql += " and a.year = :year";
        sql += " and a.assessed_Dept_Id like :assessedDeptId";
        if (queryParams.containsKey("status"))
            sql += " and e.status like :status";

        SimplePage page = selfEvaluationDao.findByPageWithSql(sql, queryParams, Integer.MAX_VALUE, 1);
        List<SelfEvaluation> list = new ArrayList<SelfEvaluation>();
        for (Object o : page.getContent()) {
            Object[] objects = (Object[])o;
            SelfEvaluation selfEvaluation = new SelfEvaluation();
            if(objects[0]!=null)
            selfEvaluation.setMark(((BigDecimal)objects[0]).intValue());
            selfEvaluation.setCompletion((String) objects[1]);
            if(objects[2]!=null)
            selfEvaluation.setStatus(((Character) objects[2]).toString());

            selfEvaluation.setRemoved(((Character) objects[3]).toString());
            selfEvaluation.setSelfEvaluationId(((String) objects[4]));

            selfEvaluation.setKpiAssess(new KpiAssess());
            selfEvaluation.getKpiAssess().setKpiAssessId(((String) objects[5]));
            if(objects[6]!=null)
            selfEvaluation.setCheckValue(((BigDecimal)objects[6]).intValue());
            list.add(selfEvaluation);
        }
        page.setContent(list);

        HashMap assessMap = new HashMap();

        assessMap.put("year",queryParams.get("year"));
        assessMap.put("assessedDeptId",queryParams.get("assessedDeptId"));
        List<KpiAssess> kpiAssessList = kpiAssessService.getAssesses(assessMap);
        return kpiDataInfoService.getKpiAssessGatherVo(null, kpiAssessList, page.getContent());
    }


}
