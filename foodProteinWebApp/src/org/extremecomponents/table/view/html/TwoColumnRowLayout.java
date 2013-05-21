// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TwoColumnRowLayout.java

package org.extremecomponents.table.view.html;

import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.handler.ColumnHandler;
import org.extremecomponents.util.HtmlBuilder;

public abstract class TwoColumnRowLayout
{

	private HtmlBuilder html;
	private TableModel model;

	public TwoColumnRowLayout(HtmlBuilder html, TableModel model)
	{
		this.html = html;
		this.model = model;
	}

	protected HtmlBuilder getHtmlBuilder()
	{
		return html;
	}

	protected TableModel getTableModel()
	{
		return model;
	}

	public void layout()
	{
		if (!showLayout(model))
		{
			return;
		} else
		{
			html.tr(1).style("padding: 0px;").close();
			html.td(2).colSpan(String.valueOf(model.getColumnHandler().columnCount())).close();
			html.table(2).border("0").cellPadding("0").cellSpacing("0").width("100%").close();
			html.tr(3).close();
			columnLeft(html, model);
			columnRight(html, model);
			html.trEnd(3);
			html.tableEnd(2);
			html.newline();
			html.tabs(2);
			html.tdEnd();
			html.trEnd(1);
			html.tabs(2);
			html.newline();
			return;
		}
	}

	public String toString()
	{
		return html.toString();
	}

	protected abstract boolean showLayout(TableModel tablemodel);

	protected abstract void columnLeft(HtmlBuilder htmlbuilder, TableModel tablemodel);

	protected abstract void columnRight(HtmlBuilder htmlbuilder, TableModel tablemodel);
}
