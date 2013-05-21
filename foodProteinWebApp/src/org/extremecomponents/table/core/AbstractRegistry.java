// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   AbstractRegistry.java

package org.extremecomponents.table.core;

import java.util.*;
import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.state.State;

// Referenced classes of package org.extremecomponents.table.core:
//			Registry, TableModelUtils, TableCache

public abstract class AbstractRegistry
	implements Registry
{

	protected Map parameterMap;
	protected Context context;
	protected String tableId;
	protected String prefixWithTableId;
	protected String state;
	protected String stateAttr;
	protected boolean autoIncludeParameters;

	public AbstractRegistry()
	{
	}

	public void setParameterMap()
	{
		Map tableParameterMap = new HashMap();
		Map userDefinedParameterMap = new HashMap();
		Map params = context.getParameterMap();
		for (Iterator iter = params.keySet().iterator(); iter.hasNext();)
		{
			String paramName = (String)iter.next();
			if (!paramName.equals("ec_eti") && !paramName.equals("ec_i"))
				if (paramName.startsWith(prefixWithTableId + "p") || paramName.startsWith(prefixWithTableId + "crd") || paramName.startsWith(prefixWithTableId + "s_") || paramName.startsWith(prefixWithTableId + "f_") || paramName.startsWith(prefixWithTableId + "ev") || paramName.startsWith(prefixWithTableId + "efn") || paramName.startsWith(prefixWithTableId + "a_") || paramName.startsWith(prefixWithTableId + "o_"))
				{
					String paramValues[] = TableModelUtils.getValueAsArray(params.get(paramName));
					tableParameterMap.put(paramName, paramValues);
				} else
				if (autoIncludeParameters)
				{
					String paramValues[] = TableModelUtils.getValueAsArray(params.get(paramName));
					userDefinedParameterMap.put(paramName, paramValues);
				}
		}

		parameterMap = handleState(tableParameterMap);
		parameterMap.putAll(userDefinedParameterMap);
	}

	public Map handleState(Map tableParameterMap)
	{
		State state = TableCache.getInstance().getState(this.state);
		if (tableParameterMap.isEmpty())
		{
			Map stateParameters = state.getParameters(context, tableId, stateAttr);
			if (stateParameters != null)
				tableParameterMap = stateParameters;
		}
		handleStateInternal(state, tableParameterMap);
		return tableParameterMap;
	}

	public void addParameter(String name, Object value)
	{
		String paramValues[] = TableModelUtils.getValueAsArray(value);
		parameterMap.put(name, paramValues);
	}

	public String getParameter(String parameter)
	{
		String values[] = (String[])parameterMap.get(parameter);
		if (values != null && values.length > 0)
			return values[0];
		else
			return null;
	}

	public Map getParameterMap()
	{
		return parameterMap;
	}

	public void removeParameter(String parameter)
	{
		parameterMap.remove(parameter);
	}

	protected abstract void handleStateInternal(State state1, Map map);
}
