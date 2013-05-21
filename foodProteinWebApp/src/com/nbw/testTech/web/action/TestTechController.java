package com.nbw.testTech.web.action;

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
import com.nbw.testTech.domain.TestTech;
import com.nbw.testTech.service.TestTechManager;

public class TestTechController extends NbwController{
	
	
	private TestTechManager testTechmanager;
	
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 进入search页面
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontTestTech(HttpServletRequest request,
			HttpServletResponse response, TestTech command, BindException errors)
			throws ServletException, IOException {
		
		List<String> cnameList = this.testTechmanager.findAllCname();//加载select选项

		return new ModelAndView("testTech/techSearch","dataList",cnameList);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 搜索结果显示到列表页面
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontTestTechSearch(HttpServletRequest request,
			HttpServletResponse response, TestTech command, BindException errors)
			throws ServletException, IOException {
			
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		
		
		String cname = request.getParameter("cname");
		cname = URLDecoder.decode(cname,"UTF-8");
		System.out.println("Name:"+cname);
		
		List<String> cnameList = this.testTechmanager.findAllCname();
		TestTech testTech  = this.testTechmanager.findByCname(cname);
		request.setAttribute("testTech", testTech);
		return new ModelAndView("testTech/techList","dataList",cnameList);
	}
	
	
	
	
	
	

	@Override
	protected void convertStringToDate(HttpServletRequest request,
			Object command) {
		// TODO Auto-generated method stub
		
	}







	public TestTechManager getTestTechmanager() {
		return testTechmanager;
	}







	public void setTestTechmanager(TestTechManager testTechmanager) {
		this.testTechmanager = testTechmanager;
	}
	
	
	

}
