<%@page pageEncoding="gb2312" contentType="text/html; charset=gb2312" isELIgnored="false" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>中国食品微生物数据共享平台</title>
<link href="<%=request.getContextPath() %>/resources/images/hygl/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.6.2.js"></script>
<script type="text/javascript">
	function sendEmailCode(){
	var useremail = $("#useremail").val();
	var yy = $("#yy").val();
	var userid = $("#userid").val();
	if(document.getElementById("ysend").style.dispaly != "inline" && useremail != "" && yy==""){
			
		    $.ajax( {
					"url" : "fmptuser.action?m=frontSendEmail&useremail="+useremail+"&userid="+userid,
					"type" : "post",
					"dataType" : "json",
					"success" : function(data, desc1) {
						if(data[0]=="OK"){
							document.getElementById("ysend").style.display="inline";
							$("#yy").val("1");
						}
						
					}
				});
		}
	};
	
	function valiEC(){
		var yme = $("#yme").val();
		var userid = $("#userid").val();
		$.ajax({
			"url":"fmptuser.action?m=frontValiEmailCode&yme="+yme,
			"type":"post",
			"dataType":"json",
			"success":function(data,desc1){
				if(data.flag=="ok"){
					window.location.href="fmptuser.action?m=frontResPwd&userid="+userid;
				}else{
					$("#yinfo").html("错误");
				}
			}
		});
	};
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
        <td width="259" align="center" bgcolor="#FFE6BD" class="red12">第一步：验证身份</td>
        <td width="20" height="25" align="center"><img src="<%=request.getContextPath() %>/resources/images/hygl/images/p2.jpg" width="20" height="25" /></td>
        <td width="258" align="center" bgcolor="#EDEDED" class="hei12">第二步：重置密码</td>
        <td width="20" align="center" bgcolor="#EDEDED" class="hei12"><img src="<%=request.getContextPath() %>/resources/images/hygl/images/p1.jpg" width="20" height="25" /></td>
        <td width="258" align="center" bgcolor="#EDEDED" class="hei12"><div>
          第三步：完成</td>
        </tr>
    </table>
      <table width="850" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
        </tr>
      </table>
      <table width="850" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td class="hei12" align="center" class="hei12" colspan="2">邮箱号：&nbsp;${ue}&nbsp;
          <input id="useremail" type="hidden" name="useremail" value="${useremail}"/>
          <input type="hidden" name="userid" value="${user.id}" id="userid"/>
          </td>
        </tr>
        <tr>
        	<td height="60" align="center" class="hei12" colspan="2">
        		<input type="button" name="button" onclick="sendEmailCode();" style="background-image: url('<%=request.getContextPath() %>/resources/images/hygl/images/hyzm.gif');width:121px;height:25px;border:0;padding:0;"/>
        		<input id="yy" name="yy" value="" type="hidden"/>
          		<label id="ysend" style="display: none;">请查收邮件，点击链接进入下一步!</label>
        	</td>
        </tr>
        <!-- 
        <tr>
          <td height="60" align="right" class="hei12">邮箱证码：&nbsp;</td>
          <td height="60" class="hei12">
                         <input id="yme" name="yme" style="height: 25px;"/><span id="yinfo" style="color: red;"></span>
          </td>
        </tr>
         
        <tr>
          <td height="60" colspan="2" align="center" class="hei12">
          <input type="button" onclick="valiEC();" name="button" style="background-image: url('<%=request.getContextPath() %>/resources/images/hygl/images/next-n.jpg');width:68px;height:25px;border:0;padding:0;"/>
          </td>
        </tr>
        -->
      </table></td>
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
