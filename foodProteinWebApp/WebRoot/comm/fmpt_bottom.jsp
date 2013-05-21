<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="com.nbw.common.SysParameter"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="${csscssFile}" rel="stylesheet" type="text/css" />
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
	</head>
	<%
		String foodProteinWebApp = SysParameter.getParameter("foodProteinWebApp");
	%>
	<body>
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			background="${skinPath}images/bottom-bg.jpg">
			<tr>
				<td height="120" align="center" class="hei14">
					<a href="<%=foodProteinWebApp%>/comm/fmpt_linkus.jsp" class="hei14" target="_blank">联系我们</a> |
					<a href="javascript:window.external.Addfavorite('http://www.cfmd.com.cn/','食品微生物平台')" class="hei14">收藏本站</a> |
					<a href="<%=foodProteinWebApp%>/comm/fmpt_sitepolicy.jsp" class="hei14" target="_blank">网站声明</a>
					<br />
					网站管理：北京中防昊通科技中心
					<br />
					地址：北京北三环东路18号中国计量科学研究院 邮编：100013
				</td>
			</tr>
		</table>
	</body>
</html>