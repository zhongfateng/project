// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CalcUtils.java

package org.extremecomponents.table.calc;

import java.math.BigDecimal;
import java.util.*;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.core.*;
import org.extremecomponents.table.handler.ColumnHandler;
import org.extremecomponents.util.ExtremeUtils;

// Referenced classes of package org.extremecomponents.table.calc:
//			CalcResult, Calc, CalcHandler

public final class CalcUtils
{

	private static Log logger;

	private CalcUtils()
	{
	}

	public static CalcResult[] getCalcResults(TableModel model, Column column)
	{
		List values = new ArrayList();
		String calcs[] = column.getCalc();
		for (int i = 0; i < calcs.length; i++)
			values.add(getCalcResultsByPosition(model, column, i));

		return (CalcResult[])values.toArray(new CalcResult[values.size()]);
	}

	public static CalcResult getCalcResultsByPosition(TableModel model, Column column, int position)
	{
		String calcClassName = getCalcClassNameByPosition(model, column, position);
		if (!isCalcClassName(calcClassName))
		{
			return new CalcResult(calcClassName, null);
		} else
		{
			Calc calc = TableCache.getInstance().getCalc(calcClassName);
			return new CalcResult(calcClassName, calc.getCalcResult(model, column));
		}
	}

	public static String[] getFirstCalcColumnTitles(TableModel model)
	{
		List values = new ArrayList();
		Column column = model.getColumnHandler().getFirstCalcColumn();
		String calcs[] = column.getCalc();
		for (int i = 0; i < calcs.length; i++)
			values.add(getFirstCalcColumnTitleByPosition(model, i));

		return (String[])values.toArray(new String[values.size()]);
	}

	public static String getFirstCalcColumnTitleByPosition(TableModel model, int position)
	{
		Column column = model.getColumnHandler().getFirstCalcColumn();
		String calcTitle[] = column.getCalcTitle();
		return calcTitle[position];
	}

	private static String getCalcClassNameByPosition(TableModel model, Column column, int position)
	{
		String calcs[] = column.getCalc();
		String calcName = calcs[position];
		String calcClassName = model.getPreferences().getPreference("column.calc." + calcName);
		if (StringUtils.isBlank(calcClassName))
			calcClassName = calcName;
		return calcClassName;
	}

	private static boolean isCalcClassName(String calcClassName)
	{
		try
		{
			Class.forName(calcClassName);
			return true;
		}
		catch (ClassNotFoundException e)
		{
			return false;
		}
	}

	public static void eachRowCalcValue(CalcHandler handler, Collection rows, String property)
	{
		if (rows == null)
			return;
		for (Iterator listIter = rows.iterator(); listIter.hasNext();)
		{
			Object row = listIter.next();
			Object value = null;
			if (ExtremeUtils.isBeanPropertyReadable(row, property))
				try
				{
					value = PropertyUtils.getProperty(row, property);
					if (value instanceof Number)
						handler.processCalcValue((Number)value);
					else
						handler.processCalcValue(getValue(property, value));
				}
				catch (Exception e)
				{
					String errorMessage = "Problem parsing numeric value for property [" + property + "].";
					logger.error("CalcUtils.eachCalc() - " + errorMessage);
				}
		}

	}

	private static Number getValue(String property, Object value)
	{
		String valueAsString = String.valueOf(value);
		if (StringUtils.isNotBlank(valueAsString))
			try
			{
				return new BigDecimal(valueAsString);
			}
			catch (NumberFormatException e)
			{
				String errorMessage = "Problem parsing numeric value for property [" + property + "] with value [" + valueAsString + "].";
				logger.error("CalcUtils.getValue() - " + errorMessage);
			}
		return new BigDecimal(0.0D);
	}

	static 
	{
		logger = LogFactory.getLog(org.extremecomponents.table.calc.CalcUtils.class);
	}
}
