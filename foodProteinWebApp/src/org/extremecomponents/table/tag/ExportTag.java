// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ExportTag.java

package org.extremecomponents.table.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import org.extremecomponents.table.bean.Export;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.interceptor.ExportInterceptor;
import org.extremecomponents.util.ExceptionUtils;

// Referenced classes of package org.extremecomponents.table.tag:
//			TagUtils

public class ExportTag extends TagSupport
	implements ExportInterceptor
{

	private String encoding;
	private String fileName;
	private String imageName;
	private String interceptor;
	private String text;
	private String tooltip;
	private String view;
	private String viewResolver;

	public ExportTag()
	{
	}

	public void setEncoding(String encoding)
	{
		this.encoding = encoding;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public void setImageName(String imageName)
	{
		this.imageName = imageName;
	}

	public void setInterceptor(String interceptor)
	{
		this.interceptor = interceptor;
	}

	public void setView(String view)
	{
		this.view = view;
	}

	public void setViewResolver(String viewResolver)
	{
		this.viewResolver = viewResolver;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public void setTooltip(String tooltip)
	{
		this.tooltip = tooltip;
	}

	public int doEndTag()
		throws JspException
	{
		if (TagUtils.isIteratingBody(this))
			return 6;
		try
		{
			TableModel model = TagUtils.getModel(this);
			Export export = new Export(model);
			export.setEncoding(TagUtils.evaluateExpressionAsString("encoding", encoding, this, pageContext));
			export.setFileName(TagUtils.evaluateExpressionAsString("fileName", fileName, this, pageContext));
			export.setImageName(TagUtils.evaluateExpressionAsString("imageName", imageName, this, pageContext));
			export.setInterceptor(TagUtils.evaluateExpressionAsString("interceptor", interceptor, this, pageContext));
			export.setText(TagUtils.evaluateExpressionAsString("text", text, this, pageContext));
			export.setTooltip(TagUtils.evaluateExpressionAsString("tooltip", tooltip, this, pageContext));
			export.setView(TagUtils.evaluateExpressionAsString("view", view, this, pageContext));
			export.setViewResolver(TagUtils.evaluateExpressionAsString("viewResolver", viewResolver, this, pageContext));
			addExportAttributes(model, export);
			model.addExport(export);
		}
		catch (Exception e)
		{
			throw new JspException("ExportTag.doEndTag() Problem: " + ExceptionUtils.formatStackTrace(e));
		}
		return 6;
	}

	public void addExportAttributes(TableModel tablemodel, Export export1)
	{
	}

	public void release()
	{
		encoding = null;
		fileName = null;
		imageName = null;
		interceptor = null;
		view = null;
		viewResolver = null;
		text = null;
		tooltip = null;
		super.release();
	}
}
