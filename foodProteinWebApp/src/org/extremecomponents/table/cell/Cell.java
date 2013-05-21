// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Cell.java

package org.extremecomponents.table.cell;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.core.TableModel;

public interface Cell
{

	public abstract String getExportDisplay(TableModel tablemodel, Column column);

	public abstract String getHtmlDisplay(TableModel tablemodel, Column column);
}
