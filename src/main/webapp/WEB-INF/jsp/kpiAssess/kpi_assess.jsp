<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>指标配置</title>
<meta charset="utf-8"/>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" href="<%=basePath%>css/formalize.css"/>
<link rel="stylesheet" href="<%=basePath%>css/page.css"/>
<link rel="stylesheet" href="<%=basePath%>css/default/imgs.css"/>
<link rel="stylesheet" href="<%=basePath%>css/reset.css"/>
<link type="text/css" href="<%=basePath%>css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet"/>

<script src="<%=basePath%>js/html5.js"></script>
<script src="<%=basePath%>js/jquery-1.7.1.min.js"></script>
<script src="<%=basePath%>js/jquery-ui-1.8.18.custom.min.js"></script>
<script src="<%=basePath%>js/jquery.formalize.js"></script>
<script src="<%=basePath%>js/custom_widgets.js"></script>
<script src="<%=basePath%>js/kpi_info.js"></script>
<style>
    #kpiResource ul li {
        float: left;
        line-height: 20px;
        margin: 5px;
        width: 200px;
    }

    #kpiResource span {
        display: inline;
    }

    #kpiResource input[type="checkbox"] {
        position: inherit;
        vertical-align: top;
    }
</style>
<script>
    var selectedKpis = [], allKpis = [],kpiInfos = [];
    $(function () {
    	$("#button").click(function(){
    		/* var url= window.location.href;
			var index = url.substring(url.lastIndexOf('/') + 1);//2014,2540
			var param = index.split(",", 4);  
			$("#useYear").val(param[0]);
			alert($("#useYear").val(); */
					
    	});
   		var isleader= $.isleader("${pageContext.request.contextPath}");
        initSelected();
        $("select[name$=assessDeptId]").html($("select[name=template]").html());
        $("select[name$=assessDeptId]").each(function (i, n) {

            $(this).find("option[value=" + $(this).parents("tr:eq(0)").find("td:eq(0)>:hidden:eq(3)").val() + "]").prop("selected", true);
        });
        
        

        $("#useKpi").click(function () {
            if($("select[name=assessedDeptId]").val()=="")
            {
                alert("请选择考核单位");
                return false;
            }
            if($("select[name=year]").val()=="")
            {
                alert("请选择考核年份")
                return false;
            }
            if(window.confirm("是否延用上年"+$("select[name=assessedDeptId]").find("option:selected").text()+"指标")){
                location.href = "<%=basePath%>kpiAssess/useKpi?year="+($("select[name=year]").val()-1)+"&assessedDeptId="+$("select[name=assessedDeptId]").val();
            }
//            $("#useKpiBydiv").dialog({
//                resizable: false,
//                height: 435,
//                width: 1000,
//                modal: true
//            });
        });
       
        
        addEvent();
        $("select[name=businessType]").wrapCategorySelect({type: 'kpiInfo', name: 'businessType'});
        $("select[name=kpiType]").wrapCategorySelect({type: 'kpiInfo', name: 'kpiType'});
        $("select[name=year]").wrapCategorySelect({type: 'kpiInfo', name: 'year', selectVal: '${list[0].year}'});
        $("select[name=assessedDeptId]").find("option[value=${list[0].assessedDeptId}]").prop("selected", true);
        $("select[name=assessedDeptId]").change(function () {

            $(":hidden[name=assessedDept]").val($(this).find("option:selected").text());
        });
        $("select[name=kpiType]").bind("completed", function () {
            var $this = $(this);
            $("#kpiList>tbody>tr").find("td:eq(1)").each(function (i, o) {
                var val = $(o).text();
                $(o).text($this.find("option[value=" + val + "]").text());
            });
        });
       
       
        $("form:eq(1)").submit(function () {
            $.get("<%=basePath%>data/kpiInfo/page?" + $(this).serialize(),function (data) {
                $("#kpiListUl > li").remove();
                allKpis = data.result.content;
                $.each(allKpis, function (i, o) {
                	var $html = $(' <li><input type="checkbox" name="kpiChk"/><span></span></li>');
                    $html.find(":checkbox").val(o.kpiId);
                    $html.find("span").text(o.kpiName);
                    $.each(selectedKpis, function (i, kpi) {
                        if (o.kpiId == kpi.kpiId) {
                            $html.find(":checkbox").prop("checked", true);
                            $html.find(":checkbox").prop("disabled", true);
                            $html.hide();  
                        }
                    });
                    $("#kpiListUl").append($html);
                });
            });
            return false;
        });
        $("#kpiResource").dialog({
            <c:if test="${fn:length(list)>0}">autoOpen: false, </c:if>
            width: 1000,  
            height: 600,  
            title: '指标库',
            modal: true,
            resizable: false,
            open: function () { 
            	/* $("#isTemp").val("0");  */
                $("#formList").submit();
				var url= window.location.href;
				var index = url.substring(url.lastIndexOf('/') + 1);//2014,2540
				var param = index.split(",", 4);  
				$("#useYear").val(param[0]);
            },
            buttons: {    
                "确定": function () {
                		//$.postsave("${pageContext.request.contextPath}","formList","");
		   				setSelectedKpi();
                    $(this).dialog("close");
                },
                "关闭": function () {
                    $(this).dialog("close");
                }
            }, dialogClass: 'noTitleStuff '
        });
		
       $("#isTempSave").click(function(){
       		$(":text[name='createTime']").val((new Date()).getTime());
    	    $(":text[name='updateTime']").val((new Date()).getTime());
    	    $(":text[name='creator']").val(isleader);
            $(":text[name='updater']").val(isleader);
            
        	$.isTempsave("${pageContext.request.contextPath}","formList","");        
        	$("#tempKpi").val("");   
        	$("#formList").submit();
        	var url= window.location.href;
			var index = url.substring(url.lastIndexOf('/') + 1);//2014,2540
			var param = index.split(",", 4);  
			$("#useYear").val(param[0]);
        	   
           /*  setSelectedKpi();    */ 
           
        });
        
        $("#selectKpiBtn").click(function () {
            $(":text[name='year']").val($("#year1").val());
            $("#kpiResource").dialog("open");   
        });

    });

    function dynAddRows(kpiAssess) {
        var row = $("#kpiList>tbody>tr").length;
        var $html = $("<tr><td style='display: none;'></td><td></td><td></td><td></td><td></td><td></td></tr>");
        $html.find("td:eq(0)").html("<input type='hidden' name='kpiAssesses[" + row + "].removed' value='0'><input type='hidden' name='kpiAssesses[" + row + "].kpiInfo.kpiId' value='" + kpiAssess.kpiId + "'><input type='hidden' name='kpiAssesses[" + row + "].kpiAssessId' value='" + kpiAssess.kpiAssessId + "'><input type='hidden' name='assessDeptId'><input type='hidden' name='kpiAssesses[" + row + "].assessDept' value='" + kpiAssess.assessDept + "'>");
        $html.find("td:eq(1)").text(kpiAssess.kpiType);
        $html.find("td:eq(2)").text(kpiAssess.kpiName);
        $html.find("td:eq(3)").html("<input type='text' name='kpiAssesses[" + row + "].mark' value='" + kpiAssess.mark + "'/>");
        $html.find("td:eq(4)").html("<select id='assessDeptId_" + row + "' name='kpiAssesses[" + row + "].assessDeptId'><option></option></select>");
        $html.find("td:eq(5)").html("<a name='delAssess' href='javascript:void(0)'>删除</a>");
        $("#kpiList > tbody").append($html);

        $(":text[name$='mark']").keyup(function () {
            kpiAssess.mark = $(this).val();
        });

        $("#assessDeptId_" + row).html($("select[name=template]").html());
        
        addEvent();
    }

    function addEvent() {
        $("a[name=delAssess]").click(function () {
            delSelectedKpi($(this).parents("tr:eq(0)").find("input[type=hidden]:eq(2)").val());
            $(this).parents("tr:eq(0)").find("input[type=hidden]:eq(0)").val("1");
            $(this).parents("tr:eq(0)").hide();
        });
        $("select[name$=assessDeptId]").change(function () {
            var txt = "";
            if ($(this).val() != "")
                txt = $(this).find("option[value=" + $(this).val() + "]").text();
            $(this).parents("tr:eq(0)").find("td:eq(0)>:hidden:eq(4)").val(txt);
        });
    }

    function initSelected() {
        $("#kpiList>tbody>tr").each(function (i, o) {
            var kpiAssess = {};
            kpiAssess.kpiAssessId = $(o).find("td:eq(0)>:hidden:eq(2)").val();
            kpiAssess.kpiId = $(o).find("td:eq(0)>:hidden:eq(1)").val();
            kpiAssess.mark = $(o).find(":text:eq(0)").val();
            kpiAssess.kpiName = $(o).find("td:eq(2)").text();
            kpiAssess.kpiType = $(o).find("td:eq(0)>:hidden:eq(5)").val();
            ;
            selectedKpis.push(kpiAssess);
        })
    }
	
    function setSelectedKpi() {
         $(":checkbox[name=kpiChk][checked]").each(function (i, o) {
            $.each(allKpis, function (i, kpi) {
                if (kpi.kpiId == $(o).val() && !$(o).prop("disabled")) {
                    var kpiAssess = {};
                    kpiAssess.kpiAssessId = "";
                    kpiAssess.kpiId = kpi.kpiId;
                    kpiAssess.mark = 0;
                    kpiAssess.kpiName = kpi.kpiName;
                    kpiAssess.kpiType = $("#kpiTypeResource > option[value='" + kpi.kpiType + "']").text();
                    selectedKpis.push(kpiAssess);
                    dynAddRows(kpiAssess);
                }  
            });
        });
    } 
//            console.log("选中个数:" + selectedKpis.length);
    

	
    function delSelectedKpi(v) {
        var tmpSelectedKpi = [];
        var index = 0;
        $.each(selectedKpis, function (i, kpi) {
            if (kpi.kpiId != v) {
                tmpSelectedKpi[index] = selectedKpis[i];
                index++;
            }
        });
        selectedKpis = tmpSelectedKpi;
        tmpSelectedKpi = [];
//            console.log("选中个数:" + selectedKpis.length);
    }

    function clearSelectedKpi() {
        selectedKpis = [];
    }
 

</script>

</head>

<body>
<div class="button t_c">
</div>

<div class="main">
    <!--Ctrl-->
    <div class="ctrl clearfix nwarp">
        <div class="fl"><img src="css/default/images/sideBar_arrow_left.jpg" width="46" height="30" alt="收起"></div>
        <div class="posi fl nwarp">
            <ul>
                <li><a href="#">首页</a></li>
                <li>指标配置</li>
            </ul>
        </div>
        <div class="fr lit_nav nwarp">
            <ul>
                <li class="selected"><a class="print" href="#">打印</a></li>
                <li><a class="express" href="#">导出数据</a></li>
                <li class="selected"><a class="table" href="#">表格模式</a></li>
                <li><a class="treeOpen" href="#">打开树</a></li>
                <li><a class="filterClose" href="#">关闭过滤</a></li>
            </ul>
        </div>
    </div>
    <!--Ctrl End-->
    <div class="pt45">
        <!--Filter-->
        <div class="filter">
            <div class="query">
                <div class="filter_search p8">
                    <!--这里根据内容做样式调整。1、筛选：filter_sandglass 2、搜索filter_search 3、提示：.filter_tips 背景图会改变。-->
                    <table class="nwarp" width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td colspan="6" class="t_r">
                                <%--<input type="reset" value="导 入" /><input type="button" value="下载模板"/></td>--%>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="mb10">
                <form action="<%=basePath%>kpiAssess/assesses" method="post" id="form">
                    <input type="hidden" name="assessedDept" value="${list[0].assessedDept}"/>
                    <input type="hidden" name="oldAssessedDept" value="${list[0].assessedDept}"/>
                    <input type="hidden" name="oldAssessedDeptId" value="${list[0].assessedDeptId}"/>
                    <input type="hidden" name="oldYear" value="${list[0].year}"/>
                    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_2">
                        <thead>
                        <th colspan="6" class="f1"><h5 class="fl">指标配置</h5>
                        </th>
                        </thead>
                        <tr>
                            <td class="t_r">指标年份</td>
                            <td>
                                <select name="year" class="input_large" id="year1">
                                </select>
                            </td>
                            <td class="t_r">考核单位</td>
                            <td>

                                <select name="assessedDeptId">
                                    <option>请选择</option>
                                    <c:forEach items="${sessionScope.currentUser.depts}" var="dept">
                                        <c:if test="${(dept.id >=2540 && dept.id <=2545) || dept.id == 2743}">
                                            <option value="${dept.id}">${dept.description}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </td>
                            <td><input type="button" id="selectKpiBtn" value="选择指标"/></td>
                            <td><input type="button" id="useKpi" value="延用上年指标"/></td>
                        </tr>

                        <tr>
                            <td colspan="6">
                                <div class="fn clearfix">
                                    <h5 class="fl"><a class="fl">考核指标列表</a></h5>
                                    <%--<input type="button" id="oututBtn" value="关闭"  class="fr" style="margin:0 15px">&nbsp;--%>
                                </div>
                                <div class="mb10">
                                    <table id="kpiList" width="100%" class="table_1">
                                        <thead>

                                        <tr class="tit">
                                            <td class="sort" style="display: none;"></td>
                                            <td class="sort" width="350"><a><span class="fl">类型</span></a></td>
                                            <td class="sort" width="350"><a><span class="fl">指标</span></a></td>
                                            <td class="sort" width="100"><a><span class="fl">分数</span></a></td>
                                            <td class="sort" width="200"><a><span class="fl">考核部门</span></a></td>
                                            <td class="sort"><a><span class="fl">操作</span></a></td>

                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${list}" var="assess" varStatus="status">
                                            <tr>
                                                <td style='display: none;'><input type='hidden'
                                                                                  name='kpiAssesses[${status.index }].removed'
                                                                                  value='0'><input type="hidden"
                                                                                                   name="kpiAssesses[${status.index }].kpiInfo.kpiId"
                                                                                                   value="${assess.kpiInfo.kpiId}"/><input
                                                        type="hidden" name="kpiAssesses[${status.index }].kpiAssessId"
                                                        value="${assess.kpiAssessId}"/><input type='hidden'
                                                                                              name='assessDeptId'
                                                                                              value='${assess.assessDeptId}'><input
                                                        type='hidden' name='kpiAssesses[${status.index }].assessDept'
                                                        value='${assess.assessDept}'><input type='hidden'
                                                                                            name='kpiAssesses[${status.index }].kpiInfo.kpiType'
                                                                                            value='${assess.kpiInfo.kpiType}'>
                                                </td>
                                                <td>${assess.kpiInfo.kpiType}</td>
                                                <td>${assess.kpiInfo.kpiName}</td>
                                                <td><input type='text' name='kpiAssesses[${status.index }].mark'
                                                           value='${assess.mark}'/></td>
                                                <td><select name='kpiAssesses[${status.index }].assessDeptId'
                                                            value="${assess.assessDeptId}">
                                                    <option></option>
                                                </select></td>
                                                <td><a name='delAssess' href='javascript:void(0)'>删除</a></td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                        <tfoot>
                                    </table>
                                </div>
                            </td>
                        </tr>
                        <%--<input type='text' name='kpiAssesses[${status.index }].assessDept' value='${assess.assessDept}'/>--%>
                    </table>
                    <div class="fn clearfix t_c">
                        <h5 class="fl"><a href="#" class="colSelect fl"></a></h5>
                        <input type="submit" value="保 存"/>&nbsp;
                    </div>
                </form>
            </div>
            <div class="footer"></div>
        </div>
        <!--Filter End-->
    </div>
</div>

<div id="kpiResource" style="display: none;">
    <div class="mb10">
        <form  id="formList">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_2">
                <input type="hidden" name="isTemp" value="0" id="isTemp">
                <thead>
	                <th colspan="5" class="f1"><h5 class="fl">指标库</h5></th>
                </thead>
                <tr>
                    <td class="t_r">业务类型</td>
                    <td>
                        <select name="businessType" class="input_large">  
                        </select>
                    </td>
                    <td class="t_r">指标分类</td>
                    <td>
                        <select id="kpiTypeResource" name="kpiType" class="input_large">
                        </select>
                    </td>
                    <td><input type="submit" value="查询"/></td>
                </tr>
                <tr>
	                <td colspan="5">
		                <ul id="kpiListUl">  
		                </ul>
	                </td>
                </tr>
                <tr>
                    <td class="t_r">临时指标</td>
                    <td ><input type="text"  id="tempKpi" name="kpiName" class="input_large"/> </td>
                    <td ><input type="button"  id="isTempSave" value="添加"  /></td>
                    <td><input  type="hidden" id="useYear" name="year"/></td>
                    <td><input type="hidden" name="removed" class="input_large" value="0"/></td>
                </tr>
                <tr style="display:none;">
                	<td><input type="text" name="createTime" class="input_large" ></td>
                    <td><input type="text" name="updateTime" class="input_large" ></td>
                    <td><input type="text" name="creator" class="input_large" ></td>
                    <td><input type="text" name="updater" class="input_large" ></td>
			 	</tr>
            </table>
        </form>
    </div>
</div>
<%--<div class="mb10" id="useKpiBydiv" style="display:none;">--%>
    <%--<form action="<%=basePath%>kpiAssess/useKpi" method="post">--%>
        <%--<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_2">--%>
            <%--<thead>--%>
            <%--<th colspan="6" class="f1"><h5 class="fl">指标配置</h5>--%>
            <%--</th>--%>
            <%--</thead>--%>
            <%--<tr>--%>
                <%--<td class="t_r">指标年份</td>--%>
                <%--<td>--%>
                    <%--<select name="year" class="input_large">--%>
                    <%--</select>--%>
                <%--</td>--%>
                <%--<td class="t_r">考核单位</td>--%>
                <%--<td>--%>

                    <%--<select name="assessedDeptId1">--%>
                        <%--<option>请选择</option>--%>
                        <%--<c:forEach items="${sessionScope.currentUser.depts}" var="dept">--%>
                            <%--<c:if test="${(dept.id >=2540 && dept.id <=2545) || dept.id == 2743}">--%>
                                <%--<option value="${dept.id}">${dept.description}</option>--%>
                            <%--</c:if>--%>
                        <%--</c:forEach>--%>
                    <%--</select>--%>
                <%--</td>--%>
                <%--<td><input type="submit" value="确定"/></td>--%>
            <%--</tr>--%>
        <%--</table>--%>
    <%--</form>--%>
<%--</div>--%>

<select name="template" style="display: none;">
    <option>请选择</option>
    <c:forEach items="${sessionScope.currentUser.depts}" var="dept">
        <option value="${dept.id}">${dept.name}</option>
    </c:forEach>
</select>
 <div id="Prompt" style="display:none">
            	<p></p>
            </div>
</body>
</html>