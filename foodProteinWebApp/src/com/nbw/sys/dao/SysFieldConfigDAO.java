package com.nbw.sys.dao;

import java.util.LinkedList;
import java.util.List;

import com.nbw.common.GenericDAO;
import com.nbw.sys.domain.SysOrganizationsDetailCol;
import com.nbw.sys.domain.SysUsersDetailCol;

/**
 * 
 * SysModulesDAO
 * 
 * @author 狄巨礼 May 17, 2010 09:31:00 AM
 * 
 */
public class SysFieldConfigDAO extends GenericDAO<Object, String> {

	/**
	 * 获取用户附属信息字段配置
	 * @return
	 */
	public List<SysUsersDetailCol> getSysUsersDetailCols() {
		String hql = "from SysUsersDetailCol m order by m.dataSources" ;
		return (List<SysUsersDetailCol>)this.findByHQL(hql);
	}

	/**
	 * 获取组织机构附属信息字段配置
	 * @return
	 */
	public List<SysOrganizationsDetailCol> getSysOrganizationsDetailCols() {
		String hql = "from SysOrganizationsDetailCol m order by m.dataSources ";
		return (List<SysOrganizationsDetailCol>)this.findByHQL(hql);
	}

	/**
	 * 根据SysUserId获取用户的附属信息的字段配置
	 * @param id
	 * @return
	 */
	public SysUsersDetailCol loadSysUsersDetailCol(String id) {
		String hql = "from SysUsersDetailCol m where m.colId = ?";
		List list = this.findByHQL(hql,id);
		if(list.size()>0){
			return (SysUsersDetailCol)list.get(0);
		}
		return null;
		
	}

	/**
	 * 根据Id获取组织机构的附属信息的字段配置
	 * @param id
	 * @return
	 */
	public SysOrganizationsDetailCol loadSysOrganizationsDetailCol(String id) {
		String hql = "from SysOrganizationsDetailCol m where m.colId = ?";
		List list = this.findByHQL(hql,id);
		if(list.size()>0){
			return (SysOrganizationsDetailCol)list.get(0);
		}
		return null;
	}

	/**
	 * 加载组织机构的附属信息的有效字段配置（可用字段）
	 * @return
	 */
	public List<SysOrganizationsDetailCol> loadOrgConfig() {
		String hql = "from SysOrganizationsDetailCol m where m.enabled = ? order by m.orderNum ";
		List list = this.findByHQL(hql,SysOrganizationsDetailCol.ENABLE_TRUE);
		return list;
	}

	/**
	 * 加载用户的附属信息的有效字段配置（可用字段）
	 * @return
	 */
	public List<SysUsersDetailCol> loadUserConfig() {
		String hql = "from SysUsersDetailCol m where m.enabled = ? order by m.orderNum ";
		List list = this.findByHQL(hql,SysUsersDetailCol.ENABLE_TRUE);
		return list;
	}

}


