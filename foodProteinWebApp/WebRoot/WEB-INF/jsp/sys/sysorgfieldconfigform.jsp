<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page
	import="java.util.*,com.nbw.sys.domain.SysOrganizationsDetailCol"%>
<%
	List<SysOrganizationsDetailCol> organizationConfigList = (List<SysOrganizationsDetailCol>) request
			.getAttribute("organizationConfigList");
%>
<html>
	<head>
		<title>组织机构附属信息字段配置</title>
		<%@ include file="/resources/common/res_all.inc"%>
		<script type="text/javascript">
			save = function () {
				flag = "failure!";
				var options = {
					type: 'POST',
					async: false,
					dataType: 'content-type',
					success: function(responseText) {
						flag = ""+responseText;
					}
				}
				//提交form
				$('#myform').ajaxSubmit(options);
				
				if ("success"==flag.substring(0,7)) {
					alert("保存成功！");
					document.location.reload();
				}
			}
		</script>
	</head>
	<body>
		<form action="sysfieldcfg.action?m=saveo" name="myform" method="post"
			id="myform">

			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td height="1"></td>
				</tr>
				<tr>
					<td height="28"
						background="resources/skins/default/images/navigation.gif">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="4">
									&nbsp;
								</td>
								<td width="16">
									<img src="resources/skins/default/images/nav_title.gif"
										width="16" height="16" />
								</td>
								<td>
									组织机构附属信息字段配置
								</td>
								<td align="right" valign="middle">
									<input type="button" name="id" value="保存" onclick="save()" />
								</td>
								<td width="5">
									&nbsp;
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<table align="center" border="1" cellpadding="0" cellspacing="0"
				style="text-align: center;" width="100%" bordercolorlight="#8FC5DC"
				bordercolordark="#FFFFFF">
				<tr
					style="background-image: url('resources/skins/default/images/tiao.jpg'); text-align: center;"
					height="30">
					<td width="5%">
						序号
					</td>
					<td width="5%">
						启动
					</td>
					<td width="5%">
						字段名
					</td>
					<td width="10%">
						列名
					</td>
					<td width="10%">
						数据类型
					</td>
					<td width="10%">
						长度
					</td>
					<td width="10%">
						展现方式
					</td>
					<td width="25%">
						初始值
					</td>
					<td width="5%">
						必填
					</td>
					<td width="5%">
						排序
					</td>
				</tr>
				<%
					for (int i = 0; i < organizationConfigList.size(); i++) {
						SysOrganizationsDetailCol obj = organizationConfigList.get(i);
				%>
				<input type="hidden" name="oid" value="<%=obj.getColId()%>">
				<tr>
					<td>
						<%=i + 1%>
					</td>
					<td>
						<input class="checkbox_nbw" type="checkbox"
							<%if(SysOrganizationsDetailCol.ENABLE_TRUE==obj.getEnabled()){ %>
							checked="checked" <%} %> name="<%=obj.getColId()%>enable"
							value="<%=obj.getEnabled()%>">
					</td>
					<td>
						<%=obj.getDataSources()%>
					</td>
					<td>
						<input class="textfiled_nbw" type="text" size="38"
							name="<%=obj.getColId()%>name" value="<%=obj.getColName()%>">
					</td>
					
					<td>
						<%if(SysOrganizationsDetailCol.TYPE_DATE.equals(obj.getType())){ %>
								日期
						<%}else if(SysOrganizationsDetailCol.TYPE_FLOAT.equals(obj.getType())){ %>
								数字
						<%}else{ %>
								字符串
						<%}%>
					</td>
					<td>
						<input type="text" value="<%=obj.getLength() %>" name="<%=obj.getColId()%>length"/>
					</td>
					<td>
						<select name="<%=obj.getColId()%>showform" class="select_nbw">
							<option value="<%=SysOrganizationsDetailCol.SHOW_FORM_TEXT%>">
								文本框
							</option>
							<option value="<%=SysOrganizationsDetailCol.SHOW_FORM_SELECT%>"
								<%if(SysOrganizationsDetailCol.SHOW_FORM_SELECT.equals(obj.getShowForm())){ %>
								selected="selected" <%} %>>
								下拉框
							</option>
							<option value="<%=SysOrganizationsDetailCol.SHOW_FORM_CHECKBOX%>"
								<%if(SysOrganizationsDetailCol.SHOW_FORM_CHECKBOX.equals(obj.getShowForm())){ %>
								selected="selected" <%} %>>
								复选框
							</option>
							<option value="<%=SysOrganizationsDetailCol.SHOW_FORM_RADIO%>"
								<%if(SysOrganizationsDetailCol.SHOW_FORM_RADIO.equals(obj.getShowForm())){ %>
								selected="selected" <%} %>>
								单选框
							</option>
						</select>
					</td>
					<td>
						<input class="textfiled_nbw" type="text" size="50"
							name="<%=obj.getColId()%>colItems"
							value="<%=obj.getColItems() == null ? "" : obj
						.getColItems()%>">
					</td>
					<td>
						<input class="checkbox_nbw" type="checkbox"
							<%if(SysOrganizationsDetailCol.FILLED_TRUE==obj.getFilled()){ %>
							checked="checked" <%} %> name="<%=obj.getColId()%>filled"
							value="<%=obj.getFilled()%>">
					</td>
					<td>
						<input class="textfiled_nbw" type="text" size="3"
							name="<%=obj.getColId()%>orderNum" value="<%=obj.getOrderNum()%>">
					</td>
				</tr>
				<%
					}
				%>
			</table>
		</form>
	</body>
</html>

