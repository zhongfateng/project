<%@ page contentType="text/html; charset=utf-8" language="java"%>
<% String id = (String)request.getSession().getAttribute("userid");%>
<html>
<head>
<title>用户设置</title>
<%@ include file="/resources/common/res_common.inc"%>
<script type="text/javascript">
function submitForm(){
  		var sysCodesId = document.myform.sysCodesId;
		var name = document.myform.name;
		var describe = document.myform.describe;
		var sysRole = document.myform.sysRole;
		var validFlag = document.myform.validFlag;
		var remarks = document.myform.remarks;

		if (isLegalText(sysCodesId,32,-1,"sysCodesId")==false) {
			return false;
		}
		if (isLegalText(name,32,-1,"name")==false) {
			return false;
		}
		if (isLegalText(describe,32,-1,"describe")==false) {
			return false;
		}
		if (f_check_input_num(sysRole,32,-1,"sysRole")==false) {
			return false;
		}
		if (f_check_input_num(validFlag,32,-1,"validFlag")==false) {
			return false;
		}
		if (isLegalText(remarks,32,-1,"remarks")==false) {
			return false;
		}
	
	ajaxValidate("<%=request.getContextPath()%>/sysroles.action?m=validateData",
	              {id: document.myform.id.value});
}
</script>

</head>
<body>
<form action="sysroles.action?m=save" name="myform" method="post">
<table width="1000" border="0" cellpadding="0" cellspacing="0" align="center">
  <tr>
    <td height="26" background="resources/skins/default/images/navigation.gif">
	    <table width="1000" border="0" cellpadding="0" cellspacing="0">
	      <tr>
        	<td width="4">&nbsp;</td>
        	<td width="16"><img src="resources/skins/default/images/nav_title.gif" width="16" height="16"/></td>
        	<td>xxxx</td>
        	<td align="right"  valign="middle">
		     <input type="button" icon="icon-save" onclick="submitForm()" value="保存" />
        	 <input type="button" icon="icon-reset"  onclick="javascript:window.history.back()" value="返回" />
	        </td>
	        <td width="5">&nbsp;</td>
	      </tr>
	    </table>
    </td>
  </tr>
</table>

<table class="ceeedit" align="center" border="1" cellpadding="0" cellspacing="0" width="1000" bordercolorlight="#8FC5DC" bordercolordark="#FFFFFF" >
	<input type="hidden" name="id" value="<%=id %>">
	<tr>
		<td class="ceeedit_label" >原密码:</td>
		<td><input name="passwordold" value=""></td>
	</tr>
	<tr>
		<td class="ceeedit_label" >新密码:</td>
		<td><input name="passwordnew" value=""></td>
	</tr>
	<tr>
		<td class="ceeedit_label" >确认新密码:</td>
		<td><input name="repasswordnew" value=""></td>
	</tr>
	<tr>
		<td class="ceeedit_label" >皮肤:</td>
		<td><input name="skin" value=""></td>
	</tr>	
</table>
</form>
</body>
</html> 
