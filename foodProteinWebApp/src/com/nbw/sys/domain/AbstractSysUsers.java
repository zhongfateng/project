package com.nbw.sys.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.nbw.common.Constants;

/**
 * AbstractSysUsers entity provides the base persistence definition of the
 * SysUsers entity.
 * 
 * @author 狄巨礼 May 17, 2010 09:31:00 AM
 */

public abstract class AbstractSysUsers implements java.io.Serializable {

	// Fields

	private String id;
	private String sysOrganizationsId;
	private String loginCode;
	private String password = Constants.defaultPassword;
	private String name;
	private String personaId;
	private Integer validFlag = SysUsers.VALIDFLAG_TRUE;
	private Integer status;
	private Integer orderNum;
	private String remarks;
	private Date createTime;
	private String createUser;
	private String jbid;

	// Constructors

	/** default constructor */
	public AbstractSysUsers() {
	}

	/** minimal constructor */
	public AbstractSysUsers(String id, String sysOrganizationsId,
			String loginCode, String password, String name) {
		this.id = id;
		this.sysOrganizationsId = sysOrganizationsId;
		this.loginCode = loginCode;
		this.password = password;
		this.name = name;
	}

	/** full constructor */
	public AbstractSysUsers(String id, String sysOrganizationsId,
			String loginCode, String password, String name, String personaId,
			Integer validFlag, Integer status, Integer orderNum,
			String remarks, Date createTime, String createUser) {
		this.id = id;
		this.sysOrganizationsId = sysOrganizationsId;
		this.loginCode = loginCode;
		this.password = password;
		this.name = name;
		this.personaId = personaId;
		this.validFlag = validFlag;
		this.status = status;
		this.orderNum = orderNum;
		this.remarks = remarks;
		this.createTime = createTime;
		this.createUser = createUser;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoginCode() {
		return this.loginCode;
	}

	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPersonaId() {
		return this.personaId;
	}

	public void setPersonaId(String personaId) {
		this.personaId = personaId;
	}

	public Integer isValidFlag() {
		return this.validFlag;
	}

	public void setValidFlag(Integer validFlag) {
		this.validFlag = validFlag;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getOrderNum() {
		return this.orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return this.createUser;
	}
	public void setCreateUser(String createUser) {

		this.createUser = createUser;
	}

	public String getSysOrganizationsId() {
		return sysOrganizationsId;
	}

	public void setSysOrganizationsId(String sysOrganizationsId) {
		this.sysOrganizationsId = sysOrganizationsId;
	}

	public String getJbid() {
		return jbid;
	}

	public void setJbid(String jbid) {
		this.jbid = jbid;
	}
}