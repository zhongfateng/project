// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   FilterCell.java

package org.extremecomponents.table.cell;

import org.apache.commons.lang.StringUtils;
import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.handler.TableHandler;
import org.extremecomponents.table.view.html.TableActions;
import org.extremecomponents.util.HtmlBuilder;

// Referenced classes of package org.extremecomponents.table.cell:
//			Cell

public class FilterCell
	implements Cell
{

	public FilterCell()
	{
	}

	public String getExportDisplay(TableModel model, Column column)
	{
		return null;
	}

	public String getHtmlDisplay(TableModel model, Column column)
	{
		HtmlBuilder html = new HtmlBuilder();
		html.td(2);
		if (StringUtils.isNotEmpty(column.getFilterClass()))
			html.styleClass(column.getFilterClass());
		if (StringUtils.isNotEmpty(column.getFilterStyle()))
			html.style(column.getFilterStyle());
		if (StringUtils.isNotEmpty(column.getWidth()))
			html.width(column.getWidth());
		html.close();
		if (!column.isFilterable())
			html.append("");
		else
			html.append(input(model, column));
		html.tdEnd();
		return html.toString();
	}

	private String input(TableModel model, Column column)
	{
		HtmlBuilder html = new HtmlBuilder();
		html.input("text");
		html.name(model.getTableHandler().prefixWithTableId() + "f_" + column.getAlias());
		String value = column.getValueAsString();
		if (StringUtils.isNotBlank(value))
			html.value(value);
		StringBuffer onkeypress = new StringBuffer();
		onkeypress.append("if (event.keyCode == 13) {");
		onkeypress.append((new TableActions(model)).getFilterAction());
		onkeypress.append("}");
		html.onkeypress(onkeypress.toString());
		html.xclose();
		return html.toString();
	}
}
