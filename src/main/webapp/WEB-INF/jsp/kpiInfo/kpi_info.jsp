<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2014/12/1
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" import="java.util.*,java.text.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
   
<!DOCTYPE html>
<html>
 <head>
	
    <base href="<%=basePath%>">
    <title>指标新增</title>
    <meta charset="utf-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	
    <link rel="stylesheet" href="css/formalize.css" />
	<link rel="stylesheet" href="css/page.css" />
	<link rel="stylesheet" href="css/default/imgs.css" />
	<link rel="stylesheet" href="css/reset.css" />
	<link type="text/css" href="css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
	
	<link rel="stylesheet" href="css/uploadify.css" type="text/css" />
	<script src="js/html5.js"></script>
	<script src="js/jquery-1.7.1.min.js"></script>
	<script src="js/jquery-ui-1.8.18.custom.min.js"></script>
	<script src="js/jquery.formalize.js"></script>
	<script src="js/custom_widgets.js"></script>
	
	<script src="js/kpi_info.js"></script>
	<script type="text/javascript">
	$(function() {
		var isleader= $.isleader("${pageContext.request.contextPath}"); 
		//var isleader = "hahaha";
		$.initContextPath("<%=basePath%>");
	    $("select[name=businessType]").wrapCategorySelect({type:'kpiInfo',name:'businessType'});
	    $("select[name=kpiType]").wrapCategorySelect({type:'kpiInfo',name:'kpiType'});
	    $("select[name=year]").wrapCategorySelect({type:'kpiInfo',name:'year'});
	    $(":text[name='createTime']").val((new Date()).getTime());
	    $(":text[name='updateTime']").val((new Date()).getTime());
	    $(":text[name='creator']").val(isleader);
	    $(":text[name='updater']").val(isleader);
	    
	    
	    
	    $("#kpiCodeNew").blur(function(){
				if($("#kpiCodeNew").val()==''){}else{
				$.ajax({
					type: "get",
                    url: "<%=basePath%>kpiInfo/compare",
                    dataType: "json",
                    data: 'format=json'+"&routeName="+$("#routeName").val()+"&routeId="+$("#routeId").val(),
                    contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                    success: function (data) {
                        if("no"==data.msg){
                        	$("#com").html("<font color='red'>该路线名称已经存在</font>");
                        }else{
                        	$("#com").html("<font color='green'>该路线名称可以保存</font>");
                        }
                    }
				});
				}
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
            <form id="kpi_infoAdd"><!-- projectPlanForm -->
              <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_2">
                <thead>
                <th colspan="4" class="f1"> <h5 class="fl">新增指标信息</h5>  
                  </th>
                    </thead>
                <tr>
                 <td class="t_r">业务分类</td>
                      <td>
                       <select name="businessType" class="input_large" id="businessTypeNew">
                       </select>
                      </td>
                      <td class="t_r">指标分类</td>
                      <td>
                       <select name="kpiType" class="input_large" id="kpiTypeNew">
                       </select>
                      </td>
                </tr>
               
                <tr>
                  <td class="t_r">指标名称</td>
                      <td><input type="text" name="kpiName" class="input_large" id="kpiNameNew"></td>
                   <td class="t_r">指标代码</td>
                      <td><input type="text" name="kpiCode" class="input_large" id="kpiCodeNew"></td>
                </tr>
                
                <tr>
                 <td class="t_r">启用年份</td>
                      <td><select name="year" class="input_large" id="yearNew">
                          
                          </select>
                      </td>
                       <td class="t_r">指标状态</td>
                       <td>
                          <select name="status" class="input_large" id="statusNew">
                          <option value="">请选择</option>
                          <option value="1">正常使用</option>
                          <option value="0">已停用</option>
                          </select>  
                       </td>
                </tr>
              
               <tr>
                  <td class="t_r">是否考核</td>
                       <td>
                          <select name="isAssess" class="input_large" id="isAssessNew">
                          <option value="">请选择</option>
                          <option value="1">是</option>
                          <option value="0">否</option>
                          </select>
                       </td>
                       <td class="t_r">单位</td>  
                       <td><input type="text" name="unit" class="input_large" id="unitNew"></td>  
                </tr>  
                
                <tr>
                	<td class="t_r" >指标定义 </td>
                    <td colspan="3"><textarea name="kpiDefinition" rows="5px" style="width:840px;height:50px;" id="kpiDefinitionNew"></textarea></td> 
                </tr>     
                <tr>
                	<td class="t_r">指标算法 </td>
                    <td colspan="3"><textarea name="kpiAlgorithm" rows="5px" style="width:840px;height:50px;" id="kpiAlgorithmNew"></textarea></td> 
                </tr>
                
			 	<tr style="display:none">
                    <td><input type="text" name="createTime" class="input_large" value=""></td>
                    <td><input type="text" name="updateTime" class="input_large" value=""></td>
			 	</tr>
			 	<tr style="display:none">
                    <td><input type="text" name="creator" class="input_large" value=""></td>
                    <td><input type="text" name="updater" class="input_large" value=""></td>
			 	</tr>
                <tr style="display:none">
                	<td><input type="text" name="removed" class="input_large" value="0"/></td>
                	<td><input type="text" name="isTemp" class="input_large" value="0"/></td>
                </tr>
                <tr>	
                </tr>
    		 </table>
              <div class="fn clearfix t_c">
            	<h5 class="fl"><a href="#" class="colSelect fl"></a></h5>
                <input type="button" id="btnSubmit" value="保 存" />&nbsp;
                <input type="reset" id="renewbtn" value="重 置"/>&nbsp; 
              </div>
              </form>
            </div>
          
            <div id="dialog-confirm" title="" style="display:none">
                <p></p>
            </div>
            <div id="Prompt" style="display:none">
            	<p></p>
            </div>
           
      </div>
        <!--Filter End-->
       
        </div>
</div>
</body>
</html>
