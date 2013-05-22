<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="com.nbw.hygl.domain.BzptZh"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html>
	<head>
		<title>充值账户界面</title>
		<link href="${adminCssFile}" type="text/css" rel="stylesheet">
		<link
			href="<%=request.getContextPath()%>/resources/component/formValidator/style/validator.css"
			type="text/css" rel="stylesheet" />
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/resources/js/validata.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/uploadify/scripts/jquery-1.3.2.min.js"></script>
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/formValidator/formValidator.js"></script>
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/formValidator/formValidatorRegex.js"></script>
		<script type="text/javascript">
			function backPage(){
				document.all.zhFrom.action = "bzptczd.action?m=loadData";
				document.all.zhFrom.submit();
			}
			$(document).ready(function() {
					$("#sbmintButton").click(function(){
						var oldValue=document.getElementById("oldPtmony").value;
					    var addValue=document.getElementById("addPtmony").value;
							var oldPtmony=parseFloat(oldValue);
						    var addPtmony=parseFloat(addValue);
						    document.getElementById("newptmoney").value=(oldPtmony+addPtmony)+"";
						    document.all.zhFrom.action="bzptzh.action?m=save";
						    document.all.zhFrom.submit();
					});
			});
		</script>
	</head>
	<body>
		<input type="hidden" id="oldPtmony" value="${bzptzh.ptmoney}" size="20" />
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
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
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<form method="post" name="zhFrom" id="zhFrom">
						<table width="100%" class="table_nbw">
							<caption>
								账户充值
							</caption>
							<tr class="tr_nbw">
								<td style="width: 20%" class="td_nbw_left">
									<input type="hidden" name="id" value="${bzptzh.id}" />
									<input type="hidden" name="czdId" value="${czd.id}" />
									账号名称：
								</td>
								<td class="td_nbw">
									<input type="hidden" name="zhm" value="${bzptzh.zhm}" />
									${bzptzh.zhm}
								</td>
							</tr>
							<tr class="tr_nbw">
								<td style="width: 20%" class="td_nbw_left">
									类型：
									<input type="hidden" name="glid" value="${bzptzh.glid}" />
								</td>
								<td class="td_nbw_right">
									${bzptzh.type}
									<input type="hidden" name="type" value="${bzptzh.type}" />
								</td>
							</tr>
							<tr class="tr_nbw">
								<td style="width: 20%" class="td_nbw_left">
									当前金额：
								</td>
								<td class="td_nbw_right">
									<input type="hidden" id="newptmoney" name="ptmoney" value=""
										size="20" />
									${bzptzh.ptmoney} 元
								</td>
							</tr>
							<tr class="tr_nbw">
								<td style="width: 20%" class="td_nbw_left">
									充值金额：
								</td>
								<td class="td_nbw_right">
									<input type="text" id="addPtmony" value="${czd.je}"  readonly="readonly" size="20" />
									元
									<span id="addPtmonyTip" style="width: 200px"></span>
								</td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
			<tr>
				<td>
					<table>
						<tr class="tr_nbw">
							<td style="width: 20%" align="center" colspan="2">
								<img src="${skinPath}images/button/button_1/bc.gif"
									style="cursor: pointer" id="sbmintButton">
								<img src="${skinPath}images/button/button_1/fh.gif"
									style="cursor: pointer" onclick="backPage();">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>
