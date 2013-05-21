package com.nbw.techDy.dao;

import java.util.List;

import com.nbw.common.GenericDAO;
import com.nbw.common.util.PageBean;
import com.nbw.common.util.PageBeanUtil;
import com.nbw.techDy.domain.TechDy;

public class TechDyDAO extends GenericDAO<TechDy, String>{
	
	
	public PageBean findByTime(int page,int pageSize){
		
		String hql = "select t from TechDy t order by ttime desc";
		
		
		int currentPage = PageBean.countCurrentPage(page);
		int offset = PageBean.countOffset(pageSize, currentPage);
		
		List<TechDy> tDList = (List<TechDy>) findPagerByHQL(hql, offset, pageSize);
		
		String hqlAllRow = "select count(t.tid) from TechDy t order by ttime desc";
		List li = findByHQL(hqlAllRow);
		int allRowSize = ((Long) li.get(0)).intValue();
		PageBean pageBean = PageBeanUtil.queryForPage(tDList, allRowSize, page, pageSize);
		
		return pageBean;
	}
	
	public TechDy findByTid(String id){
		String hql = "select t from TechDy t where t.tid = '"+id+"'";
		TechDy techDy = (TechDy) findByHQL(hql, null).get(0);
		techDy.setTsum(techDy.getTsum()+1);
		save(techDy);
		return (TechDy) findByHQL(hql, null).get(0);
	}

}
