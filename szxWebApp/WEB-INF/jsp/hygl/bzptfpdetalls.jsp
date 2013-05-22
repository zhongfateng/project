<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tld/extremecomponents.tld" prefix="ec"%>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<title>订单详细</title>
		<link
			href="<%=request.getContextPath()%>/resources/skins/default/style.css"
			type="text/css" rel="stylesheet">
		<link href="${adminCssFile}" type="text/css" rel="stylesheet" />
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/js/util.js"></script>
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript">
function openwindowsPhd(oid,uid){
		   var URL="bzptsforders.action?m=toPhdOrderAdmin&ordernum="+oid+"&userId="+uid;
		   window.location.replace(URL);
}
function openwindowsFhd(oid,uid){
	   var URL="bzptsforders.action?m=toFhdOrderAdmin&ordernum="+oid+"&userId="+uid;
	   window.location.replace(URL);
}
function backPh(){
		document.all.frm1.action = "bzptsforders.action?m=loadPHOrder";
		document.all.frm1.submit()
}
function backFh(){
		document.all.frm1.action = "bzptsforders.action?m=loadFHOrder";
		document.all.frm1.submit()
}
function backPage(){
	window.location.replace("bzptfp.action?m=loadData");
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
								<span style="font-size: 11px;"> <font color="#016250">
										当前位置信息：发票管理-&gt;&gt; 查看发票 </font> </span>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					

					<div id="table_content">
						<table width="100%" border="0" cellpadding="0"
							style="margin-top: 10px;" class="table_nbw">
							<caption>
								发票详细
							</caption>
							<tr class="table_nbw">
								<td align="center" width="15%" class="td_nbw_left">
									发票抬头：
								</td>
								<td class="td_nbw_right">
									${fp.fptt }
								</td>
							</tr>
							<tr class="table_nbw">
								<td align="center" width="15%" class="td_nbw_left">
									发票内容：
								</td>
								<td class="td_nbw_right">
									${fp.invoicecontent }
								</td>
							</tr>

							<tr class="table_nbw">
								<td align="center" width="15%" class="td_nbw_left">
									发票金额：
								</td>
								<td class="td_nbw_right">
									${fp.je }
								</td>
							</tr>
							<tr class="table_nbw">
								<td align="center" width="15%" class="td_nbw_left">
									收货人姓名：
								</td>
								<td class="td_nbw_right">
									${fp.receivename }
								</td>
							</tr>
							<tr class="table_nbw">
								<td align="center" width="15%" class="td_nbw_left">
									收货人地址：
								</td>
								<td class="td_nbw_right">
									${fp.address }
								</td>
							</tr>
							<tr class="table_nbw">
								<td align="center" width="15%" class="td_nbw_left">
									收货人邮编：
								</td>
								<td class="td_nbw_right">
									${fp.zip }
								</td>
							</tr>
							<tr class="table_nbw">
								<td align="center" width="15%" class="td_nbw_left">
									送货方式：
								</td>
								<td class="td_nbw_right">
									<c:if test="${fp.deliverytype=='1'}">
										普通快递送货上门
									</c:if>
									<c:if test="${fp.deliverytype=='2'}">
										加急快递送货上门
									</c:if>
									<c:if test="${fp.deliverytype=='3'}">
										邮政特快专递 EMS
									</c:if>
								</td>
							</tr>
							<tr class="table_nbw">
								<td align="center" width="15%" class="td_nbw_left">
									收货人手机：
								</td>
								<td class="td_nbw_right">
									${fp.mobile }
								</td>
							</tr>
							<tr class="table_nbw">
								<td align="center" width="15%" class="td_nbw_left">
									充值时间：
								</td>
								<td class="td_nbw_right">
									${fp.czsj }
								</td>
							</tr>
							<tr class="table_nbw">
								<td align="center" width="15%" class="td_nbw_left">
									发票编号：
								</td>
								<td class="td_nbw_right">
									${fp.fpNumber }
								</td>
							</tr>
							<tr>
								<td align="center">
							       <img src="${skinPath}images/button/button_1/fh.gif"
													style="cursor: pointer"
													onclick="backPage();">
								</td>
							</tr>
						</table>
					</div>

				</td>
			</tr>
			<tr height="15px">
				<td>
				</td>
			</tr>
			<c:if test="">
				<tr>
					<td style="width: 20%" align="center" colspan="2">
						<img src="${skinPath}images/button/button_1/fh.gif"
							style="cursor: pointer" onclick="backPh()">
					</td>
				</tr>
			</c:if>
			<c:if test="">
				<tr>
					<td style="width: 20%" align="center" colspan="2">
						<img src="${skinPath}images/button/button_1/fh.gif"
							style="cursor: pointer" onclick="backFh()">
					</td>
				</tr>
			</c:if>
		</table>
		<form id="frm1" action="" method="post">
		</form>
	</body>
</html>