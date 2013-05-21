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
			loginname : {
				required : true,
				remote : "fmptuser.action?m=frontValiLoginName"
			},
			yanzhengma : {
				required : true,
				remote : "fmptuser.action?m=frontValiImgCode"
			}
		},
		messages : {
			loginname : {
				required : "<span style='color:red'>必填</span>",
				remote : "<span style='color:red'>没有此用户</span>"
			}			,
			yanzhengma : {
				required : "<span style='color:red'>必填</span>",
				remote : "<span style='color:red'>输入有误</span>"
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
        <td width="194" align="center" bgcolor="#FFE6BD" class="red12">填写账户名</td>
        <td width="20" height="25" align="center"><img src="<%=request.getContextPath() %>/resources/images/hygl/images/p2.jpg" width="20" height="25" /></td>
        <td width="193" align="center" bgcolor="#EDEDED" class="hei12">验证身份</td>
        <td width="20" align="center" bgcolor="#EDEDED" class="hei12"><img src="<%=request.getContextPath() %>/resources/images/hygl/images/p1.jpg" width="20" height="25" /></td>
        <td width="193" align="center" bgcolor="#EDEDED" class="hei12">设置新密码</td>
        <td width="20" align="center" bgcolor="#EDEDED" class="hei12"><img src="<%=request.getContextPath() %>/resources/images/hygl/images/p1.jpg" width="20" height="25" /></td>
        <td width="193" align="center" bgcolor="#EDEDED" class="hei12">完成</td>
      </tr>
    </table>
    <form name="frm1" id="frm1" action="fmptuser.action?m=frontValiUser" method="post">
      <table width="850" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="342" height="60" align="right" class="hei12">登录名：&nbsp;</td>
          <td width="508" class="hei12"><input name="loginname" style="height: 25px;"/></td>
        </tr>
        <tr>
          <td height="60" align="right" class="hei12">验证码：&nbsp;</td>
          <td height="60" class="hei12">
              <label ><input name="yanzhengma" style="height: 25px;" align="absmiddle"/></label>
              <label ><img align="absmiddle" src="fmptuser.action?m=frontImageCode" id="imgCode" width="100px;" height="30px;"/></label>
              <label> 看不清？<a href="javascript:;" onclick="document.getElementById('imgCode').src='fmptuser.action?m=frontImageCode&'+Math.random();">换一张</a></label>
          </td>
        </tr>
        <tr>
          <td height="60" colspan="2" align="center">
          <input name="submit" value="" type="submit" style="background-image: url('<%=request.getContextPath() %>/resources/images/hygl/images/next-n.jpg');width:68px; height:25px;border:0px;padding:0px;"/></td>
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
