// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ExportHandler.java

package org.extremecomponents.table.handler;

import java.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.bean.Export;
import org.extremecomponents.table.bean.Table;
import org.extremecomponents.table.core.*;
import org.extremecomponents.table.interceptor.ExportInterceptor;

// Referenced classes of package org.extremecomponents.table.handler:
//			TableHandler

public class ExportHandler
{

	private static Log logger;
	private TableModel model;
	private List exports;

	public ExportHandler(TableModel model)
	{
		exports = new ArrayList();
		this.model = model;
	}

	public void addExport(Export export)
	{
		exports.add(export);
		addExportAttributes(export);
		export.defaults();
	}

	public void addExportAttributes(Export export)
	{
		String interceptor = TableModelUtils.getInterceptPreference(model, export.getInterceptor(), "export.interceptor.");
		export.setInterceptor(interceptor);
		TableCache.getInstance().getExportInterceptor(interceptor).addExportAttributes(model, export);
	}

	public Export getExport(String view)
	{
		for (Iterator iter = exports.iterator(); iter.hasNext();)
		{
			Export export = (Export)iter.next();
			if (export.getView().equals(view))
				return export;
		}

		return null;
	}

	public Export getCurrentExport()
	{
		String prefixWithTableId = model.getTableHandler().prefixWithTableId();
		String exportView = model.getRegistry().getParameter(prefixWithTableId + "ev");
		Export export = getExport(exportView);
		if (export == null)
		{
			String msg = "There is no export defined. This commonly happens if you do not declare the export (Export or ExportTag) before the row and columns.";
			logger.error(msg);
			throw new IllegalStateException(msg);
		} else
		{
			return export;
		}
	}

	public List getExports()
	{
		return exports;
	}

	/**
	 * @deprecated Method isExported is deprecated
	 */

	public boolean isExported()
	{
		return model.getExportHandler().isExported();
	}

	/**
	 * @deprecated Method showExports is deprecated
	 */

	public boolean showExports()
	{
		if (!model.getTableHandler().getTable().isShowExports())
			return false;
		return getExports().size() > 0;
	}

	static 
	{
		logger = LogFactory.getLog(org.extremecomponents.table.handler.ExportHandler.class);
	}
}
