package com.nbw.hygl.dao;

import java.util.List;

import com.nbw.common.GenericDAO;
import com.nbw.hygl.domain.FmptHyJb;

/**
 * 
 * FmptHyJbDAO
 * 
 * @author
 * 
 */
public class FmptHyJbDAO extends GenericDAO<FmptHyJb, String> {

	/**
	 * 获得有效的 用户列表
	 * 
	 * @return
	 */
	public List<FmptHyJb> findAllValid() {
		String hql = " from FmptHyJb jb where jb.validFlag != "
				+ FmptHyJb.VALIDFLAG_FALSE;
		return (List<FmptHyJb>) this.findByHQL(hql);
	}

	/**
	 * 逻辑删除用户
	 * 
	 * @param ID
	 *            用户Id
	 * @return 是否成功
	 */
	@SuppressWarnings("finally")
	public boolean deleteJb(String id) {
		String hql = "update FmptHyJb jb set jb.validFlag = "
				+ FmptHyJb.VALIDFLAG_FALSE + " where jb.id = ?";

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

	/**
	 * 物理删除用户
	 *
	 */
	public void deleteObject(Object obj) {
		this.getSession().delete(obj);
	}

}
