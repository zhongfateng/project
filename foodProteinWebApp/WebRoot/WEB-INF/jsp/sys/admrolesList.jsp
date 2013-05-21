<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<html>
<head>
<title>列表</title>
<script type="text/javascript">parent.document.getElementById('module').style.height=470;</script>
<%@include file="/resources/common/res_base.inc" %>
<%@ include file="/resources/common/res_common.inc"%>
<%@ include file="/resources/common/res_grid.inc" %>
<%@include file="/resources/common/res_layout.inc" %>
<%@include file="/resources/common/res_tree.inc" %>
</head>
<body>
<table width="100%" height="500" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td height="1"></td>
  </tr>
  <tr>
    <td class="td_head"><table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="4">&nbsp;</td>
        <td width="16"><img src="resources/skins/default/images/nav_title.gif" width="16" height="16"/></td>
        <td>系统角色维护</td>
        <td align="right"  valign="middle">
		  <%if (isAdd) {%>
		  <input name="buttonA" type="button" onClick="addRow();" value="添加" icon='icon-add'/>
		  <%} if (isAdd || isMod) {%>
		  <input name="buttonS" type="button" onClick="saveData();" value="保存" icon='icon-save'/>
		  <%} if (isDel) {%>
		  <input name="buttonD" type="button" onClick="delData();" value="删除" icon='icon-delete'/>
		  <%}%>
        </td>
        <td width="5">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="440"><div id="layoutGird" style="width:998px; height: 440px; aborder: #B5CDE4 1px solid; overflow: hidden;" ></div></td>
  </tr>
</table>
<script>
	function addRow(){
		var values=["","","","","1",""];
		var id = new UUID();
		mygrid.addRow(id,values);
		mygrid.selectRowById(id);
		dhxTreeU.setCheck("ROOT",false);
		dhxTreeM.setCheck("ROOT",false);
	}
	function saveData(){
		myDataProcessor.sendData();
		//保存权限
		$.ajax({
			type: "post",
			url: "<%=request.getContextPath()%>/admroles.action?m=saveFunc",
			beforeSend: function(XMLHttpRequest){
				//ShowLoading();
			},
			success: function(data, textStatus){
				if("success"==data){
				}else{
				   ymPrompt.errorInfo(data,null,null,"错误提示",null);
				}
			},
			data :{roleId:mygrid.getSelectedRowId(),
					userIds:dhxTreeU.getAllChecked(),
					moduleIds:dhxTreeM.getAllCheckedBranches()},
			complete: function(XMLHttpRequest, textStatus){
				//HideLoading();
			},
			error: function(){
				ymPrompt.errorInfo({title:"提示",message:"系统出现异常，请稍后再尝试!"});
			}
	   });
	}
	function delData(){
		function confirmDel(i){if('yes' != i) return false;
			mygrid.deleteSelectedItem();
			myDataProcessor.sendData();
		}
		if (ymPrompt.confirmInfo({title:"提示",message:'确定删除选中的记录吗?',btn:[['是','yes'],['否','no']],handler:confirmDel}));
	}

	//创建一个布局对象
    dhxLayout = new dhtmlXLayoutObject("layoutGird", "3T");
    dhxLayout.cells("a").hideHeader();
    dhxLayout.cells("b").hideHeader();
    dhxLayout.cells("c").hideHeader();
	dhxLayout.cells("a").setHeight("150");
    //创建一个dhtmlxgrid对象
    mygrid = dhxLayout.cells("a").attachGrid();
	mygrid.setImagePath("<%=request.getContextPath()%>/resources/component/dhtmlxGrid/imgs/");
	mygrid.setHeader("sysCodesId,name,describe,sysRole,validFlag,remarks");
	mygrid.setInitWidths("100,100,100,100,100,100");
	mygrid.setColAlign("left,left,left,left,left,left");
	mygrid.setColTypes("ed,ed,ed,ed,ed,ed");
	mygrid.setColSorting("str,str,str,str,str,str");
	mygrid.selMultiRows = true;
    mygrid.setPagingSkin("toolbar", "dhx_skyblue");
	mygrid.init();
	mygrid.setSkin("dhx_skyblue");
	mygrid.loadXML("sysroles.action?m=loadGridXML", function(){});
	mygrid.attachEvent("onEditCell", function(stage,rId,cInd,nValue,oValue){
		rowNum = mygrid.getRowIndex(rId)+1;
		if (stage == 2){
			switch (cInd) {
				case -1:
				case 0:
					if(isNotOverlength(nValue, rowNum, cInd + 1, 0, 32)==false)
					break;
					return true;					
				case 1:
					if(isNotOverlength(nValue, rowNum, cInd + 1, 0, 32)==false)
					break;
					return true;					
				case 2:
					if(isNotOverlength(nValue, rowNum, cInd + 1, 0, 32)==false)
					break;
					return true;					
				case 3:
					if(isNotOverlength(nValue, rowNum, cInd + 1, 0, 32)==false)
					break;
					return true;					
				case 4:
					if(isNotOverlength(nValue, rowNum, cInd + 1, 0, 32)==false)
					break;
					return true;					
				case 5:
					if(isNotOverlength(nValue, rowNum, cInd + 1, 0, 32)==false)
					break;
					return true;					
				default:
					return true;
					break;
			}
		}
	});
	function notEmpty(value, id, ind) {
		if (value == "")
			mygrid.setCellTextStyle(id, ind-1, "background-color:yellow;");
		return isNotEmpty(value, mygrid.getRowIndex(id)+1, ind);
	}
    //创建一个dhtmltree模块对象
    dhxTreeM = dhxLayout.cells("b").attachTree();
    dhxTreeM.setImagePath("<%=request.getContextPath()%>/resources/component/dhtmlxTree/imgs/csh_bluebooks/");
    dhxTreeM.enableCheckBoxes(1);
    dhxTreeM.enableThreeStateCheckboxes(true);
    dhxTreeM.loadXML("sysroles.action?m=loadFuncTreeXML");
    //创建一个dhtmltree人员对象
    dhxTreeU = dhxLayout.cells("c").attachTree();
    dhxTreeU.setImagePath("<%=request.getContextPath()%>/resources/component/dhtmlxTree/imgs/csh_bluebooks/");
    dhxTreeU.enableCheckBoxes(1);
    dhxTreeU.enableThreeStateCheckboxes(true);
    dhxTreeU.loadXML("sysroles.action?m=singleLoadUserTreeXML&noAdmin=1");

	dhxTreeM.attachEvent("onCheck", function(id,state){
		if("FUNCTIONS"==id.substring(0,9)){
			var ids = dhxTreeM.getSubItems(dhxTreeM.getParentId(id));
			var idsArr = ids.split(",");
			if ("A"==id.substring(10,11)) {
				if (0==state) {//取消查询权限时同时取消其它权限
					for (i=0;i<idsArr.length;i++){
						dhxTreeM.setCheck(idsArr[i],state);
					}
				}
			}else{
				if (1==state) {//选择某个权限时判断是否选择查询权限
					var flag = 0;
					for (i=0;i<idsArr.length;i++){
						if ("A"==idsArr[i].substring(10,11)) {
							dhxTreeM.setCheck(idsArr[i],1);
							break;
						}
					}
				}
			}
		}
	});

	mygrid.attachEvent("onRowSelect", function(id,ind){
		dhxTreeU.setCheck("ROOT",false);
		dhxTreeM.setCheck("ROOT",false);
		$.ajax({
			type: "POST",
			url:"<%=request.getContextPath()%>/admroles.action?m=loadSelectUsersXML&id="+id,
			async: true,
			success:function(data){
				if ("failure"!=data){
					var jsonArray = eval("("+data+")");
					for(i=0;i<jsonArray.length;i++){
						var itemId = "ADMUSERS_" + jsonArray[i];
						dhxTreeU.setCheck(itemId,true);
					}
				}
			}
		});
		$.ajax({
			type: "POST",
			url:"<%=request.getContextPath()%>/admroles.action?m=loadSelectModulesXML&id="+id,
			async: true,
			success:function(data){
				if ("failure"!=data){
					var jsonArray = eval("("+data+")");
					for(i=0;i<jsonArray.length;i++){
						var itemId = jsonArray[i];
						if(itemId.indexOf("1_") > -1){
							var ids = dhxTreeM.getAllSubItems(itemId);
							if (7 < ids.length) {
								var idsArr = ids.split(",");
								for (j=0;j<idsArr.length;j++){
									var mid = idsArr[j];
									if ("A"==idsArr[j].substring(10,11)) {
										dhxTreeM.setCheck(mid,true);
										break;
									}
								}
							}
						}else{
							var mid = "FUNCTIONS_" + itemId;
							dhxTreeM.setCheck(mid,true);
						}
					}
				}
			}
		});
	});

	mygrid.attachEvent("onEditCell", function(stage,rId,cInd,nValue,oValue){
		rowNum = mygrid.getRowIndex(rId)+1;
		if (stage == 2){
			switch (cInd) {
				case 0:
					if (!isNotOverlength(nValue, rowNum, cInd + 1, 4, 32)) {
						return false;
					} else {
						return isNotRepeatInGrid(mygrid, nValue, rowNum, cInd + 1, 1);
					}
					break;
				case 1:
					if (!isNotOverlength(nValue, rowNum, cInd + 1, 4, 32)) {
						return false;
					} else {
						return isNotRepeatInGrid(mygrid, nValue, rowNum, cInd + 1, 1);
					}
					break;
				case 3:
					return isNotOverlength(nValue, rowNum, 3, 0, 128);
					break;
				case 5:
					return isNotOverlength(nValue, rowNum, 4, 0, 128);
					break;
				default:
					return true;
					break;
			}
		}
	});
	function notEmpty(value, id, ind) {
		if (value == "")
			mygrid.setCellTextStyle(id, ind-1, "background-color:yellow;");
		return isNotEmpty(value, mygrid.getRowIndex(id)+1, ind);
	}
	myDataProcessor = new dataProcessor("admroles.action?m=save");
	myDataProcessor.setUpdateMode("off");
	myDataProcessor.setTransactionMode("POST",false);

	myDataProcessor.setVerificator(0, notEmpty);
	myDataProcessor.setVerificator(1, notEmpty);
	myDataProcessor.attachEvent("onRowMark", function(id) {
    	if (this.is_invalid(id) == "invalid")
        	return false;
	    return true;
	});

	//判断增加出错
 	myDataProcessor.defineAction("insert",myErrorHandler);
	function myErrorHandler(obj){
		var meg = obj.firstChild.nodeValue;
		if (meg==1) {
			return true;
		} else {
			ymPrompt.errorInfo({title:"提示",message:meg});
			myDataProcessor.stopOnError = true;
	   	 	return false;
		}
    }
    //判断修改出错
    myDataProcessor.defineAction("update",myErrorHandler);
	function myErrorHandler(obj){
		var meg = obj.firstChild.nodeValue;
		if (meg==1) {
			return true;
		} else {
			ymPrompt.errorInfo({title:"提示",message:meg});
			myDataProcessor.stopOnError = true;
	   	 	return false;
		}
    }

	myDataProcessor.init(mygrid);
</script> 
</body>
</html>

