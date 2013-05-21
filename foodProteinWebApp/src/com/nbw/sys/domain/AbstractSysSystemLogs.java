package com.nbw.sys.domain;

import java.util.Date;

/**
 * AbstractSysSystemLogs entity provides the base persistence definition of the
 * SysSystemLogs entity. 
 * @author 狄巨礼 May 17, 2010 09:31:00 AM
 */

public abstract class AbstractSysSystemLogs implements java.io.Serializable {

	// Fields

	private String id;
	private String userId;
	private String moduleId;
	private Date logTime;
	private String workIp;
	private Integer success;
	private String describe;
	private Integer logLevel;

	// Constructors

	/** default constructor */
	public AbstractSysSystemLogs() {
	}

	/** minimal constructor */
	public AbstractSysSystemLogs(String id, Date logTime) {
		this.id = id;
		this.logTime = logTime;
	}


	// Property accessors

	public AbstractSysSystemLogs(String id, String userId, String moduleId,
			Date logTime, String workIp, Integer success, String describe,
			Integer logLevel) {
		super();
		this.id = id;
		this.userId = userId;
		this.moduleId = moduleId;
		this.logTime = logTime;
		this.workIp = workIp;
		this.success = success;
		this.describe = describe;
		this.logLevel = logLevel;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getLogTime() {
		return this.logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	public String getWorkIp() {
		return this.workIp;
	}

	public void setWorkIp(String workIp) {
		this.workIp = workIp;
	}

	public String getDescribe() {
		return this.describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public Integer getLogLevel() {
		return this.logLevel;
	}

	public void setLogLevel(Integer logLevel) {
		this.logLevel = logLevel;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public Integer getSuccess() {
		return success;
	}

	public void setSuccess(Integer success) {
		this.success = success;
	}

}