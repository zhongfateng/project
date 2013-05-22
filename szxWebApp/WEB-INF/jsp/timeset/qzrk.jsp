<%@page contentType="text/html; charset= utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>无标题文档</title>
		<link
			href="<%=request.getContextPath()%>/resources/timeset/images/skin.css"
			rel="stylesheet" type="text/css"></link>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.6.2.js"></script>
		<script type="text/javascript">
			function timeSave(){
				var times = $("#times").val();
				var reg = /^[1-9]\d*$/;
				if(times=="" || !reg.test(times)){
					$("#info").html("输入有误");
				}else{
					document.all.form1.submit();
				}
			};
		</script>
		
	</head>

	<body>

		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0"
			background="<%=request.getContextPath()%>/resources/timeset/images/man-info_14.png">
			<tr>
				<td width="11">
					<img
						src="<%=request.getContextPath()%>/resources/timeset/images/man-info_12.png"
						width="11" height="30" />
				</td>
				<td width="144" align="center"
					background="<%=request.getContextPath()%>/resources/timeset/images/man-info_13.png"
					class="txt_bt">
					<span class="login_txt">强制认证评价规则管理</span>
				</td>
				<td>
					&nbsp;
				</td>
				<td width="14">
					<img
						src="<%=request.getContextPath()%>/resources/timeset/images/man-info_17.png"
						width="14" height="30" />
				</td>
			</tr>
		</table>
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0" bgcolor="#F7F8F9">
			<tr>
				<td width="11"
					background="<%=request.getContextPath()%>/resources/timeset/images/man-info_19.png">
					&nbsp;
				</td>
				<td>
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td valign="middle">
							<form action="timeset.action?m=toSaveOrUpdate" method="post" name="form1" id="form1">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="19%">
											&nbsp;
										</td>
										<td width="15%">
											&nbsp;
										</td>
										<td width="66%">
											&nbsp;
										</td>
									</tr>
									<tr>
										<td height="40" align="right" class="login_txt_bt">
											<p>
												整改间隔期限：
											</p>
										</td>
										<td colspan="2">
											<label>
												<span class="left_txt"> 
												<input name="times" id="times" type="text" class="input4" value="${ts.tabnum}" size="20" />
												<input name="tsid" value="${ts.id}" type="hidden"/>
												</span>
											</label>
											<span class="left_txt">
												个月 &nbsp;&nbsp;&nbsp;&nbsp; 
											</span>
											<span id="info" style="color: red"><%=request.getAttribute("info") %></span>
										</td>
									</tr>
								</table>
								</form>
								<span class="left_txt"><br /> </span>
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td width="3%" height="27">
											<img
												src="<%=request.getContextPath()%>/resources/timeset/images/news-title-bg.gif"
												width="2" height="27" />
										</td>
										<td width="97%" class="left_txt">
											<span class="left_ts">说明</span>:实施强制性标准和强制性认证的产品，如未满足强制标准或强制认证的要求，在规定的期限内是否通过整改达到要求，为判断信用等级为C或D的条件。此处设定整改期限的最大时间范围数值。
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td width="3%" height="27">
											<img
												src="<%=request.getContextPath()%>/resources/timeset/images/news-title-bg.gif"
												width="2" height="27" />
										</td>
										<td width="97%" class="left_txt">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="40" align="center">
								<img
									src="<%=request.getContextPath()%>/resources/timeset/images/01.gif"
									width="84" height="28" />
								&nbsp;
								<img
									src="<%=request.getContextPath()%>/resources/timeset/images/02.gif"
									width="84" height="28" onclick="timeSave();"/>
							</td>
						</tr>
					</table>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="30" align="center">
								<table width="100%" height="2" border="0" cellpadding="0"
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
								<span class="left_txt"> <br /> </span>
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
							<td width="57%" class="left_txt">
								<img
									src="<%=request.getContextPath()%>/resources/timeset/images/icon-mail2.gif"
									width="16" height="11" />
								客户服务邮箱：123121212@qq.com
								<br />
								<img
									src="<%=request.getContextPath()%>/resources/timeset/images/icon-phone.gif"
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
					background="<%=request.getContextPath()%>/resources/timeset/images/man-info_21.png">
					&nbsp;
				</td>
			</tr>
		</table>
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0"
			background="<%=request.getContextPath()%>/resources/timeset/images/man-info_30.png">
			<tr>
				<td width="11"
					background="<%=request.getContextPath()%>/resources/timeset/images/man-info_28.png">
					&nbsp;
				</td>
				<td width="144">
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
				<td width="14"
					background="<%=request.getContextPath()%>/resources/timeset/images/man-info_32.png">
					&nbsp;
				</td>
			</tr>
		</table>
	</body>
</html>