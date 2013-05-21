package com.nbw.sys.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.nbw.common.Constants;
import com.nbw.common.NbwController;
import com.nbw.common.util.CommonUtils;
import com.nbw.sys.domain.SysOrganizations;
import com.nbw.sys.domain.SysOrganizationsDetail;
import com.nbw.sys.domain.SysOrganizationsDetailCol;
import com.nbw.sys.service.SysOrganizationsManager;

/**
 * 
 * SysOrganizationsController
 * 
 * @author 狄巨礼 May 17, 2010 09:31:00 AM
 * 
 */
public class SysOrganizationsController extends NbwController {

    private SysOrganizationsManager sysOrganizationsManager;

	/**
	 * 初始化页面
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 *  
	 * @throws ServletException, IOException
	 */
	public ModelAndView toIndex(HttpServletRequest request,
			HttpServletResponse response, SysOrganizations command, BindException errors)
			throws ServletException, IOException {
		return new ModelAndView("sys/sysorganizationsList");
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
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public ModelAndView save(HttpServletRequest request,
			HttpServletResponse response, SysOrganizations command, BindException errors)
			throws ServletException, IOException, IllegalAccessException, InvocationTargetException {
		response = CommonUtils.setCommonHtmlResponse(response);
		
		PrintWriter out = response.getWriter();
		//处理dhtmlgrid提交上来的操作，并返回dhtmlgrid所需要xml
		String xml = sysOrganizationsManager.process(command,request);
		
		out.write(xml);
		out.close();
		return null;
	}
	
	/**
	 * 保存组织机构附属信息
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * 
	 * @throws ServletException,
	 *             IOException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public ModelAndView saveAtt(HttpServletRequest request,
			HttpServletResponse response, SysOrganizations command, BindException errors)
			throws ServletException, IOException, IllegalAccessException,
			InvocationTargetException {
		response = CommonUtils.setCommonHtmlResponse(response);
		PrintWriter out = response.getWriter();
		boolean b = false;
		String id = command.getId();
		String xml = "";
		// 通过反射存储附属信息
		SysOrganizationsDetail sod = sysOrganizationsManager.getSysOrganizationsDetail(id);
		List<SysOrganizationsDetailCol> orgConfigs = Constants.orgFieldConfig;
		for (int i = 0; i < orgConfigs.size(); i++) {
			SysOrganizationsDetailCol orgConfig = orgConfigs.get(i);
			String colname = orgConfig.getDataSources();
			// 获取页面传过来的值
			Object objvalue = orgConfig.getValueByRequest(request);
			if (objvalue != null)
				BeanUtils.setProperty(sod, colname, objvalue);
		}
		sysOrganizationsManager.saveOrUpdateObject(sod);
		xml = Constants.DEFAULT_AJAX_SUCCESS + command.getId();

		out.write(xml);
		out.close();
		return null;
	}
	
	/**
	 * 删除数据
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * @throws IOException 
	 * @throws Exception 
	 */
	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response, SysOrganizations command,
			BindException errors) throws IOException  {
		response = CommonUtils.setCommonHtmlResponse(response);
		
		String ids = request.getParameter("ids");
		PrintWriter out = response.getWriter();

		if (sysOrganizationsManager.delete(ids)) {
			out.write(Constants.DEFAULT_AJAX_SUCCESS);
		} else {
			out.write(Constants.ERROR_DELETE);
		}
		out.close();
		return null;
	}
	
	/**
	 * 加载dhtmltree需要的xml，内容是根据组织机构parentId获取的子机构
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 *  
	 * @throws ServletException, IOException
	 */
	public ModelAndView loadTreeXML(HttpServletRequest request,
			HttpServletResponse response, SysOrganizations command,
			BindException errors) throws ServletException, IOException {
		response = CommonUtils.setCommonXmlResponse(response);

		String id = null == request.getParameter("id") ? Constants.DEFAULT_ROOT
				: request.getParameter("id");
		try {
			response.getWriter().write(sysOrganizationsManager.getTreeXml(id));
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 加载dhtmltree需要的xml，内容是根据组织机构parentId一次性加载parentId下的所有机构树xml，
	 * 如果是parentId==NULL则加载整颗树
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 *  
	 * @throws ServletException, IOException
	 */
	public ModelAndView loadAllTreeXML(HttpServletRequest request,
			HttpServletResponse response, SysOrganizations command,
			BindException errors) throws ServletException, IOException {
		response = CommonUtils.setCommonXmlResponse(response);
		String id = null == request.getParameter("id") ? Constants.DEFAULT_ROOT
				: request.getParameter("id");
		try {
			String xml = sysOrganizationsManager.getAllTreeXML(id);
			response.getWriter().write(xml);
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 加载dhtmlgrid需要的xml，内容是根据组织机构parentId获取的子机构
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 *  
	 * @throws ServletException, IOException
	 */
	public ModelAndView loadGridXML(HttpServletRequest request,
			HttpServletResponse response, SysOrganizations command, BindException errors)
			throws ServletException, IOException {
		response = CommonUtils.setCommonXmlResponse(response);
		
		String orgId = request.getParameter("orgId");
		
		//根据组织机构Id 获取其子机构封装的xml
		String xml = sysOrganizationsManager.getGridXml(orgId);
		response.getWriter().write(xml);
		return null;
	}
	
	/**
	 * 添加页面
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
	public ModelAndView toFormPage(HttpServletRequest request,
			HttpServletResponse response, SysOrganizations command,
			BindException errors) throws ServletException, IOException {
		return new ModelAndView("sys/sysorganizationsForm");
	}

	/**
	 * 附属信息添加编辑页面
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * 
	 * @throws ServletException ,
	 *             IOException
	 */
	public ModelAndView toAttFormPage(HttpServletRequest request,
			HttpServletResponse response, SysOrganizations command, BindException errors)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		if (id != null) {
			SysOrganizationsDetail sod = sysOrganizationsManager.getSysOrganizationsDetail(id);
			request.setAttribute("sod", sod);
		}
		return new ModelAndView("sys/sysorganizationsAttForm");
	}
	
	/**
	 * 获取添加页面数据
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
	public ModelAndView getFormData(HttpServletRequest request,
			HttpServletResponse response, SysOrganizations command,
			BindException errors) throws ServletException, IOException {
		response = CommonUtils.setCommonHtmlResponse(response);
		
		PrintWriter out = response.getWriter();

		String id = null == request.getParameter("id") ? Constants.DEFAULT_ROOT
				: request.getParameter("id");
		command = sysOrganizationsManager.findByID(id);
		if (null != command) {
			if (Constants.DEFAULT_ROOT.equals(id)) {
				command.setParentName("无");
			} else {
				command.setParentName(sysOrganizationsManager.findNameByID(command.getParentId()));
			}
			JSONObject jObj1= JSONObject.fromObject(command);
			out.print(jObj1);
		} else {
			out.print(Constants.DEFAULT_AJAX_FAILURE);
		}
		out.close();
		return null;
	}
	
    //*******************************************************************************set and get	
	public SysOrganizationsManager getSysOrganizationsManager() {
		return sysOrganizationsManager;
	}

	public void setSysOrganizationsManager(SysOrganizationsManager sysOrganizationsManager) {
		this.sysOrganizationsManager = sysOrganizationsManager;
	}
	
	@Override
	protected void convertStringToDate(HttpServletRequest request,
			Object command1) {
	}
}
