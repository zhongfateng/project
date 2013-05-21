package com.nbw.testTech.service;

import java.util.List;

import com.nbw.common.util.PageBean;
import com.nbw.testTech.dao.TestTechDAO;
import com.nbw.testTech.domain.TestTech;

public class TestTechManager {
	
	
	private TestTechDAO testTechdao;
	
	
	
	
	/**
	 * 
	 * @return select选项的value值
	 */
	public List<String> findAllCname(){
		
		return this.testTechdao.findAllCname();
	}
	
	
	public TestTech findByCname(String name){
		return this.testTechdao.findByCname(name);
	}
	
	
	/**
	 * 通过id得到对象
	 *
	 * @param  id
	 * @return pObject
	 */
	public TestTech findById(String id){
		if(id==null||"".equals(id)){
		   return null;
		}else{
			TestTech pObject = testTechdao.findByID(id);
		   return pObject;
		}		
	}
	
	/**
	 * 获得该对象所有数据的列表
	 * 
	 * @return list
	 */
	public List getAll(){
		return testTechdao.findAll();
	}
	
    /**
	 * 保存对象
	 *
	 * @param  rna
	 */
	public void saveTestTech(TestTech rna) {
		testTechdao.save(rna);
	}


    /**
	 * 编辑对象
	 *
	 * @param  rna
	 */
	public void editTestTech(TestTech rna) {
		testTechdao.update(rna);
	}
	
	/**
	 * 删除对象
	 *
	 * @param  id
	 */
	public void deleteTestTech(String id) {
		testTechdao.delete(id);
	}

	public TestTechDAO getTestTechdao() {
		return testTechdao;
	}

	public void setTestTechdao(TestTechDAO testTechdao) {
		this.testTechdao = testTechdao;
	}

}
