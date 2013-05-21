<%@ page language="java" pageEncoding="utf-8" isELIgnored="false"
	import="java.lang.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>中国食品微生物数据共享平台</title>
<link href="<%=request.getContextPath() %>/resources/images/techDy/css/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/component/kindeditor/kindeditor.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/component/kindeditor/kindeditor-min.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/component/kindeditor/plugins/code/prettify.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/component/kindeditor/lang/zh_CN.js" charset="utf-8"></script>
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
    <td height="34" valign="bottom" background="<%=request.getContextPath() %>/resources/images/techDy/images/top-bg.jpg"><table width="965" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-bottom:5px;">
      <tr>
        <td width="34" height="25" align="center" valign="middle" class="hei12"><img src="<%=request.getContextPath() %>/resources/images/techDy/images/gif-0661.gif" width="13" height="11" /></td>
        <td width="931" align="left" valign="bottom" class="hei12">首页-&gt;科技动态</td>
      </tr>
    </table></td>
  </tr>
</table>
<table width="965" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="39" height="35"><img src="<%=request.getContextPath() %>/resources/images/techDy/images/leftmenu-bg01.jpg" width="39" height="35" /></td>
    <td width="913" background="<%=request.getContextPath() %>/resources/images/techDy/images/leftmenu-bg02.jpg" class="hei16B">科技动态</td>
    <td width="13"><img src="<%=request.getContextPath() %>/resources/images/techDy/images/leftmenu-bg03.jpg" width="13" height="35" /></td>
  </tr>
</table>
<table width="965" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td valign="top" bgcolor="#F7FBFE"  style="border:solid; border-width:0px 1px 1px 1px; border-color:#cbcbcb; padding:25px 5px;"><table width="95%" border="0" align="center" cellpadding="0" cellspacing="0"  style="margin-bottom:10px;">
        <tr>
          <td height="45" align="center" valign="bottom" bgcolor="#E3F1FC"  style="border-bottom:solid 1px #66CCFF"><span class="hei14"><strong>${techDy.title}</strong></span></td>
        </tr>
        <tr>
          <td height="45" align="center" valign="middle" class="hei12"  style="border-bottom:solid 1px #eeeeee">时间：${techDy.ttime} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;来源：${techDy.tsource}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;作者：${techDy.tauthor}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 浏览${techDy.tsum}次&nbsp;</td>
        </tr>
        <tr>
          <td align="left" class="hei14" style="padding:25px;"><p>  &nbsp;&nbsp;&nbsp;&nbsp;
			${techDy.tcontent}
           </p>
          </td>
        </tr>
        <tr></tr>
      </table>
    </td>
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
