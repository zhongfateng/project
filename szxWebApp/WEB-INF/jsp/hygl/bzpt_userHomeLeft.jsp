<%@ page contentType="text/html; charset=utf-8" language="java"
	errorPage=""%>
<%@ taglib uri="/WEB-INF/tld/extremecomponents.tld" prefix="ec"%>
<%@ page import="com.nbw.common.SysParameter"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="${cssFile}" rel="stylesheet" type="text/css" />
		<script language="javascript">
		<%
			String portalPath = SysParameter.getParameter("portalPath");
			String bzzyWebApp = SysParameter.getParameter("bzzyWebApp");
			String skinLabel=(String)application.getAttribute("skinLabel");
		%>
		    var fid=null;
			function on_ifr(num){
				var ifrm=window.parent.document.getElementById("infoFrame");
				var s='<%=skinLabel%>';
				if(s=="bzpt_ll"){
		          top.location.href = "<%=portalPath%>/appmanager/eip/home?bzptForwardFlag=bzzx_"+num+"&_nfpb=true&_pageLabel=P1400796841275384227156";	
	           }else{
		         top.location.href = "<%=portalPath%>/appmanager/eip/main?bzptForwardFlag=bzzx_"+num+"&_nfpb=true&_nfxr=false&_pageLabel=P5600899591322477860352";
	           }
			}
			function on_bg(t){
				if(fid!=t)
			    document.getElementById(t).background='${skinPath}images/hygl/ps_02.gif';
			}
			function on_null(t){
				if(fid!=t)
			    document.getElementById(t).background='';
			}
		</script>
		<style type="text/css">
		.white {
			font-size: 12px;
			color: #FFFFFF
		}
		
		.white_bold {
			font-size: 12px;
			font-weight: bold;
			color: #FFFFFF
		}
		
		.left a {
			color: #013065;
			text-align: center;
			font-size: 12px;
			display: block;
			padding: 5px 0px 7px 0px;
			text-decoration: none;
		}
		
		.left a:link,.white12 a:visited {
			color: #013065;
		}
		
		.left a:hover {
			color: #FD4A03;
			background: url(${skinPath}images/hygl/z12-12.jpg) repeat-x;
		}
		
		.12_blue {
			font-size: 12px;
			color: #014584
		}
		
		.12_black {
			font-size: 12px;
			color: #000000
		}
		
		.12_blue2 {
			font-size: 12px;
			color: #194D8B
		}
		
		.12b_blue {
			font-size: 12px;
			font-weight: bold;
			color: #194D8B
		}
		</style>
	</head>

	<body>
		<table width="168" border="0" cellspacing="0" cellpadding="0">
			<tr>
			  <td height="26" align="center" class="left" id="t11">
			    <table width="219" border="0" cellspacing="0" cellpadding="0">
			    <tr>
			      <td width="32"><img src="${skinPath}images/hygl/z9.jpg" width="32" height="29" /></td>
			      <td width="109" background="${skinPath}images/hygl/z8.jpg" class="white_bold">用户中心</td>
			      <td width="68" background="${skinPath}images/hygl/z10.jpg">&nbsp;</td>
			      <td width="10"><img src="${skinPath}images/hygl/z11.jpg" width="10" height="29" /></td>
		        </tr>
		      </table>
			</td>
		  </tr>  
			<tr>
				<td height="26" background="${skinPath}images/hygl/z12.jpg"
					align="center" class="left" id="t1">
					<a href="javascript:on_ifr('1')" class="left">个人资料</a>
				</td>
			</tr>
			<tr>
				<td height="26" background="${skinPath}images/hygl/z12.jpg"
					align="center" class="left" id="t2">
					<a href="javascript:on_ifr('2')" class="left">会员提醒</a>
				</td>
			</tr>
			<tr>
				<td height="26" background="${skinPath}images/hygl/z12.jpg" 
					align="center" class="left" id="t3">
					<a href="javascript:on_ifr('3')"  class="left">修改密码</a>
				</td>
			</tr>
			<tr>
				<td height="26" background="${skinPath}images/hygl/z12.jpg"
					align="center" class="left" id="t4">
					<a href="javascript:on_ifr('4')" class="left">购 物 车</a>
				</td>
			</tr>
			<tr>
				<td height="26" background="${skinPath}images/hygl/z12.jpg"
					align="center" class="left" id="t5">
					<a href="javascript:on_ifr('5')" class="left">订单中心</a>
				</td>
			</tr>
			<tr>
				<td height="26" background="${skinPath}images/hygl/z12.jpg"
					align="center" class="left" id="t6">
					<a href="javascript:on_ifr('6')" class="left">购买记录</a>
				</td>
			</tr>
			<tr>
				<td height="26" background="${skinPath}images/hygl/z12.jpg"
					align="center" class="left" id="t7">
					<a href="javascript:on_ifr('7')" class="left">我的收藏</a>
				</td>
			</tr>
			<tr>
				<td height="26" background="${skinPath}images/hygl/z12.jpg"
					align="center" class="left" id="t8">
					<a href="javascript:on_ifr('8')" class="left">浏览记录</a>
				</td>
			</tr>
			<tr>
				<td height="26" background="${skinPath}images/hygl/z12.jpg"
					align="center" class="left" id="t9">
					<a href="javascript:on_ifr('9')" class="left">查看账户</a>
				</td>
			</tr>
			<tr>
				<td height="26" background="${skinPath}images/hygl/z12.jpg"
					align="center" class="left" id="t11">
					<a href="javascript:on_ifr('11')" class="left">充值记录</a>
				</td>
			</tr>
			<tr>
				<td height="26" background="${skinPath}images/hygl/z12.jpg"
					align="center" class="left" id="t10">
					<a href="javascript:on_ifr('10')" class="left">充值服务</a>
				</td>
			</tr>
		<!--  批量购买和批量订单
			<tr>
				<td height="26" background="${skinPath}images/hygl/z12.jpg"
					align="center" class="left" id="t12">
					<a href="javascript:on_ifr('12')" class="left">批量购买</a>
				</td>
			</tr>
			<tr>
				<td height="26" background="${skinPath}images/hygl/z12.jpg"
					align="center" class="left" id="t13">
					<a href="javascript:on_ifr('13')" class="left">批量订单</a>
				</td>
			</tr>
		-->	
		</table>
	</body>
</html>