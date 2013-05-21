package com.nbw.common.util;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.nbw.common.Constants;
import com.nbw.sys.domain.SysModuleActions;
import com.nbw.sys.domain.SysModules;

/**
 * ����8�ı�ǩ��
 * 
 * struts-menu
 * 
 * @author �
 * @see NbwButtonTag
 */
public class CheckTag extends BodyTagSupport {

	private String moduleUrl = "";
	private String action = "";
	
	public int doStartTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();
		Map sysModulesMap = (Map)request.getSession().getAttribute(Constants.SESSION_USER_MACTION);
		if(sysModulesMap == null){
			return SKIP_BODY;
		}else{
			SysModules m = (SysModules)sysModulesMap.get(moduleUrl);
			if(m==null){
				return SKIP_BODY;
			}else{
				SysModuleActions ma = (SysModuleActions)m.getActionsMap().get(action);
				if(ma==null){
					return SKIP_BODY;
				}
			}
		}
		return EVAL_BODY_INCLUDE;
	}

	public String getModuleUrl() {
		return moduleUrl;
	}
	public void setModuleUrl(String moduleUrl) {
		this.moduleUrl = moduleUrl;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}

}
