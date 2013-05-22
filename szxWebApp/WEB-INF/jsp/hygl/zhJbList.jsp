<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tld/extremecomponents.tld" prefix="ec"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html>
	<head>
		<title>列表</title>
		<link href="${skinPath}css/extremecomponents.css" type="text/css"
			rel="stylesheet" />
		<%@ include file="/resources/common/res_all.inc"%>
		<link href="${adminCssFile}" type="text/css" rel="stylesheet" />
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/js/util.js"></script>
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript">
function showUserInfo(userId){
	document.all.frm1.action = "bzptzh.action?m=showZhJb&userID="+userId;
	document.all.frm1.submit();

}
    
</script>
	</head>
	<body style="margin: 0px;">
		<form id="frm1" action="" method="post"></form>
		<table width="98%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td>
					<div style="overFlow: auto; height: 550px">
						<ec:table items="userJbList"
							action="bzptzh.action?m=loadUserJbList"
							imagePath="${skinPath}/images/table/*.gif" width="100%"
							rowsDisplayed="15" var="m">
							<ec:row>
								<ec:column property="loginCode" title="登录名称" width="30%">
									<a href="#" onclick="showUserInfo('${m.userID}')"> ${m.loginCode}</a>
								</ec:column>
								<ec:column property="userName" title="用户姓名" width="40%" />
								<ec:column property="zhJbName" title="账户级别" width="30%">
								</ec:column>
							</ec:row>
						</ec:table>
					</div>
				</td>
			</tr>
		</table>
	</body>
</html>

