<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tld/extremecomponents.tld" prefix="ec"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html>
	<head>
		<title>列表</title>
		<link href="${skinPath}/css/extremecomponents.css" type="text/css"
			rel="stylesheet">
		<link href="${adminCssFile}" type="text/css" rel="stylesheet">
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/js/util.js"></script>
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript">
//增加数据
function kjFp(fpId){ 
  document.all.frm1.action = "bzptfp.action?m=kjFpSave&fpId="+fpId;
  document.all.frm1.submit();
}

</script>

	</head>
	<body style="margin: 0px;">
		<table cellpadding="0" cellspacing="0" width="100%" border="0"
			align="center">
			<tr>
				<td>
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
								<font color="#016250"> 当前位置信息：账户管理中心-&gt;&gt;发票管理</font>  
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<ec:table items="dataList" action="bzptfp.action?m=loadData"
						imagePath="${skinPath}/images/table/*.gif" width="100%"
						rowsDisplayed="15" var="m">
						<ec:row>
							<ec:column property="fpNumber" title="发票编号" width="10%">
							<a href="bzptfp.action?m=findBzptFp&fpNumber=${m.fpNumber}">${m.fpNumber}</a>
							</ec:column>
							<ec:column property="gmrName" title="真实名称" width="10%">
							${m.receivename}
							</ec:column>
							<ec:column property="fptt" title="发票抬头" width="20%" >
							${m.fptt}
							</ec:column>
							<ec:column property="invoicecontent" title="发票内容" width="20%">
							${m.invoicecontent}
							</ec:column>
							<ec:column property="je" title="金额(元)" width="10%">
							${m.je}
							</ec:column>
							<ec:column property="mobile" title="移动电话" width="10%">
							${M.mobile}
							</ec:column>
							<ec:column property="isinvoice" title="发票状态" width="10%">
								<c:if test="${m.isinvoice=='0'}">
									已开发票
								</c:if>
								<c:if test="${m.isinvoice=='1'}">未开发票</c:if>
							</ec:column>
							<ec:column property="isinvoice" title="操作" width="20%">
								<c:if test="${m.isinvoice=='1'}">
									<input type="button" value="开具发票" onclick="kjFp('${m.id}')">
								</c:if>
								<c:if test="${m.isinvoice=='0'}">
									无
								</c:if>
							</ec:column>
						</ec:row>
					</ec:table>
				</td>
			</tr>
		</table>

		<form id="frm1" action="" method="post">
			<input name="objectId" type="hidden">
		</form>

	</body>
</html>

