package com.wonders.frame.kpi.model.bo;

import java.util.Date;

import javax.persistence.Basic;    
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import com.wonders.frame.core.tags.ShowInView;

/**
 * Created by Administrator on 2014/12/19.
 */
@Entity   
@javax.persistence.Table(name = "K_ASSESS_REVISE")   
public class KpiAssessRevise implements java.io.Serializable{
	@ShowInView(noUse=true)
    private static final long serialVersionUID = -1705674125247143920L;
	
	private String kpiReviseId;    
    private String kpiName;
    private Integer mark;
    private String assessDept;   
    @ShowInView(name="考核部门",operateType= ShowInView.OperateType.SELECT,ccateType="kpiAssessDept")
    private String assessDeptId;
    private String markStandard;
    private String reward;
    private String basicValueRange;
    private String assessReason;
    private String reviseDept;
    private Date reviseTime;
//    private String kpiAssessId;
    
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
    
    private KpiAssess kpiAssess;
    
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "KPI_ASSESS_ID")
	public KpiAssess getKpiAssess() {
		return kpiAssess;
	}
	public void setKpiAssess(KpiAssess kpiAssess) {
		this.kpiAssess = kpiAssess;
	}
	@Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "KPI_REVISE_ID" ,length = 32)
	public String getKpiReviseId() {
		return kpiReviseId;
	}
	public void setKpiReviseId(String kpiReviseId) {
		this.kpiReviseId = kpiReviseId;
	}
	
	
	@Basic
    @javax.persistence.Column(name = "KPI_NAME")
	public String getKpiName() {
		return kpiName;
	}
	public void setKpiName(String kpiName) {
		this.kpiName = kpiName;
	}
	
//	@Basic
//    @javax.persistence.Column(name = "KPI_ASSESS_ID")
//	public String getKpiAssessId() {
//		return kpiAssessId;
//	}
//	public void setKpiAssessId(String kpiAssessId) {
//		this.kpiAssessId = kpiAssessId;
//	}
	@Basic
    @javax.persistence.Column(name = "MARK")
	public Integer getMark() {
		return mark;
	}
	public void setMark(Integer mark) {
		this.mark = mark;
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
    @javax.persistence.Column(name = "REWARD")
	public String getReward() {
		return reward;
	}
	public void setReward(String reward) {
		this.reward = reward;
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
    @javax.persistence.Column(name = "ASSESS_REASON")
	public String getAssessReason() {
		return assessReason;
	}
	public void setAssessReason(String assessReason) {
		this.assessReason = assessReason;
	}
	
	@Basic
    @javax.persistence.Column(name = "REVISE_DEPT")
	public String getReviseDept() {
		return reviseDept;
	}
	public void setReviseDept(String reviseDept) {
		this.reviseDept = reviseDept;
	}
	
	@Basic
    @javax.persistence.Column(name = "REVISE_TIME")
	public Date getReviseTime() {
		return reviseTime;
	}
	public void setReviseTime(Date reviseTime) {
		this.reviseTime = reviseTime;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((assessDept == null) ? 0 : assessDept.hashCode());
		result = prime * result
				+ ((assessDeptId == null) ? 0 : assessDeptId.hashCode());
		result = prime * result
				+ ((assessReason == null) ? 0 : assessReason.hashCode());
		result = prime * result
				+ ((basicValueRange == null) ? 0 : basicValueRange.hashCode());
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((creator == null) ? 0 : creator.hashCode());
		result = prime * result + ((kpiName == null) ? 0 : kpiName.hashCode());
		result = prime * result
				+ ((kpiReviseId == null) ? 0 : kpiReviseId.hashCode());
		result = prime * result + ((mark == null) ? 0 : mark.hashCode());
		result = prime * result
				+ ((markStandard == null) ? 0 : markStandard.hashCode());
		result = prime * result + ((removed == null) ? 0 : removed.hashCode());
		result = prime * result
				+ ((reviseDept == null) ? 0 : reviseDept.hashCode());
		result = prime * result
				+ ((reviseTime == null) ? 0 : reviseTime.hashCode());
		result = prime * result + ((reward == null) ? 0 : reward.hashCode());
		result = prime * result
				+ ((updateTime == null) ? 0 : updateTime.hashCode());
		result = prime * result + ((updater == null) ? 0 : updater.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KpiAssessRevise other = (KpiAssessRevise) obj;
		if (assessDept == null) {
			if (other.assessDept != null)
				return false;
		} else if (!assessDept.equals(other.assessDept))
			return false;
		if (assessDeptId == null) {
			if (other.assessDeptId != null)
				return false;
		} else if (!assessDeptId.equals(other.assessDeptId))
			return false;
		if (assessReason == null) {
			if (other.assessReason != null)
				return false;
		} else if (!assessReason.equals(other.assessReason))
			return false;
		if (basicValueRange == null) {
			if (other.basicValueRange != null)
				return false;
		} else if (!basicValueRange.equals(other.basicValueRange))
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (creator == null) {
			if (other.creator != null)
				return false;
		} else if (!creator.equals(other.creator))
			return false;
		if (kpiName == null) {
			if (other.kpiName != null)
				return false;
		} else if (!kpiName.equals(other.kpiName))
			return false;
		if (kpiReviseId == null) {
			if (other.kpiReviseId != null)
				return false;
		} else if (!kpiReviseId.equals(other.kpiReviseId))
			return false;
		if (mark == null) {
			if (other.mark != null)
				return false;
		} else if (!mark.equals(other.mark))
			return false;
		if (markStandard == null) {
			if (other.markStandard != null)
				return false;
		} else if (!markStandard.equals(other.markStandard))
			return false;
		if (removed == null) {
			if (other.removed != null)
				return false;
		} else if (!removed.equals(other.removed))
			return false;
		if (reviseDept == null) {
			if (other.reviseDept != null)
				return false;
		} else if (!reviseDept.equals(other.reviseDept))
			return false;
		if (reviseTime == null) {
			if (other.reviseTime != null)
				return false;
		} else if (!reviseTime.equals(other.reviseTime))
			return false;
		if (reward == null) {
			if (other.reward != null)
				return false;
		} else if (!reward.equals(other.reward))
			return false;
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else if (!updateTime.equals(other.updateTime))
			return false;
		if (updater == null) {
			if (other.updater != null)
				return false;
		} else if (!updater.equals(other.updater))
			return false;
		return true;
	}
	
	
    
    

}
