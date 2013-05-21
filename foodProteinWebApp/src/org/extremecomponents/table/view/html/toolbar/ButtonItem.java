// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ButtonItem.java

package org.extremecomponents.table.view.html.toolbar;

import org.extremecomponents.table.bean.Table;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.handler.TableHandler;
import org.extremecomponents.util.HtmlBuilder;

// Referenced classes of package org.extremecomponents.table.view.html.toolbar:
//			AbstractItem, ToolbarItem

public class ButtonItem extends AbstractItem
	implements ToolbarItem
{

	private String contents;

	public ButtonItem()
	{
	}

	public String getContents()
	{
		return contents;
	}

	public void setContents(String contents)
	{
		this.contents = contents;
	}

	public void disabled(HtmlBuilder html)
	{
		html.button().disabled().close().append(getContents()).buttonEnd();
	}

	public void enabled(HtmlBuilder html, TableModel model)
	{
		boolean showTooltips = model.getTableHandler().getTable().isShowTooltips();
		if (showTooltips)
		{
			html.button();
			html.title(getTooltip());
			html.onclick(getAction());
			html.styleClass(getStyleClass()).style(getStyle());
			html.onmouseover(getOnmouseover()).onmouseout(getOnmouseout());
			html.close();
			html.append(contents);
			html.buttonEnd();
		} else
		{
			html.button();
			html.onclick(getAction());
			html.styleClass(getStyleClass()).style(getStyle());
			html.onmouseover(getOnmouseover()).onmouseout(getOnmouseout());
			html.close();
			html.append(contents);
			html.buttonEnd();
		}
	}
}
