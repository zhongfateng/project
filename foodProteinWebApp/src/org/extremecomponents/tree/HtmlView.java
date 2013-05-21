// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   HtmlView.java

package org.extremecomponents.tree;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.bean.Export;
import org.extremecomponents.table.bean.Row;
import org.extremecomponents.table.bean.Table;
import org.extremecomponents.table.calc.CalcResult;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.view.View;
import org.extremecomponents.table.view.html.BuilderUtils;
import org.extremecomponents.util.ExtremeUtils;
import org.extremecomponents.util.HtmlBuilder;

// Referenced classes of package org.extremecomponents.tree:
//			TreeRegistryUtils, HtmlViewUtils

public class HtmlView extends HtmlBuilder
	implements View
{

	public static final String TOOLBAR = "toolbar";
	public static final String SEPARATOR = "separator";
	public static final String STATUS_BAR = "statusBar";
	public static final String FILTER_BUTTONS = "filterButtons";
	public static final String FORM_BUTTONS = "formButtons";
	public static final String FILTER = "filter";
	public static final String TITLE = "title";
	public static final String TABLE_TOTAL_TITLE = "tableTotalTitle";
	public static final String TABLE_TOTALS = "tableTotal";
	public static final String TABLE_TOTALS_EMPTY = "tableTotalEmpty";
	public static final String TABLE_BODY = "tableBody";
	public static final String LAST_PAGE = "lastPage";
	public static final String LAST_PAGE_DISABLED = "lastPageDisabled";
	public static final String FIRST_PAGE = "firstPage";
	public static final String FIRST_PAGE_DISABLED = "firstPageDisabled";
	public static final String PREV_PAGE = "prevPage";
	public static final String PREV_PAGE_DISABLED = "prevPageDisabled";
	public static final String NEXT_PAGE = "nextPage";
	public static final String NEXT_PAGE_DISABLED = "nextPageDisabled";
	public static final String SEARCH_ARROW_IMAGE = "searchArrow";
	public static final String SEARCH_IMAGE = "search";
	public static final String CLEAR_IMAGE = "clear";

	public HtmlView()
	{
	}

	public void beforeBody(TableModel model)
	{
		div().styleClass("eXtremeTable").close();
		toolbarPlacement(model);
		tableStart(model);
		statusBar(model);
		filter(model);
		header(model);
		tbody(1).styleClass("tableBody").close();
	}

	public void body(TableModel model, Column column)
	{
		if (column.isFirstColumn())
		{
			tr(1);
			if (model.getRowHandler().isRowEven())
				rowStyleAndJavascript(model, "even");
			else
				rowStyleAndJavascript(model, "odd");
			close();
		}
		append(column.getCellDisplay());
		if (column.isLastColumn())
			trEnd(1);
	}

	public Object afterBody(TableModel model)
	{
		totals(model);
		tbodyEnd(1);
		tableEnd(model);
		newline().divEnd();
		return toString();
	}

	private void rowStyleAndJavascript(TableModel model, String defaultStyleClass)
	{
		Row row = model.getRowHandler().getRow();
		String styleClass = row.getStyleClass();
		String style = row.getStyle();
		if (StringUtils.isBlank(styleClass))
			styleClass = defaultStyleClass;
		styleClass(styleClass);
		style(style);
		onclick(row.getOnclick());
		boolean highlightRow = row.isHighlightRow();
		if (highlightRow)
		{
			String highlightClass = row.getHighlightClass();
			if (StringUtils.isNotBlank(row.getOnmouseover()))
				onmouseover("this.className='" + highlightClass + "'; " + row.getOnmouseover());
			else
				onmouseover("this.className='" + highlightClass + "'");
			if (StringUtils.isNotBlank(row.getOnmouseout()))
				onmouseout("this.className='" + styleClass + "'; " + row.getOnmouseout());
			else
				onmouseout("this.className='" + styleClass + "'");
		} else
		{
			onmouseover(row.getOnmouseover());
			onmouseout(row.getOnmouseout());
		}
	}

	public void toolbarPlacement(TableModel model)
	{
		boolean showPagination = model.getTableHandler().getTable().isShowPagination();
		boolean showExports = model.getExportHandler().showExports();
		if (!showPagination && !showExports && StringUtils.isBlank(model.getTableHandler().getTable().getTitle()))
		{
			return;
		} else
		{
			table(0).border("0").cellPadding("0").cellSpacing("0");
			width(model.getTableHandler().getTable().getWidth()).close();
			tr(1).close();
			td(2).styleClass("title").close();
			title(model);
			tdEnd();
			td(2).align("right").close();
			toolbar(model);
			tdEnd();
			trEnd(1);
			tableEnd(0);
			newline();
			return;
		}
	}

	public void title(TableModel model)
	{
		String title = model.getTableHandler().getTable().getTitle();
		if (StringUtils.isNotBlank(title))
			span().close().append(title).spanEnd();
	}

	public void toolbar(TableModel model)
	{
		boolean showPagination = model.getTableHandler().getTable().isShowPagination();
		boolean showExports = model.getExportHandler().showExports();
		if (!showPagination && !showExports)
			return;
		table(2).border("0").cellPadding("0").cellSpacing("1").styleClass("toolbar").close();
		tr(3).close();
		toolbarFormStart(model);
		if (showPagination)
		{
			toolbarPaginationIcons(model);
			String separator = BuilderUtils.getImage(model, "separator");
			td(4).rowSpan("2").styleClass("separator").close().img(separator).tdEnd();
			rowsDisplayedDroplist(model);
			if (showExports)
				td(4).rowSpan("2").styleClass("separator").close().img(separator).tdEnd();
		}
		if (showExports)
			toolbarExportIcons(model);
		trEnd(3);
		tr(3).close();
		formEnd();
		trEnd(3);
		tableEnd(2);
		newline();
		tabs(2);
	}

	private void toolbarFormStart(TableModel model)
	{
		form();
		name(model.getTableHandler().prefixWithTableId() + "toolbar");
		String action = model.getTableHandler().getTable().getAction();
		if (StringUtils.isNotEmpty(action))
			action(action);
		close();
		String hiddenFields = TreeRegistryUtils.getFormHiddenFields(model, true, true, false, false);
		if (StringUtils.isNotEmpty(hiddenFields))
			append(hiddenFields);
	}

	public void toolbarPaginationIcons(TableModel model)
	{
		int page = model.getLimit().getPage();
		int totalPages = BuilderUtils.getTotalPages(model);
		td(4).close();
		if (!BuilderUtils.isFirstPageEnabled(page))
		{
			String firstPageImage = BuilderUtils.getImage(model, "firstPageDisabled");
			img(firstPageImage);
		} else
		{
			String firstPageImage = BuilderUtils.getImage(model, "firstPage");
			String firstPageTooltip = model.getMessages().getMessage("toolbar.tooltip.firstPage");
			paginationImage(model, 1, firstPageImage, firstPageTooltip);
		}
		tdEnd();
		td(4).close();
		if (!BuilderUtils.isPrevPageEnabled(page))
		{
			String prevPageImage = BuilderUtils.getImage(model, "prevPageDisabled");
			img(prevPageImage);
		} else
		{
			String prevPageImage = BuilderUtils.getImage(model, "prevPage");
			String prevPageTooltip = model.getMessages().getMessage("toolbar.tooltip.prevPage");
			paginationImage(model, page - 1, prevPageImage, prevPageTooltip);
		}
		tdEnd();
		td(4).close();
		if (!BuilderUtils.isNextPageEnabled(page, totalPages))
		{
			String nextPageImage = BuilderUtils.getImage(model, "nextPageDisabled");
			img(nextPageImage);
		} else
		{
			String nextPageImage = BuilderUtils.getImage(model, "nextPage");
			String nextPageTooltip = model.getMessages().getMessage("toolbar.tooltip.nextPage");
			paginationImage(model, page + 1, nextPageImage, nextPageTooltip);
		}
		tdEnd();
		td(4).close();
		if (!BuilderUtils.isLastPageEnabled(page, totalPages))
		{
			String lastPageImage = BuilderUtils.getImage(model, "lastPageDisabled");
			img(lastPageImage);
		} else
		{
			String lastPageImage = BuilderUtils.getImage(model, "lastPage");
			String lastPageTooltip = model.getMessages().getMessage("toolbar.tooltip.lastPage");
			paginationImage(model, totalPages, lastPageImage, lastPageTooltip);
		}
		tdEnd();
	}

	public void paginationImage(TableModel model, int page, String image, String tooltip)
	{
		a();
		quote();
		String action = model.getTableHandler().getTable().getAction();
		if (StringUtils.isNotEmpty(action))
			append(action);
		question().append(model.getTableHandler().prefixWithTableId()).append("p").equals().append(page);
		append(TreeRegistryUtils.getURLParameterString(model, true, true, false, true));
		quote().close();
		img(image, tooltip);
		aEnd();
	}

	public void toolbarExportIcons(TableModel model)
	{
		for (Iterator iter = model.getExportHandler().getExports().iterator(); iter.hasNext(); tdEnd())
		{
			td(4).close();
			Export export = (Export)iter.next();
			exportImage(model, export);
		}

	}

	public void rowsDisplayedDroplist(TableModel model)
	{
		int rowsDisplayed = model.getTableHandler().getTable().getRowsDisplayed();
		int medianRowsDisplayed = model.getTableHandler().getTable().getMedianRowsDisplayed();
		int maxRowsDisplayed = model.getTableHandler().getTable().getMaxRowsDisplayed();
		int currentRowsDisplayed = model.getLimit().getCurrentRowsDisplayed();
		td(4).width("20").close();
		newline();
		tabs(4);
		select().name(model.getTableHandler().prefixWithTableId() + "crd");
		StringBuffer onchange = new StringBuffer();
		onchange.append(HtmlViewUtils.paginationJavaScript(model));
		onchange(onchange.toString());
		close();
		newline();
		tabs(4);
		option().value(String.valueOf(rowsDisplayed));
		if (currentRowsDisplayed == rowsDisplayed)
			append(" selected");
		close();
		append(String.valueOf(rowsDisplayed));
		optionEnd();
		option().value(String.valueOf(medianRowsDisplayed));
		if (currentRowsDisplayed == medianRowsDisplayed)
			append(" selected");
		close();
		append(String.valueOf(medianRowsDisplayed));
		optionEnd();
		option().value(String.valueOf(maxRowsDisplayed));
		if (currentRowsDisplayed == maxRowsDisplayed)
			append(" selected");
		close();
		append(String.valueOf(maxRowsDisplayed));
		optionEnd();
		newline();
		tabs(4);
		selectEnd();
		img(BuilderUtils.getImage(model, "rowsDisplayed"));
		tdEnd();
	}

	private void exportImage(TableModel model, Export export)
	{
		a();
		quote();
		String action = model.getTableHandler().getTable().getAction();
		if (StringUtils.isNotEmpty(action))
			append(action);
		question().append("ec_eti").equals().append(model.getTableHandler().getTable().getTableId()).ampersand().append(model.getTableHandler().prefixWithTableId() + "ev").equals().append(export.getView()).ampersand().append(model.getTableHandler().prefixWithTableId() + "efn").equals().append(export.getFileName());
		append(TreeRegistryUtils.getURLParameterString(model, true, true, true, true));
		quote();
		close();
		String imageName = BuilderUtils.getImage(model, export.getImageName());
		if (StringUtils.isNotEmpty(imageName))
		{
			String tooltip = export.getTooltip();
			if (StringUtils.isEmpty(tooltip))
				img(imageName);
			else
				img(imageName, tooltip);
		} else
		{
			nbsp().append(export.getView()).nbsp();
		}
		aEnd();
	}

	public void statusBar(TableModel model)
	{
		if (!model.getTableHandler().getTable().isShowStatusBar() && !model.getTableHandler().getTable().isFilterable())
			return;
		tr(1).close();
		td(2).colSpan(String.valueOf(model.getColumnHandler().columnCount())).close();
		table(2).border("0").cellPadding("0").cellSpacing("0").width("100%").close();
		tr(3).close();
		if (model.getTableHandler().getTable().isShowStatusBar())
		{
			td(4).styleClass("statusBar").close();
			if (model.getLimit().getTotalRows() == 0)
			{
				append(model.getMessages().getMessage("statusbar.noResultsFound"));
			} else
			{
				Integer total = new Integer(model.getLimit().getTotalRows());
				Integer from = new Integer(model.getLimit().getRowStart() + 1);
				Integer to = new Integer(model.getLimit().getRowEnd());
				Object messageArguments[] = {
					total, from, to
				};
				append(model.getMessages().getMessage("statusbar.resultsFound", messageArguments));
			}
			tdEnd();
		}
		if (model.getTableHandler().getTable().isFilterable())
		{
			td(4).styleClass("filterButtons").close();
			String imageSearchArrow = BuilderUtils.getImage(model, "searchArrow");
			img(imageSearchArrow);
			nbsp();
			a().quote().append(HtmlViewUtils.filterJavaScript(model)).quote().close();
			String imageSearch = BuilderUtils.getImage(model, "search");
			if (StringUtils.isNotEmpty(imageSearch))
			{
				String searchTooltip = model.getMessages().getMessage("toolbar.tooltip.filter");
				img(imageSearch, searchTooltip);
			} else
			{
				append("&nbsp;Filter&nbsp;");
			}
			aEnd();
			nbsp();
			a().quote().append("javascript:document.forms." + model.getTableHandler().prefixWithTableId() + "filter." + model.getTableHandler().prefixWithTableId() + "f_" + "a" + ".value='" + "ca" + "';document.forms." + model.getTableHandler().prefixWithTableId() + "filter.submit()").quote().close();
			String imageClear = BuilderUtils.getImage(model, "clear");
			if (StringUtils.isNotEmpty(imageClear))
			{
				String clearTooltip = model.getMessages().getMessage("toolbar.tooltip.clear");
				img(imageClear, clearTooltip);
			} else
			{
				append("&nbsp;Clear&nbsp;");
			}
			aEnd();
			tdEnd();
		}
		trEnd(3);
		tableEnd(2);
		newline();
		tabs(2);
		tdEnd();
		trEnd(1);
		tabs(2);
		newline();
	}

	public void tableStart(TableModel model)
	{
		Table table = model.getTableHandler().getTable();
		table(0);
		id(table.getTableId());
		border(table.getBorder());
		cellSpacing(table.getCellspacing());
		cellPadding(table.getCellpadding());
		width(table.getWidth());
		styleClass(table.getStyleClass());
		style(table.getStyle());
		close();
	}

	public void tableEnd(TableModel model)
	{
		tableEnd(0);
	}

	public void header(TableModel model)
	{
		tr(1).close();
		List columns = model.getColumnHandler().getHeaderColumns();
		Column column;
		for (Iterator iter = columns.iterator(); iter.hasNext(); append(column.getCellDisplay()))
			column = (Column)iter.next();

		trEnd(1);
	}

	public void filter(TableModel model)
	{
		if (!model.getTableHandler().getTable().isFilterable())
			return;
		filterFormStart(model);
		tr(1).styleClass("filter").id("filter").close();
		List columns = model.getColumnHandler().getFilterColumns();
		Column column;
		for (Iterator iter = columns.iterator(); iter.hasNext(); append(column.getCellDisplay()))
			column = (Column)iter.next();

		trEnd(1);
		formEnd();
	}

	private void filterFormStart(TableModel model)
	{
		form();
		name(model.getTableHandler().prefixWithTableId() + "filter");
		String action = model.getTableHandler().getTable().getAction();
		if (StringUtils.isNotEmpty(action))
			action(action);
		close();
		input("hidden").name(model.getTableHandler().prefixWithTableId() + "f_" + "a").close();
		String hiddenFields = TreeRegistryUtils.getFormHiddenFields(model, false, true, false, true);
		if (StringUtils.isNotEmpty(hiddenFields))
			append(hiddenFields);
	}

	public void totals(TableModel model)
	{
		Column calcColumn = model.getColumnHandler().getFirstCalcColumn();
		if (calcColumn == null)
			return;
		String calcTitle[] = calcColumn.getCalcTitle();
		if (calcTitle != null && calcTitle.length > 0)
		{
			tr(1).close();
			td(2).styleClass("tableTotalTitle").colSpan(String.valueOf(model.getColumnHandler().columnCount())).close();
			for (int i = 0; i < calcTitle.length; i++)
			{
				String title = calcTitle[i];
				append(title);
			}

			tdEnd();
			trEnd(1);
		}
		tr(1).close();
		for (Iterator iter = model.getColumnHandler().getColumns().iterator(); iter.hasNext(); tdEnd())
		{
			Column column = (Column)iter.next();
			if (column.isCalculated())
			{
				td(2).styleClass("tableTotal").close();
				CalcResult calcValues[] = model.getColumnHandler().getCalcResults(column);
				for (int i = 0; i < calcValues.length; i++)
				{
					CalcResult calcValue = calcValues[i];
					Number value = calcValue.getValue();
					append(ExtremeUtils.formatNumber(column.getFormat(), value, model.getLocale()));
					if (calcValues.length > 0 && i + 1 != calcValues.length)
						append(" / ");
				}

			} else
			{
				td(2).styleClass("tableTotalEmpty").close();
				nbsp();
			}
		}

		trEnd(1);
	}
}
