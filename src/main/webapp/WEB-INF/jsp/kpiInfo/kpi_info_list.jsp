<%--
  Created by IntelliJ IDEA.
  User: Administrator   
  Date: 2014/12/1
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <title>指标管理</title>
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
	<script src="js/custom_widgets.js"></script>
	<script src="js/kpi_info.js"></script>
	<script type="text/javascript">
	
		 $(function() { 
		 	$.initContextPath("<%=basePath%>");
		 	
		 	 $("select[name=businessType]").wrapCategorySelect({type:'kpiInfo',name:'businessType'});
		     $("select[name=kpiType]").wrapCategorySelect({type:'kpiInfo',name:'kpiType'});
		     $("select[name=year]").wrapCategorySelect({type:'kpiInfo',name:'year'});
		     $("#businessType").bind("completed",function(){
		     	$.postSearchForm("${pageContext.request.contextPath}","&pageSize=15&pageNum=1");
		     });
		     
		 	$("#searchbtn").click(function(){
		 		$.postSearchForm("${pageContext.request.contextPath}","&pageSize=15&pageNum=1");
		 	});
		 	
		 	$("#renewbtn").click(function(){ 
				$("#kpi_infoList input,#kpi_infoList select").not("input[type=button]").val("");
				$.postSearchForm("${pageContext.request.contextPath}","&pageSize=15&pageNum=1");//,isleader
			});    
			
		    $("#btnSubmit").click(function(){  
	        	//做验证
	        	if($("#businessTypeNew").val()==""){  
	        		alert("业务分类不能为空");    
	        	}else if($("#kpiTypeNew").val()==""){  
	        		alert("指标分类不能为空");    
	        	}else if($("#kpiNameNew").val()==""){  
	        		alert("指标名称不能为空");    
	        	}else if($("#kpiCodeNew").val()==""){  
	        		alert("指标代码不能为空");    
	        	}else if($("#yearNew").val()==""){   
	        		alert("启用年份不能为空");    
	        	}else if($("#statusNew").val()==""){     
	        		alert("指标状态不能为空");    
	        	}else if($("#isAssessNew").val()==""){   
	        		alert("是否考核不能为空");       
	        	}else if($("#kpiNameNew").val().length>50){
		    		alert("指标名称长度不超过50");
		    	}else if($("#kpiCodeNew").val().length>10){
		    		alert("指标代码长度不超过10");
		    	}else if($("#unitNew").val().length>50){
		    		alert("单位长度不超过50");
		    	}else if($("#kpiDefinitionNew").val().length>100){
		    		alert("指标定义长度不超过100");
		    	}else if($("#kpiAlgorithmNew").val().length>100){
		    		alert("指标算法长度不超过100");   
		    	}else{
		    		/* $.ajax({
					async: false,
					url : "${pageContext.request.contextPath}"+"/data/kpiInfo/page",
					type : 'post',
					success: function(data, status, xhr){
						if (status == "success" && data != null){
							if (data.success == true&&data.result.content &&JSON.stringify(data.result.content) != "[]"){
								var flag = 0;
								$.each(data.result.content,function(entryIndex,entry){				
									if(entry.kpiCode == $("#kpiCodeNew").val()){
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
									$.postsave("${pageContext.request.contextPath}","kpi_infoAdd","");  
								}
							} 
						}    
					}, 
					error: function() {
						hiAlert("对不起！你的请求出现异常，请联系服务人员！","重要提示");
				    } 
		    		}); */
		    		//验证KpiCode
		    		$("#kpiCodeNew").val();
		    		//$.postsave("${pageContext.request.contextPath}","kpi_infoAdd","");
	        	}
	        });  
		    
			 $("#addbtn").click(function(){
			   	$("#newadd").dialog({
			        resizable: false,
			        height:435,  
					width:1000,
				    modal: true
			    }); 
			 });
			 
			$("#oututButton").click(function(){
			 	var content =$("#kpi_infoList").serialize();
			 	window.location.href="<%=basePath%>data/kpiInfo/export2Excel?type=KpiInfo&"+content+"&excelName=集团指标导出表";        
			});     
		});
		   
		  
		   function test(id){
		   	   $("#detail").dialog({  
			      resizable: false,
			      height:400,   
				  width:1000,
				  modal: true,
				  open:function(){  
				      $("#detail").load("<%=basePath%>kpiInfo/detail/"+id);
				  }
			  });    
		   }
		   function kpiInfoEdit(id){
		   	  $("#edit").dialog({
			      resizable: false,
			      height:430,   
				  width:1000,
				  modal: true,
				  open:function(){
				     $("#edit").load("<%=basePath%>kpiInfo/kpiInfoedit/"+id);
				  }
			  }); 
		   } 
		    function kpiInfoHistory(id){
		    	//location.href="<%=basePath%>kpiInfo/kpiInfohistory/"+id;  
		    	window.open("<%=basePath%>kpiInfo/kpiInfohistory/"+id);
		    }
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
                <li>首页</li>  
                <li>集团指标管理</li>
            </ul>
        </div>
    </div>
    <div class="pt45">
      <div class="filter">
        	<div class="query">
            	<div class="filter_search p8"><!--这里根据内容做样式调整。1、筛选：filter_sandglass 2、搜索filter_search 3、提示：.filter_tips 背景图会改变。-->
                 <form id="kpi_infoList" action="<%=basePath%>kpiInfo/exportInfoExcel">
                  <table class="nwarp" width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td class="t_r">业务分类</td>   
                      <td>
                       <select id="businessType" name="businessType" class="input_large" >  
                       </select>
                      </td>
                      <td class="t_r">指标分类</td>
                      <td>
                       <select name="kpiType" class="input_large" id="kpiType">
                       </select>
                      </td>
                      <td class="t_r">指标名称</td>
                      <td><input type="text" name="kpiName" class="input_large" id="kpiName"></td>
                      <td class="t_r">指标代码</td>
                      <td><input type="text" name="kpiCode" class="input_large" id="kpiCode"></td>
                    </tr>
                    <tr>
                      <td class="t_r">启用年份</td>
                      <td><select  name="year" class="input_large" id="year">
                          </select>
                      </td>
                       <td class="t_r">指标状态</td>
                       <td>
                          <select name="status" class="input_large">
                          <option value="">请选择</option>
                          <option value="1">正常使用</option>
                          <option value="0">已停用</option>  
                          </select>  
                       </td>
                       <td class="t_r">是否考核</td>
                       <td>
                          <select name="isAssess" class="input_large">
                          <option value="">请选择</option>
                          <option value="1">是</option>
                          <option value="0">否</option>
                          </select>
                       </td>
                       
                      </tr>
                      <tr>
                      	<td colspan="3"></td>
                      	<td class="t_r"> <!--  style="text-align: center" -->
                       		<input type="button"  id="searchbtn" value="查询" />&nbsp;<input type="button" id="renewbtn" value="重 置" />
                       </td>
                      </tr>
                      <tr style="display:none;">
                      	<td class="t_r">临时指标</td>
                        <td><input type="text" name="isTemp" class="input_large" value="0" >
                        </td>
                      </tr>
                    </table>
                </form>
            </div>
        </div>
        <div class="fn clearfix">
            	<h5 class="fl"><a class="fl">指标管理表</a></h5>
            	<input type="button" id="oututButton" value="导 出"  class="fr" style="margin:0 15px">   &nbsp;
            <c:if test="${sessionScope.currentUser.companyId==2518}">
              <input type="button" id="addbtn" value="新 增"  class="fr">
            </c:if>
         </div>
      </div>
        <!--Filter End-->
        <!--Table-->
        <div class="mb10">
        	<table width="100%"  class="table_1">
                <tbody>
                   <tr class="tit">
                       <td class=" t_c"><input type="hidden" id="test_checkbox_1" name="test_checkbox_1" /></td><!-- checkbox -->
                       <td class="sort"><a><span class="fl">业务分类</span></a></td>
                       <td class="sort"><a><span class="fl">指标分类</span></a></td>
                       <td class="sort"><a ><span class="fl">指标名称</span></a></td>
                       <td class="sort"><a ><span class="fl">指标代码</span></a></td>
                       <td class="sort"><a ><span class="fl">是否考核</span></a></td>
                       <td class="sort"><a class="asc desc">启用年份</a></td>  
                       <!-- <td class="sort"><a><span class="fl">操作</span></a></td> -->
                       <c:if test="${sessionScope.currentUser.companyId==2518}">
                       <td class="sort" colspan="3"><a ><span class="fl">操作</span></a></td>
                       </c:if>     
                     <!--   <td class="sort"><a ><span class="fl">操作</span></a></td> -->                    
                   </tr>
                 </tbody>
                 <tfoot>
                   <tr class="tfoot">
                      <td colspan="10">
                        <div class="clearfix">
                            <span class="fl">共0条记录，当前显示0条</span>
                               <ul id="page" class="fr clearfix pager">
                            	<li><a href="#">&gt;&gt;</a></li>
                              <li class="selected"><a href="#">1</a></li>
                              <li><a href="#">&lt;&lt;</a></li>
                              <li>
                              	Pages:1/1
                                <input type="number" id="number" name="number" min="0" max="999" step="1" class="input_tiny" />
                                <input type="submit" name="button" id="button" value="Go">
                              </li>
                              
                            </ul>
                         </div>
                        </td>
                    </tr>
                  </tfoot>
           </table>
      </div>
        <!--Table End-->
     </div>
	<!-- 新增 -->  
	<div id="newadd" style="display:none;">	
		 <jsp:include page="/kpiInfo/form"/>  
	</div>
	
	<!-- 详细信息 -->
	<div id="detail" style="display:none;">	
	</div>
	
	<!-- 编辑 -->
	<div id="edit" style="display:none;">	
	</div>
	<div id="history" style="display:none;">	
	</div></div>
</body>
</html>
