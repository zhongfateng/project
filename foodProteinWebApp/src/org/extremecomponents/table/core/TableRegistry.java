// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TableRegistry.java

package org.extremecomponents.table.core;

import java.util.Map;
import org.extremecomponents.table.bean.Table;
import org.extremecomponents.table.handler.TableHandler;
import org.extremecomponents.table.state.State;

// Referenced classes of package org.extremecomponents.table.core:
//			AbstractRegistry, TableModel

public final class TableRegistry extends AbstractRegistry
{

	public TableRegistry(TableModel model)
	{
		context = model.getContext();
		tableId = model.getTableHandler().getTable().getTableId();
		prefixWithTableId = model.getTableHandler().prefixWithTableId();
		state = model.getTableHandler().getTable().getState();
		stateAttr = model.getTableHandler().getTable().getStateAttr();
		autoIncludeParameters = model.getTableHandler().getTable().isAutoIncludeParameters();
		setParameterMap();
	}

	protected void handleStateInternal(State state, Map tableParameterMap)
	{
		state.saveParameters(context, tableId, tableParameterMap);
	}
}
