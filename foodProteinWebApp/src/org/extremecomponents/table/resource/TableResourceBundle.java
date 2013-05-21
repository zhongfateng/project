// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TableResourceBundle.java

package org.extremecomponents.table.resource;

import java.text.MessageFormat;
import java.util.*;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.core.Messages;
import org.extremecomponents.table.core.TableModelUtils;

public class TableResourceBundle
	implements Messages
{

	private static Log logger;
	public static final String EXTREMETABLE_RESOURCE_BUNDLE = "org.extremecomponents.table.resource.extremetableResourceBundle";
	private ResourceBundle customResourceBundle;
	private ResourceBundle defaultResourceBundle;
	private Locale locale;

	public TableResourceBundle()
	{
	}

	public void init(Context context, Locale locale)
	{
		this.locale = locale;
		defaultResourceBundle = findResourceBundle("org.extremecomponents.table.resource.extremetableResourceBundle", locale);
		String messagesLocation = TableModelUtils.getMessagesLocation(context);
		if (StringUtils.isNotBlank(messagesLocation))
			customResourceBundle = findResourceBundle(messagesLocation, locale);
	}

	private ResourceBundle findResourceBundle(String resourceBundleLocation, Locale locale)
	{
		try
		{
			return ResourceBundle.getBundle(resourceBundleLocation, locale, getClass().getClassLoader());
		}
		catch (MissingResourceException e)
		{
			if (logger.isErrorEnabled())
				logger.error("The resource bundle [ " + resourceBundleLocation + "] was not found. Make sure the path and resource name is correct.", e);
		}
		return null;
	}

	public String getMessage(String code)
	{
		return getMessage(code, null);
	}

	public String getMessage(String code, Object args[])
	{
		String result = findResource(customResourceBundle, code);
		if (result == null)
			result = findResource(defaultResourceBundle, code);
		if (result != null && args != null)
		{
			MessageFormat formatter = new MessageFormat("");
			formatter.setLocale(locale);
			formatter.applyPattern(result);
			result = formatter.format(((Object) (args)));
		}
		return result;
	}

	private String findResource(ResourceBundle resourceBundle, String code)
	{
		String result = null;
		if (resourceBundle == null)
			return result;
		try
		{
			result = resourceBundle.getString(code);
		}
		catch (MissingResourceException missingresourceexception) { }
		return result;
	}

	static 
	{
		logger = LogFactory.getLog(org.extremecomponents.table.resource.TableResourceBundle.class);
	}
}
