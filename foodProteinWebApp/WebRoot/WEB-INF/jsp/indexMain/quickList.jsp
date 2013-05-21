<%@ page language="java" pageEncoding="gbk" isELIgnored="false"
	import="java.lang.*"%>
<%@ taglib uri="/WEB-INF/tld/extremecomponents.tld" prefix="ec" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>中国食品微生物数据共享平台</title>
<link href="<%=request.getContextPath()%>/resources/images/indexMain/css1/css.css" rel="stylesheet" type="text/css" />
</head>
<body>
<table width="100%" height="170px;" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="100%" align="center"><iframe frameborder=0 scrolling="no" name="topFrame"
						src="./comm/fmpt_top.jsp" width="100%" height="100%" scrolling="no"></iframe></td>
  </tr>
</table>
<table width="965" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-bottom:5px;">
  <tr>
    <td height="34" valign="bottom" background="<%=request.getContextPath()%>/resources/images/indexMain/image1/top-bg.jpg"><table width="965" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-bottom:5px;">
      <tr>
        <td width="34" height="25" align="center" valign="middle" class="hei12"><img src="<%=request.getContextPath()%>/resources/images/indexMain/image1/gif-0661.gif" width="13" height="11" /></td>
        <td width="931" align="left" valign="bottom" class="hei12">首页-&gt;快速检索</td>
      </tr>
    </table></td>
  </tr>
</table>
<table width="965" height="407" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-bottom:5px;">
  <tr>
    <td align="center" background="<%=request.getContextPath()%>/resources/images/indexMain/image1/quickS-bg.jpg">
    <table width="900" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="153" height="95">&nbsp;</td>
        <td width="297" valign="middle"><table width="230" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="182" height="40" align="left" class="blue14">基因数据库</td>
            <td width="48" align="left" class="link14"><a href="gene.action?m=frontGeneList&geneInfo=${speciesName}">${geneSum}</a></td>
          </tr>
          <tr></tr>
        </table></td>
        <td width="93">&nbsp;</td>
        <td width="357" valign="middle">
        <table width="230" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="182" height="40" align="left" class="blue14">文献数据库</td>
            <td width="48" align="left" class="link14">
            <a href="search.action?m=fronttoload&searchWord=${speciesName}">${docSum}</a>
            </td>
          </tr>
          <tr></tr>
        </table>
        </td>
      </tr>
      <tr>
        <td width="153" height="95">&nbsp;</td>
        <td valign="middle"><table width="230" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="182" height="40" align="left" class="blue14">蛋白质数据库</td>
            <td width="48" align="left" class="link14"><a href="protein.action?m=frontProteinList&proteinInfo=${speciesName}">${proteinSum}</a></td>
          </tr>
          <tr></tr>
        </table></td>
        <td>&nbsp;</td>
        <td valign="middle"><table width="230" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="182" height="40" align="left" class="blue14">食品微生物质谱数据库</td>
            <td width="48" align="left" class="link14"><a href="fmpttp.action?m=frontToIndex&key=${speciesName}">${fmptSum}</a></td>
          </tr>
          <tr></tr>
        </table></td>
      </tr>
      <tr>
        <td height="95">&nbsp;</td>
        <td valign="middle"><table width="230" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="182" height="40" align="left" class="blue14">RNA数据库</td>
            <td width="48" align="left" class="link14"><a href="rna.action?m=frontRnaList&rnaInfo=${speciesName}">${rnaSum}</a></td>
          </tr>
          <tr></tr>
        </table></td>
        <td>&nbsp;</td>
        <td valign="middle">
        	<table width="230" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="182" height="40" align="left" class="blue14">三维数据</td>
            <td width="48" align="left" class="link14">
            <a href="struct.action?m=frontThreeStructSearch&speciesName=${speciesName}">${threeStructSum}</a>
            </td>
          </tr>
          <tr></tr>
        </table>
        </td>
      </tr>
    </table></td>
  </tr>
</table>
<table align="center" width="100%">
<tr>
  <td height="120" ><iframe frameborder=0 scrolling="no" name="topFrame" width="100%" height="100%"
						src="./comm/fmpt_bottom.jsp" scrolling="no"></iframe></td>
</tr>
</table>
</body>
</html>
