// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   NumberCell.java

package org.extremecomponents.table.cell;

import org.apache.commons.lang.StringUtils;
import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.util.ExtremeUtils;

// Referenced classes of package org.extremecomponents.table.cell:
//			AbstractCell

public class NumberCell extends AbstractCell
{

	public NumberCell()
	{
	}

	protected String getCellValue(TableModel model, Column column)
	{
		String value = column.getPropertyValueAsString();
		if (StringUtils.isNotBlank(value))
		{
			java.util.Locale locale = model.getLocale();
			value = ExtremeUtils.formatNumber(column.getFormat(), value, locale);
		}
		return value;
	}
}
