<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="java.util.*"%>
<%@page import="com.nbw.sys.domain.SysRolesUsers"%>
<%
	String show = (String) request.getAttribute("show");
	List<SysRolesUsers> roleUsers = (List<SysRolesUsers>) request
			.getAttribute("roleUsers");
	String userIds = "";
	if (roleUsers != null) {
		for (int i = 0; i < roleUsers.size(); i++) {
			SysRolesUsers sru = roleUsers.get(i);
			userIds += sru.getUserId() + "|";
		}
	}
	String roleId = request.getParameter("roleId");
%>
<html>
	<head>
		<title>添加数据</title>
		<%@ include file="/resources/common/res_all.inc"%>
		<script type="text/javascript">
			save = function () { 
				var aa = realsave();
				ymPrompt.resizeWin(300,150);
				return aa;
			}
			realsave = function (){
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
	<body style="margin: 0; padding: 0;">
		<form id="myroleuserform" action="sysroles.action?m=saveRUsers"
			name="myroleuserform" method="post">
			<input type="hidden" name="userIds" id="userIds"
				value="<%=userIds%>" />
			<input type="hidden" name="roleId" id="roleId" value="<%=roleId%>" />
		</form>

		<div id="selectMultiDiv" style="width: 100%; height: 100%"></div>
		<script type="text/javascript">
			selMultiUserByIdsInnerDiv("userIds","selectMultiDiv");
		</script>
	</body>

</html>
