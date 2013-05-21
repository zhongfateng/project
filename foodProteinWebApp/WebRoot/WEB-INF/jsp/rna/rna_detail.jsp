<%@ page language="java" pageEncoding="gbk" isELIgnored="false"
	import="java.lang.*"%>
<%@ page import="java.util.*" %>
<%@ taglib uri="/WEB-INF/tld/extremecomponents.tld" prefix="ec" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>中国食品微生物数据共享平台</title>
<link href="<%=request.getContextPath()%>/resources/images/rna/css/css.css" rel="stylesheet" type="text/css" />

<!-- tr背景鼠标经过效果-->

<style type="text/css">
a:link {
	text-decoration: none;
}

a:visited {
	text-decoration: none;
}

a:hover {
	text-decoration: none;
}

a:active {
	text-decoration: none;
}
</style>


<script language=javascript>
function changebg(obj,cl){
var bgcolor;
if(cl=="0"){bgcolor="#EDF1F3";}else{bgcolor="#f4f4f4";}
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
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-1.6.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/component/ymPrompt/ymPrompt.js"></script>
<link rel="stylesheet" id='skin' type="text/css" href="<%=request.getContextPath()%>/resources/component/ymPrompt/skin/qq/ymPrompt.css" />
<script type="text/javascript">
	$(function(){
		if($("#d_sum").html()==0){
			$("#dd_00").after("<table border='0' width='100%'><tr><td id='aa_00' align='center' style='color:red;'></td></tr></table>");
			$("#aa_00").html("无数据！");
		};
		
		
		$("#d1_1").click(function(){
			var dbName = $("#dbName").val();
			var geneInfo = $("#d1_2").val();
			var reg = /^[a-zA-Z\s]+$/;
			if(geneInfo==""||geneInfo=="请输入物种拉丁名"|| !reg.test(geneInfo)){
				ymPrompt.alert('请输入物种拉丁名！');
				//alert("");
			}else if(dbName==""){
				ymPrompt.alert('请选择数据库类型！');
			}else{
				if(dbName=="gene"){
					window.location.href="gene.action?m=frontGeneList&geneInfo="+geneInfo;
				}else if(dbName=="protein"){
					window.location.href="protein.action?m=frontProteinList&proteinInfo="+geneInfo;
				}else if(dbName=="rna"){
					window.location.href="rna.action?m=frontRnaList&rnaInfo="+geneInfo;
				}
				
			}
			 
		});
		
		
		$("#deleteId").click(
			function() {
				$.ajax( {
					"url" : "gene.action?m=frontGeneDel",
					"type" : "post",
					"dataType" : "json",
					"success" : function(data, desc1) {
							$("#dedte").html(data);
					}
				});
			});
		
		
	});
	
	
	function MyFocus(){
			if($("#d1_2").val()=="请输入物种拉丁名"){
				$("#d1_2").val("");
			}
		};
		
		function MyBlur(){
			if($("#d1_2").val()==""){
				$("#d1_2").val("请输入物种拉丁名");
			}
		};
</script>

<!-- 栏目更换结束 -->
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
    <td height="34" background="<%=request.getContextPath()%>/resources/images/rna/images/top-bg.jpg">&nbsp;</td>
  </tr>
</table>
<table width="965" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="25" valign="top" class="hei12">首页-&gt;基础数据库-&gt;RNA数据</td>
  </tr>
</table>
<table width="965" height="692" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="734" height="692" valign="top"><table width="734" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="35" background="<%=request.getContextPath()%>/resources/images/rna/images/leftmenu-bg-rna.jpg"  class="green14B">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Summary</td>
     </tr>
    </table>
      <table width="734" border="0" cellpadding="0" cellspacing="0" style="border:solid; border-color:#d0ced1; border-width:0px 1px 1px 1px; margin-bottom:5px;">
        <tr>
          <td valign="top" bgcolor="#FBFFF9"><table width="100%" height="61" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td width="33%" align="right" valign="top" class="eng14" style="padding:20px 10px;"><strong>
                  Rna   ID：<br />
                  Chromosome   ref：<br />
                  Chromosome   start：<br />
                  Chromosome   end：<br />
                  Species ID：<br />
                  Gene ID：<br />
                  Organism：<br/>
                  Ncbi   species ID：<br />
                  Note：</strong></td>
              <td width="67%" align="left" valign="top" style="padding:20px 10px;" class="eng14">${rna.gid}<br />
               ${rna.chromosomeRef}<br />
                ${rna.chromosomeStart}<br />
                ${rna.chromosomeEnd}<br />
                ${rna.speciesId}<br />
                <a href="gene.action?m=frontGeneDetail&ncbiGeneRef=${rna.geneId}">
                ${rna.geneId}
                </a><br />
                ${rna.organism}<br/>
                <a href="http://www.ncbi.nlm.nih.gov/Taxonomy/Browser/wwwtax.cgi?id=${rna.ncbiSpeciesId}" target="_blank">
                ${rna.ncbiSpeciesId}
                </a><br />
                ${rna.note}
</td>
            </tr>
          </table></td>
        </tr>
      </table>
      <table width="734" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="35" background="<%=request.getContextPath()%>/resources/images/rna/images/leftmenu-bg-rna.jpg"  class="green14B">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sequence </td>
        </tr>
      </table>
      <table width="734" border="0" cellpadding="0" cellspacing="0" style="border:solid; border-color:#d0ced1; border-width:0px 1px 1px 1px; margin-bottom:5px;">
        <tr>
          <td valign="top" bgcolor="#FBFFF9" style="padding:20px 0"><table width="100%" height="150" border="0" cellpadding="0" cellspacing="0">
           
           
            <%
				//根据总组数分为每5组为一行，最后一行不够五组的为一行
				List<String> seqList = new ArrayList<String>();
				seqList = (List<String>)request.getAttribute("seqList");
				int groupSize = seqList.size();//组的总数
				int rowSize = 0;//一共显示的行数.一行显示5组。
				if(groupSize/5==0){
					rowSize = groupSize/5;
				}else{
					rowSize = groupSize/5+1;
				}
				
				for(int j=0;j<rowSize;j++){
				
					if(j==rowSize-1){//当到最后一行时，判断是组的个数，然后分别显示
			%>
			<tr>
              <td width="10%" height="40" align="right" valign="middle" class="eng14" style="padding:0 15px;"><%=j*50+1 %></td>
              <td width="90%" height="30" align="left" valign="middle" class="eng14">
              
              <%
              		if(groupSize%5==1){//剩下一组
              %>
              							 <%=seqList.get(j*5)%>   &nbsp;&nbsp;&nbsp;&nbsp;
              <%
               		}else if(groupSize%5==2){//剩下二组
              %>	
              			             	 <%=seqList.get(j*5)%>   &nbsp;&nbsp;&nbsp;&nbsp;
              		    				 <%=seqList.get(j*5 +1)%>&nbsp;&nbsp;&nbsp;&nbsp;
              	
              <%
              		}else if(groupSize%5==3){//剩下三组
              %>
              			                 <%=seqList.get(j*5)%>   &nbsp;&nbsp;&nbsp;&nbsp;
              					         <%=seqList.get(j*5 +1)%>&nbsp;&nbsp;&nbsp;&nbsp;
              							 <%=seqList.get(j*5 +2)%>&nbsp;&nbsp;&nbsp;&nbsp;
             <% 
             		}else if(groupSize%5==4){//剩下四组
             %> 	
              			                 <%=seqList.get(j*5)%>   &nbsp;&nbsp;&nbsp;&nbsp;
              					         <%=seqList.get(j*5 +1)%>&nbsp;&nbsp;&nbsp;&nbsp;
              							 <%=seqList.get(j*5 +2)%>&nbsp;&nbsp;&nbsp;&nbsp;
               							 <%=seqList.get(j*5 +3)%>&nbsp;&nbsp;&nbsp;&nbsp;
              <%	
              		}else if(groupSize%5==5){//剩下五组
              %>	
              			                 <%=seqList.get(j*5)%>   &nbsp;&nbsp;&nbsp;&nbsp;
              					         <%=seqList.get(j*5 +1)%>&nbsp;&nbsp;&nbsp;&nbsp;
              							 <%=seqList.get(j*5 +2)%>&nbsp;&nbsp;&nbsp;&nbsp;
              							 <%=seqList.get(j*5 +3)%>&nbsp;&nbsp;&nbsp;&nbsp;
              							 <%=seqList.get(j*5 +4)%>&nbsp;&nbsp;&nbsp;&nbsp;
              <%
              		}
              
              %>
       		        					 
              </td>
            </tr>
			
			
			<%
					}else{
			%>
			
			<tr>
              <td width="10%" height="40" align="right" valign="middle" class="eng14" style="padding:0 15px;"><%=j*50+1 %></td>
              <td width="90%" height="30" align="left" valign="middle" class="eng14">
       		        					 <%=seqList.get(j*5)%>   &nbsp;&nbsp;&nbsp;&nbsp;
              					         <%=seqList.get(j*5 +1)%>&nbsp;&nbsp;&nbsp;&nbsp;
              							 <%=seqList.get(j*5 +2)%>&nbsp;&nbsp;&nbsp;&nbsp;
              							 <%=seqList.get(j*5 +3)%>&nbsp;&nbsp;&nbsp;&nbsp;
              							 <%=seqList.get(j*5 +4)%>&nbsp;&nbsp;&nbsp;&nbsp;
              </td>
            </tr>
			
			<%		
						
					}
			%>
				
						
			<%		
				}
			%>


          </table></td>
        </tr>
      </table>
      <table width="734" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="35" background="<%=request.getContextPath()%>/resources/images/rna/images/leftmenu-bg-rna.jpg"  class="green14B">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Related Bibliography</td>
        </tr>
      </table>
      <table width="734" border="0" cellpadding="0" cellspacing="0" style="border:solid; border-color:#d0ced1; border-width:0px 1px 1px 1px">
        <tr>
          <td valign="top" bgcolor="#FBFFF9">
			
			<table width="734" border="0" cellpadding="0" cellspacing="0" style="border:solid; border-color:#d0ced1; border-width:0px 1px 1px 1px">
        <tr>
          <td valign="top">
              <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="dd_00">
                <tr>
                  <td>&nbsp;</td>
                  <td height="10" align="right">&nbsp;</td>
                </tr>
                <tr>
                  <td width="100%" height="30" bgcolor="#FBF0B5" class="hei14" align="center">找到<span class="lanlink" id="d_sum">${pageBean.allRow}</span>条数据，<a href="rna.action?m=frontRnaDocList&rnaId=${rna.geneId}&page=1&pageSize=10">显示更多</a>&nbsp;&nbsp;
                  </td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td height="10" align="right">&nbsp;</td>
                </tr>
            </table>
            
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
            
            <c:forEach items="${pageBean.list}" var="pp" begin="0" varStatus="i">
                <tr>
              <td align="left" valign="middle" class="eng14" style="height: 60px;">
              &nbsp;&nbsp;&nbsp;
              <strong>${i.count+pageBean.pageSize*(pageBean.currentPage-1)}. </strong>
              <a href="search.action?m=fronttodetail&pmid=${pp.pmid}">${pp.title}</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;出版日期:&nbsp;${pp.publicationDate}
			  </td>
            </tr>
                
            </c:forEach>
            
          </table>
            
            </td>
        </tr>
      </table>
			

          </td>
        </tr>
      </table></td>
    <td width="226" valign="top" bgcolor="#FFFFFF" style="padding-left:5px">
    <table width="100%" cellspacing="0" background="<%=request.getContextPath()%>/resources/images/rna/images/right-bg-rna.jpg" bgcolor="226">
      <tr>
        <td width="54%" height="30" style="padding-left: 20px;"><span class="bai14">最新搜索历史</span></td>
        <td width="24%" style="padding-left: 35px;" class="bai12"><span><a href="javascript:;" id="deleteId">清除</a></span></td>
      </tr>
    </table>
      <table id="dedte" width="100%" border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;border:solid; border-width:0px 1px 1px 1px; border-color:#cbcbcb; margin-bottom:5px; padding-left:">
        <c:forEach items="${itemList}" var="dd" varStatus="i" begin="0">
        <tr>
          <td id="deId" align="left" class="hei14" style="white-space:nowrap;overflow:hidden;text-overflow:ellipsis;padding:2px 5px 5px 5px; background:url(<%=request.getContextPath()%>/resources/images/rna/images/jhesh_cbg-rna.jpg); background-position:bottom; background-repeat:repeat-x">
              <img src="<%=request.getContextPath()%>/resources/images/rna/images/arrow_045_small-rna.png" width="16" height="16" />&nbsp;<a href="${dd.serchUrl}" target="_blank">${dd.serchWord}</a><br />
        	</td>
       		<td id="deId2" width="60" align="left" class="hei14" style="background:url(<%=request.getContextPath()%>/resources/images/rna/images/jhesh_cbg-rna.jpg); background-position:bottom; background-repeat:repeat-x">
          	  &nbsp;&nbsp;${dd.serchDb}<br />
          </td>
        </tr>
        </c:forEach>
      </table>
      <table width="100%" cellspacing="0">
        <tr>
          <td height="30" align="center" background="<%=request.getContextPath()%>/resources/images/rna/images/right-bg-rna.jpg"><span class="bai14">相关数据库检索 </span></td>
        </tr>
      </table>
      <table width="100%" cellspacing="0"  style="border:solid; border-width:0px 1px 1px 1px; border-color:#cbcbcb;margin-bottom:5px;">
        <tr>
          <td height="20" bgcolor="#F6FCFC" class="hei14" style="padding:2px 15px; background:url(<%=request.getContextPath()%>/resources/images/rna/images/jhesh_cbg-rna.jpg); background-position:bottom; background-repeat:repeat-x"><p>
              <select name="dbName" id="dbName" style="height: 25px; width: 110px;">
              	<option value="">请选择数据库</option>
              	<option value="gene">基因库</option>
              	<option value="protein">蛋白库</option>
              	<option value="rna">RNA库</option>
              </select>
            </p>
          </td>
        </tr>
        <tr>
          <td height="35" bgcolor="#F6FCFC" class="hei14" style="padding:10px 15px; background:url(<%=request.getContextPath()%>/resources/images/rna/images/jhesh_cbg-rna.jpg); background-position:bottom; background-repeat:repeat-x"><p>
              <input name="geneInfo" class="TxtUserNameCssClass" id="d1_2" onblur="MyBlur();" onfocus="MyFocus();" value="请输入物种拉丁名" style="padding-bottom:6px; height:24px;border:solid 1px #CCCCCC; "/>
            </p>
              <p align="center"><a href="javascript:;" id="d1_1"><img src="<%=request.getContextPath()%>/resources/images/rna/images/search-menu-rna.jpg" width="102" height="35" border="0" /></a></p>
          </td>
        </tr>
      </table></td>
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

</body>
</html>