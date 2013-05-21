<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>中国食品微生物数据共享平台</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/images/usage/css/css.css">
	

  </head>
  
  <body>
<table width="100%" height="170px;" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="100%" align="center"><iframe frameborder=0 scrolling="no" name="topFrame"
						src="./comm/fmpt_top.jsp" width="100%" height="100%"></iframe></td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:5px;">
  <tr>
    <td height="255" align="center" valign="top" background="<%=request.getContextPath()%>/resources/images/usage/image/main(17).png" bgcolor="#FEECA2"><table width="965" height="430" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td height="257" valign="top"  style="background-image:url(<%=request.getContextPath()%>/resources/images/usage/image/user-zhi_03.gif); background-repeat:no-repeat; background-position:top"><table width="806" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td height="280">&nbsp;</td>
          </tr>
          <tr>
            <td><img src="<%=request.getContextPath()%>/resources/images/usage/image/user-zhi_06.gif" width="806" height="27" /></td>
          </tr>
          <tr>
            <td background="<%=request.getContextPath()%>/resources/images/usage/image/user-zhi_08.gif" style="padding:0 20px; text-align:left"><div class="hei12">
           <br>
            ${usage.body} 
             
            </div></td>
          </tr>
          <tr>
            <td><img src="<%=request.getContextPath()%>/resources/images/usage/image/user-zhi_10.gif" width="806" height="141" /></td>
          </tr>
        </table></td>
      </tr>
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
						src="./comm/fmpt_bottom.jsp" ></iframe></td>
</tr>
</table>
</body>
</html>
