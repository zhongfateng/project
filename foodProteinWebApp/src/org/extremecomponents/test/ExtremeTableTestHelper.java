// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ExtremeTableTestHelper.java

package org.extremecomponents.test;


// Referenced classes of package org.extremecomponents.test:
//			TableParameters

public class ExtremeTableTestHelper
{

	private TableParameters parameters;
	private String prefixWithTableId;

	public ExtremeTableTestHelper(TableParameters parameters)
	{
		this(parameters, "ec");
	}

	public ExtremeTableTestHelper(TableParameters parameters, String tableId)
	{
		this.parameters = parameters;
		prefixWithTableId = tableId + "_";
		parameters.addParameter("ec_eti", tableId);
	}

	public void addFilter(String property, String value)
	{
		parameters.addParameter(getFilter(property), value);
	}

	public void addPage(int page)
	{
		parameters.addParameter(prefixWithTableId + "p", String.valueOf(page));
	}

	public void addSortAsc(String property)
	{
		parameters.addParameter(prefixWithTableId + "s_" + property, "asc");
	}

	public void addSortDesc(String property)
	{
		parameters.addParameter(prefixWithTableId + "s_" + property, "asc");
	}

	public void addSortDefault(String property)
	{
		parameters.addParameter(prefixWithTableId + "s_" + property, "default");
	}

	public void doFilter()
	{
		parameters.addParameter(prefixWithTableId + "f_" + "a", "fa");
	}

	public void doClear()
	{
		parameters.addParameter(prefixWithTableId + "f_" + "a", "ca");
	}

	public void addExportView(String view)
	{
		parameters.addParameter(prefixWithTableId + "ev", view);
	}

	public void addExportFileName(String fileName)
	{
		parameters.addParameter(prefixWithTableId + "efn", fileName);
	}

	public String getFilter(String property)
	{
		return prefixWithTableId + "f_" + property;
	}
}
