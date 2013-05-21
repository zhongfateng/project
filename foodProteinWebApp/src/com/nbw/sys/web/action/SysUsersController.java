package com.nbw.sys.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.nbw.common.Constants;
import com.nbw.common.NbwController;
import com.nbw.common.SysParameter;
import com.nbw.common.util.CommonUtils;
import com.nbw.common.util.GUID;

import com.nbw.sys.domain.SysRoles;
import com.nbw.sys.domain.SysRolesUsers;
import com.nbw.sys.domain.SysUsers;
import com.nbw.sys.domain.SysUsersDetail;
import com.nbw.sys.domain.SysUsersDetailCol;
import com.nbw.sys.service.SysOrganizationsManager;
import com.nbw.sys.service.SysRolesManager;
import com.nbw.sys.service.SysUsersManager;

/**
 * 
 * SysUsersController
 * 
 * @author 狄巨礼 May 17, 2010 09:31:00 AM
 * 
 */
public class SysUsersController extends NbwController {

	private SysUsersManager sysUsersManager;
	private SysOrganizationsManager sysOrganizationsManager;
	private SysRolesManager sysRolesManager;

	/**
	 * constructor
	 * 
	 * @throws ClassNotFoundException
	 */
	public SysUsersController() throws ClassNotFoundException {
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
	 * @throws ServletException,
	 *             IOException
	 */
	public ModelAndView toIndex(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException {
		return new ModelAndView("sys/sysusersList");
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
	 * @throws ServletException,
	 *             IOException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public ModelAndView frontSave(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException, IllegalAccessException,
			InvocationTargetException {
		response = CommonUtils.setCommonHtmlResponse(response);
		String userId = (String)request.getSession().getAttribute(Constants.SESSION_USER_ID);
		PrintWriter out = response.getWriter();
		boolean flag = false;
		String id = command.getId();
		String loginCode = command.getLoginCode();
		String xml = "";
		if (null == command.getId() || "".equals(command.getId())) {
			// 查看用户名是否重复
			boolean result = sysUsersManager.isLoginCodeExist(loginCode);
			if (result) {
				xml = Constants.ERROR_USERNAME_EXIST;
			} else {
				command.setId(new GUID().toString());
				command.setCreateUser(userId);
				flag = sysUsersManager.save(command);
				xml = Constants.DEFAULT_AJAX_SUCCESS + command.getId();
			}
		} else {
			boolean result = sysUsersManager.isLoginCodeExist(id, loginCode);
			if (result) {
				xml = Constants.ERROR_USERNAME_EXIST;
			} else {
				flag = sysUsersManager.update(command);
				xml = Constants.DEFAULT_AJAX_SUCCESS + command.getId();
			}
		}

		out.write(xml);
		out.close();
		return null;
	}

	/**
	 * 保存用户附属信息
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * 
	 * @throws ServletException,
	 *             IOException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public ModelAndView saveAtt(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException, IllegalAccessException,
			InvocationTargetException {
		response = CommonUtils.setCommonHtmlResponse(response);
		PrintWriter out = response.getWriter();
		boolean b = false;
		String id = command.getId();
		String xml = "";
		// 通过反射存储附属信息
		SysUsersDetail sud = sysUsersManager.getSysUsersDetail(id);
		if (sud == null) {
			sud = new SysUsersDetail();
			sud.setId(id);
		}
		List<SysUsersDetailCol> userConfigs = Constants.userFieldConfig;
		for (int i = 0; i < userConfigs.size(); i++) {
			SysUsersDetailCol userConfig = userConfigs.get(i);
			String colname = userConfig.getDataSources();
			// 获取页面传过来的值
			Object objvalue = userConfig.getValueByRequest(request);
			if (objvalue != null)
				BeanUtils.setProperty(sud, colname, objvalue);
		}
		sysUsersManager.saveOrUpdateObject(sud);
		xml = Constants.DEFAULT_AJAX_SUCCESS + command.getId();

		out.write(xml);
		out.close();
		return null;
	}
	
	/**
	 * 保存用户设置
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * 
	 * @throws ServletException,
	 *             IOException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public ModelAndView resetUser(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException, IllegalAccessException,
			InvocationTargetException {
		response = CommonUtils.setCommonHtmlResponse(response);
		PrintWriter out = response.getWriter();
		String  passwordold = request.getParameter("passwordold");
		String  passwordnew = request.getParameter("passwordnew");
		String  pskin = request.getParameter("pskin");
		String  id = request.getParameter("id");
		String xml = Constants.DEFAULT_AJAX_FAILURE;
		if(id!=null && !"".equals(id.trim())){
			SysUsers su = sysUsersManager.findByID(id);
			if(su != null){
				if(su.getPassword().equals(passwordold)){
					su.setPassword(passwordnew);
					su.setPersonaId(pskin);
					sysUsersManager.saveOrUpdateObject(su);
					xml = Constants.DEFAULT_AJAX_SUCCESS;
				}
			}
		}
		out.write(xml);
		out.close();
		return null;
	}
	
	/**
	 * 用户设置页面
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * 
	 * @throws ServletException,
	 *             IOException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public ModelAndView toResetUser(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException, IllegalAccessException,
			InvocationTargetException {
		return new ModelAndView("sys/sysusersSetting");
	}

	/**
	 * 保存用户所有的角色
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * 
	 * @throws ServletException,
	 *             IOException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public ModelAndView saveUserRole(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException, IllegalAccessException,
			InvocationTargetException {
		response = CommonUtils.setCommonHtmlResponse(response);
		PrintWriter out = response.getWriter();
		String[] roleIds = request.getParameterValues("roleId");
		String userId = request.getParameter("userId");
		sysRolesManager.updateUserRoles(userId, roleIds);
		String xml = Constants.DEFAULT_AJAX_SUCCESS;
		out.write(xml);
		out.close();
		return null;
	}

	/**
	 * 加载dhtmlgrid需要的xml，内容是根据组织机构Id下的用户
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
	public ModelAndView loadGridXML(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException {
		response = CommonUtils.setCommonXmlResponse(response);
		String orgId = request.getParameter("orgId");
		String xml = sysUsersManager.getGridXmlByOrgId(orgId);
		response.getWriter().write(xml);
		return null;
	}
	/**
	 * 重置用户密码
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView checkPass(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException {
		   String id=request.getParameter("id");
		   if(!"".equals(id)&&id!=null)
		   {
			   sysUsersManager.updateCheckPass(id);
		   }
		   return null;
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
	 * @throws ServletException ,
	 *             IOException
	 */
	public ModelAndView toFormPage(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException {
		return new ModelAndView("sys/sysusersForm");
	}

	/**
	 * 附属信息添加编辑页面
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
	public ModelAndView toAttFormPage(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		if (id != null) {
			SysUsersDetail sud = sysUsersManager.getSysUsersDetail(id);
			request.setAttribute("sud", sud);
		}
		return new ModelAndView("sys/sysusersAttForm");
	}

	/**
	 * 分配角色页面
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
	public ModelAndView toAddRoles(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		if (userId != null) {
			List<SysRolesUsers> roleusers = sysRolesManager
					.getSysRolesUsersByUserId(userId);
			request.setAttribute("roleusers", roleusers);
		}
		List<SysRoles> allroles = sysRolesManager.findAllSysRoles();
		request.setAttribute("allroles", allroles);
		return new ModelAndView("sys/sysuserRolesForm");
	}

	/**
	 * 获取添加页面数据
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
	public ModelAndView getFormData(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException {
		response = CommonUtils.setCommonHtmlResponse(response);
		PrintWriter out = response.getWriter();

		String id = null == request.getParameter("id") ? Constants.DEFAULT_ROOT
				: request.getParameter("id");
		command = sysUsersManager.findByID(id);
		if (null != command) {
			command.setSysOrganizationsName(sysOrganizationsManager
					.findNameByID(command.getSysOrganizationsId()));
			JSONObject jObj = JSONObject.fromObject(command);
			out.print(jObj);
		
		} else {
			out.print(Constants.DEFAULT_AJAX_FAILURE);
		}
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
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException {
		response = CommonUtils.setCommonHtmlResponse(response);
		String ids = request.getParameter("ids");
		PrintWriter out = response.getWriter();

		if (sysUsersManager.delete(ids)) {
			out.write(Constants.DEFAULT_AJAX_SUCCESS);
		} else {
			out.write(Constants.ERROR_DELETE);
		}
		out.close();
		return null;
	}
	
	/**
	 * 加载dhtmltree需要的xml，内容是User的树
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView singleLoadUserTreeXML(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response = CommonUtils.setCommonXmlResponse(response);
		try {
			String xml = this.sysUsersManager.singleLoadUser();
			response.getWriter().write(xml);
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 验证用户名密码(使用key)
	 * @param request
	 * @param response
	 * @return
	 * @throws ParseException
	 */
	public ModelAndView frontKey(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException {
		String userName=request.getParameter("userName");
		String passWord=request.getParameter("password");
		if(this.sysUsersManager.validateUser(userName, passWord)){
			return new ModelAndView("resources/caindex");
		}else{
			response.setContentType("text/html; charset=utf-8");
			response.setCharacterEncoding("utf-8");
			String html = "";
			html = "<script>alert('用户名或密码错误请重新输入');top.location.href = '"
					+ SysParameter.getParameter("portalPath")
					+ "/appmanager/eip/home1';</script>";

			response.getWriter().write(html);
			return null;
		}
		
	}

	// *******************************************************************************set
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
}