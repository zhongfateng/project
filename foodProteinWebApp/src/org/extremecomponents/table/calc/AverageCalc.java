// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   AverageCalc.java

package org.extremecomponents.table.calc;

import java.math.BigDecimal;
import java.util.Collection;
import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.core.TableModel;

// Referenced classes of package org.extremecomponents.table.calc:
//			Calc, CalcUtils, CalcHandler

public class AverageCalc
	implements Calc
{
	private static class AverageValue
		implements CalcHandler
	{

		private double total;
		private double rowCount;

		public void processCalcValue(Number calcValue)
		{
			total += calcValue.doubleValue();
		}

		public Number getAverageValue()
		{
			if (rowCount > 0.0D)
				return new BigDecimal(total / rowCount);
			else
				return new BigDecimal(0.0D);
		}

		public AverageValue(double rowCount)
		{
			total = 0.0D;
			this.rowCount = rowCount;
		}
	}


	public AverageCalc()
	{
	}

	public Number getCalcResult(TableModel model, Column column)
	{
		Collection rows = model.getCollectionOfFilteredBeans();
		String property = column.getProperty();
		AverageValue totalValue = new AverageValue(rows.size());
		CalcUtils.eachRowCalcValue(totalValue, rows, property);
		return totalValue.getAverageValue();
	}
}
