<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
  	<title>中国食品微生物数据共享平台</title>
	<link href="css.css" rel="stylesheet" type="text/css" />
    
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
			<link
			href="<%=request.getContextPath()%>/resources/skins/default/css/search.css"
			rel="stylesheet" type="text/css">
			<link rel="stylesheet" id='skin' type="text/css" href="<%=request.getContextPath()%>/resources/component/ymPrompt/skin/qq/ymPrompt.css" />
	<style>
	A {text-decoration: NONE} 
	</style>
	

	<script language=javascript>
function changebg(obj,cl){
var bgcolor;
if(cl=="0"){bgcolor="#f1f9ff";}else{bgcolor="#fff";}
obj.style.backgroundColor=bgcolor;
}
</script>
	<script type="text/javascript"
			src="<%=request.getContextPath()%>/resources/js/jquery-1.6.2.js"></script>
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/ymPrompt/ymPrompt.js"></script>


<!-- 栏目更换开始 -->

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

<script type="text/javascript">
	function changepage(pid){
	
	    if(pid=='1'){
	    	window.location = "expert.action?m=frontGetMoreExpert&page=1&pageSize=${expertList.pageSize}";
	    }
	    if(pid=='2'){
	    	window.location="expert.action?m=frontGetMoreExpert&page=${expertList.currentPage-1}&pageSize=${expertList.pageSize}";
	    }
	    if(pid=='3'){
	    	window.location="expert.action?m=frontGetMoreExpert&page=${expertList.currentPage+1}&pageSize=${expertList.pageSize}";
	    }
	    if(pid=='4'){
	   		window.location="expert.action?m=frontGetMoreExpert&page=${expertList.totalPage}&pageSize=${expertList.pageSize}";
	    }
	}
</script>
<script type="text/javascript">
		function gene1(){
		var page = $("#genePage1").val();
		var reg = /^[1-9][0-9]*$/;
		if(page=="" || isNaN(page) || page<=0 || page>${expertList.totalPage}){
			ymPrompt.alert('请输入正确页码！');
		}else{
			window.location.href="expert.action?m=frontGetMoreExpert&page="+page+"&pageSize=${expertList.pageSize}";
		}
		
	};
	function gene(){
		var page = $("#genePage").val();
		var reg = /^[1-9][0-9]*$/;
		if(page=="" || isNaN(page) || page<=0 || page>${expertList.totalPage}){
			ymPrompt.alert('请输入正确页码！');
		}else{
			window.location.href="expert.action?m=frontGetMoreExpert&page="+page+"&pageSize=${expertList.pageSize}";
		}
		
	};
</script>




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
    <td valign="top">
    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="10">&nbsp;</td>
      </tr>
      <tr>
        <td width="965" height="30" align="center" bgcolor="edf7fd" class="hei14">共<span class="lanlink">${expertList.allRow}</span>条数据，共<span class="lanlink">${expertList.totalPage}</span>页&nbsp;&nbsp; <c:choose> 
                        <c:when test="${expertList.currentPage==1}"> 
                            <img src="<%=request.getContextPath()%>/resources/images/expert/image/first-hui.gif" align="absmiddle" border="0"/>
                            <img src="<%=request.getContextPath()%>/resources/images/expert/image/front-hui.gif" align="absmiddle" border="0"/>
                        </c:when>
                        <c:otherwise>
                        <a href="javascript:changepage('1')">
                        <img src="<%=request.getContextPath()%>/resources/images/expert/image/first-lv.gif" align="absmiddle" border="0"/></a>
                            <!--<a href="javascript:changepage('2')">
                            <img src="${skinPath}images/hygl/left_h.gif" align="absmiddle" border="0" /></a>
                            -->
                            <a href="javascript:changepage('2')">
                            <img src="<%=request.getContextPath()%>/resources/images/expert/image/front-lv.gif" align="absmiddle" border="0"/></a>
                        </c:otherwise>
                    </c:choose>
                  第${expertList.currentPage}页
                    <c:choose>
                        <c:when test="${expertList.currentPage < expertList.totalPage}">
                            <a href="javascript:changepage('3')">
                            <img src="<%=request.getContextPath()%>/resources/images/expert/image/next-lv.gif" align="absmiddle" border="0"/></a>
                            <!--
                            <a href="javascript:changepage('3')">
                           <img src="${skinPath}images/hygl/right_h.gif" align="absmiddle" border="0"  /></a>
                            --><a href="javascript:changepage('4')">
                            <img src="<%=request.getContextPath()%>/resources/images/expert/image/last-lv.gif" align="absmiddle" border="0"/></a>
                        </c:when>
                        <c:otherwise>
                            <img src="<%=request.getContextPath()%>/resources/images/expert/image/next-hui.gif" align="absmiddle" border="0" />
                           <img src="<%=request.getContextPath()%>/resources/images/expert/image/last-hui.gif" align="absmiddle" border="0"/>
                        </c:otherwise>
                    </c:choose>
                  &nbsp;&nbsp;第&nbsp;<input name="pageSize" style="width: 50px;" id="genePage" />&nbsp;页&nbsp;<input type="button" align="absmiddle" style="height:20px;width:50px;background-image: url('<%=request.getContextPath()%>/resources/images/geneButton/go.gif');border:0;" onclick="gene();"/>
                  </td>
      </tr>
      <tr>
        <td height="10">&nbsp;</td>
      </tr>
      
      
    </table>
     <c:forEach items="${expertList.list}" var="ex" begin="0" varStatus="i">
    
        <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr  onmousemove="javascript:changebg(this,0)" style="backgroud-color:#f4f4f4" onmouseout="javascript:changebg(this,1)">
            <td style="border-bottom:solid 1px #addeff; padding-bottom:8px;"><table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td width="177" height="150"><table width="115" height="135" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="cbcbcb">
                      <tr>
                        <td align="center" valign="middle" bgcolor="#FFFFFF"><img src="<%=request.getContextPath()%>/expertimage/${ex.jpgname }.jpg" width="107" height="126" /></td>
                      </tr>
                  </table></td>
                  <td width="649" class="hei12"><p><span class="hei14"><strong>姓名:${ex.ename }</strong></span><br />
                    <br />
                    &gt;&nbsp;职称   ：${ex.professional }<br />
                    &gt;&nbsp;所在单位：${ex.affiation }<br />
                    &gt;&nbsp;研究领域:${ex.researchField }</p>                     </td>
                  <td width="118" class="hei12"><p><a href="expert.action?m=frontGetDetailExpert&eid=${ex.eid}">详情&gt;&gt;</a></p></td>
                </tr>
            </table></td>
          </tr>
        </table>
       </c:forEach>
       </td>
  </tr>
    <tr>
        <td width="965" height="30" align="center" bgcolor="edf7fd" class="hei14">共<span class="lanlink">${expertList.allRow}</span>条数据，共<span class="lanlink">${expertList.totalPage}</span>页&nbsp;&nbsp; <c:choose> 
                        <c:when test="${expertList.currentPage==1}"> 
                            <img src="<%=request.getContextPath()%>/resources/images/expert/image/first-hui.gif" align="absmiddle" border="0"/>
                            <img src="<%=request.getContextPath()%>/resources/images/expert/image/front-hui.gif" align="absmiddle" border="0"/>
                        </c:when>
                        <c:otherwise>
                        <a href="javascript:changepage('1')">
                        <img src="<%=request.getContextPath()%>/resources/images/expert/image/first-lv.gif" align="absmiddle" border="0"/></a>
                            <!--<a href="javascript:changepage('2')">
                            <img src="${skinPath}images/hygl/left_h.gif" align="absmiddle" border="0" /></a>
                            -->
                            <a href="javascript:changepage('2')">
                            <img src="<%=request.getContextPath()%>/resources/images/expert/image/front-lv.gif" align="absmiddle" border="0"/></a>
                        </c:otherwise>
                    </c:choose>
                  第${expertList.currentPage}页
                    <c:choose>
                        <c:when test="${expertList.currentPage < expertList.totalPage}">
                            <a href="javascript:changepage('3')">
                            <img src="<%=request.getContextPath()%>/resources/images/expert/image/next-lv.gif" align="absmiddle" border="0"/></a>
                            <!--
                            <a href="javascript:changepage('3')">
                           <img src="${skinPath}images/hygl/right_h.gif" align="absmiddle" border="0"  /></a>
                            --><a href="javascript:changepage('4')">
                            <img src="<%=request.getContextPath()%>/resources/images/expert/image/last-lv.gif" align="absmiddle" border="0"/></a>
                        </c:when>
                        <c:otherwise>
                            <img src="<%=request.getContextPath()%>/resources/images/expert/image/next-hui.gif" align="absmiddle" border="0"/>
                           <img src="<%=request.getContextPath()%>/resources/images/expert/image/last-hui.gif" align="absmiddle" border="0"/>
                        </c:otherwise>
                    </c:choose>
                 &nbsp;&nbsp;第&nbsp;<input name="pageSize" style="width: 50px;" id="genePage1" />&nbsp;页&nbsp;<input type="button" align="absmiddle" style="height:20px;width:50px;background-image: url('<%=request.getContextPath()%>/resources/images/geneButton/go.gif');border:0;" onclick="gene1();"/>  </td>
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
