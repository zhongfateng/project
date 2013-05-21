// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ExportInterceptor.java

package org.extremecomponents.table.interceptor;

import org.extremecomponents.table.bean.Export;
import org.extremecomponents.table.core.TableModel;

public interface ExportInterceptor
{

	public abstract void addExportAttributes(TableModel tablemodel, Export export);
}
