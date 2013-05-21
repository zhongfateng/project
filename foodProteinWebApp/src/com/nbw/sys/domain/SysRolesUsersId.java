package com.nbw.sys.domain;

/**
 * SysRolesUsersId entity.
 * @author 狄巨礼 May 17, 2010 09:31:00 AM
 */
public class SysRolesUsersId extends AbstractSysRolesUsersId implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public SysRolesUsersId() {
	}

	/** full constructor */
	public SysRolesUsersId(SysRoles sysRoles, SysUsers sysUsers) {
		super(sysRoles, sysUsers);
	}

}
