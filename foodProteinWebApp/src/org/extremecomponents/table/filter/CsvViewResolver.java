// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CsvViewResolver.java

package org.extremecomponents.table.filter;

import javax.servlet.*;
import org.extremecomponents.table.core.Preferences;

// Referenced classes of package org.extremecomponents.table.filter:
//			ViewResolver

public class CsvViewResolver
	implements ViewResolver
{

	public CsvViewResolver()
	{
	}

	public void resolveView(ServletRequest request, ServletResponse response, Preferences preferences, Object viewData)
		throws Exception
	{
		byte contents[] = ((String)viewData).getBytes();
		response.setContentLength(contents.length);
		response.getOutputStream().write(contents);
	}
}
