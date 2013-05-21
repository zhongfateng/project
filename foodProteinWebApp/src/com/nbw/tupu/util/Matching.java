package com.nbw.tupu.util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Matching {

	
	public static  List<List<Point>> getAllList(List<Point> list ,List<Point> listone) throws CloneNotSupportedException
	{
		List<List<Point>>  listAll=new ArrayList<List<Point>>();
		List<Point>  closelist=new ArrayList<Point>();
		List<Point>   anotherlist=new ArrayList<Point>();
		
		
		for(int i=0;i<list.size();i++)
		{
			Point p1=list.get(i);
			Point p=null;
			for(int j=0;j<listone.size();j++)
			{
				Point p2=listone.get(j);
				if(Math.abs(p1.getX()-p2.getX())<=3.0&&!(p2.isFlag()))
				{
					p2.setFlag(true);
					p=p2;
					//p= (Point)p2.clone();
					break;
				}
				else
				{
					continue;
				}
				
			}
			if(p!=null)
			{
			closelist.add(p1);
			anotherlist.add(p);
			}
			else
			{
				continue;
				
			}
			
		}
		List<Point> listCopy=new ArrayList<Point>();
		List<Point> listoneCopy =new ArrayList<Point>();
		for(Point p:list)
		{
		Point p1=	(Point)p.clone();
			listCopy.add(p1);
			
		}
		for(Point p: listone)
		{
				Point p1=(Point)p.clone();
			listoneCopy.add(p1);
			
		}
		int n =listoneCopy.size();
		//		Collections.copy(listCopy, list);
//		Collections.copy(listoneCopy, listone);
		list.removeAll(closelist);
		for(Point p:list)
		{
			p.setY(0);
			listoneCopy.add(p);
			
		}
		listone.removeAll(anotherlist);
		int m=listone.size();
		for(Point p:listone)
		{
			p.setY(0);
			listCopy.add(p);
			
		}
		
//		list.removeAll(closelist);
//		for(Point p:list)
//		{
//			p.setY(0);
//			anotherlist.add(p);
//		}
//		
//		
//		
//		listone.removeAll(anotherlist);
//		
//		for(Point p:listone)
//		{
//			p.setY(0);
//			closelist.add(p);
//		}
//		
		
//		listAll.add(closelist);
//		listAll.add(anotherlist);
		
		listAll.add(listCopy);
		listAll.add(listoneCopy);
		
		return listAll; 
		
	}
	
	
	
}
