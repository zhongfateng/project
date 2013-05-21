<%@page pageEncoding="gb2312" contentType="text/html; charset=gb2312" isELIgnored="false" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>中国食品微生物数据共享平台</title>
<link href="<%=request.getContextPath() %>/resources/images/hygl/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.6.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.validate.js"></script>
<script type="text/javascript">
	$(function(){
	
		$('#frm1').validate( {
		rules : {
			newPwd : {
				required : true,
				minlength : 6,
				maxlength : 16
			},
			newPwdq : {
				equalTo : "#newPwd"
			}
		},
		messages : {
			newPwd : {
				required : "<span style='color:red'>必填</span>",
				minlength : "<span style='color:red'>至少6位</span>",
				maxlength : "<span style='color:red'>最多16位</span>"
			},
			newPwdq : {
				equalTo : "<span style='color:red'>新密码不一致</span>"
			}
		}
	});
	});
</script>
</head>

<body>
<table width="100%" height="170px;" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="100%" align="center"><iframe frameborder=0 scrolling="no" name="topFrame"
						src="./comm/fmpt_top.jsp" width="100%" height="100%" scrolling="no"></iframe></td>
  </tr>
</table>
<table width="965" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="25" valign="top" class="hei12">首页-&gt;找回密码</td>
  </tr>
</table>
<table width="965" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="39" height="35"><img src="<%=request.getContextPath() %>/resources/images/hygl/images/leftmenu-bg01.jpg" width="39" height="35" /></td>
    <td background="<%=request.getContextPath() %>/resources/images/hygl/images/leftmenu-bg02.jpg" class="hei16B">找回密码</td>
    <td width="13"><img src="<%=request.getContextPath() %>/resources/images/hygl/images/leftmenu-bg03.jpg" width="13" height="35" /></td>
  </tr>
</table>
<table width="965" border="0" align="center" cellpadding="0" cellspacing="0" style="border:solid; border-color:#d0ced1; border-width:0px 1px 1px 1px;  margin-bottom:5px;">
  <tr>
    <td height="124" valign="top" style="padding:10px; 0"><table width="850" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="270" align="center" bgcolor="#BBBBBB" class="hei12">第一步：验证身份</td>
        <td width="20" height="25" align="center"><img src="<%=request.getContextPath() %>/resources/images/hygl/images/p10.jpg" width="20" height="25" /></td>
        <td width="270" align="center" bgcolor="#FFE6BD" class="red12">第二步：重置密码</td>
        <td width="20" height="25" align="center" bgcolor="#EDEDED"><img src="<%=request.getContextPath() %>/resources/images/hygl/images/p2.jpg" width="20" height="25" /></td>
        <td width="270" align="center" bgcolor="#EDEDED" class="hei12"><div>
          第三步：完成</td>
        </tr>
    </table>
      <table width="850" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td>&nbsp;</td>
        </tr>
      </table>
      <form action="fmptuser.action?m=frontResUserPwd" method="post" id="frm1" name="frm1">
      <table width="850" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="259" height="60" align="right" class="hei12">新登录密码：&nbsp;</td>
          <td width="591" class="hei12">
          <input type="hidden" name="userid" id="userid" value="${userid}"/>
          <input name="newPwd" type="password" id="newPwd"/>&nbsp;
           至少六位</td>
        </tr>
        <tr>
          <td height="60" align="right" class="hei12">确认新密码：&nbsp;</td>
          <td height="60" class="hei12">
          <input name="newPwdq" type="password" id="newPwdq"/>&nbsp;
            请再次输入新密码。</td>
        </tr>
        <tr>
          <td height="60" colspan="2" align="center" class="hei12">
          <input type="submit" name="submit" value="" style="background-image: url('<%=request.getContextPath() %>/resources/images/hygl/images/next-n.jpg');width:68px;height:25px;border:0;padding:0;"/>
          </td>
        </tr>
      </table>
      </form>
      </td>
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
