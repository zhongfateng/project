// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Registry.java

package org.extremecomponents.table.core;

import java.util.Map;

public interface Registry
{

	public abstract Map handleState(Map map);

	public abstract void addParameter(String s, Object obj);

	public abstract String getParameter(String s);

	public abstract void setParameterMap();

	public abstract Map getParameterMap();

	public abstract void removeParameter(String s);
}
