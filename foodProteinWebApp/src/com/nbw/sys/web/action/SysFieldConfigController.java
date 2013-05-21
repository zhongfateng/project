package com.nbw.sys.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.nbw.common.Constants;
import com.nbw.common.NbwController;
import com.nbw.common.util.CommonUtils;
import com.nbw.sys.domain.SysOrganizationsDetailCol;
import com.nbw.sys.domain.SysUsersDetailCol;
import com.nbw.sys.service.SysFieldConfigManager;

/**
 * 
 * SysFieldConfigController
 * 
 * @author 狄巨礼 May 17, 2010 09:31:00 AM
 * 
 */
public class SysFieldConfigController extends NbwController {

    private SysFieldConfigManager sysFieldConfigManager;
    
    /**
	 * constructor
	 *  
	 * @throws ClassNotFoundException
	 */
	public SysFieldConfigController() throws ClassNotFoundException {
		super();
	}

	/**
	 * 获得用户字段配置
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 *  
	 * @throws ServletException, IOException
	 */
	public ModelAndView loadUserConfig(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws ServletException, IOException {
		List<SysUsersDetailCol> userConfigList = sysFieldConfigManager.getSysUsersDetailCols();
		Map<String, List<?>> map = new HashMap<String, List<?>>();
		map.put("userConfigList", userConfigList);
		return new ModelAndView("sys/sysuserfieldconfigform",map);
	}
	
	/**
	 * 获得组织机构字段配置
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 *  
	 * @throws ServletException, IOException
	 */
	public ModelAndView loadOrgConfig(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws ServletException, IOException {
		List<SysOrganizationsDetailCol> organizationConfigList = sysFieldConfigManager.getSysOrganizationsDetailCols();
		Map<String, List<?>> map = new HashMap<String, List<?>>();
		map.put("organizationConfigList", organizationConfigList);
		return new ModelAndView("sys/sysorgfieldconfigform",map);
	}

	/**
	 * 保存用户字段配置
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 *  
	 * @throws ServletException, IOException
	 */
	public ModelAndView saveu(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws ServletException, IOException {
		response = CommonUtils.setCommonXmlResponse(response);
		
		PrintWriter out = response.getWriter();
		String xml = "";
		String [] uids = request.getParameterValues("uid");
		for(int i=0;i<uids.length;i++){
			String name = request.getParameter(uids[i]+"name");
			String enable = request.getParameter(uids[i]+"enable");
			String filled = request.getParameter(uids[i]+"filled");
			String orderNum = request.getParameter(uids[i]+"orderNum");
			String showform = request.getParameter(uids[i]+"showform");
			String colItems = request.getParameter(uids[i]+"colItems");
			String length = request.getParameter(uids[i]+"length");
			SysUsersDetailCol oo = sysFieldConfigManager.loadSysUsersDetailCol(uids[i]);
			oo.setColName(name);
			oo.setEnabled(SysUsersDetailCol.ENABLE_FALSE);
			if(enable!=null)
				oo.setEnabled(SysUsersDetailCol.ENABLE_TRUE);
			oo.setFilled(SysUsersDetailCol.FILLED_FALSE);
			if(filled!=null)
				oo.setFilled(SysUsersDetailCol.FILLED_TRUE);
			oo.setOrderNum(CommonUtils.StringToInteger(orderNum));
			oo.setShowForm(showform);
			oo.setLength(CommonUtils.StringToInteger(length));
			oo.setColItems(colItems);
			sysFieldConfigManager.updateSysUsersDetailCol(oo);
		}
		xml = Constants.DEFAULT_AJAX_SUCCESS ;
		out.write(xml);
		out.close();
		return null;
	}
	
	/**
	 * 保存组织机构字段配置
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 *  
	 * @throws ServletException, IOException
	 */
	public ModelAndView saveo(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws ServletException, IOException {
		response = CommonUtils.setCommonXmlResponse(response);
		
		PrintWriter out = response.getWriter();
		String xml = "";
		String [] oids = request.getParameterValues("oid");
		for(int i=0;i<oids.length;i++){
			String name = request.getParameter(oids[i]+"name");
			String enable = request.getParameter(oids[i]+"enable");
			String filled = request.getParameter(oids[i]+"filled");
			String orderNum = request.getParameter(oids[i]+"orderNum");
			String colItems = request.getParameter(oids[i]+"colItems");
			String showform = request.getParameter(oids[i]+"showform");
			String length = request.getParameter(oids[i]+"length");
			SysOrganizationsDetailCol oo = sysFieldConfigManager.loadSysOrganizationsDetailCol(oids[i]);
			oo.setColName(name);
			oo.setEnabled(SysOrganizationsDetailCol.ENABLE_FALSE);
			if(enable!=null)
				oo.setEnabled(SysOrganizationsDetailCol.ENABLE_TRUE);
			oo.setFilled(SysOrganizationsDetailCol.FILLED_FALSE);
			if(filled!=null)
				oo.setFilled(SysOrganizationsDetailCol.FILLED_TRUE);
			oo.setOrderNum(CommonUtils.StringToInteger(orderNum));
			oo.setShowForm(showform);
			oo.setLength(CommonUtils.StringToInteger(length));
			oo.setColItems(colItems);
			sysFieldConfigManager.updateSysOrganizationsDetailCol(oo);
		}
		xml = Constants.DEFAULT_AJAX_SUCCESS ;
		out.write(xml);
		out.close();
		return null;
	}

	public SysFieldConfigManager getSysFieldConfigManager() {
		return sysFieldConfigManager;
	}

	public void setSysFieldConfigManager(SysFieldConfigManager sysFieldConfigManager) {
		this.sysFieldConfigManager = sysFieldConfigManager;
	}

	@Override
	protected void convertStringToDate(HttpServletRequest request,
			Object command) {
		// TODO Auto-generated method stub
		
	}
	
	
}

