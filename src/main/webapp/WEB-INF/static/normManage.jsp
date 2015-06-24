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

	<script src="js/normManage.js"></script>
	
	<script type="text/javascript">
		 $(document).ready(function () {
		 	$.getMenu("${pageContext.request.contextPath}");//,"resourceManage"
        	//var isleader= $.isleader("${pageContext.request.contextPath}");
        	//if(isleader=="不是领导"){
        		//$("#addbtn").css("display","");
        	//}else{
        		//$("#addbtn").css("display","none");
        	//}       	
        	$.postSearchForm("${pageContext.request.contextPath}","&pageSize=16&pageNum=1");//,isleader
        	window.onfocus = function(){
        		var _page="1";
        		if($(".selected a").length>0&&$(".selected a").text()!="")
        			_page=$(".selected a").text();
        		
                $.postSearchForm("${pageContext.request.contextPath}","&pageSize=16&pageNum="+_page); //,isleader
          	}
        	$("#searchbtn").click(function(){ 		
				$.postSearchForm("${pageContext.request.contextPath}","&pageSize=16&pageNum=1");//,isleader
			});
        	  
        	$("#renewbtn").click(function(){ 
				$("#projectAppform input,#projectAppform select").not("input[type=button]").val("");
				$.postSearchForm("${pageContext.request.contextPath}","&pageSize=16&pageNum=1");//,isleader
			});
			$("#oututBtn").click(function(){
       			var content =$("#projectAppform").serialize()+paging+otherData+"&sort=status asc,applyUseTime desc";
       			window.location.href=firUrl+"/basicCrud/netResource/export2Excel?"+content;

       	    });
			$.setBtn("${pageContext.request.contextPath}"); //,isleader
        }); 	
		function add(){
			window.open("/iims/netResource/resourceManageAdd");
		}
	</script>
  </head>
  
  <body class="Flow">
	<div class="main">
		<div class="ctrl clearfix nwarp">
        	<div class="fl"><img src="css/default/images/sideBar_arrow_left.jpg" width="46" height="30" alt="收起"></div>
                <div class="posi fl nwarp">
            	   <ul>
                	  <li><a >首页</a></li>
                	  <li><a >集团指标管理</a></li>
                	  <li class="fin">指标管理<div id="myname" style="display:none"></div></li>
                    </ul>
                  </div>
   		    </div> 
        <div class="pt45">
  
      <div class="filter">
        	<div class="query">
            	<div class="filter_search p8"><!--这里根据内容做样式调整。1、筛选：filter_sandglass 2、搜索filter_search 3、提示：.filter_tips 背景图会改变。-->
                 <form id="projectAppform">
                  <table class="nwarp" width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td class="t_r">业务分类</td>   
                      <td>
                       <select name="service_classification" class="input_large" >
                          <option value="">请选择</option>
                          <option value="run_production">运营生产类</option>
                          <option value=""></option>
                          <option value=""></option>
                          <option value=""></option>
                          <option value=""></option>
                       </select>
                      </td>
                      <td class="t_r">指标分类</td>
                      <td>
                       <select name="target_classification" class="input_large" >
                          <option value="">请选择</option>
                          <option value="financial_benefit">财务效益</option>
                          <option value="production_safety">安全生产</option>
                          <option value="quality_service">服务质量</option>
                          <option value="facilities">设施设备</option>
                          <option value="key_work">重点工作</option>
                       </select>
                      </td>
                      <td class="t_r">指标名称</td>
                      <td><input type="text" name="IndexName" class="input_large" ></td>
                      <td class="t_r">指标代码</td>
                      <td><input type="text" name="IndexCode" class="input_large" ></td>
                    </tr>
                    <tr>
                      <td class="t_r">启用年份</td>
                      <td><select  name="enableYear" class="input_large">
                          <option value="2014">2014</option>
                          <option value="2013">2013</option>
                          <option value="2012">2012</option>
                          </select>
                      </td>
                       <td class="t_r">指标状态</td>
                       <td>
                          <select name="IndexState" class="input_large" >
                          <option value="">请选择</option>
                          <option value="normal">正常使用</option>
                          <option value="disable">已停用</option>
                          </select>
                       </td>
                       <td class="t_r">是否考核</td>
                       <td>
                          <select name="examination" class="input_large" >
                          <option value="">请选择</option>
                          <option value="yes">是</option>
                          <option value="no">否</option>
                          </select>
                       </td>
                       <td class="t_r">
                       		<input type="button"  id="searchbtn" value="查询" />&nbsp;<input type="button" id="renewbtn" value="重 置" />
                       </td>
                      </tr>
                    </table>
                </form>
            </div>
        </div>
        <div class="fn clearfix">
            	<h5 class="fl"><a class="fl">指标管理表</a></h5>
            	<input type="button" id="oututBtn" value="导 出"  class="fr" style="margin:0 15px">   &nbsp;
              <input type="button" id="addbtn" value="新 增"  onclick="window.open('${pageContext.request.contextPath}/projectPlan/normManageAdd');" class="fr">
         </div>
      </div>
        <!--Filter End-->
        <!--Table-->
        <div class="mb10">
        	<table width="100%"  class="table_1">
                <tbody>
                   <tr class="tit">
                       <td class=" t_c"><input type="checkbox" id="test_checkbox_1" name="test_checkbox_1" /></td>
                       <td class="sort"><a><span class="fl">业务分类</span></a></td>
                       <td class="sort"><a><span class="fl">指标分类</span></a></td>
                       <td class="sort"><a ><span class="fl">指标名称</span></a></td>
                       <td class="sort"><a ><span class="fl">指标代码</span></a></td>
                       <td class="sort"><a ><span class="fl">是否考核</span></a></td>
                       <td class="sort"><a class="asc desc">启用年份</a></td>
                       <td class="sort"><a><span class="fl">操作</span></a></td>
                       <td class="sort"><a ><span class="fl">操作</span></a></td>    
                       <td class="sort"><a ><span class="fl">操作</span></a></td>                              
                   </tr>
                 </tbody>
                 <tfoot>
                   <tr class="tfoot">
                      <td colspan="10">
                        <div class="clearfix">
                            <span class="fl">共7条记录，当前显示1-16条</span>
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
</div>
</body>
</html>
