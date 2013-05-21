// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   FilterPredicate.java

package org.extremecomponents.table.callback;

import java.util.Iterator;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.handler.ColumnHandler;
import org.extremecomponents.table.limit.FilterSet;
import org.extremecomponents.table.limit.Limit;
import org.extremecomponents.util.ExtremeUtils;

public final class FilterPredicate
	implements Predicate
{

	private static Log logger;
	private TableModel model;

	public FilterPredicate(TableModel model)
	{
		this.model = model;
	}

	public boolean evaluate(Object bean)
	{
		boolean match = false;
		try
		{
			Iterator iter = model.getColumnHandler().getColumns().iterator();
			while (iter.hasNext()) 
			{
				Column column = (Column)iter.next();
				String alias = column.getAlias();
				String filterValue = model.getLimit().getFilterSet().getFilterValue(alias);
				if (StringUtils.isEmpty(filterValue))
					continue;
				String property = column.getProperty();
				Object value = PropertyUtils.getProperty(bean, property);
				if (value == null)
					continue;
				if (column.isDate())
				{
					java.util.Locale locale = model.getLocale();
					value = ExtremeUtils.formatDate(column.getParse(), column.getFormat(), value, locale);
				} else
				if (column.isCurrency())
				{
					java.util.Locale locale = model.getLocale();
					value = ExtremeUtils.formatNumber(column.getFormat(), value, locale);
				}
				if (!isSearchMatch(value.toString(), filterValue))
				{
					match = false;
					break;
				}
				match = true;
			}
		}
		catch (Exception e)
		{
			logger.error("FilterPredicate.evaluate() had problems", e);
		}
		return match;
	}

	private boolean isSearchMatch(String value, String search)
	{
		value = value.toLowerCase().trim();
		search = search.toLowerCase().trim();
		if (search.startsWith("*") && value.endsWith(StringUtils.replace(search, "*", "")))
			return true;
		if (search.endsWith("*") && value.startsWith(StringUtils.replace(search, "*", "")))
			return true;
		return StringUtils.contains(value, search);
	}

	static 
	{
		logger = LogFactory.getLog(org.extremecomponents.table.callback.FilterPredicate.class);
	}
}
