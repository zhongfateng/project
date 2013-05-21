package com.nbw.sys.domain;

import java.util.Date;

/**
 * SysSystemLogs entity.
 * @author 狄巨礼 May 17, 2010 09:31:00 AM
 */
public class SysSystemLogs extends AbstractSysSystemLogs implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public SysSystemLogs() {
	}

	/** minimal constructor */
	public SysSystemLogs(String id, Date logTime) {
		super(id, logTime);
	}

	public SysSystemLogs(String id, String userId, String moduleId,
			Date logTime, String workIp, Integer success, String describe,
			Integer logLevel) {
		super(id, userId, moduleId, logTime, workIp, success, describe, logLevel);
		// TODO Auto-generated constructor stub
	}

}
