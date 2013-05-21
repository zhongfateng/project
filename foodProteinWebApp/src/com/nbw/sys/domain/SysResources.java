package com.nbw.sys.domain;


/**
 * SysResources entity.
 * @author 狄巨礼 May 17, 2010 09:31:00 AM
 */
public class SysResources extends AbstractSysResources implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public SysResources() {
	}

	public SysResources(String id, String parentId, String name, String url,
			String remarks) {
		super(id, parentId, name, url, remarks);
		// TODO Auto-generated constructor stub
	}
}
