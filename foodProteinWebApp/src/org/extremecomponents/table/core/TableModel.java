// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TableModel.java

package org.extremecomponents.table.core;

import java.util.Collection;
import java.util.Locale;
import org.extremecomponents.table.bean.*;
import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.handler.*;
import org.extremecomponents.table.limit.Limit;

// Referenced classes of package org.extremecomponents.table.core:
//			Preferences, Messages, Registry

public interface TableModel
{

	public abstract Context getContext();

	public abstract Preferences getPreferences();

	public abstract Messages getMessages();

	public abstract Registry getRegistry();

	public abstract Table getTableInstance();

	public abstract Export getExportInstance();

	public abstract Row getRowInstance();

	public abstract Column getColumnInstance();

	public abstract void addTable(Table table);

	public abstract void addExport(Export export);

	public abstract void addRow(Row row);

	public abstract void addColumn(Column column);

	public abstract void addColumns(String s);

	public abstract void addParameter(String s, Object obj);

	public abstract TableHandler getTableHandler();

	public abstract RowHandler getRowHandler();

	public abstract ColumnHandler getColumnHandler();

	public abstract ViewHandler getViewHandler();

	public abstract ExportHandler getExportHandler();

	public abstract Object getCurrentRowBean();

	public abstract void setCurrentRowBean(Object obj);

	public abstract Collection getCollectionOfBeans();

	public abstract void setCollectionOfBeans(Collection collection);

	public abstract Collection getCollectionOfFilteredBeans();

	public abstract void setCollectionOfFilteredBeans(Collection collection);

	public abstract Collection getCollectionOfPageBeans();

	public abstract void setCollectionOfPageBeans(Collection collection);

	public abstract Limit getLimit();

	public abstract void setLimit(Limit limit);

	public abstract Locale getLocale();

	public abstract Collection execute()
		throws Exception;

	public abstract void setColumnValues()
		throws Exception;

	public abstract Object getViewData()
		throws Exception;

	public abstract Object assemble()
		throws Exception;
}
