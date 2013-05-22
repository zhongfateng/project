<%@ page language="java"  pageEncoding="utf-8"%>
<%@ page import="${longDomainClass}"%>
<html>
<head>
<title>编缉页面</title>
<link href="${r"${adminCssFile}"}" type="text/css" rel="stylesheet"> 
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath() %>/resources/component/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/validata.js"></script>
</head>
  
<body>
	<form action="${shortDomainClass?lower_case}.action?m=save" method="post">	
	  <table width="100%" height="30" border="0" cellpadding="0" cellspacing="0" >
        <tr class="tr_title">
          <td width="2%" background="${r"${skinPath}"}images/manage/dh_bg2.gif">&nbsp;</td>
          <td background="${r"${skinPath}"}images/manage/dh_bg2.gif">当前位置信息:***</td>
        </tr>
      </table>
	  <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#ACD6FF">
	  <#list propertyList as being>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">${being.name}:</td>
			 <#if (being.type='Date')>	
			 <fmt:formatDate value="${r"${"}${shortDomainClass?lower_case}.${being.name}}"  pattern="yyyy-MM-dd"  var="${being.name}"/>	         			
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="${being.name}_showS"  value="${r"${"}${being.name}}" class="Wdate"  onFocus="WdatePicker({isShowWeek:true})" />
				 </td>
			 <#else>	
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="${being.name}"  value="${r"${"}${shortDomainClass?lower_case}.${being.name}}" size="15" />
				 </td>
			 </#if>	
          </tr>
      </#list>
	  <tr>
	    <td height="40" colspan="2" align="center" bgcolor="#FFFFFF">
	      <input type="submit" value="提交" class="buttonF" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	      <input type="button" value="取消" class="buttonF" onClick="javascript:history.back()" />
	    </td>
	    </tr>
	  </table>	
	</form>
</body>
</html>