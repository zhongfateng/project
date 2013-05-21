package com.nbw.sys.domain;

import java.util.Date;

import com.nbw.hygl.domain.FmptZh;

/**
 * SysUsers entity.
 * 
 * @author 狄巨礼 May 17, 2010 09:31:00 AM
 */
public class SysUsers extends AbstractSysUsers implements java.io.Serializable {

	public static Integer VALIDFLAG_TRUE = 0;
	public static Integer VALIDFLAG_FALSE = -1;

	// Constructors
	private String sysOrganizationsName;
	private SysOrganizations sysOrganizations;
	// ADD BY QINCY FOR USER ONE TO ONE ZH
	private FmptZh zh;
	

	/** default constructor */
	public SysUsers() {
	}

	/** minimal constructor */
	public SysUsers(String id, String sysOrganizationsId, String loginCode,
			String password, String name) {
		super(id, sysOrganizationsId, loginCode, password, name);
	}

	/** full constructor */
	public SysUsers(String id, String sysOrganizationsId, String loginCode,
			String password, String name, String personaId, Integer validFlag,
			Integer status, Integer orderNum, String remarks, Date createTime,
			String createUser) {
		super(id, sysOrganizationsId, loginCode, password, name, personaId,
				validFlag, status, orderNum, remarks, createTime, createUser);
	}

	public String getSysOrganizationsName() {
		return sysOrganizationsName;
	}

	public void setSysOrganizationsName(String sysOrganizationsName) {
		this.sysOrganizationsName = sysOrganizationsName;
	}

	public SysOrganizations getSysOrganizations() {
		return sysOrganizations;
	}

	public void setSysOrganizations(SysOrganizations sysOrganizations) {
		this.sysOrganizations = sysOrganizations;
	}

	public FmptZh getZh() {
		return zh;
	}

	public void setZh(FmptZh zh) {
		this.zh = zh;
	}

}
