package com.wonders.frame.kpi.model.bo;

import com.wonders.frame.core.tags.ShowInView;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2014/12/2.  
 */
@Entity   
@javax.persistence.Table(name = "K_KPI_ASSESS")             
public class KpiAssess implements java.io.Serializable {
    @ShowInView(noUse=true)
    private static final long serialVersionUID = -1705674125247143920L;

    private String kpiAssessId;    
    private Integer year; 
    private Integer mark;
    private String assessDept;   
    @ShowInView(name="考核部门",operateType= ShowInView.OperateType.SELECT,ccateType="kpiAssessDept")
    private String assessDeptId;
    private String markStandard;
    private String basicValueRange;
    @ShowInView(name="被考核单位",operateType= ShowInView.OperateType.SELECT,ccateType="kpiDept")
    private String assessedDeptId;
    private String assessedDept;
    private String assessedCompanyId;  
    private String assessedCompany;
    private String reward;
    private String performanceValueRange;
    private String basicValue;
    private String performanceValue;
    
    
    private String assessReason;

	@Basic
    @javax.persistence.Column(name = "ASSESS_REASON")
    public String getAssessReason() {
		return assessReason;
	}

	public void setAssessReason(String assessReason) {
		this.assessReason = assessReason;
	}

	@ShowInView(noUse=true)
    private Date updateTime;
    @ShowInView(noUse=true)
    private String removed;
    @ShowInView(noUse=true)
    private String creator;
    @ShowInView(noUse=true)
    private Date createTime;
    @ShowInView(noUse=true)
    private String updater;
//    private KpiFormula formula;
//    private String kpiId;


    private KpiInfo kpiInfo;
//    private KpiDataInfo kpiDataInfo;
//
//    @OneToOne(cascade = CascadeType.REFRESH)
//    @JoinColumn(name = "KPI_ASSESS_ID")
//    public KpiDataInfo getKpiDataInfo() {
//        return kpiDataInfo;
//    }
//
//    public void setKpiDataInfo(KpiDataInfo kpiDataInfo) {
//        this.kpiDataInfo = kpiDataInfo;
//    }

//    @OneToOne(cascade = CascadeType.REFRESH)
//    @JoinColumn(name = "FORMULA_ID")
//    public KpiFormula getFormula() {
//        return formula;
//    }
//
//    public void setFormula(KpiFormula formula) {
//        this.formula = formula;
//    }

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "KPI_ID")
    public KpiInfo getKpiInfo() {
        return kpiInfo;
    }

    public void setKpiInfo(KpiInfo kpi) {
        this.kpiInfo = kpi;
    }

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "KPI_ASSESS_ID" ,length = 32)
    public String getKpiAssessId() {
        return kpiAssessId;
    }

    public void setKpiAssessId(String kpiAssessId) {
        this.kpiAssessId = kpiAssessId;
    }


    @Basic
    @javax.persistence.Column(name = "MARK")
    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }


    @Basic
    @javax.persistence.Column(name = "YEAR")
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }


    @Basic
    @javax.persistence.Column(name = "ASSESS_DEPT")
    public String getAssessDept() {
        return assessDept;
    }

    public void setAssessDept(String assessDept) {
        this.assessDept = assessDept;
    }


    @Basic
    @javax.persistence.Column(name = "ASSESS_DEPT_ID")
    public String getAssessDeptId() {
        return assessDeptId;
    }

    public void setAssessDeptId(String assessDeptId) {
        this.assessDeptId = assessDeptId;
    }


    @Basic
    @javax.persistence.Column(name = "MARK_STANDARD")
    public String getMarkStandard() {
        return markStandard;
    }

    public void setMarkStandard(String markStandard) {
        this.markStandard = markStandard;
    }


    @Basic
    @javax.persistence.Column(name = "BASIC_VALUE_RANGE")
    public String getBasicValueRange() {
        return basicValueRange;
    }

    public void setBasicValueRange(String basicValueRange) {
        this.basicValueRange = basicValueRange;
    }


    @Basic
    @javax.persistence.Column(name = "BASIC_VALUE")
    public String getBasicValue() {
        return basicValue;
    }

    public void setBasicValue(String basicValue) {
        this.basicValue = basicValue;
    }

    @Basic
    @javax.persistence.Column(name = "PERFORMANCE_VALUE")
    public String getPerformanceValue() {
        return performanceValue;
    }

    public void setPerformanceValue(String performanceValue) {
        this.performanceValue = performanceValue;
    }

    @Basic
    @javax.persistence.Column(name = "PERFORMANCE_VALUE_RANGE")
    public String getPerformanceValueRange() {
        return performanceValueRange;
    }

    public void setPerformanceValueRange(String performanceValueRange) {
        this.performanceValueRange = performanceValueRange;
    }


    @Basic
    @javax.persistence.Column(name = "REWARD")
    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    @Basic
    @javax.persistence.Column(name = "ASSESSED_COMPANY")
    public String getAssessedCompany() {
        return assessedCompany;
    }

    public void setAssessedCompany(String assessedCompany) {
        this.assessedCompany = assessedCompany;
    }


    @Basic
    @javax.persistence.Column(name = "ASSESSED_COMPANY_ID")
    public String getAssessedCompanyId() {
        return assessedCompanyId;
    }

    public void setAssessedCompanyId(String assessCompanyId) {
        this.assessedCompanyId = assessCompanyId;
    }


    @Basic
    @javax.persistence.Column(name = "ASSESSED_DEPT")
    public String getAssessedDept() {
        return assessedDept;
    }

    public void setAssessedDept(String assessedDept) {
        this.assessedDept = assessedDept;
    }


    @Basic
    @javax.persistence.Column(name = "ASSESSED_DEPT_ID")
    public String getAssessedDeptId() {
        return assessedDeptId;
    }

    public void setAssessedDeptId(String assessedDeptId) {
        this.assessedDeptId = assessedDeptId;
    }

    @Basic
    @javax.persistence.Column(name = "UPDATE_TIME")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    @Basic
    @javax.persistence.Column(name = "REMOVED")
    public String getRemoved() {
        return removed;
    }

    public void setRemoved(String removed) {
        this.removed = removed;
    }


    @Basic
    @javax.persistence.Column(name = "CREATOR")
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }


    @Basic
    @javax.persistence.Column(name = "CREATE_TIME")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    @Basic
    @javax.persistence.Column(name = "UPDATER")
    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }


//    @Basic
//    @javax.persistence.Column(name = "KPI_ID")
//    public String getKpiId() {
//        return kpiId;
//    }
//
//    public void setKpiId(String kpiId) {
//        this.kpiId = kpiId;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KpiAssess kpiAssess = (KpiAssess) o;

        if (assessedCompanyId != null ? !assessedCompanyId.equals(kpiAssess.assessedCompanyId) : kpiAssess.assessedCompanyId != null)
            return false;
        if (assessDept != null ? !assessDept.equals(kpiAssess.assessDept) : kpiAssess.assessDept != null) return false;
        if (assessDeptId != null ? !assessDeptId.equals(kpiAssess.assessDeptId) : kpiAssess.assessDeptId != null)
            return false;
        if (assessedCompany != null ? !assessedCompany.equals(kpiAssess.assessedCompany) : kpiAssess.assessedCompany != null)
            return false;
        if (assessedDept != null ? !assessedDept.equals(kpiAssess.assessedDept) : kpiAssess.assessedDept != null)
            return false;
        if (assessedDeptId != null ? !assessedDeptId.equals(kpiAssess.assessedDeptId) : kpiAssess.assessedDeptId != null)
            return false;
        if (basicValue != null ? !basicValue.equals(kpiAssess.basicValue) : kpiAssess.basicValue != null) return false;
        if (basicValueRange != null ? !basicValueRange.equals(kpiAssess.basicValueRange) : kpiAssess.basicValueRange != null)
            return false;
        if (createTime != null ? !createTime.equals(kpiAssess.createTime) : kpiAssess.createTime != null) return false;
        if (creator != null ? !creator.equals(kpiAssess.creator) : kpiAssess.creator != null) return false;
        if (kpiAssessId != null ? !kpiAssessId.equals(kpiAssess.kpiAssessId) : kpiAssess.kpiAssessId != null)
            return false;
//        if (kpiId != null ? !kpiId.equals(kpiAssess.kpiId) : kpiAssess.kpiId != null) return false;
        if (mark != null ? !mark.equals(kpiAssess.mark) : kpiAssess.mark != null) return false;
        if (markStandard != null ? !markStandard.equals(kpiAssess.markStandard) : kpiAssess.markStandard != null)
            return false;
        if (performanceValue != null ? !performanceValue.equals(kpiAssess.performanceValue) : kpiAssess.performanceValue != null)
            return false;
        if (performanceValueRange != null ? !performanceValueRange.equals(kpiAssess.performanceValueRange) : kpiAssess.performanceValueRange != null)
            return false;
        if (removed != null ? !removed.equals(kpiAssess.removed) : kpiAssess.removed != null) return false;
        if (reward != null ? !reward.equals(kpiAssess.reward) : kpiAssess.reward != null) return false;
        if (updateTime != null ? !updateTime.equals(kpiAssess.updateTime) : kpiAssess.updateTime != null) return false;
        if (updater != null ? !updater.equals(kpiAssess.updater) : kpiAssess.updater != null) return false;
        if (year != null ? !year.equals(kpiAssess.year) : kpiAssess.year != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = kpiAssessId != null ? kpiAssessId.hashCode() : 0;
        result = 31 * result + (mark != null ? mark.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (assessDept != null ? assessDept.hashCode() : 0);
        result = 31 * result + (assessDeptId != null ? assessDeptId.hashCode() : 0);
        result = 31 * result + (markStandard != null ? markStandard.hashCode() : 0);
        result = 31 * result + (basicValueRange != null ? basicValueRange.hashCode() : 0);
        result = 31 * result + (basicValue != null ? basicValue.hashCode() : 0);
        result = 31 * result + (performanceValue != null ? performanceValue.hashCode() : 0);
        result = 31 * result + (performanceValueRange != null ? performanceValueRange.hashCode() : 0);
        result = 31 * result + (reward != null ? reward.hashCode() : 0);
        result = 31 * result + (assessedCompany != null ? assessedCompany.hashCode() : 0);
        result = 31 * result + (assessedCompanyId != null ? assessedCompanyId.hashCode() : 0);
        result = 31 * result + (assessedDept != null ? assessedDept.hashCode() : 0);
        result = 31 * result + (assessedDeptId != null ? assessedDeptId.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (removed != null ? removed.hashCode() : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updater != null ? updater.hashCode() : 0);
//        result = 31 * result + (kpiId != null ? kpiId.hashCode() : 0);
        return result;
    }
}
