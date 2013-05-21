package com.nbw.tupu.util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;


public class Tiqulist {

	
 public static Double getXiangShiDu(String str1,String str2) throws Exception {
	 
	 
	 List<Point> ls=ExtractPoint.getList(str1);
	 List<Point> lsone=ExtractPoint.getList(str2);
	 
//	 for(Point p:ls)
//	 {
//		 System.out.println(p.getX()+"++++"+p.getY());
//	 }
	// System.out.println("############");
//	 for(Point p:lsone)
//	 {
//		 System.out.println(p.getX()+"+++"+p.getY());
//		 
//	 }
//	 System.out.println("before的+++++++++++++++++++++++++++++++++++++++++++");
	 
//	TreeSet<Point>  ts=new TreeSet<Point>(new Comparatorer());
//	
//	List<Point> listd=new ArrayList<Point>();
	// List<Point>  list=new ArrayList<Point>();
//	 for(int i=0;i<36;i++)
//	 {
//		 System.out.println(ls.get(i).getX()+"::"+ls.get(i).getY());
//		 
//		 
//	 }
//	 	System.out.println(ls.size());
// 		for(int i=1;i<ls.size();i++)
//		{
//			if(ls.get(i).getY()-ls.get(i-1).getY()>0&&ls.get(i).getY()-ls.get(i+1).getY()>0)
//			{
//				listd.add(ls.get(i));
//			}
//		}
// 		for(Point p:list)
// 		{
// 			System.out.println(p.getX()+":::"+p.getY());
// 			
// 			
// 		}
 	
// 		Iterator<Point> itr=ts.iterator();
// 			List listone=new ArrayList<Point>();
// 		for(j=0;j<100;j++)
// 		{
// 			itr.next()
// 			
// 			
// 			
// 		}
//	 
// 		Collections.sort(listd, new Comparator<Point>(){
//
//			public int compare(Point o1, Point o2) {
//				
//				return (int)(o2.getY()-o1.getY());
//			}
// 		});
 		
 		
 		
// 		
// 		System.out.println(listd.get(0).getY());
// 		
// 		
// 		
// 		
// 		
// 		List<Point> list100=new ArrayList<Point>();
// 		
// 		
//	 for(int i=0;i<100;i++)
//	 {
//		 
//		 list100.add(listd.get(i));
//		 
//		 
//	 }
//	 
//	 
//	 for(Point p:ls)
//	 {
//		 System.out.println(p.getX()+":::"+p.getY());
//	 }
//	 System.out.println(ls.size());
//	 for(Point p:lsone)
//	 {
//		 
//		 System.out.println(p.getX()+":::"+p.getY());
//	 }
//	 
//	 
//	 System.out.println(lsone.size());
//	}
//	
	 
//	
	List<List<Point>> listAll= Matching.getAllList(ls, lsone);
	List<Point> list1=listAll.get(0);
	List<Point> list2=listAll.get(1);
	Collections.sort(list1, new Comparator<Point>(){

		public int compare(Point o1, Point o2) {
			// TODO Auto-generated method stub
			return (int)(o1.getX()-o2.getX());
		}
	});
	
	
//	for( Point p:list1 )
//	{
//		System.out.println(p.getX()+"::"+p.getY()+"++"+p.isFlag());
//	}
//	System.out.println("###################");
	Collections.sort(list2, new Comparator<Point>(){

		public int compare(Point o1, Point o2) {
			// TODO Auto-generated method stub
			return (int)(o1.getX()-o2.getX());
		}
	});
//	for( Point p:list2 )
//	{
//		System.out.println(p.getX()+"::"+p.getY());
//	}
//	
//	 Double m=Method.getScore(listAll);
//	 
//	 System.out.println(m);
	Double m=Method.getScore(list1, list2);
	return m; 
 } 
}
