<%@ page language="java" pageEncoding="gbk" isELIgnored="false"
	import="java.lang.*"%>
<%@ page import="java.util.*" %>
<%@ taglib uri="/WEB-INF/tld/extremecomponents.tld" prefix="ec" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>�й�ʳƷ΢�������ݹ���ƽ̨</title>
<link href="<%=request.getContextPath()%>/resources/images/rna/css/css.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" id='skin' type="text/css" href="<%=request.getContextPath()%>/resources/component/ymPrompt/skin/qq/ymPrompt.css" />
<!-- tr������꾭��Ч��-->

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



<!-- ��Ŀ������ʼ -->

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
<script type="text/javascript">
	$(function(){
		if($("#d_sum").html()==0){
			$("#dd_00").after("<table border='0' width='100%'><tr><td id='aa_00' align='center' style='color:red;'></td></tr></table>");
			$("#aa_00").html("�����ݣ�");
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
		
		
		//$("#select").html("<option value='<%=request.getAttribute("pageSize")%>'><%=request.getAttribute("pageSize")%></option><option value='5'>5</option><option value='10'>10</option><option value='15'>15</option><option value='20'>20</option>");

		$("#select").change(function(){
			var pageSize = $(this).val();
			window.location.href="rna.action?m=frontRnaDocList&rnaId=${rna.geneId}&page=1&pageSize="+pageSize;
		});
		
		$("#d1_1").click(function(){
			var dbName = $("#dbName").val();
			var geneInfo = $("#d1_2").val();
			var reg = /^[a-zA-Z\s]+$/;
			if(geneInfo==""||geneInfo=="����������������"|| !reg.test(geneInfo)){
				ymPrompt.alert('������������������');
				//alert("");
			}else if(dbName==""){
				ymPrompt.alert('��ѡ�����ݿ����ͣ�');
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
			if($("#d1_2").val()=="����������������"){
				$("#d1_2").val("");
			}
		};
		
		function MyBlur(){
			if($("#d1_2").val()==""){
				$("#d1_2").val("����������������");
			}
		};
</script>

<!-- ��Ŀ�������� -->
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
    <td height="25" valign="top" class="hei12">��ҳ-&gt;�������ݿ�-&gt;RNA����</td>
  </tr>
</table>
<table width="965" height="692" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="734" height="692" valign="top">
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
          <td valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td height="40" bgcolor="#F7F7F7">&nbsp;&nbsp;&nbsp;<span class="hei14">��ʾ��ʽ
                  <select name="select2" style="height:20px; border:solid 1px #CCCCCC;">
                        <option>Ĭ��</option>
                  </select>
                  &nbsp;&nbsp;&nbsp;&nbsp;ÿҳ��ʾ
                  <select name="select2" id="select" style="height:20px; border:solid 1px #CCCCCC;">
                    <option>5</option>
                    <option>10</option>
                    <option>15</option>
                    <option>20</option>
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
                  <td width="100%" height="30" bgcolor="#FBF0B5" class="hei14" align="center">�ҵ�<span class="lanlink" id="d_sum">${pageBean.allRow}</span>�����ݣ���<span class="lanlink">${pageBean.totalPage}</span>ҳ&nbsp;&nbsp;
                  <c:choose> 
                        <c:when test="${pageBean.currentPage==1}"> 
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
                  ��${pageBean.currentPage}ҳ
                    <c:choose>
                        <c:when test="${pageBean.currentPage < pageBean.totalPage}">
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
              <td align="left" valign="middle" class="eng14" style="height: 60px;" style="padding:20px 10px;">
              &nbsp;&nbsp;&nbsp;
              <strong>${i.count+pageBean.pageSize*(pageBean.currentPage-1)}. </strong>
              <a href="search.action?m=fronttodetail&pmid=${pp.pmid}">${pp.title}</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��������:&nbsp;${pp.publicationDate}
			  </td>
            </tr>
                
            </c:forEach>
            
          </table>
            
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td>&nbsp;</td>
                <td height="10" align="right">&nbsp;</td>
              </tr>
              <tr>
                <td width="100%" height="30" bgcolor="#FBF0B5" class="hei14" align="center">�ҵ�<span class="lanlink">${pageBean.allRow}</span>�����ݣ���<span class="lanlink">${pageBean.totalPage}</span>ҳ&nbsp;&nbsp;
                
                <c:choose> 
                        <c:when test="${pageBean.currentPage==1}"> 
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
                  ��${pageBean.currentPage}ҳ
                    <c:choose>
                        <c:when test="${pageBean.currentPage < pageBean.totalPage}">
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
                
                </td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td height="10" align="right">&nbsp;</td>
              </tr>
            </table></td>
        </tr>
      </table>
			

          </td>
        </tr>
      </table></td>
    <td width="226" valign="top" bgcolor="#FFFFFF" style="padding-left:5px"><table width="100%" cellspacing="0" background="<%=request.getContextPath()%>/resources/images/rna/images/right-bg-rna.jpg" bgcolor="226">
      <tr>
        <td width="54%" height="30" style="padding-left: 20px;"><span class="bai14">����������ʷ</span></td>
        <td width="24%" style="padding-left: 35px;" class="bai12"><span><a href="javascript:;" id="deleteId">���</a></span></td>
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
          <td height="30" align="center" background="<%=request.getContextPath()%>/resources/images/rna/images/right-bg-rna.jpg"><span class="bai14">������ݿ���� </span></td>
        </tr>
      </table>
      <table width="100%" cellspacing="0"  style="border:solid; border-width:0px 1px 1px 1px; border-color:#cbcbcb;margin-bottom:5px;">
        <tr>
          <td height="20" bgcolor="#F6FCFC" class="hei14" style="padding:2px 15px; background:url(<%=request.getContextPath()%>/resources/images/rna/images/jhesh_cbg-rna.jpg); background-position:bottom; background-repeat:repeat-x"><p>
              <select name="dbName" id="dbName" style="height: 25px; width: 110px;">
              	<option value="">��ѡ�����ݿ�</option>
              	<option value="gene">�����</option>
              	<option value="protein">���׿�</option>
              	<option value="rna">RNA��</option>
              </select>
            </p>
          </td>
        </tr>
        <tr>
          <td height="35" bgcolor="#F6FCFC" class="hei14" style="padding:10px 15px; background:url(<%=request.getContextPath()%>/resources/images/rna/images/jhesh_cbg-rna.jpg); background-position:bottom; background-repeat:repeat-x"><p>
              <input name="geneInfo" class="TxtUserNameCssClass" id="d1_2" onblur="MyBlur();" onfocus="MyFocus();" value="����������������" style="padding-bottom:6px; height:24px;border:solid 1px #CCCCCC; "/>
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

<script type="text/javascript">
	function changepage(pid){
		var pageSize = $("#select").val();
	    if(pid=='1'){
	    	window.location.href="rna.action?m=frontRnaDocList&page=1&rnaId=${rna.geneId}&pageSize="+pageSize;
	    }
	    if(pid=='2'){
	    	window.location="rna.action?m=frontRnaDocList&page=${pageBean.currentPage-1}&rnaId=${rna.geneId}&pageSize="+pageSize;
	    }
	    if(pid=='3'){
	    	window.location="rna.action?m=frontRnaDocList&page=${pageBean.currentPage+1}&rnaId=${rna.geneId}&pageSize="+pageSize;
	    }
	    if(pid=='4'){
	   		window.location="rna.action?m=frontRnaDocList&page=${pageBean.totalPage}&rnaId=${rna.geneId}&pageSize="+pageSize;
	    }
	}
</script>
</body>
</html>