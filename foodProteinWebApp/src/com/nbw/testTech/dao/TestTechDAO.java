package com.nbw.testTech.dao;

import java.util.ArrayList;
import java.util.List;

import com.nbw.common.GenericDAO;
import com.nbw.common.util.PageBean;
import com.nbw.testTech.domain.TestTech;

public class TestTechDAO extends GenericDAO<TestTech, String>{
	
	
	/**
	 * 
	 * @return 查找所有的方法名
	 */
	public List<String> findAllCname(){
		
		List<String> cnameList = new ArrayList<String>();
		
		String hql = "select t.cname from TestTech t group by t.cname";
		cnameList = (List<String>) findByHQL(hql, null);
		return cnameList;
	}
	
	
	public TestTech findByCname(String name){
		
		String hql = "select t from TestTech t where t.cname = '"+name+"'";
		TestTech testTech = (TestTech) (findByHQL(hql, null).get(0));
		return testTech;
	}
	

}
