<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中国食品微生物数据共享平台</title>
		<link  href="<%=request.getContextPath()%>/resources/skins/default/css/zhuce.css"	rel="stylesheet" type="text/css" />
		<style type="text/css">
.TxtUserNameCssClass {
	BORDER-TOP-WIDTH: 0px;
	PADDING-LEFT: 10px;
	padding-right: 15px;
	BORDER-LEFT-WIDTH: 0px;
	BACKGROUND: url(resources/images/sys_zhuce/text.jpg) no-repeat;
	BORDER-BOTTOM-WIDTH: 0px;
	WIDTH: 165px;
	LINE-HEIGHT: 28px;
	HEIGHT: 31px;
	BORDER-RIGHT-WIDTH: 0px
}

.TxtUser-s {
	BORDER-TOP-WIDTH: 0px;
	PADDING-LEFT: 10px;
	padding-right: 15px;
	BORDER-LEFT-WIDTH: 0px;
	BACKGROUND: url(resources/images/sys_zhuce/s-text.jpg) no-repeat;
	BORDER-BOTTOM-WIDTH: 0px;
	WIDTH: 92px;
	LINE-HEIGHT: 28px;
	HEIGHT: 31px;
	BORDER-RIGHT-WIDTH: 0px
}
</style>
		<link
			href="<%=request.getContextPath()%>/resources/component/formValidator/style/validator.css"
			type="text/css" rel="stylesheet" />
		<link rel="stylesheet" id='skin' type="text/css"
			href="<%=request.getContextPath()%>/resources/component/ymPrompt/skin/qq/ymPrompt.css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/uploadify/scripts/jquery-1.3.2.min.js"></script>
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/formValidator/formValidator.js"></script>
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/formValidator/formValidatorRegex.js"></script>
		<script language="JavaScript" type="text/javascript"  src="<%=request.getContextPath()%>/resources/component/ymPrompt/ymPrompt.js"></script>

		<script type="text/javascript">
       
	$(document).ready(function() {
	    $.formValidator.initConfig({ validatorGroup: "1", formID: "zhuce1",onerror:function(msg){ ymPrompt.errorInfo({title:"提示",message:"校验没有通过，具体错误请看错误提示!"});}});
	    $("#loginCode")
	    .formValidator({validatorGroup: "1", tipID: "loginCodeTip",onshow:"请输入用户名",onfocus:"用户名2~10个字符",oncorrect:"该用户名可以注册"})
	    .inputValidator({min:2,max:10,onerror:"你输入的用户名非法,请确认"})
	    .regexValidator({regexp:"username",datatype:"enum",onerror:"用户名格式不正确"})
	    .ajaxValidator({
			    type : "post",
				url : "fmptuser.action?m=frontLoginCodeIsExit",
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
				error: function(){alert("服务器没有返回数据，可能服务器忙，请重试!!!!!");},
				onerror : "该用户名不可用，请更换用户名",
				onwait : "正在对用户名进行合法性校验,请稍候..."
	      });
	         $("#pwd").formValidator({validatorGroup: "1", tipID: "pwdTip",
	                    onshow: "请输入0～6位数字或者字母",
	                    onfocus: "密码不能为空"
	                }).inputValidator({
	                    min: 1,
	                    onerror: "用户名称不能为空,请确认"
	                });   
	       $("#pwd2").formValidator({validatorGroup: "1", tipID: "pwdTip2",onshow:"请输入重复密码",onfocus:"两次密码必须一致",oncorrect:"密码一致"})
	              .inputValidator({min:1,empty:{leftempty:false,rightempty:false,emptyerror:"重复密码两边不能有空符号"},onerror:"重复密码不能为空,请确认"})
	              .compareValidator({desid:"pwd",operateor:"=",onerror:"2次密码不一致,请确认"});
	                
	       $("#realName").formValidator({validatorGroup: "1", tipID: "realNameTip",
	                    onshow: "请输入真实姓名",
	                    onfocus: "真实姓名"
	                }).inputValidator({
	                    min: 1,
	                    onerror: "真实姓名不能为空,请确认"
	                }).inputValidator({
	                    max:10,
	                    onerror: "真实姓名不能超过10个字符"
	                });
           $("#attchar11").formValidator({validatorGroup: "1", tipID: "attchar11Tip",
               onshow: "请输入单位名称",
               onfocus: "单位名称"
           });
       
           $("#attchar13").formValidator({
               onshow: "请输入通讯地址",
               onfocus: "通讯地址"
         });
          $("#attchar15").formValidator({
	                    onshow: "请输入联系电话",
	                    onfocus: "例如：0577-88888888",
	                    oncorrect: "联系电话不能为空"
	                }).inputValidator({
	                    min: 1,
	                    onerror: "联系不能为空,请确认"
	                }).regexValidator({
		                    regexp:"tel",
			                datatype:"enum",
			                onerror:"国内电话格式不正确"
		                });
		   $("#attchar16").formValidator({
	                    onshow: "请输入E-mail",
	                    onfocus: "E-mail不能为空"
	                }).inputValidator({
	                    min: 1,
	                    onerror: "邮箱不能为空,请确认"
	                }).regexValidator({
	                   regexp:"^([\\w-.]+)@(([[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.)|(([\\w-]+.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(]?)$",
	                   onerror:"你输入的邮箱格式不正确"});
           $("#submitButton").click(function(){
	                 if($.formValidator.pageIsValid('1')){
						  $("#zhuce1").get(0).submit();
						}
				   });
        	$("#one").click(function(){
    		window.location.href="fmptuser.action?m=frontIndex&checked=0";
    		});
   	 		$("#two").click(function(){
    		window.location.href="fmptuser.action?m=frontIndex&checked=1";
    		});                                    
});
              

	function ff(){
	
	var objSelect=document.getElementById("attchar03");
	var sel=document.getElementsByName("attchar10")[0];
	var selvalue= sel.options[sel.options.selectedIndex].value
	objSelect.focus();

	$.ajax({
	url:"city.action?m=frontGetCity",
	data:{provincialID:selvalue},
	error:function(){
						alert("系统出现异常，请稍后再尝试!");
						},
	success: function(data){
		objSelect.options.length=0;
	
		var strs=new Array();
		strs=data.split("|");
		for(var i=0;i<strs.length;i++)
		{
		
		   var varItem = new Option(strs[i], i);      
        	objSelect.options.add(varItem); 
		}
		
	}
	
	});
};

	function tanchu()
	{
		var code =	document.getElementById("loginCode");
		var pwd =	document.getElementById("pwd");
		var dh =	document.getElementById("attchar15");
		var yx =	document.getElementById("attchar16");
		if(code.value==""||pwd.value==""||dh.value==""||yx.value=="")
		{
		ymPrompt.alert("带*项不能为空，为必填项");
		code.focus();
		return false;
		
		}
		else
		{
		return true;
		}
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
				<td width="200" height="71">
					&nbsp;
				</td>
				<td height="70">
					<table width="960" height="65" border="0" cellpadding="0"
						cellspacing="0" style="margin: 5px 0px">
						<tr>
							<td
								style="padding: 20px; border: solid 1px #ffcc66; background-color: #FFFFEC;">
								<p class="stitle">
									为何需要注册：
								</p>
								<p class="hei12">
									账户管理 - 我们承诺您的所有信息将是保密的。同时我们也希望您所填的账户信息尽可能真实，以便有问题时能及时和您联系。
									<br />
									<br />
									数据提交 - 如果您有自己的数据希望在本网站共享，我们将非常欢迎，比昂强烈希望您注册成为我们的用户，以便我们能够及时跟您联系。
									<br />
									<br />
									个人中心 -
									您可以在未登录的状态下使用我们为您提供的blast等在线分析工具，我们提供了很多比对库。但是，如果您想记录自己的比对任务并随时查询，那请注册成为我们的用户，同时我们还提供可管理的个人参考文献库。
								</p>
							</td>
						</tr>
					</table>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="50" class="stitle">
								<table width="960" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="50" bgcolor="f7f7f7" class="stitle"
											style="padding-left: 20px; border-bottom: solid 1px #CCCCCC">
											新用户注册
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="50" align="left">
								<table width="960" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="50" bgcolor="#F7FEFF" class="hei12">
											如果您希望向我们提交数据，请填写真实信息，以便我们与您联系。（
											<span class="red12">* 为必填项。</span>）
										</td>
									</tr>
								</table>
							</td>
						</tr>

						<tr></tr>
					</table>
					<c:if test="${checked==0}">
						<form action="fmptuser.action?m=frontSave" name="zhuce1"
							id="zhuce1" method="post">
							<input id="attchar02" type="hidden" value="0" name="attchar02" />
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="50" class="hei14">
										<label>
											&nbsp;&nbsp;&nbsp;
											<input name="personOne" type="radio" id="one"
												value="radiobutton"
												<c:if test="${checked ==0}">checked</c:if> />
											个人用户注册 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="radio" name="compay" id="two"
												value="radiobutton"
												<c:if test="${checked !=0}">checked</c:if> />
											机构用户注册
										</label>
									</td>
								</tr>
								<tr>
									<td height="50" class="hei14">
										&nbsp;&nbsp;&nbsp;用&nbsp;&nbsp;户&nbsp;&nbsp;名：
										<input type="text" class="TxtUserNameCssClass" id="loginCode"
											maxlength="20" name="loginCode" />
										&nbsp;
										<span class="red12">*</span><span class="red_italic"
											id="loginCodeTip"></span>
									</td>
								</tr>
								<tr>
									<td height="50" class="hei14">
										&nbsp;&nbsp;&nbsp;密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：
										<input type="password" class="TxtUserNameCssClass" id="pwd"
											maxlength="12" name="password" />
										&nbsp;
										<span class="red12">*</span><span class="red_italic"
											id="pwdTip">（6-12位，字母和数字组成。）</span>
									</td>
								</tr>
								<tr>
									<td height="50" class="hei14">
										&nbsp;&nbsp;&nbsp;确认密码：
										<input type="password" class="TxtUserNameCssClass" id="pwd2"
											maxlength="12" name="passWord1" />&nbsp;&nbsp;&nbsp;&nbsp;
											<span class="red_italic" id="pwd2Tip"></span>
									</td>
								</tr>

								<tr>
									<td height="50" class="hei14">
										&nbsp;
									</td>
								</tr>
								<tr>
									<td height="50" class="hei14">
										<table width="960" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td height="50" bgcolor="f7f7f7" class="stitle"
													style="padding-left: 20px; border-bottom: solid 1px #CCCCCC">
													<p>
														填写详细信息
													</p>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td height="70" class="hei14">
										<p>
											&nbsp;&nbsp;&nbsp;真实姓名：
											<input type="text" class="TxtUserNameCssClass" id="realName"
												maxlength="12" name="name" />&nbsp;&nbsp;&nbsp;<span class="red_italic" id="realNameTip"></span>
									</td>
								</tr>
								<tr>
									<td height="50" class="hei14">
										&nbsp;&nbsp; 联系电话：
										<input type="text" class="TxtUserNameCssClass" id="attchar15"
											maxlength="20" name="attchar15" />
										<span class="red12">*</span><span class="red_italic" id="attchar15Tip"></span>
									</td>
								</tr>
								<tr>
									<td height="50" class="hei14">
										&nbsp;&nbsp; 电子邮箱：
										<input type="text" class="TxtUserNameCssClass" id="attchar16"
											maxlength="30" name="attchar16" />
										<span class="red12">*</span><span class="red_italic" id="attchar16Tip"></span>
									</td>
								</tr>
								<tr>
									<td height="50" class="hei14">
										<p>
											&nbsp;&nbsp;&nbsp;单位名称：
											<input type="text" class="TxtUserNameCssClass" id="attchar11"
												maxlength="50" name="attchar11" />&nbsp;&nbsp;&nbsp;<span class="red_italic" id="attchar11Tip"></span>
									</td>
								</tr>
								<tr>
									<td height="60" class="hei14">
										&nbsp;&nbsp;&nbsp;所在地区：
										<label></label>

										<select onchange="ff()" id="attchar10" name="attchar10">
											<option selected="selected">
												请选择所在地区
											</option>
											<c:forEach items="${listPro}" var="pro">
												<option value="${pro.provincialID}" style="width: 252px;">
													${pro.provincialName}
												</option>
											</c:forEach>
										</select>
										省
										<select id="attchar03" name="attchar03">
											<option selected="selected">
												请选择
											</option>
										</select>
									</td>
								</tr>
								<tr>
									<td height="80" class="hei14">
										&nbsp;&nbsp;&nbsp;详细地址：
										<label>
											<textarea type="textarea" name="attchar13" id="attchar13"
												cols="50" rows="5" style="border: solid 1px #CCCCCC"></textarea>
										</label>
									</td>
								</tr>
								<tr>
									<td height="50" class="hei14">
										&nbsp;
									</td>
								</tr>
								<tr>
									<td height="40">
										<label>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="image"
												src="<%=path%>/resources/images/sys_zhuce/zhuce-menu.jpg"
												name="Submit1" id="submitButton" value="提交"
												onclick="return tanchu();" />
										</label>
									</td>
								</tr>
								<tr>
									<td height="40">
										&nbsp;
									</td>
								</tr>
							</table>
						</form>
					</c:if>
					<c:if test="${checked==1}">
						<form action="fmptuser.action?m=frontSave" name="zhuce1"
							id="zhuce1" method="post">
							<input id="attchar02" type="hidden" value="1" name="attchar02" />
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="50" class="hei14">
										<label>
											&nbsp;&nbsp;&nbsp;
											<input name="personOne" type="radio" id="one"
												value="radiobutton"
												<c:if test="${checked !=1}">checked</c:if> />
											个人用户注册 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="radio" name="compay" id="two"
												value="radiobutton"
												<c:if test="${checked ==1}">checked</c:if> />
											机构用户注册
										</label>
									</td>
								</tr>
								<tr>
									<td height="50" class="hei14">
										&nbsp;&nbsp;&nbsp;用&nbsp;&nbsp;户&nbsp;&nbsp;名：
										<input type="text" class="TxtUserNameCssClass" id="loginCode"
											maxlength="20" name="loginCode" />
										&nbsp;
										<span class="red12">*</span><span class="red_italic"
											id="loginCodeTip"></span>
									</td>
								</tr>
								<tr>
									<td height="50" class="hei14">
										&nbsp;&nbsp;&nbsp;密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：
										<input type="password" class="TxtUserNameCssClass" id="pwd"
											maxlength="12" name="password" />
										&nbsp;
										<span class="red12">*</span><span class="red_italic"
											id="pwdTip">（6-12位，字母和数字组成。）</span>
									</td>
								</tr>
								<tr>
									<td height="50" class="hei14">
										&nbsp;&nbsp;&nbsp;确认密码：
										<input type="password" class="TxtUserNameCssClass" id="pwd2"
											maxlength="12" name="passWord1" />
										&nbsp;&nbsp;&nbsp;&nbsp;
										<span class="red_italic" id="pwd2Tip">*</span>
									</td>
								</tr>
								<tr>
									<td height="50" class="hei14">
										&nbsp;&nbsp;&nbsp;单位名称：
										<input class="TxtUserNameCssClass" id="attchar11"
											maxlength="50" name="attchar11" />
										&nbsp;&nbsp;&nbsp;&nbsp;
										<span class="red_italic" id="attchar11Tip">*</span>
									</td>
								</tr>

								<tr>
									<td height="50" class="hei14">
										&nbsp;&nbsp;&nbsp;所在地区：
										<label></label>
										<select onchange="ff()" name="attchar10" id="attchar10">
											<option selected="selected">
												请选择所在地区
											</option>
											<c:forEach items="${listPro}" var="pro" varStatus="i"
												begin="0">
												<option value="${pro.provincialID}" style="width: 252px;">
													${pro.provincialName}
												</option>
											</c:forEach>
										</select>
										省
										<select id="attchar03" name="attchar03">
											<option>
												请选择
											</option>
										</select>
										<span class="red12">*</span>
									</td>
								</tr>
								<tr>
									<td height="50" class="hei14">
										&nbsp;&nbsp; 联系电话：
										<input class="TxtUserNameCssClass" id="attchar15"
											maxlength="60" name="attchar15" />
										<span class="red12">*</span><span class="red_italic" id="attchar15Tip">*</span>
									</td>
								</tr>
								<tr>
									<td height="50" class="hei14">
										&nbsp;&nbsp; 电子邮箱：
										<input type="text" class="TxtUserNameCssClass" id="attchar16"
											maxlength="30" name="attchar16" />
										<span class="red12">*</span><span class="red_italic" id="attchar16Tip"></span>
									</td>
								</tr>
								<tr>
									<td height="50" class="hei14">
										&nbsp;&nbsp;&nbsp;详细地址：
										<label>
											<textarea name="attchar13" id="attchar13" cols="50" rows="5"
												style="border: solid 1px #CCCCCC"></textarea>
										</label>
									</td>
								</tr>
								<tr>
									<td height="50" class="hei14">
										&nbsp;
									</td>
								</tr>
								<tr>
									<td height="40">
										<label>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="image"
												src="<%=path%>/resources/images/sys_zhuce/zhuce-menu.jpg"
												name="Submit1" id="submitButton" value="提交"
												onclick="return tanchu();" />
										</label>
									</td>
								</tr>
								<tr>
									<td height="40">
										&nbsp;
									</td>
								</tr>
							</table>
						</form>
					</c:if>
					</td>
					</tr>
					</table>
					<table align="center" width="100%">
						<tr>
							<td height="120">
								<iframe frameborder=0 scrolling="no" name="topFrame"
									width="100%" height="100%" src="./comm/fmpt_bottom.jsp"></iframe>
							</td>
						</tr>
					</table>
	</body>
</html>
