<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/resources/common/res_all.inc"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ path + "/";
	%>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/FusionCharts/FusionCharts.js"></script>
		<link href="${adminCssFile}" type="text/css" rel="stylesheet">
		<script language="javascript"> 
         function loadColumnXML(){
            var chart = new FusionCharts("<%=request.getContextPath()%>/resources/component/FusionCharts/Column3D.swf", "ChartId", "900", "300", "0", "0");
		   chart.setDataURL(escape("analysis.action?m=frontLoadColumnChartsXML"));    
		   chart.render("chartdiv");
         }
        
		function getSecond(fid){
         window.open ('analysis.action?m=toBzAnalysisSecond&fid='+fid, '_blank', 'height=1000, width=1000, top=0,left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no'); 
		}
        
function submitForm(){ 
		var chart = new FusionCharts("<%=request.getContextPath()%>/resources/component/FusionCharts/Line.swf", "ChartId", "600", "300", "0", "0");
		var stime=document.getElementById("stime"); 
		var etime=document.getElementById("etime"); 
		if(stime.value.length==0||etime.value.length==0){
		    alert("请选择开始月份与结束月份")
		}else{
			chart.setDataURL(escape("bzptbook.action?m=loadBookLineChartsXMLGroupByMonth&startTime="+stime.value+"&endTime="+etime.value));    
			chart.render("chartdiv");
		}
	   createDateGrid(stime,etime);
} 
function createDateGrid(stime,etime){
		dhxGrid.clearAll();
		dhxGrid.loadXML("bzptbook.action?m=loadBookDateByTimeStamp&startTime="+stime.value+"&endTime="+etime.value, function(){});
}
function loadExcelDate(){ 
		var stime=document.getElementById("stime"); 
		var etime=document.getElementById("etime"); 
		if(stime.value.length==0||etime.value.length==0){
		    alert("请选择开始月份与结束月份")
		}else{
		
			document.all.frm1.action = "bzptbook.action?m=loadBookDateByTimeStampForExcel&startTime="+stime.value+"&endTime="+etime.value;
	        document.all.frm1.target="_blank"
	        document.all.frm1.submit();
		}
} 
</script>
	</head>
	<body>
		<table cellpadding="0" cellspacing="0" width="100%" border="0"
			align="center">
			<tr>
				<td>
					<table width="100%" height="30" border="0" cellpadding="0"
						cellspacing="0">
						<tr class="tr_title">
							<td width="2%" >
								&nbsp;
							</td>
							<td >
								<img src="${skinPath}/images/manage/dh-bg0.gif" width="14"
									height="14" />
								&nbsp;
								<font color="#016250"> 当前位置信息：系统接口管理 </font>
							</td>
							<td align="right" valign="middle">
							<input id="buttonA" name="buttonA" type="button"
								onClick="addRow();" value="添加" icon='icon-add' />
								<input id="buttonD" name="buttonD" type="button"
									onClick="delData();" value="删除" icon='icon-delete' />
								<input id="buttonS" name="buttonS" type="button"
									onClick="saveData()"
									value="保存" icon='icon-save' />
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					
					<div id="gridbox"
						style="width: 100%; height: 400px; background-color: white;"
						align="center"></div>
							<div id="pageArea"
						style="width: 100%; background-color: white; float: inherit; height: 30px;" align="center"></div>
				</td>
				
			</tr>
		</table>
		<script type="text/javascript">
		var isDel = <%=isDel%>;
	function addRow(){
	    var id = new UUID();
		var values=[id,"","","","","","","","","","",""];
		dhxGrid.addRow(id,values);
		dhxGrid.selectRowById(id);
	}
	function saveData(){
		myDataProcessor.sendData();
		ymPrompt.succeedInfo({title:"提示",message:"保存成功！"});
	}
	function delData(){
	
	if(!dhxGrid.getSelectedRowId()){
		alert("未选中行！");
		return false;
		}
	
		function confirmDel(i){if('yes' != i) return false;
			dhxGrid.deleteSelectedItem();
			myDataProcessor.sendData();
		}
		if (ymPrompt.confirmInfo({title:"提示",message:'确定删除选中的记录吗?',btn:[['是','yes'],['否','no']],handler:confirmDel}));
	}
		
var dhxGrid = new dhtmlXGridObject("gridbox");
dhxGrid.setImagePath("<%=request.getContextPath()%>/resources/component/dhtmlxGrid/imgs/");
dhxGrid.setHeader("id,外部系统编号,外部系统名称,PARENT_ORG_ID,REMARK,外部系统域名或者IP,webserviceUrl,webservice方法,webservice Qname,超时时间,加密方式,是否本地");
dhxGrid.setColumnIds("ORG_ID,ORG_CODE,ORG_NAME,PARENT_ORG_ID,REMARK,YUMING_IP,WEBSERVICEURL,METHOD,QNAME,TIMEOUT,SERCETWAY,ISNATIVE");
dhxGrid.setInitWidthsP("0,0,14,0,0,13,18,11,11,11,11,11");
dhxGrid.setColAlign("left,left,left,left,left,left,left,left,left,left,left,left");
dhxGrid.setColTypes("ed,ed,ed,ed,ed,ed,ed,ed,ed,ed,coro,coro");
dhxGrid.setColSorting("str,str,str,str,str,str,str,str,str,str,str,str");
dhxGrid.selMultiRows = true;
dhxGrid.setMultiLine(false);
dhxGrid.setSkin("<%=gridSkin%>");
dhxGrid.init();
dhxGrid.loadXML("organization.action?m=frontLoadOrganizationXML", function(){
<%--       dhxGrid.setColumnHidden(0,true);--%>
	   dhxGrid.setColumnHidden(1,true);
<%--	   dhxGrid.setColumnHidden(3,true);--%>
<%--	   dhxGrid.setColumnHidden(4,true);--%>
});
dhxGrid.getCombo(11).put('0','本地');
dhxGrid.getCombo(11).put('1','非本地');
dhxGrid.getCombo(10).put('1','传加密文件');
dhxGrid.getCombo(10).put('2','传原文件不需要水印');
dhxGrid.getCombo(10).put('3','传原文件需要水印');
//分页
<%--	dhxGrid.enablePaging(true,15, 1, "pageArea");--%>
<%--	dhxGrid.setPagingSkin("toolbar", "${dhtmlSkinStyle}");--%>
//dhxGrid.enablePaging(true,15, 1, "pageArea");
	dhxGrid.enablePaging(true,15,1,"pageArea",true,"dhx_skyblue");
			dhxGrid.setPagingSkin("toolbar","dhx_skyblue");
			dhxGrid.i18n.paging={
		      results:"结果",
		      records:"结果 从 ",
		      to:" 到 ",
		      page:"页 ",
		      perpage:"条/页",
		      first:"To first Page",
		      previous:"Previous Page",
		      found:"Found records",
		      next:"Next Page",
		      last:"To last Page",
		      of:" of ",
		      notfound:"没有记录" 
		    };
	
	function checkCode(value, id, ind) {
		if (!isLegalGridValueCode(value,dhxGrid.getRowIndex(id)+1, ind-1,4,32)){
			dhxGrid.setCellTextStyle(id, ind-1, "background-color:yellow;");
			return false;
		}
		return true;
	}		
	function notEmpty(value, id, ind) {
		if (!isNotOverlength(value,dhxGrid.getRowIndex(id)+1, ind,1,64)){
			dhxGrid.setCellTextStyle(id, ind-1, "background-color:yellow;");
			return false;
		}
		return true;
	}	
	function notEmpty2(value, id, ind) {
		if (!isNotOverlength(value,dhxGrid.getRowIndex(id)+1, ind+2,1,64)){
			dhxGrid.setCellTextStyle(id, ind-1, "background-color:yellow;");
			return false;
		}
		return true;
	}	
	function isnum(value, id, ind) {
		if (!isNumeric(value,dhxGrid.getRowIndex(id)+1, ind-1)){
			dhxGrid.setCellTextStyle(id, ind-1, "background-color:yellow;");
			return false;
		}
		return true;
	}
	myDataProcessor = new dataProcessor("organization.action?m=save");
	myDataProcessor.setUpdateMode("off");
	myDataProcessor.setTransactionMode("POST",false);
	myDataProcessor.enableDataNames(true);
	myDataProcessor.setVerificator(2, notEmpty);
	myDataProcessor.attachEvent("onRowMark", function(id) {
	    
    	if (this.is_invalid(id) == "invalid")
        	return false;
	    return true;
	});

	//判断不同用户重复增加或修改
 	myDataProcessor.defineAction("insert",myErrorHandler);
 	myDataProcessor.defineAction("update",myErrorHandler);
	function myErrorHandler(obj){
		var meg = obj.firstChild.nodeValue;
		
		if (meg==1) {
			return true;
		} else {
			ymPrompt.errorInfo({title:"提示",message:meg});
			myDataProcessor.stopOnError = true;
	   	 	return false;
		}
    }
	myDataProcessor.init(dhxGrid);
	
</script>
		<form id="frm1" action="" method="post">
		</form>
	</body>
</html>
