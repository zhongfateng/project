package  com.nbw.sys.service;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.nbw.common.Constants;
import com.nbw.common.dhtml.GridDataXml;
import com.nbw.common.util.CommonUtils;
import com.nbw.sys.dao.SysResourcesDAO;
import com.nbw.sys.domain.SysResources;

/**
 * 
 * SysResourcesManager
 * 
 * @author 狄巨礼 May 17, 2010 09:31:00 AM
 * 
 */
public class SysResourcesManager{

	private SysResourcesDAO sysResourcesDAO;

	/**
	 * 通过id得到对象
	 *
	 * @param  id
	 * @return pObject
	 */
	public SysResources findByID(String id){ 
		return sysResourcesDAO.findByID(id);
	}
	
	/**
	 * 保存对象
	 *
	 * @param  sysCodes
	 */
	public boolean save(SysResources sysResources) {
		//设置treeid
//		String pid = sysResources.getParentId();
//		SysCodes psc = this.findByID(pid);
//		sysResources.setTreeId(psc.getTreeId()+","+sysResources.getId());
		return sysResourcesDAO.save(sysResources);
	}

	/**
	 * 更新对象
	 *
	 * @param  sysCodes
	 */
	public boolean update(SysResources sysResources) {
		return sysResourcesDAO.update(sysResources);
	}
	
	/**
	 * 删除编码
	 * 首先要删除所有的子编码再删除自己
	 * @param  id
	 */
	public void deleteCodes(String id) {
		//删除其所有的子编码
		List<SysResources> list = this.findSysCodes(id);
		for(int i=0;i<list.size();i++){
			deleteCodes(list.get(i).getId());
		}
		this.delete(id);
	}
	
	/**
	 * 删除对象 根据id
	 * @param  id
	 */
	private boolean delete(String id) {
		//需要完善 删除资源关系
		
		return sysResourcesDAO.delete(id);
	}

	
	/**
	 * 获取所有父节点等于parentId的对象。
	 * @param parentId	
	 * @return
	 */
	public List<SysResources> findSysCodes(String parentId) {
		return sysResourcesDAO.findChildByID(parentId);
	}

	/**
	 * 处理DhtmlxGrid发过来的操作包括添加、删除、修改
	 * @param command			  	要处理的对象
	 * @param nativeeditor_status 	处理方式
	 * @return			返回DhtmlxGrid 接收的处理反馈结果（xml）
	 */
	public String process(SysResources command, String nativeeditor_status) {
		String xml="";
		String id = command.getId();
		if ("updated".equals(nativeeditor_status)) {
			update(command);
			xml = CommonUtils.getDhtmlxGridXml("update", id, id, "1");
		} else if ("inserted".equals(nativeeditor_status)) {
			save(command);
			xml = CommonUtils.getDhtmlxGridXml("insert", id, id, "1");
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
		List<SysResources> list = sysResourcesDAO.findChildByID(parentId);
		if (list != null) {
			for (SysResources sr : list) {
				gridDataXml.setId(sr.getId());
				gridDataXml.setXmlData(CommonUtils.DealString(sr.getParentId()));
				gridDataXml.setXmlData(CommonUtils.DealString(sr.getName()));
				gridDataXml.setXmlData(CommonUtils.DealString(sr.getUrl()));
				gridDataXml.setXmlData(CommonUtils.DealString(sr.getRemarks()));
			}
		}
		String xml = gridDataXml.getXml();
		return xml;
	}
	
	/********************************获取编码树的xml开始********************************/
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
		List<SysResources> list = sysResourcesDAO.findChildByID(id);
		if (Constants.DEFAULT_ROOT.equals(id)) {
			treeElement.addAttribute("id", "0");
			/** 建立XML文档的根节点item */
			Element rootElement = treeElement.addElement("item");

			rootElement.addAttribute("id", id);
			rootElement.addAttribute("text", sysResourcesDAO.findNameByID(id));
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
	private Element addSysCodes(List<SysResources> list, Element rElement) {
		for (SysResources obj : list) {
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
	 * @param SysCode Id
	 * @return 
	 */
	private boolean sysCodesIsLeaf(String id){
		List list = sysResourcesDAO.findChildByID(id);
		return list.size()<=0;
	}
	/********************************获取编码树的xml结束********************************/

	public SysResourcesDAO getSysResourcesDAO() {
		return sysResourcesDAO;
	}

	public void setSysResourcesDAO(SysResourcesDAO sysResourcesDAO) {
		this.sysResourcesDAO = sysResourcesDAO;
	}


	
}

