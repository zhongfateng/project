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
	function _submit(){
    	var returnVal = parent.document.getElementById("selCheckedIds").value;
		parent.parent.document.getElementById("selExistStrsparent").value = returnVal;
    }
    
    function _checkUser(id,userName){
    	var thisUser = document.getElementById(id);
    	//userid1|userid2|userid3|$username|&username2|username3|
    	
    	var checkedStrs = parent.document.getElementById("selCheckedIds").value;
    	
    	if(checkedStrs == ""){
    		if(thisUser.checked){
	    		checkedStrs = id+"|$"+userName+"|";
	    	}
		}else{
			if(thisUser.checked){
			
				if(checkedStrs.indexOf(id+"|") == -1){
					var indexNum = checkedStrs.indexOf("\$");
		    		var before = checkedStrs.substring(0,indexNum);
		    		var behind = checkedStrs.substring(indexNum);
		    		checkedStrs = before + id +"|"+ behind + userName + "|";
				}
				
	    	}else{
	    	 
	    		if(checkedStrs.indexOf(id+"|") != -1){
	    		
		    		var indexId = checkedStrs.indexOf(id+"|");
		    		//alert(indexId)
		    		var beginId = checkedStrs.substring(0,indexId);
		    		var endId = checkedStrs.substring(indexId + id.length +1);
		    		checkedStrs = beginId + endId;
		    		//alert(beginId +"sss"+ endId)
		    	//	var indexName = checkedStrs.indexOf(userName+"|");
		    		//var beginName = checkedStrs.substring(0,indexName);
		    		//var endName = checkedStrs.substring(indexName + userName.length +1);
		    		//checkedStrs = beginName + endName;
		    		//alert(beginName +"ddd"+ endName)
		    	}
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
    	var checkedFlag = checkAll.checked;
    	if(users == null)
    		return true;
    	for (var i=0; i<users.length; i++){
    		users[i].checked = checkedFlag;
	   		_checkUser(users[i].id,users[i].value);
    	}
    }
</script>
<body>
<input type="hidden" id="selCheckedIds" name="selCheckedIds"/>
<table  align="center" cellpadding="0" cellspacing="0" class="table_head"
	width="100%">
	<tr>
		<td align="center" width="20%"  class="table_td">
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
   	if(selCheckedIds == "$"){
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

