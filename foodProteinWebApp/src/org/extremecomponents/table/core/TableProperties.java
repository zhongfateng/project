// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TableProperties.java

package org.extremecomponents.table.core;

import java.io.IOException;
import java.util.Properties;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.context.Context;

// Referenced classes of package org.extremecomponents.table.core:
//			Preferences

public class TableProperties
	implements Preferences
{

	private static Log logger;
	public static final String EXTREMECOMPONENTS_PROPERTIES = "extremecomponents.properties";
	public static final String EXTREMETABLE_PROPERTIES = "extremetable.properties";
	private Properties properties;

	public TableProperties()
	{
		properties = new Properties();
	}

	public void init(Context context, String preferencesLocation)
	{
		try
		{
			properties.load(getClass().getResourceAsStream("extremetable.properties"));
			if (StringUtils.isNotBlank(preferencesLocation))
			{
				java.io.InputStream input = getClass().getResourceAsStream(preferencesLocation);
				if (input != null)
					properties.load(input);
			}
		}
		catch (IOException e)
		{
			if (logger.isErrorEnabled())
				logger.error("Could not load the eXtremeTable preferences.", e);
		}
	}

	public String getPreference(String name)
	{
		return (String)properties.get(name);
	}

	static 
	{
		logger = LogFactory.getLog(org.extremecomponents.table.core.TableProperties.class);
	}
}
