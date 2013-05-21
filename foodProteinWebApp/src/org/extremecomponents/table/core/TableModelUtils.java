// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TableModelUtils.java

package org.extremecomponents.table.core;

import java.util.*;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.callback.*;
import org.extremecomponents.table.cell.Cell;
import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.limit.Limit;
import org.extremecomponents.table.limit.Sort;
import org.extremecomponents.util.ExtremeUtils;

// Referenced classes of package org.extremecomponents.table.core:
//			TableCache, TableModel, Preferences, Messages

public final class TableModelUtils
{

	private static Log logger;

	private TableModelUtils()
	{
	}

	static Collection retrieveRows(TableModel model)
		throws Exception
	{
		RetrieveRowsCallback retrieveRowsCallback = TableCache.getInstance().getRetrieveRowsCallback(model);
		return retrieveRowsCallback.retrieveRows(model);
	}

	static Collection filterRows(TableModel model, Collection rows)
		throws Exception
	{
		FilterRowsCallback filterRowsCallback = TableCache.getInstance().getFilterRowsCallback(model);
		return filterRowsCallback.filterRows(model, rows);
	}

	static Collection sortRows(TableModel model, Collection rows)
		throws Exception
	{
		SortRowsCallback sortRowsCallback = TableCache.getInstance().getSortRowsCallback(model);
		return sortRowsCallback.sortRows(model, rows);
	}

	static Collection getCurrentRows(TableModel model, Collection rows)
	{
		Limit limit = model.getLimit();
		int rowStart = limit.getRowStart();
		int rowEnd = limit.getRowEnd();
		if (rowStart >= rows.size())
		{
			if (logger.isDebugEnabled())
				logger.debug("The Limit row start is >= items.size(). Return the items available.");
			return rows;
		}
		if (rowEnd > rows.size())
		{
			if (logger.isWarnEnabled())
				logger.warn("The Limit row end is > items.size(). Return as many items as possible.");
			rowEnd = rows.size();
		}
		Collection results = new ArrayList();
		for (int i = rowStart; i < rowEnd; i++)
		{
			Object bean = ((List)rows).get(i);
			results.add(bean);
		}

		return results;
	}

	public static boolean isSorted(TableModel model, String alias)
	{
		Sort sort = model.getLimit().getSort();
		return sort.isSorted() && alias.equals(sort.getAlias());
	}

	public static boolean isResourceBundleProperty(String value)
	{
		return StringUtils.contains(value, ".");
	}

	public static Locale getLocale(Context context, Preferences preferences, String locale)
	{
		if (StringUtils.isEmpty(locale))
			locale = preferences.getPreference("table.locale");
		if (StringUtils.isBlank(locale))
			return context.getLocale();
		Locale result = null;
		String parts[] = StringUtils.split(locale, "_");
		String language = parts[0];
		if (parts.length == 2)
		{
			String country = parts[1];
			result = new Locale(language, country);
		} else
		{
			result = new Locale(language, "");
		}
		return result;
	}

	public static String getPreferencesLocation(Context context)
	{
		String result = (String)context.getApplicationInitParameter("extremecomponentsPreferencesLocation");
		if (StringUtils.isNotBlank(result))
			return result;
		if (logger.isDebugEnabled())
			logger.debug("There are no custom preferences defined. You need to include the context-param extremecomponentsPreferencesLocation in the web.xml to include custom preferences.");
		return null;
	}

	public static String getMessagesLocation(Context context)
	{
		String result = (String)context.getApplicationInitParameter("extremecomponentsMessagesLocation");
		if (StringUtils.isNotBlank(result))
			return result;
		if (logger.isDebugEnabled())
			logger.debug("There are no custom messages defined. You need to include the context-param extremecomponentsMessagesLocation in the web.xml to include custom messages.");
		return null;
	}

	public static Cell getCell(Column column)
	{
		Cell cell = TableCache.getInstance().getCell(column.getCell());
		return cell;
	}

	public static Cell getFilterCell(Column column, Object value)
	{
		Cell cell = TableCache.getInstance().getCell(column.getFilterCell());
		column.setValue(value);
		return cell;
	}

	public static Cell getHeaderCell(Column column, Object value)
	{
		Cell cell = TableCache.getInstance().getCell(column.getHeaderCell());
		column.setValue(value);
		return cell;
	}

	public static String getInterceptColumnPreference(TableModel model, String intercept)
	{
		return getInterceptPreference(model, intercept, "column.interceptor.");
	}

	public static String getInterceptRowPreference(TableModel model, String intercept)
	{
		return getInterceptPreference(model, intercept, "row.interceptor.");
	}

	public static String getInterceptPreference(TableModel model, String intercept, String interceptPreference)
	{
		String result;
		if (StringUtils.isNotBlank(intercept))
		{
			result = model.getPreferences().getPreference(interceptPreference + intercept);
			if (StringUtils.isBlank(result))
				result = intercept;
		} else
		{
			result = model.getPreferences().getPreference(interceptPreference + "default");
		}
		return result;
	}

	public static String getAlias(String alias, String property)
	{
		if (StringUtils.isBlank(alias) && StringUtils.isNotBlank(property))
			return property;
		else
			return alias;
	}

	public static Object getColumnPropertyValue(Object bean, String property)
	{
		Object result = null;
		try
		{
			if (ExtremeUtils.isBeanPropertyReadable(bean, property))
				result = PropertyUtils.getProperty(bean, property);
		}
		catch (Exception e)
		{
			if (logger.isDebugEnabled())
				logger.debug("Could not find the property [" + property + "]. Either the bean or property is null");
		}
		return result;
	}

	public static String getAutoGenerateColumnsPreference(TableModel model, String autoGenerateColumns)
	{
		String result = autoGenerateColumns;
		if (StringUtils.isNotBlank(autoGenerateColumns))
		{
			result = model.getPreferences().getPreference("columns.autoGenerateColumns." + autoGenerateColumns);
			if (StringUtils.isBlank(result))
				result = autoGenerateColumns;
		}
		return result;
	}

	public static Messages getMessages(TableModel model)
	{
		String messages = model.getPreferences().getPreference("messages");
		try
		{
			Class classDefinition = Class.forName(messages);
			return (Messages)classDefinition.newInstance();
		}
		catch (Exception e)
		{
			String msg = "Could not create the messages [" + messages + "]. The class was not found or does not exist.";
			logger.error(msg, e);
			throw new IllegalStateException(msg);
		}
	}

	public static String[] getValueAsArray(Object value)
	{
		if (value == null)
			return new String[0];
		if (value instanceof String[])
			return (String[])value;
		if (value instanceof List)
		{
			List valueList = (List)value;
			return (String[])valueList.toArray(new String[valueList.size()]);
		} else
		{
			return (new String[] {
				value.toString()
			});
		}
	}

	static 
	{
		logger = LogFactory.getLog(org.extremecomponents.table.core.TableModelUtils.class);
	}
}
