// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TwoColumnTableLayout.java

package org.extremecomponents.table.view.html;

import org.extremecomponents.table.bean.Table;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.handler.TableHandler;
import org.extremecomponents.util.HtmlBuilder;

public abstract class TwoColumnTableLayout
{

	private HtmlBuilder html;
	private TableModel model;

	public TwoColumnTableLayout(HtmlBuilder html, TableModel model)
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
			html.table(0).border("0").cellPadding("0").cellSpacing("0");
			Table table = model.getTableHandler().getTable();
			html.width(table.getWidth()).close();
			html.tr(1).close();
			columnLeft(html, model);
			columnRight(html, model);
			html.trEnd(1);
			html.tableEnd(0);
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
