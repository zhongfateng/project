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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/images/zzjgcx/images/skin.css">
	<link rel="stylesheet" id='skin' type="text/css" href="<%=request.getContextPath()%>/resources/component/ymPrompt/skin/simple_gray/ymPrompt.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.6.2.js"></script>
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/js/util.js"></script>
	<script type="text/javascript" src ="<%=request.getContextPath()%>/resources/component/ymPrompt/ymPrompt.js"></script>
	<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath() %>/resources/component/My97DatePicker/WdatePicker.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/images/zzjgcx/images/skin.css">

	
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
	var sql="import.action?m=ToImportList";
	var importtypeid=$('#slec option:selected').val();
	var importfile=$('#inputid').val();
	var startdate=$('#data1').val();
	var enddate=$('#data2').val();
	if(importtypeid!=null&&!importtypeid=='')
	{
	sql=sql+"&importtypeid="+importtypeid;

	}
	if(importfile!=null&&!importfile=='')
	{
	sql=sql+"&importfile="+encodeURI(encodeURI(importfile));
	}
	if(startdate!=''&&!startdate==''&&enddate!=null&&!enddate=='')
	{
	sql=sql+"&startdate="+startdate+"&enddate="+enddate;
	
	}
	
	if(sql=="import.action?m=ToImportList")
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
	
	var importfile="${importfile}";
	importfile=encodeURI(encodeURI(importfile));
	    if(pid=='1'){
	    	window.location = "import.action?m=ToImportList&page=1&pageSize=${importList.pageSize}&importtypeid=${importtypeid}&startdate=${startdate}&enddate=${enddate}&importfile="+importfile;
	    }
	    if(pid=='2'){
	    	window.location="import.action?m=ToImportList&page=${importList.currentPage-1}&pageSize=${importList.pageSize}&importtypeid=${importtypeid}&startdate=${startdate}&enddate=${enddate}&importfile="+importfile;
	    }
	    if(pid=='3'){
	    	window.location="import.action?m=ToImportList&page=${importList.currentPage+1}&pageSize=${importList.pageSize}&importtypeid=${importtypeid}&startdate=${startdate}&enddate=${enddate}&importfile="+importfile;
	    }
	    if(pid=='4'){
	   		window.location="import.action?m=ToImportList&page=${impportList.totalPage}&pageSize=${importList.pageSize}&importtypeid=${importtypeid}&startdate=${startdate}&enddate=${enddate}&importfile="+importfile;
	    }
	}
</script>
<script type="text/javascript">
		function deldata(){
   		 if(!isSingleSel('chkid')){
         ymPrompt.alert('请先选中1条记录！');
         return;
  	  }
		var objIds = $n('chkid');
		if(objIds!=null){
		for(i = 0 ; i < objIds.length; i++){
			if(objIds[i].checked == true){
			   if(confirm('确定要删除该条记录吗?')){
					document.all.frm1.objectId.value = objIds[i].value;
					document.all.frm1.action = "import.action?m=deleteImportByID";
					document.all.frm1.submit();
					break;
				}
			}
		}
	}
}
	</script>
<script type="text/javascript">
	function jump()
	{
		var importfile="${importfile}";
		importfile=encodeURI(encodeURI(importfile));
		var page = $("#pageid").val();
		var reg = /^[1-9][0-9]*$/;
		if(page=="" || isNaN(page) || page<=0 || page>${importList.totalPage}){
			ymPrompt.alert('请输入正确页码！');
		}else{
			window.location.href="import.action?m=ToImportList&pageSize=${importList.pageSize}&page="+page+"&importfile="+importfile+"&importtypeid=${importtypeid}&startdate=${startdate}&enddate=${enddate}"
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
                  <c:forEach var="reporttype" items="${reporttypeList}"> 
		<option value='${reporttype.id}' <c:if test="${importtypeid == reporttype.id}">selected="selected"</c:if> >
		${reporttype.description}
 		</option> 
		</c:forEach> 
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
                  <td colspan="2" align="center" background="<%=request.getContextPath()%>/resources/images/back/images/news_tb_02.gif"><img src="<%=request.getContextPath()%>/resources/images/back/images/aniu_02.gif" onclick="javascript:deldata();"  /></td>
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
                  <td align="center" bgcolor="#F7F8F9" class="left_txt"><input type="checkbox" name="chkid" value="${info.id }"/></td>
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
	<form id="frm1" action="" method="post">
			<input name="objectId" type="hidden">
		</form>
  </body>
</html>
