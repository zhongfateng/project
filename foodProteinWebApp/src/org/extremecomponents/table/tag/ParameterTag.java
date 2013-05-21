// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ParameterTag.java

package org.extremecomponents.table.tag;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.util.ExceptionUtils;

// Referenced classes of package org.extremecomponents.table.tag:
//			TagUtils

public class ParameterTag extends TagSupport
{

	private String name;
	private Object value;

	public ParameterTag()
	{
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setValue(Object value)
	{
		this.value = value;
	}

	public int doEndTag()
		throws JspException
	{
		try
		{
			if (TagUtils.isIteratingBody(this))
				return 6;
			String name = TagUtils.evaluateExpressionAsString("name", this.name, this, pageContext);
			Object value = TagUtils.evaluateExpressionAsObject("value", this.value, this, pageContext);
			if (value == null)
				value = pageContext.getRequest().getParameterValues(name);
			TagUtils.getModel(this).addParameter(name, value);
		}
		catch (Exception e)
		{
			throw new JspException("ParameterTag.doEndTag() Problem: " + ExceptionUtils.formatStackTrace(e));
		}
		return 6;
	}

	public void release()
	{
		name = null;
		value = null;
		super.release();
	}
}
