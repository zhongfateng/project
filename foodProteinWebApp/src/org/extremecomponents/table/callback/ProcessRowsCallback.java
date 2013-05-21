// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ProcessRowsCallback.java

package org.extremecomponents.table.callback;

import java.util.*;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.comparators.NullComparator;
import org.apache.commons.collections.comparators.ReverseComparator;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.bean.Table;
import org.extremecomponents.table.core.RetrievalUtils;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.handler.TableHandler;
import org.extremecomponents.table.limit.Limit;
import org.extremecomponents.table.limit.Sort;

// Referenced classes of package org.extremecomponents.table.callback:
//			RetrieveRowsCallback, FilterRowsCallback, SortRowsCallback, FilterPredicate, 
//			NullSafeBeanComparator

public class ProcessRowsCallback
	implements RetrieveRowsCallback, FilterRowsCallback, SortRowsCallback
{

	private static Log logger;

	public ProcessRowsCallback()
	{
	}

	public Collection retrieveRows(TableModel model)
		throws Exception
	{
		Table table = model.getTableHandler().getTable();
		return RetrievalUtils.retrieveCollection(model.getContext(), table.getItems(), table.getScope());
	}

	public Collection filterRows(TableModel model, Collection rows)
		throws Exception
	{
		boolean filtered = model.getLimit().isFiltered();
		boolean cleared = model.getLimit().isCleared();
		if (!filtered || cleared)
			return rows;
		if (filtered)
		{
			Collection collection = new ArrayList();
			FilterPredicate filterPredicate = new FilterPredicate(model);
			CollectionUtils.select(rows, filterPredicate, collection);
			return collection;
		} else
		{
			return rows;
		}
	}

	public Collection sortRows(TableModel model, Collection rows)
		throws Exception
	{
		boolean sorted = model.getLimit().isSorted();
		if (!sorted)
			return rows;
		Sort sort = model.getLimit().getSort();
		String property = sort.getProperty();
		String sortOrder = sort.getSortOrder();
		if (StringUtils.contains(property, "."))
			try
			{
				if (sortOrder.equals("asc"))
					Collections.sort((List)rows, new NullSafeBeanComparator(property, new NullComparator()));
				else
				if (sortOrder.equals("desc"))
				{
					NullSafeBeanComparator reversedNaturalOrderBeanComparator = new NullSafeBeanComparator(property, new ReverseComparator(new NullComparator()));
					Collections.sort((List)rows, reversedNaturalOrderBeanComparator);
				}
			}
			catch (NoClassDefFoundError e)
			{
				String msg = "The column property [" + property + "] is nested and requires BeanUtils 1.7 or greater for proper sorting.";
				logger.error(msg);
				throw new NoClassDefFoundError(msg);
			}
		else
		if (sortOrder.equals("asc"))
		{
			BeanComparator comparator = new BeanComparator(property, new NullComparator());
			Collections.sort((List)rows, comparator);
		} else
		if (sortOrder.equals("desc"))
		{
			BeanComparator reversedNaturalOrderBeanComparator = new BeanComparator(property, new ReverseComparator(new NullComparator()));
			Collections.sort((List)rows, reversedNaturalOrderBeanComparator);
		}
		return rows;
	}

	static 
	{
		logger = LogFactory.getLog(org.extremecomponents.table.callback.ProcessRowsCallback.class);
	}
}
