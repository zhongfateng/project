// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CsvView.java

package org.extremecomponents.table.view;

import org.apache.commons.lang.StringUtils;
import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.bean.Export;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.handler.ExportHandler;

// Referenced classes of package org.extremecomponents.table.view:
//			View, ExportViewUtils

public class CsvView
	implements View
{

	public static final String DELIMITER = "delimiter";
	static final String DEFAULT_DELIMITER = ",";
	private StringBuffer plainData;

	public CsvView()
	{
		plainData = new StringBuffer();
	}

	public void beforeBody(TableModel tablemodel)
	{
	}

	public void body(TableModel model, Column column)
	{
		Export export = model.getExportHandler().getCurrentExport();
		String delimiter = export.getAttributeAsString("delimiter");
		if (StringUtils.isBlank(delimiter))
			delimiter = ",";
		String value = ExportViewUtils.parseCSV(column.getCellDisplay());
		plainData.append(value.trim());
		plainData.append(delimiter);
		if (column.isLastColumn())
			plainData.append("\r\n");
	}

	public Object afterBody(TableModel model)
	{
		return plainData.toString();
	}
}
