package com.nbw.sys.domain;


/**
 * AbstractSysOrganizations entity provides the base persistence definition of
 * the SysOrganizations entity. 
 * @author 狄巨礼 May 17, 2010 09:31:00 AM
 */

public abstract class AbstractSysOrganizations implements java.io.Serializable {

	// Fields

	private String id;
	private String parentId;
	private String code;
	private String name;
	private String shortName;
	private Integer validFlag=SysOrganizations.VALIDFLAG_TRUE;
	private Integer status;
	private Integer orderNum;
	private String remarks;
	private String treeId;

	// Constructors

	public AbstractSysOrganizations(String id, String parentId, String code,
			String name, String shortName, Integer validFlag, Integer status,
			Integer orderNum, String remarks, String treeId) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.code = code;
		this.name = name;
		this.shortName = shortName;
		this.validFlag = validFlag;
		this.status = status;
		this.orderNum = orderNum;
		this.remarks = remarks;
		this.treeId = treeId;
	}

	/** default constructor */
	public AbstractSysOrganizations() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}


	public Integer getValidFlag() {
		return validFlag;
	}

	public void setValidFlag(Integer validFlag) {
		this.validFlag = validFlag;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getTreeId() {
		return treeId;
	}

	public void setTreeId(String treeId) {
		this.treeId = treeId;
	}

	

}