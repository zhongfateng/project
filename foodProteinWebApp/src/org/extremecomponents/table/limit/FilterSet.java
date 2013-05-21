// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   FilterSet.java

package org.extremecomponents.table.limit;

import org.apache.commons.lang.builder.ToStringBuilder;

// Referenced classes of package org.extremecomponents.table.limit:
//			Filter

public class FilterSet
{

	private String action;
	private Filter filters[];

	public FilterSet()
	{
	}

	public FilterSet(String action, Filter filters[])
	{
		this.action = action;
		this.filters = filters;
	}

	public boolean isFiltered()
	{
		return action != null && action.equals("fa") && filters != null && filters.length > 0;
	}

	public boolean isCleared()
	{
		return action != null && action.equals("ca");
	}

	public String getAction()
	{
		return action;
	}

	public Filter[] getFilters()
	{
		return filters;
	}

	public String getFilterValue(String alias)
	{
		for (int i = 0; i < filters.length; i++)
		{
			Filter filter = filters[i];
			if (filter.getAlias().equals(alias))
				return filter.getValue();
		}

		return "";
	}

	public Filter getFilter(String alias)
	{
		for (int i = 0; i < filters.length; i++)
		{
			Filter filter = filters[i];
			if (filter.getAlias().equals(alias))
				return filter;
		}

		return null;
	}

	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.append("action", action);
		if (filters != null)
		{
			for (int i = 0; i < filters.length; i++)
			{
				Filter filter = filters[i];
				builder.append(filter.toString());
			}

		}
		return builder.toString();
	}
}
