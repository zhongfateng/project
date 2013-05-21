<%@ page contentType="text/html; charset=utf-8" language="java"
	errorPage=""%>
<%@ page import="com.nbw.sys.domain.SysModules"%>
<%@ page import="java.util.*"%>
<%@include file="/resources/common/res_all.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="${adminCssFile}" rel="stylesheet" type="text/css" />
		<script language="javascript">
	function on_ifr(url){
		document.parentWindow.parent.on_src(url);
	}
	function on_tr(tid,mg){
		var trs=document.getElementById(tid+'s');
		if(trs.style.display==''){
	    	trs.style.display='none'; 
	    	mg.src='${skinPath}images/manage/folderClosed.gif';
		}else{
			trs.style.display=''; 
			mg.src='${skinPath}images/manage/folderOpen.gif';
		}
	}
</script>
		<%
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path;
		%>
		<title>导航</title>
	</head>

	<body>
		<table width="256" border="0" cellpadding="0" cellspacing="0"
			bgcolor="#B4C9C6">
			<tr>
				<td height="700" align="left" valign="top" bgcolor="#FFFFFF">
					<div id="treeObject"
						style="width: 100%; height: 100%;border: 1px solid Silver;"></div>
				</td>
			</tr>
		</table>
		<script>
    tree=new dhtmlXTreeObject(document.getElementById('treeObject'),"100%","100%","root"); 
    tree.setImagePath("<%=request.getContextPath()%>/resources/component/dhtmlxTree/imgs/csh_vista/"); 
    tree.setXMLAutoLoading("login.action?m=loadModuleTree&&id='root'");
    tree.loadXML("login.action?m=loadModuleTree");
    tree.attachEvent("onSelect", function(id){
        var mouduleUrl=id.split(",")[1];
        if(mouduleUrl!="#"){
	        var url="<%=basePath%>"+mouduleUrl;
	        parent.document.getElementById("infoFrame").src =url;
        }
    });
</script>
	</body>
</html>