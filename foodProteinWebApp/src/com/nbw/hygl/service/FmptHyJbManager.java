package com.nbw.hygl.service;

import java.util.List;

import com.nbw.hygl.dao.FmptHyJbDAO;
import com.nbw.hygl.domain.FmptHyJb;



/**
 * 
 * FmptHyJbManager
 * 
 * @author
 * 
 */
public class FmptHyJbManager {

	private FmptHyJbDAO fmpthyjbdao;

	/**
	 * 通过id得到对象
	 * 
	 * @param id
	 * @return pObject
	 */
	public FmptHyJb findById(String id) {
		if (id == null || "".equals(id)) {
			return null;
		} else {
			FmptHyJb pObject = fmpthyjbdao.findByID(id);
			return pObject;
		}
	}

	/**
	 * 获得该对象所有数据的列表
	 * 
	 * @return list
	 */
	public List getAll() {
		return fmpthyjbdao.findAll();
	}

	/**
	 * 获得有效的 数据列表
	 * 
	 * @return
	 */
	public List getAllByValid() {
		return fmpthyjbdao.findAllValid();
	}

	/**
	 * 逻辑删除
	 * 
	 * @param id
	 */
	public void deleteJb(String id) {
		fmpthyjbdao.deleteJb(id);
	}

	/**
	 * 保存对象
	 * 
	 * @param FmptHyJb
	 */
	public void saveFmptHyJb(FmptHyJb FmptHyJb) {
		fmpthyjbdao.save(FmptHyJb);
	}

	/**
	 * 编辑对象
	 * 
	 * @param FmptHyJb
	 */
	public void editFmptHyJb(FmptHyJb FmptHyJb) {
		fmpthyjbdao.update(FmptHyJb);
	}

	/**
	 * 删除对象
	 * 
	 * @param id
	 */
	public void deleteFmptHyJb(String id) {
		fmpthyjbdao.delete(id);
	}

	public FmptHyJbDAO getFmpthyjbdao() {
		return fmpthyjbdao;
	}

	public void setFmpthyjbdao(FmptHyJbDAO fmpthyjbdao) {
		this.fmpthyjbdao = fmpthyjbdao;
	}

	// *******************************************************************************set
	// and get

}
