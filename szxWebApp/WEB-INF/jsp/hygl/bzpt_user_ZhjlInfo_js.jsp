<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt"%>
<html>
	<head>
		<title>列表</title>
		<link href="${cssFile}" type="text/css" rel="stylesheet" />
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/js/util.js"></script>
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript">
function changepage(pid){
	    var url="bzptzh.action?m=showQtZhjlInfo";
	    if(pid=='1'){
	    	window.location=url+"&page=1";
	    }
	    if(pid=='2'){
	    	window.location=url+"&page=${dataList.currentPage-1}";
	    }
	    if(pid=='3'){
	    	window.location=url+"&page=${dataList.currentPage+1}";
	    }
	    if(pid=='4'){
	   		window.location=url+"&page=${dataList.totalPage}";
	    }
	}
function changInfoFrame(url){
    location.href=url;//
}
</script>
	</head>
	<body style="margin: 0px;">
		<table cellpadding="0" cellspacing="0" width="100%" border="0"
			align="center">
			<tr>
				<td>
					<table cellpadding="0" cellspacing="0" width="100%" border="0"
						align="center">
						<tr>
							<td width="2" background="${skinPath}images/hygl/right_03.gif"></td>
							<td height="25" align="left" width="677"
								background="${skinPath}images/hygl/right_04.gif">
								<span class="menu_p" style="padding-left: 10px"> <img
										src="${skinPath}images/hygl/hygl_01.gif" width="9" height="9" />
									&nbsp;您的位置：用户中心-&gt;&gt; <span style="font-size: 12px;">会员充值记录查询-&gt;&gt;已充值列表</span>
								</span>
							</td>
							<td width="2" background="${skinPath}images/hygl/right_06.gif"></td>
							<td background="${skinPath}images/hygl/right_04.gif">
								<table border="0" cellspacing="0" cellpadding="0" align="right">
									<tr style="height: 21">
										<td>
											<img style="cursor: pointer"
												src="${skinPath}images/dczlbq.gif"
												onclick="changInfoFrame('bzptzh.action?m=showQtZhjlDBInfo');" />
										</td>
										<td style="width: 3">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" border="0" cellpadding="0" cellspacing="1"
						bgcolor="C7D9F3" style="margin-top: 0px;">
						<tr>
							<td height="30" align="center"
								background="${skinPath}images/hygl/ps_04.gif" class="title"
								width="30%">
								充值时间
							</td>
							<td align="center" background="${skinPath}images/hygl/ps_04.gif"
								width="30%" class="title">
								充值金额
							</td>
							<td align="center" background="${skinPath}images/hygl/ps_04.gif"
								width="40%" class="title">
								充值人
							</td>
						</tr>
						<c:forEach items="${dataList.list}" var="m" begin="0"
							varStatus="i">
							<tr>
								<td height="30" align="left" bgcolor="#FFFFFF" class="txt_p"
									width="30%">
									<fmt:formatDate value="${m[0].czsj}" pattern="yyyy年MM月dd日" />
								</td>
								<td height="30" align="left" bgcolor="#FFFFFF" class="txt_p"
									width="30%">
									${m[0].czje }
								</td>
								<td height="30" align="left" bgcolor="#FFFFFF" class="txt_p"
									width="40%">
									${m[1].name }
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

				</td>
			</tr>
		</table>
	</body>
</html>

