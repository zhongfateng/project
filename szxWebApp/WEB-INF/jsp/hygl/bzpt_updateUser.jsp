<%@ page contentType="text/html; charset=utf-8" language="java"
	import="com.nbw.sys.domain.SysUsersDetail" errorPage=""%>
<%@ include file="/resources/common/res_all.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
		<script language="javascript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/jquery.form.js"></script>
		<script type="text/javascript">
$(document).ready(function() {
	    $.formValidator.initConfig({onerror:function(msg){ ymPrompt.errorInfo({title:"提示",message:"校验没有通过，具体错误请看错误提示!"});}});
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
	            });
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
                 oncorrect: "输入正确"
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
      $("#sbmintButton").bind("click",function(){
			if($.formValidator.pageIsValid('1')){
		      
			      var options = {
					type: 'POST',
					async: false,
					dataType: 'content-type',
					success: function(data){
						if(data=="<%=Constants.DEFAULT_AJAX_FAILURE%>"){
						       ymPrompt.errorInfo({title:"提示",message:"资料修改错误！"});
						}else{
							 ymPrompt.succeedInfo({title:"提示",message:"资料修改成功！"});
						}
					}
				}
			 //提交form
			 $('#theFrom').ajaxSubmit(options);  
		    }
     });    
     
          
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
    
</script>
		<title>个人资料修改</title>

	</head>
	<%
		SysUsersDetail sysUsersDetail = (SysUsersDetail) request
				.getAttribute("userDetail");
	%>
	<body style="">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="500" align="center" valign="middle">
					<form action="bzptuser.action?m=frontUpdateUserInfo" method="post"
						id="theFrom">
						<table width="350" border="0" cellspacing="1" cellpadding="0"
							bgcolor="#00366C">
							<tr>
								<td height="50" align="left" bgcolor="#0051A2" class="">
									&nbsp;&nbsp;&nbsp;&nbsp;
									<label style="font-size: 14px; color: #CCC;">
										<strong>资料修改</strong>
									</label>
								</td>
							</tr>
							<tr>
								<td>
									<table width="600" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td height="30" align="right" class="menu_p"
												bgcolor="#FFFFFF">
												真实姓名：
											</td>
											<td align="left" class="menu_p" bgcolor="#FFFFFF">
												<input type="text" name="realName" id="realName" size='26'
													value="<%=request.getAttribute("realName")%>" />
												<span class="red" height="22">&nbsp;&nbsp;*</span>
											</td>
											<td align="left" class="menu_p" bgcolor="#FFFFFF">
												<div class="red_italic" id="realNameTip"></div>
											</td>
										</tr>
										<tr>
											<td height="30" align="right" bgcolor="#FFFFFF"
												class="menu_p">
												密码提示问题：
											</td>
											<td align="left" bgcolor="#FFFFFF" class="menu_p">
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
											<td align="left" bgcolor="#FFFFFF" class="menu_p">
												<div class="red_italic" id="attchar08Tip"></div>

											</td>
										</tr>
										<tr>
											<td height="30" align="right" bgcolor="#FFFFFF"
												class="menu_p">
												密码问题答案：
											</td>
											<td align="left" bgcolor="#FFFFFF" class="menu_p">
												<input type='text' name='attchar09' id='attchar09' size='26'
													value="<%=sysUsersDetail.getChar09() == null ? ""
					: sysUsersDetail.getChar09()%>" />
												<span class="red" height="22">&nbsp;&nbsp;*</span>
											</td>
											<td align="left" bgcolor="#FFFFFF" class="menu_p">
												<div class="red_italic" id="attchar09Tip"></div>

											</td>
										</tr>
										<tr>
											<td height="30" align="right" bgcolor="#FFFFFF"
												class="menu_p">
												所属区域：
											</td>
											<td width="222" align="left" bgcolor="#FFFFFF" class="menu_p">
												<select name="attchar10" onchange="others()" id="attchar10">
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
													<input type='text' name='attchar07' size='10'
														value='<%=sysUsersDetail.getChar07() == null ? ""
					: sysUsersDetail.getChar07()%>'
														id="t1" />
													<span class="red">*</span>
													<a href="javascript:others2()">返回</a>
												</div>
											</td>
											<td width="203" align="left" bgcolor="#FFFFFF" class="menu_p"></td>
										</tr>
										<tr>
										 <td  bgcolor="#FFFFFF" colspan="3" class="menu_p" height="19"><input type="checkbox" onclick="qy()" 
										  name="c2" style="margin-left: 80px"/>企业信息(点击可填写)>></td>
										</tr>
										<tr>
											<td height="30" align="right" bgcolor="#FFFFFF"
												class="menu_p">
												公司名称：
											</td>
											<td align="left" bgcolor="#FFFFFF" class="menu_p">
												<input type='text' id='attchar11' name='attchar11' size='26'
													value='<%=sysUsersDetail.getChar11() == null ? ""
					: sysUsersDetail.getChar11()%>' disabled=true />
												<span class="red" height="22">&nbsp;&nbsp;*</span>
											</td>
											<td align="left" bgcolor="#FFFFFF" class="menu_p">
												<div class="red_italic" id="attchar11Tip"></div>

											</td>
										</tr>
										<tr>
											<td height="30" align="right" bgcolor="#FFFFFF"
												class="menu_p">
												公司简称：
											</td>
											<td align="left" bgcolor="#FFFFFF" class="menu_p">
												<input type='text' id='attchar12' name='attchar12' size='26'
													value='<%=sysUsersDetail.getChar12() == null ? ""
					: sysUsersDetail.getChar12()%>' disabled=true />
												<span class="red" height="22">&nbsp;&nbsp;*</span>
											</td>
											<td align="left" bgcolor="#FFFFFF" class="menu_p">
												<div class="red_italic" id="attchar12Tip"></div>

											</td>
										</tr>
										<tr>
											<td height="30" align="right" bgcolor="#FFFFFF"
												class="menu_p">
												通信地址：
											</td>
											<td align="left" bgcolor="#FFFFFF" class="menu_p">
												<input type='text' id='attchar13' name='attchar13' size='26'
													value='<%=sysUsersDetail.getChar13() == null ? ""
					: sysUsersDetail.getChar13()%>' />
												<span class="red" height="22">&nbsp;&nbsp;*</span>
											</td>
											<td align="left" bgcolor="#FFFFFF" class="menu_p">
												<div class="red_italic" id="attchar13Tip"></div>
											</td>
										</tr>
										<tr>
											<td height="30" align="right" bgcolor="#FFFFFF"
												class="menu_p">
												邮政编码：
											</td>
											<td align="left" bgcolor="#FFFFFF" class="menu_p">
												<input type='text' id='attchar14' name='attchar14' size='26'
													value='<%=sysUsersDetail.getChar14() == null ? ""
					: sysUsersDetail.getChar14()%>' />
												<span class="red" height="22">&nbsp;&nbsp;*</span>
											</td>
											<td align="left" bgcolor="#FFFFFF" class="menu_p">
												<div class="red_italic" id="attchar14Tip"></div>
											</td>
										</tr>
										<tr>
											<td height="30" align="right" class="menu_p"
												bgcolor="#FFFFFF">
												联系电话：
											</td>
											<td align="left" bgcolor="#FFFFFF" class="menu_p">
												<input type='text' id='attchar15' name='attchar15' size='26'
													value='<%=sysUsersDetail.getChar15()==null ? "": sysUsersDetail
					.getChar15()%>' />
												<span class="red" height="22">&nbsp;&nbsp;*</span>
											</td>
											<td align="left" class="menu_p" bgcolor="#FFFFFF">
												<div class="red_italic" id="attchar15Tip"></div>
											</td>
										</tr>
										<tr>
											<td height="30" align="right" class="menu_p"
												bgcolor="#FFFFFF">
												手机号码：
											</td>
											<td align="left" class="menu_p" bgcolor="#FFFFFF">
												<input type='text' id='attchar17' name='attchar17' size='26'
													value='<%=sysUsersDetail.getChar17() == null ? ""
					: sysUsersDetail.getChar17()%>' />
												<span class="red" height="22">&nbsp;&nbsp;*</span>
											</td>
											<td align="left" class="menu_p" bgcolor="#FFFFFF">
												<div class="red_italic" id="attchar17Tip"></div>
											</td>
										</tr>
										<tr>
											<td height="30" align="right" bgcolor="#FFFFFF"
												class="menu_p">
												E-mail：
											</td>
											<td align="left" bgcolor="#FFFFFF" class="menu_p">
												<input type='text' id='attchar16' name='attchar16' size='26'
													value='<%=sysUsersDetail.getChar16() == null ? ""
					: sysUsersDetail.getChar16()%>' />
												<span class="red" height="22">&nbsp;&nbsp;*</span>
											</td>
											<td align="left" bgcolor="#FFFFFF" class="menu_p">
												<div class="red_italic" id="attchar16Tip">
													>
												</div>
											</td>
										</tr>
										<tr>
											<td height="30" align="right" bgcolor="#FFFFFF"
												class="menu_p">
												传真：
											</td>
											<td align="left" bgcolor="#FFFFFF" class="menu_p">
												<input type='text' name='attchar06' size='26'
													value='<%=sysUsersDetail.getChar06() == null ? ""
					: sysUsersDetail.getChar06()%>' />
											</td>
											<td align="left" bgcolor="#FFFFFF" class="menu_p"></td>
										</tr>
										<tr>
											<td align="center" bgcolor="#FFFFFF" colspan="3">
												<img id="sbmintButton" style="cursor: pointer"
													src="${skinPath}images/hygl/edit.gif" />
											</td>

										</tr>
									</table>
								</td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
		</table>
		<script language="javascript">
       var a8=document.getElementById('attchar08');
	   a8.value='<%=sysUsersDetail.getChar08()%>';
	   var a10=document.getElementById('attchar10');
	   a10.value='<%=sysUsersDetail.getChar10()%>';
  </script>
	</body>
</html>