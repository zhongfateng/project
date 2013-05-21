// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   FilterRowsCallback.java

package org.extremecomponents.table.callback;

import java.util.Collection;
import org.extremecomponents.table.core.TableModel;

public interface FilterRowsCallback
{

	public abstract Collection filterRows(TableModel tablemodel, Collection collection)
		throws Exception;
}
