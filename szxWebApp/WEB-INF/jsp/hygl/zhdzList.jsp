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
function showUserInfo(userId,userName){
     //alert(userId+"+"+userName);
	document.all.frm1.action = "standardresources.action?m=batchBuyStandards&userID="+userId+"&userName="+userName;
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
					<div style="overFlow: auto; height: 100%">
						<ec:table items="userJbList"
							action="bzptzh.action?m=loadUserDZList"
							imagePath="${skinPath}/images/table/*.gif" width="100%"
							rowsDisplayed="15" var="m">
							<ec:row>
								<ec:column property="loginCode" title="登录名称" width="20%">
								</ec:column>
								<ec:column property="userName" title="用户姓名" width="40%" />
								<ec:column property="zhJbName" title="账户级别" width="20%">
								</ec:column>
								<ec:column property="id" title="操作" width="20%">
								<a href="#" onclick="showUserInfo('${m.userID}','${m.userName}')">定制</a>
								</ec:column>
							</ec:row>
						</ec:table>
					</div>
				</td>
			</tr>
		</table>
	</body>
</html>

