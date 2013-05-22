<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="com.nbw.common.SysParameter"%>
<%
	String bzzyWebApp = SysParameter.getParameter("bzzyWebApp");
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

</head>

<body>
<table width="914" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td style="border-bottom:solid #CCCCCC 1px">&nbsp;</td>
  </tr>
  <tr>
        <td height="60" align="center" class="txt_p">
        <a href="<%=bzzyWebApp%>/comm/bxpt_linkus.jsp" class="txt_p" target="_blank">联系我们</a>        |  
        <a href="javascript:window.external.Addfavorite('http://192.168.2.184:7001/BzptWeb/appmanager/eip/home1','标准平台')" class="txt_p">收藏本站</a>        |  
        <a href="<%=bzzyWebApp%>/appmanager/eip/main?_nfpb=true&_pageLabel=P5800125901322719086481&url=/comm/bzpt_aboutus.jsp&width=938&height=400
" class="txt_p" target="_blank">关于平台</a>        |  
        <a href="<%=bzzyWebApp%>/comm/bzpt_erji_mianze.jsp" class="txt_p" target="_blank">网站声明</a>
        <br />
    网站管理：中国国家标准化管理委员会标准信息中心 共建单位：中国标准化研究院    中国标准出版社    中国标准化协会  <br/> 
    地址：北京海淀区马甸东路9号 邮编：100088</td>
  </tr>
</table>
</body>
</html>