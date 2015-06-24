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

    <title>部门自评</title>
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
    <script>
        $(function () {
            $("select[name=deptId]").find("option[value=${param.deptId}]").prop("selected",true);
            $("select[name=year]").wrapCategorySelect({type: 'kpiInfo', name: 'year',selectVal:'${param.year}'});
            $(":text[name$=mark]").keyup(function(){
                $("#sum2").text(sum(4));
            });
            $("#sum1").text(sum(2));
<c:if test="${list[0].selfEvaluation.status !='2'}">
            $("#sum2").text(sum(4));
            </c:if>
<c:if test="${list[0].selfEvaluation.status =='2'}">
            $("#sum2").text(sum(5));
            </c:if>
			$("#confirm").click(function () {
                $(":hidden[name$=status]").val("1");
				$("#formSelf").submit();
				
            });
            $("#check").click(function () {
                $(":hidden[name$=status]").val("2");
                $("#formSelf").submit();

            });
            $(":text[name$=mark]").focus(function(){
           		if($(this).val()=="0"){
           			$(this).val("");
           		}
           }); 
            $(":text[name$=mark]").blur(function(){
           		if(parseInt($(this).val())>parseInt($(this).parents("tr:eq(0)").find("td:eq(2)").text())){
           			alert("所填分值不合理");
           			$(this).val("");
                    $("#sum2").text(sum(4));
                    $(this).focus();
           		}	
           });
           
           $("#oututBtn").click(function(){
           		location.href="<%=basePath%>kpiData/exportSelfExcel/"+$("#year").val()+","+$("#deptId").val();
           });
            
        });
        
        function sum(index){
            var value=0;
            $("#selfEvaluationList>tbody>tr:lt(${fn:length(list)})").find("td:eq("+index+")").each(function(i,o){
                //console.log($(o).text());
                if($(o).find(":text").length>0){
                    var v= $(o).find(":text").val()||0;
                    value += parseInt(v);
                }else
                value += parseInt($(o).text()||0);
            });
            return value;
         }
        
    </script>

</head>

<body class="Flow">
<div class="main">
    <div class="ctrl clearfix nwarp">
        <div class="fl"><img src="css/default/images/sideBar_arrow_left.jpg" width="46" height="30" alt="收起"></div>
        <div class="posi fl nwarp">
            <ul>
                <li><a >首页</a></li>
                <li><a >数据填报</a></li>
                <li class="fin">部门自评<div id="myname" style="display:none"></div></li>
            </ul>
        </div>
    </div>
    <div class="pt45">

        <div class="filter">
            <div class="query">
                <div class="filter_search p8"><!--这里根据内容做样式调整。1、筛选：filter_sandglass 2、搜索filter_search 3、提示：.filter_tips 背景图会改变。-->
                    <form action="<%=basePath%>kpiData/self">

                        <table class="nwarp" width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td class="t_r">被考核部门</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${sessionScope.currentUser.companyId <2920 && sessionScope.currentUser.companyId >2925}">

                                            <select name="deptId">
                                                <option>请选择</option>
                                                <c:forEach items="${sessionScope.currentUser.depts}" var="dept">
                                                    <c:if test="${(dept.id >=2920 && dept.id <=2925)}">
                                                        <option value="${dept.id}">${dept.name}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                        </c:when>
                                        <c:otherwise>
                                            ${sessionScope.currentUser.companyName}
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td class="t_r">考核年份</td>
                                <td>
                                    <select name="year" id="year">
                                    </select>
                                </td>
                                <td class="t_r" >
                                    <input type="submit" value="查询" />
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
            <div class="fn clearfix">
                <h5 class="fl"><a class="fl">部门自评</a></h5>
                <input type="button" id="oututBtn" value="导 出"  class="fr" style="margin:0 15px">   &nbsp;
                <%--<input type="button" id="addbtn" value="新 增"  onclick="window.open('${pageContext.request.contextPath}/projectPlan/normManageAdd');" class="fr">--%>
            </div>
        </div>
        <!--Filter End-->
        <!--Table-->
        <div class="mb10">
            <form id="formSelf" action="<%=basePath%>kpiData/self" method="POST">
                <input type="hidden" name="kpiYear" value="${param.year}"/>
                <c:choose>
                    <c:when test="${(sessionScope.currentUser.companyId >=2920 && sessionScope.currentUser.companyId <=2925)}">
                        <input type="hidden" name="kpiDeptId" value="${sessionScope.currentUser.companyId}"/>
                    </c:when>
                    <c:otherwise>
                        <input type="hidden" name="kpiDeptId" value="${param.deptId}"/>
                    </c:otherwise>
                </c:choose>
                <table width="100%"  class="table_1" id="selfEvaluationList">
                    <thead>
                    <tr class="tit">
                        <td class="sort" style="display: none;"></td>
                        <td class="sort" width="200"><a><span class="fl">考核指标</span></a></td>
                        <td class="sort" width="100"><a ><span class="fl">权重</span></a></td>
                        <td class="sort" width="400"><a ><span class="fl">完成情况</span></a></td>
                        <td class="sort" style="text-align: right" width="100"><a ><span class="fl">公司自评分</span></a></td>
                        <td class="sort" style="text-align: right"><a ><span class="fl">审核值</span></a></td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${list}" var="data" varStatus="status">
                        <tr>

                            <td style='display: none;'>
                                <input type="hidden" name="selfEvaluationList[${status.index }].selfEvaluationId" value="${data.selfEvaluation.selfEvaluationId}"/>
                                <input type="hidden" name="selfEvaluationList[${status.index }].kpiAssess.kpiAssessId" value="${data.kpiAssess.kpiAssessId}"/>
                                <input type="hidden" name="selfEvaluationList[${status.index }].status" value="${data.selfEvaluation.status}"/>
                            </td>
                            <td>${data.kpiAssess.kpiInfo.kpiName}</td>
                            <td>${data.kpiAssess.mark}</td>
                            <c:choose>
                                <c:when test="${data.selfEvaluation.status=='1'}">
                                    <td>${data.selfEvaluation.completion}</td>
                                    <td>${data.selfEvaluation.mark}</td>

                                    <td>
                                    <c:if test="${sessionScope.currentUser.isLeader == '1'}">
                                        <input type='text' style="width:300px;" name='selfEvaluationList[${status.index }].checkValue' value='${data.selfEvaluation.checkValue}' />

                                        <input type="hidden" name="selfEvaluationList[${status.index }].mark" value="${data.selfEvaluation.mark}"/>
                                    </c:if>

                                        <c:if test="${sessionScope.currentUser.isLeader != '1'}">

                                            ${data.selfEvaluation.checkValue}
                                        </c:if>
                                    </td>
                                </c:when>
                                <c:when test="${data.selfEvaluation.status=='2'}">
                                    <td>${data.selfEvaluation.completion}</td>
                                    <td>${data.selfEvaluation.mark}</td>
                                    <td>${data.selfEvaluation.checkValue}</td>
                                </c:when>
                                <c:otherwise>
                                    <td><input type='text' style="width:300px;" name='selfEvaluationList[${status.index }].completion' value='${data.selfEvaluation.completion}'/></td>
                                    <td><input type='text' style="width:300px;" name='selfEvaluationList[${status.index }].mark' value='${data.selfEvaluation.mark}' /></td>
                                    <td> ${data.selfEvaluation.checkValue}</td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td style='display: none;'></td>
                        <td></td>
                        <td>权重总计：<span style="display: inline;font-weight: bold;"id="sum1"></span></td>
                        <td></td>
                        <td>总分：<span style="display: inline;font-weight: bold;" id="sum2"></span></td>
                        <td class="sort"></td>
                    </tr>
                    </tbody>
                    <tfoot>
                    
                    <tr class="tfoot">
                        <td colspan="10" style="text-align: center">
<c:if test="${(sessionScope.currentUser.companyId >=2920 && sessionScope.currentUser.companyId <=2925)}">
    <c:if test="${list[0].selfEvaluation == null || list[0].selfEvaluation .status == '0'}">
                            <input type="submit"  value="保 存" >
    </c:if>
    <c:choose>
        <c:when test="${list[0].selfEvaluation .status != '2' && list[0].selfEvaluation .status != '1' }">
            <input type="button"  value="提 交" style="margin:0 15px" id="confirm">
        </c:when>
        <c:when test="${list[0].selfEvaluation .status == '1'&&  sessionScope.currentUser.isLeader == '1'  }">
            <input type="button"  value="提 交" style="margin:0 15px" id="check">
        </c:when>
        <c:otherwise>

        </c:otherwise>
    </c:choose>

    </c:if>
                        </td>
                    </tr>
                    </tfoot>
                </table>
            </form>
        </div>
        <!--Table End-->
    </div>
</div>
</body>
</html>

