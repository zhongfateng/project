// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ToolbarItemUtils.java

package org.extremecomponents.table.view.html.toolbar;

import org.extremecomponents.table.bean.Export;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.limit.Limit;
import org.extremecomponents.table.view.html.BuilderUtils;
import org.extremecomponents.table.view.html.TableActions;
import org.extremecomponents.util.HtmlBuilder;

// Referenced classes of package org.extremecomponents.table.view.html.toolbar:
//			ToolbarItem

public final class ToolbarItemUtils
{

	public ToolbarItemUtils()
	{
	}

	public static void buildFirstPage(HtmlBuilder html, TableModel model, ToolbarItem item)
	{
		String action = (new TableActions(model)).getPageAction(1);
		item.setAction(action);
		int page = model.getLimit().getPage();
		if (!BuilderUtils.isFirstPageEnabled(page))
			item.disabled(html);
		else
			item.enabled(html, model);
	}

	public static void buildPrevPage(HtmlBuilder html, TableModel model, ToolbarItem item)
	{
		int page = model.getLimit().getPage();
		String action = (new TableActions(model)).getPageAction(page - 1);
		item.setAction(action);
		if (!BuilderUtils.isPrevPageEnabled(page))
			item.disabled(html);
		else
			item.enabled(html, model);
	}

	public static void buildNextPage(HtmlBuilder html, TableModel model, ToolbarItem item)
	{
		int page = model.getLimit().getPage();
		String action = (new TableActions(model)).getPageAction(page + 1);
		item.setAction(action);
		int totalPages = BuilderUtils.getTotalPages(model);
		if (!BuilderUtils.isNextPageEnabled(page, totalPages))
			item.disabled(html);
		else
			item.enabled(html, model);
	}

	public static void buildLastPage(HtmlBuilder html, TableModel model, ToolbarItem item)
	{
		int totalPages = BuilderUtils.getTotalPages(model);
		String action = (new TableActions(model)).getPageAction(totalPages);
		item.setAction(action);
		int page = model.getLimit().getPage();
		if (!BuilderUtils.isLastPageEnabled(page, totalPages))
			item.disabled(html);
		else
			item.enabled(html, model);
	}

	public static void buildFilter(HtmlBuilder html, TableModel model, ToolbarItem item)
	{
		item.setAction((new TableActions(model)).getFilterAction());
		item.enabled(html, model);
	}

	public static void buildClear(HtmlBuilder html, TableModel model, ToolbarItem item)
	{
		item.setAction((new TableActions(model)).getClearAction());
		item.enabled(html, model);
	}

	public static void buildExport(HtmlBuilder html, TableModel model, ToolbarItem item, Export export)
	{
		String action = (new TableActions(model)).getExportAction(export.getView(), export.getFileName());
		item.setAction(action);
		item.enabled(html, model);
	}
}
