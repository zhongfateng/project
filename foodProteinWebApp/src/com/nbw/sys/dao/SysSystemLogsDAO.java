package com.nbw.sys.dao;

import java.util.Date;
import java.util.List;

import com.nbw.common.GenericDAO;
import com.nbw.common.util.DateUtils;
import com.nbw.sys.domain.SysSystemLogs;
import com.nbw.sys.domain.SysSystemLogsForm;

/**
 * 
 * SysCodesDAO
 * 
 * @author 狄巨礼 May 17, 2010 09:31:00 AM
 * 
 */
public class SysSystemLogsDAO extends GenericDAO<SysSystemLogs, String> {


	@SuppressWarnings("unchecked")
	public List<SysSystemLogsForm> findSysSystemLogs(String stime, String etime,
			String[] userIds, String[] moduleIds) {
		String hql = "select new com.nbw.sys.domain.SysSystemLogsForm(m,u.name,o.name) " +
				" from SysSystemLogs m,SysModules o,SysUsers u " +
				" where m.moduleId = o.id and m.userId = u.id and m.logTime >= ? and m.logTime < ? ";
		//默认搜索1000-01-01到当前时间的结果
		Date sdate = DateUtils.stringToDate("1000-01-01");
		Date edate = new Date();
		if(stime!=null && !"".equals(stime)){
			sdate = DateUtils.stringToDatetime(stime);
		}
		if(etime!=null && !"".equals(etime)){
			edate = DateUtils.stringToDatetime(etime);
		}
		if(userIds!=null && userIds.length>0 ){
			String hqlUserIds = "'"+userIds[0]+"'";
			for(int i=1;i<userIds.length;i++){
				hqlUserIds+=",'"+userIds[i]+"'";
			}
			hql += " and m.userId in ("+hqlUserIds+") ";
		}
		if(moduleIds!=null && moduleIds.length>0 ){
			String hqlModuleIds = "'"+moduleIds[0]+"'";
			for(int i=1;i<moduleIds.length;i++){
				hqlModuleIds+=",'"+moduleIds[i]+"'";
			}
			hql += " and m.moduleId in ("+hqlModuleIds+") ";
		}
		List list = this.findByHQL(hql,sdate,edate);
		return list;
	}

}


