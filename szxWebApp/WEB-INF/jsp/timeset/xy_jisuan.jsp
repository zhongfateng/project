<%@page contentType="text/html; charset= utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/tld/extremecomponents.tld" prefix="ec" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>无标题文档</title>
<link href="<%=request.getContextPath() %>/resources/images/back/images/skin.css" rel="stylesheet" type="text/css"></link>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.6.2.js"></script>
<script type="text/javascript">
	function getProce(){
		$.ajax( {
					"url" : "timeset.action?m=toGetProce",
					"type" : "post",
					"dataType" : "json",
					"beforeSend": function(XMLHttpRequest){
                    		
                    },
					"success" : function(data, desc1) {
						alert(data[0]);
					},
					"complete": function(XMLHttpRequest, textStatus){  
                    	 
                	}
				});
	}
	
	function goPage(){
		var page = $("#pagese").val();
		var reg = /^[1-9][0-9]*$/;
		var all = ${dataList.totalPage};
		if(page=="" || isNaN(page) || !reg.test(page) || all<page){
			alert('请输入正确页码！');
		}else{
		window.location="timeset.action?m=toPjb&page="+page;
		}
	}
</script>

</head>

<body>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" background="<%=request.getContextPath() %>/resources/images/back/images/man-info_14.png">
  <tr>
    <td width="11"><img src="<%=request.getContextPath() %>/resources/images/back/images/man-info_12.png" width="11" height="30" /></td>
    <td width="144" align="center" background="<%=request.getContextPath() %>/resources/images/back/images/man-info_13.png" class="txt_bt"><span class="left_bt2">信用等级管理</span></td>
    <td>&nbsp;</td>
    <td width="14"><img src="<%=request.getContextPath() %>/resources/images/back/images/man-info_17.png" width="14" height="30" /></td>
  </tr>
</table>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#F7F8F9">
  <tr>
    <td width="11" background="<%=request.getContextPath() %>/resources/images/back/images/man-info_19.png">&nbsp;</td>
    <td><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td valign="middle"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          
          
          
          <tr>
            <td colspan="4" align="center">
            
            <div class="login_txt_tb0">
            <div>
              <p><span class="left_bt2">数据截至时间设定为
                <input name="textfield" type="text" class="input4" value="2013-5-1" size="30" />
              </span> </p>
              <p class="login_txt_align">
              <img src="<%=request.getContextPath() %>/resources/images/back/images/input_1.gif" width="118" height="28" onclick="getProce();"/>
              </p>
            </div></div>
              
              </td>
          </tr>
          <tr>
            <td height="40" colspan="4" align="right" class="login_txt_bt"><table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
              <tr>
                <td></td>
                </tr>
              </table></td>
          </tr>
        </table>
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td width="16%" align="left" height="25" background="<%=request.getContextPath() %>/resources/images/back/images/news_tb_02.gif"><span class="left_txt"><span class="login_icon"><img src="<%=request.getContextPath() %>/resources/images/back/images/icon_01.gif" /></span></span><span class="txt_bt">&nbsp;信用等级信息</span></td>
                  <td width="70%" align="right" background="<%=request.getContextPath() %>/resources/images/back/images/news_tb_02.gif" class="left_bt2">&nbsp;</td>
                  <td width="14%" colspan="2" align="center" background="<%=request.getContextPath() %>/resources/images/back/images/news_tb_02.gif">&nbsp;</td>
                </tr>
            </table>
          <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">
                <tr>
                  <td width="6%" height="25" align="center" background="images/a1.gif" class="txt_bt">序号</td>
                  <td width="17%" align="center" background="<%=request.getContextPath() %>/resources/images/back/images/a2.gif" class="txt_bt">企业数量</td>
                  <td width="16%" align="center" background="<%=request.getContextPath() %>/resources/images/back/images/a2.gif" class="txt_bt">有效企业数量</td>
                  <td width="19%" align="center" background="<%=request.getContextPath() %>/resources/images/back/images/a2.gif" class="txt_bt">数据截至时间</td>
                  <td width="27%" align="center" background="<%=request.getContextPath() %>/resources/images/back/images/a2.gif" class="txt_bt">生成时间 </td>
                  <td width="15%" align="center" background="<%=request.getContextPath() %>/resources/images/back/images/a2.gif" class="txt_bt">操作</td>
              </tr>
              <c:forEach items="${dataList.list}" var="pp" begin="0" varStatus="i">
                <tr>
                  <td height="25" align="center" bgcolor="#F7F8F9" class="left_txt">1</td>
                  <td align="center" bgcolor="#F7F8F9" class="left_txt">${pp.companySum} </td>
                  <td align="center" bgcolor="#F7F8F9" class="left_txt">${pp.aboveSum}</td>
                  <td align="center" bgcolor="#F7F8F9" class="left_txt"><div id="u22">
                      <div id="u22_rtf">${pp.endTime}</div>
                  </div></td>
                  <td align="center" bgcolor="#F7F8F9" class="left_txt"><div id="u22">
                      <div id="u22_rtf">${pp.birTime}</div>
                  </div></td>
                  <td align="center" bgcolor="#F7F8F9" class="left_txt"><div id="u22">
                      <div class="left_txt_l" id="u22_rtf"> <a href="timeset.action?m=toGetDetail&comid=${pp.hisId}">查看详细</a> </div>
                  </div></td>
              </tr>
              </c:forEach>
            </table>
            
          <table width="100%" height="40" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td align="right">
                  <c:choose>
                  <c:when test="${dataList.currentPage==1}">
                  <img src="<%=request.getContextPath() %>/resources/images/back/images/pro_34.jpg" width="60" height="22" border="0"/>
                  <img src="<%=request.getContextPath() %>/resources/images/back/images/pro_36.jpg" width="60" height="22" border="0"/>
                  </c:when>
                  <c:otherwise>
                  <a href="javascript:changepage('1')">
                  <img src="<%=request.getContextPath() %>/resources/images/back/images/pro_35.jpg" width="60" height="22" border="0"/></a>&nbsp;
                  <a href="javascript:changepage('2')">
                  <img src="<%=request.getContextPath() %>/resources/images/back/images/pro_37.jpg" width="60" height="22" border="0"/></a>
                  </c:otherwise>
                  </c:choose>
                  
                  </td>
                  <td align="center" class="left_txt">第
                    <label>
                      <input name="textfield3" id="pagese" type="text" class="input1" size="3" />
                    </label>
                    &nbsp;页 
                    	&nbsp;<input name="GO" value="GO" type="button" onclick="goPage();"/>
                    	&nbsp;共 &nbsp;${dataList.totalPage}&nbsp;页
                    </td>
                  <td height="80" align="left">
                  <c:choose>
                        <c:when test="${dataList.currentPage < dataList.totalPage}">
                        	<a href="javascript:changepage('3')">
                        	<img src="<%=request.getContextPath() %>/resources/images/back/images/pro_38.jpg" width="60" height="22" border="0"/></a>
                        	<a href="javascript:changepage('4')">
                        	<img src="<%=request.getContextPath() %>/resources/images/back/images/pro_40.jpg" width="60" height="22" border="0"/></a>
                        </c:when>
                        <c:otherwise>
                             <img src="<%=request.getContextPath() %>/resources/images/back/images/pro_39.jpg" width="60" height="22" border="0"/>&nbsp;
                  		     <img src="<%=request.getContextPath() %>/resources/images/back/images/pro_41.jpg" width="60" height="22" border="0"/>
                        </c:otherwise>
                  </c:choose>
                  </td>
                </tr>
          </table>
          
          </td>
      </tr>
      <tr>
        <td height="40"><table width="100%" height="auto" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td height="40" class="txt_bt"><strong>说明：如企业基本信息不完整，则不会计算该企业信用等级。故有效企业数量有可能少于上传企业数量。</strong></td>
          </tr>
          <tr>
            <td></td>
          </tr>
        </table></td>
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
            <td width="57%" class="left_txt"><img src="<%=request.getContextPath() %>/resources/images/back/images/icon-mail2.gif" width="16" height="11" /> 客户服务邮箱：123121212@qq.com<br />
                <img src="<%=request.getContextPath() %>/resources/images/back/images/icon-phone.gif" width="17" height="14" /> 官方网站：http://www.11212121.cn</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
      </table></td>
    <td width="14" background="<%=request.getContextPath() %>/resources/images/back/images/man-info_21.png">&nbsp;</td>
  </tr>
</table>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" background="<%=request.getContextPath() %>/resources/images/back/images/man-info_30.png">
  <tr>
    <td width="11" background="<%=request.getContextPath() %>/resources/images/back/images/man-info_28.png">&nbsp;</td>
    <td width="144">&nbsp;</td>
    <td>&nbsp;</td>
    <td width="14" background="<%=request.getContextPath() %>/resources/images/back/images/man-info_32.png">&nbsp;</td>
  </tr>
</table>
<p></p>
<script type="text/javascript">
	function changepage(pid){
	    if(pid=='1'){
	    	window.location.href="timeset.action?m=toPjb&page=1";
	    }
	    if(pid=='2'){
	    	window.location="timeset.action?m=toPjb&page=${dataList.currentPage-1}";
	    }
	    if(pid=='3'){
	    	window.location="timeset.action?m=toPjb&page=${dataList.currentPage+1}";
	    }
	    if(pid=='4'){
	   		window.location="timeset.action?m=toPjb&page=${dataList.totalPage}";
	    }
	}
</script>
</body>
</html>