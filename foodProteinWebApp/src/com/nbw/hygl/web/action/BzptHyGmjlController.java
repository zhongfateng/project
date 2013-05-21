package com.nbw.hygl.web.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.nbw.common.Constants;
import com.nbw.common.NbwController;
import com.nbw.common.util.DateUtils;
import com.nbw.common.util.PageBean;
import com.nbw.hygl.domain.BzptHyGmjl;
import com.nbw.hygl.service.BzptHyGmjlManager;

/**
 * 
 * BzptHyGmjlController
 * 
 * @author 張帆
 * 
 */
public class BzptHyGmjlController extends NbwController {

	private BzptHyGmjlManager bzpthygmjlmanager;

	/**
	 * constructor
	 * 
	 * @throws ClassNotFoundException
	 */
	public BzptHyGmjlController() throws ClassNotFoundException {
		super();
	}

	/**
	 * 获得列表数据,查看用户的购买记录
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
			HttpServletResponse response, BzptHyGmjl command,
			BindException errors) throws ServletException, IOException {
		String userId = (String) request.getSession().getAttribute(
				Constants.SESSION_USER_ID);
		int pageSize = 10;
		int page = 1;
		if (request.getParameter("page") != null) {
			String pageString = (String) request.getParameter("page");
			try {
				page = Integer.parseInt(pageString);
			} catch (NumberFormatException e) {
				System.out.println("数字类型错误:" + e.getMessage());
			}
		}
		PageBean dataList = this.bzpthygmjlmanager.findByUser(userId, page,
				pageSize);
		return new ModelAndView("hygl/bzpthygmjlList", "dataList", dataList);
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
			HttpServletResponse response, BzptHyGmjl command,
			BindException errors) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		BzptHyGmjl bzpthygmjl = this.bzpthygmjlmanager.findById(id);
		// if(bzpthygmjl==null){
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
		BzptHyGmjl tempObject = (BzptHyGmjl) command;
		String cjsj_showS = request.getParameter("cjsj_showS");
		if (cjsj_showS != null && !cjsj_showS.equals("")) {
			tempObject.setCjsj(DateUtils.stringToDate(cjsj_showS));
		}
	}

	// *******************************************************************************set
	// and get
	public BzptHyGmjlManager getBzpthygmjlmanager() {
		return bzpthygmjlmanager;
	}

	public void setBzpthygmjlmanager(BzptHyGmjlManager bzpthygmjlmanager) {
		this.bzpthygmjlmanager = bzpthygmjlmanager;
	}

}
