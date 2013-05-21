<%@ page language="java" pageEncoding="utf-8" isELIgnored="false"
	import="java.lang.*"%>
<%@ taglib uri="/WEB-INF/tld/extremecomponents.tld" prefix="ec" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>中国食品微生物数据共享平台</title>
<link href="<%=request.getContextPath() %>/resources/images/techDy/css/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.6.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/component/kindeditor/kindeditor.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/component/kindeditor/kindeditor-min.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/component/kindeditor/plugins/code/prettify.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/component/kindeditor/lang/zh_CN.js" charset="utf-8"></script>
<!-- tr背景鼠标经过效果-->
<script language=javascript>
function changebg(obj,cl){
var bgcolor;
if(cl=="0"){bgcolor="#f1f9ff";}else{bgcolor="#fff";}
obj.style.backgroundColor=bgcolor;
}
</script>
<script type="text/javascript">
	$(function(){
		if($("#d_sum").html()==0){
			$("#dd_00").after("<table border='0' width='100%'><tr><td id='aa_00' align='center' style='color:red;'></td></tr></table>");
			$("#aa_00").html("无数据！");
		};
		
		if(<%=request.getAttribute("pageSize")%>==5){
			$("#select").html("<option value='<%=request.getAttribute("pageSize")%>'><%=request.getAttribute("pageSize")%></option><option value='10'>10</option><option value='15'>15</option><option value='20'>20</option>");
		}else if(<%=request.getAttribute("pageSize")%>==10){
			$("#select").html("<option value='<%=request.getAttribute("pageSize")%>'><%=request.getAttribute("pageSize")%></option><option value='5'>5</option><option value='15'>15</option><option value='20'>20</option>");
		}else if(<%=request.getAttribute("pageSize")%>==15){
			$("#select").html("<option value='<%=request.getAttribute("pageSize")%>'><%=request.getAttribute("pageSize")%></option><option value='5'>5</option><option value='10'>10</option><option value='20'>20</option>");
		}else if(<%=request.getAttribute("pageSize")%>==20){
			$("#select").html("<option value='<%=request.getAttribute("pageSize")%>'><%=request.getAttribute("pageSize")%></option><option value='5'>5</option><option value='10'>10</option><option value='15'>15</option>");
		};
		
		
		$("#select").change(function(){
			var pageSize = $(this).val();
			window.location.href="techDy.action?m=frontTechDyList&page=1&pageSize="+pageSize;
		});
		
		
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
    <td height="34" valign="bottom" background="<%=request.getContextPath() %>/resources/images/techDy/images/top-bg.jpg"><table width="965" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-bottom:5px;">
      <tr>
        <td width="34" height="25" align="center" valign="middle" class="hei12"><img src="<%=request.getContextPath() %>/resources/images/techDy/images/gif-0661.gif" width="13" height="11" /></td>
        <td width="931" align="left" valign="bottom" class="hei12">首页-&gt;用户须知列表</td>
      </tr>
    </table></td>
  </tr>
</table>
<table width="965" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="39" height="35"><img src="<%=request.getContextPath() %>/resources/images/techDy/images/leftmenu-bg01.jpg" width="39" height="35" /></td>
    <td width="913" background="<%=request.getContextPath() %>/resources/images/techDy/images/leftmenu-bg02.jpg" class="hei16B">用户须知</td>
    <td width="13"><img src="<%=request.getContextPath() %>/resources/images/techDy/images/leftmenu-bg03.jpg" width="13" height="35" /></td>
  </tr>
</table>
<table width="965" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td valign="top"  style="border:solid; border-width:0px 1px 1px 1px; border-color:#cbcbcb; padding:5px;">
    <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="hei14" style="margin:8px 0;">
          <tr onmousemove="javascript:changebg(this,0)" style="backgroud-color:#f4f4f4" onmouseout="javascript:changebg(this,1)">
            <td width="40" align="center" valign="top" class="eng14" style="padding:8px 2px; border-bottom:dashed #66CCFF 1px;">&nbsp;</td>
            <td align="center" valign="top" class="eng14" style="padding:8px 2px; border-bottom:dashed #66CCFF 1px;">用户须知</td>
            
          </tr>
          
          <c:forEach items="${dataList}" var="tt" begin="0" varStatus="i">
          <tr onmousemove="javascript:changebg(this,0)" style="backgroud-color:#f4f4f4" onmouseout="javascript:changebg(this,1)">
            <td align="center" valign="top" class="eng14" style="padding:8px 2px; border-bottom:dashed #66CCFF 1px;">${i.count} &nbsp;.</td>
            <td align="center" valign="top" class="eng14" style="padding:8px 2px; border-bottom:dashed #66CCFF 1px;"><a href="usage.action?m=frontGetUsageDetail&id=${tt.id}">${tt.title}</a></td>
          </tr>
          </c:forEach>
          
      </table>
      
      
	    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                  <td>&nbsp;</td>
                  <td height="10" align="right">&nbsp;</td>
                </tr>
         
                <tr>
                  <td>&nbsp;</td>
                  <td height="10" align="right">&nbsp;</td>
                </tr>
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
