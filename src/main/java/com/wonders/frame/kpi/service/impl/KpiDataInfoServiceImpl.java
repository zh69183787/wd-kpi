package com.wonders.frame.kpi.service.impl;

import com.wonders.frame.core.model.vo.SimplePage;
import com.wonders.frame.kpi.dao.KpiAssessDao;
import com.wonders.frame.kpi.dao.KpiDataInfoDao;
import com.wonders.frame.kpi.dao.SelfEvaluationDao;
import com.wonders.frame.kpi.model.bo.KpiAssess;
import com.wonders.frame.kpi.model.bo.KpiDataInfo;
import com.wonders.frame.kpi.model.bo.KpiInfo;
import com.wonders.frame.kpi.model.bo.SelfEvaluation;
import com.wonders.frame.kpi.model.vo.KpiAssessGatherVo;
import com.wonders.frame.kpi.model.vo.KpiDataInfoListVo;
import com.wonders.frame.kpi.service.KpiAssessService;
import com.wonders.frame.kpi.service.KpiDataInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Administrator on 2014/12/4.
 */
@Service
public class KpiDataInfoServiceImpl implements KpiDataInfoService {

    @Autowired
    private KpiDataInfoDao kpiDataInfoDao;
    @Autowired
    private KpiAssessService kpiAssessService;

    @Override
    public void save(List<KpiDataInfo> kpiDataInfos) {
        kpiDataInfoDao.save(kpiDataInfos);
    }

    @Override
    public List<KpiAssessGatherVo> getKpiDataInfo(HashMap queryParams){
        return getKpiDataInfo(queryParams,true);
    }

    @Override
    public List<KpiAssessGatherVo> getKpiDataInfo(HashMap queryParams,boolean isAll) {
//        String sql = "select d.kpi_data_info_id,d.source_system,d.kpi_raw_value,d.removed,d.kpi_confirm_value,d.check_memo," +
//                "d.kpi_adjust_value,d.kpi_assess_value,d.according_info,a.assessed_dept,a.assessed_dept_id,a.assess_dept_id," +
//                "a.assess_dept,a.kpi_assess_id,d.mark,d.status,k.kpi_name,k.kpi_type,k.unit from k_Kpi_data_info d,k_kpi_assess a,k_kpi_info k  where a.kpi_assess_id = d.kpi_assess_id and a.kpi_id = k.kpi_id and d.removed = 0";
    	String sql = "select d.kpi_data_info_id,d.source_system,d.kpi_raw_value,d.removed,d.kpi_confirm_value,d.check_memo," +
                "d.kpi_adjust_value,d.kpi_assess_value,d.according_info,a.assessed_dept,a.assessed_dept_id,a.assess_dept_id," +
                "a.assess_dept,a.kpi_assess_id,d.mark,d.status,k.kpi_name,k.kpi_type,k.unit from k_Kpi_data_info d,k_kpi_assess a,k_kpi_info k  where a.kpi_assess_id = d.kpi_assess_id and a.kpi_id = k.kpi_id and d.removed = '0'";
    	if (queryParams.containsKey("year"))
            sql += " and a.year = :year";
        if (queryParams.containsKey("assessedDeptId"))
            sql += " and a.assessed_Dept_Id like :assessedDeptId";
        if (queryParams.containsKey("assessDeptId"))
            sql += " and a.assess_Dept_Id like :assessDeptId";
        if (queryParams.containsKey("status"))
            sql += " and d.status like :status";
        
        SimplePage page = kpiDataInfoDao.findByPageWithSql(sql, queryParams, Integer.MAX_VALUE, 1);
        List<KpiDataInfo> list = new ArrayList<KpiDataInfo>();
        for (Object o : page.getContent()) {
            Object[] objects = (Object[]) o;
            KpiDataInfo dataInfo = new KpiDataInfo();
            dataInfo.setKpiDataInfoId((String) objects[0]);
            dataInfo.setSourceSystem((String) objects[1]);
            dataInfo.setKpiRawValue((String) objects[2]);
            dataInfo.setRemoved(((Character) objects[3]).toString());
            dataInfo.setKpiConfirmValue((String) objects[4]);
            dataInfo.setCheckMemo((String) objects[5]);
            dataInfo.setKpiAdjustValue((String) objects[6]);
            dataInfo.setKpiAssessValue((String) objects[7]);
            dataInfo.setAccordingInfo((String) objects[8]);
            if (objects[14] != null)
                dataInfo.setMark(((BigDecimal) objects[14]).intValue());
            else
                dataInfo.setMark(0);
            if (objects[15] != null)
            dataInfo.setStatus(((Character) objects[15]).toString());
            
            dataInfo.setKpiAssess(new KpiAssess());
            dataInfo.getKpiAssess().setKpiAssessId((String) objects[13]);
            dataInfo.getKpiAssess().setAssessDeptId((String) objects[11]);
            dataInfo.getKpiAssess().setAssessDept((String) objects[12]);
            dataInfo.getKpiAssess().setAssessedDeptId((String) objects[10]);
            dataInfo.getKpiAssess().setAssessedDept((String) objects[9]);

            dataInfo.getKpiAssess().setKpiInfo(new KpiInfo());
            dataInfo.getKpiAssess().getKpiInfo().setKpiName((String) objects[16]);
            dataInfo.getKpiAssess().getKpiInfo().setKpiType((String) objects[17]);
            dataInfo.getKpiAssess().getKpiInfo().setUnit((String) objects[18]);
            list.add(dataInfo);
        }
        page.setContent(list);
//        if(list ==null ||list.size() < 1)
//        {
//            return null;
//        }else{
            List<KpiAssess> kpiAssessList = null;
            if(isAll){
                HashMap assessMap = new HashMap();
                assessMap.put("year",queryParams.get("year"));
                assessMap.put("assessedDeptId",queryParams.get("assessedDeptId"));
                if(queryParams.containsKey("assessDeptId"))
                    assessMap.put("assessDeptId",queryParams.get("assessDeptId"));
                kpiAssessList = kpiAssessService.getAssesses(assessMap);

            }
            return getKpiAssessGatherVo(page.getContent(), kpiAssessList, null);
//        }


    }

    @Override
    public List<KpiAssessGatherVo> getKpiDataInfo(Integer year, String deptId, String assessDeptId) {
        List<KpiDataInfo> kpiDataInfoList = null;
        if (StringUtils.isNotBlank(assessDeptId))
            kpiDataInfoList = kpiDataInfoDao.findByYearAndDept(year, deptId, assessDeptId);
        else
            kpiDataInfoList = kpiDataInfoDao.findByYearAndDept(year, deptId);

//        List<KpiAssess> kpiAssessList = kpiAssessService.getAssesses(year, deptId, assessDeptId);

//        return getKpiAssessGatherVo(kpiDataInfoList, kpiAssessList,null);
        return  getKpiAssessGatherVo(kpiDataInfoList, null,null);
    }


    public List<KpiAssessGatherVo> getKpiAssessGatherVo(List<KpiDataInfo> kpiDataInfoList, List<KpiAssess> kpiAssessList, List<SelfEvaluation> selfEvaluationList) {
        List<KpiAssessGatherVo> nList = new ArrayList<KpiAssessGatherVo>();
        if (kpiAssessList == null) {
            if (kpiDataInfoList != null) {
                for (KpiDataInfo kpiDataInfo : kpiDataInfoList) {
                    KpiAssessGatherVo vo = new KpiAssessGatherVo();
                    vo.setKpiDataInfo(kpiDataInfo);
                    vo.setKpiAssess(kpiDataInfo.getKpiAssess());
                    vo.setKpiInfo(kpiDataInfo.getKpiAssess().getKpiInfo());
                    nList.add(vo);
                }
            }
            return nList;
        }
        for (KpiAssess kpiAssess : kpiAssessList) {


            KpiAssessGatherVo vo = new KpiAssessGatherVo();
            vo.setKpiAssess(kpiAssess);
            vo.setKpiInfo(kpiAssess.getKpiInfo());
            if (kpiDataInfoList != null) {
                for (KpiDataInfo kpiDataInfo : kpiDataInfoList) {
                    if (kpiAssess.getKpiAssessId().equals(kpiDataInfo.getKpiAssess().getKpiAssessId())) {
                        vo.setKpiDataInfo(kpiDataInfo);
                        break;
                    }
                }
            }

            if (selfEvaluationList != null) {
                for (SelfEvaluation selfEvaluation : selfEvaluationList) {
                    if (kpiAssess.getKpiAssessId().equals(selfEvaluation.getKpiAssess().getKpiAssessId())) {
                        vo.setSelfEvaluation(selfEvaluation);
                        break;
                    }
                }
            }

            nList.add(vo);
        }

        return nList;
    }

    @Override
    public List<KpiDataInfo> getKpiDataInfo(Integer year, String assessedDeptId) {
        return kpiDataInfoDao.findByYearAndDept(year, assessedDeptId);
    }

}
