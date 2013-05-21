// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   BottomView.java

package org.extremecomponents.table.view;

import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.view.html.CalcBuilder;
import org.extremecomponents.table.view.html.TableBuilder;
import org.extremecomponents.util.HtmlBuilder;

// Referenced classes of package org.extremecomponents.table.view:
//			AbstractHtmlView, BottomToolbar2, BottomToolbar

public class BottomView extends AbstractHtmlView
{

	public BottomView()
	{
	}

	protected void beforeBodyInternal(TableModel model)
	{
		getTableBuilder().tableStart();
		getTableBuilder().theadStart();
		getTableBuilder().titleRowSpanColumns();
		oldtoolbar(getHtmlBuilder(), getTableModel());
		getTableBuilder().filterRow();
		getTableBuilder().headerRow();
		getTableBuilder().theadEnd();
		getTableBuilder().tbodyStart();
	}

	protected void afterBodyInternal(TableModel model)
	{
		getCalcBuilder().defaultCalcLayout();
		getTableBuilder().tbodyEnd();
		toolbar(getHtmlBuilder(), getTableModel());
		getTableBuilder().tableEnd();
	}

	protected void toolbar(HtmlBuilder html, TableModel model)
	{
		(new BottomToolbar2(html, model)).layout();
	}

	protected void oldtoolbar(HtmlBuilder html, TableModel model)
	{
		(new BottomToolbar(html, model)).layout();
	}
}
