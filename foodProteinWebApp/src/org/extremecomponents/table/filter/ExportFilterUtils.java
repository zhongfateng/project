// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ExportFilterUtils.java

package org.extremecomponents.table.filter;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.context.Context;

public final class ExportFilterUtils
{

	private static Log logger;

	private ExportFilterUtils()
	{
	}

	public static boolean isExported(Context context)
	{
		return StringUtils.isNotBlank(getTableId(context));
	}

	public static String getExportFileName(Context context)
	{
		String tableId = getTableId(context);
		if (StringUtils.isNotBlank(tableId))
		{
			String exportFileNameStr = tableId + "_" + "efn";
			String exportFileName = context.getParameter(exportFileNameStr);
			if (logger.isDebugEnabled())
				logger.debug("eXtremeTable export file name [" + exportFileNameStr + "] is [" + exportFileName + "]");
			return exportFileName;
		} else
		{
			return null;
		}
	}

	public static String getTableId(Context context)
	{
		return context.getParameter("ec_eti");
	}

	static 
	{
		logger = LogFactory.getLog(org.extremecomponents.table.filter.ExportFilterUtils.class);
	}
}
