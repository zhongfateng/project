// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TableCache.java

package org.extremecomponents.table.core;

import java.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.bean.Table;
import org.extremecomponents.table.calc.Calc;
import org.extremecomponents.table.callback.*;
import org.extremecomponents.table.cell.Cell;
import org.extremecomponents.table.handler.TableHandler;
import org.extremecomponents.table.interceptor.*;
import org.extremecomponents.table.state.State;

// Referenced classes of package org.extremecomponents.table.core:
//			TableModel, AutoGenerateColumns

public class TableCache
{

	private static Log logger;
	public static TableCache tableCache = new TableCache();
	private Map cache;

	private TableCache()
	{
		cache = Collections.synchronizedMap(new HashMap());
	}

	public static TableCache getInstance()
	{
		return tableCache;
	}

	private Object getCachedObject(String className)
	{
		Object cachedObject = null;
		try
		{
			Class classForName = Class.forName(className);
			cachedObject = cache.get(classForName);
			if (cachedObject == null)
			{
				cachedObject = classForName.newInstance();
				cache.put(classForName, cachedObject);
			}
		}
		catch (Exception e)
		{
			String msg = "Could not create the object [" + className + "]. The class was not found or does not exist.";
			logger.error(msg, e);
			throw new IllegalStateException(msg);
		}
		return cachedObject;
	}

	public Cell getCell(String cell)
	{
		return (Cell)getCachedObject(cell);
	}

	public State getState(String state)
	{
		return (State)getCachedObject(state);
	}

	public RetrieveRowsCallback getRetrieveRowsCallback(TableModel model)
	{
		String retrieveRowsCallback = model.getTableHandler().getTable().getRetrieveRowsCallback();
		return (RetrieveRowsCallback)getCachedObject(retrieveRowsCallback);
	}

	public FilterRowsCallback getFilterRowsCallback(TableModel model)
	{
		String filterRowsCallback = model.getTableHandler().getTable().getFilterRowsCallback();
		return (FilterRowsCallback)getCachedObject(filterRowsCallback);
	}

	public SortRowsCallback getSortRowsCallback(TableModel model)
	{
		String sortRowsCallback = model.getTableHandler().getTable().getSortRowsCallback();
		return (SortRowsCallback)getCachedObject(sortRowsCallback);
	}

	public Calc getCalc(String calc)
	{
		return (Calc)getCachedObject(calc);
	}

	public TableInterceptor getTableInterceptor(String tableInterceptor)
	{
		return (TableInterceptor)getCachedObject(tableInterceptor);
	}

	public RowInterceptor getRowInterceptor(String rowInterceptor)
	{
		return (RowInterceptor)getCachedObject(rowInterceptor);
	}

	public ColumnInterceptor getColumnInterceptor(String columnInterceptor)
	{
		return (ColumnInterceptor)getCachedObject(columnInterceptor);
	}

	public ExportInterceptor getExportInterceptor(String exportInterceptor)
	{
		return (ExportInterceptor)getCachedObject(exportInterceptor);
	}

	public AutoGenerateColumns getAutoGenerateColumns(String autoGenerateColumns)
	{
		return (AutoGenerateColumns)getCachedObject(autoGenerateColumns);
	}

	static 
	{
		logger = LogFactory.getLog(org.extremecomponents.table.core.TableCache.class);
	}
}
