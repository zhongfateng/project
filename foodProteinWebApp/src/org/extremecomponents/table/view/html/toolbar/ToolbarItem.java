// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ToolbarItem.java

package org.extremecomponents.table.view.html.toolbar;

import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.util.HtmlBuilder;

public interface ToolbarItem
{

	public abstract String getAction();

	public abstract void setAction(String s);

	public abstract String getTooltip();

	public abstract void setTooltip(String s);

	public abstract String getOnmouseout();

	public abstract void setOnmouseout(String s);

	public abstract String getOnmouseover();

	public abstract void setOnmouseover(String s);

	public abstract String getStyle();

	public abstract void setStyle(String s);

	public abstract String getStyleClass();

	public abstract void setStyleClass(String s);

	public abstract void disabled(HtmlBuilder htmlbuilder);

	public abstract void enabled(HtmlBuilder htmlbuilder, TableModel tablemodel);
}
