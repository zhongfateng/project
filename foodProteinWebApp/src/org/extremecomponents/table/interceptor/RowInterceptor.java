// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   RowInterceptor.java

package org.extremecomponents.table.interceptor;

import org.extremecomponents.table.bean.Row;
import org.extremecomponents.table.core.TableModel;

public interface RowInterceptor
{

	public abstract void addRowAttributes(TableModel tablemodel, Row row);

	public abstract void modifyRowAttributes(TableModel tablemodel, Row row);
}
