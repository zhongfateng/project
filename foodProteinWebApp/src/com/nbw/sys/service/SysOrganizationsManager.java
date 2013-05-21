package  com.nbw.sys.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.nbw.common.Constants;
import com.nbw.common.dhtml.GridDataXml;
import com.nbw.common.util.CommonUtils;
import com.nbw.sys.dao.SysOrganizationsDAO;
import com.nbw.sys.dao.SysUsersDAO;
import com.nbw.sys.domain.SysOrganizations;
import com.nbw.sys.domain.SysOrganizationsDetail;

/**
 * 
 * SysOrganizationsManager
 * 
 * @author 狄巨礼 May 17, 2010 09:31:00 AM 
 * 
 */
public class SysOrganizationsManager{

	private SysOrganizationsDAO sysOrganizationsDAO;
	private SysUsersDAO sysUsersDAO;
	
	/**
	 * 处理DhtmlxGrid发过来的操作包括添加、删除、修改
	 * @param obj			  	要处理的对象
	 * @param request 	
	 * @return			返回DhtmlxGrid 接收的处理反馈结果（xml）
	 */
	public String process(SysOrganizations obj, HttpServletRequest request) throws IllegalAccessException, InvocationTargetException{
		String id = obj.getId();
		String code = obj.getCode();
		String xml = "";
		if (isCodeExist(id,code)){
			xml = Constants.ERROR_CODE_EXIST;
		} else {
			saveOrUpdate(obj);
			xml = Constants.DEFAULT_AJAX_SUCCESS + obj.getId();
		}
		return xml;
	}
	
	/**
	 * 通过id获取组织机构的附属信息，没有则new一个对象返回
	 * @param  id
	 * @return pObject
	 */
	public SysOrganizationsDetail getSysOrganizationsDetail(String id) {
		SysOrganizationsDetail sod = sysOrganizationsDAO.getSysOrganizationsDetail(id);
		if(sod == null){
			sod = new SysOrganizationsDetail();
			sod.setId(id);
		}
		//没有则new一个对象返回
		return sod;
	}
	
	/**
	 * 通过id得到对象
	 *
	 * @param  id
	 * @return pObject
	 */
	public SysOrganizations findByID(String id){
		return sysOrganizationsDAO.findByID(id);
	}
	
	/**
	 * 保存对象
	 *
	 * @param  sysOrganizations
	 */
	public boolean saveOrUpdate(SysOrganizations sysOrganizations) {
		if(sysOrganizations.getTreeId()==null){
			String pid = sysOrganizations.getParentId();
			SysOrganizations psm = findByID(pid);
			if(psm!=null){
				sysOrganizations.setTreeId(psm.getTreeId()+","+sysOrganizations.getId());
			}else{
				sysOrganizations.setTreeId(sysOrganizations.getId());
			}
		}
		return sysOrganizationsDAO.saveOrUpdate(sysOrganizations);
	}
	
	/**
	 * 删除组织机构，以及所有的子机构
	 *
	 * @param  id
	 * @throws Exception 
	 */
	public boolean delete(String ids) {
		boolean success = false;
		String [] idarray = ids.split(",");
		for(int i=0;i<idarray.length;i++){
			if(hasSubUser(idarray[i])){
				return false;
			}
			success=deleteSysOrganizations(idarray[i]);
		}
		return success;
	}
	
	/**
	 * 删除组织机构,和组织机构对应的附属信息
	 * @param  id
	 */
	private boolean deleteSysOrganizations(String id) {
		List<SysOrganizations> list = sysOrganizationsDAO.findChildByID(id);
		for(int i=0;i<list.size();i++){
			deleteSysOrganizations(list.get(i).getId());
		}
		if(sysOrganizationsDAO.deleteSysOrganizations(id)){
			//删除附属信息
			sysOrganizationsDAO.deleteObject(new SysOrganizationsDetail(id));
		}else{
			return false;
		}
		return true;
	}
	
	/**
	 * 判断该机构及所有子机构下是否有user 
	 * @param orgId
	 * @return 有则返回true 否则返回false
	 */
	private boolean hasSubUser(String orgId){
		List<SysOrganizations> list = sysOrganizationsDAO.findChildByID(orgId);
		for(int i=0;i<list.size();i++){
			SysOrganizations m = list.get(i);
			if(hasSubUser(m.getId())){
				return true;
			}
		}
		return sysUsersDAO.findSysUsersByOrgId(orgId).size()>0;
	}

	/** ******************************获取组织机构grid的xml开始******************************* */
	/**
	 * 根据组织机构Id 获取其子机构封装的xml
	 * 
	 * @param orgId
	 *            机构Id
	 * @return 子机构封装的xml
	 */
	public String getGridXml(String orgId) {
		GridDataXml gridDataXml = new GridDataXml();
		if (orgId == null || orgId.trim().equals("")) {
			orgId = Constants.DEFAULT_ROOT;
		}
		List<SysOrganizations> list = sysOrganizationsDAO.findChildByID(orgId);
		if (list != null) {
			for (SysOrganizations so : list) {
				gridDataXml.setId(so.getId());
				gridDataXml
						.setXmlData(CommonUtils.DealString(so.getParentId()));
				gridDataXml.setXmlData(CommonUtils.DealString(so.getCode()));
				gridDataXml.setXmlData(CommonUtils.DealString(so.getName()));
				gridDataXml.setXmlData(CommonUtils
						.DealString(so.getShortName()));
				gridDataXml.setXmlData(CommonUtils.DealString(so.getRemarks()));
				gridDataXml.setXmlData(so.getOrderNum()==null?"0":so.getOrderNum());
			}
		}
		return gridDataXml.getXml();
	}

	/** ******************************获取组织机构grid的xml结束******************************* */

	
	/********************************获取组织机构树的xml开始********************************/
	/**
	 * 获取组织机构树的xml
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
		List<SysOrganizations> list = sysOrganizationsDAO.findChildByID(id);
		if (Constants.DEFAULT_ROOT.equals(id)) {
			treeElement.addAttribute("id", "0");
			/** 建立XML文档的根节点item */
			Element rootElement = treeElement.addElement("item");

			rootElement.addAttribute("id", id);
			rootElement.addAttribute("text", sysOrganizationsDAO.findNameByID(id));
			rootElement.addAttribute("open", "1");
			rootElement.addAttribute("im0", "folderClosed.gif");
			rootElement.addAttribute("im1", "folderOpen.gif");
			rootElement.addAttribute("im2", "folderClosed.gif");
			rootElement.addAttribute("select", "yes");

			addOrganizations(list, rootElement);
		} else {
			treeElement.addAttribute("id", id);
			addOrganizations(list, treeElement);
		}
		return document.asXML();
	}
	
	/**
	 * 动态加载时增加下级节点（只有一级节点）
	 * 
	 * @param
	 * @return Element
	 */
	private Element addOrganizations(List<SysOrganizations> list, Element rElement) {
		for (SysOrganizations obj : list) {
			Element itemElement = rElement.addElement("item");
			itemElement.addAttribute("id", obj.getId());
			itemElement.addAttribute("text", obj.getName());
			// itemElement.addAttribute("open", "1");
			if (organizationIsLeaf(obj.getId())) {
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
	 * 判断该组织机构是否是叶子节点
	 * @param SysOrganizations Id
	 * @return 
	 */
	private boolean organizationIsLeaf(String id){
		List list = sysOrganizationsDAO.findChildByID(id);
		return list.size()<=0;
	}
	
	/**
	 * 根据模块Id一次性加载Id下的所有模块树xml，如果是ID==ROOT则加载整颗树
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
		List list = sysOrganizationsDAO.findChildByID(id);
		if (Constants.DEFAULT_ROOT.equals(id)) {
			treeElement.addAttribute("id", "0");
			/** 建立XML文档的根节点item */
			Element rootElement = treeElement.addElement("item");

			rootElement.addAttribute("id", id);
			rootElement.addAttribute("text", sysOrganizationsDAO.findNameByID(id));
			rootElement.addAttribute("open", "1");
			rootElement.addAttribute("im0", "folderClosed.gif");
			rootElement.addAttribute("im1", "folderOpen.gif");
			rootElement.addAttribute("im2", "folderClosed.gif");
			rootElement.addAttribute("select", "yes");

			addAllOrgNodes(list, rootElement);
		} else {
			treeElement.addAttribute("id", id);
			addAllOrgNodes(list, treeElement);
		}
		return document.asXML();
	}
	
	/**
	 * 递归加载时增加下级节点
	 * @param
	 * @return Element
	 */
	private Element addAllOrgNodes(List<SysOrganizations> list, Element rElement) {
		for (SysOrganizations obj : list) {
			Element itemElement = rElement.addElement("item");
			itemElement.addAttribute("id", obj.getId());
			itemElement.addAttribute("text", obj.getName());
			List listsub = sysOrganizationsDAO.findChildByID(obj.getId());
			// itemElement.addAttribute("open", "1");
			if (listsub.size()==0) {
				itemElement.addAttribute("im0", "iconText.gif");
				itemElement.addAttribute("im1", "iconText.gif");
				itemElement.addAttribute("im2", "iconText.gif");
			} else {
				itemElement.addAttribute("im0", "folderClosed.gif");
				itemElement.addAttribute("im1", "folderOpen.gif");
				itemElement.addAttribute("im2", "folderClosed.gif");
				itemElement.addAttribute("child", obj.getId());
				addAllOrgNodes(listsub, itemElement);
			}
		}
		return rElement;
	}
	/********************************获取组织机构树的xml结束********************************/
	/** 
	 * @param str
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SysOrganizations> getSysOrganizationsByStr(String str){
		String[] orgIdArray = str.split(",");
		List list = new ArrayList();
		for(int i=0;i<orgIdArray.length;i++){
			SysOrganizations so=sysOrganizationsDAO.findByID(orgIdArray[i]);
			list.add(so);
		}
		return list;
		
	}

	//*************************************set and get
	public SysOrganizationsDAO getSysOrganizationsDAO() {
		return sysOrganizationsDAO;
	}

	public void setSysOrganizationsDAO(SysOrganizationsDAO sysOrganizationsDAO) {
		this.sysOrganizationsDAO = sysOrganizationsDAO;
	}

	public String findNameByID(String id) {
		return sysOrganizationsDAO.findNameByID(id);
	}

	/**
	 * 判断编码信息不重复
	 * 
	 * @param id
	 * @param code
	 * @param codeId
	 * @return
	 */
	public boolean isCodeExist(String id, String code) {
		return sysOrganizationsDAO.isCodeExist(id,code);
	}

	public boolean isCodeExist(String code) {
		return sysOrganizationsDAO.isCodeExist(code);
	}

	public void saveOrUpdateObject(Object sod) {
		sysOrganizationsDAO.saveOrUpdateObject(sod);
	}

	public SysUsersDAO getSysUsersDAO() {
		return sysUsersDAO;
	}

	public void setSysUsersDAO(SysUsersDAO sysUsersDAO) {
		this.sysUsersDAO = sysUsersDAO;
	}
}

