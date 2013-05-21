// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CompactToolbar.java

package org.extremecomponents.table.view;

import java.util.Iterator;
import java.util.List;
import org.extremecomponents.table.bean.Export;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.handler.ExportHandler;
import org.extremecomponents.table.handler.TableHandler;
import org.extremecomponents.table.view.html.BuilderUtils;
import org.extremecomponents.table.view.html.StatusBarBuilder;
import org.extremecomponents.table.view.html.ToolbarBuilder;
import org.extremecomponents.table.view.html.TwoColumnRowLayout;
import org.extremecomponents.util.HtmlBuilder;

public class CompactToolbar extends TwoColumnRowLayout
{

	public CompactToolbar(HtmlBuilder html, TableModel model)
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
		{
			return;
		} else
		{
			html.td(4).styleClass("statusBar").close();
			(new StatusBarBuilder(html, model)).statusMessage();
			html.tdEnd();
			return;
		}
	}

	protected void columnRight(HtmlBuilder html, TableModel model)
	{
		boolean filterable = BuilderUtils.filterable(model);
		boolean showPagination = BuilderUtils.showPagination(model);
		boolean showExports = BuilderUtils.showExports(model);
		ToolbarBuilder toolbarBuilder = new ToolbarBuilder(html, model);
		html.td(4).styleClass("compactToolbar").align("right").close();
		html.table(4).border("0").cellPadding("1").cellSpacing("2").close();
		html.tr(5).close();
		if (showPagination)
		{
			html.td(5).close();
			toolbarBuilder.firstPageItemAsImage();
			html.tdEnd();
			html.td(5).close();
			toolbarBuilder.prevPageItemAsImage();
			html.tdEnd();
			html.td(5).close();
			toolbarBuilder.nextPageItemAsImage();
			html.tdEnd();
			html.td(5).close();
			toolbarBuilder.lastPageItemAsImage();
			html.tdEnd();
			html.td(5).close();
			toolbarBuilder.separator();
			html.tdEnd();
			html.td(5).close();
			toolbarBuilder.rowsDisplayedDroplist();
			html.tdEnd();
			if (showExports)
			{
				html.td(5).close();
				toolbarBuilder.separator();
				html.iframe().id(model.getTableHandler().prefixWithTableId() + "iframeExport");
				html.name(model.getTableHandler().prefixWithTableId() + "iframeExport");
				html.width("0");
				html.height("0");
				html.close();
				html.iframeEnd();
				html.tdEnd();
			}
		}
		if (showExports)
		{
			Iterator iterator = model.getExportHandler().getExports().iterator();
			for (Iterator iter = iterator; iter.hasNext(); html.tdEnd())
			{
				html.td(5).close();
				Export export = (Export)iter.next();
				toolbarBuilder.exportItemAsImage(export);
			}

		}
		if (filterable)
		{
			if (showExports || showPagination)
			{
				html.td(5).close();
				toolbarBuilder.separator();
				html.tdEnd();
			}
			html.td(5).close();
			toolbarBuilder.filterItemAsImage();
			html.tdEnd();
			html.td(5).close();
			toolbarBuilder.clearItemAsImage();
			html.tdEnd();
		}
		html.trEnd(5);
		html.tableEnd(4);
		html.tdEnd();
	}
}
