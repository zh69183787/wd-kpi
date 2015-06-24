package com.wonders.frame.kpi.model.bo;

import com.wonders.frame.core.tags.ShowInView;  
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2014/12/4.
 */
@Entity  
@javax.persistence.Table(name = "K_KPI_DATA_INFO")          
public class KpiDataInfo {

	public static final String TO_ADJUSTMENT = "1";  
	public static final String TO_FILL = "2";
	public static final String TO_EXAMINE = "3";
	public static final String EXAMINE = "4";
    public static final String LEADER_CHECK = "5";
	
	private String status;
	
	@Basic
    @javax.persistence.Column(name = "STATUS")
    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private Integer mark;
    

    @Basic
    @javax.persistence.Column(name = "MARK")
    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    private String kpiDataInfoId;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "KPI_DATA_INFO_ID" ,length = 32)
    public String getKpiDataInfoId() {
        return kpiDataInfoId;
    }

    public void setKpiDataInfoId(String kpiDataInfoId) {
        this.kpiDataInfoId = kpiDataInfoId;
    }

    private Integer year;

    @Basic
    @javax.persistence.Column(name = "YEAR")
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    private String assessedDept;

    @Basic
    @javax.persistence.Column(name = "ASSESSED_DEPT")
    public String getAssessedDept() {
        return assessedDept;
    }

    public void setAssessedDept(String assessedDept) {
        this.assessedDept = assessedDept;
    }

    @ShowInView(name="被考核单位",operateType= ShowInView.OperateType.SELECT,ccateType="kpiDept")
    private String assessedDeptId;

    @Basic
    @javax.persistence.Column(name = "ASSESSED_DEPT_ID")
    public String getAssessedDeptId() {
        return assessedDeptId;
    }

    public void setAssessedDeptId(String assessedDeptId) {
        this.assessedDeptId = assessedDeptId;
    }
    @ShowInView(name="系统数据来源",operateType= ShowInView.OperateType.SELECT,ccateType="kpiDataSource")
    private String sourceSystem;

    @Basic
    @javax.persistence.Column(name = "SOURCE_SYSTEM")
    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    private String kpiRawValue;

    @Basic
    @javax.persistence.Column(name = "KPI_RAW_VALUE")
    public String getKpiRawValue() {
        return kpiRawValue;
    }

    public void setKpiRawValue(String kpiRawValue) {
        this.kpiRawValue = kpiRawValue;
    }

    private String creator;

    @Basic
    @javax.persistence.Column(name = "CREATOR")
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    private Date createTime;

    @Basic
    @javax.persistence.Column(name = "CREATE_TIME")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    private String updater;

    @Basic
    @javax.persistence.Column(name = "UPDATER")
    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    private Date updateTime;

    @Basic
    @javax.persistence.Column(name = "UPDATE_TIME")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    private String removed;

    @Basic
    @javax.persistence.Column(name = "REMOVED")
    public String getRemoved() {
        return removed;
    }

    public void setRemoved(String removed) {
        this.removed = removed;
    }

    private String assessDept;

    @Basic
    @javax.persistence.Column(name = "ASSESS_DEPT")
    public String getAssessDept() {
        return assessDept;
    }

    public void setAssessDept(String assessDept) {
        this.assessDept = assessDept;
    }

    private String kpiConfirmValue;

    @Basic
    @javax.persistence.Column(name = "KPI_CONFIRM_VALUE")
    public String getKpiConfirmValue() {
        return kpiConfirmValue;
    }

    public void setKpiConfirmValue(String kpiConfirmValue) {
        this.kpiConfirmValue = kpiConfirmValue;
    }

    private String checkMemo;

    @Basic
    @javax.persistence.Column(name = "CHECK_MEMO")
    public String getCheckMemo() {
        return checkMemo;
    }

    public void setCheckMemo(String checkMemo) {
        this.checkMemo = checkMemo;
    }

    private String assessDeptId;

    @Basic
    @javax.persistence.Column(name = "ASSESS_DEPT_ID")
    public String getAssessDeptId() {
        return assessDeptId;
    }

    public void setAssessDeptId(String assessDeptId) {
        this.assessDeptId = assessDeptId;
    }

    private String kpiAdjustValue;

    @Basic
    @javax.persistence.Column(name = "KPI_ADJUST_VALUE")
    public String getKpiAdjustValue() {
        return kpiAdjustValue;
    }

    public void setKpiAdjustValue(String kpiAdjuestValue) {
        this.kpiAdjustValue = kpiAdjuestValue;
    }

    private String kpiAssessValue;

    @Basic
    @javax.persistence.Column(name = "KPI_ASSESS_VALUE")
    public String getKpiAssessValue() {
        return kpiAssessValue;
    }

    public void setKpiAssessValue(String kpiAssessValue) {
        this.kpiAssessValue = kpiAssessValue;
    }

    private String accordingInfo;

    @Basic
    @javax.persistence.Column(name = "ACCORDING_INFO")
    public String getAccordingInfo() {
        return accordingInfo;
    }

    public void setAccordingInfo(String accordingInfo) {
        this.accordingInfo = accordingInfo;
    }

    private KpiAssess kpiAssess;
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "KPI_ASSESS_ID")
    public KpiAssess getKpiAssess() {
        return kpiAssess;
    }

    public void setKpiAssess(KpiAssess kpiAssess) {
        this.kpiAssess = kpiAssess;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KpiDataInfo that = (KpiDataInfo) o;

        if (accordingInfo != null ? !accordingInfo.equals(that.accordingInfo) : that.accordingInfo != null)
            return false;
        if (assessDept != null ? !assessDept.equals(that.assessDept) : that.assessDept != null) return false;
        if (assessDeptId != null ? !assessDeptId.equals(that.assessDeptId) : that.assessDeptId != null) return false;
        if (assessedDept != null ? !assessedDept.equals(that.assessedDept) : that.assessedDept != null) return false;
        if (assessedDeptId != null ? !assessedDeptId.equals(that.assessedDeptId) : that.assessedDeptId != null)
            return false;
        if (checkMemo != null ? !checkMemo.equals(that.checkMemo) : that.checkMemo != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (creator != null ? !creator.equals(that.creator) : that.creator != null) return false;
        if (kpiAdjustValue != null ? !kpiAdjustValue.equals(that.kpiAdjustValue) : that.kpiAdjustValue != null)
            return false;
//        if (kpiAssessId != null ? !kpiAssessId.equals(that.kpiAssessId) : that.kpiAssessId != null) return false;
        if (kpiAssessValue != null ? !kpiAssessValue.equals(that.kpiAssessValue) : that.kpiAssessValue != null)
            return false;
        if (kpiConfirmValue != null ? !kpiConfirmValue.equals(that.kpiConfirmValue) : that.kpiConfirmValue != null)
            return false;
        if (kpiDataInfoId != null ? !kpiDataInfoId.equals(that.kpiDataInfoId) : that.kpiDataInfoId != null)
            return false;
        if (kpiRawValue != null ? !kpiRawValue.equals(that.kpiRawValue) : that.kpiRawValue != null) return false;
        if (removed != null ? !removed.equals(that.removed) : that.removed != null) return false;
        if (sourceSystem != null ? !sourceSystem.equals(that.sourceSystem) : that.sourceSystem != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (updater != null ? !updater.equals(that.updater) : that.updater != null) return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = kpiDataInfoId != null ? kpiDataInfoId.hashCode() : 0;
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (assessedDept != null ? assessedDept.hashCode() : 0);
        result = 31 * result + (assessedDeptId != null ? assessedDeptId.hashCode() : 0);
        result = 31 * result + (sourceSystem != null ? sourceSystem.hashCode() : 0);
        result = 31 * result + (kpiRawValue != null ? kpiRawValue.hashCode() : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updater != null ? updater.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (removed != null ? removed.hashCode() : 0);
        result = 31 * result + (assessDept != null ? assessDept.hashCode() : 0);
        result = 31 * result + (kpiConfirmValue != null ? kpiConfirmValue.hashCode() : 0);
        result = 31 * result + (checkMemo != null ? checkMemo.hashCode() : 0);
        result = 31 * result + (assessDeptId != null ? assessDeptId.hashCode() : 0);
        result = 31 * result + (kpiAdjustValue != null ? kpiAdjustValue.hashCode() : 0);
        result = 31 * result + (kpiAssessValue != null ? kpiAssessValue.hashCode() : 0);
        result = 31 * result + (accordingInfo != null ? accordingInfo.hashCode() : 0);
//        result = 31 * result + (kpiAssessId != null ? kpiAssessId.hashCode() : 0);
        return result;
    }
}
