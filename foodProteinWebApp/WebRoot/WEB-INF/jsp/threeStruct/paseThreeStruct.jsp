<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" contentType="text/html; charset=utf-8"%>
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
		<meta http-equiv=Content-Type content="text/html; charset=utf-8" />
		<title>编缉页面</title>
		<link href="${adminCssFile}" type="text/css" rel="stylesheet">
		<link
			href="<%=request.getContextPath()%>/resources/component/uploadify/css/uploadify.css"
			rel="stylesheet" type="text/css" />
			<link rel="stylesheet" id='skin' type="text/css" href="<%=request.getContextPath()%>/resources/component/ymPrompt/skin/qq/ymPrompt.css" />
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
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.6.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/component/ymPrompt/ymPrompt.js"></script>
		<script type="text/javascript">
$(function() {


	//处理GFF格式文件并返回相应信息。
	$("#d1").click(
	
			function() {
				
			});

	//执行SQL语句初步完善gene、cds、rna数据项。
	$('#d2_2').click(
			function() {
				//var speName = $("#d2_1").val();
				
			
			});

});
function ff(){
	var filepath = $("#d2").val();
	var filepath=encodeURI(filepath);
				var speName = $("#d2_1").val();
				if(filepath==""||speName==""){
					ymPrompt.alert('请输入路径和种名！');
				}else if($('#dddp').html()=="正在处理请稍等'''''''"){
					ymPrompt.alert('正在执行！');
				}else if($('#tipsses').html()=="您所处理的信息:"){
					ymPrompt.alert('执行完成！');
				}else{
				
					$.ajax( {
					"url" : "struct.action?m=toPaseThreeStruct&filePath=" + filepath +"&speciesName="+speName,
					"type" : "post",
					"dataType" : "json",
					"beforeSend": function(XMLHttpRequest){  
							$("#tips").remove();
                    		$('#d3').after("<tr id='tipst'><td id='tipsses' height='30' colspan='2' class='td_nbw_left' align='center'></td><td id='dddp' align='center' style='color: red'></td></tr>");
							$('#tipsses').html(
								"执行状态:");
							$('#dddp').html(
								"正在处理请稍等'''''''");
                    }, 
					"success" : function(data, desc1) {
						if(data.g==""){
							$('#d3').after("<tr id='tips'><td id='tipsses' height='30' colspan='2' class='td_nbw_left' align='center'></td><td id='ddd' align='center' style='color: red'></td></tr>");
							$('#ddd').html("文件不存在");
						}else{
							$('#tips').remove();
						$('#d3').after("<tr id='tips'><td id='tipsses' height='30' colspan='2' class='td_nbw_left' align='center'></td><td id='ddd' align='center' style='color: red'></td></tr>");
						$('#tipsses').html(
								"您所处理的信息:");
						$('#ddd').html(data[0]+"条");
						}
						
					},
					"complete": function(XMLHttpRequest, textStatus){  
                    	$("#tipst").remove();  
                	}
				});
				}
				
};

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
					<font color="#016250"> 当前位置信息:处理三维数据文件</font>
				</td>
			</tr>
		</table>
		<div style="overFlow: auto; height: 550px">
				<table width="100%" align="center" class="table_nbw" border="0">
					<tr>
						<td>
							<input type="hidden" name="id" value="${bzptbook.id}" />
							<input type="hidden" name="imgId" id="img_Id"
								value="${bzptbook.imgId}" />
						</td>
					</tr>
					<tr class="tr_nbw">
						<td width="50%" class="td_nbw_left" align="center">
							请输入物种英文名称:
						</td>
						<td height="30" class="td_nbw_right" colspan="2">
							<input type="text" id="d2_1" name="speciesName"
								size="40%" />
							<span id="isbnTip" style="width: 200px"></span>
						</td>
						<td height="30" class="td_nbw_right" colspan="3">
							<!--<input id="d2_2" type="button" value="执行SQL"
								size="10%" />
							--><span id="isbnTip" style="width: 200px"></span>
						</td>
					</tr>
					<tr class="tr_nbw" id="d3">
						<td width="50%" class="td_nbw_left" align="center">
							请输入三维文件路径:
						</td>
						<td height="30" class="td_nbw_right" colspan="2">
							<input type="text" id="d2" name="filePath" 
								size="40%" />
							<span id="slTip" style="width: 200px"></span>
						</td>
						<td height="30" class="td_nbw_right" colspan="3">
							<input id="d1" type="button" value='提交' size="10%" onclick="ff();"/>
							<span id="isbnTipsss" style="width: 200px"></span>
						</td>
					</tr>
					
					<tr>
						<td>
							<input type="hidden" name="tgz" id="orgIds"
								value="${bzptbook.tgz}" />
						</td>
					</tr>
				</table>
		</div>
		<form id="frm1" name="frm1" action="" method="post">
		</form>
	</body>
</html>