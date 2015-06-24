package com.wonders.frame.kpi.model.bo;

import com.wonders.frame.core.tags.ShowInView;  
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;  

import java.sql.Date;

/**
 * Created by Administrator on 2014/12/1.
 */
@Entity  
@Table(name = "K_KPI_INFO")                  
public class KpiInfo  implements java.io.Serializable {  
   
    @ShowInView(noUse=true)    
    private static final long serialVersionUID = -1705674125247143920L;
    
    @ShowInView(name="指标ID")
    private String kpiId;
    
    @ShowInView(name="业务分类",operateType= ShowInView.OperateType.SELECT,ccateType="kpiBusinessType")
    private String businessType;
    
    @ShowInView(name="指标分类",operateType= ShowInView.OperateType.SELECT,ccateType="kpiType")   
    private String kpiType;
    
    @ShowInView(name="指标名称")   
    private String kpiName;
    
    @ShowInView(name="指标代码")
    private String kpiCode;
    
    @ShowInView(name="是否考核",operateType= ShowInView.OperateType.SELECT,ccateType="isAssess")   
    private String isAssess;
    
    @ShowInView(name="启用年份",operateType= ShowInView.OperateType.SELECT,ccateType="year")
    private Integer year;
    
    @ShowInView(name="指标状态",operateType= ShowInView.OperateType.SELECT,ccateType="status")
    private String status;  
    
    @ShowInView(name="单位")
    private String unit;
    
    @ShowInView(name="指标定义")
    private String kpiDefinition;
    
    @ShowInView(name="指标算法")
    private String kpiAlgorithm;
    
    @ShowInView(name="临时指标")
    private String isTemp;
    
    @ShowInView(noUse=true)
    private String published;
    @ShowInView(noUse=true)
    private String creator;
    @ShowInView(noUse=true)
    private Date createTime;
    @ShowInView(noUse=true)
    private String updater;
    @ShowInView(noUse=true)
    private Date updateTime;
    @ShowInView(noUse=true)
    private String removed;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "KPI_ID" ,length = 32)
    public String getKpiId() {
        return kpiId;
    }

    public void setKpiId(String kpiId) {
        this.kpiId = kpiId;
    }
    
    @Basic
    @javax.persistence.Column(name = "IS_TEMP")
    public String getIsTemp() {
		return isTemp;
	}

	public void setIsTemp(String isTemp) {
		this.isTemp = isTemp;
	}

    @Basic
    @Column(name = "BUSINESS_TYPE")
    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    @Basic
    @Column(name = "KPI_TYPE")
    public String getKpiType() {
        return kpiType;
    }

    public void setKpiType(String kpiType) {
        this.kpiType = kpiType;
    }

    @Basic
    @Column(name = "KPI_NAME")
    public String getKpiName() {
        return kpiName;
    }

    public void setKpiName(String kpiName) {
        this.kpiName = kpiName;
    }

    @Basic
    @Column(name = "KPI_CODE")
    public String getKpiCode() {
        return kpiCode;
    }

    public void setKpiCode(String kpiCode) {
        this.kpiCode = kpiCode;
    }

    @Basic
    @Column(name = "IS_ASSESS")
    public String getIsAssess() {
        return isAssess;
    }

    public void setIsAssess(String isAssess) {
        this.isAssess = isAssess;
    }

    @Basic
    @Column(name = "YEAR")
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Basic
    @Column(name = "STATUS")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "UNIT")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Basic
    @Column(name = "KPI_DEFINITION")
    public String getKpiDefinition() {
        return kpiDefinition;
    }

    public void setKpiDefinition(String kpiDefinition) {
        this.kpiDefinition = kpiDefinition;
    }

    @Basic
    @Column(name = "KPI_ALGORITHM")
    public String getKpiAlgorithm() {
        return kpiAlgorithm;
    }

    public void setKpiAlgorithm(String kpiAlgorithm) {
        this.kpiAlgorithm = kpiAlgorithm;
    }

    @Basic
    @Column(name = "PUBLISHED")
    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KpiInfo kpiInfo = (KpiInfo) o;

        if (businessType != null ? !businessType.equals(kpiInfo.businessType) : kpiInfo.businessType != null)
            return false;
        if (createTime != null ? !createTime.equals(kpiInfo.createTime) : kpiInfo.createTime != null) return false;
        if (creator != null ? !creator.equals(kpiInfo.creator) : kpiInfo.creator != null) return false;
        if (isAssess != null ? !isAssess.equals(kpiInfo.isAssess) : kpiInfo.isAssess != null) return false;
        if (kpiAlgorithm != null ? !kpiAlgorithm.equals(kpiInfo.kpiAlgorithm) : kpiInfo.kpiAlgorithm != null)
            return false;
        if (kpiCode != null ? !kpiCode.equals(kpiInfo.kpiCode) : kpiInfo.kpiCode != null) return false;
        if (kpiDefinition != null ? !kpiDefinition.equals(kpiInfo.kpiDefinition) : kpiInfo.kpiDefinition != null)
            return false;
        if (kpiId != null ? !kpiId.equals(kpiInfo.kpiId) : kpiInfo.kpiId != null) return false;
        if (kpiName != null ? !kpiName.equals(kpiInfo.kpiName) : kpiInfo.kpiName != null) return false;
        if (kpiType != null ? !kpiType.equals(kpiInfo.kpiType) : kpiInfo.kpiType != null) return false;
        if (published != null ? !published.equals(kpiInfo.published) : kpiInfo.published != null) return false;
        if (removed != null ? !removed.equals(kpiInfo.removed) : kpiInfo.removed != null) return false;
        if (status != null ? !status.equals(kpiInfo.status) : kpiInfo.status != null) return false;
        if (unit != null ? !unit.equals(kpiInfo.unit) : kpiInfo.unit != null) return false;
        if (updateTime != null ? !updateTime.equals(kpiInfo.updateTime) : kpiInfo.updateTime != null) return false;
        if (updater != null ? !updater.equals(kpiInfo.updater) : kpiInfo.updater != null) return false;
        if (year != null ? !year.equals(kpiInfo.year) : kpiInfo.year != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = kpiId != null ? kpiId.hashCode() : 0;  
        result = 31 * result + (businessType != null ? businessType.hashCode() : 0);
        result = 31 * result + (kpiType != null ? kpiType.hashCode() : 0);
        result = 31 * result + (kpiName != null ? kpiName.hashCode() : 0);
        result = 31 * result + (kpiCode != null ? kpiCode.hashCode() : 0);
        result = 31 * result + (isAssess != null ? isAssess.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        result = 31 * result + (kpiDefinition != null ? kpiDefinition.hashCode() : 0);
        result = 31 * result + (kpiAlgorithm != null ? kpiAlgorithm.hashCode() : 0);
        result = 31 * result + (published != null ? published.hashCode() : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updater != null ? updater.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (removed != null ? removed.hashCode() : 0);
        return result;
    }
}
