<%@ page contentType="text/html; charset=utf-8" language="java"%>
<html>
	<head>
		<title>选择多个部门</title>
		<%@ include file="/resources/common/res_all.inc"%>
	</HEAD>
	<BODY topmargin="0" leftmargin="0">
		<table>
			<tr>
				<td valign="top">
					<div id="treeObject"></div>
				</td>
			</tr>
		</table>
		<script>
    tree=new dhtmlXTreeObject(document.getElementById('treeObject'),"100%","100%",0); 
    tree.setImagePath("<%=request.getContextPath()%>/resources/component/dhtmlxTree/imgs/csh_bluebooks/"); 
    tree.loadXML("sysorganizations.action?m=loadAllTreeXML");
    tree.attachEvent("onSelect", function(id){
        var url=parent.document.getElementById("rightUrl").value;//rightUrl 是 右侧页面的隐藏信息
         parent.document.getElementById("rightFrame").src =url+"&orgId="+id;
         parent.document.getElementById("orgId").value=id;//设置右侧页面orgId
        
    });
</script>
	</body>
</html>
