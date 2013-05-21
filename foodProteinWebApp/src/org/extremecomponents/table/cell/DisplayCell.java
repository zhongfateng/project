// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   DisplayCell.java

package org.extremecomponents.table.cell;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.core.TableModel;

// Referenced classes of package org.extremecomponents.table.cell:
//			AbstractCell

public class DisplayCell extends AbstractCell
{

	public DisplayCell()
	{
	}

	public String getExportDisplay(TableModel model, Column column)
	{
		return column.getPropertyValueAsString();
	}

	protected String getCellValue(TableModel model, Column column)
	{
		return column.getValueAsString();
	}
}
