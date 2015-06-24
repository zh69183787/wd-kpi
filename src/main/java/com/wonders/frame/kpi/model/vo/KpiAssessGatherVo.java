package com.wonders.frame.kpi.model.vo;

import com.wonders.frame.core.tags.ShowInView;
import com.wonders.frame.kpi.model.bo.KpiAssess;
import com.wonders.frame.kpi.model.bo.KpiDataInfo;
import com.wonders.frame.kpi.model.bo.KpiInfo;
import com.wonders.frame.kpi.model.bo.SelfEvaluation;

import java.util.List;

/**
 * Created by Administrator on 2014/12/4.
 */
public class KpiAssessGatherVo {
    private KpiInfo kpiInfo;
    private KpiAssess kpiAssess;
    private SelfEvaluation selfEvaluation;
    private KpiDataInfo kpiDataInfo;

    public KpiInfo getKpiInfo() {
        return kpiInfo;
    }

    public void setKpiInfo(KpiInfo kpiInfo) {
        this.kpiInfo = kpiInfo;
    }

    public KpiAssess getKpiAssess() {
        return kpiAssess;
    }

    public void setKpiAssess(KpiAssess kpiAssess) {
        this.kpiAssess = kpiAssess;
    }

    public KpiDataInfo getKpiDataInfo() {
        return kpiDataInfo;
    }

    public void setKpiDataInfo(KpiDataInfo kpiDataInfo) {
        this.kpiDataInfo = kpiDataInfo;
    }

    public SelfEvaluation getSelfEvaluation() {
        return selfEvaluation;
    }

    public void setSelfEvaluation(SelfEvaluation selfEvaluation) {
        this.selfEvaluation = selfEvaluation;
    }
}
