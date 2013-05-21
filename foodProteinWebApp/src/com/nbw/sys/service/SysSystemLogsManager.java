package  com.nbw.sys.service;

import java.util.List;

import com.nbw.common.dhtml.GridDataXml;
import com.nbw.common.util.CommonUtils;
import com.nbw.common.util.DateUtils;
import com.nbw.sys.dao.SysSystemLogsDAO;
import com.nbw.sys.domain.SysSystemLogsForm;

/**
 * 
 * SysSystemLogsManager
 * 
 * @author 狄巨礼 May 17, 2010 09:31:00 AM
 * 
 */
public class SysSystemLogsManager{

	private SysSystemLogsDAO sysSystemLogsDAO;

	/**
	 * 根据起止时间，操作人，操作模块查询
	 * @param stime		查询开始时间
	 * @param etime		查询结束时间
	 * @param userIds		
	 * @param moduleIds
	 * @return 日志组成的grid xml
	 */
	public String getSearchGridXml(String stime, String etime, String[] userIds,
			String[] moduleIds) {
		GridDataXml gridDataXml = new GridDataXml();
		List<SysSystemLogsForm> list = sysSystemLogsDAO.findSysSystemLogs(stime,etime,userIds,moduleIds);
		if (list != null) {
			for (SysSystemLogsForm o : list) {
				gridDataXml.setId(o.getId());
				gridDataXml.setXmlData(DateUtils.datetimeToString(o.getLogTime()));
				gridDataXml.setXmlData(CommonUtils.DealString(o.getUserName()));
				gridDataXml.setXmlData(CommonUtils.DealString(o.getWorkIp()));
				gridDataXml.setXmlData(CommonUtils.DealString(o.getModuleName()));
				gridDataXml.setXmlData(CommonUtils.DealString(o.getSuccess()));
				gridDataXml.setXmlData(CommonUtils.DealString(o.getDescribe()));
			}
		}
		return gridDataXml.getXml();
	}
	


	public SysSystemLogsDAO getSysSystemLogsDAO() {
		return sysSystemLogsDAO;
	}

	public void setSysSystemLogsDAO(SysSystemLogsDAO sysSystemLogsDAO) {
		this.sysSystemLogsDAO = sysSystemLogsDAO;
	}

	
}

