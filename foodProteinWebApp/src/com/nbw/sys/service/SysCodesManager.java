package com.nbw.sys.service;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.nbw.common.Constants;
import com.nbw.common.dhtml.GridDataXml;
import com.nbw.common.util.CommonUtils;
import com.nbw.sys.dao.SysCodesDAO;
import com.nbw.sys.domain.SysCodes;

/**
 * 
 * SysCodesManager
 * 
 * @author 狄巨礼 May 17, 2010 09:31:00 AM
 * 
 */
public class SysCodesManager {

	private SysCodesDAO sysCodesDAO;

	/**
	 * 通过id得到对象
	 * 
	 * @param id
	 * @return pObject
	 */
	public SysCodes findByID(String id) {
		return sysCodesDAO.findByID(id);
	}

	/**
	 * 通过 编码来 查找 
	 * @param code
	 * @return
	 */
	public SysCodes findByCode(String code) {
		return sysCodesDAO.getByCode(code);
	}

	/**
	 * 保存对象
	 * 
	 * @param sysCodes
	 */
	public boolean save(SysCodes sysCodes) {
		// 设置treeid
		String pid = sysCodes.getParentId();
		SysCodes psc = this.findByID(pid);
		sysCodes.setTreeId(psc.getTreeId() + "," + sysCodes.getId());

		// 设置编码类型，凡是非系统初始化的数据都为 TYPE_USER
		sysCodes.setType(SysCodes.TYPE_USER);

		// 设置为有效
		sysCodes.setValidFlag(SysCodes.VALIDFLAG_TRUE);
		return sysCodesDAO.save(sysCodes);
	}

	/**
	 * 更新对象
	 * 
	 * @param sysCodes
	 */
	public boolean update(SysCodes sysCodes) {
		return sysCodesDAO.update(sysCodes);
	}

	/**
	 * 删除编码 首先要删除所有的子编码再删除自己
	 * 
	 * @param id
	 */
	public void deleteCodes(String id) {
		// 删除其所有的子编码
		List<SysCodes> list = this.findSysCodes(id);
		for (int i = 0; i < list.size(); i++) {
			deleteCodes(list.get(i).getId());
		}
		this.delete(id);
	}

	/**
	 * 删除对象 根据id
	 * 
	 * @param id
	 */
	private boolean delete(String id) {
		SysCodes sc = this.findByID(id);
		// 如果是编码类型是系统级编码则修改有效标识为无效，否则物理删除
		if (SysCodes.TYPE_SYS.equals(sc.getType())) {
			sc.setValidFlag(SysCodes.VALIDFLAG_FALSE);
			return this.update(sc);
		}
		return sysCodesDAO.delete(id);
	}

	/**
	 * 判断 SysCode.id!=id 编码不重复,更新时使用
	 * 
	 * @param id
	 * @param code
	 * @return
	 */
	public boolean isCodeExist(String id, String code) {
		return sysCodesDAO.isCodeExist(id, code);
	}

	/**
	 * 判断编码不重复,添加时使用
	 * 
	 * @param code
	 * @return
	 */
	public boolean isCodeExist(String code) {
		return sysCodesDAO.isCodeExist(code);
	}

	/**
	 * 获取所有父节点等于parentId的对象。
	 * 
	 * @param parentId
	 * @return
	 */
	public List<SysCodes> findSysCodes(String parentId) {
		return sysCodesDAO.findChildByID(parentId);
	}

	/**
	 * 处理DhtmlxGrid发过来的操作包括添加、删除、修改
	 * 
	 * @param command
	 *            要处理的对象
	 * @param nativeeditor_status
	 *            处理方式
	 * @return 返回DhtmlxGrid 接收的处理反馈结果（xml）
	 */
	public String process(SysCodes command, String nativeeditor_status) {
		String xml = "";
		String id = command.getId();
		String code = command.getCode();
		if ("updated".equals(nativeeditor_status)) {
			boolean result = isCodeExist(id, code);
			if (result) {
				xml = CommonUtils.getDhtmlxGridXml("update", id, id,
						Constants.ERROR_CODE_EXIST);
			} else {
				update(command);
				xml = CommonUtils.getDhtmlxGridXml("update", id, id, "1");
			}
		} else if ("inserted".equals(nativeeditor_status)) {
			boolean result = isCodeExist(code);
			if (result) {
				xml = CommonUtils.getDhtmlxGridXml("insert", id, id,
						Constants.ERROR_CODE_EXIST);
			} else {
				save(command);
				xml = CommonUtils.getDhtmlxGridXml("insert", id, id, "1");
			}
		} else if ("deleted".equals(nativeeditor_status)) {
			deleteCodes(id);
			xml = CommonUtils.getDhtmlxGridXml("delete", id, id, "1");
		}
		return xml;
	}

	/**
	 * 加载dhtmlgrid需要的xml，内容是根据编码parentId获取的子编码
	 * 
	 * @param parentId
	 *            SysCodes父Id
	 * @return 返回DhtmlxGrid 接收的处理反馈结果（xml）
	 */
	public String getGridXmlByPId(String parentId) {
		GridDataXml gridDataXml = new GridDataXml();
		if (parentId == null || parentId.trim().equals("")) {
			parentId = Constants.DEFAULT_ROOT;
		}
		List<SysCodes> list = sysCodesDAO.findChildByID(parentId);
		if (list != null) {
			for (SysCodes sysCodes : list) {
				gridDataXml.setId(sysCodes.getId());
				gridDataXml.setXmlData(CommonUtils.DealString(sysCodes
						.getParentId()));
				gridDataXml.setXmlData(CommonUtils.DealString(sysCodes
						.getCode()));
				gridDataXml.setXmlData(CommonUtils.DealString(sysCodes
						.getName()));
				gridDataXml.setXmlData(CommonUtils.DealString(sysCodes
						.getDescribe()));
				gridDataXml.setXmlData(CommonUtils.DealString(sysCodes
						.getPam1()));
				gridDataXml.setXmlData(CommonUtils.DealString(sysCodes
						.getPam2()));
				gridDataXml.setXmlData(sysCodes.getOrderNum() == null ? "0"
						: sysCodes.getOrderNum());
				gridDataXml.setXmlData(CommonUtils.DealString(sysCodes
						.getRemarks()));
			}
		}
		String xml = gridDataXml.getXml();
		return xml;
	}

	/** ******************************获取编码树的xml开始******************************* */
	/**
	 * 得到动态加载编码树的生成串
	 * 
	 * @param id
	 * @return pObject
	 */
	public String getTreeXml(String id) {
		/** 建立document对象 */
		Document document = DocumentHelper.createDocument();
		/** 建立XML文档的根tree */
		Element treeElement = document.addElement("tree");
		// treeElement.addAttribute("id", "0");
		if (null == id || "".equals(id)) {
			id = Constants.DEFAULT_ROOT;
		}
		List<SysCodes> list = sysCodesDAO.findChildByID(id);
		if (Constants.DEFAULT_ROOT.equals(id)) {
			treeElement.addAttribute("id", "0");
			/** 建立XML文档的根节点item */
			Element rootElement = treeElement.addElement("item");

			rootElement.addAttribute("id", id);
			rootElement.addAttribute("text", sysCodesDAO.findNameByID(id));
			rootElement.addAttribute("open", "1");
			rootElement.addAttribute("im0", "folderClosed.gif");
			rootElement.addAttribute("im1", "folderOpen.gif");
			rootElement.addAttribute("im2", "folderClosed.gif");
			rootElement.addAttribute("select", "yes");
			addSysCodes(list, rootElement);
		} else {
			treeElement.addAttribute("id", id);
			addSysCodes(list, treeElement);
		}
		String ss = document.asXML();
		return document.asXML();
	}

	/**
	 * 动态加载时增加下级节点（只有一级节点）
	 * 
	 * @param
	 * @return Element
	 */
	private Element addSysCodes(List<SysCodes> list, Element rElement) {
		for (SysCodes obj : list) {
			Element itemElement = rElement.addElement("item");
			itemElement.addAttribute("id", obj.getId());
			itemElement.addAttribute("text", obj.getName());
			if (sysCodesIsLeaf(obj.getId())) {
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
	 * 判断该编码是否是叶子节点
	 * 
	 * @param SysCode
	 *            Id
	 * @return
	 */
	private boolean sysCodesIsLeaf(String id) {
		List list = sysCodesDAO.findChildByID(id);
		return list.size() <= 0;
	}

	/** ******************************获取编码树的xml结束******************************* */

	/**
	 * 获取角色类型的编码list
	 */
	public List<SysCodes> getRoleTypes() {
		// 角色类型的编码pid
		String pid = Constants.CODE_CONSTANT_ROLETYPE;
		return sysCodesDAO.findChildByID(pid);
	}

	public SysCodesDAO getSysCodesDAO() {
		return sysCodesDAO;
	}

	public void setSysCodesDAO(SysCodesDAO sysCodesDAO) {
		this.sysCodesDAO = sysCodesDAO;
	}

}
