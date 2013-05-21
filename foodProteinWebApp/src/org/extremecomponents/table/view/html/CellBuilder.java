// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CellBuilder.java

package org.extremecomponents.table.view.html;

import org.apache.commons.lang.StringUtils;
import org.extremecomponents.table.bean.Column;
import org.extremecomponents.util.HtmlBuilder;

/**
 * @deprecated Class CellBuilder is deprecated
 */

public class CellBuilder
{

	private CellBuilder()
	{
	}

	/**
	 * @deprecated Method tdStart is deprecated
	 */

	public static void tdStart(HtmlBuilder html, Column column)
	{
		html.td(2);
		styleClass(html, column);
		style(html, column);
		width(html, column);
		html.close();
	}

	/**
	 * @deprecated Method tdEnd is deprecated
	 */

	public static void tdEnd(HtmlBuilder html)
	{
		html.tdEnd();
	}

	/**
	 * @deprecated Method style is deprecated
	 */

	public static void style(HtmlBuilder html, Column column)
	{
		String style = column.getStyle();
		html.style(style);
	}

	/**
	 * @deprecated Method styleClass is deprecated
	 */

	public static void styleClass(HtmlBuilder html, Column column)
	{
		String styleClass = column.getStyleClass();
		html.styleClass(styleClass);
	}

	/**
	 * @deprecated Method width is deprecated
	 */

	public static void width(HtmlBuilder html, Column column)
	{
		String width = column.getWidth();
		html.width(width);
	}

	/**
	 * @deprecated Method tdBody is deprecated
	 */

	public static void tdBody(HtmlBuilder html, String value)
	{
		if (StringUtils.isNotBlank(value))
			html.append(value);
		else
			html.nbsp();
	}
}
