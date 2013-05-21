package com.nbw.sys.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractSysRoles entity provides the base persistence definition of the
 * SysRoles entity. 
 * @author 狄巨礼 May 17, 2010 09:31:00 AM
 */

public abstract class AbstractSysRoles implements java.io.Serializable {

	// Fields

	private String id;
	private String sysCodesId;
	private String name;
	private String describe;
	private Integer sysRole;
	private Integer validFlag;
	private String remarks;

	// Constructors

	/** default constructor */
	public AbstractSysRoles() {
	}

	/** minimal constructor */
	public AbstractSysRoles(String id, String sysCodesId, String name) {
		this.id = id;
		this.sysCodesId = sysCodesId;
		this.name = name;
	}

	/** full constructor */
	public AbstractSysRoles(String id, String sysCodesId, String name,
			String describe, Integer sysRole, Integer validFlag,
			String remarks) {
		this.id = id;
		this.sysCodesId = sysCodesId;
		this.name = name;
		this.describe = describe;
		this.sysRole = sysRole;
		this.validFlag = validFlag;
		this.remarks = remarks;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSysCodesId() {
		return this.sysCodesId;
	}

	public void setSysCodesId(String sysCodesId) {
		this.sysCodesId = sysCodesId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescribe() {
		return this.describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public Integer getValidFlag() {
		return validFlag;
	}

	public void setValidFlag(Integer validFlag) {
		this.validFlag = validFlag;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getSysRole() {
		return sysRole;
	}

	public void setSysRole(Integer sysRole) {
		this.sysRole = sysRole;
	}

}