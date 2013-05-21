<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="com.nbw.common.SysParameter"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="${csscssFile}" rel="stylesheet" type="text/css" />
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
						src="fmpt_top.jsp" width="100%" height="100%" scrolling="no"></iframe></td>
  </tr>
</table>
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0" style="margin-bottom: 5px;">
			<tr>
				<td height="34" valign="bottom" background="${skinPath}images/top-bg.jpg">
					<table width="965" border="0" align="center" cellpadding="0"
						cellspacing="0" style="margin-bottom: 5px;">
						<tr>
							<td width="34" height="25" align="center" valign="middle"
								class="hei12">
								<img src="${skinPath}images/gif-0661.gif" width="13" height="11" />
							</td>
							<td width="931" align="left" valign="bottom" class="hei12">
								首页-&gt;关于我们
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<table width="965" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="39" height="35">
					<img src="${skinPath}images/leftmenu-bg01.jpg" width="39" height="35" />
				</td>
				<td width="913" background="${skinPath}images/leftmenu-bg02.jpg" class="hei16B">
					关于我们
				</td>
				<td width="13">
					<img src="${skinPath}images/leftmenu-bg03.jpg" width="13" height="35" />
				</td>
			</tr>
		</table>
		<table width="965" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr></tr>
			<tr>
				<td valign="top" class="hei14"
					style="border: solid; border-width: 0px 1px 1px 1px; border-color: #cbcbcb; padding: 25px;">
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;这是一个食品微生物蛋白质平台，主要有两大数据库：基础数据库和特色数据库。主要功能有蛋白质指纹图谱比对，在线blast比对，菌种的基因、蛋白、rna序列介绍，以及相关文献的搜索。
						<br />
						<strong>基础数据库：</strong>基因序列数据库、蛋白质序列数据库、RNA序列数据库、文献数据库、三维结构数据库。
						<br />
						<strong>特色数据库：</strong>微生物目录数据库、指纹图谱数据库。
						<br />
						<strong>蛋白质指纹图谱比对模块：</strong>用户可以上传自己的相关图谱文件，然后可以单图显示，也可多图显示。
						<br />
						<strong>目录数据库：</strong>界门纲目科属种的树形结构。
						<br />
						<strong>在线blast比对模块：</strong>用户可以在线输入基因或者蛋白质相关序列，然后进行相似度比对。
						<br />
						<strong>基因、蛋白、rna模块：</strong>用户可以输入常见微生物的种名进行搜索，结果中会显示对应的列表和其详细信息。
						<br />
						<strong>文献模块：</strong>用户可以进行中文文献和英文文献的搜索。
						<br />
						<strong>三级结构模块：</strong>用户可以通过菌种名查看相关详细信息。
						<br />
						<strong>快检技术模块：</strong>用户可以选择相应的技术名称，查看此技术的应用范围等信息。
						<br />
						<strong>检测机构模块：</strong>用户可以查看不同省份的检测机构详情。
						<br />
						<strong>领域专家模块：</strong>用户可以了解不同领域的专家详情。
					</p>
					<p></p>
				</td>
			</tr>
		</table>
		<table align="center" width="100%">
			<tr>
				<td height="120">
					<iframe frameborder=0 scrolling="no" name="topFrame" width="100%"
						height="100%" src="./fmpt_bottom.jsp"></iframe>
				</td>
			</tr>
		</table>

	</body>
</html>