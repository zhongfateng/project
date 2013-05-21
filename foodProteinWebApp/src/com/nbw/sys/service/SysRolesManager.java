package  com.nbw.sys.service;

import java.util.List;

import com.nbw.common.dhtml.GridDataXml;
import com.nbw.common.util.CommonUtils;
import com.nbw.common.util.GUID;
import com.nbw.sys.dao.SysRolesDAO;
import com.nbw.sys.domain.SysRoles;
import com.nbw.sys.domain.SysRolesModules;
import com.nbw.sys.domain.SysRolesUsers;
import com.nbw.sys.domain.SysUsersDetail;

/**
 * 
 * SysRolesManager
 * 
 * @author 狄巨礼 May 17, 2010 09:31:00 AM
 * 
 */
public class SysRolesManager{

	private SysRolesDAO sysRolesDAO;

	/**
	 * 通过id得到对象
	 *
	 * @param  id
	 * @return pObject
	 */
	public SysRoles findByID(String id){
		return sysRolesDAO.findByID(id);
	}
	
	/**
	 * 获得该对象所有数据的列表
	 * 
	 * @return list
	 */
	public List<SysRoles> findAllSysRoles(){
		return sysRolesDAO.findAllSysRoles();
	}
	
  /**
	 * 保存对象
	 *
	 * @param  sysRoles
	 */
	public boolean save(SysRoles sysRoles) {
		return sysRolesDAO.save(sysRoles);
	}

  /**
	 * 更新对象
	 *
	 * @param  sysRoles
	 */
	public boolean update(SysRoles sysRoles) {
		return sysRolesDAO.update(sysRoles);
	}
	
	/**
	 * 删除对象SysRoles
	 *
	 * @param  id
	 */
	public boolean deleteSysRoles(String id) {
		SysRoles sr = sysRolesDAO.findByID(id);
		if(sr!=null){
			sr.setValidFlag(SysRoles.VALIDFLAG_FALSE);
			sysRolesDAO.update(sr);
			//删除关联的用户
			sysRolesDAO.deleteSysRolesUsersByRoleId(id);
			//删除关联的ModuleAction
			sysRolesDAO.deleteSysRolesModulesByRoleId(id);
			return true;
		}
		return false;
	}

	/*
	 * 重新设置用户所拥有的角色
	 * 先删除该用户对应的所有角色，再重新分配
	 * 
	 * roleIds 角色Id所组成的数据
	 */
	public void updateUserRoles(String userId, String[] roleIds) {
		sysRolesDAO.deleteSysRolesUsersByUserId(userId);
		if(roleIds != null){
			for(int i=0;i<roleIds.length;i++){
				SysRolesUsers sru = new SysRolesUsers();
				sru.setRoleId(roleIds[i]);
				sru.setUserId(userId);
				sru.setId(new GUID().toString());
				sysRolesDAO.saveOrUpdateObject(sru);
			}
		}
		
	}
	
	/** ******************************获取角色grid的xml开始******************************* */
	/**
	 * 加载dhtmlgrid需要的xml，内容是根据编码parentId获取的子编码
	 * 
	 * @param parentId
	 *            SysCodes父Id
	 * @return 返回DhtmlxGrid 接收的处理反馈结果（xml）
	 */
	public String getGridXML() {
		GridDataXml gridDataXml = new GridDataXml();
		//条件查询
		List<SysRoles> list = sysRolesDAO.findAllSysRoles();
		if (list != null) {
			for (SysRoles sysRoles : list) {
				gridDataXml.setId(sysRoles.getId());
				gridDataXml.setXmlData(CommonUtils.DealString(sysRoles.getSysCodesId()));
				gridDataXml.setXmlData(CommonUtils.DealString(sysRoles.getName()));
				gridDataXml.setXmlData(CommonUtils.DealString(sysRoles.getDescribe()));
				gridDataXml.setXmlData(CommonUtils.DealString(sysRoles.getRemarks()));
				gridDataXml.setXmlData(sysRoles.getSysRole());
			}
		}
		String xml = gridDataXml.getXml();
		return xml;
	}
	/** ******************************获取角色grid的xml结束******************************* */
		
	/**
	 * 根据userid 获取用户和角色对应对象的列表
	 */
	public List<SysRolesUsers> getSysRolesUsersByUserId(String userId) {
		return sysRolesDAO.getSysRolesUsersByUserId(userId);
	}
	
	/**
	 * 根据roleId 获取用户和角色对应对象的列表
	 * @param roleId
	 * @return
	 */
	public List<SysRolesUsers> getSysRolesUsersByRoleId(String roleId) {
		return sysRolesDAO.getSysRolesUsersByRoleId(roleId);
	}
	
	/**
	 * 根据roleId 获取角色和模块功能对应对象的列表
	 * @param roleId
	 * @return
	 */
	public List<SysRolesModules> getSysRolesModulesByRoleId(String roleId) {
		return sysRolesDAO.getSysRolesModulesByRoleId(roleId);
	}

	/**
	 * 重置角色和用户的关系（根据角色ID删除所有的SysUserRole,再根据RoleId，userIds添加SysUserRole）
	 * @param roleId
	 * @param userIds 
	 * @return
	 */
	public void updateRoleUsers(String roleId, String [] userIds) {
		sysRolesDAO.deleteSysRolesUsersByRoleId(roleId);
		if(userIds != null){
			for(int i=0;i<userIds.length;i++){
				if(userIds[i].trim().equals("")){
					continue;
				}
				SysRolesUsers sru = new SysRolesUsers();
				sru.setRoleId(roleId);
				sru.setUserId(userIds[i]);
				sru.setId(new GUID().toString());
				sysRolesDAO.saveOrUpdateObject(sru);
			}
		}
	}
	
	/**
	 * 重置角色和模块功能的关系（根据角色ID删除所有的SysRolesModules,再根据RoleId，mActionIds添加SysRolesModules）
	 * @param roleId
	 * @param mActionIds
	 */
	public void updateRoleMActions(String roleId, String[] mActionIds) {
		sysRolesDAO.deleteSysRolesModulesByRoleId(roleId);
		if(mActionIds != null){
			for(int i=0;i<mActionIds.length;i++){
				if(mActionIds[i].trim().equals("")){
					continue;
				}
				SysRolesModules srm = new SysRolesModules();
				srm.setRoleId(roleId);
				srm.setModuleActionId(mActionIds[i]);
				srm.setId(new GUID().toString());
				sysRolesDAO.saveOrUpdateObject(srm);
			}
		}
	}

	/**
	 * 删除多个对象
	 *
	 * @param  ids
	 */
	public boolean delete(String ids) {
		boolean success = false;
		try {
			String [] idarray = ids.split(",");
			for(int i=0;i<idarray.length;i++){
				success = deleteSysRoles(idarray[i]);
			}
		} catch (Exception re) {
			success = false;
			throw re;
		}finally{
			return success;
		}
	}

	//*************************************set and get
	public SysRolesDAO getSysRolesDAO() {
		return sysRolesDAO;
	}

	public void setSysRolesDAO(SysRolesDAO sysRolesDAO) {
		this.sysRolesDAO = sysRolesDAO;
	}
}

