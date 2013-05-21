<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="java.util.*"%>
<%@ page import="com.nbw.common.SysParameter"%>
<%@ page import="com.nbw.sys.domain.SysCodes"%>
<%
	SysParameter sp = new SysParameter();
	sp.loadFile();
	String pageSize = sp.getParameter("table.pageRowNums");
%>
<html>
	<head>
		<title>角色管理</title>
		<%@ include file="/resources/common/res_all.inc"%>
	</head>
	<body>
		<table width="100%" height="500" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="4"></td>
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
								角色管理
							</td>
							<td align="right" valign="middle">
								<input name="buttonA" type="button" onClick="addRow();"
									value="添加" icon='icon-add' />
								<input name="buttonS" type="button" onClick="saveData();"
									value="保存" icon='icon-save' />
								<input name="buttonD" type="button" onClick="delData();"
									value="删除" icon='icon-delete' />
							</td>
							<td width="5">
								&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="410">
					<div id="gridbox" class="sys_layout"></div>
				</td>
			</tr>
		</table>
		<table id="projectGrid" border="0" style="width: 100% px;"
			align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td valign="top">
					<div id="mygrid111"
						style="width: 100% px; height: 162px; background-color: white;"></div>
				</td>
			</tr>
			<tr>
				<td style="height: 120px;">
					<div id="pageArea" style="width: 100%; background-color: white;"></div>
				</td>
			</tr>
		</table>
	<script type="text/javascript">
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
	function addRow(){
		dhxTabbar.cells("a1")._frame.contentWindow.resetForm();
		initTabbar("add");
	}
	
	function saveData(){
		var flag = "failure!";
		var cellid = dhxTabbar.getActiveTab();
		if(cellid=="a1"){
			//提交基本信息
			flag = dhxTabbar.cells(cellid)._frame.contentWindow.save();
		} else{
			//提交其他授权信息
			flag = dhxTabbar.cells(cellid)._frame.contentWindow.save();
			//其他授权信息提交后不要更新列表
			if(flag.length>6)
				flag = "fsuccess";
		}
		
		if (flag.length > 6){
			if ("success"==flag.substring(0,7)) {
				//刷新当前grid
				var addgriid = flag.substring(7);
				mygrid.clearAll();
				mygrid.loadXML("sysroles.action?m=loadGridXML", function(){
					mygrid.selectRowById(addgriid);
					addgriid = "";
				});
				ymPrompt.succeedInfo({title:"提示",message:"保存成功"});
			}else if("fsuccess"==flag){
				ymPrompt.succeedInfo({title:"提示",message:"保存成功"});
			}else{
				ymPrompt.errorInfo({title:"提示",message:flag});
			}
		}
	}
	
	function delData(){
		var ids = mygrid.getSelectedId();
		if (null!=ids) {
			function confirmDel(i){if('yes' != i) return false;
				$.ajax({
					type: "POST",
					url:"<%=request.getContextPath()%>/sysroles.action?m=delete&ids="+ids,
					async: false,
					success:function(data){
						if (data=="success") {
							mygrid.deleteSelectedRows();
							//如果删除后GRID里还有记录则选中第一条，没有则把编辑页面置成添加状态
							if(mygrid.getRowsNum()>0){
								mygrid.selectRow(0,true,false,true);
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
	dhxLayout = new dhtmlXLayoutObject("gridbox", "2E");
    dhxLayout.cells("a").setWidth("200");
    dhxLayout.cells("a").setText("角色列表");
    dhxLayout.cells("b").hideHeader();  
    dhxLayout.cells("b").fixSize(false, true);
    //创建一个编辑页面对象
    dhxTabbar = dhxLayout.cells("b").attachTabbar();
	dhxTabbar.setSkin('<%=gridSkin %>');
	dhxTabbar.setImagePath("<%=request.getContextPath()%>/resources/component/dhtmlxTabbar/imgs/");
	dhxTabbar.addTab("a1", "基本信息", "100px");
	dhxTabbar.addTab("a2", "用户授权", "100px");
	dhxTabbar.addTab("a3", "功能授权", "100px");
	dhxTabbar.cells("a1").attachURL("<%=request.getContextPath()%>/sysroles.action?m=toFormPage");
	dhxTabbar.setTabActive("a1");
	dhxTabbar.attachEvent("onSelect", function(id,last_id){
		var roleId = mygrid.getSelectedRowId();
		if("a2"==id){
			dhxTabbar.cells("a2").attachURL("<%=request.getContextPath()%>/sysroles.action?m=toAddUserForm&roleId="+roleId);
		}
		if("a3"==id){
			dhxTabbar.cells("a3").attachURL("<%=request.getContextPath()%>/sysroles.action?m=toAddActionForm&roleId="+roleId);
		}
		return true;
	});	
    
    mygrid = new dhtmlXGridObject('mygrid111');
	mygrid.setImagePath("<%=request.getContextPath()%>/resources/component/dhtmlxGrid/imgs/");
	mygrid.setHeader("角色类型,角色名称,角色说明,备注,是否为系统角色");
	mygrid.attachHeader("#select_filter,#text_filter,#text_filter,#text_filter,#select_filter");
	mygrid.setInitWidthsP("16,20,24,24,16");
	mygrid.setColAlign("left,left,left,left,left");
	mygrid.setColTypes("coro,ro,ro,ro,coro");
	mygrid.setColSorting("str,str,str,str,str");
	mygrid.selMultiRows = true;
	//分页
	mygrid.enablePaging(true,<%=pageSize%>, 1, "pageArea");
    mygrid.setPagingSkin("toolbar", "<%=gridSkin %>");
	mygrid.init();
	mygrid.setSkin("<%=gridSkin %>");
	mygrid.loadXML("sysroles.action?m=loadGridXML", function(){});
	mygrid.attachEvent("onRowSelect", doOnRowSelected);
	dhxLayout.cells("a").attachObject("projectGrid");
	//表格记录选择点击事件
    function doOnRowSelected(id){
    	initTabbar();
		$.ajax({
			type: "POST",
			url:"<%=request.getContextPath()%>/sysroles.action?m=getFormData&id="+id,
			async: true,
			success:function(data){
				var jsonObject = eval("("+data+")");
				dhxTabbar.cells("a1")._frame.contentWindow.setValue(jsonObject);
				dhxTabbar.setTabActive("a1");
			}
		});
	}
	initTabbar("add");
	
	mygrid.getCombo(4).put('1','是');
	mygrid.getCombo(4).put('0','否');
	defaultIsSys = '否';
	
	<%
		List<SysCodes> roleTypes = (List<SysCodes>)request.getAttribute("roleTypes");
		if(roleTypes.size()>0){
	%>
			defaultRoleType='<%=roleTypes.get(0).getCode()%>'
	<%		
		}
		for(int i=0;i<roleTypes.size();i++){
	%>
			mygrid.getCombo(0).put('<%=roleTypes.get(i).getCode()%>','<%=roleTypes.get(i).getName()%>');
	<%		
		}
	%>	
</script>
	</body>
</html>

