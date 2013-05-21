package com.nbw.usage.dao;

import java.util.List;

import org.hibernate.Query;

import com.nbw.common.GenericDAO;
import com.nbw.usage.domain.Usage;

public class UsageDao extends GenericDAO<Usage, String> {
	
	
	public String getUsageByTitle(String title)
	{
		
		String hql=" from Usage u where u.title = '"+title+"'";
		 return  ((Usage)(this.findByHQL(hql).get(0))).getBody();
		
		
	}
	
//	public  Usage getUsageByID(Integer id)
//	{
//		
//	return (Usage)getSession().get(Usage.class, id);	
//		
//	}
//	public void saveUsage(Usage u)
//	{
//		String sql =" insert into usage values('"+u.getId()+"','"+u.getTitle()+"','"+u.getBody()+"'"+")";
//		
//		
//		
//		
//	}
	
	public Usage getUsageByID(Integer id)
	{
		
		
		return (Usage)getSession().get(Usage.class, id);
	}
	
	
	public List<Usage> getLittleUsage()
	{
		String hql="select u from Usage u";
		Query query= getSession().createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(4);
	return query.list();
	}
	
	public void deleteUsageByID(String id)
	{
		String hql=" delete from organism.usage where id= '"+id+"'";
		this.executeSQL(hql, null);
		
	}

}
