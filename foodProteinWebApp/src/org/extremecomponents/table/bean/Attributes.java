// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Attributes.java

package org.extremecomponents.table.bean;

import java.util.HashMap;
import java.util.Map;

public abstract class Attributes
{

	private Map attr;

	public Attributes()
	{
		attr = new HashMap();
	}

	public Object getAttribute(String key)
	{
		return attr.get(key);
	}

	public String getAttributeAsString(String key)
	{
		Object value = attr.get(key);
		if (value != null)
			return String.valueOf(value);
		else
			return null;
	}

	public int getAttributeAsInt(String key)
	{
		Object value = attr.get(key);
		if (value != null)
			return Integer.parseInt(String.valueOf(value));
		else
			return 0;
	}

	public void addAttribute(String key, Object value)
	{
		attr.put(key, value);
	}
}
