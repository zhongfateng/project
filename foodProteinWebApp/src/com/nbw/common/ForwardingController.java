package com.nbw.common;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.multiaction.MethodNameResolver;
import org.springframework.web.util.WebUtils;

public class ForwardingController extends AbstractController {
	
	private MethodNameResolver methodNameResolver; 
	public MethodNameResolver getMethodNameResolver() {
		return methodNameResolver;
	}

	public void setMethodNameResolver(MethodNameResolver methodNameResolver) {
		this.methodNameResolver = methodNameResolver;
	}
	/**
	 * 覆盖基类的方法 转发的执行方法
	 * 
	 * @param request
	 *            current HTTP request
	 * @param response
	 *            current HTTP response
	 * @return for forward
	 * @see javax.servlet.RequestDispatcher#forward
	 * @see javax.servlet.RequestDispatcher#include
	 * @see javax.servlet.ServletResponse#isCommitted
	 * @see org.springframework.web.util.WebUtils#isIncludeRequest
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String url = request.getParameter("url");
		
		if (url == null) {
			throw new ServletException("没有你所请求的路径，请详细检查～");
		}
		if(url.indexOf(".action") != -1){
			url = url.substring(0,url.indexOf("@"))+"?"+url.substring(url.indexOf("@")+1,url.length());
		}
		RequestDispatcher rd = request.getRequestDispatcher(url);
		// If already included, include again, else forward.
		if (useInclude(request, response)) {
			rd.include(request, response);
			if (logger.isDebugEnabled()) {
				logger.debug("Included servlet [" + url
						+ "] in ServletForwardingController '"
						+ "ForwardingController" + "'");
			}
		} else {
			rd.forward(request, response);
			if (logger.isDebugEnabled()) {
				logger.debug("Forwarded to servlet [" + url
						+ "] in ServletForwardingController '"
						+ "ForwardingController" + "'");
			}
		}
		return null;

	}

	/**
	 * Determine whether to use RequestDispatcher's <code>include</code> or
	 * <code>forward</code> method.
	 * <p>
	 * Performs a check whether an include URI attribute is found in the
	 * request, indicating an include request, and whether the response has
	 * already been committed. In both cases, an include will be performed, as a
	 * forward is not possible anymore.
	 * 
	 * @param request
	 *            current HTTP request
	 * @param response
	 *            current HTTP response
	 * @return <code>true</code> for include, <code>false</code> for forward
	 * @see javax.servlet.RequestDispatcher#forward
	 * @see javax.servlet.RequestDispatcher#include
	 * @see javax.servlet.ServletResponse#isCommitted
	 * @see org.springframework.web.util.WebUtils#isIncludeRequest
	 */
	protected boolean useInclude(HttpServletRequest request,
			HttpServletResponse response) {
		return (WebUtils.isIncludeRequest(request) || response.isCommitted());
	}
}
