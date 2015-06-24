package com.wonders.frame.kpi.model.bo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2014/12/7.
 */
@Entity
@Table(name = "K_SELF_EVALUATION")
public class SelfEvaluation {
    private String selfEvaluationId;  
    private KpiAssess kpiAssess;   
    private String completion;
//    private String selfAssessment;
    private String creator;
    private Date createTime;
    private String updater;
    private Date updateTime;
    private String removed;
    private Integer mark;
    private String kpiAssesId;
    private String status;
    private Integer checkValue;

    @Basic
    @Column(name = "CHECK_VALUE")
    public Integer getCheckValue() {
        return checkValue;
    }

    public void setCheckValue(Integer checkValue) {
        this.checkValue = checkValue;
    }

    @Basic
    @Column(name = "STATUS")
    public String getStatus() {
		return status;
	}

	public void setStatus(String isConfirm) {
		this.status = isConfirm;
	}

	@Basic
    @javax.persistence.Column(name = "MARK")
    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }



    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "SELF_EVALUATION_ID" ,length = 32)
    public String getSelfEvaluationId() {
        return selfEvaluationId;
    }

    public void setSelfEvaluationId(String selfEvaluationId) {
        this.selfEvaluationId = selfEvaluationId;
    }

//    @Basic
//    @Column(name = "KPI_ASSESS_ID")
//    public String getKpiAssessId() {
//        return kpiAssessId;
//    }
//
//    public void setKpiAssessId(String kpiEvaluationId) {
//        this.kpiAssessId = kpiEvaluationId;
//    }

    @Basic
    @Column(name = "COMPLETION")
    public String getCompletion() {
        return completion;
    }

    public void setCompletion(String completion) {
        this.completion = completion;
    }

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "KPI_ASSESS_ID")
    public KpiAssess getKpiAssess() {
        return kpiAssess;
    }

    public void setKpiAssess(KpiAssess kpiAssess) {
        this.kpiAssess = kpiAssess;
    }

//    @Basic
//    @Column(name = "SELF_ASSESSMENT")
//    public String getSelfAssessment() {
//        return selfAssessment;
//    }
//
//    public void setSelfAssessment(String selfAssessment) {
//        this.selfAssessment = selfAssessment;
//    }

    @Basic
    @Column(name = "CREATOR")
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Basic
    @Column(name = "CREATE_TIME")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "UPDATER")
    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    @Basic
    @Column(name = "UPDATE_TIME")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "REMOVED")
    public String getRemoved() {
        return removed;
    }

    public void setRemoved(String removed) {
        this.removed = removed;
    }

    
    @Transient
    public String getKpiAssesId() {
		return kpiAssesId;
	}

	public void setKpiAssesId(String kpiAssesId) {
		this.kpiAssesId = kpiAssesId;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SelfEvaluation that = (SelfEvaluation) o;

        if (completion != null ? !completion.equals(that.completion) : that.completion != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (creator != null ? !creator.equals(that.creator) : that.creator != null) return false;
//        if (kpiAssessId != null ? !kpiAssessId.equals(that.kpiAssessId) : that.kpiAssessId != null)
//            return false;
        if (removed != null ? !removed.equals(that.removed) : that.removed != null) return false;
//        if (selfAssessment != null ? !selfAssessment.equals(that.selfAssessment) : that.selfAssessment != null)
//            return false;
        if (selfEvaluationId != null ? !selfEvaluationId.equals(that.selfEvaluationId) : that.selfEvaluationId != null)
            return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (updater != null ? !updater.equals(that.updater) : that.updater != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = selfEvaluationId != null ? selfEvaluationId.hashCode() : 0;
//        result = 31 * result + (kpiAssessId != null ? kpiAssessId.hashCode() : 0);
        result = 31 * result + (completion != null ? completion.hashCode() : 0);
//        result = 31 * result + (selfAssessment != null ? selfAssessment.hashCode() : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updater != null ? updater.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (removed != null ? removed.hashCode() : 0);
        return result;
    }
}
