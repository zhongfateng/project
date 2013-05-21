<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1 
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0 
	response.setDateHeader("Expires", 0);
	//定义树的皮肤路径
	String xtreePath1 = path
			+ "/resources/component/dhtmlxTree/codebase/imgs/csh_scbrblue/";
	
%>

<html>
	<link
		href="<%=path%>/resources/component/dhtmlxTree/codebase/dhtmlxtree.css"
		rel="stylesheet" type="text/css" />
	<link href="${csscssFile}" rel="stylesheet" type="text/css" />
	<script type="text/javascript"
		src="<%=path%>/resources/component/dhtmlxTree/codebase/dhtmlxtree.js"></script>
	<script type="text/javascript"
		src="<%=path%>/resources/component/dhtmlxTree/codebase/dhtmlxcommon.js"></script>
    <script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/FusionCharts/FusionCharts.js"></script>
	<head>
		<title></title>
	</head>

<body style="background-color:transparent">
    <table width="734" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="35" background="${skinPath}images/leftmenu-bg.jpg" class="hei16B">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${fs.ename}</td>
      </tr>
    </table>
        <table width="700" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td align="center">
            <div  align="center"  id="chartdiv"></div>
         <script> 	
         var chart = new FusionCharts("<%=request.getContextPath()%>/resources/component/FusionCharts/Pie3D.swf", "chart1Id", "400", "300", "0", "1");		   			
         
         chart.setDataURL(escape("fmptstrain.action?m=frontLoadChartsXML&id=${fs.nodeId}"));
	     chart.render("chartdiv");
	       </script>
            </td>
          </tr>
        </table>
        <table width="648" height="259" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td valign="top" background="${skinPath}images/date_bg.png"><table width="648" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td width="84" height="72">&nbsp;</td>
                  <td width="167">&nbsp;</td>
                  <td width="57"><a href="#"><span class="bai14">(${fs.dbz})</span></a></td>
                  <td width="80">&nbsp;</td>
                  <td width="192">&nbsp;</td>
                  <td width="68"><a href="#"><span class="bai14">(${fs.jy})</span></a></td>
                </tr>
                <tr>
                  <td height="43">&nbsp;</td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td height="54">&nbsp;</td>
                  <td>&nbsp;</td>
                  <td><a href="@"><span class="bai14">(${fs.rna})</span></a></td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td><a href="@"><span class="bai14">(${fs.tp})</span></a></td>
                </tr>
                <tr>
                  <td height="36">&nbsp;</td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td height="82">&nbsp;</td>
                  <td>&nbsp;</td>
                  <td><a href="#"><span class="bai14">(${fs.wx})</span></a></td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                </tr>
            </table></td>
          </tr>
        </table>

	</body>
</html>