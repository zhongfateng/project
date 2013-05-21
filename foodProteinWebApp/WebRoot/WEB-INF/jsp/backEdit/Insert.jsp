<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Random"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt"%>
<%
request.setCharacterEncoding("UTF-8");
String htmlData = request.getParameter("body") != null ? request.getParameter("body") : "";
%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>后台用户须知编辑</title>
			<link href="${adminCssFile}" type="text/css" rel="stylesheet">
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
	
	
	
	
	
	
	<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/component/kindeditornew/themes/default/default.css" />
	<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/component/kindeditornew/plugins/code/prettify.css" />
	<script charset="utf-8" src="<%=request.getContextPath() %>/resources/component/kindeditornew/kindeditor.js"></script>
	<script charset="utf-8" src="<%=request.getContextPath() %>/resources/component/kindeditornew/lang/zh_CN.js"></script>
	<script charset="utf-8" src="<%=request.getContextPath() %>/resources/component/kindeditornew/plugins/code/prettify.js"></script>
	<script>
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="body"]', {
				cssPath : '<%=request.getContextPath() %>/resources/component/kindeditor/plugins/code/prettify.css',
				uploadJson : '<%=request.getContextPath() %>/usage.action?m=frontUpLoad',
			
				allowFileManager : true
				
				
			});
			prettyPrint();
		});
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
					<font color="#016250"> 当前位置信息:后台科技动态上传</font>
				</td>
			</tr>
		</table>
		<div style="overFlow: auto; height: 550px">
<%=htmlData%>
<form name="example" method="post" action="<%=request.getContextPath() %>/usage.action?m=insertDB" id="f">
				<table width="100%" align="center" class="table_nbw">
										<tr><td><input type="hidden" id="d3_2" name="id"
								size="40%"  value="<%=new Random(1000).nextInt()%>" /></td>
							</tr>
					
					
					<tr class="tr_nbw" id="d3_1">
						<td width="50%" class="td_nbw_left" align="center">
							请输入标题:
						</td>
						<td height="30" class="td_nbw_right">
							<input type="text" id="d3_2" name="title"
								size="40%"  />
							<span id="slTip" style="width: 200px"></span>
						</td>
					</tr>
				
				
					<tr class="tr_nbw" id="d7_1">
						<td width="50%" class="td_nbw_left" align="center" colspan="2">
							文章正文:
						</td>
					</tr>
					 
					<tr>
						<td height="30" class="td_nbw_right" align="center" colspan="2">
							<textarea name="body"  id="textarea" cols="100" rows="8" style="width:100%;height:400px;visibility:hidden;" > </textarea>
							<br />
							<input type="submit" name="button" value="提交内容"/>
							<span id="isbnTip" style="width: 200px"></span>
						</td>
					</tr>
					<tr>
						<td><input type="hidden" name="tgz" id="orgIds"
								value="${bzptbook.tgz}" />
						</td>
					</tr>
				</table>
				</form>
		</div>


	
</body>
</html>
<%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;
}
%>