// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TreeCell.java

package org.extremecomponents.tree;

import java.util.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.bean.Table;
import org.extremecomponents.table.cell.Cell;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.handler.TableHandler;
import org.extremecomponents.table.view.html.BuilderUtils;
import org.extremecomponents.table.view.html.CellBuilder;
import org.extremecomponents.util.HtmlBuilder;

// Referenced classes of package org.extremecomponents.tree:
//			TreeNode, TreeRegistryUtils, TreeModelUtils

public final class TreeCell
	implements Cell
{

	private static Log logger;
	public static final String PLUS_IMAGE = "plus";
	public static final String MINUS_IMAGE = "minus";

	public TreeCell()
	{
	}

	public String getExportDisplay(TableModel model, Column column)
	{
		return null;
	}

	public String getHtmlDisplay(TableModel model, Column column)
	{
		HtmlBuilder html = new HtmlBuilder();
		CellBuilder.tdStart(html, column);
		String value = column.getValueAsString();
		if (StringUtils.isNotBlank(value))
			try
			{
				buildNodeCell(html, model, value);
			}
			catch (Exception e)
			{
				logger.error("TreeCell.html() Problem", e);
			}
		else
			html.nbsp();
		CellBuilder.tdEnd(html);
		return html.toString();
	}

	private void buildNodeCell(HtmlBuilder html, TableModel model, String value)
		throws Exception
	{
		html.table(0).cellPadding("0").cellSpacing("0").border("0").close().tr(1).close();
		TreeNode node = (TreeNode)model.getCurrentRowBean();
		for (int i = 0; i < node.getDepth(); i++)
			html.td(2).close().nbsp().nbsp().nbsp().tdEnd();

		if (node.getChildren() != null && node.getChildren().size() > 0)
		{
			buildLink(html, model, node, value);
		} else
		{
			html.td(2).close().nbsp().nbsp().nbsp().tdEnd();
			html.td(2).close().nbsp().append(value).tdEnd();
		}
		html.trEnd(1).tableEnd(0);
	}

	private void buildLink(HtmlBuilder html, TableModel model, TreeNode node, String value)
		throws Exception
	{
		html.td(2).close();
		html.a().quote();
		String action = model.getTableHandler().getTable().getAction();
		if (StringUtils.isNotEmpty(action))
			html.append(action);
		html.append(getQueryString(node, model));
		html.quote().close();
		if (node.isOpen())
			html.img(BuilderUtils.getImage(model, "minus"));
		else
			html.img(BuilderUtils.getImage(model, "plus"));
		html.aEnd();
		html.tdEnd().td(2).close().nbsp().append(value).tdEnd();
	}

	private String getQueryString(TreeNode node, TableModel model)
		throws Exception
	{
		HtmlBuilder html = new HtmlBuilder();
		html.append(TreeRegistryUtils.getURLParameterString(model, true, true, false, false));
		String identifier = BeanUtils.getProperty(node, model.getTableHandler().getTable().getAttributeAsString("identifier"));
		String currentCellOpenKey = TreeModelUtils.getNodeKey(model, identifier);
		if (!node.isOpen())
			html.append("&amp;").append(currentCellOpenKey).equals().append("true");
		Map openNodes = (Map)model.getTableHandler().getTable().getAttribute("openNodes");
		Object keys[] = openNodes.keySet().toArray();
		for (int i = 0; i < keys.length; i++)
			if (!keys[i].equals(currentCellOpenKey))
				html.append("&amp;").append(keys[i]).equals().append("true");

		if (html.length() == 0)
			return "";
		else
			return "?" + StringUtils.substringAfter(html.toString(), "&amp;");
	}

	static 
	{
		logger = LogFactory.getLog(org.extremecomponents.tree.TreeCell.class);
	}
}
