package com.wonders.frame.kpi.model.bo;

import com.wonders.frame.core.tags.ShowInView;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.sql.Date;

/**
 * Created  on 2014/12/15.
 */

@Entity  
@Table(name = "K_KPI_INFO_LOG")
public class KpiInfoHistory implements java.io.Serializable{
	@ShowInView(noUse=true)  
    private static final long serialVersionUID = -1705674125247143920L;
	
	@ShowInView(name="指标历史ID")  
	private String kpiLogId;
	
    @ShowInView(name="指标ID")
    private String kpiId;
    
    @ShowInView(name="业务类型",operateType= ShowInView.OperateType.SELECT,ccateType="kpiBusinessType")
    private String businessType;
    
    @ShowInView(name="指标类型",operateType= ShowInView.OperateType.SELECT,ccateType="kpiType")   
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
    @Column(name = "KPI_LOG_ID" ,length = 32)
	public String getKpiLogId() {
		return kpiLogId;
	}
	public void setKpiLogId(String kpiLogId) {  
		this.kpiLogId = kpiLogId;
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
    @Column(name = "KPI_ID")
	public String getKpiId() {
		return kpiId;
	}
	public void setKpiId(String kpiId) {
		this.kpiId = kpiId;
	}
	
	@Basic
    @Column(name = "KPI_BUSINESSTYPE")
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
    @Column(name = "KPI_ISASSESS")
	public String getIsAssess() {
		return isAssess;
	}
	public void setIsAssess(String isAssess) {
		this.isAssess = isAssess;
	}
	
	@Basic
    @Column(name = "KPI_YEAR")
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	
	@Basic
    @Column(name = "KPI_STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Basic
    @Column(name = "KPI_UNIT")
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
    @Column(name = "KPI_ALGIRITHM")
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
    @Column(name = "CREATETIME")
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
    @Column(name = "UPDATETIME")
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@Basic
    @Column(name = "REMOVE")
	public String getRemoved() {
		return removed;
	}
	public void setRemoved(String removed) {
		this.removed = removed;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((businessType == null) ? 0 : businessType.hashCode());
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((creator == null) ? 0 : creator.hashCode());
		result = prime * result
				+ ((isAssess == null) ? 0 : isAssess.hashCode());
		result = prime * result
				+ ((kpiAlgorithm == null) ? 0 : kpiAlgorithm.hashCode());
		result = prime * result + ((kpiCode == null) ? 0 : kpiCode.hashCode());
		result = prime * result
				+ ((kpiDefinition == null) ? 0 : kpiDefinition.hashCode());
		result = prime * result + ((kpiId == null) ? 0 : kpiId.hashCode());
		result = prime * result + ((kpiName == null) ? 0 : kpiName.hashCode());
		result = prime * result + ((kpiType == null) ? 0 : kpiType.hashCode());
		result = prime * result
				+ ((kpiLogId == null) ? 0 : kpiLogId.hashCode());
		result = prime * result
				+ ((published == null) ? 0 : published.hashCode());
		result = prime * result + ((removed == null) ? 0 : removed.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
		result = prime * result
				+ ((updateTime == null) ? 0 : updateTime.hashCode());
		result = prime * result + ((updater == null) ? 0 : updater.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
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
		KpiInfoHistory other = (KpiInfoHistory) obj;
		if (businessType == null) {
			if (other.businessType != null)
				return false;
		} else if (!businessType.equals(other.businessType))
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
		if (isAssess == null) {
			if (other.isAssess != null)
				return false;
		} else if (!isAssess.equals(other.isAssess))
			return false;
		if (kpiAlgorithm == null) {
			if (other.kpiAlgorithm != null)
				return false;
		} else if (!kpiAlgorithm.equals(other.kpiAlgorithm))
			return false;
		if (kpiCode == null) {
			if (other.kpiCode != null)
				return false;
		} else if (!kpiCode.equals(other.kpiCode))
			return false;
		if (kpiDefinition == null) {
			if (other.kpiDefinition != null)
				return false;
		} else if (!kpiDefinition.equals(other.kpiDefinition))
			return false;
		if (kpiId == null) {
			if (other.kpiId != null)
				return false;
		} else if (!kpiId.equals(other.kpiId))
			return false;
		if (kpiName == null) {
			if (other.kpiName != null)
				return false;
		} else if (!kpiName.equals(other.kpiName))
			return false;
		if (kpiType == null) {
			if (other.kpiType != null)
				return false;
		} else if (!kpiType.equals(other.kpiType))
			return false;
		if (kpiLogId == null) {
			if (other.kpiLogId != null)
				return false;
		} else if (!kpiLogId.equals(other.kpiLogId))
			return false;
		if (published == null) {
			if (other.published != null)
				return false;
		} else if (!published.equals(other.published))
			return false;
		if (removed == null) {
			if (other.removed != null)
				return false;
		} else if (!removed.equals(other.removed))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (unit == null) {
			if (other.unit != null)
				return false;
		} else if (!unit.equals(other.unit))
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
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}
    
    

}
