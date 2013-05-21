package com.nbw.sys.domain;

/**
 * AbstractSysModuleActions entity provides the base persistence definition of
 * the SysModuleActions entity. 
 * @author 狄巨礼 May 17, 2010 09:31:00 AM
 */

public abstract class AbstractSysModuleActions implements java.io.Serializable {

	// Fields

	private String id;
	private String moduleId;
	private Integer code;
	private String name;
	private String methods;

	// Constructors

	/** default constructor */
	public AbstractSysModuleActions() {
	}

	/** minimal constructor */
	public AbstractSysModuleActions(String id, Integer code, String name) {
		this.id = id;
		this.code = code;
		this.name = name;
	}

	// Property accessors

	public AbstractSysModuleActions(String id, String moduleId, Integer code,
			String name, String methods) {
		super();
		this.id = id;
		this.moduleId = moduleId;
		this.code = code;
		this.name = name;
		this.methods = methods;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getCode() {
		return this.code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMethods() {
		return this.methods;
	}

	public void setMethods(String methods) {
		this.methods = methods;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

}