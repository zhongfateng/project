package com.nbw.testTech.domain;

import java.io.Serializable;

public class TestTech implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -579032709437669905L;
	private int tid;
	private String cname;
	private String ename;
	private String overview;
	private String theory;
	private String apply;
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public String getTheory() {
		return theory;
	}
	public void setTheory(String theory) {
		this.theory = theory;
	}
	public String getApply() {
		return apply;
	}
	public void setApply(String apply) {
		this.apply = apply;
	}
	
	
	

}
