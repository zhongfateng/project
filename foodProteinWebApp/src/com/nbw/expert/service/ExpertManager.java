package com.nbw.expert.service;

import java.util.List;

import com.nbw.common.util.PageBean;
import com.nbw.expert.dao.ExpertDAO;
import com.nbw.expert.domain.Expert;

public class ExpertManager {

	
	
	
	private ExpertDAO expertDao;
	
	public PageBean getMoreExpert(int page,int pageSize)
	{
		
		return this.expertDao.getMoreExpert(page,pageSize);
		
		
	}
	public Expert getDetailExpertByID(Integer id)
	{
		
		return this.expertDao.getDetailExpertByID(id);
		
	}
	
	public List<Expert> getFourExpert()
	{
		
		return this.expertDao.getFourExpert();
		
	}
	
	

	public ExpertDAO getExpertDao() {
		return expertDao;
	}

	public void setExpertDao(ExpertDAO expertDao) {
		this.expertDao = expertDao;
	}
	
	
	
	
	
	
}
