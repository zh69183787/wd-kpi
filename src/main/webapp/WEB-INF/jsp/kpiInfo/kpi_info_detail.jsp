<%--
  Created by IntelliJ IDEA.   
  User: Administrator
  Date: 2014/12/1
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*,java.text.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
 <head>
	
    <base href="<%=basePath%>">
    <title>指标详细信息</title>
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
	<script type="text/javascript">
		$(function() {
			$.initContextPath("<%=basePath%>");
		    $("#businessType1").wrapCategorySelect({type:'kpiInfo',name:'businessType',selectVal:'${kpi.businessType}'});
		    $("#kpiType1").wrapCategorySelect({type:'kpiInfo',name:'kpiType',selectVal:'${kpi.kpiType}'});
		    $("#year1").wrapCategorySelect({type:'kpiInfo',name:'year',selectVal:'${kpi.year}'});

		    $("#isAssess1 > option[value=${kpi.isAssess}]").prop("selected",true);
		    $("#status1 > option[value=${kpi.status}]").prop("selected",true);
		    
		    
		   $("#businessType1").bind("completed",function(){
		   		if($("#a").text()==""){
	     		$("#a").text($($(this).find("option:selected")[0]).text());
		   		}
	        });
	        
	   		$("#kpiType1").bind("completed",function(){
	     		$("#b").text($($(this).find("option:selected")[0]).text());	
	        });
	       
		    $("#year1").bind("completed",function(){
	     		$("#c").text($($(this).find("option:selected")[0]).text());
	        });
	        
		});
	</script>	
</head>

<body>
	<div>
        <div>
        <!--Filter-->
        <div class="filter">	
           <div class="mb10">
            <form id="kpiInfoDetail">
              <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_2">
                <thead>
                <th colspan="4" class="f1"> <h5 class="fl">指标详细信息</h5>
                  </th>
                    </thead>  
                <tr>
                 <td class="t_r">业务分类</td>
                 <td id="a"></td>
                 
                 <td class="t_r">指标分类</td>
                 <td id="b"></td>
                </tr>
               
                <tr>
                  <td class="t_r">指标名称</td>
                      <td>${kpi.kpiName}</td>
                   <td class="t_r">指标代码</td>
                      <td>${kpi.kpiCode}</td>
                </tr>
                
                <tr>
                 <td class="t_r">启用年份</td>
                 <td id="c"></td>
                 <td class="t_r">指标状态</td>
                 <td>
                 	<c:choose>
                		<c:when test="${kpi.status==1}">
                			正常使用
                		</c:when>
                		<c:when test="${kpi.status==0}">
		        	                           已停用
                		</c:when>
                		<c:otherwise>
                		
                		</c:otherwise>
                	</c:choose>
                 	  
                 </td>
                </tr>
              
               <tr>
                  <td class="t_r">是否考核</td>
                  <td>
                  <c:choose>
                		<c:when test="${kpi.isAssess==1}">
                			是
                		</c:when>
                		<c:when test="${kpi.isAssess==0}">
		        	                            否
                		</c:when>
                		<c:otherwise>
                		
                		</c:otherwise>
                	</c:choose>
                 </td>
                  <td class="t_r">单位</td>
                  <td>${kpi.unit}</td>
                </tr>
                
                <tr>
                	<td class="t_r">指标定义 </td>
                    <td colspan="3" style="width:840px;height:50px;">${kpi.kpiDefinition}</td> 
                </tr>
                <tr>
                	<td class="t_r">指标算法 </td>
                    <td colspan="3" style="width:840px;height:50px;">${kpi.kpiAlgorithm}</td> 
                </tr>
                <div style="display:none">
                	<input type="text" name="businessType" id="businessType" value="${kpi.businessType}"/>
                	<input type="text" name="kpiType" id="kpiType" value="${kpi.kpiType}"/>
                	<input type="text" name="year" id="" value="${kpi.year}"/>
                </div>
                <div style="display:none">
                	<select name="businessType" class="input_large" id="businessType1" >
                 		<option>${kpi.businessType}</option>
                    </select>
                    <select name="kpiType" class="input_large" id="kpiType1" >
                 		<option>${kpi.kpiType}</option>
                    </select> 
                    <select name="year" class="input_large" id="year1">
                 		<option>${kpi.year}</option>
                    </select>
                    <select name="status" class="input_large" id="status1" disabled="true">
                 		<option>请选择</option>
                 		<option value="1">正常使用</option>
                 		<option value="0">已停用</option>
                    </select> 
                </div>        
              </table>
              </form>
            </div>
      </div>
        </div>
</div>
</body>
</html>
