<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="${cssFile}" rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/uploadify/scripts/jquery-1.3.2.min.js"></script>
		<script language="javascript"
			src="<%=request.getContextPath()%>/resources/js/hygl/openDiv.js"></script>
	</head>

	<body style="background-image: url(${skinPath}images/hygl/fff.gif)">
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="pc_boxr">
			<tr>
				<td height="25" align="left">
					<img src="${skinPath}images/hygl/hygl_01.gif" width="9" height="9" />
					&nbsp;您的位置：用户中心-&gt;&gt;会员提醒
				</td>
			</tr>
		</table>
		<table width="100%" border="0" cellpadding="0" cellspacing="1"
			bgcolor="C7D9F3" style="margin-top: 0px;">
			<tr>
				<td height="30" align="center"
					background="${skinPath}images/hygl/ps_04.gif" class="title">
					信息内容
				</td>
				<td align="center" background="${skinPath}images/hygl/ps_04.gif"
					class="title">
					创建时间
				</td>
				<td align="center" background="${skinPath}images/hygl/ps_04.gif"
					class="title">
					是否阅读
				</td>
				<td align="center" background="${skinPath}images/hygl/ps_04.gif"
					class="title">
					操作
				</td>
			</tr>
			<c:forEach items="${dataList.list}" var="tx">
				<tr>
					<td height="30" align="left" bgcolor="#FFFFFF" class="txt_p"
						width="50%">
						<div class="sub200">
							${tx.txnr}
						</div>
					</td>
					<td align="center" bgcolor="#FFFFFF" class="txt_p" width="20%">
						<div class="sub55">
							${tx.cjsj}
						</div>
					</td>
					<td align="center" bgcolor="#FFFFFF" class="txt_p" width="20%" >
					    <div id="tip${tx.id}">
					    <c:if test="${tx.sfyd==1}">未阅读</c:if>
						<c:if test="${tx.sfyd==0}">已阅读</c:if>
					    </div>
					</td>
					<td align="center" bgcolor="#FFFFFF" class="txt_p" width="10%">
						<a href="#" onclick="checkTip('${tx.id}')" class="txt_p" id="showdiv${tx.id}">查看</a>
						<div id="divInfo${tx.id}" style="display: none">
							<table width="300" border="0" cellspacing="1" cellpadding="0"
								bgcolor="#669900">
								<tr>
									<td height="30" bgcolor="#666633">
										<label style="font-size: 14px; color: #FFF;">
											<strong>会员提醒</strong>
										</label>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<label onclick="closeLayer()" style="color: #000;">
											<a href="#">关闭</a>
										</label>
									</td>
								</tr>
								<tr>
									<td height="270" valign="top" bgcolor="#FFFFFF" align="left">
										&nbsp;&nbsp;${tx.txnr}
									</td>
								</tr>
							</table>
						</div>
					</td>
				</tr>
			</c:forEach>
		</table>
		<table width="100%" border="0" cellspacing="2" cellpadding="0">
			<tr>
				<td height="25" align="center" bgcolor="#EDF5FE">
					<div id="page" class="txt_p">
						共${dataList.totalPage}页
						<c:choose>
							<c:when test="${dataList.hasPreviousPage}">
								<a href="javascript:changepage('1')">首页</a>
								<a href="javascript:changepage('2')"> <img
										src="${skinPath}images/hygl/left_h.gif" align="absmiddle"
                                   border="0" /> </a>
								<a href="javascript:changepage('2')">上一页</a>
							</c:when>
							<c:otherwise>
											首页
										<img src="${skinPath}images/hygl/left_b.gif" align="absmiddle" />上一页
										</c:otherwise>
						</c:choose>
						第${dataList.currentPage}页
						<c:choose>
							<c:when test="${dataList.hasNextPage}">
								<a href="javascript:changepage('3')">下一页</a>
								<a href="javascript:changepage('3')"> <img
										src="${skinPath}images/hygl/right_h.gif" align="absmiddle"
										border="0" /> </a>
								<a href="javascript:changepage('4')">尾页</a>
							</c:when>
							<c:otherwise>
                                              下一页<img
									src="${skinPath}images/hygl/right_b.gif" align="absmiddle" />尾页
                                         </c:otherwise>
						</c:choose>
					</div>
				</td>
			</tr>
		</table>
		<form id="frm1" action="" method="post">
		</form>
		<script type="text/javascript">
function changepage(pid){
	    if(pid=='1'){
	    	window.location="bzpthytx.action?m=frontUserList&page=1";
	    }
	    if(pid=='2'){
	    	window.location="bzpthytx.action?m=frontUserList&page=${dataList.currentPage-1}";
	    }
	    if(pid=='3'){
	    	window.location="bzpthytx.action?m=frontUserList&page=${dataList.currentPage+1}";
	    }
	    if(pid=='4'){
	   		window.location="bzpthytx.action?m=frontUserList&page=${dataList.totalPage}";
	    }
	}
function checkTip(id){
    $.ajax({
		type: "post",
		url: "bzpthytx.action?m=frontSetTx&txId="+id,
		success: function(data){
		     $('#tip'+data).replaceWith('已阅读');
			 openLayer('showdiv'+data,'divInfo'+data);
		}
		
      });
    
}
</script>
	</body>
</html>