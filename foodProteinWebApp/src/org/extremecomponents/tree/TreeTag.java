// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TreeTag.java

package org.extremecomponents.tree;

import org.extremecomponents.table.bean.Table;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.tag.TableTag;
import org.extremecomponents.table.tag.TagUtils;

public class TreeTag extends TableTag
{

	private String parentAttribute;
	private String identifier;

	public TreeTag()
	{
	}

	public void setParentAttribute(String parentAttribute)
	{
		this.parentAttribute = parentAttribute;
	}

	public void setIdentifier(String identifier)
	{
		this.identifier = identifier;
	}

	public void addTableAttributes(TableModel model, Table table)
	{
		table.addAttribute("parentAttribute", TagUtils.evaluateExpressionAsString("parentAttribute", parentAttribute, this, pageContext));
		table.addAttribute("identifier", TagUtils.evaluateExpressionAsString("identifier", identifier, this, pageContext));
		table.setShowPagination(Boolean.FALSE);
		table.setFilterRowsCallback("org.extremecomponents.tree.ProcessTreeRowsCallback");
		table.setSortRowsCallback("org.extremecomponents.tree.ProcessTreeRowsCallback");
	}

	public void release()
	{
		parentAttribute = null;
		identifier = null;
		super.release();
	}
}
