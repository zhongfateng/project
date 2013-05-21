package com.nbw.lucene.web.action;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.nbw.common.NbwController;
import com.nbw.common.util.PageBean;
import com.nbw.lucene.domain.Doctxt;
import com.nbw.lucene.service.DoctxtManager;
import com.nbw.lucene.util.ExtractXml;
import com.nbw.lucene.util.Pageable;
import com.nbw.lucene.util.SearchPDF;

/**
 * 
 * DoctxtController
 * 
 * @author 
 * 
 */
public class DoctxtController extends NbwController {
    private DoctxtManager doctxtmanager;
    /**
	 * constructor
	 *  
	 * @throws ClassNotFoundException
	 */
    private List<String> listpmid;
	public DoctxtController() throws ClassNotFoundException {
		super();
	}
	/**
	 * ?????????
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 *  
	 * @throws ServletException, IOException
	 */
	//根据物种的id获得文献的全部列表
	public ModelAndView getDocList(HttpServletRequest request,
			HttpServletResponse response, Doctxt command, BindException errors)
	throws ServletException, IOException {
	 
	 
	String gid= (String)request.getAttribute("gid");
	 
	
	Integer cgid= Integer.parseInt(gid);
	 
	 List<Doctxt>  list= doctxtmanager.getListDoctxtByGid(cgid);
	 
	 
	 return null;
	 
	 
	 
	 
	
}
	
	
	
	
	public ModelAndView loadData(HttpServletRequest request,
			HttpServletResponse response, Doctxt command, BindException errors)
			throws ServletException, IOException {
		return new ModelAndView("lucene/doctxtList","dataList",this.doctxtmanager.getAll());
	}
	 public ModelAndView fronttosearch(HttpServletRequest request,
				HttpServletResponse response, Doctxt command, BindException errors) throws ServletException, IOException,Exception 
				{
					return new ModelAndView("lucene/Searchwenxian");
				}
//		public ModelAndView frontsearch(HttpServletRequest request,
//				HttpServletResponse response, Doctxt command, BindException errors) throws ServletException, IOException,Exception 
//		{
//			String parameter =request.getParameter("searchWord");
//			List<String>  list=SearchPDF.search(parameter);	
//			listpmid=list;
//			if(list.size()==0)
//			{
//				return new ModelAndView("lucene/Searchwenxian");
//			} 
//			else
//			{
//			response.sendRedirect("search.action?m=fronttoload");
//			}
//			return new ModelAndView("search.action?m=fronttoload");
//		}
		public ModelAndView fronttoload(HttpServletRequest request,
				HttpServletResponse response, Doctxt command, BindException errors) throws ServletException, IOException,Exception 
				{
			
			String parameter =request.getParameter("searchWord");
			List<String>  list=SearchPDF.search(parameter,true);
			
			request.setAttribute("test",String.valueOf(list.size()));
			
			if(list.size()!=0)
			{	
				
				int page=1 ;
				int pageSize=10;
//				if(request.getParameter("page")==null)
//				{
//				 page=1;
//				}
//				else
//				{
//					page=Integer.parseInt(request.getParameter("page"));
//					
//				}
//				if(request.getParameter("pageSize")==null)
//				{
//					
//					pageSize=10;
//					
//				}
//				else
//				{
//					pageSize=Integer.parseInt(request.getParameter("pageSize"));
//					
//				}
				if (request.getParameter("page") != null) {
					String pageString = (String) request.getParameter("page");
					try {
						page = Integer.parseInt(pageString);
					} catch (NumberFormatException e) {
						System.out.println("数字类型错误:" + e.getMessage());
					}
				}
				
				if (request.getParameter("pageSize") != null) {
					String pageSizeString = (String) request.getParameter("pageSize");
					try {
						pageSize = Integer.parseInt(pageSizeString);
					} catch (NumberFormatException e) {
						System.out.println("数字类型错误:" + e.getMessage());
					}
				}
				
					Pageable pageable=new Pageable(list);
					
					request.setAttribute("totalnum", pageable.getList().size());
					request.setAttribute("total", pageable.getMaxPages());
					request.setAttribute("searchWord", parameter);
					pageable.setPageSize(pageSize);
					pageable.setPage(page);
				
					request.setAttribute("pageable", pageable);
					list=pageable.getListForPage();
					
					List<Doctxt> listDoctxt=doctxtmanager.getDocTxtByList(list);
					return new ModelAndView("lucene/SearchList","searchResult",listDoctxt);
				
			}
			else
			{	
				return 	new ModelAndView("lucene/SearchList","message","没找到相关记录！！！！");
			}
	}
		public ModelAndView fronttodetail(HttpServletRequest request,
				HttpServletResponse response, Doctxt command, BindException errors) throws ServletException, IOException,Exception 
		{
			String pmid=request.getParameter("pmid");
			Doctxt doctxt=	doctxtmanager.getDoctxtByPmid(pmid);
			return new ModelAndView("lucene/SearchDetail","doctxt",doctxt);
		}
		public ModelAndView frontadvancesearch(HttpServletRequest request,
				HttpServletResponse response, Doctxt command, BindException errors) throws ServletException, IOException,Exception 
		{
			
		String Title=request.getParameter("Title");
		//String Title=request.getParameter("Title");
		String Author=request.getParameter("Author");
		String Abstract=request.getParameter("Abstract");
		String Journal=request.getParameter("Journal");
		String StartDate=request.getParameter("StartDate");
		String EndDate=request.getParameter("EndDate");
		
		
		StringBuffer sb=new StringBuffer();
		if(Title!=null&&!Title.equals("")&&!Title.equals("null"))
		{
			sb.append("&Title="+Title);
			Title=URLDecoder.decode(Title,"UTF-8");
			request.setAttribute("Title", Title);
		}
		if(Author!=null&&!Author.equals("")&&!Author.equals("null"))
		{
			sb.append("&Author="+Author);
			Author=URLDecoder.decode(Author,"UTF-8");
			request.setAttribute("Author", Author);
		}
		if(Abstract!=null&&!Abstract.equals("")&&!Abstract.equals("null"))
		{
			sb.append("&Abstract="+Abstract);
			Abstract=URLDecoder.decode(Abstract,"UTF-8");
			request.setAttribute("Abstract", Abstract);
		}
		if(Journal!=null&&!Journal.equals("")&&!Journal.equals("null"))
		{
			sb.append("&Journal="+Journal);
			Journal=URLDecoder.decode(Journal,"UTF-8");
			request.setAttribute("Journal", Journal);
		}
		if(StartDate!=null&&!StartDate.equals("")&&!StartDate.equals("null"))
		{
			
			StartDate=StartDate.substring(0, 4);
			
			sb.append("&StartDate="+StartDate);
			StartDate=URLDecoder.decode(StartDate,"UTF-8");
			request.setAttribute("StartDate", StartDate);
			
		}
		if(EndDate!=null&&!EndDate.equals("")&&!EndDate.equals("null"))
		{
			
			EndDate=EndDate.substring(0,4);
			sb.append("&EndDate="+EndDate);
			EndDate=URLDecoder.decode(EndDate,"UTF-8");
			request.setAttribute("EndDate", EndDate);
		}
		
		
		String str=sb.toString();
		if(str!=null&&!str.equals(""))
			
		{
		
		//request.setAttribute("arg",str);
		int page = 1;
		int pageSize = 10;

		
		if (request.getParameter("page") != null) {
			String pageString = (String) request.getParameter("page");
			try {
				page = Integer.parseInt(pageString);
			} catch (NumberFormatException e) {
				System.out.println("数字类型错误:" + e.getMessage());
			}
		}
		
		if (request.getParameter("pageSize") != null) {
			String pageSizeString = (String) request.getParameter("pageSize");
			try {
				pageSize = Integer.parseInt(pageSizeString);
			} catch (NumberFormatException e) {
				System.out.println("数字类型错误:" + e.getMessage());
			}
		}
		PageBean dataList = this.doctxtmanager.advanceSearchList(Title, Author, Abstract, Journal, page, pageSize,StartDate,EndDate);
		return new ModelAndView("lucene/AdvanceSearchList", "dataList", dataList);
		}
		else
		{
			
			return new ModelAndView("lucene/Searchwenxian");
		}
		}
    /**
	 * ?????????
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 *  
	 * @throws ServletException, IOException
	 */
	public ModelAndView toAddPage(HttpServletRequest request,
			HttpServletResponse response, Doctxt command, BindException errors)
			throws ServletException, IOException {
		return new ModelAndView("lucene/doctxtForm");
	}
    /**
	 * ?????????
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * 
	 * @throws ServletException, IOException
	 */
	public ModelAndView toEditPage(HttpServletRequest request,
			HttpServletResponse response, Doctxt command, BindException errors)
			throws ServletException, IOException {
		String id = request.getParameter("objectId");
		command = this.doctxtmanager.findById(id);
		return new ModelAndView("lucene/doctxtForm","doctxt",command);
	}

    /**
	 * ??????
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * 
	 * @throws ServletException, IOException
	 */
	public ModelAndView save(HttpServletRequest request,
			HttpServletResponse response, Doctxt command, BindException errors)
			throws ServletException, IOException {
		if (command!=null&&command.getDid()!=null&&!"".equals(command.getDid())){
			this.doctxtmanager.editDoctxt(command);
		} else {
			this.doctxtmanager.saveDoctxt(command);
		}
		return new ModelAndView("lucene/doctxtList","dataList",this.doctxtmanager.getAll());
	}
	/**
	 * ??????
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * 
	 * @throws ServletException, IOException
	 */	
	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response, Doctxt command, BindException errors)
			throws ServletException, IOException {
		String id = request.getParameter("objectId");		
		try {
			this.doctxtmanager.deleteDoctxt(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("lucene/doctxtList","dataList",this.doctxtmanager.getAll());
	}
	
	public ModelAndView toParseXml(HttpServletRequest request,
			HttpServletResponse response, Doctxt command, BindException errors)
	throws ServletException, IOException
	{
		
		return new ModelAndView("lucene/parseXml");
		
	}
	
	
	
	
	public ModelAndView parseXml(HttpServletRequest request,
			HttpServletResponse response, Doctxt command, BindException errors)
	throws ServletException, IOException
	{
		
		String docpath=	request.getParameter("docpath");
		String speciesname=request.getParameter("speciesname");
		try
		{
		int i=ExtractXml.parseXml(docpath, speciesname);
		
		return new  ModelAndView("lucene/success","num",i);
		}
		catch (Exception e) {
			return new ModelAndView("lucene/fail","message","请你检查文献的格式与路径是否正确");
		}
		
	}
	public ModelAndView frontToLoadOne(HttpServletRequest request,
			HttpServletResponse response, Doctxt command, BindException errors) throws ServletException, IOException,Exception 
			{
		
		String parameter =request.getParameter("searchWord");
		parameter=URLDecoder.decode(parameter,"UTF-8");
		
		List<String>  list=SearchPDF.search(parameter,false);
		
		request.setAttribute("test",String.valueOf(list.size()));
		
		if(list.size()!=0)
		{	
			int page=1 ;
			int pageSize=10;
//			if(request.getParameter("page")==null)
//			{
//			 page=1;
//			}
//			else
//			{
//				page=Integer.parseInt(request.getParameter("page"));
//				
//			}
//			if(request.getParameter("pageSize")==null)
//			{
//				
//				pageSize=10;
//				
//			}
//			else
//			{
//				pageSize=Integer.parseInt(request.getParameter("pageSize"));
//				
//			}
			if (request.getParameter("page") != null) {
				String pageString = (String) request.getParameter("page");
				try {
					page = Integer.parseInt(pageString);
				} catch (NumberFormatException e) {
					System.out.println("数字类型错误:" + e.getMessage());
				}
			}
			
			if (request.getParameter("pageSize") != null) {
				String pageSizeString = (String) request.getParameter("pageSize");
				try {
					pageSize = Integer.parseInt(pageSizeString);
				} catch (NumberFormatException e) {
					System.out.println("数字类型错误:" + e.getMessage());
				}
			}
			
				Pageable pageable=new Pageable(list);
				
				request.setAttribute("totalnum", pageable.getList().size());
				request.setAttribute("total", pageable.getMaxPages());
				request.setAttribute("searchWord", parameter);
				pageable.setPageSize(pageSize);
				pageable.setPage(page);
			
				request.setAttribute("pageable", pageable);
				list=pageable.getListForPage();
				
				List<Doctxt> listDoctxt=doctxtmanager.getDocTxtByList(list);
				return new ModelAndView("lucene/SearchListOne","searchResult",listDoctxt);
			
		}
		else
		{	
			return 	new ModelAndView("lucene/SearchListOne","message","没找到相关记录！！！！");
		}
			}
	
	/**
	 * ??????
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * 
	 * @throws ServletException, IOException
	 */	
	public ModelAndView validateData(HttpServletRequest request,
			HttpServletResponse response, Doctxt command, BindException errors)
			throws ServletException, IOException {
	    response.setContentType("text/html; charset=utf-8");    
	    response.setCharacterEncoding("utf-8"); 
	    PrintWriter out = response.getWriter (); 
		String id = request.getParameter("id");
		Doctxt doctxt = this.doctxtmanager.findById(id);
//		if(doctxt==null){
		if(!"123".equals(id)){
			out.print("success");
		}else{
			out.print("id="+id+"????????????");
		}		
		return null;
	}
	
	/**
	 * ???????????????
	 * 
	 * @param request
	 * @param command
	 * @return
	 */
	protected void convertStringToDate(HttpServletRequest request,Object command){
		Doctxt tempObject = (Doctxt)command;
	}

    //*******************************************************************************set and get	
	public DoctxtManager getDoctxtmanager() {
		return doctxtmanager;
	}
	public void setDoctxtmanager(DoctxtManager doctxtmanager) {
		this.doctxtmanager = doctxtmanager;
	}
	public List<String> getListpmid() {
		return listpmid;
	}
	public void setListpmid(List<String> listpmid) {
		this.listpmid = listpmid;
	}
}

