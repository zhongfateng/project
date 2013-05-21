// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TotalCalc.java

package org.extremecomponents.table.calc;

import java.math.BigDecimal;
import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.core.TableModel;

// Referenced classes of package org.extremecomponents.table.calc:
//			Calc, CalcUtils, CalcHandler

public class TotalCalc
	implements Calc
{
	private static class TotalValue
		implements CalcHandler
	{

		double total;

		public void processCalcValue(Number calcValue)
		{
			total += calcValue.doubleValue();
		}

		public Number getTotalValue()
		{
			return new BigDecimal(total);
		}

		TotalValue()
		{
			total = 0.0D;
		}
	}


	public TotalCalc()
	{
	}

	public Number getCalcResult(TableModel model, Column column)
	{
		java.util.Collection rows = model.getCollectionOfFilteredBeans();
		String property = column.getProperty();
		TotalValue totalValue = new TotalValue();
		CalcUtils.eachRowCalcValue(totalValue, rows, property);
		return totalValue.getTotalValue();
	}
}
