// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ColumnTag.java

package org.extremecomponents.table.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import org.apache.commons.lang.StringUtils;
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;
import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.core.TableModelUtils;
import org.extremecomponents.table.handler.ColumnHandler;
import org.extremecomponents.table.handler.ViewHandler;
import org.extremecomponents.table.interceptor.ColumnInterceptor;
import org.extremecomponents.util.ExceptionUtils;

// Referenced classes of package org.extremecomponents.table.tag:
//			TagUtils

public class ColumnTag extends BodyTagSupport
	implements ColumnInterceptor
{

	private String alias;
	private String calc;
	private String calcTitle;
	private String cell;
	private Object filterOptions;
	private String filterable;
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
	private String sortable;
	private String style;
	private String styleClass;
	private String title;
	private Object value;
	private String viewsAllowed;
	private String viewsDenied;
	private String width;
	private String escapeAutoFormat;

	public ColumnTag()
	{
	}

	public void setAlias(String alias)
	{
		this.alias = alias;
	}

	public void setCalc(String calc)
	{
		this.calc = calc;
	}

	public void setCalcTitle(String totalTitle)
	{
		calcTitle = totalTitle;
	}

	public void setCell(String cell)
	{
		this.cell = cell;
	}

	public void setEscapeAutoFormat(String escapeAutoFormat)
	{
		this.escapeAutoFormat = escapeAutoFormat;
	}

	public void setFilterable(String filterable)
	{
		this.filterable = filterable;
	}

	public void setFilterCell(String filterCell)
	{
		this.filterCell = filterCell;
	}

	public void setFilterClass(String filterClass)
	{
		this.filterClass = filterClass;
	}

	public void setFilterOptions(Object filterOptions)
	{
		this.filterOptions = filterOptions;
	}

	public void setFilterStyle(String filterStyle)
	{
		this.filterStyle = filterStyle;
	}

	public void setFormat(String format)
	{
		this.format = format;
	}

	public void setHeaderCell(String headerCell)
	{
		this.headerCell = headerCell;
	}

	public void setHeaderClass(String headerClass)
	{
		this.headerClass = headerClass;
	}

	public void setHeaderStyle(String headerStyle)
	{
		this.headerStyle = headerStyle;
	}

	public void setInterceptor(String interceptor)
	{
		this.interceptor = interceptor;
	}

	public void setParse(String parse)
	{
		this.parse = parse;
	}

	public void setProperty(String property)
	{
		this.property = property;
	}

	public void setSortable(String sortable)
	{
		this.sortable = sortable;
	}

	public void setStyle(String style)
	{
		this.style = style;
	}

	public void setStyleClass(String styleClass)
	{
		this.styleClass = styleClass;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void setValue(Object value)
	{
		this.value = value;
	}

	public void setViewsAllowed(String viewsAllowed)
	{
		this.viewsAllowed = viewsAllowed;
	}

	public void setViewsDenied(String viewsDenied)
	{
		this.viewsDenied = viewsDenied;
	}

	public void setWidth(String width)
	{
		this.width = width;
	}

	protected Object getColumnValue(Object propertyValue)
		throws JspException
	{
		Object result = value;
		if (result == null && bodyContent != null)
			result = getBodyContent().getString();
		if (result != null)
			result = ExpressionEvaluatorManager.evaluate("result", result.toString(), java.lang.Object.class, this, pageContext);
		if (result == null || result != null && (result instanceof String) && StringUtils.isBlank(result.toString()))
			result = propertyValue;
		return result;
	}

	public int doStartTag()
		throws JspException
	{
		return TagUtils.isIteratingBody(this) ? 2 : 0;
	}

	public int doEndTag()
		throws JspException
	{
		try
		{
			TableModel model = TagUtils.getModel(this);
			if (TagUtils.isIteratingBody(this))
			{
				String alias = TagUtils.evaluateExpressionAsString("alias", this.alias, this, pageContext);
				String property = TagUtils.evaluateExpressionAsString("property", this.property, this, pageContext);
				Column column = model.getColumnHandler().getColumnByAlias(TableModelUtils.getAlias(alias, property));
				if (column != null)
				{
					Object bean = TagUtils.getModel(this).getCurrentRowBean();
					Object propertyValue = TableModelUtils.getColumnPropertyValue(bean, property);
					column.setValue(getColumnValue(propertyValue));
					column.setPropertyValue(propertyValue);
					modifyColumnAttributes(model, column);
					model.getColumnHandler().modifyColumnAttributes(column);
					model.getViewHandler().addColumnValueToView(column);
				}
			} else
			{
				Column column = new Column(model);
				column.setAlias(TagUtils.evaluateExpressionAsString("alias", this.alias, this, pageContext));
				column.setCalc(TagUtils.evaluateExpressionAsString("calc", calc, this, pageContext));
				column.setCalcTitle(TagUtils.evaluateExpressionAsString("calcTitle", calcTitle, this, pageContext));
				column.setCell(TagUtils.evaluateExpressionAsString("cell", cell, this, pageContext));
				column.setEscapeAutoFormat(TagUtils.evaluateExpressionAsBoolean("escapeAutoFormat", escapeAutoFormat, this, pageContext));
				column.setFilterable(TagUtils.evaluateExpressionAsBoolean("filterable", filterable, this, pageContext));
				column.setFilterCell(TagUtils.evaluateExpressionAsString("filterCell", filterCell, this, pageContext));
				column.setFilterClass(TagUtils.evaluateExpressionAsString("filterClass", filterClass, this, pageContext));
				column.setFilterOptions(TagUtils.evaluateExpressionAsObject("filterOptions", filterOptions, this, pageContext));
				column.setFilterStyle(TagUtils.evaluateExpressionAsString("filterStyle", filterStyle, this, pageContext));
				column.setFormat(TagUtils.evaluateExpressionAsString("format", format, this, pageContext));
				column.setHeaderCell(TagUtils.evaluateExpressionAsString("headerCell", headerCell, this, pageContext));
				column.setHeaderClass(TagUtils.evaluateExpressionAsString("headerClass", headerClass, this, pageContext));
				column.setHeaderStyle(TagUtils.evaluateExpressionAsString("headerStyle", headerStyle, this, pageContext));
				column.setInterceptor(TagUtils.evaluateExpressionAsString("interceptor", interceptor, this, pageContext));
				column.setParse(TagUtils.evaluateExpressionAsString("parse", parse, this, pageContext));
				column.setProperty(TagUtils.evaluateExpressionAsString("property", this.property, this, pageContext));
				column.setSortable(TagUtils.evaluateExpressionAsBoolean("sortable", sortable, this, pageContext));
				column.setStyle(TagUtils.evaluateExpressionAsString("style", style, this, pageContext));
				column.setStyleClass(TagUtils.evaluateExpressionAsString("styleClass", styleClass, this, pageContext));
				column.setTitle(TagUtils.evaluateExpressionAsString("title", title, this, pageContext));
				column.setViewsAllowed(TagUtils.evaluateExpressionAsString("viewsToAllow", viewsAllowed, this, pageContext));
				column.setViewsDenied(TagUtils.evaluateExpressionAsString("viewsToDeny", viewsDenied, this, pageContext));
				column.setWidth(TagUtils.evaluateExpressionAsString("width", width, this, pageContext));
				addColumnAttributes(model, column);
				model.getColumnHandler().addColumn(column);
			}
			if (bodyContent != null)
				bodyContent.clearBody();
		}
		catch (Exception e)
		{
			throw new JspException("ColumnTag.doEndTag() Problem: " + ExceptionUtils.formatStackTrace(e));
		}
		return 6;
	}

	public void addColumnAttributes(TableModel tablemodel, Column column1)
	{
	}

	public void modifyColumnAttributes(TableModel tablemodel, Column column1)
	{
	}

	public void release()
	{
		alias = null;
		calc = null;
		calcTitle = null;
		cell = null;
		escapeAutoFormat = null;
		filterable = null;
		filterCell = null;
		filterClass = null;
		filterStyle = null;
		format = null;
		headerCell = null;
		headerClass = null;
		headerStyle = null;
		interceptor = null;
		parse = null;
		property = null;
		sortable = null;
		style = null;
		styleClass = null;
		title = null;
		value = null;
		viewsAllowed = null;
		viewsDenied = null;
		width = null;
		super.release();
	}
}
