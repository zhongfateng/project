// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   AbstractExportFilter.java

package org.extremecomponents.table.filter;

import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.context.HttpServletRequestContext;
import org.extremecomponents.table.core.*;
import org.extremecomponents.util.MimeUtils;

// Referenced classes of package org.extremecomponents.table.filter:
//			ExportFilterUtils, ViewResolver

public abstract class AbstractExportFilter
	implements Filter
{

	private int isIE;

	public AbstractExportFilter()
	{
		isIE = -1;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException
	{
		if (request.getCharacterEncoding() == null)
			request.setCharacterEncoding("UTF-8");
		String agent = ((HttpServletRequest)request).getHeader("user-agent");
		if (agent == null || agent.toLowerCase().indexOf("msie") < 0)
			isIE = 1;
		else
			isIE = 0;
		Context context = new HttpServletRequestContext((HttpServletRequest)request);
		boolean isExported = ExportFilterUtils.isExported(context);
		if (isExported)
		{
			String exportFileName = ExportFilterUtils.getExportFileName(context);
			doFilterInternal(request, response, chain, exportFileName);
			handleExport((HttpServletRequest)request, (HttpServletResponse)response, context);
		} else
		{
			chain.doFilter(request, response);
		}
	}

	protected void handleExport(HttpServletRequest request, HttpServletResponse response, Context context)
	{
		try
		{
			Object viewData = request.getAttribute("viewData");
			if (viewData != null)
			{
				Preferences preferences = new TableProperties();
				preferences.init(null, TableModelUtils.getPreferencesLocation(context));
				String viewResolver = (String)request.getAttribute("viewResolver");
				Class classDefinition = Class.forName(viewResolver);
				ViewResolver handleExportViewResolver = (ViewResolver)classDefinition.newInstance();
				handleExportViewResolver.resolveView(request, response, preferences, viewData);
				response.getOutputStream().flush();
				response.getOutputStream().close();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private String processFileName(String fileName, int flag)
	{
		String codedfilename = null;
		try
		{
			if (flag == 0)
			{
				String prefix = fileName.lastIndexOf(".") == -1 ? fileName : fileName.substring(0, fileName.lastIndexOf("."));
				String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf("."));
				String name = URLEncoder.encode(prefix, "UTF8");
				if (name.lastIndexOf("%0A") != -1)
					name = name.substring(0, name.length() - 3);
				int limit = 150 - extension.length();
				if (name.length() > limit)
				{
					name = URLEncoder.encode(prefix.substring(0, Math.min(prefix.length(), limit / 9)), "UTF-8");
					if (name.lastIndexOf("%0A") != -1)
						name = name.substring(0, name.length() - 3);
				}
				codedfilename = name + extension;
			} else
			if (flag == 1)
				codedfilename = "=?UTF-8?B?" + new String(Base64.encodeBase64(fileName.getBytes("UTF-8"))) + "?=";
			else
				codedfilename = fileName;
		}
		catch (Exception exception) { }
		return codedfilename;
	}

	protected void setResponseHeaders(HttpServletResponse response, String exportFileName)
	{
		String mimeType = MimeUtils.getFileMimeType(exportFileName);
		if (StringUtils.isNotBlank(mimeType))
			response.setContentType(mimeType);
		try
		{
			String fileName = processFileName(exportFileName, isIE);
			response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
		response.setHeader("Pragma", "public");
		response.setDateHeader("Expires", System.currentTimeMillis() + 1000L);
	}

	protected abstract void doFilterInternal(ServletRequest servletrequest, ServletResponse servletresponse, FilterChain filterchain, String s)
		throws IOException, ServletException;
}
