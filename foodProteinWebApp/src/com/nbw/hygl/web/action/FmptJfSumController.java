package com.nbw.hygl.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.nbw.common.NbwController;
import com.nbw.hygl.domain.FmptJfSum;
import com.nbw.hygl.service.FmptJfSumManager;

public class FmptJfSumController extends NbwController{
	
	
	private FmptJfSumManager fmptJfSummanager;
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 用户积分列表
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView toUserJfList(HttpServletRequest request,
			HttpServletResponse response, FmptJfSum command, BindException errors)
			throws ServletException, IOException {
		
		
	//List<FmptJfSum> jfsList = this.fmptJfSummanager.getAll();
		
		return new ModelAndView("hygl/fmptJfSumList");
		
	}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 所以的用户积分
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView toUserJfAllList(HttpServletRequest request,
			HttpServletResponse response, FmptJfSum command, BindException errors)
			throws ServletException, IOException {
		
		String orgid = request.getParameter("orgId");
		request.setAttribute("orgid", orgid);
		List<FmptJfSum> jfsList = null;
		
		if(orgid.equals("ROOT")){
			
			jfsList = this.fmptJfSummanager.getAll();
		}else{
			 jfsList = this.fmptJfSummanager.findByOrgid(orgid.trim());
		}
		
		return new ModelAndView("hygl/fmptJfSumAll","dataList",jfsList);
		
	}
	
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 修改
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView toModifyUserJf(HttpServletRequest request,
			HttpServletResponse response, FmptJfSum command, BindException errors)
			throws ServletException, IOException {
		
		String id = request.getParameter("objectId");
		String org = request.getParameter("orgid");
		request.setAttribute("orgid", org);
		FmptJfSum fsum = this.fmptJfSummanager.findById(id);
		
		
		return new ModelAndView("hygl/fmptJfSumModify","fsum",fsum);
		
	}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 更新
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView toUpJfSum(HttpServletRequest request,
			HttpServletResponse response, FmptJfSum command, BindException errors)
			throws ServletException, IOException {
		
		String id = request.getParameter("tid");
		String username = request.getParameter("username");
		String fsum = request.getParameter("fsum");
		String trname = request.getParameter("trname");
		String orgid = request.getParameter("orgid");
		request.setAttribute("orgid", orgid);
		FmptJfSum fs = new FmptJfSum();
		fs.setId(id);
		fs.setJfsum(Integer.parseInt(fsum));
		fs.setUsername(username);
		fs.setTrname(trname);
		this.fmptJfSummanager.saveOrUpdate(fs);
		
		List<FmptJfSum> jfsList = null;
		
		if(orgid.equals("ROOT")){
			
			jfsList = this.fmptJfSummanager.getAll();
		}else{
			 jfsList = this.fmptJfSummanager.findByOrgid(orgid.trim());
		}
		
		return new ModelAndView("hygl/fmptJfSumAll","dataList",jfsList);
		
	}
	
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView toDelUserJf(HttpServletRequest request,
			HttpServletResponse response, FmptJfSum command, BindException errors)
			throws ServletException, IOException {
		
		String id = request.getParameter("objectId");
		String orgid = request.getParameter("orgid");
		this.fmptJfSummanager.deleteFmptJfSum(id);
		
		List<FmptJfSum> jfsList = null;
		
		if(orgid.equals("ROOT")){
			
			jfsList = this.fmptJfSummanager.getAll();
		}else{
			 jfsList = this.fmptJfSummanager.findByOrgid(orgid.trim());
		}
		
		
		return new ModelAndView("hygl/fmptJfSumAll","dataList",jfsList);
		
	}
	
	
	@Override
	protected void convertStringToDate(HttpServletRequest request,
			Object command) {
		// TODO Auto-generated method stub
		
	}



	public FmptJfSumManager getFmptJfSummanager() {
		return fmptJfSummanager;
	}


	public void setFmptJfSummanager(FmptJfSumManager fmptJfSummanager) {
		this.fmptJfSummanager = fmptJfSummanager;
	}

}
