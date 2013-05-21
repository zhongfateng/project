// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   HttpServletRequestContext.java

package org.extremecomponents.table.context;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.extremecomponents.table.core.TableModelUtils;

// Referenced classes of package org.extremecomponents.table.context:
//			Context

public class HttpServletRequestContext
	implements Context
{

	private HttpServletRequest request;
	private Map parameterMap;

	public HttpServletRequestContext(HttpServletRequest request)
	{
		this.request = request;
	}

	public HttpServletRequestContext(HttpServletRequest request, Map parameterMap)
	{
		this(request);
		this.parameterMap = parameterMap;
	}

	public Object getApplicationInitParameter(String name)
	{
		return request.getSession().getServletContext().getInitParameter(name);
	}

	public Object getApplicationAttribute(String name)
	{
		return request.getSession().getServletContext().getAttribute(name);
	}

	public void setApplicationAttribute(String name, Object value)
	{
		request.getSession().getServletContext().setAttribute(name, value);
	}

	public void removeApplicationAttribute(String name)
	{
		request.getSession().getServletContext().removeAttribute(name);
	}

	public Object getPageAttribute(String name)
	{
		return request.getAttribute(name);
	}

	public void setPageAttribute(String name, Object value)
	{
		request.setAttribute(name, value);
	}

	public void removePageAttribute(String name)
	{
		request.removeAttribute(name);
	}

	public String getParameter(String name)
	{
		if (parameterMap != null)
		{
			String values[] = TableModelUtils.getValueAsArray(parameterMap.get(name));
			if (values != null && values.length > 0)
				return values[0];
		}
		return request.getParameter(name);
	}

	public Map getParameterMap()
	{
		if (parameterMap != null)
			return parameterMap;
		else
			return request.getParameterMap();
	}

	public Object getRequestAttribute(String name)
	{
		return request.getAttribute(name);
	}

	public void setRequestAttribute(String name, Object value)
	{
		request.setAttribute(name, value);
	}

	public void removeRequestAttribute(String name)
	{
		request.removeAttribute(name);
	}

	public Object getSessionAttribute(String name)
	{
		return request.getSession().getAttribute(name);
	}

	public void setSessionAttribute(String name, Object value)
	{
		request.getSession().setAttribute(name, value);
	}

	public void removeSessionAttribute(String name)
	{
		request.getSession().removeAttribute(name);
	}

	public Writer getWriter()
	{
		return new StringWriter();
	}

	public Locale getLocale()
	{
		return request.getLocale();
	}

	public String getContextPath()
	{
		return request.getContextPath();
	}

	public Object getContextObject()
	{
		return request;
	}
}
