// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ViewResolver.java

package org.extremecomponents.table.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.extremecomponents.table.core.Preferences;

public interface ViewResolver
{

	public abstract void resolveView(ServletRequest servletrequest, ServletResponse servletresponse, Preferences preferences, Object obj)
		throws Exception;
}
