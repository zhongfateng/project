<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="java.util.*"%>
<%@page import="com.nbw.sys.domain.SysRoles"%>
<%@page import="com.nbw.sys.domain.SysRolesUsers"%>
<%
	List<SysRoles> allroles = (List<SysRoles>) request
			.getAttribute("allroles");
	List<SysRolesUsers> roleusers = (List<SysRolesUsers>) request
			.getAttribute("roleusers");
%>
<html>
	<head>
		<title>分配角色</title>
		<%@ include file="/resources/common/res_common.inc"%>

		<script type="text/javascript">
			save = function () { 
				var aa = realsave();
				ymPrompt.resizeWin(300,150);
				return aa;
			}
			realsave = function () {
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
				$('#myformroles').ajaxSubmit(options);
				return flag;
			
			}
		</script>
	</head>
	<body>
		<form action="sysusers.action?m=saveUserRole" name="myformroles"
			method="post" id="myformroles">
			<table align="center" border="1" cellpadding="0" cellspacing="0"
				width="100%" bordercolorlight="#8FC5DC" bordercolordark="#FFFFFF">
				<tr>
					<td height="30" align="center">
						选择
					</td>
					<td align="center">
						角色名称
					</td>
					<td align="center">
						角色分类
					</td>
				</tr>
				<%
					if (allroles != null)
						for (int i = 0; i < allroles.size(); i++) {
							SysRoles sr = allroles.get(i);
				%>
				<tr>
					<td align="center">
						<input type="checkbox" id="role<%=sr.getId()%>" name="roleId"
							value="<%=sr.getId()%>">
					</td>
					<td align="center">
						<%=sr.getName()%>
					</td>
					<td align="center">
						<%=sr.getSysCodesId()%>
					</td>
				</tr>
				<%
					}
				%>
				<tr>
					<td>
					</td>
				</tr>
			</table>
			<input type="hidden" name="userId" name="userId"
				value="<%=request.getParameter("userId")%>">
		</form>
		<script type="text/javascript">
		<%
			if(roleusers!=null)
			for(int i=0;i<roleusers.size();i++){
				SysRolesUsers sru = roleusers.get(i);
				%>
						document.getElementById("role<%=sru.getRoleId()%>").checked=true;
				<%
			}
		%>
		</script>
	</body>
</html>
