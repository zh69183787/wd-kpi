<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
      
    <title>修订汇总</title>
    <meta charset="utf-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

    <link rel="stylesheet" href="css/formalize.css" />
    <link rel="stylesheet" href="css/page.css" />
    <link rel="stylesheet" href="css/default/imgs.css" />
    <link rel="stylesheet" href="css/reset.css" />
    <link type="text/css" href="css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
	
	<script src="js/html5.js"></script>
	<script src="js/jquery-1.7.1.min.js"></script>
	<script src="js/jquery-ui-1.8.18.custom.min.js"></script>
	<script src="js/jquery.formalize.js"></script>
	<script src="js/custom_widgets.js"></script>
	<script src="js/kpi_info_log.js"></script>
	<script type="text/javascript">	
		$(function(){
			$("#oututBtn").click(function(){
           		
           		location.href="<%=basePath%>kpiAssess/exportReviseExcel?"+$("#kpiAssessId").val();
           });
		});
	</script>
</head>
 <body class="Flow">
 <div class="button t_c">
</div>
	<div  class="main"> 
	<div class="ctrl clearfix nwarp">
        <div class="fl"><img src="css/default/images/sideBar_arrow_left.jpg" width="46" height="30" alt="收起"></div>
        <div class="posi fl nwarp">
            <ul>
                <li><a href="#">首页</a></li>
                <li>指标配置</li>
                <li>修订汇总</li>
            </ul>
        </div>
    </div>
    <div class="pt45">
      <div class="filter">
        <div class="fn clearfix">
            	<h5 class="fl"><a class="fl">修订汇总</a></h5>  
            	<input type="button" id="oututBtn" value="导 出"  class="fr" style="margin:0 15px">   &nbsp;
         </div>
      </div>
        <!--Filter End-->
        <!--Table-->
        <div class="mb10">
        	<table width="100%"  class="table_1">
                <thead>
                	<tr><input type="hidden" value="<%=request.getQueryString()%>" id="kpiAssessId"/></tr>
                   <tr class="tit">
                       <td class="sort"><a><span class="fl">指标名称</span></a></td>
                       <td class="sort"><a><span class="fl">考核部门</span></a></td>
                       <td class="sort"><a ><span class="fl">分值</span></a></td>
                       <td class="sort"><a ><span class="fl">基础</span></a></td>
                       <td class="sort"><a ><span class="fl">嘉奖</span></a></td>
                       <td class="sort"><a ><span class="fl">评分标准</span></a></td>
                       <td class="sort"><a><span class="fl">修订原因</span></a></td>
                       <td class="sort"><a><span class="fl">修订部门</span></a></td>
                       <td class="sort"><a><span class="fl">修订时间</span></a></td>
                   </tr>
                 </thead>
                   <tbody>
                   	<form >
                     <c:forEach items="${list}" var="revise" >
                       <tr>
	                      <td>${revise.kpiName}</td>
	                      <td>${revise.assessDept}</td>
	                      <td>${revise.mark}</td>
	                      <td>${revise.basicValueRange}</td>
	                      <td>${revise.reward}</td>
	                      <td>${revise.markStandard}</td>
	                      <td>${revise.assessReason}</td>
	                      <td>${revise.assessDept}</td>
	                      <td>${revise.reviseTime}</td>
	                  </tr>
	                </c:forEach>
	               </form>
                   </tbody>
                 <tfoot>
                 </tfoot>   
           </table>
      </div>
        <!--Table End-->
     </div>
     </div>
	
	
</body>
</html>