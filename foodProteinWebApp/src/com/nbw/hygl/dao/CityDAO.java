package com.nbw.hygl.dao;

import java.util.List;

import com.nbw.common.GenericDAO;
import com.nbw.hygl.domain.City;
import com.nbw.hygl.domain.Province;

public class CityDAO extends GenericDAO<City, String> {

	public List<City> getCityByPID(Integer provincialID) {
		String hql = "from City c where c.provincialID= " + provincialID;
		return (List<City>) this.findByHQL(hql);

	}
	public List<Province> getProvince() {
		String sql = "from Province";
		List<Province> list = (List<Province>) this.findByHQL(sql);
		return list;
	}
	
	public Province getProvinceByID(Integer id)
	{
		return (Province)this.getSession().get(Province.class, id);	
	}

}
