package com.nbw.sys.domain;

import java.util.Set;

/**
 * SysRoles entity. 
 * @author 狄巨礼 May 17, 2010 09:31:00 AM
 */
public class SysRoles extends AbstractSysRoles implements java.io.Serializable {
	public SysRoles(String id, String sysCodesId, String name, String describe,
			Integer sysRole, Integer validFlag, String remarks) {
		super(id, sysCodesId, name, describe, sysRole, validFlag, remarks);
		// TODO Auto-generated constructor stub
	}

	public static Integer VALIDFLAG_TRUE = 0;
	public static Integer VALIDFLAG_FALSE = -1;

	// Constructors

	/** default constructor */
	public SysRoles() {
	}

	/** minimal constructor */
	public SysRoles(String id, String sysCodesId, String name) {
		super(id, sysCodesId, name);
	}


}
