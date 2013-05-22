<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.nbw.hygl.domain.BzptHyJb"%>

<html>
	<head>
		<title>编缉页面</title>
		<link href="${adminCssFile}" type="text/css" rel="stylesheet">
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/resources/js/validata.js"></script>
		<script type="text/javascript">
function checkTheForm(){
	  	document.myForm.submit();
}
function backPage(){
	document.all.frm1.action = "bzpthytx.action?m=LoadData";
	document.all.frm1.submit();
}
</script>
	</head>

	<body>
		<form action="bzpthytx.action?m=save" method="post" name="myForm">
			<table width="100%" height="30" border="0" cellpadding="0"
				cellspacing="0">
				<tr class="tr_title">
					<td width="2%" background="${skinPath}images/manage/dh_bg.jpg">
						&nbsp;
					</td>
					<td background="${skinPath}images/manage/dh_bg.jpg">
						<img src="${skinPath}/images/manage/dh-bg0.gif" width="14"
							height="14" />
						&nbsp;
						<span style="font-size: 11px;"> <font color="#016250">
								当前位置信息: 添加会员提醒信息 </font> </span>
					</td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="table_nbw" border="3">
				<tr class="tr_nbw">
					<td width="30%" class="td_nbw_left" align="center">
						提醒内容:
					</td>
					<td height="30" class="td_nbw_right">
						<input type="text" name="txnr" value="${bzpthytx.txnr}" size="40" />
					</td>
				</tr>
				<tr class="tr_nbw">
					<td width="30%" class="td_nbw_left" align="center">
						选择级别:
					</td>
					<td height="30" class="td_nbw_right">
						<select name="jbid">
							<%
								List jblist = (List) request.getAttribute("jbList");
								for (int i = 0; i < jblist.size(); i++) {
									BzptHyJb bh = (BzptHyJb) jblist.get(i);
									out.write("<option value=\"");
									out.write(bh.getId());
									out.write("\">");
									out.write(bh.getJbmc());
									out.write("</option>");
								}
							%>
						</select>
					</td>
				</tr>
				<tr class="tr_nbw">
					<td align="center" colspan="2">
						<img src="${skinPath}images/button/button_1/bc.gif"
							style="cursor: pointer" onclick="checkTheForm();">
						<img src="${skinPath}images/button/button_1/fh.gif"
							style="cursor: pointer" onclick="backPage();">
					</td>
				</tr>
			</table>
		</form>
		<form id="frm1" action="" method="post">
		</form>
	</body>
</html>
