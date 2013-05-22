<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.nbw.common.SysParameter"%>
<%
		String bzzyWebApp1 = SysParameter.getParameter("bzzyWebApp");
	%>
<%
String portalPath=com.nbw.common.SysParameter.getParameter("portalPath");
String randStr = String.valueOf(System.currentTimeMillis());
session.setAttribute("loginRand", randStr);
String keyId = (String)session.getAttribute("keyId");
String keyTitle = session.getAttribute("keyTitle")!=null?(String)session.getAttribute("keyTitle"):"";
//keyTitle =new String(keyTitle.getBytes("ISO-8859-1"),"GBK");
//出于安全考虑，签名原文必须在服务端产生，并且验证时也必须以服务端session中的签名原文为准
%>
<html>
  <head>
	<script language="vbscript" src="<%=bzzyWebApp1%>/secease/DeclareCom.vbs"></script>
	<script language="vbscript">
 	 DeclareSignForm
  	 DeclareSignSrc
  	 DeclarewStamp
	</script>
	<script language="javascript" src="<%=bzzyWebApp1%>/secease/SignForm.js"></script>
	<script type="text/javascript" src="<%=bzzyWebApp1%>/resources/js/jquery-1.6.2.js"></script>
  </head>
  <body >
  <form name="loginForm" action="<%=bzzyWebApp1%>/checkKeyServlet" method="get">	
	<!-- 随机数 -->
	<input name="random" type="hidden" value="${sessionScope.loginRand}" >	
		<!-- 对随机数签名 -->
	<input name="randomSignature" type="hidden">
	<table width="100%" id="keyTab" border="0" cellspacing="0" cellpadding="0" style="display: none;">
		<tr>
			<td>
				请选择证书：<select name="CertList" onChange="javascript:SelectCertByIndex(this.value);" class="select"></select>
			 </td>
		</tr>
		 <tr>
         <td height="40" align="center"><img src="${skinPath}images/cqcca/u-8.gif" width="66" height="23" onClick="tijiao()"/></td>
      </tr>
	</table>
   </form>	
   <script language="javascript">
   
   
   var list ;
   var oplength;
	/*
	 * 页面加载时判读key信息
	 */ 
	function checkKey(){
	 	//初始化select选择框
		    var arrayIssuerDN = new Array(
			//"O=\"iTruschina Co., Ltd.\", OU=Chinese Trust Network, OU=Terms of use at https://www.itrus.com.cn/ctnrpa (c)2004, CN=iTruschina CN //Enterprise Individual Subscriber CA"
		 	"C=CN, O=China Quality Certification Center, CN=CQC CA"
			//, "O=SECease, OU=SECease, CN=SECease User CA"
			);
			SetFilters(arrayIssuerDN,"","");
			InitCertList(document.all.CertList);
			list =  document.all.CertList;
			//判读插入key个数
		    oplength=document.all.CertList.options.length;
		    if (oplength==0){
		        alert("没有找到数字证书，请重新插入key!");
		        top.window.opener=null;
		        top.window.open('', '_self', ''); 
		        top.window.close(); 
		        return false;
		     }
	  		//判读插入key个数
			   else  if(oplength==1){//有一个key插入 
				    return firstKeyLogin();
			      }else{//多个key插入
	           		  //如果已有用户登陆 判读用户是否有现在key用户 
				      var oldkeyTitle = '<%=keyTitle%>';
				      for(var i=0;i<list.length;i++){
				        var nowkeyTitle = list[i].text;
				        if(oldkeyTitle==nowkeyTitle){
				         //有当前用户
				         return true;
				        }
				      }
				      alert("原登陆key信息丢失,请重新选择key登陆！");
			         top.window.opener=null;
			         top.window.open('', '_self', ''); 
			         top.window.close(); 
			         return false;
			      }
	  }
	/*
	 * 多key情况下选择第一次key登陆
	 */    
	 function firstKeyLogin(){
	        //判读当前是否有用户登陆
	        <% if(null==keyId){ %>
	          tijiao();
	         <%}else{ %>
	         //判读当前key是否与登陆可以一致
	          var oldkeyTitle = '<%=keyTitle%>';
	          var nowkeyTitle = list[0].text;
	          if(oldkeyTitle==nowkeyTitle){
	           return true;
	          }else{
		       alert("key信息不一致,请重新登陆!");
		       top.window.opener=null;
		       top.window.open('', '_self', ''); 
		       top.window.close(); 
		       return false;
			 }	
	         <%}%>  
	 }   
     
    /*
	 * 选择相应的key提交key信息
	 */
   function tijiao(){
            var signature = SignMessage(loginForm.random.value);
			var sign=escape(signature).replace(/\+/g, '%2B').replace(/\"/g,'%22').replace(/\'/g, '%27').replace(/\//g,'%2F');
			var form = document.getElementById("loginForm");
			if(form.CertList.value==null || form.CertList.value =="" || form.CertList.value == -1) {
			    alert("没有找到数字证书，请重新插入key!");
			    top.window.opener=null;
		        top.window.open('', '_self', ''); 
		        top.window.close(); 
				return false;
			} else {
				if(signature.length > 0){
					//key的标识名
					var keyTitle = list[0].text;
					//验证用户信息与key信息
					 $.ajax({  
						type: "get",
						url: "<%=bzzyWebApp1%>/LoginServlet?randomSignature="+sign+"&loginRand="+form.random.value+"&keyTitle="+encodeURI(encodeURI(keyTitle)),
						success: function(data){
						if (data.indexOf('success')!=-1){
						   if(data=='success'){ //当前登陆用户与key用户信息一致 登陆成功
						       return true;
						   }else{ //当前登陆用户状态为空 key登陆信息验证成功 到portal项目中的ldap判读用户信息
						       var dataStr = data.split(",");
						       return true;
						      //到portal项目中的ldap判读用户信息 如果判读失败 将keyId从session中删除 防止session值存储过多导致性能降低
						      return ajaxDL(dataStr[1],dataStr[2]);
						   }
						}else{//用户信息验证失败 提示失败原因
					         alert(data);
					         top.window.opener=null;
					         top.window.open('', '_self', ''); 
					         top.window.close(); 
					         return false;
						}
					  }
			  		 });
				}
			}
 	}
   /*
 	* 进行portal的ldap信息验证
 	*/
 	function ajaxDL(username,ee){
  		   var url="<%=portalPath%>/ajaxlogin.do?username="+username+"&ee="+ee;
	       $.ajax({  
				type: "get",
				url: url,
				success: function(data, textStatus){
				 return true;
				},
				error: function(){
					alert("系统出现异常，登陆失败，请稍后再尝试！");
					return false;
				}
		    });
		    return true;
	}
	
	checkKey();
	</script>
  </body>
</html>
