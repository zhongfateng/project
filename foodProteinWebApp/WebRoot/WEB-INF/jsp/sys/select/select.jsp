<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String url = request.getParameter("url");
String selExistStrs = java.net.URLDecoder.decode(request.getParameter("selExistStrs"),"UTF-8") ;
String selId = request.getParameter("selId");
String selName = request.getParameter("selName");
String flag = request.getParameter("flag");
%>
<html>
  <body> 
  	<input type="hidden" name="selExistStrsparent" id="selExistStrsparent" value="<%=selExistStrs %>">
    <iframe src="<%=url %>&selId=<%=selId %>&selName=<%=selName %>&flag=<%=flag %>" width="100%" height="100%"></iframe>
  </body>
</html>
