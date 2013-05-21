package com.nbw.techDy.web.action;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.icu.text.SimpleDateFormat;
import com.nbw.common.NbwController;
import com.nbw.common.util.PageBean;
import com.nbw.techDy.domain.TechDy;
import com.nbw.techDy.service.TechDyManager;

public class TechDyController extends NbwController{
	
	private TechDyManager techDymanager;
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 科技动态列表
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontTechDyList(HttpServletRequest request,
			HttpServletResponse response, TechDy command, BindException errors)
			throws ServletException, IOException {
		
		int page = 1;
		int pageSize = 10;
		
		if(request.getParameter("page")!=null){
			page = Integer.parseInt(request.getParameter("page"));
		}
		if(request.getParameter("pageSize")!=null){
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}
		request.setAttribute("pageSize", pageSize);
		
		PageBean pageBean = this.techDymanager.findByTime(page,pageSize);
		
		return new ModelAndView("techDy/techDy","dataList",pageBean);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 文章详情
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontTechDyDetail(HttpServletRequest request,
			HttpServletResponse response, TechDy command, BindException errors)
			throws ServletException, IOException {
		
		String tid = request.getParameter("tid");
		
		TechDy techDy = this.techDymanager.findByTid(tid);
		
		return new ModelAndView("techDy/techDyDetail","techDy",techDy);
	}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 后台上传
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView toFileUp(HttpServletRequest request,
			HttpServletResponse response, TechDy command, BindException errors)
			throws ServletException, IOException {
			
		List<TechDy> tdList = this.techDymanager.getAll();
		
		
		return new ModelAndView("techDy/listAll","dataList",tdList);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 删除记录
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView toDelTd(HttpServletRequest request,
			HttpServletResponse response, TechDy command, BindException errors)
			throws ServletException, IOException {
			
		String id = request.getParameter("objectId");
		TechDy td = this.techDymanager.findByTid(id);
		this.techDymanager.deleteTechDy(td);
		
		List<TechDy> tdList = this.techDymanager.getAll();
		return new ModelAndView("techDy/listAll","dataList",tdList);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 编辑页面
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView toEditDy(HttpServletRequest request,
			HttpServletResponse response, TechDy command, BindException errors)
			throws ServletException, IOException {
			
		return new ModelAndView("techDy/fileUp");
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 修改页面
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView toModifyTd(HttpServletRequest request,
			HttpServletResponse response, TechDy command, BindException errors)
			throws ServletException, IOException {
			
		
		String id = request.getParameter("objectId");
		TechDy td = this.techDymanager.findByTid(id);
		request.setAttribute("tid", id);
		
		return new ModelAndView("techDy/modify","techDy",td);
	}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 保存
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView toSaveFile(HttpServletRequest request,
			HttpServletResponse response, TechDy command, BindException errors)
			throws ServletException, IOException {
		
		
		String content = request.getParameter("content1");
		String fileTitle = request.getParameter("fileTitle");
		String fileTime = request.getParameter("fileTime");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
    	try {
			 date = (Date) sdf.parse(fileTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String fileSource = request.getParameter("fileSource");
		String fileAuthor = request.getParameter("fileAuthor");
			TechDy tes = new TechDy();
			tes.setTcontent(content);
			tes.setTitle(fileTitle);
			tes.setTsource(fileSource);
			tes.setTsum(0);
			tes.setTtime(date);
			tes.setTauthor(fileAuthor);
			this.techDymanager.saveTechDy(tes);
			
		List<TechDy> tdList = this.techDymanager.getAll();
		return new ModelAndView("techDy/listAll","dataList",tdList);
	}
	
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 提交更改后的
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView toUpdateFile(HttpServletRequest request,
			HttpServletResponse response, TechDy command, BindException errors)
			throws ServletException, IOException {
		
		
		String content = request.getParameter("content1");
		String fileTitle = request.getParameter("fileTitle");
		String fileTime = request.getParameter("fileTime");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
    	try {
			 date = (Date) sdf.parse(fileTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String fileSource = request.getParameter("fileSource");
		String fileAuthor = request.getParameter("fileAuthor");
		String tid = request.getParameter("tid");
		System.out.println("tid:"+tid);
			TechDy tes = new TechDy();
			tes.setTcontent(content);
			tes.setTitle(fileTitle);
			tes.setTsource(fileSource);
			tes.setTsum(0);
			tes.setTtime(date);
			tes.setTauthor(fileAuthor);
			tes.setTid(Integer.parseInt(tid));
			this.techDymanager.saveOrUpdate(tes);
			
		List<TechDy> tdList = this.techDymanager.getAll();
		response.sendRedirect("techDy.action?m=toFileUp");
		return null;
	}

	

	@Override
	protected void convertStringToDate(HttpServletRequest request,
			Object command) {
		// TODO Auto-generated method stub
		
	}




	public TechDyManager getTechDymanager() {
		return techDymanager;
	}




	public void setTechDymanager(TechDyManager techDymanager) {
		this.techDymanager = techDymanager;
	}
	
	
	

}
