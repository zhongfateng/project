<%@ page contentType="text/html; charset=utf-8" language="java"
	errorPage=""%>
<%@ page import="com.nbw.sys.domain.SysUsers"%>
<%@page import="com.nbw.common.Constants"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%
			SysUsers lui = (SysUsers) session
					.getAttribute(Constants.SESSION_USER_INFO);
		%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>国家标准技术服务平台-后台管理</title>
		<link href="${adminCssFile}" rel="stylesheet" type="text/css" />

		<script language="javascript">
	function on_src(url){
		var ifrs=document.getElementById("infoFrame");
		ifrs.src=url;
	}
	function onTree(mg){
		var te=document.getElementById("tree");
		var tw1=document.getElementById("tw");
		if(te.style.display==''){
			te.style.display='none';
			mg.src="${skinPath}images/manage/b_right.gif";
			tw1.width='100%';
		}else{
			te.style.display='';
			mg.src="${skinPath}images/manage/b_left.gif";
			tw1.width='74%';
		}
	}
	function exitLogin(url){
		if (confirm("您确定退出管理系统吗？")) {
			window.location='login.action?m=ExitAdminLogin';
		    return true;
		}
		
	}
	
</script>
	</head>
	<body>
		<table width="1024" border="0" height="52" cellspacing="0"
			cellpadding="0" align="center">
			<tr>
				<td colspan="4">
					<img src="${skinPath}images/manage/banner.jpg" width="100%"
						height="64"/>
				</td>
			</tr>
			<tr>
				<td height="30" width="24"
					background="${skinPath}images/manage/banner2.jpg">
				</td>
				<td height="30" width="882"
					background="${skinPath}images/manage/banner2.jpg">
					欢迎用户：<%=lui.getLoginCode()%>
				</td>
				<td height="30" width="118"
					background="${skinPath}images/manage/banner2.jpg">
					<a href="javascript:exitLogin()" class="hy_black">退出登录</a>
				</td>
			</tr>
		</table>
		<table width="1024" border="0" align="center" cellpadding="0"
			cellspacing="0" id="frm_table">
			<tr bgcolor="#FFFFFF">
				<td width="25%" align="left" valign="top" id="tree"
					bgcolor="#FFFFFF">
					<table border="0" width="100%" cellspacing="1" cellpadding="0"
						bgcolor="#65B9B9">
						<tr>
							<td width="100%">
								<iframe src="login.action?m=toTree" scrolling="no" width="100%"
									height="600" id="leftFrame" frameborder="0"></iframe>
							</td>
						</tr>
					</table>
				</td>
				<td width="1%" align="center" valign="middle">
					<img src="${skinPath}images/manage/b_left.gif" border="0"
						onclick="onTree(this)" />
				</td>
				<td id="tw" width="74%" colspan="2" align="left" valign="top">
					<table border="0" width="100%" cellspacing="1" cellpadding="0"
						bgcolor="#65B9B9">
						<tr>
							<td width="100%" valign="top" bgcolor="#FFFFFF">
								<iframe src="login.action?m=toWelcome" scrolling="no"
									width="100%" height="600" id="infoFrame" frameborder="0"></iframe>
								<br />
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="60" colspan="4">
					<iframe frameborder=0 scrolling="no" name="myframe"
						src="${skinPath}bzpt_bottom.jsp" width="100%" height="100"></iframe>
				</td>
			</tr>
		</table>
	</body>
</html>