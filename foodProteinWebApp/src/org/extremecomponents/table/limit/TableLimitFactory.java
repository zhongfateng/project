// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TableLimitFactory.java

package org.extremecomponents.table.limit;

import org.apache.commons.lang.StringUtils;
import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.core.*;

// Referenced classes of package org.extremecomponents.table.limit:
//			AbstractLimitFactory, LimitRegistry

public final class TableLimitFactory extends AbstractLimitFactory
{

	public TableLimitFactory(Context context)
	{
		this(context, "ec");
	}

	public TableLimitFactory(Context context, String tableId)
	{
		this(context, tableId, "default", null);
	}

	public TableLimitFactory(Context context, String tableId, String state, String stateAttr)
	{
		this.tableId = tableId;
		String prefixWithTableId = tableId + "_";
		this.prefixWithTableId = prefixWithTableId;
		Preferences preferences = new TableProperties();
		preferences.init(null, TableModelUtils.getPreferencesLocation(context));
		state = preferences.getPreference("table.state." + state);
		if (StringUtils.isBlank(stateAttr))
			stateAttr = preferences.getPreference("table.stateAttr");
		this.context = context;
		registry = new LimitRegistry(context, tableId, prefixWithTableId, state, stateAttr);
		isExported = getExported();
	}

	protected boolean showPagination()
	{
		return true;
	}
}
