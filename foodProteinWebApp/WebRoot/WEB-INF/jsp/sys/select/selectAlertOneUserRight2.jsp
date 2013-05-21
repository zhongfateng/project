<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="java.util.*"%>
<%@ page import="com.nbw.sys.domain.SysUsers"%>
<%@ include file="/resources/common/res_common.inc"%>
<%
	List<SysUsers> list = (List<SysUsers>)request.getAttribute("userList");;
	String currentDeptName = (String)request.getAttribute("currentDeptName");
	if(currentDeptName==null || "".equals(currentDeptName)){
		currentDeptName="所有机构";
	}
%>
<html>
<head>
	<title></title>
</head>
<script language=Javascript>
    function _checkUser(id,userName){
    	parent.parent.document.getElementById("selExistStrsparent").value = id+"$"+userName+"|";
    }
</script>
<body>
<input type="hidden" id="selCheckedIds" name="selCheckedIds"/>
<table  align="center" cellpadding="0" cellspacing="0" class="table_head"
	width="100%">
	<tr>
		<td align="center" colspan="2" class="table_td">[当前：<b><%=currentDeptName %></b>]已有用户</td>
	</tr>
	<%for(int i=0;i<list.size();i++){ %>
	<tr >
		<td align="center" class="table_td">
			<input type="radio" name="checkUser" onclick="_checkUser('<%=list.get(i).getId() %>','<%=list.get(i).getName() %>')"/>
 		</td>
		<td align="center" class="table_td"><%=list.get(i).getName() %></td>
	</tr>
	<%} %>
</table>
</body>
</html>

