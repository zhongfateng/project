package com.nbw.usage.service;

import java.util.List;

import com.nbw.usage.dao.UsageDao;
import com.nbw.usage.domain.Usage;

public class UsageManager {
	private UsageDao usageDao;

	public UsageDao getUsageDao() {
		return usageDao;
	}

	public void setUsageDao(UsageDao usageDao) {
		this.usageDao = usageDao;
	}
	
	public boolean save(Usage u)
	{
	return 	this.usageDao.save(u);
		
		
		
	}
	public String getUsageByTitle(String title)
	{
		
		
	return 	this.usageDao.getUsageByTitle(title);
		
		
		
	}
	
	public List<Usage> getLittleUsage()
	{
		
		 return this.usageDao.getLittleUsage();
		
	}
	public Usage getUsageByID(Integer id)
	{
		
		return this.usageDao.getUsageByID(id);
		
		
	}
	public List<Usage> getAll()
	{
		
	return 	this.usageDao.findAll();
		
	}
	
//	public void saveUsage(Usage usage) {
//		this.usageDao.save(usage);
//	}
	public Boolean saveUsage(Usage usage) {
	return	this.usageDao.save(usage);	}
	
	public Usage findById(Integer id)
	{
		
		
		return this.usageDao.getUsageByID(id);
		}
	

    /**
	 * 编辑对象
	 *
	 * @param  testtt
	 */
	public void editUsage(Usage usage) {
		this.usageDao.update(usage);
	}
	
	public void deleteUsage(String id)
	{
		
		this.usageDao.deleteUsageByID(id);
		
		
	}
	
	
	
}
