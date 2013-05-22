<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ include file="/resources/common/res_all.inc"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${cssFile}" rel="stylesheet" type="text/css" />

<script language="javascript">
	function upps(){
		var frm=document.getElementById('myform');
		if(frm.oldpassword.value==''){
		    ymPrompt.errorInfo({title:"提示",message:"请输入旧密码！"});
			frm.oldpassword.focus();
			return false;
		}
	    if(frm.newpassword.value==''){
		    ymPrompt.errorInfo({title:"提示",message:"请输入新密码!"});
			frm.newpassword.focus();
			return false;
		}
		if(frm.newpassword2.value==''){
		    ymPrompt.errorInfo({title:"提示",message:"请输入确认密码!"});
			frm.newpassword2.focus();
			return false;
		}
		if(frm.newpassword.value!=frm.newpassword2.value){
		    ymPrompt.errorInfo({title:"提示",message:"确认密码与新密码不一致!"});
			frm.newpassword2.value='';
			frm.newpassword2.focus();
			return false;
		}
		sub();
	}
	function sub(){
		var options = {
			type: 'POST',
			async: false,
			dataType: 'content-type',
			success: function(responseText){
				if(responseText=="<%=Constants.ERROR_LOGIN %>"){
					ymPrompt.errorInfo({title:"提示",message:"旧密码输入错误！"});
				}else{
					ymPrompt.succeedInfo({title:"提示",message:"密码修改成功！"});
				}
			}
		}
		//提交form
		$('#myform').ajaxSubmit(options);
	} 
</script>
<title>修改密码</title>


</head>

<body style="background:${skinPath}images/hygl/fff.gif">
<form action="bzptuser.action?m=frontUpdatePassword" name="myform" method="post" id="myform">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td height="500" align="center" valign="middle">
        <table width="350" border="0" cellspacing="1" cellpadding="0" bgcolor="#00366C">
          <tr>
            <td height="50" bgcolor="#0051A2" class="">&nbsp;&nbsp;&nbsp;&nbsp;
            <label style="font-size: 14px;color: #CCC;"><strong>修改密码</strong></label></td>
          </tr>
          <tr>
            <td bgcolor="#FFFFFF"><table width="350" height="194" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="150" align="center" class="menu_p">旧&nbsp;密&nbsp;码：</td>
                <td width="250" class="menu_p"><input type="password" name="oldpassword"  /></td>
              </tr>
              <tr>
                <td align="center" class="menu_p">新&nbsp;密&nbsp;码：</td>
                <td class="menu_p"><input type="password" name="newpassword" /></td>
              </tr>
              <tr>
                <td align="center" class="menu_p">确认密码：</td>
                <td class="menu_p"><input type="password" name="newpassword2" /></td>
              </tr>
              <tr>
                <td colspan="2" align="center" class="menu_p"><img id="sbmintButton" style="cursor: pointer" src="${skinPath}images/hygl/ok.gif" onclick="upps();"/></td>
              </tr>
            </table></td>
          </tr>
        </table></td>
    </tr>
  </table>
</form>
</body>
</html>