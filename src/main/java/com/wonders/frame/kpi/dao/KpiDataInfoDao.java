package com.wonders.frame.kpi.dao;

import com.wonders.frame.core.dao.GenericRepository;
import com.wonders.frame.kpi.model.bo.KpiDataInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Administrator on 2014/12/4.
 */
public interface KpiDataInfoDao extends GenericRepository<KpiDataInfo, String> {

    @Query("select d from KpiDataInfo d left join d.kpiAssess a where d.removed = 0 and a.year =:year and a.assessedDeptId = :deptId")
    List<KpiDataInfo> findByYearAndDept(@Param("year")Integer year,@Param("deptId")String deptId);

    @Query("select d from KpiDataInfo d left join d.kpiAssess a where d.removed = 0 and a.year =:year and a.assessedDeptId = :deptId and a.assessDeptId = :assessDeptId")
    List<KpiDataInfo> findByYearAndDept(@Param("year")Integer year,@Param("deptId")String deptId,@Param("assessDeptId") String assessDeptId);

}
