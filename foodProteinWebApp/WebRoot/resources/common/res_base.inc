<%
String moduleFunctions = (String)session.getAttribute("userWorkModuleFunctions");
moduleFunctions = null == moduleFunctions ? "" : moduleFunctions;
boolean isAdd = moduleFunctions.indexOf("B") > -1;
boolean isMod = moduleFunctions.indexOf("C") > -1;
boolean isDel = moduleFunctions.indexOf("D") > -1;
%>
