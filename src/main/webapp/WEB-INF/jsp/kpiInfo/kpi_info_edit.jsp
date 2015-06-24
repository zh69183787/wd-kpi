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
	<script src="js/kpi_info.js"></script>
	<script type="text/javascript">
		$(function() {
			var isleader= $.isleader("${pageContext.request.contextPath}"); 
			$.initContextPath("<%=basePath%>");
		    $("#businessTypeEdit").wrapCategorySelect({type:'kpiInfo',name:'businessType',selectVal:'${kpi.businessType}'});
		    $("#kpiTypeEdit").wrapCategorySelect({type:'kpiInfo',name:'kpiType',selectVal:'${kpi.kpiType}'});
		    $("#yearEdit").wrapCategorySelect({type:'kpiInfo',name:'year',selectVal:'${kpi.year}'});
		    
			$("#status > option[value=${kpi.status}]").prop("selected",true);
		    $("#isAssess > option[value=${kpi.isAssess}]").prop("selected",true);
		    
		    $(":text[name='createTime']").val((new Date()).getTime());
    		$(":text[name='updateTime']").val((new Date()).getTime());
    		$(":text[name='creator']").val(isleader);
    		$(":text[name='updater']").val(isleader);  
    
		   $("#btnEdit").click(function(){
		   		//验证KpiCode
		   		if('${kpi.kpiCode}' == $("#kpiCodeEdit").val()){
		   			alert("直接保存");
		   			console.log("直接保存");
		   			//$.postsavehistory("${pageContext.request.contextPath}","kpiInfoEdit","");
		        	//$.postsave("${pageContext.request.contextPath}","kpiInfoEdit","");
		   		}else{
		   			//验证指标代码是否唯一
		   			/* $.ajax({
					async: false,
					url : "${pageContext.request.contextPath}"+"/data/kpiInfo/page",
					type : 'post',
					success: function(data, status, xhr){
						if (status == "success" && data != null){
							if (data.success == true&&data.result.content &&JSON.stringify(data.result.content) != "[]"){
								var flag = 0;
								$.each(data.result.content,function(entryIndex,entry){				
									if(entry.kpiCode == $("#kpiCodeEdit").val()){
										alert("该指标代码已存在，不可重复添加");
										console.log("已经存在");
										return;
									}else{
										flag++;
									}			
								});
								if(flag == data.result.content.length){
									alert("成功");
									console.log("成功");
									//$.postsavehistory("${pageContext.request.contextPath}","kpiInfoEdit","");
		        					//$.postsave("${pageContext.request.contextPath}","kpiInfoEdit","");
								}
							} 
						}    
					}, 
					error: function() {
						hiAlert("对不起！你的请求出现异常，请联系服务人员！","重要提示");
				    } 
		    		}); */
		    		//验证指标代码是否唯一
		    		//$.postsavehistory("${pageContext.request.contextPath}","kpiInfoEdit","");
		        	//$.postsave("${pageContext.request.contextPath}","kpiInfoEdit","");
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
            <form id="kpiInfoEdit">
              <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_2">
                <thead>
                <th colspan="4" class="f1"> <h5 class="fl">指标编辑</h5>
                  </th>
                    </thead>  
                <tr>
                 <td class="t_r">业务分类</td>
                 	<td>
                 		<select name="businessType" class="input_large" id="businessTypeEdit">
                 			<option>${kpi.businessType}</option>
                        </select>
                    </td>
                 <td class="t_r">指标分类</td>
                 	<td>
                 		<select name="kpiType" class="input_large" id="kpiTypeEdit">
                 			<option>${kpi.kpiType}</option>
                        </select>
                 	</td>
                </tr>
               
                <tr>
                  <td class="t_r">指标名称</td>
                      <td><input type="text" name="kpiName" class="input_large" value="${kpi.kpiName}"></td>
                   <td class="t_r">指标代码</td>
                      <td><input type="text" name="kpiCode" class="input_large" value="${kpi.kpiCode}" id="kpiCodeEdit"></td>
                </tr>
                
                <tr>
                 <td class="t_r">启用年份</td>
                 	<td>
                 		<select name="year" class="input_large" id="yearEdit">
                 			<option>${kpi.year}</option>
                        </select>
                 	</td>
                 	
                 <td class="t_r">指标状态</td>
                 <td>
                 	<select name="status" class="input_large" id="status">
                 		<option>请选择</option>
                 		<option value="1">正常使用</option>
                        <option value="0">已停用</option>
                    </select>
                 </td>
                </tr>
               <tr>
                  <td class="t_r">是否考核</td>
                  <td>
                 	<select name="isAssess" class="input_large" id="isAssess">
                 		<option>请选择</option>
                 		<option value="1">是</option>
                        <option value="0">否</option>
                    </select>
                 </td>
                  <td class="t_r">单位</td>
                    <td><input type="text" name="unit" class="input_large" value="${kpi.unit}"></td>
                </tr>
                
                <tr>
                	<td class="lableTd t_r">指标定义 </td>
                    <td colspan="3"><textarea name="kpiDefinition" class="input_large" rows="5px" style="width:840px;height:50px;" value="${kpi.kpiDefinition}">${kpi.kpiDefinition}</textarea> </td>
                </tr>
                <tr>
                	<td class="lableTd t_r">指标算法 </td>
                    <td colspan="3"><textarea name="kpiAlgorithm" class="input_large" rows="5px" style="width:840px;height:50px;" value="${kpi.kpiAlgorithm}">${kpi.kpiAlgorithm}</textarea></td>
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
                <div style="display:none">
                	<input type="text" name="kpiId" id="kpiId" value="${kpi.kpiId }"/>
                </div>
              </table>
              <div class="fn clearfix t_c">
            	<h5 class="fl"><a href="#" class="colSelect fl"></a></h5>
                <input type="button" id="btnEdit" value="保存" />&nbsp;
              </div>
              </form>
            </div>
            <div id="dialog-confirm" title="" style="display:none">
                <p></p>
            </div>
            <div id="Prompt" style="display:none">
            	<p></p>
            </div>
           <!--  <div>
            	<form id="kpi_infoEdit">
            		<table>
            			<tr>
            				<td><input type="text" value="2013" name="year" id="ds"/></td>
            			</tr>
            		</table>
            	</form>
            </div> -->
      </div>
        </div>
</div>
</body>
</html>
