<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="java.util.*"%>
<%@ page import="com.nbw.sys.domain.SysCodes"%>
<html>
	<head>
		<title>添加数据</title>
		<%@ include file="/resources/common/res_all.inc"%>
		<script type="text/javascript">
		//给form赋值
		setValue = function (jsonObject){
			document.myform.id.value            = jsonObject.id ;
			document.myform.name.value       	= jsonObject.name ;
			document.myform.describe.value    	= jsonObject.describe ;
			document.myform.remarks.innerHTML  	= jsonObject.remarks ;
			var slt=document.myform.elements["sysCodesId"];
			for(var i=0;i<slt.options.length;i++){
				if(slt.options[i].value==jsonObject.sysCodesId){
					slt.options[i].selected=true;
				}
			}
			slt=document.myform.elements["sysRole"];
			for(var i=0;i<slt.options.length;i++){
				if(slt.options[i].value==jsonObject.sysRole){
					slt.options[i].selected=true;
				}
			}
		}
		//删除时清空form
		resetForm = function () {
			document.myform.id.value         = "";
			document.myform.name.value       = "" ;
			document.myform.describe.value    = "" ;
			document.myform.remarks.innerHTML  = "" ;
			var slt=document.myform.elements["sysCodesId"];
			slt.options[0].selected=true;
			slt=document.myform.elements["sysRole"];
			slt.options[0].selected=true;
		}
		//保存
		save = function () { 
			var aa = realsave();
			ymPrompt.resizeWin(300,150);
			return aa;
		}
		realsave = function () {
			var flag = "failure!";
			var name = document.myform.name;
			var describe = document.myform.describe;
			var remarks = document.myform.remarks;
			if (isLegalTextContainspaces(name,2,32,"角色名称")==false) {
				return false;
			}
			if (isLegalTextContainspaces(describe,0,128,"角色说明")==false) {
				return false;
			}
			if (isLegalTextContainspaces(remarks,0,128,"备注")==false) {
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
	</script>

	</head>
	<body>
		<form action="sysroles.action?m=save" name="myform" method="post"
			id="myform">
			<input type="hidden" name="id" value="" />
			<table align="center" border="1" cellpadding="0"
				cellspacing="0" width="100%" bordercolorlight="#8FC5DC"
				bordercolordark="#FFFFFF">
				<tr>
					<td style="text-align: right" >
						角色类型：
					</td>
					<td align="left" >
						<select id="sysCodesId" name="sysCodesId">
						<%
							List<SysCodes> roleTypes = (List<SysCodes>)request.getAttribute("roleTypes");
							for(int i=0;i<roleTypes.size();i++){
						%>
								<option value=<%=roleTypes.get(i).getCode()%>><%=roleTypes.get(i).getName()%></option>
						<%		
							}
						%>
						</select>
					</td>
					<td style="text-align: right" >
						角色名称：
					</td>
					<td align="left" >
						<input type="text" name="name" value="" />
					</td>
				</tr>
				<tr>
					<td style="text-align: right" >
						是否为系统角色：
					</td>
					<td align="left" >
						<select id="sysRole" name="sysRole">
							<option value=0>否</option>
							<option value=1>是</option>
						</select>
					</td>
					<td style="text-align: right" >
						角色说明：
					</td>
					<td align="left" >
						<input type="text" name="describe"
							value="" />
					</td>
				</tr>
				<tr>
					<td style="text-align: right" >
						备注：
					</td>
					<td colspan="3" align="left" >
						<textarea rows="3" cols="60" name="remarks" id="remarks"></textarea>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>

