// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   LimitCallback.java

package org.extremecomponents.table.callback;

import java.util.Collection;
import org.extremecomponents.table.bean.Table;
import org.extremecomponents.table.core.RetrievalUtils;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.handler.TableHandler;

// Referenced classes of package org.extremecomponents.table.callback:
//			RetrieveRowsCallback, FilterRowsCallback, SortRowsCallback

public final class LimitCallback
	implements RetrieveRowsCallback, FilterRowsCallback, SortRowsCallback
{

	public static final String TOTAL_ROWS = "totalRows";

	public LimitCallback()
	{
	}

	public Collection retrieveRows(TableModel model)
		throws Exception
	{
		Table table = model.getTableHandler().getTable();
		Collection rows = RetrievalUtils.retrieveCollection(model.getContext(), table.getItems(), table.getScope());
		Object totalRows = RetrievalUtils.retrieve(model.getContext(), "totalRows");
		if (totalRows == null)
			totalRows = (Integer)RetrievalUtils.retrieve(model.getContext(), model.getTableHandler().prefixWithTableId() + "totalRows");
		if (totalRows instanceof Integer)
		{
			model.getTableHandler().setTotalRows((Integer)totalRows);
		} else
		{
			String message = "You need to specify the totalRows (as an Integer) to use the " + getClass().getName() + ".";
			throw new Exception(message);
		}
		return rows;
	}

	public Collection filterRows(TableModel model, Collection rows)
		throws Exception
	{
		return rows;
	}

	public Collection sortRows(TableModel model, Collection rows)
		throws Exception
	{
		return rows;
	}
}
