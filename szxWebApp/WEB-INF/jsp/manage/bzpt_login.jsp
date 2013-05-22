<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/resources/common/res_all.inc"%>

<html>
	<head>
	<style type="text/css">
	<!--
	body {
		background-image: url(${skinPath}images/hygl/login_bg1.jpg);
	}
	
	.user_main_text {
		color: #000000;
		font-size: 12px;
		LINE-HEIGHT: 34px;
		HEIGHT: 34px;
	}
	
	.TxtUserNameCssClass {
		BORDER-TOP-WIDTH: 0px;
		PADDING-LEFT: 25px;
		BORDER-LEFT-WIDTH: 0px;
		BACKGROUND: url(${skinPath}images/hygl/user_login_name.gif) no-repeat;
		BORDER-BOTTOM-WIDTH: 0px;
		WIDTH: 165px;
		LINE-HEIGHT: 20px;
		HEIGHT: 21px;
		BORDER-RIGHT-WIDTH: 0px
	}
	
	.TxtPasswordCssClass {
		BORDER-TOP-WIDTH: 0px;
		PADDING-LEFT: 25px;
		BORDER-LEFT-WIDTH: 0px;
		BACKGROUND: url(${skinPath}images/hygl/user_login_password.gif)
			no-repeat;
		BORDER-BOTTOM-WIDTH: 0px;
		WIDTH: 165px;
		LINE-HEIGHT: 20px;
		HEIGHT: 21px;
		BORDER-RIGHT-WIDTH: 0px
	}
	
	.TxtyanzhengmaCssClass {
		BORDER-TOP-WIDTH: 0px;
		PADDING-LEFT: 25px;
		BORDER-LEFT-WIDTH: 0px;
		BACKGROUND: url(${skinPath}images/hygl/user_login_validatecode.gif)
			no-repeat;
		BORDER-BOTTOM-WIDTH: 0px;
		WIDTH: 165px;
		LINE-HEIGHT: 20px;
		HEIGHT: 21px;
		BORDER-RIGHT-WIDTH: 0px
	}
	
	.IbtnEnterCssClass {
		WIDTH: 111px;
		HEIGHT: 122px
	}
	-->
	</style>
		<title>登录</title>
		<script type="text/javascript">
			function sub(){
				var options = {
					type: 'POST',
					async: false,
					dataType: 'content-type',
					success: function(responseText){
						if(responseText=="<%=Constants.ERROR_LOGIN %>"){
							alert("用户名或密码错误！");
						}else{
							window.location="<%=request.getContextPath()%>/login.action?m=toMain";
						}
					}
				}
				//提交form
				$('#myform').ajaxSubmit(options);
			}      
			function doClick(e){       
				if(e.keyCode == 13){       
					sub(); 
					return true;
				}else{
				    return false;
				}
			}  
		</script>
	</head>
	<body>
	<table width="586" border="0" align="center" cellpadding="0">
			<tr>
				<td height="98">&nbsp; 
					 
				</td>
			</tr>
			<tr>
				<td width="586" height="413" valign="top" background="${skinPath}images/hygl/login_bg2.jpg">
                   	<form action="login.action?m=loginHt" name="myform" method="post"	id="myform">
					<table width="400" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td height="130" colspan="3">&nbsp;
							  
						      </td>
						</tr>
						<tr>
							<td width="78" height="40" align="right" class="user_main_text">
								<span class="STYLE1">用户名：</span>
							</td>
							<td width="184">
								<span class="user_main_input">
								<input	class="TxtUserNameCssClass" maxlength="20"
										name="username"  value="admin"/> </span>
							</td>
							<td width="138" rowspan="3" align="right">
								<img class="IbtnEnterCssClass"
									style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-RIGHT-WIDTH: 0px;cursor:pointer"
									onclick="sub(this.form)" src="${skinPath}images/hygl/user_botton.gif" onKeyDown="13" />
							</td>
						</tr>
						<tr>
							<td height="40" align="right" class="user_main_text">
								密 &nbsp;码：
							</td>
							<td>
								<span class="user_main_input"> <input
										class="TxtPasswordCssClass" maxlength="20" type="password"
										name="password" onKeyPress="doClick(event)"  value="dbz"/> </span>
							</td>
						</tr>
					</table>
                    </form>
				</td>
			</tr>
		</table>
	</body>
</html>
