package com.nbw.sys.domain;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * SysModules entity.
 * @author 狄巨礼 May 17, 2010 09:31:00 AM
 */
public class SysModules extends AbstractSysModules implements
		java.io.Serializable {

	public static Integer VALIDFLAG_TRUE = 0;
	public static Integer VALIDFLAG_FALSE = -1;
	public static Integer LINK_TYPE_PUBLIC = -1;
	public static Integer LINK_TYPE_PURVIEW = 0;
	public static Integer ISMENU_TRUE = 0;
	public static Integer ISMENU_FALSE = -1;
	
	private String parentName;
	
	private List<SysModules> childs = new LinkedList();
	private Map actionsMap = new HashMap();

	// Constructors

	/** default constructor */
	public SysModules() {
	}

	/** minimal constructor */
	public SysModules(String id, String name) {
		super(id, name);
	}

	public SysModules(String id, String parentId, String code, String name,
			String shortName, String describe, String url, Integer linkType,
			Integer validFlag, Integer orderNum, String remarks, String treeId,
			Integer isMenu) {
		super(id, parentId, code, name, shortName, describe, url, linkType, validFlag,
				orderNum, remarks, treeId, isMenu);
		// TODO Auto-generated constructor stub
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public List<SysModules> getChilds() {
		return childs;
	}

	public void setChilds(List<SysModules> childs) {
		this.childs = childs;
	}

	public Map getActionsMap() {
		return actionsMap;
	}

	public void setActionsMap(Map actionsMap) {
		this.actionsMap = actionsMap;
	}


}
