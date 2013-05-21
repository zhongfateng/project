// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Messages.java

package org.extremecomponents.table.core;

import java.util.Locale;
import org.extremecomponents.table.context.Context;

public interface Messages
{

	public abstract void init(Context context, Locale locale);

	public abstract String getMessage(String s);

	public abstract String getMessage(String s, Object aobj[]);
}
