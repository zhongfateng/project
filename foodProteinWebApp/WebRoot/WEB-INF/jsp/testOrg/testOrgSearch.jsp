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
<!-- tr背景鼠标经过效果-->
<script language=javascript>
function changebg(obj,cl){
var bgcolor;
if(cl=="0"){bgcolor="#f1f9ff";}else{bgcolor="#fff";}
obj.style.backgroundColor=bgcolor;
}
</script>

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.6.2.js"></script>
<script type="text/javascript" src ="<%=request.getContextPath()%>/resources/component/ymPrompt/ymPrompt.js"></script>
<script type="text/javascript">
	$(function(){
	
		$("#d1_1").click(function(){
			var geneInfo = $("#d1_2").val();
			var addr = $("#d1_3").val();
			if((geneInfo==""||geneInfo=="请输入机构名称")&&addr==""){
				ymPrompt.alert('请输入机构名称或选择机构地址！');
			}else{
				window.location.href="testOrg.action?m=frontTestOrgSearch&oname="+encodeURI(encodeURI(geneInfo))+"&oaddr="+encodeURI(encodeURI(addr));
				//$("#d1_4").submit();
			}
			 
		});
});

	function MyFocus(){
			if($("#d1_2").val()=="请输入机构名称"){
				$("#d1_2").val("");
			}
		};
		
		function MyBlur(){
			if($("#d1_2").val()==""){
			$("#d1_2").val("请输入机构名称");
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
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-bottom:5px;">
  <tr>
    <td height="34" valign="bottom" background="<%=request.getContextPath()%>/resources/images/testOrg/images/top-bg.jpg">
    <table width="965" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-bottom:5px;">
      <tr>
        <td width="34" height="25" align="center" valign="middle" class="hei12"><img src="<%=request.getContextPath()%>/resources/images/testOrg/images/gif-0661.gif" width="13" height="11" /></td>
        <td width="931" align="left" valign="bottom" class="hei12">首页-&gt;检测机构</td>
      </tr>
    </table>
    </td>
  </tr>
</table>
<table width="965" height="80" border="0" align="center" cellpadding="0" cellspacing="0" background="<%=request.getContextPath()%>/resources/images/testOrg/images/search-bg.jpg"  style="margin-bottom:8px;">
  <tr>
    <td width="511" align="right" >
	<input name="oname" class="TxtsearchClass" id="d1_2" onblur="MyBlur();" onfocus="MyFocus();" style="padding-bottom:6px; height:24px;border:solid 1px #CCCCCC;" value="请输入机构名称" />
      &nbsp;</td>
    <td width="252" align="left" >
    <select name="oaddr" id="d1_3" style="height:30px; width:152px; border:solid 1px #CCCCCC; text-align:center">
      <option value="" style="width:102px;">机构所在地</option>
      <c:forEach items="${addrList}" var="dd" varStatus="i" begin="0">
      	<option value="${dd}" style="width:102px;">${dd}</option>
      </c:forEach>
    </select>
    </td>
    
    <td width="202" align="center">
    <a  href="javascript:;" id="d1_1">
    <img src="<%=request.getContextPath()%>/resources/images/testOrg/images/search-png.png" width="102" height="35" border="0" />
    </a>&nbsp;&nbsp;</td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
<table width="965" align="center" border="0" cellpadding="0" cellspacing="1" bgcolor="#FEF4B1">
        <tr>
          <td bgcolor="#FFFFEE"  class="hei14" style="padding:15px;"><p class="stitle" >使用说明</p>
              <p class="hei12"> &nbsp;&nbsp;&nbsp;&nbsp;账户管理 - 我们承诺您的所有信息僵尸保密的。同时我们也希望您所填的账户信息尽可能真实，以便有问题时能及时和您联系。
                数据提交 - 如果您有自己的数据希望在本网站共享，我们将非常欢迎，比昂强烈希望您注册成为我们的用户，以便我们能够及时跟您联系。<br />
                          <br />
                &nbsp;&nbsp;&nbsp;&nbsp;个人中心 - 您可以在未登录的状态下使用我们为您提供的blast等在线分析工具，我们提供了很多比对库。但是，如果您想记录自己的比对任务并随时查询，那请注册成为我们的用户，同时我们还提供可管理的个人参考文献库。</p></td>
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