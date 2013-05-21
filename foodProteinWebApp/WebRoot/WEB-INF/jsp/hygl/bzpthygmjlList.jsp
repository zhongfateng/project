<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>会员管理-用户中心</title>
<link href="${cssFile}" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath() %>/resources/js/util.js"></script> 
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath() %>/resources/component/My97DatePicker/WdatePicker.js"></script> 
</head>

<body>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="pc_boxr">
          <tr>
            <td height="25" align="left">
            <img src="${skinPath}/images/hygl/hygl_01.gif" width="9" height="9" />
            &nbsp;您的位置：用户中心-&gt;&gt; 我的购买记录 </td>
          </tr>
        </table>
		<table width="100%" border="0" cellspacing="2" cellpadding="0" >
          <tr bgcolor="#F3F3F1">
            <td height="25" align="left"  class="txt_p">&nbsp;
              <label for="cb34790440918199">&nbsp;</label>
             </td>
          </tr>
        </table>
		<div class="table_content">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="C7D9F3" style="margin-top:0px;"> 
          <tr>
            <td width="7%" height="30" align="center"  class="title">序号&nbsp;</td>
            <td width="40%" align="center" background="${skinPath}/images/hygl/ps_04.gif" class="title">名称</td>
            <td width="10%" align="center" background="${skinPath}/images/hygl/ps_04.gif" class="title">买入单价</td>
            <td width="20%" align="center" background="${skinPath}/images/hygl/ps_04.gif" class="title">成交时间</td>
            <td width="10%" align="center" background="${skinPath}/images/hygl/ps_04.gif" class="title">类型</td>
            <td width="8%" align="center" background="${skinPath}/images/hygl/ps_04.gif" class="title">数量</td>
          </tr>
         <c:forEach items="${dataList.list}" var="gm" begin="0" varStatus="i">
          <tr>
            <td height="30" align="center" bgcolor="#FFFFFF" class="txt_p">
            ${i.count+dataList.pageSize*(dataList.currentPage-1)}
           </td>
            <td align="center" bgcolor="#FFFFFF" class="txt_p">
            <c:if test="${gm.type==1}">
            <div style="width: 250px;overflow: hidden;white-space: nowrap;word-break: keep-all;text-overflow:ellipsis;" title="${gm.bzName}">
            <c:if test="${gm.dflag=='0'}">${gm.bzname}</c:if>
            <c:if test="${gm.dflag=='1'}"><a href="/BzptWeb/appmanager/eip/main?_nfpb=true&_pageLabel=P5800125901322719086481&url=/bzptmetadata.action?m=frontFindDetail'id=${gm.bztlId}'tableName=${gm.tableName}&width=938&height=400"  target="_blank">&nbsp;${gm.bzname}</a></c:if>
            </div>
            </c:if>
            <c:if test="${gm.type==2}">
            <div style="width: 250px;overflow: hidden;white-space: nowrap;word-break: keep-all;text-overflow:ellipsis;" title="${gm.bzName}"><a href="/BzptWeb/appmanager/eip/main?_nfpb=true&_pageLabel=P5800125901322719086481&url=/bzptbook.action?m=frontShowQtBookInfo'bookShowType=0'bookId=${gm.bztlId}&width=938&height=400" target="_blank">&nbsp;
            <c:if test="${gm.bzname!=null&&gm.bzname!=''}">${gm.bzname}</c:if>
            <c:if test="${gm.bzname==null||gm.bzname==''}">${gm.bzName}</c:if>
            </a></div>
            </c:if>
            </td>
            <td align="center" bgcolor="#FFFFFF" class="txt_p">${gm.mrjg}</td>
            <td align="center" bgcolor="#FFFFFF" class="txt_p"><fmt:formatDate value="${gm.cjsj}" pattern="yyyy-MM-dd"/></td>
            <td align="center" bgcolor="#FFFFFF" class="txt_p">
             <c:if test="${gm.type==1}">标准</c:if>
             <c:if test="${gm.type==2}">图书</c:if>
            </td>
            <td align="center" bgcolor="#FFFFFF" class="txt_p">${gm.buycount}</td>
          </tr>
        </c:forEach>
        </table>
		<table width="100%" border="0" cellspacing="2" cellpadding="0">
          <tr>
            <td height="25" align="center" bgcolor="#EDF5FE" >
            	<div id="page" class="txt_p">
					共${dataList.totalPage}页
                    <c:choose> 
                        <c:when test="${dataList.currentPage==1}"> 
                            首页<img src="${skinPath}images/hygl/left_b.gif" align="absmiddle" />上一页
                        </c:when>
                        <c:otherwise><a href="javascript:changepage('1')">首页</a>
                            <a href="javascript:changepage('2')">
                            <img src="${skinPath}images/hygl/left_h.gif" align="absmiddle" border="0" /></a>
                            <a href="javascript:changepage('2')">上一页</a>
                        </c:otherwise>
                    </c:choose>
					第${dataList.currentPage}页
                    <c:choose>
                        <c:when test="${dataList.currentPage < dataList.totalPage}">
                            <a href="javascript:changepage('3')">下一页</a>
                            <a href="javascript:changepage('3')">
                            <img src="${skinPath}images/hygl/right_h.gif" align="absmiddle" border="0"  /></a>
                            <a href="javascript:changepage('4')">尾页</a>
                        </c:when>
                        <c:otherwise>
                            下一页<img src="${skinPath}images/hygl/right_b.gif" align="absmiddle" />尾页
                        </c:otherwise>
                    </c:choose>
				</div>
            </td>
          </tr>
</table>
<script type="text/javascript">
	function changepage(pid){
	    if(pid=='1'){
	    	window.location="bzpthygmjl.action?m=loadData&page=1";
	    }
	    if(pid=='2'){
	    	window.location="bzpthygmjl.action?m=loadData&page=${dataList.currentPage-1}";
	    }
	    if(pid=='3'){
	    	window.location="bzpthygmjl.action?m=loadData&page=${dataList.currentPage+1}";
	    }
	    if(pid=='4'){
	   		window.location="bzpthygmjl.action?m=loadData&page=${dataList.totalPage}";
	    }
	}
</script>
</body>
</html>