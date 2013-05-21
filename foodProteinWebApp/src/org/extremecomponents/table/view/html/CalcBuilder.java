// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CalcBuilder.java

package org.extremecomponents.table.view.html;

import java.util.Iterator;
import java.util.List;
import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.calc.CalcResult;
import org.extremecomponents.table.calc.CalcUtils;
import org.extremecomponents.table.core.Preferences;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.handler.ColumnHandler;
import org.extremecomponents.util.ExtremeUtils;
import org.extremecomponents.util.HtmlBuilder;

public class CalcBuilder
{

	private Log logger;
	private HtmlBuilder html;
	private TableModel model;

	public CalcBuilder(TableModel model)
	{
		this(new HtmlBuilder(), model);
	}

	public CalcBuilder(HtmlBuilder html, TableModel model)
	{
		logger = LogFactory.getLog(org.extremecomponents.table.view.html.CalcBuilder.class);
		this.html = html;
		this.model = model;
	}

	public HtmlBuilder getHtmlBuilder()
	{
		return html;
	}

	protected TableModel getTableModel()
	{
		return model;
	}

	public void defaultCalcLayout()
	{
		Column calcColumn = model.getColumnHandler().getFirstCalcColumn();
		if (calcColumn == null)
			return;
		String layout = model.getPreferences().getPreference("defaultCalcLayout");
		try
		{
			MethodUtils.invokeExactMethod(this, layout, null);
		}
		catch (Exception e)
		{
			logger.error("There is no method with the layout [" + layout + "].", e);
		}
	}

	public void singleRowCalcResults()
	{
		html.tr(1).styleClass("calcRow").close();
		for (Iterator iter = model.getColumnHandler().getColumns().iterator(); iter.hasNext();)
		{
			Column column = (Column)iter.next();
			if (column.isFirstColumn())
			{
				String calcTitle[] = CalcUtils.getFirstCalcColumnTitles(model);
				if (calcTitle != null && calcTitle.length > 0)
				{
					html.td(2).styleClass("calcTitle").close();
					for (int i = 0; i < calcTitle.length; i++)
					{
						String title = calcTitle[i];
						html.append(title);
						if (calcTitle.length > 0 && i + 1 != calcTitle.length)
							html.append(" / ");
					}

					html.tdEnd();
				}
			} else
			{
				if (column.isCalculated())
				{
					html.td(2).styleClass("calcResult").close();
					CalcResult calcResults[] = CalcUtils.getCalcResults(model, column);
					for (int i = 0; i < calcResults.length; i++)
					{
						CalcResult calcResult = calcResults[i];
						Number value = calcResult.getValue();
						if (value == null)
							html.append(calcResult.getName());
						else
							html.append(ExtremeUtils.formatNumber(column.getFormat(), value, model.getLocale()));
						if (calcResults.length > 0 && i + 1 != calcResults.length)
							html.append(" / ");
					}

				} else
				{
					html.td(2).close();
					html.nbsp();
				}
				html.tdEnd();
			}
		}

		html.trEnd(1);
	}

	public void multiRowCalcResults()
	{
		Column firstCalcColumn = model.getColumnHandler().getFirstCalcColumn();
		int rows = firstCalcColumn.getCalc().length;
		for (int i = 0; i < rows; i++)
		{
			html.tr(1).styleClass("calcRow").close();
			for (Iterator iter = model.getColumnHandler().getColumns().iterator(); iter.hasNext();)
			{
				Column column = (Column)iter.next();
				if (column.isFirstColumn())
				{
					String calcTitle = CalcUtils.getFirstCalcColumnTitleByPosition(model, i);
					html.td(2).styleClass("calcTitle").close();
					html.append(calcTitle);
					html.tdEnd();
				} else
				{
					if (column.isCalculated())
					{
						html.td(2).styleClass("calcResult").close();
						CalcResult calcResult = CalcUtils.getCalcResultsByPosition(model, column, i);
						Number value = calcResult.getValue();
						if (value == null)
							html.append(calcResult.getName());
						else
							html.append(ExtremeUtils.formatNumber(column.getFormat(), value, model.getLocale()));
					} else
					{
						html.td(2).close();
						html.nbsp();
					}
					html.tdEnd();
				}
			}

			html.trEnd(1);
		}

	}

	public String toString()
	{
		return html.toString();
	}
}
