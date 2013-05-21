package com.nbw.hygl.domain;

import java.util.Date;



public class Record {

	private String ID;
	private Integer type;
	private String userID;
	private String title;
	private Integer jf;
	private Date date;
	public String getID() {
		return ID;
	}
	public void setID(String id) {
		ID = id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getJf() {
		return jf;
	}
	public void setJf(Integer jf) {
		this.jf = jf;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	
	
	
	
	
	
	
}
