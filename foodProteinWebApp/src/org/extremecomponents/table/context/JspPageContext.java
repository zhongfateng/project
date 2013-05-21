// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   JspPageContext.java

package org.extremecomponents.table.context;

import java.io.Writer;
import java.util.Locale;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

// Referenced classes of package org.extremecomponents.table.context:
//			Context

public final class JspPageContext
	implements Context
{

	private PageContext pageContext;

	public JspPageContext(PageContext pageContext)
	{
		this.pageContext = pageContext;
	}

	public Object getApplicationInitParameter(String name)
	{
		return pageContext.getServletContext().getInitParameter(name);
	}

	public Object getApplicationAttribute(String name)
	{
		return pageContext.getServletContext().getAttribute(name);
	}

	public void setApplicationAttribute(String name, Object value)
	{
		pageContext.getServletContext().setAttribute(name, value);
	}

	public void removeApplicationAttribute(String name)
	{
		pageContext.getServletContext().removeAttribute(name);
	}

	public Object getPageAttribute(String name)
	{
		return pageContext.getAttribute(name);
	}

	public void setPageAttribute(String name, Object value)
	{
		pageContext.setAttribute(name, value);
	}

	public void removePageAttribute(String name)
	{
		pageContext.removeAttribute(name);
	}

	public String getParameter(String name)
	{
		return pageContext.getRequest().getParameter(name);
	}

	public Map getParameterMap()
	{
		return pageContext.getRequest().getParameterMap();
	}

	public Object getRequestAttribute(String name)
	{
		return pageContext.getRequest().getAttribute(name);
	}

	public void setRequestAttribute(String name, Object value)
	{
		pageContext.getRequest().setAttribute(name, value);
	}

	public void removeRequestAttribute(String name)
	{
		pageContext.getRequest().removeAttribute(name);
	}

	public Object getSessionAttribute(String name)
	{
		return pageContext.getSession().getAttribute(name);
	}

	public void setSessionAttribute(String name, Object value)
	{
		pageContext.getSession().setAttribute(name, value);
	}

	public void removeSessionAttribute(String name)
	{
		pageContext.getSession().removeAttribute(name);
	}

	public Writer getWriter()
	{
		return pageContext.getOut();
	}

	public Locale getLocale()
	{
		return pageContext.getRequest().getLocale();
	}

	public String getContextPath()
	{
		ServletRequest request = pageContext.getRequest();
		if (request instanceof HttpServletRequest)
			return ((HttpServletRequest)request).getContextPath();
		else
			throw new UnsupportedOperationException("There is no context path associated with the request.");
	}

	public Object getContextObject()
	{
		return pageContext;
	}
}
