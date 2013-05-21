// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   RowHandler.java

package org.extremecomponents.table.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.bean.Row;
import org.extremecomponents.table.core.*;
import org.extremecomponents.table.interceptor.RowInterceptor;

public class RowHandler
{

	private static Log logger;
	private TableModel model;
	private Row row;

	public RowHandler(TableModel model)
	{
		this.model = model;
	}

	public TableModel getModel()
	{
		return model;
	}

	public Row getRow()
	{
		return row;
	}

	public void addRow(Row row)
	{
		this.row = row;
		addRowAttributes();
		row.defaults();
	}

	public void addRowAttributes()
	{
		String interceptor = TableModelUtils.getInterceptPreference(model, row.getInterceptor(), "row.interceptor.");
		row.setInterceptor(interceptor);
		TableCache.getInstance().getRowInterceptor(interceptor).addRowAttributes(model, row);
	}

	public void modifyRowAttributes()
	{
		TableCache.getInstance().getRowInterceptor(row.getInterceptor()).modifyRowAttributes(model, row);
	}

	public boolean isRowEven()
	{
		return row.getRowCount() != 0 && row.getRowCount() % 2 == 0;
	}

	public boolean isRowOdd()
	{
		return row.getRowCount() == 0 || row.getRowCount() % 2 != 0;
	}

	public int increaseRowCount()
	{
		if (row == null)
		{
			String msg = "There is no row defined. The row (Row or RowTag) is now required and needs to be put around the columns.";
			logger.error(msg);
			throw new IllegalStateException(msg);
		} else
		{
			row.setRowCount(row.getRowCount() + 1);
			return row.getRowCount();
		}
	}

	static 
	{
		logger = LogFactory.getLog(org.extremecomponents.table.handler.RowHandler.class);
	}
}
