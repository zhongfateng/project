<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/common/res_all.inc"%>
<script type="text/javascript">
   var isSave="<%=(String) request.getAttribute("isSave")%>";
   var fwid="<%=(String) request.getAttribute("fwid")%>";
   var index="<%=(String) request.getAttribute("index")%>";
   if (index=="ht"){
      parent.location.reload();
   }else{
	   if(isSave=="<%=Constants.DEFAULT_AJAX_SUCCESS%>"){
	     var flagURL="<%=(String) request.getAttribute("flushPsUrl")%>";
	     top.location.href = "<%=(String) request.getAttribute("flushPsUrl")%>";
	   }else{
	      ymPrompt.errorInfo({title:"提示",message:"网络无法连接！,请稍后尝试!!!"});
	   }
   }
</script>

