<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>会员管理-用户中心</title>
<link href="${cssFile}" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath() %>/resources/js/util.js"></script> 
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath() %>/resources/component/My97DatePicker/WdatePicker.js"></script>  
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/resources/component/ymPrompt/ymPrompt.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/component/ymPrompt/skin/qq/ymPrompt.css"/>
<script type="text/javascript">
//删除数据
function deldata(){
	var objIds = $n('chkid');
	var str='';
	var ch = 0;
	for(i = 0 ; i < objIds.length; i++){
			if(objIds[i].checked == true){
					str += objIds[i].value+',';
					ch = 1;
			}
	}
	if(ch!=0){
	ymPrompt.confirmInfo({message:'确定要删除选中记录吗？',handler:function(tp){if(tp=='ok'){
				document.all.frm1.objectId.value = str;
	            document.all.frm1.action = "bzpthygllljl.action?m=delete";
	            document.all.frm1.submit();
			}}});
		return true;
	}
	ymPrompt.alert({title:'提示',message:'请选择要删除的项'});
}
function checkAll(tid) {
	var objIds = $n('chkid');
	var ti = document.getElementById(tid);
	var type = true;
	if(ti.checked==false){
		type=false;	
	}
	for ( var i = 0; i < objIds.length; i++) {
			objIds[i].checked = type;
	}
}
function deleted(id){
	ymPrompt.confirmInfo({message:'确定要删除本条记录吗？',handler:function(tp){if(tp=='ok'){
				document.URL = "bzpthygllljl.action?m=delete&id="+id;
			}}});
}

function forward(rid,sid){
   window.open("/BzptWeb/appmanager/eip/main?_nfpb=true&_pageLabel=P5800125901322719086481&width=938&url=/bzptsfgwc.action?m=save'id="+rid+"'sid="+sid+"'type=1");
}
</script>
</head>

<body>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="pc_boxr">
          <tr>
            <td height="25" align="left">
            <img src="${skinPath}/images/hygl/hygl_01.gif" width="9" height="9" />
            &nbsp;您的位置：用户中心-&gt;&gt; 我的浏览记录 </td>
          </tr>
        </table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" >
          <tr bgcolor="#F3F3F1">
            <td height="25" align="left"  class="txt_p">&nbsp;
                <input type="checkbox" name="checkbox422" value="checkbox" id="all" onclick="checkAll(this.id)" />
              全选
              <label for="cb34790440918199">&nbsp;</label>
             </td>
               <td align="right"  class="txt_p" height="25">
               <input type="image" src="${skinPath}/images/sfgl/dele-pro23.gif"  name="delete" onClick="javascript:deldata();" />&nbsp;&nbsp;&nbsp; </td>
          </tr>
        </table>
		<div class="table_content">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="C7D9F3" style="margin-top:0px;">
          <tr>
            <td width="7%" height="30" align="center"  class="title">序号&nbsp;</td>
            <td width="30%" align="center" background="${skinPath}/images/hygl/ps_04.gif" class="title">标准名称</td>
            <td width="18%" align="center" background="${skinPath}/images/hygl/ps_04.gif" class="title">价格（元）</td>
            <td width="25%" align="center" background="${skinPath}/images/hygl/ps_04.gif" class="title">类型</td>
            <td width="20%" align="center" background="${skinPath}/images/hygl/ps_04.gif" class="title">放入购物车</td>
          </tr>
         <c:forEach items="${dataList.list}" var="ll" begin="0" varStatus="i" >
          <tr>
            <td height="30" align="center" bgcolor="#FFFFFF" class="txt_p">
              <label><input type="checkbox" name="chkid" value="${ll.id}" /></label>
           </td>
            <td align="center" bgcolor="#FFFFFF" class="txt_p">
            <div style="width: 250px;overflow: hidden;white-space: nowrap;word-break: keep-all;text-overflow:ellipsis;" title="${ll.bzptHyglLljl.bzname}">
            <c:if test="${ll.dflag=='0'}">${ll.bzptHyglLljl.bzname}<div class="red_m" >（该标准已下架!）</div></c:if>
            <c:if test="${ll.dflag=='1'}"><a href="/BzptWeb/appmanager/eip/main?_nfpb=true&_pageLabel=P5800125901322719086481&url=/bzptmetadata.action?m=frontFindDetail'id=${ll.bzptHyglLljl.rid}'tableName=${ll.tableName}&width=938&height=400" target="_blank"  >&nbsp;${ll.bzptHyglLljl.bzname}</a></c:if> 
            </div></td>
            <td align="center" bgcolor="#FFFFFF" class="txt_p">
            <c:choose>
            <c:when test="${ll.url!=''}">
             ${ll.bzjg}
            </c:when>
            <c:otherwise>0&nbsp;<font color="red">(无全文)</font></c:otherwise>
            </c:choose>
           </td>
            <td align="center" bgcolor="#FFFFFF" class="txt_p">${ll.bzType}</td>
            <td align="center" bgcolor="#FFFFFF" class="txt_p">
             <c:if test="${ll.url!='' && ll.dflag=='1' && ll.bzjg!=null&& ll.bzjg!='0.0'}">
            <input type="image" src="${skinPath}/images/sfgl/buy.gif"  onclick="javascript:forward('${ll.bzptHyglLljl.rid}','${ll.bzptHyglLljl.type}')" /><br/>
            </c:if>
            <a href="javascript:deleted('${ll.id}');" >删除</a></td>
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
	    	window.location="bzpthygllljl.action?m=loadData&page=1";
	    }
	    if(pid=='2'){
	    	window.location="bzpthygllljl.action?m=loadData&page=${dataList.currentPage-1}";
	    }
	    if(pid=='3'){
	    	window.location="bzpthygllljl.action?m=loadData&page=${dataList.currentPage+1}";
	    }
	    if(pid=='4'){
	   		window.location="bzpthygllljl.action?m=loadData&page=${dataList.totalPage}";
	    }
	}
</script>
</div>
<form id="frm1" action="" method="post">
	<input name="objectId" type="hidden" />
</form> 
</body>
</html>

