// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   LimitRegistry.java

package org.extremecomponents.table.limit;

import java.util.Map;
import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.core.AbstractRegistry;
import org.extremecomponents.table.state.State;

public final class LimitRegistry extends AbstractRegistry
{

	public LimitRegistry(Context context, String tableId, String prefixWithTableId, String state, String stateAttr)
	{
		this.context = context;
		this.tableId = tableId;
		this.prefixWithTableId = prefixWithTableId;
		this.state = state;
		this.stateAttr = stateAttr;
		autoIncludeParameters = false;
		setParameterMap();
	}

	protected void handleStateInternal(State state1, Map map)
	{
	}
}
