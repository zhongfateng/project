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
function openCreateZhPage(czdId,czrId){
       document.all.frm1.action = "bzptzh.action?m=toEditPage&czdId="+czdId+"&czrId="+czrId;
	   document.all.frm1.submit();
}
function czdSqFp(czdId){
       document.all.frm1.action = "bzptczd.action?m=toSqCzdFp&czdId="+czdId;
	   document.all.frm1.submit();
}
function showZhInfo(zhId){
	document.all.frm1.action = "bzptzh.action?m=showHtBookInfo&userId="+zhId;
	document.all.frm1.submit();

}
function changPage(URL){
   window.location.replace(URL);
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
								<font color="#016250"> 当前位置信息：账户管理中心-&gt;&gt;账户充值</font>
							</td>
							<td width="50%" align="right"
								background="${skinPath}images/manage/dh_bg.jpg">
								<table border="0" cellspacing="0" cellpadding="0" align="right">
									<tr style="height: 21">
										<td>
											<c:if test="${iscl==0}">
												<img id="sbmintButton" style="cursor: pointer"
													src="${skinPath}images/yczlb.gif"
													onclick="javascript:changPage('bzptczd.action?m=loadClData');" />
											</c:if>
											<c:if test="${iscl!=0}">
												<img id="sbmintButton" style="cursor: pointer"
													src="${skinPath}images/wczlb.gif"
													onclick="javascript:changPage('bzptczd.action?m=loadData');" />
											</c:if>
										</td>
										<td style="width: 3">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<ec:table items="dataList" action="bzptczd.action?m=loadClData"
						imagePath="${skinPath}/images/table/*.gif" width="100%"
						rowsDisplayed="15" var="m">
						<ec:row>
						     <ec:column property="logCode" title="登录名" width="10%" />
							<ec:column property="userName" title="用户名" width="10%" />
							<ec:column property="je" title="充值金额" width="10%" />
							<ec:column property="bz" title="备注" width="50%" />
							<ec:column property="sqsj" title="申请时间" cell="date"
								format="yyyy-MM-dd" width="10%" />
							<ec:column property="czrid" title="充值状态" width="15%">
								<c:if test="${m.iscl!=0}">
									已充值
								</c:if>
							</ec:column>
							<ec:column property="isXYFP" title="发票操作" width="15%">
								<c:if test="${m.isXYFP=='0'}">
								   未申请开具发票
									<!-- <input type="button" value="申请开具发票" class="button_nbw"
										style="width: 80" onclick="czdSqFp('${m.id}');" /> -->
								</c:if>
								<c:if test="${m.isXYFP=='1'}">
									  已申请开具发票
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

