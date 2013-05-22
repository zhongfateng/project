<%@ page contentType="text/html; charset=utf-8" language="java" errorPage=""%>
<%@ page import="com.nbw.common.SysParameter"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="${skinPath}css/css.css" rel="stylesheet" type="text/css" />
	<style type="text/css">
	<!--
	a:link {
		text-decoration: none;
	}
	a:visited {
		text-decoration: none;
	}
	a:hover {
		text-decoration: underline;
	}
	a:active {
		text-decoration: none;
	}

	
	
	-->
	</style>
		<script language="javascript"
			src="<%=request.getContextPath()%>/resources/js/jquery.pack.js"></script>
		<script language="javascript">
   function ajaxValidate(url,frm){
	  $.ajax({
			type: "post",
			url: url,
			beforeSend: function(XMLHttpRequest){
				//ShowLoading();
			},
			success: function(data, textStatus){
				if("success"==data){
					var ttf=document.getElementById('t1');
					var ttd=document.getElementById('t2');
					ttd.innerHTML='欢迎用户：'+frm.username.value;
					ttf.style.display='none';
					ttd.style.display='';
				}else{
				    alert('用户名或密码有误，登录失败！');
				}
			},
			data :'username='+frm.username.value+'&password='+frm.password.value,
			complete: function(XMLHttpRequest, textStatus){
				//HideLoading();
			},
			error: function(){
				alert("系统出现异常，请稍后再尝试!");
			}
	   });
   }
</script>

	</head>
    <%
		String bzzyWebApp = SysParameter.getParameter("bzzyWebApp");
	%>
	<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
		<td height="79" align="center" background="${skinPath}images/comm/index_04.gif"><table width="938" border="0" cellspacing="0" cellpadding="0">
		  <tr>
			<td align="left"><img src="${skinPath}images/comm/index_06.gif" width="427" height="79" /></td>
			<td align="right" valign="bottom" class="more"><a href="javascript:window.external.Addfavorite('/BzptWeb/appmanager/eip/main','标准平台')" class="more">加入收藏</a> |<a href="<%=bzzyWebApp%>/appmanager/eip/main?_nfpb=true&_pageLabel=P5800125901322719086481&url=/comm/bzpt_aboutus.jsp&width=938&height=400" class="more">关于平台</a> &nbsp;&nbsp;</td>
		  </tr>
		</table></td>
	  </tr>
	</table>
	<table width="938" border="0" align="center" cellpadding="0" cellspacing="0" background="${skinPath}images/comm/index_09.gif">
	  <tr>
		<td width="131" height="73" align="center"><a href="/BzptWeb/appmanager/eip/main"  target="_top" class="title">首 页</a></td>
		<td width="681"><table width="100%" border="0" cellspacing="0" cellpadding="0">
		  <tr>
			<td height="35"><a href="/BzptWeb/appmanager/eip/main?_nfpb=true&_pageLabel=portal_main_portal_page_3" class="title"  target="_top">国家标准化资源</a></td>
			<td><a href="/BzptWeb/appmanager/eip/main?_nfpb=true&_pageLabel=portal_main_portal_page_4" target="_top" class="title">国际标准资源</a></td>
			<td><a href="/BzptWeb/appmanager/eip/main?_nfpb=true&_pageLabel=portal_main_portal_page_6" target="_top" class="title">TBT/SPS资源</a></td>
			<td><a href="/BzptWeb/appmanager/eip/main?_nfpb=true&_pageLabel=portal_main_portal_page_7" target="_top" class="title">标准文献资源</a></td>
			<td><a href="/BzptWeb/appmanager/eip/main?_nfpb=true&_pageLabel=portal_main_portal_page_8" target="_top" class="title">标准全文资源</a></td>
		  </tr>
		  <tr>
			<td height="35"><a href="/BzptWeb/appmanager/eip/main?_nfpb=true&_pageLabel=portal_main_portal_page_9" target="_top" class="title">数&nbsp;据&nbsp;检&nbsp;索</a></td>
			<td><a href="/BzptWeb/appmanager/eip/main?_nfpb=true&_pageLabel=portal_main_portal_page_10"  target="_top" class="title">标 准 图 书</a></td>
			<td><a href="/BzptWeb/appmanager/eip/main?_nfpb=true&_pageLabel=portal_main_portal_page_11" target="_top"  class="title">标&nbsp;准&nbsp;服&nbsp;务</a></td>
			<td><a href="/BzptWeb/appmanager/eip/main?_nfpb=true&_pageLabel=portal_main_portal_page_12" target="_top"  class="title">热&nbsp;门&nbsp;话&nbsp;题</a></td>
			<td><a href="/BzptWeb/appmanager/eip/main?_nfpb=true&_pageLabel=portal_main_portal_page_13" target="_top"  class="title">意&nbsp;见&nbsp;征&nbsp;集</a></td>
		  </tr>
		</table></td>
		<td width="126"><a href="/BzptWeb/appmanager/eip/main?_nfpb=true&_pageLabel=portal_main_portal_page_14" target="_top" class="cain">企业阅览室&gt;&gt;</a></td>
	  </tr>
	</table>
	</body>
</html>