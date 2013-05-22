<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/resources/common/res_all.inc"%>
<%@ page import="com.nbw.common.SysParameter"%>
<%
	String xtpath = SysParameter.getParameter("xtpath");
%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="${cssFile}" rel="stylesheet" type="text/css" />
		<link
			href="<%=request.getContextPath()%>/resources/component/formValidator/style/validator.css"
			type="text/css" rel="stylesheet" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/uploadify/scripts/jquery-1.3.2.min.js"></script>
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/formValidator/formValidator.js"></script>
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/formValidator/formValidatorRegex.js"></script>
		<script type="text/javascript">
	$(document).ready(function() {
	    $.formValidator.initConfig({onerror:function(msg){ ymPrompt.errorInfo({title:"提示",message:"校验没有通过，具体错误请看错误提示!"});}});
	    $("#loginCode")
	    .formValidator({onshow:"请输入用户名",onfocus:"用户名2~10个字符",oncorrect:"该用户名可以注册"})
	    .inputValidator({min:2,max:10,onerror:"你输入的用户名非法,请确认"})
	    .regexValidator({regexp:"username",datatype:"enum",onerror:"用户名格式不正确"})
	    .ajaxValidator({
			    type : "post",
				url : "bzptuser.action?m=frontLoginCodeIsExit",
				success : function(data){	
		            if( data == "true" )
					{
		                return false;
					}
		            else
					{
		                return true;
					}
				},
				error: function(){alert("服务器没有返回数据，可能服务器忙，请重试");},
				onerror : "该用户名不可用，请更换用户名",
				onwait : "正在对用户名进行合法性校验，请稍候..."
	      });
	      $("#pwd").formValidator({
	                    onshow: "请输入0～6位数字或者字母",
	                    onfocus: "密码不能为空"
	                }).inputValidator({
	                    min: 1,
	                    onerror: "密码称不能为空,请确认"
	                });  
	       $("#pwd2").formValidator({onshow:"请输入重复密码",onfocus:"两次密码必须一致",oncorrect:"密码一致"})
	              .inputValidator({min:1,empty:{leftempty:false,rightempty:false,emptyerror:"重复密码两边不能有空符号"},onerror:"重复密码不能为空,请确认"})
	              .compareValidator({desid:"pwd",operateor:"=",onerror:"2次密码不一致,请确认"});
	                
	       $("#realName").formValidator({
	                    onshow: "请输入真实姓名",
	                    onfocus: "真实姓名"
	                }).inputValidator({
	                    min: 1,
	                    onerror: "真实姓名不能为空,请确认"
	                }).inputValidator({
	                    max:10,
	                    onerror: "真实姓名不能超过10个字符"
	                });
           $("#attchar08").formValidator({
               onshow: "请选择密码提示问题",
               onfocus: "密码提示问题不能为空"
           }).inputValidator({
               min: 1,
               onerror: "提示问题不能为空,请确认"
           })
           $("#attchar09").formValidator({
               onshow: "请输入密码提示问题答案",
               onfocus: "密码提示问题答案不能为空"
           }).inputValidator({
               min: 1,
               onerror: "提示问题答案不能为空,请确认"
           }).inputValidator({
               max: 20,
               onerror: "提示问题不能超过30个字符"
           });
           $("#attchar11").formValidator({
               onshow: "请输入公司名称",
               onfocus: "公司名称"
           });
           $("#attchar12").formValidator({
               onshow: "请输入公司简称",
               onfocus: "公司简称"
           });
           $("#attchar13").formValidator({
               onshow: "请输入通讯地址",
               onfocus: "通讯地址"
           });
           $("#attchar14").formValidator({
               onshow: "请输入邮政编码",
               onfocus: "邮政编码"
           });
       
           
               

           $("#attchar16").formValidator({
               onshow: "请输入E-mail",
               onfocus: "E-mail不能为空"
	           }).inputValidator({
	               min: 1,
	               onerror: "邮箱不能为空,请确认"
	           }).regexValidator({
	              regexp:"^([\\w-.]+)@(([[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.)|(([\\w-]+.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(]?)$",
	              onerror:"你输入的邮箱格式不正确"});
          
          $("#sbmintButton").click(function(){
           if($.formValidator.pageIsValid('1')){ 
             var attchar17=$("#attchar17").val();
             var regu =/(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/;
             if(!regu.test(attchar17)) {
                alert("请填写有效联系电话!");
                return false;
            }else{
  				$("#theFrom").get(0).submit();
               }
		   }
 	 })                                            
});
	
    
	
	
	function qy(){
         var mc=document.getElementById("attchar11");
         var jc=document.getElementById("attchar12");
         if(mc.disabled==true && jc.disabled==true){
            mc.disabled=false;
            jc.disabled=false;
           $("#attchar11").formValidator({
             onshow: "请输入公司名称",
             onfocus: "公司名称"
	         }).inputValidator({
	             min: 1,
	             onerror: "公司名称不能为空,请确认"
	         }).inputValidator({
	             max:30,
	             onerror: "公司名称不能超过30个字符"
	         });
	       $("#attchar12").formValidator({
	             onshow: "请输入公司简称",
	             onfocus: "公司简称"
	         }).inputValidator({
	             min: 1,
	             onerror: "公司简称不能为空,请确认"
	         }).inputValidator({
	             max:10,
	             onerror: "公司简称不能超过10个字符"
	         });
         }else{
            mc.value="";
            jc.value="";
            mc.disabled=true;
            jc.disabled=true;
          $("#attchar11").formValidator({
             onshow: "请输入公司名称",
             onfocus: "公司名称"
           });
          $("#attchar12").formValidator({
             onshow: "请输入公司简称",
             onfocus: "公司简称"
           });
       }
    }
    
	function agree1(){
	 var agree = $("#agree");
	 if(agree[0].checked){
		 $("#sbmintButton")[0].disabled = false;
		 $("#sbmintButton").css("cursor","hand");
		 $("#agreeDiv").html("");
	 }else {
		 $("#sbmintButton")[0].disabled = true;
		 $("#sbmintButton").css("cursor","auto");
		 $("#agreeDiv").html("<img src='<%=request.getContextPath()%>/resources/images/icon_no.gif' /> <font color='red'>请接受服务条款</font>");
	 }
	}

	function sure(){
		window.open("<%=xtpath %>/BzptWeb/appmanager/eip/main?_nfpb=true&_pageLabel=P5800125901322719086481&url=/bzptuser.action?m=frontSure'flag=f&width=938&height=550","_blank");
	}
	
    function morexs(){
	   var more=document.getElementById("More");
	   if(more.style.display=="none"){
	   	more.style.display="block"
	   }else{
	   	more.style.display="none"
	   }
	}						    
</script>
	</head>
	<body>
		<table width="500" height="30" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td>
					<form action="bzptuser.action?m=frontEESave" method="post"
						id="theFrom">
					<input type="hidden" name="index" value="${index}">
						<table width="500" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
								<td align="center">
									<table width="500" border="0" align="center" cellpadding="0"
										cellspacing="0">
										<tr>
											<td width="500" align="center" valign="top" class="bjing"
												colspan="2">
											</td>
										</tr>
										<tr>
											<td align="center">
												<table width="500" border="0" cellpadding="0"
													cellspacing="0">
													<tr>
														<td width="100%" height="40" align="center" class="title">
															创建用户账号
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td align="center">
									<table width="600" border="0" cellpadding="0" cellspacing="5">
										<tr>
											<td width="143" height="22" align="right" class="menu_p">
												用&nbsp;户&nbsp;名：
											</td>
											<td width="238" align="left" class="menu_p">
												<input type="text" name="loginCode" id="loginCode"
													class="menu_p_reg" />
												<span class="red" height="22">&nbsp;&nbsp;*</span>
											</td>
											<td width="199" align="left" class="menu_p">
												<div class="red_italic" id="loginCodeTip"></div>
											</td>
										</tr>
										<tr>
											<td height="19" align="right" class="menu_p">
												密&nbsp;&nbsp;&nbsp;&nbsp;码：
											</td>
											<td align="left" class="menu_p">
												<input type="password" name="password" id="pwd"
													class="menu_p_reg" />
												<span class="red" height="22">&nbsp;&nbsp;*</span>
											</td>
											<td align="left" class="menu_p">
												<div class="red_italic" id="pwdTip"></div>
											</td>
										</tr>
										<tr>
											<td height="19" align="right" class="menu_p">
												确认密码：
											</td>
											<td align="left" class="menu_p">
												<input type="password" name="password2" id="pwd2"
													class="menu_p_reg" />
												<span class="red" height="22">&nbsp;&nbsp;*</span>
											</td>
											<td align="left" class="menu_p">
												<div class="red_italic" id="pwd2Tip"></div>
											</td>
										</tr>
										
										<tr>
											<td height="19" align="right" class="menu_p">
												真实姓名：
											</td>
											<td align="left" class="menu_p">
												<input type="text" name="name" id="realName" size='26' />
												<span class="red" height="22">&nbsp;&nbsp;*</span>
											</td>
											<td align="left" class="menu_p">
												<div class="red_italic" id="realNameTip"></div>
											</td>
										</tr>
										<tr>
											<td height="19" align="right" class="menu_p">
												E - mail：
											</td>
											<td align="left" class="menu_p">
												<input type='text' id='attchar16' name='attchar16' size='26'
													value='' />
												<span class="red" height="22">&nbsp;&nbsp;*</span>
											</td>
											<td align="left" class="menu_p">
												<div class="red_italic" id="attchar16Tip"></div>
											</td>
										</tr>
										
										<tr>
											<td height="19" align="right" class="menu_p">
												联系电话：
											</td>
											<td align="left" class="menu_p">
												<input type='text' id='attchar17' name='attchar17' size='26'
													value='' />
												<span class="red" height="22">&nbsp;&nbsp;*</span>
											</td>
											<td align="left" class="menu_p">
												<div id="utelInfo" class="dlRed">
                   如:12345678901,010-12345678-1234
				  </div>
											</td>
											</tr>
											
									</table>
								</td>
							</tr>
							<tr>
								<td align="center">
									<HR class=hr2>
								</td>
							</tr>
							<tr>
								<td style="padding-left: 10">		
								<input type="checkbox" onclick="morexs()" name="c1" />更多的信息>>
								 <div id="More" style="display: none;">
									<table width="600" border="0" cellpadding="0" cellspacing="5">
										<tr>
											<td width="143" height="21" align="right" class="menu_p">
												所属区域：
											</td>
											<td width="238" align="left" class="menu_p">
												<select name="attchar10" onChange="others()" id="s1">
													<option value="1001">
														北京市
													</option>
													<option value="1002">
														安徽
													</option>
													<option value="1003">
														重庆市
													</option>
													<option value="1004">
														福建
													</option>
													<option value="1005">
														甘肃
													</option>
													<option value="1006">
														广东
													</option>
													<option value="1007">
														广西
													</option>
													<option value="1008">
														贵州
													</option>
													<option value="1009">
														海南
													</option>
													<option value="1010">
														河北
													</option>
													<option value="1011">
														黑龙江
													</option>
													<option value="1012">
														河南
													</option>
													<option value="1013">
														香港
													</option>
													<option value="1014">
														湖北
													</option>
													<option value="1015">
														湖南
													</option>
													<option value="1016">
														内蒙古
													</option>
													<option value="1017">
														江苏
													</option>
													<option value="1018">
														江西
													</option>
													<option value="1019">
														吉林
													</option>
													<option value="1020">
														辽宁
													</option>
													<option value="1031">
														澳门
													</option>
													<option value="1032">
														宁夏
													</option>
													<option value="1033">
														青海
													</option>
													<option value="1034">
														陕西
													</option>
													<option value="1035">
														山东
													</option>
													<option value="1036">
														上海市
													</option>
													<option value="1037">
														山西
													</option>
													<option value="1038">
														四川
													</option>
													<option value="1039">
														台湾
													</option>
													<option value="1040">
														天津市
													</option>
													<option value="1041">
														西藏
													</option>
													<option value="1042">
														新疆
													</option>
													<option value="1043">
														云南
													</option>
													<option value="1044">
														浙江
													</option>
													<option value="1045">
														其他地区
													</option>
													<option value="1046">
														其他国家
													</option>
												</select>
												<div id="t1" style="display: none">
													<input type='text' name='attchar07' size='10' value='' id="t1" />
													<a href="javascript:others2()">返回</a>
												</div>
											</td>
											<td width="199" align="left" class="menu_p"></td>
										<tr>
										  <td colspan="3" class="menu_p" height="19"><input type="checkbox" onclick="qy()" 
										  name="c2" style="margin-left: 80px"/>企业信息(点击可填写)>></td>
										</tr>
										<tr>
											<td height="19" align="right" class="menu_p">
												公司名称：
											</td>
											<td align="left" class="menu_p">
												<input type='text' name='attchar11' id="attchar11" size='26' value='' disabled=true/>
											</td>
											<td align="left" class="menu_p">
												<div class="red_italic" id="attchar11Tip"></div>
											</td>
										</tr>
										<tr>
											<td height="19" align="right" class="menu_p">
												公司简称：
											</td>
											<td align="left" class="menu_p">
												<input type='text' name='attchar12' id="attchar12" size='26'
													value='' disabled=true />
											</td>
											<td align="left" class="menu_p">
												<div class="red_italic" id="attchar12Tip"></div>
											</td>
										</tr>
										<tr>
											<td height="19" align="right" class="menu_p">
												通信地址：
											</td>
											<td align="left" class="menu_p">
												<input type='text' id='attchar13' name='attchar13' size='26'
													value='' />
											</td>
											<td align="left" class="menu_p">
												<div class="red_italic" id="attchar13Tip"></div>
											</td>
										</tr>
										<tr>
											<td height="19" align="right" class="menu_p">
												邮政编码：
											</td>
											<td align="left" class="menu_p">
												<input type='text' id="attchar14" name='attchar14' size='26'
													value='' />
											</td>
											<td align="left" class="menu_p">
												<div class="red_italic" id="attchar14Tip"></div>
											</td>
										</tr>					
									</table>
									</div>
								</td>
							</tr>
						<tr>
						<table>
						<tr>
						<td align="center" width="400px">
						 <input type="checkbox" id="agree" checked  onclick="agree1()" /> 同意"<a href="#" onclick="sure()" ><font color="blue">服务条款</font></a>"
						</td>
						<td align="left"  width="130px">
						<div id="agreeDiv" class="red"></div></td>
						</tr>
						</table>
						</tr>
						<tr>
						<td height="12"></td>
						</tr>
							<tr>
								<td align="center">
									<img id="sbmintButton" style="cursor: pointer"
										src="${skinPath}images/hygl/login_07.gif" />
								</td>
							</tr>
						</table>
					</from>
				</td>
			</tr>
		</table>
	</body>
</html>