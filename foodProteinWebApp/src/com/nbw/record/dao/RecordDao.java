package com.nbw.record.dao;

import com.nbw.common.GenericDAO;
import com.nbw.record.domain.Record;

public class RecordDao  extends GenericDAO<Record, String>{

	
	
	public void UpdateRecordByNameAndTitle(String userName,String title)
	{
		
		String hql="update record set jf=jf-2,title= '"+title+"'"+" where userName= '"+userName+"' and type =0";
		this.findByHQL(hql, userName,title);
	}
	
	
	
	
	
}
