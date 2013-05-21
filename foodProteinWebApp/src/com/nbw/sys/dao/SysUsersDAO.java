package com.nbw.sys.dao;

import java.util.ArrayList;
import java.util.List;

import com.nbw.common.GenericDAO;
import com.nbw.sys.domain.SysOrganizations;
import com.nbw.sys.domain.SysUsers;
import com.nbw.sys.domain.SysUsersDetail;


/**
 * 
 * SysUsersDAO
 * 
 * @author 狄巨礼 May 17, 2010 09:31:00 AM
 * 
 */
public class SysUsersDAO extends GenericDAO<SysUsers, String> {
	
	
	public String findEmail(String name){
		String sql = "SELECT char_16 FROM sys_users_detail LEFT JOIN sys_users ON sys_users_detail.id = sys_users.id WHERE sys_users.login_code = '"+name+"'";
		List<String> eList = (List<String>) findBySQL(sql, null);
		if(eList.size()!=0){
			return eList.get(0);
		}else{
			return null;
		}
	}
	
	
	/**
	 * 逻辑删除用户
	 * 
	 * @param ID
	 *            用户Id
	 * @return 是否成功
	 */
	public boolean deleteSysUsers(String id) {
		String hql = "update SysUsers m set m.validFlag = "
				+ SysUsers.VALIDFLAG_FALSE + " where m.id = ?";

		boolean success = false;
		try {
			this.executeHQL(hql, id);
			success = true;
		} catch (RuntimeException re) {
			throw re;
		} finally {
			return success;
		}
	}
	public SysUsers findByUserName(String userName)
	{
		
		String hql=" from SysUsers m where m.loginCode= '"+userName+"'";
	return 	(SysUsers)this.findByHQL(hql, null).get(0);
	}

	public List<SysUsers> findAllSysUsers() {
		String hql = " from SysUsers m where m.validFlag != "
				+ SysUsers.VALIDFLAG_FALSE + " order by m.orderNum  ";
		return (List<SysUsers>) this.findByHQL(hql);
	}

	/**
	 * 根据组织机构Id获取下面所有用户
	 * 
	 * @param orgId
	 * @return
	 */
	public List<SysUsers> findSysUsersByOrgId(String orgId) {
		String hql = " from SysUsers m where m.validFlag != "
				+ SysUsers.VALIDFLAG_FALSE
				+ " and m.sysOrganizationsId = ?  order by m.orderNum ";
		return (List<SysUsers>) this.findByHQL(hql, orgId);
	}

	public boolean isLoginCodeExist(String loginCode) {
		String hql = "select m from SysUsers m where  m.loginCode=? and m.validFlag != "
				+ SysUsers.VALIDFLAG_FALSE;
		return this.findByHQL(hql, loginCode).size() > 0;
	}

	public boolean isLoginCodeExist(String id, String loginCode) {
		String hql = "select m from SysUsers m where m.id<>? and m.loginCode=? and m.validFlag != "
				+ SysUsers.VALIDFLAG_FALSE;
		return this.findByHQL(hql, id, loginCode).size() > 0;
	}

	public SysUsersDetail getSysUsersDetail(String id) {
		String hql = "from SysUsersDetail m where m.id = ?";
		List list = findByHQL(hql, id);
		if (list.size() > 0) {
			return (SysUsersDetail) list.get(0);
		}
		return null;
	}

	/**
	 * 根据用户名密码返回该用户，没有则返回空
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public SysUsers findByUsernameAndPwd(String username, String password) {
		String hql = " from SysUsers m where m.validFlag != "
				+ SysUsers.VALIDFLAG_FALSE
				+ " and m.loginCode = ? and m.password= ?  order by m.orderNum ";
		List<SysUsers> list = (List<SysUsers>) this.findByHQL(hql, username,
				password);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 根据用户名密码返回该用户，没有则返回空
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public SysUsers findByUsernameAndPwdHt(String username, String password) {
		String hql = " from SysUsers m where m.validFlag != "
				+ SysUsers.VALIDFLAG_FALSE
				+ " and m.loginCode = ? and m.password= ? and m.sysOrganizationsId <> 'C4564085C0B00002D12A1C706DF08FF0' order by m.orderNum ";
		List<SysUsers> list = (List<SysUsers>) this.findByHQL(hql, username,
				password);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public void saveOrUpdateObject(Object obj) {
		this.getSession().saveOrUpdate(obj);
	}

	/*
	 * 物理删除
	 */
	public void deleteObject(Object obj) {
		this.getSession().delete(obj);
	}

	/**
	 * 关联查询
	 * 
	 * @param id
	 * @return
	 */
	public SysUsers findByIdWithOrg(String id) {
		String sql = "from SysUsers as su,SysOrganizations as org where su.sysOrganizationsId=org.id and su.id= '"+id+"'";
		List list = this.getHibernateTemplate().find(sql);
		SysUsers su = new SysUsers();
		Object[] ss = (Object[]) list.get(0);
		su = ((SysUsers) ss[0]);
		su.setSysOrganizations((SysOrganizations) ss[1]);
		return su;
	}
	/**
	 * 验证用户名与密码
	 * @param userName
	 * @param passWord
	 * @return boolean
	 */
	public List validate(String userName, String passWord) {
		// TODO Auto-generated method stub
		String sql="from SysUsers as u where u.loginCode='"+userName+"' and u.password='"+passWord+"'";
		List list=this.findByHQL(sql);
		return list;
	}
	
	/**
	 * 验证用户名与密码
	 * @param userName
	 * @param passWord
	 * @return boolean
	 */
	public SysUsers getUsersBycode(String userName) {
		// TODO Auto-generated method stub
		String sql="from SysUsers as u where u.id='"+userName+"'";
		List<SysUsers> list = this.getHibernateTemplate().find(sql);
		SysUsers su = new SysUsers();
		if (list==null||list.size()<=0){
		  return null;	
		}else{
			SysUsers ss =  list.get(0);
			return ss;
		}
	}
	
	
	public List validateUserId(String userId){
		String hql="select count(*) from BzptUsbkey as u where u.usbkeyName='"+userId+"'";
		List list=this.getHibernateTemplate().find(hql);
		return list;
	}
	public List findinfobyid(String userId)
	{
		String sql="select t.name,td.char_17 from sys_users t,sys_users_detail td where td.id=t.id and t.id=?";
		Object[] obc=(Object[]) this.findBySQL(sql, userId).get(0);
		List list=new ArrayList();
		list.add(obc[0]==null?" ":obc[0].toString());
		//list.add(obc[1]==null?" ":obc[1].toString());
		try {
			list.add(obc[1].toString());	
		} catch (Exception e) {
			// TODO: handle exception
			list.add("电话号码为空");
		}
		return list;
		
		
	}
	
	
}
