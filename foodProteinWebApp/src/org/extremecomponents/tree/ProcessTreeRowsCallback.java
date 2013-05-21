// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ProcessTreeRowsCallback.java

package org.extremecomponents.tree;

import java.util.*;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.comparators.NullComparator;
import org.apache.commons.collections.comparators.ReverseComparator;
import org.extremecomponents.table.bean.Table;
import org.extremecomponents.table.callback.*;
import org.extremecomponents.table.core.Registry;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.handler.TableHandler;
import org.extremecomponents.table.limit.Limit;
import org.extremecomponents.table.limit.Sort;

// Referenced classes of package org.extremecomponents.tree:
//			TreeModelUtils, TreeNode

public final class ProcessTreeRowsCallback
	implements FilterRowsCallback, SortRowsCallback
{

	public ProcessTreeRowsCallback()
	{
	}

	public Collection filterRows(TableModel model, Collection rows)
		throws Exception
	{
		model.getTableHandler().getTable().addAttribute("openNodes", getParameters(model.getRegistry().getParameterMap(), "o_", model.getTableHandler().prefixWithTableId()));
		boolean filtered = model.getLimit().isFiltered();
		boolean cleared = model.getLimit().isCleared();
		if (filtered && !cleared)
		{
			rows = filter(model, rows);
			rows = TreeModelUtils.findParents(model, rows);
			rows = TreeModelUtils.loadTreeStructure(model, rows);
			TreeModelUtils.addClosedChildren(model, rows);
		} else
		{
			rows = TreeModelUtils.loadTreeStructure(model, rows);
		}
		setFilteredCount(model, rows);
		return rows;
	}

	private Map getParameters(Map parameterMap, String parameter, String prefixWithTableId)
	{
		Map subset = new HashMap();
		String find = prefixWithTableId + parameter;
		Set set = parameterMap.keySet();
		for (Iterator iter = set.iterator(); iter.hasNext();)
		{
			String name = (String)iter.next();
			if (name.startsWith(find))
			{
				String values[] = (String[])parameterMap.get(name);
				subset.put(name, values);
			}
		}

		return subset;
	}

	private Collection filter(TableModel model, Collection rows)
		throws Exception
	{
		List results = new ArrayList();
		FilterPredicate filterPredicate = new FilterPredicate(model);
		CollectionUtils.select(rows, filterPredicate, results);
		for (int i = 0; i < results.size(); i++)
		{
			Object bean = results.get(i);
			TreeModelUtils.findBeanParents(model, rows, results, bean);
		}

		return results;
	}

	private void setFilteredCount(TableModel model, Collection rows)
	{
		if (rows == null)
		{
			model.getTableHandler().getTable().addAttribute("FILTERED_COUNT", "0");
			return;
		} else
		{
			model.getTableHandler().getTable().addAttribute("FILTERED_COUNT", (new StringBuffer(String.valueOf(rows.size()))).toString());
			return;
		}
	}

	public Collection sortRows(TableModel model, Collection rows)
		throws Exception
	{
		boolean sorted = model.getLimit().isSorted();
		if (!sorted)
			return rows;
		List parents = new ArrayList();
		for (Iterator iter = rows.iterator(); iter.hasNext();)
		{
			TreeNode node = (TreeNode)iter.next();
			if (node.getParent() == null)
				parents.add(node);
		}

		List output = new ArrayList();
		Sort sort = model.getLimit().getSort();
		String property = sort.getProperty();
		String sortOrder = sort.getSortOrder();
		subSort(parents, property, sortOrder);
		recursiveSort(output, parents, property, sortOrder);
		output.retainAll(rows);
		rows.clear();
		rows.addAll(output);
		return rows;
	}

	private void recursiveSort(List output, List rows, String property, String sortOrder)
	{
		for (Iterator iter = rows.iterator(); iter.hasNext();)
		{
			TreeNode node = (TreeNode)iter.next();
			output.add(node);
			if (node.getChildren() != null && node.getChildren().size() > 0)
			{
				subSort(node.getChildren(), property, sortOrder);
				recursiveSort(output, node.getChildren(), property, sortOrder);
			}
		}

	}

	private void subSort(List rows, String property, String sortOrder)
	{
		if (sortOrder.equals("asc"))
		{
			BeanComparator comparator = new BeanComparator(property, new NullComparator());
			Collections.sort(rows, comparator);
		} else
		if (sortOrder.equals("desc"))
		{
			BeanComparator reversedNaturalOrderBeanComparator = new BeanComparator(property, new ReverseComparator(new NullComparator()));
			Collections.sort(rows, reversedNaturalOrderBeanComparator);
		}
	}
}
