<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/resources/common/res_all.inc"%>
<html>
	<head>	
	<link href="${adminCssFile}" rel="stylesheet" type="text/css" />	
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.hei14 {
	font-family: "宋体";
	font-size: 14px;
	font-style: normal;
	line-height: 20px;
	font-variant: normal;
	color: #000000;
}

a.hei14:link,a.hei14:visited {
	font-family: "宋体";
	font-size: 14px;
	line-height: 20px;
	font-variant: normal;
	color: #E5F5FB;
}

a.hei14:hover,a.hei14:active {
	font-family: "宋体";
	font-size: 14px;
	line-height: 20px;
	font-variant: normal;
	color: #ffcc66;
}

.hei12 {
	font-family: "宋体";
	font-size: 12px;
	font-style: normal;
	line-height: 20px;
	font-variant: normal;
	color: #333333;
	padding-left: 10px;
}

a.hei12:link,a.hei12:visited {
	font-family: "宋体";
	font-size: 12px;
	line-height: 20px;
	font-variant: normal;
	color: #006DAC;
}

a.hei12:hover,a.hei12:active {
	font-family: "宋体";
	font-size: 12px;
	line-height: 20px;
	font-variant: normal;
	color: #ffcc66;
	padding-left: 10px;
}

.TxtUserNameCssClass {
	BORDER-TOP-WIDTH: 0px;
	PADDING-LEFT: 25px;
	BORDER-LEFT-WIDTH: 0px;
	BACKGROUND: url(${skinPath}images/htgl/text.jpg) no-repeat;
	BORDER-BOTTOM-WIDTH: 0px;
	WIDTH: 200px;
	LINE-HEIGHT: 20px;
	HEIGHT: 31px;
	BORDER-RIGHT-WIDTH: 0px
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
						if(responseText=="<%=Constants.ERROR_LOGIN%>"){
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
function clearName(){
   $("#username").attr("value",'');//清空内容 
   $("#password").attr("value",'');//清空内容 
}
		</script>
	</head>
	<body>
		<table width="100%" height="747" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td valign="top" background="${skinPath}images/htgl/login-bg.jpg">
					<table width="110" height="110" border="0" align="center"
						cellpadding="0" cellspacing="0">
						<tr>
							<td>
								&nbsp;
							</td>
						</tr>
					</table>
					<table width="996" height="455" border="0" align="center"
						cellpadding="0" cellspacing="0">
						<tr>
							<td width="466" height="376">
								<img src="${skinPath}images/htgl/login-logo.jpg" width="466"
									height="356" />
							</td>
							<td width="386" rowspan="2">
								<form action="login.action?m=loginHt"  method="post" id="myform">
									<table width="386" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td height="50">
												<span class="hei14"><img src="${skinPath}images/htgl/circle.gif" width="33"
														height="33" />&nbsp;登录网站后台管理</span>
											</td>
										</tr>
										<tr>
										<td height="50" class="hei12">
												管理员：
												<label>
													<input class="TxtUserNameCssClass" id="TxtUserName"
														maxlength="20" name="username" value="admin"/>
												</label>
											</td>
										</tr>
										<tr>
											<td height="50" class="hei12">
												密 &nbsp;码：
												<input class="TxtUserNameCssClass" id="TxtUserName2"  type="password" maxlength ="20" name="password" onKeyPress="doClick(event)" value="dbz" />&nbsp;
												<img src="${skinPath}images/htgl/login-lock.jpg" width="14"  height="16" />
												</td>
										</tr>
										<tr>
											<td height="50" class="hei12">
												<label>
													&nbsp;&nbsp;&nbsp;&nbsp;
													<img src="${skinPath}images/htgl/login-denglu.jpg" onclick="sub(this.form)"
														onKeyDown="13" />
													&nbsp;
													<img src="${skinPath}images/htgl/login-cancle.jpg"  onclick="clearName()"/>
												</label>
											</td>
										</tr>
										<tr>
											<td height="50">
												&nbsp;
											</td>
										</tr>
										<tr>
											<td align="right">
												<img src="${skinPath}images/htgl/login-welcome.jpg"
													width="240" height="85" />
											</td>
										</tr>
									</table>
								</form>
							</td>
							<td width="500" rowspan="2">
								&nbsp;
							</td>
						</tr>
						<tr>
							<td height="77">
								&nbsp;
							</td>
						</tr>

					</table>
					
					<table width="996" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td height="74">
								&nbsp;
							</td>
						</tr>
						<tr>
							<td height="86" align="center" class="qianlan12">
								地址：北京北三环东路18号中国计量科学研究院 邮编：100013 电话：010-84290315
								邮箱：admin@cfmpd.org
								<br />
								⑥ 版权所有 北京中防昊通科技中心 京ICP证 84290315号 京公网安备 102154441414 2011-2012
								CFMPD
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>

</html>
