package com.wonders.frame.kpi.model.vo;

import com.wonders.frame.kpi.model.bo.KpiDataInfo;

import java.util.List;

/**
 * Created by Administrator on 2014/12/4.
 */
public class KpiDataInfoListVo {
    private List<KpiDataInfo> kpiDataInfos;

    public List<KpiDataInfo> getKpiDataInfos() {
        return kpiDataInfos;
    }

    public void setKpiDataInfos(List<KpiDataInfo> kpiDataInfos) {
        this.kpiDataInfos = kpiDataInfos;
    }
}
