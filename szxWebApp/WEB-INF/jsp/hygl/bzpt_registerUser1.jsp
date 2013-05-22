<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/resources/common/res_all.inc"%>
<%@ page import="com.nbw.common.SysParameter"%>
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="${cssFile}" rel="stylesheet" type="text/css" />
		<link
			href="<%=request.getContextPath()%>/resources/component/formValidator/style/validator.css"
			type="text/css" rel="stylesheet" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/uploadify/scripts/jquery-1.3.2.min.js"></script>
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/formValidator/formValidator.js"></script>
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/formValidator/formValidatorRegex.js"></script>
			<%
	String xtpath = SysParameter.getParameter("xtpath");
%>
		<script type="text/javascript">
$(document).ready(function() {
	    $.formValidator.initConfig({onerror:function(msg){ymPrompt.errorInfo({title:"提示",message:"校验没有通过，具体错误请看错误提示!"});}});
	   
	    $("#loginCode")
	    .formValidator({onshow:"请输入用户名",onfocus:"用户名2~10个字符",oncorrect:"该用户名可以注册"})
	    .inputValidator({min:2,max:10,onerror:"你输入的用户名非法,请确认"})
	    .regexValidator({regexp:"username",datatype:"enum",onerror:"用户名格式不正确"})
	    .ajaxValidator({
			    type : "post",
				url : "bzptuser.action?m=frontLoginCodeIsExit",
				success : function(data){	
		            if( data == "true" )
					{
		                return false;
					}
		            else
					{
		                return true;
					}
				},
				error: function(){alert("服务器没有返回数据，可能服务器忙，请重试");},
				onerror : "请更换用户名",
				onwait : "正在对用户名进行合法性校验，请稍候..."
	     });
	          
	      $("#pwd").formValidator({
	                    onshow: "请输入0～6位数字或者字母",
	                    onfocus: "密码不能为空"
	                }).inputValidator({
	                    min: 1,
	                    onerror: "用户名称不能为空,请确认"
	                });  
	       $("#pwd2").formValidator({onshow:"请输入重复密码",onfocus:"两次密码必须一致",oncorrect:"密码一致"})
	              .inputValidator({min:1,empty:{leftempty:false,rightempty:false,emptyerror:"重复密码两边不能有空符号"},onerror:"重复密码不能为空,请确认"})
	              .compareValidator({desid:"pwd",operateor:"=",onerror:"2次密码不一致,请确认"});
	                
	       $("#realName").formValidator({
	                    onshow: "请输入真实姓名",
	                    onfocus: "真实姓名"
	                }).inputValidator({
	                    min: 1,
	                    onerror: "真实姓名不能为空,请确认"
	                }).inputValidator({
	                    max:10,
	                    onerror: "真实姓名不能超过10个字符"
	                });
	                
	     $("#sbmintButton").click(function(){
	               if($.formValidator.pageIsValid('1')){
	                      $("#theFrom").get(0).submit();
	               }
		})                                            
});

function agree1(){
 var agree = $("#agree");
 if(agree[0].checked){
  $("#sbmintButton")[0].disabled = false;
  $("#sbmintButton").css("cursor","hand");
  $("#agreeDiv").html("");
 }else {
 $("#sbmintButton")[0].disabled = true;
 $("#sbmintButton").css("cursor","auto");
 $("#agreeDiv").html("<img src='<%=request.getContextPath()%>/resources/images/icon_no.gif' />  <font color='red'>请接受服务条款</font>");
 }
}


function sure(){
window.open("<%=xtpath %>/BzptWeb/appmanager/eip/main?_nfpb=true&_pageLabel=P5800125901322719086481&url=/bzptuser.action?m=frontSure'flag=p&width=938&height=550","_blank");
//window.open("bzptuser.action?m=frontSure&flag=p","_blank");

}
</script>
	</head>
	<body>
		<table width="500" height="30" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td>
					<table width="500" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td 
								align="center">
								<table width="500" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td width="500" align="center" valign="top" class="bjing"
											colspan="2">
										</td>
									</tr>
									<tr>
										<td align="center" >
											<table width="500" border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td width="100%" height="40" align="center" class="title">
														创建个人账号
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td align="center">
								<form action="bzptuser.action?m=frontSave1" method="post"
									id="theFrom">
									<input type="hidden" name="fwid" value="${fwid}">
									<table width="500" height="120px" border="0" cellpadding="0" cellspacing="5">
										<tr>
											<td width="100" height="22" align="right" class="menu_p">
												用户名：
											</td>
											<td width="200" align="left" class="menu_p">
												<input type="text" name="loginCode" id="loginCode"
													class="menu_p_reg" />
												<span class="red" height="22">&nbsp;&nbsp;*</span>
											</td>
											<td width="200" align="left" class="menu_p">
												<div class="red_italic" id="loginCodeTip"></div>
											</td>
										</tr>
										<tr>
											<td width="100" height="22" align="right" class="menu_p">
												密码：
											</td>
											<td width="200" align="left" class="menu_p">
												<input type="password" name="password" id="pwd"
													class="menu_p_reg" />
												<span class="red" height="22">&nbsp;&nbsp;*</span>
											</td>
											<td width="200" align="left" class="menu_p">
												<div class="red_italic" id="pwdTip"></div>
											</td>
										</tr>
										<tr>
											<td width="100" height="22" align="right" class="menu_p">
												确认密码：
											</td>
											<td width="200" align="left" class="menu_p">
												<input type="password" name="password2" id="pwd2"
													class="menu_p_reg" />
												<span class="red" height="22">&nbsp;&nbsp;*</span>
											</td>
											<td width="200" align="left" class="menu_p">
												<div class="red_italic" id="pwd2Tip"></div>
											</td>
										</tr>
										<tr>
											<td width="100" height="22" align="right" class="menu_p">
												真实姓名：
											</td>
											<td width="200" align="left" class="menu_p">
												<input type="text" name="name" id="realName"
													class="menu_p_reg" />
												<span class="red" height="22">&nbsp;&nbsp;*</span>
											</td>
											<td width="200" align="left" class="menu_p">
												<div class="red_italic" id="realNameTip"></div>
											</td>
										</tr>
										
										<tr >
						<table>
						<tr >
						<td width="100" height="22" align="right" class="menu_p"></td>
						<td  width="220" align="left" class="menu_p"> <input name="agree" type="checkbox" id="agree" checked  onclick="agree1()" /> 同意"<a href="#" onclick="sure()" ><font color="blue">服务条款</font></a>"
						</td>
						<td width="200" align="left" class="menu_p">
						<div id="agreeDiv" class="red"></div></td>
						</tr>
						</table>
						</tr>
										
									</table>
								</form>
							</td>
						</tr>
						
						
						<tr>
							<td align="center" class="menu_p">
								<img id="sbmintButton" style="cursor: pointer"
									src="${skinPath}images/hygl/login_07.gif" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>