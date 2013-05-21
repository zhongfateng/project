package com.nbw.hygl.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.nbw.common.Constants;
import com.nbw.common.NbwController;


import com.nbw.hygl.domain.FmptZh;
import com.nbw.hygl.service.FmptHyJbManager;
import com.nbw.hygl.service.FmptUserManager;
import com.nbw.hygl.service.FmptZhManager;

import com.nbw.sys.service.SysUsersManager;

/**
 * 
 * BzptZhController
 * 
 * @author
 * 
 */
public class FmptZhController extends NbwController {

	private FmptZhManager fmptzhmanager;
	private SysUsersManager sysUsersManager;
	private FmptUserManager fmptUserManager;
	private FmptHyJbManager fmpthyjbmanager;
	
	
	public FmptZhManager getFmptzhmanager() {
		return fmptzhmanager;
	}

	public void setFmptzhmanager(FmptZhManager fmptzhmanager) {
		this.fmptzhmanager = fmptzhmanager;
	}

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
	public FmptZhController() throws ClassNotFoundException {
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
	public ModelAndView loadData(HttpServletRequest request,
			HttpServletResponse response, FmptZh command, BindException errors)
			throws ServletException, IOException {
		return new ModelAndView("hygl/bzptzhList", "dataList",
				this.fmptzhmanager.getAll());
	}

	/**
	 * 用户级别调整fm
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView loadUserJbtz(HttpServletRequest request,
			HttpServletResponse response, FmptZh command, BindException errors)
			throws ServletException, IOException {
		return new ModelAndView("hygl/userJbList");

	}

	/**
	 * 显示全部用户的金额列表 单击叶子节点显示
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView loadOnclickLeafUserPage(HttpServletRequest request,
			HttpServletResponse response, FmptZh command, BindException errors)
			throws ServletException, IOException {
		String orgId = request.getParameter("orgId");
		return new ModelAndView("hygl/zhInfoList", "userList",
				this.fmptzhmanager.getsysUsersWithZhByOrg(orgId));

	}

	/**
	 * 显示全部用户级别列表fm
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView loadUserJbList(HttpServletRequest request,
			HttpServletResponse response, FmptZh command, BindException errors)
			throws ServletException, IOException {
		String orgId = request.getParameter("orgId");
		return new ModelAndView("hygl/zhJbList", "userJbList",
				this.fmptzhmanager.getAllUserJb(orgId));

	}
	/**
	 * 显示全部定制用户列表
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView loadUserDZList(HttpServletRequest request,
			HttpServletResponse response, FmptZh command, BindException errors)
			throws ServletException, IOException {
		String orgId = request.getParameter("orgId");
		return new ModelAndView("hygl/zhdzList", "userJbList",
				this.fmptzhmanager.getAllUserJb(orgId));

	}

	/**
	 * 获得列表信息通过用户的gid
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public ModelAndView loadZhByUserIdPage(HttpServletRequest request,
			HttpServletResponse response, FmptZh command, BindException errors)
			throws ServletException, IOException {
		String glId = (String) request.getSession().getAttribute(
				Constants.SESSION_USER_ID);
		Map map = new HashMap();
		map.put("zh", this.fmptzhmanager.findByUserId(glId));
		return new ModelAndView("hygl/fmpt_userZhInfo", map);
	}

	/**
	 * 显示后台信息
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public ModelAndView showHtZhInfo(HttpServletRequest request,
			HttpServletResponse response, FmptZh command, BindException errors)
			throws ServletException, IOException {
		String glId = request.getParameter("userId");
		Map map = new HashMap();
		map.put("zh", this.fmptzhmanager.findByUserId(glId));
	//	map.put("zhjlList", this.fmptzhjlmanager.getZhJlByZhId(glId));
		map.put("user", this.fmptUserManager.getBzptUsersDetail(glId));
		map.put("userId", glId);
		return new ModelAndView("hygl/fmptZh_Ht_Info", map);
	}

	/**
	 * 显示详细信息与级别调整fm
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public ModelAndView showZhJb(HttpServletRequest request,
			HttpServletResponse response, FmptZh command, BindException errors)
			throws ServletException, IOException {
		String userID = request.getParameter("userID");
		Map map = new HashMap();
		String jb = sysUsersManager.findByID(userID).getJbid();
		map.put("jbList", this.fmpthyjbmanager.getAllByValid());
		map.put("myJb", jb);
		map.put("userID", userID);
		return new ModelAndView("hygl/fmptZhJb", map);
	}

	/**
	 * 调整账号级别
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public ModelAndView tzZhJb(HttpServletRequest request,
			HttpServletResponse response, FmptZh command, BindException errors)
			throws ServletException, IOException {
		try {
			String userID = request.getParameter("userID");
			String jbID = request.getParameter("jbID");
			sysUsersManager.updateUserJb(userID, jbID);
			String jb = sysUsersManager.findByID(userID).getJbid();
			//Map map = new HashMap();
			request.setAttribute("my", jb);
			request.setAttribute("jbList", this.fmpthyjbmanager.getAllByValid());
			response.getWriter().write(Constants.DHTMLXGRID_SAVEXML_SUFFIX);
		} catch (Exception e) {
			response.getWriter().write(Constants.DEFAULT_AJAX_FAILURE);
			e.printStackTrace();
		}
		return null;
	}

	
	/**
	 * 显示充值记录前台
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public ModelAndView showQtZhjlInfo(HttpServletRequest request,
			HttpServletResponse response, FmptZh command, BindException errors)
			throws ServletException, IOException {
		String page = request.getParameter("page");
		request.setAttribute("page", page);
		return new ModelAndView("hygl/bzpt_user_Qt_ZhjlInfo");
	}

	/**
	 * 显示充值记录前台 待办 待充值。没有审批通过
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	/*public ModelAndView showQtZhjlDBInfo(HttpServletRequest request,
			HttpServletResponse response, FmptZh command, BindException errors)
			throws ServletException, IOException {
		String userId = (String) request.getSession().getAttribute(
				Constants.SESSION_USER_ID);
		Map map = new HashMap();

		int pageSize = 5;
		int page = 1;
		if (request.getParameter("page") != null) {
			String pageString = (String) request.getParameter("page");
			try {
				page = Integer.parseInt(pageString);
			} catch (NumberFormatException e) {
				System.out.println("数字类型错误:" + e.getMessage());
			}
		}
		map.put("dataList", this.bzptczdmanager.findCzdByIsCl("0", userId,
				page, pageSize));
		return new ModelAndView("hygl/bzpt_user_ZhjlInfo_db", map);
	}*/

	/**
	 * 显示充值记录前台 审批通过
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	/*@SuppressWarnings("unchecked")
	public ModelAndView showQtZhjlJSInfo(HttpServletRequest request,
			HttpServletResponse response, FmptZh command, BindException errors)
			throws ServletException, IOException {
		String userId = (String) request.getSession().getAttribute(Constants.SESSION_USER_ID);
		Map map = new HashMap();
		int pageSize = 5;
		int page = 1;
		if (request.getParameter("page") != null) {
			String pageString = (String) request.getParameter("page");
			try {
				page = Integer.parseInt(pageString);
			} catch (NumberFormatException e) {
				System.out.println("数字类型错误:" + e.getMessage());
			}
		}
		map.put("dataList", this.bzptzhjlmanager.getZhJlByZhIdPager(userId,page, pageSize));
		return new ModelAndView("hygl/bzpt_user_ZhjlInfo_js", map);
	}*/

	/**
	 * 转向新增页面
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * @throws ServletException,IOException
	 */
	public ModelAndView toAddPage(HttpServletRequest request,
			HttpServletResponse response, FmptZh command, BindException errors)
			throws ServletException, IOException {
		return new ModelAndView("hygl/fmptzhForm");
	}

	/**
	 * 先创建账号信息， objectId 是 用户的id 号码
	 * 跳转创建账户页面 bzptzhForm
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * @throws ServletException,IOException
	 */
	@SuppressWarnings("unchecked")
	/*public ModelAndView toEditPage(HttpServletRequest request,
			HttpServletResponse response, FmptZh command, BindException errors)
			throws ServletException, IOException {
		String czdId = request.getParameter("czdId");// 充值单id
		String czrId = request.getParameter("czrId");// 充值人ID
		Map map = new HashMap();
		map.put("bzptzh", this.fmptzhmanager.saveOrUpdetaZh(czrId));
		BzptCzd czd = fmptzhmanager.findById(czdId);
		map.put("czd", czd);
		return new ModelAndView("hygl/bzptzhForm", map);
	}*/

	/**
	 * 充值金额
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * @throws ServletException,IOException
	 */
	/*@SuppressWarnings("unchecked")
	public ModelAndView save(HttpServletRequest request,
			HttpServletResponse response, FmptZh command, BindException errors)
			throws ServletException, IOException {
		String czrId = (String) request.getSession().getAttribute(Constants.SESSION_USER_ID);
		String czdId = request.getParameter("czdId");
		bzptzhmanager.updateNoFpZh(czrId, czdId, command);
		BzptCzd czd = bzptczdmanager.findById(czdId);
		if ("1".equals(czd.getIsXYFP())&&null!=czd.getAddressid()) {// 判读前台选择是否需要发票 
			BzptOrderAddress address = bzptorderaddressmanager.findById(czd.getAddressid());
			//修改开发票申请状态
			address.setIsinvoice("1");
			address.setFpstate("0");
			address.setFpje(czd.getJe());
			bzptorderaddressmanager.editBzptOrderAddress(address);
		} 
		request.setAttribute("iscl", 0);
		return new ModelAndView("hygl/bzptczdList", "dataList",
				this.bzptczdmanager.findByCl("0"));
	}*/

	/**
	 * 有 ，表示 true
	 * @param command
	 * @return
	 */
	public Boolean snalysisCommand(FmptZh command) {
		if (command != null && command.getId() != null
				&& !"".equals(command.getId())) {
			return true;
		} else {
			return false;
		}
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
	 * @throws ServletException,
	 *             IOException
	 */
	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response, FmptZh command, BindException errors)
			throws ServletException, IOException {
		String id = request.getParameter("objectId");
		try {
			this.fmptzhmanager.deleteBzptZh(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("hygl/bzptzhList", "dataList",
				this.fmptzhmanager.getAll());
	}

	/**
	 * 校验数据
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
	public ModelAndView validateData(HttpServletRequest request,
			HttpServletResponse response, FmptZh command, BindException errors)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		if (!"123".equals(id)) {
			out.print("success");
		} else {
			out.print("id=" + id + "的对象已经存在！");
		}
		return null;
	}

	/**
	 * 把字符型转化为时间型
	 * 
	 * @param request
	 * @param command
	 * @return
	 */
	protected void convertStringToDate(HttpServletRequest request,
			Object command) {
	}

	// *******************************************************************************set and get
	

	public SysUsersManager getSysUsersManager() {
		return sysUsersManager;
	}

	public void setSysUsersManager(SysUsersManager sysUsersManager) {
		this.sysUsersManager = sysUsersManager;
	}

	
	public ModelAndView createZh(HttpServletRequest request,
			HttpServletResponse response, FmptZh command, BindException errors)
	throws ServletException, IOException {
			request.setAttribute("subject", "充值服务");
			request.setAttribute("alibody", "账户充值");
			request.setAttribute("total_fee", "0.01");
			return new ModelAndView("apliay/alipayto");

}
}
