// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Limit.java

package org.extremecomponents.table.limit;


// Referenced classes of package org.extremecomponents.table.limit:
//			FilterSet, Sort

public interface Limit
{

	public abstract FilterSet getFilterSet();

	public abstract int getRowEnd();

	public abstract int getRowStart();

	public abstract Sort getSort();

	public abstract int getPage();

	public abstract int getCurrentRowsDisplayed();

	public abstract int getTotalRows();

	public abstract boolean isFiltered();

	public abstract boolean isCleared();

	public abstract boolean isSorted();

	public abstract boolean isExported();

	public abstract void setRowAttributes(int i, int j);
}
