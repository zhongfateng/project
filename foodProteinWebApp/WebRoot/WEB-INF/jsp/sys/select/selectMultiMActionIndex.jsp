<%@ page contentType="text/html; charset=utf-8" language="java"%>
<html>
	<head>
		<title>选择用户</title>
		<%@ include file="/resources/common/res_all.inc"%>
		<%
			String selId = request.getParameter("selId");
			String rightUrl = "/select.action?m=getModuleActions&selId="
					+ selId;
		%>
	</head>
	<body topmargin="0" leftmargin="0">
		<input type="hidden" id="selCheckedIds" value="" />
		<div id="layoutGird" class="sys_layout"></div>
		<script type="text/javascript">
			document.all.selCheckedIds.value = parent.document.getElementById("selExistStrsparent").value;
			
			var dhxLayout;
			var dhxTree;
			var dhxGrid ;
		
			//创建一个布局对象
		    dhxLayout = new dhtmlXLayoutObject("layoutGird", "2U");
		    dhxLayout.cells("a").setWidth("200");
		    dhxLayout.cells("a").setText("模块树");
		    dhxLayout.cells("b").setText("功能列表");
		    //创建一个dhtmltree对象
		    dhxTree = dhxLayout.cells("a").attachTree();
		    dhxTree.setImagePath("<%=request.getContextPath()%>/resources/component/dhtmlxTree/imgs/csh_bluebooks/");
		    dhxTree.setOnClickHandler(doTreeOnClick); 
			dhxTree.setXMLAutoLoading("sysmodules.action?m=loadTreeXML");
		    dhxTree.loadXML("sysmodules.action?m=loadTreeXML");
			dhxLayout.cells("b").attachURL("<%=request.getContextPath() + rightUrl%>");
		    //树节点的点击事件
		    function doTreeOnClick(nodeId){
				//dhxTree.openItem(nodeId);
				dhxLayout.cells("b").attachURL("<%=request.getContextPath() + rightUrl%>&moduleId="+nodeId);
			}		
		</script>
	</body>
</html>
