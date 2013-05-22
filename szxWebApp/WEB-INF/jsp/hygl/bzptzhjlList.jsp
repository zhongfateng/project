<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tld/extremecomponents.tld" prefix="ec" %>
<html>
<head>
<title>列表</title>
<link href="${skinPath}/css/extremecomponents.css" type="text/css" rel="stylesheet"> 
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
					document.all.frm1.action = "bzptzhjl.action?m=delete";
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
				document.all.frm1.action = "bzptzhjl.action?m=toEditPage";
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
			        <td width="2%">&nbsp;</td>
			        <td width="48%"><span style="font-size: 12px;">当前位置信息：xxx</span></td>
			        <td width="50%" align="right">
						<table border="0" cellspacing="0" cellpadding="0" align="right">
					     <tr style="height: 21">
							<td background="${skinPath}images/hygl/botton.gif" align="center" style="width: 64">
							   <a href="#" onclick="javascript:adddata('bzptzhjl.action?m=toAddPage');">
							      添加
							    </a>
							</td>
							<td style="width: 3">&nbsp;</td>
							<td background="${skinPath}images/hygl/botton.gif" align="center" style="width: 64">
							   <a href="#" onclick="javascript:moddata();">
							      修改
							    </a>
							</td>
							<td style="width: 3">&nbsp;</td>
							<td background="${skinPath}images/hygl/botton.gif" align="center" style="width: 64">
							   <a href="#" onclick="javascript:deldata();">
							      删除
							    </a>
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
		<ec:table 
			items="dataList"
			action="bzptzhjl.action?m=loadData"
			imagePath="${skinPath}/images/table/*.gif"
			width="100%"
			rowsDisplayed="15"
			var="m"
			>
			<ec:row>
					<ec:column property="id" title="选择"><input name="chkid" type="checkbox"  value="${m.id}"/></ec:column>
					       <ec:column property="czzhid" title="czzhid"/>
					       <ec:column property="czje" title="czje"/>
					       <ec:column property="czsj" title="czsj" cell="date" format="yyyy-MM-dd" />
					       <ec:column property="czr" title="czr"/>
			</ec:row>
		</ec:table>
		</td>
	</tr>
</table>
	
<form id="frm1" action="" method="post">
	<input name="objectId" type="hidden">
</form>

</body>
</html>

