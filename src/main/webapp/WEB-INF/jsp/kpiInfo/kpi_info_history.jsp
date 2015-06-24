<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
   <base href="<%=basePath%>">
    
    <title>指标历史情况</title>
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
		$(document).ready(function () {  
        	window.onfocus = function(){
        		var _page="1";
        		if($(".selected a").length>0&&$(".selected a").text()!="")
        			_page=$(".selected a").text();
             //   $.postSearchForm("${pageContext.request.contextPath}","&pageSize=15&pageNum="+_page); //,isleader
          	}
        	$("#searchbtn").click(function(){ 	
        		$("#kpiId").val("");	
				$.postSearchForm("${pageContext.request.contextPath}","&pageSize=15&pageNum=1");//,isleader
			});
			
			$("#renewbtn").click(function(){ 
				$("#kpi_infoList input,#kpi_infoList select").not("input[type=button]").val("");
				$.postSearchForm("${pageContext.request.contextPath}","&pageSize=15&pageNum=1");//,isleader
			});  
        	 
			$.setBtn("${pageContext.request.contextPath}"); //,isleader
        }); 	
	
		 $(function() { 
			 $.initContextPath("<%=basePath%>");
			 // $.postSearchForm("${pageContext.request.contextPath}","&pageSize=15&pageNum=1");
			  $("select[name=kpiType]").wrapCategorySelect({type:'kpiInfo',name:'kpiType'});
		     $("select[name=businessType]").wrapCategorySelect({type:'kpiInfo',name:'businessType'});
		    
		    
		     $("select[name=year]").wrapCategorySelect({type:'kpiInfo',name:'year'});
		     
		   //   $("select[name=businessType]").bind("completed",function(){
		      		//$("select[name=kpiType]").wrapCategorySelect({type:'kpiInfo',name:'kpiType',selectVal: '${param.kpiType}'})
		    //   }); 
		      
		     	
		     /*   setTimeout(function(){
 					 $("tr.tableItem").find("td:eq(1)").each(function(i,o){
                     var val = $(o).text();
                     if(val!="")
                     $(o).text($this.find("option[value="+val+"]").text()); 
		           },3000);   */
		    
		 
		     
		      $("select[name=businessType]").bind("completed",function(){
		      		$.postSearchForm("${pageContext.request.contextPath}","&pageSize=15&pageNum=1");
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
                <li>指标历史情况</li>
            </ul>
        </div>
    </div>
     <div class="pt45">
      <div class="filter">
        	<div class="query">
            	<div class="filter_search p8">
                 <form id="kpi_infoList">
                  <table class="nwarp" width="100%" border="0" cellspacing="0" cellpadding="0">
                  	<div style="display:none;">
                  	  <input type="text" value="${kpiId }" name="kpiId" id="kpiId" class="input_large" >
                  	</div>
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
                      <td><input type="text" name="kpiName" class="input_large" ></td>
                    </tr>
                    <tr>
                      <td class="t_r">启用年份</td>
                      <td><select  name="year" class="input_large" id="year">
                          </select>
                      </td>
                       <td class="t_r">指标状态</td>
                       <td>
                          <select name="status" class="input_large" >
                          <option value="">请选择</option>
                          <option value="1">正常使用</option>
                          <option value="0">已停用</option>
                          </select>  
                       </td>
                       <td class="t_r">是否考核</td>
                       <td>
                          <select name="isAssess" class="input_large" >
                          <option value="">请选择</option>
                          <option value="1">是</option>
                          <option value="0">否</option>
                          </select>
                       </td>
                      </tr>
                      <tr>
                      	<td class="t_r" colspan="4">
                       		<input type="button"  id="searchbtn" value="查询" />&nbsp;<input type="button" id="renewbtn" value="重 置" />
                       </td>
                      </tr>
                    </table>
                </form>
            </div>
        </div>
        <div class="fn clearfix">
            	<h5 class="fl"><a class="fl">指标历史情况</a></h5>  
         </div>
      </div>
        <!--Filter End-->
        <!--Table-->
        <div class="mb10">
        	<table width="100%"  class="table_1">
                <tbody>
                   <tr class="tit">
                       <td class="sort"><a><span class="fl">业务分类</span></a></td>
                       <td class="sort"><a><span class="fl">指标分类</span></a></td>
                       <td class="sort"><a ><span class="fl">指标名称</span></a></td>
                       <td class="sort"><a ><span class="fl">指标代码</span></a></td>
                       <td class="sort"><a ><span class="fl">是否考核</span></a></td>
                       <td class="sort"><a class="asc desc">启用年份</a></td>
                       <td class="sort"><a ><span class="fl">指标定义</span></a></td>
                       <td class="sort"><a ><span class="fl">指标算法</span></a></td>
                      <%--<td class="sort"><a ><span class="fl">指标定义</span></a></td> --%> 
                      <%--<td class="sort"><a><span class="fl">操作</span></a></td> --%> 
                   </tr>
                 </tbody>
                 <tfoot>
                   <tr class="tfoot">
                      <td colspan="10">
                        <div class="clearfix">
                            <span class="fl">共0条记录，当前显示0条</span>
                            <ul id="page"class="fr clearfix pager">
                              <li>
                              	Pages:1/1
                                <input type="number" id="number" name="number" min="0" max="999" step="1" class="input_tiny" />
                                <input type="submit" name="button" id="button" value="Go">
                              </li>
                              <li><a href="#">&gt;&gt;</a></li>
                              <li class="selected"><a href="#">1</a></li>
                              <li><a href="#">&lt;&lt;</a></li>
                            </ul>
                         </div>
                        </td>
                    </tr>
                  </tfoot>
           </table>
      </div>
        <!--Table End-->
     </div>
	
	<!-- 详细信息 -->
	<div id="history" style="display:none">	
	</div>
	</div>
</body>
</html>