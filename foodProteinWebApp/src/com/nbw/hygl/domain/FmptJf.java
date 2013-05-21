package com.nbw.hygl.domain;

import java.sql.Timestamp;

/**
 * FmptJf entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class FmptJf extends AbstractFmptJf implements java.io.Serializable {

	// Constructors

	/**
	 * 
	 */
	private static final long serialVersionUID = -3651375620548405611L;



	/** default constructor */
	public FmptJf() {
	}



	public FmptJf(int id, Integer jfcount, String username,
			Timestamp timestamp, String note, String ftype, String userid) {
		super(id, jfcount, username, timestamp, note, ftype, userid);
		// TODO Auto-generated constructor stub
	}

}
