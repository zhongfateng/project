// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Context.java

package org.extremecomponents.table.context;

import java.io.Writer;
import java.util.Locale;
import java.util.Map;

public interface Context
{

	public abstract Object getApplicationInitParameter(String s);

	public abstract Object getApplicationAttribute(String s);

	public abstract void setApplicationAttribute(String s, Object obj);

	public abstract void removeApplicationAttribute(String s);

	public abstract Object getPageAttribute(String s);

	public abstract void setPageAttribute(String s, Object obj);

	public abstract void removePageAttribute(String s);

	public abstract String getParameter(String s);

	public abstract Map getParameterMap();

	public abstract Object getRequestAttribute(String s);

	public abstract void setRequestAttribute(String s, Object obj);

	public abstract void removeRequestAttribute(String s);

	public abstract Object getSessionAttribute(String s);

	public abstract void setSessionAttribute(String s, Object obj);

	public abstract void removeSessionAttribute(String s);

	public abstract Writer getWriter();

	public abstract Locale getLocale();

	public abstract String getContextPath();

	public abstract Object getContextObject();
}
