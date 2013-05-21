package com.nbw.sys.dao;

import java.util.List;

import com.nbw.common.Constants;
import com.nbw.common.GenericDAO;
import com.nbw.sys.domain.SysOrganizations;
import com.nbw.sys.domain.SysOrganizationsDetail;

/**
 * 
 * @author 狄巨礼
 * 2010 Apr 29, 2010 5:08:35 PM
 */
public class SysOrganizationsDAO extends GenericDAO<SysOrganizations, String> {


	/**
	 * 获取所有的组织机构
	 * @return
	 */
	public List<SysOrganizations> findAllSysOrganizations() {
		String hql = " from SysOrganizations m where m.validFlag != "+SysOrganizations.VALIDFLAG_FALSE+" order by m.orderNum  ";
		return (List<SysOrganizations>)this.findByHQL(hql);	
	}
	
	/**
	 * 获取id!=ROOT的所有组织机构
	 * @return
	 */
	public List<SysOrganizations> findAllNotRootSysOrganizations() {
		String hql = " from SysOrganizations m where m.id != '"+Constants.DEFAULT_ROOT+"' and m.validFlag != "+SysOrganizations.VALIDFLAG_FALSE+" order by m.orderNum  ";
		return (List<SysOrganizations>)this.findByHQL(hql);	
	}
	
	/**
	 * 通过ID得到所有的下级节点
	 * 
	 * @param ID
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<SysOrganizations> findChildByID(String id) {
		String hql = "select m from SysOrganizations m where m.parentId = ? and m.validFlag != "+SysOrganizations.VALIDFLAG_FALSE+" order by m.orderNum  ";
		return (List<SysOrganizations>) this.findByHQL(hql, id);
	}
	
	/**
	 * 通过ID得到NAME
	 * 
	 * @param ID
	 * @return String
	 */
	public String findNameByID(String id) {
		SysOrganizations obj = this.findByID(id);
		if(obj==null){
			return "";
		}
		return obj.getName();
	}
	
	/**
	 * 逻辑删除组织机构以及下面的所有子机构
	 * @param ID 机构Id
	 * @return 是否成功
	 */
	@SuppressWarnings("finally")
	public boolean deleteSysOrganizations(String id) {
		String hql = "update SysOrganizations so set so.validFlag = "+SysOrganizations.VALIDFLAG_FALSE+" where so.id = ?";
			
		boolean success = false;
		try {
			//删掉子机构及自己
			this.executeHQL(hql,id);
			success = true;
		} catch (RuntimeException re) {
			throw re;
		}finally{
			return success;
		}
	}

	/**
	 * 判断 SysOrganizations.id!=id 模块编码不重复,更新时使用
	 * 
	 * @param id
	 * @param code
	 * @return
	 */
	public boolean isCodeExist(String id, String code) {
		String hql = "select m from SysOrganizations m where m.id<>? and m.code=? and m.validFlag != "+SysOrganizations.VALIDFLAG_FALSE;
		return this.findByHQL(hql,id,code).size()>0;
	}

	/**
	 * 判断编码信息不重复 添加时使用
	 * @param code
	 * @return
	 */
	public boolean isCodeExist(String code) {
		String hql = "select m from SysOrganizations m where  m.code=? and m.validFlag != "+SysOrganizations.VALIDFLAG_FALSE;
		return this.findByHQL(hql,code).size()>0;
	}

	/**
	 * 
	 * @param id
	 * @return

	 */
	public SysOrganizationsDetail getSysOrganizationsDetail(String id) {
		String hql = "from SysOrganizationsDetail m where m.id = ?";
		List list = findByHQL(hql, id);
		if(list.size() > 0){
			return (SysOrganizationsDetail)list.get(0);
		}
		return null;
	}

	public void saveOrUpdateObject(Object obj) {
		this.getSession().saveOrUpdate(obj);
	}
	
	/*
	 * 物理删除
	 */
	public void deleteObject(Object obj) {
		this.getSession().delete(obj);
	}

}


