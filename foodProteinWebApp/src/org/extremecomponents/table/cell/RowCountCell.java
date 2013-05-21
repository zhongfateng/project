// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   RowCountCell.java

package org.extremecomponents.table.cell;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.bean.Row;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.handler.RowHandler;
import org.extremecomponents.table.limit.Limit;

// Referenced classes of package org.extremecomponents.table.cell:
//			AbstractCell

public class RowCountCell extends AbstractCell
{

	public RowCountCell()
	{
	}

	protected String getCellValue(TableModel model, Column column)
	{
		int rowcount = (model.getLimit().getPage() - 1) * model.getLimit().getCurrentRowsDisplayed() + model.getRowHandler().getRow().getRowCount();
		return String.valueOf(rowcount);
	}
}
