package com.nbw.hygl.dao;

import java.util.List;

import com.nbw.common.GenericDAO;
import com.nbw.sys.domain.SysUsers;
import com.nbw.sys.domain.SysUsersDetail;

public class FmptUserDao extends GenericDAO<SysUsers, String>{


	
	/**
	 * 判断用户是否存在
	 * @param loginCode
	 * @return
	 */
	public boolean isLoginCodeExist(String loginCode) {
//		String hql = "select m from SysUsers m where  m.loginCode=? and m.validFlag != "+SysUsers.VALIDFLAG_FALSE;
		String hql = "select m from SysUsers m where  m.loginCode=? ";
		return this.findByHQL(hql,loginCode).size()>0;
	}
	
	public boolean isEELoginCodeExist(String loginCode) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * 得到该级别下边的所有用户
	 * @param jbId
	 * @return
	 */
	public List isUserJb(String jbId) {
		String hql = "from SysUsers m where m.jbid=? ";
		return this.findByHQL(hql,jbId);
	}
	/**
	 * 保存用户信息
	 * @param obj
	 * @return
	 */
	
	public void saveOrUpdateObject(Object obj) {
		this.getSession().saveOrUpdate(obj);
	}
	
	/**
	 * ID查询用户详细信息
	 * @param id
	 * @return
	 */
	
	public SysUsersDetail getBzptUsersDetail(String id) {
		String hql = "from SysUsersDetail m where m.id = ?";
		List list = findByHQL(hql, id);
		if(list.size() > 0){
			return (SysUsersDetail)list.get(0);
		}
		return null;
	}

	
}
