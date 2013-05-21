<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
   <title>中国食品微生物数据共享平台</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link
			href="<%=request.getContextPath()%>/resources/images/expert/css/expert.css"
			rel="stylesheet" type="text/css">
  </head>
  
  <body>
<table width="100%" height="170px;" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="100%" align="center"><iframe frameborder=0 scrolling="no" name="topFrame"
						src="./comm/fmpt_top.jsp" width="100%" height="100%"></iframe></td>
  </tr>
</table>
<table width="965" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-bottom:5px;">
  <tr>
    <td height="34" valign="bottom" background="<%=request.getContextPath()%>/resources/images/expert/image/top-bg.jpg"><table width="965" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-bottom:5px;">
      <tr>
        <td width="34" height="25" align="center" valign="middle" class="hei12"><img src="<%=request.getContextPath()%>/resources/images/expert/image/gif-0661.gif" width="13" height="11" /></td>
        <td width="931" align="left" valign="bottom" class="hei12">首页-&gt;领域专家</td>
      </tr>
    </table></td>
  </tr>
</table>
<table width="965" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="39" height="35"><img src="<%=request.getContextPath()%>/resources/images/expert/image/leftmenu-bg01.jpg" width="39" height="35" /></td>
    <td background="<%=request.getContextPath()%>/resources/images/expert/image/leftmenu-bg02.jpg" class="hei16B">领域专家</td>
    <td width="13"><img src="<%=request.getContextPath()%>/resources/images/expert/image/leftmenu-bg03.jpg" width="13" height="35" /></td>
  </tr>
</table>
<table width="965" height="48%" border="0" align="center" cellpadding="0" cellspacing="0" style="border:solid; border-color:#d0ced1; border-width:0px 1px 1px 1px">
  <tr>
    <td valign="top"><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr  onmousemove="javascript:changebg(this,0)" style="backgroud-color:#f4f4f4" onmouseout="javascript:changebg(this,1)">
            <td style="padding:8px;"><table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td width="177" height="150"><table width="115" height="135" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="cbcbcb">
                      <tr>
                        <td align="center" valign="middle" bgcolor="#FFFFFF"><img src="<%=request.getContextPath()%>/expertimage/${e.jpgname}.jpg" width="107" height="126" /></td>
                      </tr>
                  </table></td>
                  <td width="649" class="hei12"><p><span class="stitle"><strong>${e.ename }</strong></span><br />
                    <br />
                   ${e.affiation}${e.professional }<br />
                  </p>                     </td>
                  <td width="118" class="hei12"><p>&nbsp;</p></td>
                </tr>
            </table></td>
          </tr>
        </table>
        <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td height="45" bgcolor="f1f9ff" class="hei14" style="border-bottom:solid 1px #addeff; padding:10px 0 0px 20px;"><strong>个人简介</strong></td>
          </tr>
          <tr>
            <td class="hei14" style="padding:15px;"> &nbsp;&nbsp;&nbsp;&nbsp;${e.resume }</td>
          </tr>
        </table>  
        <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td height="45" bgcolor="f1f9ff" class="hei14" style="border-bottom:solid 1px #addeff; padding:10px 0 0 20px;"><strong>研究领域</strong></td>
          </tr>
          <tr>
            <td style="padding:15px;"><span class="hei14" style="padding:15px;"> &nbsp;&nbsp;&nbsp;&nbsp;${e.researchField }</span></td>
          </tr>
        </table>
        <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td height="45" bgcolor="f1f9ff" class="hei14" style="border-bottom:solid 1px #addeff; padding:10px 0 0 20px;"><strong>研究成果</strong></td>
          </tr>
          <tr   style="backgroud-color:#f4f4f4">
            <td style="padding:15px;"><span class="hei14" style="padding:15px;"> &nbsp;&nbsp;&nbsp;&nbsp;${e.researchFinding }</span></td>
          </tr>
        </table>
    </td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>&nbsp;</td>
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
