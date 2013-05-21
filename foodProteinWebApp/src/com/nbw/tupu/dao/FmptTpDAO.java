package com.nbw.tupu.dao;

import java.util.List;

import com.nbw.common.GenericDAO;
import com.nbw.tupu.domain.FmptTp;

/**
 * 
 * FmptPtDAO 指纹图谱
 * 
 * @author 
 * 
 */
public class FmptTpDAO extends GenericDAO<FmptTp, String> {
	/**
	 * 根据用户输入的名字搜索
	 * @param name
	 * @return
	 */
	public List<FmptTp> findByName(String name){
		String hql = "select m from FmptTp  m where m.speciesName like '%" +name
		+"%'  or m.latinName like '%" + name +"%' order by m.id";
		return (List<FmptTp>) this.findByHQL(hql, null);
	}
	/**
	 * 通过种id查找数量
	 * @param spid
	 * @return
	 */
	public int findBySpid(Integer spid){
		String hql = "select m from FmptTp  m where m.speciesId = " +spid+" order by m.id";
		return this.findByHQL(hql, null).size();
	}
	 
}
 

