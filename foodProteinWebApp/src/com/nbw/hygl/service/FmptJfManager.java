package com.nbw.hygl.service;

import java.util.List;

import com.nbw.common.util.PageBean;
import com.nbw.hygl.dao.FmptJfDAO;
import com.nbw.hygl.domain.FmptJf;

public class FmptJfManager {
	
	
	
	private FmptJfDAO fmptJfdao;
	
	
	
	public PageBean findByTime(int page,int pageSize,String name){
		
		return this.fmptJfdao.findByTime(page,pageSize,name);
	}
	
	public PageBean findByUploadAndDownloadTime(int page,int pageSize,String name,String fclick){
		
		return this.fmptJfdao.findByUploadAndDownloadTime(page,pageSize,name,fclick);
	}

	
	
	public int findSumSorce(String name){
		
		return this.fmptJfdao.findSumSorce(name);
	}
	public int findSumSorceByUserId(String userid)
	{
		
		return this.fmptJfdao.findSumSorceByUserId(userid);
	}
	
	
	/**
	 * 通过id得到对象
	 *
	 * @param  id
	 * @return pObject
	 */
	public FmptJf findById(String id){
		if(id==null||"".equals(id)){
		   return null;
		}else{
			FmptJf pObject = fmptJfdao.findByID(id);
		   return pObject;
		}		
	}
	
	/**
	 * 获得该对象所有数据的列表
	 * 
	 * @return list
	 */
	public List getAll(){
		return fmptJfdao.findAll();
	}
	
    /**
	 * 保存对象
	 *
	 * @param  rna
	 */
	public void saveFmptJf(FmptJf rna) {
		fmptJfdao.save(rna);
	}


    /**
	 * 编辑对象
	 *
	 * @param  rna
	 */
	public void editFmptJf(FmptJf rna) {
		fmptJfdao.update(rna);
	}
	
	public void saveOrUpdate(FmptJf td){
		fmptJfdao.saveOrUpdate(td);
	}
	
	/**
	 * 删除对象
	 *
	 * @param  id
	 */
	public void deleteFmptJf(String id) {
		fmptJfdao.delete(id);
	}
	
	public void deleteFmptJf(FmptJf id) {
		fmptJfdao.delete(id);
	}





	public FmptJfDAO getFmptJfdao() {
		return fmptJfdao;
	}





	public void setFmptJfdao(FmptJfDAO fmptJfdao) {
		this.fmptJfdao = fmptJfdao;
	}
	
	
	

}
