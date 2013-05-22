<%@ page language="java" pageEncoding="utf-8"%>
<html>
	<head>
		<title>充值页面</title>
		<link href="${cssFile}" type="text/css" rel="stylesheet" />
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/resources/js/validata.js"></script>
	</head>
   <script type="text/javascript">
     function forward(){
        var payment=document.getElementsByName("payment");
        for (var iIndex = 0; iIndex < payment.length ; iIndex++ )
        {
            if(payment[iIndex].checked)
            {
                if (payment[iIndex].value=='1'){
                    form1.target="_parent";
                }else  if (payment[iIndex].value=='1'){
                    form1.target="_self";
                }
                
                
                form1.submit();
            }
        }
     }
   </script>
	<body>
		<table width="100%" height="30" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="2" background="${skinPath}images/hygl/right_03.gif"></td>
				<td height="25" align="left" width="677"
					background="${skinPath}images/hygl/right_04.gif">
					<span class="menu_p" style="padding-left: 10px"> <img
							src="${skinPath}images/hygl/hygl_01.gif" width="9" height="9" />
						&nbsp;您的位置：个人中心-&gt;&gt; <span style="font-size: 12px;">充值页面</span>
					</span>
				</td>
				<td width="2" background="${skinPath}images/hygl/right_06.gif"></td>
			</tr>
		</table>
		<div id="table_content">
			<form action="bzptczd.action?m=savePAYMENT" method="post" id="form1"  >
				<input type="hidden" name="subject" value="${subject }">
				<input type="hidden" name="alibody" value="${command.bz }">
				<input type="hidden" name="total_fee" value="${command.je }">
				<input type="hidden" name="out_trade_no" value="${command.id }">
				<input type="hidden" name="czrid" value="${command.czrid }">
				<input type="hidden" name="je" value="${command.je }">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					bgcolor="C7D9F3" style="margin-top: 0px;">
					<caption>
						充值页面
					</caption>
					<tr>
						<td class="td1" style="width: 20%">
							充值方式：
						</td>
						<td>
							备注: 用户充值后,请到充值服务填写充值单,若不填写,后果自负！！！
						</td>
					</tr>
					<tr>
						<td class="td1" style="width: 20%">
							<input type="radio" name="payment" value="1">
							在线支付 ：
						</td>
						<td>
							即时到帐，支持绝大数银行借记卡及部分银行信用卡 查看银行及限额
						</td>
					</tr>
					<tr>
						<td class="td1" style="width: 20%">
							<input type="radio" name="payment" value="2">
							公司转账 ：
						</td>
						<td>
							转帐后1－5个工作日内到帐
						</td>
					</tr>
					<tr>
						<td colspan=2 align="center">
							<input class="button_nbw2" type="button" onclick="forward()" value="确定" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>
