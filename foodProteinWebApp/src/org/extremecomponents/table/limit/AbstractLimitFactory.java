// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   AbstractLimitFactory.java

package org.extremecomponents.table.limit;

import java.util.*;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.core.Registry;

// Referenced classes of package org.extremecomponents.table.limit:
//			LimitFactory, Sort, FilterSet, Filter

public abstract class AbstractLimitFactory
	implements LimitFactory
{

	protected String tableId;
	protected String prefixWithTableId;
	protected boolean isExported;
	protected Registry registry;
	protected Context context;

	public AbstractLimitFactory()
	{
	}

	public boolean isExported()
	{
		return isExported;
	}

	boolean getExported()
	{
		String exportTableId = context.getParameter("ec_eti");
		if (StringUtils.isBlank(exportTableId))
			return false;
		return exportTableId.equals(tableId);
	}

	public int getCurrentRowsDisplayed(int totalRows, int rowsDisplayed)
	{
		if (isExported || !showPagination())
			return totalRows;
		String currentRowsDisplayed = registry.getParameter(prefixWithTableId + "crd");
		if (StringUtils.isNotBlank(currentRowsDisplayed))
			return Integer.parseInt(currentRowsDisplayed);
		else
			return rowsDisplayed;
	}

	public int getPage()
	{
		if (isExported)
			return 1;
		String page = registry.getParameter(prefixWithTableId + "p");
		if (!StringUtils.isEmpty(page))
			return Integer.parseInt(page);
		else
			return 1;
	}

	public Sort getSort()
	{
		Map sortedParameters = getSortedOrFilteredParameters("s_");
		if (sortedParameters == null)
			return new Sort();
		for (Iterator iter = sortedParameters.keySet().iterator(); iter.hasNext();)
		{
			String propertyOrAlias = (String)iter.next();
			String value = (String)sortedParameters.get(propertyOrAlias);
			if (value.equals("default"))
			{
				return new Sort();
			} else
			{
				String property = getProperty(propertyOrAlias);
				return new Sort(propertyOrAlias, property, value);
			}
		}

		return new Sort();
	}

	public FilterSet getFilterSet()
	{
		Map filteredParameters = getSortedOrFilteredParameters("f_");
		FilterSet filterSet = getFilterSet(filteredParameters);
		if (filterSet.isCleared())
		{
			removeFilterParameters();
			filterSet = new FilterSet(filterSet.getAction(), new Filter[0]);
		}
		return filterSet;
	}

	void removeFilterParameters()
	{
		Set set = registry.getParameterMap().keySet();
		for (Iterator iter = set.iterator(); iter.hasNext();)
		{
			String name = (String)iter.next();
			if (name.startsWith(prefixWithTableId + "f_"))
				iter.remove();
		}

	}

	FilterSet getFilterSet(Map filteredParameters)
	{
		if (filteredParameters == null)
			return new FilterSet();
		String action = (String)filteredParameters.get("a");
		List filters = new ArrayList();
		for (Iterator iter = filteredParameters.keySet().iterator(); iter.hasNext();)
		{
			String propertyOrAlias = (String)iter.next();
			String value = (String)filteredParameters.get(propertyOrAlias);
			if (!StringUtils.isBlank(value) && !propertyOrAlias.equals("a"))
			{
				String property = getProperty(propertyOrAlias);
				filters.add(new Filter(propertyOrAlias, property, value));
			}
		}

		return new FilterSet(action, (Filter[])filters.toArray(new Filter[filters.size()]));
	}

	public Map getSortedOrFilteredParameters(String parameter)
	{
		Map subset = new HashMap();
		String find = prefixWithTableId + parameter;
		Set set = registry.getParameterMap().keySet();
		for (Iterator iter = set.iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			if (key.startsWith(find))
			{
				String value = registry.getParameter(key);
				if (StringUtils.isNotBlank(value))
				{
					String propertyOrAlias = StringUtils.substringAfter(key, find);
					subset.put(propertyOrAlias, value);
				}
			}
		}

		return subset;
	}

	private String getProperty(String propertyOrAlias)
	{
		String property = registry.getParameter(prefixWithTableId + "a_" + propertyOrAlias);
		if (StringUtils.isNotBlank(property))
			return property;
		else
			return propertyOrAlias;
	}

	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.append("tableId", tableId);
		return builder.toString();
	}

	protected abstract boolean showPagination();
}
