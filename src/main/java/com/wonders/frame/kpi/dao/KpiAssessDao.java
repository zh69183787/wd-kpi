package com.wonders.frame.kpi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.frame.core.dao.GenericRepository;
import com.wonders.frame.kpi.model.bo.KpiAssess;

/**
 * Created by Administrator on 2014/12/3.
 */
public interface KpiAssessDao extends GenericRepository<KpiAssess, String> {
	
	@Query("select year,assessedDeptId,assessedDept from KpiAssess  where removed = 0 group by (year,assessedDeptId,assessedDept)")
	List<Object[]> findByYearAndDeptId();
	
	@Query("select year,assessedDeptId,assessedDept from KpiAssess  where removed = 0 and year =:year and assessedDeptId = :deptId group by (year,assessedDeptId,assessedDept)")
	List<Object[]> findYearAndDeptId(@Param("year")Integer year,@Param("deptId")String deptId);
	
	@Query("select year,assessedDeptId,assessedDept from KpiAssess  where removed = 0 and year =:year group by (year,assessedDeptId,assessedDept)")
	List<Object[]> findByYear(@Param("year")Integer year);
	
	@Query("select year,assessedDeptId,assessedDept from KpiAssess  where removed = 0 and assessedDeptId = :deptId group by (year,assessedDeptId,assessedDept)")
	List<Object[]> findByDeptId(@Param("deptId")String deptId);
	
	@Transactional
	@Modifying
	@Query("update KpiAssess k set k.assessReason =:assessReason where k.kpiAssessId =:kpiAssessId")  
	public Integer updateAssess(@Param("assessReason")String assessReason,@Param("kpiAssessId")String kpiAssessId);
	
	@Query("select k from KpiAssess k  where k.removed = 0 and k.year = :year and k.assessedDeptId = :assessedDeptId" )
	public List<KpiAssess> findByYearandAssessedDeptId(@Param("year")Integer year,@Param("assessedDeptId")String assessedDeptId);
	
}
