package com.wonders.frame.kpi.controller;

import com.wonders.frame.core.controller.AbstractGenericController;
import com.wonders.frame.kpi.dao.KpiAssessDao;
import com.wonders.frame.kpi.model.bo.KpiAssess;
import com.wonders.frame.kpi.model.bo.KpiAssessRevise;
import com.wonders.frame.kpi.model.bo.KpiInfo;
import com.wonders.frame.kpi.model.bo.Page;
import com.wonders.frame.kpi.model.vo.CurrentLogin;

import com.wonders.frame.kpi.model.vo.KpiAssessListVo;
import com.wonders.frame.kpi.service.CaRestAPIService;
import com.wonders.frame.kpi.service.KpiAssessService;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.OutputStream;
import java.util.*;

/**
 * Created by Administrator on 2014/12/2.
 */
@Controller
@RequestMapping("/kpiAssess")

@SessionAttributes("currentUser")
public class KpiAssessController extends AbstractGenericController<KpiAssess> {

	@Autowired
	private KpiAssessService kpiAssessService;

	// @RequestMapping(value = "/index", method = RequestMethod.GET)
	// public String index(Model model) {
	// model.addAttribute("list", kpiAssessService.getAssessesIndex());
	// return "info";
	// }

	@RequestMapping(value = "/assesses", method = RequestMethod.GET)
	public String List(Model model, String deptId, Integer year) {
		HashMap queryParams = new HashMap();
		queryParams.put("year", year);
		queryParams.put("assessedDeptId", deptId);

		// queryParams.put("assessDeptId","1074");
		String companyId = ((CurrentLogin) model.asMap().get("currentUser"))
				.getCompanyId();
		if (StringUtils.isNotBlank(companyId) && !"2518".equals(companyId))
			queryParams.put("assessDeptId",
					((CurrentLogin) model.asMap().get("currentUser"))
							.getCompanyId());

		model.addAttribute("list", kpiAssessService.getAssesses(queryParams));
		return "kpiAssess/kpi_assess_list";
	}

	@RequestMapping(value = "/history", method = RequestMethod.GET)
	public String assessHistory(Model model, KpiAssess kpiAssess, Page page) {
		model.addAttribute("kpiAssess", kpiAssess);
		model.addAttribute("list",kpiAssessService.getAssessandKpiInfo(kpiAssess, page));
		model.addAttribute("page", page);
		return "kpiAssess/kpi_assessHistory";

	}

	@RequestMapping(value = "/reasonAdd", method = RequestMethod.GET)
	public String reasonAdd(Model model, KpiAssess kpiAssess) {  
		model.addAttribute("assess", kpiAssess);
		return "kpiAssess/kpi_reasonAdd";
	}

	@RequestMapping(value = "/revised", method = RequestMethod.GET)
	public String assessRevised(Model model,HttpServletRequest request) {
		String kpiAssessId = request.getQueryString();
		List<Object> object = kpiAssessService.getKpiAssessRevise(kpiAssessId); 
		List<KpiAssessRevise> list = new ArrayList<KpiAssessRevise>();
		for(Object obj : object){
			Object[] objects = (Object[]) obj;
			KpiAssessRevise kpiAssessRevise = new KpiAssessRevise();
			kpiAssessRevise.setKpiName((String) objects[0]);
			kpiAssessRevise.setAssessDept((String) objects[1]);
			kpiAssessRevise.setMark((Integer) objects[2]);
			kpiAssessRevise.setBasicValueRange((String) objects[3]);
			kpiAssessRevise.setReward((String) objects[4]);
			kpiAssessRevise.setMarkStandard((String) objects[5]);
			kpiAssessRevise.setAssessReason((String) objects[6]);
			kpiAssessRevise.setAssessDept((String) objects[7]);
			kpiAssessRevise.setReviseTime((Date) objects[8]);
		
			list.add(kpiAssessRevise);
		}
		model.addAttribute("list", list);
		return "kpiAssess/kpi_assess_revised";  
	}

	@RequestMapping(value = "/reasonSave", method = RequestMethod.POST)
	public String reasonSave(Model model, KpiAssess kpiAssess,KpiAssessListVo kpiAssessListVo) {
		kpiAssess.setAssessReason(kpiAssess.getAssessReason().trim());
		kpiAssessService.saveAssess(kpiAssess);
		return "redirect:/kpiAssess/assesses?year="
		+ kpiAssessListVo.getOldYear() + "&deptId="
		+ kpiAssessListVo.getOldAssessedDeptId();
	}

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form(Model model, HttpServletRequest request) {
		return "kpiAssess/kpi_assess";
	}

	@RequestMapping(value = "/{year},{deptId}", method = RequestMethod.GET)
	public String kpiAssess(Model model, @PathVariable("deptId") String deptId,
			@PathVariable("year") Integer year, HttpServletRequest request) {
		HashMap queryParams = new HashMap();
		queryParams.put("year", year);
		queryParams.put("assessedDeptId", deptId);
		model.addAttribute("list", kpiAssessService.getAssesses(queryParams));
		return "kpiAssess/kpi_assess";
	}

	
	@RequestMapping(value = "/assesses", method = RequestMethod.POST)
	public String save(Model model, KpiAssessListVo kpiAssessListVo) {
		HashMap queryParams = new HashMap();
		queryParams.put("year", kpiAssessListVo.getOldYear());
		queryParams.put("assessedDeptId",
				kpiAssessListVo.getOldAssessedDeptId());
		String companyId = ((CurrentLogin) model.asMap().get("currentUser"))
				.getCompanyId();
		if (StringUtils.isNotBlank(companyId) && !"2518".equals(companyId))
			queryParams.put("assessDeptId",
					((CurrentLogin) model.asMap().get("currentUser"))
							.getCompanyId());
		List<KpiAssess> kpiAssessList = kpiAssessService
				.getAssesses(queryParams);//
		List<KpiAssess> nKpiList = new ArrayList<KpiAssess>();
		if (kpiAssessList == null)
			kpiAssessList = new ArrayList<KpiAssess>();
		for (KpiAssess assess : kpiAssessListVo.getKpiAssesses()) {
			assess.setAssessedDept(kpiAssessListVo.getAssessedDept());
			assess.setAssessedDeptId(kpiAssessListVo.getAssessedDeptId());
			assess.setYear(kpiAssessListVo.getYear());
			if (StringUtils.isNotBlank(assess.getKpiAssessId())) {
				for (KpiAssess oKpiAssess : kpiAssessList) {
					
					if (assess.getKpiAssessId().equals(
							oKpiAssess.getKpiAssessId())) {
						oKpiAssess.setAssessedDept(kpiAssessListVo
								.getAssessedDept());
						oKpiAssess.setAssessedDeptId(kpiAssessListVo
								.getAssessedDeptId());
						oKpiAssess.setYear(kpiAssessListVo.getYear());
						oKpiAssess.setMark(assess.getMark());
						oKpiAssess.setAssessDeptId(assess.getAssessDeptId());
						oKpiAssess.setAssessDept(assess.getAssessDept());
						oKpiAssess.setKpiInfo(new KpiInfo());
						oKpiAssess.getKpiInfo().setKpiId(
								assess.getKpiInfo().getKpiId());
						oKpiAssess.setRemoved(assess.getRemoved());
						
						if(oKpiAssess.getKpiInfo().getKpiId()==null){
							//save
							//istemp
							System.out.println("--");
						}
						nKpiList.add(oKpiAssess);  
						break;
					}
				}
			} else {
				assess.setCreateTime(new Date());
				if(assess.getKpiInfo().getKpiId()==null){
					//save
					//istemp
					System.out.println("\\");
				}
				nKpiList.add(assess);
			}

			assess.setUpdateTime(new Date());
		}
		

		kpiAssessService.save(nKpiList);
		kpiAssessService.saveRevise(nKpiList);
		return "redirect:/kpiAssess/" + kpiAssessListVo.getYear() + ","
				+ kpiAssessListVo.getAssessedDeptId();
	}

	// ListSave
	@RequestMapping(value = "/listAssesses", method = RequestMethod.POST)
	public String listSave(Model model, KpiAssessListVo kpiAssessListVo) {
		HashMap queryParams = new HashMap();
		queryParams.put("year", kpiAssessListVo.getOldYear());
		queryParams.put("assessedDeptId",
				kpiAssessListVo.getOldAssessedDeptId());
		String companyId = ((CurrentLogin) model.asMap().get("currentUser"))
				.getCompanyId();
		List<KpiAssess> kpiAssessList = kpiAssessService
				.getAssesses(queryParams);//

		List<KpiAssess> nKpiList = new ArrayList<KpiAssess>();
		if (kpiAssessList == null)
			kpiAssessList = new ArrayList<KpiAssess>();
		for (KpiAssess assess : kpiAssessListVo.getKpiAssesses()) {
			if (StringUtils.isNotBlank(assess.getKpiAssessId())) {
				for (KpiAssess oKpiAssess : kpiAssessList) { // 信息编号ID
					if (assess.getKpiAssessId().equals(
							oKpiAssess.getKpiAssessId())) {
						if (companyId.equals("2518")) {
							oKpiAssess.setBasicValueRange(assess
									.getBasicValueRange());
							oKpiAssess.setPerformanceValueRange(assess
									.getPerformanceValueRange());
							oKpiAssess.setReward(assess.getReward());
							oKpiAssess
									.setMarkStandard(assess.getMarkStandard());
						} else {

							oKpiAssess.setBasicValueRange(assess
									.getBasicValueRange());
							oKpiAssess.setPerformanceValueRange(assess
									.getPerformanceValueRange());
						}
						nKpiList.add(oKpiAssess);
						break;
					}
				}
			} else {
				assess.setCreateTime(new Date());
				nKpiList.add(assess);
			}

			assess.setUpdateTime(new Date());
		}
		kpiAssessService.save(nKpiList);
		kpiAssessService.saveRevise(nKpiList);

		return "redirect:/kpiAssess/assesses?year="
				+ kpiAssessListVo.getOldYear() + "&deptId="
				+ kpiAssessListVo.getOldAssessedDeptId();
	}


/**
 * 指标延用
 * @param model
 * @param kpiAssess
 * @return
 */
	@RequestMapping(value = "/useKpi", method = RequestMethod.GET)
	public String useKpi(Model model, KpiAssess kpiAssess) {
//		kpiAssess.setAssessedDeptId("2541");
//        kpiAssess.setYear(2016);
        KpiAssess example = new KpiAssess();
        //上年指标
        example.setYear(Calendar.getInstance().get(Calendar.YEAR)-1);
        example.setAssessedDeptId(kpiAssess.getAssessedDeptId());

		List<KpiAssess> assess = kpiAssessService.findAssess(example);
		
		List<KpiAssess> list = new ArrayList<KpiAssess>();  
		for(KpiAssess kpiAssesses : assess){  
			KpiAssess ass = new KpiAssess();
			
            ass.setYear(kpiAssess.getYear());
            ass.setMark(kpiAssesses.getMark());
            ass.setAssessDept(kpiAssesses.getAssessDept());
            ass.setAssessDeptId(kpiAssesses.getAssessDeptId());
            ass.setMarkStandard(kpiAssesses.getMarkStandard());
            ass.setBasicValueRange(kpiAssesses.getBasicValueRange());
            ass.setAssessedDeptId(kpiAssesses.getAssessedDeptId());
            ass.setAssessedDept(kpiAssesses.getAssessedDept());
            ass.setAssessedCompanyId(kpiAssesses.getAssessedCompanyId());
            ass.setAssessedCompany(kpiAssesses.getAssessedCompany());
            ass.setReward(kpiAssesses.getReward());
            ass.setPerformanceValue(kpiAssesses.getPerformanceValue());
            ass.setPerformanceValueRange(kpiAssesses.getPerformanceValueRange());
            ass.setBasicValue(kpiAssesses.getBasicValue());
            ass.setAssessReason(kpiAssesses.getAssessReason());
            ass.setRemoved("0");
            ass.setCreateTime(new Date());
            ass.setUpdateTime(new Date());
            ass.setKpiInfo(new KpiInfo());
            ass.getKpiInfo().setKpiId(kpiAssesses.getKpiInfo().getKpiId());
			
			list.add(ass);
		}	
		kpiAssessService.save(list);
        return "redirect:/kpiAssess/assesses?year="
                + kpiAssess.getYear() + "&deptId="
                + kpiAssess.getAssessedDeptId();
	}

	

	@ModelAttribute
	public void init(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		if (!model.asMap().containsKey("currentUser")) {
			CurrentLogin login = CaRestAPIService.getCurrentLoginInfoFromCa(
					request, response);
			model.addAttribute("currentUser", login);
		}
	}
	
	@RequestMapping(value = "/exportReviseExcel", method = RequestMethod.GET)//List<KpiAssessGatherVo> list, 
	public void exportSelfExcel(Integer rowNumber, HttpServletRequest request,HttpServletResponse response) {
		List<Object> object = kpiAssessService.getKpiAssessRevise(request.getQueryString());
		List<KpiAssessRevise> list = new ArrayList<KpiAssessRevise>();
		for(Object obj : object){
			Object[] objects = (Object[]) obj;
			KpiAssessRevise kpiAssessRevise = new KpiAssessRevise();
			kpiAssessRevise.setKpiName((String) objects[0]);
			kpiAssessRevise.setAssessDept((String) objects[1]);
			kpiAssessRevise.setMark((Integer) objects[2]);
			kpiAssessRevise.setBasicValueRange((String) objects[3]);
			kpiAssessRevise.setReward((String) objects[4]);
			kpiAssessRevise.setMarkStandard((String) objects[5]);
			kpiAssessRevise.setAssessReason((String) objects[6]);
			kpiAssessRevise.setAssessDept((String) objects[7]);
			kpiAssessRevise.setReviseTime((Date) objects[8]);
			list.add(kpiAssessRevise);
		}
		
		
    	String name = "修订导出表.xls";
    	String[] title = {"指标名称","考核部门","分值","基础","嘉奖","评分标准","修订原因","修订部门","修订时间"};
    	try {
			response.setContentType("application/x-download");  
			response.setHeader("Content-Disposition", "attachment;filename="+ new String(name.getBytes("GBK"), "ISO8859-1"));
			// 创建新的Excel 工作簿
			HSSFWorkbook workbook = new HSSFWorkbook();   

			HSSFSheet sheet = workbook.createSheet();

			for (int i = 0; i < title.length; i++) {
				sheet.autoSizeColumn((short) i);
			}

			// 创建第一行并设置标题
			HSSFRow titleRow = sheet.createRow(0);
			for (int i = 0; i < title.length; i++) {
				titleRow.createCell(i).setCellValue(title[i]);
			}
			// 创建单元格并为单元格赋值
			int rowIndex = 0;
			for (int i = 0; i < list.size(); i++) {
				KpiAssessRevise kpiAssessRevise = list.get(i);
				
				HSSFRow row = sheet.createRow(++rowIndex);
				row.createCell(0).setCellValue(
						kpiAssessRevise.getKpiName() == null ? "" : kpiAssessRevise.getKpiName());
							
				row.createCell(1).setCellValue(
						kpiAssessRevise.getAssessDept() == null ? "" : kpiAssessRevise.getAssessDept());
				
				row.createCell(2).setCellValue(
						kpiAssessRevise.getMark().toString() == null ? "" : kpiAssessRevise.getMark().toString());
				
				row.createCell(3).setCellValue(
						kpiAssessRevise.getBasicValueRange() == null ? "" : kpiAssessRevise.getBasicValueRange());
				
				row.createCell(4).setCellValue(
						kpiAssessRevise.getReward() == null ? "" : kpiAssessRevise.getReward());
				
				row.createCell(5).setCellValue(
						kpiAssessRevise.getMarkStandard() == null ? "" : kpiAssessRevise.getMarkStandard());
				
				row.createCell(6).setCellValue(
						kpiAssessRevise.getAssessReason() == null ? "" : kpiAssessRevise.getAssessReason());
				
				row.createCell(7).setCellValue(
						kpiAssessRevise.getAssessDept() == null ? "" : kpiAssessRevise.getAssessDept());
				
				row.createCell(8).setCellValue(
						kpiAssessRevise.getReviseTime().toString() == null ? "" : kpiAssessRevise.getReviseTime().toString());
				
			}

			OutputStream out = response.getOutputStream();   
			workbook.write(out);

			out.flush();
			out.close();

		} catch (Exception e) {
			System.out.println("Exception：" + e);
		}
	}
}
