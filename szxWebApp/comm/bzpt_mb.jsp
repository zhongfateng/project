<%@ page language="java" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>模板</title>
	</head>
	<body style="background-color: #cccccc">
		<table width="100%" height="30" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td>
					<jsp:include
						page="../comm/bzpt_top.jsp"
						flush="true" />
				</td>
			</tr>
			<tr>
				<td align="center">
					
				</td>
			</tr>
			<tr>
				<td>
					<jsp:include
						page="../comm/bzpt_bottom.jsp"
						flush="true" />
				</td>
			</tr>
		</table>
	</body>
</html>