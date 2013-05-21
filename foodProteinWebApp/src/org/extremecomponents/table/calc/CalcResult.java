// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CalcResult.java

package org.extremecomponents.table.calc;


public final class CalcResult
{

	private final String name;
	private final Number value;

	public CalcResult(String name, Number value)
	{
		this.name = name;
		this.value = value;
	}

	public String getName()
	{
		return name;
	}

	public Number getValue()
	{
		return value;
	}
}
