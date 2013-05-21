<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="java.util.*"%>
<%@ page import="com.nbw.sys.domain.SysModuleActions"%>
<%
	List<SysModuleActions> list = (List<SysModuleActions>) request
			.getAttribute("mActionList");
	String currentModuleName = (String) request
			.getAttribute("currentModuleName");
	String selId = request.getParameter("selId");
	String selName = request.getParameter("selName");
	if(currentModuleName==null || "".equals(currentModuleName)){
		currentModuleName="所有模块";
	}
%>
<html>
	<head>
		<%@ include file="/resources/common/res_all.inc"%>
	</head>
	<script language=Javascript>
	function _submit(){
		var returnVal = parent.document.getElementById("selCheckedIds").value;
		eval("parent.parent.document.all.<%=selId%>").value = returnVal;
    }
    
    function _checkMAction(id){
    	var thisUser = document.getElementById(id);
    	//userid1|userid2|userid3|
    	var checkedStrs = parent.document.getElementById("selCheckedIds").value;
		if(thisUser.checked){
			if(checkedStrs.indexOf(id+"|") == -1){
    			checkedStrs = checkedStrs + id +"|";
    		}
    	}else{
    		if(checkedStrs.indexOf(id+"|") != -1){
    		    var indexId = checkedStrs.indexOf(id+"|");
	    		var beginId = checkedStrs.substring(0,indexId);
	    		var endId = checkedStrs.substring(indexId + id.length +1);
	    		checkedStrs = beginId + endId;
    		}
    	}
    	parent.document.getElementById("selCheckedIds").value = checkedStrs;
    	_submit();	
    	var all = document.getElementById("selAll");
		if(!thisUser.checked) all.checked = false;
	  	else{
	    	var aa = document.getElementsByName("checkMAction");
	    	for (var i=0; i<aa.length; i++)
	     		if(!aa[i].checked) return;
	    		all.checked = true;
	  	}
    }
    
    function _selAll(){
    	var users = document.getElementsByName("checkMAction");
    	var checkAll = document.getElementById("selAll");
    	if(users == null)
    		return true;
    	for (var i=0; i<users.length; i++){
    		users[i].checked = checkAll.checked;
	   		_checkMAction(users[i].id);
    	}
    }

	</script>
	<body>
		<input type="hidden" id="selCheckedIds" name="selCheckedIds" />
		<table align="center" border="1" cellpadding="0" cellspacing="0"
			class="table_head" width="100%">
			<tr>
				<td align="center" width="20%" class="table_td">
					<input type="checkbox" id="selAll" onclick="_selAll()" />
					全选
				</td>
				<td align="center" colspan="2" width="80%" class="table_td">
					[当前：
					<b><%=currentModuleName%></b>]下的功能
				</td>
			</tr>
			<%
				for (int i = 0; i < list.size(); i++) {
			%>
			<tr>
				<td align="center" class="table_td">
					<input type="checkbox" name="checkMAction"
						id="<%=list.get(i).getId()%>" value="<%=list.get(i).getName()%>"
						onclick="_checkMAction('<%=list.get(i).getId()%>','<%=list.get(i).getName()%>')" />
				</td>
				<td align="center" class="table_td"><%=list.get(i).getName()%></td>
				<td align="center" class="table_td"><%=list.get(i).getMethods()%></td>
			</tr>
			<%
				}
			%>
		</table>
		<script>
	var allUsers = document.getElementsByName("checkMAction");
   	var selCheckedIds = parent.document.getElementById("selCheckedIds").value;
   	if(selCheckedIds == "|"){
   		selCheckedIds = document.getElementById("selCheckedIds").value;
   	}
   	if(allUsers){
	   	for(var i = 0;i<allUsers.length;i++){
	   		if(selCheckedIds.indexOf(allUsers[i].id+"|") != -1 )
	   			allUsers[i].checked = true;
	   	}
	   	//判断全选框是否应该选中
		var all = document.getElementById("selAll");
		var flag = true;
    	var aa = document.getElementsByName("checkMAction");
    	for (var i=0; i<aa.length; i++){
     		if(!aa[i].checked) {
     			flag = false;
     			break;
     		}
     	}
     	if(aa.length==0) flag = false;
    	all.checked = flag;
   	}
</script>
	</body>
</html>


