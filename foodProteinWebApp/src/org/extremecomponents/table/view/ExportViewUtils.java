// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ExportViewUtils.java

package org.extremecomponents.table.view;

import org.apache.commons.lang.StringUtils;

public class ExportViewUtils
{

	public ExportViewUtils()
	{
	}

	public static String parseXLS(String value)
	{
		if (StringUtils.isBlank(value))
		{
			return "";
		} else
		{
			value = replaceNonBreakingSpaces(value);
			return value;
		}
	}

	public static String parsePDF(String value)
	{
		if (StringUtils.isBlank(value))
		{
			return "";
		} else
		{
			value = replaceNonBreakingSpaces(value);
			value = escapeChars(value);
			return value;
		}
	}

	public static String parseCSV(String value)
	{
		if (StringUtils.isBlank(value))
		{
			return "";
		} else
		{
			value = replaceNonBreakingSpaces(value);
			return value;
		}
	}

	public static String replaceNonBreakingSpaces(String value)
	{
		if (StringUtils.isBlank(value))
			return "";
		if (StringUtils.contains(value, "&nbsp;"))
			value = StringUtils.replace(value, "&nbsp;", "");
		return value;
	}

	public static String escapeChars(String value)
	{
		if (StringUtils.isBlank(value))
			return "";
		if (StringUtils.contains(value, "&"))
			value = StringUtils.replace(value, "&", "&#38;");
		if (StringUtils.contains(value, ">"))
			value = StringUtils.replace(value, ">", "&gt;");
		if (StringUtils.contains(value, "<"))
			value = StringUtils.replace(value, "<", "&lt;");
		return value;
	}
}
