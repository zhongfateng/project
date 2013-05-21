<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="com.nbw.docToDB.domain.Gene"%>
<%@ page import="com.nbw.common.SysParameter"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt"%>
<%
		String bzzyWebApp = SysParameter.getParameter("bzzyWebApp");
	%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>编缉页面</title>
		<link href="${adminCssFile}" type="text/css" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/component/kindeditor/themes/default/default.css" />
	<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/component/kindeditor/plugins/code/prettify.css" />
	<script charset="utf-8" src="<%=request.getContextPath() %>/resources/component/kindeditor/kindeditor.js"></script>
	<script charset="utf-8" src="<%=request.getContextPath() %>/resources/component/kindeditor/lang/zh_CN.js"></script>
	<script charset="utf-8" src="<%=request.getContextPath() %>/resources/component/kindeditor/plugins/code/prettify.js"></script>
	<script charset="utf-8" src="<%=request.getContextPath() %>/resources/component/kindeditor/plugins/image/image.js"></script>
			<link
			href="<%=request.getContextPath()%>/resources/component/uploadify/css/uploadify.css"
			rel="stylesheet" type="text/css" />
		<link
			href="<%=request.getContextPath()%>/resources/component/formValidator/style/validator.css"
			type="text/css" rel="stylesheet" />
			<link rel="stylesheet" id='skin' type="text/css" href="<%=request.getContextPath()%>/resources/component/ymPrompt/skin/qq/ymPrompt.css" />
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/resources/js/validata.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/uploadify/scripts/jquery-1.3.2.min.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/uploadify/scripts/swfobject.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/uploadify/scripts/jquery.uploadify.v2.1.0.min.js"></script>
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/formValidator/formValidator.js"></script>
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/formValidator/formValidatorRegex.js"></script>
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/My97DatePicker/WdatePicker.js">
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.6.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/component/ymPrompt/ymPrompt.js"></script>

	<script type="text/javascript">
		$(function() {
	$('#f').validate( {
		rules : {
			username : {
				required : true
			},
			fsum : {
				required : true,
				digits : true
			}
		},
		messages : {
			username : {
				required : "<span style='color:red'>必填</span>"
			},
			fsum : {
				required : "<span style='color:red'>必填</span>",
				digits : "<span style='color:red'>请输入数字</span>"
			}
		}
	});
});
		function fback(){
			var ogrid = document.getElementById("orgid").value;
			window.location.href="fmptJfSum.action?m=toUserJfAllList&orgId="+ogrid;
		};
	</script>
	</head>
	<body>
		<table width="100%" height="30" border="0" cellpadding="0"
			cellspacing="0">
			<tr class="tr_title">
				<td width="2%" background="${skinPath}images/manage/dh_bg.jpg">
					&nbsp;


				</td>
				<td background="${skinPath}images/manage/dh_bg.jpg">
					<img src="${skinPath}/images/manage/dh-bg0.gif" width="14"
						height="14" />
					<font color="#016250"> 当前位置信息:修改用户积分</font>
				</td>
			</tr>
		</table>
		<div style="overFlow: auto; height: 550px">
		<form name="example" method="post" action="fmptJfSum.action?m=toUpJfSum" id="f">
				<table width="100%" align="center" class="table_nbw">
					<tr>
						<td>
							<input type="hidden" name="id" value="${bzptbook.id}" />
							<input type="hidden" name="imgId" id="img_Id"
								value="${bzptbook.imgId}" />
						</td>
					</tr>
					<tr>
						<td>
							<input type="hidden" name="tid" value="${fsum.id}" />
							<input type="hidden" name ="orgid" id="orgid" value="${orgid}"/>
						</td>
					</tr>
					<tr class="tr_nbw" id="d3_1">
						<td width="40%" class="td_nbw_left" align="center">
							请输入用户名:
						</td>
						<td height="30" class="td_nbw_right">
							<input type="text" id="d3_2" name="username"
								size="40%" value="${fsum.username}" readonly="readonly"/>
							<span id="slTip" style="width: 200px"></span>
						</td>
					</tr>
					
					<tr class="tr_nbw" id="d4_1">
						<td width="40%" class="td_nbw_left" align="center">
							请输入用户真实姓名:
						</td>
						<td height="30" class="td_nbw_right">
							<input id="d4_2" name="trname" size="40%" value="${fsum.trname}" readonly="readonly"/>
							<span id="isbnTip" style="width: 200px; color: red;"></span>
						</td>
					</tr>
					
					<tr class="tr_nbw" id="d4_1">
						<td width="40%" class="td_nbw_left" align="center">
							请输入用户积分:
						</td>
						<td height="30" class="td_nbw_right">
							<input id="d4_2" name="fsum" size="40%" value="${fsum.jfsum}"/>
							<span id="isbnTip" style="width: 200px; color: red;"></span>
						</td>
					</tr>
					 
					<tr>
						<td height="30" class="td_nbw_right" align="center" colspan="2">
							<input type="submit" name="button" value="提交内容"/>
							<input type="button" name="back" value="返回" onclick="fback();"/>
							<span id="isbnTip" style="width: 200px"></span>
						</td>
					</tr>
					<tr>
						<td>
							<input type="hidden" name="tgz" id="orgIds"
								value="${bzptbook.tgz}" />
						</td>
					</tr>
				</table>
				</form>
		</div>
	</body>
</html>