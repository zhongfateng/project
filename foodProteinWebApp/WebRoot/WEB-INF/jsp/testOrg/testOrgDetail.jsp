<%@ page language="java" pageEncoding="utf-8" isELIgnored="false"
	import="java.lang.*"%>
<%@taglib uri="/WEB-INF/tld/extremecomponents.tld" prefix="ec"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>中国食品微生物数据共享平台</title>
<link href="<%=request.getContextPath()%>/resources/images/testOrg/css/css.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" id='skin' type="text/css" href="<%=request.getContextPath()%>/resources/component/ymPrompt/skin/qq/ymPrompt.css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.6.2.js"></script>
<script type="text/javascript" src ="<%=request.getContextPath()%>/resources/component/ymPrompt/ymPrompt.js"></script>
<script type="text/javascript">
	$(function(){
		
	});

</script>

<style type="text/css">
<!--
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
-->
</style></head>

<body>
<table width="100%" height="170px;" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="100%" align="center"><iframe frameborder=0 scrolling="no" name="topFrame"
						src="./comm/fmpt_top.jsp" width="100%" height="100%" scrolling="no"></iframe></td>
  </tr>
</table>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-bottom:5px;">
  <tr>
    <td height="34" valign="bottom" background="<%=request.getContextPath()%>/resources/images/testOrg/images/top-bg.jpg"><table width="965" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-bottom:5px;">
      <tr>
        <td width="34" height="25" align="center" valign="middle" class="hei12"><img src="<%=request.getContextPath()%>/resources/images/testOrg/images/gif-0661.gif" width="13" height="11" /></td>
        <td width="931" align="left" valign="bottom" class="hei12">首页-&gt;检测机构</td>
      </tr>
    </table></td>
  </tr>
</table>
<table width="965" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="39" height="35"><img src="<%=request.getContextPath()%>/resources/images/testOrg/images/leftmenu-bg01.jpg" width="39" height="35" /></td>
    <td width="913" background="<%=request.getContextPath()%>/resources/images/testOrg/images/leftmenu-bg02.jpg" class="hei16B">基本信息</td>
    <td width="13"><img src="<%=request.getContextPath()%>/resources/images/testOrg/images/leftmenu-bg03.jpg" width="13" height="35" /></td>
  </tr>
</table>
<table width="965" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td valign="top" bgcolor="#F7FBFE"  style="border:solid; border-width:0px 1px 1px 1px; border-color:#cbcbcb; padding:25px 5px;"><table width="95%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CEE8FF" style="margin-bottom:15px;">
        <tr>
          <td height="45" colspan="4" align="left" valign="bottom" bgcolor="#E3F1FC" class="hei14"><strong>&nbsp;基本信息</strong>&nbsp;&nbsp;</td>
        </tr>
        <tr>
          <td width="16%" height="30" align="center" bgcolor="#FFFFFF" class="hei14">机构名称</td>
          <td colspan="3" bgcolor="#FFFFFF" class="hei14">${testOrg.oname}</td>
        </tr>
        <tr>
          <td height="30" align="center" bgcolor="#FFFFFF" class="hei14">机构地址</td>
          <td colspan="3" bgcolor="#FFFFFF" class="hei14">${testOrg.oaddr}</td>
        </tr>
        <tr>
          <td height="30" align="center" bgcolor="#FFFFFF" class="hei14">联系人</td>
          <td width="30%" bgcolor="#FFFFFF" class="hei14">${testOrg.opeople}</td>
          <td width="16%" align="center" bgcolor="#FFFFFF" class="hei14">联系电话</td>
          <td width="38%" bgcolor="#FFFFFF" class="hei14">${testOrg.otel}</td>
        </tr>
      </table>
      <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0"  style="border:solid 1px #66CCFF; margin-bottom:10px;">
        <tr>
          <td height="45" align="left" valign="bottom" bgcolor="#E3F1FC"  style="border-bottom:solid 1px #66CCFF">
                  <strong>&nbsp;&nbsp;检测项目</strong>&nbsp;&nbsp;<span class="hei14" style="border-bottom:solid 1px #cfe2f3"></span></td>
        </tr>
        <tr>
          <td align="left" bgcolor="#FFFFFF" class="hei14" style="padding:15px;"><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${testOrg.oproject}</p>
          </td>
        </tr>
        <tr></tr>
      </table>
      <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0"  style="border:solid 1px #66CCFF">
        <tr>
          <td height="45" align="left" valign="bottom" bgcolor="#E3F1FC"  style="border-bottom:solid 1px #66CCFF"><strong>&nbsp;&nbsp;检测仪器</strong>&nbsp;&nbsp;<span class="hei14" style="border-bottom:solid 1px #cfe2f3"></span></td>
        </tr>
        <tr>
          <td align="left" bgcolor="#FFFFFF" class="hei14" style="padding:15px;"><p> &nbsp;&nbsp;&nbsp;&nbsp;${testOrg.oinstrument}</p></td>
        </tr>
        <tr></tr>
      </table></td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
<table align="center" width="100%">
<tr>
  <td height="120" ><iframe frameborder=0 scrolling="no" name="topFrame" width="100%" height="100%"
						src="./comm/fmpt_bottom.jsp" scrolling="no"></iframe></td>
</tr>
</table>
</body>
</html>
