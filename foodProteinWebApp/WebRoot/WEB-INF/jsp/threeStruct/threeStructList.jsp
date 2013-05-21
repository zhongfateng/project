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
	
		if($("#d_sum").html()==""){
			$("#aa_00").html("无数据！");
		};
		
		
		
		$("#d1_1").click(function(){
			var geneInfo = $("#d1_2").val();
			if(geneInfo==""||geneInfo=="输入种名"){
				ymPrompt.alert('请输入种名！');
				//alert("");
			}else{
				window.location.href="struct.action?m=frontThreeStructSearch&speciesName="+geneInfo;
			}
			 
		});
});

	function MyFocus(){
			if($("#d1_2").val()=="输入种名"){
				$("#d1_2").val("");
			}
		};
		
		function MyBlur(){
			if($("#d1_2").val()==""){
				$("#d1_2").val("输入种名");
			}
		};
</script>

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
    <td width="511" align="right" ><input name="queren22222" class="TxtsearchClass" id="d1_2" onblur="MyBlur();" onfocus="MyFocus();" value="输入种名" style="padding-bottom:6px; height:24px;border:solid 1px #CCCCCC;"/>
      &nbsp;</td>
    <td width="343" align="center"><a href="javascript:;" id="d1_1"><img src="<%=request.getContextPath()%>/resources/images/threeStruct/images/search-png.png" width="102" height="35" border="0" /></a>&nbsp;&nbsp;<a href="indexMain.action?m=frontIndexMain"><img src="<%=request.getContextPath()%>/resources/images/threeStruct/images/cancle-png.png" width="102" height="35" border="0"/></a></td>
  </tr>
</table>
<table width="965" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="39" height="35"><img src="<%=request.getContextPath()%>/resources/images/threeStruct/images/leftmenu-bg01.jpg" width="39" height="35" /></td>
    <td width="913" background="<%=request.getContextPath()%>/resources/images/threeStruct/images/leftmenu-bg02.jpg" class="hei16B">检索结果</td>
    <td width="13"><img src="<%=request.getContextPath()%>/resources/images/threeStruct/images/leftmenu-bg03.jpg" width="13" height="35" /></td>
  </tr>
</table>
<table width="965" height="400" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td valign="top"  style="border:solid; border-width:0px 1px 1px 1px; border-color:#cbcbcb; padding:5px;">
    <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin:8px 0;">
       	  <tr onmousemove="javascript:changebg(this,0)" style="backgroud-color:#f4f4f4" onmouseout="javascript:changebg(this,1)">
            <td width="34%" height="40" align="left" valign="top" class="eng14" style="padding:5px 2px; border-bottom:dashed #66CCFF 1px;padding-left:20px;">
            <strong>${speciesName}</strong>&nbsp;&nbsp;&nbsp;
            </td>
            <td width="9%" align="center" valign="top" class="link14" style="padding:5px 2px; border-bottom:dashed #66CCFF 1px;">
            <a id="d_sum" href="struct.action?m=frontThreeStructDetailSum&speciesName=${speciesName}">${sum}</a>
            </td>
            <td id="aa_00" width="57%" align="left" valign="top" class="red12" style="padding:5px 2px; border-bottom:dashed #66CCFF 1px;">&nbsp;(点击菌株后的数字获取详细信)</td>
          </tr>
       <c:forEach items="${dataList}" var="gg" begin="0" varStatus="i">
          <tr onmousemove="javascript:changebg(this,0)" style="backgroud-color:#f4f4f4" onmouseout="javascript:changebg(this,1)">
            <td width="50%" height="40" align="left" valign="top" class="eng14" style="padding:5px 2px; border-bottom:dashed #66CCFF 1px;padding-left:20px;">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${gg[0]}
            </td>
            <td width="9%" align="center" valign="top" class="link14" style="padding:5px 2px; border-bottom:dashed #66CCFF 1px;">
            <a href="struct.action?m=frontThreeStructDetail&speciesName=${gg[0]}">${gg[1]}</a>
            </td>
            <td width="41%" align="left" valign="top" class="red12" style="padding:5px 2px; border-bottom:dashed #66CCFF 1px;">&nbsp;</td>
          </tr>
       </c:forEach>
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
