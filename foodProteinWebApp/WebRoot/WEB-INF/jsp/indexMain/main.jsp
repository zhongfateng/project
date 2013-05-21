<%@page  language="java" pageEncoding="utf-8" isELIgnored="false" import="java.lang.*"%>
<%@ taglib uri="/WEB-INF/tld/extremecomponents.tld" prefix="ec" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>中国食品微生物数据共享平台</title>
<link href="<%=request.getContextPath()%>/resources/images/indexMain/css/css.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" id='skin' type="text/css" href="<%=request.getContextPath()%>/resources/component/ymPrompt/skin/qq/ymPrompt.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.6.2.js"></script>
<script type="text/javascript" src ="<%=request.getContextPath()%>/resources/component/ymPrompt/ymPrompt.js"></script>
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
<script type="text/javascript">
function ff(){
	var optionLength = document.form1.optionType.length;
	var options = "";
	var info = $("#d1_2").val();
	var reg = /^[a-zA-Z\s]+$/;
	for(var i=0;i<optionLength;i++){
		if(form1.optionType[i].checked){
			options = form1.optionType[i].value;
		}
	}
	if($("#d1_2").val()==""|| !reg.test(info)){
		ymPrompt.alert('Enter Latin name of the Microorganism');
	}else if(options == ""){
		ymPrompt.alert('请选择一种类型！');
	}else{
		
		if(options == "all"){
		window.location.href="indexMain.action?m=frontQuickList&speciesName="+info;
	}else if(options == "gene"){
		window.location.href="gene.action?m=frontGeneList&geneInfo="+info;
	}else if(options == "protein"){
		window.location.href="protein.action?m=frontProteinList&proteinInfo="+info;
	}else if(options == "doctxt"){
		window.location.href="search.action?m=fronttoload&searchWord="+info;
	}else if(options == "zhipu"){
		window.location.href="fmpttp.action?m=frontToIndex&key="+encodeURI(encodeURI(info));
	}else if(options == "threeStruct"){
		window.location.href="struct.action?m=frontThreeStructSearch&speciesName="+info;
	}else if(options == "rna"){
		window.location.href="rna.action?m=frontRnaList&rnaInfo="+info;
	}	
		
	}
	
};
/*function MyFocus(){
			if($("#d1_2").val()=="请输入种名"){
				$("#d1_2").val("");
			}
		};
		
		function MyBlur(){
			if($("#d1_2").val()==""){
				$("#d1_2").val("请输入种名");
			}
		};*/
</script>
</head>
<body>
<table width="100%" height="170px;" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td height="100%" align="center"><iframe frameborder=0 scrolling="no" name="topFrame"
						src="./comm/fmpt_top.jsp" width="100%" height="100%" scrolling="no"></iframe></td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="99" background="<%=request.getContextPath()%>/resources/images/indexMain/image/main(0).gif">
    <form action="" method="post" name="form1">
    <table width="965" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="83" height="99"><img src="<%=request.getContextPath()%>/resources/images/indexMain/image/main(18).gif" width="83" height="99" /></td>
        <td width="750" class="link14"><table width="750" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td height="40" colspan="3" class="alan" align="left">
              <input type="radio" name="optionType" value="all" checked="checked"/>
              All databases
              <input type="radio" name="optionType" value="gene" />
              Gene
              <input type="radio" name="optionType" value="protein" />
              Protein
              <input type="radio" name="optionType" value="rna" />
      RNA
              <input type="radio" name="optionType" value="doctxt" />
              Literature
              <input type="radio" name="optionType" value="zhipu" />
              MS
              <input type="radio" name="optionType" value="threeStruct"/>
               Structure</td>
          </tr>
          <tr>
            <td height="40" width="340" align="left" valign="top"><span class="qianlan12">
              <input name="speciesName" id="d1_2" type="text" style="width: 300px;" class="search-g" value=""/>
            </span></td>
            <td height="40" align="left">
            <div align="left"><span class="qianlan12">
            <img src="<%=request.getContextPath()%>/resources/images/indexMain/image/main-(13).gif" width="65" height="32" onclick="ff();"/>
            </span></div></td>
            <td><span class="alan">Enter Latin name of the Microorganism</span></td>
          </tr>
        </table></td>
      </tr>
      
    </table>
    </form>
    </td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table">
  <tr>
    <td height="255" align="center" valign="top" background="<%=request.getContextPath()%>/resources/images/indexMain/image/main(17).png" bgcolor="#FEECA2"><table width="955" border="0" align="center" cellpadding="0" cellspacing="0" style="margin:5px;">
      <tr>
        <td width="36" height="30" align="center" bgcolor="f4f4f4"><img src="<%=request.getContextPath()%>/resources/images/indexMain/image/main (12).gif" width="19" height="23" /></td>
        <td width="929" align="left" bgcolor="f4f4f4" class="red12">网站公告：因本周六晚9：:0--12:00服务器检修，暂停服务！给您带来不便，请谅解！</td>
      </tr>
    </table>
      <table width="955" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-bottom:10px;">
        <tr>
          <td width="714" height="254" align="center" background="<%=request.getContextPath()%>/resources/images/indexMain/image/main (11).gif">
		   
			  <!--焦点图开始-->
			 <div align="center" id="div1">
			 <script type="text/javascript">
			 var focus_width=705;
			 var focus_height=227;
			 var text_height=20;
			 var swf_height = focus_height+text_height;
			 
			 var pics='<%=request.getContextPath()%>/resources/images/indexMain/image/d1.jpg|<%=request.getContextPath()%>/resources/images/indexMain/image/d2.jpg|<%=request.getContextPath()%>/resources/images/indexMain/image/d3.jpg|<%=request.getContextPath()%>/resources/images/indexMain/image/d4.jpg';
			 var links='search.action?m=fronttosearch|fmpttp.action?m=frontToIndex|http://www.cfmd.com.cn/wwwblast/blast.html|fmptstrain.action?m=frontToIndex';
			 var texts="文献数据库提供常见食品微生物文献信息检索|蛋白质指纹图谱数据库提供常见病原微生物的质谱图查询以及Web页面谱图上传分析功能|BLAST实现食品微生物序列相似性在线比较|微生物目录数据库按照生物学分类展示食品相关微生物";
			 document.write('<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0" width="'+ focus_width +'" height="'+ swf_height +'">');
			 document.write('<param name="allowScriptAccess" value="sameDomain"><param name="movie" value="<%=request.getContextPath()%>/resources/images/indexMain/image/focus.swf"> <param name="quality" value="high"><param name="bgcolor" value="#FEECA2">');
			 document.write('<param name="menu" value="false"><param name=wmode value="opaque">');
			 document.write('<param name="FlashVars" value="pics='+pics+'&links='+links+'&texts='+texts+'&borderwidth='+focus_width+'&borderheight='+focus_height+'&textheight='+text_height+'">');
			 document.write('<embed src="<%=request.getContextPath()%>/resources/images/indexMain/image/focus.swf" wmode="opaque" FlashVars="pics='+pics+'&links='+links+'&texts='+texts+'&borderwidth='+focus_width+'&borderheight='+focus_height+'&textheight='+text_height+'" menu="false" bgcolor="#ffffff" quality="high" width="'+ focus_width +'" height="'+ swf_height +'" allowScriptAccess="sameDomain" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer"/>'); 
			 document.write('</object>');
			 </script>
			 </div>
										  <!--焦点图结束-->
		  </td>
          <td width="5" valign="top"></td>
          <td width="126" valign="top" class="hei14"><table width="236" height="27" border="0" align="center" cellpadding="0" cellspacing="0" background="<%=request.getContextPath()%>/resources/images/indexMain/image/main (10).gif">
              <tr>
                <td align="left" class="bai14" style="padding-left:50px;">科技动态</td>
                <td><a href="techDy.action?m=frontTechDyList" class="white12" target="_blank">更多</a></td>
              </tr>
            </table>
              <table width="100%" height="225" border="0" cellpadding="0" cellspacing="0" background="<%=request.getContextPath()%>/resources/images/indexMain/image/main (9).gif">
                <tr>
                  <td height="20" valign="top"></td>
                </tr>
                <tr>
                  <td height="200" valign="top">
                  <c:forEach items="${techDyList.list}" var="tt" varStatus="i" begin="0">
                  <table width="200" border="0" cellspacing="0" cellpadding="0" style= "table-layout:fixed;">
                      <tr>
                        <td width="17%" height="30" align="center"><img src="<%=request.getContextPath()%>/resources/images/indexMain/image/front_01.png" width="11" height="13" /></td>
                        <td width="83%" align="left" class="lan12" style= "white-space:nowrap;overflow:hidden;text-overflow:ellipsis;"><a href="techDy.action?m=frontTechDyDetail&tid=${tt.tid}" class="lan12" target="_blank" title="${tt.title}">${tt.title}</a></td>
                      </tr>
                  </table>
                  </c:forEach>
                  </td>
                </tr>
            </table></td>
        </tr>
      </table>
      <table width="955" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-bottom:5px;">
        <tr>
          <td width="715" height="468" align="center" background="<%=request.getContextPath()%>/resources/images/indexMain/image/index201211_03.png">
          <table width="100%" height="440" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="16%" height="90">&nbsp;</td>
              <td width="34%">
              <table width="180" border="0" cellspacing="0" cellpadding="0" style="table-layout: fixed">
                <tr>
                  <td height="40" align="left"><a href="gene.action?m=frontGeneSearch" class="blue14" target="_blank">基因数据库</a></td>
                </tr>
                <tr>
                  <td height="40" align="left" class="lan12" style= "white-space:nowrap;overflow:hidden;text-overflow:ellipsis;">基因序列数据库专门存储完整测<br />序的食品微生物基因序列信息的数据库，除了提供基因序列ID、染色体ID、起始位点、物种ID、产物ID等基本信息外，还提供具体的序列以及相关文献等信息。</td>
                </tr>
              </table></td>
              <td width="14%">&nbsp;</td>
              <td width="36%">
              <table width="180" border="0" cellspacing="0" cellpadding="0" style="table-layout: fixed">
                <tr>
                  <td height="40" align="left"><a href="fmptstrain.action?m=frontToIndex" class="blue14" target="_blank">食品微生物目录数据库</a></td>
                </tr>
                <tr>
                  <td height="40" align="left" class="lan12" style= "white-space:nowrap;overflow:hidden;text-overflow:ellipsis;">按照门、纲、目、科、属、种的<br />生物分类，建立常见食品微生物目录库，其中涉及细菌67个属，霉菌20个属，酵母菌14个属，还收录了各物种对应的NCBI分类号。</td>
                </tr>
              </table></td>
            </tr>
            <tr>
              <td height="90">&nbsp;</td>
              <td><table width="180" border="0" cellspacing="0" cellpadding="0" style="table-layout: fixed">
                <tr>
                  <td height="40" align="left"><a href="protein.action?m=frontProteinSearch" class="blue14" target="_blank">蛋白质数据库</a></td>
                </tr>
                <tr>
                  <td height="40" align="left" class="lan12" style= "white-space:nowrap;overflow:hidden;text-overflow:ellipsis;">蛋白质序列数据库专门存储完整<br />测序的食品微生物蛋白质序列信息的数据库，除了提供蛋白质序列ID、染色体ID、起始位点、物种ID、注释等基本信息外，还提供具体的序列以及相关文献等信息。</td>
                </tr>
              </table></td>
              <td>&nbsp;</td>
              <td><table width="180" border="0" cellspacing="0" cellpadding="0" style="table-layout: fixed">
                <tr>
                  <td height="40" align="left"><a href="fmpttp.action?m=frontToIndex" class="blue14" target="_blank">食品微生物质谱数据库</a></td>
                </tr>
                <tr>
                  <td height="40" align="left" class="lan12" style= "white-space:nowrap;overflow:hidden;text-overflow:ellipsis;">在实验研究的基础上，建立标准<br />的指纹图谱采集方法，收录了食品中常见重要病原微生物的指纹图谱，提供Web页面谱图的展示和比较，同时允许用户上传数据到Web页面进行分析。</td>
                </tr>
              </table></td>
            </tr>
            <tr>
              <td height="90">&nbsp;</td>
              <td><table width="180" border="0" cellspacing="0" cellpadding="0" style="table-layout: fixed">
                <tr>
                  <td height="40" align="left"><a href="rna.action?m=frontRnaSearch" class="blue14" target="_blank">RNA数据库</a></td>
                </tr>
                <tr>
                  <td height="40" align="left" class="lan12" style= "white-space:nowrap;overflow:hidden;text-overflow:ellipsis;">序列数据库专门存储完整测序的<br />食品微生物RNA序列信息的数据库，除了提供蛋RNA的名称、染色体ID、起始位点、物种ID、注释等基本信息外，还提供具体的序列以及相关文献等信息。</td>
                </tr>
              </table></td>
              <td>&nbsp;</td>
              <td><table width="180" border="0" cellspacing="0" cellpadding="0" style="table-layout: fixed">
                <tr>
                  <td height="40" align="left"><a href="testTech.action?m=frontTestTech" class="blue14" target="_blank">食品微生物快检技术</a></td>
                </tr>
                <tr>
                  <td height="40" align="left" class="lan12" style= "white-space:nowrap;overflow:hidden;text-overflow:ellipsis;">收录国际上具有良好发展空间的<br />食品微生物检测技术，通过原理、特点及应用的简单介绍，为用户检测方法的选择提供导向性。</td>
                </tr>
              </table></td>
            </tr>
            <tr>
              <td height="90">&nbsp;</td>
              <td><table width="180" border="0" cellspacing="0" cellpadding="0" style="table-layout: fixed">
                <tr>
                  <td height="40" align="left"><a href="search.action?m=fronttosearch" class="blue14" target="_blank">文献数据库 </a></td>
                </tr>
                <tr>
                  <td height="40" align="left" class="lan12" style= "white-space:nowrap;overflow:hidden;text-overflow:ellipsis;">文献数据库涵盖了国内外核心期<br />刊的重要文献，主要选取了2000年以后发表的食品微生物类科技文献，涉及常见重要食品微生物550余种。数据库能够提供的关于文献的信息有：作者、文献题目，发表的期刊名称、卷刊号、发表时间、摘要等。</td>
                </tr>
              </table></td>
              <td>&nbsp;</td>
              <td><table width="180" border="0" cellspacing="0" cellpadding="0" style="table-layout: fixed">
                <tr>
                  <td height="40" align="left"><a href="struct.action?m=frontThreeStruct" class="blue14" target="_blank">三维结构</a></td>
                </tr>
                <tr>
                  <td height="40" align="left" class="lan12" style= "white-space:nowrap;overflow:hidden;text-overflow:ellipsis;">本平台三维结构数据库主要元数<br />据信息包括结构的名称、分类、MMDB和PDB的序列号，通过物种进行搜索，得到的检索结果中序列号与MMDB和PDB进行链接，了解相关序列的功能、相互作用、活性位点等。</td>
                </tr>
              </table></td>
            </tr>
          </table></td>
          <td width="5" valign="top"></td>
          <td width="126" valign="top" class="hei14"><table width="236" height="27" border="0" align="center" cellpadding="0" cellspacing="0" background="<%=request.getContextPath()%>/resources/images/indexMain/image/main (7).gif">
              <tr>
                <td align="left" class="bai14" style="padding-left:50px;">用户须知</td>
                 <td><a href="usage.action?m=frontToList" class="white12" target="_blank">更多</a></td>
              </tr>
            </table>
              <table width="100%" height="174" border="0" cellpadding="0" cellspacing="0" background="<%=request.getContextPath()%>/resources/images/indexMain/image/main (6).gif">
                <tr>
                  <td valign="middle">
                  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <c:forEach items="${usList}" var="tt" varStatus="i" begin="0">
                    <tr>
                      <td width="26%" height="40" align="center">&nbsp;</td>
                      <td width="74%" align="left"><a href="usage.action?m=frontGetUsageDetail&id=${tt.id}" class="blue14" target="_blank">${tt.title}</a> </td>
                    </tr>
                    </c:forEach>
                  </table>
                  </td>
                </tr>
            </table>
              <table width="236" height="27" border="0" align="center" cellpadding="0" cellspacing="0" background="<%=request.getContextPath()%>/resources/images/indexMain/image/main (5).gif">
                <tr>
                  <td align="left" class="bai14" style="padding-left:50px;">平台介绍</td>
                </tr>
              </table>
              <table width="100%" height="191" border="0" cellpadding="0" cellspacing="0" background="<%=request.getContextPath()%>/resources/images/indexMain/image/main (4).gif">
                <tr>
                  <td align="left" valign="top" class="lan12" style="padding:30px 20px;">&nbsp;&nbsp;&nbsp;&nbsp;运行维护平台提供的各类生物信息资源共享服务；发布或更新合作单位完成的新的共享资源；根据国内和国际生物技术的发展，及时开发整合具备自身特色的生物资源数据库、分析软件以及综合平台。</td>
                </tr>
              </table>
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td height="46" valign="bottom"><a href="http://www.cfmd.com.cn/wwwblast/blast.html" target="_blank"><img src="<%=request.getContextPath()%>/resources/images/indexMain/image/index201211_06.png" width="236" height="41" border="0" />
                  </a></td>
                </tr>
              </table>
              </td>
        </tr>
      </table>
    <img src="<%=request.getContextPath()%>/resources/images/indexMain/image/9137649.gif" width="960" height="95" />
    <table width="955" border="0" align="center" cellpadding="0" cellspacing="0" style="margin:5px;">
      <tr>
        <td width="232" height="218" valign="top" background="<%=request.getContextPath()%>/resources/images/indexMain/image/main (3).gif">
		<table width="210" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-bottom:10px;">
          <tr>
            <td width="141" height="40" align="left" class="blue14">推荐检测机构</td>
            <td width="69" align="right"><a href="testOrg.action?m=frontTestOrg" class="lan12" target="_blank">更多</a></td>
          </tr>
        </table>
          <table width="200" border="0" cellspacing="0" cellpadding="0" style= "table-layout:fixed;">
            <tr>
              <td width="17%" height="30" align="center"><img src="<%=request.getContextPath()%>/resources/images/indexMain/image/front_01.png" width="11" height="13" /></td>
              <td width="83%" align="left" style= "white-space:nowrap;overflow:hidden;text-overflow:ellipsis;"><a href="testOrg.action?m=frontTestOrgDetail&tid=4" class="lan12" target="_blank" title="国家食品质量监督检验中心">国家食品质量监督检验中心</a></td>
            </tr>
            <tr>
              <td width="17%" height="30" align="center"><img src="<%=request.getContextPath()%>/resources/images/indexMain/image/front_01.png" width="11" height="13" /></td>
              <td width="83%" align="left" class="lan12" style= "white-space:nowrap;overflow:hidden;text-overflow:ellipsis;"><a href="testOrg.action?m=frontTestOrgDetail&tid=16" class="lan12" target="_blank" title="国家粮油质量监督检验中心">国家粮油质量监督检验中心</a></td>
            </tr>
            <tr>
              <td width="17%" height="30" align="center"><img src="<%=request.getContextPath()%>/resources/images/indexMain/image/front_01.png" width="11" height="13" /></td>
              <td width="83%" align="left" class="lan12" style= "white-space:nowrap;overflow:hidden;text-overflow:ellipsis;"><a href="testOrg.action?m=frontTestOrgDetail&tid=62" class="lan12" target="_blank" title="国家加工食品质量监督检验中心">国家加工食品质量监督检验中心</a></td>
            </tr>
            <tr>
              <td width="17%" height="30" align="center"><img src="<%=request.getContextPath()%>/resources/images/indexMain/image/front_01.png" width="11" height="13" /></td>
              <td width="83%" align="left" class="lan12" style= "white-space:nowrap;overflow:hidden;text-overflow:ellipsis;"><a href="testOrg.action?m=frontTestOrgDetail&tid=22" class="lan12" target="_blank" title="中国检验检疫科学研究院综合检测中心">中国检验检疫科学研究院综合检测中心</a></td>
            </tr>
            <tr>
              <td width="17%" height="30" align="center"><img src="<%=request.getContextPath()%>/resources/images/indexMain/image/front_01.png" width="11" height="13" /></td>
              <td width="83%" align="left" class="lan12" style= "white-space:nowrap;overflow:hidden;text-overflow:ellipsis;"><a href="testOrg.action?m=frontTestOrgDetail&tid=36" class="lan12" target="_blank" title="北京市茶叶质量监督检验站">北京市茶叶质量监督检验站</a></td>
            </tr>
          </table>
          </td>
        <td width="5">&nbsp;</td>
        <td width="481" valign="top" background="<%=request.getContextPath()%>/resources/images/indexMain/image/main (2).gif"><table width="448" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-bottom:10px;">
          <tr>
            <td width="312" height="40" align="left" class="blue14">领域专家</td>
            <td width="136" align="right"><a href="expert.action?m=frontGetMoreExpert" class="lan12" target="_blank">更多</a></td>
          </tr>
        </table>
          <table width="96%" height="156" border="0" align="center" cellpadding="0" cellspacing="0">
          
            <tr>
            <c:forEach items="${expertList}" var="ex" begin="0" varStatus="i">
              <td width="115" height="96" align="center"><img src="<%=request.getContextPath()%>/expertimage/${ex.jpgname }.jpg" width="70" height="87" /></td>
             </c:forEach>
            </tr>
            <tr>
            <c:forEach items="${expertList}" var="ex" begin="0" varStatus="i">
              <td height="60" width="115" align="center" ><span class="blue14"><a href="expert.action?m=frontGetDetailExpert&eid=${ex.eid }" target="_blank">${ex.ename }</a></span><br /><span class="lan12" >${ex.professional}</span><br /></td>
              </c:forEach>
            </tr>
          </table></td>
        <td width="5">&nbsp;</td>
        <td width="232" valign="top" background="<%=request.getContextPath()%>/resources/images/indexMain/image/main (3).gif"><table width="210" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-bottom:10px;">
          <tr>
            <td width="141" height="40" align="left" class="blue14">友情链接</td>
          </tr>
        </table>
          <table width="190" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr></tr>
            <tr>
              <td width="81%" height="36" align="left" class="hei12">
              <label>
                <select name="select3" id="select3" style="width: 170px;"  onchange="javascript:window.open(this.options[this.selectedIndex].value);this.selectedIndex=0" class="wxinput2">
                <option value="">各省质量技术监督局</option> 
                <option 
              value="http://www.bjtsb.gov.cn">北京市质量技术监督局</option> 
                <option 
              value="http://www.tjjj.gov.cn">天津市质量技术监督局</option> 
                <option 
              value="http://www.hebqts.gov.cn">河北省质量技术监督局</option> 
                <option 
              value="http://www.bqts.gov.cn">山西省质量技术监督局</option> 
                <option 
              value="http://www.nmgzjj.gov.cn">内蒙古自治区质量技术监督局</option> 
                <option 
              value="http://www.12365.ln.cn">辽宁省质量技术监督局</option> 
                <option 
              value="http://www.jlqi.gov.cn">吉林省质量技术监督局</option> 
                <option 
              value="http://www.hljqts.gov.cn">黑龙江省质量技术监督局</option> 
                <option 
              value="http://www.shzj.gov.cn">上海市质量技术监督局</option> 
                <option 
              value="http://www.jsqts.gov.cn">江苏省质量技术监督局</option> 
                <option 
              value="http://www.zjbts.gov.cn">浙江省质量技术监督局</option> 
                <option 
              value="http://www.ahqi.gov.cn">安徽省质量技术监督局</option> 
                <option 
              value="http://www.fjqi.gov.cn">福建省质量技术监督局</option>
                <option 
              value="http://www.xmzjj.gov.cn">厦门市质量技术监督局</option> 
                <option 
              value="http://www.jxzj.gov.cn">江西省质量技术监督局</option> 
                <option 
              value="http://www.sdqts.gov.cn">山东省质量技术监督局</option> 
                <option 
              value="http://www.haqi.gov.cn">河南省质量技术监督局</option> 
                <option 
              value="http://www.hbzljd.gov.cn">湖北省质量技术监督局</option> 
                <option 
              value="http://www.hn315.gov.cn">湖南省质量技术监督局</option> 
                <option 
              value="http://www.gdqts.gov.cn">广东省质量技术监督局</option> 
                <option 
              value="http://www.szscjg.gov.cn">深圳市市场监督管理局</option> 
                <option 
              value="http://www.gxqts.gov.cn">广西自治区质量技术监督局</option> 
                <option 
              value="http://qtsb.hainan.gov.cn">海南省质量技术监督局</option> 
                <option 
              value="http://www.cqzj.gov.cn">重庆市质量技术监督局</option> 
                <option 
              value="http://www.sczj.gov.cn">四川省质量技术监督局</option> 
                <option 
              value="http://www.gzqts.gov.cn">贵州省质量技术监督局</option> 
                <option 
              value="http://www.ynqi.gov.cn">云南省质量技术监督局</option> 
                <option 
              value="http://www.xzzj.gov.cn">西藏自治区质量技术监督局</option> 
                <option 
              value="http://www.snqi.gov.cn">陕西省质量技术监督局</option> 
                <option 
              value="http://www.gszl.gov.cn">甘肃省质量技术监督局</option> 
                <option 
              value="http://www.qhzljd.gov.cn">青海省质量技术监督局</option> 
                <option 
              value="http://www.nxzj.gov.cn">宁夏自治区质量技术监督局</option> 
                <option 
              value="http://www.xjnewqi.gov.cn">新疆自治区质量技术监督局</option>
      </select>

              </label>
              </td>
            </tr>
            <tr>
              <td width="81%" height="36" align="left" class="hei12">
              <select name="select3" id="select3" style="width: 170px;"  onchange="javascript:window.open(this.options[this.selectedIndex].value);this.selectedIndex=0" class="wxinput2">
                <option value="">直属出入境检验检疫局</option> 
                <option value="http://www.bjciq.gov.cn" >北京出入境检验检疫局</option> 
                <option value="http://www.tjciq.gov.cn" >天津出入境检验检疫局</option> 
                <option value="http://www.heciq.gov.cn" >河北出入境检验检疫局</option> 
                <option value="http://www.sxciq.gov.cn" >山西出入境检验检疫局</option> 
                <option value="http://www.nmciq.gov.cn" >内蒙古出入境检验检疫局</option> 
                <option value="http://www.lnciq.gov.cn" >辽宁出入境检验检疫局</option> 
                <option value="http://www.jlciq.gov.cn" >吉林出入境检验检疫局</option> 
                <option value="http://www.hljciq.gov.cn" >黑龙江出入境检验检疫局</option> 
                <option value="http://www.shciq.gov.cn" >上海出入境检验检疫局</option> 
                <option value="http://www.jsciq.gov.cn" >江苏出入境检验检疫局</option> 
                <option value="http://www.ziq.gov.cn" >浙江出入境检验检疫局</option> 
                <option value="http://www.nbciq.gov.cn" >宁波出入境检验检疫局</option> 
                <option value="http://www.ahciq.gov.cn" >安徽出入境检验检疫局</option> 
                <option value="http://www.fjciq.gov.cn" >福建出入境检验检疫局</option> 
                <option value="http://www.xmciq.gov.cn" >厦门出入境检验检疫局</option> 
                <option value="http://www.jxciq.gov.cn" >江西出入境检验检疫局</option> 
                <option value="http://www.sdciq.gov.cn" >山东出入境检验检疫局</option> 
                <option value="http://www.haciq.gov.cn" >河南出入境检验检疫局</option> 
                <option value="http://www.hbciq.gov.cn" >湖北出入境检验检疫局</option> 
                <option value="http://www.hnciq.gov.cn" >湖南出入境检验检疫局</option> 
                <option value="http://www.gdciq.gov.cn" >广东出入境检验检疫局</option> 
                <option value="http://www.szciq.gov.cn" >深圳出入境检验检疫局</option> 
                <option value="http://www.zhciq.gov.cn" >珠海出入境检验检疫局</option> 
                <option value="http://www.hiciq.gov.cn" >海南出入境检验检疫局</option> 
                <option value="http://www.gxciq.gov.cn" >广西出入境检验检疫局</option> 
                <option value="http://www.scciq.gov.cn" >四川出入境检验检疫局</option> 
                <option value="http://www.cqciq.gov.cn" >重庆出入境检验检疫局</option> 
                <option value="http://www.gzciq.gov.cn" >贵州出入境检验检疫局</option> 
                <option value="http://www.ynciq.gov.cn" >云南出入境检验检疫局</option> 
                <option value="http://www.xzciq.gov.cn" >西藏出入境检验检疫局</option> 
                <option value="http://www.snciq.gov.cn" >陕西出入境检验检疫局</option> 
                <option value="http://www.gsciq.gov.cn" >甘肃出入境检验检疫局</option> 
                <option value="http://www.qhciq.gov.cn" >青海出入境检验检疫局</option> 
                <option value="http://www.nxciq.gov.cn" >宁夏出入境检验检疫局</option> 
                <option value="http://www.xjciq.gov.cn" >新疆出入境检验检疫局</option>
      </select>
                                          </td>
            </tr>
            <tr>
              <td width="81%" height="36" align="left" class="hei12">
                <select style="width: 170px;"  onchange="javascript:window.open(this.options[this.selectedIndex].value);this.selectedIndex=0" class="wxinput2" name=select2> 
                <option value="">各地食品药品管理机构</option> 
                <option value="http://www.bjda.gov.cn">北京市药品监督管理局</option> 
                <option value="http://www.tda.gov.cn">天津市食品药品监督管理局</option> 
                <option value="http://www.hebfda.gov.cn/webportal/portal.po">河北省食品药品监督管理局</option> 
                <option value="http://www.shxda.gov.cn">山西省食品药品监督管理局</option> 
                <option value="http://www.nmfda.gov.cn/CL0145">内蒙古自治区食品药品监督管理局</option> 
                <option value="http://www.lnfda.gov.cn/CL0001/index.html">辽宁省食品药品监督管理局</option> 
                <option value="http://www.jlda.gov.cn">吉林省食品药品监督管理局</option> 
                <option value="http://www.hljda.gov.cn">黑龙江省食品药品监督管理局</option> 
                <option value="http://www.shfda.gov.cn/gb/node2/node3/index.html">上海市食品药品监督管理局</option> 
                <option value="http://www.jsfda.gov.cn">江苏省食品药品监督管理局</option> 
                <option value="http://www.zjfda.gov.cn">浙江省食品药品监督管理局</option> 
                <option value="http://www.ada.gov.cn">安徽省食品药品监督管理局</option> 
                <option value="http://www.fjfda.gov.cn">福建省食品药品监督管理局</option> 
                <option value="http://www.jxda.gov.cn">江西省食品药品监督管理局</option> 
                <option value="http://www.sdfda.gov.cn">山东省食品药品监督管理局</option> 
                <option value="http://www.hda.gov.cn/CL0001">河南省食品药品监督管理局</option> 
                <option value="http://www.hubfda.gov.cn/structure/index.htm">湖北省食品药品监督管理局</option> 
                <option value="http://www.hn-fda.gov.cn">湖南省食品药品监督管理局</option> 
                <option value="http://www.gdda.gov.cn/publicfiles/business/htmlfiles/gdfda/index.htm">广东省食品药品监督管理局</option> 
                <option value="http://www.gxfda.gov.cn">广西壮族自治区食品药品监督管理局</option> 
                <option value="http://www.hifda.gov.cn">海南食品药品监督管理局</option> 
                <option value="http://www.cqda.gov.cn">重庆市食品药品监督管理局</option> 
                <option value="http://www.scfda.gov.cn/CL0182">四川省食品药品监督管理局</option> 
                <option value="http://www.gzhfda.gov.cn">贵州省食品药品监督管理局</option> 
                <option value="http://www.yp.yn.gov.cn/nfda/72057594037927936/index.html">云南省食品药品监督管理局</option> 
                <option value="http://www.xizangda.gov.cn">西藏自治区食品药品监督管理局</option> 
                <option value="http://www.sxfda.gov.cn/CL0001/index.html">陕西省食品药品监督管理局</option> 
                <option value="http://www.gsda.gov.cn">甘肃省食品药品监督管理局</option> 
                <option value="http://www.sdaqh.gov.cn/CL0184/index.html">青海省食品药品监督管理局</option> 
                <option value="http://www.xjda.gov.cn/CL0012/index.html">新疆维吾尔自治区食品药品监督管理局</option> 
                <option value="http://www.nxfda.gov.cn/directory/web/WS01/CL0001/index.html">宁夏回族自治区食品药品监督管理局</option>
                </select>
              </td>
            </tr>
          </table></td>
      </tr>
    </table>
    <table width="955" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-bottom:10px;">
      <tr>
        <td width="955" height="154" align="center" valign="top" background="<%=request.getContextPath()%>/resources/images/indexMain/image/main (16).gif">
        <table width="930" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="312" height="35" align="left" class="blue14">共建单位</td>
            <!-- <td width="136" align="right"><a href="#" class="lan12">更多</a></td> -->
          </tr>
        </table>
          <table width="100%" height="117" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td align="center"><a href="http://www.aqsiq.gov.cn/" target="_blank"><img src="<%=request.getContextPath()%>/resources/images/indexMain/image/link01.jpg" width="159" height="51" border="0" /></a></td>
              <td align="center"><a href="http://www.cbi.pku.edu.cn/chinese/" target="_blank"><img src="<%=request.getContextPath()%>/resources/images/indexMain/image/link02.jpg" width="152" height="51" border="0" /></a></td>
              <td align="center"><a href="http://www.im.cas.cn/" target="_blank"><img src="<%=request.getContextPath()%>/resources/images/indexMain/image/link03.jpg" width="182" height="50" border="0" /></a></td>
              <td align="center"><a href="http://foodsci.jiangnan.edu.cn/" target="_blank"><img src="<%=request.getContextPath()%>/resources/images/indexMain/image/link04.jpg" width="167" height="49" border="0" /></a></td>
            </tr>
          </table>
          </td>
      </tr>
    </table>
    </td>
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