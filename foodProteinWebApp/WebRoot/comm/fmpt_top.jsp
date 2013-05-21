<%@ page language="java"  pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.nbw.common.*"%>
<%@ page import="com.nbw.sys.domain.SysUsers"%>
<%
    SysUsers sysUsers = (SysUsers)session.getAttribute(Constants.SESSION_USER_INFO);
    String username = null;
    if(sysUsers!=null){
     username = sysUsers.getLoginCode();
    }
 %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中国食品微生物数据共享平台</title>
		<link href="${csscssFile}" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" id='skin' type="text/css" href="<%=request.getContextPath()%>/resources/component/ymPrompt/skin/qq/ymPrompt.css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.6.2.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/component/ymPrompt/ymPrompt.js"></script>
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

body {font-family:\5B8B\4F53,Arial Narrow,arial,serif;background:#ffffff;font-size:12px;}
body,div,dl,dt,dd,ul,ol,li,pre,form,fieldset,input,textarea,blockquote,emBED{padding:0; margin:0;}
li{list-style-type:none;}
a {color:#2b2b2b;text-decoration:none;}
a:visited {color:#2b2b2b;text-decoration:none;}
a:hover {color:#ff0000;text-decoration:underline;}
a:active {color:#2b2b2b;}
/*主导航菜单*/
#menu ul{padding:0;border:0;list-style:none;line-height:150%;margin-top: 0;margin-right: 0;margin-bottom: 0;margin-left: 0;width:100%;text-align: center;}
#menu_out{width:100%;padding-left:0px;	margin-left:auto;margin-right:auto;	background:url('../images/menu_left.gif') no-repeat left top;}
#menu_in{background:url('../images/menu_right.gif') no-repeat right top;padding-right:5px;width:100%;}
#menu{background:url('../images/menu_bg.gif') repeat-x;	height:73px; width:100%;position:relative; left:0px; top:0px;text-align: center;}
.menu_line{	background:url('../images/menu_line.gif') no-repeat center top;	width:8px;}
.menu_line2{background:url('../images/menu_line2.gif') no-repeat center top;width:15px;}
#nav{padding-left:20px;}
#nav li{float:left;height:35px;}
#nav li a{float:left;display:block;padding-left:9px;height:37px;background:url('../images/menu_on_left.gif') no-repeat left top;cursor:pointer;text-decoration:none;}
#nav li a span{float:left;padding:13px 20px 10px 10px;line-height:14px;background:url('../images/menu_on_right.gif') no-repeat right top;font-size:14px;font-weight:bold;color:#FFFFFF;text-decoration:none;}
#nav li .nav_on{   /*鼠标经过时变换背景，方便JS获取样式*/background-position:left 100%;}
#nav li .nav_on span{  /*鼠标经过时变换背景，方便JS获取样式*/background-position:right 100%;color:#ffcc66;text-decoration:none;	padding:14px 24px 9px 20px;}
/*子栏目*/
#menu_con{text-align:left;padding-left:20px;clear:both;}
#menu_con li{float:left;height:22px;margin-top:8px;}
#menu_con li a{display:block;float:left;background:url('../images/menu_on_left2.gif') no-repeat left top;cursor:pointer;padding-left:3px;}
#menu_con li a span{float:left;	padding:6px 10px 4px 10px;	line-height:12px;background:url('../images/menu_on_right2.gif') no-repeat right top;}
#menu_con li a:hover{text-decoration:none;background:url('../images/menu_on_left2.gif') no-repeat left bottom;}
#menu_con li a:hover span{	background:url('../images/menu_on_right2.gif') no-repeat right bottom;}
/*子栏目位置 */
#qh_con0 { position:absolute;top:35px;left: 20px; }
#qh_con1 { position:absolute;top:35px;left: 20%; }
#qh_con2 { position:absolute;top:35px;left: 30%; }
#qh_con3 { position:absolute;top:35px;left: 272px; }
#qh_con4 { position:absolute;top:35px;left: 380px; }
#qh_con5 { position:absolute;top:35px;left: 480px;}
#qh_con6 { position:absolute;top:35px;left:50%;}
#qh_con7 { position:absolute;top:35px;left: auto;right:20px;}
</style>

<script type="text/javascript">

function qiehuan(num){
		for(var id = 0;id<=7;id++)
		{
			if(id==num)
			{
				document.getElementById("qh_con"+id).style.display="block";
				document.getElementById("mynav"+id).className="nav_on";
			}
			else
			{
				document.getElementById("qh_con"+id).style.display="none";
				document.getElementById("mynav"+id).className="";
			}
		}
}

	function exit()
	{
	top.location.href="<%=request.getContextPath()%>/login.action?m=ExitLogin";
	top.location.reload();
	}
	
window.onload = function login(){
           var userId = '<%=(String)session.getAttribute(Constants.SESSION_USER_ID)%>';
 		   if(userId == null || userId ==""||userId == "null"){
		      document.getElementById("show").innerHTML = "<a href='../fmptuser.action?m=frontIndex&checked=0' class='hei12' target='_top'>注册</a>|<a href='../login.action?m=toLoginIndexQt' class='hei12' target='_top'>登录</a>";
 		   }else{
 		    document.getElementById("show").innerHTML = "欢迎<%=username%>用户[<a href='javascript:exit()'>退出</a>]| <a href='<%=request.getContextPath()%>/fmptuser.action?m=frontUserLeft' target='_top'> 我的CFMDSP</a>";
 		   }
 }
</script>

	</head>
	<%
		String foodProteinWebApp = SysParameter.getParameter("foodProteinWebApp");
	%>
	<body>
		<table width="965" border="0" align="center" cellpadding="0"
			cellspacing="0" background="${skinPath}images/logo-bg.jpg">
			<tr>
				<td width="36%" height="103">&nbsp; 
				</td>
				<td width="64%">
					<table width="100%" height="59" border="0" cellpadding="0"
						cellspacing="0">
						<tr>
							<td colspan="3" align="right" class="hei12">
							 <div id = "show"></div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div id=menu_out>
	  <div id=menu_in  style="text-align:center">
      <div id=menu style="background-image: url('../images/menu_bg-1.jpg');">
	<table width="965" border="0" align="center" cellpadding="0"
			cellspacing="0">
	<tr>
		<td align="center">
		<ul id=nav  style="text-align:center">
<li><a class=nav_off id=mynav0 onmouseover="javascript:qiehuan(0)" href="../indexMain.action?m=frontIndexMain" target="_blank"><span>首 页</span></a></li>
<li class="menu_line"></li>
<li><a href="javascript:;" onmouseover="javascript:qiehuan(1)" id="mynav1" class="nav_off"><span>基础数据库</span></a></li>
<li class="menu_line"></li>
<li><a href="javascript:;" onmouseover="javascript:qiehuan(2)" id="mynav2" class="nav_off"><span>特色数据库</span></a></li>
<li class="menu_line"></li>
<li><a href="http://www.cfmd.com.cn/wwwblast/blast.html" onmouseover="javascript:qiehuan(3)" id="mynav3" class="nav_off" target="_blank"><span>在线分析</span></a></li>
<li class="menu_line"></li>
<li><a href="../testTech.action?m=frontTestTech" onmouseover="javascript:qiehuan(4)" id="mynav4" class="nav_off" target="_blank"><span>微生物快速检测方法</span></a></li>
<li class="menu_line"></li>
<li><a href="http://www.cfmd.com.cn/bbs" onmouseover="javascript:qiehuan(5)" id="mynav5" class="nav_off" target="_blank"><span>技术论坛</span></a></li>
<li class="menu_line"></li>
<li><a href="../usage.action?m=frontToList" onmouseover="javascript:qiehuan(6)" id="mynav6" class="nav_off" target="_blank"><span>使用帮助</span></a></li>
<li><a href="<%=foodProteinWebApp%>/comm/fmpt_aboutus.jsp" onmouseover="javascript:qiehuan(7)" id="mynav7" class="nav_off" target="_blank"><span>关于我们</span></a></li>
</ul>
<div id=menu_con>
<div id=qh_con0 style="DISPLAY: block">
</div>
<div id=qh_con1 style="DISPLAY: none">
<ul>
  <li><a href="../gene.action?m=frontGeneSearch" target="_blank"><span style="font-size: 12px;font-family:\5B8B\4F53,Arial Narrow,arial,serif;">基因数据库</span></a></li>
  <li class=menu_line2></li>
  <li><a href="../protein.action?m=frontProteinSearch" target="_blank"><span style="font-size: 12px;font-family:\5B8B\4F53,Arial Narrow,arial,serif;">蛋白质数据库</span></a></li>
  <li class=menu_line2></li>
  <li><a href="../rna.action?m=frontRnaSearch" target="_blank"><span style="font-size: 12px;font-family:\5B8B\4F53,Arial Narrow,arial,serif;">RNA数据库</span></a></li>
  <li><a href="../search.action?m=fronttosearch" target="_blank"><span style="font-size: 12px;font-family:\5B8B\4F53,Arial Narrow,arial,serif;">文献数据库</span></a></li>
  <li><a href="../struct.action?m=frontThreeStruct" target="_blank"><span style="font-size: 12px;font-family:\5B8B\4F53,Arial Narrow,arial,serif;">三维结构</span></a></li>
</ul>
</div> 
<div id=qh_con2 style="DISPLAY: none">
<ul>
  <li><a href="../fmptstrain.action?m=frontToIndex" target="_blank"><span style="font-size: 12px;font-family:\5B8B\4F53,Arial Narrow,arial,serif;">目录数据库</span></a></li>
  <li class=menu_line2></li>
  <li><a href="../fmpttp.action?m=frontToIndex" target="_blank"><span style="font-size: 12px;font-family:\5B8B\4F53,Arial Narrow,arial,serif;">图谱数据库</span></a></li>
  <li class=menu_line2></li>
</ul>
</div> 
<div id=qh_con3 style="DISPLAY: block">
</div>
<div id=qh_con4 style="DISPLAY: block">
</div>
<div id=qh_con5 style="DISPLAY: block">
</div>
<div id=qh_con6 style="DISPLAY: none">

</div>
<div id=qh_con7 style="DISPLAY: block">
</div>
</div>
			</td>
			</tr>
		</table>
</div>

</div>
</div> 
	</body>
</html>
