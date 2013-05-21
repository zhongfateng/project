// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CallbackUtils.java

package org.extremecomponents.table.callback;

import java.util.Collection;
import org.extremecomponents.table.bean.Table;
import org.extremecomponents.table.core.RetrievalUtils;
import org.extremecomponents.table.core.TableModel;

/**
 * @deprecated Class CallbackUtils is deprecated
 */

public class CallbackUtils
{

	private CallbackUtils()
	{
	}

	/**
	 * @deprecated Method getItems is deprecated
	 */

	public static Collection getItems(TableModel model, Table table)
		throws Exception
	{
		String items = String.valueOf(table.getItems());
		return RetrievalUtils.retrieveCollection(model.getContext(), items, table.getScope());
	}
}
