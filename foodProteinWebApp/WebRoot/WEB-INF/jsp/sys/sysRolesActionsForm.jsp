<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="java.util.*"%>
<%@page import="com.nbw.sys.domain.SysRolesModules"%>
<%
	String show = (String) request.getAttribute("show");
	List<SysRolesModules> roleUsers = (List<SysRolesModules>) request
			.getAttribute("roleActions");
	String mActionIds = "";
	if (roleUsers != null) {
		for (int i = 0; i < roleUsers.size(); i++) {
			SysRolesModules srm = roleUsers.get(i);
			mActionIds += srm.getModuleActionId() + "|";
		}
	}
	String roleId = request.getParameter("roleId");
%>
<html>
	<head>
		<title>添加数据</title>
		<%@ include file="/resources/common/res_all.inc"%>
		<script type="text/javascript">
			function save(){
			<%
				if(roleId == null || "null".equals(roleId)){
			%>
				return "请先选择角色再修改！";
			<%		
				}
			%>
				flag = "failure!";
				var options = {
					type: 'POST',
					async: false,
					dataType: 'content-type',
					success: function(responseText) {
						flag = ""+responseText;
					}
				}
				//提交附属信息
				$('#myroleuserform').ajaxSubmit(options);
				return flag;
			}
	</script>

	</head>
	<body>
		<form style="margin: 0px" id="myroleuserform"
			action="sysroles.action?m=saveRMActions" name="myroleuserform"
			method="post">
			<input type="hidden" name="mActionIds" id="mActionIds"
				value="<%=mActionIds%>" />
			<input type="hidden" name="roleId" id="roleId" value="<%=roleId%>" />
		</form>
		<div id="selectMultiDiv" style="width: 100%; height: 100%"></div>
		<script type="text/javascript">
			selMultiModuleActionIdsByIdsInnerDiv("mActionIds","selectMultiDiv");
		</script>
	</body>
</html>
