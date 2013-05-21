package com.nbw.usage.domain;

import java.io.Serializable;

public class Usage implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2443731912415815292L;
	private int id;
	private String title;
	private String body;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	
	
	
}
