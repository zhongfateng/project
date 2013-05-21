package com.nbw.hygl.domain;

import java.sql.Timestamp;

/**
 * AbstractFmptJf entity provides the base persistence definition of the FmptJf
 * entity.会员积分
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractFmptJf implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3922652526845423581L;
	private int id;
	private Integer jfcount;
	private String username;
	private Timestamp timestamp;
	private String note;
	private String ftype;
	private String userid;

	public AbstractFmptJf() {
		super();
		// TODO Auto-generated constructor stub
	}


	public AbstractFmptJf(int id, Integer jfcount, String username,
			Timestamp timestamp, String note, String ftype, String userid) {
		super();
		this.id = id;
		this.jfcount = jfcount;
		this.username = username;
		this.timestamp = timestamp;
		this.note = note;
		this.ftype = ftype;
		this.userid = userid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public Integer getJfcount() {
		return this.jfcount;
	}

	public void setJfcount(Integer jfcount) {
		this.jfcount = jfcount;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getFtype() {
		return ftype;
	}

	public void setFtype(String ftype) {
		this.ftype = ftype;
	}
	
	

}