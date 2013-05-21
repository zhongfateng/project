<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'zhipu.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script language="JavaScript" src="<%=request.getContextPath()%>/resources/component/FusionCharts/FusionCharts.js"></script>
	
	
  </head>
  
  <body>

  <table width="98%" border="0" cellspacing="0" cellpadding="3" align="center">
  <tr> 
    <td valign="top" class="text" align="center"> 
    <div id="chartdiv" align="center"> 
        FusionCharts. </div>
      <script type="text/javascript">
		   var chart = new FusionCharts("<%=request.getContextPath()%>/resources/component/FusionCharts/FCF_Line.swf", "ChartId", "1400", "700");
		   chart.setDataURL("<%=request.getContextPath()%>/aaa.xml");	   
		   chart.render("chartdiv");
		</script> </td>
  </tr>
  <tr>
    <td valign="top" class="text" align="center">&nbsp;</td>
  </tr>
  <tr> 
    <td valign="top" class="text" align="center"><a href="Data/Line2D.xml" target="_blank"><img src="../Contents/Images/BtnViewXML.gif" alt="View XML for the above chart" width="75" height="25" border="0" /></a></td>
  </tr>
</table>
  
  
  
  
  
  
  
  
  
  
  </body>
</html>
