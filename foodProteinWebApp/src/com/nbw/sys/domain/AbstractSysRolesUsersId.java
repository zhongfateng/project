package com.nbw.sys.domain;

/**
 * AbstractSysRolesUsersId entity provides the base persistence definition of
 * the SysRolesUsersId entity.
 * @author 狄巨礼 May 17, 2010 09:31:00 AM
 */

public abstract class AbstractSysRolesUsersId implements java.io.Serializable {

	// Fields

	private SysRoles sysRoles;
	private SysUsers sysUsers;

	// Constructors

	/** default constructor */
	public AbstractSysRolesUsersId() {
	}

	/** full constructor */
	public AbstractSysRolesUsersId(SysRoles sysRoles, SysUsers sysUsers) {
		this.sysRoles = sysRoles;
		this.sysUsers = sysUsers;
	}

	// Property accessors

	public SysRoles getSysRoles() {
		return this.sysRoles;
	}

	public void setSysRoles(SysRoles sysRoles) {
		this.sysRoles = sysRoles;
	}

	public SysUsers getSysUsers() {
		return this.sysUsers;
	}

	public void setSysUsers(SysUsers sysUsers) {
		this.sysUsers = sysUsers;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractSysRolesUsersId))
			return false;
		AbstractSysRolesUsersId castOther = (AbstractSysRolesUsersId) other;

		return ((this.getSysRoles() == castOther.getSysRoles()) || (this
				.getSysRoles() != null
				&& castOther.getSysRoles() != null && this.getSysRoles()
				.equals(castOther.getSysRoles())))
				&& ((this.getSysUsers() == castOther.getSysUsers()) || (this
						.getSysUsers() != null
						&& castOther.getSysUsers() != null && this
						.getSysUsers().equals(castOther.getSysUsers())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getSysRoles() == null ? 0 : this.getSysRoles().hashCode());
		result = 37 * result
				+ (getSysUsers() == null ? 0 : this.getSysUsers().hashCode());
		return result;
	}

}