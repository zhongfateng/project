// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Export.java

package org.extremecomponents.table.bean;

import org.extremecomponents.table.core.TableModel;

// Referenced classes of package org.extremecomponents.table.bean:
//			Attributes, ExportDefaults

public class Export extends Attributes
{

	private TableModel model;
	private String encoding;
	private String fileName;
	private String imageName;
	private String interceptor;
	private String view;
	private String viewResolver;
	private String text;
	private String tooltip;

	public Export(TableModel model)
	{
		this.model = model;
	}

	public void defaults()
	{
		encoding = ExportDefaults.getEncoding(model, encoding);
		text = ExportDefaults.getText(model, text);
		tooltip = ExportDefaults.getTooltip(model, tooltip);
		viewResolver = ExportDefaults.getviewResolver(model, viewResolver);
	}

	public String getEncoding()
	{
		return encoding;
	}

	public void setEncoding(String encoding)
	{
		this.encoding = encoding;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public String getImageName()
	{
		return imageName;
	}

	public void setImageName(String imageName)
	{
		this.imageName = imageName;
	}

	public String getInterceptor()
	{
		return interceptor;
	}

	public void setInterceptor(String interceptor)
	{
		this.interceptor = interceptor;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public String getTooltip()
	{
		return tooltip;
	}

	public void setTooltip(String tooltip)
	{
		this.tooltip = tooltip;
	}

	public String getView()
	{
		return view;
	}

	public void setView(String view)
	{
		this.view = view;
	}

	public String getViewResolver()
	{
		return viewResolver;
	}

	public void setViewResolver(String viewResolver)
	{
		this.viewResolver = viewResolver;
	}
}
