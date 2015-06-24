package com.wonders.frame.kpi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.wonders.frame.core.dao.GenericRepository;
import com.wonders.frame.kpi.model.bo.KpiInfo;

/**
 * Created by Administrator on 2014/12/1.
 */

public interface KpiInfoDao extends GenericRepository<KpiInfo, String> {
	
	
}
