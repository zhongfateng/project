<%@ page language="java" pageEncoding="utf-8"%>
<%@page import="com.nbw.hygl.domain.BzptZhJl"%>
<%@page import="com.nbw.hygl.domain.BzptZh"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/extremecomponents.tld" prefix="ec"%>
<html>
	<head>
		<title>显示后台账户页面</title>
		<link href="${adminCssFile}" type="text/css" rel="stylesheet">
		<link href="${skinPath}/css/extremecomponents.css" type="text/css"
			rel="stylesheet">
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
function onSumbit(){
   document.all.tzframe.submit();
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
					<font color="#016250"> 当前位置信息:详细信息</font>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" align="center" class="table_nbw">
						<tr>
							<td align="center" colspan="2">
								用户基本信息
							</td>
						</tr>
						<tr class="tr_nbw">
							<td width="10%" class="td_nbw_left" align="center">
								单位名称:
							</td>
							<td height="30" class="td_nbw_right">
								${user.char11}
							</td>
						</tr>
						<tr class="tr_nbw">
							<td width="10%" class="td_nbw_left" align="center">
								通讯地址:
							</td>
							<td height="30" class="td_nbw_right">
								${user.char13}
							</td>
						</tr>
						<tr class="tr_nbw">
							<td width="10%" class="td_nbw_left" align="center">
								邮政编码:
							</td>
							<td height="30" class="td_nbw_right">
								${user.char14}
							</td>
						</tr>
						<tr class="tr_nbw">
							<td width="10%" class="td_nbw_left" align="center">
								联系电话:
							</td>
							<td height="30" class="td_nbw_right">
								${user.char15}
							</td>
						</tr>
						<tr class="tr_nbw">
							<td width="10%" class="td_nbw_left" align="center">
								E-mail:
							</td>
							<td height="30" class="td_nbw_right">
								${user.char16}
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr height="10">
				<td></td>
			</tr>
			<tr>
				<td>
					<form action="bzptzh.action?m=tzZhInfo&userId=${userId}"
						id="tzfrme" name="tzframe" method="post">
						<table width="100%" align="center" class="table_nbw">
							<tr>
								<td align="center" colspan="2">
									账户基本信息
								</td>
							</tr>
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
							<tr class="tr_nbw">
								<td width="10%" class="td_nbw_left" align="center">
									充值金额:
								</td>
								<td height="30" class="td_nbw_right">
									<input type="text" id="tzJe" name="tzJe">
								</td>
							</tr>
							<tr class="tr_nbw">
								<td align="center" colspan="2">
									<Input type="button" style="cursor: pointer" value="充值"
										onclick="onSumbit();">
								</td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
			<tr height="10">
				<td></td>
			</tr>
			<tr>
				<td>
					<table width="100%" align="center" class="table_nbw">
						<tr>
							<td align="center" colspan="2">
								充值历史详细信息
							</td>
						</tr>
						<tr>
							<td>
								<ec:table items="zhjlList" action=""
									imagePath="${skinPath}/images/table/*.gif" width="100%"
									rowsDisplayed="15" var="m">
									<ec:row>
										<ec:column property="czje" title="金额" width="33%" />
										<ec:column property="czsj" title="充值时间" width="33%">
											<fmt:formatDate value="${m.czsj}" pattern="yyyy-MM-dd" />
										</ec:column>
										<ec:column property="czrName" title="充值人" width="33%" />
									</ec:row>
								</ec:table>
							</td>
						</tr>
					</table>

				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" height="40" border="0" cellpadding="0"
						cellspacing="0">
						<tr>
							<td height="40" align="center" colspan="2">
								<img src="${skinPath}images/button/button_1/fh.gif"
									style="cursor: pointer" onclick="backPage();">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<form id="frm1" action="" method="post">
		</form>
	</body>
</html>
