// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TableLimit.java

package org.extremecomponents.table.limit;

import org.apache.commons.lang.builder.ToStringBuilder;

// Referenced classes of package org.extremecomponents.table.limit:
//			Limit, LimitFactory, FilterSet, Sort

public final class TableLimit
	implements Limit
{

	protected LimitFactory limitFactory;
	protected FilterSet filterSet;
	protected Sort sort;
	protected boolean exported;
	protected int rowStart;
	protected int rowEnd;
	protected int currentRowsDisplayed;
	protected int page;
	protected int totalRows;

	public TableLimit(LimitFactory limitFactory)
	{
		this.limitFactory = limitFactory;
		filterSet = limitFactory.getFilterSet();
		sort = limitFactory.getSort();
		page = limitFactory.getPage();
		exported = limitFactory.isExported();
	}

	public FilterSet getFilterSet()
	{
		return filterSet;
	}

	public int getRowEnd()
	{
		return rowEnd;
	}

	public int getRowStart()
	{
		return rowStart;
	}

	public Sort getSort()
	{
		return sort;
	}

	public int getPage()
	{
		return page;
	}

	public int getCurrentRowsDisplayed()
	{
		return currentRowsDisplayed;
	}

	public int getTotalRows()
	{
		return totalRows;
	}

	public boolean isFiltered()
	{
		return filterSet.isFiltered();
	}

	public boolean isCleared()
	{
		return filterSet.isCleared();
	}

	public boolean isSorted()
	{
		return sort.isSorted();
	}

	public boolean isExported()
	{
		return exported;
	}

	public void setRowAttributes(int totalRows, int rowsDisplayed)
	{
		int currentRowsDisplayed = limitFactory.getCurrentRowsDisplayed(totalRows, rowsDisplayed);
		int page = getValidPage(this.page, totalRows, currentRowsDisplayed);
		int rowStart = (page - 1) * currentRowsDisplayed;
		int rowEnd = rowStart + currentRowsDisplayed;
		if (rowEnd > totalRows)
			rowEnd = totalRows;
		this.page = page;
		this.currentRowsDisplayed = currentRowsDisplayed;
		this.totalRows = totalRows;
		this.rowStart = rowStart;
		this.rowEnd = rowEnd;
	}

	private int getValidPage(int page, int totalRows, int currentRowsDisplayed)
	{
		if (!isValidPage(page, totalRows, currentRowsDisplayed))
			return getValidPage(--page, totalRows, currentRowsDisplayed);
		else
			return page;
	}

	private boolean isValidPage(int page, int totalRows, int currentRowsDisplayed)
	{
		if (page == 1)
			return true;
		int rowStart = (page - 1) * currentRowsDisplayed;
		int rowEnd = rowStart + currentRowsDisplayed;
		if (rowEnd > totalRows)
			rowEnd = totalRows;
		return rowEnd > rowStart;
	}

	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.append("rowStart", rowStart);
		builder.append("rowEnd", rowEnd);
		builder.append("currentRowsDisplayed", currentRowsDisplayed);
		builder.append("page", page);
		builder.append("totalRows", totalRows);
		builder.append("exported", exported);
		builder.append("sort", sort);
		builder.append("filterSet", filterSet);
		return builder.toString();
	}
}
