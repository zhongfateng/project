<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.nbw.lucene.util.Pageable"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@page import="com.nbw.lucene.domain.Doctxt"%>

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
if(cl=="0"){bgcolor="#f4f4f4";}else{bgcolor="#fff";}
obj.style.backgroundColor=bgcolor;
}
</script>
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
	var searchWord="${searchWord}";
		searchWord=encodeURI(encodeURI(searchWord));
		function gene1(){
		
		var page = $("#genePage1").val();
		var reg = /^[1-9][0-9]*$/;
		if(page=="" || isNaN(page) || page<=0 || page>${pageable.maxPages}){
			ymPrompt.alert('请输入正确页码！');
		}else{
			window.location.href="search.action?m=frontToLoadOne&searchWord="+searchWord+"&page="+page+"&pageSize=${pageable.pageSize}";
		}
		
	};
	function gene(){
		var page = $("#genePage").val();
		var reg = /^[1-9][0-9]*$/;
		if(page=="" || isNaN(page) || page<=0 || page>${pageable.maxPages}){
			ymPrompt.alert('请输入正确页码！');
		}else{
window.location.href="search.action?m=frontToLoadOne&searchWord="+searchWord+"&page="+page+"&pageSize=${pageable.pageSize}";
		}
		
	};
</script>


<script type="text/javascript">
	function changepage(pid){
	
		var searchWord="${searchWord}";
		searchWord=encodeURI(encodeURI(searchWord));
	
	    if(pid=='1'){
	    	window.location="search.action?m=frontToLoadOne&searchWord="+searchWord+"&page=1&pageSize=${pageable.pageSize}";
	    }
	    if(pid=='2'){
	    	window.location="search.action?m=frontToLoadOne&searchWord="+searchWord+"&page=${pageable.page-1}&pageSize=${pageable.pageSize}";
	    }
	    if(pid=='3'){
	    	window.location="search.action?m=frontToLoadOne&searchWord="+searchWord+"&page=${pageable.page+1}&pageSize=${pageable.pageSize}";
	    }
	    if(pid=='4'){
	   		window.location="search.action?m=frontToLoadOne&searchWord="+searchWord+"&page=${pageable.maxPages}&pageSize=${pageable.pageSize}";
	    }
	}
</script>
	<script type="text/javascript"
			src="<%=request.getContextPath()%>/resources/js/jquery-1.6.2.js"></script>
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/ymPrompt/ymPrompt.js"></script>

<script type="text/javascript">
	
		function  changeone()
		{
			var searchWord="${searchWord}";
		searchWord=encodeURI(encodeURI(searchWord));
			var pageSize=document.getElementById("selectedtwo").value;
			window.location.href="search.action?m=frontToLoadOne&searchWord="+searchWord+"&page=1&pageSize="+pageSize;
		}
	</script>
	
	<script type="text/javascript">
	function AllChecked(obj,param)
	{
	var check = document.getElementsByName(param);
	for(var i=0;i<check.length;i++){
     if(obj.checked){
      check[i].checked=true;
     }
     else{
      check[i].checked=false;
     }
    } 
  }
	
	
	</script>
	
	

  </head>
  
  <body>
<table width="100%" height="170px;" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="100%" align="center"><iframe frameborder=0 scrolling="no" name="topFrame"
						src="./comm/fmpt_top.jsp" width="100%" height="100%"></iframe></td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="34" background="${skinPath}images/lucene/top-bg.jpg">&nbsp;</td>
  </tr>
</table>
  <table width="960" height="65" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td valign="top"  style="background-image:url(../images/lefttext-bg.jpg); background-position:bottom; background-repeat:repeat-x">
    <table width="960" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="39" height="35"><img src="${skinPath}images/leftmenu-bg01.jpg" width="39" height="35" /></td>
    <td background="${skinPath}images/leftmenu-bg02.jpg" class="hei16B">检索结果列表</td>
    <td width="13"><img src="${skinPath}images/leftmenu-bg03.jpg" width="13" height="35" /></td>
     </tr>
    </table>
     <table width="960" border="0" cellpadding="0" cellspacing="0" style="border:solid; border-color:#d0ced1; border-width:0px 1px 1px 1px">
           <c:if test="${test!=0}">
            
        <tr>
          <td valign="top">
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td height="40" bgcolor="edf7fd" align="left">&nbsp;&nbsp;&nbsp;<span class="hei14">&nbsp;&nbsp;&nbsp;&nbsp;显示
                <select name="select2" id="selectedtwo" style="height:20px; border:solid 1px #CCCCCC; color:#999999" onchange="changeone()">
                <option  value="10" <c:if test="${pageable.pageSize =='10'}">selected</c:if>>10 </option>
                   	<option  value="20" <c:if test="${pageable.pageSize	=='20'}">selected</c:if>>20 </option>
                   	<option value="50" <c:if test="${pageable.pageSize	=='50'}">selected</c:if>>50</option>
                   	<option value="100" <c:if test="${pageable.pageSize	=='100'}">selected</c:if>>100</option>
                   
                </select>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></td>
                  <td  height="30" align="center" bgcolor="edf7fd" class="hei14">找到<span class="lanlink">${totalnum}</span>条数据，共<span class="lanlink">${pageable.maxPages}页</span>&nbsp;&nbsp;
                    <c:choose> 
                        <c:when test="${pageable.page==1}"> 
                            <img src="${skinPath}images/lucene/first-hui.gif" align="absmiddle" border="0" />
                            <img src="${skinPath}images/lucene/front-hui.gif" align="absmiddle" border="0"/>
                            
                        </c:when>
                        <c:otherwise>
                        <a href="javascript:changepage('1')"><img src="${skinPath}images/lucene/first-lv.gif" align="absmiddle" border="0"/></a>
                            <a href="javascript:changepage('2')">
                            <img src="${skinPath}images/lucene/front-lv.gif" align="absmiddle" border="0" /></a>
                        </c:otherwise>
                    </c:choose>
					第${pageable.page}页
                    <c:choose>
                        <c:when test="${pageable.page < pageable.maxPages}">
                            <a href="javascript:changepage('3')">
                            <img src="${skinPath}images/lucene/next-lv.gif" align="absmiddle" border="0"  /></a>
                            <a href="javascript:changepage('4')"><img src="${skinPath}images/lucene/last-lv.gif" align="absmiddle" border="0"/></a>
                        </c:when>
                        <c:otherwise>
                         	<img src="${skinPath}images/lucene/next-hui.gif" align="absmiddle" border="0" />
                           <img src="${skinPath}images/lucene/last-hui.gif" align="absmiddle" border="0" />
                        </c:otherwise>
                    </c:choose>
				&nbsp;&nbsp;第&nbsp;<input name="pageSize" style="width: 50px;" id="genePage"/>&nbsp;页&nbsp;<input type="button" align="absmiddle" style="height:20px;width:50px;background-image: url('<%=request.getContextPath()%>/resources/images/geneButton/go.gif');border:0;" onclick="gene();"/>
				</td>
            </tr>
          </table>
          
            
          
             
             <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style= "table-layout:fixed;">
              <tr  onmousemove="javascript:changebg(this,0)" style="backgroud-color:#f4f4f4" onmouseout="javascript:changebg(this,1)">
                <td  height="25" align="center" background="${skinPath}images/lucene/bg_repeat.png" bgcolor="#4E85E2" class="hei14">序号</td>
                <td  height="40" align="center" background="${skinPath}images/lucene/bg_repeat.png" bgcolor="#4E85E2" class="hei14">标题</td>
                <td  height="40" align="center" background="${skinPath}images/lucene/bg_repeat.png" bgcolor="#4E85E2" class="hei14">作者</td>
                <td  height="40" align="center" background="${skinPath}images/lucene/bg_repeat.png" bgcolor="#4E85E2" class="hei14">出版社</td>
                <td  height="40" align="center" background="${skinPath}images/lucene/bg_repeat.png" bgcolor="#4E85E2" class="hei14">出版日期</td>
              </tr>
           
                <%  
                List searchResult = (List)request.getAttribute("searchResult");
                 int resultCount = 0;
	    		if(null != searchResult){
	    		resultCount = searchResult.size();
	    		}
                 for(int i = 0; i < resultCount; i++){
	    		Doctxt doctxt = (Doctxt)searchResult.get(i);
	    		String Title = doctxt.getTitle();
	    		String pmid=doctxt.getPmid();
	    		String Author=doctxt.getAuthorFull();
	    		String Journal=(doctxt.getPublisher()==null)?"":(doctxt.getPublisher());
	    		String Publication=(doctxt.getPublicationType()==null)?"":(doctxt.getPublicationType());
	    		String PDATA=(doctxt.getPublicationDate()==null)?"":(doctxt.getPublicationDate());
                %>
                 <tr  onmousemove="javascript:changebg(this,0)" style="backgroud-color:#f4f4f4" onmouseout="javascript:changebg(this,1)">
                <td  style= "overflow:hidden;text-overflow:ellipsis;" height="30" align="center" class="hei14"><input type="checkbox" name="xuankuang" value="checkbox" />
                  <%=i+1 %>&nbsp;</td>
                <td  style= "white-space:nowrap;overflow:hidden;text-overflow:ellipsis;" height="30" align="center" class="hei14" ><p><a href="search.action?m=fronttodetail&pmid=<%=pmid %>" title="<%=Title %>"><%=Title %></a></p></td>
                <td  style= "white-space:nowrap;overflow:hidden;text-overflow:ellipsis;"  height="30" align="center" class="hei14"><%=Author %>&nbsp;&nbsp;&nbsp;</td>
                <td  style= "white-space:nowrap;overflow:hidden;text-overflow:ellipsis;"  height="30" align="center" class="hei14"><%=Journal%>&nbsp;&nbsp;&nbsp;</td>
                <td  style= "white-space:nowrap;overflow:hidden;text-overflow:ellipsis;"  height="30" align="center" class="hei14"><%=PDATA %>&nbsp;&nbsp;&nbsp;</td>
              </tr>
             
                 <%
 				}
	  			%>
                 </table>
             <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
             	<tr>
                   <td bgcolor="edf7fd" align="left">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="checkbox" name="checkbox273" value="checkbox" onclick="AllChecked(this,'xuankuang')"/>
              全选&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  </td>
                <td  height="30" align="center" bgcolor="edf7fd" class="hei14">找到<span class="lanlink">${totalnum}</span>条数据，共<span class="lanlink">${pageable.maxPages}页</span>&nbsp;&nbsp;
                    <c:choose> 
                        <c:when test="${pageable.page==1}"> 
                            <img src="${skinPath}images/lucene/first-hui.gif" align="absmiddle" border="0"/>
                            <img src="${skinPath}images/lucene/front-hui.gif" align="absmiddle" border="0"/>
                            
                        </c:when>
                        <c:otherwise>
                        <a href="javascript:changepage('1')"><img src="${skinPath}images/lucene/first-lv.gif" align="absmiddle" border="0"/></a>
                            <a href="javascript:changepage('2')">
                            <img src="${skinPath}images/lucene/front-lv.gif" align="absmiddle" border="0" /></a>
                        </c:otherwise>
                    </c:choose>
					第${pageable.page}页
                    <c:choose>
                        <c:when test="${pageable.page < pageable.maxPages}">
                            <a href="javascript:changepage('3')">
                            <img src="${skinPath}images/lucene/next-lv.gif" align="absmiddle" border="0"  /></a>
                            <a href="javascript:changepage('4')"><img src="${skinPath}images/lucene/last-lv.gif" align="absmiddle" border="0"/></a>
                        </c:when>
                        <c:otherwise>
                         	<img src="${skinPath}images/lucene/next-hui.gif" align="absmiddle" border="0" />
                           <img src="${skinPath}images/lucene/last-hui.gif" align="absmiddle" border="0" />
                        </c:otherwise>
                    </c:choose>
				&nbsp;&nbsp;第&nbsp;<input name="pageSize" style="width: 50px;" id="genePage1"/>&nbsp;页&nbsp;<input type="button" align="absmiddle" style="height:20px;width:50px;background-image: url('<%=request.getContextPath()%>/resources/images/geneButton/go.gif');border:0;" onclick="gene1();"/>
				</td>
              <tr>
                <td>&nbsp;</td>
                <td height="5" align="right">&nbsp;</td>
              </tr>
            </table>
         
            
            </td>
        </tr>
           </c:if>
           <c:if test="${test=='0'}">
           <tr>
           <td>
           ${message} 
           </td>
            </tr>
           
           </c:if>
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
