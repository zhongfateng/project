<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>显示ICS目录</title>
		<meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="Cache-Control" content="no-cache" />
		<meta http-equiv="Expires" content="0" />

	</head>
	<body>
		<TABLE border=0 cellSpacing=1 cellPadding=0 bgColor=#e1e1e1
			width="100%">
			<CAPTION style="LINE-HEIGHT: 30px" class=l12b align=center>
				<STRONG>│国际标准分类│</STRONG>
			</CAPTION>
			<tr>
				<td>
					<TABLE border=0 cellSpacing=3 cellPadding=8 width="100%"
						bgColor=#f2f2f2>
						<c:forEach var="m" items="${ICSList}" varStatus="status">
							<c:if test="${status.count%2==1}">
								<tr>
							</c:if>
							<td>
								<a
									href="sysmulu.action?m=frontStandardSearchList&search=ICS&treeId=${m[1]}&ICSLeve=<c:if test='${fn:length(m[1])==2}'>2</c:if><c:if test='${fn:length(m[1])==6}'>3</c:if>";>
									${m[1]} ${m[3]} </a>
							</td>
							<c:if test="${status.count%2==0}"></c:if>
							<c:set var="v_count" value="${status.count}" />
						</c:forEach>
						<c:if test="${v_count%2==1}">
							<td></td>
						</c:if>
					</TABLE>

				</td>
			</tr>
		</TABLE>
		<form name="form1" method="post" id="form1">

		</form>
	</body>
</html>