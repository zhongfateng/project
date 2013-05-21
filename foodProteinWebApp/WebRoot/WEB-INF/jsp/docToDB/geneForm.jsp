<%@ page language="java" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@ page import="com.nbw.docToDB.domain.Gene"%>
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
		<link
			href="<%=request.getContextPath()%>/resources/component/formValidator/style/validator.css"
			type="text/css" rel="stylesheet" />
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/resources/js/validata.js"></script>
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
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/My97DatePicker/WdatePicker.js">
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.6.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/component/ymPrompt/ymPrompt.js"></script>
		<script type="text/javascript">
$(function() {

	//处理FFN格式文件并返回相应信息。
	
			
			//处理FFN格式文件并返回相应信息。
	$('#d4_2').click(
			function() {
				
			});

});

function fgg(){
	var filepath = $("#d3_2").val();
	var filepath=encodeURI(filepath);
	if(filepath==""){
		ymPrompt.alert('请输入路径！');
	}else if($("#dddp").html()=="正在处理请稍等'''''''"){
		ymPrompt.alert('正在执行！');
	}else if($("#tipsse").html()=="您所处理的信息量:"){
		ymPrompt.alert('执行完成！');
	}else{
	
		$.ajax( {
					"url" : "gene.action?m=toPaseFFN&ffnPath=" + filepath,
					"type" : "post",
					"dataType" : "json",
					"beforeSend": function(XMLHttpRequest){
							  $("#tips").remove();
                    		$('#d3_1').after("<tr id='tipst'><td id='tipsses' height='30' colspan='2' class='td_nbw_left' align='center'></td><td id='dddp' align='center' style='color: red'></td></tr>");
							$('#tipsses').html(
								"执行状态:");
							$('#dddp').html(
								"正在处理请稍等'''''''");
                    },
					"success" : function(data, desc1) {
						if(data[0]==0){
							$('#d3_1').after("<tr id='tips'><td id='tipsse' colspan='2' height='30' class='td_nbw_left' align='center'></td><td id='dd' style='color: red'></td></tr>");
							$('#dd').html(
								"文件不存在");
						}else{
							$('#tips').remove();
						$('#d3_1').after("<tr id='tips'><td id='tipsse' colspan='2' height='30' class='td_nbw_left' align='center'></td><td id='dd' style='color: red'></td></tr>");
						$('#tipsse').html(
								"您所处理的信息量:");
					    $('#dd').html("处理Gene条数:"+data[0]);
						}
						
					},
					"complete": function(XMLHttpRequest, textStatus){  
                    	$("#tipst").remove();  
                	},
                	"error": function(XMLHttpRequest, textStatus, errorThrown){
                		alert("文件不存在");
                	}
				});
	}
				
};

function ffgg(){
	if($('#dddpt').html()=="正在处理请稍等'''''''"){
		ymPrompt.alert('正在执行！');
	}else if($('#tipssww').html()=="恭喜SQL语句执行完成:"){
		ymPrompt.alert('执行完成！');
	}else if($('#tipsse').html()=="您所处理的信息量:"){
		$.ajax( {
					"url" : "gene.action?m=toFFNSQL",
					"type" : "post",
					"dataType" : "json",
					"beforeSend": function(XMLHttpRequest){  
                    		$('#d4_1').after("<tr id='tipstt'><td id='tipssest' height='30' colspan='2' class='td_nbw_left' align='center'></td><td id='dddpt' align='center' style='color: red'></td></tr>");
							$('#tipssest').html(
								"执行状态:");
							$('#dddpt').html(
								"正在处理请稍等'''''''");
                    },
					"success" : function(data, desc1) {
						$('#tipss').remove();
						$('#d4_1').after("<tr id='tipss'><td id='tipssww' colspan='2' height='30' class='td_nbw_left' align='center'></td><td id='ddd' style='color: red'></td></tr>");
						$('#tipssww').html(
								"恭喜SQL语句执行完成:");
						$('#ddd').html("更新gene条数:"+data[0]);
					},
					"complete": function(XMLHttpRequest, textStatus){  
                    	$("#tipstt").remove();  
                	}
				});
	}else{
		ymPrompt.alert('上一步还没执行完！');
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
					<font color="#016250"> 当前位置信息:处理FFN文件</font>
				</td>
			</tr>
		</table>
		<div style="overFlow: auto; height: 550px">
				<table width="100%" align="center" class="table_nbw">
					<tr>
						<td>
							<input type="hidden" name="id" value="${bzptbook.id}" />
							<input type="hidden" name="imgId" id="img_Id"
								value="${bzptbook.imgId}" />
						</td>
					</tr>
					<tr class="tr_nbw" id="d3_1">
						<td width="50%" class="td_nbw_left" align="center">
							请输入FFN文件路径:
						</td>
						<td height="30" class="td_nbw_right" colspan="2">
							<input type="text" id="d3_2" name="ffnPath"
								size="40%" />
							<span id="slTip" style="width: 200px"></span>
						</td>
						<td height="30" class="td_nbw_right" colspan="3" align="right">
							<input id="d3_3" type="button" value="提交文件" size="10%" onclick="fgg();"/>
							<span id="isbnTipsss" style="width: 200px"></span>
						</td>
					</tr>
					<tr class="tr_nbw" id="d4_1">
						<td width="50%" class="td_nbw_left" align="center">
							通过gene(辅助表)表来更新jiyin表:
						</td>
						<td height="30" class="td_nbw_right" colspan="5" align="right">
							<input id="d4_2" type="button" value="执行SQL" onclick="ffgg();"
								size="10%"/>
							<span id="isbnTip" style="width: 200px"></span>
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
