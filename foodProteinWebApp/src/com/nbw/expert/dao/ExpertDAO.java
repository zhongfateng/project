package com.nbw.expert.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.nbw.common.GenericDAO;
import com.nbw.common.util.PageBean;
import com.nbw.common.util.PageBeanUtil;
import com.nbw.expert.domain.Expert;
import com.nbw.threeStruct.domain.ThreeStructure;

public class ExpertDAO extends GenericDAO<Expert, String>{

	

	public PageBean getMoreExpert(int page,int pageSize)
	{
		
		int currentPage = PageBean.countCurrentPage(page);
		int offset = PageBean.countOffset(pageSize, currentPage);
		String hql="select e from Expert e ";
		List<Expert> expertList = (List<Expert>) findPagerByHQL(hql, offset, pageSize);
		String hqlAllRow = "select count(e.eid) from Expert e ";
		List li = findByHQL(hqlAllRow);
		int allRowSize = ((Long) li.get(0)).intValue();
		PageBean pageBean = PageBeanUtil.queryForPage(expertList, allRowSize, page, pageSize);
		return pageBean;
	}
	
	public List<Expert> getFourExpert()
	{
		String hql="select e from Expert e";
		Query query= getSession().createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(4);
	return query.list();
		
		
		
	}
	
	public Expert getDetailExpertByID(Integer id)
	{
	return	(Expert)getSession().get(Expert.class, id);
	}
	
	
	
}
