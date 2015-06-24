package com.wonders.frame.kpi.model.bo;

import com.wonders.frame.core.tags.ShowInView;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Administrator on 2014/12/18.
 */
@Entity
@Table(name = "K_KPI_FORMULA")
public class KpiFormula implements Serializable {

    @ShowInView(noUse = true)
    private static final long serialVersionUID = -1705674125247143920L;
    private String formulaId;
    private Double avgAddMark;
    private Double gapValue;
    private Double avgSubMark;
    private Double maxBasicValue;
    private Double minBasicValue;
    private Double maxPerformanceValue;
    private Double minPerformanceValue;
    private Double maxAddMark;
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
    @Column(name = "FORMULA_ID", length = 32)
    public String getFormulaId() {
        return formulaId;
    }

    public void setFormulaId(String formluaId) {
        this.formulaId = formluaId;
    }

    @Basic
    @Column(name = "AVG_ADD_MARK")
    public Double getAvgAddMark() {
        return avgAddMark;
    }

    public void setAvgAddMark(Double avgAddMark) {
        this.avgAddMark = avgAddMark;
    }

    @Basic
    @Column(name = "GAP_VALUE")
    public Double getGapValue() {
        return gapValue;
    }

    public void setGapValue(Double gapValue) {
        this.gapValue = gapValue;
    }

    @Basic
    @Column(name = "AVG_SUB_MARK")
    public Double getAvgSubMark() {
        return avgSubMark;
    }

    public void setAvgSubMark(Double avgSubMark) {
        this.avgSubMark = avgSubMark;
    }

    @Basic
    @Column(name = "MAX_BASIC_VALUE")
    public Double getMaxBasicValue() {
        return maxBasicValue;
    }

    public void setMaxBasicValue(Double maxBasicValue) {
        this.maxBasicValue = maxBasicValue;
    }

    @Basic
    @Column(name = "MIN_BASIC_VALUE")
    public Double getMinBasicValue() {
        return minBasicValue;
    }

    public void setMinBasicValue(Double minBasicValue) {
        this.minBasicValue = minBasicValue;
    }

    @Basic
    @Column(name = "MAX_PERFORMANCE_VALUE")
    public Double getMaxPerformanceValue() {
        return maxPerformanceValue;
    }

    public void setMaxPerformanceValue(Double maxPerformanceValue) {
        this.maxPerformanceValue = maxPerformanceValue;
    }

    @Basic
    @Column(name = "MIN_PERFORMANCE_VALUE")
    public Double getMinPerformanceValue() {
        return minPerformanceValue;
    }

    public void setMinPerformanceValue(Double minPerformanceValue) {
        this.minPerformanceValue = minPerformanceValue;
    }

    @Basic
    @Column(name = "MAX_ADD_MARK")
    public Double getMaxAddMark() {
        return maxAddMark;
    }

    public void setMaxAddMark(Double maxAddMarkNum) {
        this.maxAddMark = maxAddMarkNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KpiFormula that = (KpiFormula) o;

        if (avgAddMark != null ? !avgAddMark.equals(that.avgAddMark) : that.avgAddMark != null) return false;
        if (avgSubMark != null ? !avgSubMark.equals(that.avgSubMark) : that.avgSubMark != null) return false;
        if (formulaId != null ? !formulaId.equals(that.formulaId) : that.formulaId != null) return false;
        if (gapValue != null ? !gapValue.equals(that.gapValue) : that.gapValue != null) return false;
        if (maxAddMark != null ? !maxAddMark.equals(that.maxAddMark) : that.maxAddMark != null)
            return false;
        if (maxBasicValue != null ? !maxBasicValue.equals(that.maxBasicValue) : that.maxBasicValue != null)
            return false;
        if (maxPerformanceValue != null ? !maxPerformanceValue.equals(that.maxPerformanceValue) : that.maxPerformanceValue != null)
            return false;
        if (minBasicValue != null ? !minBasicValue.equals(that.minBasicValue) : that.minBasicValue != null)
            return false;
        if (minPerformanceValue != null ? !minPerformanceValue.equals(that.minPerformanceValue) : that.minPerformanceValue != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = formulaId != null ? formulaId.hashCode() : 0;
        result = 31 * result + (avgAddMark != null ? avgAddMark.hashCode() : 0);
        result = 31 * result + (gapValue != null ? gapValue.hashCode() : 0);
        result = 31 * result + (avgSubMark != null ? avgSubMark.hashCode() : 0);
        result = 31 * result + (maxBasicValue != null ? maxBasicValue.hashCode() : 0);
        result = 31 * result + (minBasicValue != null ? minBasicValue.hashCode() : 0);
        result = 31 * result + (maxPerformanceValue != null ? maxPerformanceValue.hashCode() : 0);
        result = 31 * result + (minPerformanceValue != null ? minPerformanceValue.hashCode() : 0);
        result = 31 * result + (maxAddMark != null ? maxAddMark.hashCode() : 0);
        return result;
    }
}
