<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>实名制质量信用信息化平台</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" id='skin' type="text/css"
			href="<%=request.getContextPath()%>/resources/component/ymPrompt/skin/simple_gray/ymPrompt.css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/resources/js/jquery-1.6.2.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/ymPrompt/ymPrompt.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/resources/images/zzjgcx/images/skin.css">
		<script type="text/javascript">
	function formsubmit()
	{
	
	
	var select = document.getElementsByName("select2")[0];
	var filetype=select.options[select.selectedIndex].value; 
	var filename=document.getElementById("filenameid").value;
	
	
	if(filetype!=''&&filename!='')
	{
	document.getElementById("filetypeid").value=filetype;
	
	
	document.form1.submit();
	
	}
	else
	{
	ymPrompt.alert("请选择输入数据类型和上传文件");
	
	}
	
	}
	</script>
	</head>

	<body>

		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0"
			background="<%=request.getContextPath()%>/resources/images/back/images/man-info_14.png">
			<tr>
				<td width="11">
					<img
						src="<%=request.getContextPath()%>/resources/images/back/images/man-info_12.png"
						width="11" height="30" />
				</td>
				<td width="144" align="center"
					background="<%=request.getContextPath()%>/resources/images/back/images/man-info_13.png"
					class="txt_bt">
					<span class="login_txt_bt">数据导入</span>
				</td>
				<td>
					&nbsp;
				</td>
				<td width="14">
					<img
						src="<%=request.getContextPath()%>/resources/images/back/images/man-info_17.png"
						width="14" height="30" />
				</td>
			</tr>
		</table>
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0" bgcolor="#F7F8F9">
			<tr>
				<td width="11"
					background="<%=request.getContextPath()%>/resources/images/back/images/man-info_19.png">
					&nbsp;
				</td>
				<td>
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td colspan="2" valign="middle">
								<form method="post" name="form1"
									action="import.action?m=upload"
									enctype="multipart/form-data">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="24%">
												&nbsp;
											</td>
											<td width="61%">
												&nbsp;
											</td>
											<td width="15%">
												&nbsp;
											</td>
										</tr>
										<tr>
											<td height="40" align="right" class="left_txt1">
												导入数据类型：
											</td>
											<td>
												<label>
													<input id="filetypeid" name="filetype" type="hidden"
														value="java"></input>
													<select name="select2" class="input4" id="selectid">
														<c:forEach var="reporttype" items="${reporttypeList}">
															<option value='${reporttype.tablename}'
																<c:if test="${importtypeid == reporttype.tablename}">selected="selected"</c:if>>
																${reporttype.description}
															</option>
														</c:forEach>
													</select>
												</label>
											</td>
											<td>
												&nbsp;
											</td>
										</tr>
										<tr>
											<td height="40" align="right" class="">
												<p class="left_txt1">
													上 传 文 件 ：
												</p>
											</td>
											<td colspan="2">
												<label></label>
												<label>
													<input id="filenameid" type="file" name="filename"
														class="input4" value="请输入机构全称或关键词……" size="35" />

												</label>
											</td>
										</tr>
										<tr>
											<td height="40" align="right" class="login_txt_bt">
												&nbsp;
											</td>
											<td>
												<a href="javascript:formsubmit()"><img
														src="<%=request.getContextPath()%>/resources/images/back/images/03.jpg"
														width="84" height="28" border="0" />
												</a>&nbsp;
												<img
													src="<%=request.getContextPath()%>/resources/images/back/images/04.jpg"
													width="84" height="28" />
											</td>
											<td>
												&nbsp;
											</td>
										</tr>
										<c:if test="${flag=='success'}">
											<tr>
												<td height="50" align="right" class="login_txt_bt">
													&nbsp;
												</td>
												<td>
													<div id="divid">
														<span class="left_txt">上传成功。</span>
														<span class="left_txt">成功导入数据${num }条</span>
														<a
															href="import.action?m=ToImportList"><span
															class="left_ts1">查看</span>
														</a>
													</div>
												</td>
												<td>
													&nbsp;
												</td>
											</tr>
										</c:if>
										<c:if test="${flag=='fail'}">
											<tr>
												<td height="50" align="right" class="login_txt_bt">
													&nbsp;
												</td>
												<td>
													<div id="divid">
														<span class="left_txt">上传失败</span><a
															href="shujuguanli.html"><span class="left_ts1">查看</span>
														</a>
													</div>
												</td>
												<td>
													&nbsp;
												</td>
											</tr>
										</c:if>
									</table>
								</form>
							</td>
							<td width="1%" class="left_txt_l">
								&nbsp;
							</td>
							<td width="35%" valign="top">
								<table width="100%" height="144" border="0" cellpadding="0"
									cellspacing="0" class="line_table">
									<tr>
										<td width="7%" height="27"
											background="<%=request.getContextPath()%>/resources/images/back/images/news-title-bg02.gif">
											<img
												src="<%=request.getContextPath()%>/resources/images/back/images/news-title-bg.gif"
												width="2" height="27" />
										</td>
										<td width="93%"
											background="<%=request.getContextPath()%>/resources/images/back/images/news-title-bg02.gif"
											class="left_bt2">
											<span class="left_txt"><img
													src="<%=request.getContextPath()%>/resources/images/back/images/ts.gif"
													width="16" height="16" />
											</span>提示
										</td>
									</tr>
									<tr>
										<td valign="top">
											&nbsp;
										</td>
										<td valign="top">
											<br />
											<span class="left_txt">说明</span><span class="left_txt">：输入组织机构代码查询可得到
											</span><span class="left_ts">唯一</span><span class="left_txt">结果。通过机构名称查询有可能得到多条结果！
												&nbsp;</span>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="40" colspan="4">
								<table width="100%" height="1" border="0" cellpadding="0"
									cellspacing="0" bgcolor="#CCCCCC">
									<tr>
										<td></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td colspan="2" valign="middle">
								<span class="left_txt"><br /> </span>
							</td>
							<td width="1%">
								&nbsp;
							</td>
							<td width="40%" valign="top">
								&nbsp;
							</td>
						</tr>
						<tr>
							<td width="2%">
								&nbsp;
							</td>
							<td width="57%" class="left_txt2">
								<img
									src="<%=request.getContextPath()%>/resources/images/back/images/icon-mail2.gif"
									width="16" height="11" />
								客户服务邮箱：123121212@qq.com
								<br />
								<img
									src="<%=request.getContextPath()%>/resources/images/back/images/icon-phone.gif"
									width="17" height="14" />
								官方网站：http://www.11212121.cn
							</td>
							<td>
								&nbsp;
							</td>
							<td>
								&nbsp;
							</td>
						</tr>
					</table>
					<p>
						&nbsp;
					</p>
				</td>
				<td width="14"
					background="<%=request.getContextPath()%>/resources/images/back/images/man-info_21.png">
					&nbsp;
				</td>
			</tr>
		</table>
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0"
			background="<%=request.getContextPath()%>/resources/images/back/images/man-info_30.png">
			<tr>
				<td width="11"
					background="<%=request.getContextPath()%>/resources/images/back/images/man-info_28.png">
					&nbsp;
				</td>
				<td width="144">
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
				<td width="14"
					background="<%=request.getContextPath()%>/resources/images/back/images/man-info_32.png">
					&nbsp;
				</td>
			</tr>
		</table>







	</body>
</html>
