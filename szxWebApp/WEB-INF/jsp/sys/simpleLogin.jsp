<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link
			href="<%=request.getContextPath()%>/resources/component/formValidator/style/validator.css"
			type="text/css" rel="stylesheet" />
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/jquery.js"></script>
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/formValidator/formValidator.js"></script>
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/formValidator/formValidatorRegex.js"></script>
		<script type="text/javascript">
$(document).ready(function() {
	    $.formValidator.initConfig({onerror:function(msg){ ymPrompt.errorInfo({title:"提示",message:"校验没有通过，具体错误请看错误提示!"});}});
	    $("#userName").formValidator({
	                    onshow: "请输入登录名称",
	                    onfocus: "登录名称不能为空"
	                }).inputValidator({
	                    min: 1,
	                    onerror: "登录名称不能为空,请确认"
	                });
	     $("#pwd").formValidator({
	                    onshow: "请输入密码",
	                    onfocus: "密码不能为空"
	                }).inputValidator({
	                    min: 1,
	                    onerror: "密码不能为空,请确认"
	                });
	       $("#sbmintButton").click(function(){
	                  if($.formValidator.pageIsValid('1')){
	                     $("#theFrom").get(0).submit();
	                      window.opener=null;       
                          window.open('','_self');
                          window.close();
	                      window.close();
	                  }
	              })    
	       $("#clearButton").click(function(){
	                   $("input[type='text']").val(''); //清空内容 
	                   $("#pwd").val(''); //清空内容 
	                   window.opener=null;       
                       window.open('','_self');
                       window.close();

	              })            
	    })
	</script>
	</head>

	<body>
		<form id="theFrom" action="login.action?m=simpleLogin" method="post">
			<table>
				<caption>
					登录
				</caption>
				<tr>
					<td>
						用户名称：
					</td>
					<td>
						<input type="text" name="username" id="userName">
					</td>
					<td>
						<div class="" id="userNameTip"></div>
					</td>
				</tr>
				<tr>
					<td>
						密码：
					</td>
					<td>
						<input type="password" name="password" id="pwd">
					</td>
					<td>
						<div class="" id="pwdTip"></div>
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" value="确定" id="sbmintButton">
					</td>
					<td>
						&nbsp;&nbsp;
					</td>
					<td>
						<input type="button" value="重置" id="clearButton">
					</td>
				</tr>
				<input type="hidden" name="flagURL"
					value="<%=request.getAttribute("flagURL")%>">
			</table>
		</form>
	</body>
</html>
