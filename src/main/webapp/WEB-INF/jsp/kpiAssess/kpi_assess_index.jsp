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

    <title>kpi管理</title>
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
        .tit>td{
            width:200px;
        }
        #indexBody a{
            margin:2px 5px;
            display: inline;
        }
        h6>input{
            float:right;
            margin:2px 5px;
        }
    </style>
    <script>
        $(function(){
            $("select[name=year]").wrapCategorySelect({type: 'kpiInfo', name: 'year', selectVal: '${param.year}'});
            $("select[name=deptId]").find("option[value=${param.deptId}]").prop("selected",true);

            $("#kpiBtn").click(function(){
                location.href = "<%=basePath%>kpiInfo/kpiInfos";
            });

            $("#renewBtn").click(function(){
                location.href = "<%=basePath%>kpiAssess/form";
            });



        });


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
                <li>部门指标管理</li>
            </ul>
        </div>
    </div>
    <!--Ctrl End-->
    <div class="pt45">
        <!--Filter-->
        <div class="filter">
            <div class="mb10">

                <form action="<%=basePath%>index" method="get">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_2">
                        <thead>
                        <th colspan="5" class="f1"><h5 class="fl" id="tableTitle"></h5>
                        </th>
                        </thead>
                        <tr>
                            <td class="t_r">指标年份</td>
                            <td>
                                <select name="year">
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

                        <tr>
                            <td colspan="5">
                                <div class="fn clearfix">
                                    <h5 class="fl"><a class="fl">部门指标管理</a></h5>
                                    <c:if test="${sessionScope.currentUser.companyId == 2518}">
                                    <input type="button" onclick="location.href='<%=basePath%>kpiAssess/form'" value="新增指标配置"  class="fr" style="margin:0 15px">&nbsp;
                                    </c:if>
                                </div>
                                <div class="mb10">
                                    <table id="kpiList" width="100%" class="table_1">
                                        <thead>
                                        <tr class="tit">
                                            <td class="sort"><a><span class="fl">指标年份</span></a></td>
                                            <td class="sort"><a><span class="fl">考核单位</span></a></td>
                                            <td class="sort"><a><span class="fl">操作</span></a></td>
                                        </tr>
                                        </thead>
                                        <tbody id="indexBody">
                                        <c:forEach items="${list.content}" var="assess">
                                            <tr>
                                                <td>${assess.year}</td>
                                                <td>${assess.assessedDept}</td>
                                                <td><!--  style="text-align: center" -->    
                                                    <c:if test="${sessionScope.currentUser.companyId == 2518}">
                                                        <a href="kpiAssess/${assess.year},${assess.assessedDeptId}">配置</a>
                                                    </c:if>
                                                    <c:if test="${((sessionScope.currentUser.companyId <2920 && sessionScope.currentUser.companyId >2925)) || sessionScope.currentUser.companyId == 2518}">
                                                       <a href="kpiAssess/assesses?year=${assess.year}&deptId=${assess.assessedDeptId}">修改</a>
</c:if>
                                                </td>

                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                        <tfoot>
                                    </table>
                                </div>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
            <div class="footer"></div>

        </div>
        <!--Filter End-->
    </div>
</div>
</body>
</html>
