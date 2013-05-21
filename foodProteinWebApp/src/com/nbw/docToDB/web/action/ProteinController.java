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

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.nbw.common.NbwController;
import com.nbw.common.util.PageBean;
import com.nbw.docToDB.domain.Gene;
import com.nbw.docToDB.domain.Protein;
import com.nbw.docToDB.service.ProteinManager;
import com.nbw.indexMain.domain.VisitItem;

/**
 * 
 * ProteinController
 * 
 * @author wangshaobin
 * 
 */
public class ProteinController extends NbwController {

	private ProteinManager proteinmanager;
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 进入FAA处理页面
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView toFAAPage(HttpServletRequest request,
			HttpServletResponse response, Protein command, BindException errors)
			throws ServletException, IOException {

		return new ModelAndView("docToDB/proteinForm");
	}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 进入蛋白质检索页面
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontProteinSearch(HttpServletRequest request,
			HttpServletResponse response, Protein command, BindException errors)
			throws ServletException, IOException {

		return new ModelAndView("cds/protein_search");
	}
	
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 蛋白列表
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontProteinList(HttpServletRequest request,
			HttpServletResponse response, Protein command, BindException errors)
			throws ServletException, IOException {

		
		String proteinInfo = request.getParameter("proteinInfo");
		int pageSize = 10;
		int page = 1;
		request.setAttribute("INFO", proteinInfo);
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
		PageBean proteinList = this.proteinmanager.searchProtein(proteinInfo,page,pageSize);
		
		HttpSession session = request.getSession();
		List<VisitItem> vItemList = (List<VisitItem>) session.getAttribute("VisitItemList");
		if(vItemList==null){
			vItemList=new ArrayList<VisitItem>();
			VisitItem item = new VisitItem();
			item.setSerchWord(proteinInfo);
			item.setSerchDb("蛋白库");
			item.setSerchUrl("protein.action?m=frontProteinList&proteinInfo="+proteinInfo);
			vItemList.add(item);
			session.setAttribute("VisitItemList", vItemList);
		}else{
			
			boolean flag = true;
			
			for(int i=0;i<vItemList.size();i++){
				VisitItem item = vItemList.get(i);
				if(item.getSerchWord().equals(proteinInfo)&&item.getSerchDb().equals("蛋白库")){
					flag = false;
				}
			}
			
			if(flag){
				VisitItem item = new VisitItem();
				item.setSerchWord(proteinInfo);
				item.setSerchDb("蛋白库");
				item.setSerchUrl("protein.action?m=frontProteinList&proteinInfo="+proteinInfo);
				vItemList.add(item);
			}
			
		}
		
		
		request.setAttribute("itemList", vItemList);
		
		return new ModelAndView("cds/protein_list","dataList",proteinList);
	}
	
	
	
	
	public ModelAndView frontIdProteinList(HttpServletRequest request,
			HttpServletResponse response, Protein command, BindException errors)
			throws ServletException, IOException {

		
		String proteinId = request.getParameter("proteinId");
		String proteinInfo = this.proteinmanager.findByProteinIds(proteinId);
		int pageSize = 10;
		int page = 1;
		request.setAttribute("INFO", proteinInfo);
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
		PageBean proteinList = this.proteinmanager.searchProteinById(proteinId,page,pageSize);
		return new ModelAndView("cds/protein_list","dataList",proteinList);
	}
	
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 高级检索
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontAdProteinList(HttpServletRequest request,
			HttpServletResponse response, Protein command, BindException errors)
			throws ServletException, IOException {

		
		String NcbiProteinRef = request.getParameter("NcbiProteinRef");
		String ProteinID = request.getParameter("ProteinID");
		String NCBISpeciesID = request.getParameter("NCBISpeciesID");
		String chromosomeRef = request.getParameter("chromosomeRef");
		
		String key_1 = request.getParameter("key_1");
		String key_2 = request.getParameter("key_2");
		String key_3 = request.getParameter("key_3");
		
		String[] key = new String[3];
		key[0] = key_1;
		key[1] = key_2;
		key[2] = key_3;
		
		int pageSize = 10;
		int page = 1;
		request.setAttribute("NcbiProteinRef", NcbiProteinRef);
		request.setAttribute("ProteinID", ProteinID);
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
		PageBean proteinList = this.proteinmanager.adSearchProtein(NCBISpeciesID,ProteinID,NcbiProteinRef,chromosomeRef,page,pageSize,key);
		
		if(proteinList.getList().size()!=0){
			Protein g = (Protein) proteinList.getList().get(0);
			request.setAttribute("INFO", g.getSpeciesName());
		}else{
			request.setAttribute("INFO", "");
		}
		
		
		HttpSession session = request.getSession();
		List<VisitItem> vItemList = (List<VisitItem>) session.getAttribute("VisitItemList");
		request.setAttribute("itemList", vItemList);
		
		return new ModelAndView("cds/ad_protein_list","dataList",proteinList);
	}
	
	
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 蛋白质详情
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontProteinDetail(HttpServletRequest request,
			HttpServletResponse response, Protein command, BindException errors)
			throws ServletException, IOException {

		String id = request.getParameter("proteinId");
		
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
		request.setAttribute("pageSize", pageSize);
		
		Protein protein = this.proteinmanager.findByProteinId(id);
		PageBean pageBean = this.proteinmanager.findDocByProteinId(id,page,pageSize);
		
		//拆分基因序列，每10个字母为一组    
		String seque = protein.getSequence();
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
		
		return new ModelAndView("cds/protein_detail","protein",protein);
	}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 蛋白质文献列表
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontProteinDocList(HttpServletRequest request,
			HttpServletResponse response, Protein command, BindException errors)
			throws ServletException, IOException {

		String id = request.getParameter("proteinId");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
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
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("page", page);
		Protein protein = this.proteinmanager.findByProteinId(id);
		PageBean pageBean = this.proteinmanager.findDocByProteinId(id,page,pageSize);
		request.setAttribute("pageBean", pageBean);
		
		HttpSession session = request.getSession();
		List<VisitItem> vItemList = (List<VisitItem>) session.getAttribute("VisitItemList");
		request.setAttribute("itemList", vItemList);
		
		return new ModelAndView("cds/protein_docList","protein",protein);
	}
	
	

	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 处理FAA格式文件。将文件数据转换成数据库数据。蛋白质数据项。
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView toPaseFAA(HttpServletRequest request,
			HttpServletResponse response, Protein command, BindException errors)
			throws ServletException, IOException {

		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String filepath = request.getParameter("faaPath");
		int faa = this.proteinmanager.parseFAA(filepath);
		int[] ary = new int[1];
		ary[0] = faa;
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
	 * @return 执行sql语句:用FAA格式文件的数据项更新cds表
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView toSQL(HttpServletRequest request,
			HttpServletResponse response, Protein command, BindException errors)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		int sql = this.proteinmanager.doSql();
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
	public ProteinController() throws ClassNotFoundException {
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
	 * @throws ServletException
	 *             , IOException
	 */
	public ModelAndView loadData(HttpServletRequest request,
			HttpServletResponse response, Protein command, BindException errors)
			throws ServletException, IOException {
		return new ModelAndView("docToDB/proteinList", "dataList",
				this.proteinmanager.getAll());
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
	 * @throws ServletException
	 *             , IOException
	 */
	public ModelAndView toAddPage(HttpServletRequest request,
			HttpServletResponse response, Protein command, BindException errors)
			throws ServletException, IOException {
		return new ModelAndView("docToDB/proteinForm");
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
	 * @throws ServletException
	 *             , IOException
	 */
	public ModelAndView toEditPage(HttpServletRequest request,
			HttpServletResponse response, Protein command, BindException errors)
			throws ServletException, IOException {
		String id = request.getParameter("objectId");
		command = this.proteinmanager.findById(id);
		return new ModelAndView("docToDB/proteinForm", "protein", command);
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
	 * @throws ServletException
	 *             , IOException
	 */
	public ModelAndView save(HttpServletRequest request,
			HttpServletResponse response, Protein command, BindException errors)
			throws ServletException, IOException {
		if (command != null
				&& !"".equals(command.getId())) {
			this.proteinmanager.editProtein(command);
		} else {
			this.proteinmanager.saveProtein(command);
		}
		return new ModelAndView("docToDB/proteinList", "dataList",
				this.proteinmanager.getAll());
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
	 * @throws ServletException
	 *             , IOException
	 */
	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response, Protein command, BindException errors)
			throws ServletException, IOException {
		String id = request.getParameter("objectId");
		try {
			this.proteinmanager.deleteProtein(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("docToDB/proteinList", "dataList",
				this.proteinmanager.getAll());
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
	 * @throws ServletException
	 *             , IOException
	 */
	public ModelAndView validateData(HttpServletRequest request,
			HttpServletResponse response, Protein command, BindException errors)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		Protein protein = this.proteinmanager.findById(id);
		// if(protein==null){
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
		Protein tempObject = (Protein) command;
	}

	// *******************************************************************************set
	// and get
	public ProteinManager getProteinmanager() {
		return proteinmanager;
	}

	public void setProteinmanager(ProteinManager proteinmanager) {
		this.proteinmanager = proteinmanager;
	}
}
