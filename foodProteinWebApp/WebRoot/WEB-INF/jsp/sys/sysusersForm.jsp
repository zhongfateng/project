<%@ page contentType="text/html; charset=utf-8" language="java"%>
<% String show = (String)request.getAttribute("show");%>
<html> 
<head>  
<title>添加数据</title>  
<%@ include file="/resources/common/res_all.inc"%>
<script type="text/javascript">
//给form赋值
setValue = function (jsonObject){
	document.myform.id.value               = jsonObject.id ;
	document.myform.loginCode.value       = jsonObject.loginCode ;
	document.myform.name.value       = jsonObject.name ;
	document.myform.orderNum.value    = jsonObject.orderNum ;
	document.myform.sysOrganizationsId.value    = jsonObject.sysOrganizationsId ;
	document.myform.sysOrganizationsName.value     = jsonObject.sysOrganizationsName ;
	document.myform.remarks.innerHTML     = jsonObject.remarks ;
}
//删除时清空form
resetForm = function (oid,oname) {
	document.myform.id.value         = "" ;
	document.myform.name.value       = "" ;
	document.myform.orderNum.value    = "0" ;
	document.myform.sysOrganizationsId.value    = oid ;
	document.myform.loginCode.value   = "" ; 
	document.myform.sysOrganizationsName.value     = oname ;
	document.myform.remarks.innerHTML = "" ;
	$("[type='checkbox']").attr("checked",'');
}
//保存
save = function () { 
	var aa = realsave();
	ymPrompt.resizeWin(300,150);
	return aa;
}

realsave = function () {
	var flag = "failure!";
	if(document.myform.sysOrganizationsId.value==""){
		ymPrompt.alert({title:"提示",message:"没有选择上级节点，请先选择上级节点再添加数据！",winPos:"c"});
		return false;
	}
	var name = document.myform.name;
	var loginCode = document.myform.loginCode;
	var remarks = document.myform.remarks;
	var orderNum = document.myform.orderNum;
	//用户名称验证
	if (isLegalTextContainspaces(name,2,32,"名称")==false) {
		return false;
	}
	//登录帐号验证
	if (isLegalTextCode(loginCode, 4, 32, "登录帐号")==false){
		return false;
	}
	//用户备注验证
	if (isLegalTextContainspaces(remarks,0,128,"备注")==false) {
		return false;
	}
	//排序号验证
	if (isNaN(orderNum.value) || orderNum.value.length > 6) {
		ymPrompt.errorInfo({title:"提示",message:"排序号必须是6位以内的数字！"});
		return false;
	}
	var options = {
		type: 'POST',
		async: false,
		dataType: 'content-type',
		success: function(responseText) {
			flag = ""+responseText;
		}
	}
	//提交form
	$('#myform').ajaxSubmit(options);
	if ("success"==flag.substring(0,7)) {
		document.myform.id.value=flag.substring(7);
	}
	return flag;
}

getOrgId = function () {
	return document.myform.sysOrganizationsId.value;
}
</script>

</head>  
<body> 
	<form action="sysusers.action?m=save" name="myform" method="post" id="myform">
		<input type="hidden" name="id" value="" />
		<table align="center" border="1" cellpadding="0"
			cellspacing="0" width="100%" bordercolorlight="#8FC5DC"
			bordercolordark="#FFFFFF">
			<tr>
				<td style="text-align:right">
					所属部门:
				</td>
				<td  align="left">
					<input type="text" readonly="readonly"
					 name="sysOrganizationsName" value="" />
					<input type="hidden" name="sysOrganizationsId" value="" />
					<input name="buttonD5" type="button" onClick="selMultiOrg('sysOrganizationsId','sysOrganizationsName','one');ymPrompt.resizeWin(300,150);" icon='icon-search' value="选择部门" />
				</td>
				<td style="text-align:right">
					名称：
				</td>
				<td  align="left">
					<input type="text" <%if (show!=null&&"true".equals(show)){%>readonly="readonly"<%} %> name="name" value="" />
				</td>
			</tr>
			<tr>
				<td  style="text-align:right">
					登录帐号：
				</td>
				<td  align="left">
					<input type="text" <%if (show!=null&&"true".equals(show)){%>readonly="readonly"<%} %> name="loginCode" value="" />
				</td>
				<td style="text-align:right">
					排序号：
				</td>
				<td  align="left" >
					<input type="text" <%if (show!=null&&"true".equals(show)){%>
						readonly="readonly" <%} %> name="orderNum"
						id="orderNum" value="" />
				</td>
			</tr>
			<tr>
				<td colspan="4">
					是否可登录系统：<input type="checkBox" name="">
				</td>
			</tr>
			<tr>
				<td style="text-align:right">
					备注：
				</td>
				<td colspan="3" align="left">
					<textarea rows="2" cols="50"  name="remarks" id="remarks" ></textarea>
				</td>
			</tr>
		</table>
	</form>
</body>
</html> 
