// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ExtremeUtils.java

package org.extremecomponents.util;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// Referenced classes of package org.extremecomponents.util:
//			ExceptionUtils

public final class ExtremeUtils
{

	private static Log logger;

	private ExtremeUtils()
	{
	}

	public static String camelCaseToWord(String camelCaseText)
	{
		if (StringUtils.isEmpty(camelCaseText))
			return camelCaseText;
		if (camelCaseText.equals(camelCaseText.toUpperCase()))
			return camelCaseText;
		char ch[] = camelCaseText.toCharArray();
		String first = String.valueOf(ch[0]);
		String build = first.toUpperCase();
		for (int i = 1; i < ch.length; i++)
		{
			String test = String.valueOf(ch[i]);
			if (test.equals(test.toUpperCase()))
				build = build + " ";
			build = build + test;
		}

		return build;
	}

	public static String formatDate(String parse, String format, Object value)
	{
		return formatDate(parse, format, value, Locale.getDefault());
	}

	public static String formatDate(String parse, String format, Object value, Locale locale)
	{
		if (value == null)
			return null;
		if (StringUtils.isBlank(format))
		{
			String valueAsString = value.toString();
			logger.error("The format was not defined for date [" + valueAsString + "].");
			return valueAsString;
		}
		Date date = null;
		if (value instanceof Date)
		{
			date = (Date)value;
		} else
		{
			String valueAsString = value.toString();
			if (StringUtils.isBlank(valueAsString))
				return valueAsString;
			if (StringUtils.isBlank(parse))
			{
				logger.error("The parse was not defined for date String [" + valueAsString + "].");
				return valueAsString;
			}
			try
			{
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(parse, locale);
				date = simpleDateFormat.parse(valueAsString);
			}
			catch (Exception e)
			{
				logger.error("The parse was incorrectly defined for date String [" + valueAsString + "].");
				return valueAsString;
			}
		}
		return DateFormatUtils.format(date, format, locale);
	}

	public static String formatNumber(String format, Object value)
	{
		return formatNumber(format, value, Locale.getDefault());
	}

	public static String formatNumber(String format, Object value, Locale locale)
	{
		String result = null;
		if (value == null)
			return result;
		if (StringUtils.isBlank(format))
		{
			logger.error("The format was not defined for number [" + value.toString() + "].");
			return value.toString();
		} else
		{
			NumberFormat nf = NumberFormat.getNumberInstance(locale);
			DecimalFormat df = (DecimalFormat)nf;
			df.applyLocalizedPattern(format);
			return df.format(Double.parseDouble(value.toString()));
		}
	}

	public static Object retrieveFromScope(PageContext pageContext, String name)
	{
		return retrieveFromScope(pageContext, name, null);
	}

	public static Object retrieveFromScope(PageContext pageContext, String name, String scope)
	{
		if (StringUtils.isBlank(scope))
			return pageContext.findAttribute(name);
		int scopeType = 2;
		if (scope.equalsIgnoreCase("page"))
			scopeType = 1;
		else
		if (scope.equalsIgnoreCase("application"))
			scopeType = 4;
		else
		if (scope.equalsIgnoreCase("session"))
			scopeType = 3;
		return pageContext.getAttribute(name, scopeType);
	}

	public static int sessionSize(HttpSession session)
	{
		int total = 0;
		try
		{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			String name;
			int size;
			for (Enumeration enumeration = session.getAttributeNames(); enumeration.hasMoreElements(); logger.debug("The session name: " + name + " and the size is: " + size))
			{
				name = (String)enumeration.nextElement();
				Object obj = session.getAttribute(name);
				oos.writeObject(obj);
				size = baos.size();
				total += size;
			}

			logger.debug("Total session size is: " + total);
		}
		catch (Exception e)
		{
			logger.error("Could not get the session size - " + ExceptionUtils.formatStackTrace(e));
		}
		return total;
	}

	public static List beanProperties(Object bean)
		throws Exception
	{
		List properties = new ArrayList();
		if (bean instanceof Map)
			properties.addAll(((Map)bean).keySet());
		else
			properties.addAll(BeanUtils.describe(bean).keySet());
		return properties;
	}

	public static boolean isBeanPropertyReadable(Object bean, String property)
	{
		if (bean instanceof Map)
			return ((Map)bean).containsKey(property);
		boolean isReadable;
		try
		{
			isReadable = PropertyUtils.isReadable(bean, property);
		}
		catch (IllegalArgumentException e)
		{
			if (logger.isDebugEnabled())
				logger.debug("Could not find the property [" + property + "]. Either the bean or property is null");
			isReadable = false;
		}
		return isReadable;
	}

	public static List checkboxesSelected(HttpServletRequest request, String startsWithValue)
	{
		List results = new ArrayList();
		for (Enumeration parameterNames = request.getParameterNames(); parameterNames.hasMoreElements();)
		{
			String parameterName = (String)parameterNames.nextElement();
			if (parameterName.startsWith(startsWithValue))
				results.add(StringUtils.substringAfter(parameterName, startsWithValue));
		}

		return results;
	}

	public static String getQueryString(Map parameterMap)
	{
		StringBuffer results = new StringBuffer();
		Iterator iterator = parameterMap.keySet().iterator();
		for (Iterator iter = iterator; iter.hasNext();)
		{
			String key = (String)iter.next();
			String value[] = (String[])parameterMap.get(key);
			if (results.length() == 0)
				results.append("?");
			else
				results.append("&");
			results.append(key + "=");
			if (value != null && value.length > 0)
				results.append(value[0]);
		}

		return results.toString();
	}

	static 
	{
		logger = LogFactory.getLog(org.extremecomponents.util.ExtremeUtils.class);
	}
}
