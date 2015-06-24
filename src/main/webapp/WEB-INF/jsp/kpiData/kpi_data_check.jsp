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

    <title>考核数据审核</title>
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
            $("select[name=kpiType]").wrapCategorySelect({type: 'kpiInfo', name: 'kpiType'});

            $("select[name=sourceSystem]").bind("completed",function(){
                var $this = $(this);
                $("#kpiDataList>tbody>tr").find("td:eq(5)").each(function(i,o){
                    var val = $(o).text();
                    if(val!="")
                        $(o).text($this.find("option[value="+val+"]").text());
                });
            });

            $("select[name=kpiType]").bind("completed",function(){
                var $this = $(this);
                $("#kpiDataList>tbody>tr").find("td:eq(1)").each(function(i,o){
                    var val = $(o).text();
                    $(o).text($this.find("option[value="+val+"]").text());
                });
            });
            
            $("#confirm").click(function () {
				$(":hidden[name$=status]").val("4");
				$("#formCheck").submit();
				
            });


            $(":text[name$=kpiConfirmValue]").blur(function(){
                var tdMark = $(this).parents("tr:eq(0)").find(":text[name$=mark]");
                var value = $(this).parents("tr:eq(0)").find(":text[name$=kpiConfirmValue]").val();
                var assessId = $(this).parents("tr:eq(0)").find(":hidden[name$=kpiAssessId]").val();
                $.get("<%=basePath%>formula?kpiAssessId="+assessId+"&value="+value,function(data){
                    tdMark.val(data);
                })
            })
        });
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
                <li class="fin">考核数据审核<div id="myname" style="display:none"></div></li>
            </ul>
        </div>
    </div>
    <div class="pt45">

        <div class="filter">
            <div class="query">
                <div class="filter_search p8"><!--这里根据内容做样式调整。1、筛选：filter_sandglass 2、搜索filter_search 3、提示：.filter_tips 背景图会改变。-->
                    <form action="<%=basePath%>kpiData/check">
                        <input type="hidden" name="assessDeptId" value="${param.assessDeptId}"/>
                        <table class="nwarp" width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td class="t_r">被考核部门</td>
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
                <h5 class="fl"><a class="fl">考核数据审核</a></h5>
                <%--<input type="button" id="oututBtn" value="导 出"  class="fr" style="margin:0 15px">   &nbsp;--%>
                <%--<input type="button" id="addbtn" value="新 增"  onclick="window.open('${pageContext.request.contextPath}/projectPlan/normManageAdd');" class="fr">--%>
            </div>
        </div>
        <!--Filter End-->
        <!--Table-->
        <div class="mb10">
            <form id="formCheck" action="<%=basePath%>kpiData/check" method="POST">
                <input type="hidden" name="kpiYear" value="${param.year}"/>
                <input type="hidden" name="kpiDeptId" value="${param.deptId}"/>
                <input type="hidden" name="assessDeptId" value="${param.assessDeptId}"/>
                <table width="100%"  class="table_1" id="kpiDataList">
                    <thead>
                    <tr class="tit">
                        <td class="sort" style="display: none;"></td>
                        <td class="sort"><a><span class="fl">类型</span></a></td>
                        <td class="sort"><a><span class="fl">指标</span></a></td>
                        <td class="sort"><a ><span class="fl">单位</span></a></td>
                        <%--<td class="sort"><a ><span class="fl">调整值</span></a></td>--%>
                        <td class="sort"><a class="asc desc">分值</a></td>
                        <td class="sort"><a ><span class="fl">指标填报</span></a></td>
                        <%--<td class="sort"><a class="asc desc">填报依据</a></td>--%>
                        <td class="sort"><a class="asc desc">考核部门</a></td>
                        <td class="sort"><a ><span class="fl">系统参考值</span></a></td>
                        <td class="sort"><a ><span class="fl">系统来源</span></a></td>
                        <td class="sort"><a class="asc desc">审核值</a></td>
                        <td class="sort"><a class="asc desc">审核说明</a></td>
                        <td class="sort"><a class="asc desc">得分</a></td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${list}" var="data" varStatus="status">
                        <tr>
                            <td style='display: none;'>
                                <input type="hidden" name="kpiDataInfos[${status.index }].kpiDataInfoId" value="${data.kpiDataInfo.kpiDataInfoId}"/>
                                <input type="hidden" name="kpiDataInfos[${status.index }].kpiAssess.kpiAssessId" value="${data.kpiAssess.kpiAssessId}"/>
                            </td>
                            <td>${data.kpiAssess.kpiInfo.kpiType}</td>
                            <td>${data.kpiAssess.kpiInfo.kpiName}</td>
                            <td>${data.kpiAssess.kpiInfo.unit}</td>
                            <td>${data.kpiDataInfo.mark}</td>
                            <td>${data.kpiDataInfo.kpiAssessValue}</td>
                            <%--<td>${data.kpiDataInfo.kpiAdjustValue}</td>--%>
                            <%--<td>${data.kpiDataInfo.accordingInfo}</td>--%>
                            <td>${data.kpiAssess.assessDept}</td>
                            <td>${data.kpiDataInfo.kpiRawValue}</td>
                            <td>${data.kpiDataInfo.sourceSystem}</td>
                            <td><input type='text' name='kpiDataInfos[${status.index }].kpiConfirmValue' value='${data.kpiDataInfo.kpiConfirmValue}'/></td>
                            <td><input type='text' name='kpiDataInfos[${status.index }].checkMemo' value='${data.kpiDataInfo.checkMemo}'/></td>
                            <td><input type='text' name='kpiDataInfos[${status.index }].mark' value='${data.kpiDataInfo.mark}'/></td>
                            <td style="display: none;"><input type="hidden" name="kpiDataInfos[${status.index }].status" value="${data.kpiDataInfo.status}" id="status"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                    <tfoot>
                    <tr class="tfoot">
                        <td colspan="12" style="text-align: center">
                            <input type="submit"  value="保 存" >
                            <input type="button"  value="提 交" id="confirm" style="margin:0 15px">
                        </td>  
                    </tr>
                    </tfoot>
                </table>
            </form>
        </div>
        <!--Table End-->
    </div>
</div>
<select style="display: none;" name="kpiType">

</select>
</body>
</html>

