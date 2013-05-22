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
	                  $("#realName").formValidator({
	                    onshow: "如果您是个人用户，请输入真实姓名",
	                    onfocus: "真实姓名"
	                  });
	                  $("#attchar11").formValidator({
	                    onshow: "如果您是企业用户，请输入公司名称",
	                    onfocus: "公司名称"
	                });
	                 $("#attchar12").formValidator({
	                    onshow: "请输入公司简称",
	                    onfocus: "公司简称"
	                }); /*.inputValidator({
	                    min: 1,
	                    onerror: "单位名称不能为空,请确认"
	                }).inputValidator({
	                    max:30,
	                    onerror: "单位名称不能超过30个字符"
	                });*/
	                 /*.inputValidator({
	                    min: 1,
	                    onerror: "单位简称不能为空,请确认"
	                }).inputValidator({
	                    max:10,
	                    onerror: "单位简称不能超过10个字符"
	                });*/
	                 /*
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
		                */
	              $("#sbmintButton").click(function(){
	                  if($.formValidator.pageIsValid('1')){
	                     $("#theFrom").get(0).submit();
	                  }
	              })                                
})
  
</script>
	</head>
	<body style="background-color: ;width: 800">
		<table width="100%" height="30" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td>
					<table width="650" border="0" align="center" cellpadding="0"
						cellspacing="0">
						
						<tr>
							<td background=""
								align="center">
								<script type="text/javascript">
								   function tijiao(){
                                        var rename=document.getElementById("realName").value;
                                        var att1=document.getElementById("attchar11").value;
                                        var att2=document.getElementById("attchar12").value;
                                        if(rename!="" && rename!=null || att1!="" && att1!=null && att2!="" && att2!=null){
                                           theFrom.action="bzptuser.action?m=saveUserDetail";
                                           theFrom.submit();
                                        }else{
                                        alert("请填写(真实姓名)或者(公司名称，公司简称)其中一项");
                                        }
                                       }
								</script>
								<form  method="post" 
									id="theFrom">
									<table width="650" border="0" align="center" cellpadding="0"
										cellspacing="0">
										<tr>
											<td>
												<table width="650" border="0" cellpadding="0"
													cellspacing="0"
													background="${skinPath}images/hygl/login_10.gif">
													<tr>
														<td width="17%" height="40" align="center"
															background="${skinPath}images/hygl/login_09.gif"
															class="title">
															  补充个人信息
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
												<table width="650" border="0" cellpadding="0"
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
												真实姓名：
											</td>
											<td align="left" class="menu_p">
												<input type="text" name="realName" id="realName" size='26'
												/>
												
											</td>
											<td align="left" class="menu_p">
												<div class="red_italic" id="realNameTip"></div>
											</td>
										</tr>
													<tr>
														<td height="19" align="right" class="menu_p">
															单位名称：
														</td>
														<td align="left" class="menu_p">
															<input type='text' name='attchar11' id="attchar11"
																size='26' value='' />
															
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
															
														</td>
														<td align="left" class="menu_p">
															<div class="red_italic" id="attchar12Tip"></div>
														</td>
													</tr>
													<!-- 
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
													 -->
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
												<img id="sbmintButton" onclick="tijiao()" style="cursor: pointer"
													src="${skinPath}images/hygl/login_07.gif" />
											</td>
										</tr>
									</table>
								</form>
							</td>
						</tr>
						<tr>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>