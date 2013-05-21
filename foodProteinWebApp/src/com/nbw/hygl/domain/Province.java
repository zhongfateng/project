package com.nbw.hygl.domain;

public class Province {
	private int provincialID; //ʡID
	private String provincialName; //ʡ����
	
	
	public Province() {
	}

	public Province(int provinceID, String provinceName){
		this.provincialID=provinceID;
		this.provincialName=provinceName;
	}

	public int getProvincialID() {
		return provincialID;
	}

	public void setProvincialID(int provinceID) {
		this.provincialID = provinceID;
	}

	public String getProvincialName() {
		return provincialName;
	}

	public void setProvincialName(String provinceName) {
		this.provincialName = provinceName;
	}
	
}