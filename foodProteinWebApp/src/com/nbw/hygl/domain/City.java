package com.nbw.hygl.domain;

public class City {
	private Integer cityID; //城市的ID
	private String cityName;  //城市的真实名字
	 private Integer provincialID;
	public Integer getCityID() {
		return cityID;
	}
	public void setCityID(Integer cityID) {
		this.cityID = cityID;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public Integer getProvincialID() {
		return provincialID;
	}
	public void setProvincialID(Integer provincialID) {
		this.provincialID = provincialID;
		
	} 
	
	
	
}
