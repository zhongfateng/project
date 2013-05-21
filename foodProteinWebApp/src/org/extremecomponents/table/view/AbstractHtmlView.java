// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   AbstractHtmlView.java

package org.extremecomponents.table.view;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.bean.Table;
import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.handler.TableHandler;
import org.extremecomponents.table.view.html.CalcBuilder;
import org.extremecomponents.table.view.html.FormBuilder;
import org.extremecomponents.table.view.html.RowBuilder;
import org.extremecomponents.table.view.html.TableBuilder;
import org.extremecomponents.util.HtmlBuilder;

// Referenced classes of package org.extremecomponents.table.view:
//			View

public abstract class AbstractHtmlView
	implements View
{

	private HtmlBuilder html;
	private TableModel model;
	private FormBuilder formBuilder;
	private boolean bufferView;
	private TableBuilder tableBuilder;
	private RowBuilder rowBuilder;
	private CalcBuilder calcBuilder;

	public AbstractHtmlView()
	{
	}

	protected HtmlBuilder getHtmlBuilder()
	{
		return html;
	}

	protected TableModel getTableModel()
	{
		return model;
	}

	protected TableBuilder getTableBuilder()
	{
		return tableBuilder;
	}

	protected void setTableBuilder(TableBuilder tableBuilder)
	{
		this.tableBuilder = tableBuilder;
	}

	public RowBuilder getRowBuilder()
	{
		return rowBuilder;
	}

	protected void setRowBuilder(RowBuilder rowBuilder)
	{
		this.rowBuilder = rowBuilder;
	}

	public CalcBuilder getCalcBuilder()
	{
		return calcBuilder;
	}

	protected void setCalcBuilder(CalcBuilder calcBuilder)
	{
		this.calcBuilder = calcBuilder;
	}

	public final void beforeBody(TableModel model)
	{
		this.model = model;
		bufferView = model.getTableHandler().getTable().isBufferView();
		if (bufferView)
			html = new HtmlBuilder();
		else
			html = new HtmlBuilder(model.getContext().getWriter());
		formBuilder = new FormBuilder(html, model);
		init(html, model);
		formBuilder.formStart();
		tableBuilder.themeStart();
		beforeBodyInternal(model);
	}

	public void body(TableModel model, Column column)
	{
		if (column.isFirstColumn())
			rowBuilder.rowStart();
		html.append(column.getCellDisplay());
		if (column.isLastColumn())
			rowBuilder.rowEnd();
	}

	public final Object afterBody(TableModel model)
	{
		afterBodyInternal(model);
		tableBuilder.themeEnd();
		formBuilder.formEnd();
		if (bufferView)
			return html.toString();
		else
			return "";
	}

	protected void init(HtmlBuilder html, TableModel model)
	{
		setTableBuilder(new TableBuilder(html, model));
		setRowBuilder(new RowBuilder(html, model));
		setCalcBuilder(new CalcBuilder(html, model));
	}

	protected abstract void beforeBodyInternal(TableModel tablemodel);

	protected abstract void afterBodyInternal(TableModel tablemodel);
}
