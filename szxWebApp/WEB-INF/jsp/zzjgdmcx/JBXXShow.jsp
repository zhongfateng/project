<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/images/zzjgcx/images/skin.css">

	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/images/zzjgcx/images/skinone.css">
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
        <td width="17%" align="right"><a href="#" target="_self" onClick="logout();"><img src="<%=request.getContextPath()%>/resources/images/zzjgcx/imagess/out.gif" alt="安全退出" width="46" height="20" border="0"></a></td>
        <td>&nbsp;</td>
      </tr>
    </table></td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/man-info_06.png" class="bai14">
  <tr>
    <td height="12" style="background-<%=request.getContextPath()%>/resources/images/zzjgcx/images:url(../<%=request.getContextPath()%>/resources/images/zzjgcx/images/man-info_06.png); background-position:bottom;"></td>
  </tr>
  <tr>
    <td height="12" bgcolor="#F7F8F9"></td>
  </tr>
</table>


<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/man-info_14.png">
  <tr>
    <td width="11"><img src="<%=request.getContextPath()%>/resources/images/zzjgcx/images/man-info_12.png" width="11" height="30" /></td>
    <td width="144" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/man-info_13.png" class="txt_bt">&nbsp; <span class="login_txt_bt">组织机构代码查询</span></td>
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
                <input id="zzjgname" name="textfield" type="text" class="input4" value="请输入9位组织机构代码……" size="45">
              </label></td>
              <td> <a  href="javascript:;" id="zzjgcxByid"><img src="<%=request.getContextPath()%>/resources/images/zzjgcx/images/search.jpg" width="98" height="23" border="0"></a></td>
            </tr>
            <tr>
              <td height="40" align="right" class="login_txt_bt"><p>组织机构名称：</p></td>
              <td><label>
                <select name="select" class="input4">
                  <option>全国</option>
                </select>
                </label>
                  <input id="jgname" name="textfield2" type="text" class="input4" value="请输入机构全称或关键词……" size="35"></td>
              <td><a href="javascript:;" id="jgmohu"><img src="<%=request.getContextPath()%>/resources/images/zzjgcx/images/search.jpg" width="98" height="23" border="0"></a></td>
            </tr>
          </table>
            <span class="left_txt"><br>
          </span></td>
        <td width="1%">&nbsp;</td>
        <td width="35%" valign="top"><table width="100%" height="144" border="0" cellpadding="0" cellspacing="0" class="line_table">
            <tr>
              <td width="7%" height="27" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/news-title-bg02.gif"><img src="<%=request.getContextPath()%>/resources/images/zzjgcx/imagess/news-title-bg.gif" width="2" height="27"></td>
              <td width="93%" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/news-title-bg02.gif" class="left_bt2">最新动态</td>
            </tr>
            <tr>
              <td height="102" valign="top">&nbsp;</td>
              <td height="102" valign="top"><br>
                  <span class="left_txt">&nbsp;<img src="<%=request.getContextPath()%>/resources/images/zzjgcx/images/ts.gif" width="16" height="16"> 提示：<br>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;说明：输入组织机构代码查询可得到 </span><span class="left_ts">唯一</span><span class="left_txt">结果。通过机构名称查询有可能得到多条结果！<br>
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
      <!--JavaScript部分-->
        <SCRIPT language=javascript>
	function secBoard(n)
	{
	for(i=0;i<secTable.cells.length;i++)
	secTable.cells[i].className="sec1";
	secTable.cells[n].className="sec2";
	for(i=0;i<mainTable.tBodies.length;i++)
	mainTable.tBodies[i].style.display="none";
	mainTable.tBodies[n].style.display="block";
	}
          </SCRIPT>
        <!--HTML部分-->
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="72%" height="40"><TABLE width=100% border=0 cellPadding=0 cellSpacing=0 id=secTable>
              <TBODY>
                <TR align=middle height=20>
                  <TD height="40" align="center" class=sec2 onclick=secBoard(0)><DIV id="div">
                      <DIV id="div2">
                        <p>企业基本信息</p>
                      </DIV>
                  </DIV></TD>
                  <TD align="center" class=sec1 onclick=secBoard(1)><DIV id="div3">
                      <DIV id="div4">
                        <p>质量保证能力信息</p>
                      </DIV>
                  </DIV></TD>
                  <TD align="center" class=sec1 onclick=secBoard(2)><DIV id="div5">
                      <DIV id="div6">
                        <p>质量表现信息</p>
                      </DIV>
                  </DIV></TD>
                </TR>
              </TBODY>
            </TABLE></td>
            <td align="center"><img src="<%=request.getContextPath()%>/resources/images/zzjgcx/images/daochu.jpg" width="130"></td>
          </tr>
        </table>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><TABLE class=main_tab id=mainTable cellSpacing=0
cellPadding=0 width=100% border=0>
              <!--关于TBODY标记-->
              <TBODY style="DISPLAY: block">
                <TR>
                  <TD vAlign=top align=middle><TABLE width=98% height="345" border=0 align="center" cellPadding=0 cellSpacing=0>
                       <TBODY>
                        <TR>
                          <TD height="10" colspan="5"></TD>
                        </TR>
                        <TR>
                          <TD width="18%" height="40" align="right" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/news-title-bg.gif" class="left_bt2">该企业质量信用等级为：</TD>
                          <TD height="40" align="center" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/news-title-bg.gif" class="left_txt">&nbsp;</TD>
                          <TD width="22%" height="40" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/news-title-bg.gif" class="left_txt">&nbsp;</TD>
                          <TD height="40" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/news-title-bg.gif" class="left_txt">&nbsp;</TD>
                          <TD width="32%" height="40" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/news-title-bg.gif" class="left_txt">&nbsp;</TD>
                        </TR>
                        <TR>
                          <TD bgcolor="#FAFBFC">&nbsp;</TD>
                          <TD height="40" align="right" bgcolor="#FAFBFC" class="titlebt">&nbsp;</TD>
                          <TD bgcolor="#FAFBFC" class="left_txt">&nbsp;</TD>
                          <TD align="right" bgcolor="#FAFBFC" class="titlebt" >&nbsp;</TD>
                          <TD height="40" bgcolor="#FAFBFC" class="left_txt">&nbsp;</TD>
                        </TR>
                        <TR>
                          <TD rowspan="5" align="center" bgcolor="#FAFBFC" class="login_line"> <span class="login_txt_A"> ${xypjb.asslevel }</span> </TD>
                          <TD width="15%" height="40" align="right" bgcolor="#FAFBFC" class="titlebt">企 业 名 称：&nbsp;</TD>
                          <TD bgcolor="#FAFBFC" class="left_txt">${jbxx.comorgname }</TD>
                          <TD width="13%" align="right" bgcolor="#FAFBFC" class="titlebt" style="border-left:dashed #eeeeee 1px;">营业执照编号：&nbsp;</TD>
                          <TD height="40" bgcolor="#FAFBFC" class="left_txt"> ${jbxx.comliseid }</TD>
                        </TR>
                        <TR>
                          <TD height="40" align="right" bgcolor="#FAFBFC" class="titlebt">组织机构代码：&nbsp;</TD>
                          <TD bgcolor="#FAFBFC" class="left_txt">${jbxx.comcodes }</TD>
                          <TD align="right" bgcolor="#FAFBFC" class="titlebt"  style="border-left:dashed #eeeeee 1px;"><p>企 业 地 址：&nbsp;</p></TD>
                          <TD height="40" bgcolor="#FAFBFC" class="left_txt"> ${jbxx.comaddr }</TD>
                        </TR>
                        <TR>
                          <TD height="40" align="right" bgcolor="#FAFBFC" class="titlebt">行 政 区 划：&nbsp;</TD>
                          <TD bgcolor="#FAFBFC" class="left_txt">${jbxx.comadmindiv }</TD>
                          <TD align="right" bgcolor="#FAFBFC" class="titlebt"  style="border-left:dashed #eeeeee 1px;"><p>注 册 资 金：&nbsp;</p></TD>
                          <TD height="40" bgcolor="#FAFBFC" class="left_txt">5000万</TD>
                        </TR>
                        <TR>
                          <TD height="40" align="right" bgcolor="#FAFBFC" class="titlebt">法 人 代 表：&nbsp;</TD>
                          <TD bgcolor="#FAFBFC" class="left_txt">${jbxx.comagentname }</TD>
                          <TD align="right" bgcolor="#FAFBFC" class="titlebt"  style="border-left:dashed #eeeeee 1px;"><p>注 册 机 关：&nbsp;</p></TD>
                          <TD height="40" bgcolor="#FAFBFC" class="left_txt"></TD>
                        </TR>
                        <TR>
                          <TD height="40" align="right" bgcolor="#FAFBFC" class="titlebt"><p>法人代表身份证号：&nbsp;</p></TD>
                          <TD bgcolor="#FAFBFC" class="left_txt">${jbxx.comagentid }</TD>
                          <TD align="right" bgcolor="#FAFBFC" class="titlebt"  style="border-left:dashed #eeeeee 1px;"><p>成 立 日 期：&nbsp;</p></TD>
                          <TD height="40" bgcolor="#FAFBFC" class="left_txt"> ${jbxx.comfounddate }</TD>
                        </TR>
                        <TR>
                          <TD bgcolor="#FAFBFC">&nbsp;</TD>
                          <TD height="40" align="right" bgcolor="#FAFBFC" class="titlebt">&nbsp;</TD>
                          <TD bgcolor="#FAFBFC" class="left_txt">&nbsp;</TD>
                          <TD align="right" bgcolor="#FAFBFC" class="titlebt" >&nbsp;</TD>
                          <TD height="40" bgcolor="#FAFBFC" class="left_txt">&nbsp;</TD>
                        </TR>
                        <TR>
                          <TD height="15" colspan="5"></TD>
                        </TR>
                      </TBODY>
                  </TABLE></TD>
                </TR>
              </TBODY>
              <!--关于cells集合-->
               <TBODY style="DISPLAY: none">
                <TR>
                  <TD vAlign=top align=middle>
                  
                  <TABLE width=98% border=0 align="center" cellPadding=0 cellSpacing=0>
                    <TBODY>
                      <TR>
                        <TD height="10" colspan="5"></TD>
                      </TR>
                      <TR>
                        <TD width="2%" height="28" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/news-title-bg.gif"></TD>
                        <TD height="40" align="center" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/news-title-bg.gif" class="left_txt">&nbsp;</TD>
                        <TD height="40" align="right" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/news-title-bg.gif" class="left_ts">&nbsp;</TD>
                        <TD height="40" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/news-title-bg.gif" class="left_txt"><span class="login_txt_bt">行政许可信息</span></TD>
                        <TD height="40" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/news-title-bg.gif" class="left_txt">&nbsp;</TD>
                      </TR>
                      <TR>
                      
                      <TR>
                        <TD bgcolor="#FAFBFC">&nbsp;</TD>
                        <TD height="40" align="right" bgcolor="#FAFBFC" class="titlebt">&nbsp;</TD>
                        <TD bgcolor="#FAFBFC" class="left_txt">&nbsp;</TD>
                        <TD align="right" bgcolor="#FAFBFC" class="titlebt" >&nbsp;</TD>
                        <TD height="25" bgcolor="#FAFBFC" class="left_txt">&nbsp;</TD>
                      </TR>
                      <TR>
                        <TD bgcolor="#FAFBFC">&nbsp;</TD>
                        <TD width="17%" height="40" align="right" bgcolor="#FAFBFC" class="titlebt">企业名称：&nbsp;</TD>
                        <TD width="27%" bgcolor="#FAFBFC" class="left_txt">${xzxkxx.admcomname }</TD>
                        <TD width="16%" align="right" bgcolor="#FAFBFC" class="titlebt" style="border-left:dashed #eeeeee 1px;">行政许可分类：&nbsp;</TD>
                        <TD width="38%" height="25" bgcolor="#FAFBFC" class="left_txt"> ${xzxkxx.adminclasses }</TD>
                      </TR>
                      <TR>
                        <TD bgcolor="#FAFBFC">&nbsp;</TD>
                        <TD height="40" align="right" bgcolor="#FAFBFC" class="titlebt">产品分类：&nbsp;</TD>
                        <TD bgcolor="#FAFBFC" class="left_txt">${xzxkxx.adminproduct }</TD>
                        <TD align="right" bgcolor="#FAFBFC" class="titlebt"  style="border-left:dashed #eeeeee 1px;"><p>证书编号：&nbsp;</p></TD>
                        <TD height="25" bgcolor="#FAFBFC" class="left_txt"> ${xzxkxx.admincrenum }</TD>
                      </TR>
                      <TR>
                        <TD bgcolor="#FAFBFC">&nbsp;</TD>
                        <TD height="40" align="right" bgcolor="#FAFBFC" class="titlebt">发证机构：&nbsp;</TD>
                        <TD bgcolor="#FAFBFC" class="left_txt">${xzxkxx.adminorga }</TD>
                        <TD align="right" bgcolor="#FAFBFC" class="titlebt"  style="border-left:dashed #eeeeee 1px;"><p>发证日期：&nbsp;</p></TD>
                        <TD height="25" bgcolor="#FAFBFC" class="left_txt">${xzxkxx.admintime }</TD>
                      </TR>
                      <TR>
                        <TD bgcolor="#FAFBFC">&nbsp;</TD>
                        <TD height="40" align="right" bgcolor="#FAFBFC" class="titlebt">有效期：&nbsp;</TD>
                        <TD bgcolor="#FAFBFC" class="left_txt"> ${xzxkxx.adminendtime }</TD>
                        <TD align="right" bgcolor="#FAFBFC" class="titlebt"  style="border-left:dashed #eeeeee 1px;">&nbsp;</TD>
                        <TD height="25" bgcolor="#FAFBFC" class="left_txt">&nbsp;</TD>
                      </TR>
                      <TR>
                        <TD bgcolor="#FAFBFC">&nbsp;</TD>
                        <TD height="10" align="right" bgcolor="#FAFBFC" class="titlebt">&nbsp;</TD>
                        <TD bgcolor="#FAFBFC" class="left_txt">&nbsp;</TD>
                        <TD align="right" bgcolor="#FAFBFC" class="titlebt" >&nbsp;</TD>
                        <TD bgcolor="#FAFBFC" class="left_txt">&nbsp;</TD>
                      </TR>
                      <TR>
                        <TD height="15" colspan="5"></TD>
                      </TR>
                    </TBODY>
                  </TABLE>
                  
                  
                  
                   <TABLE width=98% border=0 align="center" cellPadding=0 cellSpacing=0>
                    <TBODY>
                      <TR>
                        <TD height="10" colspan="5"></TD>
                      </TR>
                      <TR>
                        <TD width="2%" height="28" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/news-title-bg.gif"></TD>
                        <TD height="40" align="center" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/news-title-bg.gif" class="left_txt">&nbsp;</TD>
                        <TD height="40" align="right" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/news-title-bg.gif" class="left_ts">&nbsp;</TD>
                        <TD height="40" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/news-title-bg.gif" class="left_txt"><span class="login_txt_bt">强制性认证信息</span></TD>
                        <TD height="40" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/news-title-bg.gif" class="left_txt">&nbsp;</TD>
                      </TR>
                      <TR>
                        <TD bgcolor="#FAFBFC">&nbsp;</TD>
                        <TD height="25" align="right" bgcolor="#FAFBFC" class="titlebt">&nbsp;</TD>
                        <TD bgcolor="#FAFBFC" class="left_txt">&nbsp;</TD>
                        <TD align="right" bgcolor="#FAFBFC" class="titlebt" >&nbsp;</TD>
                        <TD bgcolor="#FAFBFC" class="left_txt">&nbsp;</TD>
                      </TR>
                      <TR>
                        <TD bgcolor="#FAFBFC">&nbsp;</TD>
                        <TD width="17%" height="25" align="right" bgcolor="#FAFBFC" class="titlebt">企业名称：&nbsp;</TD>
                        <TD width="27%" bgcolor="#FAFBFC" class="left_txt">${rzrkxx.acccomname }</TD>
                        <TD width="16%" align="right" bgcolor="#FAFBFC" class="titlebt" style="border-left:dashed #eeeeee 1px;">证书编号：&nbsp;</TD>
                        <TD width="38%" bgcolor="#FAFBFC" class="left_txt"> ${rzrkxx.accnum }</TD>
                      </TR>
                      <TR>
                        <TD bgcolor="#FAFBFC">&nbsp;</TD>
                        <TD height="25" align="right" bgcolor="#FAFBFC" class="titlebt">发证机构：&nbsp;</TD>
                        <TD bgcolor="#FAFBFC" class="left_txt">${rzrkxx.accsendorg }</TD>
                        <TD align="right" bgcolor="#FAFBFC" class="titlebt"  style="border-left:dashed #eeeeee 1px;"><p>发证日期：&nbsp;</p></TD>
                        <TD bgcolor="#FAFBFC" class="left_txt">${rzrkxx.accsendtime } </TD>
                      </TR>
                      
                      
                      <TR>
                        <TD bgcolor="#FAFBFC">&nbsp;</TD>
                        <TD height="25" align="right" bgcolor="#FAFBFC" class="titlebt">有效日期：&nbsp;</TD>
                        <TD bgcolor="#FAFBFC" class="left_txt">${rzrkxx.accendtime }</TD>
                        <TD align="right" bgcolor="#FAFBFC" class="titlebt"  style="border-left:dashed #eeeeee 1px;">&nbsp;</TD>
                        <TD bgcolor="#FAFBFC" class="left_txt">&nbsp;</TD>
                      </TR>
                       <TR>
                        <TD bgcolor="#FAFBFC">&nbsp;</TD>
                        <TD height="25" align="right" bgcolor="#FAFBFC" class="titlebt">产品名称：&nbsp;</TD>
                        <TD bgcolor="#FAFBFC" class="left_txt"></TD>
                        <TD align="right" bgcolor="#FAFBFC" class="titlebt"  style="border-left:dashed #eeeeee 1px;"><p>产品分类：&nbsp;</p></TD>
                        <TD bgcolor="#FAFBFC" class="left_txt">${rzrkxx.accproclass}  </TD>
                      </TR>
                      
                      <TR>
                        <TD bgcolor="#FAFBFC">&nbsp;</TD>
                        <TD height="25" align="right" bgcolor="#FAFBFC" class="titlebt">&nbsp;</TD>
                        <TD bgcolor="#FAFBFC" class="left_txt">&nbsp;</TD>
                        <TD align="right" bgcolor="#FAFBFC" class="titlebt" >&nbsp;</TD>
                        <TD bgcolor="#FAFBFC" class="left_txt">&nbsp;</TD>
                      </TR>
                    </TBODY>
                  </TABLE>
                  
                  
                  
                  
                  
                  
                  
                  
                  
                  
                  </TD>
                </TR>
              </TBODY>
              <!--关于tBodies集合-->
               <TBODY style="DISPLAY: none">
                <TR>
                  <TD vAlign=top align=middle>
                  <c:forEach items="${jdjcbhgxx}" var="jditem" varStatus="i" begin="0">
                  <TABLE width=98% border=0 align="center" cellPadding=0 cellSpacing=0>
                    <TBODY>
                      <TR>
                        <TD height="10" colspan="5"></TD>
                      </TR>
                      <TR>
                        <TD width="2%" height="28" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/news-title-bg.gif"></TD>
                        <TD height="40" align="center" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/news-title-bg.gif" class="left_txt">&nbsp;</TD>
                        <TD height="40" align="right" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/news-title-bg.gif" class="left_ts">&nbsp;</TD>
                        <TD height="40" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/news-title-bg.gif" class="left_txt"><span class="login_txt_bt">检查内容</span></TD>
                        <TD height="40" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/news-title-bg.gif" class="left_txt">&nbsp;</TD>
                      </TR>
                      <TR>
                        <TD bgcolor="#FAFBFC">&nbsp;</TD>
                        <TD height="25" align="right" bgcolor="#FAFBFC" class="titlebt">&nbsp;</TD>
                        <TD bgcolor="#FAFBFC" class="left_txt">&nbsp;</TD>
                        <TD align="right" bgcolor="#FAFBFC" class="titlebt" >&nbsp;</TD>
                        <TD bgcolor="#FAFBFC" class="left_txt">&nbsp;</TD>
                      </TR>
                      <TR>
                        <TD bgcolor="#FAFBFC">&nbsp;</TD>
                        <TD width="17%" height="25" align="right" bgcolor="#FAFBFC" class="titlebt">检查内容：&nbsp;</TD>
                        <TD width="27%" bgcolor="#FAFBFC" class="left_txt">${jditem.checontent }</TD>
                        <TD width="16%" align="right" bgcolor="#FAFBFC" class="titlebt" style="border-left:dashed #eeeeee 1px;">不合格内容：&nbsp;</TD>
                        <TD width="38%" bgcolor="#FAFBFC" class="left_txt"> ${jditem.chefailpro }</TD>
                      </TR>
                      <TR>
                        <TD bgcolor="#FAFBFC">&nbsp;</TD>
                        <TD height="25" align="right" bgcolor="#FAFBFC" class="titlebt">检查日期：&nbsp;</TD>
                        <TD bgcolor="#FAFBFC" class="left_txt">${jditem.chetime }</TD>
                        <TD align="right" bgcolor="#FAFBFC" class="titlebt"  style="border-left:dashed #eeeeee 1px;"><p>检察人员：&nbsp;</p></TD>
                        <TD bgcolor="#FAFBFC" class="left_txt"> ${jditem.chepeople }</TD>
                      </TR>
                      <TR>
                        <TD bgcolor="#FAFBFC">&nbsp;</TD>
                        <TD height="25" align="right" bgcolor="#FAFBFC" class="titlebt">检查单位：&nbsp;</TD>
                        <TD bgcolor="#FAFBFC" class="left_txt">${jditem.checompany }</TD>
                        <TD align="right" bgcolor="#FAFBFC" class="titlebt"  style="border-left:dashed #eeeeee 1px;">&nbsp;</TD>
                        <TD bgcolor="#FAFBFC" class="left_txt">&nbsp;</TD>
                      </TR>
                      <TR>
                        <TD bgcolor="#FAFBFC">&nbsp;</TD>
                        <TD height="25" align="right" bgcolor="#FAFBFC" class="titlebt">&nbsp;</TD>
                        <TD bgcolor="#FAFBFC" class="left_txt">&nbsp;</TD>
                        <TD align="right" bgcolor="#FAFBFC" class="titlebt" >&nbsp;</TD>
                        <TD bgcolor="#FAFBFC" class="left_txt">&nbsp;</TD>
                      </TR>
                    </TBODY>
                  </TABLE>
                  </c:forEach>
                   <c:forEach items="${jlxx }" var="jlitem" varStatus="i" begin="0">
                    <TABLE width=98% border=0 align="center" cellPadding=0 cellSpacing=0>
                      <TBODY>
                        <TR>
                          <TD height="10" colspan="5" bgcolor="#FAFBFC"></TD>
                        </TR>
                        <TR>
                          <TD width="2%" height="28" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/news-title-bg.gif"></TD>
                          <TD height="40" align="center" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/news-title-bg.gif" class="left_txt">&nbsp;</TD>
                          <TD height="40" align="right" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/news-title-bg.gif" class="left_ts">&nbsp;</TD>
                          <TD height="40" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/news-title-bg.gif" class="left_txt"><span class="login_txt_bt">奖励</span></TD>
                          <TD height="40" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/news-title-bg.gif" class="left_txt">&nbsp;</TD>
                        </TR>
                        <TR>
                          <TD bgcolor="#FAFBFC">&nbsp;</TD>
                          <TD height="25" align="right" bgcolor="#FAFBFC" class="titlebt">&nbsp;</TD>
                          <TD bgcolor="#FAFBFC" class="left_txt">&nbsp;</TD>
                          <TD align="right" bgcolor="#FAFBFC" class="titlebt" >&nbsp;</TD>
                          <TD bgcolor="#FAFBFC" class="left_txt">&nbsp;</TD>
                        </TR>
                        <TR>
                          <TD bgcolor="#FAFBFC">&nbsp;</TD>
                          <TD width="17%" height="25" align="right" bgcolor="#FAFBFC" class="titlebt">企业名称：&nbsp;</TD>
                          <TD width="27%" bgcolor="#FAFBFC" class="left_txt">${jlitem.rewcomname }</TD>
                          <TD width="16%" align="right" bgcolor="#FAFBFC" class="titlebt" style="border-left:dashed #eeeeee 1px;">奖励类别：&nbsp;</TD>
                          <TD width="38%" bgcolor="#FAFBFC" class="left_txt"> ${jlitem.rewclass }</TD>
                        </TR>
                        <TR>
                          <TD bgcolor="#FAFBFC">&nbsp;</TD>
                          <TD height="25" align="right" bgcolor="#FAFBFC" class="titlebt">发证日期：&nbsp;</TD>
                          <TD bgcolor="#FAFBFC" class="left_txt">${jlitem.rewtime }</TD>
                          <TD align="right" bgcolor="#FAFBFC" class="titlebt"  style="border-left:dashed #eeeeee 1px;"><p>有效日期：&nbsp;</p></TD>
                          <TD bgcolor="#FAFBFC" class="left_txt"> ${jlitem.rewendtime }</TD>
                        </TR>
                        <TR>
                          <TD bgcolor="#FAFBFC">&nbsp;</TD>
                          <TD height="25" align="right" bgcolor="#FAFBFC" class="titlebt">发证机构：&nbsp;</TD>
                          <TD bgcolor="#FAFBFC" class="left_txt">${jlitem.reworg }</TD>
                          <TD align="right" bgcolor="#FAFBFC" class="titlebt"  style="border-left:dashed #eeeeee 1px;">&nbsp;</TD>
                          <TD bgcolor="#FAFBFC" class="left_txt">&nbsp;</TD>
                        </TR>
                        <TR>
                          <TD bgcolor="#FAFBFC">&nbsp;</TD>
                          <TD height="25" align="right" bgcolor="#FAFBFC" class="titlebt">&nbsp;</TD>
                          <TD bgcolor="#FAFBFC" class="left_txt">&nbsp;</TD>
                          <TD align="right" bgcolor="#FAFBFC" class="titlebt" >&nbsp;</TD>
                          <TD bgcolor="#FAFBFC" class="left_txt">&nbsp;</TD>
                        </TR>
                      </TBODY>
                    </TABLE>
                    </c:forEach>
                     <c:forEach items="${wfxx }" var="wfitem" varStatus="i" begin="0">
                    <TABLE width=98% border=0 align="center" cellPadding=0 cellSpacing=0>
                      <TBODY>
                        <TR>
                          <TD height="10" colspan="5" bgcolor="#FAFBFC"></TD>
                        </TR>
                        <TR>
                          <TD width="2%" height="28" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/news-title-bg.gif"></TD>
                          <TD height="40" align="center" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/news-title-bg.gif" class="left_txt">&nbsp;</TD>
                          <TD height="40" align="right" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/news-title-bg.gif" class="left_txt">&nbsp;</TD>
                          <TD height="40" align="left" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/news-title-bg.gif" class="login_txt_bt">质量违法</TD>
                          <TD height="40" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/news-title-bg.gif" class="left_txt">&nbsp;</TD>
                        </TR>
                        <TR>
                          <TD bgcolor="#FAFBFC">&nbsp;</TD>
                          <TD height="25" align="right" bgcolor="#FAFBFC" class="titlebt">&nbsp;</TD>
                          <TD bgcolor="#FAFBFC" class="left_txt">&nbsp;</TD>
                          <TD align="right" bgcolor="#FAFBFC" class="titlebt" >&nbsp;</TD>
                          <TD bgcolor="#FAFBFC" class="left_txt">&nbsp;</TD>
                        </TR>
                        <TR>
                          <TD bgcolor="#FAFBFC">&nbsp;</TD>
                          <TD width="17%" height="25" align="right" bgcolor="#FAFBFC" class="titlebt">违法违规类型：&nbsp;</TD>
                          <TD width="27%" bgcolor="#FAFBFC" class="left_txt">${wfitem.illclass }</TD>
                          <TD width="16%" align="right" bgcolor="#FAFBFC" class="titlebt" style="border-left:dashed #eeeeee 1px;">违规内容：&nbsp;</TD>
                          <TD width="38%" bgcolor="#FAFBFC" class="left_txt"> ${wfitem.illcontent }</TD>
                        </TR>
                        <TR>
                          <TD bgcolor="#FAFBFC">&nbsp;</TD>
                          <TD height="25" align="right" bgcolor="#FAFBFC" class="titlebt">处理结果：&nbsp;</TD>
                          <TD bgcolor="#FAFBFC" class="left_txt">${wfitem.illresult }</TD>
                          <TD align="right" bgcolor="#FAFBFC" class="titlebt"  style="border-left:dashed #eeeeee 1px;"><p>违法日期：&nbsp;</p></TD>
                          <TD bgcolor="#FAFBFC" class="left_txt"> ${wfitem.illtime }</TD>
                        </TR>
                        <TR>
                          <TD bgcolor="#FAFBFC">&nbsp;</TD>
                          <TD height="25" align="right" bgcolor="#FAFBFC" class="titlebt">处罚单位：&nbsp;</TD>
                          <TD bgcolor="#FAFBFC" class="left_txt">${wfitem.illcompany }</TD>
                          <TD align="right" bgcolor="#FAFBFC" class="titlebt"  style="border-left:dashed #eeeeee 1px;"><p>行政处罚类型：&nbsp;</p></TD>
                          <TD bgcolor="#FAFBFC" class="left_txt">${wfitem.illpuniclass }</TD>
                        </TR>
                        <TR>
                          <TD bgcolor="#FAFBFC">&nbsp;</TD>
                          <TD height="25" align="right" bgcolor="#FAFBFC" class="titlebt">&nbsp;</TD>
                          <TD bgcolor="#FAFBFC" class="left_txt">&nbsp;</TD>
                          <TD align="right" bgcolor="#FAFBFC" class="titlebt" >&nbsp;</TD>
                          <TD bgcolor="#FAFBFC" class="left_txt">&nbsp;</TD>
                        </TR>
                      </TBODY>
                    </TABLE>
                    </c:forEach>
                     <c:forEach items="${cpccbhgxx }" var="item" varStatus="i" begin="0">
                     <TABLE width=98% border=0 align="center" cellPadding=0 cellSpacing=0>
                      <TBODY>
                        <TR>
                          <TD height="10" colspan="5" bgcolor="#FAFBFC"></TD>
                        </TR>
                        <TR>
                          <TD width="2%" height="28" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/news-title-bg.gif"></TD>
                          <TD height="40" align="center" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/news-title-bg.gif" class="left_txt">&nbsp;</TD>
                          <TD height="40" align="right" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/news-title-bg.gif" class="left_txt">&nbsp;</TD>
                          <TD height="40" align="left" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/news-title-bg.gif" class="login_txt_bt">产品抽查</TD>
                          <TD height="40" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/news-title-bg.gif" class="left_txt">&nbsp;</TD>
                        </TR>
                        <TR>
                          <TD bgcolor="#FAFBFC">&nbsp;</TD>
                          <TD height="25" align="right" bgcolor="#FAFBFC" class="titlebt">&nbsp;</TD>
                          <TD bgcolor="#FAFBFC" class="left_txt">&nbsp;</TD>
                          <TD align="right" bgcolor="#FAFBFC" class="titlebt" >&nbsp;</TD>
                          <TD bgcolor="#FAFBFC" class="left_txt">&nbsp;</TD>
                        </TR>
                        <TR>
                          <TD bgcolor="#FAFBFC">&nbsp;</TD>
                          <TD width="17%" height="25" align="right" bgcolor="#FAFBFC" class="titlebt">产品名称：&nbsp;</TD>
                          <TD width="27%" bgcolor="#FAFBFC" class="left_txt">${item.raname }</TD>
                          <TD width="16%" align="right" bgcolor="#FAFBFC" class="titlebt" style="border-left:dashed #eeeeee 1px;">产品类别：&nbsp;</TD>
                          <TD width="38%" bgcolor="#FAFBFC" class="left_txt"> ${item.ranclass }</TD>
                        </TR>
                        <TR>
                          <TD bgcolor="#FAFBFC">&nbsp;</TD>
                          <TD height="25" align="right" bgcolor="#FAFBFC" class="titlebt">抽查日期：&nbsp;</TD>
                          <TD bgcolor="#FAFBFC" class="left_txt">${item.rantime }</TD>
                          <TD align="right" bgcolor="#FAFBFC" class="titlebt"  style="border-left:dashed #eeeeee 1px;"><p>抽查机构：&nbsp;</p></TD>
                          <TD bgcolor="#FAFBFC" class="left_txt"> ${item.rancompany }</TD>
                        </TR>
                        <TR>
                          <TD bgcolor="#FAFBFC">&nbsp;</TD>
                          <TD height="25" align="right" bgcolor="#FAFBFC" class="titlebt">检验结论：&nbsp;</TD>
                          <TD bgcolor="#FAFBFC" class="left_txt">${item.ranresult }</TD>
                         <TD height="25" align="right" bgcolor="#FAFBFC" class="titlebt">&nbsp;</TD>
                          <TD bgcolor="#FAFBFC" class="left_txt">&nbsp;</TD>
                        </TR>
                        <TR>
                          <TD bgcolor="#FAFBFC">&nbsp;</TD>
                          <TD height="25" align="right" bgcolor="#FAFBFC" class="titlebt">&nbsp;</TD>
                          <TD bgcolor="#FAFBFC" class="left_txt">&nbsp;</TD>
                          <TD align="right" bgcolor="#FAFBFC" class="titlebt" >&nbsp;</TD>
                          <TD bgcolor="#FAFBFC" class="left_txt">&nbsp;</TD>
                        </TR>
                      </TBODY>
                    </TABLE>
                    </c:forEach>
                    </TD>
                </TR>
              </TBODY>
              <!--关于display属性-->
              <TBODY style="DISPLAY: none">
                <TR>
                  <TD vAlign=top align=middle>&nbsp;</TD>
                </TR>
              </TBODY>
            </TABLE></td>
            <td>&nbsp;</td>
          </tr>
        </table>
        <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td colspan="4" valign="middle" height="50"><br>
                <table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
                  <tr>
                    <td></td>
                  </tr>
              </table></td>
          </tr>
          <tr>
            <td width="2%">&nbsp;</td>
            <td width="57%" class="left_txt"><img src="<%=request.getContextPath()%>/resources/images/zzjgcx/images/icon-mail2.gif" width="16" height="11"> 客户服务邮箱：123121212@qq.com<br>
                <img src="<%=request.getContextPath()%>/resources/images/zzjgcx/images/icon-phone.gif" width="17" height="14"> 官方网站：http://www.11212121.cn</td>
            <td width="1%">&nbsp;</td>
            <td width="40%">&nbsp;</td>
          </tr>
        </table>
        </td>
    <td width="14" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/man-info_21.png">&nbsp;</td>
  </tr>
</table>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/man-info_30.png">
  <tr>
    <td width="11" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/man-info_28.png">&nbsp;</td>
    <td width="144">&nbsp;</td>
    <td>&nbsp;</td>
    <td width="14" background="<%=request.getContextPath()%>/resources/images/zzjgcx/images/man-info_32.png">&nbsp;</td>
  </tr>
</table>
</body>
</html>
