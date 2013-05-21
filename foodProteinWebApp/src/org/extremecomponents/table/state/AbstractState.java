// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   AbstractState.java

package org.extremecomponents.table.state;

import java.util.*;
import org.extremecomponents.table.context.Context;

// Referenced classes of package org.extremecomponents.table.state:
//			State

abstract class AbstractState
	implements State
{

	AbstractState()
	{
	}

	public void saveParameters(Context context, String tableId, Map parameterMap)
	{
		Map savedAttributes = new HashMap();
		Set keys = parameterMap.keySet();
		String key;
		Object value;
		for (Iterator iter = keys.iterator(); iter.hasNext(); savedAttributes.put(key, value))
		{
			key = (String)iter.next();
			value = parameterMap.get(key);
		}

		context.setSessionAttribute("s_" + tableId, savedAttributes);
	}
}
