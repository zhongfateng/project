// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ExportPdfTag.java

package org.extremecomponents.table.tag;

import org.apache.commons.lang.StringUtils;
import org.extremecomponents.table.bean.Export;
import org.extremecomponents.table.core.TableModel;

// Referenced classes of package org.extremecomponents.table.tag:
//			ExportTag, TagUtils

public class ExportPdfTag extends ExportTag
{

	private String headerBackgroundColor;
	private String headerTitle;
	private String headerColor;

	public ExportPdfTag()
	{
	}

	public void setHeaderBackgroundColor(String headerBackgroundColor)
	{
		this.headerBackgroundColor = headerBackgroundColor;
	}

	public void setHeaderColor(String headerColor)
	{
		this.headerColor = headerColor;
	}

	public void setHeaderTitle(String headerTitle)
	{
		this.headerTitle = headerTitle;
	}

	public void addExportAttributes(TableModel model, Export export)
	{
		if (StringUtils.isBlank(export.getView()))
			export.setView("pdf");
		if (StringUtils.isBlank(export.getViewResolver()))
			export.setViewResolver("pdf");
		if (StringUtils.isBlank(export.getImageName()))
			export.setImageName("pdf");
		if (StringUtils.isBlank(export.getText()))
			export.setText("toolbar.text.pdf");
		export.addAttribute("headerBackgroundColor", TagUtils.evaluateExpressionAsString("headerBackgroundColor", headerBackgroundColor, this, pageContext));
		export.addAttribute("headerColor", TagUtils.evaluateExpressionAsString("headerColor", headerColor, this, pageContext));
		export.addAttribute("headerTitle", TagUtils.evaluateExpressionAsString("headerTitle", headerTitle, this, pageContext));
	}

	public void release()
	{
		headerBackgroundColor = null;
		headerTitle = null;
		headerColor = null;
		super.release();
	}
}
