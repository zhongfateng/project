<%@ page language="java" pageEncoding="utf-8" isELIgnored="false"
	import="java.lang.*"%>
<%@ taglib uri="/WEB-INF/tld/extremecomponents.tld" prefix="ec" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>中国食品微生物数据共享平台</title>
<link href="<%=request.getContextPath() %>/resources/images/myCenter/css.css" rel="stylesheet" type="text/css" />
<!-- tr背景鼠标经过效果-->

<style type="text/css">
a:link {
	text-decoration: none;
}

a:visited {
	text-decoration: none;
}

a:hover {
	text-decoration: underline;
}

a:active {
	text-decoration: none;
}
</style>

<script language=javascript>
function changebg(obj,cl){
var bgcolor;
if(cl=="0"){bgcolor="#f1f9ff";}else{bgcolor="#fff";}
obj.style.backgroundColor=bgcolor;
}
</script>
</head>

<body>


<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="100%" valign="top" bgcolor="#FFFFFF" style="padding-left:5px">
    <table width="100%" height="100" border="0" cellpadding="0" cellspacing="0" style="border:solid; border-color:#d0ced1; border-width:1px; margin-bottom:8px; background:url(<%=request.getContextPath() %>/resources/images/myCenter/images/jhesh_cbg-protein.jpg); background-repeat:repeat-x">
      <tr>
        <td valign="top" style="padding:15px;">
		<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td class="hei14" style="border-bottom:solid 1px #F7D89D">
			<p>您好，个人会员 <span class="stitle">${userName}</span> <br />
              您的会员积分为 <span class="stitle">${sumSorce}</span>，会员等级为 <span class="stitle">${jb}</span><br />
            </p>
          </td>
      </tr>
    </table>
    </td>
  </tr>
</table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="35" background="<%=request.getContextPath() %>/resources/images/myCenter/images/leftmenu-bg.jpg" class="hei16B"><p class="jifen"> 我的积分</p></td>
        </tr>
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td  style="border:solid; border-width:0 1px 1px 1px; border-color:#d0ced1;">
          
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr  onmousemove="javascript:changebg(this,0)" style="backgroud-color:#f4f4f4" onmouseout="javascript:changebg(this,1)">
                <td width="80" height="50" align="center" background="<%=request.getContextPath() %>/resources/images/myCenter/images/bg_repeat.png" bgcolor="#4E85E2" class="hei14">序号</td>
                <td width="80" height="50" align="center" background="<%=request.getContextPath() %>/resources/images/myCenter/images/bg_repeat.png" bgcolor="#4E85E2" class="hei14">积分</td>
                <td width="200" height="50" align="center" background="<%=request.getContextPath() %>/resources/images/myCenter/images/bg_repeat.png" bgcolor="#4E85E2" class="hei14">时间</td>
                <td width="120" height="50" align="center" background="<%=request.getContextPath() %>/resources/images/myCenter/images/bg_repeat.png" bgcolor="#4E85E2" class="hei14">类型</td>
                <td width="350" height="50" align="center" background="<%=request.getContextPath() %>/resources/images/myCenter/images/bg_repeat.png" bgcolor="#4E85E2" class="hei14">备注&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
              </tr>
              
              </table>
              
              
              <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="table-layout: fixed;">
              <c:forEach items="${dataList.list}" var="ff" varStatus="i" begin="0">
              
              <tr  onmousemove="javascript:changebg(this,0)" style="backgroud-color:#f4f4f4" onmouseout="javascript:changebg(this,1)">
                <td width="50" height="50" align="center" class="hei14">
                ${i.count+dataList.pageSize*(dataList.currentPage-1)}</td>
                <td width="55" height="50" align="center" class="hei14">${ff.jfcount}</td>
                <td width="150" height="50" align="center" class="hei14">${ff.timestamp}</td>
                <td width="60" height="50" align="center" class="hei14">${ff.ftype}</td>
                <td width="250" height="50" align="center" style="white-space:nowrap;overflow:hidden;text-overflow:ellipsis;"><span class="hei14">${ff.note}</span></td>
              </tr>
              
              </c:forEach>
              
            </table>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td width="100%" height="30" align="right" bgcolor="edf7fd" class="hei14">
               
                
                
                <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                  <td>&nbsp;</td>
                  <td height="10" align="right">&nbsp;</td>
                </tr>
                <tr>
                  <!-- <td width="53%" bgcolor="edf7fd" class="hei14">&nbsp;</td> -->
                  <td width="100%" height="30" bgcolor="edf7fd" class="hei14" align="center">找到<span class="lanlink">${dataList.allRow}</span>条数据，共<span class="lanlink">${dataList.totalPage}</span>
                  页&nbsp;&nbsp;
                  
                  <c:choose> 
                        <c:when test="${dataList.currentPage==1}"> 
                           <img src="<%=request.getContextPath()%>/resources/images/geneButton/first-hui.gif" align="absmiddle" />
                            <!--<img src="${skinPath}images/hygl/left_b.gif" align="absmiddle" />
                            --><img src="<%=request.getContextPath()%>/resources/images/geneButton/front-hui.gif" align="absmiddle" />
                        </c:when>
                        <c:otherwise>
                        <a href="javascript:changepage('1')">
                       <img src="<%=request.getContextPath()%>/resources/images/geneButton/first-lv.gif" align="absmiddle" /></a>
                            <a href="javascript:changepage('2')">

                            <a href="javascript:changepage('2')">
                            <img src="<%=request.getContextPath()%>/resources/images/geneButton/front-lv.gif" align="absmiddle" /></a>
                        </c:otherwise>
                    </c:choose>
                  第${dataList.currentPage}页
                    <c:choose>
                        <c:when test="${dataList.currentPage < dataList.totalPage}">
                            <a href="javascript:changepage('3')">
                            <img src="<%=request.getContextPath()%>/resources/images/geneButton/next-lv.gif" align="absmiddle" /></a>
                            
                            <!--<a href="javascript:changepage('3')">
                            <img src="${skinPath}images/hygl/right_h.gif" align="absmiddle" border="0"  /></a>
                            
                            --><a href="javascript:changepage('4')">
                           <img src="<%=request.getContextPath()%>/resources/images/geneButton/last-lv.gif" align="absmiddle" /></a>
                        </c:when>
                        <c:otherwise>
                        <img src="<%=request.getContextPath()%>/resources/images/geneButton/next-hui.gif" align="absmiddle" />
                            <!--<img src="${skinPath}images/hygl/right_b.gif" align="absmiddle" />
                           --><img src="<%=request.getContextPath()%>/resources/images/geneButton/last-hui.gif" align="absmiddle" />
                        </c:otherwise>
                    </c:choose>
                  
                  </td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td height="10" align="right">&nbsp;</td>
                </tr>
              </table>
                
                
                
                
                
                
                </td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td height="10" align="right">&nbsp;</td>
              </tr>
            </table></td>
        </tr>
      </table></td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
<script type="text/javascript">
	function changepage(pid){
		var pageSize = $("#select").val();
	    if(pid=='1'){
	    	window.location.href="fmptJf.action?m=frontJF&page=1&pageSize="+pageSize;
	    }
	    if(pid=='2'){
	    	window.location="fmptJf.action?m=frontJF&page=${dataList.currentPage-1}&pageSize="+pageSize;
	    }
	    if(pid=='3'){
	    	window.location="fmptJf.action?m=frontJF&page=${dataList.currentPage+1}&pageSize="+pageSize;
	    }
	    if(pid=='4'){
	   		window.location="fmptJf.action?m=frontJF&page=${dataList.totalPage}&pageSize="+pageSize;
	    }
	}
</script>

</body>
</html>
