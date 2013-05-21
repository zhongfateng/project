<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="java.util.*"%>
<%@ page import="com.nbw.sys.domain.SysUsers"%>
<%
	List<SysUsers> list = (List<SysUsers>)request.getAttribute("userList");;
	String currentDeptName = (String)request.getAttribute("currentDeptName");
	String selId = request.getParameter("selId");
	String selName = request.getParameter("selName");
	if(currentDeptName==null || "".equals(currentDeptName)){
		currentDeptName="所有机构";
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
    
    function _checkUser(id){
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
	    	var aa = document.getElementsByName("checkUser");
	    	for (var i=0; i<aa.length; i++)
	     		if(!aa[i].checked) return;
	    		all.checked = true;
	  	}
    }
    
    function _selAll(){
    	var users = document.getElementsByName("checkUser");
    	var checkAll = document.getElementById("selAll");
    	if(users == null)
    		return true;
    	for (var i=0; i<users.length; i++){
    		users[i].checked = checkAll.checked;
	   		_checkUser(users[i].id);
    	}
    }

</script>
<body>
<input type="hidden" id="selCheckedIds" name="selCheckedIds"/>
<table border="1" class="table_head" align="center" cellpadding="0" cellspacing="0"
	width="100%" >
	<tr>
		<td align="center" width="20%" class="table_td">
			<input type="checkbox" id="selAll" onclick="_selAll()"/>全选
		</td>
		<td align="center" width="80%" class="table_td">[当前：<b><%=currentDeptName %></b>]已有用户</td>
	</tr>
	<%for(int i=0;i<list.size();i++){ %>
	<tr >
		<td align="center" class="table_td">
		<input type="checkbox" name="checkUser" id="<%=list.get(i).getId() %>" value="<%=list.get(i).getName() %>"
						onclick="_checkUser('<%=list.get(i).getId() %>','<%=list.get(i).getName() %>')"/>
 			</td>
		<td align="center" class="table_td"><%=list.get(i).getName() %></td>
	</tr>
	<%} %>
</table>
<script>
	var allUsers = document.getElementsByName("checkUser");
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
    	var aa = document.getElementsByName("checkUser");
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

