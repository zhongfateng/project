// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   LimitView.java

package org.extremecomponents.table.view;

import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.util.HtmlBuilder;

// Referenced classes of package org.extremecomponents.table.view:
//			HtmlView, LimitToolbar

public class LimitView extends HtmlView
{

	public LimitView()
	{
	}

	protected void toolbar(HtmlBuilder html, TableModel model)
	{
		(new LimitToolbar(html, model)).layout();
	}
}
