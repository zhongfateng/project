package com.nbw.hygl.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.nbw.common.NbwController;
import com.nbw.hygl.domain.FmptHyJb;
import com.nbw.hygl.service.FmptHyJbManager;

/**
 * 
 * FmptHyJbController
 * 
 * @author
 * 
 */
public class FmptHyJbController extends NbwController {

	private FmptHyJbManager fmpthyjbmanager;

	/**
	 * constructor
	 * 
	 * @throws ClassNotFoundException
	 */
	public FmptHyJbController() throws ClassNotFoundException {
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
			HttpServletResponse response, FmptHyJb command, BindException errors)
			throws ServletException, IOException {
		List<FmptHyJb> jb= fmpthyjbmanager.getAllByValid();
		return new ModelAndView("hygl/fmpthyjbList", "dataList",jb);
	}

	/**
	 * 转向新增页面
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
	public ModelAndView toAddPage(HttpServletRequest request,
			HttpServletResponse response, FmptHyJb command, BindException errors)
			throws ServletException, IOException {
		return new ModelAndView("hygl/fmpthyjbForm");
	}

	/**
	 * 转向编辑页面
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
	public ModelAndView toEditPage(HttpServletRequest request,
			HttpServletResponse response, FmptHyJb command, BindException errors)
			throws ServletException, IOException {
		String id = request.getParameter("objectId");
		command = this.fmpthyjbmanager.findById(id);
		return new ModelAndView("hygl/fmpthyjbForm", "FmptHyJb", command);
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
	 */
	public ModelAndView save(HttpServletRequest request,
			HttpServletResponse response, FmptHyJb command, BindException errors)
			throws ServletException, IOException {
		if (command != null && command.getId() != null
				&& !"".equals(command.getId())) {
			this.fmpthyjbmanager.editFmptHyJb(command);
		} else {
			this.fmpthyjbmanager.saveFmptHyJb(command);
		}
		List<FmptHyJb> jb= fmpthyjbmanager.getAllByValid();
		return new ModelAndView("hygl/fmpthyjbList", "dataList",jb);
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
			HttpServletResponse response, FmptHyJb command, BindException errors)
			throws ServletException, IOException {
		String id = request.getParameter("objectId");
		try {
			this.fmpthyjbmanager.deleteJb(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("hygl/fmpthyjbList", "dataList",
				this.fmpthyjbmanager.getAllByValid());
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
			HttpServletResponse response, FmptHyJb command, BindException errors)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		FmptHyJb FmptHyJb = this.fmpthyjbmanager.findById(id);
		// if(FmptHyJb==null){
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
		FmptHyJb tempObject = (FmptHyJb) command;
	}

	public FmptHyJbManager getFmpthyjbmanager() {
		return fmpthyjbmanager;
	}

	public void setFmpthyjbmanager(FmptHyJbManager fmpthyjbmanager) {
		this.fmpthyjbmanager = fmpthyjbmanager;
	}

	// *******************************************************************************set
	// and get
	
	
}
