<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="java.util.List"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<html>
<head>
<link href="${cssFile}" type="text/css" rel="stylesheet">
<link rel="stylesheet" id='skin' type="text/css" href="<%=request.getContextPath()%>/resources/component/ymPrompt/skin/qq/ymPrompt.css" />

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>文件上传</title>
<script type="text/javascript"
			src="<%=request.getContextPath()%>/resources/js/jquery-1.6.2.js">
</script>
<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/ymPrompt/ymPrompt.js"></script>


<script language="JavaScript">
	function htmlSubmit(){
		if(	!CheckForm()){
		 return ;
	 	}
		document.form1.submit();
	}
	

		function CheckForm(){
			var s2=document.getElementById("filename").value;
			var n=s2.lastIndexOf(".");
			var s1=s2.substring(n+1,s2.length).toLowerCase();
			if ("gif|jpg|bmp|png".indexOf(s1) == -1 || s1.length != 3){
				if(s2 == ""){
					document.getElementById("errorMsg").innerHTML="上传文件地址不能为空！";
				}else{
					document.getElementById("errorMsg").innerHTML="上传文件格式错误！请您上传gif/jpg/bmp/png格式文件";
				}
			}else{
				document.getElementById("form1").submit();
			}
		}
			
			
</script>

<script type="text/javascript">
function yanzhen()
{


			var s2=document.getElementById("fname").value;
			var n=s2.lastIndexOf(".");
			var s1=s2.substring(n+1,s2.length).toLowerCase();
			if ("txt|xy".indexOf(s1) == -1 || s1.length != 2){
				if(s2 == ""){
					ymPrompt.alert("地址不能为空");
					return false;
				}else{
					ymPrompt.alert("上传文件格式错误！请您上传gif/jpg/txt/xy格式文件");
					return false;
				}
			}else{
				document.form1.submit();
				return true;
			}
}


</script>




  </head>
  
  <body>
 
  <p>&nbsp;效果演示： </p>
<p align="center"  class="big_font">&nbsp; 文件上传下载  </p>
<form method="post" id="form" name="form1" action="upload.action?m=doUpload" enctype="multipart/form-data" >

<table width="50%" border="0" cellspacing="0" cellpadding="0" align="center"  class="tb_main2">
  <tr>
    <td class="td_style2"></td>
  </tr>  
  <tr> 
    <td class="td_style1"> &nbsp;&nbsp;&nbsp;&nbsp; <input class="file_nbw" type="file" id="fname" name="filename"  value="" size="30" >
    <input type="submit" id="upload" value="上传数据" onclick="return yanzhen();"> 
	  <br>	


	 
	 
	 
	    <div id="errorMsg" style="margin-bottom: 5px;color: #FF0000">&nbsp;&nbsp;</div>
  </tr>
</table>
</form>

<%
List downloadList = (List)request.getAttribute("downloadList");
if(downloadList!=null&&downloadList.size()>0){ %>
<p align="center"  class="big_font">&nbsp; 文件列表  </p>
<table width="50%" border="1" cellspacing="0" cellpadding="0" align="center"  class="tb_main2">
  <tr>
    <td class="td_style2">序号</td>
    <td class="td_style1"> 文件名称</td>
  </tr>
  <% for(int i=0 ; i<downloadList.size();i++){ %>
    <tr>
    <td class="td_style2"><%=i+1%></td>
    <td class="td_style1"><a href="fileUpload.action?m=downloadFile&filename=<%=downloadList.get(i)%>"><%=downloadList.get(i)%></a></td>
  </tr>
  
  <%}%>
</table>

<%}%>


</body>
</html>
