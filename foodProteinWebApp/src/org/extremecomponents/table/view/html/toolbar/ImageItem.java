// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ImageItem.java

package org.extremecomponents.table.view.html.toolbar;

import org.extremecomponents.table.bean.Table;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.handler.TableHandler;
import org.extremecomponents.util.HtmlBuilder;

// Referenced classes of package org.extremecomponents.table.view.html.toolbar:
//			AbstractItem, ToolbarItem

public class ImageItem extends AbstractItem
	implements ToolbarItem
{

	String image;
	String disabledImage;
	String alt;

	public ImageItem()
	{
	}

	public String getDisabledImage()
	{
		return disabledImage;
	}

	public void setDisabledImage(String disabledImage)
	{
		this.disabledImage = disabledImage;
	}

	public String getImage()
	{
		return image;
	}

	public void setImage(String image)
	{
		this.image = image;
	}

	public void setImage()
	{
	}

	public void disabled(HtmlBuilder html)
	{
		html.img().src(getDisabledImage()).style(getStyle()).alt(getAlt()).xclose();
	}

	public String getAlt()
	{
		return alt;
	}

	public void setAlt(String alt)
	{
		this.alt = alt;
	}

	public void enabled(HtmlBuilder html, TableModel model)
	{
		html.a();
		html.quote();
		html.append(getAction());
		html.quote().close();
		boolean showTooltips = model.getTableHandler().getTable().isShowTooltips();
		if (showTooltips)
			html.img().src(getImage()).style(getStyle()).title(getTooltip()).onmouseover(getOnmouseover()).onmouseout(getOnmouseout()).alt(getAlt()).xclose();
		else
			html.img().src(getImage()).style(getStyle()).onmouseover(getOnmouseover()).onmouseout(getOnmouseout()).alt(getAlt()).xclose();
		html.aEnd();
	}
}
