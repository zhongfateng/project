// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TableBuilder.java

package org.extremecomponents.table.view.html;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.bean.Table;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.util.HtmlBuilder;

// Referenced classes of package org.extremecomponents.table.view.html:
//			BuilderUtils

public class TableBuilder
{

	private HtmlBuilder html;
	private TableModel model;
	private Table table;

	public TableBuilder(TableModel model)
	{
		this(new HtmlBuilder(), model);
	}

	public TableBuilder(HtmlBuilder html, TableModel model)
	{
		this.html = html;
		this.model = model;
		table = model.getTableHandler().getTable();
	}

	public HtmlBuilder getHtmlBuilder()
	{
		return html;
	}

	protected TableModel getTableModel()
	{
		return model;
	}

	protected Table getTable()
	{
		return table;
	}

	public void tableStart()
	{
		html.table(0);
		id();
		border();
		cellSpacing();
		cellPadding();
		styleClass();
		style();
		width();
		html.close();
	}

	public void tableEnd()
	{
		html.tableEnd(0);
	}

	public void id()
	{
		html.id(model.getTableHandler().prefixWithTableId() + "table");
	}

	public void border()
	{
		String border = table.getBorder();
		html.border(border);
	}

	public void cellSpacing()
	{
		String cellSpacing = table.getCellspacing();
		html.cellSpacing(cellSpacing);
	}

	public void cellPadding()
	{
		String cellPadding = table.getCellpadding();
		html.cellPadding(cellPadding);
	}

	public void styleClass()
	{
		String styleClass = table.getStyleClass();
		html.styleClass(styleClass);
	}

	public void style()
	{
		String style = table.getStyle();
		html.style(style);
	}

	public void width()
	{
		String width = table.getWidth();
		html.width(width);
	}

	public void title()
	{
		boolean showTitle = BuilderUtils.showTitle(model);
		if (showTitle)
		{
			String title = model.getTableHandler().getTable().getTitle();
			if (StringUtils.isNotBlank(title))
				html.span().styleClass("title").close().append(title).spanEnd();
		}
	}

	public void titleRowSpanColumns()
	{
		boolean showTitle = BuilderUtils.showTitle(model);
		if (showTitle)
		{
			String title = model.getTableHandler().getTable().getTitle();
			if (StringUtils.isNotBlank(title))
			{
				int columnCount = model.getColumnHandler().columnCount();
				html.tr(1).styleClass("titleRow").close();
				html.td(2).colSpan( String.valueOf(columnCount)).close();
				html.span().close().append(title).spanEnd();
				html.tdEnd();
				html.trEnd(1);
			}
		}
	}

	public void headerRow()
	{
		html.tr(1).close();
		List columns = model.getColumnHandler().getHeaderColumns();
		Column column;
		for (Iterator iter = columns.iterator(); iter.hasNext(); html.append(column.getCellDisplay()))
			column = (Column)iter.next();

		html.trEnd(1);
	}

	public void filterRow()
	{
		if (!model.getTableHandler().getTable().isFilterable())
			return;
		html.tr(1).styleClass("filter").close();
		List columns = model.getColumnHandler().getFilterColumns();
		Column column;
		for (Iterator iter = columns.iterator(); iter.hasNext(); html.append(column.getCellDisplay()))
			column = (Column)iter.next();

		html.trEnd(1);
	}

	public void theadStart()
	{
		html.thead(1).close();
	}

	public void theadEnd()
	{
		html.theadEnd(1);
	}

	public void tbodyStart()
	{
		html.tbody(1).styleClass("tableBody").close();
	}

	public void tbodyEnd()
	{
		html.tbodyEnd(1);
	}

	public void themeStart()
	{
		html.newline();
		String theme = model.getTableHandler().getTable().getTheme();
		html.div().styleClass(theme);
		html.close();
	}

	public void themeEnd()
	{
		html.newline();
		html.divEnd();
	}

	public String toString()
	{
		return html.toString();
	}
}
