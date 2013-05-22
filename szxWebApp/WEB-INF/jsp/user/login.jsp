<%@page pageEncoding="utf-8" contentType="text/html; charset=utf-8"
	language="java" isELIgnored="false"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>实名制质量信用信息化平台</title>
		<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
		<link href="<%=request.getContextPath()%>/resources/user/style.css"
			rel="stylesheet" type="text/css" />
			
			
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-1.6.2.js"></script>	
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.validate.js"></script>
	<script type="text/javascript">
		$(function(){
		$('#form1').validate( {
		rules : {
			username : {
				required : true,
			},
			userpwd :{
				required : true,
			},
			vercode : {
				required : true,
				remote : "login.action?m=toValiImgCode"
			}
		},
		messages : {
			username : {
				required : "<span style='color:red'>必填</span>",
			},
			userpwd :{
				required :"<span style='color:red'>必填</span>",
			},
			vercode : {
				required : "<span style='color:red'>必填</span>",
				remote : "<span style='color:red'>输入错误</span>"
			}
		}
	});
	});
	
	
	function MyFocus(){
		if($("#username").val()=="请输入用户名"){
			$("#username").val("");
		}
	};
		
		function MyBlur(){
			if($("#username").val()==""){
				$("#username").val("请输入用户名");
			}
		};
	</script>		
	</head>

	<body>
		<table width="1424" height="32" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td bgcolor="777877">&nbsp; 
				</td>
			</tr>
		</table>
		<table width="1424" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="811" height="147">
					<img
						src="<%=request.getContextPath()%>/resources/user/image/login-left.jpg"
						width="811" height="690" />
				</td>
				<td valign="top"
					background="<%=request.getContextPath()%>/resources/user/image/login-r4.jpg">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="613" height="68">
								&nbsp;
							</td>
						</tr>
						<tr>
							<td height="102">
								<img
									src="<%=request.getContextPath()%>/resources/user/image/shimz.png"
									width="296" height="46" />
							</td>
						</tr>
					</table>
					<form action="login.action?m=toCheckUser" method="post" name="form1" id="form1">
					<table border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="60" colspan="3">
								<input name="username" type="text" class="input1"
									 id="username"  onblur="MyBlur();" value="admin"/>
							</td>
						</tr>
						<tr>
							<td height="60" colspan="3">
								<input name="userpwd" type="password" class="input2"
									value="dbz" />
									<span style="color: red"><%=request.getAttribute("errInfo")%></span>
							</td>
						</tr>
						<tr>
							<td width="62" height="70" class="hei14B">
								验证码：
							</td>
							<td width="561">
								<input type="text" name="vercode" class="input3" />
								<img align="absmiddle" src="login.action?m=toGetImageCode" id="imgCode" width="100px;" height="30px;"/>
              <label> 看不清？<a href="javascript:;" onclick="document.getElementById('imgCode').src='login.action?m=toGetImageCode&'+Math.random();">换一张</a></label>
							</td>
						</tr>
						<tr>
							<td height="60" colspan="3">
								<input name="submit" value="" type="submit" style="background-image: url('<%=request.getContextPath() %>/resources/user/image/log-in.png');width:153px; height:51px;border:0px;"/>
								<input name="quite" value="" type="button" style="background-image: url('<%=request.getContextPath()%>/resources/user/image/cancle.png');width:153px;height:51px;border:0px;"/>
							</td>
						</tr>
					</table>
					</form>
				</td>
			</tr>
		</table>
	</body>
</html>