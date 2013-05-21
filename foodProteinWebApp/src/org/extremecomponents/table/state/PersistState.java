// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   PersistState.java

package org.extremecomponents.table.state;

import java.util.Map;
import org.extremecomponents.table.context.Context;

// Referenced classes of package org.extremecomponents.table.state:
//			AbstractState

public class PersistState extends AbstractState
{

	public PersistState()
	{
	}

	public Map getParameters(Context context, String tableId, String stateAttr)
	{
		return (Map)context.getSessionAttribute("s_" + tableId);
	}
}
