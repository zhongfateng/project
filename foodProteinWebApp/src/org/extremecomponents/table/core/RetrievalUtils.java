// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   RetrievalUtils.java

package org.extremecomponents.table.core;

import java.util.*;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.context.Context;
import org.extremecomponents.util.ExtremeUtils;

// Referenced classes of package org.extremecomponents.table.core:
//			TableConstants

public class RetrievalUtils
{

	private static Log logger;

	public RetrievalUtils()
	{
	}

	public static Object retrieve(Context context, String name)
	{
		return retrieve(context, name, null);
	}

	public static Object retrieve(Context context, String name, String scope)
	{
		if (StringUtils.isNotBlank(scope))
		{
			if (scope.equalsIgnoreCase(TableConstants.PAGE_SCOPE))
				return context.getPageAttribute(name);
			if (scope.equalsIgnoreCase(TableConstants.REQUEST_SCOPE))
				return context.getRequestAttribute(name);
			if (scope.equalsIgnoreCase(TableConstants.SESSION_SCOPE))
				return context.getSessionAttribute(name);
			if (scope.equalsIgnoreCase(TableConstants.APPLICATION_SCOPE))
				return context.getApplicationAttribute(name);
		}
		Object value = context.getPageAttribute(name);
		if (value == null)
			value = context.getRequestAttribute(name);
		if (value == null)
			value = context.getSessionAttribute(name);
		if (value == null)
			value = context.getApplicationAttribute(name);
		return value;
	}

	public static Collection retrieveCollection(Context context, Object collection)
		throws Exception
	{
		return retrieveCollection(context, collection, null);
	}

	public static Collection retrieveCollection(Context context, Object collection, String scope)
		throws Exception
	{
		if (collection instanceof Collection)
			return (Collection)collection;
		if (collection instanceof Map)
			return ((Map)collection).values();
		if (collection instanceof String)
			return retrieveCollectionFromScope(context, String.valueOf(collection), scope);
		if (logger.isDebugEnabled())
			logger.debug("Could not find the Collection.");
		return Collections.EMPTY_LIST;
	}

	static Collection retrieveCollectionFromScope(Context context, String collection, String scope)
		throws Exception
	{
		Collection results = null;
		if (StringUtils.isBlank(collection) || "null".equals(collection))
		{
			if (logger.isDebugEnabled())
				logger.debug("The collection is not defined.");
			return Collections.EMPTY_LIST;
		}
		if (StringUtils.contains(collection, "."))
			results = retrieveNestedCollection(context, collection, scope);
		else
			results = retrieveCollectionAsObject(context, collection, scope);
		if (results == null)
		{
			if (logger.isDebugEnabled())
				logger.debug("Could not find the Collection.");
			return Collections.EMPTY_LIST;
		} else
		{
			return results;
		}
	}

	static Collection retrieveNestedCollection(Context context, String collection, String scope)
		throws Exception
	{
		String split[] = StringUtils.split(collection, ".");
		Object obj = retrieve(context, split[0], scope);
		String collectionToFind = StringUtils.substringAfter(collection, ".");
		if (ExtremeUtils.isBeanPropertyReadable(obj, collectionToFind))
			obj = PropertyUtils.getProperty(obj, collectionToFind);
		if (!(obj instanceof Collection))
		{
			if (logger.isDebugEnabled())
				logger.debug("The object is not of type Collection.");
			return Collections.EMPTY_LIST;
		} else
		{
			return (Collection)obj;
		}
	}

	static Collection retrieveCollectionAsObject(Context context, String collection, String scope)
		throws Exception
	{
		Object obj = retrieve(context, collection, scope);
		if (obj instanceof Collection)
			return (Collection)obj;
		if (obj instanceof Map)
			return ((Map)obj).values();
		if (logger.isDebugEnabled())
			logger.debug("The object is not of type Collection.");
		return Collections.EMPTY_LIST;
	}

	static 
	{
		logger = LogFactory.getLog(org.extremecomponents.table.core.RetrievalUtils.class);
	}
}
