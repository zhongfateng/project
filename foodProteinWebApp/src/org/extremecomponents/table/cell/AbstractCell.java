// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   AbstractCell.java

package org.extremecomponents.table.cell;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.view.html.ColumnBuilder;

// Referenced classes of package org.extremecomponents.table.cell:
//			Cell

public abstract class AbstractCell
	implements Cell
{

	public AbstractCell()
	{
	}

	public String getExportDisplay(TableModel model, Column column)
	{
		return getCellValue(model, column);
	}

	public String getHtmlDisplay(TableModel model, Column column)
	{
		ColumnBuilder columnBuilder = new ColumnBuilder(column);
		columnBuilder.tdStart();
		columnBuilder.tdBody(getCellValue(model, column));
		columnBuilder.tdEnd();
		return columnBuilder.toString();
	}

	protected abstract String getCellValue(TableModel tablemodel, Column column);
}
