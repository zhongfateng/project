	<%@ page language="java" contentType="text/html; charset=utf-8"
	    pageEncoding="utf-8"%>
	<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
	<%@ page import="java.util.List" %>

<%@page import="com.nbw.lucene.domain.Doctxt;"%>
	<HTML><HEAD>
	<script type= "text/javascript">
	function isEmpty()
	{
	 var searchWord=document.getElementById("searchWord")
	if(searchWord.value=="")
	{
	alert("请输入搜素关键字");
	return false;
	}	
	else
	{
	return true;
	}
	}
	</script>
	<TITLE>Lucene Search Engine Demo Client</TITLE>
	<META http-equiv=Content-Type content="text/html; charset=utf-8">
	<META content="MSHTML 6.00.2800.1543" name=GENERATOR>
	<style type="text/css">
	<!--
	body{margin:0;
	font-family: Arial, Helvetica, sans-serif;
		font-size: 0.8em;
	}
	td,th,div{
			font-size: 0.8em;
	}
	a{
		color: #0066FF;
	}
	a:hover{
		color: #666;
		text-decoration: none;
	}
	
	h1{
	margin:0px;
	height:70px;
	line-height:70px;
	background: #6699CC;
	color: #fff;
	border-bottom:solid 1px #006699;
	}
	.search{
	background:#ddeeff;
	padding-top:5px;
	padding-bottom:5px;
	border-bottom:solid 1px #9ABBCB;
	}
	form{margin:0;
	}
	.result{
	width:70%;
		clear:both;
	margin-bottom:20px;
	margin-top:20px;}
	.result h3{
			margin:0px;
			line-height: 25px;
			font-size: 1.3em;
	}
	.linked {
		padding-top: 5px;
		padding-bottom: 5px;
	}
	.linked a{
	
	margin-right: 10px;
	border:solid 1px #CCCCCC;
	padding:3px 10px 3px 10px;
	text-decoration: none;
	}
	.linked a:hover{
	
	margin-right: 10px;
	border:solid 1px #0066FF;
		background: #0066FF;
		color: #fff;
	
	}
	hr{
	
		border: dashed 1px #ddd;
			display: block;
			background:#fff;
			height: 1px;
	}
	.footer{
		font-size:0.8em;
		border-top:solid 1px #ddd;
		padding-top:10px;
	}
	.footer a{color:#666;
	text-decoration: none;}
	.footer a:hover{
		text-decoration: underline;
	}
	-->
	</style>
	</HEAD>
	<BODY>
	<CENTER><h1>Lucene PDF 文档的全文索引</h1>
	<div class="search">
	  <FORM id=searchForm action=search.action?m=search method="post" onsubmit="return isEmpty()" id="form">
	  <TABLE>
	    <TBODY>
	      <TR>
	        <TD colspan="3">
	          <INPUT name=searchWord id=searchWord type=text size="40"> 
	          <INPUT id=doSearch type=submit value=search > 
	        </TD>
	      </TR>
	    </TBODY>
	  </TABLE>
	  </FORM>
	</div>
 <TABLE class="result" width="647" height="213" border="1">
	  <TBODY>
	  <tr>
	  <td class="title">题名</td>
	  
	  <td class="title">作者列表</td>
	  <td class="title">出版社</td>
	  </tr>
	  <%
	    List searchResult = (List)request.getAttribute("searchResult");
	    int resultCount = 0;
	    if(null != searchResult){
	    	resultCount = searchResult.size();
	    }
	    String singal="yes";
	  
	    for(int i = 0; i < resultCount; i++){
	    	Doctxt arts = (Doctxt)searchResult.get(i);
	    	String path = arts.getPmid();
	    	String Title=arts.getTitle();
	    	String Author=arts.getAuthor();
	    	String Journal=arts.getJournal();
	  %>
	  <TR>
	  	<TD class="title"><h4><%=Title %></h4></TD>
	  	<TD class="title"><h4><%=Author %></h4></TD>
	  	<TD class="title"><h4><%=Journal %></h4></TD>
	    <TD class="title"><h4><A href="<%="ftp://192.168.100.53/"+path+".pdf"%>" target="_blank">下载</A></h4></TD>
	  </TR>
	  <tr><td></td></tr>
	  <%
	    }
	  %>
	  <TR>
	  <TD class="title"><h4>共找到<%=resultCount %>条相关记录</h4></TD>
	  </TR>
	  
	</TBODY>
 	</TABLE>
	</CENTER>
	</BODY></HTML>