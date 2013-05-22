<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/images/back/images/skin.css">
	<link rel="stylesheet" id='skin' type="text/css" href="<%=request.getContextPath()%>/resources/component/ymPrompt/skin/simple_gray/ymPrompt.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.6.2.js"></script>
	<script type="text/javascript" src ="<%=request.getContextPath()%>/resources/component/ymPrompt/ymPrompt.js"></script>
	<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath() %>/resources/component/My97DatePicker/WdatePicker.js"></script>
	
	<script type="text/javascript">
	
	
	$(function()
	{
	
	$("#searchid").click(function()
	{
	var sql="import.action?m=toPageXYPJBSearch"
	var asscodes=$("#zzjgdm").val();
	var asscomname=$("#qiyeming").val();
	var asslevel=$('#select3 option:selected').val();
	var	asstime=$('#data2').val();

	if(asscodes!=null&&!asscodes=='')
	{
	sql=sql+"&asscodes="+asscodes;
	
	}	
	if(asscomname!=null&&!asscomname=='')
	{
	sql=sql+"&asscomname="+encodeURI(encodeURI(asscomname));
	}
	if(asslevel!=null&&!asslevel=='')
	{
	sql=sql+"&asslevel="+asslevel;
	}
	if(asstime!=null&&!asstime=='')
	{
	
	sql=sql+"&asstime="+asstime;
	
	}
	if(sql=="import.action?m=toPageXYPJBSearch")
	{
	ymPrompt.alert("请输入查询条件");
	}
	else
	{
	window.location.href=sql;
	
	}
	});
	});
	</script>
	<script type="text/javascript">
	
	$(function()
	{
	
	$("#output").click(function(){
	
	
	window.location.href="import.action?m=toOutput";
	});
	});
	</script>
	<script type="text/javascript">
	function jump()
	{
		 var asscomname="${asscomname}";
		 asscomname=encodeURI(encodeURI(asscomname));
		var page = $("#pageid").val();
		var reg = /^[1-9][0-9]*$/;
		if(page=="" || isNaN(page) || page<=0 || page>${xypjbList.totalPage}){
			ymPrompt.alert('请输入正确页码！');
		}else{
			window.location.href="import.action?m=toPageXYPJBSearch&pageSize=${xypjbList.pageSize}&asscodes=${asscodes}&asslevel=${asslevel}&asstime=${asstime}&assdivision=${assdivision}&assid=${assid}&assclass=${assclass}&page="+page+"&asscomname="+asscomname;
		}
	}
	</script>
	<script type="text/javascript">
		function changepage(pid){
		 var asscomname="${asscomname}";
		 asscomname=encodeURI(encodeURI(asscomname));
	 	if(pid=='1'){
	    	window.location ="import.action?m=toPageXYPJBSearch&page=1&pageSize=${xypjbList.pageSize}&asscodes=${asscodes}&asslevel=${asslevel}&asstime=${asstime}&assdivision=${assdivision}&assclass=${assclass}&assid=${assid}&asscomname="+asscomname;
	    }
	    if(pid=='2'){
	    	window.location="import.action?m=toPageXYPJBSearch&page=${xypjbList.currentPage-1}&asscodes=${asscodes}&asslevel=${asslevel}&asstime=${asstime}&assdivision=${assdivision}&assclass=${assclass}&assid=${assid}&asscomname="+asscomname;
	    }
	    if(pid=='3'){
	    	window.location="import.action?m=toPageXYPJBSearch&page=${xypjbList.currentPage+1}&pageSize=${xypjbList.pageSize}&asscodes=${asscodes}&asslevel=${asslevel}&asstime=${asstime}&assdivision=${assdivision}&assclass=${assclass}&assid=${assid}&asscomname="+asscomname;
	    }
	    if(pid=='4'){
	   		window.location="import.action?m=toPageXYPJBSearch&page=${xypjbList.totalPage}&pageSize=${xypjbList.pageSize}&asscodes=${asscodes}&asslevel=${asslevel}&asstime=${asstime}&assdivision=${assdivision}&assclass=${assclass}&assid=${assid}&asscomname="+asscomname;
	    }
	    }
	</script>
  </head>
  
  <body>
   <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" background="<%=request.getContextPath()%>/resources/images/back/images/man-info_14.png">
  <tr>
    <td width="11"><img src="<%=request.getContextPath()%>/resources/images/back/images/man-info_12.png" width="11" height="30" /></td>
    <td width="144" align="center" background="<%=request.getContextPath()%>/resources/images/back/images/man-info_13.png" class="txt_bt"><span class="left_bt2">信用等级查询</span></td>
    <td>&nbsp;</td>
    <td width="14"><img src="<%=request.getContextPath()%>/resources/images/back/images/man-info_17.png" width="14" height="30" /></td>
  </tr>
</table>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#F7F8F9">
  <tr>
    <td width="11" background="<%=request.getContextPath()%>/resources/images/back/images/man-info_19.png">&nbsp;</td>
    <td><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td valign="middle">
        
        <div class="login_txt_tb0">
        
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="35" colspan="2" align="right" class="left_txt2"><span class="login_text">组织机构代码：
              </span>              <input id="zzjgdm" name="textfield2" type="text" class="input4" value="${asscodes}" size="30" /></td>
            <td width="40%" align="center" class="login_text">行 政 区 划 ：
              <select name="select" id="select" style="width:71px;">
                      <option value="北京">北京</option>
                      <option value="湖北">湖北</option>
                    </select>
              省&nbsp;&nbsp;
              <select name="select2" id="select2" style="width:71px;">
                <option value="北京">北京</option>
                <option value="武汉">武汉</option>
              </select>
              市</td>
            <td width="27%" align="left"><p class="login_text">信用等级：
              <select name="select3" id="select3" style="width:71px;">
                          <option value="">请选择</option>
                          <option value="A" <c:if test="${asslevel =='A'}">selected</c:if>>A</option>
                          <option value="B" <c:if test="${asslevel =='B'}">selected</c:if>>B</option>
                          <option value="C" <c:if test="${asslevel =='C'}">selected</c:if>>C</option>
                          <option value="D" <c:if test="${asslevel =='D'}">selected</c:if>>D</option>
                         
                        </select>
            </p></td>
          </tr>
          <tr>
            <td height="35" colspan="2" align="right" class=""><span class="login_text">企业名称：</span>              <input id="qiyeming" name="textfield4" type="text" class="input4" value="${asscomname }" size="30" /></td>
            <td align="center" class="login_text">数据截至时间：
              <input type="text" id="data2" name="EndDate"  value="${asstime}" class="Wdate"  onFocus="WdatePicker({isShowWeek:true})" /></td>
            <td height="20" class="login_txt_bt">&nbsp;</td>
          </tr>
          <tr>
            <td width="6%" align="right" class="login_txt_bt">&nbsp;</td>
            <td colspan="2" align="left" ></td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td height="40" align="right" class="login_txt_bt">&nbsp;</td>
            <td colspan="2" align="center" ><a href="javascript:;" id="searchid"><img src="<%=request.getContextPath()%>/resources/images/back/images/searchbb.gif" width="118" height="28" border="0" /></a>&nbsp;&nbsp; </td>
            <td>&nbsp;</td>
          </tr>
          
        </table>
        </div>
              <div>
              <tr>
            <td height="40" colspan="4" align="right" class="login_txt_bt"><table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
              <tr>
                <td></td>
              </tr>
            </table></td>
          </tr>
              
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td width="25%" height="30" background="<%=request.getContextPath()%>/resources/images/back/images/news_tb_02.gif"><span class="login_icon"><img src="<%=request.getContextPath()%>/resources/images/back/images/icon_01.gif" /></span><span class="txt_bt"> 信用等级信息</span></td>
                  <td width="25%" background="<%=request.getContextPath()%>/resources/images/back/images/news_tb_02.gif">&nbsp;</td>
                  <td width="49%" background="<%=request.getContextPath()%>/resources/images/back/images/news_tb_02.gif" align="right"><a href="javascript:;" id="output"><img src="<%=request.getContextPath()%>/resources/images/back/images/inputbb.gif"  border="0"/></a>&nbsp;</td>
                  <td width="1%" background="<%=request.getContextPath()%>/resources/images/back/images/news_tb_02.gif">&nbsp;</td>
                </tr>
              </table>
          <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">
                <tr>
                  <td width="18%" height="25" align="center" background="<%=request.getContextPath()%>/resources/images/back/images/a1.gif" class="txt_bt"><div id="u6">
                      <div id="u6_rtf"> 组织机构代码 </div>
                  </div></td>
                  <td width="26%" align="center" background="<%=request.getContextPath()%>/resources/images/back/images/a2.gif" class="txt_bt">企业名称</td>
                  <td width="16%" align="center" background="<%=request.getContextPath()%>/resources/images/back/images/a2.gif" class="txt_bt">信用等级 </td>
                  <td width="23%" align="center" background="<%=request.getContextPath()%>/resources/images/back/images/a2.gif" class="txt_bt">行政区划 </td>
                  <td width="17%" align="center" background="<%=request.getContextPath()%>/resources/images/back/images/a2.gif" class="txt_bt">数据截至时间 </td>
              </tr>
            <c:forEach items="${xypjbList.list}" var="info" begin="0" varStatus="i">
                <tr>
                  <td height="25" align="center" bgcolor="#F7F8F9" class="left_txt">${info.asscodes } </td>
                  <td align="center" bgcolor="#F7F8F9" class="left_txt">${info.asscomname } </td>
                  <td align="center" bgcolor="#F7F8F9" class="left_txt"><div id="u22">
                      <div class="login_txt_sm" id="u22_rtf"> ${info.asslevel } </div>
                  </div></td>
                  <td align="center" bgcolor="#F7F8F9" class="left_txt"><div id="u22">
                      <div id="u22_rtf"> ${info.assdivision }</div>
                  </div></td>
                  <td align="center" bgcolor="#F7F8F9" class="left_txt"><div id="u22">
                      <div class="left_txt_l" id="u22_rtf"> ${info.asstime } </div>
                  </div></td>
              </tr>
              </c:forEach> 
            </table>
        <table width="100%" height="40" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td align="right">
            <c:choose> 
             <c:when test="${xypjbList.currentPage==1}"> 
             <img src="<%=request.getContextPath()%>/resources/images/zzjgcx/images/pro_34.jpg" width="60" height="22" border="0"/>&nbsp;<img src="<%=request.getContextPath()%>/resources/images/zzjgcx/images/pro_36.jpg" width="60" height="22" border="0" />
              </c:when>
               <c:otherwise>
               <a href="javascript:changepage('1')"><img src="<%=request.getContextPath()%>/resources/images/zzjgcx/images/pro_35.jpg" width="60" height="22" border="0" /> </a>
               
                  <a href="javascript:changepage('2')"><img src="<%=request.getContextPath()%>/resources/images/zzjgcx/images/pro_37.jpg" width="60" height="22" border="0"/></a>
               
               </c:otherwise>
                 </c:choose>
           </td>
            <td align="center" class="left_txt">第
              <label>
                <input name="textfield3" type="text" id="pageid" class="input4" size="3" />
                </label>
              &nbsp;页
              <label>
												<input type="image"
													src="<%=request.getContextPath()%>/resources/images/back/images/go.gif"
													onclick="jump()" />
											</label>
              </td>
            <td align="left">
               <c:choose>
                        <c:when test="${xypjbList.currentPage < xypjbList.totalPage}">
                            <a href="javascript:changepage('3')"><img src="<%=request.getContextPath()%>/resources/images/zzjgcx/images/pro_38.jpg" width="60" height="22" border="0" /></a>
                           
                            <a href="javascript:changepage('4')"><img src="<%=request.getContextPath()%>/resources/images/zzjgcx/images/pro_40.jpg" width="60" height="22" border="0"/></a>
             </c:when>
            <c:otherwise>
            <img src="<%=request.getContextPath()%>/resources/images/zzjgcx/images/pro_39.jpg" width="60" height="22" border="0"/>&nbsp;
              <img src="<%=request.getContextPath()%>/resources/images/zzjgcx/images/pro_41.jpg" width="60" height="22" border="0"/>
            
            </c:otherwise>
            </c:choose>
          </td>
          </tr>
        </table>
      <tr>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td height="20"><table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
          <tr>
            <td></td>
          </tr>
        </table></td>
      </tr>
    </table>
        <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td colspan="2" valign="middle"><span class="left_txt"><br />
            </span></td>
            <td width="1%">&nbsp;</td>
            <td width="40%" valign="top">&nbsp;</td>
          </tr>
          <tr>
            <td width="2%">&nbsp;</td>
            <td width="57%" class="left_txt"><img src="<%=request.getContextPath()%>/resources/images/back/images/icon-mail2.gif" width="16" height="11" /> 客户服务邮箱：123121212@qq.com<br />
                <img src="<%=request.getContextPath()%>/resources/images/back/images/icon-phone.gif" width="17" height="14" /> 官方网站：http://www.11212121.cn</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
      </table></td>
    <td width="14" background="<%=request.getContextPath()%>/resources/images/back/images/man-info_21.png">&nbsp;</td>
  </tr>
</table>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" background="<%=request.getContextPath()%>/resources/images/back/images/man-info_30.png">
  <tr>
    <td width="11" background="<%=request.getContextPath()%>/resources/images/back/images/man-info_28.png">&nbsp;</td>
    <td width="144">&nbsp;</td>
    <td>&nbsp;</td>
    <td width="14" background="<%=request.getContextPath()%>/resources/images/back/images/man-info_32.png">&nbsp;</td>
  </tr>
</table>
   
  </body>
</html>
