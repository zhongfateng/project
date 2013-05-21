package com.nbw.docToDB.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
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
import com.nbw.common.util.PageBean;
import com.nbw.docToDB.domain.GPR;
import com.nbw.docToDB.domain.Gene;
import com.nbw.docToDB.service.GeneManager;
import com.nbw.indexMain.domain.VisitItem;

/**
 * 
 * GeneController
 * 
 * @author wangshaobin
 * 
 */
public class GeneController extends NbwController {

	private GeneManager genemanager;
	private GPR gpr;
	
	
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
	public ModelAndView frontGeneSearch(HttpServletRequest request,
			HttpServletResponse response, Gene command, BindException errors)
			throws ServletException, IOException {

		return new ModelAndView("gene/search_gene_db");
	}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 删除历史记录
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontGeneDel(HttpServletRequest request,
			HttpServletResponse response, Gene command, BindException errors)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		List<VisitItem> vItemList = (List<VisitItem>) session.getAttribute("VisitItemList");
		vItemList.clear();
		PrintWriter out = response.getWriter();
		JSONObject obj = JSONObject.fromObject("{}");
		out.println(obj.toString());
		out.close();
		return new ModelAndView("gene/search_gene_db");
	}
	
	
	/**
	 * 
	 * 
	 * 搜索结果页面
	 */
	public ModelAndView frontGeneSearchList(HttpServletRequest request,
			HttpServletResponse response, Gene command, BindException errors)
			throws ServletException, IOException {

		return new ModelAndView("gene/search_list");
	}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 基础数据库页面
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontBaseData(HttpServletRequest request,
			HttpServletResponse response, Gene command, BindException errors)
			throws ServletException, IOException {

		return new ModelAndView("gene/basic_data");
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return gene详情
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontGeneDetail(HttpServletRequest request,
			HttpServletResponse response, Gene command, BindException errors)
			throws ServletException, IOException {

		String id = request.getParameter("ncbiGeneRef");
		Gene gene = this.genemanager.findByGeneId(id);
		
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
		PageBean pageBean = this.genemanager.findDocByGeneId(id,page,pageSize);
		
		
		//拆分基因序列，每10个字母为一组    
		String seque = gene.getSequence();
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
		for(VisitItem vi:vItemList){
			System.out.println("d:"+vi.getSerchDb()+"  E:  "+vi.getSerchWord());
			
		}
		
		request.setAttribute("seqList", seqList);
		request.setAttribute("pageBean", pageBean);
		return new ModelAndView("gene/gene_detail","gene",gene);
	}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 基因文献列表
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontGeneDocList(HttpServletRequest request,
			HttpServletResponse response, Gene command, BindException errors)
			throws ServletException, IOException {

		String id = request.getParameter("ncbiGeneRef");
		Gene gene = this.genemanager.findByGeneId(id);
		
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
		PageBean pageBean = this.genemanager.findDocByGeneId(id,page,pageSize);
		request.setAttribute("pageBean", pageBean);
		
		HttpSession session = request.getSession();
		List<VisitItem> vItemList = (List<VisitItem>) session.getAttribute("VisitItemList");
		request.setAttribute("itemList", vItemList);
		
		return new ModelAndView("gene/gene_docList","gene",gene);
	}
	
	
	
	
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 基因列表
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontGeneList(HttpServletRequest request,
			HttpServletResponse response, Gene command, BindException errors)
			throws ServletException, IOException {
		
		String geneInfo = request.getParameter("geneInfo");
		int pageSize = 10;
		int page = 1;
		request.setAttribute("INFO", geneInfo);
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
		PageBean geneList = this.genemanager.searchGene(geneInfo,page,pageSize);
		
		HttpSession session = request.getSession();
		List<VisitItem> vItemList = (List<VisitItem>) session.getAttribute("VisitItemList");
		if(vItemList==null){
			 vItemList = new ArrayList<VisitItem>();
			 VisitItem vItem = new VisitItem();
			 vItem.setSerchWord(geneInfo);
			 vItem.setSerchDb("基因库");
			 vItem.setSerchUrl("gene.action?m=frontGeneList&geneInfo="+geneInfo+"&page="+page+"&pageSize="+pageSize);
			 vItemList.add(vItem);
			 session.setAttribute("VisitItemList", vItemList);
		}else{
			
			boolean flag = true;
			
			for(int i=0;i<vItemList.size();i++){
				VisitItem item = vItemList.get(i);
				if(item.getSerchWord().equals(geneInfo) && item.getSerchDb().equals("基因库")){
					flag = false;
				}
			}
			
			if(flag){
				VisitItem vItem = new VisitItem();
				 vItem.setSerchWord(geneInfo);
				 vItem.setSerchDb("基因库");
				 vItem.setSerchUrl("gene.action?m=frontGeneList&geneInfo="+geneInfo+"&page="+page+"&pageSize="+pageSize);
				 vItemList.add(vItem);
			}
			
		}
		
		
		 
		
		request.setAttribute("itemList", vItemList);
		return new ModelAndView("gene/gene_list", "dataList",geneList);
	}
	
	
	
	
	public ModelAndView frontIdGeneList(HttpServletRequest request,
			HttpServletResponse response, Gene command, BindException errors)
			throws ServletException, IOException {
		
		String geneId = request.getParameter("geneId");
		String geneInfo = this.genemanager.findByGeneIds(geneId);
		int pageSize = 10;
		int page = 1;
		request.setAttribute("INFO", geneInfo);
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
		PageBean geneList = this.genemanager.searchGeneById(geneId,page,pageSize);
		return new ModelAndView("gene/gene_list", "dataList",geneList);
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
	public ModelAndView frontAdGeneList(HttpServletRequest request,
			HttpServletResponse response, Gene command, BindException errors)
			throws ServletException, IOException {
		
		String geneName = request.getParameter("geneName");
		String genID = request.getParameter("genID");
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
		request.setAttribute("geneName", geneName);
		request.setAttribute("genID", genID);
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
		//PageBean geneList = this.genemanager.adSearchGene(speciesName,genID,geneName,chromosomeRef,page,pageSize);
		PageBean geneList = this.genemanager.adSearchGene(NCBISpeciesID,genID,geneName,chromosomeRef,page,pageSize,key);
		if(geneList.getList().size()!=0){
			Gene g = (Gene) geneList.getList().get(0);
			request.setAttribute("INFO", g.getSpeciesName());
		}else{
			request.setAttribute("INFO", "");
		}
		
		HttpSession session = request.getSession();
		List<VisitItem> vItemList = (List<VisitItem>) session.getAttribute("VisitItemList");
		request.setAttribute("itemList", vItemList);
		
		return new ModelAndView("gene/ad_gene_list", "dataList",geneList);
	}
	

	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 进入到GFF处理页面。
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView toGFFPage(HttpServletRequest request,
			HttpServletResponse response, Gene command, BindException errors)
			throws ServletException, IOException {

		return new ModelAndView("docToDB/gffPage");
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 进入FFN处理页面
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView toFFNPage(HttpServletRequest request,
			HttpServletResponse response, Gene command, BindException errors)
			throws ServletException, IOException {

		return new ModelAndView("docToDB/geneForm");
	}
	

	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 对GFF文件进行处理。将文件数据转化成数据库数据。包括基因、蛋白质、RNA数据项。
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView toPaseGFF(HttpServletRequest request,
			HttpServletResponse response, Gene command, BindException errors)
			throws ServletException, IOException {
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String filepath = request.getParameter("gffPath");
		System.out.println("路径："+filepath);
		
		String speName = request.getParameter("speName");
		gpr = this.genemanager.paseGFF(filepath,speName);
		JSONObject obj = JSONObject.fromObject(gpr);
		out.println(obj.toString());
		out.close();
		// 提示Gene、cds、rna分别多少条入库
		return new ModelAndView("docToDB/success");
	}

	/**
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 处理FNN格式文件，转换成Gene表中的数据项。基因数据。
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView toPaseFFN(HttpServletRequest request,
			HttpServletResponse response, Gene command, BindException errors)
			throws ServletException, IOException {
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String ffnPath = request.getParameter("ffnPath");
		System.out.println("路径："+ffnPath);
		int ffn = this.genemanager.paseFFN(ffnPath);
		if(ffn==0){
			request.setAttribute("errInfo", "文件不存在");
		}
		int[] ary = new int[1];
		ary[0] = ffn;
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
	 * @return 执行SQL语句完善基因表、蛋白质表中的种的id字段和基因表中的蛋白质基因字段。
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView toSQL(HttpServletRequest request,
			HttpServletResponse response, Gene command, BindException errors)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		//String speName = request.getParameter("speName");
		int[] ary = this.genemanager.doSql();
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
	 * @return 执行sql语句：用FFN上的数据更新jiyin表中的数据。
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public ModelAndView toFFNSQL(HttpServletRequest request,
			HttpServletResponse response, Gene command, BindException errors)
			throws ServletException, IOException, SQLException {

		PrintWriter out = response.getWriter();
		int ffnSql = this.genemanager.doFFNSql();
		int[] ary = new int[1];
		ary[0] = ffnSql;
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
	public GeneController() throws ClassNotFoundException {
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
			HttpServletResponse response, Gene command, BindException errors)
			throws ServletException, IOException {
		return new ModelAndView("docToDB/geneList", "dataList",
				this.genemanager.getAll());
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
			HttpServletResponse response, Gene command, BindException errors)
			throws ServletException, IOException {
		return new ModelAndView("docToDB/geneForm");
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
			HttpServletResponse response, Gene command, BindException errors)
			throws ServletException, IOException {
		String id = request.getParameter("objectId");
		command = this.genemanager.findById(id);
		return new ModelAndView("docToDB/geneForm", "gene", command);
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
			HttpServletResponse response, Gene command, BindException errors)
			throws ServletException, IOException {
		if (command != null && !"".equals(command.getId())) {
			this.genemanager.editGene(command);
		} else {
			this.genemanager.saveGene(command);
		}
		return new ModelAndView("docToDB/geneList", "dataList",
				this.genemanager.getAll());
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
			HttpServletResponse response, Gene command, BindException errors)
			throws ServletException, IOException {
		String id = request.getParameter("objectId");
		try {
			this.genemanager.deleteGene(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("docToDB/geneList", "dataList",
				this.genemanager.getAll());
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
			HttpServletResponse response, Gene command, BindException errors)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		Gene gene = this.genemanager.findById(id);
		// if(gene==null){
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
		Gene tempObject = (Gene) command;
	}

	// *******************************************************************************set
	// and get
	public GeneManager getGenemanager() {
		return genemanager;
	}

	public void setGenemanager(GeneManager genemanager) {
		this.genemanager = genemanager;
	}

	public GPR getGpr() {
		return gpr;
	}

	public void setGpr(GPR gpr) {
		this.gpr = gpr;
	}

}
