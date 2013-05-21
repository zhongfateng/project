package com.nbw.sys.domain;


/**
 * SysCodes entity. 
 * @author 狄巨礼 May 17, 2010 09:31:00 AM
 */
public class SysCodes extends AbstractSysCodes implements java.io.Serializable {
	
	public static String TYPE_SYS = "S";
	public static String TYPE_USER = "U";
	public static Integer VALIDFLAG_TRUE = 0;
	public static Integer VALIDFLAG_FALSE = -1;
	// Constructors

	/** default constructor */
	public SysCodes() {
	}

	public SysCodes(String id, String parentId, String type, String code,
			String name, String describe, Integer validFlag, Integer orderNum,
			String pam1, String pam2, String remarks, String treeId) {
		super(id, parentId, type, code, name, describe, validFlag, orderNum, pam1,
				pam2, remarks, treeId);
		// TODO Auto-generated constructor stub
	}


}
