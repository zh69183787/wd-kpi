package com.wonders.frame.kpi.controller;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wonders.frame.kpi.model.vo.CurrentLogin;
import com.wonders.frame.kpi.model.vo.KpiAssessGatherVo;
import com.wonders.frame.kpi.service.CaRestAPIService;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.wonders.frame.core.controller.AbstractGenericController;
import com.wonders.frame.kpi.model.bo.KpiInfo;
import com.wonders.frame.kpi.model.bo.KpiInfoHistory;
import com.wonders.frame.kpi.service.KpiInfoService;

/**
 * Created by Administrator on 2014/12/1.
 */
@Controller
@RequestMapping("/kpiInfo")
@SessionAttributes("currentUser")
public class KpiInfoController extends AbstractGenericController<KpiInfo> {
	

    @RequestMapping(value="/form",method= RequestMethod.GET)
    public String form(Model model,HttpServletRequest request) {

        return "kpiInfo/kpi_info";  
    }
    
    @RequestMapping(value="/kpiInfos",method= RequestMethod.GET)
    public String kpiInfo(Model model,HttpServletRequest request) {
    	  
        return "kpiInfo/kpi_info_list";
    }
    
    @RequestMapping(value="/detail/{kpiId}",method= RequestMethod.GET)
    public String kpiInfoDetail(Model model,@PathVariable("kpiId")String kpiId) {
    	KpiInfo kpiInfo = kpiInfoService.getKpiInfo(kpiId);
    	model.addAttribute("kpi",kpiInfo);
        return "kpiInfo/kpi_info_detail";  
    }
    
    @RequestMapping(value="/kpiInfoedit/{kpiId}",method= RequestMethod.GET)
    public String kpiInfoEdit(Model model,@PathVariable("kpiId")String kpiId) {
    	KpiInfo kpiInfo = kpiInfoService.getKpiInfo(kpiId);
    	model.addAttribute("kpi",kpiInfo);
        return "kpiInfo/kpi_info_edit";
    }
    
    @RequestMapping(value="/kpiInfohistory/{kpiId}",method= RequestMethod.GET)  
    public String kpiInfoHistory(Model model,HttpServletRequest request,@PathVariable("kpiId")String kpiId) {
    	model.addAttribute("kpiId", kpiId);
        return "kpiInfo/kpi_info_history";
    }
    
    
    @RequestMapping(value="/index",method= RequestMethod.GET)
    public String kpiIndex(Model model,HttpServletRequest request) {
        return "index";
    }
    
    @Autowired
    private KpiInfoService kpiInfoService;
    @RequestMapping(value="/{kpiId}",method= RequestMethod.GET)
    @ResponseBody
    public HashMap<String,Object> getKpiInfo(Model model,@PathVariable("kpiId")String kpiId) {
    	HashMap<String,Object> result = new HashMap<String ,Object>();
    	KpiInfo kpiInfo = kpiInfoService.getKpiInfo(kpiId);
    	result.put("success", true);
    	result.put("result", kpiInfo);
        return result;

    }

    @ModelAttribute
    public void init(Model model, HttpServletRequest request, HttpServletResponse response) {
        if(!model.asMap().containsKey("currentUser")){

            CurrentLogin login = CaRestAPIService.getCurrentLoginInfoFromCa(request, response);
            model.addAttribute("currentUser", login);
        }
    }
}
