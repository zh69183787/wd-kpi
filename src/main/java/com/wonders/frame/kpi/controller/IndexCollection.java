package com.wonders.frame.kpi.controller;

import com.wonders.frame.kpi.model.bo.KpiFormula;
import com.wonders.frame.kpi.model.vo.CurrentLogin;
import com.wonders.frame.kpi.service.CaRestAPIService;
import com.wonders.frame.kpi.service.KpiAssessService;
import com.wonders.frame.kpi.service.KpiFormulaService;
import org.apache.commons.lang.StringUtils;
import org.omg.CORBA.Current;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * Created by Administrator on 2014/12/12.
 */
@Controller
@RequestMapping("/")
@SessionAttributes("currentUser")
public class IndexCollection {
    @Autowired
    private KpiAssessService kpiAssessService;
    @Autowired
    private KpiFormulaService formulaService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model, Integer year, String deptId, @RequestParam(required = false, defaultValue = "1") int pageNum,
                        @RequestParam(required = false, defaultValue = "15") int pageSize) {
        HashMap queryParams = new HashMap();
        if (StringUtils.isNotBlank(deptId))
            queryParams.put("assessedDeptId", deptId);
        if (year != null)
            queryParams.put("year", year);
        model.addAttribute("list", kpiAssessService.getAssessesIndex(queryParams, pageNum, pageSize));
        return "/kpiAssess/kpi_assess_index";
    }

    @RequestMapping(value = "/index2", method = RequestMethod.GET)
    public String index2(Model model, Integer year, String deptId, @RequestParam(required = false, defaultValue = "1") int pageNum,
                        @RequestParam(required = false, defaultValue = "15") int pageSize) {
//        HashMap queryParams = new HashMap();
//        if (StringUtils.isNotBlank(deptId))
//            queryParams.put("assessedDeptId", deptId);
//        if (year != null)
//            queryParams.put("year", year);
//        model.addAttribute("list", kpiAssessService.getAssessesIndex(queryParams, pageNum, pageSize));
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "/formula", method = RequestMethod.GET)
    public String formula(Model model, String kpiAssessId,String value) {
        Double mark = formulaService.calc(kpiAssessId,value);
        model.addAttribute("mark",mark);
        return mark.intValue()+"";
    }

    @ModelAttribute
    public void init(Model model, HttpServletRequest request, HttpServletResponse response) {
        if(!model.asMap().containsKey("currentUser")){

            CurrentLogin login = CaRestAPIService.getCurrentLoginInfoFromCa(request, response);
            model.addAttribute("currentUser", login);
        }
    }
}
