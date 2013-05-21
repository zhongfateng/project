package com.nbw.zhipu.util;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		
		
	try {
		List<Point> list=	ExtractPoint.getList("D:/1-1.txt");
		
		System.out.println(list.get(2).getX()+":"+list.get(2).getY());
		ConvertFromListToXmlUtil.convert(list);
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	}

}
