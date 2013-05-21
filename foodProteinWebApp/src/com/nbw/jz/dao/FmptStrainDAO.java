package com.nbw.jz.dao;

import java.util.List;

import com.nbw.jz.domain.FmptStrain;
import com.nbw.sys.domain.SysModules;
import com.nbw.common.GenericDAO;

/**
 * 
 * FmptBioclassDAO 菌种
 * 
 * @author 
 * 
 */
public class FmptStrainDAO extends GenericDAO<FmptStrain, String> {
	/**
	 * 根据nodeId查找该条记录
	 * @param spid
	 * @return
	 */
	public FmptStrain findByNid(Integer id) {
		System.out.println(id+"********");
		String hql = "select m from FmptStrain  m where m.nodeId ="+id;
		return (FmptStrain) this.findByHQL(hql, null).get(0);
	}
	/**
	 * 根据id左右值
	 * @param spid
	 * @return
	 */
	public List<FmptStrain> findByTree(Integer lft,Integer rgh) {
		String hql = "select m from FmptStrain  m where m.lft BETWEEN " +lft
				+"  AND  " + rgh +" ORDER BY m.lft ASC ";
		return (List<FmptStrain>) this.findByHQL(hql, null);
	}
	/**
	 * 通过ID得到所有是菜单的下级节点
	 * 
	 * @param ID
	 * @return List
	 */
	public List<FmptStrain> findChildByID(Integer id) {
		String hql = "select m from FmptStrain m where m.parentid = ? order by m.nodeId ";
		return (List<FmptStrain>) this.findByHQL(hql, id);
	}

	/**
	 * 通过ID得到NAME
	 * 
	 * @param ID
	 * @return String
	 */
	public String findNameByID(Integer id) {
		String hql = "select m from FmptStrain m where m.nodeId = ? ";
		FmptStrain fs = (FmptStrain)this.findByHQL(hql, id).get(0);
		if(fs.getCname()!=null&&!"".equals(fs.getCname())){
			return fs.getEname()+"("+fs.getCname()+")";
		}else{
			return fs.getEname();
		}
	}
	/**
	 * 根据id查看更新日期
	 * @param id
	 * @return
	 */
	public String findDateByID(Integer id) {
		String hql = "select m from FmptStrain m where m.nodeId = ? ";
		FmptStrain fs = (FmptStrain)this.findByHQL(hql, id).get(0);
		return fs.getUpdatedate();
	}
	/**
	 * 更新数据
	 */
	public boolean  updateDataByID(int id,int jy,int dbz,int rna,int wx,int tp,String updatedate){
		String hql = "update FmptStrain m set m.jy ="+jy+", m.dbz = "+dbz+" ,m.rna= "+rna+" ,m.wx= "+rna+" ,m.tp = "+tp+ ",m.updatedate ='"+updatedate+"'  where m.nodeId ="+id;
		boolean success = false;
		try {
			this.executeHQL(hql);
			success = true;
		} catch (RuntimeException re) {
			throw re;
		} finally {
			return success;
		}
	}
	/**
	 * 更新日期
	 */
	public boolean  updateDateByID(int id,String date){
		String hql = "update FmptStrain m set m.updatedate ='"+date+"' where m.nodeId ="+id;
		boolean success = false;
		try {
			this.executeHQL(hql);
			success = true;
		} catch (RuntimeException re) {
			throw re;
		} finally {
			return success;
		}
	}
}
 

