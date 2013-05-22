<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.nbw.common.*"%>
<%@ page import="com.nbw.common.SysParameter"%>

<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String portalPath = SysParameter.getParameter("portalPath");
%>
<%
		String bzzyWebApp = SysParameter.getParameter("bzzyWebApp");
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>服务申请</title>
<style type="text/css">
<!--
.dlblue14 {
	font-size: 14px;
	font-weight: bold;
	color: #1F5EBE;
}
.dlblue {
	font-size: 14px;
	font-weight:bold;
	color: #1F5EBE;
}
.dlRed{
	font-size: 12px;
	color:#FF0000;
}
.send_input {  font-family: "宋体"; font-size: 12px; background-color: #F0F9FF; height: 18px; border: #A6D5F4; border-style: solid; border-top-width: 1px; border-right-width: 1px; border-bottom-width: 1px; border-left-width: 1px}
-->
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/component/jquery.js"></script>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/component/ymPrompt/skin/qq/ymPrompt.css"/>
    <script language="javascript" src="<%=request.getContextPath()%>/resources/component/ymPrompt/ymPrompt.js"></script>

<script type="text/javascript">
$(document).ready(function (){
    var userId = '<%=(String) session.getAttribute(Constants.SESSION_USER_ID)%>';
    if(userId==null||userId==""||userId=="null"){
  	  $("#loginfrom").hide();
    }else{
      $("#tologin").hide();
    }
    $("#unameInfo").hide();
    $("#utelInfo").hide();
    $("#utel").blur(function(){
       if(checkTel()==false){
         $("#utelInfo").show();
       }else{
         $("#utelInfo").hide();
       }
    });
    $("#utel").focus(function(){
       $("#utelInfo").hide();
    });
    $("#uname").blur(function(){
       if(checkName()==false){
          $("#unameInfo").show();
       }else{
          $("#unameInfo").hide();
       }
     });
     $("#uname").focus(function(){
         $("#unameInfo").hide();
     });
 });
 function checkName() {
	 var uname= $("#uname").val();
	 if(uname==""||uname==null){
	    return false;
	 }else{
	    return true;
	 }
 }
    
function checkTel(){
     var utel=$("#utel").val();
     var regu =/(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/;
     if(utel==""||utel==null) {
        return false;
     }else{
	     if(regu.test(utel)){
	     return true;
	     }else{
	     return false;
	     }
     }
}
                    
 //离线申请          
function toApply()  {
   if(checkTel()==true&&checkName()==true){
    if($("#uname").val().length<=20)
    {
    $.ajax({  
		type: "post",
		url: "bzptuser.action?m=frontoffApply",
		success: function(data, textStatus){
               $("#apply").html("</br></br></br></br></br><table width='300' border='0' cellspacing='0' cellpadding='0'><tr>"+data+"</tr></table>");
		},
		data :'uname='+applyfrom.uname.value+'&utel='+applyfrom.utel.value+'&objectId=${objectId}',
		complete: function(XMLHttpRequest, textStatus){
		},
		error: function(){
			alert("系统出现异常，请稍后再尝试!");
		}
     });
    }
    else
    {
    alert("姓名不要超过20个字");
    }
   } else{
    if(checkTel()==false) {
      $("#unameInfo").show();
    }
    if(checkName()==false) {
       $("#utelInfo").show();
    }
  }
}
    
//用户登录
  function subLogin1(){
				 $.ajax({  
					type: "post",
					url: "login.action?m=login",
					success: function(data, textStatus){
					    var dataStr = data.split(",");
						if("success"==dataStr[0]){
						a_flushEipSession(); 
						 $("#tologin").hide();
						  $("#loginfrom").show();
						 
						}else{
						    alert('用户名或密码有误，登录失败！');
						}
					},
					data :'username='+frm1.username.value+'&password='+frm1.password.value,
					complete: function(XMLHttpRequest, textStatus)
					{
						
					},
					error: function(){
						alert("系统出现异常，请稍后再尝试!");
					}
			   });
			}
			function subLogin2(index){
			   if (index==1)
			   //企业阅览室
			   	location.href="standardresources.action?m=batchBuyOrder";
			   else
			   //批量购买
			    location.href="standardresources.action?m=batchBuyStandards";
			}
			function fwdz()
			{
				location.href="bzptfw.action?m=openAddFwJyWindow&objectId=${objectId}";
			}
			
			function a_flushEipSession(){
				$.ajax({  
					type: "post",
					url: "<%=portalPath%>/ajaxlogin.do?username="+frm1.username.value,
					success: function(data, textStatus){
				   	
					},
					data :'username='+frm1.username.value+'&password='+frm1.password.value,
					error: function(){
						alert("系统出现异常，请稍后再尝试!");
					}
			   }); 
			}
			
			
		function zhuce(){
		   var url="<%=bzzyWebApp%>/bzptuser.action?m=frontEEIndex&fwid";
	        ymPrompt.win({message:url,
	                     width:630,height:580,title:'注册页面',maskAlpha:0.5,iframe:true});
	   }
			
</script>
<!-- PHPStat Start -->
				<script language="javascript">
				 var myDate = new Date();
	             var __$nodeid=20120456;
	             var __$contentid=2456;
	             var __$pubtime=myDate.toLocaleString();
	            </script>
	          <script language="JavaScript" charset="utf-8" src="http://stat.sac.gov.cn/phpstat/count/abemffgh/abemffgh.js" ></script>
	          <noscript><img src="http://stat.sac.gov.cn/phpstat/count/abemffgh/abemffgh.php" alt="" style="border:0" /></noscript>
<!-- /PHPStat End -->
</head>
<body>
<table width="681" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="20">&nbsp;</td>
  </tr>
</table>
<table width="681" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td style="background-image:url('<%=request.getContextPath()%>/resources/images/offservice/a-1.gif');" width="681" height="48"/>
  </tr>
</table>
<table width="681" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr bgcolor="#FFFFFF"> 
    <td width="7" height="258" style="background-image:url('<%=request.getContextPath()%>/resources/images/offservice/a-2.gif');" ></td>
    <td width="667" height="258" align="center" valign="top"> 
      <table width="667" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="335" height="165" align="right" valign="top"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td height="8"></td>
              </tr>
            </table>
            <div id="apply">
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td align="center" style="background-image:url('<%=request.getContextPath()%>/resources/images/offservice/dl-4.jpg');background-repeat:no-repeat;" width="30" height="35"></td>
                <td width="250" height="38" class="dlblue14" align="left">直接申请:请留下您的联系方式,我们会在3个工作日内联系您！</td>
              </tr>
            </table>
            <form action="" id="applyfrom">
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="47" height="35" align="center" class="dlblue" valign="top">姓名:</td>
                <td width="253" height="35" align="left" valign="top"> 
                  <input type="text" id="uname" name="uname" value="${loginUserInfo.name}" class="send_input" size="20" />
                  <div id="unameInfo" class="dlRed">用户名不能为空</div>
                </td>
              </tr>
              <tr> 
                <td height="35" align="center" class="dlblue" width="47" valign="top">电话:</td>
                <td height="35" width="253" align="left" valign="top"> 
                  <input type="text" id="utel" name="utel" value="${loginUserdetail.char17}" class="send_input" size="20"  />
                  <div id="utelInfo" class="dlRed">
                     11位手机号码,3-4位区号,7-8位直拨号码,1－4位分机号如:12345678901,1234-12345678-1234
				  </div>
                </td>
              </tr>
               <tr> 
                <td height="35" align="center" class="dlblue" width="50">服务:</td>
                <td height="35" width="253" align="left" class="dlblue14"> 
                 ${fwmc}
                </td>
              </tr>
            </table>
            </form>
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="50" align="center"><img src="<%=request.getContextPath()%>/resources/images/offservice/dl-6.jpg" width="94" height="26" onclick="toApply()"></td>
              </tr>
            </table>
            </div>
            <table width="330" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="37" height="40" align="center"><img src="<%=request.getContextPath()%>/resources/images/offservice/dl-8.gif" width="15" height="15" ></td>
                <td width="263" height="40" class="dlblue14" align="left">您也可以直接拨打我们的客服电话：400-650-6190</td>
              </tr>
            </table>
          </td>
          <td width="9" height="165"><img src="<%=request.getContextPath()%>/resources/images/offservice/a-5.gif" width="9" height="274"></td>
          <td width="323" height="165" align="center" valign="top"> 
           
          <div id="tologin">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="2"></td>
              </tr>
            </table>
            <table width="280" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="38" align="left" class="dlblue14">如果您想获得更多的定制化服务，<br/>请登录：</td>
              </tr>
            </table>
            <form action="" id="frm1">
            <table width="240" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="57" height="38" align="center" class="dlblue">用户名</td>
                <td width="163" height="38" align="left"> 
                  <input type="text" name="username" class="send_input" size="20" />
                </td>
              </tr>
              <tr> 
                <td height="38" align="center" class="dlblue" width="57">密　码</td>
                <td height="38" width="163" align="left"> 
                  <input type="password" name="password" class="send_input" size="20" />
                </td>
              </tr>
            </table>
            </form>
             <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="29">&nbsp;</td>
              </tr>
            </table>
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="50" align="center"><img src="<%=request.getContextPath()%>/resources/images/offservice/dl-7.jpg" width="94" height="26" onclick="subLogin1()"></td>
              </tr>
            </table>
            <table width="260" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="100" height="40" class="dlblue14" align="center">未注册？请先</td>
                <td width="160" height="40" align="left"><img src="<%=request.getContextPath()%>/resources/images/offservice/dl-9.gif" width="84" height="32" onclick="zhuce();" style="cursor:pointer"></td>
              </tr>
            </table>
            </div>
            <div id="loginfrom">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="10"></td>
              </tr>
            </table>
            <table width="280" border="0" cellspacing="0" cellpadding="0">
              <tr>
              <td height="35" align="left" class="dlblue14">在线定制：您可以点击定制按钮进行更多的服务定制：</td>
              </tr>
             </table>
              <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="29">&nbsp;</td>
              </tr>
            </table> <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="29">&nbsp;</td>
              </tr>
            </table>
            <c:choose>
            <c:when test="${fwmc=='批量购买'||fwmc=='电子阅览室'}">
            <c:if test="${fwmc=='批量购买'}">
            <form action="" method="post" id="frm2" >
            <img src="<%=request.getContextPath()%>/resources/images/offservice/dl-10.jpg" width="94" height="26" onclick="subLogin2(2)"/>
            </form>
            </c:if>
            <c:if test="${fwmc=='电子阅览室'}">
            <form action="" method="post" id="frm2" >
             <img src="<%=request.getContextPath()%>/resources/images/offservice/dl-10.jpg" width="94" height="26" onclick="subLogin2(1)"/>
            </form>
            </c:if>
            </c:when>
            <c:otherwise>
            <form action="" method="post" id="frm2" >
             <img src="<%=request.getContextPath()%>/resources/images/offservice/dl-10.jpg" width="94" height="26" onclick="fwdz()"/>
            </form>
            </c:otherwise>
            </c:choose>
            </div>
          </td>
        </tr>
      </table>
    </td>
    <td width="7" height="258" background="<%=request.getContextPath()%>/resources/images/offservice/a-3.gif"></td>
  </tr>
</table>
<table width="681" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr> 
    <td style="background-image: url('<%=request.getContextPath()%>/resources/images/offservice/a-4.gif')" width="681" height="28"/>
  </tr>
</table>
</body>
</html>


