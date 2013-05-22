<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="java.util.*"%>
<%@page import="com.nbw.common.Constants"%>
<%@page import="com.nbw.sys.domain.SysUsersDetailCol"%>
<%@page import="org.apache.commons.beanutils.BeanUtils"%>
<%@page import="com.nbw.sys.domain.SysUsersDetail"%>


<%
	List<SysUsersDetailCol> userConfigs = Constants.userFieldConfig;
	SysUsersDetail sud = (SysUsersDetail) request.getAttribute("sud");
	if (sud == null) {
		sud = new SysUsersDetail();
	}
%>
<html>
	<head>
		<title>添加数据</title>
		<%@ include file="/resources/common/res_all.inc"%>

		<script type="text/javascript">

setSelect = function (name,value){
	var objSelect = document.getElementById(name);
	objSelect.options[0].selected = true;     
	for (var i = 0; i < objSelect.options.length; i++) {
        if (objSelect.options[i].value == value) {        
            objSelect.options[i].selected = true;        
            break;        
        }        
    }
}

setCheckBox = function (name,value){
	var objs = document.getElementsByName(name);
	var values = value.split("<%=SysUsersDetailCol.SPLIT_FLAG%>");
	for(var  i=0;i <objs.length;i++) {
		objs[i].checked = false;
		objs[i].disabled="";
		for(var  j=0;j <values.length;j++) {
			if(objs[i].value==values[j]){
				objs[i].checked = true;
			}
		}
	}
}

setRadio = function (name,value){
	var objs = document.getElementsByName(name);
	var values = value.split("<%=SysUsersDetailCol.SPLIT_FLAG%>");
	for(var  i=0;i <objs.length;i++) {
		objs[i].checked = false;
		for(var  j=0;j <values.length;j++) {
			if(objs[i].value==values[j]){
				objs[i].checked = true;
			}
		}
	}
}

setText = function (name,value){
	var objs = document.getElementById(name).value = value;
}

realsave = function () {
	flag = "failure!";
	<%
		for(int i=0;i<userConfigs.size();i++){
			SysUsersDetailCol sudc = userConfigs.get(i);
	%>
			<%=sudc.getJsCheck()%>
	<%
		}
	%>	
	var options = {
		type: 'POST',
		async: false,
		dataType: 'content-type',
		success: function(responseText) {
			flag = ""+responseText;
		}
	}
	//提交附属信息
	$('#myattform').ajaxSubmit(options);
	return flag;
}

save = function () { 
	var aa = realsave();
	ymPrompt.resizeWin(300,150);
	return aa;
}

</script>
	</head>
	<body>
		<form action="sysusers.action?m=saveAtt" name="myattform"
			method="post" id="myattform">
			<input type="hidden" name="id" value="<%=sud.getId()%>" />
			<table align="center" border="1" cellpadding="0" cellspacing="0"
				width="100%" bordercolorlight="#8FC5DC" bordercolordark="#FFFFFF">
				<%
					for (int i = 0; i < userConfigs.size(); i++) {
						SysUsersDetailCol uoc = userConfigs.get(i);
				%>
				<tr>
					<td>
						<%=uoc.getShowHtml()%>
					</td>
				</tr>
				<%
					}
				%>
				<tr>
					<td>
					</td>
				</tr>
			</table>
		</form>
		<script type="text/javascript">
	<%
		for(int i=0;i<userConfigs.size();i++){
			SysUsersDetailCol suc = userConfigs.get(i);
			String value = BeanUtils.getProperty(sud,suc.getDataSources())==null?"":BeanUtils.getProperty(sud,suc.getDataSources());
			if(suc.getShowForm().equals(SysUsersDetailCol.SHOW_FORM_CHECKBOX)){
				%>
					setCheckBox("att<%=suc.getDataSources()%>","<%=value%>");
				<%
			}else if(suc.getShowForm().equals(SysUsersDetailCol.SHOW_FORM_RADIO)){
				%>
					setRadio("att<%=suc.getDataSources()%>","<%=value%>");
				<%
			}else if(suc.getShowForm().equals(SysUsersDetailCol.SHOW_FORM_SELECT)){
				%>
					setSelect("att<%=suc.getDataSources()%>","<%=value%>");
				<%
			}else{
				%>
					setText("att<%=suc.getDataSources()%>","<%=value%>");
				<%
			}
	}%>
</script>
	</body>
</html>
