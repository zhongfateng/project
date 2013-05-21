package com.nbw.hygl.service;

import java.util.List;

import com.nbw.hygl.dao.CityDAO;
import com.nbw.hygl.domain.City;
import com.nbw.hygl.domain.Province;

public class CityManager {

	private CityDAO getCityDAO;

	public CityDAO getGetCityDAO() {
		return getCityDAO;
	}

	public void setGetCityDAO(CityDAO getCityDAO) {
		this.getCityDAO = getCityDAO;
	}

	public List<City> getCityByPID(Integer provincialID) {
		return this.getCityDAO.getCityByPID(provincialID);
	}
	
	public List<Province> getProvince(){
		return this.getCityDAO.getProvince();
	}
	public Province getProvinceByID(Integer id){
		return this.getCityDAO.getProvinceByID(id);
	}

}
