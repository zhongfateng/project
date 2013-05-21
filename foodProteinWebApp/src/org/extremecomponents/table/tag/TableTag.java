// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TableTag.java

package org.extremecomponents.table.tag;

import java.util.Collection;
import java.util.Iterator;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.tagext.TryCatchFinally;
import org.extremecomponents.table.bean.Table;
import org.extremecomponents.table.context.JspPageContext;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.core.TableModelImpl;
import org.extremecomponents.table.interceptor.TableInterceptor;
import org.extremecomponents.util.ExceptionUtils;

// Referenced classes of package org.extremecomponents.table.tag:
//			TagUtils

public class TableTag extends TagSupport
	implements TryCatchFinally, TableInterceptor
{

	private String action;
	private String autoIncludeParameters;
	private String border;
	private String bufferView;
	private String cellpadding;
	private String cellspacing;
	private String filterable;
	private String filterRowsCallback;
	private String form;
	private String imagePath;
	private String interceptor;
	private Object items;
	private String locale;
	private String method;
	private String onInvokeAction;
	private String retrieveRowsCallback;
	private String rowsDisplayed;
	private String scope;
	private String showExports;
	private String showPagination;
	private String showStatusBar;
	private String showTitle;
	private String showTooltips;
	private String sortRowsCallback;
	private String sortable;
	private String state;
	private String stateAttr;
	private String style;
	private String styleClass;
	private String tableId;
	private String theme;
	private String title;
	private String var;
	private String view;
	private String width;
	protected TableModel model;
	private Iterator iterator;

	public TableTag()
	{
	}

	public TableModel getModel()
	{
		return model;
	}

	public void setAction(String action)
	{
		this.action = action;
	}

	public void setAutoIncludeParameters(String autoIncludeParameters)
	{
		this.autoIncludeParameters = autoIncludeParameters;
	}

	public void setBorder(String border)
	{
		this.border = border;
	}

	public void setBufferView(String bufferView)
	{
		this.bufferView = bufferView;
	}

	public void setCellpadding(String cellpadding)
	{
		this.cellpadding = cellpadding;
	}

	public void setCellspacing(String cellspacing)
	{
		this.cellspacing = cellspacing;
	}

	public void setFilterable(String filterable)
	{
		this.filterable = filterable;
	}

	public void setFilterRowsCallback(String filterRowsCallback)
	{
		this.filterRowsCallback = filterRowsCallback;
	}

	public void setForm(String form)
	{
		this.form = form;
	}

	public void setImagePath(String imagePath)
	{
		this.imagePath = imagePath;
	}

	public void setInterceptor(String interceptor)
	{
		this.interceptor = interceptor;
	}

	public void setItems(Object items)
	{
		this.items = items;
	}

	public void setLocale(String locale)
	{
		this.locale = locale;
	}

	public void setMethod(String method)
	{
		this.method = method;
	}

	public void setOnInvokeAction(String onInvokeAction)
	{
		this.onInvokeAction = onInvokeAction;
	}

	public void setRetrieveRowsCallback(String retrieveRowsCallback)
	{
		this.retrieveRowsCallback = retrieveRowsCallback;
	}

	public void setRowsDisplayed(String rowsDisplayed)
	{
		this.rowsDisplayed = rowsDisplayed;
	}

	public void setScope(String scope)
	{
		this.scope = scope;
	}

	public void setShowPagination(String showPagination)
	{
		this.showPagination = showPagination;
	}

	public void setShowExports(String showExports)
	{
		this.showExports = showExports;
	}

	public void setShowStatusBar(String showStatusBar)
	{
		this.showStatusBar = showStatusBar;
	}

	public void setShowTitle(String showTitle)
	{
		this.showTitle = showTitle;
	}

	public void setShowTooltips(String showTooltips)
	{
		this.showTooltips = showTooltips;
	}

	public void setSortRowsCallback(String sortRowsCallback)
	{
		this.sortRowsCallback = sortRowsCallback;
	}

	public void setSortable(String sortable)
	{
		this.sortable = sortable;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public void setStateAttr(String stateAttr)
	{
		this.stateAttr = stateAttr;
	}

	public void setStyle(String style)
	{
		this.style = style;
	}

	public void setStyleClass(String styleClass)
	{
		this.styleClass = styleClass;
	}

	public void setTableId(String tableId)
	{
		this.tableId = tableId;
	}

	public void setTheme(String theme)
	{
		this.theme = theme;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void setVar(String var)
	{
		this.var = var;
	}

	public void setView(String view)
	{
		this.view = view;
	}

	public void setWidth(String width)
	{
		this.width = width;
	}

	public int doStartTag()
		throws JspException
	{
		try
		{
			iterator = null;
			pageContext.setAttribute("ROWCOUNT", "0");
			model = new TableModelImpl(new JspPageContext(pageContext), TagUtils.evaluateExpressionAsString("locale", locale, this, pageContext));
			Table table = new Table(model);
			table.setAction(TagUtils.evaluateExpressionAsString("action", action, this, pageContext));
			table.setAutoIncludeParameters(TagUtils.evaluateExpressionAsBoolean("autoIncludeParameters", autoIncludeParameters, this, pageContext));
			table.setBorder(TagUtils.evaluateExpressionAsString("border", border, this, pageContext));
			table.setBufferView(TagUtils.evaluateExpressionAsBoolean("bufferView", bufferView, this, pageContext));
			table.setCellpadding(TagUtils.evaluateExpressionAsString("cellpadding", cellpadding, this, pageContext));
			table.setCellspacing(TagUtils.evaluateExpressionAsString("cellspacing", cellspacing, this, pageContext));
			table.setFilterable(TagUtils.evaluateExpressionAsBoolean("filterable", filterable, this, pageContext));
			table.setFilterRowsCallback(TagUtils.evaluateExpressionAsString("filterRowsCallback", filterRowsCallback, this, pageContext));
			table.setForm(TagUtils.evaluateExpressionAsString("form", form, this, pageContext));
			table.setImagePath(TagUtils.evaluateExpressionAsString("imagePath", imagePath, this, pageContext));
			table.setInterceptor(TagUtils.evaluateExpressionAsString("interceptor", interceptor, this, pageContext));
			table.setItems(TagUtils.evaluateExpressionAsObject("items", items, this, pageContext));
			table.setLocale(TagUtils.evaluateExpressionAsString("locale", locale, this, pageContext));
			table.setMethod(TagUtils.evaluateExpressionAsString("method", method, this, pageContext));
			table.setOnInvokeAction(TagUtils.evaluateExpressionAsString("onInvokeAction", onInvokeAction, this, pageContext));
			table.setRetrieveRowsCallback(TagUtils.evaluateExpressionAsString("retrieveRowsCallback", retrieveRowsCallback, this, pageContext));
			table.setRowsDisplayed(TagUtils.evaluateExpressionAsInt("rowsDisplayed", rowsDisplayed, this, pageContext));
			table.setScope(TagUtils.evaluateExpressionAsString("scope", scope, this, pageContext));
			table.setShowExports(TagUtils.evaluateExpressionAsBoolean("showExports", showExports, this, pageContext));
			table.setShowPagination(TagUtils.evaluateExpressionAsBoolean("showPagination", showPagination, this, pageContext));
			table.setShowStatusBar(TagUtils.evaluateExpressionAsBoolean("showStatusBar", showStatusBar, this, pageContext));
			table.setShowTitle(TagUtils.evaluateExpressionAsBoolean("showTitle", showTitle, this, pageContext));
			table.setShowTooltips(TagUtils.evaluateExpressionAsBoolean("showTooltips", showTooltips, this, pageContext));
			table.setSortRowsCallback(TagUtils.evaluateExpressionAsString("sortRowsCallback", sortRowsCallback, this, pageContext));
			table.setSortable(TagUtils.evaluateExpressionAsBoolean("sortable", sortable, this, pageContext));
			table.setState(TagUtils.evaluateExpressionAsString("state", state, this, pageContext));
			table.setStateAttr(TagUtils.evaluateExpressionAsString("stateAttr", stateAttr, this, pageContext));
			table.setStyle(TagUtils.evaluateExpressionAsString("style", style, this, pageContext));
			table.setStyleClass(TagUtils.evaluateExpressionAsString("styleClass", styleClass, this, pageContext));
			table.setTableId(TagUtils.evaluateExpressionAsString("tableId", tableId, this, pageContext));
			table.setTheme(TagUtils.evaluateExpressionAsString("theme", theme, this, pageContext));
			table.setTitle(TagUtils.evaluateExpressionAsString("title", title, this, pageContext));
			table.setVar(TagUtils.evaluateExpressionAsString("var", var, this, pageContext));
			table.setView(TagUtils.evaluateExpressionAsString("view", view, this, pageContext));
			table.setWidth(TagUtils.evaluateExpressionAsString("width", width, this, pageContext));
			addTableAttributes(model, table);
			model.addTable(table);
		}
		catch (Exception e)
		{
			throw new JspException("TableTag.doStartTag() Problem: " + ExceptionUtils.formatStackTrace(e));
		}
		return 1;
	}

	public int doAfterBody()
		throws JspException
	{
		try
		{
			if (iterator == null)
				iterator = model.execute().iterator();
			if (iterator != null && iterator.hasNext())
			{
				Object bean = iterator.next();
				model.setCurrentRowBean(bean);
				return 2;
			}
		}
		catch (Exception e)
		{
			throw new JspException("TableTag.doAfterBody() Problem: " + ExceptionUtils.formatStackTrace(e));
		}
		return 0;
	}

	public int doEndTag()
		throws JspException
	{
		try
		{
			pageContext.getOut().println(model.getViewData());
		}
		catch (Exception e)
		{
			throw new JspException("TableTag.doEndTag() Problem: " + ExceptionUtils.formatStackTrace(e));
		}
		return 6;
	}

	public void addTableAttributes(TableModel tablemodel, Table table1)
	{
	}

	public void doCatch(Throwable e)
		throws Throwable
	{
		throw new JspException("TableTag Problem: " + ExceptionUtils.formatStackTrace(e));
	}

	public void doFinally()
	{
		iterator = null;
		model = null;
	}

	public void release()
	{
		action = null;
		autoIncludeParameters = null;
		border = null;
		cellpadding = null;
		cellspacing = null;
		filterable = null;
		filterRowsCallback = null;
		form = null;
		imagePath = null;
		interceptor = null;
		items = null;
		locale = null;
		method = null;
		onInvokeAction = null;
		retrieveRowsCallback = null;
		rowsDisplayed = null;
		scope = null;
		showExports = null;
		showPagination = null;
		showStatusBar = null;
		sortRowsCallback = null;
		sortable = null;
		state = null;
		stateAttr = null;
		style = null;
		styleClass = null;
		tableId = null;
		title = null;
		var = null;
		view = null;
		width = null;
		super.release();
	}
}
