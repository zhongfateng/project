// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TableHandler.java

package org.extremecomponents.table.handler;

import org.extremecomponents.table.bean.Table;
import org.extremecomponents.table.core.*;
import org.extremecomponents.table.interceptor.TableInterceptor;

public class TableHandler
{

	protected TableModel model;
	private Table table;

	public TableHandler(TableModel model)
	{
		this.model = model;
	}

	public Table getTable()
	{
		return table;
	}

	public void addTable(Table table)
	{
		this.table = table;
		addTableAttributes();
		table.defaults();
	}

	public void addTableAttributes()
	{
		String interceptor = TableModelUtils.getInterceptPreference(model, table.getInterceptor(), "table.interceptor.");
		table.setInterceptor(interceptor);
		TableCache.getInstance().getTableInterceptor(interceptor).addTableAttributes(model, table);
	}

	public String prefixWithTableId()
	{
		return table.getTableId() + "_";
	}

	public Integer getTotalRows()
	{
		return (Integer)table.getAttribute("totalRows");
	}

	public void setTotalRows(Integer totalRows)
	{
		table.addAttribute("totalRows", totalRows);
	}
}
