<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="com.nbw.hygl.domain.BzptFp"%>
<html>
	<head>
		<title>补开发票页面</title>
		<link href="${adminCssFile}" type="text/css" rel="stylesheet">
		<link
			href="<%=request.getContextPath()%>/resources/component/formValidator/style/validator.css"
			type="text/css" rel="stylesheet" />
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/resources/js/validata.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/uploadify/scripts/jquery-1.3.2.min.js"></script>
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/formValidator/formValidator.js"></script>
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/formValidator/formValidatorRegex.js"></script>
		<script type="text/javascript">
		
$(document).ready(function() {
        // 选择 1 是 验证 0 不验证 
	   
	    
		    $.formValidator.initConfig({onerror:function(msg){alert("校验没有通过，具体错误请看错误提示")}});
		    $("#fptt").formValidator({
	              onshow: "请输入发票抬头",
	              onfocus: "发票抬头不能为空"
	          }).inputValidator({
	              min: 1,
	              onerror: "发票抬头不能为空,请确认"
	          });
		     $("#invoicecontent").formValidator({
	               onshow: "请输入发票内容",
	               onfocus: "发票内容不能为空"
	           }).inputValidator({
	               min: 1,
	               onerror: "发票内容不能为空,请确认"
	           });    
	           $("#je").formValidator({
	              onshow: "请输入金额",
	              onfocus: "金额不能为空"
	          }).inputValidator({
	              min: 1,
	              onerror: "金额不能为空,请确认"
	          });  
	           $("#receivename").formValidator({
	              onshow: "请输入发票接受人",
	              onfocus: "发票接受人不能为空"
	          }).inputValidator({
	              min: 1,
	              onerror: "发票接受人不能为空,请确认"
	          });  
	           $("#address").formValidator({
	              onshow: "请输入发票接受人地址",
	              onfocus: "发票接受人地址不能为空"
	          }).inputValidator({
	              min: 1,
	              onerror: "发票接受人地址不能为空,请确认"
	          });  
	           $("#zip").formValidator({
	              onshow: "请输入发票接受人邮编",
	              onfocus: "发票接受人邮编不能为空"
	          }).regexValidator({
		                regexp:"zipcode",
		                datatype:"enum",
		                onerror:"国内邮编格式不正确"
		        });     
	            $("#mobile").formValidator({
	              onshow: "请输入发票接受人手机",
	              onfocus: "发票接受人手机不能为空"
	          }).regexValidator({
		                regexp:"mobile",
		                datatype:"enum",
		                onerror:"手机格式不正确"
		        });       
	           $("#deliverytype").formValidator({
	              onshow: "请输入送发票方式",
	              onfocus: "送发票方式不能为空"
	          }).inputValidator({
	              min: 1,
	              onerror: "送发票方式邮编不能为空,请确认"
	          });   
			                                                
				 $("#sbmintButton").click(function(){
						if($.formValidator.pageIsValid('1')){
			               document.all.theFrom.action="bzptfp.action?m=saveBk";
			               document.all.theFrom.submit();
						
						}
					})
				
 
 });

function backPage(){
  
    document.all.frm1.action = "bzptfp.action?m=loadData";
	document.all.frm1.submit();
}
		</script>
	</head>

	<body>
		<form action="bzptfp.action?m=save" method="post" name="theFrom">
			<input type="hidden" name="id" value="${bzptfp.id}" />
			<input type="hidden" name="gmrId" value="${bzptfp.gmrId}" />
			<table width="100%" height="30" border="0" cellpadding="0"
				cellspacing="0">
				<tr class="tr_title">
					<td width="2%" background="${skinPath}images/manage/dh_bg.jpg">
						&nbsp;
					</td>
					<td background="${skinPath}images/manage/dh_bg.jpg">
						<img src="${skinPath}/images/manage/dh-bg0.gif" width="14"
							height="14" />
						&nbsp;
						<span style="font-size: 11px;"> <font color="#016250">
								当前位置信息: 会员管理-&gt;&gt; 发票开具 </font> </span>
					</td>
				</tr>
			</table>
			<table width="100%" class="table_nbw">
				<tr class="tr_nbw">
					<td width="20%" class="td_nbw_left" align="center">
						发票抬头:
					</td>
					<td height="30" class="td_nbw_right">
						<input type="text" name="fptt" id="fptt" value=""
							size="40" />
						<span id="fpttTip" style="width: 200px"></span>
					</td>
				</tr>
				<tr class="tr_nbw">
					<td width="20%" class="td_nbw_left" align="center">
						发票内容:
					</td>
					<td height="30" class="td_nbw_right">
						<input type="text" name="invoicecontent" id="invoicecontent"
							value="" size="40" />
						<span id="invoicecontentTip" style="width: 200px"></span>
					</td>
				</tr>

				<tr class="tr_nbw">
					<td width="20%" class="td_nbw_left" align="center">
						发票金额:
					</td>
					<td height="30" class="td_nbw_right">
						<input type="text" name="je" id="je" value="${bzptfp.je}"
							readonly="readonly" size="40" />
						<span id="jeTip" style="width: 200px"></span>
					</td>
				</tr>
				<tr class="tr_nbw">
					<td width="20%" class="td_nbw_left" align="center">
						接受人姓名:
					</td>
					<td height="30" class="td_nbw_right">
						<input type="text" name="receivename" id="receivename"
							value="" size="40" />
						<span id="receivenameTip" style="width: 200px"></span>
					</td>
				</tr>
				<tr class="tr_nbw">
					<td width="20%" class="td_nbw_left" align="center">
						接受人地址:
					</td>
					<td height="30" class="td_nbw_right">
						<input type="text" name="address" id="address"
							value="" size="40" />
						<span id="addressTip" style="width: 200px"></span>
					</td>
				</tr>
				<tr class="tr_nbw">
					<td width="20%" class="td_nbw_left" align="center">
						接受人邮编:
					</td>
					<td height="30" class="td_nbw_right">
						<input type="text" name="zip" id="zip" value="${user.char14}"
							size="40" />
						<span id="zipTip" style="width: 200px"></span>
					</td>
				</tr>
				<tr class="tr_nbw">
					<td width="20%" class="td_nbw_left" align="center">
						接受人手机:
					</td>
					<td height="30" class="td_nbw_right">
						<input type="text" name="mobile" id="mobile"
							value="" size="40" value="${user.char15}"/>
						<span id="mobileTip" style="width: 200px"></span>
					</td>
				</tr>
				<tr class="tr_nbw">
					<td width="20%" class="td_nbw_left" align="center">
						送发票方式:
					</td>
					<td height="30" class="td_nbw_right">
						<input type="text" name="deliverytype" id="deliverytype"
							value="" size="40" />
						<span id="deliverytypeTip" style="width: 200px"></span>
					</td>
				</tr>
				<tr class="tr_nbw">
					<td height="40" align="center" colspan="2">
						<img src="${skinPath}images/button/button_1/bc.gif"
							style="cursor: pointer" id="sbmintButton">
						<img src="${skinPath}images/button/button_1/fh.gif"
							style="cursor: pointer" onClick="backPage();">
					</td>
					<td></td>
				</tr>
			</table>
		</form>
		<form id="frm1" name="frm1" action="" method="post">
		</form>
	</body>
</html>
