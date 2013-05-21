<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page import="com.nbw.common.*"%>
<%@ page import="com.nbw.sys.domain.SysUsers"%>
<%
    SysUsers sysUsers = (SysUsers)session.getAttribute(Constants.SESSION_USER_INFO);
    String username = null;
    if(sysUsers!=null){
     username = sysUsers.getLoginCode();
    }
 %>

<%@page import="com.nbw.lucene.domain.Doctxt;"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
   <title>中国食品微生物数据共享平台</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link
			href="<%=request.getContextPath()%>/resources/skins/default/css/search.css"
			rel="stylesheet" type="text/css">
			<link rel="stylesheet" id='skin' type="text/css" href="<%=request.getContextPath()%>/resources/component/ymPrompt/skin/qq/ymPrompt.css" />
			
		
			<script language="javascript">
function tihuan(td){
  var tr = td.parentElement.cells;
  var ob = obody.rows;
  for(var ii=0; ii<tr.length; ii++)
  {
    tr[ii].className = (td.cellIndex==ii)?"tag1_01":"tag1_02";
    ob[ii].style.display = (td.cellIndex==ii)?"block":"none";
  }
}

</script>
<script>
function trim(obj)
{
 return obj.replace(/^\s+|\s+$/g,"")
 } 
function show123()
{
	var arrcook1 = document.cookie.split(";");

			for(i=0;i<arrcook1.length;i++)
			{
				var arr123=arrcook1[i].split("=");

				
				if("hostsupplycount1"==trim(arr123[0]))
				{
					document.cookie="hostsupplycount1=0";
				}
				if("hostsupplyid1"==trim(arr123[0]))
				{
					
					document.cookie="hostsupplyid1=";
					//document.cookie="proid="+hidpro+"";
					
				}
				
				
			}
}
</script>


<!-- 栏目更换结束 -->





<!-- 栏目2更换开始 -->

<script language="javascript">
function change(td){
  var tr = td.parentElement.cells;
  var ob = obody.rows;
  for(var ii=0; ii<tr.length; ii++)
  {
    tr[ii].className = (td.cellIndex==ii)?"menu1_01":"menu1_02";
    ob[ii].style.display = (td.cellIndex==ii)?"block":"none";
  }
}

</script>
<script>
function trim(menu)
{
 return menu.replace(/^\s+|\s+$/g,"")
 } 
function show123()
{
	var arrcook1 = document.cookie.split(";");

			for(i=0;i<arrcook1.length;i++)
			{
				var arr123=arrcook1[i].split("=");

				
				if("hostsupplycount1"==trim(arr123[0]))
				{
					document.cookie="hostsupplycount1=0";
				}
				if("hostsupplyid1"==trim(arr123[0]))
				{
					
					document.cookie="hostsupplyid1=";
					//document.cookie="proid="+hidpro+"";
					
				}
				
				
			}
}
</script>
		<script language="Javascript" type="text/javascript">

			function downLoad()
			{
		
			 var userId = '<%=(String)session.getAttribute(Constants.SESSION_USER_ID)%>';
 		   if(userId == null || userId ==""||userId == "null")
 		   {
			window.location.href='login.action?m=toLoginIndexQt';
			}
			
			
			else
			{
			
			window.location.href='upload.action?m=downloadFile&filename=${doctxt.pmid}&title=${doctxt.title}';
			
			}
			}
			function tiaozhuan()
			{
			
			
			}
</script>
			<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/ymPrompt/ymPrompt.js"></script>	
			
			
  </head>
  
  <body>
<table width="100%" height="170px;" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="100%" align="center"><iframe frameborder=0 scrolling="no" name="topFrame"
						src="./comm/fmpt_top.jsp" width="100%" height="100%"></iframe></td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="34" background="${skinPath}images/lucene/top-bg.jpg">&nbsp;</td>
  </tr>
</table>
<table width="965" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="25" valign="top" class="hei12">首页-&gt;文献资源-&gt;文献检索</td>
  </tr>
</table>
<c:if test="${doctxt.publisher==null}">
<table width="960" height="65" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="cccccc">
	<% Doctxt doctxt=(Doctxt)request.getAttribute("doctxt");%>

	
	

  <tr>
    <td width="960" valign="top" bgcolor="#FFFFFF" style="padding:20px;"><p><span class="hei14">${doctxt.journal }&nbsp; ${doctxt.publicationDate} year&nbsp; <%=doctxt.getVolume()%>&nbsp;Volume&nbsp;&nbsp;&nbsp;&nbsp;</span>&nbsp;&nbsp;&nbsp;&nbsp;
      <label>
      <img src="${skinPath}images/lucene/down-pdf.jpg" width="102"
									height="25"  onclick="downLoad()" />
      </label>
      <br />
      <br />
      <br />
      <span class="hei16B" id="title">${doctxt.title }</span><br />
      <br />
    </p>
	  <span class="hei14"><strong>Author：</strong>${ doctxt.authorFull}<br />
	<strong>Organization：</strong>${doctxt.affiation}<br />
    <strong>Abstract：</strong>
	${doctxt.abstract_}<br/>
<strong>DownLoad Num：</strong>${doctxt.featureWord}</span></td>
  </tr>
</table>
</c:if>

<c:if test="${doctxt.publisher!=null}">
<table width="960" height="65" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="cccccc">
	<% Doctxt doctxt=(Doctxt)request.getAttribute("doctxt");%>

	
	

  <tr>
    <td width="960" valign="top" bgcolor="#FFFFFF" style="padding:20px;"><p><span class="hei14">${doctxt.journal }&nbsp; ${doctxt.publicationDate} 年第<%=doctxt.getVolume()%>期&nbsp;&nbsp;&nbsp;&nbsp;</span>&nbsp;&nbsp;&nbsp;&nbsp;
      <label>
      <img src="${skinPath}images/lucene/down-pdf.jpg" width="102"
									height="25"  onclick="downLoad()" />
      </label>
      <br />
      <br />
      <br />
      <span class="hei16B" id="title">${doctxt.title }</span><br />
      <br />
    </p>
	  <span class="hei14"><strong>作者：</strong>${ doctxt.authorFull}<br />
	<strong>关键字：</strong>${doctxt.keyWord}<br />
	<strong>出版社：</strong>${doctxt.publisher}<br />
    <strong>摘要：</strong>
	${doctxt.abstract_}<br/>
<strong>下载次数：</strong>${doctxt.featureWord}</span></td>
  </tr>
</table>
</c:if>



  <table align="center" width="100%">
<tr>
  <td height="120" ><iframe frameborder=0 scrolling="no" name="topFrame" width="100%" height="100%"
src="./comm/fmpt_bottom.jsp" ></iframe></td>
</tr>
</table>
   
  </body>
</html>
