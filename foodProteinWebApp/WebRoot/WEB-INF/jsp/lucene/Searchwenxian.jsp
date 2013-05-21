<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

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
			
			<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath() %>/resources/component/My97DatePicker/WdatePicker.js"></script> 
		<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/validata.js"></script>
			
			
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
function changefirst()
{
 document.getElementById('first1').name = document.getElementById('first').value;
}
function changesecond()
{

document.getElementById('second2').name = document.getElementById('second').value;

}
function changethird()
{

document.getElementById('third3').name = document.getElementById('third').value;
}

function changefour()
{

document.getElementById('four4').name = document.getElementById('four').value;
}


</script>

<script type="text/javascript">
	function tanchu()
	{
	var v1=document.getElementById('first1');
	var v2=document.getElementById('second2');
	var v3=document.getElementById('third3');
	
	var v4=document.getElementById('four4');
	
	
	
	if(v1.name=="" && v2.name   ==   "" && v3.name   ==   ""  && v4.name   ==  "")
	{
	
	ymPrompt.alert("请选择相应的匹配字段并输入相应的关键字");
	return false;
	}
	else
	{
	if(v1.name!=""&&v1.value=="")
	{
	ymPrompt.alert(v1.name+"不能为空");
	return false;
	}
	if(v2.name!=""&&v2.value=="")
	{
	ymPrompt.alert(v2.name+"不能为空");
	return false;
	}
	if(v3.name!=""&&v3.value=="")
	{
	ymPrompt.alert(v3.name+"不能为空");
	return false;
	}
	if(v4.name!=""&&v4.value=="")
	{
	ymPrompt.alert(v4.name+"不能为空");
	return false;
	}
	else
	{
	document.form2.submit();
	return true;
	
	}
	}
	}



</script>




		<script type="text/javascript"
			src="<%=request.getContextPath()%>/resources/js/jquery-1.6.2.js"></script>
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/ymPrompt/ymPrompt.js"></script>

		<script type="text/javascript">
	$(function(){
		
		var flag="one";
		 var radio = $('input[type="radio"]');
		radio.click(function()
  		{
     	flag=($(this).attr("value"));   //取值 
	 	
	 	});
		
		if($("guanjianzi").focus()){
			$("#guanjianzi").val("");
		};
		$("#d1_1").click(function(){
			var searchWord = $("#guanjianzi").val();
			if(searchWord==""||searchWord=="输入搜索关键字"){
		
				ymPrompt.alert("请输入搜索关键字！！！");
			}else{
				if(flag=='one')
				{
				window.location.href="search.action?m=fronttoload&searchWord="+searchWord;
				
				}
				else
				{
				window.location.href="search.action?m=frontToLoadOne&searchWord="+encodeURI(encodeURI(searchWord));
				}
			}
		});
		});
		
</script>
<script>
function reset()
{
	var v1=document.getElementById('guanjianzi');
	v1.value="";
}
function reset2()
{
	var v1=document.getElementById('first1');
	var v2=document.getElementById('second2');
	var v3=document.getElementById('third3');
	var v4=document.getElementById('four4');
	var v5=document.getElementById('data1');
	var v6=document.getElementById('data2');
	v1.value="";
	v2.value="";
	v3.value="";
	v4.value="";			
	v5.value="";
	v6.value="";





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
<table width="965" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-bottom:5px;">
  <tr>
    <td height="34" valign="bottom" background="<%=request.getContextPath()%>/resources/images/expert/image/top-bg.jpg"><table width="965" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-bottom:5px;">
      <tr>
        <td width="34" height="25" align="center" valign="middle" class="hei12"><img src="<%=request.getContextPath()%>/resources/images/expert/image/gif-0661.gif" width="13" height="11" /></td>
        <td width="931" align="left" valign="bottom" class="hei12">首页-&gt;基础数据库-&gt;文献数据库</td>
      </tr>
    </table></td>
  </tr>
</table>


		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="34" background="${skinPath}images/lucene/top-bg.jpg">
					&nbsp;
				</td>
			</tr>
		</table>
		<table width="960" height="32" border="0" align="center"
			cellpadding="0" cellspacing="0">
			<tr>
				<td width="10%">
					&nbsp;
				</td>
				<td width="84%">
					<table width="300" border="0" cellspacing="1" class="tag1">
						<tr>
							<td height="28" align="center" bgcolor="#cccccc" class="tag1_01"
								onmouseover="tihuan(this)">
								<span class="bai14">标准检索</span>
							</td>
							<td align="center" bgcolor="#CCCCCC" onmouseover="tihuan(this)">
								<span class="bai14">高级检索</span>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>

		<table width="960" height="65" border="0" align="center"
			cellpadding="0" cellspacing="1" bgcolor="cccccc">
			<tr>
				<td valign="top" bgcolor="#FFFFFF">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="tag1_04" id="obody">

						<tr>
							<td class="hei14" style="padding: 45px 15px 15px 180px;" />
								<label>
									<input type="radio" name="onlyone" value="one" checked="checked"/>
								</label>
								&nbsp;英文文献
								<label>
									<input type="radio" name="onlyone" value="two" />
								</label>
								&nbsp;中文文献
								<br />
								<input class="TxtUserNameCssClass" id="guanjianzi"
									value="输入搜索关键字"
									style="padding-bottom: 6px; height: 24px; border: solid 1px #CCCCCC; color: #000000" />
								<br />
								<br />
							<img src="${skinPath}images/lucene/search-menu.jpg" width="102" id="d1_1"
										height="35" id="search"/>
								<img src="${skinPath}images/lucene/login-cancle.jpg" width="102"
									height="35"  onclick="reset()" />
							</td>
						</tr>
						<tr style="display: none">
							<td class="hei14" style="padding: 15px;">
							
							<form name="form2" action="search.action?m=frontadvancesearch"
							method="post">
								<table width="90%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr onmousemove=javascript:changebg(this,0)
										style="backgroud-color: #f4f4f4"
										onmouseout=javascript:changebg(this,1)>
										<td width="6%">
											&nbsp;
										</td>
										<td width="94%" height="50">
											&nbsp;
										</td>
									</tr>
									<tr onmousemove="javascript:changebg(this,0)"
										style="backgroud-color: #f4f4f4"
										onmouseout="javascript:changebg(this,1)">
										<td>
											&nbsp;
										</td>
										<td height="50">
											<label>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<input type="radio" name="onlyone" value="one" checked="checked"/>
											</label>
											&nbsp;英文文献
											<label>
												<input type="radio" name="onlyone" value="two" />
											</label>
											&nbsp;中文文献
										</td>
									</tr>
									<tr onmousemove="javascript:changebg(this,0)"
										style="backgroud-color: #f4f4f4"
										onmouseout="javascript:changebg(this,1)">
										<td>
											&nbsp;
										</td>
										<td height="50">
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<select onchange="changefirst()" id="first" name="select3"
												style="height: 30px; width: 180px; border: solid 1px #CCCCCC; color: #000000">
												<option value="">
													All Field
												</option>
												<option value="Title">
													Title
												</option>
												<option value="Author">
													Author
												</option>
												<option value="Abstract">
													Abstract
												</option>
												<option value="Journal">
													Journal
												</option>
											</select>
											<input id="first1" class="TxtUserNameCssClass"  type="text"
												maxlength="50" name=""
												style="height: 25px; width: 180px; border: solid 1px #CCCCCC; color: #000000" />
										</td>
									</tr>
									<tr onmousemove="javascript:changebg(this,0)"
										style="backgroud-color: #f4f4f4"
										onmouseout="javascript:changebg(this,1)">
										<td>
											&nbsp;
										</td>
										<td height="50">
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<select onchange="changesecond()" id="second" name="select5"
												style="height: 30px; width: 180px; border: solid 1px #CCCCCC; color: #000000">
												<option value="">
													All Field
												</option>
												<option value="Title">
													Title
												</option>
												<option value="Author">
													Author
												</option>
												<option value="Abstract">
													Abstract
												</option>
												<option value="Journal">
													Journal
												</option>
											</select>
											<input id="second2" class="TxtUserNameCssClass" 
												maxlength="50" name=""
												style="height: 25px; width: 180px; border: solid 1px #CCCCCC; color: #000000" />
										</td>
									</tr>
									<tr onmousemove="javascript:changebg(this,0)"
										style="backgroud-color: #f4f4f4"
										onmouseout="javascript:changebg(this,1)">
										<td>
											&nbsp;
										</td>
										<td height="50">
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<select onchange="changethird()" id="third" name="select6"
												style="height: 30px; width: 180px; border: solid 1px #CCCCCC; color: #000000">
												<option value="">
													All Field
												</option>
												<option value="Title">
													Title
												</option>
												<option value="Author">
													Author
												</option>
												<option value="Abstract">
													Abstract
												</option>
												<option value="Journal">
													Journal
												</option>
											</select>
											<input id="third3" class="TxtUserNameCssClass"
												maxlength="50" name=""
												style="height: 25px; width: 180px; border: solid 1px #CCCCCC; color: #000000" />
										</td>
									</tr>
									<tr onmousemove="javascript:changebg(this,0)"
										style="backgroud-color: #f4f4f4"
										onmouseout="javascript:changebg(this,1)">
										<td>
											&nbsp;
										</td>
										<td height="50">
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<select onchange="changefour()" id="four" name="select7"
												style="height: 30px; width: 180px; border: solid 1px #CCCCCC; color: #000000">
												<option value="">
													All Field
												</option>
												<option value="Title">
													Title
												</option>
												<option value="Author">
													Author
												</option>
												<option value="Abstract">
													Abstract
												</option>
												<option value="Journal">
													Journal
												</option>
											</select>
											<input id="four4" class="TxtUserNameCssClass" 
												maxlength="50" name=""
												style="height: 25px; width: 180px; border: solid 1px #CCCCCC; color: #000000" />
										</td>
									</tr>
									<tr onmousemove="javascript:changebg(this,0)"
										style="backgroud-color: #f4f4f4"
										onmouseout="javascript:changebg(this,1)">
										<td bgcolor="f4f4f4" class="hei12">
											&nbsp;
										</td>
										<td height="70" bgcolor="f4f4f4" class="hei12">
											&nbsp;&nbsp;&nbsp;出版日期&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="text" id="data1" name="StartDate"  value="${mydate}" class="Wdate"  onFocus="WdatePicker({isShowWeek:true})" />
											至
											<input type="text" id="data2" name="EndDate"  value="${myenddate}" class="Wdate"  onFocus="WdatePicker({isShowWeek:true})" />
										</td>
									</tr>
									<tr>
										<td align="center">
											&nbsp;
										</td>
										<td height="90" align="center">
											<input type="image"
												src="${skinPath}images/lucene/search-menu.jpg" width="102"
												height="35"  onclick="return tanchu()"/>
											&nbsp;
											<img src="${skinPath}images/lucene/login-cancle.jpg" width="102"
									height="35"  onclick="reset2()" />
										</td>
									</tr>

								</table>
								
									</form>

							</td>
						</tr>

					</table>
					<table width="100%" border="0" cellpadding="0" cellspacing="1"
						bgcolor="#FEF4B1">
						<tr>
							<td bgcolor="#FFFFEE" class="hei14" style="padding: 15px;">
								<p class="stitle">
									数据库简介
								</p>
								<p class="hei12">
									&nbsp;&nbsp;&nbsp;&nbsp;文献数据库：文献数据库涵盖了国内外核心期刊的重要文献，主要选取了2000年以后发表的食品微生物类科技文献，涉及常见重要食品微生物550余种。数据库能够提供的关于文献的信息有：作者、文献题目，发表的期刊名称、卷刊号、发表时间、摘要等。
								</p>
							</td>
						</tr>
					</table>					
				</td>
			</tr>
		</table>
		<br />
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					&nbsp;
				</td>
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
