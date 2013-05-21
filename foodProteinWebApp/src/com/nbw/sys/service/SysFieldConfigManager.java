package  com.nbw.sys.service;

import java.util.List;

import com.nbw.common.Constants;
import com.nbw.sys.dao.SysFieldConfigDAO;
import com.nbw.sys.domain.SysOrganizationsDetailCol;
import com.nbw.sys.domain.SysUsersDetailCol;

/**
 * 
 * SysCodesManager
 * 
 * @author 狄巨礼 May 17, 2010 09:31:00 AM
 * 
 */
public class SysFieldConfigManager{

	private SysFieldConfigDAO sysFieldConfigDAO;

	/**
	 * 获取用户附属信息字段配置
	 * @return
	 */
	public List<SysUsersDetailCol> getSysUsersDetailCols() {
		return sysFieldConfigDAO.getSysUsersDetailCols();
	}

	/**
	 * 获取组织机构附属信息字段配置
	 * @return
	 */
	public List<SysOrganizationsDetailCol> getSysOrganizationsDetailCols() {
		return sysFieldConfigDAO.getSysOrganizationsDetailCols();
	}
	
	/**
	 * 获取用户的附属信息
	 * @param id
	 * @return
	 */
	public SysUsersDetailCol loadSysUsersDetailCol(String id) {
		return sysFieldConfigDAO.loadSysUsersDetailCol(id);
	}
	
	/**
	 * 更新用户附属字段的配置
	 * @param obj
	 */
	public void updateSysUsersDetailCol(SysUsersDetailCol obj) {
		sysFieldConfigDAO.update(obj);
		//加载配置到静态变量中
		Constants.userFieldConfig = sysFieldConfigDAO.loadUserConfig();
	}
	
	/**
	 * 获取组织机构的附属信息
	 * @param id
	 * @return
	 */
	public SysOrganizationsDetailCol loadSysOrganizationsDetailCol(String id) {
		return sysFieldConfigDAO.loadSysOrganizationsDetailCol(id);
	}
	
	/**
	 * 更新组织机构附属字段的配置
	 * @param obj
	 */
	public void updateSysOrganizationsDetailCol(SysOrganizationsDetailCol obj) {
		sysFieldConfigDAO.update(obj);
		//加载配置到静态变量中
		Constants.orgFieldConfig = sysFieldConfigDAO.loadOrgConfig();
	}

	public SysFieldConfigDAO getSysFieldConfigDAO() {
		return sysFieldConfigDAO;
	}

	public void setSysFieldConfigDAO(SysFieldConfigDAO sysFieldConfigDAO) {
		this.sysFieldConfigDAO = sysFieldConfigDAO;
	}
	
	public void init(){
		Constants.orgFieldConfig = sysFieldConfigDAO.loadOrgConfig();
		Constants.userFieldConfig = sysFieldConfigDAO.loadUserConfig();
	}

}


