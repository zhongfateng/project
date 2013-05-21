/**
 * Program  : Item.java
 * Author   : zhongwf
 * Create   : 2012-11-10 ����10:22:53
 *
 * Copyright 2008 by beeagle Technologies Ltd.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of beeagle Technologies Ltd.("Confidential Information").  
 * You shall not disclose such Confidential Information and shall 
 * use it only in accordance with the terms of the license agreement 
 * you entered into with beeagle Technologies Ltd.
 *
 */

package com.nbw.tupu.domain;

/**
 * 
 * @author zhongwf
 * @version 1.0.0
 * @2012-11-10 ����10:22:53
 */
public class Item {

	private String name;
	private String jsonUrl;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJsonUrl() {
		return jsonUrl;
	}
	public void setJsonUrl(String jsonUrl) {
		this.jsonUrl = jsonUrl;
	}
	public String toString(){
		return "name="+this.getName()+",url="+this.getJsonUrl();
	}

	
}
