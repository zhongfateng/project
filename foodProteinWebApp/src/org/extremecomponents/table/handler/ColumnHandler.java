// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ColumnHandler.java

package org.extremecomponents.table.handler;

import java.util.*;
import org.apache.commons.lang.StringUtils;
import org.extremecomponents.table.bean.*;
import org.extremecomponents.table.calc.CalcResult;
import org.extremecomponents.table.calc.CalcUtils;
import org.extremecomponents.table.cell.Cell;
import org.extremecomponents.table.core.*;
import org.extremecomponents.table.interceptor.ColumnInterceptor;
import org.extremecomponents.table.limit.FilterSet;
import org.extremecomponents.table.limit.Limit;

// Referenced classes of package org.extremecomponents.table.handler:
//			TableHandler, ExportHandler

public class ColumnHandler
{

	private TableModel model;
	private List columns;
	private Column firstColumn;
	private Column lastColumn;

	public ColumnHandler(TableModel model)
	{
		columns = new ArrayList();
		this.model = model;
	}

	public void addAutoGenerateColumn(Column column)
	{
		column.addAttribute("isAutoGenerateColumn", "true");
		addColumn(column);
	}

	public void addColumn(Column column)
	{
		column.defaults();
		addColumnAttributes(column);
		if (!isViewAllowed(column))
			return;
		if (firstColumn == null)
		{
			firstColumn = column;
			column.setFirstColumn(true);
		}
		if (lastColumn != null)
			lastColumn.setLastColumn(false);
		lastColumn = column;
		column.setLastColumn(true);
		columns.add(column);
	}

	public void addColumnAttributes(Column column)
	{
		String interceptor = TableModelUtils.getInterceptPreference(model, column.getInterceptor(), "column.interceptor.");
		column.setInterceptor(interceptor);
		TableCache.getInstance().getColumnInterceptor(interceptor).addColumnAttributes(model, column);
	}

	public void modifyColumnAttributes(Column column)
	{
		TableCache.getInstance().getColumnInterceptor(column.getInterceptor()).modifyColumnAttributes(model, column);
	}

	public int columnCount()
	{
		return columns.size();
	}

	public List getColumns()
	{
		return columns;
	}

	public Column getColumnByAlias(String alias)
	{
		for (Iterator iter = columns.iterator(); iter.hasNext();)
		{
			Column column = (Column)iter.next();
			String columnAlias = column.getAlias();
			if (columnAlias != null && columnAlias.equals(alias))
				return column;
		}

		return null;
	}

	public boolean hasMetatData()
	{
		return columnCount() > 0;
	}

	public List getFilterColumns()
	{
		boolean cleared = model.getLimit().isCleared();
		Column column;
		Cell cell;
		for (Iterator iter = columns.iterator(); iter.hasNext(); column.setCellDisplay(cell.getHtmlDisplay(model, column)))
		{
			column = (Column)iter.next();
			String value = model.getLimit().getFilterSet().getFilterValue(column.getAlias());
			if (cleared || StringUtils.isEmpty(value))
				value = "";
			cell = TableModelUtils.getFilterCell(column, value);
		}

		return columns;
	}

	public List getHeaderColumns()
	{
		for (Iterator iter = columns.iterator(); iter.hasNext();)
		{
			Column column = (Column)iter.next();
			Cell cell = TableModelUtils.getHeaderCell(column, column.getTitle());
			boolean isExported = model.getLimit().isExported();
			if (!isExported)
				column.setCellDisplay(cell.getHtmlDisplay(model, column));
			else
				column.setCellDisplay(cell.getExportDisplay(model, column));
		}

		return columns;
	}

	private boolean isViewAllowed(Column column)
	{
		String view = model.getTableHandler().getTable().getView();
		boolean isExported = model.getLimit().isExported();
		if (isExported)
			view = model.getExportHandler().getCurrentExport().getView();
		boolean allowView = allowView(column, view);
		boolean denyView = denyView(column, view);
		return allowView & (!denyView);
	}

	private boolean allowView(Column column, String view)
	{
		String viewsAllowed[] = column.getViewsAllowed();
		if (viewsAllowed == null || viewsAllowed.length == 0)
			return true;
		for (int i = 0; i < viewsAllowed.length; i++)
			if (view.equals(viewsAllowed[i]))
				return true;

		return false;
	}

	private boolean denyView(Column column, String view)
	{
		String viewsDenied[] = column.getViewsDenied();
		if (viewsDenied == null || viewsDenied.length == 0)
			return false;
		for (int i = 0; i < viewsDenied.length; i++)
			if (view.equals(viewsDenied[i]))
				return true;

		return false;
	}

	public Column getFirstCalcColumn()
	{
		for (Iterator iter = columns.iterator(); iter.hasNext();)
		{
			Column column = (Column)iter.next();
			if (column.isCalculated())
				return column;
		}

		return null;
	}

	public CalcResult[] getCalcResults(Column column)
	{
		if (!column.isCalculated())
			return null;
		else
			return CalcUtils.getCalcResults(model, column);
	}
}
