// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TreeRegistryUtils.java

package org.extremecomponents.tree;

import java.net.URLEncoder;
import java.util.*;
import org.extremecomponents.table.core.Registry;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.handler.TableHandler;
import org.extremecomponents.util.HtmlBuilder;

public class TreeRegistryUtils
{

	public TreeRegistryUtils()
	{
	}

	/**
	 * @deprecated Method getHiddenFields is deprecated
	 */

	public static String getHiddenFields(TableModel model, String parameter)
	{
		HtmlBuilder html = new HtmlBuilder();
		Set keys = model.getRegistry().getParameterMap().keySet();
		for (Iterator iter = keys.iterator(); iter.hasNext();)
		{
			String name = (String)iter.next();
			if (parameter == null && !name.startsWith(model.getTableHandler().prefixWithTableId()) || name.startsWith(model.getTableHandler().prefixWithTableId() + parameter))
			{
				String values[] = (String[])model.getRegistry().getParameterMap().get(name);
				if (values == null || values.length == 0)
				{
					html.newline();
					html.input("hidden").name(name).xclose();
				} else
				{
					for (int i = 0; i < values.length; i++)
					{
						html.newline();
						html.input("hidden").name(name).value(values[i]).xclose();
					}

				}
			}
		}

		return html.toString();
	}

	/**
	 * @deprecated Method getParameterString is deprecated
	 */

	public static String getParameterString(TableModel model, String parameter)
	{
		HtmlBuilder html = new HtmlBuilder();
		Set keys = model.getRegistry().getParameterMap().keySet();
		for (Iterator iter = keys.iterator(); iter.hasNext();)
		{
			String name = (String)iter.next();
			if (parameter == null && !name.startsWith(model.getTableHandler().prefixWithTableId()) || name.startsWith(model.getTableHandler().prefixWithTableId() + parameter))
			{
				String values[] = (String[])model.getRegistry().getParameterMap().get(name);
				if (values == null || values.length == 0)
				{
					html.ampersand().append(name).equals();
				} else
				{
					for (int i = 0; i < values.length; i++)
					{
						String encodedValue = URLEncoder.encode(values[i]);
						html.append("&amp;").append(name).equals().append(encodedValue);
					}

				}
			}
		}

		return html.toString();
	}

	/**
	 * @deprecated Method getURLParameterString is deprecated
	 */

	public static String getURLParameterString(TableModel model, boolean filter, boolean sort, boolean page, boolean rowsDisplayed)
	{
		StringBuffer sb = new StringBuffer();
		if (filter)
			sb.append(getParameterString(model, "f_"));
		if (sort)
			sb.append(getParameterString(model, "s_"));
		if (page)
			sb.append(getParameterString(model, "p"));
		if (rowsDisplayed)
			sb.append(getParameterString(model, "crd"));
		sb.append(getParameterString(model, null));
		return sb.toString();
	}

	/**
	 * @deprecated Method getFormHiddenFields is deprecated
	 */

	public static String getFormHiddenFields(TableModel model, boolean filter, boolean sort, boolean page, boolean rowsDisplayed)
	{
		StringBuffer sb = new StringBuffer();
		if (filter)
			sb.append(getHiddenFields(model, "f_"));
		if (sort)
			sb.append(getHiddenFields(model, "s_"));
		if (page)
			sb.append(getHiddenFields(model, "p"));
		if (rowsDisplayed)
			sb.append(getHiddenFields(model, "crd"));
		sb.append(getHiddenFields(model, null));
		return sb.toString();
	}
}
