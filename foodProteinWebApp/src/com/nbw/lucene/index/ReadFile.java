package com.nbw.lucene.index;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.util.PDFTextStripper;


public class ReadFile {

	public static String readTxt(String path) {
        StringBuffer content = new StringBuffer("");// 文档内容
        try {
            FileReader reader = new FileReader(path);
            BufferedReader br = new BufferedReader(reader);
            String s1 = null;

            while ((s1 = br.readLine()) != null) {
                content.append(s1 + "\r");
            }
            br.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString().trim();
    }
	public static String readPdf(File f) throws Exception {
        StringBuffer content = new StringBuffer("");// 文档内容
        FileInputStream fis = new FileInputStream(f);
        PDFParser p = new PDFParser(fis);
       try
       {p.parse();
       PDFTextStripper ts = new PDFTextStripper();
       content.append(ts.getText(p.getPDDocument()));
       fis.close();
       p.getPDDocument().close();
       }
       
       
        catch(Exception e)
        {
        	
        	System.out.println(e.getMessage()+":"+f.getName().toString());
        	
        }
        
        
        return content.toString().trim();
    }
	public static String[] getMetedate(File f) throws IOException
	{
	//	COSDocument cosDocument =new COSDocument(f);
	//	PDDocument pDDocumnet =new PDDocument(cosDocument);
	//	PDDocumentInformation docInfo =PDDocumnet.load(f);
	//	PDDocument pDDocumnet=PDDocument.load(f);
	//	PDDocumentInformation docInfo=pDDocumnet.getDocumentInformation();
		PDDocument document=null;
		PDFParser parser = new PDFParser(new FileInputStream(f));  
		                parser.parse();  
		                document = parser.getPDDocument();  
		PDDocumentInformation docInfo= document.getDocumentInformation();

		String[] strs=new String[1];
		//strs[0] = docInfo.getAuthor();
		strs[0] = docInfo.getTitle();
		//strs[2] = docInfo.getKeywords();
		//strs[3] = docInfo.getSubject();
		return strs;
		
		
	}
	
	
}
