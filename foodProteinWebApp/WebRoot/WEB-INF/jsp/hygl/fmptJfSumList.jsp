<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tld/extremecomponents.tld" prefix="ec" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>后台科技动态管理列表</title>
	<link href="${skinPath}/css/extremecomponents.css" type="text/css"
			rel="stylesheet">
		<link href="${adminCssFile}" type="text/css" rel="stylesheet">
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath() %>/resources/js/util.js"></script> 
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath() %>/resources/component/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript">
//增加数据
function adddata(URL){
   window.location.replace(URL);
}
//删除数据
function deldata(){
    if(!isSingleSel('chkid')){
         alert('请先选中1条记录！');
         return;
    }
	var objIds = $n('chkid');
	if(objIds!=null){
		for(i = 0 ; i < objIds.length; i++){
			if(objIds[i].checked == true){
			   if(confirm('确定要删除该条记录吗?')){
					document.all.frm1.objectId.value = objIds[i].value;
					document.all.frm1.action = "fmptJfSum.action?m=toDelUserJf";
					document.all.frm1.submit();
					break;
				}
			}
		}
	}
}

//修改数据
function moddata(){
    if(!isSingleSel('chkid')){
        alert("请先选中1条记录！");
        return;
    }
	var objIds = $n('chkid');
	if(objIds!=null){
		for(i = 0 ; i < objIds.length; i++){
		
			
			if(objIds[i].checked == true){
				document.all.frm1.objectId.value = objIds[i].value;
				document.all.frm1.action = "fmptJfSum.action?m=toModifyUserJf";
				document.all.frm1.submit();
				break;
			}
		}
	}
}
</script>
	
</head>
<body style="margin:0px;">

<table cellpadding="0" cellspacing="0" width="100%" border="0"
			align="center">
			<tr>
				<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr class="tr_title">
							<td height="26"
								background="resources/skins/default/images/dh_bg2.gif">
								<table width="100%" height="30" border="0" cellpadding="0"
									cellspacing="0">
									<tr class="tr_title">
										<td width="2%" background="${skinPath}images/manage/dh_bg.jpg">
											&nbsp;
										</td>
										<td background="${skinPath}images/manage/dh_bg.jpg">
											<img src="${skinPath}/images/manage/dh-bg0.gif" width="14"
												height="14" />
											&nbsp;
											<font color="#016250"> 当前位置信息：账户管理中心-&gt;&gt;调整账户级别</font>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<table width="98%" border="0" align="center" cellpadding="0" cellspacing="10">
				<tr>
					<td width="150" height="171" valign="top" class="pc_boxl">
						<iframe src="select.action?m=selectMultiOrgPage&type=one"
							scrolling="no" width="100%" height="500" id="leftFrame"
							frameborder="0"></iframe>
					</td>
					<td align="center" valign="top" class="pc_boxl" width="80%">
						<iframe scrolling="no" width="100%" height="800" id="rightFrame"
							frameborder="0">
						</iframe>
						
					</td>
			</table>
		</table>
		<input id="rightUrl" type="hidden"
			value="fmptJfSum.action?m=toUserJfAllList">
		<input id="orgId" type="hidden">
		<form id="frm1" action="" method="post">
		</form>

</body>
</html>