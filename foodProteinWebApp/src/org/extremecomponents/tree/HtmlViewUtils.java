// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   HtmlViewUtils.java

package org.extremecomponents.tree;

import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.handler.TableHandler;

public final class HtmlViewUtils
{

	private HtmlViewUtils()
	{
	}

	/**
	 * @deprecated Method filterJavaScript is deprecated
	 */

	public static String filterJavaScript(TableModel model)
	{
		return "javascript:document.forms." + model.getTableHandler().prefixWithTableId() + "filter." + model.getTableHandler().prefixWithTableId() + "f_" + "a" + ".value='" + "fa" + "';document.forms." + model.getTableHandler().prefixWithTableId() + "filter.submit()";
	}

	/**
	 * @deprecated Method paginationJavaScript is deprecated
	 */

	public static String paginationJavaScript(TableModel model)
	{
		return "javascript:document.forms." + model.getTableHandler().prefixWithTableId() + "toolbar.submit()";
	}
}
