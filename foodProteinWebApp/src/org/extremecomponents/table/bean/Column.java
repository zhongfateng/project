// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Column.java

package org.extremecomponents.table.bean;

import java.util.Collection;
import org.apache.commons.lang.StringUtils;
import org.extremecomponents.table.core.Preferences;
import org.extremecomponents.table.core.TableModel;

// Referenced classes of package org.extremecomponents.table.bean:
//			Attributes, ColumnDefaults

public class Column extends Attributes
{

	private TableModel model;
	private String alias;
	private String calc[];
	private String calcTitle[];
	private String cell;
	private String cellDisplay;
	private Object filterOptions;
	private Boolean escapeAutoFormat;
	private Boolean filterable;
	private String filterCell;
	private String filterClass;
	private String filterStyle;
	private String format;
	private String headerCell;
	private String headerClass;
	private String headerStyle;
	private String interceptor;
	private String parse;
	private String property;
	private Object propertyValue;
	private Boolean sortable;
	private String style;
	private String styleClass;
	private String title;
	private Object value;
	private String viewsAllowed[];
	private String viewsDenied[];
	private String width;
	private boolean isFirstColumn;
	private boolean isLastColumn;

	public Column(TableModel model)
	{
		this.model = model;
	}

	public void defaults()
	{
		cell = ColumnDefaults.getCell(model, cell);
		alias = ColumnDefaults.getAlias(alias, property);
		calcTitle = ColumnDefaults.getCalcTitle(model, calcTitle);
		escapeAutoFormat = ColumnDefaults.isEscapeAutoFormat(model, escapeAutoFormat);
		format = ColumnDefaults.getFormat(model, this, format);
		filterable = ColumnDefaults.isFilterable(model, filterable);
		filterCell = ColumnDefaults.getFilterCell(model, filterCell);
		filterOptions = ColumnDefaults.getFilterOptions(model, filterOptions);
		headerCell = ColumnDefaults.getHeaderCell(model, headerCell);
		headerClass = ColumnDefaults.getHeaderClass(model, headerClass);
		parse = ColumnDefaults.getParse(model, this, parse);
		sortable = ColumnDefaults.isSortable(model, sortable);
		title = ColumnDefaults.getTitle(model, title, alias);
	}

	public String getAlias()
	{
		return alias;
	}

	public void setAlias(String alias)
	{
		this.alias = alias;
	}

	public boolean isCalculated()
	{
		return calc != null && calc.length > 0;
	}

	public String[] getCalc()
	{
		return calc;
	}

	public void setCalc(String calc)
	{
		if (calc != null)
			this.calc = StringUtils.split(calc, ",");
	}

	public String[] getCalcTitle()
	{
		return calcTitle;
	}

	public void setCalcTitle(String calcTitle)
	{
		if (calcTitle != null)
			this.calcTitle = StringUtils.split(calcTitle, ",");
	}

	public String getCell()
	{
		return cell;
	}

	public void setCell(String cell)
	{
		this.cell = cell;
	}

	public String getCellDisplay()
	{
		return cellDisplay;
	}

	public void setCellDisplay(String cellDisplay)
	{
		this.cellDisplay = cellDisplay;
	}

	public Collection getFilterOptions()
	{
		return (Collection)filterOptions;
	}

	public void setFilterOptions(Object filterOptions)
	{
		this.filterOptions = filterOptions;
	}

	public boolean isEscapeAutoFormat()
	{
		return escapeAutoFormat.booleanValue();
	}

	public void setEscapeAutoFormat(Boolean escapeAutoFormat)
	{
		this.escapeAutoFormat = escapeAutoFormat;
	}

	public boolean isFilterable()
	{
		return filterable.booleanValue();
	}

	public void setFilterable(Boolean filterable)
	{
		this.filterable = filterable;
	}

	public String getFilterCell()
	{
		return filterCell;
	}

	public void setFilterCell(String filterCell)
	{
		this.filterCell = filterCell;
	}

	public String getFilterClass()
	{
		return filterClass;
	}

	public void setFilterClass(String filterClass)
	{
		this.filterClass = filterClass;
	}

	public String getFilterStyle()
	{
		return filterStyle;
	}

	public void setFilterStyle(String filterStyle)
	{
		this.filterStyle = filterStyle;
	}

	public String getFormat()
	{
		return format;
	}

	public void setFormat(String format)
	{
		this.format = format;
	}

	public String getHeaderCell()
	{
		return headerCell;
	}

	public void setHeaderCell(String headerCell)
	{
		this.headerCell = headerCell;
	}

	public String getHeaderClass()
	{
		return headerClass;
	}

	public void setHeaderClass(String headerClass)
	{
		this.headerClass = headerClass;
	}

	public String getHeaderStyle()
	{
		return headerStyle;
	}

	public void setHeaderStyle(String headerStyle)
	{
		this.headerStyle = headerStyle;
	}

	public String getInterceptor()
	{
		return interceptor;
	}

	public void setInterceptor(String interceptor)
	{
		this.interceptor = interceptor;
	}

	public String getParse()
	{
		return parse;
	}

	public void setParse(String parse)
	{
		this.parse = parse;
	}

	public String getProperty()
	{
		return property;
	}

	public void setProperty(String property)
	{
		this.property = property;
	}

	public Object getPropertyValue()
	{
		return propertyValue;
	}

	public String getPropertyValueAsString()
	{
		Object value = getPropertyValue();
		if (value != null)
			return String.valueOf(value);
		else
			return "";
	}

	public void setPropertyValue(Object propertyValue)
	{
		this.propertyValue = propertyValue;
	}

	public boolean isSortable()
	{
		return sortable.booleanValue();
	}

	public void setSortable(Boolean sortable)
	{
		this.sortable = sortable;
	}

	public String getStyle()
	{
		return style;
	}

	public void setStyle(String style)
	{
		this.style = style;
	}

	public String getStyleClass()
	{
		return styleClass;
	}

	public void setStyleClass(String styleClass)
	{
		this.styleClass = styleClass;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public Object getValue()
	{
		return value;
	}

	public String getValueAsString()
	{
		Object value = getValue();
		if (value != null)
			return String.valueOf(value);
		else
			return "";
	}

	public void setValue(Object value)
	{
		this.value = value;
	}

	public String[] getViewsAllowed()
	{
		return viewsAllowed;
	}

	public void setViewsAllowed(String viewsAllowed)
	{
		if (viewsAllowed != null)
			this.viewsAllowed = StringUtils.split(viewsAllowed, ",");
	}

	public String[] getViewsDenied()
	{
		return viewsDenied;
	}

	public void setViewsDenied(String viewsDenied)
	{
		if (viewsDenied != null)
			this.viewsDenied = StringUtils.split(viewsDenied, ",");
	}

	public String getWidth()
	{
		return width;
	}

	public void setWidth(String width)
	{
		this.width = width;
	}

	public boolean isDate()
	{
		return StringUtils.isNotEmpty(getCell()) && getCell().equals(model.getPreferences().getPreference("column.cell.date"));
	}

	public boolean isCurrency()
	{
		return StringUtils.isNotBlank(getCell()) && getCell().equalsIgnoreCase(model.getPreferences().getPreference("column.cell.currency"));
	}

	public boolean isFirstColumn()
	{
		return isFirstColumn;
	}

	public void setFirstColumn(boolean isFirstColumn)
	{
		this.isFirstColumn = isFirstColumn;
	}

	public boolean isLastColumn()
	{
		return isLastColumn;
	}

	public void setLastColumn(boolean isLastColumn)
	{
		this.isLastColumn = isLastColumn;
	}
}
