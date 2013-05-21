<%@ page language="java"  pageEncoding="utf-8"%>
<%@ page import="com.nbw.lucene.domain.Doctxt"%>
<html>
<head>
<title>??????</title>
<link href="${adminCssFile}" type="text/css" rel="stylesheet"> 
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath() %>/resources/component/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/validata.js"></script>
</head>
  
<body>
	<form action="doctxt.action?m=save" method="post">	
	  <table width="100%" height="30" border="0" cellpadding="0" cellspacing="0" >
        <tr class="tr_title">
          <td width="2%" background="${skinPath}images/manage/dh_bg2.gif">&nbsp;</td>
          <td background="${skinPath}images/manage/dh_bg2.gif">?????????:***</td>
        </tr>
      </table>
	  <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#ACD6FF">
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">did:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="did"  value="${doctxt.did}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">pmid:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="pmid"  value="${doctxt.pmid}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">firAuthor:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="firAuthor"  value="${doctxt.firAuthor}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">affiation:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="affiation"  value="${doctxt.affiation}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">author:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="author"  value="${doctxt.author}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">corporateAuthor:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="corporateAuthor"  value="${doctxt.corporateAuthor}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">authorFull:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="authorFull"  value="${doctxt.authorFull}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">endAuthor:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="endAuthor"  value="${doctxt.endAuthor}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">book:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="book"  value="${doctxt.book}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">isbn:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="isbn"  value="${doctxt.isbn}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">journal:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="journal"  value="${doctxt.journal}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">issue:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="issue"  value="${doctxt.issue}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">completionDate:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="completionDate"  value="${doctxt.completionDate}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">createDate:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="createDate"  value="${doctxt.createDate}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">entrezDate:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="entrezDate"  value="${doctxt.entrezDate}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">meShdate:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="meShdate"  value="${doctxt.meShdate}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">modificationDate:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="modificationDate"  value="${doctxt.modificationDate}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">publicationDate:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="publicationDate"  value="${doctxt.publicationDate}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">rnNumber:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="rnNumber"  value="${doctxt.rnNumber}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">editor:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="editor"  value="${doctxt.editor}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">filter:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="filter"  value="${doctxt.filter}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">grantNumber:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="grantNumber"  value="${doctxt.grantNumber}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">investigator:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="investigator"  value="${doctxt.investigator}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">investigatorFull:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="investigatorFull"  value="${doctxt.investigatorFull}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">language:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="language"  value="${doctxt.language}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">contry:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="contry"  value="${doctxt.contry}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">locationId:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="locationId"  value="${doctxt.locationId}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">meShMajorTopic:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="meShMajorTopic"  value="${doctxt.meShMajorTopic}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">meShSubheading:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="meShSubheading"  value="${doctxt.meShSubheading}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">meShTerms:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="meShTerms"  value="${doctxt.meShTerms}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">pagination:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="pagination"  value="${doctxt.pagination}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">pharmacologicalAction:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="pharmacologicalAction"  value="${doctxt.pharmacologicalAction}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">publicationType:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="publicationType"  value="${doctxt.publicationType}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">publisher:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="publisher"  value="${doctxt.publisher}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">secondarySourceId:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="secondarySourceId"  value="${doctxt.secondarySourceId}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">supplementaryConcept:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="supplementaryConcept"  value="${doctxt.supplementaryConcept}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">textWord:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="textWord"  value="${doctxt.textWord}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">title:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="title"  value="${doctxt.title}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">transliteratedTitle:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="transliteratedTitle"  value="${doctxt.transliteratedTitle}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">abstract_:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="abstract_"  value="${doctxt.abstract_}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">volume:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="volume"  value="${doctxt.volume}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">substanceName:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="substanceName"  value="${doctxt.substanceName}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">issn:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="issn"  value="${doctxt.issn}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">cn:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="cn"  value="${doctxt.cn}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">keyWord:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="keyWord"  value="${doctxt.keyWord}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">subjectTerm:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="subjectTerm"  value="${doctxt.subjectTerm}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">featureWord:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="featureWord"  value="${doctxt.featureWord}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">apec:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="apec"  value="${doctxt.apec}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">textFull:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="textFull"  value="${doctxt.textFull}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">gid:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="gid"  value="${doctxt.gid}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">gname:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="gname"  value="${doctxt.gname}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">speid:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="speid"  value="${doctxt.speid}" size="15" />
				 </td>
          </tr>
          <tr>
             <td width="30%" bgcolor="#8EC7FF" align="center">speName:</td>
				 <td height="30" bgcolor="#FFFFFF">
				 	<input type="text"  name="speName"  value="${doctxt.speName}" size="15" />
				 </td>
          </tr>
	  <tr>
	    <td height="40" colspan="2" align="center" bgcolor="#FFFFFF">
	      <input type="submit" value="???" class="buttonF" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	      <input type="button" value="???" class="buttonF" onClick="javascript:history.back()" />
	    </td>
	    </tr>
	  </table>	
	</form>
</body>
</html>
