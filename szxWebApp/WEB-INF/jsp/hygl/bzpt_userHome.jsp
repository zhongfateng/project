<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>会员管理-用户中心</title>
<link href="${cssFile}" rel="stylesheet" type="text/css" />
</head>

<body>
<jsp:include page="../../../forward.action?url=/WEB-INF/jsp/util/bzpt_top.jsp" flush="true" />
<table width="914" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><img src="${skinPath}images/hygl/main_top.jpg" width="914" height="11" /></td>
  </tr>
  <tr>
    <td background="${skinPath}images/hygl/main_bg.jpg"><table width="98%" border="0" align="center" cellpadding="0" cellspacing="10">
      <tr>
        <td width="220" height="171" valign="top" class="pc_boxl">
        <iframe src="bzptuser.action?m=frontUserLeft" scrolling="no" width="100%" height="500" id="leftFrame" frameborder="0"></iframe></td>
        <td align="center" valign="top" class="pc_boxl">
		<iframe src="bzptuser.action?m=frontUpdateUser" scrolling="no" width="100%" height="500" id="infoFrame" frameborder="0"></iframe>
        </td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><img src="${skinPath}images/hygl/main_bottom.jpg" width="914" height="14" /></td>
  </tr>
</table> 
<jsp:include page="../../../forward.action?url=/WEB-INF/jsp/util/bzpt_bottom.jsp" flush="true" />
</body>
</html>