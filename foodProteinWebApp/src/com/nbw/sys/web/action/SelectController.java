package com.nbw.sys.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.nbw.common.NbwController;
import com.nbw.sys.domain.SysModuleActions;
import com.nbw.sys.domain.SysUsers;
import com.nbw.sys.service.SysModulesManager;
import com.nbw.sys.service.SysOrganizationsManager;
import com.nbw.sys.service.SysRolesManager;
import com.nbw.sys.service.SysUsersManager;

/**
 * 
 * SelectController
 * 
 * @author 狄巨礼 May 17, 2010 09:31:00 AM
 * 
 */
public class SelectController extends NbwController {

	private SysUsersManager sysUsersManager;
	private SysOrganizationsManager sysOrganizationsManager;
	private SysRolesManager sysRolesManager;
	private SysModulesManager sysModulesManager;

	/**
	 * constructor
	 * 
	 * @throws ClassNotFoundException
	 */
	public SelectController() throws ClassNotFoundException {
		super();
	}

	/**
	 * 所有选择页面的跳转器
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * 
	 * @throws ServletException,
	 *             IOException
	 */
	public ModelAndView select(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws ServletException, IOException {
		return new ModelAndView("sys/select/select");
	}

	/** **************************选择用户开始********************************* */
	/**
	 * 获得选择用户的页面
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * 
	 * @throws ServletException,
	 *             IOException
	 */
	public ModelAndView selectMultiUser(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws ServletException, IOException {
		return new ModelAndView("sys/select/selectUserIndex");
	}
	public ModelAndView selectMultiUser1(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws ServletException, IOException {
		return new ModelAndView("sys/select/selectUserIndex1");
	}
	/**
	 * 获得选择用户的右边页面
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * 
	 * @throws ServletException,
	 *             IOException
	 */
	public ModelAndView getUsers(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws ServletException, IOException {
		String orgId = request.getParameter("orgId");
		List list = sysUsersManager.findSysUsers(orgId);
		String currentDeptName = "";
		if (orgId != null) {
			currentDeptName = sysOrganizationsManager.findNameByID(orgId);
		}
		request.setAttribute("userList", list);
		request.setAttribute("currentDeptName", currentDeptName);

		String flag = request.getParameter("flag");
		String type = request.getParameter("type");
		// 如果是单选用户则返回相应的页面
		if ("one".equals(type)) {
			// 只通过id来选
			return new ModelAndView("sys/select/selectAlertOneUserRight");
		}
		// 返回选择用户的页面
		if ("byIds".equals(flag)) {
			// 只通过id来选
			return new ModelAndView("sys/select/selectMultiUserByIdsRight");
		} else if ("alert".equals(flag)) {
			// 弹出窗口
			return new ModelAndView("sys/select/selectAlertMultiUserRight");
		}
		return new ModelAndView("sys/select/selectMultiUserRight");
	}

	/** **************************选择用户结束********************************* */

	/** **************************多选 模块的功能 开始********************************* */
	/**
	 * 获得多选用户的页面
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * 
	 * @throws ServletException,
	 *             IOException
	 */
	public ModelAndView selectMultiMAction(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws ServletException, IOException {
		return new ModelAndView("sys/select/selectMultiMActionIndex");
	}

	/**
	 * 获得多选用户的右边页面
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * 
	 * @throws ServletException,
	 *             IOException
	 */
	public ModelAndView getModuleActions(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws ServletException, IOException {
		String moduleId = request.getParameter("moduleId");
		List<SysModuleActions> list = sysModulesManager
				.findSysModuleActionsByModuleID(moduleId);
		String currentModuleName = "";
		if (moduleId != null) {
			currentModuleName = sysModulesManager.findNameByID(moduleId);
		}
		request.setAttribute("mActionList", list);
		request.setAttribute("currentModuleName", currentModuleName);

		return new ModelAndView("sys/select/selectMultiMActionRight");
	}

	/** **************************多选 模块的功能 结束********************************* */

	/** **************************多选 模块 开始********************************* */
	/**
	 * 获得多选模块的页面
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * 
	 * @throws ServletException,
	 *             IOException
	 */
	public ModelAndView selectMultiModule(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws ServletException, IOException {
		return new ModelAndView("sys/select/selectAlertMultiModule");
	}

	/**
	 * 输入带单位的字符串
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView selectOrgNameById(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws ServletException, IOException {
		StringBuffer sb = new StringBuffer();
		String userIdList = request.getParameter("userIdList");
		String[] userIdArray = userIdList.split("\\|");
		if (userIdList.length() != 0) {
			for (int i = 0; i < userIdArray.length; i++) {
				SysUsers su = sysUsersManager.findByIdWithOrg(userIdArray[i]);
				sb.append(su.getName() + "("
						+ su.getSysOrganizations().getName() + ")");
				sb.append(",");
			}

		}
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.write(sb.toString());
		return null;
	}

	/** **************************多选 模块 结束********************************* */

	/** **************************多选 组织机构 开始********************************* */
	/**
	 * 获得多选组织机构的页面
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * 
	 * @throws ServletException,
	 *             IOException
	 */
	public ModelAndView selectMultiOrg(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws ServletException, IOException {
		String type = request.getParameter("type");
		if ("one".equals(type)) {
			return new ModelAndView("sys/select/selectAlertOneOrg");
		}
		return new ModelAndView("sys/select/selectAlertMultiOrg");
	}

	/**
	 * 嵌入一个 jsp 页面
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView selectMultiOrgPage(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws ServletException, IOException {
		String type = request.getParameter("type");
		if ("one".equals(type)) {
			return new ModelAndView("sys/select/selectOneOrg");
		}
		return new ModelAndView("sys/select/selectAlertMultiOrg");
	}

	/** **************************多选 组织机构 结束********************************* */

	// and get
	public SysUsersManager getSysUsersManager() {
		return sysUsersManager;
	}

	public void setSysUsersManager(SysUsersManager sysUsersManager) {
		this.sysUsersManager = sysUsersManager;
	}

	@Override
	protected void convertStringToDate(HttpServletRequest request,
			Object command) {

	}

	public SysOrganizationsManager getSysOrganizationsManager() {
		return sysOrganizationsManager;
	}

	public void setSysOrganizationsManager(
			SysOrganizationsManager sysOrganizationsManager) {
		this.sysOrganizationsManager = sysOrganizationsManager;
	}

	public SysRolesManager getSysRolesManager() {
		return sysRolesManager;
	}

	public void setSysRolesManager(SysRolesManager sysRolesManager) {
		this.sysRolesManager = sysRolesManager;
	}

	public SysModulesManager getSysModulesManager() {
		return sysModulesManager;
	}

	public void setSysModulesManager(SysModulesManager sysModulesManager) {
		this.sysModulesManager = sysModulesManager;
	}
}
