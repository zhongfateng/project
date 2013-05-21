package com.nbw.testOrg.dao;

import java.util.List;

import com.nbw.common.GenericDAO;
import com.nbw.common.util.PageBean;
import com.nbw.common.util.PageBeanUtil;
import com.nbw.testOrg.domain.TestOrg;

public class TestOrgDAO extends GenericDAO<TestOrg, String>{
	
	
	
	public List<String> findByOrgAddr(){
		
		String hql = "select t.oprovince from TestOrg t group by t.oprovince";
		return (List<String>)findByHQL(hql, null);
	}
	
	
	
	public PageBean findByNameAndAddr(String name,String addr,int page,int pageSize){
		
		int currentPage = PageBean.countCurrentPage(page);
		int offset = PageBean.countOffset(pageSize, currentPage);
		String hql = "select t from TestOrg t where ";
		String hqlAllRow = "select count(t.tid) from TestOrg t where ";
		if("".equals(name)||name.equals("请输入机构名称")){
			hql = hql + " t.oprovince = '" + addr + "'";
			hqlAllRow = hqlAllRow + " t.oprovince = '" + addr + "'";
		}else if("".equals(addr)){
			hql = hql + " t.oname like '%" + name + "%'";
			hqlAllRow = hqlAllRow+ " t.oname like '%" + name + "%'";
		}else{
			hql = hql + " t.oname like '%"+name +"%' and t.oprovince = '"+addr+"'";
			hqlAllRow = hqlAllRow + " t.oname like '%"+name +"%' and t.oprovince = '"+addr+"'";
		}
		
		List<TestOrg> testOrgList = (List<TestOrg>) findPagerByHQL(hql, offset, pageSize);
		
		List li = findByHQL(hqlAllRow);
		int allRowSize = ((Long) li.get(0)).intValue();
		PageBean pageBean = PageBeanUtil.queryForPage(testOrgList, allRowSize, page, pageSize);
		return pageBean;
	}
	
	
	public TestOrg findByTid(String id){
		
		String hql = "select t from TestOrg t where t.tid = '"+id+"'";
		return (TestOrg) findByHQL(hql, null).get(0);
	}
	
	
	public List<TestOrg> findIndexFouth(){
		String hql = "select t from TestOrg t where ";
		
		return null;
		
	}
	

}
