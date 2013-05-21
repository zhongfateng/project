// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   FormBuilder.java

package org.extremecomponents.table.view.html;

import java.util.*;
import org.apache.commons.lang.StringUtils;
import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.bean.Table;
import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.core.Registry;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.handler.ColumnHandler;
import org.extremecomponents.table.handler.TableHandler;
import org.extremecomponents.table.limit.Limit;
import org.extremecomponents.table.limit.Sort;
import org.extremecomponents.util.HtmlBuilder;

// Referenced classes of package org.extremecomponents.table.view.html:
//			BuilderUtils

public class FormBuilder
{

	private HtmlBuilder html;
	private TableModel model;

	public FormBuilder(TableModel model)
	{
		this(new HtmlBuilder(), model);
	}

	public FormBuilder(HtmlBuilder html, TableModel model)
	{
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

	public void formStart()
	{
		formAttributes();
		html.newline();
		html.div().close();
		instanceParameter();
		exportTableIdParameter();
		exportParameters();
		rowsDisplayedParameter();
		filterParameter();
		pageParameters();
		sortParameters();
		aliasParameters();
		userDefinedParameters();
		html.newline();
		html.divEnd();
	}

	public void formEnd()
	{
		String form = model.getTableHandler().getTable().getForm();
		if (StringUtils.isBlank(form))
			html.formEnd();
	}

	public void formAttributes()
	{
		String form = model.getTableHandler().getTable().getForm();
		if (StringUtils.isBlank(form))
		{
			html.form();
			html.id(model.getTableHandler().getTable().getTableId());
			html.action(model.getTableHandler().getTable().getAction());
			html.method(model.getTableHandler().getTable().getMethod());
			html.close();
		}
	}

	public void instanceParameter()
	{
		html.newline();
		html.input("hidden");
		html.name("ec_i");
		html.value(model.getTableHandler().getTable().getTableId());
		html.xclose();
	}

	public void filterParameter()
	{
		if (BuilderUtils.filterable(model))
		{
			html.newline();
			html.input("hidden");
			html.name(model.getTableHandler().prefixWithTableId() + "f_" + "a");
			if (model.getLimit().isFiltered())
				html.value("fa");
			html.xclose();
		}
	}

	public void rowsDisplayedParameter()
	{
		html.newline();
		html.input("hidden");
		html.name(model.getTableHandler().prefixWithTableId() + "crd");
		int currentRowsDisplayed = model.getLimit().getCurrentRowsDisplayed();
		html.value(String.valueOf(currentRowsDisplayed));
		html.xclose();
	}

	public void pageParameters()
	{
		html.newline();
		html.input("hidden");
		html.name(model.getTableHandler().prefixWithTableId() + "p");
		int page = model.getLimit().getPage();
		if (page > 0)
			html.value(String.valueOf(page));
		html.xclose();
	}

	public void exportTableIdParameter()
	{
		if (!BuilderUtils.showExports(model))
			return;
		String form = BuilderUtils.getForm(model);
		String existingForm = (String)model.getContext().getRequestAttribute("ec_eti");
		if (form.equals(existingForm))
		{
			return;
		} else
		{
			html.newline();
			html.input("hidden");
			html.name("ec_eti");
			html.xclose();
			model.getContext().setRequestAttribute("ec_eti", form);
			return;
		}
	}

	public void exportParameters()
	{
		if (!BuilderUtils.showExports(model))
		{
			return;
		} else
		{
			html.newline();
			html.input("hidden");
			html.name(model.getTableHandler().prefixWithTableId() + "ev");
			html.xclose();
			html.newline();
			html.input("hidden");
			html.name(model.getTableHandler().prefixWithTableId() + "efn");
			html.xclose();
			return;
		}
	}

	public void sortParameters()
	{
		List columns = model.getColumnHandler().getColumns();
		for (Iterator iter = columns.iterator(); iter.hasNext();)
		{
			Column column = (Column)iter.next();
			if (column.isSortable())
			{
				html.newline();
				html.input("hidden");
				html.name(model.getTableHandler().prefixWithTableId() + "s_" + column.getAlias());
				Sort sort = model.getLimit().getSort();
				if (sort.isSorted() && sort.getAlias().equals(column.getAlias()))
					html.value(sort.getSortOrder());
				html.xclose();
			}
		}

	}

	public void userDefinedParameters()
	{
		Map parameterMap = model.getRegistry().getParameterMap();
		Set keys = parameterMap.keySet();
		for (Iterator iter = keys.iterator(); iter.hasNext();)
		{
			String name = (String)iter.next();
			if (!name.startsWith(model.getTableHandler().prefixWithTableId()))
			{
				String values[] = (String[])parameterMap.get(name);
				if (values == null || values.length == 0)
				{
					html.newline();
					html.input("hidden").name(name).xclose();
				} else
				{
					for (int i = 0; i < values.length; i++)
					{
						html.newline();
						html.input("hidden").name(name).value(values[i]).xclose();
					}

				}
			}
		}

	}

	public void aliasParameters()
	{
		List columns = model.getColumnHandler().getColumns();
		for (Iterator iter = columns.iterator(); iter.hasNext();)
		{
			Column column = (Column)iter.next();
			if (StringUtils.isNotBlank(column.getProperty()) && !column.getProperty().equals(column.getAlias()))
			{
				html.newline();
				html.input("hidden");
				html.name(model.getTableHandler().prefixWithTableId() + "a_" + column.getAlias());
				html.value(column.getProperty());
				html.xclose();
			}
		}

	}

	public String toString()
	{
		return html.toString();
	}
}
