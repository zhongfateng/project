package com.nbw.sys.domain;

/**
 * AbstractSysRolesUsers entity provides the base persistence definition of the
 * SysRolesUsers entity.
 * @author 狄巨礼 May 17, 2010 09:31:00 AM
 */

public abstract class AbstractSysRolesUsers implements java.io.Serializable {

	// Fields
	private String id;
	
	private String roleId;
	
	private String userId;

	public AbstractSysRolesUsers(){};
	public AbstractSysRolesUsers(String roleId, String userId) {
		super();
		this.roleId = roleId;
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	

}