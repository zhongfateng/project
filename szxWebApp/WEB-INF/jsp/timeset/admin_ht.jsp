<%@page contentType="text/html; charset= utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%
	String path = request.getContextPath();
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>管理页面</title>
<link href="<%=request.getContextPath() %>/resources/timeset/images/skin.css" rel="stylesheet" type="text/css"></link>
<script src="<%=request.getContextPath() %>/resources/timeset/js/prototype.lite.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/resources/timeset/js/moo.fx.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/resources/timeset/js/moo.fx.pack.js" type="text/javascript"></script>
<style>
body {
	font:12px Arial, Helvetica, sans-serif;
	color: #000;
	background-color: #EEF2FB;
	margin: 0px;
}
#container {
	width: 182px;
}
H1 {
	font-size: 12px;
	margin: 0px;
	width: 182px;
	cursor: pointer;
	height: 30px;
	line-height: 20px;	
}
H1 a {
	display: block;
	width: 182px;
	color: #000;
	height: 30px;
	text-decoration: none;
	moz-outline-style: none;
	background-image: url(<%=request.getContextPath() %>/resources/timeset/images/menu_bgs1.gif);
	background-repeat: no-repeat;
	line-height: 30px;
	text-align: left;
	margin: 0px;
	padding: 0px 0px 0px 32px;
}

.content{
	width: 182px;
	height: 26px;
	
}
.MM ul {
	list-style-type: none;
	margin: 0px;
	padding: 0px;
	display: block;
}
.MM li {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	list-style-type: none;
	display: block;
	text-decoration: none;
	height: 26px;
	width: 182px;
	padding-left: 0px;
}
.MM {
	width: 156px;
	margin: 0px;
	padding: 0px;
	left: 0px;
	top: 0px;
	right: 0px;
	bottom: 0px;
	clip: rect(0px,0px,0px,0px);
}
.MM a:link {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	background-image: url(<%=request.getContextPath() %>/resources/timeset/images/menu_bg1.gif);
	background-repeat: no-repeat;
	height: 26px;
	width: 156px;
	display: block;
	text-align: left;
	margin: 0px;
	padding: 0 0 0 26px;
	overflow: hidden;
	text-decoration: none;
}
.MM a:visited {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	background-image: url(<%=request.getContextPath() %>/resources/timeset/images/menu_bg1.gif);
	background-repeat: no-repeat;
	display: block;
	text-align: left;
	margin: 0px;
	padding: 0 0 0 26px;
	height: 26px;
	width: 156px;
	text-decoration: none;
}
.MM a:active {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	background-image: url(<%=request.getContextPath() %>/resources/timeset/images/menu_bg1.gif);
	background-repeat: no-repeat;
	height: 26px;
	width: 156px;
	display: block;
	text-align: left;
	margin: 0px;
	padding: 0 0 0 26px;
	overflow: hidden;
	text-decoration: none;
}
.MM a:hover {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	font-weight: bold;
	color: #006600;
	background-image: url(<%=request.getContextPath() %>/resources/timeset/images/menu_bg2.gif);
	background-repeat: no-repeat;
	text-align: left;
	display: block;
	margin: 0px;
	padding: 0 0 0 26px;
	height: 26px;
	width: 156px;
	text-decoration: none;
}
</style>


<script type="text/javascript">
	function doOnClick(id){
	var ifr=document.getElementById("ifrm");
	if(id=='1'){
		ifr.src="<%=path%>/timeset.action?m=toXzxk";
	}
	if(id=='2'){
		ifr.src="<%=path%>/timeset.action?m=toQzrk";
	}
	if(id=='3'){
		ifr.src="<%=path%>/timeset.action?m=toJdjc";
	}
	if(id=='4'){
		ifr.src="<%=path%>/timeset.action?m=toJdcc";
	}
	if(id=='5'){
		ifr.src="<%=path%>/timeset.action?m=toZlwf";
	}
	if(id=='6'){
		ifr.src="<%=path%>/sysroles.action?m=toIndex";
	}
	if(id=='7'){
		ifr.src="<%=path%>/sysfieldcfg.action?m=loadUserConfig";
	}
	if(id=='9'){
		ifr.src="<%=path%>/sysusers.action?m=toIndex";
	}
	if(id=='10'){
		ifr.src="<%=path%>/sysorganizations.action?m=toIndex";
	}
	if(id=='11'){
		ifr.src="<%=path%>/bzpthyjb.action?m=loadData";
	}
	if(id=='12'){
		ifr.src="<%=path%>/bzptzh.action?m=loadUserJbtz";
	}
	if(id=='8'){
		ifr.src="<%=path%>/organization.action?m=toOrganization";
	}

	if(id=='13'){
		ifr.src="<%=path%>/import.action?m=ToImportList";
	}

	if(id=='14'){
		ifr.src="<%=path%>/import.action?m=ToUpload";
	}
	

	if(id=='15'){
		ifr.src="<%=path%>/import.action?m=toPageXYPJBSearch";
	}
	if(id=='16'){
		ifr.src="<%=path%>/import.action?m=toCountShow";
	}

	if(id=='170'){
		ifr.src="<%=path%>/timeset.action?m=toPjb";
	}

	if(id=='17'){
		ifr.src="<%=path%>/import.action?m=toJBXXList";
	}
	if(id=='18'){
		ifr.src="<%=path%>/import.action?m=toXZXKXXList";
	}
	if(id=='19'){
		ifr.src="<%=path%>/import.action?m=toRZRKXXList";
	}
	if(id=='20'){
		ifr.src="<%=path%>/import.action?m=toJDJCBHGXXList";
	}
	if(id=='21'){
		ifr.src="<%=path%>/import.action?m=toCPCCBHGXXList";
	}
	if(id=='22'){
		ifr.src="<%=path%>/import.action?m=toWFXXList";
	}
	if(id=='23'){
		ifr.src="<%=path%>/import.action?m=toJLXXList";
	}
	
	
	}
	
	</script>

</head>

<body>
<table width="100%" height="64" border="0" cellpadding="0" cellspacing="0" background="<%=request.getContextPath() %>/resources/timeset/images/man-info_02.png">
  <tr>
    <td height="64"><img src="<%=request.getContextPath() %>/resources/timeset/images/man-info_01.png" width="534" height="78"/></td>
    <td width="380" height="78" valign="top" background="<%=request.getContextPath() %>/resources/timeset/images/man-info_04.png">&nbsp;</td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0" background="<%=request.getContextPath() %>/resources/timeset/images/man-info_06.png" class="bai14">
  <tr>
    <td width="185" height="42"><img src="<%=request.getContextPath() %>/resources/timeset/images/man-info_05.png" width="185" height="42"/></td>
    <td align="center" valign="bottom"><table width="100%" height="30" border="0" cellpadding="0" cellspacing="0" bgcolor="#EDF3F7">
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td><span class="login_txt">2012年11月28日&nbsp;&nbsp;星期四<a href="#" target="_self" class="login_txt" onClick="logout();"> </a>&nbsp;您好：张三</span></td>
        <td align="center"><a href="#" target="_self" onClick="logout();"><img src="<%=request.getContextPath() %>/resources/timeset/images/out.gif" alt="安全退出" width="46" height="20" border="0"/></a></td>
      </tr>
    </table></td>
  </tr>
</table>
<table width="100%" height="280" border="0" cellpadding="0" cellspacing="0" bgcolor="#EEF2FB">
  <tr>
    <td width="182" valign="top"><div id="container">
      <h1 class="type"><a href="javascript:void(0)">质量信用信息管理</a></h1>
      <div class="content">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><img src="<%=request.getContextPath() %>/resources/timeset/images/menu_topline.gif" width="182" height="5" /></td>
          </tr>
        </table>
        <ul class="MM">
          <li><a href="javascript:doOnClick('14');" >数据导入</a></li>
          <li><a href="javascript:doOnClick('13');" >数据管理</a></li>
          <li><a href="javascript:doOnClick('17');" >基本信息管理</a></li>
          <li><a href="javascript:doOnClick('18');">行政许可信息管理</a></li>
           <li><a href="javascript:doOnClick('19');">强制性认证认可信息管理</a></li>
            <li><a href="javascript:doOnClick('20');">监督检查不合格信息管理</a></li>
             <li><a href="javascript:doOnClick('21');">产品抽查不合格管理</a></li>
              <li><a href="javascript:doOnClick('22');">企业产品违法信息管理</a></li>
               <li><a href="javascript:doOnClick('23');">企业产品奖励信息管理</a></li>
               
        </ul>
      </div>
      <h1 class="type"><a href="javascript:void(0)">质量信用评价规则管理</a></h1>
      <div class="content">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><img src="<%=request.getContextPath() %>/resources/timeset/images/menu_topline.gif" width="182" height="5" /></td>
          </tr>
        </table>
        <ul class="MM">
          <li><a href="javascript:doOnClick('1');">行政许可评价规则管理</a></li>
          <li><a href="javascript:doOnClick('2')">强制认证评价规则管理</a></li>
          <li><a href="javascript:doOnClick('3')">监督检查不合格规则管理</a></li>
          <li><a href="javascript:doOnClick('4')">监督抽查不合格规则管理</a></li>
          <li><a href="javascript:doOnClick('5')">质量违法评价规则管理</a></li>
        </ul>
      </div>
      <h1 class="type"><a href="javascript:void(0)">质量信用等級管理</a></h1>
      <div class="content">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><img src="<%=request.getContextPath() %>/resources/timeset/images/menu_topline.gif" width="182" height="5" /></td>
          </tr>
        </table>
        <ul class="MM">
		  <li><a href="javascript:doOnClick('170');">信用等级计算</a></li>
          <li><a href="javascript:doOnClick('15');" >信用等级查询</a></li>
          <li><a href="javascript:doOnClick('16');" >信用等级统计</a></li>
        </ul>
      </div>
      <h1 class="type"><a href="javascript:void(0)">用戶管理</a></h1>
      <div class="content">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><img src="<%=request.getContextPath() %>/resources/timeset/images/menu_topline.gif" width="182" height="5" /></td>
          </tr>
        </table>
        <ul class="MM">
          <li><a href="javascript:doOnClick('6');" >角色管理</a></li>
          <li><a href="javascript:doOnClick('7');" >用户附属信息配置</a></li>
          <li><a href="javascript:doOnClick('9');" >用户管理</a></li>
          <li><a href="javascript:doOnClick('10');" >组织机构管理</a></li>
          <li><a href="javascript:doOnClick('11');" >会员级别</a></li>
          <li><a href="javascript:doOnClick('12');" >调整用户会员级别</a></li>
        </ul>
      </div>
    </div>
	<h1 class="type"><a href="javascript:void(0)">外部系统接口管理</a></h1>
      <div class="content">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><img src="<%=request.getContextPath() %>/resources/timeset/images/menu_topline.gif" width="182" height="5" /></td>
          </tr>
        </table>
        <ul class="MM">
          <li><a href="javascript:doOnClick('8');" >外部系统接口管理</a></li>
        </ul>
      </div>
<script type="text/javascript">
		var contents = document.getElementsByClassName('content');
		var toggles = document.getElementsByClassName('type');
	
		var myAccordion = new fx.Accordion(
			toggles, contents, {opacity: true, duration: 400}
		);
		myAccordion.showThisHideOpen(contents[0]);
	</script>
    </td>
    <td>
    	<iframe src="<%=path%>/timeset.action?m=toXzxk" width="100%"  height="600" scrolling="auto"  id="ifrm" name="ifrm" ></iframe>
    </td>
  </tr>
</table>
</body>
</html>