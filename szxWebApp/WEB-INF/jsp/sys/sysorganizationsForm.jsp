<%@ page contentType="text/html; charset=utf-8" language="java"%>
<html>
	<head>
		<title>添加数据</title>
		<%@ include file="/resources/common/res_all.inc"%>
		<script type="text/javascript">
		//给form赋值
		setValue = function (jsonObject){
			document.myform.id.value               = jsonObject.id ;
			document.getElementById("code").value       = jsonObject.code ;
			document.getElementById("name").value       = jsonObject.name ;
			document.getElementById("remarks").innerHTML    = jsonObject.remarks ;
			document.getElementById("parentId").value    = jsonObject.parentId ;
			document.getElementById("parentName").value     = jsonObject.parentName ;
			document.getElementById("orderNum").value   = jsonObject.orderNum ;
			document.getElementById("shortName").value 	= jsonObject.shortName ;
			
			$("[type='checkbox']").attr("checked",'');
			if (jsonObject.leaf==true){
				$("[type='checkbox']").attr("disabled","");
				var functions = jsonObject.functions;
				if(0<functions.length){
					for(i=0;i<functions.length;i++){
						if ("A" != functions[i]) {
							var ck = "checkbox" + functions[i];
							$("[name="+ck+"]").attr("checked",true);
						}
					}
				}
			}else{
				$("[type='checkbox']").attr("disabled",true);
			}
			//数据更新模式
			document.myform.Action_SaveFlag.value     = "U" ;
		}

		//删除时清空form
		resetForm = function (pid,pname,id) {
			document.myform.id.value        			= id ;
			document.getElementById("code").value       = "" ;
			document.getElementById("name").value       = "" ;
			document.getElementById("remarks").innerHTML    = "" ;
			document.getElementById("parentId").value    = pid ;
			document.getElementById("parentName").value     = pname ;
			document.getElementById("orderNum").value   = "0" ;
			document.getElementById("shortName").value 	= "" ;
			
			
			$("[type='checkbox']").attr("checked",'');
			//数据更新模式
			document.myform.Action_SaveFlag.value     = "I" ;
		}
	
		//保存
		save = function () { 
			var aa = realsave();
			ymPrompt.resizeWin(300,150);
			return aa;
		}
		realsave = function () {
			var flag = "failure!";
			if(document.getElementById("parentId").value==""){
				ymPrompt.errorInfo({title:"提示",message:"没有选择上级节点，请先选择上级节点再添加数据！"});
				return false;
			}
			var code = document.getElementById("code");
			var name = document.getElementById("name");
			var shortName = document.getElementById("shortName");
			var orderNum = document.getElementById("orderNum");
			var remarks = document.getElementById("remarks");
			if (isLegalTextCode(code,4,32,"机构编号")==false) {
				return false;
			}
			if (isLegalTextContainspaces(name,2,64,"机构名称")==false) {
				return false;
			}
			if (isLegalTextContainspaces(shortName,0,32,"机构简称")==false) {
				return false;
			}
			//排序号验证
			if (isNaN(orderNum.value) || orderNum.value.length > 6) {
				ymPrompt.errorInfo({title:"提示",message:"排序号必须是6位以内的数字！"});
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
			//提交form基本信息
			$('#myform').ajaxSubmit(options);
			
			if ("success"==flag.substring(0,7)) {
				document.myform.id.value=flag.substring(7);
			}
			return flag;
		}
	
		getName = function () {
			return document.getElementById("name").value;
		}
		getSaveFlag = function () {
			return document.getElementById("Action_SaveFlag").value;
		}
		setSaveFlag = function (falg) {
			//数据更新模式
			document.getElementById("Action_SaveFlag").value = falg ;
		}
		
	</script>
	</head>
	<body>
		<form action="sysorganizations.action?m=save" name="myform"
			method="post" id="myform">
			<input type="hidden" name="id" value="" />
			<input type="hidden" name="Action_SaveFlag" value="U" />
			<table  align="center" border="1" cellpadding="0"
				cellspacing="0" width="100%" bordercolorlight="#8FC5DC"
				bordercolordark="#FFFFFF" >
				<tr>
					<td  style="text-align: right">
						上级机构：
					</td>
					<td align="left" colspan="3" >
						<input type="text" readonly="readonly" 
							name="parentName" id="parentName" value=""  />
						<input type="hidden" name="parentId" id="parentId" value="" />
					</td>

				</tr>
				<tr>
					<td style="text-align: right" >
						机构编号：
					</td>
					<td align="left" >
						<input type="text" name="code" id="code"
							value=""  />
					</td>
					<td style="text-align: right" >
						机构名称：
					</td>
					<td align="left" >
						<input type="text" name="name" id="name"
							value=""  />
					</td>
				</tr>
				<tr>
					<td style="text-align: right" >
						机构简称：
					</td>
					<td align="left" >
						<input type="text" name="shortName"
							id="shortName" value=""  />
					</td>
					<td style="text-align: right" >
						排序号：
					</td>
					<td align="left" >
						<input type="text" name="orderNum"
							id="orderNum" value=""  />
					</td>
				</tr>
				<tr>
					<td style="text-align: right" > 
						备注：
					</td>
					<td colspan="3" align="left" >
						<textarea rows="2" cols="50" name="remarks" id="remarks"></textarea>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>


