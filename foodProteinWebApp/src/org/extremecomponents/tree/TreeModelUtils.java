// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TreeModelUtils.java

package org.extremecomponents.tree;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.bean.Table;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.handler.TableHandler;
import org.extremecomponents.table.limit.Limit;

// Referenced classes of package org.extremecomponents.tree:
//			TreeNode

public final class TreeModelUtils
{

	private static Log logger;

	private TreeModelUtils()
	{
	}

	public static List loadTreeStructure(TableModel model, Collection inputList)
		throws Exception
	{
		List result = new ArrayList();
		List searchList = new ArrayList();
		searchList.addAll(inputList);
		for (Iterator iter = searchList.iterator(); iter.hasNext();)
		{
			Object bean = iter.next();
			String parentAttribute = model.getTableHandler().getTable().getAttributeAsString("parentAttribute");
			Object parentId = BeanUtils.getProperty(bean, parentAttribute);
			if (parentId == null || StringUtils.isBlank(((String) (parentId))))
			{
				TreeNode node = new TreeNode(bean, getBeanId(model, bean), 0);
				result.add(node);
				iter.remove();
				loadChildren(model, result, searchList, node, 0);
			}
		}

		return result;
	}

	public static void loadChildren(TableModel model, List displayList, List searchList, TreeNode node, int currentDepth)
		throws Exception
	{
		currentDepth++;
		List subList = new ArrayList();
		subList.addAll(searchList);
		Object id = node.getIdentifier();
		String key = getNodeKey(model, id);
		Map openNodes = (Map)model.getTableHandler().getTable().getAttribute("openNodes");
		if (openNodes.get(key) != null)
			node.setOpen(true);
		else
			node.setOpen(false);
		for (Iterator iter = subList.iterator(); iter.hasNext();)
		{
			Object bean = iter.next();
			if (nodeIsBeanParent(model, node, bean))
			{
				TreeNode childNode = new TreeNode(bean, getBeanId(model, bean), currentDepth);
				node.addChild(childNode);
				childNode.setParent(node);
				iter.remove();
				if (isOpen(model, node, true))
					displayList.add(childNode);
				loadChildren(model, displayList, subList, childNode, currentDepth);
			}
		}

	}

	public static Collection findParents(TableModel model, Collection searchList)
		throws Exception
	{
		logger.debug("TableModel.findParents()");
		List result = new ArrayList();
		Object bean;
		for (Iterator iter = searchList.iterator(); iter.hasNext(); findBeanParents(model, searchList, result, bean))
		{
			bean = iter.next();
			if (!result.contains(bean))
				result.add(bean);
		}

		return result;
	}

	public static void findBeanParents(TableModel model, Collection searchList, Collection parents, Object bean)
		throws Exception
	{
		Object parent = null;
		String parentAttribute = model.getTableHandler().getTable().getAttributeAsString("parentAttribute");
		if (bean instanceof Map)
			parent = ((Map)bean).get(parentAttribute);
		else
			parent = PropertyUtils.getProperty(bean, parentAttribute);
		if (parent == null)
			return;
		Object parentBean = findByIdentifierOrReference(model, (List)searchList, parent);
		if (parentBean == null)
			return;
		if (!parents.contains(parentBean))
			parents.add(parentBean);
		findBeanParents(model, searchList, parents, parentBean);
	}

	public static Object findByIdentifierOrReference(TableModel model, List searchList, Object bean)
	{
		if (searchList.contains(bean))
			return bean;
		String identifier = model.getTableHandler().getTable().getAttributeAsString("identifier");
		for (int i = 0; i < searchList.size(); i++)
		{
			Object row = searchList.get(i);
			try
			{
				if (bean.equals(BeanUtils.getProperty(row, identifier)))
					return row;
			}
			catch (IllegalAccessException e)
			{
				e.printStackTrace();
			}
			catch (InvocationTargetException e)
			{
				e.printStackTrace();
			}
			catch (NoSuchMethodException e)
			{
				e.printStackTrace();
			}
		}

		return null;
	}

	public static void addClosedChildren(TableModel model, Collection displayList)
		throws Exception
	{
		logger.debug("TableModel.addClosedChildren()");
		List parents = new ArrayList();
		parents.addAll(displayList);
		for (Iterator iter = parents.iterator(); iter.hasNext();)
		{
			TreeNode node = (TreeNode)iter.next();
			if (node.getChildren() == null || node.getChildren().size() == 0)
				addChildren(model, displayList, node);
		}

	}

	public static void addChildren(TableModel model, Collection displayList, TreeNode node)
		throws Exception
	{
		for (Iterator iter = model.getCollectionOfBeans().iterator(); iter.hasNext();)
		{
			Object bean = iter.next();
			if (nodeIsBeanParent(model, node, bean))
			{
				TreeNode childNode = new TreeNode(bean, getBeanId(model, bean), node.getDepth() + 1);
				node.addChild(childNode);
				Object id = node.getIdentifier();
				Map openNodes = (Map)model.getTableHandler().getTable().getAttribute("openNodes");
				String nodeKey = getNodeKey(model, id);
				Object openParam = openNodes.get(nodeKey);
				if (openParam != null && (openParam instanceof String[]))
					openParam = ((String[])openParam)[0];
				if (isOpen(model, node, false) || "true".equals(openParam))
				{
					node.setOpen(true);
					int parentPosition = ((List)displayList).indexOf(node);
					((List)displayList).add(parentPosition + 1, childNode);
				}
				addChildren(model, displayList, childNode);
			}
		}

	}

	public static boolean nodeIsBeanParent(TableModel model, TreeNode node, Object bean)
		throws Exception
	{
		Object parent = null;
		String parentAttribute = model.getTableHandler().getTable().getAttributeAsString("parentAttribute");
		if (bean instanceof Map)
			parent = ((Map)bean).get(parentAttribute);
		else
			parent = PropertyUtils.getProperty(bean, parentAttribute);
		if (parent != null)
			logger.debug("parent instanceof " + parent.getClass().getName());
		if (parent == null || StringUtils.isBlank(((String) (parent))))
			return false;
		Object nodeId = node.getIdentifier();
		Object parentId = getBeanId(model, parent);
		if (node.getBean().equals(parent))
			return true;
		return nodeId.equals(parentId);
	}

	public static Object getBeanId(TableModel model, Object bean)
		throws Exception
	{
		try
		{
			String identifier = model.getTableHandler().getTable().getAttributeAsString("identifier");
			if (bean instanceof Map)
				return ((Map)bean).get(identifier);
			else
				return PropertyUtils.getProperty(bean, identifier);
		}
		catch (NoSuchMethodException e)
		{
			return bean;
		}
	}

	public static boolean isOpen(TableModel model, TreeNode node, boolean filterControlled)
	{
		boolean filtered = model.getLimit().isFiltered();
		boolean cleared = model.getLimit().isCleared();
		if (filterControlled && filtered && !cleared)
		{
			node.setOpen(true);
			return true;
		}
		if (!node.isOpen())
			return false;
		if (node.getParent() == null)
			return true;
		else
			return isOpen(model, node.getParent(), filterControlled);
	}

	public static String getNodeKey(TableModel model, Object id)
	{
		return model.getTableHandler().prefixWithTableId() + "o_" + id;
	}

	static 
	{
		logger = LogFactory.getLog(org.extremecomponents.tree.TreeModelUtils.class);
	}
}
