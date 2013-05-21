// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   NotifyToPersistState.java

package org.extremecomponents.table.state;

import java.util.Map;
import org.extremecomponents.table.context.Context;

// Referenced classes of package org.extremecomponents.table.state:
//			AbstractState

public class NotifyToPersistState extends AbstractState
{

	public NotifyToPersistState()
	{
	}

	public Map getParameters(Context context, String tableId, String stateAttr)
	{
		String stateAttrValue = context.getParameter(stateAttr);
		if ("true".equalsIgnoreCase(stateAttrValue))
			return (Map)context.getSessionAttribute("s_" + tableId);
		else
			return null;
	}
}
