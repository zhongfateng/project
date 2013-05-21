// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   DefaultStatusBar.java

package org.extremecomponents.table.view;

import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.view.html.BuilderUtils;
import org.extremecomponents.table.view.html.StatusBarBuilder;
import org.extremecomponents.table.view.html.ToolbarBuilder;
import org.extremecomponents.table.view.html.TwoColumnRowLayout;
import org.extremecomponents.util.HtmlBuilder;

public class DefaultStatusBar extends TwoColumnRowLayout
{

	public DefaultStatusBar(HtmlBuilder html, TableModel model)
	{
		super(html, model);
	}

	protected boolean showLayout(TableModel model)
	{
		boolean showStatusBar = BuilderUtils.showStatusBar(model);
		boolean filterable = BuilderUtils.filterable(model);
		return showStatusBar || filterable;
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
		if (!filterable)
		{
			return;
		} else
		{
			html.td(4).styleClass("filterButtons").close();
			html.img();
			html.src(BuilderUtils.getImage(model, "filterArrow"));
			html.style("border:0");
			html.alt("Arrow");
			html.xclose();
			html.nbsp();
			ToolbarBuilder toolbarBuilder = new ToolbarBuilder(html, model);
			toolbarBuilder.filterItemAsImage();
			html.nbsp();
			toolbarBuilder.clearItemAsImage();
			html.tdEnd();
			return;
		}
	}
}
