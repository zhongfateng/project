// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ExportCsvTag.java

package org.extremecomponents.table.tag;

import org.apache.commons.lang.StringUtils;
import org.extremecomponents.table.bean.Export;
import org.extremecomponents.table.core.TableModel;

// Referenced classes of package org.extremecomponents.table.tag:
//			ExportTag, TagUtils

public class ExportCsvTag extends ExportTag
{

	private String delimiter;

	public ExportCsvTag()
	{
	}

	public String getDelimiter()
	{
		return TagUtils.evaluateExpressionAsString("delimiter", delimiter, this, pageContext);
	}

	public void setDelimiter(String delimiter)
	{
		this.delimiter = delimiter;
	}

	public void addExportAttributes(TableModel model, Export export)
	{
		if (StringUtils.isBlank(export.getView()))
			export.setView("csv");
		if (StringUtils.isBlank(export.getViewResolver()))
			export.setViewResolver("csv");
		if (StringUtils.isBlank(export.getImageName()))
			export.setImageName("csv");
		if (StringUtils.isBlank(export.getText()))
			export.setText("toolbar.text.csv");
		export.addAttribute("delimiter", TagUtils.evaluateExpressionAsString("delimiter", delimiter, this, pageContext));
	}

	public void release()
	{
		delimiter = null;
		super.release();
	}
}
