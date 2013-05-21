// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   BuilderUtils.java

package org.extremecomponents.table.view.html;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.extremecomponents.table.bean.Table;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.handler.ExportHandler;
import org.extremecomponents.table.handler.TableHandler;
import org.extremecomponents.table.limit.Limit;

public class BuilderUtils
{

	private BuilderUtils()
	{
	}

	public static boolean showPagination(TableModel model)
	{
		return model.getTableHandler().getTable().isShowPagination();
	}

	public static boolean showExports(TableModel model)
	{
		if (!model.getTableHandler().getTable().isShowExports())
			return false;
		return model.getExportHandler().getExports().size() > 0;
	}

	public static boolean showStatusBar(TableModel model)
	{
		return model.getTableHandler().getTable().isShowStatusBar();
	}

	public static boolean showTitle(TableModel model)
	{
		return model.getTableHandler().getTable().isShowTitle();
	}

	public static boolean filterable(TableModel model)
	{
		return model.getTableHandler().getTable().isFilterable();
	}

	public static boolean isFirstPageEnabled(int page)
	{
		return page != 1;
	}

	public static boolean isPrevPageEnabled(int page)
	{
		return page - 1 >= 1;
	}

	public static boolean isNextPageEnabled(int page, int totalPages)
	{
		return page + 1 <= totalPages;
	}

	public static boolean isLastPageEnabled(int page, int totalPages)
	{
		return page != totalPages && totalPages != 0;
	}

	public static int getTotalPages(TableModel model)
	{
		int currentRowsDisplayed = model.getLimit().getCurrentRowsDisplayed();
		if (currentRowsDisplayed == 0)
			currentRowsDisplayed = model.getLimit().getTotalRows();
		int totalRows = model.getLimit().getTotalRows();
		int totalPages = 1;
		if (currentRowsDisplayed != 0)
			totalPages = totalRows / currentRowsDisplayed;
		if (currentRowsDisplayed != 0 && totalRows % currentRowsDisplayed > 0)
			totalPages++;
		return totalPages;
	}

	public static String getImage(TableModel model, String imageName)
	{
		String imagePath = model.getTableHandler().getTable().getImagePath();
		if (StringUtils.isNotBlank(imagePath))
		{
			int index = imagePath.indexOf("*.");
			return imagePath.substring(0, index) + imageName + imagePath.substring(index + 1);
		} else
		{
			return null;
		}
	}

	public static String getForm(TableModel model)
	{
		String form = model.getTableHandler().getTable().getForm();
		if (StringUtils.isBlank(form))
			form = model.getTableHandler().getTable().getTableId();
		return form;
	}
}
