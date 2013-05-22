<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="com.nbw.common.SysParameter"%>
<%
	SysParameter sp = new SysParameter();
	sp.loadFile();
	String pageSize = sp.getParameter("table.pageRowNums");
	
%>
<html>
<head>
<title>用户管理</title>
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
        <td>用户信息维护</td>
        <td align="right"  valign="middle">
		  <input id="buttonA" name="buttonA" type="button" onClick="addRow();" value="添加" icon='icon-add'/>
		  <input id="buttonS" name="buttonS" type="button" onClick="saveData();" value="保存" icon='icon-save'/>
		  <input id="buttonD" name="buttonD" type="button" onClick="delData();" value="删除" icon='icon-delete'/>
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
	<div id="layoutGird2" class="sys_layout" style="float: inherit;height: 220px;"></div>
	<div id="pageArea" style="width: 100%; background-color: white;float: inherit;height: 30px;"></div>
</div>
<script>
	//初始化该页面，在添加时把不该显示的TABLE页隐藏
	initTabbar = function(flag){
		if(flag=="add"){
			dhxTabbar.hideTab("a2");
			dhxTabbar.hideTab("a3");
		}else{
			dhxTabbar.showTab("a2");
			dhxTabbar.showTab("a3");
		}
	}
	//存放当前正在编辑的grid rowId
	var addgriid = "";
	function addRow(){
		var pid = dhxTree.getSelectedItemId();
		var pname = dhxTree.getSelectedItemText();
		dhxTabbar.cells("a1")._frame.contentWindow.resetForm(pid,pname);
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
			if("fsuccess"==flag){
				ymPrompt.succeedInfo({title:"提示",message:"保存成功"});
			}else if ("success"==flag.substring(0,7)) {
				var pid = dhxTabbar.cells("a1")._frame.contentWindow.getOrgId();
				//刷新当前grid
				addgriid = flag.substring(7);
				dhxTree.selectItem(pid,true,null);
				ymPrompt.succeedInfo({title:"提示",message:"保存成功"});
			}else {
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
					url:"<%=request.getContextPath()%>/sysusers.action?m=delete&ids="+ids,
					async: false,
					success:function(data){
						if (data=="success") {
							dhxGrid.deleteSelectedRows();
							//如果删除后GRID里还有记录则选中第一条，没有则把编辑页面置成添加状态
							if(dhxGrid.getRowsNum()>0){
								dhxGrid.selectRow(0,true,false,true);
							}else{
								addRow();
							}
						}
					}
				});
				return true;
			}
			if (ymPrompt.confirmInfo({title:"提示",message:'确定删除选中的记录吗?',btn:[['是','yes'],['否','no']],handler:confirmDel}));
		}
	}
	var dhxLayout;
	var dhxTree;
	var dhxGrid ;

	//创建一个布局对象
    dhxLayout = new dhtmlXLayoutObject("layoutGird", "3L");
    dhxLayout.cells("a").setWidth("200");
    dhxLayout.cells("b").setHeight("275");
    dhxLayout.cells("a").setText("组织机构树");
    dhxLayout.cells("b").setText("用户列表");
    dhxLayout.cells("c").setText("用户详细信息");
    dhxLayout.cells("b").fixSize(false, true);
    //创建一个编辑页面对象
    dhxTabbar = dhxLayout.cells("c").attachTabbar();
	dhxTabbar.setSkin('<%=gridSkin %>');
	dhxTabbar.setImagePath("<%=request.getContextPath()%>/resources/component/dhtmlxTabbar/imgs/");
	dhxTabbar.addTab("a1", "基本信息", "100px");
	dhxTabbar.addTab("a2", "附属信息", "100px");
	dhxTabbar.addTab("a3", "角色选择", "100px");
	//dhxTabbar.enableAutoSize(true, true);
	dhxTabbar.cells("a1").attachURL("<%=request.getContextPath()%>/sysusers.action?m=toFormPage");
	dhxTabbar.setTabActive("a1");
	dhxTabbar.attachEvent("onSelect", function(id,last_id){
		var userid = dhxGrid.getSelectedRowId();
		if("a2"==id){
			dhxTabbar.cells("a2").attachURL("<%=request.getContextPath()%>/sysusers.action?m=toAttFormPage&id="+userid);;
		}else if("a3"==id){
			dhxTabbar.cells("a3").attachURL("<%=request.getContextPath()%>/sysusers.action?m=toAddRoles&userId="+userid);
		}
		return true;
	});
    
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
	dhxGrid.setHeader("sysOrganizationsId,帐号,名称,所属机构,备注,排序号");
	dhxGrid.attachHeader("&nbsp;,#text_filter,#text_filter,#select_filter,#text_filter,&nbsp;");
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
	dhxGrid.loadXML("sysusers.action?m=loadGridXML", function(){
	});
	dhxGrid.attachEvent("onRowSelect", doOnRowSelected);
	
	//树节点的点击事件
    function doTreeOnClick(nodeId){
		//dhxTree.openItem(nodeId);
		dhxGrid.clearAll();
		dhxGrid.loadXML("sysusers.action?m=loadGridXML&orgId="+nodeId, function(){
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
			url:"<%=request.getContextPath()%>/sysusers.action?m=getFormData&id="+id,
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



