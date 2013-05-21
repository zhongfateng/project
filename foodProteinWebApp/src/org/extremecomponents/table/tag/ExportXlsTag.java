// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ExportXlsTag.java

package org.extremecomponents.table.tag;

import org.apache.commons.lang.StringUtils;
import org.extremecomponents.table.bean.Export;
import org.extremecomponents.table.core.TableModel;

// Referenced classes of package org.extremecomponents.table.tag:
//			ExportTag

public class ExportXlsTag extends ExportTag
{

	public ExportXlsTag()
	{
	}

	public void addExportAttributes(TableModel model, Export export)
	{
		if (StringUtils.isBlank(export.getView()))
			export.setView("xls");
		if (StringUtils.isBlank(export.getViewResolver()))
			export.setViewResolver("xls");
		if (StringUtils.isBlank(export.getImageName()))
			export.setImageName("xls");
		if (StringUtils.isBlank(export.getText()))
			export.setText("toolbar.text.xls");
	}
}
