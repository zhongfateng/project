// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SelectAllHeaderCell.java

package org.extremecomponents.table.cell;

import org.apache.commons.lang.StringUtils;
import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.util.HtmlBuilder;

// Referenced classes of package org.extremecomponents.table.cell:
//			Cell

public class SelectAllHeaderCell
	implements Cell
{

	public SelectAllHeaderCell()
	{
	}

	public String getExportDisplay(TableModel model, Column column)
	{
		return column.getTitle();
	}

	public String getHtmlDisplay(TableModel model, Column column)
	{
		HtmlBuilder html = new HtmlBuilder();
		html.td(2);
		if (StringUtils.isNotEmpty(column.getHeaderClass()))
			html.styleClass(column.getHeaderClass());
		if (StringUtils.isNotEmpty(column.getHeaderStyle()))
			html.style(column.getHeaderStyle());
		if (StringUtils.isNotEmpty(column.getWidth()))
			html.width(column.getWidth());
		html.close();
		String controlName = column.getAlias() + "_selector";
		String selectableControlName = column.getAlias();
		html.input("checkbox");
		html.id(controlName);
		html.name(controlName);
		html.title("(Un)Select All");
		html.onclick("for(i = 0; i < document.getElementsByName('" + selectableControlName + "').length; i++)document.getElementsByName('" + selectableControlName + "').item(i).checked=document.getElementById('" + controlName + "').checked;");
		html.close();
		html.tdEnd();
		return html.toString();
	}
}
