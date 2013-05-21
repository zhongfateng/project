<%@ page language="java" pageEncoding="utf-8" isELIgnored="false"
	import="java.lang.*"%>
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
<link rel="stylesheet" id='skin' type="text/css" href="<%=request.getContextPath()%>/resources/component/ymPrompt/skin/qq/ymPrompt.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.6.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src ="<%=request.getContextPath()%>/resources/component/ymPrompt/ymPrompt.js"></script>
<script type="text/javascript">

	$(function(){
	
		if($("#ok").val()=="OK"){
			ymPrompt.alert('修改成功！');
		};
	
	
		$('#frm1').validate( {
		rules : {
			oldPwd : {
				required : true,
				remote : "fmptJf.action?m=frontModifyOldPwd"
			},
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
			oldPwd : {
				required : "<span style='color:red'>必填</span>",
				remote : "<span style='color:red'>密码错误</span>"
			}			,
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
	function tijiao(){
	document.all.frm1.submit();
	}
		
	</script>
</head>

<body>

    <table width="734" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="35" background="<%=request.getContextPath() %>/resources/images/myCenter/images/leftmenu-bg.jpg" class="hei16B">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;个人信息管理</td>
        </tr>
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td align="center" valign="top"  style="border:solid; border-width:0 1px 1px 1px; border-color:#d0ced1; padding:15px;">
          
          <form name="frm1" method="post" action="fmptJf.action?m=frontModifyOldPd" id="frm1">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td align="left" valign="bottom"  style="border-bottom:solid 1px #66CCFF">
                <p class="hei14"><br />
                        <strong>修改密码</strong>&nbsp;&nbsp;<span class="hei14" style="border-bottom:solid 1px #cfe2f3"></span>
                </p></td>
              </tr>
              <tr>
                <td height="50" align="left" class="hei14">&nbsp;&nbsp;&nbsp;&nbsp;旧&nbsp;&nbsp;密&nbsp;&nbsp;码：
                  <input name="oldPwd" type="password" value="" size="20"   style="border:solid 1px #CCCCCC"/></td>
              </tr>
              <tr>
                <td height="50" align="left" class="hei14">&nbsp;&nbsp;&nbsp; 新&nbsp;&nbsp;密&nbsp;&nbsp;码：
                  <input name="newPwd" id="newPwd" type="password" value="" size="20"   style="border:solid 1px #CCCCCC"/>                </td>
              </tr>
              <tr>
                <td height="50" align="left" class="hei14">&nbsp;&nbsp;&nbsp; 确认新密码：
                  <input name="newPwdq" id="newPwdq" type="password" value="" size="20"   style="border:solid 1px #CCCCCC"/></td>
              </tr>
              <tr>
                <td height="101" align="center" class="hei14"  style="border-bottom:solid 1px #66CCFF">
                <input type="submit" name="submit" value="" style="background: url('${skinPath}images/tijiao.jpg');width:134px;height:35px;border:0;padding:0;"/>
                </td>
              </tr>
              <tr>
                <td height="50" align="center" class="hei14">&nbsp;<input name="ok" id="ok" value="${info}" type="hidden"/></td>
              </tr>
            </table>
            </form>
            
            </td>
        </tr>
      </table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
</body>
</html>
