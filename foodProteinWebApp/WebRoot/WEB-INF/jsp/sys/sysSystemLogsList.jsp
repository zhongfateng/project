<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="com.nbw.common.SysParameter"%>
<%
	SysParameter sp = new SysParameter();
	sp.loadFile();
	String pageSize = sp.getParameter("table.pageRowNums");
%>
<html>
	<head>
		<title>日志查询</title>
		<%@include file="/resources/common/res_all.inc"%>
		<script>
			function seachLogs(){
				var stime = $("#stime").val();
				var etime = $("#etime").val();
				var userIds = $("#userIds").val();
				var moduleIds = $("#moduleIds").val();
				dhxGrid.clearAll();
				dhxGrid.loadXML("syslogs.action?m=loadGridXML&stime="+stime+"&etime="+etime+
					"&userIds="+userIds+"&moduleIds="+moduleIds, function(){});
			}
		</script>
	</head>
	<body>
		<form action="syslogs.action?m=save" name="myform" method="post"
			id="myform">
			<table width="100%" height="500" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td height="1"></td>
				</tr>
				<tr>
					<td class="td_head">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="10%" height="30">
									<img src="resources/skins/default/images/nav_title.gif"
										width="16" height="16" />
									日志查询
								</td>
								<td width="10%">
									<input type="text" name="stime" id="stime" size="20"
										class="Wdate"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss', skin:'whyGreen'})" />
								</td>
								<td width="3%" align="center">
									到
								</td>
								<td width="10%">
									<input type="text" name="etime" id="etime" size="20"
										class="Wdate"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss', skin:'whyGreen'})" />
								</td>
								<td width="12%" align="right">
									<input type="text" name="userNames" id="userNames"
										readonly="readonly" value="" />
									<input type="hidden" name="userIds" id="userIds" />
								</td>
								<td width="6%" align="center">
									<input id="buttonD" name="buttonD" type="button"
										onClick="selMultiUser('userIds','userNames');"
										icon='icon-search' value="选择用户" />
								</td>
								<td width="12%" align="right">
									<input type="text" name="moduleNames" id="moduleNames"
										readonly="readonly" />
									<input type="hidden" name="moduleIds" id="moduleIds" />
								</td>
								<td width="6%" align="center">
									<input id="buttonD" name="buttonD" type="button"
										onClick="selMultiModule('moduleIds','moduleNames');"
										icon='icon-search' value="选择模块" />
								</td>
								<td align="center" valign="middle" width="10%">
									<input id="buttonD" name="buttonD" type="button"
										onClick="seachLogs();" value="查询" icon='icon-search' />
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<div id="layoutGird" class="sys_layout"></div>
					</td>
				</tr>
			</table>
			<div id="pageArea"
				style="width: 100%; background-color: white; float: inherit; height: 30px;"></div>
		</form>
		<script>
			var dhxGrid ;
		    //创建一个dhtmlxgrid对象
		    dhxGrid = new dhtmlXGridObject('layoutGird')
			dhxGrid.setImagePath("<%=request.getContextPath()%>/resources/component/dhtmlxGrid/imgs/");
			dhxGrid.setHeader("日志时间,操作用户,机器IP,操作模块,是否成功,操作内容");
			dhxGrid.setInitWidthsP("18,15,18,18,9,22");
			dhxGrid.setColAlign("left,left,left,left,left,left");
			dhxGrid.setColTypes("ro,ro,ro,ro,ro,ro");
			dhxGrid.setColSorting("str,str,str,str,str,str");
			dhxGrid.selMultiRows = true;
			dhxGrid.setMultiLine(false);
			//分页
			dhxGrid.enablePaging(true,<%=pageSize%>, 1, "pageArea");
			dhxGrid.setPagingSkin("toolbar", "<%=gridSkin %>");
			
			dhxGrid.init();
			dhxGrid.setSkin("<%=gridSkin %>");
			dhxGrid.loadXML("syslogs.action?m=loadGridXML", function(){});
		</script>
	</body>
</html>



