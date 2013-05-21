package com.nbw.sys.dao;

import java.util.List;

import com.nbw.common.Constants;
import com.nbw.common.GenericDAO;
import com.nbw.sys.domain.SysModuleActions;
import com.nbw.sys.domain.SysModules;
import com.nbw.sys.domain.SysRolesModules;

/**
 * 
 * SysModulesDAO
 * 
 * @author 狄巨礼 May 17, 2010 09:31:00 AM
 * 
 */
public class SysModulesDAO extends GenericDAO<SysModules, String> {

	/**
	 * 通过ID得到所有的下级节点
	 * 
	 * @param ID
	 * @return List
	 */
	public List<SysModules> findChildByID(String id) {
		String hql = "select m from SysModules m where m.parentId = ? and m.validFlag != "
				+ SysModules.VALIDFLAG_FALSE + " order by m.orderNum ";
		return (List<SysModules>) this.findByHQL(hql, id);
	}

	/**
	 * 获取id!=ROOT的所有模块
	 * 
	 * @return List
	 */
	public List<SysModules> findAllNotRootSysModules() {
		String hql = "select m from SysModules m where m.id = ? and m.validFlag != "
				+ SysModules.VALIDFLAG_FALSE + " order by m.orderNum ";
		return (List<SysModules>) this.findByHQL(hql, Constants.DEFAULT_ROOT);
	}

	/**
	 * 通过ID得到所有是菜单的下级节点
	 * 
	 * @param ID
	 * @return List
	 */
	public List<SysModules> findMenuChildByID(String id) {
		String hql = "select m from SysModules m where m.parentId = ? and m.validFlag != "
				+ SysModules.VALIDFLAG_FALSE
				+ " "
				+ " and m.isMenu != "
				+ SysModules.ISMENU_FALSE + " order by m.orderNum ";
		return (List<SysModules>) this.findByHQL(hql, id);
	}

	/**
	 * 通过ID得到NAME
	 * 
	 * @param ID
	 * @return String
	 */
	public String findNameByID(String id) {
		SysModules module = this.findByID(id);
		return module.getName();
	}

	/**
	 * 逻辑删除模块以及下面的所有子模块
	 * 
	 * @param ID
	 *            模块Id
	 * @return 是否成功
	 */
	public boolean deleteSysModules(String id) {
		String hql = "update SysModules m set m.validFlag = "
				+ SysModules.VALIDFLAG_FALSE + " where m.treeId like ?";
		boolean success = false;
		try {
			// 删掉子部门及自己
			this.executeHQL(hql, "%" + id + "%");
			success = true;
		} catch (RuntimeException re) {
			throw re;
		} finally {
			return success;
		}
	}

	/**
	 * 判断 SysModules.id!=id 模块编码不重复,更新时使用
	 * 
	 * @param id
	 * @param code
	 * @param codeId
	 * @return
	 */
	public boolean isCodeExist(String id, String code) {
		String hql = "select m from SysModules m where m.id<>? and m.code=? and m.validFlag != "
				+ SysModules.VALIDFLAG_FALSE;
		return this.findByHQL(hql, id, code).size() > 0;
	}

	/**
	 * 判断编码信息不重复 添加时使用
	 * 
	 * @param code
	 * @param codeId
	 * @return
	 */
	public boolean isCodeExist(String code) {
		String hql = "select m from SysModules m where  m.code=? and m.validFlag != "
				+ SysModules.VALIDFLAG_FALSE;
		return this.findByHQL(hql, code).size() > 0;
	}

	/**
	 * 获取该模块的操作
	 */
	public List<SysModuleActions> findSysModuleActionsByModuleID(String moduleId) {
		String hql = "select m from SysModuleActions m where m.moduleId = ? order by m.code";
		return (List<SysModuleActions>) this.findByHQL(hql, moduleId);
	}

	/**
	 * 获取所有模块的操作
	 */
	public List<SysModuleActions> findAllSysModuleActions() {
		String hql = "select m from SysModuleActions m ";
		return (List<SysModuleActions>) this.findByHQL(hql);
	}

	/**
	 * 根据上层模块ID，获取用户有权限的的模块列表
	 * 
	 * @param userId
	 * @param pid
	 *            pid==null则返回所有模块
	 * @return
	 */
	public List<SysModules> findSysModulesByUserID(String userId, String pid) {
		String hql = "select distinct sm from SysRolesModules rm,SysRolesUsers ru,SysModuleActions ra,SysModules sm "
				+ " where ((rm.roleId = ru.roleId and ru.userId=? and rm.moduleActionId = ra.id "
				+ " and ra.moduleId = sm.id ) or sm.linkType = "
				+ SysModules.LINK_TYPE_PUBLIC + " ) ";
		if (pid == null) {
			return (List<SysModules>) this.findByHQL(hql, userId);
		}
		hql += " and sm.parentId=? ";
		return (List<SysModules>) this.findByHQL(hql, userId, pid);
	}

	/**
	 * 根据上层模块ID，获取用户有权限的菜单模块列表
	 * 
	 * @param userId
	 * @param pid
	 *            pid==null则返回所有模块
	 * @return
	 */
	public List<SysModules> findMenuSysModulesByUserID(String userId, String pid) {
		String hql = "select distinct sm from SysRolesModules rm,SysRolesUsers ru,SysModuleActions ra,SysModules sm "
				+ " where ((rm.roleId = ru.roleId and ru.userId=? and rm.moduleActionId = ra.id "
				+ " and ra.moduleId = sm.id ) or sm.linkType = "
				+ SysModules.LINK_TYPE_PUBLIC
				+ " ) and sm.validFlag != "
				+ SysModules.VALIDFLAG_FALSE
				+ " and sm.isMenu != "
				+ SysModules.ISMENU_FALSE + "";
		if (pid == null) {
			hql += " order by sm.orderNum ";
			return (List<SysModules>) this.findByHQL(hql, userId);
		}
		hql += " and sm.parentId=?  order by sm.orderNum ";
		return (List<SysModules>) this.findByHQL(hql, userId, pid);
	}

	/**
	 * 获取用户有权限的下模块下的和公共的功能列表
	 * 
	 * @param userId
	 * @param moduleId
	 * @return
	 */
	public List<SysModuleActions> findSysModuleActionsByUserIDmoduleId(
			String userId, String moduleId) {
		String hql = "select distinct ra from SysRolesModules rm,SysRolesUsers ru,SysModuleActions ra "
				+ " where rm.roleId = ru.roleId and ru.userId=? and rm.moduleActionId = ra.id and ra.moduleId = ? ";
		return (List<SysModuleActions>) this.findByHQL(hql, userId, moduleId);
	}

	public void saveOrUpdateObject(Object obj) {
		this.getSession().saveOrUpdate(obj);
	}

	public void deleteObject(Object obj) {
		this.getSession().delete(obj);
	}

	/**
	 * 绝对删除模块的子功能的关系
	 * 
	 * @param moduleId
	 */
	public void deleteSysModuleActionsByModuleId(String moduleId) {
		String hql = "delete SysModuleActions ma where  ma.moduleId = ?";
		this.executeHQL(hql, moduleId);
	}

	/**
	 * 根据moduleId 获取角色和模块功能对应对象的列表
	 * 
	 * @param moduleId
	 * @return
	 */
	public List<SysRolesModules> getSysRolesModulesByModuleId(String moduleId) {
		String hql = "select m from SysRolesModules m,SysModuleActions ma where m.moduleActionId = ma.id and  ma.moduleId = ? ";
		List list = this.findByHQL(hql, moduleId);
		return list;
	}

}
