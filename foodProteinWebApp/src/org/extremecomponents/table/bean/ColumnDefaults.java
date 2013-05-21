// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ColumnDefaults.java

package org.extremecomponents.table.bean;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.extremecomponents.table.core.*;
import org.extremecomponents.table.handler.TableHandler;
import org.extremecomponents.util.ExtremeUtils;

// Referenced classes of package org.extremecomponents.table.bean:
//			Column, Table

final class ColumnDefaults
{

	ColumnDefaults()
	{
	}

	static String getCell(TableModel model, String cell)
	{
		String result;
		if (StringUtils.isNotBlank(cell))
		{
			result = model.getPreferences().getPreference("column.cell." + cell);
			if (StringUtils.isBlank(result))
				result = cell;
		} else
		{
			result = model.getPreferences().getPreference("column.cell.display");
		}
		return result;
	}

	static String getFilterCell(TableModel model, String filterCell)
	{
		String result;
		if (StringUtils.isNotBlank(filterCell))
		{
			result = model.getPreferences().getPreference("column.filterCell." + filterCell);
			if (StringUtils.isBlank(result))
				result = filterCell;
		} else
		{
			result = model.getPreferences().getPreference("column.filterCell.filter");
		}
		return result;
	}

	static String getHeaderCell(TableModel model, String headerCell)
	{
		String result;
		if (StringUtils.isNotBlank(headerCell))
		{
			result = model.getPreferences().getPreference("column.headerCell." + headerCell);
			if (StringUtils.isBlank(result))
				result = headerCell;
		} else
		{
			result = model.getPreferences().getPreference("column.headerCell.header");
		}
		return result;
	}

	static String getParse(TableModel model, Column column, String parse)
	{
		if (StringUtils.isNotBlank(parse))
			return parse;
		if (column.isDate())
			return model.getPreferences().getPreference("column.parse.date");
		else
			return parse;
	}

	static String getFormat(TableModel model, Column column, String format)
	{
		String result = getFormatInResourceBundle(model, column, format);
		if (StringUtils.isBlank(result))
			result = getFormatInProperties(model, column, format);
		if (StringUtils.isNotBlank(result))
			return result;
		else
			return format;
	}

	static String getFormatInResourceBundle(TableModel model, Column column, String format)
	{
		if (StringUtils.isNotBlank(format) && isNamedFormat(format))
			return model.getMessages().getMessage("column.format." + format);
		if (StringUtils.isBlank(format))
		{
			if (column.isCurrency())
				return model.getMessages().getMessage("column.format.currency");
			if (column.isDate())
				return model.getMessages().getMessage("column.format.date");
		}
		return null;
	}

	static String getFormatInProperties(TableModel model, Column column, String format)
	{
		if (StringUtils.isNotBlank(format) && isNamedFormat(format))
			return model.getPreferences().getPreference("column.format." + format);
		if (StringUtils.isBlank(format))
		{
			if (column.isCurrency())
				return model.getPreferences().getPreference("column.format.currency");
			if (column.isDate())
				return model.getPreferences().getPreference("column.format.date");
		}
		return null;
	}

	static boolean isNamedFormat(String format)
	{
		char args[] = {
			'#', '/', '-'
		};
		return StringUtils.containsNone(format, args);
	}

	static Boolean isSortable(TableModel model, Boolean sortable)
	{
		if (sortable == null)
			return new Boolean(model.getTableHandler().getTable().isSortable());
		else
			return sortable;
	}

	static Boolean isFilterable(TableModel model, Boolean filterable)
	{
		if (filterable == null)
			return new Boolean(model.getTableHandler().getTable().isFilterable());
		else
			return filterable;
	}

	static String getTitle(TableModel model, String title, String property)
	{
		if (StringUtils.isEmpty(title))
			return ExtremeUtils.camelCaseToWord(property);
		if (TableModelUtils.isResourceBundleProperty(title))
		{
			String resourceValue = model.getMessages().getMessage(title);
			if (resourceValue != null)
				return resourceValue;
		}
		return title;
	}

	static String getHeaderClass(TableModel model, String headerClass)
	{
		if (StringUtils.isBlank(headerClass))
			return model.getPreferences().getPreference("table.headerClass");
		else
			return headerClass;
	}

	static String getAlias(String alias, String property)
	{
		if (StringUtils.isBlank(alias) && StringUtils.isNotBlank(property))
			return property;
		else
			return alias;
	}

	public static String[] getCalcTitle(TableModel model, String calcTitle[])
	{
		List results = new ArrayList();
		if (calcTitle == null)
			return new String[0];
		for (int i = 0; i < calcTitle.length; i++)
		{
			String title = calcTitle[i];
			if (TableModelUtils.isResourceBundleProperty(title))
			{
				String resourceValue = model.getMessages().getMessage(title);
				if (resourceValue == null)
					resourceValue = title;
				if (StringUtils.isNotBlank(resourceValue))
					results.add(resourceValue);
			} else
			{
				results.add(title);
			}
		}

		return (String[])results.toArray(new String[results.size()]);
	}

	static Boolean isEscapeAutoFormat(TableModel model, Boolean escapeAutoFormat)
	{
		if (escapeAutoFormat == null)
			return Boolean.valueOf(model.getPreferences().getPreference("column.escapeAutoFormat"));
		else
			return escapeAutoFormat;
	}

	static Object getFilterOptions(TableModel model, Object filterOptions)
	{
		try
		{
			if (filterOptions != null)
				return RetrievalUtils.retrieveCollection(model.getContext(), filterOptions);
		}
		catch (Exception exception) { }
		return null;
	}
}
