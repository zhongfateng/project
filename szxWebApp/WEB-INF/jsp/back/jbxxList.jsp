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
	function changepage(pid){
	
		var comcodes="${comcodes}";
		comcodes=encodeURI(encodeURI(comcodes));
		var comorgname="${comorgname}";
		comorgname=encodeURI(encodeURI(comorgname));
	
	    if(pid=='1'){
	    	window.location = "import.action?m=toJBXXList&page=1&pageSize=${jbxxList.pageSize}&comcodes="+comcodes+"&comorgname="+comorgname;
	    }
	    if(pid=='2'){
	    	window.location="import.action?m=toJBXXList&page=${jbxxList.currentPage-1}&pageSize=${jbxxList.pageSize}&comcodes="+comcodes+"&comorgname="+comorgname;
	    }
	    if(pid=='3'){
	    	window.location="import.action?m=toJBXXList&page=${jbxxList.currentPage+1}&pageSize=${jbxxList.pageSize}&comcodes="+comcodes+"&comorgname="+comorgname;
	    }
	    if(pid=='4'){
	   		window.location="import.action?m=toJBXXList&page=${jbxxList.totalPage}&pageSize=${jbxxList.pageSize}&comcodes="+comcodes+"&comorgname="+comorgname;
	    }
	}
</script>
	<script type="text/javascript">
	
	$(function()
	{
	$("#searchid").click(function(){
	var sql="import.action?m=toJBXXList";
	var comcodes=$("#comcodesid").val();
	var comorgname=$("#comorgnameid").val();
	if(comcodes!=null&&!comcodes=='')
	{
	sql=sql+"&comcodes="+encodeURI(encodeURI(comcodes));

	}
	if(comorgname!=null&&!comorgname=='')
	{
	sql=sql+"&comorgname="+encodeURI(encodeURI(comorgname));

	}
	if(sql=="import.action?m=toJBXXList")
	{
	
	ymPrompt.alert("请选择输入条件");
	
	
	}
	
	else
	{
	window.location.href=sql;
	}
	});
	});
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
					document.all.frm1.action = "import.action?m=deleteJBXXByID";
					document.all.frm1.submit();
					break;
				}
			}
		}
	}
}
	</script>
	<script type="text/javascript">
	function  resetall()
	{
	document.getElementById('comcodesid').value='';
	document.getElementById('comorgnameid').value='';
	}
	
	</script>
	<script type="text/javascript">
	function jump()
	{
			var comcodes="${comcodes}";
		comcodes=encodeURI(encodeURI(comcodes));
		var comorgname="${comorgname}";
		comorgname=encodeURI(encodeURI(comorgname));
		var page = $("#pageid").val();
		var reg = /^[1-9][0-9]*$/;
		if(page=="" || isNaN(page) || page<=0 || page>${jbxxList.totalPage}){
			ymPrompt.alert('请输入正确页码！');
		}else{
			window.location.href="import.action?m=toJBXXList&pageSize=${jbxxList.pageSize}&page="+page+"&comcodes="+comcodes+"&comorgname="+comorgname;
		}
	}
	</script>
  </head>
  
  <body>
    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" background="<%=request.getContextPath()%>/resources/images/back/images/man-info_14.png">
  <tr>
    <td width="11"><img src="<%=request.getContextPath()%>/resources/images/back/images/man-info_12.png" width="11" height="30" /></td>
    <td width="144" align="center" background="<%=request.getContextPath()%>/resources/images/back/images/man-info_13.png" class="txt_bt"><span class="login_txt_bt">基本信息管理</span></td>
    <td align="right" class="left_txt">&nbsp;</td>
    <td width="86" align="left" class="left_txt"><img src="<%=request.getContextPath()%>/resources/images/back/images/level_up_16x16.gif" width="66" height="16" /></td>
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
            <td width="35%">&nbsp;</td>
            <td width="47%">&nbsp;</td>
            <td width="18%">&nbsp;</td>
          </tr>
          <tr style="border-bottom-width:2px; border-bottom-color:#fff; border-bottom-style:solid;">
            <td colspan="3" align="center" class="left_txt_tb0">
            
            
            <table><tr>
            <td height="35" align="right" class="left_txt">组&nbsp; 织&nbsp; 机 &nbsp;构 &nbsp;代 &nbsp;码&nbsp; &nbsp;：</td>  
                <td align="left"><label>
                  <input id="comcodesid" name="textfield" type="text" class="input4" value="${comcodes}" size="30">
                </label>
              </td> 
                </tr>
              <tr>
              <td height="35" align="right" class="left_txt">企 &nbsp;&nbsp;业&nbsp; &nbsp;名&nbsp; &nbsp;称&nbsp; &nbsp;：</td> 

              <td align="left"> <input id="comorgnameid" name="textfield2" type="text" class="input4" value="${comorgname}" size="30">
              </td></tr><tr>
              <td height="35" align="right" class="left_txt"> 行 &nbsp;&nbsp;政&nbsp; &nbsp;区&nbsp; &nbsp;划&nbsp;&nbsp; ：    </td>
              <td align="left"> 
<select name="select" class="input4">
    <option>请选择省……</option>
  </select>
                 &nbsp;
                 <select name="select3" class="input4">
                  <option>请选择市……</option>
                </select>
              </td></tr>
              <tr>
                <td height="50" colspan="2" align="center"> <a href="javascript:;" id="searchid" ><img src="<%=request.getContextPath()%>/resources/images/back/images/search.jpg" width="98" height="23" border="0"></a> &nbsp;&nbsp;&nbsp;<input type="image" src="<%=request.getContextPath()%>/resources/images/back/images/search1.jpg" width="98" height="23" onclick="resetall()"></td></tr></table> 
              
            
            </td>
          </tr>
          <tr>
            <td height="40" colspan="3" align="right" class="login_txt_bt"><table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
              <tr>
                <td></td>
                </tr>
              </table></td>
          </tr>
        </table>
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td width="15%" align="left" height="25" background="<%=request.getContextPath()%>/resources/images/back/images/news_tb_02.gif"><span class="left_bt2"><img src="<%=request.getContextPath()%>/resources/images/back/images/tb_1.gif" /> 数据导入列表</span></td>
                  <td width="74%" align="right" background="<%=request.getContextPath()%>/resources/images/back/images/news_tb_02.gif" class="left_bt2"><input type="checkbox" name="checkbox7" value="checkbox" />
                    全选</td>
                  <td colspan="2" align="center" background="<%=request.getContextPath()%>/resources/images/back/images/news_tb_02.gif"><img src="<%=request.getContextPath()%>/resources/images/back/images/aniu_02.gif" width="82" height="28" onclick="javascript:deldata();" /></td>
                </tr>
                <tr>
                  <td height="5" colspan="3"></td>
                  <td width="7%"></td>
                </tr>
            </table>
          <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">
                <tr>
                  <td align="center" background="<%=request.getContextPath()%>/resources/images/back/images/a1.gif" class="txt_bt"><label>
                    <input type="checkbox" name="checkbox" value="checkbox" />
                  </label></td>
                  <td height="25" align="center" background="<%=request.getContextPath()%>/resources/images/back/images/a1.gif" class="txt_bt">序号</td>
                  <td align="center" background="<%=request.getContextPath()%>/resources/images/back/images/a2.gif" class="txt_bt">组织机构代码</td>
                  <td align="center" background="<%=request.getContextPath()%>/resources/images/back/images/a2.gif" class="txt_bt">企业名称</td>
                  <td align="center" background="<%=request.getContextPath()%>/resources/images/back/images/a2.gif" class="txt_bt">法定代表人</td>
                  <td align="center" background="<%=request.getContextPath()%>/resources/images/back/images/a2.gif" class="txt_bt">法人身份证号码</td>
                  <td align="center" class="txt_bt"  style="background-image:url(<%=request.getContextPath()%>/resources/images/back/images/a3.gif); background-position:right;">企业地址</td>
                  <td align="center" class="txt_bt"  style="background-image:url(<%=request.getContextPath()%>/resources/images/back/images/a3.gif); background-position:right;">行政区划</td>
                  <td align="center" class="txt_bt"  style="background-image:url(<%=request.getContextPath()%>/resources/images/back/images/a3.gif); background-position:right;">营业执照编号</td>
                  <td align="center" class="txt_bt"  style="background-image:url(<%=request.getContextPath()%>/resources/images/back/images/a3.gif); background-position:right;">联系电话</td>
                </tr>
                <c:forEach items="${jbxxList.list}" var="info" begin="0" varStatus="i">
                <tr>
                  <td align="center" bgcolor="#F7F8F9" class="left_txt"><input type="checkbox" name="chkid" value="${info.id }" /></td>
                  <td height="25" align="center" bgcolor="#F7F8F9" class="left_txt">${i.index+1 }</td>
                  <td align="center" bgcolor="#F7F8F9" class="left_txt">${info.comcodes }</td>
                  <td align="center" bgcolor="#F7F8F9" class="left_txt">${info.comorgname }</td>
                  <td align="center" bgcolor="#F7F8F9" class="left_txt">${info.comagentname }</td>
                  <td align="center" bgcolor="#F7F8F9" class="left_txt">${info.comagentid }</td>
                  <td align="center" bgcolor="#F7F8F9" class="left_txt"><a href="#">${info.comaddr }</a></td>
                  <td align="center" bgcolor="#F7F8F9" class="left_txt">${info.comadmindiv }</td>
                  <td align="center" bgcolor="#F7F8F9" class="left_txt">${info.comliseid }</td>
                  <td align="center" bgcolor="#F7F8F9" class="left_txt">${info.comtel }</td>
                </tr>
               </c:forEach>
               
             
            </table>
          <table width="100%" height="40" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td align="right">
            <c:choose> 
             <c:when test="${jbxxList.currentPage==1}"> 
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
                        <c:when test="${jbxxList.currentPage < jbxxList.totalPage}">
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
        </table></td>
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
