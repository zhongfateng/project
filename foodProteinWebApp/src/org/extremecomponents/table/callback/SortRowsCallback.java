// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SortRowsCallback.java

package org.extremecomponents.table.callback;

import java.util.Collection;
import org.extremecomponents.table.core.TableModel;

public interface SortRowsCallback
{

	public abstract Collection sortRows(TableModel tablemodel, Collection collection)
		throws Exception;
}
