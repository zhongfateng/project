package com.nbw.sys.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.nbw.common.Constants;
import com.nbw.common.dhtml.GridDataXml;
import com.nbw.common.util.CommonUtils;
import com.nbw.sys.dao.SysModulesDAO;
import com.nbw.sys.dao.SysRolesDAO;
import com.nbw.sys.domain.SysModuleActions;
import com.nbw.sys.domain.SysModules;
import com.nbw.sys.domain.SysUsersDetail;

/**
 * 
 * SysModulesManager
 * 
 * @author 狄巨礼 May 17, 2010 09:31:00 AM
 * 
 */
public class SysModulesManager {

	private SysModulesDAO sysModulesDAO;

	private SysRolesDAO sysRolesDAO;

	/**
	 * 处理DhtmlxGrid发过来的操作包括添加、修改
	 * 
	 * @param command
	 *            要处理的对象
	 * @return 返回处理反馈结果（xml）
	 */
	public String process(SysModules command) {
		String id = command.getId();
		String code = command.getCode();
		String xml = "";
		if (isCodeExist(id, code)) {
			xml = Constants.ERROR_CODE_EXIST;
		} else {
			saveOrUpdate(command);
			xml = Constants.DEFAULT_AJAX_SUCCESS + command.getId();
		}
		return xml;
	}

	/**
	 * 通过id得到对象
	 * 
	 * @param id
	 * @return pObject
	 */
	public SysModules findByID(String id) {
		return sysModulesDAO.findByID(id);
	}

	/**
	 * 保存对象
	 * 
	 * @param sysModules
	 */
	public boolean saveOrUpdate(SysModules sysModules) {
		// 计算出TreeId
		String pid = sysModules.getParentId();
		SysModules psm = this.findByID(pid);
		if (psm != null) {
			sysModules.setTreeId(psm.getTreeId() + "," + sysModules.getId());
		} else {
			sysModules.setTreeId(sysModules.getId());
		}
		sysModules.setValidFlag(SysModules.VALIDFLAG_TRUE);

		return sysModulesDAO.saveOrUpdate(sysModules);
	}

	/**
	 * 删除模块及模块对应关系
	 * 
	 * @param ids
	 */
	public boolean delete(String ids) {
		boolean success = false;
		try {
			String[] idarray = ids.split(",");
			for (int i = 0; i < idarray.length; i++) {
				success = sysModulesDAO.deleteSysModules(idarray[i]);
				// 删除SysModuleActions要先删除SysRolesModules，所以下面的两条删除语句不可调换
				List list = sysModulesDAO
						.getSysRolesModulesByModuleId(idarray[i]);
				for (int j = 0; j < list.size(); j++) {
					sysModulesDAO.deleteObject(list.get(j));
				}
				sysModulesDAO.deleteSysModuleActionsByModuleId(idarray[i]);
			}
		} catch (Exception re) {
			success = false;
			throw re;
		} finally {
			return success;
		}
	}

	/**
	 * 通过ID得到NAME
	 * 
	 * @param ID
	 * @return String
	 */
	public String findNameByID(String id) {
		return sysModulesDAO.findNameByID(id);
	}

	/**
	 * 判断 SysModules.id!=id 模块编码不重复,更新时使用
	 * 
	 * @param id
	 * @param code
	 * @return
	 */
	public boolean isCodeExist(String id, String code) {
		return sysModulesDAO.isCodeExist(id, code);
	}

	/**
	 * 判断编码不重复,添加时使用
	 * 
	 * @param code
	 * @return
	 */
	public boolean isCodeExist(String code) {
		return sysModulesDAO.isCodeExist(code);
	}

	/**
	 * 获取SysModuleActions通过MoculeId
	 * 
	 * @param moduleId
	 * @return
	 */
	public List<SysModuleActions> findSysModuleActionsByModuleID(String moduleId) {
		if (moduleId == null || moduleId.trim().equals("")
				|| moduleId.equals(Constants.DEFAULT_ROOT)) {
			return sysModulesDAO.findAllSysModuleActions();
		}
		return sysModulesDAO.findSysModuleActionsByModuleID(moduleId);
	}

	/**
	 * 获取用户有权限的和公共的模块Map key=SysModules.url value = SysModules
	 * SysModules.actionsMap.key=SysModuleActions.methods
	 * SysModules.actionsMap.value=SysModuleActions
	 * 
	 * @param userId
	 * @return
	 */
	public Map getSysModulesMapByUserID(String userId) {
		List<SysModules> list = sysModulesDAO.findSysModulesByUserID(userId,
				null);
		Map map = new HashMap();
		for (int i = 0; i < list.size(); i++) {
			SysModules m = list.get(i);
			// 如果该模块的URL不合法说明该模块是父模块没有实际意义，或模块配置错误
			if (m.getUrl() == null || "".equals(m.getUrl())
					|| "#".equals(m.getUrl())) {
				continue;
			}
			m.setActionsMap(generateSysModuleActionsMap(userId, m.getId()));
			String uri = getSysModulesMapKey(m.getUrl());
			if (map.get(uri) != null) {
				SysModules o = (SysModules) map.get(uri);
				m.getActionsMap().putAll(o.getActionsMap());
			}
			map.put(uri, m);
		}
		return map;
	}

	private String getSysModulesMapKey(String url) {
		if (url.indexOf("?") > 0) {
			url = url.substring(0, url.indexOf("?"));
		}
		return url;
	}

	/**
	 * 生成根据用户的权限生成moduleId 下的 SysModuleActions组成的Map（即包括有权限的子模块）
	 * key=SysModuleActions.methods value=SysModuleActions
	 * 
	 * @param userId
	 * @param m
	 * @return
	 */
	private Map generateSysModuleActionsMap(String userId, String moduleId) {
		List<SysModuleActions> list = sysModulesDAO
				.findSysModuleActionsByUserIDmoduleId(userId, moduleId);
		Map map = new HashMap();
		for (int i = 0; i < list.size(); i++) {
			map.put(list.get(i).getMethods(), list.get(i));
		}
		return map;
	}

	/**
	 * 获取用户有权限的和公共的所有模块列表 该SysModules
	 * list被重新封装，list里面直接为一级模块，每级的每个模块中的childlist为下级模块，没有则为null
	 * 
	 * @param userId
	 * @return
	 */
	public List<SysModules> findSysModulesByUserID(String userId) {
		List<SysModules> reList = new LinkedList();
		List<SysModules> list = sysModulesDAO.findMenuSysModulesByUserID(
				userId, Constants.DEFAULT_ROOT);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) != null) {
				reList.add(generateSysModules(userId, list.get(i)));
			}
		}
		return reList;
	}

	/**
	 * 生成根据用户的权限生成SysModules（即包括有权限的子模块）
	 * 
	 * @param userId
	 * @param m
	 * @return
	 */
	private SysModules generateSysModules(String userId, SysModules m) {
		List<SysModules> reList = new LinkedList();
		List<SysModules> list = sysModulesDAO.findMenuSysModulesByUserID(
				userId, m.getId());
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) != null) {
					reList.add(generateSysModules(userId, list.get(i)));
				}

			}
			m.setChilds(reList);
		}
		return m;
	}

	/** ******************************获取模块和模块操作grid的xml开始******************************* */
	/**
	 * 根据模块Id 获取其子模块封装的xml
	 */
	public String getGridXml(String id) {
		GridDataXml gridDataXml = new GridDataXml();
		if (id == null || id.trim().equals("")) {
			id = Constants.DEFAULT_ROOT;
		}
		List<SysModules> list = sysModulesDAO.findChildByID(id);
		if (list != null) {
			for (SysModules so : list) {
				gridDataXml.setId(so.getId());
				gridDataXml.setXmlData(CommonUtils.DealString(so.getCode()));
				gridDataXml.setXmlData(CommonUtils.DealString(so.getName()));
				gridDataXml
						.setXmlData(CommonUtils.DealString(so.getDescribe()));
				gridDataXml.setXmlData(CommonUtils.DealString(so.getRemarks()));
				gridDataXml.setXmlData(so.getOrderNum() == null ? "0" : so
						.getOrderNum());
			}
		}
		return gridDataXml.getXml();
	}

	/**
	 * 根据模块Id 获取模块的SysModuleActions封装的xml
	 * 
	 * @param moduleId
	 * @return
	 */
	public String getActionGridXML(String moduleId) {
		GridDataXml gridDataXml = new GridDataXml();
		List<SysModuleActions> list = findSysModuleActionsByModuleID(moduleId);
		if (list != null) {
			for (SysModuleActions so : list) {
				gridDataXml.setId(so.getId());
				gridDataXml
						.setXmlData(CommonUtils.DealString(so.getModuleId()));
				gridDataXml.setXmlData(CommonUtils.DealInteger(so.getCode()));
				gridDataXml.setXmlData(CommonUtils.DealString(so.getName()));
				gridDataXml.setXmlData(CommonUtils.DealString(so.getMethods()));
			}
		}
		return gridDataXml.getXml();
	}

	/** ******************************获取模块和模块操作grid的xml结束******************************* */

	/** ******************************获取模块树的xml开始******************************* */
	/**
	 * 获取模块树的xml
	 * 
	 * @param id
	 * @return 模块树的 xml
	 */
	public String getTreeXml(String id) {
		/** 建立document对象 */
		Document document = DocumentHelper.createDocument();
		/** 建立XML文档的根tree */
		Element treeElement = document.addElement("tree");

		/** 如果id是空则说明是首次加载 把ID置成ROOT */
		if (null == id || "".equals(id)) {
			id = Constants.DEFAULT_ROOT;
		}
		List<SysModules> list = sysModulesDAO.findMenuChildByID(id);
		if (Constants.DEFAULT_ROOT.equals(id)) {
			treeElement.addAttribute("id", "0");
			/** 建立XML文档的根节点item */
			Element rootElement = treeElement.addElement("item");

			rootElement.addAttribute("id", id);
			rootElement.addAttribute("text", sysModulesDAO.findNameByID(id));
			rootElement.addAttribute("open", "1");
			rootElement.addAttribute("im0", "folderClosed.gif");
			rootElement.addAttribute("im1", "folderOpen.gif");
			rootElement.addAttribute("im2", "folderClosed.gif");
			rootElement.addAttribute("select", "yes");

			addModuleNodes(list, rootElement);
		} else {
			treeElement.addAttribute("id", id);
			addModuleNodes(list, treeElement);
		}
		return document.asXML();
	}

	/**
	 * 动态加载时增加下级节点（只有一级节点）
	 * 
	 * @param
	 * @return Element
	 */
	private Element addModuleNodes(List<SysModules> list, Element rElement) {
		for (SysModules obj : list) {
			Element itemElement = rElement.addElement("item");
			itemElement.addAttribute("id", obj.getId());
			itemElement.addAttribute("text", obj.getName());
			// itemElement.addAttribute("open", "1");
			if (moduleIsLeaf(obj.getId())) {
				itemElement.addAttribute("im0", "iconText.gif");
				itemElement.addAttribute("im1", "iconText.gif");
				itemElement.addAttribute("im2", "iconText.gif");
			} else {
				itemElement.addAttribute("im0", "folderClosed.gif");
				itemElement.addAttribute("im1", "folderOpen.gif");
				itemElement.addAttribute("im2", "folderClosed.gif");
				itemElement.addAttribute("child", obj.getId());
			}
		}
		return rElement;
	}

	/**
	 * 判断模块是否是叶子节点
	 * 
	 * @param SysModules
	 *            id
	 * @return
	 */
	private boolean moduleIsLeaf(String id) {
		List list = sysModulesDAO.findMenuChildByID(id);
		return list.size() <= 0;
	}

	/**
	 * 根据模块Id一次性加载Id下的所有模块树xml，如果是ID==ROOT则加载整颗树
	 * 
	 * @param id
	 * @return
	 */
	public String getAllTreeXML(String id) {
		/** 建立document对象 */
		Document document = DocumentHelper.createDocument();
		/** 建立XML文档的根tree */
		Element treeElement = document.addElement("tree");

		/** 如果id是空则说明是首次加载 把ID置成ROOT */
		if (null == id || "".equals(id)) {
			id = Constants.DEFAULT_ROOT;
		}
		List<SysModules> list = sysModulesDAO.findMenuChildByID(id);
		if (Constants.DEFAULT_ROOT.equals(id)) {
			treeElement.addAttribute("id", "0");
			/** 建立XML文档的根节点item */
			Element rootElement = treeElement.addElement("item");

			rootElement.addAttribute("id", id);
			rootElement.addAttribute("text", sysModulesDAO.findNameByID(id));
			rootElement.addAttribute("open", "1");
			rootElement.addAttribute("im0", "folderClosed.gif");
			rootElement.addAttribute("im1", "folderOpen.gif");
			rootElement.addAttribute("im2", "folderClosed.gif");
			rootElement.addAttribute("select", "yes");

			addAllModuleNodes(list, rootElement);
		} else {
			treeElement.addAttribute("id", id);
			addAllModuleNodes(list, treeElement);
		}
		return document.asXML();
	}

	/**
	 * 递归加载时增加下级节点
	 * 
	 * @param
	 * @return Element
	 */
	private Element addAllModuleNodes(List<SysModules> list, Element rElement) {
		for (SysModules obj : list) {
			Element itemElement = rElement.addElement("item");
			itemElement.addAttribute("id", obj.getId());
			itemElement.addAttribute("text", obj.getName());
			List listsub = sysModulesDAO.findMenuChildByID(obj.getId());
			// itemElement.addAttribute("open", "1");
			if (listsub.size() == 0) {
				itemElement.addAttribute("im0", "iconText.gif");
				itemElement.addAttribute("im1", "iconText.gif");
				itemElement.addAttribute("im2", "iconText.gif");
			} else {
				itemElement.addAttribute("im0", "folderClosed.gif");
				itemElement.addAttribute("im1", "folderOpen.gif");
				itemElement.addAttribute("im2", "folderClosed.gif");
				itemElement.addAttribute("child", obj.getId());
				addAllModuleNodes(listsub, itemElement);
			}
		}
		return rElement;
	}

	/** ******************************获取模块树的xml结束******************************* */

	public SysModulesDAO getSysModulesDAO() {
		return sysModulesDAO;
	}

	public void setSysModulesDAO(SysModulesDAO sysModulesDAO) {
		this.sysModulesDAO = sysModulesDAO;
	}

	public void updateSysModuleActions(SysModuleActions sma) {
		sysModulesDAO.saveOrUpdateObject(sma);
	}

	public void saveSysModuleActions(SysModuleActions sma) {
		sysModulesDAO.saveOrUpdateObject(sma);
	}

	public void deleteSysModuleActions(SysModuleActions sma) {
		sysModulesDAO.deleteObject(sma);
	}

	public SysRolesDAO getSysRolesDAO() {
		return sysRolesDAO;
	}

	public void setSysRolesDAO(SysRolesDAO sysRolesDAO) {
		this.sysRolesDAO = sysRolesDAO;
	}
}
