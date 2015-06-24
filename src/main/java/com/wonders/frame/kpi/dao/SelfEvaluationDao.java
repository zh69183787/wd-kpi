package com.wonders.frame.kpi.dao;

        import com.wonders.frame.core.dao.GenericRepository;
        import com.wonders.frame.kpi.model.bo.SelfEvaluation;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.query.Param;

        import java.util.List;

/**
 * Created by Administrator on 2014/12/7.
 */
public interface SelfEvaluationDao extends GenericRepository<SelfEvaluation, String> {
    @Query("select d from SelfEvaluation d left join d.kpiAssess a where d.removed = 0 and a.year =:year and a.assessedDeptId = :deptId")
    List<SelfEvaluation> findByYearAndDept(@Param("year")Integer year,@Param("deptId")String deptId);
    
}
