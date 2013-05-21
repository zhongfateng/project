<%@ page language="java" pageEncoding="utf-8" isELIgnored="false"%>
<%@ include file="/resources/common/res_all.inc"%>
<%@ page import="com.nbw.common.SysParameter"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt"%>
<%
		String bzzyWebApp = SysParameter.getParameter("bzzyWebApp");
	%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<title>编缉页面</title>
		<link href="${adminCssFile}" type="text/css" rel="stylesheet">
		<link
			href="<%=request.getContextPath()%>/resources/component/uploadify/css/uploadify.css"
			rel="stylesheet" type="text/css" />
		<link
			href="<%=request.getContextPath()%>/resources/component/formValidator/style/validator.css"
			type="text/css" rel="stylesheet" />
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/resources/js/validate.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/uploadify/scripts/jquery-1.3.2.min.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/uploadify/scripts/swfobject.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/uploadify/scripts/jquery.uploadify.v2.1.0.min.js"></script>
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/formValidator/formValidator.js"></script>
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/formValidator/formValidatorRegex.js"></script>
<script type="text/javascript"
			src="<%=request.getContextPath()%>/resources/js/jquery-1.6.2.js">
</script>
	<script type="text/javascript">
	$(function(){
		
		
		
		
		
		$("#d1").click(function(){
			var docpath = $("#d2_1").val();
			var speciesname=$("#d2_2").val();
			if(docpath==""||speciesname==""){
				ymPrompt.alert("请输入文献xml对应路径和物种名");
			}else{
				window.location="search.action?m=parseXml&docpath="+docpath+"&speciesname="+speciesname;
			}
			 
		});
		});
		
</script>












	</head>
	<body>
		<table width="100%" height="30" border="0" cellpadding="0"
			cellspacing="0">
			<tr class="tr_title">
				<td width="2%" background="${skinPath}images/manage/dh_bg.jpg">
					&nbsp;


				</td>
				<td background="${skinPath}images/manage/dh_bg.jpg">
					<img src="${skinPath}/images/manage/dh-bg0.gif" width="14"
						height="14" />
					<font color="#016250"> 当前位置信息:处理GFF文件</font>
				</td>
			</tr>
		</table>
		<div style="overFlow: auto; height: 550px">
		
		<form  action="search.action?m=parseXml" method="post"  >
				<table width="100%" align="center" class="table_nbw">
					<tr>
						<td>
							<input type="hidden" name="id" value="${bzptbook.id}" />
							<input type="hidden" name="imgId" id="img_Id"
								value="${bzptbook.imgId}" />
						</td>
					</tr>
					<tr class="tr_nbw" id="d3">
						<td width="50%" class="td_nbw_left" align="center">
							请输入文献对应xml路径:
						</td>
						<td height="30" class="td_nbw_right" colspan="2">
							<input type="text" id="d2_1" name="docpath"
								size="40%" />
							<span id="slTip" style="width: 200px"></span>
						</td>
						
						
					</tr>
					
					<tr>
					
					<td width="50%" class="td_nbw_left" align="center">
							请输入文献对应物种名称:
						</td>
						<td height="30" class="td_nbw_right" colspan="2">
							<input type="text" id="d2_2" name="speciesname"
								size="40%" />
							<span id="slTip" style="width: 200px"></span>
						</td>
						
						
						<td height="30" class="td_nbw_right" colspan="3">
							<input id="d1" type="submit" value='执行' size="10%" />
							<span id="isbnTipsss" style="width: 200px"></span>
						</td>
					
					
						<td><input type="hidden" name="tgz" id="orgIds"
								value="${bzptbook.tgz}" />
						</td>
					</tr>
				</table>
				</form>
		</div>
		<form id="frm1" name="frm1" action="" method="post">
		</form>
	</body>
</html>
