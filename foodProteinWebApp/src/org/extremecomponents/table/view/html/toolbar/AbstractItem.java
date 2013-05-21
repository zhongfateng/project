// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   AbstractItem.java

package org.extremecomponents.table.view.html.toolbar;

import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.util.HtmlBuilder;

public abstract class AbstractItem
{

	private String action;
	private String onmouseover;
	private String onmouseout;
	private String styleClass;
	private String style;
	private String tooltip;

	public AbstractItem()
	{
	}

	public String getAction()
	{
		return action;
	}

	public void setAction(String action)
	{
		this.action = action;
	}

	public String getTooltip()
	{
		return tooltip;
	}

	public void setTooltip(String tooltip)
	{
		this.tooltip = tooltip;
	}

	public String getOnmouseout()
	{
		return onmouseout;
	}

	public void setOnmouseout(String onmouseout)
	{
		this.onmouseout = onmouseout;
	}

	public String getOnmouseover()
	{
		return onmouseover;
	}

	public void setOnmouseover(String onmouseover)
	{
		this.onmouseover = onmouseover;
	}

	public String getStyle()
	{
		return style;
	}

	public void setStyle(String style)
	{
		this.style = style;
	}

	public String getStyleClass()
	{
		return styleClass;
	}

	public void setStyleClass(String styleClass)
	{
		this.styleClass = styleClass;
	}

	protected abstract void disabled(HtmlBuilder htmlbuilder);

	protected abstract void enabled(HtmlBuilder htmlbuilder, TableModel tablemodel);
}
