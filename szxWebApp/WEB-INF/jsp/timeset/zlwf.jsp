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
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.validate.js"></script>
		<script type="text/javascript">
			
			$(function(){
			
			setTimeout("codefans()",2000);//3秒
			
		$('#form1').validate( {
		rules : {
			times1 : {
				required : true,
				digits:true
			},
			times2 :{
				required : true,
				digits:true
			},
			times3 : {
				required : true,
				digits:true
			}
		},
		messages : {
			times1 : {
				required : "<span style='color:red'>必填</span>",
				digits:"<span style='color:red'>请输入正整数</span>"
			},
			times2 :{
				required :"<span style='color:red'>必填</span>",
				digits:"<span style='color:red'>请输入正整数</span>"
			},
			times3 : {
				required : "<span style='color:red'>必填</span>",
				digits:"<span style='color:red'>请输入正整数</span>"
			}
		}
	});
	});
	
	
	function codefans(){
			var box=document.getElementById("info");
			var box1=document.getElementById("info1");
			box.style.display="none";
			box1.style.display="none";
}
			
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
					<span class="login_txt">质量违法评价规则管理</span>
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
		<form action="timeset.action?m=toSaveOrUpdate2" method="post" name="form1" id="form1">
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
							<td colspan="2" valign="middle">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="19%">
											&nbsp;
										</td>
										<td width="23%">
											&nbsp;
										</td>
										<td width="58%">
											&nbsp;
										</td>
									</tr>
									<tr>
										<td height="40" align="right" class="login_txt_bt">
											<p>
												一定时期：
											</p>
										</td>
										<td colspan="2">
											<label>
												<span class="left_txt"> 
												<input name="times1" id="times1" type="text" class="input4" value="${ts.tablong}" size="20" />
												<input name="tsid" value="${ts.id}" type="hidden"/>
												 </span>
												 <span class="left_txt">
												个月 &nbsp;&nbsp;&nbsp;&nbsp; 
											</span>
											<span id="info" style="color: red"><%=request.getAttribute("info") %></span>
											</label>
										</td>
									</tr>
								</table>
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
							<td colspan="4">
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td width="3%" height="27">
											<img
												src="<%=request.getContextPath()%>/resources/timeset/images/news-title-bg.gif"
												width="2" height="27" />
										</td>
										<td width="97%" class="left_txt">
											<span class="left_ts">说明</span>:在设定信用等级计算截至时间后，在一定时期内是否存在违法记录是判断等级A的条件，此处对该数值进行设定。
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="40" colspan="4">
								<table width="100%" height="2" border="0" cellpadding="0"
									cellspacing="0">
									<tr>
										<td style="border-bottom: dashed #cccccc 1px;"></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td colspan="2" valign="middle">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="19%">
											&nbsp;
										</td>
										<td width="23%">
											&nbsp;
										</td>
										<td width="58%">
											&nbsp;
										</td>
									</tr>
									<tr>
										<td height="40" align="right" class="login_txt_bt">
											<p>
												近期内：
											</p>
										</td>
										<td colspan="2">
											<label>
												<span class="left_txt"> 
												<input name="times2" id="times2" type="text" class="input4" value="${ts.tabsometime}" size="20" />
												 </span>
												 <span class="left_txt">
												个月 &nbsp;&nbsp;&nbsp;&nbsp; 
											</span>
											<span id="info1" style="color: red"><%=request.getAttribute("info") %></span>
											</label>
										</td>
									</tr>
								</table>
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
							<td colspan="4">
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td width="3%" height="27">
											<img
												src="<%=request.getContextPath()%>/resources/timeset/images/news-title-bg.gif"
												width="2" height="27" />
										</td>
										<td width="97%" class="left_txt">
											<span class="left_ts">说明</span>:在设定信用等级计算截至时间后，需要判断企业近期内最近一次违法记录的时间与截止时间的间隔是否满足一定数值，此处对该数值进行设定。
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="40" colspan="4">
								<table width="100%" height="2" border="0" cellpadding="0"
									cellspacing="0" bgcolor="#CCCCCC">
									<tr>
										<td></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="40" colspan="4" align="center">
								<input align="top" name="button" value="" type="button" id="button" style="background-image: url('<%=request.getContextPath()%>/resources/timeset/images/01.gif');width:84px;height:28px;border:0px;"/>
								&nbsp;
								<input align="top" name="submit" value="" type="submit" id="submit" style="background-image: url('<%=request.getContextPath()%>/resources/timeset/images/02.gif');width:84px;height:28px;border:0px;"/>
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
		</form>
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