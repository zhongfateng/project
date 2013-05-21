package com.nbw.sys.dao;

import java.util.List;

import com.nbw.sys.domain.SysRoles;
import com.nbw.sys.domain.SysRolesModules;
import com.nbw.sys.domain.SysRolesUsers;
import com.nbw.common.GenericDAO;

/**
 * 
 * SysRolesDAO
 * 
 * @author 狄巨礼 May 17, 2010 09:31:00 AM
 * 
 */
public class SysRolesDAO extends GenericDAO<SysRoles, String> {

	/**
	 * 绝对删除角色与用户的关系
	 * @param userId
	 */
	public void deleteSysRolesUsersByUserId(String userId) {
		String hql = "delete SysRolesUsers m where m.userId = ? ";
		this.executeHQL(hql, userId);
	}

	/**
	 * 绝对删除角色与用户的关系
	 * @param userId
	 */
	public void deleteSysRolesUsersByRoleId(String roleId) {
		String hql = "delete SysRolesUsers m where m.roleId = ? ";
		this.executeHQL(hql, roleId);
	}

	/**
	 *  绝对删除角色与模块功能的关系
	 * @param roleId
	 */
	public void deleteSysRolesModulesByRoleId(String roleId) {
		String hql = "delete SysRolesModules m where m.roleId = ? ";
		this.executeHQL(hql, roleId);
	}
	
	/**
	 * 保存或更新对象
	 * @param sru
	 */
	public void saveOrUpdateObject(Object obj) {
		this.getSession().saveOrUpdate(obj);
	}
	/**
	 * 根据userId 获取用户和角色对应对象的列表
	 * @param userId
	 * @return
	 */
	public List<SysRolesUsers> getSysRolesUsersByUserId(String userId) {
		String hql = " from SysRolesUsers m where m.userId = ? ";
		List list = this.findByHQL(hql, userId);
		return list;
	}

	/**
	 * 获取所有有效的角色
	 * @return
	 */
	public List<SysRoles> findAllSysRoles() {
		String hql = " from SysRoles m where m.validFlag !=  "
			+SysRoles.VALIDFLAG_FALSE+" order by m.sysCodesId ";
		List list = this.findByHQL(hql);
		return list;
	}
	
	/**
	 * 根据roleId 获取用户和角色对应对象的列表
	 * @param roleId
	 * @return
	 */
	public List<SysRolesUsers> getSysRolesUsersByRoleId(String roleId) {
		String hql = " from SysRolesUsers m where m.roleId = ? ";
		List list = this.findByHQL(hql, roleId);
		return list;
	}

	/**
	 * 根据roleId 获取角色和模块功能对应对象的列表
	 * @param roleId
	 * @return
	 */
	public List<SysRolesModules> getSysRolesModulesByRoleId(String roleId) {
		String hql = " from SysRolesModules m where m.roleId = ? ";
		List list = this.findByHQL(hql, roleId);
		return list;
	}

}


