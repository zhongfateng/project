package com.nbw.zhipu.util;

public class Point {

	private double x;
	private double y;
	Point(double x,double y)
	{
		this.x=x;
		this.y=y;
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
	
	
	
	
}
