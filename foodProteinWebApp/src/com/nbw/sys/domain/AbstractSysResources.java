package com.nbw.sys.domain;


/**
 * AbstractSysResources entity provides the base persistence definition of the
 * SysResources entity.
 * @author 狄巨礼 May 17, 2010 09:31:00 AM
 */

public abstract class AbstractSysResources implements java.io.Serializable {

	// Fields

	private String id;
	private String parentId;
	private String name;
	private String url;
	private String remarks;

	// Constructors

	public AbstractSysResources(String id, String parentId, String name,
			String url, String remarks) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.name = name;
		this.url = url;
		this.remarks = remarks;
	}

	/** default constructor */
	public AbstractSysResources() {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	

}