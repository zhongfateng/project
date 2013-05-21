// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   BottomToolbar2.java

package org.extremecomponents.table.view;

import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.view.html.BuilderUtils;
import org.extremecomponents.table.view.html.StatusBarBuilder;
import org.extremecomponents.table.view.html.ToolbarBuilder;
import org.extremecomponents.table.view.html.TwoColumnRowLayout;
import org.extremecomponents.util.HtmlBuilder;

public class BottomToolbar2 extends TwoColumnRowLayout
{

	public BottomToolbar2(HtmlBuilder html, TableModel model)
	{
		super(html, model);
	}

	protected boolean showLayout(TableModel model)
	{
		boolean showStatusBar = BuilderUtils.showStatusBar(model);
		boolean filterable = BuilderUtils.filterable(model);
		boolean showExports = BuilderUtils.showExports(model);
		boolean showPagination = BuilderUtils.showPagination(model);
		boolean showTitle = BuilderUtils.showTitle(model);
		return showStatusBar || filterable || showExports || showPagination || showTitle;
	}

	protected void columnLeft(HtmlBuilder html, TableModel model)
	{
		boolean showStatusBar = BuilderUtils.showStatusBar(model);
		if (!showStatusBar)
			return;
		html.td(4).styleClass("statusBar").close();
		html.nbsp();
		(new StatusBarBuilder(html, model)).statusMessage();
		boolean showPagination = BuilderUtils.showPagination(model);
		if (showPagination)
		{
			html.nbsp();
			html.nbsp();
			html.nbsp();
			html.nbsp();
			html.nbsp();
			html.nbsp();
			ToolbarBuilder toolbarBuilder = new ToolbarBuilder(html, model);
			toolbarBuilder.firstPageItemAsImage();
			html.nbsp();
			html.nbsp();
			toolbarBuilder.prevPageItemAsImage();
			html.nbsp();
			html.nbsp();
			toolbarBuilder.nextPageItemAsImage();
			html.nbsp();
			html.nbsp();
			toolbarBuilder.lastPageItemAsImage();
			html.nbsp();
			html.nbsp();
			toolbarBuilder.separator();
			html.nbsp();
			html.nbsp();
			toolbarBuilder.rowsDisplayedDroplist();
		}
		html.tdEnd();
	}

	protected void columnRight(HtmlBuilder htmlbuilder, TableModel tablemodel)
	{
	}
}
