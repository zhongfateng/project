// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   NullSafeBeanComparator.java

package org.extremecomponents.table.callback;

import java.util.Comparator;
import org.apache.commons.beanutils.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class NullSafeBeanComparator extends BeanComparator
{

	private Log log;
	protected boolean nullsAreHigh;
	protected String property;
	protected Comparator comparator;

	public Comparator getComparator()
	{
		return comparator;
	}

	public void setComparator(Comparator comparator)
	{
		this.comparator = comparator;
	}

	public boolean isNullsAreHigh()
	{
		return nullsAreHigh;
	}

	public void setNullsAreHigh(boolean nullsAreHigh)
	{
		this.nullsAreHigh = nullsAreHigh;
	}

	public String getProperty()
	{
		return property;
	}

	public void setProperty(String property)
	{
		this.property = property;
	}

	public int compare(Object o1, Object o2)
	{
		if (property == null)
			return comparator.compare(o1, o2);
		Object val1 = null;
		Object val2 = null;
		try
		{
			try
			{
				val1 = PropertyUtils.getProperty(o1, property);
			}
			catch (NestedNullException nestednullexception) { }
			try
			{
				val2 = PropertyUtils.getProperty(o2, property);
			}
			catch (NestedNullException nestednullexception1) { }
			if (val1 == val2 || val1 == null && val2 == null)
				return -1;
			if (val1 == null)
				return nullsAreHigh ? 1 : -1;
			if (val2 == null)
				return nullsAreHigh ? -1 : 1;
			else
				return comparator.compare(val1, val2);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			log.warn(e);
			return 0;
		}
	}

	public NullSafeBeanComparator(String property, Comparator c)
	{
		log = LogFactory.getLog(org.extremecomponents.table.callback.NullSafeBeanComparator.class);
		nullsAreHigh = true;
		comparator = c;
		this.property = property;
	}

	public NullSafeBeanComparator(String property, Comparator c, boolean nullAreHigh)
	{
		log = LogFactory.getLog(org.extremecomponents.table.callback.NullSafeBeanComparator.class);
		nullsAreHigh = true;
		comparator = c;
		this.property = property;
		nullsAreHigh = nullAreHigh;
	}
}
