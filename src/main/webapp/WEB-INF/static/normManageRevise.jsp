<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <base href="<%=basePath%>">
    
    <title>项目计划申报</title>
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
	<script src="js/json2.js"></script>
	<script src="js/projectPlan.js"></script>

  </head>
  
  <body class="Flow">
	<div class="main">
		<div class="ctrl clearfix nwarp">
        	<div class="fl"><img src="css/default/images/sideBar_arrow_left.jpg" width="46" height="30" alt="收起"></div>
                <div class="posi fl nwarp">
            	   <ul>
                	  <li><a >首页</a></li>
                	  <li><a >指标管理</a></li>
                	  <li class="fin">指标修订汇总<div id="myname" style="display:none"></div></li>
                    </ul>
                  </div>
   		    </div> 
        <div class="pt45">
  
      <div class="filter">
        <div class="fn clearfix">
            	<h5 class="fl"><a class="fl">修订详情</a></h5>
            	<input type="button" id="oututBtn" value="关闭"  class="fr" style="margin:0 15px">&nbsp;   
         </div>
      </div>
        <!--Filter End-->
        <!--Table-->
        <div class="mb10">
        	<table width="100%"  class="table_1">
                <tbody>
                   <tr class="tit">
                       <td class="sort"><a><span class="fl">指标名称</span></a></td>
                       <td class="sort"><a><span class="fl">考核部门</span></a></td>
                       <td class="sort"><a ><span class="fl">分值</span></a></td>
                       <td class="sort"><a ><span class="fl">基础</span></a></td>
                       <td class="sort"><a ><span class="fl">绩效</span></a></td>
                       <td class="sort"><a class="asc desc">嘉奖</a></td>
                       <td class="sort"><a><span class="fl">评分标准</span></a></td>
                       <td class="sort"><a ><span class="fl">修订原因</span></a></td>  
                       <td class="sort"><a ><span class="fl">修订部门</span></a></td>  
                       <td class="sort"><a ><span class="fl">修订日期</span></a></td>                       
                   </tr>
                 </tbody>
                 <tfoot>
                   <tr class="tfoot">
                       <td colspan="10">
                         <div class="clearfix"><span class="fl"></span>
                           <ul class="fr clearfix pager"></ul>
                         </div>
                       </td>
                    </tr>
                  </tfoot>
           </table>
      </div>
        <!--Table End-->
     </div>
</div>
</body>
</html>