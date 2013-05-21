<%@ page language="java" pageEncoding="gbk" isELIgnored="false"
	import="java.lang.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title>中国食品微生物数据共享平台</title>
<link href="<%=request.getContextPath()%>/resources/images/gene/css/css.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" id='skin' type="text/css" href="<%=request.getContextPath()%>/resources/component/ymPrompt/skin/qq/ymPrompt.css" />
<!-- tr背景鼠标经过效果-->
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
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.6.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/component/ymPrompt/ymPrompt.js"></script>
<script type="text/javascript">
	$(function(){
		
		/*if($("#d1_2").focus()){
			$("#d1_2").val("");
		};*/
		
		
		$("#d1_1").click(function(){
			var geneInfo = $("#d1_2").val();
			var reg = /^[a-zA-Z\s]+$/;
			if(geneInfo==""|| !reg.test(geneInfo)){
				ymPrompt.alert('请输入物种拉丁名！');
				//alert("");
			}else{
				window.location.href="gene.action?m=frontGeneList&geneInfo="+geneInfo;
			}
			 
		});
		
		
		$("#d2_1").click(function(){
			
			var NCBISpeciesID = "";
			var chromosomeRef ="";
			var genID = "";
			var geneName ="";
			
			//select的选项类型Speciesname,chref,geneId,geneName
			var dd_1 = $("#dd_1").val();
			var dd_2 = $("#dd_2").val();
			var dd_3 = $("#dd_3").val();
			var dd_4 = $("#dd_4").val();
			
			//条件之间的关系：and,or,not
			var key_1 = $("#key_1").val();
			var key_2 = $("#key_2").val();
			var key_3 = $("#key_3").val();
			
			
			
			if(dd_1=="NCBISpeciesID"){
				//获取输入的值
				NCBISpeciesID = $("#ddd_1").val();
			}else if(dd_1=="ChromosomeRef"){
				chromosomeRef = $("#ddd_1").val();
			}else if(dd_1=="GenID"){
				genID = $("#ddd_1").val();
			}else if(dd_1=="GeneName"){
				geneName = $("#ddd_1").val();
			}
			
			
			if(dd_2=="NCBISpeciesID"){
				NCBISpeciesID = $("#ddd_2").val();
			}else if(dd_2=="ChromosomeRef"){
				chromosomeRef = $("#ddd_2").val();
			}else if(dd_2=="GenID"){
				genID = $("#ddd_2").val();
			}else if(dd_2=="GeneName"){
				geneName = $("#ddd_2").val();
			}
			
			
			if(dd_3=="NCBISpeciesID"){
				NCBISpeciesID = $("#ddd_3").val();
			}else if(dd_3=="ChromosomeRef"){
				chromosomeRef = $("#ddd_3").val();
			}else if(dd_3=="GenID"){
				genID = $("#ddd_3").val();
			}else if(dd_3=="GeneName"){
				geneName = $("#ddd_3").val();
			}
			
			
			if(dd_4=="NCBISpeciesID"){
				NCBISpeciesID = $("#ddd_4").val();
			}else if(dd_4=="ChromosomeRef"){
				chromosomeRef = $("#ddd_4").val();
			}else if(dd_4=="GenID"){
				genID = $("#ddd_4").val();
			}else if(dd_4=="GeneName"){
				geneName = $("#ddd_4").val();
			}
			
			if(dd_1==dd_2||dd_1==dd_3||dd_1==dd_4||dd_2==dd_3||dd_2==dd_4||dd_3==dd_4){
				ymPrompt.alert('请选择不同的类型!');
			}else if($("#ddd_1").val()==""&&$("#ddd_2").val()==""&&$("#ddd_3").val()==""&&$("#ddd_4").val()==""){
				ymPrompt.alert('请输入内容!');
			}else{
				window.location.href="gene.action?m=frontAdGeneList&NCBISpeciesID="+NCBISpeciesID+"&chromosomeRef="+chromosomeRef+"&genID="+genID+"&geneName="+geneName+"&key_1="+key_1+"&key_2="+key_2+"&key_3="+key_3;
			}
		});
	});
	
	
/*	function MyFocus(){
			if($("#d1_2").val()=="输入种名"){
				$("#d1_2").val("");
			}
		};
		
		function MyBlur(){
			if($("#d1_2").val()==""){
				$("#d1_2").val("输入种名");
			}
		};*/
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
    <td height="34" background="<%=request.getContextPath()%>/resources/images/gene/images/top-bg.jpg">&nbsp;</td>
  </tr>
</table>
<table width="965" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="25" valign="top" class="hei12">首页-&gt;基础数据库-&gt;基因数据</td>
  </tr>
</table>
<table width="965" height="32" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="10%">&nbsp;</td>
    <td width="84%"><table width="360" border="0" cellspacing="1"  class="tag1">
      <tr>
        <td height="28" align="center" bgcolor="#cccccc" class="tag1_01"  onmouseover="tihuan(this)" ><span class="bai14">标准检索</span></td>
        <td align="center" bgcolor="#CCCCCC" onmouseover="tihuan(this)"><span class="bai14">高级检索</span></td>
      </tr>
    </table></td>
  </tr>
</table>
<table width="965" height="65" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="cccccc">
  <tr>
    <td valign="top" bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="tag1_04" id="obody">
      <tr>
        <td class="hei14" style="padding:45px 55px;">
		  <table border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="400"  align="center" ><span class="hei14" >
              <input name="geneInfo" class="TxtUserNameCssClass" id="d1_2" value="" style="padding-bottom:6px; height:24px;border:solid 1px #CCCCCC; "/>
            请输入物种拉丁名
            </span>
            </td>
            <td width="300" style=""><a href="javascript:;" id="d1_1"><img src="<%=request.getContextPath()%>/resources/images/gene/images/search-menu.jpg" width="102" height="35" border="0" /></a>&nbsp;&nbsp;<a href="indexMain.action?m=frontIndexMain"><img src="<%=request.getContextPath()%>/resources/images/gene/images/login-cancle.jpg" width="102" height="35" border="0" /></a>
            </td>
            </tr>
        </table>
        <br/>
        <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#FEF4B1">
                <tr>
                  <td bgcolor="#FFFFEE"  class="hei14" style="padding:15px;"><p class="stitle" >数据库简介</p>
                      <p class="hei12"> &nbsp;&nbsp;&nbsp;&nbsp;基因序列数据库专门存储完整测序的食品微生物基因序列信息的数据库，除了提供基因序列ID、染色体ID、起始位点、物种ID、产物ID等基本信息外，还提供具体的序列以及相关文献等信息。</p></td>
                </tr>
            </table>
		</td>
      </tr>
      <tr style="display: none">
        <td   class="hei14" style="padding:15px;">
        <table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr  onmousemove="javascript:changebg(this,0)" style="backgroud-color:#f4f4f4" onmouseout="javascript:changebg(this,1)">
            <td width="6%">&nbsp;</td>
            <td width="94%" height="50">&nbsp;</td>
          </tr>
          <tr onmousemove="javascript:changebg(this,0)" style="backgroud-color:#f4f4f4" onmouseout="javascript:changebg(this,1)">
            <td>&nbsp;</td>
            <td height="50" style="padding-bottom: 5px;" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <select name="select3" id="dd_1" style="height:30px; width:180px; border:solid 1px #CCCCCC; ">
                      <option value="NCBISpeciesID">NCBISpeciesID</option>
                      <option value="ChromosomeRef">ChromosomeRef</option>
                      <option value="GenID">GenID</option>
                      <option value="GeneName">GeneName</option>
                  </select>
                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input class="TxtUserNameCssClass" id="ddd_1"  style="padding-bottom:6px; height:24px;border:solid 1px #CCCCCC; " name="0122" />
            </td>
          </tr>
          <tr onmousemove="javascript:changebg(this,0)" style="backgroud-color:#f4f4f4" onmouseout="javascript:changebg(this,1)">
            <td>&nbsp;</td>
            <td height="50"><select name="select4" id="key_1" style="height:30px; border:solid 1px #CCCCCC; ">
              <option value="and">AND</option>
              <option value="or">OR</option>
              <!--   <option value="not">NOT</option>  -->
            </select>
                    <select name="select5" id="dd_2" style="height:30px; width:180px; border:solid 1px #CCCCCC; ">
                      <option value="ChromosomeRef">ChromosomeRef</option>
                      <option value="GenID">GenID</option>
                      <option value="NCBISpeciesID">NCBISpeciesID</option>
                      <option value="GeneName">GeneName</option>
                  </select>
                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input class="TxtUserNameCssClass" id="ddd_2" name="012" style="padding-bottom:6px; height:24px;border:solid 1px #CCCCCC; " /></td>
          </tr>
          <tr onmousemove="javascript:changebg(this,0)" style="backgroud-color:#f4f4f4" onmouseout="javascript:changebg(this,1)">
            <td>&nbsp;</td>
            <td height="50"><select name="select6" id="key_2" style="height:30px; border:solid 1px #CCCCCC; ">
              <option value="and">AND</option>
              <option value="or">OR</option>
             <!--   <option value="not">NOT</option>  -->
            </select>
                    <select name="select6" id="dd_3" style="height:30px; width:180px; border:solid 1px #CCCCCC; ">
                      <option value="GeneName">GeneName</option>
                      <option value="ChromosomeRef">ChromosomeRef</option>
                      <option value="GenID">GenID</option>
                      <option value="NCBISpeciesID">NCBISpeciesID</option>
                  </select>
                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input class="TxtUserNameCssClass" id="ddd_3" name="01" style="padding-bottom:6px; height:24px;border:solid 1px #CCCCCC; "/>            </td>
          </tr>
          <tr onmousemove="javascript:changebg(this,0)" style="backgroud-color:#f4f4f4" onmouseout="javascript:changebg(this,1)">
            <td>&nbsp;</td>
            <td height="50"><select name="select7" id="key_3" style="height:30px; border:solid 1px #CCCCCC; ">
              <option value="and">AND</option>
              <option value="or">OR</option>
              <!--   <option value="not">NOT</option>  -->
            </select>
                    <select name="select7" id="dd_4" style="height:30px; width:180px; border:solid 1px #CCCCCC; ">
                      <option value="GenID">GenID</option>
                      <option value="ChromosomeRef">ChromosomeRef</option>
                      <option value="NCBISpeciesID">NCBISpeciesID</option>
                      <option value="GeneName">GeneName</option>
                  </select>
                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input class="TxtUserNameCssClass" id="ddd_4" name="907" style="padding-bottom:6px; height:24px;border:solid 1px #CCCCCC; "/>            </td>
          </tr>
          <tr>
            <td align="center">&nbsp;</td>
            <td height="90" align="center"><a href="javascript:;" id="d2_1"><img src="<%=request.getContextPath()%>/resources/images/gene/images/search-menu.jpg" width="102" height="35" border="0" /></a>&nbsp;<a href="indexMain.action?m=frontIndexMain"><img src="<%=request.getContextPath()%>/resources/images/gene/images/login-cancle.jpg" width="102" height="35" border="0"/></a></td>
          </tr>
        </table>
              <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#FEF4B1">
                <tr>
                  <td bgcolor="#FFFFEE"  class="hei14" style="padding:15px;"><p class="stitle" >数据库简介</p>
                      <p class="hei12"> &nbsp;&nbsp;&nbsp;&nbsp;基因序列数据库专门存储完整测序的食品微生物基因序列信息的数据库，除了提供基因序列ID、染色体ID、起始位点、物种ID、产物ID等基本信息外，还提供具体的序列以及相关文献等信息。</p></td>
                </tr>
            </table>
            </td>
      </tr>
    </table></td>
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
