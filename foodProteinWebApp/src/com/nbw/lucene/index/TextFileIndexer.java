package com.nbw.lucene.index;
import java.io.File;
import java.util.Date;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;

public class TextFileIndexer {   
    public static void main(String[] args) throws Exception {   
        /* 指明要索引文件夹的位置,这里是d盘的s文件夹下 */  
        File fileDir = new File("d:\\yuanwen");   
  
        /* 这里放索引文件的位置 */  
        File indexDir = new File("d:\\indexone");   
        Analyzer luceneAnalyzer = new StandardAnalyzer();   
        IndexWriter indexWriter = new IndexWriter(indexDir, luceneAnalyzer,   
                true);   
        File[] textFiles = fileDir.listFiles();   
        long startTime = new Date().getTime();   
           
        //增加document到索引去     
              //  System.out.println("File indexing");  
                
                /*
                 * 注意要变的就是这里，路径和读取文件的方法
                 * */
            	for (int i = 0; i < textFiles.length; i++) {
        			File f = textFiles[i];
        			if (!f.isDirectory() && !f.isHidden() && f.canRead()
        					&& AcceptFile(f)) {
        					String name=f.getName();
        				System.out.println(name);
        				   String temp=ReadFile.readPdf(f);
        			//	String[] strs=   ReadFile.getMetedate(f);
        	                Document document = new Document();   
        	                Field FieldPath = new Field("name",name, 
        	                        Field.Store.YES, Field.Index.NO);   
        	                Field FieldBody = new Field("body", temp, Field.Store.NO,   
        	                        Field.Index.TOKENIZED,   
        	                        Field.TermVector.WITH_POSITIONS_OFFSETS); 
        	             //   Field FieldAuthor=new Field("Author",strs[0],Field.Store.YES,Field.Index.TOKENIZED);
        	        //        Field FieldTitle =new Field("Title",strs[0],Field.Store.YES,Field.Index.TOKENIZED);
        	              //  Field FieldKeyWord=new Field("keywords",strs[2],Field.Store.YES,Field.Index.TOKENIZED);
        	                document.add(FieldPath);   
        	                document.add(FieldBody);   
//        	                document.add(FieldAuthor);
//       	                document.add(FieldTitle);
//        	                document.add(FieldKeyWord);
        	                indexWriter.addDocument(document);
        	               
        			}
        		}
            	
        //optimize()方法是对索引进行优化   
            	 indexWriter.optimize();   
                 indexWriter.close();  
           
        //测试一下索引的时间   
        long endTime = new Date().getTime();   
        System.out   
                .println("take"  
                        + (endTime - startTime)
                        + "millions"  
                        + fileDir.getPath());   
    }  
    
    protected static boolean AcceptFile(File f) {
		return f.getName().endsWith(".pdf");
	}
    
    
    
 }