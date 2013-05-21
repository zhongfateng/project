package com.nbw.testOrg.service;

import java.util.List;

import com.nbw.common.util.PageBean;
import com.nbw.testOrg.dao.TestOrgDAO;
import com.nbw.testOrg.domain.TestOrg;
public class TestOrgManager {
	
	
	private TestOrgDAO testOrgdao;
	
	
	
	public List<String> findByOrgAddr(){
		
		return this.testOrgdao.findByOrgAddr();
	}
	
	
	
	public PageBean findByNameAndAddr(String name,String addr,int page,int pageSize){
		return this.testOrgdao.findByNameAndAddr(name,addr,page,pageSize);
	}
	
	
	public TestOrg findByTid(String id){
		return this.testOrgdao.findByTid(id);
	}
	
	
	public List<TestOrg> findIndexFouth(){
		return this.testOrgdao.findIndexFouth();
	}
	
	
	
	
	/**
	 * 通过id得到对象
	 *
	 * @param  id
	 * @return pObject
	 */
	public TestOrg findById(String id){
		if(id==null||"".equals(id)){
		   return null;
		}else{
			TestOrg pObject = testOrgdao.findByID(id);
		   return pObject;
		}		
	}
	
	/**
	 * 获得该对象所有数据的列表
	 * 
	 * @return list
	 */
	public List getAll(){
		return testOrgdao.findAll();
	}
	
    /**
	 * 保存对象
	 *
	 * @param  rna
	 */
	public void saveTestOrg(TestOrg rna) {
		testOrgdao.save(rna);
	}


    /**
	 * 编辑对象
	 *
	 * @param  rna
	 */
	public void editTestOrg(TestOrg rna) {
		testOrgdao.update(rna);
	}
	
	/**
	 * 删除对象
	 *
	 * @param  id
	 */
	public void deleteTestOrg(String id) {
		testOrgdao.delete(id);
	}

	public TestOrgDAO getTestOrgdao() {
		return testOrgdao;
	}

	public void setTestOrgdao(TestOrgDAO testOrgdao) {
		this.testOrgdao = testOrgdao;
	}
	
	
	
	

}
