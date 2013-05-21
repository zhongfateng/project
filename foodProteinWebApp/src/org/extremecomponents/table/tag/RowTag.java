// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   RowTag.java

package org.extremecomponents.table.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import org.extremecomponents.table.bean.Row;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.handler.RowHandler;
import org.extremecomponents.table.interceptor.RowInterceptor;
import org.extremecomponents.util.ExceptionUtils;

// Referenced classes of package org.extremecomponents.table.tag:
//			TagUtils

public class RowTag extends TagSupport
	implements RowInterceptor
{

	private String highlightClass;
	private String highlightRow;
	private String interceptor;
	private String onclick;
	private String onmouseout;
	private String onmouseover;
	private String style;
	private String styleClass;

	public RowTag()
	{
	}

	public void setHighlightClass(String highlightClass)
	{
		this.highlightClass = highlightClass;
	}

	public void setHighlightRow(String showHighlight)
	{
		highlightRow = showHighlight;
	}

	public void setInterceptor(String interceptor)
	{
		this.interceptor = interceptor;
	}

	public void setOnclick(String onclick)
	{
		this.onclick = onclick;
	}

	public void setOnmouseout(String onmouseout)
	{
		this.onmouseout = onmouseout;
	}

	public void setOnmouseover(String onmouseover)
	{
		this.onmouseover = onmouseover;
	}

	public void setStyle(String style)
	{
		this.style = style;
	}

	public void setStyleClass(String styleClass)
	{
		this.styleClass = styleClass;
	}

	public int doStartTag()
		throws JspException
	{
		try
		{
			TableModel model = TagUtils.getModel(this);
			if (TagUtils.isIteratingBody(this))
			{
				Row row = model.getRowHandler().getRow();
				row.setOnclick(TagUtils.evaluateExpressionAsString("onclick", onclick, this, pageContext));
				row.setOnmouseout(TagUtils.evaluateExpressionAsString("onmouseout", onmouseout, this, pageContext));
				row.setOnmouseover(TagUtils.evaluateExpressionAsString("onmouseover", onmouseover, this, pageContext));
				modifyRowAttributes(model, row);
				model.getRowHandler().modifyRowAttributes();
			} else
			{
				Row row = new Row(model);
				row.setHighlightClass(TagUtils.evaluateExpressionAsString("highlightClass", highlightClass, this, pageContext));
				row.setHighlightRow(TagUtils.evaluateExpressionAsBoolean("highlightRow", highlightRow, this, pageContext));
				row.setInterceptor(TagUtils.evaluateExpressionAsString("interceptor", interceptor, this, pageContext));
				row.setOnclick(TagUtils.evaluateExpressionAsString("onclick", onclick, this, pageContext));
				row.setOnmouseout(TagUtils.evaluateExpressionAsString("onmouseout", onmouseout, this, pageContext));
				row.setOnmouseover(TagUtils.evaluateExpressionAsString("onmouseover", onmouseover, this, pageContext));
				row.setStyle(TagUtils.evaluateExpressionAsString("style", style, this, pageContext));
				row.setStyleClass(TagUtils.evaluateExpressionAsString("styleClass", styleClass, this, pageContext));
				addRowAttributes(model, row);
				model.addRow(row);
			}
		}
		catch (Exception e)
		{
			throw new JspException("RowTag.doStartTag() Problem: " + ExceptionUtils.formatStackTrace(e));
		}
		return 1;
	}

	public void addRowAttributes(TableModel tablemodel, Row row1)
	{
	}

	public void modifyRowAttributes(TableModel tablemodel, Row row1)
	{
	}

	public void release()
	{
		highlightClass = null;
		highlightRow = null;
		interceptor = null;
		onclick = null;
		onmouseout = null;
		onmouseover = null;
		style = null;
		styleClass = null;
		super.release();
	}
}
