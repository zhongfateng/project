// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ExceptionUtils.java

package org.extremecomponents.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public final class ExceptionUtils
{

	public ExceptionUtils()
	{
	}

	public static String formatStackTrace(Throwable t)
	{
		StringWriter sw = new StringWriter();
		try
		{
			PrintWriter p = new PrintWriter(sw);
			t.printStackTrace(p);
		}
		catch (Exception exception) { }
		return sw.toString();
	}
}
