package com.nbw.sys.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractSysModules entity provides the base persistence definition of the
 * SysModules entity. 
 * @author 狄巨礼 May 17, 2010 09:31:00 AM
 */

public abstract class AbstractSysModules implements java.io.Serializable {

	// Fields

	private String id;
	private String parentId;
	private String code;
	private String name;
	private String shortName;
	private String describe;
	private String url;
	private Integer linkType = SysModules.LINK_TYPE_PURVIEW;
	private Integer validFlag = SysModules.VALIDFLAG_TRUE;
	private Integer orderNum;
	private String remarks;
	private String treeId;
	private Integer isMenu = SysModules.ISMENU_TRUE;

	// Constructors

	/** default constructor */
	public AbstractSysModules() {
	}

	/** minimal constructor */
	public AbstractSysModules(String id, String name) {
		this.id = id;
		this.name = name;
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

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getLinkType() {
		return linkType;
	}

	public void setLinkType(Integer linkType) {
		this.linkType = linkType;
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

	public AbstractSysModules(String id, String parentId, String code,
			String name, String shortName, String describe, String url,
			Integer linkType, Integer validFlag, Integer orderNum,
			String remarks, String treeId, Integer isMenu) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.code = code;
		this.name = name;
		this.shortName = shortName;
		this.describe = describe;
		this.url = url;
		this.linkType = linkType;
		this.validFlag = validFlag;
		this.orderNum = orderNum;
		this.remarks = remarks;
		this.treeId = treeId;
		this.isMenu = isMenu;
	}

	public Integer getIsMenu() {
		return isMenu;
	}

	public void setIsMenu(Integer isMenu) {
		this.isMenu = isMenu;
	}

	

}