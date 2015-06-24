package com.wonders.frame.kpi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wonders.frame.core.dao.GenericRepository;
import com.wonders.frame.kpi.model.bo.KpiAssessRevise;
/**
 * Created by Administrator on 2014/12/21.
 */
public interface KpiAssessReviseDao extends GenericRepository<KpiAssessRevise, String>{
	@Query("select kpiName,assessDept,mark,basicValueRange,reward,markStandard,assessReason,assessDept,reviseTime from KpiAssessRevise where kpiAssess.kpiAssessId = :kpiAssessId")//  
	public List<Object> findBykpiAssessId(@Param("kpiAssessId")String kpiAssessId);  
}
