// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   HtmlView.java

package org.extremecomponents.table.view;

import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.view.html.CalcBuilder;
import org.extremecomponents.table.view.html.TableBuilder;
import org.extremecomponents.util.HtmlBuilder;

// Referenced classes of package org.extremecomponents.table.view:
//			AbstractHtmlView, CompactToolbar

public class HtmlView extends AbstractHtmlView
{

	public HtmlView()
	{
	}

	protected void beforeBodyInternal(TableModel model)
	{
		getTableBuilder().tableStart();
		getTableBuilder().theadStart();
		getTableBuilder().titleRowSpanColumns();
		toolbar(getHtmlBuilder(), getTableModel());
		getTableBuilder().filterRow();
		getTableBuilder().headerRow();
		getTableBuilder().theadEnd();
		getTableBuilder().tbodyStart();
	}

	protected void afterBodyInternal(TableModel model)
	{
		getCalcBuilder().defaultCalcLayout();
		getTableBuilder().tbodyEnd();
		getTableBuilder().tableEnd();
	}

	protected void toolbar(HtmlBuilder html, TableModel model)
	{
		(new CompactToolbar(html, model)).layout();
	}
}
