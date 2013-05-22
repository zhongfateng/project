<%@ page contentType="text/html; charset=utf-8" language="java"%>
<html>
	<head>
		<title>添加数据</title>
		<%@ include file="/resources/common/res_all.inc"%>
		<script type="text/javascript">
		//给form赋值
		setValue = function (jsonObject){
			document.myform.id.value            = jsonObject.id ;
			document.myform.code.value       	= jsonObject.code ;
			document.myform.name.value       	= jsonObject.name ;
			document.myform.describe.value    	= jsonObject.describe ;
			document.myform.parentId.value    	= jsonObject.parentId ;
			document.myform.url.value   		= jsonObject.url ; 
			document.myform.parentName.value    = jsonObject.parentName ;
			document.myform.orderNum.value     	= jsonObject.orderNum ;
			document.myform.remarks.innerHTML  	= jsonObject.remarks ;
			var slt=document.myform.elements["linkType"];
			for(var i=0;i<slt.options.length;i++){
				if(slt.options[i].value==jsonObject.linkType){
					slt.options[i].selected=true;
				}
			}
			slt=document.myform.elements["isMenu"];
			for(var i=0;i<slt.options.length;i++){
				if(slt.options[i].value==jsonObject.isMenu){
					slt.options[i].selected=true;
				}
			}
			document.myform.treeId.value 		= jsonObject.treeId ;
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
			document.myform.id.value         = id ;
			document.myform.code.value       = "" ;
			document.myform.name.value       = "" ;
			document.myform.describe.value    = "" ;
			document.myform.parentId.value    = pid ;
			document.myform.url.value   = "#" ; 
			document.myform.parentName.value     = pname ;
			document.myform.orderNum.value     = "0" ;
			document.myform.remarks.innerHTML  = "" ;
			document.myform.linkType.value  = "" ;
			var slt=document.myform.elements["linkType"];
			slt.options[0].selected=true;
			slt=document.myform.elements["isMenu"];
			slt.options[0].selected=true;
			document.myform.treeId.value  = "" ;
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
			if(document.myform.parentId.value==""){
				ymPrompt.errorInfo({title:"提示",message:"没有选择上级节点，请先选择上级节点再添加数据！"});
				return false;
			}
			var code = document.myform.code;
			var name = document.myform.name;
			var url = document.myform.url;
			var describe = document.myform.describe;
			var remarks = document.myform.remarks;
			var orderNum = document.myform.orderNum;
			var linkType = document.myform.linkType;
			if (isLegalTextCode(code,4,32,"模块编号")==false) {
				return false;
			}
			if (isLegalTextContainspaces(name,2,32,"模块名称")==false) {
				return false;
			}
			//排序号验证
			if (isNaN(orderNum.value) || orderNum.value.length > 6) {
				ymPrompt.errorInfo({title:"提示",message:"排序号必须是6位以内的数字！"});
				return false;
			}
			if (isLegalURL(url,1,256,"访问地址")==false) {
				return false;
			}
			if (isLegalTextContainspaces(describe,0,128,"模块说明")==false) {
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
		
		getName = function () {
			return document.myform.name.value;
		}
		getSaveFlag = function () {
			return document.myform.Action_SaveFlag.value;
		}
		setSaveFlag = function (flag) {
			//数据更新模式
			document.myform.Action_SaveFlag.value = flag ;
		}
	</script>

	</head>
	<body>
		<form action="sysmodules.action?m=save" name="myform" method="post"
			id="myform">
			<input type="hidden" name="id" value="" />
			<input type="hidden" name="treeId" value="" />
			<input type="hidden" name="Action_SaveFlag" value="U" />
			<table align="center" border="1" cellpadding="0"
				cellspacing="0" width="100%" bordercolorlight="#8FC5DC"
				bordercolordark="#FFFFFF">
				<tr>
					<td  style="text-align: right">
						上级模块:
					</td>
					<td align="left" colspan="3" >
						<input type="text" readonly="readonly" 
							name="parentName" value="" />
						<input type="hidden" name="parentId" value="" />
					</td>
				</tr>
				<tr>
					<td style="text-align: right" >
						模块编号：
					</td>
					<td align="left" >
						<input type="text" name="code" value="" />
					</td>
					<td style="text-align: right" >
						模块名称：
					</td>
					<td align="left" >
						<input type="text" name="name" value="" />
					</td>
				</tr>
				<tr>
					<td style="text-align: right" >
						设置：
					</td>
					<td align="left" >
						公共模块
						<select id="linkType" name="linkType">
							<option value=0>否</option>
							<option value=-1>是</option>
						</select>
						显示菜单
						<select id="isMenu" name="isMenu">
							<option value=0>是</option>
							<option value=-1>否</option>
						</select>
					</td>
					<td style="text-align: right" >
						排序号：
					</td>
					<td align="left" >
						<input type="text" name="orderNum"
							value="" />
					</td>
				</tr>
				<tr>
					<td style="text-align: right" >
						访问地址：
					</td>
					<td>
						<input type="text" name="url"
							value="" />
					</td>
					<td style="text-align: right" >
						模块说明：
					</td>
					<td >
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

