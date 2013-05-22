<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>显示ICS目录</title>
		<meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="Cache-Control" content="no-cache" />
		<meta http-equiv="Expires" content="0" />
		<script type="text/javascript">
function select_one_it(str,cn){
		 var objcn  = null;
		 obj1 = opener.document.getElementById("chin_thematic_en");
		 objcn = opener.document.getElementById("chin_typenum_en");
		 var cnName="cn_name"+cn; 
		 obj1.value =str+""+document.getElementById(cnName).value;
		 objcn.value=str
		 window.close();
}

var obj1 = null ;
var objcn  = null;
function select_it(){
        
		obj1 = opener.document.getElementById("chin_thematic_en");
	    objcn = opener.document.getElementById("chin_typenum_en");
	
		var v="";
	    var cn="";
		var total = 0;
		var max = document.getElementById("form1").ckbox.length;
	
	    var h =0;
		for (var i = 0;  i < max; i++) {
			if (eval("document.form1.ckbox[" + i + "].checked") == true) {
				v = v + "" + document.form1.ckbox[i].value+" ";
				var cnName="cn_name"+h;
				cn = cn+document.form1.ckbox[i].value +""+document.getElementById(cnName).value+" ";;
				total += 1;
	           
			}
	      h++;
	      
		}
        
		objcn.value = v.substring(0, v.length-1);
		obj1.value = cn;
		window.close();

}	



function select_all(form){

		var v="";
		var total = 0;
		var max = form.ckbox.length;
		for (var i = 0;  i < max; i++) {
			document.form1.ckbox[i].checked = true;
	
		}

}	

function clear_all(form){

		var v="";
		var total = 0;
		var max = form.ckbox.length;
		for (var i = 0;  i < max; i++) {
			document.form1.ckbox[i].checked = false;
	
		}

}	
function adddata(URL){
   window.location.replace(URL);
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

							<td width="20" valign="top">
								<input type='checkbox' name="ckbox" value="${m[1]}">
								<input type='hidden' name="cn_name" id="cn_name${i.index}"
									value="${m[3]}">
							</td>

							<td width="95%" valign="top">
								<a href="#" onclick="select_one_it('${m[1]}','${i.index}');">
									${m[1]} ${m[3]} </a>
								<a
									href="sysmulu.action?m=frontICSTreeLeaf&treeId=${m[1]}&level=2";>
									<img src="${skinPath}images/pop_window_arrow_down.gif"
										title="点击进入下一级" border="0"> </a>

							</td>
						</tr>
					</c:if>
					<c:if test="${i.index % 2 !=0}">
						<tr>

							<td width="20" valign="top">
								<input type='checkbox' name="ckbox" value="${m[1]}">
								<input type='hidden' name="cn_name" id="cn_name${i.index}"
									value="${m[3]}">
							</td>

							<td width="95%" valign="top">
								<a href="#" onclick="select_one_it('${m[1]}','${i.index}');">
									${m[1]} ${m[3]} </a>
								<a
									href="sysmulu.action?m=frontICSTreeLeaf&treeId=${m[1]}&level=2";>
									<img src="${skinPath}images/pop_window_arrow_down.gif"
										title="点击进入下一级" border="0"> </a>

							</td>
						</tr>
					</c:if>
				</c:forEach>
				<tr>
					<td width="20" valign="top" nowrap>
						<input type="button" name="Submit" value="确定"
							onclick="select_it()">
					</td>
					<td>
						<input type="button" name="all" value="全选"
							onclick="select_all(this.form)">
						<input type=button value="取消" onclick="clear_all(this.form)">
					</td>
				<tr>
			</table>
		</form>
	</body>
</html>