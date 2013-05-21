package com.nbw.testOrg.web.action;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.nbw.common.NbwController;
import com.nbw.common.util.PageBean;
import com.nbw.testOrg.domain.TestOrg;
import com.nbw.testOrg.service.TestOrgManager;

public class TestOrgController extends NbwController{
	
	private TestOrgManager testOrgmanager;
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 进入检测机构页面
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontTestOrg(HttpServletRequest request,
			HttpServletResponse response, TestOrg command, BindException errors)
			throws ServletException, IOException {
		
		List<String> nameList = this.testOrgmanager.findByOrgAddr();
		return new ModelAndView("testOrg/testOrgSearch","addrList",nameList);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 查询
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontTestOrgSearch(HttpServletRequest request,
			HttpServletResponse response, TestOrg command, BindException errors)
			throws ServletException, IOException {
		
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		
		String oname = request.getParameter("oname");
		String oaddr = request.getParameter("oaddr");
		
		oname = URLDecoder.decode(oname,"UTF-8");
		oaddr = URLDecoder.decode(oaddr,"UTF-8");
		
		int page = 1;
		int pageSize = 10;
		request.setAttribute("oname", oname);
		request.setAttribute("oaddr", oaddr);
		
		if(request.getParameter("page")!=null){
			page = Integer.parseInt(request.getParameter("page"));
		}
		if(request.getParameter("pageSize")!=null){
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}
		request.setAttribute("pageSize", pageSize);
		List<String> nameList = this.testOrgmanager.findByOrgAddr();
		request.setAttribute("addrList", nameList);
		PageBean pageBean = this.testOrgmanager.findByNameAndAddr(oname,oaddr,page,pageSize);
		return new ModelAndView("testOrg/testOrgList","dataList",pageBean);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 详细信息
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontTestOrgDetail(HttpServletRequest request,
			HttpServletResponse response, TestOrg command, BindException errors)
			throws ServletException, IOException {
		String tid = request.getParameter("tid");
		TestOrg testOrg = this.testOrgmanager.findByTid(tid);
		return new ModelAndView("testOrg/testOrgDetail","testOrg",testOrg);
	}
	
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return  首页列表
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontTestOrgList(HttpServletRequest request,
			HttpServletResponse response, TestOrg command, BindException errors)
			throws ServletException, IOException {
		
		
		List<TestOrg> testOrgList = this.testOrgmanager.findIndexFouth();
		return new ModelAndView("testOrg/testOrgDetail","dataList",testOrgList);
	}
	

	@Override
	protected void convertStringToDate(HttpServletRequest request,
			Object command) {
		// TODO Auto-generated method stub
		
	}
	public TestOrgManager getTestOrgmanager() {
		return testOrgmanager;
	}

	public void setTestOrgmanager(TestOrgManager testOrgmanager) {
		this.testOrgmanager = testOrgmanager;
	}

}
