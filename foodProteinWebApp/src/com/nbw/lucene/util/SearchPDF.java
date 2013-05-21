package com.nbw.lucene.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
public class SearchPDF {

		public static final String indexDir="d:\\downloadFile\\index";
		public static final String indexDirone="d:\\downloadFile\\chinesedocindex";
		
		public static Directory dir;
		
	    public static  List<String> search(String searchWord,boolean flag)throws Exception
	    {  
	    	if(flag)
	 
	    	
	    		dir	=FSDirectory.getDirectory(indexDir,false);
	    	else
	    	{
	    		dir=FSDirectory.getDirectory(indexDirone,false);
	    	}
	    	IndexSearcher is=new IndexSearcher(dir);
			QueryParser queryParse =new QueryParser("body",new StandardAnalyzer());
			Query q=queryParse.parse(searchWord);
			//通过给定的字段索引全文，获得相关的文档对象，
			Hits td=is.search(q);
			List<String> listString=new ArrayList<String>();
			for(int i=0;i<td.length();i++)
			{
				if(td.score(i)>0.2200)
			{
					listString .add(td.doc(i).get("name").replace(".pdf",""));
				}
			}
			is.close();
	    	return listString;
	    }
}