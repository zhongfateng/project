<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>中国食品微生物数据共享平台</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link  href="<%=request.getContextPath()%>/resources/component/formValidator/style/validator.css"
			type="text/css" rel="stylesheet" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/uploadify/scripts/jquery-1.3.2.min.js"></script>
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/formValidator/formValidator.js"></script>
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/formValidator/formValidatorRegex.js"></script>
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/ymPrompt/ymPrompt.js"></script>
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/js/util.js"></script>
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/My97DatePicker/WdatePicker.js"></script>
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/js/sfgl/div.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/resources/component/ymPrompt/skin/qq/ymPrompt.css" />

<style type="text/css">

a:link {
	text-decoration: none;
}

a:visited {
	text-decoration: none;
}

a:hover {
	text-decoration: underline;
}

a:active {
	text-decoration: none;
}
</style>

<script type="text/javascript">
			function subLogin(){
				 $.ajax({  
					type: "post",
					url: "login.action?m=login",
					beforeSend: function(XMLHttpRequest){
						
					},
					success: function(data, textStatus){
					    var dataStr = data.split(",");
						if("success"==dataStr[0]){
							top.location.href = "indexMain.action?m=frontIndexMain";
						}else{
						    alert('用户名或密码有误，登录失败！');
						}
					},
					data :'username='+frm1.userName.value+'&password='+frm1.userPass.value,
					error: function(){
						alert("系统出现异常，请稍后再尝试!");
					}
			   });
			}

	function fone()
	{
	
	window.location.href="fmptuser.action?m=frontIndex&checked=0";
	
	}
	function ftwo()
	{
	window.location.href="fmptuser.action?m=frontIndex&checked=1";
	
	}

</script>
	</head>
	<body>
		<table width="100%" height="170px;" border="0" cellspacing="0"
			cellpadding="0">
			<tr>
				<td height="100%" align="center">
					<iframe frameborder=0 scrolling="no" name="topFrame"
						src="./comm/fmpt_top.jsp" width="100%" height="100%"></iframe>
				</td>
			</tr>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="34"
					background="<%=request.getContextPath()%>/resources/images/login/image/top-bg.jpg">
					&nbsp;
				</td>
			</tr>
		</table>

		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="200" height="71">
					&nbsp;
				</td>
				<td height="70">
					<table width="960" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="39" height="35">
								<img
									src="<%=request.getContextPath()%>/resources/images/login/image/leftmenu-bg01.jpg"
									width="39" height="35" />
							</td>
							<td
								background="<%=request.getContextPath()%>/resources/images/login/image/leftmenu-bg02.jpg"
								class="hei16B">
								用户登录
							</td>
							<td width="13">
								<img
									src="<%=request.getContextPath()%>/resources/images/login/image/leftmenu-bg03.jpg"
									width="13" height="35" />
							</td>
						</tr>
					</table>
					<form action="" id="frm1" method="post" name="form1">
						<table width="960" border="0" cellpadding="0" cellspacing="0"
							style="border: solid; border-color: #d0ced1; border-width: 0px 1px 1px 1px; background-image: url(<%=request.getContextPath()%>/ resources/ images/ login/ image/ lefttext-bg . jpg ); background-position: bottom; background-repeat: repeat-x; margin-bottom: 5px;">
							<tr>
								<td width="530" valign="top">
									<table width="80%" border="0" align="center" cellpadding="0"
										cellspacing="0">
										<tr>
											<td height="70" class="hei14">
												<label>
													&nbsp;&nbsp;&nbsp;
													<input name="radiobutton" type="hidden" value="radiobutton"
														checked="checked" />
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<input name="radiobutton" type="hidden" value="radiobutton" />
												</label>
											</td>
										</tr>
										<tr>
											<td height="50" class="hei14">
												&nbsp;&nbsp;&nbsp;用户名：
												<input class="TxtUserNameCssClass" id="userName"
													maxlength="50" name="userName"
													style="padding-bottom: 6px; height: 24px; border: solid 1px #CCCCCC;" />
												&nbsp;
											</td>
										</tr>
										<tr>
											<td height="50" class="hei14">
												&nbsp;&nbsp;&nbsp;密&nbsp;&nbsp;&nbsp;&nbsp;码：
												<input border="0" type="password"
													class="TxtUserNameCssClass" id="userPass" maxlength="50"
													name="userPass"
													style="padding-bottom: 6px; height: 24px; border: solid 1px #CCCCCC;" />
												&nbsp;
												<span class="lanlink"><a href="fmptuser.action?m=frontFindPwd" style="font-size:12px;">忘记密码</a></span>
											</td>
										</tr>
										<tr>
											<td height="40" class="hei12">
												&nbsp;&nbsp;&nbsp;&nbsp;
												<label>
													<input type="checkbox" name="checkbox3" value="checkbox" />
													记住我的用户名
												</label>
											</td>
										</tr>
										<tr>
											<td height="40" class="hei12">
												&nbsp;&nbsp;&nbsp;&nbsp;
											</td>
										</tr>
										<tr>
											<td height="60">
												<label>
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<img name="Submit322"  src="<%=request.getContextPath()%>/resources/images/login/image/login.jpg"  onclick="subLogin()">
												</label>
											</td>
										</tr>
										<tr>
											<td height="40">
												&nbsp;
											</td>
										</tr>
									</table>
								</td>
								<td width="428" valign="middle">
									<table width="100%" height="261" border="0" cellpadding="0"
										cellspacing="0">
										<tr>
											<td valign="top"
												style="border-left: solid 1px #CCCCCC; padding: 0px 40px;">
												<p class="hei16B">
													还不是平台用户？
												</p>
												<p>
													<span class="hei14">现在免费注册成为平台用户，便能立刻享受更加便利和个性化服务。</span>
													<br />
													<br />
													<br />
												</p>
												<p align="center">
													<img
														src="<%=request.getContextPath()%>/resources/images/login/image/geren.jpg"
														onclick="fone()" />
													&nbsp;&nbsp;&nbsp;&nbsp;
													<img
														src="<%=request.getContextPath()%>/resources/images/login/image/jigou.jpg"
														width="134" height="37" onclick="ftwo()" />
												</p>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
			</table>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>
						&nbsp;
					</td>
				</tr>
			</table>
			<table align="center" width="100%">
				<tr>
					<td height="120">
						<iframe frameborder=0 scrolling="no" name="topFrame" width="100%"
							height="100%" src="./comm/fmpt_bottom.jsp"></iframe>
						<br>
					</td>
				</tr>
			</table>
	</body>
</html>
