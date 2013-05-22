<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="com.nbw.common.Constants"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/extremecomponents.tld" prefix="ec"%>
<%@ include file="/resources/common/res_all.inc"%>
<html>
	<head>
		<title>显示后台账户页面</title>
		<link href="${adminCssFile}" type="text/css" rel="stylesheet">
		<link href="${skinPath}/css/extremecomponents.css" type="text/css"
			rel="stylesheet">
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/resources/js/validata.js"></script>
		<script type="text/javascript">
function backPage(){
	var id=parent.document.getElementById("orgId").value; 
    document.all.frm1.action = "bzptzh.action?m=loadUserJbList&orgId="+id;
    document.all.frm1.submit();
}
function onSumbit(){
var options = {
     type:'post',    //数据发送方式   
     success: function(responseText){
	 if(responseText=="<%=Constants.DHTMLXGRID_SAVEXML_SUFFIX%>"){
		ymPrompt.succeedInfo({title:"提示",message:"会员级别调整成功！"});
	 }else{
		ymPrompt.errorInfo({title:"提示",message:"会员级别调整失败！"});
		}
	  }
};
$('#tzfrme').ajaxSubmit(options);
}
</script>
	</head>
	<body>
		<table width="100%" height="30" border="0" cellpadding="0"
			cellspacing="0">
			<tr class="tr_title">
				<td background="${skinPath}images/manage/dh_bg.jpg">
					<img src="${skinPath}/images/manage/dh-bg0.gif" width="14"
						height="14" />
					<font color="#016250"> 当前位置信息:详细信息</font>
				</td>
			</tr>
			<tr height="10">
				<td></td>
			</tr>
			<tr>
				<td>
					<form action="bzptzh.action?m=tzZhJb&userID=${userID}"
						id="tzfrme" name="tzframe" method="post">
						<table width="100%" align="center" class="table_nbw">
							<tr class="tr_nbw">
								<td width="20%" class="td_nbw_left" align="center">
									会员级别:
								</td>
								<td height="30" class="td_nbw_right">
									<select name="jbID">
										<c:forEach items="${jbList}" var="jb">
											<option value="${jb.id}" 
											<c:if test="${myJb == jb.id}">selected="selected"</c:if>>
												${jb.jbmc }
											</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr class="tr_nbw">
								<td align="center" colspan="2">
									<Input type="button" style="cursor: pointer" value="调整"
										onclick="onSumbit();">
								</td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
			<tr height="10">
				<td></td>
			</tr>
			<tr>
				<td>
					<table width="100%" height="40" border="0" cellpadding="0"
						cellspacing="0">
						<tr>
							<td height="40" align="center" colspan="2">
								<img src="${skinPath}images/button/button_1/fh.gif"
									style="cursor: pointer" onclick="backPage();">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<form id="frm1" action="" method="post">
		</form>
	</body>
</html>
