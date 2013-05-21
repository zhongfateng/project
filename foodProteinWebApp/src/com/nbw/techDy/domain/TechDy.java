package com.nbw.techDy.domain;

import java.sql.Timestamp;
import java.util.Date;

public class TechDy {
	
	
	private int tid;
	private String title;
	private Date ttime;
	private String tcontent;
	private String tsource;
	private int tsum;
	private String tauthor;
	
	
	
	public String getTauthor() {
		return tauthor;
	}
	public void setTauthor(String tauthor) {
		this.tauthor = tauthor;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	
	public Date getTtime() {
		return ttime;
	}
	public void setTtime(Date ttime) {
		this.ttime = ttime;
	}
	public String getTcontent() {
		return tcontent;
	}
	public void setTcontent(String tcontent) {
		this.tcontent = tcontent;
	}
	public String getTsource() {
		return tsource;
	}
	public void setTsource(String tsource) {
		this.tsource = tsource;
	}
	public int getTsum() {
		return tsum;
	}
	public void setTsum(int tsum) {
		this.tsum = tsum;
	}
	
}
