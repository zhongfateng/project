<%@ page language="java" pageEncoding="utf-8" isELIgnored="false"
	import="java.lang.*"%>
<%@ taglib uri="/WEB-INF/tld/extremecomponents.tld" prefix="ec" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>中国食品微生物数据共享平台</title>
<link href="<%=request.getContextPath()%>/resources/images/threeStruct/css/css.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" id='skin' type="text/css" href="<%=request.getContextPath()%>/resources/component/ymPrompt/skin/qq/ymPrompt.css" />
<!-- tr背景鼠标经过效果-->
<script language=javascript>
function changebg(obj,cl){
var bgcolor;
if(cl=="0"){bgcolor="#f1f9ff";}else{bgcolor="#fff";}
obj.style.backgroundColor=bgcolor;
}
</script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-1.6.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/component/ymPrompt/ymPrompt.js"></script>
<script type="text/javascript">
	$(function(){
		if($("#d_sum").html()==0){
			$("#dd_00").after("<table border='0' width='100%'><tr><td id='aa_00' align='center' style='color:red;'></td></tr></table>");
			$("#aa_00").html("无数据！");
		};
		
		if(<%=request.getAttribute("pageSize")%>==5){
			$("#select").html("<option value='<%=request.getAttribute("pageSize")%>'><%=request.getAttribute("pageSize")%></option><option value='10'>10</option><option value='15'>15</option><option value='20'>20</option>");
		}else if(<%=request.getAttribute("pageSize")%>==10){
			$("#select").html("<option value='<%=request.getAttribute("pageSize")%>'><%=request.getAttribute("pageSize")%></option><option value='5'>5</option><option value='15'>15</option><option value='20'>20</option>");
		}else if(<%=request.getAttribute("pageSize")%>==15){
			$("#select").html("<option value='<%=request.getAttribute("pageSize")%>'><%=request.getAttribute("pageSize")%></option><option value='5'>5</option><option value='10'>10</option><option value='20'>20</option>");
		}else if(<%=request.getAttribute("pageSize")%>==20){
			$("#select").html("<option value='<%=request.getAttribute("pageSize")%>'><%=request.getAttribute("pageSize")%></option><option value='5'>5</option><option value='10'>10</option><option value='15'>15</option>");
		};
		
		
		$("#select").change(function(){
			var pageSize = $(this).val();
			window.location.href="struct.action?m=frontThreeStructDetail&speciesName=<%=request.getAttribute("speciesName")%>&page=1&pageSize="+pageSize;
		});
		
		
		$("#d1_1").click(function(){
			var geneInfo = $("#d1_2").val();
			if(geneInfo==""||geneInfo=="输入种名"){
				ymPrompt.alert('请输入种名！');
				//alert("");
			}else{
				window.location.href="struct.action?m=frontThreeStructSearch&speciesName="+geneInfo;
			}
			 
		});
		
	});
	
	
	
	function gene(){
		var page = $("#genePage").val();
		var reg = /^[1-9][0-9]*$/;
		var pageSize = <%=request.getAttribute("pageSize")%>;
		var all = $("#yeshu").html();
		if(page=="" || isNaN(page) || !reg.test(page) || all<page){
			ymPrompt.alert('请输入正确页码！');
		}else{
			window.location.href="struct.action?m=frontThreeStructDetail&speciesName=<%=request.getAttribute("speciesName")%>&page="+page+"&pageSize="+pageSize;
		}
		
	};
	
	function gene1(){
		var page = $("#genePage1").val();
		var reg = /^[1-9][0-9]*$/;
		var pageSize = <%=request.getAttribute("pageSize")%>;
		var all = $("#yeshu").html();
		if(page=="" || isNaN(page) || !reg.test(page) || all<page){
			ymPrompt.alert('请输入正确页码！');
		}else {
			window.location.href="struct.action?m=frontThreeStructDetail&speciesName=<%=request.getAttribute("speciesName")%>&page="+page+"&pageSize="+pageSize;
		}
		
	};
	
	
	
	function MyFocus(){
			if($("#d1_2").val()=="输入种名"){
				$("#d1_2").val("");
			}
		};
		
		function MyBlur(){
			if($("#d1_2").val()==""){
				$("#d1_2").val("输入种名");
			}
		};
</script>


<style type="text/css">
<!--
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
-->
</style>
</head>

<body>
<table width="100%" height="170px;" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="100%" align="center"><iframe frameborder=0 scrolling="no" name="topFrame"
						src="./comm/fmpt_top.jsp" width="100%" height="100%" scrolling="no"></iframe></td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="34" background="<%=request.getContextPath()%>/resources/images/threeStruct/images/top-bg.jpg">&nbsp;</td>
  </tr>
</table>
<table width="965" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="25" valign="top" class="hei12">首页-&gt;特色数据库-&gt;三维结构数据库</td>
  </tr>
</table>
<table width="965" height="80" border="0" align="center" cellpadding="0" cellspacing="0" background="<%=request.getContextPath()%>/resources/images/threeStruct/images/search-bg.jpg"  style="margin-bottom:8px;">
  <tr>
    <td width="511" align="right" ><input name="queren22222" class="TxtsearchClass" id="d1_2" onblur="MyBlur();" onfocus="MyFocus();" value="输入种名" style="padding-bottom:6px; height:24px;border:solid 1px #CCCCCC;"/>
      &nbsp;</td>
    <td width="343" align="center"><a href="javascript:;" id="d1_1"><img src="<%=request.getContextPath()%>/resources/images/threeStruct/images/search-png.png" width="102" height="35" border="0" /></a>&nbsp;&nbsp;<a href="indexMain.action?m=frontIndexMain"><img src="<%=request.getContextPath()%>/resources/images/threeStruct/images/cancle-png.png" width="102" height="35" border="0"/></a></td>
  </tr>
</table>
<table width="965" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="39" height="35"><img src="<%=request.getContextPath()%>/resources/images/threeStruct/images/leftmenu-bg01.jpg" width="39" height="35" /></td>
    <td width="913" background="<%=request.getContextPath()%>/resources/images/threeStruct/images/leftmenu-bg02.jpg" class="hei16B">检索结果</td>
    <td width="13"><img src="<%=request.getContextPath()%>/resources/images/threeStruct/images/leftmenu-bg03.jpg" width="13" height="35" /></td>
  </tr>
</table>
<table width="965" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td valign="top"  style="border:solid; border-width:0px 1px 1px 1px; border-color:#cbcbcb; padding:5px;">
    
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td height="40" bgcolor="#F7F7F7">&nbsp;&nbsp;&nbsp;<span class="hei14">显示方式
                  <select name="select2" style="height:20px; border:solid 1px #CCCCCC;">
                        <option>默认</option>
                  </select>
                  &nbsp;&nbsp;&nbsp;&nbsp;每页显示
                  <select name="select2" id="select" style="height:20px; border:solid 1px #CCCCCC;">
                    <option value="5">5</option>
                    <option value="10">10</option>
                    <option value="15">15</option>
                    <option value="20">20</option>
                  </select>
                  &nbsp;&nbsp;&nbsp;&nbsp;                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></td>
              </tr>
            </table>
    
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="dd_00">
                <tr>
                  <td>&nbsp;</td>
                  <td height="10" align="right">&nbsp;</td>
                </tr>
                <tr>
                  <!-- <td width="53%" bgcolor="edf7fd" class="hei14">&nbsp;</td> -->
                  <td width="100%" height="30" bgcolor="edf7fd" class="hei14" align="center">找到<span class="lanlink" id="d_sum">${dataList.allRow}</span>条数据，共<span class="lanlink" id="yeshu">${dataList.totalPage}</span>
                  页&nbsp;&nbsp;
                  
                  <c:choose> 
                        <c:when test="${dataList.currentPage==1}"> 
                            <img src="<%=request.getContextPath()%>/resources/images/geneButton/first-hui.gif" align="absmiddle" border="0"/>
                            <img src="<%=request.getContextPath()%>/resources/images/geneButton/front-hui.gif" align="absmiddle" border="0"/>
                        </c:when>
                        <c:otherwise>
                        
                        <a href="javascript:changepage('1')">
                        <img src="<%=request.getContextPath()%>/resources/images/geneButton/first-lv.gif" align="absmiddle" border="0"/></a>
                        
                            <!--<a href="javascript:changepage('2')">
                            <img src="${skinPath}images/hygl/left_h.gif" align="absmiddle" border="0" /></a>
                            -->
                            
                            <a href="javascript:changepage('2')">
                            <img src="<%=request.getContextPath()%>/resources/images/geneButton/front-lv.gif" align="absmiddle" border="0"/></a>
                        </c:otherwise>
                    </c:choose>
                  第${dataList.currentPage}页
                    <c:choose>
                        <c:when test="${dataList.currentPage < dataList.totalPage}">
                            <a href="javascript:changepage('3')">
                            <img src="<%=request.getContextPath()%>/resources/images/geneButton/next-lv.gif" align="absmiddle" border="0"/></a>
                            <!--
                            <a href="javascript:changepage('3')">
                           <img src="${skinPath}images/hygl/right_h.gif" align="absmiddle" border="0"  /></a>
                            
                            --><a href="javascript:changepage('4')">
                            <img src="<%=request.getContextPath()%>/resources/images/geneButton/last-lv.gif" align="absmiddle" border="0"/></a>
                        </c:when>
                        <c:otherwise>
                            <img src="<%=request.getContextPath()%>/resources/images/geneButton/next-hui.gif" align="absmiddle" border="0"/>
                           <img src="<%=request.getContextPath()%>/resources/images/geneButton/last-hui.gif" align="absmiddle" border="0"/>
                        </c:otherwise>
                    </c:choose>
                  &nbsp;&nbsp;第&nbsp;<input name="pageSize" style="width: 50px;" id="genePage" value="${page}"/>&nbsp;页 &nbsp;
                  <c:if test="${dataList.allRow==0}">
                  <input type="button" align="absmiddle" style="height:20px;width:50px;background-image: url('<%=request.getContextPath()%>/resources/images/geneButton/go1.gif');border:0;"/>
                  </c:if>
                  <c:if test="${dataList.allRow!=0}">
                  <input type="button" align="absmiddle" style="height:20px;width:50px;background-image: url('<%=request.getContextPath()%>/resources/images/geneButton/go.gif');border:0;" onclick="gene();"/>
                  </c:if>
                  </td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td height="10" align="right">&nbsp;</td>
                </tr>
              </table>
		
		
	    <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin:8px 0;">
	    <c:forEach items="${dataList.list}" var="gg" begin="0" varStatus="i">
          <tr onmousemove="javascript:changebg(this,0)" style="backgroud-color:#f4f4f4" onmouseout="javascript:changebg(this,1)">
            <td width="6%" align="right" valign="top" class="eng14" style="padding:20px 2px; border-bottom:dashed #66CCFF 1px;">${i.count+dataList.pageSize*(dataList.currentPage-1)}&nbsp;.</td>
            <td width="94%" height="50" valign="top" class="eng14" style="padding:20px 2px; border-bottom:dashed #66CCFF 1px;">
            	<strong>Name</strong>: ${gg.sname}<br />
                <strong>Taxonomy</strong>: ${gg.taxonomy}<br />
                <strong>Proteins</strong>：${gg.proteins}   Chemicals：${gg.chemicals}<br />
                <strong>MMDB ID</strong>：<span class="link14"><a href="http://www.ncbi.nlm.nih.gov/Structure/mmdb/mmdbsrv.cgi?uid=${gg.mmdbId}">${gg.mmdbId}</a></span> PDB ID：<span class="link14"><a href="http://www.rcsb.org/pdb/explore/explore.do?structureId=${gg.pdbId}">${gg.pdbId}</a></span></td>
          </tr>
          </c:forEach>
        </table>
        
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
                           <img src="<%=request.getContextPath()%>/resources/images/geneButton/first-hui.gif" align="absmiddle" border="0"/>
                            <!--<img src="${skinPath}images/hygl/left_b.gif" align="absmiddle" />
                            --><img src="<%=request.getContextPath()%>/resources/images/geneButton/front-hui.gif" align="absmiddle" border="0"/>
                        </c:when>
                        <c:otherwise>
                        <a href="javascript:changepage('1')">
                       <img src="<%=request.getContextPath()%>/resources/images/geneButton/first-lv.gif" align="absmiddle" border="0"/></a>
                            <a href="javascript:changepage('2')">

                            <a href="javascript:changepage('2')">
                            <img src="<%=request.getContextPath()%>/resources/images/geneButton/front-lv.gif" align="absmiddle" border="0"/></a>
                        </c:otherwise>
                    </c:choose>
                  第${dataList.currentPage}页
                    <c:choose>
                        <c:when test="${dataList.currentPage < dataList.totalPage}">
                            <a href="javascript:changepage('3')">
                            <img src="<%=request.getContextPath()%>/resources/images/geneButton/next-lv.gif" align="absmiddle" border="0"/></a>
                            
                            <!--<a href="javascript:changepage('3')">
                            <img src="${skinPath}images/hygl/right_h.gif" align="absmiddle" border="0"  /></a>
                            
                            --><a href="javascript:changepage('4')">
                           <img src="<%=request.getContextPath()%>/resources/images/geneButton/last-lv.gif" align="absmiddle" border="0"/></a>
                        </c:when>
                        <c:otherwise>
                        <img src="<%=request.getContextPath()%>/resources/images/geneButton/next-hui.gif" align="absmiddle" border="0"/>
                            <!--<img src="${skinPath}images/hygl/right_b.gif" align="absmiddle" />
                           --><img src="<%=request.getContextPath()%>/resources/images/geneButton/last-hui.gif" align="absmiddle" border="0"/>
                        </c:otherwise>
                    </c:choose>
                  &nbsp;&nbsp;第&nbsp;<input name="pageSize" style="width: 50px;" id="genePage1" value="${page}"/>&nbsp;页 &nbsp;
                  <c:if test="${dataList.allRow==0}">
                  <input type="button" align="absmiddle" style="height:20px;width:50px;background-image: url('<%=request.getContextPath()%>/resources/images/geneButton/go1.gif');border:0;"/>
                  </c:if>
                  <c:if test="${dataList.allRow!=0}">
                  <input type="button" align="absmiddle" style="height:20px;width:50px;background-image: url('<%=request.getContextPath()%>/resources/images/geneButton/go.gif');border:0;" onclick="gene1();"/>
                  </c:if>
                  </td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td height="10" align="right">&nbsp;</td>
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
						src="./comm/fmpt_bottom.jsp" scrolling="no"></iframe></td>
</tr>
</table>
<script type="text/javascript">
	function changepage(pid){
		var pageSize = $("#select").val();
	    if(pid=='1'){
	    	window.location.href="struct.action?m=frontThreeStructDetail&speciesName=<%=request.getAttribute("speciesName")%>&page=1&pageSize="+pageSize;
	    }
	    if(pid=='2'){
	    	window.location="struct.action?m=frontThreeStructDetail&speciesName=<%=request.getAttribute("speciesName")%>&page=${dataList.currentPage-1}&pageSize="+pageSize;
	    }
	    if(pid=='3'){
	    	window.location="struct.action?m=frontThreeStructDetail&speciesName=<%=request.getAttribute("speciesName")%>&page=${dataList.currentPage+1}&pageSize="+pageSize;
	    }
	    if(pid=='4'){
	   		window.location="struct.action?m=frontThreeStructDetail&speciesName=<%=request.getAttribute("speciesName")%>&page=${dataList.totalPage}&pageSize="+pageSize;
	    }
	}
</script>
</body>
</html>
