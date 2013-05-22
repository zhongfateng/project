<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>实名制质量信用信息化平台</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="<%=request.getContextPath()%>/resources/images/searchIndex/images/skin.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #F7F8F9;
}
-->
</style>


	<link rel="stylesheet" id='skin' type="text/css" href="<%=request.getContextPath()%>/resources/component/ymPrompt/skin/simple_gray/ymPrompt.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.6.2.js"></script>
	<script type="text/javascript" src ="<%=request.getContextPath()%>/resources/component/ymPrompt/ymPrompt.js"></script>
 	<script type="text/javascript">
	function setValue(x)
	{
	document.getElementById(x).value='';
	}
	</script>
 	<script type="text/javascript">
 	$(function(){
 	$("#searchid").click(function(){
 	var inputName= $("#zzjgname").val();
			if(inputName=="请输入9位组织机构代码……")
			{
			ymPrompt.alert("请输入组织机构代码");
			}
			else{
			var number=inputName.length;
			inputName=$.trim(inputName);
			var reg=/^[A-Za-z0-9]+$/
			if(number!=9||!reg.test(inputName))
			{
			ymPrompt.alert("请输入正确的组织机构代码");
			}
			else
			{
			window.location.href="jbxx.action?m=frontToGetDetailByJbId&id="+inputName;
			
			}
			
			}
 	
 	
 	});
 	
 	
 	
 	});
 	
 	
 	
 	
 	
 	</script>
 <script type="text/javascript">
		$(function(){
		
		$("#jgmohu").click(function()
		{
		var inputName= $("#jgname").val();
		if(inputName=="请输入机构全称或关键词……"||inputName=="")
		{
		ymPrompt.alert("请输入组织机构全程或关键字");
		}
		else
		{
		
		inputName=$.trim(inputName);
		
		var reg=/^[\u4e00-\u9fa5]+$/i; 
		if(!reg.test(inputName))
		{
			ymPrompt.alert("输入的内容包括非法字符");
		}
		else
		{
			//window.location.href="jbxx.action?m=frontToGetList&inputName="+encodeURI(encodeURI(inputName));
		window.location.href="jbxx.action?m=frontToShowList&inputName="+encodeURI(encodeURI(inputName));
		
		}
		
		
		
		}
		});
		
		});
	</script>
 
 
 
 
  </head>
  
  <body>
   <table width="100%" border="0" cellpadding="0" cellspacing="0" background="<%=request.getContextPath()%>/resources/images/searchIndex/images/news-title-bg02.gif" bgcolor="#FFFFFF" style="border-bottom:solid 2px #eeeeee">
  <tr>
    <td width="69%" height="25">&nbsp;</td>
    <td width="31%" align="center">&nbsp;</td>
  </tr>
</table>
<table width="300" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="40">&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
<table width="960" height="64" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#F7F8F9">
  <tr>
    <td height="78" align="center" valign="bottom"><img src="<%=request.getContextPath()%>/resources/images/searchIndex/images/logo1.jpg" width="240" height="135"></td>
  </tr>
</table>
<table width="960" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#F7F8F9">
  <tr>
    <td valign="middle" bgcolor="#F7F8F9">
    
    <table width="300" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="40">&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
    </table>
	      <!--JavaScript部分-->

        <!--HTML部分-->
        <table width="450" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td align="center">没有找到相应的记录!!</td>
          </tr>
        </table>
		  
		   <!--关于TBODY标记-->
		   <table width="300" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40">&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
      </table>
      <p>&nbsp;</p>    
      
      </td>
    </tr>
    <tr></tr>
</table>
<table width="960" border="0" align="center" cellpadding="0" cellspacing="0" class="line_table">
  <tr>
    <td width="7%" height="27" background="<%=request.getContextPath()%>/resources/images/searchIndex/images/news-title-bg02.gif"><img src="<%=request.getContextPath()%>/resources/images/searchIndex/images/news-title-bg.gif" width="2" height="27"></td>
    <td width="93%" background="<%=request.getContextPath()%>/resources/images/searchIndex/images/news-title-bg02.gif" class="left_bt2">最新动态</td>
  </tr>
  <tr>
    <td valign="top">&nbsp;</td>
    <td height="70" valign="top"><br>
        <span class="left_txt">&nbsp;<img src="<%=request.getContextPath()%>/resources/images/searchIndex/images/ts.gif" width="16" height="16">&nbsp;说明：输入组织机构代码查询可得到 </span><span class="left_ts">唯一</span><span class="left_txt">结果。通过机构名称查询有可能得到多条结果！ </span></td>
  </tr>
  <tr>
    <td height="5" colspan="2">&nbsp;</td>
  </tr>
</table>
<blockquote>&nbsp;</blockquote>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" background="<%=request.getContextPath()%>/resources/images/searchIndex/images/man-info_30.png">
  <tr>
    <td width="144">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>

<table width="960" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan="2" valign="middle"><span class="left_txt"><br>
    </span></td>
    <td width="1%">&nbsp;</td>
    <td width="40%" valign="top">&nbsp;</td>
  </tr>
  <tr>
    <td width="2%">&nbsp;</td>
    <td colspan="3" align="center" class="left_txt"><img src="<%=request.getContextPath()%>/resources/images/searchIndex/images/icon-mail2.gif" width="16" height="11"> 客户服务邮箱：123121212@qq.com&nbsp;&nbsp;&nbsp;&nbsp;        <img src="<%=request.getContextPath()%>/resources/images/searchIndex/images/icon-phone.gif" width="17" height="14"> 官方网站：http://www.11212121.cn</td>
  </tr>
</table>
  </body>
</html>
