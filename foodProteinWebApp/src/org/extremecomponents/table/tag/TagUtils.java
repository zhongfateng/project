// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TagUtils.java

package org.extremecomponents.table.tag;

import java.util.Collection;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.util.ExceptionUtils;

// Referenced classes of package org.extremecomponents.table.tag:
//			TableTag

public final class TagUtils
{

	private static Log logger;

	private TagUtils()
	{
	}

	public static final String evaluateExpressionAsString(String attributeName, String attribute, Tag tag, PageContext pageContext)
	{
		try
		{
			if (attribute != null)
				attribute = (String)ExpressionEvaluatorManager.evaluate(attributeName, attribute, java.lang.String.class, tag, pageContext);
		}
		catch (JspException e)
		{
			logger.error("Could not resolve EL for [" + attributeName + "] - " + ExceptionUtils.formatStackTrace(e));
		}
		return attribute;
	}

	public static final Object evaluateExpressionAsObject(String attributeName, Object attribute, Tag tag, PageContext pageContext)
	{
		try
		{
			if (attribute != null)
				attribute = ExpressionEvaluatorManager.evaluate(attributeName, attribute.toString(), java.lang.Object.class, tag, pageContext);
		}
		catch (JspException e)
		{
			logger.error("Could not resolve EL for [" + attributeName + "] - " + ExceptionUtils.formatStackTrace(e));
		}
		return attribute;
	}

	public static final Collection evaluateExpressionAsCollection(String attributeName, Object attribute, Tag tag, PageContext pageContext)
	{
		attribute = evaluateExpressionAsObject(attributeName, attribute, tag, pageContext);
		if (attribute == null || !(attribute instanceof Collection))
		{
			if (logger.isDebugEnabled())
				logger.debug("The attribute [" + attributeName + "] is null or not a Collection.");
			return null;
		} else
		{
			return (Collection)attribute;
		}
	}

	public static final Boolean evaluateExpressionAsBoolean(String attributeName, String attribute, Tag tag, PageContext pageContext)
	{
		attribute = evaluateExpressionAsString(attributeName, attribute, tag, pageContext);
		if (attribute == null)
			return null;
		else
			return Boolean.valueOf(attribute);
	}

	public static final int evaluateExpressionAsInt(String attributeName, String attribute, Tag tag, PageContext pageContext)
	{
		attribute = evaluateExpressionAsString(attributeName, attribute, tag, pageContext);
		if (attribute == null)
			return 0;
		else
			return (new Integer(attribute)).intValue();
	}

	public static TableModel getModel(Tag tag)
	{
		TableTag tableTag = (TableTag)TagSupport.findAncestorWithClass(tag, org.extremecomponents.table.tag.TableTag.class);
		return tableTag.getModel();
	}

	public static boolean isIteratingBody(Tag tag)
	{
		return getModel(tag).getCurrentRowBean() != null;
	}

	static 
	{
		logger = LogFactory.getLog(org.extremecomponents.table.tag.ColumnTag.class);
	}
}
