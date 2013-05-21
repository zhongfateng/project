<%@ page language="java" pageEncoding="utf-8" isELIgnored="false"
	import="java.lang.*"%>
<%
	String path = request.getContextPath();
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>中国食品微生物数据共享平台</title>
<link href="<%=request.getContextPath() %>/resources/images/myCenter/css.css" rel="stylesheet" type="text/css" />

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
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.6.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.validate.js"></script>
<script type="text/javascript">
	function doOnClick(id){
	var ifr=document.getElementById("ifrm");
	if(id=='1'){
		ifr.src="<%=path%>/fmptuser.action?m=UpdateUser";
	}
	if(id=='2'){
		ifr.src="<%=path%>/fmptJf.action?m=frontJF";
	}
	if(id=='3'){
		ifr.src="<%=path%>/fmptJf.action?m=frontUploadAndDownload&fclick=upload";
	}
	if(id=='4'){
		ifr.src="<%=path%>/fmptJf.action?m=frontUploadAndDownload&fclick=download";
	}
	if(id=='5'){
		ifr.src="<%=path%>/fmptJf.action?m=frontModifyPwd";
	}
	}
		
	</script>
</head>

<body>
<table width="100%" height="170px;" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="100%" align="center"><iframe frameborder=0 scrolling="no" name="topFrame"
						src="./comm/fmpt_top.jsp" width="100%" height="100%" scrolling="no"></iframe></td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="34" background="<%=request.getContextPath() %>/resources/images/myCenter/images/top-bg.jpg">&nbsp;</td>
  </tr>
</table>
<table width="965" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="25" valign="top" class="hei12">首页-&gt;个人中心</td>
  </tr>
</table>
<table width="965" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="226" valign="top" bgcolor="#F1F9FF"  style="border:solid; border-width:0 1px 1px 1px; border-color:#d0ced1; padding-bottom:10px;">
    <table width="100%" cellspacing="0">
      <tr>
        <td height="35" align="center" background="<%=request.getContextPath() %>/resources/images/myCenter/images/right-bg-40.jpg" bgcolor="#4E85E2" ><span class="bai14">个人中心</span></td>
      </tr>
    </table>
      <table width="100%" height="433" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="433" valign="top"  bgcolor="f1f9ff"  >
          <div class="left-a">
            <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td height="40" align="center"  style="border-bottom:solid 1px #addeff"><a href="javascript:doOnClick('1')">个人资料</a></td>
            </tr>
            <tr>
              <td height="40" align="center"  style="border-bottom:solid 1px #addeff"><a href="javascript:doOnClick('2')">我的积分</a></td>
            </tr>
            <tr>
            
            </tr>
            <tr>
              <td height="40" align="center"  style="border-bottom:solid 1px #addeff"><a href="javascript:doOnClick('3')" class="hei14">上传记录</a></td>
            </tr>
            <tr>
              <td height="40" align="center"  style="border-bottom:solid 1px #addeff"><a href="javascript:doOnClick('4')"  class="hei14" >下载记录</a></td>
            </tr>
            <tr>
              <td height="40" align="center" background="<%=request.getContextPath() %>/resources/images/myCenter/images/yiqi-bg.jpg" style="border-bottom:solid 1px #addeff"><a href="javascript:doOnClick('5')"  class="hei14" >修改密码</a></td>
            </tr>
          </table>
         </div> 
          </td>
        </tr>
      </table>
      </td>
   <td width="734" valign="top" bgcolor="#F5F8FF" style="padding-left:5px">
    <iframe src="<%=path%>/fmptuser.action?m=UpdateUser" width="100%"  height="600" scrolling="no" frameborder="0" id="ifrm" name="ifrm" ></iframe>
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
