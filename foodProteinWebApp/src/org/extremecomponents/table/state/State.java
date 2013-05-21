// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   State.java

package org.extremecomponents.table.state;

import java.util.Map;
import org.extremecomponents.table.context.Context;

public interface State
{

	public abstract void saveParameters(Context context, String s, Map map);

	public abstract Map getParameters(Context context, String s, String s1);
}
