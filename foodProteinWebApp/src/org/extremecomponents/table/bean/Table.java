// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Table.java

package org.extremecomponents.table.bean;

import org.extremecomponents.table.core.TableModel;

// Referenced classes of package org.extremecomponents.table.bean:
//			Attributes, TableDefaults

public class Table extends Attributes
{

	private TableModel model;
	private String action;
	private Boolean autoIncludeParameters;
	private String border;
	private Boolean bufferView;
	private String cellpadding;
	private String cellspacing;
	private Boolean filterable;
	private String filterRowsCallback;
	private String form;
	private String imagePath;
	private String interceptor;
	private Object items;
	private String locale;
	private int maxRowsDisplayed;
	private int medianRowsDisplayed;
	private String method;
	private String onInvokeAction;
	private String retrieveRowsCallback;
	private int rowsDisplayed;
	private Boolean saveFilterSort;
	private String scope;
	private Boolean showExports;
	private Boolean showPagination;
	private Boolean showStatusBar;
	private Boolean showTitle;
	private Boolean showTooltips;
	private String sortRowsCallback;
	private Boolean sortable;
	private String state;
	private String stateAttr;
	private String style;
	private String styleClass;
	private String tableId;
	private String title;
	private String theme;
	private String var;
	private String view;
	private String width;

	public Table(TableModel model)
	{
		this.model = model;
	}

	public void defaults()
	{
		tableId = TableDefaults.getTableId(tableId);
		autoIncludeParameters = TableDefaults.getAutoIncludeParameters(model, autoIncludeParameters);
		border = TableDefaults.getBorder(model, border);
		bufferView = TableDefaults.isBufferView(model, bufferView);
		cellpadding = TableDefaults.getCellpadding(model, cellpadding);
		cellspacing = TableDefaults.getCellspacing(model, cellspacing);
		filterable = TableDefaults.isFilterable(model, filterable);
		filterRowsCallback = TableDefaults.getFilterRowsCallback(model, filterRowsCallback);
		imagePath = TableDefaults.getImagePath(model, imagePath);
		maxRowsDisplayed = TableDefaults.getMaxRowsDisplayed(model);
		medianRowsDisplayed = TableDefaults.getMedianRowsDisplayed(model);
		method = TableDefaults.getMethod(model, method);
		retrieveRowsCallback = TableDefaults.getRetrieveRowsCallback(model, retrieveRowsCallback);
		rowsDisplayed = TableDefaults.getRowsDisplayed(model, rowsDisplayed);
		showPagination = TableDefaults.isShowPagination(model, showPagination);
		showExports = TableDefaults.isShowExports(model, showExports);
		showStatusBar = TableDefaults.isShowStatusBar(model, showStatusBar);
		showTitle = TableDefaults.isShowTitle(model, showTitle);
		showTooltips = TableDefaults.isShowTooltips(model, showTooltips);
		state = TableDefaults.getState(model, state);
		stateAttr = TableDefaults.getStateAttr(model, stateAttr);
		sortable = TableDefaults.isSortable(model, sortable);
		sortRowsCallback = TableDefaults.getSortRowsCallback(model, sortRowsCallback);
		styleClass = TableDefaults.getStyleClass(model, styleClass);
		theme = TableDefaults.getTheme(model, theme);
		title = TableDefaults.getTitle(model, title);
		var = TableDefaults.getVar(var, tableId);
		view = TableDefaults.getView(view);
		width = TableDefaults.getWidth(model, width);
	}

	public String getAction()
	{
		return action;
	}

	public void setAction(String action)
	{
		this.action = action;
	}

	public boolean isAutoIncludeParameters()
	{
		return autoIncludeParameters.booleanValue();
	}

	public void setAutoIncludeParameters(Boolean autoIncludeParameters)
	{
		this.autoIncludeParameters = autoIncludeParameters;
	}

	public String getBorder()
	{
		return border;
	}

	public void setBorder(String border)
	{
		this.border = border;
	}

	public boolean isBufferView()
	{
		return bufferView.booleanValue();
	}

	public void setBufferView(Boolean bufferView)
	{
		this.bufferView = bufferView;
	}

	public String getCellpadding()
	{
		return cellpadding;
	}

	public void setCellpadding(String cellpadding)
	{
		this.cellpadding = cellpadding;
	}

	public String getCellspacing()
	{
		return cellspacing;
	}

	public void setCellspacing(String cellspacing)
	{
		this.cellspacing = cellspacing;
	}

	public boolean isFilterable()
	{
		return filterable.booleanValue();
	}

	public void setFilterable(Boolean filterable)
	{
		this.filterable = filterable;
	}

	public String getFilterRowsCallback()
	{
		return filterRowsCallback;
	}

	public void setFilterRowsCallback(String filterRowsCallback)
	{
		this.filterRowsCallback = filterRowsCallback;
	}

	public String getForm()
	{
		return form;
	}

	public void setForm(String form)
	{
		this.form = form;
	}

	public String getImagePath()
	{
		return imagePath;
	}

	public void setImagePath(String imagePath)
	{
		this.imagePath = imagePath;
	}

	public String getInterceptor()
	{
		return interceptor;
	}

	public void setInterceptor(String interceptor)
	{
		this.interceptor = interceptor;
	}

	public Object getItems()
	{
		return items;
	}

	public void setItems(Object items)
	{
		this.items = items;
	}

	public String getLocale()
	{
		return locale;
	}

	public void setLocale(String locale)
	{
		this.locale = locale;
	}

	public String getMethod()
	{
		return method;
	}

	public void setMethod(String method)
	{
		this.method = method;
	}

	public String getOnInvokeAction()
	{
		return onInvokeAction;
	}

	public void setOnInvokeAction(String onInvokeAction)
	{
		this.onInvokeAction = onInvokeAction;
	}

	public String getRetrieveRowsCallback()
	{
		return retrieveRowsCallback;
	}

	public void setRetrieveRowsCallback(String retrieveRowsCallback)
	{
		this.retrieveRowsCallback = retrieveRowsCallback;
	}

	public boolean isSaveFilterSort()
	{
		return saveFilterSort.booleanValue();
	}

	public void setSaveFilterSort(Boolean saveFilterSort)
	{
		this.saveFilterSort = saveFilterSort;
	}

	public String getScope()
	{
		return scope;
	}

	public void setScope(String scope)
	{
		this.scope = scope;
	}

	public boolean isShowExports()
	{
		return showExports.booleanValue();
	}

	public void setShowExports(Boolean showExports)
	{
		this.showExports = showExports;
	}

	public boolean isShowPagination()
	{
		return showPagination.booleanValue();
	}

	public void setShowPagination(Boolean showPagination)
	{
		this.showPagination = showPagination;
	}

	public boolean isShowStatusBar()
	{
		return showStatusBar.booleanValue();
	}

	public void setShowStatusBar(Boolean showStatusBar)
	{
		this.showStatusBar = showStatusBar;
	}

	public boolean isShowTitle()
	{
		return showTitle.booleanValue();
	}

	public void setShowTitle(Boolean showTitle)
	{
		this.showTitle = showTitle;
	}

	public boolean isShowTooltips()
	{
		return showTooltips.booleanValue();
	}

	public void setShowTooltips(Boolean showTooltips)
	{
		this.showTooltips = showTooltips;
	}

	public boolean isSortable()
	{
		return sortable.booleanValue();
	}

	public void setSortable(Boolean sortable)
	{
		this.sortable = sortable;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public String getStateAttr()
	{
		return stateAttr;
	}

	public void setStateAttr(String stateAttr)
	{
		this.stateAttr = stateAttr;
	}

	public String getSortRowsCallback()
	{
		return sortRowsCallback;
	}

	public void setSortRowsCallback(String sortRowsCallback)
	{
		this.sortRowsCallback = sortRowsCallback;
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

	public String getTableId()
	{
		return tableId;
	}

	public void setTableId(String tableId)
	{
		this.tableId = tableId;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getTheme()
	{
		return theme;
	}

	public void setTheme(String theme)
	{
		this.theme = theme;
	}

	public String getVar()
	{
		return var;
	}

	public void setVar(String var)
	{
		this.var = var;
	}

	public String getView()
	{
		return view;
	}

	public void setView(String view)
	{
		this.view = view;
	}

	public String getWidth()
	{
		return width;
	}

	public void setWidth(String width)
	{
		this.width = width;
	}

	public int getRowsDisplayed()
	{
		return rowsDisplayed;
	}

	public void setRowsDisplayed(int rowsDisplayed)
	{
		this.rowsDisplayed = rowsDisplayed;
	}

	public int getMedianRowsDisplayed()
	{
		return medianRowsDisplayed;
	}

	public void setMedianRowsDisplayed(int medianRowsDisplayed)
	{
		this.medianRowsDisplayed = medianRowsDisplayed;
	}

	public int getMaxRowsDisplayed()
	{
		return maxRowsDisplayed;
	}

	public void setMaxRowsDisplayed(int maxRowsDisplayed)
	{
		this.maxRowsDisplayed = maxRowsDisplayed;
	}
}
