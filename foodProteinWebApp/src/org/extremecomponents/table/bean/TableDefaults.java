// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TableDefaults.java

package org.extremecomponents.table.bean;

import org.apache.commons.lang.StringUtils;
import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.core.*;

final class TableDefaults
{

	TableDefaults()
	{
	}

	static String getTableId(String tableId)
	{
		if (StringUtils.isNotEmpty(tableId))
			return tableId;
		else
			return "ec";
	}

	static String getVar(String var, String tableId)
	{
		if (StringUtils.isNotEmpty(var))
			return var;
		else
			return tableId;
	}

	static String getStyleClass(TableModel model, String styleClass)
	{
		if (StringUtils.isBlank(styleClass))
			return model.getPreferences().getPreference("table.styleClass");
		else
			return styleClass;
	}

	static String getBorder(TableModel model, String border)
	{
		if (StringUtils.isBlank(border))
			return model.getPreferences().getPreference("table.border");
		else
			return border;
	}

	static Boolean isBufferView(TableModel model, Boolean bufferView)
	{
		if (bufferView == null)
			return Boolean.valueOf(model.getPreferences().getPreference("table.bufferView"));
		else
			return bufferView;
	}

	static String getCellpadding(TableModel model, String cellpadding)
	{
		if (StringUtils.isBlank(cellpadding))
			return model.getPreferences().getPreference("table.cellpadding");
		else
			return cellpadding;
	}

	static String getCellspacing(TableModel model, String cellspacing)
	{
		if (StringUtils.isBlank(cellspacing))
			return model.getPreferences().getPreference("table.cellspacing");
		else
			return cellspacing;
	}

	static int getRowsDisplayed(TableModel model, int rowsDisplayed)
	{
		if (rowsDisplayed == 0)
			return (new Integer(model.getPreferences().getPreference("table.rowsDisplayed"))).intValue();
		else
			return rowsDisplayed;
	}

	static int getMedianRowsDisplayed(TableModel model)
	{
		return (new Integer(model.getPreferences().getPreference("table.medianRowsDisplayed"))).intValue();
	}

	static int getMaxRowsDisplayed(TableModel model)
	{
		return (new Integer(model.getPreferences().getPreference("table.maxRowsDisplayed"))).intValue();
	}

	static Boolean isSortable(TableModel model, Boolean sortable)
	{
		if (sortable == null)
			return Boolean.valueOf(model.getPreferences().getPreference("table.sortable"));
		else
			return sortable;
	}

	static Boolean isFilterable(TableModel model, Boolean filterable)
	{
		if (filterable == null)
			return Boolean.valueOf(model.getPreferences().getPreference("table.filterable"));
		else
			return filterable;
	}

	static Boolean isShowPagination(TableModel model, Boolean showPagination)
	{
		if (showPagination == null)
			return Boolean.valueOf(model.getPreferences().getPreference("table.showPagination"));
		else
			return showPagination;
	}

	static Boolean isShowExports(TableModel model, Boolean showExports)
	{
		if (showExports == null)
			return Boolean.valueOf(model.getPreferences().getPreference("table.showExports"));
		else
			return showExports;
	}

	static Boolean isShowStatusBar(TableModel model, Boolean showStatusBar)
	{
		if (showStatusBar == null)
			return Boolean.valueOf(model.getPreferences().getPreference("table.showStatusBar"));
		else
			return showStatusBar;
	}

	static Boolean isShowTitle(TableModel model, Boolean showTitle)
	{
		if (showTitle == null)
			return Boolean.valueOf(model.getPreferences().getPreference("table.showTitle"));
		else
			return showTitle;
	}

	static Boolean isShowTooltips(TableModel model, Boolean showTooltips)
	{
		if (showTooltips == null)
			return Boolean.valueOf(model.getPreferences().getPreference("table.showTooltips"));
		else
			return showTooltips;
	}

	static String getImagePath(TableModel model, String imagePath)
	{
		if (StringUtils.isNotBlank(imagePath))
			return imagePath;
		String contextPath = model.getContext().getContextPath();
		String results = retrieveImagePath(model);
		if (results != null && results.startsWith("/") && !results.startsWith(contextPath))
			return contextPath + results;
		else
			return results;
	}

	static String retrieveImagePath(TableModel model)
	{
		String resourceValue = model.getMessages().getMessage("table.imagePath");
		if (resourceValue != null)
			return resourceValue;
		else
			return model.getPreferences().getPreference("table.imagePath");
	}

	static String getTitle(TableModel model, String title)
	{
		if (TableModelUtils.isResourceBundleProperty(title))
		{
			String resourceValue = model.getMessages().getMessage(title);
			if (resourceValue != null)
				return resourceValue;
		}
		return title;
	}

	static String getWidth(TableModel model, String width)
	{
		if (StringUtils.isBlank(width))
			return model.getPreferences().getPreference("table.width");
		else
			return width;
	}

	static Boolean getAutoIncludeParameters(TableModel model, Boolean autoIncludeParameters)
	{
		if (autoIncludeParameters == null)
			return Boolean.valueOf(model.getPreferences().getPreference("table.autoIncludeParameters"));
		else
			return autoIncludeParameters;
	}

	static String getFilterRowsCallback(TableModel model, String filterRowsCallback)
	{
		String result;
		if (StringUtils.isNotBlank(filterRowsCallback))
		{
			result = model.getPreferences().getPreference("table.filterRowsCallback." + filterRowsCallback);
			if (StringUtils.isBlank(result))
				result = filterRowsCallback;
		} else
		{
			result = model.getPreferences().getPreference("table.filterRowsCallback.default");
		}
		return result;
	}

	static String getRetrieveRowsCallback(TableModel model, String retrieveRowsCallback)
	{
		String result;
		if (StringUtils.isNotBlank(retrieveRowsCallback))
		{
			result = model.getPreferences().getPreference("table.retrieveRowsCallback." + retrieveRowsCallback);
			if (StringUtils.isBlank(result))
				result = retrieveRowsCallback;
		} else
		{
			result = model.getPreferences().getPreference("table.retrieveRowsCallback.default");
		}
		return result;
	}

	static String getSortRowsCallback(TableModel model, String sortRowsCallback)
	{
		String result;
		if (StringUtils.isNotBlank(sortRowsCallback))
		{
			result = model.getPreferences().getPreference("table.sortRowsCallback." + sortRowsCallback);
			if (StringUtils.isBlank(result))
				result = sortRowsCallback;
		} else
		{
			result = model.getPreferences().getPreference("table.sortRowsCallback.default");
		}
		return result;
	}

	static String getState(TableModel model, String state)
	{
		String result;
		if (StringUtils.isNotBlank(state))
		{
			result = model.getPreferences().getPreference("table.state." + state);
			if (StringUtils.isBlank(result))
				result = state;
		} else
		{
			result = model.getPreferences().getPreference("table.state.default");
		}
		return result;
	}

	static String getStateAttr(TableModel model, String stateAttr)
	{
		if (StringUtils.isBlank(stateAttr))
			return model.getPreferences().getPreference("table.stateAttr");
		else
			return stateAttr;
	}

	static String getView(String view)
	{
		if (StringUtils.isBlank(view))
			return "html";
		else
			return view;
	}

	static String getMethod(TableModel model, String method)
	{
		if (StringUtils.isBlank(method))
			return model.getPreferences().getPreference("table.method");
		else
			return method;
	}

	static String getTheme(TableModel model, String theme)
	{
		if (StringUtils.isEmpty(theme))
			return model.getPreferences().getPreference("table.theme");
		else
			return theme;
	}
}
