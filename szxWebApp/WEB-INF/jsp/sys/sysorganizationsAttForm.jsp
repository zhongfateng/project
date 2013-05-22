<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="java.util.*"%>
<%@page import="com.nbw.common.Constants"%>
<%@page import="com.nbw.sys.domain.SysOrganizationsDetailCol"%>
<%@page import="org.apache.commons.beanutils.BeanUtils"%>
<%@page import="com.nbw.sys.domain.SysOrganizationsDetail"%>


<%
	List<SysOrganizationsDetailCol> orgConfigs = Constants.orgFieldConfig;
	SysOrganizationsDetail sod = (SysOrganizationsDetail) request
			.getAttribute("sod");
	if (sod == null) {
		sod = new SysOrganizationsDetail();
	}
%>
<html>
	<head>
		<title>添加数据</title>
		<%@ include file="/resources/common/res_common.inc"%>
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
				var values = value.split("<%=SysOrganizationsDetailCol.SPLIT_FLAG%>");
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
				var values = value.split("<%=SysOrganizationsDetailCol.SPLIT_FLAG%>");
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
			
			save = function () { 
				var aa = realsave();
				ymPrompt.resizeWin(300,150);
				return aa;
			}
			realsave = function () {
				flag = "failure!";
				<%
					for(int i=0;i<orgConfigs.size();i++){
						SysOrganizationsDetailCol orgc = orgConfigs.get(i);
				%>
						<%=orgc.getJsCheck()%>
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
	</script>
	</head>
	<body style="width: 100%;">
		<form action="sysorganizations.action?m=saveAtt" name="myattform"
			method="post" id="myattform">
			<input type="hidden" name="id" value="<%=sod.getId()%>" />
			<table align="center" border="1" cellpadding="0"
				cellspacing="0" width="100%" bordercolorlight="#8FC5DC"
				bordercolordark="#FFFFFF">
				<%
					for (int i = 0; i < orgConfigs.size(); i++) {
						SysOrganizationsDetailCol orgc = orgConfigs.get(i);
				%>
				<tr>
					<td>
						<%=orgc.getShowHtml()%>
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
		for(int i=0;i<orgConfigs.size();i++){
			SysOrganizationsDetailCol orgc = orgConfigs.get(i);
			String value = BeanUtils.getProperty(sod,orgc.getDataSources())==null?"":BeanUtils.getProperty(sod,orgc.getDataSources());
			if(orgc.getShowForm().equals(SysOrganizationsDetailCol.SHOW_FORM_CHECKBOX)){
				%>
					setCheckBox("att<%=orgc.getDataSources()%>","<%=value%>");
				<%
			}else if(orgc.getShowForm().equals(SysOrganizationsDetailCol.SHOW_FORM_RADIO)){
				%>
					setRadio("att<%=orgc.getDataSources()%>","<%=value%>");
				<%
			}else if(orgc.getShowForm().equals(SysOrganizationsDetailCol.SHOW_FORM_SELECT)){
				%>
					setSelect("att<%=orgc.getDataSources()%>","<%=value%>");
				<%
			}else{
				%>
					setText("att<%=orgc.getDataSources()%>","<%=value%>");
				<%
			}
	}%>
</script>
	</body>
</html>
