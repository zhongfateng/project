package com.nbw.threeStruct.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.nbw.common.NbwController;
import com.nbw.common.util.PageBean;
import com.nbw.threeStruct.domain.ThreeStructure;
import com.nbw.threeStruct.service.StructManager;

public class StructController extends NbwController{
	
	
	
	
	private StructManager structmanager;
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 搜索页面
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontThreeStruct(HttpServletRequest request,
			HttpServletResponse response, ThreeStructure command, BindException errors)
			throws ServletException, IOException {
		
		return new ModelAndView("threeStruct/threeStructSearch");
	}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 搜索菌种列表
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontThreeStructSearch(HttpServletRequest request,
			HttpServletResponse response, ThreeStructure command, BindException errors)
			throws ServletException, IOException {
		
		String speciesName = request.getParameter("speciesName");
		List<String[]> tsList = this.structmanager.findBySpeciesName(speciesName);//菌株的集合
		int sum = this.structmanager.findBySpeciesNameSum(speciesName);//菌种和菌株的总数
		if(sum!=0){//如果总数不为0说明数据库里有
			request.setAttribute("speciesName", speciesName.toUpperCase().charAt(0)+speciesName.substring(1, speciesName.length()));
			request.setAttribute("sum", sum);
		}else{
			request.setAttribute("speciesName", "");
			request.setAttribute("sum", "");
		}
		
		return new ModelAndView("threeStruct/threeStructList","dataList",tsList);
	}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 菌株的详细信息
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontThreeStructDetail(HttpServletRequest request,
			HttpServletResponse response, ThreeStructure command, BindException errors)
			throws ServletException, IOException {
		
		String speciesName = request.getParameter("speciesName");
		int pageSize = 10;
		int page = 1;
		request.setAttribute("speciesName", speciesName);
		if (request.getParameter("page") != null) {
			String pageString = (String) request.getParameter("page");
			try {
				page = Integer.parseInt(pageString);
			} catch (NumberFormatException e) {
				System.out.println("数字类型错误:" + e.getMessage());
			}
		}
		if(request.getParameter("pageSize")!=null){
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("page", page);
		PageBean threeStructList = this.structmanager.findByName(speciesName,page,pageSize);
		
		return new ModelAndView("threeStruct/threeStructDetail","dataList",threeStructList);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 菌种和菌株的所有详细信息。
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontThreeStructDetailSum(HttpServletRequest request,
			HttpServletResponse response, ThreeStructure command, BindException errors)
			throws ServletException, IOException {
		
		String speciesName = request.getParameter("speciesName");
		int pageSize = 10;
		int page = 1;
		request.setAttribute("speciesName", speciesName);
		if (request.getParameter("page") != null) {
			String pageString = (String) request.getParameter("page");
			try {
				page = Integer.parseInt(pageString);
			} catch (NumberFormatException e) {
				System.out.println("数字类型错误:" + e.getMessage());
			}
		}
		if(request.getParameter("pageSize")!=null){
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("page", page);
		PageBean threeStructList = this.structmanager.findByNameSum(speciesName,page,pageSize);
		
		return new ModelAndView("threeStruct/threeStructDetail","dataList",threeStructList);
	}
	
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 进入三维数据处理页面
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView toThreeStruct(HttpServletRequest request,
			HttpServletResponse response, ThreeStructure command, BindException errors)
			throws ServletException, IOException {
		
		return new ModelAndView("threeStruct/paseThreeStruct");
	}
	
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 处理三维数据文件
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView toPaseThreeStruct(HttpServletRequest request,
			HttpServletResponse response, ThreeStructure command, BindException errors)
			throws ServletException, IOException {
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		String filePath = request.getParameter("filePath");
		System.out.println("路径："+filePath);
		String speciesName = request.getParameter("speciesName");
		PrintWriter out = response.getWriter();
		int flag = this.structmanager.paseThree(filePath,speciesName);
		int[] ary = new int[1];
		ary[0] = flag;
		JSONArray obj = JSONArray.fromObject(ary);
		out.println(obj.toString());
		out.close();
		return new ModelAndView("threeStruct/paseThreeStruct");
	}
	
	

	@Override
	protected void convertStringToDate(HttpServletRequest request,
			Object command) {
		// TODO Auto-generated method stub
		
	}


	public StructManager getStructmanager() {
		return structmanager;
	}


	public void setStructmanager(StructManager structmanager) {
		this.structmanager = structmanager;
	}
	
	
}
