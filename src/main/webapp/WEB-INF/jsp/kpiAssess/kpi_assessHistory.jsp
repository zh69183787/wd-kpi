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
		 $(function() {   
		     $("#businessType").wrapCategorySelect({type:'kpiInfo',name:'businessType',selectVal:'${kpiAssess.kpiInfo.businessType}'});
		     $("#kpiType").wrapCategorySelect({type:'kpiInfo',name:'kpiType',selectVal:'${kpiAssess.kpiInfo.kpiType}'});
		     $("select[name=year]").wrapCategorySelect({type:'kpiInfo',name:'year',selectVal:'${kpiAssess.year}'});
		     $("select[name=assessedDeptId]").wrapCategorySelect({type:'kpiAssess',name:'assessedDeptId',selectVal:'${kpiAssess.assessedDeptId}'});
		     
		     $("select[name=businessType1]").wrapCategorySelect({type: 'kpiInfo', name: 'businessType'});
             $("select[name=businessType1]").bind("completed", function () {
                var $this = $(this);
                $("#kpiList>tbody>tr").find("td:eq(2)").each(function (i, o) {
                    var val = $(o).text();
                    $(o).text($this.find("option[value=" + val + "]").text());
                });
             });
		    
	        $("select[name=kpiType1]").wrapCategorySelect({type: 'kpiInfo', name: 'kpiType'});
            $("select[name=kpiType1]").bind("completed", function () {
                var $this = $(this);
                $("#kpiList>tbody>tr").find("td:eq(3)").each(function (i, o) {
                    var val = $(o).text();
                    $(o).text($this.find("option[value=" + val + "]").text());
                });
            });
            
            $("#searchbtn").click(function(){
            	$("#currentPage").val("1");
            });
		   }); 
		   function tabledata_next(){
		   		if($("#currentPage").val()=="${page.totalPage}"){
		   			$("#currentPage").val("${page.totalPage}");
		   		}else{
		   			$("#currentPage").val("${page.currentPage+1}");
		   		}
		   		$("#kpi_assessList").submit();
		   		
		   }
		   function tabledata_previous(){
		   		if($("#currentPage").val()=="1"){
		   			$("#currentPage").val("1");
		   		}else{
		   			$("#currentPage").val("${page.currentPage-1}");
		   		}
		   		$("#kpi_assessList").submit();
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
                <li>指标配置</li>
                <li>修订历史</li>
            </ul>
        </div>
    	</div> 
    <div class="pt45">	
      <div class="filter">
        	<div class="query">
            	<div class="filter_search p8"><!--这里根据内容做样式调整。1、筛选：filter_sandglass 2、搜索filter_search 3、提示：.filter_tips 背景图会改变。-->
                 <form id="kpi_assessList" action="<%=basePath%>kpiAssess/history">
                  <table class="nwarp" width="100%" border="0" cellspacing="0" cellpadding="0">
                  
                    <tr>
                      <td class="t_r">业务分类</td>   
                      <td>
                        <select id="businessType" name="kpiInfo.businessType" class="input_large" >  
                       	</select>
                      </td>
                      <td class="t_r">指标分类</td>
                      <td>
                       <select name="kpiInfo.kpiType" class="input_large" id="kpiType">  
                       </select>
                      </td>
                      <td class="t_r">指标名称</td>
                      <td><input type="text" name="kpiInfo.kpiName"  value="${kpiAssess.kpiInfo.kpiName }" class="input_large" ></td>
                    </tr>
                    <tr>
                      <td class="t_r">启用年份</td>
                      <td><select  name="year" class="input_large" id="year">
                          </select>
                      </td>
                      <td class="t_r">被考核部门</td>
                      <td><select  name="assessedDeptId" class="input_large">  
                          </select>
                      </td>
                      
                       
                       <td class="t_r">
                       		<input type="submit"  id="searchbtn" value="查询" />
                       </td>
                      </tr>
                      <tr>
                  	    <td  style="display:none"><input type="text" name="currentPage"  id="currentPage" value="${page.currentPage}" class="input_large" ></td>
                      </tr>
                    </table>
                </form>
            </div>
        </div>
        <div class="fn clearfix">
            	<h5 class="fl"><a class="fl">修订历史情况</a></h5>  
         </div>
      </div>
        <!--Filter End-->
        
        <!--Table-->
        <div class="mb10">
        	<table width="100%"  class="table_1" id="kpiList">
                <thead>
                   <tr class="tit">
                       <td class="sort"><a><span class="fl">被考核部门</span></a></td>
                       <td class="sort"><a><span class="fl">考核年份</span></a></td>
                       <td class="sort"><a ><span class="fl">业务分类</span></a></td>
                       <td class="sort"><a ><span class="fl">指标分类</span></a></td>
                       <td class="sort"><a ><span class="fl">指标名称</span></a></td>
                       <td class="sort"><a ><span class="fl">分值</span></a></td>
                       <td class="sort"><a><span class="fl">指标值</span></a></td>
                       <td class="sort"><a><span class="fl">单位</span></a></td>
                       <td class="sort"><a><span class="fl">评分标准</span></a></td>
                       <td class="sort"><a><span class="fl">考核部门</span></a></td>
                   </tr>
                 </thead>
                 
                   <tbody>
                      <c:forEach items="${list}" var="assess" varStatus="status">	
                       <tr>
                         <td>${assess.assessedDept}</td>
                         <td>${assess.year}</td> 
                         <td>${assess.kpiInfo.businessType}</td>
                         <td>${assess.kpiInfo.kpiType}</td>
                         
                         <td>${assess.kpiInfo.kpiName}</td>
                         <td>${assess.mark}</td>
                         
                         <td>${assess.basicValue}</td>
                         <td>${assess.kpiInfo.unit}</td>
                         <td>${assess.markStandard}</td>
                         <td>${assess.assessDept}</td>
                        </tr>
                       </c:forEach>
                    </tbody>
                 <tfoot>
                	<tr class="tfoot">
 					<td colspan="10"> 
                        <div class="clearfix">
                            <span class="fl">共${page.rows}条记录，当前显示${page.pageSize}条</span>
                            <ul id="page"class="fr clearfix pager">
                              <li>
                              	<a href="javascript:void(0)" onclick="tabledata_next();">&gt;&gt;</a>
                              </li>
                              <li>
                              	Pages:${page.currentPage}/${page.totalPage}
                              </li>
                              <li>
                                <a href="javascript:void(0)" onclick="tabledata_previous();">&lt;&lt;</a>
                              </li>
                            </ul>
                         </div>
                        </td>
                    </tr>
                 </tfoot>
                 <select name="businessType1" style="display: none;"></select>
                 <select name="kpiType1" style="display: none;"></select>
           </table>
      </div>
        <!--Table End-->
        </div>
     </div>
	
	
</body>
</html>