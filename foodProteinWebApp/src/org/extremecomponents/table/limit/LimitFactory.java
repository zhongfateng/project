// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   LimitFactory.java

package org.extremecomponents.table.limit;

import java.util.Map;

// Referenced classes of package org.extremecomponents.table.limit:
//			Sort, FilterSet

public interface LimitFactory
{

	public abstract int getCurrentRowsDisplayed(int i, int j);

	public abstract int getPage();

	public abstract Sort getSort();

	public abstract boolean isExported();

	public abstract FilterSet getFilterSet();

	public abstract Map getSortedOrFilteredParameters(String s);
}
