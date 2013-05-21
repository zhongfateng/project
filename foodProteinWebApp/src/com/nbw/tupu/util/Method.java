package com.nbw.tupu.util;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Method {

	
	public static Double getScore(List<Point> list,List<Point> list1)
	{
//		
//		List<Point> list1=listAll.get(0);
//		List<Point> list2=listAll.get(1);
//		Collections.sort(list1, new Comparator<Point>(){
//
//			public int compare(Point o1, Point o2) {
//				// TODO Auto-generated method stub
//				return (int)(o1.getX()-o2.getX());
//			}
//
//	
//		});
//		Collections.sort(list2, new Comparator<Point>(){
//
//			public int compare(Point o1, Point o2) {
//				// TODO Auto-generated method stub
//				return (int)(o1.getX()-o2.getX());
//			}
//
//	
//		});
		
		
		
		
		
		Double sum=0.00;
		Double sum1=0.00;
		Double sum2=0.00;
		
		int m=list.size()<list1.size()?list.size():(list1.size());
		
		for(int i=0;i<m;i++)
		{
			
		sum=sum+(list.get(i).getY())*(list1.get(i).getY());
			
		sum1=sum1+Math.pow(list.get(i).getY(), 2);
		sum2=sum2+Math.pow(list1.get(i).getY(), 2);
			
			
			
			
		}
		
		
		double m1=Math.sqrt(sum1);
		double m2=Math.sqrt(sum2);
		
		
		
		
		return (Double)(sum/(m1*m2));
		
	}
	
	
	
	
}
