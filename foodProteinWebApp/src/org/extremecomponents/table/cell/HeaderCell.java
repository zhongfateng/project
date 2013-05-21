// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   HeaderCell.java

package org.extremecomponents.table.cell;

import org.apache.commons.lang.StringUtils;
import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.bean.Table;
import org.extremecomponents.table.core.*;
import org.extremecomponents.table.handler.TableHandler;
import org.extremecomponents.table.limit.Limit;
import org.extremecomponents.table.limit.Sort;
import org.extremecomponents.table.view.html.BuilderUtils;
import org.extremecomponents.table.view.html.TableActions;
import org.extremecomponents.util.HtmlBuilder;

// Referenced classes of package org.extremecomponents.table.cell:
//			Cell

public class HeaderCell
	implements Cell
{

	public HeaderCell()
	{
	}

	public String getExportDisplay(TableModel model, Column column)
	{
		return column.getTitle();
	}

	public String getHtmlDisplay(TableModel model, Column column)
	{
		HtmlBuilder html = new HtmlBuilder();
		String headerClass = null;
		String sortImage = null;
		String sortOrder = null;
		headerClass = column.getHeaderClass();
		if (TableModelUtils.isSorted(model, column.getAlias()))
		{
			sortOrder = model.getLimit().getSort().getSortOrder();
			if (sortOrder.equals("default"))
				sortOrder = "asc";
			else
			if (sortOrder.equals("asc"))
			{
				headerClass = model.getPreferences().getPreference("table.headerSortClass");
				sortImage = BuilderUtils.getImage(model, "sortAsc");
				sortOrder = "desc";
			} else
			if (sortOrder.equals("desc"))
			{
				headerClass = model.getPreferences().getPreference("table.headerSortClass");
				sortImage = BuilderUtils.getImage(model, "sortDesc");
				sortOrder = "default";
			}
		} else
		{
			sortOrder = "asc";
		}
		buildHeaderHtml(html, model, column, headerClass, sortImage, sortOrder);
		return html.toString();
	}

	protected void buildHeaderHtml(HtmlBuilder html, TableModel model, Column column, String headerClass, String sortImage, String sortOrder)
	{
		html.td(2);
		if (StringUtils.isNotEmpty(headerClass))
			html.styleClass(headerClass);
		if (StringUtils.isNotEmpty(column.getHeaderStyle()))
			html.style(column.getHeaderStyle());
		if (StringUtils.isNotEmpty(column.getWidth()))
			html.width(column.getWidth());
		if (column.isSortable())
		{
			if (sortOrder.equals("asc"))
			{
				html.onmouseover("this.className='tableHeaderSort';this.style.cursor='pointer'");
				if (StringUtils.isNotEmpty(headerClass))
					html.onmouseout("this.className='" + headerClass + "';this.style.cursor='default'");
				else
					html.onmouseout("this.className='tableHeader';this.style.cursor='default'");
			}
			if (sortOrder.equals("default") || sortOrder.equals("desc"))
			{
				html.onmouseover("this.style.cursor='pointer'");
				html.onmouseout("this.style.cursor='default'");
			}
			html.onclick((new TableActions(model)).getSortAction(column, sortOrder));
			boolean showTooltips = model.getTableHandler().getTable().isShowTooltips();
			if (showTooltips)
			{
				String headercellTooltip = model.getMessages().getMessage("column.headercell.tooltip.sort");
				html.title(headercellTooltip + " " + column.getTitle());
			}
		}
		html.close();
		html.append(column.getTitle());
		if (column.isSortable() && StringUtils.isNotEmpty(sortImage))
		{
			html.nbsp();
			html.img();
			html.src(sortImage);
			html.style("border:0");
			html.alt("Arrow");
			html.xclose();
		}
		html.tdEnd();
	}
}
