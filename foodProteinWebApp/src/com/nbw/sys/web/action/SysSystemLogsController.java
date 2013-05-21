package com.nbw.sys.web.action;

import java.io.IOException;  

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.nbw.common.Constants;
import com.nbw.common.NbwController;
import com.nbw.common.util.CommonUtils;
import com.nbw.sys.domain.SysSystemLogs;
import com.nbw.sys.service.SysSystemLogsManager;

/**
 * 
 * SysSystemLogsController
 * 
 * @author 狄巨礼 May 17, 2010 09:31:00 AM
 * 
 */
public class SysSystemLogsController extends NbwController {

    private SysSystemLogsManager sysSystemLogsManager;
    
    /**
	 * constructor
	 *  
	 * @throws ClassNotFoundException
	 */
	public SysSystemLogsController() throws ClassNotFoundException {
		super();
	}

	/**
	 * 初始化页面
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 *  
	 * @throws ServletException, IOException
	 */
	public ModelAndView toIndex(HttpServletRequest request,
			HttpServletResponse response, SysSystemLogs command, BindException errors)
			throws ServletException, IOException {
		return new ModelAndView("sys/sysSystemLogsList");
	}

	
	/**
	 * 加载dhtmlgrid需要的xml，内容是根据编码parentId获取的子编码
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 *  
	 * @throws ServletException, IOException
	 */
	public ModelAndView loadGridXML(HttpServletRequest request,
			HttpServletResponse response, SysSystemLogs command, BindException errors)
			throws ServletException, IOException {
		response = CommonUtils.setCommonXmlResponse(response);
		String [] userId = null;
		String [] moduleId = null;
		String stime = request.getParameter("stime");
		String etime = request.getParameter("etime");
		String userIds = request.getParameter("userIds");
		String moduleIds = request.getParameter("moduleIds");
		if(userIds!=null && !userIds.trim().equals("")){
			userId = userIds.split(Constants.SELECT_REGEX);
		}
		if(moduleIds!=null && !moduleIds.trim().equals("")){
			moduleId = moduleIds.split(Constants.SELECT_REGEX);
		}
		String xml = sysSystemLogsManager
				.getSearchGridXml(stime,etime,userId,moduleId);
		response.getWriter().write(xml);
		return null;
	}
	
	
	
    //*******************************************************************************set and get	
	
	@Override
	protected void convertStringToDate(HttpServletRequest request,
			Object command) {
		
	}

	public SysSystemLogsManager getSysSystemLogsManager() {
		return sysSystemLogsManager;
	}

	public void setSysSystemLogsManager(SysSystemLogsManager sysSystemLogsManager) {
		this.sysSystemLogsManager = sysSystemLogsManager;
	}
}

