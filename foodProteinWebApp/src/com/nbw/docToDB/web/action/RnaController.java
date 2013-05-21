package com.nbw.docToDB.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.nbw.common.NbwController;
import com.nbw.common.util.DateUtils;
import com.nbw.common.util.PageBean;
import com.nbw.docToDB.domain.Gene;
import com.nbw.docToDB.domain.Rna;
import com.nbw.docToDB.service.RnaManager;
import com.nbw.indexMain.domain.VisitItem;

/**
 * 
 * RnaController
 * 
 * @author  wangshaobin
 * 
 */
public class RnaController extends NbwController {


    private RnaManager rnamanager;
    
    
    /**
     * 
     * @param request
     * @param response
     * @param command
     * @param errors
     * @return 进入FRN处理页面
     * @throws ServletException
     * @throws IOException
     */
	public ModelAndView toFRNPage(HttpServletRequest request,
			HttpServletResponse response, Rna command, BindException errors)
			throws ServletException, IOException {

		return new ModelAndView("docToDB/rnaForm");
	}
	
	
	public ModelAndView frontRnaSearch(HttpServletRequest request,
			HttpServletResponse response, Rna command, BindException errors)
			throws ServletException, IOException {

		return new ModelAndView("rna/rna_search");
	}
	
    /**
     * 
     * @param request
     * @param response
     * @param command
     * @param errors
     * @return rna标准检索列表
     * @throws ServletException
     * @throws IOException
     */
	public ModelAndView frontRnaList(HttpServletRequest request,
			HttpServletResponse response, Rna command, BindException errors)
			throws ServletException, IOException {

		
		String rnaInfo = request.getParameter("rnaInfo");
		int pageSize = 10;
		int page = 1;
		request.setAttribute("INFO", rnaInfo);
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
		PageBean rnaList = this.rnamanager.searchRna(rnaInfo,page,pageSize);
		
		
		HttpSession session = request.getSession();
		List<VisitItem> vItemList = (List<VisitItem>) session.getAttribute("VisitItemList");
		if(vItemList==null){
			vItemList=new ArrayList<VisitItem>();
			VisitItem item = new VisitItem();
			item.setSerchWord(rnaInfo);
			item.setSerchDb("RNA库");
			item.setSerchUrl("rna.action?m=frontRnaList&rnaInfo="+rnaInfo);
			vItemList.add(item);
			session.setAttribute("VisitItemList", vItemList);
		}else{
			
			boolean flag = true;
			
			for(int i=0;i<vItemList.size();i++){
				VisitItem item = vItemList.get(i);
				if(item.getSerchWord().equals(rnaInfo)&&item.getSerchDb().equals("RNA库")){
					flag = false;
				}
			}
			
			if(flag){
				VisitItem item = new VisitItem();
				item.setSerchWord(rnaInfo);
				item.setSerchDb("RNA库");
				item.setSerchUrl("rna.action?m=frontRnaList&rnaInfo="+rnaInfo);
				vItemList.add(item);
			}
			
		}
		
		
		request.setAttribute("itemList", vItemList);
		
		return new ModelAndView("rna/rna_list", "dataList",rnaList);
	}
	
	
	
	
	
	public ModelAndView frontIdRnaList(HttpServletRequest request,
			HttpServletResponse response, Rna command, BindException errors)
			throws ServletException, IOException {

		
		String rnaId = request.getParameter("rnaId");
		String rnaInfo = this.rnamanager.findByRnaIds(rnaId);
		int pageSize = 10;
		int page = 1;
		request.setAttribute("INFO", rnaInfo);
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
		PageBean rnaList = this.rnamanager.searchRnaById(rnaId,page,pageSize);
		return new ModelAndView("rna/rna_list", "dataList",rnaList);
	}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 高级检索列表
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontAdRnaList(HttpServletRequest request,
			HttpServletResponse response, Rna command, BindException errors)
			throws ServletException, IOException {

		
		String RnaID = request.getParameter("RnaID");
		String NCBISpeciesID = request.getParameter("NCBISpeciesID");
		String chromosomeRef = request.getParameter("chromosomeRef");
		
		String key_1 = request.getParameter("key_1");
		String key_2 = request.getParameter("key_2");
		
		String[] key = new String[3];
		key[0] = key_1;
		key[1] = key_2;
		
		int pageSize = 10;
		int page = 1;
		request.setAttribute("RnaID", RnaID);
		request.setAttribute("NCBISpeciesID", NCBISpeciesID);
		request.setAttribute("chromosomeRef", chromosomeRef);
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
		PageBean rnaList = this.rnamanager.adSearchRna(NCBISpeciesID,RnaID,chromosomeRef,page,pageSize,key);
		
		if(rnaList.getList().size()!=0){
			Rna g = (Rna) rnaList.getList().get(0);
			request.setAttribute("INFO", g.getSpeciesName());
		}else{
			request.setAttribute("INFO", "");
		}
		
		
		HttpSession session = request.getSession();
		List<VisitItem> vItemList = (List<VisitItem>) session.getAttribute("VisitItemList");
		request.setAttribute("itemList", vItemList);
		
		return new ModelAndView("rna/rna_list", "dataList",rnaList);
	}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return rna详情
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontRnaDetail(HttpServletRequest request,
			HttpServletResponse response, Rna command, BindException errors)
			throws ServletException, IOException {

		
		String id = request.getParameter("rnaId");
		Rna rna = this.rnamanager.findByRnaId(id);
		
		int pageSize = 5;
		int page = 1;
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
		request.setAttribute("pageSize", pageSize);//在页面中使用
		PageBean pageBean = this.rnamanager.findDocByRnaId(id,page,pageSize);
		
		//拆分基因序列，每10个字母为一组    
		String seque = rna.getSequence();
       	List<String> seqList = new ArrayList<String>();//组的集合
        int m = 10;//10个字母一组
		int size = seque.length();//字符串总长度
		int j = 0;
		if(size%m==0){
			j = size/m;//共有多少组
		}else{
			j = size/m+1;
		}
		for(int i = 0; i < j; i++){
			
			if(i==j-1){
				//System.out.println(i*10+1 +"   "+seque.substring(i*10, size));
				seqList.add(seque.substring(i*10, size));
			}else{
				
				//System.out.print(i*10+1 +"   "+seque.substring(i*10, i*10+10)+"   ");
				seqList.add(seque.substring(i*10, i*10+10));
				/*if((i+1)%5==0){
					System.out.println();
				}*/
				
			}
			
		}
		
		
		HttpSession session = request.getSession();
		List<VisitItem> vItemList = (List<VisitItem>) session.getAttribute("VisitItemList");
		request.setAttribute("itemList", vItemList);
		
		
		request.setAttribute("seqList", seqList);
		request.setAttribute("pageBean", pageBean);
		return new ModelAndView("rna/rna_detail","rna",rna);
	}
	
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return rna文献列表
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontRnaDocList(HttpServletRequest request,
			HttpServletResponse response, Rna command, BindException errors)
			throws ServletException, IOException {

		
		String id = request.getParameter("rnaId");
		Rna rna = this.rnamanager.findByRnaId(id);
		
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
		if(request.getParameter("pageSize")!=null){
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}
		request.setAttribute("pageSize", pageSize);//在页面中使用
		request.setAttribute("page", page);
		PageBean pageBean = this.rnamanager.findDocByRnaId(id,page,pageSize);
		
		request.setAttribute("pageBean", pageBean);
		
		HttpSession session = request.getSession();
		List<VisitItem> vItemList = (List<VisitItem>) session.getAttribute("VisitItemList");
		request.setAttribute("itemList", vItemList);
		
		return new ModelAndView("rna/rna_docList","rna",rna);
	}
	
    /**
     * 
     * @param request
     * @param response
     * @param command
     * @param errors
     * @return 处理FRN格式文件。
     * @throws ServletException
     * @throws IOException
     */
	public ModelAndView toParseRNA(HttpServletRequest request,
			HttpServletResponse response, Rna command, BindException errors)
			throws ServletException, IOException {

		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String filepath = request.getParameter("rnaPath");
		int rna = this.rnamanager.parseRNA(filepath);
		int[] ary = new int[1];
		ary[0] = rna;
		JSONArray obj = JSONArray.fromObject(ary);
		out.println(obj.toString());
		out.close();
		return new ModelAndView("docToDB/success");
	}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 执行sql语句:更新rna表的数据项。
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView toSQL(HttpServletRequest request,
			HttpServletResponse response, Rna command, BindException errors)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		String ncname = request.getParameter("ncName");
		int sql = this.rnamanager.doSql(ncname);
		int[] ary = new int[1];
		ary[0] = sql;
		JSONArray obj = JSONArray.fromObject(ary);
		out.println(obj.toString());
		out.close();
		return new ModelAndView("docToDB/success");
	}
    
    
    /**
	 * constructor
	 *  
	 * @throws ClassNotFoundException
	 */
	public RnaController() throws ClassNotFoundException {
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
	 * @throws ServletException, IOException
	 */
	public ModelAndView loadData(HttpServletRequest request,
			HttpServletResponse response, Rna command, BindException errors)
			throws ServletException, IOException {
		return new ModelAndView("docToDB/rnaList","dataList",this.rnamanager.getAll());
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
	 * @throws ServletException, IOException
	 */
	public ModelAndView toAddPage(HttpServletRequest request,
			HttpServletResponse response, Rna command, BindException errors)
			throws ServletException, IOException {
		return new ModelAndView("docToDB/rnaForm");
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
	 * @throws ServletException, IOException
	 */
	public ModelAndView toEditPage(HttpServletRequest request,
			HttpServletResponse response, Rna command, BindException errors)
			throws ServletException, IOException {
		String id = request.getParameter("objectId");
		command = this.rnamanager.findById(id);
		return new ModelAndView("docToDB/rnaForm","rna",command);
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
	 * @throws ServletException, IOException
	 */
	public ModelAndView save(HttpServletRequest request,
			HttpServletResponse response, Rna command, BindException errors)
			throws ServletException, IOException {
		if (command!=null&&!"".equals(command.getId())){
			this.rnamanager.editRna(command);
		} else {
			this.rnamanager.saveRna(command);
		}
		return new ModelAndView("docToDB/rnaList","dataList",this.rnamanager.getAll());
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
	 * @throws ServletException, IOException
	 */	
	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response, Rna command, BindException errors)
			throws ServletException, IOException {
		String id = request.getParameter("objectId");		
		try {
			this.rnamanager.deleteRna(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("docToDB/rnaList","dataList",this.rnamanager.getAll());
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
	 * @throws ServletException, IOException
	 */	
	public ModelAndView validateData(HttpServletRequest request,
			HttpServletResponse response, Rna command, BindException errors)
			throws ServletException, IOException {
	    response.setContentType("text/html; charset=utf-8");    
	    response.setCharacterEncoding("utf-8"); 
	    PrintWriter out = response.getWriter (); 
		String id = request.getParameter("id");
		Rna rna = this.rnamanager.findById(id);
//		if(rna==null){
		if(!"123".equals(id)){
			out.print("success");
		}else{
			out.print("id="+id+"的对象已经存在！");
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
	protected void convertStringToDate(HttpServletRequest request,Object command){
		Rna tempObject = (Rna)command;
	}
	


    //*******************************************************************************set and get	
	public RnaManager getRnamanager() {
		return rnamanager;
	}

	public void setRnamanager(RnaManager rnamanager) {
		this.rnamanager = rnamanager;
	}
}

