// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   FilterDroplistCell.java

package org.extremecomponents.table.cell;

import java.util.*;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.comparators.NullComparator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.handler.TableHandler;
import org.extremecomponents.table.view.html.TableActions;
import org.extremecomponents.util.ExtremeUtils;
import org.extremecomponents.util.HtmlBuilder;

// Referenced classes of package org.extremecomponents.table.cell:
//			Cell, FilterOption

public class FilterDroplistCell
	implements Cell
{
	protected static class Option
		implements FilterOption
	{

		private final Object label;
		private final Object value;

		public Object getLabel()
		{
			return label;
		}

		public Object getValue()
		{
			return value;
		}

		public Option(Object obj)
		{
			label = obj;
			value = obj;
		}

		public Option(Object label, Object value)
		{
			this.label = label;
			this.value = value;
		}
	}


	private static Log logger;

	public FilterDroplistCell()
	{
	}

	public String getExportDisplay(TableModel model, Column column)
	{
		return null;
	}

	public String getHtmlDisplay(TableModel model, Column column)
	{
		HtmlBuilder html = new HtmlBuilder();
		if (!column.isFilterable())
		{
			html.append("");
		} else
		{
			Collection filterOptions = column.getFilterOptions();
			if (filterOptions == null || filterOptions.isEmpty())
				filterOptions = getFilterDropList(model, column);
			html.append(dropListHtml(model, column, filterOptions));
		}
		return html.toString();
	}

	protected Collection getFilterDropList(TableModel model, Column column)
	{
		List droplist = new ArrayList();
		Set options = new HashSet();
		Collection beans = model.getCollectionOfBeans();
		for (Iterator iter = beans.iterator(); iter.hasNext();)
		{
			Object bean = iter.next();
			try
			{
				Object obj = getFilterOption(column, bean);
				if (obj != null && !options.contains(obj))
				{
					droplist.add(new Option(obj));
					options.add(obj);
				}
			}
			catch (Exception e)
			{
				logger.debug("Problems getting the droplist.", e);
			}
		}

		BeanComparator comparator = new BeanComparator("label", new NullComparator());
		Collections.sort(droplist, comparator);
		return droplist;
	}

	protected Object getFilterOption(Column column, Object bean)
		throws Exception
	{
		return PropertyUtils.getProperty(bean, column.getProperty());
	}

	protected String dropListHtml(TableModel model, Column column, Collection droplist)
	{
		HtmlBuilder html = new HtmlBuilder();
		html.td(2).close();
		html.newline();
		html.tabs(2);
		html.select().name(model.getTableHandler().prefixWithTableId() + "f_" + column.getAlias());
		StringBuffer onkeypress = new StringBuffer();
		onkeypress.append((new TableActions(model)).getFilterAction());
		html.onchange(onkeypress.toString());
		html.close();
		html.newline();
		html.tabs(2);
		html.option().value("").close();
		html.optionEnd();
		java.util.Locale locale = model.getLocale();
		for (Iterator iter = droplist.iterator(); iter.hasNext(); html.optionEnd())
		{
			FilterOption filterOption = (FilterOption)iter.next();
			String value = String.valueOf(filterOption.getValue());
			String label = String.valueOf(filterOption.getLabel());
			if (column.isDate())
				value = ExtremeUtils.formatDate(column.getParse(), column.getFormat(), filterOption.getValue(), locale);
			html.newline();
			html.tabs(2);
			html.option().value(value);
			if (value.equals(column.getValueAsString()))
				html.selected();
			html.close();
			html.append(label);
		}

		html.newline();
		html.tabs(2);
		html.selectEnd();
		html.tdEnd();
		return html.toString();
	}

	static 
	{
		logger = LogFactory.getLog(org.extremecomponents.table.cell.FilterDroplistCell.class);
	}
}
