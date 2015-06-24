package com.wonders.frame.kpi.dao;

import com.wonders.frame.core.dao.GenericRepository;
import com.wonders.frame.kpi.model.bo.KpiDataInfo;
import com.wonders.frame.kpi.model.bo.KpiFormula;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Administrator on 2014/12/18.
 */
public interface FormulaDao extends GenericRepository<KpiFormula, String> {
   @Query("from KpiFormula f where f.kpiAssess.kpiAssessId = :assessId")
   KpiFormula findByAssessId(@Param("assessId")String  assessId);
}
