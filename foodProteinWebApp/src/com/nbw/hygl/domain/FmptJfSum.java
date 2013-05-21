package com.nbw.hygl.domain;

public class FmptJfSum {
	
	private String id;
	private String username;
	private int jfsum;
	private String trname;
	private String userid;
	
	
	public FmptJfSum(String id, String username, int jfsum, String trname,
			String userid) {
		super();
		this.id = id;
		this.username = username;
		this.jfsum = jfsum;
		this.trname = trname;
		this.userid = userid;
	}

	public FmptJfSum() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getJfsum() {
		return jfsum;
	}
	public void setJfsum(int jfsum) {
		this.jfsum = jfsum;
	}
	public String getTrname() {
		return trname;
	}
	public void setTrname(String trname) {
		this.trname = trname;
	}
	

}
