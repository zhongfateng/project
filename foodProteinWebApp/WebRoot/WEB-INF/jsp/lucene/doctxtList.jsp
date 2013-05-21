<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tld/extremecomponents.tld" prefix="ec" %>
<html>
<head>
<title>???</title>
<link href="${skinPath}/css/extremecomponents.css" type="text/css" rel="stylesheet"> 
<link href="${adminCssFile}" type="text/css" rel="stylesheet">
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath() %>/resources/js/util.js"></script> 
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath() %>/resources/component/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript">
//??????
function adddata(URL){
   window.location.replace(URL);
}
//??????
function deldata(){
    if(!isSingleSel('chkid')){
         alert('??????1?¤????');
         return;
    }
	var objIds = $n('chkid');
	if(objIds!=null){
		for(i = 0 ; i < objIds.length; i++){
			if(objIds[i].checked == true){
			   if(confirm('??????????¤?????')){
					document.all.frm1.objectId.value = objIds[i].value;
					document.all.frm1.action = "doctxt.action?m=delete";
					document.all.frm1.submit();
					break;
				}
			}
		}
	}
}

//??????
function moddata(){
    if(!isSingleSel('chkid')){
        alert("??????1?¤????");
        return;
    }
	var objIds = $n('chkid');
	if(objIds!=null){
		for(i = 0 ; i < objIds.length; i++){
			if(objIds[i].checked == true){
				document.all.frm1.objectId.value = objIds[i].value;
				document.all.frm1.action = "doctxt.action?m=toEditPage";
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
			        <td width="48%"><span style="font-size: 12px;">???????????xx</span></td>
			        <td width="50%" align="right">
						<table border="0" cellspacing="0" cellpadding="0" align="right">
					     <tr style="height: 21">
							<td background="${skinPath}images/hygl/botton.gif" align="center" style="width: 64">
							   <a href="#" onclick="javascript:adddata('doctxt.action?m=toAddPage');">
							      ???
							    </a>
							</td>
							<td style="width: 3">&nbsp;</td>
							<td background="${skinPath}images/hygl/botton.gif" align="center" style="width: 64">
							   <a href="#" onclick="javascript:moddata();">
							      ???
							    </a>
							</td>
							<td style="width: 3">&nbsp;</td>
							<td background="${skinPath}images/hygl/botton.gif" align="center" style="width: 64">
							   <a href="#" onclick="javascript:deldata();">
							      ???
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
			action="doctxt.action?m=loadData"
			imagePath="${skinPath}/images/table/*.gif"
			width="100%"
			rowsDisplayed="15"
			var="m"
			>
			<ec:row>
					<ec:column property="did" title="???"><input name="chkid" type="checkbox"  value="${m.id}"/></ec:column>
					       <ec:column property="pmid" title="pmid"/>
					       <ec:column property="firAuthor" title="firAuthor"/>
					       <ec:column property="affiation" title="affiation"/>
					       <ec:column property="author" title="author"/>
					       <ec:column property="corporateAuthor" title="corporateAuthor"/>
					       <ec:column property="authorFull" title="authorFull"/>
					       <ec:column property="endAuthor" title="endAuthor"/>
					       <ec:column property="book" title="book"/>
					       <ec:column property="isbn" title="isbn"/>
					       <ec:column property="journal" title="journal"/>
					       <ec:column property="issue" title="issue"/>
					       <ec:column property="completionDate" title="completionDate"/>
					       <ec:column property="createDate" title="createDate"/>
					       <ec:column property="entrezDate" title="entrezDate"/>
					       <ec:column property="meShdate" title="meShdate"/>
					       <ec:column property="modificationDate" title="modificationDate"/>
					       <ec:column property="publicationDate" title="publicationDate"/>
					       <ec:column property="rnNumber" title="rnNumber"/>
					       <ec:column property="editor" title="editor"/>
					       <ec:column property="filter" title="filter"/>
					       <ec:column property="grantNumber" title="grantNumber"/>
					       <ec:column property="investigator" title="investigator"/>
					       <ec:column property="investigatorFull" title="investigatorFull"/>
					       <ec:column property="language" title="language"/>
					       <ec:column property="contry" title="contry"/>
					       <ec:column property="locationId" title="locationId"/>
					       <ec:column property="meShMajorTopic" title="meShMajorTopic"/>
					       <ec:column property="meShSubheading" title="meShSubheading"/>
					       <ec:column property="meShTerms" title="meShTerms"/>
					       <ec:column property="pagination" title="pagination"/>
					       <ec:column property="pharmacologicalAction" title="pharmacologicalAction"/>
					       <ec:column property="publicationType" title="publicationType"/>
					       <ec:column property="publisher" title="publisher"/>
					       <ec:column property="secondarySourceId" title="secondarySourceId"/>
					       <ec:column property="supplementaryConcept" title="supplementaryConcept"/>
					       <ec:column property="textWord" title="textWord"/>
					       <ec:column property="title" title="title"/>
					       <ec:column property="transliteratedTitle" title="transliteratedTitle"/>
					       <ec:column property="abstract_" title="abstract_"/>
					       <ec:column property="volume" title="volume"/>
					       <ec:column property="substanceName" title="substanceName"/>
					       <ec:column property="issn" title="issn"/>
					       <ec:column property="cn" title="cn"/>
					       <ec:column property="keyWord" title="keyWord"/>
					       <ec:column property="subjectTerm" title="subjectTerm"/>
					       <ec:column property="featureWord" title="featureWord"/>
					       <ec:column property="apec" title="apec"/>
					       <ec:column property="textFull" title="textFull"/>
					       <ec:column property="gid" title="gid"/>
					       <ec:column property="gname" title="gname"/>
					       <ec:column property="speid" title="speid"/>
					       <ec:column property="speName" title="speName"/>
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

