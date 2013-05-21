<%@ page language="java" pageEncoding="utf-8" isELIgnored="false"
	import="java.lang.*"%>
<%@ taglib uri="/WEB-INF/tld/extremecomponents.tld" prefix="ec" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>中国食品微生物数据共享平台</title>
<link href="<%=request.getContextPath()%>/resources/images/threeStruct/css/css.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" id='skin' type="text/css" href="<%=request.getContextPath()%>/resources/component/ymPrompt/skin/qq/ymPrompt.css" />
<!-- tr背景鼠标经过效果-->
<script language=javascript>
function changebg(obj,cl){
var bgcolor;
if(cl=="0"){bgcolor="#f1f9ff";}else{bgcolor="#fff";}
obj.style.backgroundColor=bgcolor;
}
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.6.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/component/ymPrompt/ymPrompt.js"></script>
<script type="text/javascript">
	$(function(){
		
		$("#d1_1").click(function(){
			var geneInfo = $("#d1_2").val();
			var reg = /^[a-zA-Z\s]+$/;
			if(geneInfo==""||geneInfo=="请输入物种拉丁名"|| !reg.test(geneInfo)){
				ymPrompt.alert('请输入物种拉丁名！');
				//alert("");
			}else{
				window.location.href="struct.action?m=frontThreeStructSearch&speciesName="+geneInfo;
			}
			 
		});
});

	function MyFocus(){
			if($("#d1_2").val()=="请输入物种拉丁名"){
				$("#d1_2").val("");
			}
		};
		
		function MyBlur(){
			if($("#d1_2").val()==""){
				$("#d1_2").val("请输入物种拉丁名");
			}
		};
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
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="34" background="<%=request.getContextPath()%>/resources/images/threeStruct/images/top-bg.jpg">&nbsp;</td>
  </tr>
</table>
<table width="965" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="25" valign="top" class="hei12">首页-&gt;特色数据库-&gt;三维结构数据库</td>
  </tr>
</table>
<table width="965" height="80" border="0" align="center" cellpadding="0" cellspacing="0" background="<%=request.getContextPath()%>/resources/images/threeStruct/images/search-bg.jpg"  style="margin-bottom:8px;">
  <tr>
    <td width="511" align="right" >
    <input name="queren22222" class="TxtsearchClass" id="d1_2" onblur="MyBlur();" onfocus="MyFocus();" value="请输入物种拉丁名" style="padding-bottom:6px; height:24px;border:solid 1px #CCCCCC;"/>
      &nbsp;</td>
    <td width="343" align="center"><a href="javascript:;" id="d1_1"><img src="<%=request.getContextPath()%>/resources/images/threeStruct/images/search-png.png" width="102" height="35" border="0" /></a>&nbsp;&nbsp;<a href="indexMain.action?m=frontIndexMain"><img src="<%=request.getContextPath()%>/resources/images/threeStruct/images/cancle-png.png" width="102" height="35" border="0"/></a></td>
  </tr>
</table>
<table width="965" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#FEF4B1">
  <tr>
    <td bgcolor="#FFFFEE"  class="hei14" style="padding:15px;"><p class="stitle" >数据库简介</p>
        <p class="hei12"> &nbsp;&nbsp;&nbsp;&nbsp;本平台三维结构数据库主要元数据信息包括结构的名称、分类、MMDB和PDB的序列号，通过物种进行搜索，得到的检索结果中序列号与MMDB和PDB进行链接，了解相关序列的功能、相互作用、活性位点等。</p></td>
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
