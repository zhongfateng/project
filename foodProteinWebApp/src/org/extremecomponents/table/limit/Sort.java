// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Sort.java

package org.extremecomponents.table.limit;

import org.apache.commons.lang.builder.ToStringBuilder;

public final class Sort
{

	private final String alias;
	private final String property;
	private final String sortOrder;

	public Sort()
	{
		alias = null;
		property = null;
		sortOrder = null;
	}

	public Sort(String alias, String property, String sortOrder)
	{
		this.alias = alias;
		this.property = property;
		this.sortOrder = sortOrder;
	}

	public String getAlias()
	{
		return alias;
	}

	public String getProperty()
	{
		return property;
	}

	public String getSortOrder()
	{
		return sortOrder;
	}

	public boolean isSorted()
	{
		return sortOrder != null;
	}

	public boolean isAliased()
	{
		return !alias.equals(property);
	}

	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.append("alias", alias);
		builder.append("property", property);
		builder.append("sortOrder", sortOrder);
		return builder.toString();
	}
}
