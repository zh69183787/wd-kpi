<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
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
	<script src="js/resourceManager.js"></script>
	<script type="text/javascript">
	$(function() {
		//var isleader= $.isleader("${pageContext.request.contextPath}");
        $("#btnSubmit").click(function(){
        {
	    	$("#dialog-confirm P").text("是否提交");
	   		$("#dialog-confirm" ).dialog({
	        	resizable: false,
	        	height:140,
				width:200,
		        modal: true,
		        buttons: {
		        "确认": function() {
		            var _od="";
	                _od+="&service_classification="+$("[name=service_classification] option:selected").text();
            	 		 _od+="&target_classification="+$("[name=target_classification] option:selected").val();
            	 		 _od+="&IndexName="+$("[name=IndexName]").val();
            			 _od+="&IndexCode="+$("[name=IndexCode]").val();
	                  	 _od+="&enableYear="+$("[name=enableYear]").val();
	                  	 _od+="&IndexState="+$("[name=IndexState]").val();
	                  	 _od+="&examination="+$("[name=examination]").val();
	                  	 _od+="&company="+$("[name=company]").val();
	                  	 _od+="&definition_index="+$("[name=definition_index]").val();
	                  	 _od+="&Index_algorithm="+$("[name=Index_algorithm]").val();
	                  	 
	                	   var otherData="";
	                	  
	                		//------------------------------------------------
	                	   var _yanz=$.yanzhen();
	                	   if(isleader==true)
	            			{
		            			alert("错误身份");
		            			$( this ).dialog( "close" );
		            			return false;
	            			}
	                	   if(_yanz!=""){
	                		   alert(_yanz+"\r\n请修改");
	                		   $( this ).dialog( "close" );
	                		   return false;
	                		}
	                	 //保存
	                	 $.postsave("${pageContext.request.contextPath}","projectPlanForm",otherData+"&status=innerApproval"+_od+"&suggestion=");
	                     $( this ).dialog( "close" );
	                   },
	                   "取消": function() {
	                     $( this ).dialog( "close" );
	                   }
	                 }
	               }); 
	        	 }
	         });
	         $("#btnsave").click(function(){
         	 $("#dialog-confirm P").text("是否保存为草稿");
    	 	 $( "#dialog-confirm" ).dialog({
             	resizable: false,
             	height:140,
	         	width:200,
            	modal: true,
             	buttons: {
               "确认": function() {
            	
            	   var _od="";
            	  
              	 //if($("#initiateTime")!=""||initiateTime!=null)
              		// {
              		 //var d = new Date();
            	   //_od+="&initiateTime="+d.getTime();
            	   //_od+="&applyTime="+d.getTime();
              		 //}
              	
              	 if($("[name=handlerLoginname]").text().trim()==null)
              		 {
              		 alert("错误");
                     return false;
              		 }
              	 
              	 _od+="&service_classification="+$("[name=service_classification] option:selected").text();
            	 _od+="&target_classification="+$("[name=target_classification] option:selected").val();
            	 _od+="&initiator="+$("[name=applyer]").val();
            	 _od+="&initiatorLoginname="+$("[name=applyerLoginname]").val();
              	 _od+="&applyType="+$("[name=applyType]").val();
            	   var otherData=$.getOtherData();
            	   if(QueryString.GetValue('id')!=null)
            		   {
            		   otherData+="&id="+QueryString.GetValue('id');
            		   }
            	  
            	    var _yanz=$.yanzhen();
	                	   if(isleader==true)
	            			{
	            			alert("错误身份");
	            			$( this ).dialog( "close" );
	            			return false;

	            			}
	                	   if(_yanz!="")
	                		   {
	                		   alert(_yanz+"\r\n请修改");
	                		   $( this ).dialog( "close" );
	                		   return false;
	                		   }
            	   //草稿保存
            	   $.postsave("${pageContext.request.contextPath}","projectPlanForm",otherData+"&status=draft"+_od+"&suggestion=");
                 $( this ).dialog( "close" );
               },
               "取消": function() {
                 $( this ).dialog( "close" );
               }
             }
           }); 
     });
	         
	         		// 详细信息
	                 $.getListDetail("${pageContext.request.contextPath}");

	                // $("#date").blur(function(){
	                //      $.getApplyUseTime();
	                // });
	                 $("[name=closebtn]").click(function(){
				 window.close();
			});
	           $("[name=applyBusinessType]").change(function(){
	                
					if($("[name=applyBusinessType]").val()!="addLocal"){
						$("#red").css("display","inline");
					} else{
					    $("#red").css("display","none");
					}       	
	           }); 	        
     });
	</script>

</head>

<body>
    <div class="button t_c">     	
        </div>
    </div>

	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix nwarp">
        	<div class="fl"><img src="css/default/images/sideBar_arrow_left.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl nwarp">
            	<ul>
                	<li><a href="#">首页</a></li>
                	<li><a href="#">集团指标管理</a></li>
                	<li class="fin">指标新增</li>
                </ul>
            </div>
            <div class="fr lit_nav nwarp">
            	<ul>
                    <li class="selected"><a class="print" href="#">打印</a></li>
                    <li><a class="express" href="#">导出数据</a></li>
                    <li class="selected"><a class="table" href="#">表格模式</a></li>
                    <li><a class="treeOpen" href="#">打开树</a></li>
                    <li><a class="filterClose" href="#">关闭过滤</a></li>
                </ul>
            </div>
   		</div>
        <!--Ctrl End-->
        <div class="pt45">
        <!--Filter-->
        <div class="filter">
        	<div class="query">
            	<div class="filter_search p8"><!--这里根据内容做样式调整。1、筛选：filter_sandglass 2、搜索filter_search 3、提示：.filter_tips 背景图会改变。-->
                  <table class="nwarp" width="100%" border="0" cellspacing="0" cellpadding="0">                   
                    <tr>
                      <td colspan="6" class="t_r">                   
                        <input type="reset" value="导 入" /><input type="button" value="下载模板"/></td>
                    </tr>
                </table>
               </div>
        	</div>
           <div class="mb10">
            <form id="projectPlanForm">
              <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_2">
                <thead>
                <th colspan="4" class="f1"> <h5 class="fl">指标信息</h5>
                  </th>
                    </thead>
                <tr>
                 <td class="t_r">业务分类</td>
                      <td>
                       <select name="service_classification" class="input_large" id="input_large">
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
                </tr>
               
                <tr>
                  <td class="t_r">指标名称</td>
                      <td><input type="text" name="IndexName" class="input_large" ></td>
                   <td class="t_r">指标代码</td>
                      <td><input type="text" name="IndexCode" class="input_large" ></td>
                </tr>
                
                <tr>
                 <td class="t_r">启用年份</td>
                      <td><select name="enableYear" class="input_large" >
                          <option value="2014">2014</option>
                          <option value="2013">2013</option>
                          <option value="2012">2012</option>
                          </select>
                      </td>
                       <td class="t_r">指标状态</td>
                       <td>
                          <select name="IndexState" class="input_large" >
                          <option value="normal">正常使用</option>
                          <option value="disable">已停用</option>
                          </select>
                       </td>
                </tr>
              
               <tr>
                  <td class="t_r">是否考核</td>
                       <td>
                          <select name="examination" class="input_large" >
                          <option value="yes">是</option>
                          <option value="no">否</option>
                          </select>
                       </td>
                       <td class="t_r">单位</td>
                       <td><input type="text" name="company" class="input_large" ></td>
                </tr>
                
                <tr>
                	<td class="lableTd t_r">指标定义 </td>
                    <td colspan="3"> <textarea name="definition_index" rows="5px" style="width:840px;height:50px;"> </textarea></td> 
                </tr>
                <tr>
                	<td class="lableTd t_r">指标算法 </td>
                    <td colspan="3"> <textarea name="Index_algorithm" rows="5px" style="width:840px;height:50px;"> </textarea></td> 
                </tr>
                
                        
              </table>
              <div class="fn clearfix t_c">
            	<h5 class="fl"><a href="#" class="colSelect fl"></a></h5>
                <input type="button" id="btnSubmit" value="保 存" />&nbsp;
                <input type="reset" id="renewbtn" value="重 置"/>&nbsp; 
                <input type="button" value="关闭" onclick="window.close();"/></td>
                
              </div>
              
            
              </form>
            </div>
            <div class="footer"></div>
            <div id="dialog-confirm" title="" style="display:none">
                 	<p>是否删除</p>
                </div>
           
      </div>
        <!--Filter End-->
       
        </div>
</div>
</body>
</html>
