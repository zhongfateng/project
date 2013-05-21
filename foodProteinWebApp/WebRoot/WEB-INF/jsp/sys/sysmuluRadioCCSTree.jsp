<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>显示CSS目录</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="Cache-Control" content="no-cache" />
		<meta http-equiv="Expires" content="0" />
		<link href="${cssFile}" type="text/css" rel="stylesheet" />
		<script type="text/javascript">
function select_one_it(str){
		 window.parent.ymPrompt.doHandler(str,false);
}

var obj1 = null ;
var objcn  = null;

function select_it(){
        
		//obj1 = opener.document.getElementById("chin_radio_thematic");
		
		var radio=document.getElementsByName("radioName");
		var max=radio.length;
		for (var i = 0;  i < max; i++) {
			if (radio[i].checked == true) {   
	   		   //obj1.value =radio[i].value;
	   		   select_one_it(radio[i].value)
			}
		}


}	
</script>
	</head>
	<body>
		<form name="form1" method="post" id="form1">
			<table id="PUBL_ORG_LIST" width="96%" border=0 align=center
				cellpadding=3 cellspacing=1 class="SelectPanelContent">
				<c:forEach items="${treeList}" var="m" begin="0" varStatus="i">
					<c:if test="${i.index % 2 ==0}">
						<tr style="background-color: #E6F2FF;">
							<!-- <td width="20" valign="top">
								<input type='radio' name="radioName" value="${m[3]}">
							</td>
							-->
							<td width="95%" valign="top">
								<a href="#" onclick="select_one_it('${m[3]}');"> ${m[3]} </a>
							</td>
						</tr>
					</c:if>
					<c:if test="${i.index % 2 !=0}">
						<tr>
							<!-- <td width="20" valign="top">
								<input type='radio' name="radioName" value="${m[3]}">
							</td>
							 -->
							<td width="95%" valign="top">
								<a href="#" onclick="select_one_it('${m[3]}');"> ${m[3]} </a>
							</td>
						</tr>
					</c:if>
				</c:forEach>
				<!--  
				<tr>
					<td width="20" valign="top" nowrap>
						<input type="button" name="Submit" value="确定"
							onclick="select_it()">
					</td>
				<tr>
				-->
			</table>
		</form>
	</body>
</html>