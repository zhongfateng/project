<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="com.nbw.common.SysParameter"%>
<%
	SysParameter sp = new SysParameter();
	sp.loadFile();
	String pageSize = sp.getParameter("table.pageRowNums");
%>
<html>
<head>
<title>组织机构管理</title>
<%@include file="/resources/common/res_all.inc"%>
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
        <td>组织机构信息维护</td>
        <td align="right"  valign="middle">
		  <input name="buttonA" type="button" onClick="addRow();" value="添加" icon='icon-add'/>
		  <input name="buttonS" type="button" onClick="saveData();" value="保存" icon='icon-save'/>
		  <input name="buttonD" type="button" onClick="delData();" value="删除" icon='icon-delete'/>
        </td>
        <td width="5">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><div id="layoutGird" class="sys_layout"></div></td>
  </tr>
</table>
<div id="pageTable">
	<div id="layoutGird2" class="sys_layout" style="float: inherit;height: 205px;"></div>
	<div id="pageArea" style="width: 100%; background-color: white;float: inherit;height: 30px;"></div>
</div>
<script>
	//存放当前正在编辑的grid rowId
	var addgriid = "";
	function addRow(){
		var pid = dhxTree.getSelectedItemId();
		var pname = dhxTree.getSelectedItemText();
		var id = new UUID();
		dhxTabbar.cells("a1")._frame.contentWindow.resetForm(pid,pname,id);
		initTabbar("add");
	}
	
	function saveData(){
		var flag = "failure!";
		var cellid = dhxTabbar.getActiveTab();
		if(cellid=="a1"){
			//提交基本信息
			flag = dhxTabbar.cells(cellid)._frame.contentWindow.save();
		} else{
			//提交附属信息
			flag = dhxTabbar.cells(cellid)._frame.contentWindow.save();
			//附属信息提交后不要更新列表
			if(flag.length>6)
				flag = "fsuccess";
		}
	
		if (flag.length > 6){
			if ("success"==flag.substring(0,7)) {
				var pid = dhxTree.getSelectedItemId();
				//刷新当前grid
				addgriid = flag.substring(7);
				dhxTree.selectItem(pid,true,null);
				var id = flag.substring(7);
				var name = dhxTabbar.cells("a1")._frame.contentWindow.getName();
				var saveFlag = dhxTabbar.cells("a1")._frame.contentWindow.getSaveFlag();
				//ymPrompt.succeedInfo({title:"提示",message:"保存成功!"}); 不确定
				if("I"==saveFlag){
					dhxTree.setItemImage(pid,"folderOpen.gif","folderClosed.gif");
					dhxTree.insertNewChild(pid,id,name,doTreeOnClick,"iconText.gif","iconText.gif","iconText.gif");
					dhxTabbar.cells("a1")._frame.contentWindow.setSaveFlag("U");
				}else{
					dhxTree.setItemText(id,name,name);
				}
				ymPrompt.succeedInfo({title:"提示",message:"保存成功"});
			}else if("fsuccess"==flag){
				ymPrompt.succeedInfo({title:"提示",message:"保存成功"});
			}else{
				ymPrompt.errorInfo({title:"提示",message:flag});
			}
		}
	}
	
	function delData(){
		var ids = dhxGrid.getSelectedId();
		if (null!=ids) {
			function confirmDel(i){if('yes' != i) return false;
				$.ajax({
					type: "POST",
					url:"<%=request.getContextPath()%>/sysorganizations.action?m=delete&ids="+ids,
					async: false,
					success:function(data){
						var pid = dhxTree.getSelectedItemId();
						if (data=="success") {
							dhxGrid.deleteSelectedRows();
							var str= new Array();    
						  	str=ids.split(",");       
						    for (i=0;i<str.length ;i++ )    
						    {    
						        dhxTree.deleteItem(str[i],true);  
						    }
							if (dhxTree.hasChildren(pid)==0){
								dhxTree.updateItem(pid,dhxTree.getItemText(pid),"iconText.gif","iconText.gif","iconText.gif");
							}
							//如果删除后GRID里还有记录则选中第一条，没有则把编辑页面置成添加状态
							if(dhxGrid.getRowsNum()>0){
								dhxGrid.selectRow(0,true,false,true);
							}else{
								addRow();
							}
						}else{
							ymPrompt.errorInfo({title:"提示",message:"删除失败，要删除的机构下存在用户，请删除机构及其子机构下的所有用户后再试！"})
						}
					}
				});
				return true;
			}
			if (ymPrompt.confirmInfo({title:"提示",message:'确定删除选中的记录吗?',btn:[['是','yes'],['否','no']],handler:confirmDel}));
		}
	}
	//初始化该页面，在添加时把不该显示的TABLE页隐藏
	initTabbar = function(flag){
		if(flag=="add"){
			dhxTabbar.hideTab("a2");
		}else{
			dhxTabbar.showTab("a2");
		}
	}
	
	var dhxLayout;
	var dhxTree;

	//创建一个布局对象
    dhxLayout = new dhtmlXLayoutObject("layoutGird", "3L");
    dhxLayout.cells("a").setWidth("200");
    dhxLayout.cells("b").setHeight("260");
    dhxLayout.cells("a").setText("组织机构树");
    dhxLayout.cells("b").setText("组织机构列表");
    dhxLayout.cells("b").fixSize(false, true);
    
    //创建一个dhtmltree对象
    dhxTree = dhxLayout.cells("a").attachTree();
    dhxTree.setImagePath("<%=request.getContextPath()%>/resources/component/dhtmlxTree/imgs/csh_bluebooks/");
    dhxTree.setOnClickHandler(doTreeOnClick); 
	dhxTree.setXMLAutoLoading("sysorganizations.action?m=loadTreeXML");
    dhxTree.loadXML("sysorganizations.action?m=loadTreeXML");
    
    //创建一个dhtmlxgrid对象
 	dhxLayout.cells("b").attachObject("pageTable");
    dhxGrid = new dhtmlXGridObject("layoutGird2");
	dhxGrid.setImagePath("<%=request.getContextPath()%>/resources/component/dhtmlxGrid/imgs/");
	dhxGrid.setHeader("pid,机构编码,机构名称,机构简称,备注,排序号");
	dhxGrid.attachHeader("&nbsp;,#text_filter,#text_filter,#text_filter,#text_filter,&nbsp;");
	dhxGrid.setInitWidthsP("0,20,20,20,20,20");
	dhxGrid.setColAlign("left,left,left,left,left,left");
	dhxGrid.setColTypes("ro,ro,ro,ro,ro,ro");
	dhxGrid.setColSorting("str,str,str,str,str,str");
	dhxGrid.selMultiRows = true;
	dhxGrid.setMultiLine(false);
	//分页
	dhxGrid.enablePaging(true,<%=pageSize%>, 1, "pageArea");
	dhxGrid.setPagingSkin("toolbar", "<%=gridSkin %>");
	
	dhxGrid.init();
	dhxGrid.setSkin("<%=gridSkin %>");
	dhxGrid.loadXML("sysorganizations.action?m=loadGridXML", function(){
	});
	dhxGrid.attachEvent("onRowSelect", doOnRowSelected);

	
    //创建一个编辑页面对象
    dhxTabbar = dhxLayout.cells("c").attachTabbar();
	dhxTabbar.setSkin('<%=gridSkin %>');
	dhxTabbar.setImagePath("<%=request.getContextPath()%>/resources/component/dhtmlxTabbar/imgs/");
	dhxTabbar.addTab("a1", "基本信息", "100px");
	dhxTabbar.addTab("a2", "附属信息", "100px");
	//dhxTabbar.enableAutoSize(true, true);
	dhxTabbar.cells("a1").attachURL("<%=request.getContextPath()%>/sysorganizations.action?m=toFormPage");
	dhxTabbar.setTabActive("a1");
	dhxTabbar.attachEvent("onSelect", function(id,last_id){
		var orgid = dhxGrid.getSelectedRowId();
		if("a2"==id){
			dhxTabbar.cells("a2").attachURL("<%=request.getContextPath()%>/sysorganizations.action?m=toAttFormPage&id="+orgid);;
		}
		return true;
	});

	//树节点的点击事件
    function doTreeOnClick(nodeId){
		//dhxTree.openItem(nodeId);
		dhxGrid.clearAll();
		dhxGrid.loadXML("sysorganizations.action?m=loadGridXML&orgId="+nodeId, function(){
			if(addgriid != ""){
				dhxGrid.selectRowById(addgriid);
    			initTabbar();
				addgriid = "";
			}else if(dhxGrid.getRowsNum()>0){
				dhxGrid.selectRow(0,true,false,true);
			}
		});
	}	
	//表格记录选择点击事件
    function doOnRowSelected(id){
    	initTabbar();
		$.ajax({
			type: "POST",
			url:"<%=request.getContextPath()%>/sysorganizations.action?m=getFormData&id="+id,
			async: true,
			success:function(data){
				var jsonObject = eval("("+data+")");
				dhxTabbar.cells("a1")._frame.contentWindow.setValue(jsonObject);
				dhxTabbar.setTabActive("a1");
			}
		});
	}
	initTabbar("add");
</script> 
</body>
</html> 



