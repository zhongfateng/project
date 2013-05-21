// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   RowDefaults.java

package org.extremecomponents.table.bean;

import org.apache.commons.lang.StringUtils;
import org.extremecomponents.table.core.Preferences;
import org.extremecomponents.table.core.TableModel;

final class RowDefaults
{

	private RowDefaults()
	{
	}

	static String getHighlightClass(TableModel model, String highlightClass)
	{
		if (StringUtils.isEmpty(highlightClass))
			return model.getPreferences().getPreference("row.highlightClass");
		else
			return highlightClass;
	}

	static Boolean isHighlightRow(TableModel model, Boolean highlightRow)
	{
		if (highlightRow == null)
			return Boolean.valueOf(model.getPreferences().getPreference("row.highlightRow"));
		else
			return highlightRow;
	}
}
