<%@ page contentType="text/html; charset=utf-8" language="java"%>
<html>
<head>
<title>选择多个部门</title>
<%@ include file="/resources/common/res_all.inc"%>
<script type="text/javascript">
	function _submit(){
		var returnIds = "";
		var returnNames = "";
		var ids = tree.getAllChecked().split(",");
		if(ids != ""){
			for(var i=0;i<ids.length;i++){
				returnNames+=tree.getItemText(ids[i])+",";
				returnIds+=ids[i]+"|";
			}
		}
		parent.document.getElementById("selExistStrsparent").value = returnIds+"$"+returnNames;
    }
</script>
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
	var selCheckedIds = parent.document.getElementById("selExistStrsparent").value;
	var sels = selCheckedIds.split("|");
    tree=new dhtmlXTreeObject(document.getElementById('treeObject'),"100%","100%",0); 
    tree.setImagePath("<%=request.getContextPath()%>/resources/component/dhtmlxTree/imgs/csh_bluebooks/"); 
    tree.enableCheckBoxes(1);
    tree.enableThreeStateCheckboxes(true);
    tree.loadXML("sysorganizations.action?m=loadAllTreeXML");
    tree.attachEvent("onCheck", function(id,state){_submit();});
    tree.attachEvent("onXLE", function(tree,id){
    	for(var i = 0;i<sels.length;i++){
    		tree.setCheck(sels[i],1);
    	}
    	_submit();
    });
</script> 
</body>
</html>
