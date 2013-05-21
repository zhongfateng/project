// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ExportFilter.java

package org.extremecomponents.table.filter;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;

// Referenced classes of package org.extremecomponents.table.filter:
//			AbstractExportFilter, ExportResponseWrapper

public class ExportFilter extends AbstractExportFilter
{

	private boolean responseHeadersSetBeforeDoFilter;

	public ExportFilter()
	{
	}

	public void init(FilterConfig filterConfig)
		throws ServletException
	{
		String responseHeadersSetBeforeDoFilter = filterConfig.getInitParameter("responseHeadersSetBeforeDoFilter");
		if (StringUtils.isNotBlank(responseHeadersSetBeforeDoFilter))
			this.responseHeadersSetBeforeDoFilter = (new Boolean(responseHeadersSetBeforeDoFilter)).booleanValue();
	}

	public void destroy()
	{
	}

	protected void doFilterInternal(ServletRequest request, ServletResponse response, FilterChain chain, String exportFileName)
		throws IOException, ServletException
	{
		if (responseHeadersSetBeforeDoFilter)
			setResponseHeaders((HttpServletResponse)response, exportFileName);
		chain.doFilter(request, new ExportResponseWrapper((HttpServletResponse)response));
		if (!responseHeadersSetBeforeDoFilter)
			setResponseHeaders((HttpServletResponse)response, exportFileName);
	}
}
