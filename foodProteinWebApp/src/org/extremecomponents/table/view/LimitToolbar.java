// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   LimitToolbar.java

package org.extremecomponents.table.view;

import java.util.Iterator;
import java.util.List;
import org.extremecomponents.table.bean.Export;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.handler.ExportHandler;
import org.extremecomponents.table.handler.TableHandler;
import org.extremecomponents.table.view.html.BuilderUtils;
import org.extremecomponents.table.view.html.TableBuilder;
import org.extremecomponents.table.view.html.ToolbarBuilder;
import org.extremecomponents.table.view.html.TwoColumnTableLayout;
import org.extremecomponents.util.HtmlBuilder;

public class LimitToolbar extends TwoColumnTableLayout
{

	public LimitToolbar(HtmlBuilder html, TableModel model)
	{
		super(html, model);
	}

	protected boolean showLayout(TableModel model)
	{
		boolean showPagination = BuilderUtils.showPagination(model);
		boolean showExports = BuilderUtils.showExports(model);
		return showPagination || showExports;
	}

	protected void columnLeft(HtmlBuilder html, TableModel model)
	{
		html.td(2).close();
		(new TableBuilder(html, model)).title();
		html.tdEnd();
	}

	protected void columnRight(HtmlBuilder html, TableModel model)
	{
		boolean showPagination = BuilderUtils.showPagination(model);
		boolean showExports = BuilderUtils.showExports(model);
		ToolbarBuilder toolbarBuilder = new ToolbarBuilder(html, model);
		html.td(2).align("right").close();
		html.table(2).border("0").cellPadding("0").cellSpacing("1").styleClass("toolbar").close();
		html.tr(3).close();
		if (showPagination)
		{
			html.td(4).close();
			toolbarBuilder.firstPageItemAsImage();
			html.tdEnd();
			html.td(4).close();
			toolbarBuilder.prevPageItemAsImage();
			html.tdEnd();
			html.td(4).close();
			toolbarBuilder.nextPageItemAsImage();
			html.tdEnd();
			html.td(4).close();
			toolbarBuilder.separator();
			html.tdEnd();
			html.td(4).style("width:20px").close();
			html.newline();
			html.tabs(4);
			toolbarBuilder.rowsDisplayedDroplist();
			html.img();
			html.src(BuilderUtils.getImage(model, "rowsDisplayed"));
			html.style("border:0");
			html.alt("Rows Displayed");
			html.xclose();
			html.tdEnd();
			if (showExports)
			{
				html.td(4).close();
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
				html.td(4).close();
				Export export = (Export)iter.next();
				toolbarBuilder.exportItemAsImage(export);
			}

		}
		html.trEnd(3);
		html.tableEnd(2);
		html.newline();
		html.tabs(2);
		html.tdEnd();
	}
}
