package com.nbw.zhipu.util;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.XMLWriter;


public class ConvertFromListToxml {

	public static void main(String[] args) throws Exception {
		
		
		
		
		
		
		
		
		  StringBuffer sb = new StringBuffer();
			  
			 sb.append("<graph caption='Monthly Unit Sales' xAxisName='Month' yAxisName='Units' showNames='1' decimalPrecision='0' formatNumberScale='0'>");

			  for(int i=0;i<10;i++)
			  {
				  sb.append("<set name='Jan' value='"+i+"' color='AFD8F8' />");
				  
				  
			  }
	          
	           sb.append("</graph>");
	           
	           
	           String xmlStr=sb.toString();
		 Document document= DocumentHelper.parseText(xmlStr);
		 XMLWriter writer = new XMLWriter(new FileWriter(new File("D:/stt.xml")));
         writer.write(document);
         writer.close();
		  
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
