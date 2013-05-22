<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>实名制质量信用信息化平台</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style>
	A {text-decoration: NONE} 
	</style>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/images/zzjgcx/images/skin.css">
	<link rel="stylesheet" type="text/css" href="styles.css">
	<link rel="stylesheet" id='skin' type="text/css" href="<%=request.getContextPath()%>/resources/component/ymPrompt/skin/simple_gray/ymPrompt.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.6.2.js"></script>
	<script type="text/javascript" src ="<%=request.getContextPath()%>/resources/component/ymPrompt/ymPrompt.js"></script>
	<script type="text/javascript">
	$(function(){
		
		$("#zzjgcxByid").click(function(){
		var inputName= $("#zzjgname").val();
			if(inputName=="请输入9位组织机构代码……")
			{
			ymPrompt.alert("请输入组织机构代码");
			}
			else{
			var number=inputName.length;
			inputName=$.trim(inputName);
			var reg=/^[A-Za-z0-9]+$/
			if(number!=9||!reg.test(inputName))
			{
			ymPrompt.alert("请输入正确的组织机构代码");
			}
			else
			{
			window.location.href="jbxx.action?m=frontToGetDetailByJbId&id="+inputName;
			
			}
			
			}
		
		});
		
	});

	
</script>
	<script type="text/javascript">
		$(function(){
		
		$("#zzjgname").click(function(){
		this.value="";
		});
		
		
		$("#jgname").click(function(){
		this.value="";
		});
		
		
		});
	
	
	</script>
	<script type="text/javascript">
		$(function(){
		
		$("#jgmohu").click(function()
		{
		var inputName= $("#jgname").val();
		if(inputName=="请输入机构全称或关键词……"||inputName=="")
		{
		ymPrompt.alert("请输入组织机构全程或关键字");
		}
		else
		{
		
		inputName=$.trim(inputName);
		
		var reg=/^[\u4e00-\u9fa5]+$/i; 
		if(!reg.test(inputName))
		{
			ymPrompt.alert("输入的内容包括非法字符");
		}
		else
		{
			//window.location.href="jbxx.action?m=frontToGetList&inputName="+encodeURI(encodeURI(inputName));
		window.location.href="jbxx.action?m=frontToShowList&inputName="+encodeURI(encodeURI(inputName));
		
		}
		
		
		
		}
		});
		
		});
	</script>


  </head>
  
  <body>
   <table width="100%" height="64" border="0" cellpadding="0" cellspacing="0" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/man-info_02.png">
  <tr>
    <td height="64"><img src="<%=request.getContextPath()%>/resources/images/zzjgcx/images/man-info_01.png" width="534" height="78"></td>
    <td width="380" height="78" valign="top" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/man-info_04.png"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="38" colspan="2" align="right" class="login_txt">&nbsp;</td>
        <td width="5%">&nbsp;</td>
      </tr>
      <tr>
        <td width="78%" height="38" align="right" class="login_txt">2012年11月28日&nbsp;&nbsp;星期四<a href="#" target="_self" class="login_txt" onClick="logout();"> </a>&nbsp;&nbsp;您好：张三</td>
        <td width="17%" align="right"><a href="#" target="_self" onClick="logout();"><img src="images/out.gif" alt="安全退出" width="46" height="20" border="0"></a></td>
        <td>&nbsp;</td>
      </tr>
    </table></td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0" background="image/man-info_06.png" class="bai14">
  <tr>
    <td height="12" style="background-image:url(../image/man-info_06.png); background-position:bottom;"></td>
  </tr>
  <tr>
    <td height="12" bgcolor="#F7F8F9"></td>
  </tr>
</table>

<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" background="image/man-info_14.png">
  <tr>
    <td width="11"><img src="<%=request.getContextPath()%>/resources/images/zzjgcx/images/man-info_12.png" width="11" height="30" /></td>
    <td width="144" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/man-info_13.png" class="txt_bt">&nbsp;<span class="login_txt_bt"> 组织机构代码查询</span></td>
    <td>&nbsp;</td>
    <td width="14"><img src="<%=request.getContextPath()%>/resources/images/zzjgcx/images/man-info_17.png" width="14" height="30"></td>
  </tr>
</table>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#F7F8F9">
  <tr>
    <td width="11" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/man-info_19.png">&nbsp;</td>
    <td><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td colspan="2" valign="middle"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="24%">&nbsp;</td>
            <td width="51%">&nbsp;</td>
            <td width="25%">&nbsp;</td>
          </tr>
          <tr>
            <td height="40" align="right" class="login_txt_bt">组织机构代码：</td>
            <td ><label>
              <input id="zzjgname" name="textfield" type="text" class="input4" value="请输入9位组织机构代码……" size="45" >
            </label></td>
            <td><a  href="javascript:;" id="zzjgcxByid"><img src="<%=request.getContextPath()%>/resources/images/zzjgcx/images/search.jpg" width="98" height="23" border="0"></a></td>
          </tr>
          <tr>
            <td height="40" align="right" class="login_txt_bt"><p>组织机构名称：</p></td>
            <td><label>
              <select name="select" class="input4">
                <option>全国</option>
              </select>
            </label>
              <input  id="jgname" name="textfield2" type="text" class="input4" value="请输入机构全称或关键词……" size="35"></td>
            <td><a href="javascript:;" id="jgmohu"><img src="<%=request.getContextPath()%>/resources/images/zzjgcx/images/search.jpg" width="98" height="23" border="0"></a></td>
          </tr>
        </table>          
          <span class="left_txt"><br>
        </span></td>
        <td width="1%">&nbsp;</td>
        <td width="35%" valign="top"><table width="100%" height="144" border="0" cellpadding="0" cellspacing="0" class="line_table">
            <tr>
              <td width="7%" height="27" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/news-title-bg02.gif"><img src="images/news-title-bg.gif" width="2" height="27"></td>
              <td width="93%" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/news-title-bg02.gif" class="left_bt2">最新动态</td>
            </tr>
            <tr>
              <td height="102" valign="top">&nbsp;</td>
              <td height="102" valign="top"><br>
                  <span class="left_txt">&nbsp;<img src="<%=request.getContextPath()%>/resources/images/zzjgcx/images/ts.gif" width="16" height="16"> 提示：<br>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;说明：输入组织机构代码查询可得到                  </span><span class="left_ts">唯一</span><span class="left_txt">结果。通过机构名称查询有可能得到多条结果！<br>
                      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;　 </span></td>
            </tr>
            <tr>
              <td height="5" colspan="2">&nbsp;</td>
            </tr>
        </table></td>
      </tr>
      <tr>
        <td height="40" colspan="4"><table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
            <tr>
              <td></td>
            </tr>
        </table></td>
      </tr>
    </table>      
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td colspan="2" valign="middle"><span class="left_txt"><br>
            </span></td><td width="1%">&nbsp;</td>
          <td width="40%" valign="top">&nbsp;</td>
        </tr>
        <tr>
          <td width="2%">&nbsp;</td>
          <td width="57%" class="left_txt"><img src="<%=request.getContextPath()%>/resources/images/zzjgcx/images/icon-mail2.gif" width="16" height="11"> 客户服务邮箱：123121212@qq.com<br>
              <img src="<%=request.getContextPath()%>/resources/images/zzjgcx/images/icon-phone.gif" width="17" height="14"> 官方网站：http://www.11212121.cn</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
      </table>
      <p>&nbsp;</p>
    </td>
    <td width="14" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/man-info_21.png">&nbsp;</td>
  </tr>
</table>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" background="image/man-info_30.png">
  <tr>
    <td width="11" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/man-info_28.png">&nbsp;</td>
    <td width="144">&nbsp;</td>
    <td>&nbsp;</td>
    <td width="14" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/man-info_32.png">&nbsp;</td>
  </tr>
</table>
    
  </body>
</html>
