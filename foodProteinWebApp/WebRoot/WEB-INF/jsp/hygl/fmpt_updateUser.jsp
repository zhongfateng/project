<%@ page contentType="text/html; charset=utf-8" language="java"
	import="com.nbw.sys.domain.SysUsersDetail" errorPage=""%>
<%@ include file="/resources/common/res_all.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="${csscssFile}" rel="stylesheet" type="text/css" />
		<link
			href="<%=request.getContextPath()%>/resources/component/formValidator/style/validator.css"
			type="text/css" rel="stylesheet" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/uploadify/scripts/jquery-1.3.2.min.js"></script>
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/formValidator/formValidator.js"></script>
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/formValidator/formValidatorRegex.js"></script>
		<script language="javascript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/jquery.form.js"></script>
		<script type="text/javascript">
$(document).ready(function() {
	    $.formValidator.initConfig({onerror:function(msg){ ymPrompt.errorInfo({title:"提示",message:"校验没有通过，具体错误请看错误提示!"});}});
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
        
           $("#attchar11").formValidator({
               onshow: "请输入公司名称",
               onfocus: "公司名称不能为空"
           }).inputValidator({
               min: 1,
               onerror: "公司名称不能为空,请确认"
           }).inputValidator({
	                    max:30,
	                    onerror: "公司名称不能超过30个字符"
	                });
               $("#attchar13").formValidator({
                 onshow: "请输入详细地址",
                 onfocus: "详细地址不能为空"
             }).inputValidator({
                 min: 1,
                 onerror: "详细地址不能为空,请确认"
             }).inputValidator({
                 max:50,
                 onerror: "详细地址不能超过50个字符"
             });
            
              $("#attchar15").formValidator({
                 onshow: "请输入联系电话",
                 onfocus: "例如：0577-88888888",
                 oncorrect: "输入正确"
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
      $("#sbmintButton").bind("click",function(){
			if($.formValidator.pageIsValid('1')){
		      
			      var options = {
					type: 'POST',
					async: false,
					dataType: 'content-type',
					success: function(data){
						if(data=="<%=Constants.DEFAULT_AJAX_FAILURE%>"){
						       ymPrompt.errorInfo({title:"提示",message:"资料修改错误！"});
						}else{
							 ymPrompt.succeedInfo({title:"提示",message:"资料修改成功！"});
						}
					}
				}
			 //提交form
			 $('#theFrom').ajaxSubmit(options);  
		    }
     });    
     
          
});
    
</script>
		<title>个人资料修改</title>

	</head>
	<%
		SysUsersDetail sysUsersDetail = (SysUsersDetail) request
				.getAttribute("userDetail");
	%>
	<body>

		<table width="734" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="35"
					background="<%=request.getContextPath()%>/resources/images/myCenter/images/leftmenu-bg.jpg"
					class="hei16B">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;个人信息管理
				</td>
			</tr>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			height="560">
			<tr>
				<td align="center" valign="top"
					style="border: solid; border-width: 0 1px 1px 1px; border-color: #d0ced1; padding: 15px;">
					<form action="fmptuser.action?m=frontUpdateUserInfo" method="post"
						id="theFrom">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td align="left" valign="bottom" colspan="2"
									style="border-bottom: solid 1px #66CCFF">
									<p class="hei14">
										<strong>资料修改</strong>&nbsp;&nbsp;
										<span class="hei14" style="border-bottom: solid 1px #cfe2f3"></span>
									</p>
								</td>
							</tr>
							<tr>
								<td height="50" align="left" class="hei14" width="50%">
									&nbsp;&nbsp;&nbsp;&nbsp;真实姓名：
									<input type="text" name="realName" id="realName" size='26'
										value="<%=request.getAttribute("realName")%>"
										style="border: solid 1px #CCCCCC" />
									<span class="red" height="22">&nbsp;&nbsp;*</span>
								</td>
								<td align="left" class="menu_p" bgcolor="#FFFFFF">
									<div class="red_italic" id="realNameTip"></div>
								</td>
							</tr>
							<tr>
								<td height="50" align="left" class="hei14" width="50%">
									&nbsp;&nbsp;&nbsp;&nbsp;联系电话：
									<input type='text' id='attchar15' name='attchar15' size='26'
										value='<%=sysUsersDetail.getChar15() == null ? ""
					: sysUsersDetail.getChar15()%>' />
									<span class="red" height="22">&nbsp;&nbsp;*</span>
								</td>
								<td align="left" class="menu_p" bgcolor="#FFFFFF">
									<div class="red_italic" id="attchar15Tip"></div>
								</td>
							</tr>
							<tr>
								<td height="50" align="left" class="hei14" width="50%">
									&nbsp;&nbsp;&nbsp;&nbsp;电子邮箱：
									<input type='text' id='attchar16' name='attchar16' size='26'
										value='<%=sysUsersDetail.getChar16() == null ? ""
					: sysUsersDetail.getChar16()%>' />
									<span class="red" height="22">&nbsp;&nbsp;*</span>
								</td>
								<td align="left" class="menu_p" bgcolor="#FFFFFF">
									<div class="red_italic" id="attchar16Tip"></div>
								</td>
							</tr>

							<tr>
								<td height="50" align="left" class="hei14" width="50%">
									&nbsp;&nbsp;&nbsp;&nbsp;公司名称：
									<input type='text' id='attchar11' name='attchar11' size='26'
										value='<%=sysUsersDetail.getChar11() == null ? ""
					: sysUsersDetail.getChar11()%>' />
									<span class="red" height="22">&nbsp;&nbsp;*</span>
								</td>
								<td align="left" class="menu_p" bgcolor="#FFFFFF">
									<div class="red_italic" id="attchar11Tip"></div>
								</td>
							</tr>
							<tr>
								<td height="50" align="left" class="hei14" width="50%">
									&nbsp;&nbsp;&nbsp;&nbsp;详细地址：
									<input type='text' id='attchar13' name='attchar13' size='26'
										value='<%=sysUsersDetail.getChar13() == null ? ""
					: sysUsersDetail.getChar13()%>' />
									<span class="red" height="22">&nbsp;&nbsp;*</span>
								</td>
								<td align="left" class="menu_p" bgcolor="#FFFFFF">
									<div class="red_italic" id="attchar13Tip"></div>
								</td>
							</tr>
							<tr>
								<td height="101" align="center" class="hei14"
									style="border-bottom: solid 1px #66CCFF" colspan="2">
									<img id="sbmintButton" style="cursor: pointer"
										src="${skinPath}images/tijiao.jpg" />
								</td>
							</tr>
							<tr>
								<td height="50" align="center" class="hei14">
									&nbsp;
								</td>
							</tr>
						</table>
					</form>

				</td>
			</tr>
		</table>

	</body>
</html>