package com.nbw.sys.dao;

import java.util.List;

import com.nbw.common.GenericDAO;
import com.nbw.sys.domain.SysCodes;

/**
 * 
 * SysCodesDAO
 * 
 * @author 狄巨礼 May 17, 2010 09:31:00 AM
 * 
 */
public class SysCodesDAO extends GenericDAO<SysCodes, String> {

	/**
	 * 通过ID得到所有的下级节点
	 * 
	 * @param ID
	 * @return List
	 */
	public List<SysCodes> findChildByID(String id) {
		String hql = "select m from SysCodes m where m.parentId = ? and m.validFlag != "
				+ SysCodes.VALIDFLAG_FALSE + " order by m.orderNum ";
		return (List<SysCodes>) this.findByHQL(hql, id);
	}

	/**
	 * 通过codeID 得到所有的下级节点
	 * @param codeId
	 * @return
	 */
	public List<SysCodes> findChildByCode(String codeId) {
		String id = getByCode(codeId).getId();
		String hql = "select m from SysCodes m where m.parentId = ? and m.validFlag != "
				+ SysCodes.VALIDFLAG_FALSE + " order by m.orderNum ";
		return (List<SysCodes>) this.findByHQL(hql, id);
	}

	/**
	 * 通过ID得到NAME
	 * 
	 * @param ID
	 * @return String
	 */
	public String findNameByID(String id) {
		SysCodes obj = this.findByID(id);
		return obj.getName();
	}

	/**
	 * 判断 SysCode.id!=id 编码不重复,更新时使用
	 * 
	 * @param id
	 * @param code
	 * @return
	 */
	public boolean isCodeExist(String id, String code) {
		String hql = "select m from SysCodes m where m.id<>? and m.code=? and m.validFlag != "
				+ SysCodes.VALIDFLAG_FALSE;
		List list = this.findByHQL(hql, id, code);
		if (null != list && list.size() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 判断编码信息不重复
	 * 
	 * @param code
	 * @return
	 */
	public boolean isCodeExist(String code) {
		String hql = "select m from SysCodes m where  m.code=? and m.validFlag != "
				+ SysCodes.VALIDFLAG_FALSE;
		List list = this.findByHQL(hql, code);
		if (null != list && list.size() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 通过编码来查找
	 * 
	 * @param code
	 * @return
	 */
	public SysCodes getByCode(String code) {
		String hql = "select m from SysCodes m where  m.code=? and m.validFlag != "
				+ SysCodes.VALIDFLAG_FALSE;
		List list = this.findByHQL(hql, code);
		if (null != list && list.size() > 0) {
			return (SysCodes) list.get(0);
		}
		return null;

	}

}
