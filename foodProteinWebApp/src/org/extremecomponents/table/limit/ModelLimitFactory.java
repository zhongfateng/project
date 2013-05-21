// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ModelLimitFactory.java

package org.extremecomponents.table.limit;

import org.extremecomponents.table.bean.Table;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.handler.TableHandler;

// Referenced classes of package org.extremecomponents.table.limit:
//			AbstractLimitFactory

public final class ModelLimitFactory extends AbstractLimitFactory
{

	TableModel model;

	public ModelLimitFactory(TableModel model)
	{
		this.model = model;
		tableId = model.getTableHandler().getTable().getTableId();
		prefixWithTableId = model.getTableHandler().prefixWithTableId();
		context = model.getContext();
		registry = model.getRegistry();
		isExported = getExported();
	}

	protected boolean showPagination()
	{
		return model.getTableHandler().getTable().isShowPagination();
	}
}
