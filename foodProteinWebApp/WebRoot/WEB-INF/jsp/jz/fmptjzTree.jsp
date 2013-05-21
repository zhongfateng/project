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
	<head>
		<title>中国食品微生物数据共享平台</title>
	</head>

<body >
<table width="100%" height="170px;" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td height="100%" align="center"><iframe frameborder=0 scrolling="no" name="topFrame"
						src="./comm/fmpt_top.jsp" width="100%" height="100%" ></iframe></td>
  </tr>
</table>
<table width="965" border="0" align="center" cellpadding="0" cellspacing="0" >
  <tr>
    <td height="34" valign="bottom" background="${skinPath}images/top-bg.jpg"><table width="965" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-bottom:5px;">
      <tr>
        <td width="34" height="25" align="center" valign="middle" class="hei12"><img src="${skinPath}images/gif-0661.gif" width="13" height="11" /></td>
        <td width="931" align="left" valign="bottom" class="hei12">首页-&gt;基础数据库-&gt;微生物目录库</td>
      </tr>
    </table></td>
  </tr>
</table>
<table width="965" height="444" border="0" align="center" cellpadding="0" cellspacing="0" >
  <tr>
    <td width="226" height="444" valign="top" bgcolor="#F5F8FF"  style="background-image:url(${skinPath}images/rtext-bg.jpg); background-position:top; background-repeat:repeat-x; margin-bottom:5px;"><table width="100%" height="584" border="0" cellpadding="0" cellspacing="0"  style="border:solid; border-width:0px 1px 1px 1px; border-color:#cbcbcb;">
      <tr>
        <td valign="top" ><table width="100%" cellspacing="0">
          <tr>
            <td height="35" align="center" background="${skinPath}images/right-bg-40.jpg" bgcolor="#4E85E2"><span class="bai14">菌种目录</span></td>
          </tr>
        </table>
         <table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                  <td height="10"></td>
                </tr>
         </table>
        <div style="float: left">
						<div id="treebox_asy"
							style="width: 100%; height: 400; float: left;"></div>
		</div>
        <script> 		
		tree2=new dhtmlXTreeObject("treebox_asy","100%","100%","0");
    	tree2.setImagePath("<%=xtreePath1%>");
    	tree2.setOnClickHandler(doOnClick);
		tree2.setXMLAutoLoading("fmptstrain.action?m=frontLoadTreeXML");
        tree2.loadXML("fmptstrain.action?m=frontLoadTreeXML");
        function doOnClick(nodeId){
			if(nodeId != null&&nodeId>233){
				var ifr=document.getElementById("ifrm");
			    ifr.src="<%=path%>/fmptstrain.action?m=frontLoadRight&id="+nodeId;	   
			}else{
			    var ifr=document.getElementById("ifrm");
			    ifr.src="<%=path%>/fmptstrain.action?m=frontLoadRight&id=234";	   
			}
		}
	   </script>
       </td>
      </tr>
      <tr>
        <td height="174">&nbsp;</td>
      </tr>
    </table></td>
    <td width="734" valign="top" bgcolor="#F5F8FF" style="padding-left:5px">
    <iframe src="<%=path%>/fmptstrain.action?m=frontLoadRight" width="100%" height="100%" scrolling="no" frameborder="0" id="ifrm" name="ifrm" allowtransparency=true  ></iframe>
    </td>
    </tr>
</table>
<table align="center" width="100%">
<tr>
  <td height="120" ><iframe frameborder=0 scrolling="no" name="topFrame" width="100%" height="100%"
						src="./comm/fmpt_bottom.jsp" ></iframe></td>
</tr>
</table>

	</body>
</html>

