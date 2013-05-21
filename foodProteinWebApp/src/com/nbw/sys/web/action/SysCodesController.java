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
import com.nbw.sys.domain.SysCodes;
import com.nbw.sys.service.SysCodesManager;

/**
 * 
 * SysCodesController
 * 
 * @author 狄巨礼 May 17, 2010 09:31:00 AM 
 * 
 */
public class SysCodesController extends NbwController {

    private SysCodesManager sysCodesManager;
    
    /**
	 * constructor
	 *  
	 * @throws ClassNotFoundException
	 */
	public SysCodesController() throws ClassNotFoundException {
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
			HttpServletResponse response, SysCodes command, BindException errors)
			throws ServletException, IOException {
		return new ModelAndView("sys/syscodesList");
	}

	/**
	 * 保存数据
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 *  
	 * @throws ServletException, IOException
	 */
	public ModelAndView save(HttpServletRequest request,
			HttpServletResponse response, SysCodes command, BindException errors)
			throws ServletException, IOException {
		response = CommonUtils.setCommonXmlResponse(response);
		String nativeeditor_status = request.getParameter("!nativeeditor_status");
		String id = request.getParameter("gr_id");
		command.setId(id);
		
		//处理dhtmlgrid提交上来的操作，并返回dhtmlgrid所需要xml
		String xml = sysCodesManager.process(command,nativeeditor_status);
		
		response.getWriter().write(xml);
		return null;
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
			HttpServletResponse response, SysCodes command, BindException errors)
			throws ServletException, IOException {
		response = CommonUtils.setCommonXmlResponse(response);
		String parentId = request.getParameter("parentId");
		String xml = sysCodesManager.getGridXmlByPId(parentId);
		response.getWriter().write(xml);
		return null;
	}
	
	/**
	 * 加载dhtmltree需要的xml，内容是根据编码parentId获取的子编码
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 *  
	 * @throws ServletException, IOException
	 */
	public ModelAndView loadTreeXML(HttpServletRequest request,
			HttpServletResponse response, SysCodes command,
			BindException errors) throws ServletException, IOException {
		response = CommonUtils.setCommonXmlResponse(response);

		//如果id为空则视为首次加载
		String id = null == request.getParameter("id") ? Constants.DEFAULT_ROOT
				: request.getParameter("id");
		try {
			response.getWriter().write(sysCodesManager.getTreeXml(id));
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
    //*******************************************************************************set and get	
	public SysCodesManager getSysCodesManager() {
		return sysCodesManager;
	}

	public void setSysCodesManager(SysCodesManager sysCodesManager) {
		this.sysCodesManager = sysCodesManager;
	}
	
	@Override
	protected void convertStringToDate(HttpServletRequest request,
			Object command) {
		
	}
}

