// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   View.java

package org.extremecomponents.table.view;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.core.TableModel;

public interface View
{

	public abstract void beforeBody(TableModel tablemodel);

	public abstract void body(TableModel tablemodel, Column column);

	public abstract Object afterBody(TableModel tablemodel);
}
