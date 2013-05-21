package com.nbw.hygl.web.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.nbw.common.Constants;
import com.nbw.common.NbwController;
import com.nbw.common.util.PageBean;
import com.nbw.hygl.domain.FmptHyJb;
import com.nbw.hygl.domain.FmptJf;
import com.nbw.hygl.service.FmptHyJbManager;
import com.nbw.hygl.service.FmptJfManager;
import com.nbw.sys.domain.SysUsers;
import com.nbw.sys.service.SysUsersManager;

public class FmptJfController extends NbwController{
	
	
	private FmptJfManager fmptJfmanager;
	private SysUsersManager sysUsersManager;
	private FmptHyJbManager fmpthyjbmanager;
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 积分列表页面
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontJF(HttpServletRequest request,
			HttpServletResponse response, FmptJf command, BindException errors)
			throws ServletException, IOException {
		
		SysUsers lui = (SysUsers) request.getSession().getAttribute(
				Constants.SESSION_USER_INFO);
		
		request.setAttribute("userName", lui.getLoginCode());
		int page = 1;
		int pageSize = 10;
		
		if(request.getParameter("page")!=null){
			page = Integer.parseInt(request.getParameter("page"));
		}
		if(request.getParameter("pageSize")!=null){
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}
		request.setAttribute("pageSize", pageSize);
		
		int sumSorce = this.fmptJfmanager.findSumSorce(lui.getLoginCode());
		request.setAttribute("sumSorce", sumSorce);
		
		SysUsers sysUser = this.sysUsersManager.findSysUserByName(lui.getLoginCode());
		
		if(null==sysUser.getJbid()||"".equals(sysUser.getJbid())){
			
			request.setAttribute("jb", "暂无");
		}else{
			
			FmptHyJb hyjb = this.fmpthyjbmanager.findById(sysUser.getJbid());
			String jb = hyjb.getJbmc();
			request.setAttribute("jb", jb);
		}
		
		PageBean pageBean = this.fmptJfmanager.findByTime(page,pageSize,lui.getLoginCode());
		
		return new ModelAndView("hygl/mc_score","dataList",pageBean);
	}
	
	
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 上传记录
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontUploadAndDownload(HttpServletRequest request,
			HttpServletResponse response, FmptJf command, BindException errors)
			throws ServletException, IOException {
		
		SysUsers lui = (SysUsers) request.getSession().getAttribute(
				Constants.SESSION_USER_INFO);
		
		request.setAttribute("userName", lui.getLoginCode());
		int page = 1;
		int pageSize = 10;
		
		if(request.getParameter("page")!=null){
			page = Integer.parseInt(request.getParameter("page"));
		}
		if(request.getParameter("pageSize")!=null){
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}
		request.setAttribute("pageSize", pageSize);
		
		String fclick = request.getParameter("fclick");
		PageBean pageBean = this.fmptJfmanager.findByUploadAndDownloadTime(page, pageSize, lui.getLoginCode(),fclick);
		if(fclick.equals("upload")){//查询上传
			
			return new ModelAndView("hygl/mc_upload","dataList",pageBean);
		}else if(fclick.equals("download")){//查询下载
			
			return new ModelAndView("hygl/mc_download","dataList",pageBean);
		}
		
		
		return null;
		
	}
	
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 修改密码
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontModifyPwd(HttpServletRequest request,
			HttpServletResponse response, FmptJf command, BindException errors)
			throws ServletException, IOException {
		
		
		return new ModelAndView("hygl/mc_mima");
		
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 验证旧密码
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontModifyOldPwd(HttpServletRequest request,
			HttpServletResponse response, FmptJf command, BindException errors)
			throws ServletException, IOException {
		
		String oldpwd = request.getParameter("oldPwd");
		SysUsers lui = (SysUsers) request.getSession().getAttribute(
				Constants.SESSION_USER_INFO);
		SysUsers sysUser = this.sysUsersManager.findSysUserByName(lui.getLoginCode());
		String pwd = sysUser.getPassword();
		if(pwd.equals(oldpwd)){
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
	 * @return 注册新密码
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontModifyOldPd(HttpServletRequest request,
			HttpServletResponse response, FmptJf command, BindException errors)
			throws ServletException, IOException {
		
		SysUsers lui = (SysUsers) request.getSession().getAttribute(
				Constants.SESSION_USER_INFO);
		SysUsers sysUser = this.sysUsersManager.findSysUserByName(lui.getLoginCode());
		String newpd = request.getParameter("newPwd");
		sysUser.setPassword(newpd);
		sysUser.setCreateTime(sysUser.getCreateTime());
		sysUser.setCreateUser(sysUser.getCreateUser());
		sysUser.setId(sysUser.getId());
		sysUser.setJbid(sysUser.getJbid());
		sysUser.setLoginCode(sysUser.getLoginCode());
		sysUser.setName(sysUser.getName());
		sysUser.setOrderNum(sysUser.getOrderNum());
		sysUser.setPersonaId(sysUser.getPersonaId());
		sysUser.setRemarks(sysUser.getRemarks());
		sysUser.setStatus(sysUser.getStatus());
		sysUser.setSysOrganizationsId(sysUser.getSysOrganizationsId());
		sysUser.setValidFlag(sysUser.isValidFlag());
		this.sysUsersManager.saveOrUpdateObject(sysUser);
		return new ModelAndView("hygl/mc_mima","info","OK");
		
	}
	
	
	

	@Override
	protected void convertStringToDate(HttpServletRequest request,
			Object command) {
		// TODO Auto-generated method stub
		
	}




	public FmptJfManager getFmptJfmanager() {
		return fmptJfmanager;
	}




	public void setFmptJfmanager(FmptJfManager fmptJfmanager) {
		this.fmptJfmanager = fmptJfmanager;
	}




	public SysUsersManager getSysUsersManager() {
		return sysUsersManager;
	}




	public void setSysUsersManager(SysUsersManager sysUsersManager) {
		this.sysUsersManager = sysUsersManager;
	}




	public FmptHyJbManager getFmpthyjbmanager() {
		return fmpthyjbmanager;
	}




	public void setFmpthyjbmanager(FmptHyJbManager fmpthyjbmanager) {
		this.fmpthyjbmanager = fmpthyjbmanager;
	}
	

}
