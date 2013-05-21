// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TableInterceptor.java

package org.extremecomponents.table.interceptor;

import org.extremecomponents.table.bean.Table;
import org.extremecomponents.table.core.TableModel;

public interface TableInterceptor
{

	public abstract void addTableAttributes(TableModel tablemodel, Table table);
}
