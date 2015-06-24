<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
	 <head>
	  <base href="<%=basePath%>">
		<title>征求意见稿</title>
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
 <div id="bt" class="transparent" style="display:none"></div>
    <div class="f_window" style="display:none">
   	  <h3 class="clearfix mb10"><span class="fl">业务办理</span><div class="fr close"><a class="closewindow">关闭窗口</a></div></h3>
        <div class="con">
        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td class="td_1" colspan="2">业务栏</td>
              </tr>
              <tr>
                <td class="td_1">&nbsp;</td>

                <td ><div class="red"> *</div>审核意见：<input name="btnISPass" type="radio" value="pass" style="margin-left:20px">通过<input name="btnISPass" type="radio" value="notPass" style="margin-left:10px">不通过</td>

              </tr>

              <tr>

                <td class="td_1">&nbsp;</td>

                <td><div class="red"> *</div>意见</td>

              </tr>

              <tr>

                <td class="td_1">&nbsp;</td>

                <td><textarea  name="suggestion" rows="4" placeholder="请输入意见" ></textarea></td>

              </tr>

              <tr>

                <td class="td_1" colspan="2">操作栏</td>

              
              </tr>

              <tr>

                <td class="td_1">&nbsp;</td>

                <td><textarea   rows="2" id="nextDo" disabled></textarea></td>

              </tr>

              
            </table>

      </div>

      <div class="button t_c">

        	<input type="button" id="btnSubmit2" value="提交审核" />

&nbsp;

<input type="button" id="closebtn" value="取消申报" />

        </div>

    </div>

    <!--Transparent End-->
	<div class="f_bg">

      <!--Panel_6-->	

        <div class="Divab1">

        	<!--1st-->

            <div class="panel_6">

              <div class="divT">

                <div class="mb10 icon icon_1"></div>

                <div class="more_4"><a  title="更多">更多</a></div>

              </div>

              <div class="divH">

                <div class="divB">

                	<h5 class="clearfix">业务办理</h5>

                	<div class="con">

						<ul class="button clearfix" style="filter:Gray">

                        	<li class="disable"><a  class="ywbl">业务办理</a></li>

                        	<li class="disable"><a class="print">打印</a></li>

                        	<li class="disable"><a  class="jk">业务监控</a></li>

                        	<li class="disable"><a class="tips">小提示</a></li>

                        	<li class="disable"><a class="imp">公文导入</a></li>

                        	<li class="disable"><a  class="exp">公文导出</a></li>

                        </ul>

                	</div>

                </div>

                <div class="divF"></div>

              </div>

            </div>

            <!--1st End-->

        

        </div>

      <!--Panel_6 End-->	

    	<div class="logo_1"></div>

        <div class="gray_bg">

        	<div class="gray_bg2">

            	<div class="w_bg">

                	<div class="Bottom">

                    	<div class="Top">

                        	 <h1 class="t_c">上海申通地铁集团有限公司<br>指标管理征求意见稿</h1>
							 <div class="mb10 Step">编号：企业管理发展部-2014-23</div>
                            <div class="mb10">
            <form id="projectPlanForm">
              <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_2">
                <thead>
                	<th colspan="4" class="f1"> <h5 class="fl">工作联系单基本信息栏</h5>
                  	</th>
                </thead>
                 <tr>
                	<td class="lableTd t_r">主送部门</td>
                    <td colspan="3"> <input  type="text" class="input_large" name="main_department" style="width:100%;"/></td>
                 </tr>
                 
                 <tr>
                	<td class="lableTd t_r">抄送部门</td>
                    <td colspan="3"> <input  type="text" class="input_large" name="cc_department" style="width:100%;"/></td>
                 </tr>
               
                 <tr>
                	<td class="lableTd t_r">主题</td>
                    <td colspan="3"> <input  type="text" class="input_large" name="theme" style="width:100%;"/></td>
                 </tr>
                 
                 <tr>
                  <td class="lableTd t_r" >联系时间</td>
                  <td><input  type="text" class="input_large" name="contactTime" /></td>
                  <td class="lableTd t_r">要求回复时间</td>
                  <td><input  type="text" class="input_large" name="replayTime" /></td>    
                 </tr>
              
                <tr>
                  <td class="lableTd t_r">联系内容</td>
                  <td colspan="3"><textarea name="contact_content" class="input_large" style="width:100%;height:100px;"> </textarea></td>     
                </tr>
              
                
                <tr>
                	<td class="lableTd t_r">附件内容</td>             
                	<td>文件名</td>
                	<td>大小&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;上传时间   </td>	
                	<td>上传人&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备注</td>                               	
                </tr>
                
                <tr>
                	<td class="lableTd t_r" >相关资料</td>
                	<td colspan="3"><input  type="text" class="input_large" name="relevant_data" style="width:100%;"/></td>
                </tr>
                
                <tr>
                	<td class="lableTd t_r" >发起人</td>
                	<td><input  type="text" class="input_large" name="originator" /></td>
                	<td class="lableTd t_r" >发起部门</td>
                	<td><input  type="text" class="input_large" name="department" /></td>
                </tr>
              </table>
             
             
               <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_2" id="applyInfo">
                    <thead>
                        <th colspan="4"><h5 class="fl">工作联系单流转意见栏</h5></th>
                    <td width="1%"></thead>
                 <!--添加信息-->
                 <tr>
                 	<td  class="node"> <b class="fl">部门内部意见</td>
                 </tr>
                 <tr id="sty1">
                     <td>
                         <div class="con b_dash">
                            <i id="sty">部门领导处理中</i>             
                        </div>
                    </td>
                 </tr>
                
                </table>
              </form>
            </div>
            <div class="mb10 t_c">
              <input type="button" id="btnSubmit" value="修 改" />
              &nbsp;
           
              <input type="button"  id="btnwindowClose" value="关 闭" />
              &nbsp; </div>
            <div class="footer"></div>
            <div id="dialog-confirm" title="" style="display:none">
                 <p>是否删除</p>
                        </div>

                    </div>

                </div>

            </div>

        </div>

    </div>

</body>
</html>
