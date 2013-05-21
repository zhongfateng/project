// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TableModelImpl.java

package org.extremecomponents.table.core;

import java.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.bean.*;
import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.handler.*;
import org.extremecomponents.table.limit.*;
import org.extremecomponents.table.view.View;

// Referenced classes of package org.extremecomponents.table.core:
//			TableModel, TableProperties, TableModelUtils, Preferences, 
//			Messages, TableRegistry, TableCache, AutoGenerateColumns, 
//			Registry

public final class TableModelImpl
	implements TableModel
{

	private static Log logger;
	private Context context;
	private Preferences preferences;
	private Messages messages;
	private Registry registry;
	private TableHandler tableHandler;
	private RowHandler rowHandler;
	private ColumnHandler columnHandler;
	private ViewHandler viewHandler;
	private ExportHandler exportHandler;
	private Object currentRowBean;
	private Collection collectionOfBeans;
	private Collection collectionOfFilteredBeans;
	private Collection collectionOfPageBeans;
	private Limit limit;
	private Locale locale;

	public TableModelImpl(Context context)
	{
		this(context, null);
	}

	public TableModelImpl(Context context, String locale)
	{
		tableHandler = new TableHandler(this);
		rowHandler = new RowHandler(this);
		columnHandler = new ColumnHandler(this);
		viewHandler = new ViewHandler(this);
		exportHandler = new ExportHandler(this);
		this.context = context;
		Preferences preferences = new TableProperties();
		preferences.init(context, TableModelUtils.getPreferencesLocation(context));
		this.preferences = preferences;
		this.locale = TableModelUtils.getLocale(context, preferences, locale);
		Messages messages = TableModelUtils.getMessages(this);
		messages.init(context, this.locale);
		this.messages = messages;
	}

	public Context getContext()
	{
		return context;
	}

	public Preferences getPreferences()
	{
		return preferences;
	}

	public Messages getMessages()
	{
		return messages;
	}

	public Registry getRegistry()
	{
		return registry;
	}

	public Table getTableInstance()
	{
		return new Table(this);
	}

	public Export getExportInstance()
	{
		return new Export(this);
	}

	public Row getRowInstance()
	{
		return new Row(this);
	}

	public Column getColumnInstance()
	{
		return new Column(this);
	}

	public void addTable(Table table)
	{
		tableHandler.addTable(table);
		registry = new TableRegistry(this);
		org.extremecomponents.table.limit.LimitFactory limitFactory = new ModelLimitFactory(this);
		limit = new TableLimit(limitFactory);
	}

	public void addExport(Export export)
	{
		exportHandler.addExport(export);
	}

	public void addRow(Row row)
	{
		rowHandler.addRow(row);
	}

	public void addColumn(Column column)
	{
		columnHandler.addAutoGenerateColumn(column);
	}

	public void addColumns(String autoGenerateColumns)
	{
		autoGenerateColumns = TableModelUtils.getAutoGenerateColumnsPreference(this, autoGenerateColumns);
		TableCache.getInstance().getAutoGenerateColumns(autoGenerateColumns).addColumns(this);
	}

	public void addParameter(String name, Object value)
	{
		registry.addParameter(name, value);
	}

	public TableHandler getTableHandler()
	{
		return tableHandler;
	}

	public RowHandler getRowHandler()
	{
		return rowHandler;
	}

	public ColumnHandler getColumnHandler()
	{
		return columnHandler;
	}

	public ViewHandler getViewHandler()
	{
		return viewHandler;
	}

	public ExportHandler getExportHandler()
	{
		return exportHandler;
	}

	public Object getCurrentRowBean()
	{
		return currentRowBean;
	}

	public void setCurrentRowBean(Object bean)
	{
		int rowcount = rowHandler.increaseRowCount();
		currentRowBean = bean;
		context.setPageAttribute("ROWCOUNT", String.valueOf(rowcount));
		context.setPageAttribute(tableHandler.getTable().getVar(), bean);
	}

	public Collection getCollectionOfBeans()
	{
		return collectionOfBeans;
	}

	public void setCollectionOfBeans(Collection collectionOfBeans)
	{
		this.collectionOfBeans = collectionOfBeans;
	}

	public Collection getCollectionOfFilteredBeans()
	{
		return collectionOfFilteredBeans;
	}

	public void setCollectionOfFilteredBeans(Collection collectionOfFilteredBeans)
	{
		this.collectionOfFilteredBeans = collectionOfFilteredBeans;
	}

	public Collection getCollectionOfPageBeans()
	{
		return collectionOfPageBeans;
	}

	public void setCollectionOfPageBeans(Collection collectionOfPageBeans)
	{
		this.collectionOfPageBeans = collectionOfPageBeans;
	}

	public Limit getLimit()
	{
		return limit;
	}

	public void setLimit(Limit limit)
	{
		this.limit = limit;
	}

	public Locale getLocale()
	{
		return locale;
	}

	public Collection execute()
		throws Exception
	{
		Collection rows = TableModelUtils.retrieveRows(this);
		rows = new ArrayList(rows);
		collectionOfBeans = rows;
		rows = TableModelUtils.filterRows(this, rows);
		rows = TableModelUtils.sortRows(this, rows);
		collectionOfFilteredBeans = rows;
		Integer totalRows = getTableHandler().getTotalRows();
		int defaultRowsDisplayed = getTableHandler().getTable().getRowsDisplayed();
		if (totalRows != null)
			limit.setRowAttributes(totalRows.intValue(), defaultRowsDisplayed);
		else
			limit.setRowAttributes(rows.size(), defaultRowsDisplayed);
		if (logger.isDebugEnabled())
			logger.debug(limit.toString());
		rows = TableModelUtils.getCurrentRows(this, rows);
		collectionOfPageBeans = rows;
		viewHandler.setView();
		return rows;
	}

	public void setColumnValues()
		throws Exception
	{
		List columns = columnHandler.getColumns();
		for (Iterator iter = columns.iterator(); iter.hasNext();)
		{
			Column column = (Column)iter.next();
			if ("true".equals(column.getAttribute("isAutoGenerateColumn")))
			{
				String property = column.getProperty();
				Object propertyValue = TableModelUtils.getColumnPropertyValue(currentRowBean, property);
				column.setValue(propertyValue);
				column.setPropertyValue(propertyValue);
				columnHandler.modifyColumnAttributes(column);
				viewHandler.addColumnValueToView(column);
			}
		}

	}

	public Object getViewData()
		throws Exception
	{
		Object viewData = viewHandler.getView().afterBody(this);
		if (limit.isExported())
		{
			context.setRequestAttribute("viewData", viewData);
			context.setRequestAttribute("viewResolver", exportHandler.getCurrentExport().getViewResolver());
			context.setRequestAttribute("efn", exportHandler.getCurrentExport().getFileName());
			return "";
		} else
		{
			return viewData;
		}
	}

	public Object assemble()
		throws Exception
	{
		Iterator iterator = execute().iterator();
		for (Iterator iter = iterator; iter.hasNext(); setColumnValues())
		{
			Object bean = iterator.next();
			setCurrentRowBean(bean);
			getRowHandler().modifyRowAttributes();
		}

		return getViewData();
	}

	static 
	{
		logger = LogFactory.getLog(org.extremecomponents.table.core.TableModel.class);
	}
}
