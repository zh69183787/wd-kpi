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
    <style>
        a {
            display: inline;
        }
    </style>
    <script>
        $(function () {
            $("select[name=year]").wrapCategorySelect({type: 'kpiInfo', name: 'year', selectVal: '${param.year}'});
            $("select[name=deptId]").find("option[value=${param.deptId}]").prop("selected", true);


            $("select[name=kpiType]").wrapCategorySelect({type: 'kpiInfo', name: 'kpiType'});
            $("select[name=kpiType]").bind("completed", function () {
                var $this = $(this);
                $("#kpiList>tbody>tr").find("td:eq(0)").each(function (i, o) {
                    var val = $(o).text();
                    $(o).text($this.find("option[value=" + val + "]").text());
                });
            });

            $("#configBtn").click(function(){
                location.href="<%=basePath%>kpiAssess/form";
            });

            $("#confirm").click(function(){

            });

        });
        function history(kpiId,kpiName){
            location.href="<%=basePath%>kpiAssess/history?kpiInfo.kpiName="+kpiName+"&kpiId="+kpiId;
        }

        function revise(KpiAssessId){
            location.href="<%=basePath%>kpiAssess/revised?"+KpiAssessId;
        }
        function reason(kpiAssessId){
            location.href="<%=basePath%>kpiAssess/reasonAdd?"+kpiAssessId;
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
                <li>考核结果</li>
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
                    <%--<table class="nwarp" width="100%" border="0" cellspacing="0" cellpadding="0">--%>
                    <%--<tr>--%>
                    <%--<td colspan="6" class="t_r">--%>
                    <%--<input id="configBtn" type="button" value="配 置" /></td>--%>
                    <%--</tr>--%>
                    <%--</table>--%>
                </div>
            </div>
            <div class="footer"></div>

            <div class="mb10">
                <form  method="get">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_2">
                        <thead>
                        <th colspan="5" class="f1"><h5 class="fl" id="tableTitle">部门考核表</h5>
                        </th>
                        </thead>

                        <tr>
                            <td class="t_r">指标年份</td>
                            <td>
                                <select name="year" class="input_large">
                                </select>
                            </td>
                            <td class="t_r">考核单位</td>
                            <td>
                                <select name="deptId">
                                    <option>请选择</option>
                                    <c:forEach items="${sessionScope.currentUser.depts}" var="dept">
                                        <c:if test="${(dept.id >=2920 && dept.id <=2925)}">
                                            <option value="${dept.id}">${dept.name}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </td>

                            <td>
                                <input type="submit" value="查询"/>
                            </td>
                        </tr>
                    </table>
                </form>

            </div>
            <div class="mb10">


                    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_2">


                        <tr>
                            <td colspan="5">
                                <%--<div class="fn clearfix">--%>
                                    <%--<h5 class="fl"><a class="fl">考核结果表</a></h5>--%>
                                    <%--<input type="button" id="oututBtn" value="关闭"  class="fr" style="margin:0 15px">&nbsp;--%>
                                <%--</div>--%>
                                <div class="mb10">
                                    <table id="kpiList" width="100%" class="table_1">
                                        <thead>
                                        <tr class="tit">
                                            <td rowspan="2" class="sort" style="display: none;"></td>
                                            <td rowspan="2" class="sort" width="65"><a><span class="fl">类型</span></a></td>
                                            <td rowspan="2" class="sort" width="100"><a><span class="fl">指标</span></a></td>
                                            <td rowspan="2" class="sort" width="30"><a><span class="fl">分数</span></a></td>
                                            <td rowspan="2" class="sort" width="40"><a><span class="fl">单位</span></a></td>

                                            <td class="sort" width="240" colspan="3" style="text-align: center;"><a><span class="fl">指标值</span></a></td>


                                            <td rowspan="2" class="sort" width="240"><a><span class="fl">评分标准</span></a></td>
                                            <td rowspan="2" class="sort" width="65"><a><span class="fl">考核部门</span></a></td>
                                            <td rowspan="2" class="sort" width="120"><a><span class="fl">审核值</span></a></td>
                                        </tr>
                                        <tr class="tit">

                                            <td class="sort" width="80"><a><span class="fl">基础</span></a></td>
                                            <td class="sort" width="80"><a><span class="fl">绩效</span></a></td>
                                            <td class="sort" width="80"><a><span class="fl">嘉奖</span></a></td>


                                            <%--<td class="sort"><a><span class="fl">操作</span></a></td>--%>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${list}" var="data" varStatus="status">
                                            <tr>

                                                <td>${data.kpiInfo.kpiType}</td>
                                                <td>${data.kpiInfo.kpiName}</td>
                                                <td>${data.kpiAssess.mark}</td>
                                                <td>${data.kpiInfo.unit}</td>

                                                <td>${data.kpiAssess.basicValueRange}</td>
                                                <td>${data.kpiAssess.performanceValueRange}</td>

                                                <td>${data.kpiAssess.reward}</td>
                                                <td>${data.kpiAssess.markStandard}</td>

                                                <td>${data.kpiAssess.assessDept}</td>
                                                <td>${data.kpiDataInfo.kpiConfirmValue}</td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                        <tfoot>
                                    </table>
                                </div>
                            </td>
                        </tr>
                        <select name="kpiType" style="display: none;"></select>
                    </table>
            </div>
        </div>
        <!--Filter End-->

    </div>

</div>
</body>
</html>
