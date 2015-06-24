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
    <style>
        #kpiResource ul li {
            float: left;
            line-height: 20px;
            margin: 5px;
            width: 160px;
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
        $(function () {
            $("select[name=deptId]").find("option[value=${param.deptId}]").prop("selected",true);
            $("select[name=year]").wrapCategorySelect({type: 'kpiInfo', name: 'year',selectVal:'${param.year}'});
            $("#sum1").text(sum(2));
            <c:if test="${list[0].selfEvaluation.status !='2'}">
            $("#sum2").text(sum(4));
            </c:if>
            <c:if test="${list[0].selfEvaluation.status =='2'}">
            $("#sum2").text(sum(5));
            </c:if>


        });
        
        function sum(index){
            var value=0;
            $("#selfEvaluationList>tbody>tr:lt(${fn:length(list)})").find("td:eq("+index+")").each(function(i,o){
//                if($(o).find(":text").length>0){
//                    value += parseInt($(o).find(":text").val());
//                }else

                value += parseInt($(o).text());
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
                <li class="fin">部门自评查询<div id="myname" style="display:none"></div></li>
            </ul>
        </div>
    </div>
    <div class="pt45">

        <div class="filter">
            <div class="query">
                <div class="filter_search p8"><!--这里根据内容做样式调整。1、筛选：filter_sandglass 2、搜索filter_search 3、提示：.filter_tips 背景图会改变。-->
                    <form action="<%=basePath%>kpiData/self/list">

                        <%--<input type="hidden" name="assessDeptId" value="${param.assessDeptId}"/>--%>
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
                                            <input type="hidden" name="deptId" value="${sessionScope.currentUser.companyId}"/>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td class="t_r">考核年份</td>
                                <td>
                                    <select name="year" >
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
                <h5 class="fl"><a class="fl">部门自评表</a></h5>
                <%--<input type="button" id="oututBtn" value="导 出"  class="fr" style="margin:0 15px">   &nbsp;--%>
                <%--<input type="button" id="addbtn" value="新 增"  onclick="window.open('${pageContext.request.contextPath}/projectPlan/normManageAdd');" class="fr">--%>
            </div>
        </div>
        <!--Filter End-->
        <!--Table-->
        <div class="mb10">
            <form>
                <input type="hidden" name="kpiYear" value="${param.year}"/>

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
                                <input type="hidden" name="selfEvaluationList[${status.index }].kpiDataInfoId" value="${data.kpiDataInfo.kpiDataInfoId}"/>
                                <input type="hidden" name="selfEvaluationList[${status.index }].kpiAssess.kpiAssessId" value="${data.kpiAssess.kpiAssessId}"/>
                            </td>
                            <td>${data.kpiAssess.kpiInfo.kpiName}</td>
                            <td >${data.kpiAssess.mark}</td>
                            <td>${data.selfEvaluation.completion}</td><!--input type='text' readonly style="width:300px;border-left:0px;border-top:0px;border-right:0px;border-bottom:1px;" name='selfEvaluationList[${status.index }].completion' value='${data.selfEvaluation.completion}'/  -->
                            <td>
                                <c:if test="${data.selfEvaluation.mark == null}">0</c:if><c:if test="${data.selfEvaluation.mark != null}">${data.selfEvaluation.mark}</c:if>

                            </td>
                            <td>
                                <c:if test="${data.selfEvaluation.checkValue == null}">0</c:if><c:if test="${data.selfEvaluation.checkValue != null}">${data.selfEvaluation.checkValue}</c:if>

                            </td>
                            <!--input type='text' readonly style="width:300px;border-left:0px;border-top:0px;border-right:0px;border-bottom:1px;" name='selfEvaluationList[${status.index }].mark' value='${data.selfEvaluation.mark}' /  -->
                        </tr>
                    </c:forEach>
                    <tr>
                        <td style='display: none;'></td>
                        <td></td>
                        <td>权重总计：<span style="display: inline;font-weight: bold;"id="sum1"></span></td>
                        <td></td>
                        <td>总分：<span style="display: inline;font-weight: bold;" id="sum2"></span></td>
                        <td></td>
                    </tr>
                    </tbody>
                    <tfoot>
                    
                    <tr class="tfoot">
                        <td colspan="10" style="text-align: center">
                            <%--<input type="button"  value="关闭" style="margin:0 15px" onclick="window.close();">--%>
                        </td>
                    </tr>
                    </tfoot>
                </table>
            </form>
        </div>
        <!--Table End-->
    </div>
</div>
<select style="display: none;" name="sourceSystem">

</select>
<select style="display: none;" name="kpiType">

</select>
</body>
</html>

