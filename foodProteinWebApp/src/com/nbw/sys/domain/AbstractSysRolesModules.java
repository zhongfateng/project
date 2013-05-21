package com.nbw.sys.domain;

/**
 * AbstractSysRolesModules entity provides the base persistence definition of
 * the SysRolesModules entity.
 * @author 狄巨礼 May 17, 2010 09:31:00 AM
 */

public abstract class AbstractSysRolesModules implements java.io.Serializable {

	// Fields

	private String id;
	private String roleId;
	private String moduleActionId;

	// Constructors

	public String getModuleActionId() {
		return moduleActionId;
	}

	public void setModuleActionId(String moduleActionId) {
		this.moduleActionId = moduleActionId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public AbstractSysRolesModules(String id, String roleId,
			String moduleActionId) {
		super();
		this.id = id;
		this.roleId = roleId;
		this.moduleActionId = moduleActionId;
	}

	public AbstractSysRolesModules(){}
	

}