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

<table cellpadding="0" cellspacing="0" width="100%" border="0" align="center">
	<tr>
		<td>
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
			  <tr><td height="4"></td></tr>
			  <tr class="tr_title">
			    <td height="26" background="resources/skins/default/images/dh_bg2.gif">
			    <table width="100%" border="0" cellpadding="0" cellspacing="0">
			      <tr class="tr_title">
			        <td width="50%" align="right">
						<table border="0" cellspacing="0" cellpadding="0" align="right">
					     <tr  style="height: 21">
							<td>
							  <!--<img src="${skinPath}images/button/button_1/zj.gif"
															style="cursor: pointer" onclick="javascript:adddata('fmptJfSum.action?m=toUserJfList');"/>
							--></td>
							<td style="width: 3">&nbsp;</td>
							<td >
							   <img src="${skinPath}images/button/button_1/xg.gif"
															style="cursor: pointer" onclick="javascript:moddata();"/>
							</td>
							<td style="width: 3">&nbsp;</td>
							<td background="resources/skins/default/images/button/bu-bg1.gif" align="center" style="width: 64">
							    <img src="${skinPath}images/button/button_1/sc.gif"
															style="cursor: pointer" onclick="javascript:deldata();"/>
							     
							</td>
							<td style="width: 3">&nbsp;</td>
					      </tr>
					    </table>   
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
		<div style="overFlow: auto; height: 550px">
						<ec:table items="dataList" action="fmptJfSum.action?m=toUserJfAllList"
							imagePath="${skinPath}/images/table/*.gif" width="100%"
							rowsDisplayed="15" var="m">
							<ec:row>
								<ec:column property="tid" title="选择" width="10%">
									<input name="chkid" type="checkbox" value="${m.id}" />
								</ec:column>
								<ec:column property="username" title="用户名" width="20%"></ec:column>
								<ec:column property="trname" title="真实姓名" width="20%"></ec:column>
								<ec:column property="jfsum" title="用户积分" width="20%"></ec:column>
							</ec:row>
						</ec:table>
		</div>
		
		</td>
	</tr>
</table>
	
<form id="frm1" action="" method="post">
	<input name="objectId" type="hidden">
	<input name="orgid" type="hidden" value="${orgid}"/>
</form>
</body>
</html>