<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tld/extremecomponents.tld" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<html>
	<head>
		<title>列表</title>
		<link href="${cssFile}" type="text/css" rel="stylesheet" />
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/js/util.js"></script>
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/My97DatePicker/WdatePicker.js"></script>
	</head>
	<body style="margin: 0px;">
		<table cellpadding="0" cellspacing="0" width="100%" border="0"
			align="center">
			<tr>
				<td width="2" background="${skinPath}images/hygl/right_03.gif"></td>
				<td height="25" align="left" width="677"
					background="${skinPath}images/hygl/right_04.gif">
					<span class="menu_p" style="padding-left: 10px"> <img
							src="${skinPath}images/hygl/hygl_01.gif" width="9" height="9" />
						&nbsp;您的位置：用户中心-&gt;&gt; <span style="font-size: 12px;">会员余额查询</span>
					</span>
				</td>
				<td width="2" background="${skinPath}images/hygl/right_06.gif"></td>
			</tr>
		</table>
		<div id="table_content">
			<table  class="table_nbw" align="center" width="100%">
				<caption>
					${zh.zhm}余额查询
				</caption>
				<tr>
					<td class="td1" style="width: 20%">
						账号名称：
					</td>
					<td class="left_td" bgcolor="#FFFFFF">
						${zh.zhm}
					</td>
				</tr>
				<tr>
					<td class="td1" style="width: 20%">
						剩余金额(元)：
					</td>
					<td class="left_td" bgcolor="#FFFFFF">
						 <fmt:formatNumber value="${zh.ptmoney}" type="currency"/>
						 
					</td>
				</tr>
				<!--  
				<tr>
					<td colspan=2 align="center">
						<a href="bzptzh.action?m=loadCreateZhPage">充值</a>
					</td>

				</tr>
				-->
			</table>
		</div>
	</body>
</html>

