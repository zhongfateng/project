// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ServletRequestContext.java

package org.extremecomponents.table.context;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;
import javax.servlet.ServletRequest;

// Referenced classes of package org.extremecomponents.table.context:
//			Context

public class ServletRequestContext
	implements Context
{

	private ServletRequest request;

	public ServletRequestContext(ServletRequest request)
	{
		this.request = request;
	}

	public Object getApplicationInitParameter(String name)
	{
		throw new UnsupportedOperationException("There is no session associated with the request.");
	}

	public Object getApplicationAttribute(String name)
	{
		throw new UnsupportedOperationException("There is no session associated with the request.");
	}

	public void setApplicationAttribute(String name, Object value)
	{
		throw new UnsupportedOperationException("There is no session associated with the request.");
	}

	public void removeApplicationAttribute(String name)
	{
		throw new UnsupportedOperationException("There is no session associated with the request.");
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
		return request.getParameter(name);
	}

	public Map getParameterMap()
	{
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
		throw new UnsupportedOperationException("There is no session associated with the request.");
	}

	public void setSessionAttribute(String name, Object value)
	{
		throw new UnsupportedOperationException("There is no session associated with the request.");
	}

	public void removeSessionAttribute(String name)
	{
		throw new UnsupportedOperationException("There is no session associated with the request.");
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
		throw new UnsupportedOperationException("There is no context path associated with the request.");
	}

	public Object getContextObject()
	{
		return request;
	}
}
