package com.nbw.hygl.service;

import java.util.ArrayList;
import java.util.List;

import com.nbw.hygl.dao.FmptJfSumDAO;
import com.nbw.hygl.domain.FmptJfSum;
import com.nbw.sys.dao.SysUsersDAO;
import com.nbw.sys.domain.SysUsers;

public class FmptJfSumManager {
	
	private FmptJfSumDAO fmptJfSumdao;
	private SysUsersDAO sysUsersDAO;
	
	
	public List<FmptJfSum> findByOrgid(String id){
		List<SysUsers> sysUsers = this.sysUsersDAO.findByProperty("sysOrganizationsId",id);
		List<FmptJfSum> fmptJfSum = new ArrayList<FmptJfSum>();
		for(SysUsers user:sysUsers){
			String username = user.getLoginCode();
			List<FmptJfSum> fjs = this.fmptJfSumdao.findByProperty("username",username);
			if(fjs.size()!=0){
				
				fmptJfSum.add(fjs.get(0));
			}
			
		}
		
		return fmptJfSum;
	}
	
	/**
	 * 通过id得到对象
	 *
	 * @param  id
	 * @return pObject
	 */
	public FmptJfSum findById(String id){
		if(id==null||"".equals(id)){
		   return null;
		}else{
			FmptJfSum pObject = fmptJfSumdao.findByID(id);
		   return pObject;
		}		
	}
	
	/**
	 * 获得该对象所有数据的列表
	 * 
	 * @return list
	 */
	public List getAll(){
		return fmptJfSumdao.findAll();
	}
	
    /**
	 * 保存对象
	 *
	 * @param  rna
	 */
	public void saveFmptJfSum(FmptJfSum rna) {
		fmptJfSumdao.save(rna);
	}


    /**
	 * 编辑对象
	 *
	 * @param  rna
	 */
	public void editFmptJfSum(FmptJfSum rna) {
		fmptJfSumdao.update(rna);
	}
	
	public void saveOrUpdate(FmptJfSum td){
		fmptJfSumdao.saveOrUpdate(td);
	}
	
	/**
	 * 删除对象
	 *
	 * @param  id
	 */
	public void deleteFmptJfSum(String id) {
		fmptJfSumdao.delete(id);
	}
	
	public void deleteFmptJfSum(FmptJfSum id) {
		fmptJfSumdao.delete(id);
	}

	public FmptJfSumDAO getFmptJfSumdao() {
		return fmptJfSumdao;
	}

	public void setFmptJfSumdao(FmptJfSumDAO fmptJfSumdao) {
		this.fmptJfSumdao = fmptJfSumdao;
	}

	public SysUsersDAO getSysUsersDAO() {
		return sysUsersDAO;
	}

	public void setSysUsersDAO(SysUsersDAO sysUsersDAO) {
		this.sysUsersDAO = sysUsersDAO;
	}
	
	

}
