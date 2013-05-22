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
    
    <title>My JSP 'importList.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" id='skin' type="text/css" href="<%=request.getContextPath()%>/resources/component/ymPrompt/skin/qq/ymPrompt.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.6.2.js"></script>
	<script type="text/javascript" src ="<%=request.getContextPath()%>/resources/component/ymPrompt/ymPrompt.js"></script>
	<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath() %>/resources/component/My97DatePicker/WdatePicker.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/images/back/images/skin.css">

	
	<script type="text/javascript">
function setValue(x)
{
document.getElementById(x).value='';
}
</script>
	

	<script type="text/javascript">
	
	$(function(){
	
	
	
	$("#searchid").click(function()
	{
	var sql="import.action?m=frontToImportListBy";
	var slec=$('#slec option:selected').text();
	var inputtest=$('#inputid').val();
	var startdate=$('#data1').val();
	var enddate=$('#data2').val();
	if(slec!='请选择')
	{
	sql=sql+"&filetype="+encodeURI(encodeURI(slec));

	}
	if(inputtest!='请输入企业名称'&&inputtest!='')
	{
	sql=sql+"&importfile="+encodeURI(encodeURI(inputtest));
	}
	if(startdate!=''&&enddate!=''&&startdate<enddate)
	{
	sql=sql+"&startdate="+encodeURI(encodeURI(startdate))+"&enddate="+encodeURI(encodeURI(enddate));
	
	}

	
	
	
	
	
	if(sql=="import.action?m=frontToImportListBy")
	{
	
	ymPrompt.alert("请选择输入条件");
	
	
	}
	
	else
	{
	window.location.href=sql;
	}
	
	}
	
	);
	});
	
	
	
	
	
	
	
	</script>
	<script type="text/javascript">
	function changepage(pid){
	
		var filetype="${filetype}";
		filetype=encodeURI(encodeURI(filetype));
		var importfile="${importfile}";
		importfile=encodeURI(encodeURI(importfile));
		var startdate="${startdate}";
		startdate=encodeURI(encodeURI(startdate));
		var enddate="${enddate}";
		enddate=encodeURI(encodeURI(enddate));
	    if(pid=='1'){
	    	window.location = "import.action?m=ToImportListBy&page=1&pageSize=${importList.pageSize}&filetype="+filetype+"&importfile="+importfile+"&startdate="+startdate+"&enddate="+enddate;
	    }
	    if(pid=='2'){
	    	window.location="import.action?m=ToImportListBy&page=${importList.currentPage-1}&pageSize=${importList.pageSize}&filetype="+filetype+"&importfile="+importfile+"&startdate="+startdate+"&enddate="+enddate;
	    }
	    if(pid=='3'){
	    	window.location="import.action?m=ToImportListBy&page=${importList.currentPage+1}&pageSize=${importList.pageSize}&filetype="+filetype+"&importfile="+importfile+"&startdate="+startdate+"&enddate="+enddate;
	    }
	    if(pid=='4'){
	   		window.location="import.action?m=ToImportListBy&page=${impportList.totalPage}&pageSize=${importList.pageSize}&filetype="+filetype+"&importfile="+importfile+"&startdate="+startdate+"&enddate="+enddate;
	    }
	}
</script>
  </head>
  
  <body>
   <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" background="<%=request.getContextPath()%>/resources/images/back/images/man-info_14.png">
  <tr>
    <td width="11"><img src="<%=request.getContextPath()%>/resources/images/back/images/man-info_12.png" width="11" height="30" /></td>
    <td width="144" align="center" background="<%=request.getContextPath()%>/resources/images/back/images/man-info_13.png" class="txt_bt"><span class="login_txt_bt">数据管理</span></td>
    <td>&nbsp;</td>
    <td width="14"><img src="<%=request.getContextPath()%>/resources/images/back/images/man-info_17.png" width="14" height="30" /></td>
  </tr>
</table>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#F7F8F9">
  <tr>
    <td width="11" background="<%=request.getContextPath()%>/resources/images/back/images/man-info_19.png">&nbsp;</td>
    <td><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td valign="middle"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td colspan="3">&nbsp;</td>
            </tr>
          
            <td width="35%" align="center" class="left_txt_tb0">
            <table><tr>
            <td height="35" align="right"><span class="login_text">数&nbsp;&nbsp; 据&nbsp;&nbsp; 类&nbsp; &nbsp;型&nbsp; &nbsp;：</span></td>  
                <td align="left"><label>
                  <select name="select3" class="input4" id="slec">
                  <option value="请选择" <c:if test="${filetype =='请选择'}">selected</c:if>>请选择</option>
 				<option value="企业基本信息" <c:if test="${filetype =='企业基本信息'}">selected</c:if>>企业基本信息</option>
 				<option value="行政许可信息" <c:if test="${filetype =='行政许可信息'}">selected</c:if>>行政许可信息</option>
 				<option value="强转性认证信息" <c:if test="${filetype =='强转性认证信息'}">selected</c:if>>强转性认证信息</option>
				<option value="监督检查不合格信息" <c:if test="${filetype =='监督检查不合格信息'}">selected</c:if>>监督检查不合格信息</option>
				<option value="监督抽查不合格信息" <c:if test="${filetype =='监督抽查不合格信息'}">selected</c:if>>监督抽查不合格信息</option>
				<option value="质量违法信息" <c:if test="${filetype =='质量违法信息'}">selected</c:if>>质量违法信息</option>
				<option value="奖励信息" <c:if test="${filetype =='奖励信息'}">selected</c:if>>奖励信息</option>
                </select>
                </label>
              </td> 
                </tr>
              <tr>
              <td height="35" align="right" class="login_text">文 &nbsp;&nbsp;件&nbsp; &nbsp;名&nbsp; &nbsp;称&nbsp; &nbsp;：</td> 

              <td align="left"> <input id="inputid"  onfocus="setValue(this.id)" name="textfield2" type="text" class="input4" value="${importfile}" size="30">
              </td></tr><tr>
              <td height="35" align="right" class="login_text"> 导 &nbsp;&nbsp;入&nbsp; &nbsp;时&nbsp; &nbsp;间&nbsp;&nbsp; ：    </td>
              <td align="left"><input type="text" id="data1" name="StartDate"  value="${startdate}" class="Wdate"  onFocus="WdatePicker({isShowWeek:true})" />
                到
               <input type="text" id="data2" name="EndDate"  value="${enddate}" class="Wdate"  onFocus="WdatePicker({isShowWeek:true})" /></td></tr>
              <tr>
                <td height="50" colspan="2" align="center"> <a href="javascript:;" id="searchid" ><img src="<%=request.getContextPath()%>/resources/images/back/images/search.jpg" width="98" height="23" border="0"></a></td></tr></table>
            
            </td>
 
          <tr>
            <td height="40" align="right" class="login_txt_bt"><table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
              <tr>
                <td></td>
                </tr>
              </table></td>
          </tr>
        </table>
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td width="15%" align="left" height="25" background="<%=request.getContextPath()%>/resources/images/back/images/news_tb_02.gif"><span class="left_txt"><img src="<%=request.getContextPath()%>/resources/images/back/images/tb_1.gif" /> 数据导入列表</span></td>
                  <td width="74%" align="right" background="<%=request.getContextPath()%>/resources/images/back/images/news_tb_02.gif" class="left_bt2"><input type="checkbox" name="checkbox7" value="checkbox" />
                    全选</td>
                  <td colspan="2" align="center" background="<%=request.getContextPath()%>/resources/images/back/images/news_tb_02.gif"><img src="<%=request.getContextPath()%>/resources/images/back/images/aniu_02.gif"  /></td>
                </tr>
                <tr>
                  <td height="5" colspan="3"></td>
                  <td width="1%"></td>
                </tr>
              </table>
          <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">
                <tr>
                  <td align="center" background="<%=request.getContextPath()%>/resources/images/back/images/a1.gif" class="txt_bt"><label>
                    <input type="checkbox" name="checkbox" value="checkbox" />
                  </label></td>
                  <td height="25" align="center" background="<%=request.getContextPath()%>/resources/images/back/images/a1.gif" class="txt_bt">序号</td>
                  <td align="center" background="<%=request.getContextPath()%>/resources/images/back/images/a2.gif" class="txt_bt">导入时间</td>
                  <td align="center" background="<%=request.getContextPath()%>/resources/images/back/images/a2.gif" class="txt_bt">导入文件名称</td>
                  <td align="center" background="<%=request.getContextPath()%>/resources/images/back/images/a2.gif" class="txt_bt">数据类型</td>
                  <td align="center" background="<%=request.getContextPath()%>/resources/images/back/images/a2.gif" class="txt_bt">操作员</td>
                  <td align="center" class="txt_bt"  style="background-image:url(<%=request.getContextPath()%>/resources/images/back/images/a3.gif); background-position:right;">操作</td>
                </tr>
                 <c:forEach items="${importList.list}" var="info" begin="0" varStatus="i">
                <tr>
                  <td align="center" bgcolor="#F7F8F9" class="left_txt"><input type="checkbox" name="checkbox2" value="checkbox" /></td>
                  <td height="25" align="center" bgcolor="#F7F8F9" class="left_txt">${i.index+1 }</td>
                  <td align="center" bgcolor="#F7F8F9" class="left_txt">${info.importdate } </td>
                  <td align="center" bgcolor="#F7F8F9" class="left_txt">${info.importfile } </td>
                  <td align="center" bgcolor="#F7F8F9" class="left_txt">${info.importtype }</td>
                  <td align="center" bgcolor="#F7F8F9" class="left_txt">${info.importoperator }</td>
                  <td align="center" bgcolor="#F7F8F9"><a href="import.action?m=ToDifByType&importtypeid=${info.id}"><span  class="left_ts1">查看详细</span></a></td>
                </tr>
               </c:forEach>
              </table>
          <table width="100%" height="40" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td align="right">
            <c:choose> 
             <c:when test="${importList.currentPage==1}"> 
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
                <input name="textfield3" type="text" class="input4" size="3" />
                </label>
              &nbsp;页</td>
            <td align="left">
               <c:choose>
                        <c:when test="${importList.currentPage < importList.totalPage}">
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
            </td>
      </tr>
      <tr>
        <td height="40"><table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
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
            <td width="57%" class="left_txt2"><img src="<%=request.getContextPath()%>/resources/images/back/images/icon-mail2.gif" width="16" height="11" /> 客户服务邮箱：123121212@qq.com<br />
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
