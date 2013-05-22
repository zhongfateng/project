<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/resources/common/res_all.inc"%>
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>会员注册</title>
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
	                    onerror: "用户名称不能为空,请确认"
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
	                    onshow: "请输入单位名称",
	                    onfocus: "单位名称不能为空"
	                }).inputValidator({
	                    min: 1,
	                    onerror: "单位名称不能为空,请确认"
	                }).inputValidator({
	                    max:30,
	                    onerror: "单位名称不能超过30个字符"
	                });
	                  $("#attchar12").formValidator({
	                    onshow: "请输入单位简称",
	                    onfocus: "单位简称不能为空"
	                }).inputValidator({
	                    min: 1,
	                    onerror: "单位简称不能为空,请确认"
	                }).inputValidator({
	                    max:10,
	                    onerror: "单位简称不能超过10个字符"
	                });
	                  $("#attchar13").formValidator({
	                    onshow: "请输入通讯地址",
	                    onfocus: "通讯地址不能为空"
	                }).inputValidator({
	                    min: 1,
	                    onerror: "通讯地址不能为空,请确认"
	                }).inputValidator({
	                    max:50,
	                    onerror: "通讯地址不能超过50个字符"
	                });
	                  $("#attchar14").formValidator({
	                    onshow: "请输入邮政编码",
	                    onfocus: "邮政编码不能为空"
	                }).inputValidator({
	                    min: 1,
	                    onerror: "邮政编码不能为空,请确认"
	                }).regexValidator({
		                    regexp:"zipcode",
		                    datatype:"enum",
		                    onerror:"邮政编码格式不正确"
		                });;
		              $("#attchar15").formValidator({
	                    onshow: "请输入联系电话",
	                    onfocus: "例如：0577-88888888",
	                    oncorrect: "联系电话不能为空"
	                }).inputValidator({
	                    min: 1,
	                    onerror: "联系不能为空,请确认"
	                }).regexValidator({
		                    regexp:"tel",
			                datatype:"enum",
			                onerror:"国内电话格式不正确"
		                });;
	                  
	                $("#attchar16").formValidator({
	                    onshow: "请输入E-mail",
	                    onfocus: "E-mail不能为空"
	                }).inputValidator({
	                    min: 1,
	                    onerror: "邮箱不能为空,请确认"
	                }).regexValidator({
	                   regexp:"^([\\w-.]+)@(([[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.)|(([\\w-]+.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(]?)$",
	                   onerror:"你输入的邮箱格式不正确"});
	                 $("#attchar17").formValidator({
	                    onshow: "请输入手机号码",
	                    onfocus: "手机不能为空"
	                }).inputValidator({
	                    min: 1,
	                    onerror: "手机不能为空,请确认"
	                }).regexValidator({
		                    regexp:"mobile",
		                    datatype:"enum",
		                    onerror:"手机格式不正确"
		                });
	                $("#sbmintButton").click(function(){
	                 if($.formValidator.pageIsValid('1')){
						  $("#theFrom").get(0).submit();
						}
				   })                                            
});
</script>
	</head>
	<body style="background-color: #cccccc">
		<table width="100%" height="30" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td>
					<table width="914" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td height="14">
								<img src="${skinPath}images/hygl/main_top.jpg" width="914"
									height="14" /></td>
						</tr>
						<tr>
							<td background="${skinPath}images/hygl/main_bg.jpg"
								align="center">
								<form action="bzptuser.action?m=frontSave" method="post"
									id="theFrom">
									<table width="800" border="0" align="center" cellpadding="0"
										cellspacing="0">
										<tr>
											<td width="800" align="center" valign="top" class="bjing"
												colspan="2">
												<img src="${skinPath}images/hygl/log_bg2.gif" width="435"
													height="94" />
											</td>
										</tr>
										<tr>
											<td align="center">
												<table width="800" border="0" cellpadding="0"
													cellspacing="0"
													background="${skinPath}images/hygl/login_10.gif">
													<tr>
														<td width="17%" height="40" align="center"
															background="${skinPath}images/hygl/login_09.gif"
															class="title">
															创建您的账号
														</td>
														<td width="83%">
															&nbsp;

														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td align="center">
												<table width="600" border="0" cellpadding="0"
													cellspacing="5">
													<tr>
														<td width="143" height="22" align="right" class="menu_p">
															用户名：
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
															密码：
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
															<input type="text" name="name" id="realName"
																class="menu_p_reg" />
															<span class="red" height="22">&nbsp;&nbsp;*</span>
														</td>
														<td align="left" class="menu_p">
															<div class="red_italic" id="realNameTip"></div>
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td align="center">
												<table width="800" border="0" cellpadding="0"
													cellspacing="0"
													background="${skinPath}images/hygl/login_10.gif">
													<tr>
														<td width="17%" height="40" align="center"
															background="${skinPath}images/hygl/login_09.gif"
															class="title">
															安全信息设置
														</td>
														<td width="83%">
															&nbsp;

														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td align="center">
												<table width="600" border="0" cellpadding="0"
													cellspacing="5">
													<tr>
														<td width="143" height="21" align="right" class="menu_p">
															密码提示问题：
														</td>
														<td width="238" align="left" class="menu_p">
															<select name='attchar08' id='attchar08'>
																<option value=''>
																	请选择密码提示问题
																</option>
																<option value='最喜欢的宠物'>
																	最喜欢的宠物
																</option>
																<option value='爸爸的职业'>
																	爸爸的职业
																</option>
																<option value='车牌号码'>
																	车牌号码
																</option>
																<option value='妈妈的手机型号'>
																	妈妈的手机型号
																</option>
															</select>
															<span class="red" height="22">&nbsp;&nbsp;*</span>
														</td>
														<td width="199" align="left" class="menu_p">
															<div class="red_italic" id="attchar08Tip"></div>
														</td>
													</tr>
													<tr>
														<td height="19" align="right" class="menu_p">
															密码问题答案：
														</td>
														<td align="left" class="menu_p">
															<input type='text' name='attchar09' id='attchar09'
																size='26' value='' />
															<span class="red" height="22">&nbsp;&nbsp;*</span>
														</td>
														<td align="left" class="menu_p">
															<div class="red_italic" id="attchar09Tip"></div>
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td>
												<table width="800" border="0" cellpadding="0"
													cellspacing="0"
													background="${skinPath}images/hygl/login_10.gif">
													<tr>
														<td width="17%" height="40" align="center"
															background="${skinPath}images/hygl/login_09.gif"
															class="title">
															个人信息设置
														</td>
														<td width="83%">
															&nbsp;

														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td align="center">
												<table width="600" border="0" cellpadding="0"
													cellspacing="5">
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
																<input type='text' name='attchar07' size='10' value=''
																	id="t1" />
																<span class="red">*</span>
																<a href="javascript:others2()">返回</a>
															</div>
														</td>
														<td width="199" align="left" class="menu_p"></td>
													</tr>
													<tr>
														<td height="19" align="right" class="menu_p">
															单位名称：
														</td>
														<td align="left" class="menu_p">
															<input type='text' name='attchar11' id="attchar11"
																size='26' value='' />
															<span class="red" height="22">&nbsp;&nbsp;*</span>
														</td>
														<td align="left" class="menu_p">
															<div class="red_italic" id="attchar11Tip"></div>
														</td>
													</tr>
													<tr>
														<td height="19" align="right" class="menu_p">
															单位简称：
														</td>
														<td align="left" class="menu_p">
															<input type='text' name='attchar12' id="attchar12"
																size='26' value='' />
															<span class="red" height="22">&nbsp;&nbsp;*</span>
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
															<input type='text' id='attchar13' name='attchar13'
																size='26' value='' />
															<span class="red" height="22">&nbsp;&nbsp;*</span>
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
															<input type='text' id="attchar14" name='attchar14'
																size='26' value='' />
															<span class="red" height="22">&nbsp;&nbsp;*</span>
														</td>
														<td align="left" class="menu_p">
															<div class="red_italic" id="attchar14Tip"></div>
														</td>
													</tr>
													<tr>
														<td height="19" align="right" class="menu_p">
															联系电话：
														</td>
														<td align="left" class="menu_p">
															<input type='text' id='attchar15' name='attchar15'
																size='26' value='' />
															<span class="red" height="22">&nbsp;&nbsp;*</span>
														</td>
														<td align="left" class="menu_p">
															<div class="red_italic" id="attchar15Tip"></div>
														</td>
													</tr>
													<tr>
														<td height="19" align="right" class="menu_p">
															手机号码：
														</td>
														<td align="left" class="menu_p">
															<input type='text' id='attchar17' name='attchar17'
																size='26' value='' />
															<span class="red" height="22">&nbsp;&nbsp;*</span>
														</td>
														<td align="left" class="menu_p">
															<div class="red_italic" id="attchar17Tip"></div>
														</td>
													</tr>
													<tr>
														<td height="19" align="right" class="menu_p">
															E-mail：
														</td>
														<td align="left" class="menu_p">
															<input type='text' id='attchar16' name='attchar16'
																size='26' value='' />
															<span class="red" height="22">&nbsp;&nbsp;*</span>
														</td>
														<td align="left" class="menu_p">
															<div class="red_italic" id="attchar16Tip"></div>
														</td>
													</tr>
													<tr>
														<td height="19" align="right" class="menu_p">
															传真：
														</td>
														<td align="left" class="menu_p">
															<input type='text' name='attchar06' size='26' value='' />
														</td>
														<td align="left" class="menu_p"></td>
													</tr>
													<tr>
														<td height="19" colspan="3" align="right" class="menu_p">
															&nbsp;

														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td align="center">
												<img id="sbmintButton" style="cursor: pointer"
													src="${skinPath}images/hygl/login_07.gif" />
											</td>
										</tr>
									</table>
								</form>
							</td>
						</tr>
						<tr>
							<td>
								<img src="${skinPath}images/hygl/main_bottom.jpg" width="914"
									height="14" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>