package com.nbw.tupu.util;
import java.util.Comparator;


public class Point implements Cloneable{

	private double x;
	private double y;
	Point(double x,double y,boolean flag)
	{
		this.x=x;
		this.y=y;
		this.flag=flag;
	}
	private boolean flag=false;
	
	
	
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	Point()
	{}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public  double getDistance(Point p)
	{
		return Math.sqrt(Math.pow(p.getX(), 2)+Math.pow(p.getY(),2));
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		
		return super.clone();
	}
	
}
