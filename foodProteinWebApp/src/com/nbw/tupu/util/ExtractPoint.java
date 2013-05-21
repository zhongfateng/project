package com.nbw.tupu.util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class ExtractPoint {
	public static List<Point> getList(String str) throws Exception {
		File f = new File(str);
		List<Point> ls = new ArrayList<Point>();
		InputStreamReader fr = new InputStreamReader(new FileInputStream(f),
				"UTF-8");
		BufferedReader br = new BufferedReader(fr);
		String st;
		while ((st = br.readLine()) != null) {
			Point p = new Point();
			String[] ds = st.split(" ");
			p.setX(Double.parseDouble(ds[0]));
			p.setY(Double.parseDouble(ds[1]));
			ls.add(p);
		}
		
		List<Point> listd=new ArrayList<Point>();
		for(int i=1;i<ls.size()-1;i++)
		{
			if(ls.get(i).getY()-ls.get(i-1).getY()>0&&ls.get(i).getY()-ls.get(i+1).getY()>0)
			{
				listd.add(ls.get(i));
			}
		}
		Collections.sort(listd, new Comparator<Point>(){

			public int compare(Point o1, Point o2) {
				
				return (int)(o2.getY()-o1.getY());
			}
 		});
		List<Point> list100=new ArrayList<Point>();
		 for(int i=0;i<100;i++)
		 {
			 
			 list100.add(listd.get(i));
			 
			 
		 }
 		
		 Collections.sort(list100, new Comparator<Point>(){

				public int compare(Point o1, Point o2) {
					
					return (int)(o1.getX()-o2.getX());
				}
	 		});
		fr.close();
		return list100;
	}
	public static List<List<Point>> getAllList(String str) {

		BufferedReader br = null;
		List<List<Point>> lists = null;
		try {
			File f = new File(str);

			lists = new ArrayList<List<Point>>();

			br = new BufferedReader(new FileReader(f));
			String st;
			List<Point> ls = new ArrayList<Point>();

			while ((st = br.readLine()) != null) {
				if (!st.contains("***")) {
					Point p = new Point();
					String[] ds = st.split(" ");
					p.setX(Double.parseDouble(ds[0]));
					p.setY(Double.parseDouble(ds[1]));
					ls.add(p);

				} else {
					lists.add(ls);
					ls = new ArrayList<Point>();
				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {

					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return lists;
	}

	// public static Double getSimpleNumber(List<Point> ls,List<Point> ls1)
	// {
	// double sum=0;
	// double sum2=0;
	//		
	// for(int i=0;i<ls.size();i++)
	// {
	// Point p1=ls.get(i);
	// Point p2=ls1.get(i);
	// sum=sum+((ls.get(i).getDistance(p1))*(ls1.get(i).getDistance(p2)));
	//			
	// sum2=sum2+Math.pow(ls.get(i).getDistance(p1),
	// 2)*Math.pow(ls1.get(i).getDistance(p2), 2);
	//			
	// }
	//		
	// return sum/Math.sqrt(sum2);
	//		
	// }

	// public static Double getOulaNumber(List<Point> ls, List<Point> ls1)
	// {
	// double sum1=0;
	// for(int i=0;i<ls.size();i++)
	// {
	// Point p1=ls.get(i);
	// Point p2=ls1.get(i);
	// sum1=sum1+((Math.pow(ls.get(i).getX()-ls1.get(i).getX(),
	// 2))+Math.pow(ls.get(i).getY()-ls1.get(i).getY(), 2));
	// }
	// return Math.sqrt(sum1);
	//		
	//		
	//		
	//		
	// }
	// 得到相近点对应x的y相似度
	public static Double getNumericalSimilarity(List<Point> ls, List<Point> ls1) {
		double sum1 = 0;
		double j = (double) ((ls.size() - ls1.size() < 0) ? ls.size() : ls1
				.size());
		for (int i = 0; i < j; i++) {
			sum1 = sum1 + Math.abs(ls.get(i).getY() - ls1.get(i).getY());
		}
		return sum1 / j;
	}

	public static Double getException(List<Point> ls, List<Point> ls1) {
		double sum = 0;
		for (int i = 0; i < ls.size(); i++) {
			sum = sum + (ls.get(i).getY() - ls1.get(i).getY());
		}
		return sum / ls.size();
	}

	public static Double getDifference(List<Point> ls, List<Point> ls1) {
		double sum = 0;

		double j = (double) ((ls.size() - ls1.size() < 0) ? ls.size() : ls1
				.size()); 
		for (int i = 0; i < j; i++) {
			sum = sum
					+ Math.abs(ls.get(i).getY() - ls1.get(i).getY()
							- getException(ls, ls1));
		}
		return sum / j;

	}

	// 获取两原数据x相似的点，重构
	public static List<List<Point>> reConstructor(List<Point> ls,
			List<Point> ls1) {
		List<List<Point>> list = new ArrayList<List<Point>>();
		List<Point> list3 = new ArrayList<Point>();
		List<Point> list4 = new ArrayList<Point>();
		for (Point point1 : ls) {
			double x1 = point1.getX();
			for (Point point2 : ls1) {
				double x2 = point2.getX();
				if (Math.abs(x1 - x2) < 0.2) {
					list3.add(point1);
					list4.add(point2);
				}
			}
		}
		list.add(list3);
		list.add(list4);
		return list;
	}
}
