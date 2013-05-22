<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/resources/common/res_all.inc"%>
<%@ page import="com.nbw.common.*"%>
<%@ page import="com.nbw.common.SysParameter"%>

<%
	String portalPath = SysParameter.getParameter("portalPath");
	String skinLabel = (String) application.getAttribute("skinLabel");
%>
<%
	String xtpath = SysParameter.getParameter("xtpath");
	String objectId = (String)request.getAttribute("objectId");
%>
<html xmlns="http://www.w3.org/1999/xhtml" >

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="${cssFile}" rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/resources/component/formValidator/style/validator.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/jquery.js"></script>
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

		<script type="text/javascript">
			
			
		

		</script>
	</head>
	<body>
		<form action="" method="post" id="frm1">
			<table width="300" height="240" border="0" align="center" cellpadding="0"
				cellspacing="0" background="${skinPath}images/hygl/login_02.gif">
				<tr>
					<td width="327" height="7">
						<img src="${skinPath}images/hygl/login_01.gif" />
					</td>
				</tr>
				<tr>
					<td height="30">
						&nbsp;
						<img src="${skinPath}images/hygl/login_08.gif" width="170"
							height="60" />
					</td>
				</tr>
				<tr>
					<td class="menu_p">
						<input type="hidden" value="${url}" name="url">
						<table width="97%" border="0" cellspacing="5" cellpadding="0">
							<tr>
								<td width="30%" height="35" align="right" class="menu_p">
									用户名：
								</td>
								<td width="70%" align="left">
									<label>
										<input type="text" name="username" id="loginCode"
											class="menu_p_reg" />
									</label>
								</td>
							</tr>
							<tr>
								<td height="35" align="right" class="menu_p">
									密 码：
								</td>
								<td align="left" class="div1">
									<input type="password" name="password" id="pwd"
										class="menu_p_reg" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class="menu_p">
						<img src="${skinPath}images/hygl/login_03.gif" width="327" 
							height="7" />
					</td>
				</tr>
				<tr>
					<td height="40" class="menu_p" align="center">
						<img name="Input4" src="${skinPath}images/hygl/login_06.gif"
							onclick="subLogin1();" style="cursor: pointer" />
					</td>
				</tr>
				<tr>
					<td height="11" class="menu_p">
						<img src="${skinPath}images/hygl/login_04.gif" width="327"
							height="11" />
					</td>
				</tr>
			</table>
		</form>
	</body>
	<script type="text/javascript">
	function subLogin1(){
				 $.ajax({  
					type: "post",
					url: "login.action?m=login",
					success: function(data, textStatus){
					    var dataStr = data.split(",");
						if("success"==dataStr[0]){
						  a_flushEipSession();
						}else{
						    alert('用户名或密码有误，登录失败！');
						}
					},
					data :'username='+frm1.username.value+'&password='+frm1.password.value,
					complete: function(XMLHttpRequest, textStatus)
					{
						
					},
					error: function(){
						alert("系统出现异常，请稍后再尝试!");
					}
			   });
			}
			
			function a_flushEipSession(){
				$.ajax({  
					type: "post",
					url: "<%=portalPath%>/ajaxlogin.do?username="+frm1.username.value,
					success: function(data, textStatus){
						//top.location.href = "<%=xtpath %>/BzptWeb/appmanager/eip/main?_nfpb=true&_pageLabel=P5800125901322719086481&url=/<%=(String) request.getAttribute("url")%>&width=938&height=1000";
					top.location.href = "<%=xtpath %>/BzptWeb/appmanager/eip/main?bzptForwardFlag=bzfw_1&objectId=<%=objectId %>&_nfpb=true&_nfxr=false&_pageLabel=P5600699591322477666493#wlp_P5600699591322477666493";
	               //document.all.frm1.submit();
					},
					//data :'username='+frm1.username.value+'&password='+frm1.password.value,
					error: function(){
						alert("系统出现异常，请稍后再尝试!");
					}
			   }); 
			}
	</script>
</html>