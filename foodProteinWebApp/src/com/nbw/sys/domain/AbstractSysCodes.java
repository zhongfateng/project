package com.nbw.sys.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractSysCodes entity provides the base persistence definition of the
 * SysCodes entity. 
 * @author 狄巨礼 May 17, 2010 09:31:00 AM
 */

public abstract class AbstractSysCodes implements java.io.Serializable {

	// Fields

	private String id;
	private String parentId;
	private String type=SysCodes.TYPE_USER;
	private String code;
	private String name;
	private String describe;
	private Integer validFlag=SysCodes.VALIDFLAG_TRUE;
	private Integer orderNum;
	private String pam1;
	private String pam2;
	private String remarks;
	private String treeId;

	// Constructors

	/** default constructor */
	public AbstractSysCodes() {
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getDescribe() {
		return describe;
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

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getPam1() {
		return pam1;
	}

	public void setPam1(String pam1) {
		this.pam1 = pam1;
	}

	public String getPam2() {
		return pam2;
	}

	public void setPam2(String pam2) {
		this.pam2 = pam2;
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

	public AbstractSysCodes(String id, String parentId, String type,
			String code, String name, String describe, Integer validFlag,
			Integer orderNum, String pam1, String pam2, String remarks,
			String treeId) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.type = type;
		this.code = code;
		this.name = name;
		this.describe = describe;
		this.validFlag = validFlag;
		this.orderNum = orderNum;
		this.pam1 = pam1;
		this.pam2 = pam2;
		this.remarks = remarks;
		this.treeId = treeId;
	}

	
}