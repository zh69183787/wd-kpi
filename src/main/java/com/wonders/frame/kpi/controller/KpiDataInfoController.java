package com.wonders.frame.kpi.controller;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wonders.frame.kpi.service.*;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.wonders.frame.core.controller.AbstractGenericController;
import com.wonders.frame.kpi.model.bo.KpiAssess;
import com.wonders.frame.kpi.model.bo.KpiDataInfo;
import com.wonders.frame.kpi.model.bo.SelfEvaluation;
import com.wonders.frame.kpi.model.vo.CurrentLogin;
import com.wonders.frame.kpi.model.vo.KpiAssessGatherVo;
import com.wonders.frame.kpi.model.vo.KpiDataInfoListVo;
import com.wonders.frame.kpi.model.vo.SelfEvaluationListVo;

/**
 * Created by Administrator on 2014/12/4.
 */
@Controller
@RequestMapping("/kpiData")
@SessionAttributes("currentUser")
public class KpiDataInfoController extends AbstractGenericController<KpiAssess> {

    @Autowired
    private KpiDataInfoService kpiDataInfoService;
    @Autowired
    private KpiAssessService kpiAssessService;
    @Autowired
    private SelfEvaluationService selfEvaluationService;
    @Autowired
    private KpiFormulaService kpiFormulaService;

    @RequestMapping(value = "/raw", method = RequestMethod.GET)
    public String raw(Model model, String deptId, Integer year) {
        if (year != null && StringUtils.isNotBlank(deptId)) {
            HashMap queryParams = new HashMap();
            queryParams.put("year", year);
            queryParams.put("assessedDeptId", deptId);
            queryParams.put("status", KpiDataInfo.TO_ADJUSTMENT);
            model.addAttribute("list", kpiDataInfoService.getKpiDataInfo(queryParams, false));
        }

        return "kpiData/kpi_raw_data";
    }

    @RequestMapping(value = "/assess", method = RequestMethod.GET)
    public String assess(Model model, String deptId, Integer year, String flag) {
        CurrentLogin login = ((CurrentLogin) model.asMap().get("currentUser"));
        if (year != null && StringUtils.isNotBlank(deptId)) {
            HashMap queryParams = new HashMap();
            queryParams.put("year", year);
                queryParams.put("assessedDeptId", deptId);
            if (!"2518".equals(login.getCompanyId()))
            queryParams.put("assessDeptId", login.getCompanyId());
            if ("1".equals(login.getIsLeader())) {
                queryParams.put("status", KpiDataInfo.LEADER_CHECK);
                model.addAttribute("list", kpiDataInfoService.getKpiDataInfo(queryParams, false));
            } else {
                queryParams.put("status", KpiDataInfo.TO_FILL);
                List<KpiAssessGatherVo> list = kpiDataInfoService.getKpiDataInfo(year, deptId, login.getCompanyId());
                List<KpiAssessGatherVo> vo = kpiDataInfoService.getKpiDataInfo(queryParams, true);
                if (list.size() == vo.size()) {
                    model.addAttribute("list", kpiDataInfoService.getKpiDataInfo(queryParams, false));
                } else {
                    model.addAttribute("list", vo);
                }
            }
        }

        return "kpiData/kpi_data_assess";
    }

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public String check(Model model, String deptId, Integer year) {
        if (year != null && StringUtils.isNotBlank(deptId)) {
            HashMap queryParams = new HashMap();
            queryParams.put("year", year);
            queryParams.put("assessedDeptId", deptId);
//      queryParams.put("assessDeptId",assessDeptId);
            queryParams.put("status", KpiDataInfo.TO_EXAMINE);
            model.addAttribute("list", kpiDataInfoService.getKpiDataInfo(queryParams, false));
        }
        return "kpiData/kpi_data_check";
    }


    @RequestMapping(value = "/result", method = RequestMethod.GET)
    public String result(Model model, String deptId, Integer year) {
        HashMap queryParams = new HashMap();
        queryParams.put("year", year);
        queryParams.put("assessedDeptId", deptId);
        List<KpiAssess> kpiAssessList = kpiAssessService.getAssesses(queryParams);
        List<KpiDataInfo> kpiDataInfoList =kpiDataInfoService.getKpiDataInfo(year,deptId);
        List<KpiAssessGatherVo> list = kpiDataInfoService.getKpiAssessGatherVo(kpiDataInfoList,kpiAssessList,null);
        model.addAttribute("list",list);
        return "kpiData/kpi_data_result";
    }

    @RequestMapping(value = "/self", method = RequestMethod.GET)
    public String self(Model model, String deptId, Integer year) {
        HashMap queryParams = new HashMap();
        if (year != null) {
            queryParams.put("year", year);
        } else {
            queryParams.put("year", Calendar.getInstance().get(Calendar.YEAR));
        }
        if (StringUtils.isNotBlank(deptId)) {
            queryParams.put("assessedDeptId", deptId);
        } else {
            queryParams.put("assessedDeptId", ((CurrentLogin) model.asMap().get("currentUser")).getCompanyId());
        }
        model.addAttribute("list", selfEvaluationService.getSelfEvaluation(queryParams));
        return "kpiData/self_evaluation";
    }


    @RequestMapping(value = "/self/list", method = RequestMethod.GET)
    public String selfDetail(Model model, String deptId, Integer year, String isConfirm) {
        if (year != null && StringUtils.isNotBlank(deptId)) {
            HashMap queryParams = new HashMap();
            queryParams.put("year", year);
            queryParams.put("assessedDeptId", deptId);
            if ("2518".equals(((CurrentLogin) model.asMap().get("currentUser")).getCompanyId())) {
                queryParams.put("status", "2");
            }
//        queryParams.put("isConfirm", isConfirm);
            model.addAttribute("list", selfEvaluationService.getSelfEvaluation(queryParams));
        }
        return "kpiData/self_evaluation_detail";
    }

    @RequestMapping(value = "/self", method = RequestMethod.POST)
    public String selfSave(Model model, SelfEvaluationListVo selfEvaluationListVo, String kpiDeptId, Integer kpiYear) {

        HashMap queryParams = new HashMap();
        queryParams.put("year", kpiYear);
        queryParams.put("assessedDeptId", kpiDeptId);
        List<KpiAssessGatherVo> kpiAssessGatherVoList = selfEvaluationService.getSelfEvaluation(queryParams);
        List<SelfEvaluation> nSelfEvaluationList = new ArrayList<SelfEvaluation>();


        for (SelfEvaluation selfEvaluation : selfEvaluationListVo.getSelfEvaluationList()) {

            if (StringUtils.isBlank(selfEvaluation.getSelfEvaluationId())) {
                selfEvaluation.setCreateTime(new Date());
                nSelfEvaluationList.add(selfEvaluation);
                if (StringUtils.isBlank(selfEvaluation.getStatus()))
                    selfEvaluation.setStatus("0");
            } else {
                for (KpiAssessGatherVo gatherVo : kpiAssessGatherVoList) {
                    if (gatherVo.getSelfEvaluation().getSelfEvaluationId().equals(selfEvaluation.getSelfEvaluationId())) {
                        gatherVo.getSelfEvaluation().setCompletion(selfEvaluation.getCompletion());
                        gatherVo.getSelfEvaluation().setMark(selfEvaluation.getMark());
                        gatherVo.getSelfEvaluation().setCheckValue(selfEvaluation.getCheckValue());
                        gatherVo.getSelfEvaluation().setStatus(selfEvaluation.getStatus());//
                        nSelfEvaluationList.add(gatherVo.getSelfEvaluation());
                        break;
                    }
                }

            }
            selfEvaluation.setUpdateTime(new Date());
            selfEvaluation.setRemoved("0");
        }
        selfEvaluationService.save(nSelfEvaluationList);
        return "redirect:/kpiData/self?year=" + kpiYear + "&deptId=" + kpiDeptId;
    }


    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public String checkSave(Model model, KpiDataInfoListVo kpiDataInfoListVo, String kpiDeptId, Integer kpiYear, String assessDeptId) throws UnsupportedEncodingException {
        HashMap queryParams = new HashMap();
        queryParams.put("year", kpiYear);
        queryParams.put("assessedDeptId", kpiDeptId);
        queryParams.put("status", KpiDataInfo.TO_EXAMINE);
        List<KpiAssessGatherVo> kpiDataList = kpiDataInfoService.getKpiDataInfo(queryParams);

        List<KpiDataInfo> nKpiDataList = new ArrayList<KpiDataInfo>();
        for (KpiDataInfo kpiDataInfo : kpiDataInfoListVo.getKpiDataInfos()) {

            if (StringUtils.isBlank(kpiDataInfo.getKpiDataInfoId())) {
                kpiDataInfo.setCreateTime(new Date());
                if (StringUtils.isNotBlank(kpiDataInfo.getStatus()))
                    kpiDataInfo.setStatus(KpiDataInfo.TO_EXAMINE);
                else
                    kpiDataInfo.setStatus(KpiDataInfo.TO_FILL);

                nKpiDataList.add(kpiDataInfo);
            } else {
                for (KpiAssessGatherVo gatherVo : kpiDataList) {
                    if (gatherVo.getKpiDataInfo() != null && gatherVo.getKpiDataInfo().getKpiDataInfoId().equals(kpiDataInfo.getKpiDataInfoId())) {
                        gatherVo.getKpiDataInfo().setCheckMemo(kpiDataInfo.getCheckMemo());
                        gatherVo.getKpiDataInfo().setKpiConfirmValue(kpiDataInfo.getKpiConfirmValue());
                        gatherVo.getKpiDataInfo().setMark(kpiDataInfo.getMark());
                        gatherVo.getKpiDataInfo().setStatus(kpiDataInfo.getStatus());
                        nKpiDataList.add(gatherVo.getKpiDataInfo());
                        break;
                    }
                }
            }
            kpiDataInfo.setUpdateTime(new Date());
            kpiDataInfo.setRemoved("0");
        }
        kpiDataInfoService.save(nKpiDataList);
        if(KpiDataInfo.EXAMINE.equals(nKpiDataList.get(0).getStatus())){
            String url = "http://10.1.48.16:8080/workflow/contact-deptContact/kpiAdd.action?operateType=add&steplabel=Begin&deptId=2518&deptName="+URLEncoder.encode(nKpiDataList.get(0).getKpiAssess().getAssessedDept(),"UTF-8")+"&title="+URLEncoder.encode(kpiYear+"年"+kpiDataList.get(0).getKpiAssess().getAssessedDept()+"绩效考核结果","UTF-8")+"&url="+URLEncoder.encode("http://10.1.48.16:7001/kpi/kpiData/result?year="+kpiYear+"&deptId="+kpiDeptId,"UTF-8");
            return "redirect:"+url;
        }
        return "redirect:/kpiData/check?year=" + kpiYear + "&deptId=" + kpiDeptId + "&assessDeptId=" + assessDeptId;
    }


    @RequestMapping(value = "/assess", method = RequestMethod.POST)
    public String assessSave(Model model, KpiDataInfoListVo kpiDataInfoListVo, String kpiDeptId, Integer kpiYear, String assessDeptId) throws UnsupportedEncodingException {
        CurrentLogin login = ((CurrentLogin) model.asMap().get("currentUser"));
        HashMap queryParams = new HashMap();
        queryParams.put("year", kpiYear);
        queryParams.put("assessedDeptId", kpiDeptId);
        queryParams.put("assessDeptId", assessDeptId);
        if ("1".equals(login.getIsLeader())) {
            queryParams.put("status", KpiDataInfo.LEADER_CHECK);
        } else {
            queryParams.put("status", KpiDataInfo.TO_FILL);
        }

        List<KpiAssessGatherVo> kpiDataList = kpiDataInfoService.getKpiDataInfo(queryParams);
        List<KpiDataInfo> nKpiDataList = new ArrayList<KpiDataInfo>();
        for (KpiDataInfo kpiDataInfo : kpiDataInfoListVo.getKpiDataInfos()) {

            if (StringUtils.isBlank(kpiDataInfo.getKpiDataInfoId())) {
                if (StringUtils.isBlank(kpiDataInfo.getStatus()))
                    kpiDataInfo.setStatus(KpiDataInfo.TO_FILL);
                kpiDataInfo.setCreateTime(new Date());
                nKpiDataList.add(kpiDataInfo);
            } else {
                for (KpiAssessGatherVo gatherVo : kpiDataList) {
                    if (gatherVo.getKpiDataInfo() != null && gatherVo.getKpiDataInfo().getKpiDataInfoId().equals(kpiDataInfo.getKpiDataInfoId())) {
                        gatherVo.getKpiDataInfo().setAccordingInfo(kpiDataInfo.getAccordingInfo());
                        gatherVo.getKpiDataInfo().setKpiAssessValue(kpiDataInfo.getKpiAssessValue());
                        gatherVo.getKpiDataInfo().setMark(kpiFormulaService.calc(kpiDataInfo.getKpiAssess().getKpiAssessId(),kpiDataInfo.getKpiAssessValue()).intValue());
                        gatherVo.getKpiDataInfo().setStatus(kpiDataInfo.getStatus());
                        nKpiDataList.add(gatherVo.getKpiDataInfo());
                        break;
                    }
                }

            }
            kpiDataInfo.setUpdateTime(new Date());
            kpiDataInfo.setRemoved("0");
        }
        kpiDataInfoService.save(nKpiDataList);
        if ("1".equals(login.getIsLeader())) {
            if(nKpiDataList.get(0).getStatus().equals(KpiDataInfo.TO_EXAMINE)) {
                String url = "";
                url = "http://10.1.48.16:8080/workflow/contact-deptContact/kpiAdd.action?operateType=add&steplabel=Begin&deptId=2518&deptName=" + URLEncoder.encode("企业管理发展部","UTF-8") + "&title=" + URLEncoder.encode(kpiDataList.get(0).getKpiAssess().getAssessedDept() + "绩效考核情况","UTF-8") + "&url=" + URLEncoder.encode("http://10.1.48.16:7001/kpi/kpiData/check?year=" + kpiYear + "&deptId=" + kpiDeptId,"UTF-8");
                return "redirect:" + url;
            }
        }
        return "redirect:/kpiData/assess?year=" + kpiYear + "&deptId=" + kpiDeptId + "&assessDeptId=" + assessDeptId;
    }

    @RequestMapping(value = "/check/list", method = RequestMethod.GET)
    public String detail(Model model, String deptId, Integer year) {
        if (year != null && StringUtils.isNotBlank(deptId)) {
            HashMap queryParams = new HashMap();
            queryParams.put("year", year);
            queryParams.put("assessedDeptId", deptId);
            model.addAttribute("list", kpiDataInfoService.getKpiDataInfo(queryParams));
        }
        return "kpiData/kpi_data_detail";
    }

    @RequestMapping(value = "/raw/{year},{deptId}", method = RequestMethod.POST)
    public String save(Model model, KpiDataInfoListVo kpiDataInfoListVo, @PathVariable("deptId") String deptId, @PathVariable("year") Integer year) {
        HashMap queryParams = new HashMap();
        queryParams.put("year", year);
        queryParams.put("assessedDeptId", deptId);
        queryParams.put("status", KpiDataInfo.TO_ADJUSTMENT);
        List<KpiAssessGatherVo> kpiDataList = kpiDataInfoService.getKpiDataInfo(queryParams);
        List<KpiDataInfo> nKpiDataList = new ArrayList<KpiDataInfo>();
        for (KpiDataInfo kpiDataInfo : kpiDataInfoListVo.getKpiDataInfos()) {

            if (StringUtils.isBlank(kpiDataInfo.getKpiDataInfoId())) {
                if (StringUtils.isBlank(kpiDataInfo.getStatus()))
                    kpiDataInfo.setStatus(KpiDataInfo.TO_ADJUSTMENT);
                kpiDataInfo.setCreateTime(new Date());
                nKpiDataList.add(kpiDataInfo);
            } else {
                for (KpiAssessGatherVo gatherVo : kpiDataList) {
                    if (gatherVo.getKpiDataInfo() != null && gatherVo.getKpiDataInfo().getKpiDataInfoId().equals(kpiDataInfo.getKpiDataInfoId())) {
                        gatherVo.getKpiDataInfo().setKpiAdjustValue(kpiDataInfo.getKpiAdjustValue());
                        gatherVo.getKpiDataInfo().setStatus(kpiDataInfo.getStatus());
                        nKpiDataList.add(gatherVo.getKpiDataInfo());
                        break;
                    }
                }

            }
            kpiDataInfo.setUpdateTime(new Date());
            kpiDataInfo.setRemoved("0");
        }
        kpiDataInfoService.save(nKpiDataList);
        return "redirect:/kpiData/raw?year=" + year + "&deptId=" + deptId;
    }

    @ModelAttribute
    public void init(Model model, HttpServletRequest request, HttpServletResponse response) {
        if (!model.asMap().containsKey("currentUser")) {

            CurrentLogin login = CaRestAPIService.getCurrentLoginInfoFromCa(request, response);
            model.addAttribute("currentUser", login);
        }
    }

//    @RequestMapping(value = "/exportSelfExcel", method = RequestMethod.GET)
//	public void exportSelfExcel(Model model,String deptId, Integer year,HttpServletResponse response) {
//    	HashMap queryParams = new HashMap();
//    	queryParams.put("yaer",year);
//    	queryParams.put("assessedDeptId", deptId);
//    	List<KpiAssessGatherVo> kpiAssessGatherVo = selfEvaluationService.getSelfEvaluation(queryParams);
//    	exportExcel(kpiAssessGatherVo,"ss",23);
//	}

    @RequestMapping(value = "/exportSelfExcel/{year},{deptId}", method = RequestMethod.GET)
//List<KpiAssessGatherVo> list,
    public void exportSelfExcel(Integer rowNumber, @PathVariable("year") Integer year, @PathVariable("deptId") String deptId, HttpServletRequest request, HttpServletResponse response) {
        HashMap queryParams = new HashMap();
        String name = "文件.xls";
        String[] title = {"考核指标", "权重", "完成情况", "公司自评分","审核值"};
        queryParams.put("year", year);
        queryParams.put("assessedDeptId", deptId);
        List<KpiAssessGatherVo> list = selfEvaluationService.getSelfEvaluation(queryParams);
        try {
            response.setContentType("application/x-download");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(name.getBytes("GBK"), "ISO8859-1"));
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
                KpiAssessGatherVo kpiAssessGatherVo = list.get(i);

                HSSFRow row = sheet.createRow(++rowIndex);
                row.createCell(0).setCellValue(

                        kpiAssessGatherVo.getKpiInfo().getKpiName() == null ? "" : kpiAssessGatherVo.
                                getKpiInfo().getKpiName());

                row.createCell(1).setCellValue(
                        kpiAssessGatherVo.getKpiAssess().getMark().toString() == null ? "" : kpiAssessGatherVo.
                                getKpiAssess().getMark().toString());

                row.createCell(2).setCellValue(
                        kpiAssessGatherVo.getSelfEvaluation().getCompletion() == null ? "" : kpiAssessGatherVo.
                                getSelfEvaluation().getCompletion());
                row.createCell(3).setCellValue(
                        kpiAssessGatherVo.getSelfEvaluation().getMark().toString() == null ? "" : kpiAssessGatherVo.
                                getSelfEvaluation().getMark().toString());
                row.createCell(4).setCellValue(
                        kpiAssessGatherVo.getSelfEvaluation().getCheckValue().toString() == null ? "" : kpiAssessGatherVo.
                                getSelfEvaluation().getCheckValue().toString());
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
