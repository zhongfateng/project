<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="com.nbw.common.SysParameter"%>
<%
	SysParameter sp = new SysParameter();
	sp.loadFile();
	String pageSize = sp.getParameter("table.pageRowNums");
	
%>
<html>
	<head>
		<title>编码管理</title>
		<%@include file="/resources/common/res_all.inc"%>
	</head>
	<body>
		<table width="100%" height="500" border="0" cellpadding="0"
			cellspacing="0">
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
								编码信息维护
							</td>
							<td align="right" valign="middle">
							<!-- 
								<checktag:check action="save" moduleUrl="/syscodes.action">
									<input id="buttonA" name="buttonA" type="button"
										onClick="addRow();" value="添加" icon='icon-add' />
								</checktag:check>
								<checktag:check action="save" moduleUrl="/syscodes.action">
									<input id="buttonS" name="buttonS" type="button"
										onClick="saveData();" value="保存" icon='icon-save' />
								</checktag:check>
								<checktag:check action="save" moduleUrl="/syscodes.action">
									<input id="buttonD" name="buttonD" type="button"
										onClick="delData();" value="删除" icon='icon-delete' />
								</checktag:check>
							 -->
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
		<div id="pageTable">
			<div id="layoutGird2" class="sys_layout" style="float: inherit;height: 420px;"></div>
			<div id="pageArea" style="width: 100%; background-color: white;float: inherit;height: 30px;"></div>
		</div>
					
		<script>
var isDel = <%=isDel%>;
	function addRow(){
		var pid = dhxTree.getSelectedItemId();
		var values=[pid,"","","","","","",""];
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
    dhxLayout.cells("a").setText("编码树");
    dhxLayout.cells("b").setText("编码详细列表");
    
    //创建一个dhtmltree对象
    dhxTree = dhxLayout.cells("a").attachTree();
    dhxTree.setImagePath("<%=request.getContextPath()%>/resources/component/dhtmlxTree/imgs/csh_bluebooks/");
    dhxTree.setOnClickHandler(doTreeOnClick); 
    dhxTree.enableTreeImages(true);
	dhxTree.setXMLAutoLoading("syscodes.action?m=loadTreeXML");
    dhxTree.loadXML("syscodes.action?m=loadTreeXML");

    //创建一个dhtmlxgrid对象
 	dhxLayout.cells("b").attachObject("pageTable");
    dhxGrid = new dhtmlXGridObject("layoutGird2");
	dhxGrid.setImagePath("<%=request.getContextPath()%>/resources/component/dhtmlxGrid/imgs/");
	dhxGrid.setHeader("pid,编码,编码名称,编码说明,辅助参数1,辅助参数2,排序号,备注");
	dhxGrid.setColumnIds("parentId,code,name,describe,pam1,pam2,orderNum,remarks");
	dhxGrid.setInitWidthsP("0,10,15,20,15,15,10,15");
	dhxGrid.setColAlign("left,left,left,left,left,left,left,left");
	dhxGrid.setColTypes("ed,ed,ed,ed,ed,ed,ed,ed");
	dhxGrid.setColSorting("str,str,str,str,str,str,str,str");
	dhxGrid.selMultiRows = true;
	dhxGrid.setMultiLine(false);
	
	//分页
	dhxGrid.enablePaging(true,<%=pageSize%>, 1, "pageArea");
	dhxGrid.setPagingSkin("toolbar", "<%=gridSkin %>");
	
	dhxGrid.init();
	dhxGrid.setSkin("<%=gridSkin %>");
	dhxGrid.loadXML("syscodes.action?m=loadGridXML", function(){});
	dhxGrid.attachEvent("onEditCell", function(stage,rId,cInd,nValue,oValue){
		rowNum = dhxGrid.getRowIndex(rId)+1;
		if (stage == 2){
			switch (cInd) {
				case 1:
					return isLegalGridValueCode(nValue, rowNum, 1, 4, 32);
					break;
				case 2:
					return isNotOverlength(nValue, rowNum, 2, 2, 64);
					break;
				case 3:
					return isNotOverlength(nValue, rowNum, 3, 0, 128);
					break;
				case 4:
					return isNotOverlength(nValue, rowNum, 4, 0, 32);
					break;
				case 5:
					return isNotOverlength(nValue, rowNum, 5, 0, 32);
					break;
				case 6:
					return isNumeric(nValue, rowNum, 6);
					break;
				case 7:
					return isNotOverlength(nValue, rowNum, 7, 0, 128);
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
		dhxGrid.loadXML("syscodes.action?m=loadGridXML&parentId="+nodeId, function(){
		});
	}		
	function checkCode(value, id, ind) {
		if (!isLegalGridValueCode(value,dhxGrid.getRowIndex(id)+1, ind-1,4,32)){
			dhxGrid.setCellTextStyle(id, ind-1, "background-color:yellow;");
			return false;
		}
		return true;
	}		
	function notEmpty(value, id, ind) {
		if (!isNotOverlength(value,dhxGrid.getRowIndex(id)+1, ind-1,2,64)){
			dhxGrid.setCellTextStyle(id, ind-1, "background-color:yellow;");
			return false;
		}
		return true;
	}		
	function isnum(value, id, ind) {
		if (!isNumeric(value,dhxGrid.getRowIndex(id)+1, ind-1)){
			dhxGrid.setCellTextStyle(id, ind-1, "background-color:yellow;");
			return false;
		}
		return true;
	}
	myDataProcessor = new dataProcessor("syscodes.action?m=save");
	myDataProcessor.setUpdateMode("off");
	myDataProcessor.setTransactionMode("POST",false);
	myDataProcessor.enableDataNames(true);
	myDataProcessor.setVerificator(1, checkCode);
	myDataProcessor.setVerificator(2, notEmpty);
	myDataProcessor.setVerificator(6, isnum);
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



