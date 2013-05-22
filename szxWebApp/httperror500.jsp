<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%
	String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>
	<HEAD>
		<title>HTTP 500</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	</HEAD>
	<style>
LI {
	font-size: 14px;
	line-height: 150%;
	margin-top: 10px;
}

body {
	background-image: url(<%=path%>/resources/images/err/er_bg1.gif);
}
</style>
	<BODY>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td >
					&nbsp;
				</td>
			</tr>
		</table>
		<center>
			<table width="582" border="0" cellpadding="0" cellspacing="0"
				background="<%=path%>/resources/images/err/er_bg3.gif">
				<tr>
					<td width="100" height="209">
					</td>
					<td width="358" align="left" valign="middle"
						style="padding-right: 50px;">
						<br>
						<br>
						<ul>
							<li>
								系统出问题了，请联系管理员！！
							<li>
								System is a query, please refresh or try again later.
					</td>
				</tr>
				<tr>
					<td height="100">
						&nbsp;
					</td>
					<td height="100" align="left" valign="top"
						style="padding-left: 60px;">
							<a href="javascript:history.go(0);" title="refresh"><img
								src="<%=path%>/resources/images/err/er_bg2.gif" width="100"
								height="35" border=0> </a>
						&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="javascript:history.back();" title="history back"><img
								src="<%=path%>/resources/images/err/er_bg7.gif" width="100"
								height="35" border=0> </a>
					</td>
				</tr>
			</table>
		</center>

	</BODY>
</HTML>
