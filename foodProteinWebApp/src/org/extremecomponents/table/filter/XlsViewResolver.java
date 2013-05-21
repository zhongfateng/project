// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   XlsViewResolver.java

package org.extremecomponents.table.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.extremecomponents.table.core.Preferences;

// Referenced classes of package org.extremecomponents.table.filter:
//			ViewResolver

public class XlsViewResolver
	implements ViewResolver
{

	public XlsViewResolver()
	{
	}

	public void resolveView(ServletRequest request, ServletResponse response, Preferences preferences, Object viewData)
		throws Exception
	{
		HSSFWorkbook wb = (HSSFWorkbook)viewData;
		wb.write(response.getOutputStream());
	}
}
