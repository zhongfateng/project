package com.nbw.jz.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.nbw.common.Constants;
import com.nbw.common.NbwController;
import com.nbw.common.util.CommonUtils;
import com.nbw.docToDB.service.GeneManager;
import com.nbw.docToDB.service.ProteinManager;
import com.nbw.docToDB.service.RnaManager;
import com.nbw.jz.domain.FmptStrain;
import com.nbw.jz.service.FmptStrainManager;
import com.nbw.lucene.service.DoctxtManager;
import com.nbw.tupu.service.FmptTpManager;


/**
 * 
 * FmptBioclassController
 * 
 * @张帆 
 * 
 */
public class FmptStrainController extends NbwController {


    private FmptStrainManager fmptstrainmanager;
    private GeneManager genemanager;
    private ProteinManager proteinmanager;
    private RnaManager rnamanager;
    private DoctxtManager doctxtmanager;
    private FmptTpManager fmpttpmanager;
    /**
	 * constructor
	 *  
	 * @throws ClassNotFoundException
	 */
	public FmptStrainController() throws ClassNotFoundException {
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
			HttpServletResponse response, FmptStrain command, BindException errors)
			throws ServletException, IOException {
		return new ModelAndView("jz/fmptstrainList","dataList",this.fmptstrainmanager.getAll());
	}
	/**
	 * 菌种页面
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 *  
	 * @throws ServletException, IOException
	 */
	public ModelAndView frontToIndex(HttpServletRequest request,
			HttpServletResponse response, FmptStrain command, BindException errors)
			throws ServletException, IOException {
		return new ModelAndView("jz/fmptjzTree");
	}
	/**
	 * 加载左侧菌种树
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontLoadTreeXML(HttpServletRequest request,
			HttpServletResponse response, FmptStrain command,
			BindException errors) throws ServletException, IOException {
		response = CommonUtils.setCommonXmlResponse(response);
		String id = null == request.getParameter("id") ? Constants.DEFAULT_ROOTWZ
				: request.getParameter("id");
		try {
			response.getWriter().write(fmptstrainmanager.getTreeXml(id));
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 加载右边
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException
	 */
	public ModelAndView frontLoadRight(HttpServletRequest request,
			HttpServletResponse response, FmptStrain command,
			BindException errors) throws ServletException, IOException, ParseException {
		String id = null == request.getParameter("id") ? Constants.DEFAULT_SPECIES
				: request.getParameter("id");
		Calendar c = new GregorianCalendar();
		Date d = c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String oldDate = fmptstrainmanager.findDateByID(Integer.valueOf(id));
		if(!"".equals(oldDate)&&oldDate!=""&&oldDate!=null){
		if((sdf.parse(sdf.format(d)).getTime()-sdf.parse(oldDate).getTime())/(24 * 60 * 60 * 1000)>3){//一个月更新下数据表中的数量(调用存储过程)
			int jy = genemanager.findBySpeciesName(Integer.valueOf(id));
			int dbz = proteinmanager.findBySpeciesName(Integer.valueOf(id));
			int rna = rnamanager.findBySpeciesName(Integer.valueOf(id));
			int wx = doctxtmanager.getCountBySpeciesId(Integer.valueOf(id));
			int tp = fmpttpmanager.findBySpid(Integer.valueOf(id));
			fmptstrainmanager.updateDataByID(Integer.valueOf(id), jy, dbz, rna, wx, tp,sdf.format(d));
		//	fmptstrainmanager.updateDateByID(Integer.valueOf(Constants.DEFAULT_ROOTWZ), sdf.format(d));
		}
		}else{
			int jy = genemanager.findBySpeciesName(Integer.valueOf(id));
			int dbz = proteinmanager.findBySpeciesName(Integer.valueOf(id));
			int rna = rnamanager.findBySpeciesName(Integer.valueOf(id));
			int wx = doctxtmanager.getCountBySpeciesId(Integer.valueOf(id));
			int tp = fmpttpmanager.findBySpid(Integer.valueOf(id));
			fmptstrainmanager.updateDataByID(Integer.valueOf(id), jy, dbz, rna, wx, tp,sdf.format(d));
		}
		FmptStrain fs = fmptstrainmanager.findByNid(Integer.valueOf(id));
		request.setAttribute("fs", fs);
		return new ModelAndView("jz/fmptstrainRight");
	}
	/**
	 * 加载饼状图
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontLoadChartsXML(HttpServletRequest request,
			HttpServletResponse response, FmptStrain command,
			BindException errors) throws ServletException, IOException {
		String id =  request.getParameter("id");
		String xml = fmptstrainmanager.loadChartsXml(Integer.valueOf(id));
		response.setCharacterEncoding("GBK");
		response.getWriter().write(xml);
		return null;
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
			HttpServletResponse response, FmptStrain command, BindException errors)
			throws ServletException, IOException {
		return new ModelAndView("jz/fmptstrainForm");
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
			HttpServletResponse response, FmptStrain command, BindException errors)
			throws ServletException, IOException {
		String id = request.getParameter("objectId");
		command = this.fmptstrainmanager.findById(id);
		return new ModelAndView("jz/fmptstrainForm","fmptstrain",command);
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
			HttpServletResponse response, FmptStrain command, BindException errors)
			throws ServletException, IOException {
		if (command!=null&&command.getId()!=null&&!"".equals(command.getId())){
			this.fmptstrainmanager.editFmptStrain(command);
		} else {
			this.fmptstrainmanager.saveFmptStrain(command);
		}
		return new ModelAndView("jz/fmptstrainList","dataList",this.fmptstrainmanager.getAll());
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
			HttpServletResponse response, FmptStrain command, BindException errors)
			throws ServletException, IOException {
		String id = request.getParameter("objectId");		
		try {
			this.fmptstrainmanager.deleteFmptStrain(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("jz/fmptstrainList","dataList",this.fmptstrainmanager.getAll());
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
			HttpServletResponse response, FmptStrain command, BindException errors)
			throws ServletException, IOException {
	    response.setContentType("text/html; charset=utf-8");    
	    response.setCharacterEncoding("utf-8"); 
	    PrintWriter out = response.getWriter (); 
		String id = request.getParameter("id");
		FmptStrain fmptstrain = this.fmptstrainmanager.findById(id);
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
		FmptStrain tempObject = (FmptStrain)command;
	}
	 
	//*******************************************************************************set and get	
	public FmptStrainManager getFmptstrainmanager() {
		return fmptstrainmanager;
	}

	public void setFmptstrainmanager(FmptStrainManager fmptstrainmanager) {
		this.fmptstrainmanager = fmptstrainmanager;
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

	public DoctxtManager getDoctxtmanager() {
		return doctxtmanager;
	}

	public void setDoctxtmanager(DoctxtManager doctxtmanager) {
		this.doctxtmanager = doctxtmanager;
	}

	public FmptTpManager getFmpttpmanager() {
		return fmpttpmanager;
	}

	public void setFmpttpmanager(FmptTpManager fmpttpmanager) {
		this.fmpttpmanager = fmpttpmanager;
	}
	
}

