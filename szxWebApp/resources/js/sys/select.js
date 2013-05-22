	var nDialogWidth1 = 300;
	var nDialogHeight1 = 400;

	var nDialogWidth2 = 800;
	var nDialogHeight2 = 600;
	
function getContextPath() { 
    var pathName = document.location.pathname; 
    var index = pathName.substr(1).indexOf("/"); 
    var result = pathName.substr(0,index+1); 
    return result+"/"; 
}


/**
*	多选用户
*	通过userIds选择用户
*	oName		input的名字（该对象的值即为传入值也为输出值），用来存放选择后的UserNames 以"，"分隔。
*	oId 		input的名字（该对象的值即为传入值也为输出值），用来存放选择后的UserIds 以|分隔。
*	divName		div的名字，选择框所在的DIV
*   非弹出
*/
function selMultiUserInnerDiv(oId,oName,divName){
	var userIds = eval("document.all."+oId).value;
	var userNames = eval("document.all."+oName).value;
	if(userNames != null && userNames != "")
		userNames = userNames+"|";
	var existUserStrs = userIds+"$"+userNames.replace(/\,/g,"|");
	existUserStrs = encodeURI(encodeURI(existUserStrs));
	$.ajax({
	   type: "POST",
	   url: getContextPath()+"select.action",
	   data: "m=select&url=select.action?m=selectMultiUser&selExistStrs="+existUserStrs+"&selId="+oId+"&selName="+oName,
	   success: function(data){
	     eval("document.all."+divName).innerHTML = data;
	   }
	});	
}

/**
*	多选用户
*	通过userIds选择用户
*	
*	oId 		input的名字（该对象的值即为传入值也为输出值），用来存放选择后的UserIds 以|分隔。
*	divName		div的名字，选择框所在的DIV
*	非弹出
*/
function selMultiUserByIdsInnerDiv(oId,divName){
	var userIds = eval("document.all."+oId).value;
	var existUserStrs = ""+userIds;
	existUserStrs = encodeURI(encodeURI(existUserStrs));
	$.ajax({
	   type: "POST",
	   url: getContextPath()+"select.action",
	   data: "m=select&url=select.action?m=selectMultiUser&selId="+oId+"&flag=byIds&selExistStrs="+existUserStrs,
	   success: function(data){
	     eval("document.all."+divName).innerHTML = data;
	   }
	});	
}

/**
*	多选Module_Action_Ids
*	通过Module_Action_Ids选择模块的功能
*	
*	oId 		input的名字（该对象的值即为传入值也为输出值），用来存放选择后的Module_Action_Ids 以|分隔。
*	divName		div的名字，选择框所在的DIV
*	非弹出
*/
function selMultiModuleActionIdsByIdsInnerDiv(oId,divName){
	var mActionIds = eval("document.all."+oId).value;
	var existMActionIds = ""+mActionIds;
	existMActionIds = encodeURI(encodeURI(existMActionIds));
	$.ajax({
	   type: "POST",
	   url: getContextPath()+"select.action",
	   data: "m=select&url=select.action?m=selectMultiMAction&selExistStrs="+existMActionIds+"&selId="+oId,
	   success: function(data){
	     eval("document.all."+divName).innerHTML = data;
	   }
	});
}

/**
*	多选用户  如果 type=="one"则为单选用户
*	通过userIds选择用户
*	oName		input的名字（该对象的值即为传入值也为输出值），用来存放选择后的UserNames 以"，"分隔。
*	oId 		input的名字（该对象的值即为传入值也为输出值），用来存放选择后的UserIds 以|分隔。
*	弹出窗口
*/
function selMultiUser(oId,oName,type){
	var userIds = eval("document.all."+oId).value;
	var userNames = eval("document.all."+oName).value;
	if(userNames != null && userNames != "")
		userNames = userNames+"|";
	var existUserStrs = userIds+"$"+userNames.replace(/\,/g,"|");
	
	//存放参数，如果存在则直接放入数据，没有则建立。
	var input = document.getElementById("selExistStrsparent");
	if(input != null){
		input.value=existUserStrs;
	}else{
		input=document.createElement("input");
		input.type="hidden";
		input.id="selExistStrsparent";
		input.value=existUserStrs;
		document.body.appendChild(input);
	}	  
	if(type=="one"){
		ymPrompt.win(getContextPath()+"select.action?m=selectMultiUser&flag=alert&type=one",nDialogWidth2,nDialogHeight2,'选择单个用户',selMultiUserHandler,null,null,true,true,[['确定',oId+','+oName],['关闭','close']]);
	}else{
		ymPrompt.win(getContextPath()+"select.action?m=selectMultiUser&flag=alert",nDialogWidth2,nDialogHeight2,'选择多个用户',selMultiUserHandler,null,null,true,true,[['确定',oId+','+oName],['关闭','close']]);
	}
	
}
//变小
function selMultiUserSmall(oId,oName,type){
	var userIds = eval("document.all."+oId).value;
	var userNames = eval("document.all."+oName).value;
	if(userNames != null && userNames != "")
		userNames = userNames+"|";
	var existUserStrs = userIds+"$"+userNames.replace(/\,/g,"|");
	
	//存放参数，如果存在则直接放入数据，没有则建立。
	var input = document.getElementById("selExistStrsparent");
	if(input != null){
		input.value=existUserStrs;
	}else{
		input=document.createElement("input");
		input.type="hidden";
		input.id="selExistStrsparent";
		input.value=existUserStrs;
		document.body.appendChild(input);
	}	  
	if(type=="one"){
		ymPrompt.win(getContextPath()+"select.action?m=selectMultiUser&flag=alert&type=one",700,500,'选择单个用户',selMultiUserHandler2,null,null,true,true,[['确定',oId+','+oName],['关闭','close']]);
	}else{
		ymPrompt.win(getContextPath()+"select.action?m=selectMultiUser&flag=alert",nDialogWidth2,nDialogHeight2,'选择多个用户',selMultiUserHandler,null,null,true,true,[['确定',oId+','+oName],['关闭','close']]);
	}
	
}
function selMultiUserHandler(tp){
	if(tp != 'close'){
		var tps = tp.split(',');
		var input = document.getElementById("selExistStrsparent");
		var returnVal = input.value;
		if(returnVal == null || returnVal == "")
			close();
		if(returnVal == "$"){
			eval("document.all."+tps[0]).value = "";
			eval("document.all."+tps[1]).value = ""; 
		}else{
			
	 		var returnUserIds = returnVal.split("$")[0];
	 	//	prompt("Please enter your 888name",returnUserIds)
	 		var returnUserNames = returnVal.split("$")[1];// 这里查询数据库
	 		eval("document.all."+tps[0]).value = returnUserIds;
	 		eval("document.all."+tps[1]).value = returnUserNames.substring(0,returnUserNames.length-1).replace(/\|/g,","); 
		}
	
	}
	
}
function selMultiUserHandler2(tp){
	if(tp != 'close'){
		var tps = tp.split(',');
		var input = document.getElementById("selExistStrsparent");
		var returnVal = input.value;
		if(returnVal == null || returnVal == "")
			close();
		if(returnVal == "$"){
			eval("document.all."+tps[0]).value = "";
			eval("document.all."+tps[1]).value = ""; 
		}else{
			
	 		var returnUserIds = returnVal.split("$")[0];
	 	//	prompt("Please enter your 888name",returnUserIds)
	 		var returnUserNames = returnVal.split("$")[1];// 这里查询数据库
	 		eval("document.all."+tps[0]).value = returnUserIds;
	 		eval("document.all."+tps[1]).value = returnUserNames.substring(0,returnUserNames.length-1).replace(/\|/g,","); 
		}
		
		var id=dhxGrid.getCheckedRows(0);
	    var userid=document.getElementById("userid").value;
	    $.post("usbkey.action?m=fenpei&id="+id+"&userid="+userid,function(data){
	        if(data!=null&&data!=''){
	          alert(data)
	        }
	      
	        dhxGrid.clearAll();
	        dhxGrid.loadXML("usbkey.action?m=toFenpeixml", function(){});
	    
	    })
	}
	
}

function selMultiUserWithOrgName(oId,oName,type){
	var userIds = eval("document.all."+oId).value;
	var userNames = eval("document.all."+oName).value;
	if(userNames != null && userNames != "")
		userNames = userNames+"|";
	var existUserStrs = userIds+"$"+userNames.replace(/\,/g,"|");
	
	//存放参数，如果存在则直接放入数据，没有则建立。
	var input = document.getElementById("selExistStrsparent");
	if(input != null){
		input.value=existUserStrs;
		//alert("ddd"+existUserStrs)
	}else{
		input=document.createElement("input");
		input.type="hidden";
		input.id="selExistStrsparent";
		input.value=existUserStrs;
		document.body.appendChild(input);
	}	  
	if(type=="one"){
		ymPrompt.win(getContextPath()+"select.action?m=selectMultiUser&flag=alert&type=one",nDialogWidth2,nDialogHeight2,'选择单个用户',selMultiUserHandler,null,null,true,true,[['确定',oId+','+oName],['关闭','close']]);
	}else{
		ymPrompt.win(getContextPath()+"select.action?m=selectMultiUser&flag=alert",nDialogWidth2,nDialogHeight2,'选择多个用户',selMultiUserHandlerWithOrgName,null,null,true,true,[['确定',oId+','+oName],['关闭','close']]);
	}
	
}
function selMultiUserHandlerWithOrgName(tp){
	if(tp != 'close'){
		var tps = tp.split(',');
		var input = document.getElementById("selExistStrsparent");
		var returnVal = input.value;
		if(returnVal == null || returnVal == "")
			close();
		if(returnVal == "$"){
			eval("document.all."+tps[0]).value = "";
			eval("document.all."+tps[1]).value = ""; 
		}else{
			
	 		var returnUserIds = returnVal.split("$")[0];
	 		getOrgNameById(returnUserIds,tps[1]);	// 这里查询数据库
	 		var returnUserNames = returnVal.split("$")[1];
	 		eval("document.all."+tps[0]).value = returnUserIds;
	 		
		}
	}
	
}
/*
 * returnUserIds  使用 |分割的
 * */
function getOrgNameById(returnUserIds,tps){
	
	$.ajax({
	   type: "POST",
	   url: getContextPath()+"select.action",
	   data: "m=selectOrgNameById&userIdList="+returnUserIds+"",
	   success: function(data){
	   	  eval("document.all."+tps).value = data; 
	     //eval("document.all.userNamesWithOrg").value =data; 
	   }
	});
   
}
/**
*	多选模块
*	oName		input的名字（该对象的值即为传入值也为输出值），用来存放选择后的moduleNames 以"，"分隔。
*	oId 		input的名字（该对象的值即为传入值也为输出值），用来存放选择后的moduleIds 以|分隔。
*	弹出窗口
*/
function selMultiModule(oId,oName){
	var existModuleStrs = eval("document.all."+oId).value;
	//存放参数，如果存在则直接放入数据，没有则建立。
	var input = document.getElementById("selExistStrsparent");
	if(input != null){
		input.value=existModuleStrs;
	}else{
		input=document.createElement("input");
		input.type="hidden";
		input.id="selExistStrsparent";
		input.value=existModuleStrs;
		document.body.appendChild(input);
	}	  
	ymPrompt.win(getContextPath()+"select.action?m=selectMultiModule&flag=alert",nDialogWidth1,nDialogHeight1,'选择多个模块',selMultiModuleHandler,null,null,true,true,[['确定',oId+','+oName],['关闭','close']]);
}


function selMultiModuleHandler(tp){
	if(tp != 'close'){
		var tps = tp.split(',');
		var input = document.getElementById("selExistStrsparent");
		var returnVal = input.value;
		if(returnVal == null || returnVal == "")
			close();
		if(returnVal == "$"){
			eval("document.all."+tps[0]).value = "";
			eval("document.all."+tps[1]).value = ""; 
		}else{
	 		var returnModuleIds = returnVal.split("$")[0];
	 		var returnModuleNames = returnVal.split("$")[1];
	 		eval("document.all."+tps[0]).value = returnModuleIds;
	 		eval("document.all."+tps[1]).value = returnModuleNames.substring(0,returnModuleNames.length-1).replace(/\|/g,","); 
		}
	}
}

/**
*	选择部门   如果 type=="one"则为单选部门
*	oName		input的名字（该对象的值即为传入值也为输出值），用来存放选择后的OrgNames 以"，"分隔。
*	oId 		input的名字（该对象的值即为传入值也为输出值），用来存放选择后的OrgIds 以|分隔。
*	弹出窗口
*/
function selMultiOrg(oId,oName,type){
	var existOrgStrs = eval("document.all."+oId).value;
	//存放参数，如果存在则直接放入数据，没有则建立。
	var input = document.getElementById("selExistStrsparent");
	if(input != null){
		input.value=existOrgStrs;
	}else{
		input=document.createElement("input");
		input.type="hidden";
		input.id="selExistStrsparent";
		input.value=existOrgStrs;
		document.body.appendChild(input);
	}	  
	if(type=="one"){
		ymPrompt.win(getContextPath()+"select.action?m=selectMultiOrg&flag=alert&type=one",nDialogWidth1,nDialogHeight1,'选择单个部门',selMultiOrgHandler,null,null,true,true,[['确定',oId+','+oName],['关闭','close']]);
	}else{
		ymPrompt.win(getContextPath()+"select.action?m=selectMultiOrg&flag=alert",nDialogWidth1,nDialogHeight1,'选择多个部门',selMultiOrgHandler,null,null,true,true,[['确定',oId+','+oName],['关闭','close']]);
	}
}


function selMultiOrgHandler(tp){
	if(tp != 'close'){
		var tps = tp.split(',');
		var input = document.getElementById("selExistStrsparent");
		var returnVal = input.value;
		if(returnVal == null || returnVal == "")
			close();
		if(returnVal == "$"){
			eval("document.all."+tps[0]).value = "";
			eval("document.all."+tps[1]).value = ""; 
		}else{
	 		var returnOrgIds = returnVal.split("$")[0];
	 		
	 		var returnOrgNames = returnVal.split("$")[1];
	 		eval("document.all."+tps[0]).value = returnOrgIds;
	 		eval("document.all."+tps[1]).value = returnOrgNames.substring(0,returnOrgNames.length-1).replace(/\|/g,","); 
		}
	}
}

