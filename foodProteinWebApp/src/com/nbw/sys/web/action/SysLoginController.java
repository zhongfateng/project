package com.nbw.sys.web.action;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.nbw.common.Constants;
import com.nbw.common.NbwController;
import com.nbw.common.SysParameter;
import com.nbw.common.util.CommonUtils;
import com.nbw.common.util.MD5Code;
import com.nbw.hygl.domain.FmptHyJb;
import com.nbw.hygl.service.FmptHyJbManager;
import com.nbw.hygl.service.FmptUserManager;
import com.nbw.sys.domain.SysModules;
import com.nbw.sys.domain.SysUsers;
import com.nbw.sys.domain.SysUsersDetail;
import com.nbw.sys.service.SysModulesManager;
import com.nbw.sys.service.SysOrganizationsManager;
import com.nbw.sys.service.SysUsersManager;

/**
 * 
 * SysLoginController
 * 
 * @author 狄巨礼 May 18, 2010 02:31:00 PM
 * 
 */
public class SysLoginController extends NbwController {
	private FmptUserManager fmptUserManager;
	private SysUsersManager sysUsersManager;
	private SysOrganizationsManager sysOrganizationsManager;
	private SysModulesManager sysModulesManager;
	private FmptHyJbManager fmpthyjbmanager;
	

	public FmptUserManager getFmptUserManager() {
		return fmptUserManager;
	}

	public void setFmptUserManager(FmptUserManager fmptUserManager) {
		this.fmptUserManager = fmptUserManager;
	}

	public FmptHyJbManager getFmpthyjbmanager() {
		return fmpthyjbmanager;
	}

	public void setFmpthyjbmanager(FmptHyJbManager fmpthyjbmanager) {
		this.fmpthyjbmanager = fmpthyjbmanager;
	}

	/**
	 * constructor
	 * 
	 * @throws ClassNotFoundException
	 */
	public SysLoginController() throws ClassNotFoundException {
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

	public ModelAndView toSimpleLogin(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException {
		String url = request.getParameter("url");
		String basePath = "http://" + SysParameter.getParameter("ip") + ":"
				+ SysParameter.getParameter("port") + request.getContextPath();
		request.setAttribute("flagURL", basePath + url);
		return new ModelAndView("sys/simpleLogin");
	}
	/**
	 * 前台登录页面fm
	 */
	public ModelAndView toLoginIndexQt(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException {
		return new ModelAndView("hygl/loginQt");
	}
	/**
	 * 前台登录验证
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
	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException, IllegalAccessException,
			InvocationTargetException {
		String ee = "0";
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		SysUsers su = sysUsersManager.checkSysUser(username, password);
		
		if (su == null) {
			String sysOrganizationsId="";
			response.getWriter().write(Constants.ERROR_LOGIN + "," + ee + "," + sysOrganizationsId);
			return null;
		}
		SysUsersDetail sysdetail = fmptUserManager.getBzptUsersDetail(su.getId());
		String userId = su.getId();
		
		String sysOrganizationsId=su.getSysOrganizationsId();
		if ((Constants.WBID).equals(su.getSysOrganizationsId())) {
			ee = "1";
		}
		request.getSession().setAttribute("EE",ee);
		request.getSession().setAttribute("qt", userId);
		request.getSession().setAttribute(Constants.SESSION_USER_ID, userId);
		request.getSession().setAttribute(Constants.SESSION_USER_INFO, su);
		request.getSession().setAttribute(Constants.SESSION_USER_DETAIL, sysdetail);
		response.getWriter().write(Constants.DEFAULT_AJAX_SUCCESS + "," + ee + 
				          "," + sysOrganizationsId);
        return null;
	}

	/**
	 * 简单登录
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public ModelAndView simpleLogin(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException, IllegalAccessException,
			InvocationTargetException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(password!=null&&!("").equals(password))
		{
			MD5Code md5=new MD5Code();
			password=md5.getMD5ofStr(password);
		}
		String flagURL = request.getParameter("flagURL");
		SysUsers su = sysUsersManager.checkSysUser(username, password);
		if (su == null) {
			response.getWriter().write(Constants.ERROR_LOGIN);
			return null;
		}
		
		try {
			String userId = su.getId();
			response.getWriter().write(Constants.ERROR_LOGIN);
			String ee="0";
			if ((Constants.WBID).equals(su.getSysOrganizationsId())) {
				ee = "1";
			}
			SysUsersDetail sysdetail = fmptUserManager.getBzptUsersDetail(su.getId());
			request.getSession().setAttribute(Constants.SESSION_USER_DETAIL, sysdetail);
			request.getSession().setAttribute("EE",ee);
			// } else {
			request.getSession().setAttribute("qt", userId);
			request.getSession().setAttribute(Constants.SESSION_USER_ID, userId);
			request.getSession().setAttribute(Constants.SESSION_USER_INFO, su);
			response.getWriter().write(Constants.DEFAULT_AJAX_SUCCESS);
			// }

		} catch (Exception e) {

		}

		request.setAttribute("flushPsUrl", flagURL);
		return new ModelAndView("hygl/close");

	}

	public boolean connectionPortalWeb(String urlStr) {
		URL url = null;
		HttpURLConnection connection = null;
		try {
			url = new URL(urlStr);
			connection = (HttpURLConnection) url.openConnection();// 新建连接实例
			connection.setDoOutput(true);// 是否打开输出流 true|false
			connection.setDoInput(true);// 是否打开输入流true|false
			connection.setRequestMethod("POST");// 提交方法POST|GET
			connection.setUseCaches(false);// 是否缓存true|false
			connection.connect();// 打开连接端口
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			connection.disconnect();
		}

		return true;

	}

	/**
	 * 后台系统登录
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public ModelAndView loginHt(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException, IllegalAccessException,
			InvocationTargetException {
        MD5Code md5=new MD5Code();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(password!=null&&!("").equals(password))
		{
			password=md5.getMD5ofStr(password);
		}
		SysUsers su = sysUsersManager.checkSysUserHt(username, password);
		if (su == null) {
			response.getWriter().write(Constants.ERROR_LOGIN);
			return null;
		}
		String userId = su.getId();
		request.getSession().setAttribute(Constants.SESSION_USER_ID, userId);
		request.getSession().setAttribute("ht", userId);
		// List<SysModules> list = sysModulesManager
		// .findSysModulesByUserID(userId);// 模块list
		// Map sysModulesMap =
		// sysModulesManager.getSysModulesMapByUserID(userId);
		request.getSession().setAttribute(Constants.SESSION_USER_INFO, su);
		// request.getSession().setAttribute(Constants.SESSION_USER_MODULES,
		// list);
		// request.getSession().setAttribute(Constants.SESSION_USER_MACTION,
		// sysModulesMap);
		// 用户皮肤
		request.getSession().setAttribute(Constants.SESSION_USER_SKIN_PATH,
				su.getPersonaId());
		response.getWriter().write(Constants.DEFAULT_AJAX_SUCCESS);
		return null;

	}

	/**
	 * 登录 模块树// add by qincy for module tree
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public ModelAndView loadModuleTree(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException, IllegalAccessException,
			InvocationTargetException {
		String userId = (String) request.getSession().getAttribute(
				Constants.SESSION_USER_ID);
		List<SysModules> list = sysModulesManager
				.findSysModulesByUserID(userId);// 模块list
		response = CommonUtils.setCommonXmlResponse(response);
		try {
			Document document = DocumentHelper.createDocument();
			/** 建立XML文档的根tree */
			Element treeElement = document.addElement("tree");
			treeElement.addAttribute("id", "root");
			addAllModuleNodes(list, treeElement);// 递归
			String xml = document.asXML();
			// response.setCharacterEncoding("utf-8");// 必须是UTF- 8 形式
			response.getWriter().write(xml);
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 递归 产生 树
	 * 
	 * @param list
	 * @param rElement
	 * @return
	 */
	@SuppressWarnings("unused")
	private Element addAllModuleNodes(List<SysModules> list, Element rElement) {
		for (SysModules module : list) {
			Element mouduleElement = rElement.addElement("item");
			mouduleElement.addAttribute("text", module.getName());
			mouduleElement.addAttribute("id", module.getId() + ","
					+ module.getUrl());
			List<SysModules> list2 = module.getChilds();
			addAllModuleNodes(list2, mouduleElement);
		}
		return rElement;

	}

	/**
	 * 退出登录
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
	public ModelAndView ExitLogin(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException {
		
		//注销session
		HttpSession session=request.getSession(false);
		if (session!=null){
			session.setAttribute("qt",null);
			session.setAttribute("EE",null);
		}
		if (session!=null&&request.isRequestedSessionIdValid()){
			
			session.invalidate();
			
		}
		
		//request.getSession().invalidate();
		return null;
	}

	/**
	 * 退出后台登录
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
	public ModelAndView ExitAdminLogin(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException {
		request.getSession().invalidate();
		return new ModelAndView("manage/fmpt_login");
	}
	

	/**
	 * 用户登录
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
	public ModelAndView toLogin(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException {
		return new ModelAndView("manage/fmpt_login");
	}

	/**
	 * 目录树
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
	public ModelAndView toTree(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException {
		return new ModelAndView("manage/treeList");
	}

	/**
	 * 后台主页
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
	public ModelAndView toMain(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException {
		return new ModelAndView("manage/main");
	}

	/**
	 * 后台欢迎页
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
	public ModelAndView toWelcome(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException {
		return new ModelAndView("manage/welcome");
	}

	/**
	 * 判断SESSION
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
	public ModelAndView frontSession(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException {
		if (request.getSession() != null) {
			response.getWriter().write(Constants.DEFAULT_AJAX_SUCCESS);
		} else {
			response.getWriter().write(Constants.ERROR_LOGIN);
		}
		return null;
	}

	@Override
	protected void convertStringToDate(HttpServletRequest request,
			Object command) {
		// TODO Auto-generated method stub

	}

	public SysUsersManager getSysUsersManager() {
		return sysUsersManager;
	}

	public void setSysUsersManager(SysUsersManager sysUsersManager) {
		this.sysUsersManager = sysUsersManager;
	}

	public SysOrganizationsManager getSysOrganizationsManager() {
		return sysOrganizationsManager;
	}

	public void setSysOrganizationsManager(
			SysOrganizationsManager sysOrganizationsManager) {
		this.sysOrganizationsManager = sysOrganizationsManager;
	}

	public SysModulesManager getSysModulesManager() {
		return sysModulesManager;
	}

	public void setSysModulesManager(SysModulesManager sysModulesManager) {
		this.sysModulesManager = sysModulesManager;
	}


}
