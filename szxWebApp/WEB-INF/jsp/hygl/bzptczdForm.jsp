<%@ page language="java" pageEncoding="utf-8"%>
<html>
	<head>
		<title>充值服务</title>
		<link href="${cssFile}" type="text/css" rel="stylesheet">
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/resources/js/validata.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-1.6.2.js"></script>
		<style type="text/css">
			<!--
			.text {	font-family: "宋体";	font-size: 12px;font-style: normal;	line-height: 20px;	font-variant: normal;
					color: #222222; padding-left:10px;
				  }
			a.text:link,a.text:visited {
					font-family: "宋体";	font-size: 12px;	line-height: 20px;	font-variant: normal;color: #006DAC;
					}
			a.text:hover,a.text:active {font-family: "宋体";	font-size: 12px;	line-height: 20px;	font-variant: normal;
					color: #ffcc66; padding-left:10px;
					}
			.tle {	font-family: "宋体";	font-size: 14px;	font-style: normal;	line-height: 25px;	font-variant: normal;
					color: #222222; padding-left:10px;
				  }
			a.tle:link,a.tle:visited {	font-family: "宋体"; font-weight:600;	font-size: 12px;line-height: 25px;
					font-variant: normal;	color: #E5F5FB;
				}
			a.tle:hover,a.tle:active {	font-family: "宋体";	font-size: 12px;	line-height: 25px;	font-variant: normal;
					color: #F48400;
				}
			.STYLE2 {font-family: "宋体"; font-size: 14px; font-style: normal; line-height: 25px; font-variant: normal; color: #222222; 
					padding-left: 10px; font-weight: bold;
				 }
			.table_b{ border-bottom:solid 1px #e9f6fc;font-size: 12px;}
			.table_right{ border-right:dashed 1px #dddddd; font-size: 14px; font-weight:bold}
			-->
		</style>
		<script type="text/javascript">
			function checkTheForm(){
				var from=document.theFrom;
				if(from.je.value.length==0){
				  	alert("必须填写金额！");
				}else if(from.je.value.length!=""&&isNaN(from.je.value)){
				    alert("必须是数字格式！");
				}else if(from.bz.value.length==0){
				   alert("必须填写备注！");
				}else if(from.je.value.length>=5){
				    alert("充值金额不能超过一万，请多次充值！");
				}else if(from.fptt.value.length>100){
				  	alert("发票抬头输入过长，最多只能输入50个字");
		     	}else if(from.invoicecontent.value.length>100){
		     		alert("发票内容过长，最多只能输入50个字");
		     	}else if(from.receivename.value.length>100){
		     		alert("接受人姓名过长，最多只能输入50个字");
		     	}else if(from.address.value.length>100){
		     		alert("接受人地址过长，最多只能输入50个字");
		     	}else if(from.zip.value.length>20){
		     		alert("接受人邮编过长，最多只能输入6个字");
		     	}else if(from.mobile.value.length>26){
		     		alert("接受人电话过长，最多只能输入13个字");
		     	}else if(from.bz.value.length>100){
		     		alert("输入的备注过长，最多只能输入50个字");
		     	}else{
		     		document.theFrom.submit();
		     	}
			}
			$(document).ready(function(){
				$("tr:gt(6)").hide();
				$("#bz").show();
				$("#submit").show();
				$("#isInvoice").click(function(){
					$("#fptt,#invoicecontent,#receivename,#address,#zip,#mobile,#deliverytype").show();
				})
				$("#notInvoice").click(function(){
					$("#fptt,#invoicecontent,#receivename,#address,#zip,#mobile,#deliverytype").hide();
				})
			})
		</script>
</head>
<body >
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
	 <td height="1" bgcolor="83beea"></td>
	</tr>
	<tr>
	  <td height="5" bgcolor="f2fbff"><span class="text">&nbsp;&nbsp;<strong>您现在的位置：</strong>用户中心-&gt;&gt;充值服务-&gt;&gt;填写充值单 </span></td>
	</tr>
	<tr>
	  <td height="1" bgcolor="83beea"></td>
	</tr>
</table>
<form action="bzptczd.action?m=save" method="post" name="theFrom" style="margin-top: 0px;">
<table width="650" border="0" cellspacing="0" cellpadding="0" >
  <tr>
    <td background="r_t_02.jpg">
     <table width="700" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td colspan="2" >&nbsp;</td>
      </tr>
      <tr>
        <td width="193" height="30" align="right" class="table_right">充值金额：&nbsp;</td>
        <td colspan="2" ><label>
          &nbsp;
          <input name="je" type="text" size="15" style="height:25px" value=""/>
        </label></td>
      </tr>
      <tr>
        <td width="193" height="30" align="right" class="table_right">是否开具发票：&nbsp;</td>
        <td colspan="2"><label>&nbsp;<span class="text">
            <input name="isInvoice" type="radio" value="1" id="isInvoice"/>
          是
          <input type="radio" name="isInvoice" value="0" checked="checked" id="notInvoice" />
          否 </span></label></td>
      </tr>
      <tr id="fptt">
        <td width="193" height="30" align="right" class="table_right">发票抬头：&nbsp;</td>
        <td colspan="2">&nbsp;
          <input name="fptt" type="text" size="15" style="height:25px"  value="" length="100"/></td></tr>
      <tr>
        <td height="5" align="right">&nbsp;</td>
        <td colspan="2" >&nbsp;</td>
      </tr>
       <tr id="invoicecontent">
        <td width="193" height="30" align="right" class="table_right">发票内容：&nbsp;</td>
        <td colspan="2">&nbsp;
          <input name="invoicecontent" type="text" size="15" style="height:25px"  value=""/>
        </td>
      </tr>
      <tr>
        <td height="5" align="right">&nbsp;</td>
        <td colspan="2" >&nbsp;</td>
      </tr>
      <tr id="receivename">
        <td width="193" height="30" align="right" class="table_right">接收人姓名：&nbsp;</td>
        <td colspan="2">&nbsp;
          <input name="receivename" type="text" size="15" style="height:25px" value="${sud.sysUsers.name}" /></td></tr>
      <tr>
        <td height="5" align="right">&nbsp;</td>
        <td colspan="2" >&nbsp;</td>
      </tr>
      <tr id="address">
        <td width="193" height="30" align="right" class="table_right">接收人地址：&nbsp;</td>
        <td colspan="2">&nbsp;
          <input name="address" type="text" size="50" style="height:25px" value="${sud.char13}" /></td></tr>
      <tr>
        <td height="5" align="right">&nbsp;</td>
        <td colspan="2" >&nbsp;</td>
      </tr>
      <tr id="zip">
        <td width="193" height="30" align="right" class="table_right">接收人邮编：&nbsp;</td>
        <td colspan="2">&nbsp;
          <input name="zip" type="text" size="10" style="height:25px"  value="${sud.char14}"/></td></tr>
      <tr>
        <td height="5" align="right">&nbsp;</td>
        <td colspan="2" >&nbsp;</td>
      </tr>
      <tr id="mobile">
        <td width="193" height="30" align="right" class="table_right">接收人手机：&nbsp;</td>
        <td colspan="2" >&nbsp;
          <input name="mobile" type="text" size="15"  style="height:25px" value="${sud.char17}"/>
          
      <tr>
        <td height="5" align="right">&nbsp;</td>
        <td colspan="2" >&nbsp;</td>
      </tr>
      <tr id="deliverytype">
        <td width="193" height="30" align="right" class="table_right">发票邮递方式：&nbsp;</td>
        <td colspan="2" class="text">
          <input name="deliverytype" type="radio" value="1" checked="checked" />
          普通快递送货上门
          <input type="radio" name="deliverytype" value="2" />
          加急快递送货上门
          <input name="deliverytype" type="radio" value="3" />
          普通邮递
          <input type="radio" name="deliverytype" value="4" />
          邮政特快专递 EMS</td>
      </tr>
      <tr id="bz">
        <td width="193" height="110" align="right" class="table_right">备注信息：&nbsp;&nbsp;</td>
        <td colspan="2"><label>&nbsp;
          <textarea name="bz" cols="50" rows="5"></textarea>
        </label></td>
      </tr>
      <tr id="submit">
        <td width="193" height="45" align="right">&nbsp;</td>
        <td width="172" align="center">
        	<img src="${skinPath}images/hygl/queding.jpg" width="100" height="35" onclick="checkTheForm()" />
        </td>
        <td width="385">&nbsp;</td>
      </tr>
      <tr>
        <td width="193" height="45" align="right">&nbsp;</td>
        <td colspan="2">&nbsp;</td>
      </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td height="30" background="r_t_03.jpg">&nbsp;</td>
  </tr>
</table>
</form>
</body>
</html>
