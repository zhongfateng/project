package com.nbw.record.domain;

import java.util.Date;







public class Record {

	private String ID;
	private Integer type;
	private String userName;
	private String title;
	private Integer jf;
	private  Date date;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
