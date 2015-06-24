package com.wonders.frame.kpi.service.impl;

import com.wonders.frame.kpi.dao.FormulaDao;
import com.wonders.frame.kpi.dao.KpiAssessDao;
import com.wonders.frame.kpi.model.bo.KpiAssess;
import com.wonders.frame.kpi.model.bo.KpiFormula;
import com.wonders.frame.kpi.service.KpiFormulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Created by Administrator on 2014/12/18.
 */
@Service
public class KpiFormulaServiceImpl implements KpiFormulaService {
    @Autowired
    private FormulaDao formulaDao;
    @Autowired
    private KpiAssessDao kpiAssessDao;

    @Override
    public Double calc(String kpiAssessId, String fillValue) {
//        KpiAssess kpiAssess = kpiAssessDao.findOne(kpiAssessId);
//        Integer mark = kpiAssess.getMark();
        KpiFormula formula = formulaDao.findByAssessId(kpiAssessId);
        if(formula== null)
            return 0d;
        Integer mark = formula.getKpiAssess().getMark();
        Double inputVal = Double.parseDouble(fillValue);
        Double finalMark = 0d;
        //<
        if (formula.getMinBasicValue() == null && formula.getMaxBasicValue() != null) {
            if (inputVal <= formula.getMaxBasicValue()) {
                finalMark = Double.valueOf(mark.intValue());
                if (formula.getGapValue() > 0) {
                    finalMark =  calc(formula.getMaxPerformanceValue() ,Double.valueOf(mark.intValue()), formula.getMaxAddMark(),inputVal , formula.getGapValue(), formula.getAvgAddMark(), formula.getAvgSubMark());
//                    Double num = Math.floor(((inputVal * 100 - formula.getMaxPerformanceValue() * 100) / (formula.getGapValue() * 100)));
//                    if (num >= formula.getMaxAddMark()) {
//                        finalMark += formula.getMaxAddMark() * formula.getAvgAddMark();
//                    } else {
//                        finalMark += num * formula.getAvgAddMark();
//                    }
                }
            } else {
                if(formula.getAvgAddMark()<0){
                    finalMark =  calc(inputVal ,Double.valueOf(mark.intValue()), formula.getMaxAddMark(),formula.getMaxPerformanceValue() , formula.getGapValue(), formula.getAvgAddMark(), formula.getAvgSubMark());
                }
            }
        }
        //>
        if (formula.getMaxBasicValue() == null && formula.getMinBasicValue() != null) {
            if (inputVal >= formula.getMinBasicValue()) {
                finalMark = calc(inputVal, Double.valueOf(mark.intValue()), formula.getMaxAddMark(), formula.getMinPerformanceValue(), formula.getGapValue(),formula.getAvgAddMark(),formula.getAvgSubMark());
//                if (formula.getGapValue() > 0) {
//                    Double num = Math.floor(((inputVal * 100 - formula.getMinPerformanceValue() * 100) / (formula.getGapValue() * 100)));
//                    Double addMark = num * formula.getAvgAddMark();
//                    addMark = addMark > formula.getMaxAddMark() ? formula.getMaxAddMark() : addMark;
//                    finalMark += addMark;
//                }
            }
        }
        return finalMark;
    }

    private Double calc(Double value1, Double mark, Double maxAddMark, Double value2, Double gap,Double avgAddMark,Double avgSubMark) {

        if (gap > 0) {
            Double num = Math.floor(((value1 * 100 - value2 * 100) / (gap * 100)));
            Double addMark = num * avgAddMark;
            if (addMark < 0 && avgAddMark>=0)
                addMark = 0d;
            else
                addMark = Math.abs(addMark) >  Math.abs(maxAddMark) ? maxAddMark : addMark;
            mark += addMark;
        }
        return mark;
    }

    public static void main(String[] args) {
//        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//        ac.getBean("formulaService");
    }
}
