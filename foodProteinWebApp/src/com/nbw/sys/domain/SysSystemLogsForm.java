package com.nbw.sys.domain;

import java.util.Date;

/**
 * SysSystemLogs entity. 
 * @author 狄巨礼 May 17, 2010 09:31:00 AM
 */
public class SysSystemLogsForm implements
		java.io.Serializable {

	private SysSystemLogs sysSystemLogs;
	private String userName;
	private String moduleName;
	
	public SysSystemLogsForm(SysSystemLogs sysSystemLogs,String userName,String moduleName){
		this.sysSystemLogs = sysSystemLogs;
		this.userName = userName;
		this.moduleName = moduleName;
	}
	
	public String getId() {
		return sysSystemLogs.getId();
	}

	public void setId(String id) {
		sysSystemLogs.setId(id);
	}

	public Date getLogTime() {
		return sysSystemLogs.getLogTime();
	}

	public void setLogTime(Date logTime) {
		sysSystemLogs.setLogTime(logTime);
	}

	public String getWorkIp() {
		return sysSystemLogs.getWorkIp();
	}

	public void setWorkIp(String workIp) {
		sysSystemLogs.setWorkIp(workIp);
	}

	public String getDescribe() {
		return this.sysSystemLogs.getDescribe();
	}

	public void setDescribe(String describe) {
		sysSystemLogs.setDescribe(describe);
	}

	public Integer getLogLevel() {
		return this.sysSystemLogs.getLogLevel();
	}

	public void setLogLevel(Integer logLevel) {
		sysSystemLogs.setLogLevel(logLevel);
	}

	public String getUserId() {
		return sysSystemLogs.getUserId();
	}

	public void setUserId(String userId) {
		sysSystemLogs.setUserId(userId);
	}

	public String getModuleId() {
		return sysSystemLogs.getModuleId();
	}

	public void setModuleId(String moduleId) {
		sysSystemLogs.setModuleId(moduleId);
	}

	public String getSuccess() {
		if( sysSystemLogs.getSuccess() == 1){
			return "是";
		}
		return "否";
	}

	public void setSuccess(Integer success) {
		sysSystemLogs.setSuccess(success);
	}

	public SysSystemLogs getSysSystemLogs() {
		return sysSystemLogs;
	}

	public void setSysSystemLogs(SysSystemLogs sysSystemLogs) {
		this.sysSystemLogs = sysSystemLogs;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

}
