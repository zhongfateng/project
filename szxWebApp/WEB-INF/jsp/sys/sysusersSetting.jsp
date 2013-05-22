<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="com.nbw.common.Constants"%>
<%
	String id = (String) request.getSession().getAttribute(
			Constants.SESSION_USER_ID);
	id = "101";
%>
<html>
	<head>
		<title>用户设置</title>
		<%@ include file="/resources/common/res_all.inc"%>
		<script type="text/javascript">
			function submitForm(){
		 		var passwordold = document.myform.passwordold;
		 		var passwordnew = document.myform.passwordnew;
		 		var repasswordnew = document.myform.repasswordnew;
			if (isLegalText(passwordold,-1,32,"原密码")==false) {
				return false;
			}
			if (isLegalText(passwordnew,6,32,"新密码")==false) {
				return false;
			}
			if(passwordnew.value != repasswordnew.value){
				ymPrompt.errorInfo({title:"提示",message:"两次输入密码不一致！"});
			}
		
			var options = {
				type: 'POST',
				async: false,
				dataType: 'content-type',
				success: function(responseText) {
					if ("success"==responseText.substring(0,7)) {
						ymPrompt.succeedInfo({title:"提示",message:"保存成功！"});
					}else{
						ymPrompt.errorInfo({title:"提示",message:"保存失败，密码不正确！"});
					}
				}
			}
			//提交附属信息
			$('#myform').ajaxSubmit(options);
		}
	</script>

	</head>
	<body>
		<form action="sysusers.action?m=resetUser" name="myform" id="myform"
			method="post">
			<table width="1000" border="0" cellpadding="0" cellspacing="0"
				align="center">
				<tr>
					<td height="26"
						background="resources/skins/default/images/navigation.gif">
						<table width="1000" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="4">
									&nbsp;
								</td>
								<td>
									<img src="resources/skins/default/images/nav_title.gif"
										width="16" height="16" />
									用户设置
								</td>
								<td align="right" valign="middle">
									<input type="button" icon="icon-save" onclick="submitForm()"
										value="保存" />
									<input type="button" icon="icon-reset"
										onclick="javascript:window.history.back()" value="返回" />
								</td>
								<td width="5">
									&nbsp;
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>

			<table align="center" border="1" cellpadding="0" cellspacing="0"
				width="1000" bordercolorlight="#8FC5DC" bordercolordark="#FFFFFF">
				<input type="hidden" name="id" value="<%=id%>">
				<tr>
					<td class="ceeedit_label">
						原密码:
					</td>
					<td>
						<input type="text" name="passwordold" value="" />
					</td>
				</tr>
				<tr>
					<td>
						新密码:
					</td>
					<td>
						<input type="password" name="passwordnew" id="passwordnew"
							value="">
					</td>
				</tr>
				<tr>
					<td>
						确认新密码:
					</td>
					<td>
						<input type="password" name="repasswordnew" id="repasswordnew"
							value="">
					</td>
				</tr>
				<tr>
					<td>
						皮肤:
					</td>
					<td>
						<input type="text" name="pskin" value="">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
