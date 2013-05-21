<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.net.URLDecoder" %>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>蛋白质指纹图谱数据库</title>

<script type="text/javascript" src="<%=path%>/resources/js/tupu/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/tupu/service.min.js"></script>
<script src="<%=path%>/resources/component/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css" href="<%=path%>/resources/component/uploadify/uploadify.css">
<link href="${csscssFile}" rel="stylesheet" type="text/css" />
<script src="<%=path%>/resources/js/tupu/highstock.min.js"></script>
<script src="<%=path%>/resources/js/tupu/modules/exporting.js"></script>
<script type="text/javascript">
function sub(){
var key = $("#searckKey").val();
$.ajax({
        type: "POST",
		url : "fmpttp.action?m=frontSearch&key="+encodeURI(encodeURI(key)),
		async: false,
		success : function(responseText) {
			var fmptList = responseText;
			$("#searchjg").html(fmptList);
		},
	});
} 
window.onload = function keyload(){
var key = $("#searckKey").val();
if(key!=""&&key!=null){
$.ajax({
        type: "POST",
		url : "fmpttp.action?m=frontSearch&key="+encodeURI(encodeURI(key)),
		async: false,
		success : function(responseText) {
			var fmptList = responseText;
			$("#searchjg").html(fmptList);
		},
	});
 }
} 
function showBzChart(id){
  getJsonData(id);
} 
function clearName(){
   $("#searckKey").attr("value",'');//清空内容 
}
function doClick(e){       
				if(e.keyCode == 13){  
					event.keyCode = 0;
					return false;
				}else{
				    return false;
				}
}  
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
</style>
</head>
<body>
<table width="100%" height="170px;" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td height="100%" align="center"><iframe frameborder=0 scrolling="no" name="topFrame"
						src="./comm/fmpt_top.jsp" width="100%" height="100%" ></iframe></td>
  </tr>
</table>
	<table width="965" border="0" align="center" cellpadding="0" cellspacing="0" >
  <tr>
    <td height="34" valign="bottom" background="${skinPath}images/top-bg.jpg"><table width="965" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-bottom:5px;">
      <tr>
        <td width="34" height="25" align="center" valign="middle" class="hei12"><img src="${skinPath}images/gif-0661.gif" width="13" height="11" /></td>
        <td width="931" align="left" valign="bottom" class="hei12">首页-&gt;特色数据库-&gt;蛋白质指纹图谱数据库</td>
      </tr>
    </table></td>
  </tr>
</table>
<table width="965" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="39" height="35"><img src="${skinPath}images/leftmenu-bg01.jpg" width="39" height="35" /></td>
    <td background="${skinPath}images/leftmenu-bg02.jpg" class="hei16B">蛋白质指纹图谱数据库</td>
    <td width="13"><img src="${skinPath}images/leftmenu-bg03.jpg" width="13" height="35" /></td>
  </tr>
</table>
<table width="965" border="0" align="center" cellpadding="0" cellspacing="0" style="border:solid; border-color:#d0ced1; border-width:0px 1px 1px 1px;  margin-bottom:5px;">
  <tr>
    <td valign="top">
  <form id="myform">
    <table width="100%" border="0" cellspacing="0" cellpadding="0"  style="background-image:url(${skinPath}images/lefttext-bg.jpg); background-position:bottom; background-repeat:repeat-x; margin-bottom:5px;">
      <tr>
        <td>&nbsp;</td>
        <td colspan="4" align="right" class="hei14">&nbsp;</td>
        </tr>
      <tr>
        <td width="1%" height="50">&nbsp;</td>
        <td width="24%" align="right" class="hei14"><input name="key" class="TxtUserNameCssClass" id="searckKey" value="${key}" style="color:#999999" onKeyPress="doClick(event)"/></td>
        <td colspan="2" align="center" class="hei14">
        <td width="65%" valign="bottom" class="hei14">
        <img src="${skinPath}images/search-png.png" width="102" height="35" border="0"  id="searchButton1" onclick="sub(this.form)"/>
        &nbsp;<img src="${skinPath}images/cancle-png.png" width="102" height="35" onclick="clearName()"/></td>
      </tr>
      <tr>
        <td height="29">&nbsp;</td>
        <td colspan="4" valign="top" class="red12" style="padding-left:75px;">请输入物种名或Tax ID，然后选择要查找的结果项</td>
        </tr>
      <tr>
        <td height="50">&nbsp;</td>
        <td colspan="4" class="hei14" style="padding-left:45px;"><label>
          <div id="queue"></div>
          <input id="file_upload" name="file_upload" type="file" multiple="true" />
          <span class="red12" >请上传本地 *.xy 格式的质谱数据</span>
          </label></td>
        </tr>
      <tr>
        <td height="20">&nbsp;</td>
        <td colspan="4" class="hei14"><label></label></td>
        </tr>
    </table>
    
    </form>
      <table width="964" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">

              <tr>
                <td width="60%">&nbsp;</td>
                <td width="40%" height="30">
                  <img src="${skinPath}images/tupu/record_(copy)_16x16.gif" width="16" height="16" />&nbsp;<a href="#" class="hei14" id="a_parser_all">多图叠加</a>&nbsp;&nbsp;&nbsp;
                 <img src="${skinPath}images/tupu/record_16x16.gif" width="16" height="16" />&nbsp;<a href="#" class="hei14" id="a_parser_each">单图分析</a>&nbsp;&nbsp;&nbsp;
                 <img src="${skinPath}images/tupu/date_delete.gif" width="16" height="16" />&nbsp;<a href="#" class="hei14" id="date_delete">清空数据</a>
                </td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td height="30">&nbsp;</td>
              </tr>
            </table>
            <table width="100%" height="64" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td align="center">
                <!--container 显示表格DIV  -->
	            <div id="container" style="height: 500px; min-width: 400px;"></div>
                </td>
              </tr>
            </table>
           </td>
          <td width="240" valign="top"><table width="95%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#FFCC00">
            <tr>
              <td valign="top" bgcolor="#FFFFEE"  class="hei14" style="padding:15px;"><p class="stitle" >查询结果</p>
                  <div id="searchjg" class="hei12"/>
                  </td>
            </tr>
          </table></td>
        </tr>
      </table>
    </td>
  </tr>
</table>
	<table align="center" width="100%">
<tr>
  <td height="120" ><iframe frameborder=0 scrolling="no" name="topFrame" width="100%" height="100%"
						src="./comm/fmpt_bottom.jsp" ></iframe></td>
</tr>
</table>
	
</body>
</html>
