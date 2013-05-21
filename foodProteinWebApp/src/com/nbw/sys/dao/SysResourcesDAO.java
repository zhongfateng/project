package com.nbw.sys.dao;

import java.util.List;

import com.nbw.common.GenericDAO;
import com.nbw.sys.domain.SysResources;

/**
 * 
 * SysCodesDAO
 * 
 * @author 狄巨礼 May 17, 2010 09:31:00 AM
 * 
 */
public class SysResourcesDAO extends GenericDAO<SysResources, String> {

	/**
	 * 通过ID得到所有的下级节点
	 * @param ID
	 * @return List
	 */
	public List<SysResources> findChildByID(String id) {
		String hql = "select m from SysResources m where m.parentId = ? ";
		return (List<SysResources>) this.findByHQL(hql, id);
	}
	
	/**
	 * 通过ID得到NAME
	 * @param ID
	 * @return String
	 */
	public String findNameByID(String id) {
		SysResources obj = this.findByID(id);
		return obj.getName();
	}

}


