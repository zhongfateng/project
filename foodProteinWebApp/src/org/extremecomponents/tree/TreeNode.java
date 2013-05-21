// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TreeNode.java

package org.extremecomponents.tree;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class TreeNode extends HashMap
{

	private static Log logger;
	private Object identifier;
	private Object bean;
	private TreeNode parent;
	private List children;
	private int depth;
	private boolean open;

	public TreeNode()
	{
	}

	public TreeNode(Object bean, Object identifier, int depth)
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		if (bean instanceof Map)
		{
			putAll((Map)bean);
		} else
		{
			PropertyDescriptor descriptors[] = PropertyUtils.getPropertyDescriptors(bean.getClass());
			for (int i = 0; i < descriptors.length; i++)
				put(descriptors[i].getName(), BeanUtils.getProperty(bean, descriptors[i].getName()));

		}
		setBean(bean);
		this.identifier = identifier;
		this.depth = depth;
	}

	public void addChild(Object child)
	{
		if (children == null)
			children = new ArrayList();
		children.add(child);
	}

	public Object getBean()
	{
		return bean;
	}

	public void setBean(Object bean)
	{
		this.bean = bean;
		PropertyDescriptor descriptors[] = PropertyUtils.getPropertyDescriptors(bean);
		for (int i = 0; i < descriptors.length; i++)
			try
			{
				String propertyName = descriptors[i].getDisplayName();
				Object val = BeanUtils.getProperty(bean, propertyName);
				put(propertyName, val);
			}
			catch (Exception e)
			{
				logger.error("TreeNode.setBean() Problem", e);
			}

	}

	public List getChildren()
	{
		return children;
	}

	public void setChildren(List children)
	{
		this.children = children;
	}

	public TreeNode getParent()
	{
		return parent;
	}

	public void setParent(TreeNode parent)
	{
		this.parent = parent;
	}

	public int getDepth()
	{
		return depth;
	}

	public void setDepth(int depth)
	{
		this.depth = depth;
	}

	public boolean isOpen()
	{
		return open;
	}

	public void setOpen(boolean open)
	{
		this.open = open;
	}

	public Object getIdentifier()
	{
		return identifier;
	}

	public void setIdentifier(Object object)
	{
		identifier = object;
	}

	public boolean equals(Object obj)
	{
		TreeNode node = (TreeNode)obj;
		return super.equals(obj) || identifier.equals(node.getIdentifier());
	}

	static 
	{
		logger = LogFactory.getLog(org.extremecomponents.tree.TreeNode.class);
	}
}
