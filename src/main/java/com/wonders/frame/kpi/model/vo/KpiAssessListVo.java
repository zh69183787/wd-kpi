package com.wonders.frame.kpi.model.vo;

import com.wonders.frame.kpi.model.bo.KpiAssess;

import java.util.List;

/**
 * Created by Administrator on 2014/12/3.
 */
public class KpiAssessListVo {
    private List<KpiAssess> kpiAssesses;
    private String assessedDeptId;
    private String assessedDept;
    private Integer year;
    private String oldAssessedDeptId;
    private String oldAssessedDept;
    private Integer oldYear;

    public String getOldAssessedDeptId() {
        return oldAssessedDeptId;
    }

    public void setOldAssessedDeptId(String oldAssessedDeptId) {
        this.oldAssessedDeptId = oldAssessedDeptId;
    }

    public String getOldAssessedDept() {
        return oldAssessedDept;
    }

    public void setOldAssessedDept(String oldAssessedDept) {
        this.oldAssessedDept = oldAssessedDept;
    }

    public Integer getOldYear() {
        return oldYear;
    }

    public void setOldYear(Integer oldYear) {
        this.oldYear = oldYear;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<KpiAssess> getKpiAssesses() {
        return kpiAssesses;
    }

    public void setKpiAssesses(List<KpiAssess> kpiAssesses) {
        this.kpiAssesses = kpiAssesses;
    }

    public String getAssessedDeptId() {
        return assessedDeptId;
    }

    public void setAssessedDeptId(String assessedDeptId) {
        this.assessedDeptId = assessedDeptId;
    }

    public String getAssessedDept() {
        return assessedDept;
    }

    public void setAssessedDept(String assessedDept) {
        this.assessedDept = assessedDept;
    }
}
