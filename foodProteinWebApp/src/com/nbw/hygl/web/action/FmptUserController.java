package com.nbw.hygl.web.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


import org.apache.commons.beanutils.BeanUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.nbw.common.Constants;
import com.nbw.common.NbwController;
import com.nbw.common.SysParameter;
import com.nbw.common.util.GUID;
import com.nbw.common.util.MD5Code;
import com.nbw.hygl.domain.FmptJf;
import com.nbw.hygl.domain.Province;
import com.nbw.hygl.service.CityManager;
import com.nbw.hygl.service.FmptJfManager;
import com.nbw.hygl.service.FmptUserManager;
import com.nbw.hygl.util.EmailUtil;
import com.nbw.hygl.util.VerifyCodeUtil;
import com.nbw.lucene.util.UpdateUtil;
import com.nbw.sys.domain.SysUsers;
import com.nbw.sys.domain.SysUsersDetail;
import com.nbw.sys.domain.SysUsersDetailCol;
import com.nbw.sys.service.SysUsersManager;

public class FmptUserController extends NbwController {

	private FmptUserManager fmptUserManager;
	private SysUsersManager sysUsersManager; 
	private CityManager cityManager;
	private FmptJfManager fmptJfmanager;
    
	public CityManager getCityManager() {
		return cityManager;
	}

	public void setCityManager(CityManager cityManager) {
		this.cityManager = cityManager;
	}

	public FmptUserController() throws ClassNotFoundException {
		super();
	}

	public FmptJfManager getFmptJfmanager() {
		return fmptJfmanager;
	}

	public void setFmptJfmanager(FmptJfManager fmptJfmanager) {
		this.fmptJfmanager = fmptJfmanager;
	}
	
	
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 跳转到找回密码首页
 	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontFindPwd(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException {
		
		
		return new ModelAndView("hygl/fmpt_fzhanghao");
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 验证图片验证码
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontValiImgCode(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException {
		
		String yanzhengma = request.getParameter("yanzhengma");
		String imgCode = (String) request.getSession().getAttribute("imgCode");
		if(imgCode.toLowerCase().equals(yanzhengma.toLowerCase())){
			response.getWriter().print(true);
		}else{
			response.getWriter().print(false);
		}
		
		return null;
	}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 验证用户的页面
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontValiUser(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException {
		
		String loginname = request.getParameter("loginname");
		SysUsers sysuser = this.sysUsersManager.findSysUserByName(loginname);
		
		
		String useremail = this.sysUsersManager.findEmail(loginname);
		if(null==useremail||"".equals(useremail)){
			request.setAttribute("useremail", "");
			request.setAttribute("ue", "无邮箱");
		}else{
			
			request.setAttribute("useremail", useremail);
			String[] st = useremail.split("@");
			request.setAttribute("ue", useremail.substring(0, 3)+"***"+st[1]);
		}
		
		
		return new ModelAndView("hygl/fmpt_fvalidaty","user",sysuser);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 发送邮件
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontSendEmail(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException {
		
		String useremail = request.getParameter("useremail");
		String userid = request.getParameter("userid");
		String emailCode = VerifyCodeUtil.generator();
		request.getSession().removeAttribute("emailCode");
		request.getSession().setAttribute("emailCode", emailCode);
		String msg = "您的邮箱验证码为:" + emailCode;
		EmailUtil.send(useremail, msg,userid);
		String[] ok = new String[]{"OK"};
		PrintWriter out = response.getWriter();
		JSONArray obj = JSONArray.fromObject(ok);
		out.println(obj.toString());
		out.close();
		return null;
	}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 邮箱验证码
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontValiEmailCode(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException {
		
		String yme = request.getParameter("yme");
		String emailcode = (String) request.getSession().getAttribute("emailCode");
		JSONObject obj = new JSONObject();
		if(yme.equals(emailcode)){
			
			obj.put("flag", "ok");
			response.getWriter().print(obj.toString());
			response.getWriter().close();
		}else{
			obj.put("flag", "fail");
			response.getWriter().print(obj.toString());
			response.getWriter().close();
			
		}
		return null;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 重置密码
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontResPwd(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException {
		
		
		String userid = request.getParameter("userid");
		request.setAttribute("userid", userid);
		
		return new ModelAndView("hygl/fmpt_fremima");
	}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 保存新密码
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontResUserPwd(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException {
	
		String userid = request.getParameter("userid");
		String newPwd = request.getParameter("newPwd");
		SysUsers sysuser = this.sysUsersManager.findByID(userid);
		sysuser.setPassword(newPwd);
		this.sysUsersManager.saveOrUpdateObject(sysuser);
		return new ModelAndView("hygl/fmpt_finish");
	}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 验证用户名是否存在
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontValiLoginName(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException {
		
		String loginname = request.getParameter("loginname");
		SysUsers sysuser = this.sysUsersManager.findSysUserByName(loginname);
		
		if(sysuser==null){
			response.getWriter().print(false);
		}else{
			response.getWriter().print(true);
		}
		
		return null;
	}
	
	
	/**
	 * 图片验证码开始
	 */
	private InputStream input;

	private static int WIDTH = 200;

	private static int HEIGHT = 80;

	private static int NUM = 4;

	private static char[] seq = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
			'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
			'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8',
			'9' };
	
	private Color randomColor(Random r) {
		return new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
	}

	public InputStream getInput() {
		return input;
	}

	public void setInput(InputStream input) {
		this.input = input;
	}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 发送图片验证
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontImageCode(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException {
		
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires",0);
		response.setContentType("image/jpeg") ; 
		
		Random r = new Random();

		BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_RGB);

		Graphics g = image.getGraphics();
		g.setColor(randomColor(r));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(new Color(0, 0, 0));

		StringBuffer number = new StringBuffer();

		for (int i = 0; i < NUM; i++) {
			g.setColor(randomColor(r));
			int h = (int) ((HEIGHT * 60 / 100) * r.nextDouble() + (HEIGHT * 30 / 100));
			g.setFont(new Font(null, Font.BOLD | Font.ITALIC, h));
			String ch = String.valueOf(seq[r.nextInt(seq.length)]);
			number.append(ch);
			g.drawString(ch, i * WIDTH / NUM * 90 / 100, h);
		}
		
		
		request.getSession().setAttribute("imgCode", number.toString());
		System.out.println(number.toString());

		for (int i = 0; i <= 12; i++) {
			g.setColor(randomColor(r));
			g.drawLine(r.nextInt(WIDTH), r.nextInt(HEIGHT), r.nextInt(WIDTH), r
					.nextInt(HEIGHT));

		}
		
		ServletOutputStream sos = response.getOutputStream();
		ImageIO.write(image, "jpeg", sos);
		sos.close();

		
		return null;
	}
	//图片验证码结束
	
	
	
	
	
	/**
	 * 跳转到注册页面
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
	public ModelAndView frontRegister(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException {
		String fwid = request.getParameter("fwid");
		request.setAttribute("fwid", fwid);
		return new ModelAndView("hygl/registerPage");
	}

	/**
	 * 一般用户注册
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
	public ModelAndView frontIndex(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException {
		String checked=request.getParameter("checked");
		List<Province> listProvince= cityManager.getProvince();
		request.setAttribute("checked",checked);
		request.setAttribute("listPro", listProvince);
		return new ModelAndView("hygl/zhuce");
	}

	
	/**
	 * 企业注册
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
	public ModelAndView frontEEIndex(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException {
		// String fwid=request.getParameter("fwid");
		// request.setAttribute("fwid", fwid);
		String index = request.getParameter("index");
		request.setAttribute("index", index);
		return new ModelAndView("hygl/bzpt_registerEEUser");
	}

	/**
	 * 登录名称是否存在
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontLoginCodeIsExit(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException {
		String loginCode = request.getParameter("loginCode");
		boolean result = fmptUserManager.isLoginCodeExist(loginCode);
		if (result == true) {
			response.getWriter().write("true");
		} else {
			response.getWriter().write("false");
		}
		return null;
	}

	/**
	 * 企业登录名称是否存在
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontEELoginCodeIsExit(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException {
		String loginCode = request.getParameter("loginCode");
		boolean result = fmptUserManager.isEELoginCodeExist(loginCode);
		if (result == true) {
			response.getWriter().write("true");
		} else {
			response.getWriter().write("false");
		}
		return null;
	}

	/**
	 * 保存数据(前台注册)
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

		// 查看用户名是否重复
		String loginCode = command.getLoginCode();
		// 保存用户基本信息
		command.setId(new GUID().toString());
		command.setSysOrganizationsId(Constants.WBID);
		command.setValidFlag(1);
		command.setStatus(0);
		command.setCreateUser(loginCode);
		//默认注册会员级别为普通级别
		command.setJbid(Constants.PUHY_JBID);
		fmptUserManager.save(command);
		// 保存用户详细信息
		SysUsersDetail sud = fmptUserManager
				.getBzptUsersDetail(command.getId());
		if (sud == null) {
			sud = new SysUsersDetail();
			sud.setId(command.getId());
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
		fmptUserManager.saveOrUpdateObject(sud);
		SysUsers su = sysUsersManager.checkSysUser(command.getLoginCode(),
				command.getPassword());
		String ee = "0";
		if ((Constants.WBID).equals(su.getSysOrganizationsId())) {
			ee = "1";
		}
		//保存积分表
		Date date = new Date();
		Timestamp time = new Timestamp(date.getTime());
		FmptJf fj=new FmptJf();	
		fj.setFtype("zhuce");
		fj.setUsername(loginCode);
		fj.setJfcount(100);
		fj.setNote("注册为本平台用户，即送100平台积分");
		fj.setTimestamp(time);
		fj.setUserid(command.getId());
		this.fmptJfmanager.saveFmptJf(fj);
		UpdateUtil.initJF(loginCode, loginCode,command.getName(), command.getId());
		request.getSession().setAttribute("EE", ee);
		request.getSession().setAttribute("qt", su.getId());
		request.getSession().setAttribute(Constants.SESSION_USER_ID,
				command.getId());
		request.getSession().setAttribute(Constants.SESSION_USER_INFO, su);
		String url = SysParameter.getParameter("foodProteinWebApp")
				+ "/indexMain.action?m=frontIndexMain";
		return new ModelAndView(new RedirectView(url));
	}

	/**
	 * 保存企业
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
	public ModelAndView frontEESave(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException, IllegalAccessException,
			InvocationTargetException {
		MD5Code md5=new MD5Code();
		// 保存企业基本信息
		command.setId(new GUID().toString());
		command.setSysOrganizationsId(Constants.WBID);
		command.setValidFlag(1);
		command.setStatus(0);
		command.setCreateUser(command.getLoginCode());
		command.setPassword(md5.getMD5ofStr(command.getPassword()));
		//默认注册会员级别为普通级别
		command.setJbid(Constants.PUHY_JBID);
		fmptUserManager.save(command);
		// 保存企业详细信息
		SysUsersDetail sud = fmptUserManager
				.getBzptUsersDetail(command.getId());
		if (sud == null) {
			sud = new SysUsersDetail();
			sud.setId(command.getId());
		}
		List<SysUsersDetailCol> userConfigs = Constants.userFieldConfig;
		for (int i = 0; i < userConfigs.size(); i++) {
			SysUsersDetailCol userConfig = userConfigs.get(i);
			System.out.println(userConfigs.get(i).toString());
			String colname = userConfig.getDataSources();
			// 获取页面传过来的值
			Object objvalue = userConfig.getValueByRequest(request);
			if (objvalue != null)
				BeanUtils.setProperty(sud, colname, objvalue);
		}
		fmptUserManager.saveOrUpdateObject(sud);
		String index = request.getParameter("index");
		if (index == null || "".equals(index)) {
			SysUsers su = sysUsersManager.checkSysUser(command.getLoginCode(),
					command.getPassword());
			request.getSession().setAttribute(Constants.SESSION_USER_ID,
					command.getId());
			String ee = "0";
			if ((Constants.WBID).equals(su.getSysOrganizationsId())) {
				ee = "1";
			}
			request.getSession().setAttribute("EE", ee);
			request.getSession().setAttribute("qt", su.getId());
			request.getSession().setAttribute(Constants.SESSION_USER_INFO, su);
			String url = SysParameter.getParameter("portalPath")
					+ "/Controller.jpf?username=" + su.getLoginCode()
					+ "&password=" + su.getPassword() + "&ee=" + ee;
			System.out.println("web---EE:"+ request.getSession().getAttribute("EE") +
					";qt="+ request.getSession().getAttribute("qt"));
			request.setAttribute("flushPsUrl", url);
		}
		String isSave = Constants.DEFAULT_AJAX_SUCCESS;
		request.setAttribute("isSave", isSave);
		request.setAttribute("index", index);
		return new ModelAndView("hygl/close");
	}

	/**
	 * 补充信息页面
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView jumpSaveUserDetail(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException {
		return new ModelAndView("hygl/bzpt_bc_registerUserDeail");
	}

	/**
	 * 保存用户细节
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
	public ModelAndView saveUserDetail(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException, IllegalAccessException,
			InvocationTargetException {
		String isSave = Constants.DEFAULT_AJAX_SUCCESS;
		SysUsers su = (SysUsers) request.getSession().getAttribute(
				Constants.SESSION_USER_INFO);
		SysUsersDetail sud = fmptUserManager.getBzptUsersDetail(su.getId());
		String realName = request.getParameter("realName");
		List<SysUsersDetailCol> userConfigs = Constants.userFieldConfig;
		for (int i = 0; i < userConfigs.size(); i++) {
			SysUsersDetailCol userConfig = userConfigs.get(i);
			String colname = userConfig.getDataSources();
			Object objvalue = userConfig.getValueByRequest(request);
			if (objvalue != null)
				BeanUtils.setProperty(sud, colname, objvalue);
		}
		try {
			fmptUserManager.saveOrUpdateObject(sud, realName);
		} catch (Exception e) {
			isSave = Constants.DEFAULT_AJAX_FAILURE;
		}
		request.setAttribute("isSave", isSave);
		response.getWriter().write(isSave);
		String url = "http://www.standards-portal.cn/BzptWeb/appmanager/eip/main?_nfpb=true&_pageLabel=P5800125901322719086481&width=938&url=/bzptsfgwc.action?m=checkOrder";
		request.setAttribute("realName", sysUsersManager.findByID(su.getId())
				.getName());
		request.setAttribute("flushPsUrl", url);
		return new ModelAndView("hygl/close");

	}

	/**
	 * 
	 * 不用检查用户是否重复
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
	public ModelAndView frontSave1(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException, IllegalAccessException,
			InvocationTargetException {
		String fwid = request.getParameter("fwid");
		String isSave = Constants.DEFAULT_AJAX_SUCCESS;
		String loginCode = command.getLoginCode();
		// 保存用户基本信息
		command.setId(new GUID().toString());
		command.setSysOrganizationsId("C4564085C0B00002D12A1C706DF08FF0");// 保存到外部用户中
		command.setValidFlag(1);
		command.setStatus(0);
		command.setJbid(Constants.pthyID);
		command.setCreateUser(loginCode);

		SysUsersDetail sud = new SysUsersDetail();
		sud.setId(command.getId());
		sud.setChar04("4028821328ce73e30128ce7894750001");
		if (fmptUserManager.save(command) == true) {
			fmptUserManager.saveOrUpdateObject(sud);
			SysUsers su = sysUsersManager.checkSysUser(command.getLoginCode(),
					command.getPassword());
			request.getSession().setAttribute(Constants.SESSION_USER_ID,
					command.getId());
			request.getSession().setAttribute(Constants.SESSION_USER_INFO, su);
			String url = SysParameter.getParameter("portalPath")
					+ "/Controller.jpf?username=" + su.getLoginCode()
					+ "&password=" + su.getPassword();
			request.setAttribute("flushPsUrl", url);
		} else {
			isSave = Constants.DEFAULT_AJAX_FAILURE;
		}
		

		request.setAttribute("isSave", isSave);

		return new ModelAndView("hygl/close");

	}

	/**
	 * 转向个人中心fmpt
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * 
	 * @throws ServletException,
	 *             IOException
	 */
	public ModelAndView frontUserLeft(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException {
		SysUsers lui = (SysUsers) request.getSession().getAttribute(
				Constants.SESSION_USER_INFO);
		if (lui != null) {
			request.setAttribute("sysusers", lui);
		}
		return new ModelAndView("hygl/fmpt_grzx");
	}

	/**
	 * 获得用户中心用户信息修改fmpt
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * 
	 * @throws ServletException,
	 *             IOException
	 */
	public ModelAndView UpdateUser(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException {
		SysUsers lui = (SysUsers) request.getSession().getAttribute(
				Constants.SESSION_USER_INFO);
		if (lui != null) {
			SysUsersDetail sud = fmptUserManager
					.getBzptUsersDetail(lui.getId());
			request.setAttribute("userDetail", sud);
			request.setAttribute("realName", sysUsersManager.findByID(
					lui.getId()).getName());
		} else {
			request.setAttribute("userDetail", null);
			request.setAttribute("realName", "null");
		}

		return new ModelAndView("hygl/fmpt_updateUser");
	}

	/**
	 * 获得修改个人资料
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * 
	 * @throws ServletException,
	 *             IOException
	 */
	public ModelAndView frontUpdateUserInfo(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException {
		SysUsers lui = (SysUsers) request.getSession().getAttribute(
				Constants.SESSION_USER_INFO);
		SysUsersDetail sud = fmptUserManager.getBzptUsersDetail(lui.getId());
		String realName = request.getParameter("realName");
		sud.setChar11(request.getParameter("attchar11"));
		sud.setChar13(request.getParameter("attchar13"));
		sud.setChar15(request.getParameter("attchar15"));
		sud.setChar16(request.getParameter("attchar16"));
		String flag = Constants.DEFAULT_AJAX_SUCCESS;
		try {
			fmptUserManager.saveOrUpdateObject(sud, realName);
		} catch (Exception e) {
			flag = Constants.DEFAULT_AJAX_FAILURE;
		}
		response.getWriter().write(flag);
		SysUsersDetail sud2 = fmptUserManager.getBzptUsersDetail(lui.getId());
		request.setAttribute("userDetail", sud2);
		request.setAttribute("realName", sysUsersManager.findByID(lui.getId())
				.getName());
		response.getWriter().write(flag);
		return null;
	}

	/**
	 * 修改密码
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * 
	 * @throws ServletException,
	 *             IOException
	 */
	public ModelAndView FindUpPd(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException {
		return new ModelAndView("hygl/bzpt_updatePass");
	}

	/**
	 * 修改密码action
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * 
	 * @throws ServletException,
	 *             IOException
	 */
	public ModelAndView frontUpdatePassword(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException {
		MD5Code md5=new MD5Code();
		String oldpassword = request.getParameter("oldpassword");
		String newpassword = request.getParameter("newpassword");
	     if(oldpassword!=null&&!("").equals(oldpassword))
	     {
	    	 oldpassword=md5.getMD5ofStr(oldpassword);
	     }
	     if(newpassword!=null&&!("").equals(newpassword))
	     {
	    	 newpassword=md5.getMD5ofStr(newpassword);
	     }
		SysUsers lui = (SysUsers) request.getSession().getAttribute(
				Constants.SESSION_USER_INFO);
		if (oldpassword.equals(lui.getPassword())) {
			lui.setPassword(newpassword);
			fmptUserManager.update(lui);
		} else {
			response.getWriter().write("loginerror");
		}
		return null;
	}
    
	/**
	 * 获得列表数据
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * @throws ServletException,
	 *             IOException
	 */

	public ModelAndView frontUser(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException {
		return new ModelAndView("hygl/bzpt_userHome");
	}

	

	/**
	 * 跳转到登录页面
	 */

	public ModelAndView frontGumpLogin(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String sid = request.getParameter("sid");
		String t_type = request.getParameter("t_type");
		request.setAttribute("id", id);
		request.setAttribute("sid", sid);
		request.setAttribute("t_type", t_type);
		return new ModelAndView("hygl/fmpt_login");
	}

	/**
	 * 接受传递过的URL,登录验证然后，跳转回这个URL
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontGumpLoginByURL(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException {
		String url = request.getParameter("url");
		url = url.replaceAll("&", "'");
		request.setAttribute("url", url);
		return new ModelAndView("hygl/bzpt_loginURL");
	}

	/**
	 * 重新登录
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */

	public ModelAndView frontGumpLogin2(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException {
		
		return new ModelAndView("hygl/bzpt_dl", "fwmc", "");
	}

	// 注册同意服务条款跳转
	public ModelAndView frontSure(HttpServletRequest request,
			HttpServletResponse response, SysUsers command, BindException errors)
			throws ServletException, IOException {
		String flag = request.getParameter("flag");
		request.setAttribute("flag", flag);
		return new ModelAndView("sys/zcservice");
	}

	@Override
	protected void convertStringToDate(HttpServletRequest request,
			Object command) {

	}

	

	public SysUsersManager getSysUsersManager() {
		return sysUsersManager;
	}

	public void setSysUsersManager(SysUsersManager sysUsersManager) {
		this.sysUsersManager = sysUsersManager;
	}

	public FmptUserManager getFmptUserManager() {
		return fmptUserManager;
	}

	public void setFmptUserManager(FmptUserManager fmptUserManager) {
		this.fmptUserManager = fmptUserManager;
	}

}
