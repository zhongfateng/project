// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   PdfViewResolver.java

package org.extremecomponents.table.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;
import org.extremecomponents.table.core.Preferences;

// Referenced classes of package org.extremecomponents.table.filter:
//			ViewResolver

public class PdfViewResolver
	implements ViewResolver
{

	private Logger log;
	private static final String USERCONFIG_LOCATION = "exportPdf.userconfigLocation";

	public PdfViewResolver()
	{
		log = null;
	}

	public void resolveView(ServletRequest request, ServletResponse response, Preferences preferences, Object viewData)
		throws Exception
	{
//		java.io.InputStream is = new ByteArrayInputStream(((String)viewData).getBytes("UTF-8"));
//		ByteArrayOutputStream out = new ByteArrayOutputStream();
//		Driver driver = new Driver(new InputSource(is), out);
//		if (log == null)
//		{
//			log = new ConsoleLogger(2);
//			MessageHandler.setScreenLogger(log);
//		}
//		String userconfigLocation = preferences.getPreference("exportPdf.userconfigLocation");
//		if (userconfigLocation != null)
//		{
//			java.io.InputStream input = getClass().getResourceAsStream(userconfigLocation);
//			if (input != null)
//				new Options(input);
//		}
//		driver.setLogger(log);
//		driver.setRenderer(1);
//		driver.run();
//		byte contents[] = out.toByteArray();
//		response.setContentLength(contents.length);
//		response.getOutputStream().write(contents);
	}
}
