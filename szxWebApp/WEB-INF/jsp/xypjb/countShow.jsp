<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>实名制质量信用信息化平台</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/images/back/images/skin.css">
	<link rel="stylesheet" id='skin' type="text/css" href="<%=request.getContextPath()%>/resources/component/ymPrompt/skin/simple_gray/ymPrompt.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.6.2.js"></script>
	<script type="text/javascript" src ="<%=request.getContextPath()%>/resources/component/ymPrompt/ymPrompt.js"></script>
	<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath() %>/resources/component/My97DatePicker/WdatePicker.js"></script>
	 <script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath()%>/resources/component/FusionCharts/FusionCharts.js"></script>
			<script type="text/javascript">
			
			$(function(){
			
			$("#countid").click(function(){
			
			window.location.href="import.action?m=toOutputCount";
			});
			});
			</script>
			<script type="text/javascript">
			
			function search()
			{
			var enddate =document.getElementById("data2").value;
			window.location.href="import.action?m=toCountShow&enddate="+enddate;
			}
			</script>
			
			
	<script type="text/javascript">

	function show()
	{
	

	   if    (chartdiv.style.display=="none")   
                 chartdiv.style.display="block";   
         else   
                 chartdiv.style.display="none";   
	}
	
	function showcloumn()
	{

	   if    (chartdiv2.style.display=="none")   
                 chartdiv2.style.display="block";   
         else   
                 chartdiv2.style.display="none";   
	}
	</script>
  </head>
  
  <body>
   <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" background="<%=request.getContextPath()%>/resources/images/back/images/man-info_14.png">
  <tr>
    <td width="11"><img src="<%=request.getContextPath()%>/resources/images/back/images/man-info_12.png" width="11" height="30" /></td>
    <td width="144" align="center" background="<%=request.getContextPath()%>/resources/images/back/images/man-info_13.png" class="txt_bt"><span class="left_bt2">信用等级统计</span></td>
    <td>&nbsp;</td>
    <td width="14"><img src="<%=request.getContextPath()%>/resources/images/back/images/man-info_17.png" width="14" height="30" /></td>
  </tr>
</table>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#F7F8F9">
  <tr>
    <td width="11" background="<%=request.getContextPath()%>/resources/images/back/images/man-info_19.png">&nbsp;</td>
    <td><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td valign="middle"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td colspan="4" align="center" class="login_txt_bt">
            
            <div class="login_txt_tb0">
            <table>
            <tr>
            <td width="197" height="35" align="right"><span class="login_text">行&nbsp;&nbsp; 政&nbsp;&nbsp; 区&nbsp; &nbsp;划&nbsp; &nbsp;：</span></td>  
                <td width="147" align="left"><label>
                  <select name="select3" class="input4" style="width:157px;">
                <option>请选择……</option>
                </select>
                </label>
              </td> 
                </tr>
                
                <tr>
            <td height="35" align="right"><span class="login_text">截&nbsp;&nbsp; 止&nbsp;&nbsp; 日&nbsp; &nbsp;期&nbsp; &nbsp;：</span></td>  
                <td align="left"><label>
                 <input type="text" id="data2" name="EndDate"  value="${enddate}" class="Wdate"  onFocus="WdatePicker({isShowWeek:true})" />
                </label>
              </td> 
                </tr>
               
                
                </table>
            <p>
             <input type="image"  src="<%=request.getContextPath()%>/resources/images/back/images/searchbb.gif" onclick="search()"><br>
            <input  type="image" src="<%=request.getContextPath()%>/resources/images/back/images/input_04.gif" width="108" height="28" onclick="show()"/>  <input type="image" src="<%=request.getContextPath()%>/resources/images/back/images/input_05.gif" width="108" height="28" onclick="showcloumn()"/><a href="javascript:;" id="countid"> <img src="<%=request.getContextPath()%>/resources/images/back/images/input_06.gif" width="108" height="28" border="0"/> </a></p>
              </div>
              
              </td>
          </tr>
          <tr>
            <td height="40" colspan="4" align="right" class="login_txt_bt"><table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
              <tr>
                <td></td>
                </tr>
              </table></td>
          </tr>
        </table>
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td width="25%"><span class="login_icon"><img src="<%=request.getContextPath()%>/resources/images/back/images/icon_01.gif" /></span> <span class="txt_bt"> 统计结果</span></td>
                  <td width="25%">&nbsp;</td>
                  <td width="49%" align="right">&nbsp;</td>
                  <td width="1%">&nbsp;</td>
                </tr>
              </table>
          <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">
                <tr>
                  <td height="30" colspan="6" align="center" background="<%=request.getContextPath()%>/resources/images/back/images/a0.gif" class="txt_bt">
                      <div id="u6">
                        <div class="u6_rtf"> 截至&nbsp;<span class="left_ts">${enddate} </span> 企业质量信用等级统计数据 </div>
                      </div></td>
                </tr>
                
                <tr>
                  <td width="18%" height="25" align="center" bgcolor="#F7F8F9" class="txt_bt">信用等级</td>
                  <td width="26%" align="center" bgcolor="#F7F8F9" class="left_txt">A</td>
                  <td width="16%" align="center" bgcolor="#F7F8F9" class="left_txt"><div id="u22">
                      <div class="left_txt" id="u22_rtf">B</div>
                  </div></td>
                  <td width="23%" align="center" bgcolor="#F7F8F9" class="left_txt"><div id="u22">
                      <div id="u22_rtf">C</div>
                  </div></td>
                  <td width="8%" align="center" bgcolor="#F7F8F9" class="left_txt"><div id="u22">
                      <div class="left_txt_l" id="u22_rtf">D</div>
                  </div></td>
                  <td width="9%" align="center" bgcolor="#F7F8F9" class="left_bt2">合计</td>
                </tr>
                <tr>
                  <td height="25" align="center" bgcolor="#F7F8F9" class="txt_bt">组织数量</td>
                  <td align="center" bgcolor="#F7F8F9" class="left_txt">${A }</td>
                  <td align="center" bgcolor="#F7F8F9" class="left_txt">${B }</td>
                  <td align="center" bgcolor="#F7F8F9" class="left_txt">${C }</td>
                  <td align="center" bgcolor="#F7F8F9" class="left_txt"><div id="u2">${D }</div></td>
                  <td align="center" bgcolor="#F7F8F9" class="left_txt">${all }</td>
                </tr>
                <tr>
                  <td height="25" align="center" bgcolor="#F7F8F9" class="left_txt"><div id="u45">
                      <div class="txt_bt" id="txt_bt">
                        百分比
                      </div>
                  </div></td>
                <td align="center" bgcolor="#F7F8F9" class="left_txt"><div id="u47">
                      <div id="u47_rtf">
                        ${A}/${all }
                      </div>
                  </div></td>
                  <td align="center" bgcolor="#F7F8F9" class=""><div id="div">
                     ${B}/${all }
                  </div></td>
                  <td align="center" bgcolor="#F7F8F9" class="left_txt"><div id="u51">
                      <div id="u51_rtf">
                         ${C}/${all }
                      </div>
                  </div></td>
                  <td align="center" bgcolor="#F7F8F9" class="left_txt"><div id="u">
                      <div id="u51_rtf2">
                        ${D}/${all }
                      </div>
                  </div></td>
                  <td align="center" bgcolor="#F7F8F9" class="left_txt">100%</td>
                </tr>
                <tr>
                  <td height="380" colspan="6" align="center" bgcolor="#F7F8F9"> 
                  <div  align="center" name="chartdiv" id="chartdiv" style="display:none"></div>
                  <script> 	
                  var chart = new FusionCharts("<%=request.getContextPath()%>/resources/component/FusionCharts/Pie3D.swf", "chart1Id", "600", "300");
        	 		chart.setDataXML("${str}");
	     			chart.render("chartdiv");
	      	 </script>
                  </td>
                  
                </tr>
                <tr><td height="380" colspan="6" align="center" bgcolor="#F7F8F9"><div align="center" name="chartdiv2" id="chartdiv2" style="display:none" ></div>
                  	
                  	<script>
                  	var chartcolumn=new FusionCharts("<%=request.getContextPath()%>/resources/component/FusionCharts/Column3D.swf", "chart1Id", "600", "300");
                  	
                  	chartcolumn.setDataXML("${str2}");
	     			chartcolumn.render("chartdiv2");
                  	</script>
                  	</td>
                  	</tr>
              </table>
          <table width="100%" height="40" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td align="right">&nbsp;</td>
                  <td align="center" class="left_txt">&nbsp;</td>
                  <td align="left">&nbsp;</td>
                </tr>
           </table>
        
  
      <tr>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td height="20">
        <table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
          <tr>
            <td></td>
          </tr>
        </table></td>
      </tr>
    </table>
        <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td colspan="2" valign="middle"><span class="left_txt"><br />
            </span></td>
            <td width="1%">&nbsp;</td>
            <td width="40%" valign="top">&nbsp;</td>
          </tr>
          <tr>
            <td width="2%">&nbsp;</td>
            <td width="57%" class="left_txt"><img src="<%=request.getContextPath()%>/resources/images/back/images/icon-mail2.gif" width="16" height="11" /> 客户服务邮箱：123121212@qq.com<br />
                <img src="<%=request.getContextPath()%>/resources/images/back/images/icon-phone.gif" width="17" height="14" /> 官方网站：http://www.11212121.cn</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
      </table></td>
    <td width="14" background="<%=request.getContextPath()%>/resources/images/back/images/man-info_21.png">&nbsp;</td>
  </tr>
</table>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" background="<%=request.getContextPath()%>/resources/images/back/images/man-info_30.png">
  <tr>
    <td width="11" background="<%=request.getContextPath()%>/resources/images/back/images/man-info_28.png">&nbsp;</td>
    <td width="144">&nbsp;</td>
    <td>&nbsp;</td>
    <td width="14" background="<%=request.getContextPath()%>/resources/images/back/images/man-info_32.png">&nbsp;</td>
  </tr>
</table>
<p></p>
   
   
   
   
   
   
  </body>
</html>
