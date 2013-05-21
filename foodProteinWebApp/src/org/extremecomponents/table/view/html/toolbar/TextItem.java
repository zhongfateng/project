// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TextItem.java

package org.extremecomponents.table.view.html.toolbar;

import org.extremecomponents.table.bean.Table;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.handler.TableHandler;
import org.extremecomponents.util.HtmlBuilder;

// Referenced classes of package org.extremecomponents.table.view.html.toolbar:
//			AbstractItem, ToolbarItem

public class TextItem extends AbstractItem
	implements ToolbarItem
{

	private String text;

	public TextItem()
	{
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public void disabled(HtmlBuilder html)
	{
		html.span().close().append(getText()).spanEnd();
	}

	public void enabled(HtmlBuilder html, TableModel model)
	{
		html.a();
		html.quote();
		html.append(getAction());
		html.quote().close();
		boolean showTooltips = model.getTableHandler().getTable().isShowTooltips();
		if (showTooltips)
		{
			html.span().title(getTooltip());
			html.styleClass(getStyleClass()).style(getStyle());
			html.onmouseover(getOnmouseover()).onmouseout(getOnmouseout());
			html.close();
			html.append(getText());
			html.spanEnd();
		} else
		{
			html.span();
			html.styleClass(getStyleClass()).style(getStyle());
			html.onmouseover(getOnmouseover()).onmouseout(getOnmouseout());
			html.close();
			html.append(getText());
			html.spanEnd();
		}
		html.aEnd();
	}
}
