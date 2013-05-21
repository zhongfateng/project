// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ColumnsTag.java

package org.extremecomponents.table.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.util.ExceptionUtils;

// Referenced classes of package org.extremecomponents.table.tag:
//			TagUtils

public class ColumnsTag extends TagSupport
{

	private static Log logger;
	private String autoGenerateColumns;

	public ColumnsTag()
	{
	}

	public void setAutoGenerateColumns(String autoGenerateColumns)
	{
		this.autoGenerateColumns = autoGenerateColumns;
	}

	public int doEndTag()
		throws JspException
	{
		try
		{
			TableModel model = TagUtils.getModel(this);
			if (!TagUtils.isIteratingBody(this))
			{
				String autoGenerateColumns = TagUtils.evaluateExpressionAsString("autoGenerateColumns", this.autoGenerateColumns, this, pageContext);
				model.addColumns(autoGenerateColumns);
			} else
			{
				model.setColumnValues();
			}
			return 6;
		}
		catch (Exception e)
		{
			logger.error(ExceptionUtils.formatStackTrace(e));
			throw new JspException("ColumnsTag.doEndTag() Problem: " + ExceptionUtils.formatStackTrace(e));
		}
	}

	public void release()
	{
		autoGenerateColumns = null;
		super.release();
	}

	static 
	{
		logger = LogFactory.getLog(org.extremecomponents.table.tag.ColumnsTag.class);
	}
}
