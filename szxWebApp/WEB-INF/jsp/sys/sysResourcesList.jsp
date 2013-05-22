<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<html>
<head>
<title>资源管理</title>
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
				<td class="td_head">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="4">
								&nbsp;
							</td>
							<td width="16">
								<img src="resources/skins/default/images/nav_title.gif"
									width="16" height="16" />
							</td>
							<td>
								资源维护
							</td>
							<td align="right" valign="middle">
								<input id="buttonA" name="buttonA" type="button"
									onClick="addRow();" value="添加" icon='icon-add' />
								<input id="buttonS" name="buttonS" type="button"
									onClick="saveData();" value="保存" icon='icon-save' />
								<input id="buttonD" name="buttonD" type="button"
									onClick="delData();" value="删除" icon='icon-delete' />
							</td>
							<td width="5">
								&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<div id="layoutGird" class="sys_layout"></div>
				</td>
			</tr>
		</table>
		<script>
var isDel = <%=isDel%>;
	function addRow(){
		var pid = dhxTree.getSelectedItemId();
		var values=[pid,"","",""];
		var id = new UUID();
		dhxGrid.addRow(id,values);
		dhxGrid.selectRowById(id);
	}
	function saveData(){
		myDataProcessor.sendData();
	}
	function delData(){
		function confirmDel(i){if('yes' != i) return false;
			dhxGrid.deleteSelectedItem();
			myDataProcessor.sendData();
		}
		if (ymPrompt.confirmInfo({title:"提示",message:'确定删除选中的记录吗?',btn:[['是','yes'],['否','no']],handler:confirmDel}));
	}
	var dhxLayout;
	var dhxTree;
	var dhxGrid ;

	//创建一个布局对象
    dhxLayout = new dhtmlXLayoutObject("layoutGird", "2u");
    dhxLayout.cells("a").setWidth("200");
    dhxLayout.cells("a").setText("资源树");
    dhxLayout.cells("b").setText("资源详细列表");
    //创建一个dhtmltree对象
    dhxTree = dhxLayout.cells("a").attachTree();
    dhxTree.setImagePath("<%=request.getContextPath()%>/resources/component/dhtmlxTree/imgs/csh_bluebooks/");
    dhxTree.setOnClickHandler(doTreeOnClick); 
    dhxTree.enableTreeImages(true);
	dhxTree.setXMLAutoLoading("sysres.action?m=loadTreeXML");
    dhxTree.loadXML("sysres.action?m=loadTreeXML");

    //创建一个dhtmlxgrid对象
    dhxGrid = dhxLayout.cells("b").attachGrid();
	dhxGrid.setImagePath("<%=request.getContextPath()%>/resources/component/dhtmlxGrid/imgs/");
	dhxGrid.setHeader("pid,名称,路径,说明");
	dhxGrid.setColumnIds("parentId,name,url,remarks");
	dhxGrid.setInitWidthsP("0,20,30,50");
	dhxGrid.setColAlign("left,left,left,left");
	dhxGrid.setColTypes("ed,ed,ed,ed");
	dhxGrid.setColSorting("str,str,str,str");
	dhxGrid.selMultiRows = true;
	dhxGrid.setMultiLine(false);
	dhxGrid.init();
	dhxGrid.setSkin("<%=gridSkin %>");
	dhxGrid.loadXML("sysres.action?m=loadGridXML", function(){});
	dhxGrid.attachEvent("onEditCell", function(stage,rId,cInd,nValue,oValue){
		rowNum = dhxGrid.getRowIndex(rId)+1;
		if (stage == 2){
			switch (cInd) {
				case 1:
					return isNotOverlength(nValue, rowNum, 1, 2, 64);
					break;
				case 2:
					return isLegalGridURL(nValue, rowNum, 2, 1, 256);
					break;
				case 3:
					return isNotOverlength(nValue, rowNum, 3, 0, 128);
					break;					
				default:
					return true;
					break;
			}
		}
	});
	
	//树节点的点击事件
    function doTreeOnClick(nodeId){
		//dhxTree.openItem(nodeId);
		dhxGrid.clearAll();
		dhxGrid.loadXML("sysres.action?m=loadGridXML&parentId="+nodeId, function(){
		});
	}			
	function checkName(value, id, ind) {
		if (!isNotOverlength(value,dhxGrid.getRowIndex(id)+1, ind-1,2,64)){
			dhxGrid.setCellTextStyle(id, ind-1, "background-color:yellow;");
			return false;
		}
		return true;
	}		
	function checkUrl(value, id, ind) {
		if (!isLegalGridURL(value,dhxGrid.getRowIndex(id)+1, ind-1,1,256)){
			dhxGrid.setCellTextStyle(id, ind-1, "background-color:yellow;");
			return false;
		}
		return true;
	}
	myDataProcessor = new dataProcessor("sysres.action?m=save");
	myDataProcessor.setUpdateMode("off");
	myDataProcessor.setTransactionMode("POST",false);
	myDataProcessor.enableDataNames(true);
	myDataProcessor.setVerificator(1, checkName);
	myDataProcessor.setVerificator(2, checkUrl);
	myDataProcessor.attachEvent("onRowMark", function(id) {
    	if (this.is_invalid(id) == "invalid")
        	return false;
	    return true;
	});
	myDataProcessor.attachEvent("onAfterUpdate",function(sid,action,tid,xml_node){
		var pid = dhxTree.getSelectedItemId();
		if("insert"==action){
			var name = dhxGrid.cells(sid,2).getValue();
			dhxTree.setItemImage(pid,"folderOpen.gif","folderClosed.gif");
			dhxTree.insertNewChild(pid,sid,name,doTreeOnClick,"iconText.gif","iconText.gif","iconText.gif");
		}else if("update"==action){
			var name = dhxGrid.cells(sid,2).getValue();
			dhxTree.setItemText(sid,name,name);
		}else if("delete"==action){
			dhxTree.deleteItem(sid,true);
			if (dhxTree.hasChildren(pid)==0){
				dhxTree.updateItem(pid,dhxTree.getItemText(pid),"iconText.gif","iconText.gif","iconText.gif");
			}
		}
		return true;
	})
	//判断不同用户重复增加或修改
 	myDataProcessor.defineAction("insert",myErrorHandler);
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
	myDataProcessor.init(dhxGrid);
</script> 
</body>
</html>



