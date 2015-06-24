package com.wonders.frame.kpi.model.vo;

import com.wonders.frame.kpi.model.bo.SelfEvaluation;

import java.util.List;

/**
 * Created by Administrator on 2014/12/7.
 */
public class SelfEvaluationListVo {
    private List<SelfEvaluation> selfEvaluationList;

    public List<SelfEvaluation> getSelfEvaluationList() {
        return selfEvaluationList;
    }

    public void setSelfEvaluationList(List<SelfEvaluation> selfEvaluationList) {
        this.selfEvaluationList = selfEvaluationList;
    }
}
