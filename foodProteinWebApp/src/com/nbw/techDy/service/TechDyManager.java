package com.nbw.techDy.service;

import java.util.List;

import com.nbw.common.util.PageBean;
import com.nbw.techDy.dao.TechDyDAO;
import com.nbw.techDy.domain.TechDy;

public class TechDyManager {
	
	private TechDyDAO techDydao;
	
	
	public PageBean findByTime(int page ,int pageSize){
		
		return this.techDydao.findByTime(page,pageSize);
	}
	
	
	
	public TechDy findByTid(String id){
		
		return this.techDydao.findByTid(id);
	}
	
	
	
	
	
	
	/**
	 * 通过id得到对象
	 *
	 * @param  id
	 * @return pObject
	 */
	public TechDy findById(String id){
		if(id==null||"".equals(id)){
		   return null;
		}else{
			TechDy pObject = techDydao.findByID(id);
		   return pObject;
		}		
	}
	
	/**
	 * 获得该对象所有数据的列表
	 * 
	 * @return list
	 */
	public List getAll(){
		return techDydao.findAll();
	}
	
    /**
	 * 保存对象
	 *
	 * @param  rna
	 */
	public void saveTechDy(TechDy rna) {
		techDydao.save(rna);
	}


    /**
	 * 编辑对象
	 *
	 * @param  rna
	 */
	public void editTechDy(TechDy rna) {
		techDydao.update(rna);
	}
	
	public void saveOrUpdate(TechDy td){
		techDydao.saveOrUpdate(td);
	}
	
	/**
	 * 删除对象
	 *
	 * @param  id
	 */
	public void deleteTechDy(String id) {
		techDydao.delete(id);
	}
	
	public void deleteTechDy(TechDy id) {
		techDydao.delete(id);
	}

	public TechDyDAO getTechDydao() {
		return techDydao;
	}

	public void setTechDydao(TechDyDAO techDydao) {
		this.techDydao = techDydao;
	}

	

}
