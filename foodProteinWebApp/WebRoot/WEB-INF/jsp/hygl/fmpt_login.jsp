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
%>
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

		
	</head>
	<body>
		<form action="" method="post" id="frm1">
			<table width="327" border="0" align="center" cellpadding="0"
				cellspacing="0" background="${skinPath}images/hygl/login_02.gif" height="240" align="center">
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
							height="7" style="cursor: pointer" />
					</td>
				</tr>
				<tr>
					<td height="40" class="menu_p" align="center" style="cursor:hand">
						<img name="Input4" src="${skinPath}images/hygl/login_06.gif"
							onclick="subLogin();" />
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
			function subLogin(){
				 $.ajax({  
					type: "post",
					url: "login.action?m=login",
					beforeSend: function(XMLHttpRequest){
						
					},
					success: function(data, textStatus){
					    var dataStr = data.split(",");
						if("success"==dataStr[0]){
							a_flushEipSession();
						}else{
						    alert('用户名或密码有误，登录失败！');
						}
					},
					data :'username='+frm1.username.value+'&password='+frm1.password.value,
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
						if(${t_type} =="1"){
							top.location.href="<%=xtpath %>/BzptWeb/appmanager/eip/main?_nfpb=true&_pageLabel=P5800125901322719086481&url=/bzptsfgwc.action?m=check'id=${id}'sid=${sid}'type=1&width=938&height=1000";
						}else{
							ymPrompt.win("<div align=\'center'\ style=\'margin-top: 15px;'\>该商品已成功收藏！<a  href=\'javascript:forwardPage();'\ onclick=\'javascript:forwardPage();'\ ><br/>查看收藏夹</a></div>",200,100,'提示');	
							top.location.reload();
						}
					},
					data :'username='+frm1.username.value+'&password='+frm1.password.value,
					error: function(){
						alert("系统出现异常，请稍后再尝试!");
					}
			   }); 
			}
function forwardPage(){
    var s='<%=skinLabel%>';
	if(s=="bzpt_ll"){
		top.location.href = "<%=portalPath%>/appmanager/eip/home?bzptForwardFlag=bzzx_7&_nfpb=true&_pageLabel=P1400796841275384227156#wlp_P1400796841275384227156";
	}else{
		top.location.href = "<%=portalPath%>/appmanager/eip/home1?bzptForwardFlag=bzzx_7&_nfpb=true&_nfxr=false&_pageLabel=P3202124591273910944281#wlp_P3202124591273910944281";
	}
	return false;
}
		</script>
	
</html>