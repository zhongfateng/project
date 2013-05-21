package com.nbw.sys.domain;


/**
 * SysOrganizations entity. 
 * @author 狄巨礼 May 17, 2010 09:31:00 AM
 */
public class SysOrganizations extends AbstractSysOrganizations implements
		java.io.Serializable {
	private String parentName;
	public static Integer VALIDFLAG_TRUE = 0;
	public static Integer VALIDFLAG_FALSE = -1;

	// Constructors

	/** default constructor */
	public SysOrganizations() {
	}

	public SysOrganizations(String id, String parentId, String code,
			String name, String shortName, Integer validFlag, Integer status,
			Integer orderNum, String remarks, String treeId) {
		super(id, parentId, code, name, shortName, validFlag, status, orderNum,
				remarks, treeId);
		// TODO Auto-generated constructor stub
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

}
