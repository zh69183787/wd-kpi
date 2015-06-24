package com.wonders.frame.kpi.service.impl;

import java.util.HashMap;
import java.util.List;

import com.wonders.frame.kpi.dao.KpiInfoDao;
import com.wonders.frame.kpi.model.bo.KpiInfo;
import com.wonders.frame.kpi.model.bo.KpiInfoHistory;
import com.wonders.frame.kpi.service.KpiInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2014/12/4.
 */
@Service
public class KpiInfoServiceImpl implements KpiInfoService {
    @Autowired
    private KpiInfoDao kpiInfoDao;
    @Override
    public KpiInfo getKpiInfo(String kpiId) {
        return kpiInfoDao.findOne(kpiId);
    }
	@Override
	public void save(List<KpiInfo> kpiInfos) {
		kpiInfoDao.save(kpiInfos);
	}

    
}
