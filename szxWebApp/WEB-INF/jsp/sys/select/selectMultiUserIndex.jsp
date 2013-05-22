<%@ page contentType="text/html; charset=utf-8" language="java"%>
<html>
<head>
<title>选择用户</title>
<%@ include file="/resources/common/res_all.inc"%>
<%
	String selId = request.getParameter("selId");
	String selName = request.getParameter("selName");
	String flag = request.getParameter("flag");
	String rightUrl = "/select.action?m=getUsers&selId="+selId+"&selName="+selName+"&flag="+flag;
%>
</head>
<body topmargin="0" leftmargin="0">
<input type="hidden" id="selCheckedIds" value="" />
<div id="layoutGird" class="sys_layout"></div>

<script type="text/javascript">
	var dhxLayout;
	var dhxTree;
	var dhxGrid ;
	document.getElementById("selCheckedIds").value = parent.document.getElementById("selExistStrsparent").value
	//创建一个布局对象
    dhxLayout = new dhtmlXLayoutObject("layoutGird", "2U");
    dhxLayout.cells("a").setWidth("200");
    dhxLayout.cells("a").setText("组织机构树");
    dhxLayout.cells("b").setText("用户列表");
    //创建一个dhtmltree对象
    dhxTree = dhxLayout.cells("a").attachTree();
    dhxTree.setImagePath("<%=request.getContextPath()%>/resources/component/dhtmlxTree/imgs/csh_bluebooks/");
    dhxTree.setOnClickHandler(doTreeOnClick); 
	dhxTree.setXMLAutoLoading("sysorganizations.action?m=loadTreeXML");
    dhxTree.loadXML("sysorganizations.action?m=loadTreeXML");

    //创建一个编辑页面对象
    dhxLayout.cells("b").attachURL("<%=request.getContextPath()+rightUrl%>");

	//树节点的点击事件
    function doTreeOnClick(nodeId){
		//dhxTree.openItem(nodeId);
		dhxLayout.cells("b").attachURL("<%=request.getContextPath()+rightUrl%>&orgId="+nodeId);
	}		
	
</script>
</body>
</html>
