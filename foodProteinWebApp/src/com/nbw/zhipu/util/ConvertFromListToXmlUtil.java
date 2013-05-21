package com.nbw.zhipu.util;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.XMLWriter;

public class ConvertFromListToXmlUtil {

	
	public static void  convert(List<Point> list,String str) throws Exception
	{
		
		
		StringBuffer sb =new StringBuffer();
		 sb.append("<graph caption='Monthly Sales Summary' subcaption='For the year 2004' xAxisName='Month' yAxisMinValue='15000' yAxisName='Sales' decimalPrecision='0' formatNumberScale='0' numberPrefix='$' showNames='1' showValues='0'  showAlternateHGridColor='1' AlternateHGridColor='ff5904' divLineColor='ff5904' divLineAlpha='20' alternateHGridAlpha='5' >");
		for(Point p :list)
		{
			sb.append( "<set name='"+p.getX()+"' value='"+p.getY()+"'/>");
		}
		 sb.append("</graph>");
		  		String xmlStr=sb.toString();
		  		Document document= DocumentHelper.parseText(xmlStr);
		  		XMLWriter writer = new XMLWriter(new FileWriter(new File(str)));
		  		writer.write(document);
		  		writer.close();
	
	}

	
	
	
	
	
	
	
}
