package com.nbw.sys.web.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.nbw.common.Constants;
import com.nbw.common.NbwController;
import com.nbw.common.util.CommonUtils;
import com.nbw.sys.domain.SysModuleActions;
import com.nbw.sys.domain.SysModules;
import com.nbw.sys.service.SysModulesManager;

/**
 * 
 * SysModulesController
 * 
 * @author 狄巨礼 May 17, 2010 09:31:00 AM
 * 
 */
public class SysModulesController extends NbwController {

    private SysModulesManager sysModulesManager;
    
    /**
	 * constructor
	 *  
	 * @throws ClassNotFoundException
	 */
	public SysModulesController() throws ClassNotFoundException {
		super();
	}

	/**
	 * 初始页面
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
			HttpServletResponse response, SysModules command, BindException errors)
			throws ServletException, IOException {
		return new ModelAndView("sys/sysmodulesList");
	}
	
	/**
	 * 添加页面
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * 
	 * @throws ServletException
	 *             , IOException
	 */
	public ModelAndView toFormPage(HttpServletRequest request,
			HttpServletResponse response, SysModules command,
			BindException errors) throws ServletException, IOException {
		return new ModelAndView("sys/sysmodulesForm");
	}

	/**
	 * 根据moduleId 获取模块 并返回相应的jsonObject
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * 
	 * @throws ServletException
	 *             , IOException
	 */
	public ModelAndView getFormData(HttpServletRequest request,
			HttpServletResponse response, SysModules command,
			BindException errors) throws ServletException, IOException {
		response = CommonUtils.setCommonHtmlResponse(response);
		PrintWriter out = response.getWriter();

		String id = null == request.getParameter("id") ? Constants.DEFAULT_ROOT
				: request.getParameter("id");
		command = sysModulesManager.findByID(id);
		if (null != command) {
			if (Constants.DEFAULT_ROOT.equals(id)) {
				command.setParentName("无");
			} else {
				command.setParentName(sysModulesManager.findNameByID(command.getParentId()));
			}
			JSONObject jObj = JSONObject.fromObject(command);
			out.print(jObj);
		} else {
			out.print(Constants.DEFAULT_AJAX_FAILURE);
		}
		out.close();
		return null;
	}

	/**
	 * 保存数据
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 *  
	 * @throws ServletException, IOException
	 */
	public ModelAndView save(HttpServletRequest request,
			HttpServletResponse response, SysModules command, BindException errors)
			throws ServletException, IOException {
		response = CommonUtils.setCommonHtmlResponse(response);
		PrintWriter out = response.getWriter();
		//处理dhtmlgrid提交上来的操作，并返回dhtmlgrid所需要xml
		String xml = sysModulesManager.process(command);
		out.write(xml);
		out.close();
		return null;
	}
	
	/**
	 * 保存模块action
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 *  
	 * @throws ServletException, IOException
	 */
	public ModelAndView updateAction(HttpServletRequest request,
			HttpServletResponse response, SysModules command, BindException errors)
			throws ServletException, IOException {
		response = CommonUtils.setCommonXmlResponse(response);
			String id = request.getParameter("gr_id");
			String moduleId = request.getParameter("c0");
			String code = request.getParameter("c1");
			String name = request.getParameter("c2");
			String methods = request.getParameter("c3");
			SysModuleActions sma = new SysModuleActions();
			sma.setId(id);
			sma.setCode(CommonUtils.StringToInteger(code));
			sma.setName(name);
			sma.setModuleId(moduleId);
			sma.setMethods(methods);
		
		String nativeeditor_status = request.getParameter("!nativeeditor_status");
		
		String xml = "";
		if ("updated".equals(nativeeditor_status)) {
			sysModulesManager.updateSysModuleActions(sma);
			xml = CommonUtils.getDhtmlxGridXml("update", id,id, "update success!");
		} else if ("inserted".equals(nativeeditor_status)) {
			sysModulesManager.saveSysModuleActions(sma);
			xml = CommonUtils.getDhtmlxGridXml("insert", id, id, "");
		} else if ("deleted".equals(nativeeditor_status)) {
			sysModulesManager.deleteSysModuleActions(sma);
			xml = CommonUtils.getDhtmlxGridXml("delete", id, id, "");
		}
		response.getWriter().write(xml);
		return null;
	}
	
	/**
	 * 删除数据
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * 
	 * @throws ServletException
	 *             , IOException
	 */
	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response, SysModules command,
			BindException errors) throws ServletException, IOException {
		response = CommonUtils.setCommonHtmlResponse(response);
		String ids = request.getParameter("ids");
		PrintWriter out = response.getWriter();

		if (sysModulesManager.delete(ids)) {
			out.write(Constants.DEFAULT_AJAX_SUCCESS);
		} else {
			out.write(Constants.ERROR_DELETE);
		}
		out.close();
		return null;
	}
	
	/**
	 * 加载dhtmlgrid需要的xml，内容是根据模块parentId获取的子模块
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * 
	 * @throws ServletException
	 *             , IOException
	 */
	public ModelAndView loadGridXML(HttpServletRequest request,
			HttpServletResponse response, SysModules command, BindException errors)
			throws ServletException, IOException {
		response = CommonUtils.setCommonXmlResponse(response);
		
		String id = request.getParameter("id");
		
		//根据模块Id 获取其子模块封装的xml
		String xml = sysModulesManager.getGridXml(id);
		response.getWriter().write(xml);
		return null;
	}
	
	/**
	 * 加载dhtmlgrid需要的xml，内容是根据模块parentId获取的所有操作
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * 
	 * @throws ServletException
	 *             , IOException
	 */
	public ModelAndView loadActionGridXML(HttpServletRequest request,
			HttpServletResponse response, SysModules command, BindException errors)
			throws ServletException, IOException {
		response = CommonUtils.setCommonXmlResponse(response);
		
		String moduleId = request.getParameter("moduleId");
		
		//根据模块Id 获取模块的SysModuleActions封装的xml
		String xml = sysModulesManager.getActionGridXML(moduleId);
		response.getWriter().write(xml);
		return null;
	}
	
	/**
	 * 加载dhtmltree需要的xml，内容是根据模块parentId获取的子模块
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
			HttpServletResponse response, SysModules command,
			BindException errors) throws ServletException, IOException {
		response = CommonUtils.setCommonXmlResponse(response);
		String id = null == request.getParameter("id") ? Constants.DEFAULT_ROOT
				: request.getParameter("id");
		try {
			response.getWriter().write(sysModulesManager.getTreeXml(id));
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 加载dhtmltree需要的xml，内容是根据模块parentId一次性加载parentId下的所有模块树xml，
	 * 如果是parentId==NULL则加载整颗树
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 *  
	 * @throws ServletException, IOException
	 */
	public ModelAndView loadAllTreeXML(HttpServletRequest request,
			HttpServletResponse response, SysModules command,
			BindException errors) throws ServletException, IOException {
		response = CommonUtils.setCommonXmlResponse(response);
		String id = null == request.getParameter("id") ? Constants.DEFAULT_ROOT
				: request.getParameter("id");
		try {
			String xml = sysModulesManager.getAllTreeXML(id);
			response.getWriter().write(xml);
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
    //*******************************************************************************set and get	
	public SysModulesManager getSysModulesManager() {
		return sysModulesManager;
	}

	public void setSysModulesManager(SysModulesManager sysModulesManager) {
		this.sysModulesManager = sysModulesManager;
	}
	
	@Override
	protected void convertStringToDate(HttpServletRequest request,
			Object command1) {
	}
}

