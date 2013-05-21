// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TreeView.java

package org.extremecomponents.tree;

import org.apache.commons.lang.StringUtils;
import org.extremecomponents.table.bean.Table;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.handler.ColumnHandler;
import org.extremecomponents.table.handler.TableHandler;
import org.extremecomponents.table.view.html.BuilderUtils;
import org.extremecomponents.util.HtmlBuilder;

// Referenced classes of package org.extremecomponents.tree:
//			HtmlView, HtmlViewUtils

public final class TreeView extends HtmlView
{

	public TreeView()
	{
	}

	public void beforeBody(TableModel model)
	{
		div().styleClass("eXtremeTable").close();
		titleRow(model);
		tableStart(model);
		statusBar(model);
		filter(model);
		header(model);
	}

	public Object afterBody(TableModel model)
	{
		totals(model);
		tableEnd(model);
		newline().divEnd(); 
		return toString();
	}

	public void titleRow(TableModel model)
	{
		if (StringUtils.isBlank(model.getTableHandler().getTable().getTitle()))
		{
			return;
		} else
		{
			table(0).border("0").cellPadding("0").cellSpacing("0");
			width(model.getTableHandler().getTable().getWidth()).close();
			tr(1).close();
			td(2).styleClass("title").close();
			title(model);
			tdEnd();
			trEnd(1);
			tableEnd(0);
			newline();
			return;
		}
	}

	public void statusBar(TableModel model)
	{
		if (!model.getTableHandler().getTable().isShowStatusBar() && !model.getTableHandler().getTable().isFilterable())
			return;
		tr(1).close();
		td(2).colSpan(String.valueOf(model.getColumnHandler().columnCount())).close();
		table(2).border("0").cellPadding("0").cellSpacing("0").width("100%").close();
		tr(3).close();
		if (model.getTableHandler().getTable().isShowStatusBar())
		{
			td(4).styleClass("statusBar").close();
			String count = model.getTableHandler().getTable().getAttributeAsString("FILTERED_COUNT");
			if ("0".equalsIgnoreCase(count))
				append("There were no results found.");
			else
				append(count + " total results found");
			tdEnd();
		}
		if (model.getTableHandler().getTable().isFilterable())
		{
			td(4).styleClass("filterButtons").close();
			String imageSearchArrow = BuilderUtils.getImage(model, "searchArrow");
			img(imageSearchArrow, "Search");
			nbsp();
			a().quote().append(HtmlViewUtils.filterJavaScript(model)).quote().close();
			String imageSearch = BuilderUtils.getImage(model, "search");
			if (StringUtils.isNotEmpty(imageSearch))
				img(imageSearch, "Search");
			else
				append("&nbsp;Filter&nbsp;");
			aEnd();
			nbsp();
			a().quote().append("javascript:document.forms." + model.getTableHandler().prefixWithTableId() + "filter." + model.getTableHandler().prefixWithTableId() + "f_" + "a" + ".value='" + "ca" + "';document.forms." + model.getTableHandler().prefixWithTableId() + "filter.submit()").quote().close();
			String imageClear = BuilderUtils.getImage(model, "clear");
			if (StringUtils.isNotEmpty(imageClear))
				img(imageClear, "Clear");
			else
				append("&nbsp;Clear&nbsp;");
			aEnd();
			tdEnd();
		}
		trEnd(3);
		tableEnd(2);
		newline();
		tabs(2);
		tdEnd();
		trEnd(1);
		tabs(2);
		newline();
	}
}
