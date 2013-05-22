<%@ page language="java" pageEncoding="utf-8"%>
<%@page import="com.nbw.hygl.domain.BzptZhJl"%>
<%@page import="com.nbw.hygl.domain.BzptZh"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt"%>
<html>
	<head>
		<title>显示前台账户页面</title>
		<link href="${cssFile}" type="text/css" rel="stylesheet">
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/resources/js/validata.js"></script>
		<script type="text/javascript">
function backPage(){
	var id=parent.document.getElementById("orgId").value; 
    document.all.frm1.action = "bzptzh.action?m=loadOnclickLeafUserPage&orgId="+id;
    document.all.frm1.submit();
}
</script>
	</head>
	<body>
		<table width="100%" height="30" border="0" cellpadding="0"
			cellspacing="0">
			<tr class="tr_title">
				<td background="${skinPath}images/manage/dh_bg.jpg">
					<img src="${skinPath}/images/manage/dh-bg0.gif" width="14"
						height="14" />
					<font color="#016250"> 当前位置信息:账户详细信息</font>
				</td>
			</tr>
		</table>
		<table width="100%" align="center" class="table_nbw">
			<tr class="tr_nbw">
				<td width="10%" class="td_nbw_left" align="center">
					账户名称:
				</td>
				<td height="30" class="td_nbw_right">
					${zh.zhm }
				</td>
			</tr>
			<tr class="tr_nbw">
				<td width="10%" class="td_nbw_left" align="center">
					剩余金额:
				</td>
				<td height="30" class="td_nbw_right">
					${zh.ptmoney}
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<table width="100%" align="center" class="table_nbw">
						<tr>
							<td>
								金额
							</td>
							<td>
								时间
							</td>
							<td>
								充值人
							</td>
						</tr>
						<c:forEach items="${zhjlList}" var="m" begin="0" varStatus="i">
							<tr>
								<td>
									${m.czje}
								</td>
								<td>
									<fmt:formatDate value="${m.czsj}" pattern="yyyy-MM-dd" />
								</td>
								<td>
									${m.czrName}
								</td>
							</tr>
						</c:forEach>
					</table>
				</td>
			</tr>
			<tr class="tr_nbw">
				<td height="40" align="center" colspan="2">
					<img src="${skinPath}images/button/button_1/fh.gif"
						style="cursor: pointer" onclick="backPage();">
				</td>
			</tr>
		</table>

		<form id="frm1" action="" method="post">
		</form>
	</body>
</html>
