// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ExportDefaults.java

package org.extremecomponents.table.bean;

import org.apache.commons.lang.StringUtils;
import org.extremecomponents.table.core.*;

final class ExportDefaults
{

	ExportDefaults()
	{
	}

	static String getEncoding(TableModel model, String encoding)
	{
		if (StringUtils.isBlank(encoding))
			return model.getPreferences().getPreference("export.encoding");
		else
			return encoding;
	}

	static String getText(TableModel model, String text)
	{
		if (TableModelUtils.isResourceBundleProperty(text))
		{
			String resourceValue = model.getMessages().getMessage(text);
			if (resourceValue != null)
				return resourceValue;
		}
		return text;
	}

	static String getTooltip(TableModel model, String tooltip)
	{
		if (TableModelUtils.isResourceBundleProperty(tooltip))
		{
			String resourceValue = model.getMessages().getMessage(tooltip);
			if (resourceValue != null)
				return resourceValue;
		}
		return tooltip;
	}

	static String getviewResolver(TableModel model, String viewResolver)
	{
		String result = null;
		if (StringUtils.isNotBlank(viewResolver))
		{
			result = model.getPreferences().getPreference("export.viewResolver." + viewResolver);
			if (StringUtils.isBlank(result))
				result = viewResolver;
		}
		return result;
	}
}
