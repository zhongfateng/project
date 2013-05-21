package com.nbw.lucene.util;



import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.nbw.lucene.domain.Doctxt;

public class ExtractXml {

	public static int parseXml(String filepath,String SpeciesName)
	{
		 SAXReader saxReader = null;
	        Document doc = null;
	        Element root =null;
	        Connection  conn=null;
	        File file1=null;
	       int m=0;
	        try {
	        	   saxReader = new SAXReader();
	        	   file1=new File(filepath);
		            doc = saxReader.read(file1);
		           root=doc.getRootElement();
		           
		        List<Element> list=   root.selectNodes("//PubmedArticle");
		        System.out.println(list.size());
		        conn=DBUtil.getConnection();
		           for(int j=0;j<list.size();j++)
		           {
		        	  Doctxt wx =new Doctxt();
		 	          wx.setSpeName(SpeciesName);
		 	          wx.setPmid(list.get(j).element("MedlineCitation").element("PMID").getText());
		 	          Element datacreated=list.get(j).element("MedlineCitation").element("DateCreated");
		 	          if(datacreated!=null)
		 	          {
		 	        	  wx.setCreateDate(datacreated.elementText("Year")+"/"+datacreated.elementText("Month")+"/"+datacreated.elementText("Day"));
		 	          }
		 	          else
		 	          {
		 	        	  wx.setCreateDate("");
		 	          }
		 	          Element  datacomplited =list.get(j).element("MedlineCitation").element("DateCompleted");
		 	         if(datacomplited!=null)
		 	        	 {
		 	        	 wx.setCompletionDate(datacomplited.elementText("Year")+"/"+datacomplited.elementText("Month")+"/"+datacomplited.elementText("Day"));
		 	        	 }
		 	         else
		 	         {
		 	        	 wx.setCompletionDate("");
		 	        	 
		 	         }
		 	        Element article= list.get(j).element("MedlineCitation").element("Article");
		 	        if(article!=null)
		 	        {
		 	        	if(article.element("Journal")!=null)
		 	        	
		 	        	{ 
		 	        		if(article.element("Journal").element("ISSN")!=null)
		 	        		{
		 	        		wx.setIssn(article.element("Journal").element("ISSN").getText());}
		 	        	}
		 	        }
		 	        
		 	        else
		 	        {
		 	        	wx.setIssn("");
		 	        }
		 	       
		 	        
		     wx.setVolume(article.element("Journal").element("JournalIssue").elementText("Volume"));
		 	        
		 	        wx.setIssue(article.element("Journal").element("JournalIssue").elementText("Issue"));
		 	        wx.setPublicationDate(article.element("Journal").element("JournalIssue").element("PubDate").elementText("Year"));
		 	   
		 	        wx.setJournal(article.element("Journal").element("Title").getText());
		 	      //  System.out.println(article.element("Journal").element("ISOAbbreviation").getText());
		 	        wx.setTitle(article.element("ArticleTitle").getText());
		 	        wx.setPagination(article.element("Pagination").element("MedlinePgn").getText()); 
		 	        Element abs=article.element("Abstract");
		 	        
		 	       if(abs!=null)
		 	    	   {
		 	    	   wx.setAbstract_(article.element("Abstract").elementText("AbstractText"));
		 	    	   }
		 	       
		 	       else
		 	       {
		 	    	   wx.setAbstract_("");
		 	    	   
		 	    	   
		 	       }
		 	        wx.setAffiation(article.elementText("Affiliation"));
		 	        
		 	        
		 	         Element authors= article.element("AuthorList");
		 	         
		 	         if(authors!=null)
		 	        	 
		 	         {
		 	        	 List<Element> list2=authors.elements("Author");
			 	         //System.out.println(list.size());
			 	         String austr="";
			 	         String austr1="";
			 	         
			 	         for(int i=0;i<list2.size();i++)
			 	         {
			 	        	 austr1=list2.get(i).elementText("LastName")+" "+list2.get(i).elementText("ForeName")+" "+list2.get(i).elementText("Initials");
			 	        	 austr=austr1+","+austr;
			 	         }
			 	         wx.setAuthorFull(austr);
		 	        	 
		 	        	 
		 	        	 
		 	         }
		 	         else
		 	         {
		 	        	 wx.setAuthorFull("");
		 	        	 
		 	         }
		 	        
		 	         
		 	         
//		 	         for(Element author:list)
//		 	         {
//		 	        	 System.out.println(author.elementText("LastName")+" "+author.elementText("ForeName")+" "+author.elementText("Initials"));
//		 	        	 
//		 	        	 
//		 	         }
		 	     //     System.out.println(article.elementText("Language"));
		 	          Element publicationtypelist= article.element("PublicationTypeList");
		 	          
		 	          
		 	          if(publicationtypelist!=null)
		 	          {
		 	        	  List<Element> pubs= publicationtypelist.elements("PublicationType");
		 		          String str ="";
		 		          for(int i=0;i<pubs.size();i++)
		 		          {
		 		        	str=str+pubs.get(i).getText(); 
		 		        
		 		          } 
		 		          wx.setPublicationType(str);
		 	          }
		 	          else
		 	          {
		 	        	  
		 	        	  wx.setPublicationType("");
		 	        	  
		 	          }        
		 	         
//		 	         Element articledate=article.element("ArticleDate");
//		 	         if(articledate!=null)
//		 	        	 {
//		 	        	 System.out.println(articledate.elementText("Year")+":"+articledate.elementText("Month")+":"+articledate.elementText("Day"));
//		 	        	 }
//		 	         else
//		 	         {
//		 	        	 
//		 	         }
//		 	          
//		 	        Element mji=  root.element("MedlineCitation").element("MedlineJournalInfo");
//		 	          
//		 	          
//		 	          System.out.println(mji.elementText("Country")+mji.elementText("MedlineTA")+mji.elementText("NlmUniqueID")+mji.elementText("ISSNLinking"));
		 	          
		 	          
		 	          List<Element> pubdate=list.get(j).selectNodes("//PubmedArticle/PubmedData/History/PubMedPubDate");
		 	          
		 	          wx.setEntrezDate(pubdate.get(pubdate.size()-1).elementText("Year")+"/"+pubdate.get(pubdate.size()-1).elementText("Month")+"/"+pubdate.get(pubdate.size()-1).elementText("Day"));
		 	          Element grantlist=list.get(j).element("MedlineCitation").element("Article").element("GrantList");
		 	          
		 	          if(grantlist!=null)
		 	          {
		 	        	  List<Element> gb =list.get(j).selectNodes("//PubmedArticle/MedlineCitation/Article/GrantList/Grant");
		 		          
		 		          
		 		          String gbstr="";
		 		          String gbstr1="";
		 		          for(int i=0;i<gb.size();i++)
		 		          {
		 		        	  
		 		        	  gbstr1=gb.get(i).elementText("GrantID")+"/"+gb.get(i).elementText("Acronym")+"/"+gb.get(i).elementText("Agency")+"/"+gb.get(i).elementText("Country");
		 		        	  gbstr=gbstr1+","+gbstr;
		 		        	  
		 		          } 
		 	        	  wx.setGrantNumber(gbstr);
		 	        	  
		 	          }
		 	          else
		 	          {
		 	        	  wx.setGrantNumber("");  
		 	          }
		 	          
//		 	          System.out.println(wx.getAbstract());
//		 	          
//		 	          	System.out.println(wx.getAffiation());
//		 	          	System.out.println(wx.getAuthor());
//		 	         	System.out.println(wx.getCompletionDate());  
//		 	          	System.out.println(wx.getCreateDate());
//		 	          	System.out.println(wx.getEntrezDate());
//		 	         	System.out.println(wx.getGrantNumber());
//		 	            System.out.println(wx.getISSN());
//		 	            System.out.println(wx.getIssue());
//		 	            System.out.println(wx.getJournal());
//		 	            System.out.println(wx.getPagination());
//		 	            System.out.println(wx.getPMID()) ; 
//		 	            System.out.println(wx.getPublicationDate());
//		 	            System.out.println(wx.getPublicationType());
//		 	            System.out.println(wx.getTitle());
//		 	            System.out.println(wx.getVolume());
		 	        //    List<Element> list=doc.selectNodes("//PubedArticle/MedlineCitation/PMID");
		 	          // System.out.println( doc.getRootElement().element("MedlineCitation").element("PMID"));
		 	        //  System.out.println(list.size());
		 				PreparedStatement ps= conn.prepareStatement("insert into doctxt(PMID,Affiation,Author_Full,Journal,Issue,PublicationDate,Pagination,PublicationType,Title,Abstract,Volume,ISSN,GrantNumber,CreateDate,CompletionDate,EntrezDate,speName) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		 				ps.setString(1,wx.getPmid());
		 				ps.setString(2, wx.getAffiation());
		 				ps.setString(3, wx.getAuthorFull());
		 				ps.setString(4, wx.getJournal());
		 				ps.setString(5, wx.getIssue());
		 				ps.setString(6, wx.getPublicationDate());
		 				ps.setString(7, wx.getPagination());
		 				ps.setString(8, wx.getPublicationType());
		 				ps.setString(9, wx.getTitle());
		 				ps.setString(10,wx.getAbstract_());
		 				ps.setString(11, wx.getVolume());
		 				ps.setString(12, wx.getIssn());
		 				ps.setString(13, wx.getGrantNumber());
		 				ps.setString(14, wx.getCreateDate());
		 				ps.setString(15,wx.getCompletionDate());
		 				ps.setString(16, wx.getEntrezDate());
		 				ps.setString(17, wx.getSpeName());
		 				ps.executeUpdate();
		 				m++;
		 				ps.close(); 
		           }
		       	conn.close();
		       	
	        } catch (DocumentException e) {
	        System.out.print(e.getMessage());
	        }
	        catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return m;
	        
	        
	       
	}
	
}
