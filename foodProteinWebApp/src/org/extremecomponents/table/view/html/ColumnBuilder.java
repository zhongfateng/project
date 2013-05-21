// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ColumnBuilder.java

package org.extremecomponents.table.view.html;

import org.apache.commons.lang.StringUtils;
import org.extremecomponents.table.bean.Column;
import org.extremecomponents.util.HtmlBuilder;

public class ColumnBuilder
{

	private HtmlBuilder html;
	private Column column;

	public ColumnBuilder(Column column)
	{
		this(new HtmlBuilder(), column);
	}

	public ColumnBuilder(HtmlBuilder html, Column column)
	{
		this.html = html;
		this.column = column;
	}

	public HtmlBuilder getHtmlBuilder()
	{
		return html;
	}

	protected Column getColumn()
	{
		return column;
	}

	public void tdStart()
	{
		html.td(2);
		styleClass();
		style();
		width();
		html.close();
	}

	public void tdEnd()
	{
		html.tdEnd();
	}

	public void style()
	{
		String style = column.getStyle();
		html.style(style);
	}

	public void styleClass()
	{
		String styleClass = column.getStyleClass();
		html.styleClass(styleClass);
	}

	public void width()
	{
		String width = column.getWidth();
		html.width(width);
	}

	public void tdBody(String value)
	{
		if (StringUtils.isNotBlank(value))
			html.append(value);
		else
			html.nbsp();
	}

	public String toString()
	{
		return html.toString();
	}
}
