<%@ page language="java" pageEncoding="utf-8" isELIgnored="false"
	import="java.lang.*"%>
<%@taglib uri="/WEB-INF/tld/extremecomponents.tld" prefix="ec"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>中国食品微生物数据共享平台</title>
<link href="<%=request.getContextPath()%>/resources/images/testTech/css/css.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" id='skin' type="text/css" href="<%=request.getContextPath()%>/resources/component/ymPrompt/skin/qq/ymPrompt.css" />
<script language=javascript>
function changebg(obj,cl){
var bgcolor;
if(cl=="0"){bgcolor="#f4f4f4";}else{bgcolor="#fff";}
obj.style.backgroundColor=bgcolor;
}
</script>

<script language="javascript">
function tihuan(td){
  var tr = td.parentElement.cells;
  var ob = obody.rows;
  for(var ii=0; ii<tr.length; ii++)
  {
    tr[ii].className = (td.cellIndex==ii)?"tag1_01":"tag1_02";
    ob[ii].style.display = (td.cellIndex==ii)?"block":"none";
  }
}

</script>
<script>
function trim(obj)
{
 return obj.replace(/^\s+|\s+$/g,"")
 } 
function show123()
{
	var arrcook1 = document.cookie.split(";");

			for(i=0;i<arrcook1.length;i++)
			{
				var arr123=arrcook1[i].split("=");

				
				if("hostsupplycount1"==trim(arr123[0]))
				{
					document.cookie="hostsupplycount1=0";
				}
				if("hostsupplyid1"==trim(arr123[0]))
				{
					
					document.cookie="hostsupplyid1=";
					//document.cookie="proid="+hidpro+"";
					
				}
				
				
			}
}
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.6.2.js"></script>
<script type="text/javascript" src ="<%=request.getContextPath()%>/resources/component/ymPrompt/ymPrompt.js"></script>
<script type="text/javascript">
	$(function(){
		
		$("#ss_2").click(function(){
			var geneInfo= $("#ss_1").val();
			if($("#ss_1").val()==""){
			ymPrompt.alert('请选择');
		}else{
			window.location.href="testTech.action?m=frontTestTechSearch&cname="+encodeURI(encodeURI(geneInfo));
			//$("#testId").submit();
		}
		});
	});

</script>



</head>

<body>
<table width="100%" height="170px;" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="100%" align="center"><iframe frameborder=0 scrolling="no" name="topFrame"
						src="./comm/fmpt_top.jsp" width="100%" height="100%" scrolling="no"></iframe></td>
  </tr>
</table>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-bottom:5px;">
  <tr>
    <td height="34" valign="bottom" background="<%=request.getContextPath()%>/resources/images/testTech/images/top-bg.jpg"><table width="965" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-bottom:5px;">
      <tr>
        <td width="34" height="25" align="center" valign="middle" class="hei12"><img src="<%=request.getContextPath()%>/resources/images/testTech/images/gif-0661.gif" width="13" height="11" /></td>
        <td width="931" align="left" valign="bottom" class="hei12">首页-&gt;基础数据库-&gt;微生物快速检测技术</td>
      </tr>
    </table></td>
  </tr>
</table>
<table width="965" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="39" height="35"><img src="<%=request.getContextPath()%>/resources/images/testTech/images/leftmenu-bg01.jpg" width="39" height="35" /></td>
    <td background="<%=request.getContextPath()%>/resources/images/testTech/images/leftmenu-bg02.jpg" class="hei16B">微生物快速检测技术</td>
    <td width="13"><img src="<%=request.getContextPath()%>/resources/images/testTech/images/leftmenu-bg03.jpg" width="13" height="35" /></td>
  </tr>
</table>
<table width="965" border="0" align="center" cellpadding="0" cellspacing="0" style="border:solid; border-color:#d0ced1; border-width:0px 1px 1px 1px;  margin-bottom:5px;">
  <tr>
    <td valign="top">
    <form action="testTech.action?m=frontTestTechSearch" method="post" id="testId" name="testName">
    <table width="100%" border="0" cellspacing="0" cellpadding="0"  style="background-image:url(<%=request.getContextPath()%>/resources/images/testTech/images/lefttext-bg.jpg); background-position:bottom; background-repeat:repeat-x; margin-bottom:5px;">
      <tr>
        <td height="50">&nbsp;</td>
        <td align="right" class="hei14">&nbsp;</td>
        <td class="hei14">&nbsp;</td>
        <td class="hei14">&nbsp;</td>
        <td class="hei14">&nbsp;</td>
      </tr>
      <tr>
        <td width="1%" height="50">&nbsp;</td>
        <td width="16%" align="right" class="hei14">检测方法：&nbsp; </td>
        <td width="22%" class="hei14">
          <select name="cname" id="ss_1" style="height:30px; width:180px; border:solid 1px #CCCCCC;">
            <option value="">-----请选择方法-----</option>
            <c:forEach items="${dataList}" var="dd" begin="0" varStatus="i" >
            <option value="${dd}">${dd}</option>
            </c:forEach>
          </select>        </td>
      </tr>
      <tr>
        <td height="50">&nbsp;</td>
        <td class="hei14">&nbsp;</td>
        <td class="hei14">&nbsp;</td>
        <td class="hei14">&nbsp;</td>
        <td height="80" align="left" class="hei14">
        <a href="javascript:;" id="ss_2">
        <img src="<%=request.getContextPath()%>/resources/images/testTech/images/search-menu.jpg" width="102" height="35" border="0" />
        </a>
        </td>
      </tr>
      <tr>
        <td height="20">&nbsp;</td>
        <td class="hei14">&nbsp;</td>
        <td class="hei14">&nbsp;</td>
        <td class="hei14">&nbsp;</td>
        <td align="left" class="hei14">&nbsp;</td>
      </tr>
    </table>
    </form>
      <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#FEF4B1">
        <tr>
          <td bgcolor="#FFFFEE"  class="hei14" style="padding:15px;"><p class="stitle" >使用说明</p>
              <p class="hei12"> &nbsp;&nbsp;&nbsp;&nbsp;收录国际上具有良好发展空间的食品微生物检测技术，通过原理、特点及应用的简单介绍，为用户检测方法的选择提供导向性。</p></td>
        </tr>
      </table>
      </td>
  </tr>
</table>
<br />
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>&nbsp;</td>
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
