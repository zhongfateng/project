package com.nbw.sys.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.nbw.common.Constants;
import com.nbw.common.NbwController;
import com.nbw.common.util.CommonUtils;
import com.nbw.common.util.GUID;
import com.nbw.sys.domain.SysCodes;
import com.nbw.sys.domain.SysModules;
import com.nbw.sys.domain.SysRoles;
import com.nbw.sys.domain.SysRolesModules;
import com.nbw.sys.domain.SysRolesUsers;
import com.nbw.sys.domain.SysUsers;
import com.nbw.sys.service.SysCodesManager;
import com.nbw.sys.service.SysModulesManager;
import com.nbw.sys.service.SysRolesManager;
import com.nbw.sys.service.SysUsersManager;

/**
 * 
 * SysRolesController
 * 
 * @author 狄巨礼 May 17, 2010 09:31:00 AM
 * 
 */
public class SysRolesController extends NbwController {

    private SysRolesManager sysRolesManager;
    
    private SysModulesManager sysModulesManager;
    
    private SysUsersManager sysUsersManager;
    
    private SysCodesManager sysCodesManager;

	/**
	 * constructor
	 *  
	 * @throws ClassNotFoundException
	 */
	public SysRolesController() throws ClassNotFoundException {
		super();
	}

	/**
	 * 获得列表数据
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
			HttpServletResponse response, SysRoles command, BindException errors)
			throws ServletException, IOException {
		List<SysCodes> roleTypes = sysCodesManager.getRoleTypes();
		request.setAttribute("roleTypes", roleTypes);
		return new ModelAndView("sys/sysrolesList");
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
//	public ModelAndView save(HttpServletRequest request,
//			HttpServletResponse response, SysRoles command, BindException errors)
//			throws ServletException, IOException {
//		response = CommonUtils.setCommonXmlResponse(response);
//		String nativeeditor_status = request.getParameter("!nativeeditor_status");
//
//		String xml = "";
//		if ("updated".equals(nativeeditor_status)) {
//			sysRolesManager.update(command);
//			xml = CommonUtils.getDhtmlxGridXml("update", command.getId(), command.getId(), "update success!");
//		} else if ("inserted".equals(nativeeditor_status)) {
//			sysRolesManager.save(command);
//			xml = CommonUtils.getDhtmlxGridXml("insert", command.getId(), command.getId(), "");
//		} else if ("deleted".equals(nativeeditor_status)) {
//			sysRolesManager.deleteSysRoles(command.getId());
//			xml = CommonUtils.getDhtmlxGridXml("delete", command.getId(), command.getId(), "");
//		}
//		response.getWriter().write(xml);
//		return null;
//	}
	
	/**
	 * 保存SysRolesUsers 角色和用户的关系，即给角色分配用户
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 *  
	 * @throws ServletException, IOException
	 */
	public ModelAndView saveRUsers(HttpServletRequest request,
			HttpServletResponse response, SysRoles command, BindException errors)
			throws ServletException, IOException {
		response = CommonUtils.setCommonXmlResponse(response);
		String roleId = request.getParameter("roleId");
		String userIds = request.getParameter("userIds");
		sysRolesManager.updateRoleUsers(roleId,userIds.split("\\|"));
		String xml = Constants.DEFAULT_AJAX_SUCCESS;
		response.getWriter().write(xml);
		return null;
	}
	
	/**
	 * 保存SysRolesModules 角色和模块功能的关系，即给角色分配模块功能
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 *  
	 * @throws ServletException, IOException
	 */
	public ModelAndView saveRMActions(HttpServletRequest request,
			HttpServletResponse response, SysRoles command, BindException errors)
			throws ServletException, IOException {
		response = CommonUtils.setCommonXmlResponse(response);
		String roleId = request.getParameter("roleId");
		String mActionIds = request.getParameter("mActionIds");
		sysRolesManager.updateRoleMActions(roleId,mActionIds.split("\\|"));
		String xml = Constants.DEFAULT_AJAX_SUCCESS;
		response.getWriter().write(xml);
		return null;
	}
	
	/**
	 *  加载dhtmlgrid需要的xml，内容是所有的角色
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView loadGridXML(HttpServletRequest request,
			HttpServletResponse response, SysRoles command, BindException errors)
			throws ServletException, IOException {
		response = CommonUtils.setCommonXmlResponse(response);
		String xml = sysRolesManager.getGridXML();
		response.getWriter().write(xml);
		return null;
	}

	/**
	 * 为角色添加功能权限页面
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * 
	 * @throws ServletException ,
	 *             IOException
	 */
	public ModelAndView toAddActionForm(HttpServletRequest request,
			HttpServletResponse response, SysRoles command, BindException errors)
			throws ServletException, IOException {
		String roleId = request.getParameter("roleId");
		if (roleId != null) {
			List<SysRolesModules> roleActions = sysRolesManager.getSysRolesModulesByRoleId(roleId);
			request.setAttribute("roleActions", roleActions);
		}
		return new ModelAndView("sys/sysRolesActionsForm");
	}
	
	/**
	 * 为角色添加功能权限页面
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * 
	 * @throws ServletException ,
	 *             IOException
	 */
	public ModelAndView toAddUserForm(HttpServletRequest request,
			HttpServletResponse response, SysRoles command, BindException errors)
			throws ServletException, IOException {
		String roleId = request.getParameter("roleId");
		
		if (roleId != null) {
			List<SysRolesUsers> roleUsers = sysRolesManager.getSysRolesUsersByRoleId(roleId);
			request.setAttribute("roleUsers", roleUsers);
		}
		return new ModelAndView("sys/sysRolesUsersForm");
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
			HttpServletResponse response, SysRoles command,
			BindException errors) throws ServletException, IOException {
		List<SysCodes> roleTypes = sysCodesManager.getRoleTypes();
		request.setAttribute("roleTypes", roleTypes);
		return new ModelAndView("sys/sysrolesForm");
	}

	/**
	 * 根据rolesId 获取模块 并返回相应的jsonObject
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
			HttpServletResponse response, SysRoles command,
			BindException errors) throws ServletException, IOException {
		response = CommonUtils.setCommonHtmlResponse(response);
		PrintWriter out = response.getWriter();

		String id = null == request.getParameter("id") ? Constants.DEFAULT_ROOT
				: request.getParameter("id");
		command = sysRolesManager.findByID(id);
		if (null != command) {
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
			HttpServletResponse response, SysRoles command, BindException errors)
			throws ServletException, IOException {
		response = CommonUtils.setCommonHtmlResponse(response);
		String userId = (String)request.getSession().getAttribute(Constants.SESSION_USER_ID);
		PrintWriter out = response.getWriter();
		boolean flag = false;
		String id = command.getId();
		String xml = "";
		command.setValidFlag(SysRoles.VALIDFLAG_TRUE); 
		if (null == command.getId() || "".equals(command.getId())) {
			command.setId(new GUID().toString());
			flag = sysRolesManager.save(command);
			xml = Constants.DEFAULT_AJAX_SUCCESS + command.getId();
		} else {
			flag = sysRolesManager.update(command);
			xml = Constants.DEFAULT_AJAX_SUCCESS + command.getId();
		}
		out.write(xml);
		out.close();
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
	 * @throws ServletException ,
	 *             IOException
	 */
	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response, SysRoles command, BindException errors)
			throws ServletException, IOException {
		response = CommonUtils.setCommonHtmlResponse(response);
		String ids = request.getParameter("ids");
		PrintWriter out = response.getWriter();

		if (sysRolesManager.delete(ids)) {
			out.write(Constants.DEFAULT_AJAX_SUCCESS);
		} else {
			out.write(Constants.ERROR_DELETE);
		}
		out.close();
		return null;
	}
	
    //*******************************************************************************set and get	
	public SysRolesManager getSysRolesManager() {
		return sysRolesManager;
	}

	public void setSysRolesManager(SysRolesManager sysRolesManager) {
		this.sysRolesManager = sysRolesManager;
	}
	
	@Override
	protected void convertStringToDate(HttpServletRequest request,
			Object command1) {
	}

	public SysModulesManager getSysModulesManager() {
		return sysModulesManager;
	}

	public void setSysModulesManager(SysModulesManager sysModulesManager) {
		this.sysModulesManager = sysModulesManager;
	}

	public SysUsersManager getSysUsersManager() {
		return sysUsersManager;
	}

	public void setSysUsersManager(SysUsersManager sysUsersManager) {
		this.sysUsersManager = sysUsersManager;
	}

	public SysCodesManager getSysCodesManager() {
		return sysCodesManager;
	}

	public void setSysCodesManager(SysCodesManager sysCodesManager) {
		this.sysCodesManager = sysCodesManager;
	}
}

