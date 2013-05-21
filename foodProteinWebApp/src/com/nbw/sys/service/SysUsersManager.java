package com.nbw.sys.service;

import java.util.Date;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.nbw.common.Constants;
import com.nbw.common.dhtml.GridDataXml;
import com.nbw.common.util.CommonUtils;
import com.nbw.common.util.MD5Code;
import com.nbw.sys.dao.SysOrganizationsDAO;
import com.nbw.sys.dao.SysUsersDAO;
import com.nbw.sys.domain.SysOrganizations;
import com.nbw.sys.domain.SysUsers;
import com.nbw.sys.domain.SysUsersDetail;


/**
 * 
 * SysUsersManager
 * 
 * @author 狄巨礼 May 17, 2010 09:31:00 AM
 * 
 */
public class SysUsersManager {

	private SysUsersDAO sysUsersDAO;
	private SysOrganizationsDAO sysOrganizationsDAO;
	
	
	
	/**
	 * 通过id得到对象
	 * 
	 * @param id
	 * @return pObject
	 */
	public SysUsers findByID(String id) {
		return sysUsersDAO.findByID(id);
	}

	public SysUsers findByUserName(String userName)
	{
		 return this.sysUsersDAO.findByUserName(userName);
		
	}
	
	public SysUsers findByIdWithOrg(String id) {
		return sysUsersDAO.findByIdWithOrg(id);
	}
	
	public String findEmail(String name){
		String uemail = this.sysUsersDAO.findEmail(name);
		return uemail;
	}
	
	
	/**
	 * 获得该对象所有数据的列表
	 * 
	 * @return list
	 */
	public List<SysUsers> findAll() {
		return sysUsersDAO.findAll();
	}

	/**
	 * 保存对象
	 * 
	 * @param sysUsers
	 * @param userId
	 */
	public boolean save(SysUsers sysUsers) {
		sysUsers.setCreateTime(new Date());
		return sysUsersDAO.save(sysUsers);
	}

	/**
	 * 更新用户级别
	 * 
	 * @param sysUsers
	 * @return
	 */
	public void updateUserJb(String userID, String jbID) {
		SysUsers user = findByID(userID);
		user.setJbid(jbID);
		sysUsersDAO.update(user);
	}

	/**
	 * 更新对象
	 * 
	 * @param sysUsers
	 */
	public boolean update(SysUsers sysUsers) {
		SysUsers su = sysUsersDAO.findByID(sysUsers.getId());
		su.setLoginCode(sysUsers.getLoginCode());
		su.setName(sysUsers.getName());
		su.setOrderNum(sysUsers.getOrderNum());
		su.setRemarks(sysUsers.getRemarks());
		su.setSysOrganizationsId(sysUsers.getSysOrganizationsId());
		return sysUsersDAO.update(su);
	}

	/**
	 * 删除对象
	 * 
	 * @param id
	 */
	public boolean delete(String ids) {
		boolean success = false;
		try {
			String[] idarray = ids.split(",");
			for (int i = 0; i < idarray.length; i++) {
				success = sysUsersDAO.deleteSysUsers(idarray[i]);
				sysUsersDAO.deleteObject(new SysUsersDetail(idarray[i]));
				// 缺少删除角色对用户的关系表！～～

			}
		} catch (Exception re) {
			success = false;
			throw re;
		} finally {
			return success;
		}
	}

	/**
	 * 根据组织机构ID获取该机构下的所有用户
	 * 
	 * @param orgId
	 *            当orgId为空获“”时获取所有用户。
	 */
	public List<SysUsers> findSysUsers(String orgId) {
		if (orgId == null || orgId.trim().equals("")
				|| orgId.trim().equals(Constants.DEFAULT_ROOT)) {
			return sysUsersDAO.findAllSysUsers();
		}
		return sysUsersDAO.findSysUsersByOrgId(orgId);
	}

	/** ******************************获取用户grid的xml开始******************************* */
	/**
	 * 根据组织机构Id 获取其机构下的用户封装的xml
	 * 
	 * @param orgId
	 *            机构Id
	 * @return 机构下的用户封装的xml
	 */
	public String getGridXmlByOrgId(String orgId) {
		GridDataXml gridDataXml = new GridDataXml();
		List<SysUsers> list = null;
		if (orgId == null || orgId.trim().equals("")
				|| orgId.trim().equals(Constants.DEFAULT_ROOT)) {
			list = sysUsersDAO.findAllSysUsers();
		} else {
			list = sysUsersDAO.findSysUsersByOrgId(orgId);
		}
		if (list != null) {
			for (SysUsers sysUsers : list) {
				SysOrganizations org = sysOrganizationsDAO.findByID(sysUsers
						.getSysOrganizationsId());
				gridDataXml.setId(sysUsers.getId());
				gridDataXml.setXmlData(CommonUtils.DealString(sysUsers
						.getSysOrganizationsId()));
				gridDataXml.setXmlData(CommonUtils.DealString(sysUsers
						.getLoginCode()));
				gridDataXml.setXmlData(CommonUtils.DealString(sysUsers
						.getName()));
				gridDataXml.setXmlData(CommonUtils.DealString(org.getName()));
				gridDataXml.setXmlData(CommonUtils.DealString(sysUsers
						.getRemarks()));
				gridDataXml.setXmlData(sysUsers.getOrderNum() == null ? "0"
						: sysUsers.getOrderNum());
			}
		}
		String xml = gridDataXml.getXml();
		return xml;
	}
	/**
	 * 密码重置
	 */
	public boolean updateCheckPass(String id)
	{
		MD5Code md5=new MD5Code();
		SysUsers sysuesr=findByID(id);
		sysuesr.setPassword(md5.getMD5ofStr("123456"));
		return sysUsersDAO.update(sysuesr);
		
		
	}

	/** ******************************获取用户grid的xml结束******************************* */

	/**
	 * 得到一次加载用户树的生成串
	 * 
	 * @param
	 * @return String
	 */
	public String singleLoadUser() {
		/** 建立document对象 */
		Document document = DocumentHelper.createDocument();
		/** 建立XML文档的根tree */
		Element treeElement = document.addElement("tree");
		treeElement.addAttribute("id", "0");
		/** 建立XML文档的根节点item */
		Element rootElement = treeElement.addElement("item");

		List<SysOrganizations> listOrg = sysOrganizationsDAO
				.findAllSysOrganizations();
		List<SysUsers> listUser = null;
		listUser = sysUsersDAO.findAllSysUsers();

		for (SysOrganizations obj : listOrg) {
			if ("-1".equals(obj.getParentId())) {
				rootElement.addAttribute("id", obj.getId());
				rootElement.addAttribute("text", obj.getName());
				rootElement.addAttribute("open", "1");
				rootElement.addAttribute("im0", "folderClosed.gif");
				rootElement.addAttribute("im1", "folderOpen.gif");
				rootElement.addAttribute("im2", "folderClosed.gif");
				/** 建立XML文档的节点item */
				addOrgUserNodes(listOrg, listUser, rootElement, obj.getId());
				break;
			}
		}
		return document.asXML();
	}

	/**
	 * 增加用户节点
	 * 
	 * @param
	 * @return Element
	 */
	public Element addOrgUserNodes(List<SysOrganizations> listOrg,
			List<SysUsers> listUser, Element rElement, String orgID) {
		for (SysOrganizations obj : listOrg) {
			if (orgID.equals(obj.getParentId())) {
				Element itemElement = rElement.addElement("item");
				itemElement.addAttribute("id", obj.getId());
				itemElement.addAttribute("text", obj.getName());
				// itemElement.addAttribute("open", "1");
				itemElement.addAttribute("im0", "folderClosed.gif");
				itemElement.addAttribute("im1", "folderOpen.gif");
				itemElement.addAttribute("im2", "folderClosed.gif");
				addOrgUserNodes(listOrg, listUser, itemElement, obj.getId());
			}
		}
		for (SysUsers obj : listUser) {
			if (orgID.equals(obj.getSysOrganizationsId())) {
				Element itemElement = rElement.addElement("item");
				itemElement.addAttribute("id", "ADMUSERS_" + obj.getId());
				itemElement.addAttribute("text", obj.getName());
				// itemElement.addAttribute("open", "1");
				itemElement.addAttribute("im0", "userLeaf2.gif");
				itemElement.addAttribute("im1", "userLeaf2.gif");
				itemElement.addAttribute("im2", "userLeaf2.gif");
			}
		}
		return rElement;
	}

	/**
	 * 验证用户名密码是否正确，正确则返回该用户
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public SysUsers checkSysUser(String username, String password) {
		SysUsers su = sysUsersDAO.findByUsernameAndPwd(username, password);
		return su;
	}

	public SysUsers checkSysUserHt(String username, String password) {
		SysUsers su = sysUsersDAO.findByUsernameAndPwdHt(username, password);
		return su;
	}
	
	public SysUsers getUsersBycode(String username ) {
		SysUsers su = sysUsersDAO.getUsersBycode(username);
		return su;
	}
	
	/**
	 * 验证用户名 密码
	 * @param userName
	 * @param passWord
	 * @return
	 */
	public Boolean validateUser(String userName,String passWord){
		List<SysUsers> list=this.sysUsersDAO.validate(userName,passWord);
		if(list==null||list.size()==0){
			return false;
		}else{
			
			return true;
		}
	}
	
	public SysUsers findSysUserByName(String name){
		List<SysUsers> user = this.sysUsersDAO.findByProperty("loginCode", name);
		if(user.size()!=0){
			return user.get(0);
		}else{
			
			return null;
		}
		
	}

	/**
	 * 验证用户ID与key中的是否一致
	 * @param userId
	 * @return
	 */
	public Boolean validateUserId(String userId){
		List list=this.sysUsersDAO.validateUserId(userId);
		if(list.size()>0){
			return true;
		}else{
			return false;
		}
	}
	public List findinfobyid(String userId)
	{
	  List list=sysUsersDAO.findinfobyid(userId);
	  
		return list;
	}
	// *************************************set and get
	public SysUsersDAO getSysUsersDAO() {
		return sysUsersDAO;
	}

	public void setSysUsersDAO(SysUsersDAO sysUsersDAO) {
		this.sysUsersDAO = sysUsersDAO;
	}

	public boolean isLoginCodeExist(String loginCode) {
		return sysUsersDAO.isLoginCodeExist(loginCode);
	}

	public boolean isLoginCodeExist(String id, String loginCode) {
		return sysUsersDAO.isLoginCodeExist(id, loginCode);
	}

	public SysUsersDetail getSysUsersDetail(String id) {
		SysUsersDetail sud = sysUsersDAO.getSysUsersDetail(id);
		if (sud == null) {
			sud = new SysUsersDetail();
			sud.setId(id);
		}
		return sud;
	}

	public void saveOrUpdateObject(Object obj) {
		sysUsersDAO.saveOrUpdateObject(obj);
	}

	public SysOrganizationsDAO getSysOrganizationsDAO() {
		return sysOrganizationsDAO;
	}

	public void setSysOrganizationsDAO(SysOrganizationsDAO sysOrganizationsDAO) {
		this.sysOrganizationsDAO = sysOrganizationsDAO;
	}
}
