<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/resources/common/res_all.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=uft-8">
		<title>注册页面</title>
	</head>
	<body>
		<div id="a_tabbar" style="width: 620px; height: 550px;" />
	</body>
	<script type="text/javascript">
	        tabbar=new dhtmlXTabBar("a_tabbar","top");

            tabbar.setSkin('dhx_skyblue');
            tabbar.setImagePath("<%=request.getContextPath()%>/resources/component/dhtmlxTabbar/imgs/"); 
            
           /* tabbar.addTab("a1","用户注册","200px");*/
            tabbar.addTab("a1","用户注册","610px");
         
			tabbar.setHrefMode("iframes-on-demand");
           /* tabbar.setContentHref("a1","bzptuser.action?m=frontIndex&fwid=${fwid}");*/
            tabbar.setContentHref("a1","bzptuser.action?m=frontEEIndex&fwid=${fwid}");
            
            tabbar.setTabActive("a1")
	</script>
</html>