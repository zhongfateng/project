// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ViewHandler.java

package org.extremecomponents.table.handler;

import org.apache.commons.lang.StringUtils;
import org.extremecomponents.table.bean.*;
import org.extremecomponents.table.cell.Cell;
import org.extremecomponents.table.core.*;
import org.extremecomponents.table.limit.Limit;
import org.extremecomponents.table.view.View;

// Referenced classes of package org.extremecomponents.table.handler:
//			ExportHandler, TableHandler

public class ViewHandler
{

	private TableModel model;
	private View view;

	public ViewHandler(TableModel model)
	{
		this.model = model;
	}

	public View getView()
	{
		return view;
	}

	public void setView()
		throws Exception
	{
		boolean isExported = model.getLimit().isExported();
		String currentView = null;
		if (isExported)
		{
			currentView = model.getExportHandler().getCurrentExport().getView();
			String preference = model.getPreferences().getPreference("export.view." + currentView);
			if (StringUtils.isNotBlank(preference))
				currentView = preference;
		} else
		{
			currentView = model.getTableHandler().getTable().getView();
			if ("top".equalsIgnoreCase(currentView))
				currentView = "compact";
			String preference = model.getPreferences().getPreference("table.view." + currentView);
			if (StringUtils.isNotBlank(preference))
				currentView = preference;
		}
		Class classDefinition = Class.forName(currentView);
		view = (View)classDefinition.newInstance();
		getView().beforeBody(model);
	}

	public void addColumnValueToView(Column column)
	{
		Cell cell = TableModelUtils.getCell(column);
		boolean isExported = model.getLimit().isExported();
		if (!isExported)
			column.setCellDisplay(cell.getHtmlDisplay(model, column));
		else
			column.setCellDisplay(cell.getExportDisplay(model, column));
		getView().body(model, column);
	}
}
