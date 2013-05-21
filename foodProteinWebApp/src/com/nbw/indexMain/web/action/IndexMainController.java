package com.nbw.indexMain.web.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.nbw.common.NbwController;
import com.nbw.common.util.PageBean;
import com.nbw.docToDB.service.GeneManager;
import com.nbw.docToDB.service.ProteinManager;
import com.nbw.docToDB.service.RnaManager;
import com.nbw.expert.domain.Expert;
import com.nbw.expert.service.ExpertManager;
import com.nbw.indexMain.domain.IndexMain;
import com.nbw.indexMain.domain.VisitItem;
import com.nbw.indexMain.service.IndexMainManager;
import com.nbw.lucene.service.DoctxtManager;
import com.nbw.techDy.service.TechDyManager;
import com.nbw.testTech.service.TestTechManager;
import com.nbw.threeStruct.service.StructManager;
import com.nbw.tupu.domain.FmptTp;
import com.nbw.tupu.service.FmptTpManager;
import com.nbw.usage.domain.Usage;
import com.nbw.usage.service.UsageManager;

public class IndexMainController extends NbwController{
	
	
	private IndexMainManager indexMainmanager;
	private GeneManager genemanager;
	private ProteinManager proteinmanager;
	private RnaManager rnamanager;
	private StructManager structmanager;
	private DoctxtManager doctxtmanager;
	private TestTechManager testTechmanager;
	private FmptTpManager fmpttpmanager;
	private ExpertManager expertManager;
	private TechDyManager techDymanager;
	private UsageManager usageManager; 
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 进入首页面
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontIndexMain(HttpServletRequest request,
			HttpServletResponse response, IndexMain command, BindException errors)
			throws ServletException, IOException {
		
		List<Expert> listExpert =this.expertManager.getFourExpert();
		request.setAttribute("expertList", listExpert);
		
		PageBean techDyList = this.techDymanager.findByTime(0,6);
		request.setAttribute("techDyList", techDyList);
		
		
		List<Usage> usList = this.usageManager.getLittleUsage();
		request.setAttribute("usList", usList);
		//String userIp = (String) request.getAttribute("UserIp");
//		String userIp = this.indexMainmanager.getIpAddr(request);
//		System.out.println("USERIP:"+userIp);
		
//		HttpSession session = request.getSession();
//		VisitUser vUser = new VisitUser();
//		session.putValue("VisitUser", vUser);
		
		return new ModelAndView("indexMain/main");
	}
	
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return 快速检索列表页面
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontQuickList(HttpServletRequest request,
			HttpServletResponse response, IndexMain command, BindException errors)
			throws ServletException, IOException {
		
		String speciesName = request.getParameter("speciesName");
		request.setAttribute("speciesName", speciesName);
		int page = 1;
		int pageSize = 10;
		
		PageBean geneList = this.genemanager.searchGene(speciesName,page,pageSize);
		int geneSum = geneList.getAllRow();//gene总条数
		request.setAttribute("geneSum", geneSum);
		
		PageBean proteinList = this.proteinmanager.searchProtein(speciesName,page,pageSize);
		int proteinSum = proteinList.getAllRow();//protein总条数
		request.setAttribute("proteinSum", proteinSum);
		
		PageBean rnaList = this.rnamanager.searchRna(speciesName,page,pageSize);
		int rnaSum = rnaList.getAllRow();//rna总条数
		request.setAttribute("rnaSum", rnaSum);
		
		int threeStructSum = this.structmanager.findBySpeciesNameSum(speciesName);//三级结构总条数
		request.setAttribute("threeStructSum", threeStructSum);
		
		int docSum = this.doctxtmanager.getTotalNumBySpecies(speciesName);
		request.setAttribute("docSum", docSum);//文献条数
		
		List<FmptTp> fmptList = this.fmpttpmanager.findByName(speciesName);
		int fmptSum = fmptList.size();
		request.setAttribute("fmptSum", fmptSum);//质谱条数
		
		
		HttpSession session = request.getSession();
		List<VisitItem> vItemList = (List<VisitItem>) session.getAttribute("VisitItemList");
		if(vItemList==null){
			 vItemList = new ArrayList<VisitItem>();
			 VisitItem vItem = new VisitItem();
			 vItem.setSerchWord(speciesName);
			 vItem.setSerchDb("快检库");
			 vItem.setSerchUrl("indexMain.action?m=frontQuickList&speciesName="+speciesName);
			 vItemList.add(vItem);
			 session.setAttribute("VisitItemList", vItemList);
		}else{
			
			boolean flag = true;
			
			for(int i=0;i<vItemList.size();i++){
				VisitItem item = vItemList.get(i);
				if(item.getSerchWord().equals(speciesName) && item.getSerchDb().equals("快检库")){
					flag = false;
				}
				
	         }
			
			if(flag){
				VisitItem vItem = new VisitItem();
				 vItem.setSerchWord(speciesName);
				 vItem.setSerchDb("快检库");
				 vItem.setSerchUrl("indexMain.action?m=frontQuickList&speciesName="+speciesName);
				 vItemList.add(vItem);
			}
			
		}
			
		
		return new ModelAndView("indexMain/quickList");
	}
	
	
	

	@Override
	protected void convertStringToDate(HttpServletRequest request,
			Object command) {
		// TODO Auto-generated method stub
		
	}



	public IndexMainManager getIndexMainmanager() {
		return indexMainmanager;
	}



	public void setIndexMainmanager(IndexMainManager indexMainmanager) {
		this.indexMainmanager = indexMainmanager;
	}



	public GeneManager getGenemanager() {
		return genemanager;
	}



	public void setGenemanager(GeneManager genemanager) {
		this.genemanager = genemanager;
	}



	public ProteinManager getProteinmanager() {
		return proteinmanager;
	}



	public void setProteinmanager(ProteinManager proteinmanager) {
		this.proteinmanager = proteinmanager;
	}



	public RnaManager getRnamanager() {
		return rnamanager;
	}



	public void setRnamanager(RnaManager rnamanager) {
		this.rnamanager = rnamanager;
	}



	public StructManager getStructmanager() {
		return structmanager;
	}



	public void setStructmanager(StructManager structmanager) {
		this.structmanager = structmanager;
	}



	public DoctxtManager getDoctxtmanager() {
		return doctxtmanager;
	}



	public void setDoctxtmanager(DoctxtManager doctxtmanager) {
		this.doctxtmanager = doctxtmanager;
	}



	public TestTechManager getTestTechmanager() {
		return testTechmanager;
	}



	public void setTestTechmanager(TestTechManager testTechmanager) {
		this.testTechmanager = testTechmanager;
	}



	public FmptTpManager getFmptTpmanager() {
		return fmpttpmanager;	
	}



	public void setFmptTpmanager(FmptTpManager fmptTpmanager) {
		this.fmpttpmanager = fmptTpmanager;
	}



	public FmptTpManager getFmpttpmanager() {
		return fmpttpmanager;
	}



	public void setFmpttpmanager(FmptTpManager fmpttpmanager) {
		this.fmpttpmanager = fmpttpmanager;
	}



	public ExpertManager getExpertManager() {
		return expertManager;
	}



	public void setExpertManager(ExpertManager expertManager) {
		this.expertManager = expertManager;
	}



	public TechDyManager getTechDymanager() {
		return techDymanager;
	}



	public void setTechDymanager(TechDyManager techDymanager) {
		this.techDymanager = techDymanager;
	}



	public UsageManager getUsageManager() {
		return usageManager;
	}



	public void setUsageManager(UsageManager usageManager) {
		this.usageManager = usageManager;
	}

	
}
