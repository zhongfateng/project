package com.nbw.sys.domain;

/**
 * SysModuleActions entity. 
 * @author 狄巨礼 May 17, 2010 09:31:00 AM
 */
public class SysModuleActions extends AbstractSysModuleActions implements
		java.io.Serializable {

	/** default constructor */
	public SysModuleActions() {
	}

	/** minimal constructor */
	public SysModuleActions(String id, Integer code, String name) {
		super(id, code, name);
	}

	public SysModuleActions(String id, String moduleId, Integer code,
			String name, String methods) {
		super(id, moduleId, code, name, methods);
		// TODO Auto-generated constructor stub
	}

}
