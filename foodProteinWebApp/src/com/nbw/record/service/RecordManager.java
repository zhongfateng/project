package com.nbw.record.service;

import com.nbw.record.dao.RecordDao;

public class RecordManager {
	
	private RecordDao recordDao;

	public RecordDao getRecordDao() {
		return recordDao;
	}

	public void setRecordDao(RecordDao recordDao) {
		this.recordDao = recordDao;
	}

	
	
	public void UpdateRecordByNameAndTitle(String userName,String title)
	{
		
	this.recordDao.UpdateRecordByNameAndTitle(userName, title);	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
