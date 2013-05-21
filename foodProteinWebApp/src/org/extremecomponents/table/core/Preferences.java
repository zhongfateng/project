// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Preferences.java

package org.extremecomponents.table.core;

import org.extremecomponents.table.context.Context;

public interface Preferences
{

	public abstract void init(Context context, String s);

	public abstract String getPreference(String s);
}
