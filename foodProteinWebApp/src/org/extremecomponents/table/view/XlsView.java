// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   XlsView.java

package org.extremecomponents.table.view;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.calc.CalcResult;
import org.extremecomponents.table.calc.CalcUtils;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.util.ExtremeUtils;

// Referenced classes of package org.extremecomponents.table.view:
//			View, ExportViewUtils

public class XlsView
	implements View
{

	private static Log logger;
	public static final int WIDTH_MULT = 240;
	public static final int MIN_CHARS = 8;
	public static final short DEFAULT_FONT_HEIGHT = 8;
	public static final double NON_NUMERIC = -0.99999000000000005D;
	public static final String DEFAULT_MONEY_FORMAT = "$###,###,##0.00";
	public static final String DEFAULT_PERCENT_FORMAT = "##0.0%";
	public static final String NBSP = "&nbsp;";
	private HSSFWorkbook wb;
	private HSSFSheet sheet;
	private HSSFPrintSetup ps;
	private Map styles;
	private short rownum;
	private short cellnum;
	private HSSFRow hssfRow;
	private String moneyFormat;
	private String percentFormat;
	private String encoding;

	public XlsView()
	{
	}

	public void beforeBody(TableModel model)
	{
		logger.debug("XlsView.init()");
		moneyFormat = model.getPreferences().getPreference("table.exportableformat.money");
		if (StringUtils.isEmpty(moneyFormat))
			moneyFormat = "$###,###,##0.00";
		percentFormat = model.getPreferences().getPreference("table.exportableformat.percent");
		if (StringUtils.isEmpty(percentFormat))
			percentFormat = "##0.0%";
		encoding = model.getExportHandler().getCurrentExport().getEncoding();
		wb = new HSSFWorkbook();
		sheet = wb.createSheet();
		if (encoding.equalsIgnoreCase("UTF"))
			wb.setSheetName(0, "Export Workbook");
		else
		if (encoding.equalsIgnoreCase("UNICODE"))
			wb.setSheetName(0, "Export Workbook");
		styles = initStyles(wb);
		ps = sheet.getPrintSetup();
		sheet.setAutobreaks(true);
		ps.setFitHeight((short)1);
		ps.setFitWidth((short)1);
		createHeader(model);
	}

	public void body(TableModel model, Column column)
	{
		if (column.isFirstColumn())
		{
			rownum++;
			cellnum = 0;
			hssfRow = sheet.createRow(rownum);
		}
		String value = ExportViewUtils.parseXLS(column.getCellDisplay());
		HSSFCell hssfCell = hssfRow.createCell(cellnum);
		setCellEncoding(hssfCell);
		if (column.isEscapeAutoFormat())
			writeToCellAsText(hssfCell, value, "");
		else
			writeToCellFormatted(hssfCell, value, "");
		cellnum++;
	}

	public Object afterBody(TableModel model)
	{
		if (model.getLimit().getTotalRows() != 0)
			totals(model);
		return wb;
	}

	private void createHeader(TableModel model)
	{
		rownum = 0;
		cellnum = 0;
		HSSFRow row = sheet.createRow(rownum);
		List columns = model.getColumnHandler().getHeaderColumns();
		for (Iterator iter = columns.iterator(); iter.hasNext();)
		{
			Column column = (Column)iter.next();
			String title = column.getCellDisplay();
			HSSFCell hssfCell = row.createCell(cellnum);
			setCellEncoding(hssfCell);
			hssfCell.setCellStyle((HSSFCellStyle)styles.get("titleStyle"));
			hssfCell.setCellType(1);
			hssfCell.setCellValue(title);
			int valWidth = (new StringBuffer(String.valueOf(title))).toString().length() * 240;
			sheet.setColumnWidth(hssfCell.getCellNum(), (short)valWidth);
			cellnum++;
		}

	}

	private void writeToCellAsText(HSSFCell cell, String value, String styleModifier)
	{
		if (value.trim().equals("&nbsp;"))
			value = "";
		cell.setCellStyle((HSSFCellStyle)styles.get("textStyle" + styleModifier));
		fixWidthAndPopulate(cell, -0.99999000000000005D, value);
	}

	private void writeToCellFormatted(HSSFCell cell, String value, String styleModifier)
	{
		double numeric = -0.99999000000000005D;
		try
		{
			numeric = Double.parseDouble(value);
		}
		catch (Exception e)
		{
			numeric = -0.99999000000000005D;
		}
		if (value.startsWith("$") || value.endsWith("%") || value.startsWith("($"))
		{
			boolean moneyFlag = value.startsWith("$") || value.startsWith("($");
			boolean percentFlag = value.endsWith("%");
			value = StringUtils.replace(value, "$", "");
			value = StringUtils.replace(value, "%", "");
			value = StringUtils.replace(value, ",", "");
			value = StringUtils.replace(value, "(", "-");
			value = StringUtils.replace(value, ")", "");
			try
			{
				numeric = Double.parseDouble(value);
			}
			catch (Exception e)
			{
				numeric = -0.99999000000000005D;
			}
			cell.setCellType(0);
			if (moneyFlag)
				cell.setCellStyle((HSSFCellStyle)styles.get("moneyStyle" + styleModifier));
			else
			if (percentFlag)
			{
				numeric /= 100D;
				cell.setCellStyle((HSSFCellStyle)styles.get("percentStyle" + styleModifier));
			}
		} else
		if (numeric != -0.99999000000000005D)
		{
			cell.setCellStyle((HSSFCellStyle)styles.get("numericStyle" + styleModifier));
		} else
		{
			if (value.trim().equals("&nbsp;"))
				value = "";
			cell.setCellStyle((HSSFCellStyle)styles.get("textStyle" + styleModifier));
		}
		fixWidthAndPopulate(cell, numeric, value);
	}

	private void fixWidthAndPopulate(HSSFCell cell, double numeric, String value)
	{
		int valWidth = 0;
		if (numeric != -0.99999000000000005D)
		{
			cell.setCellValue(numeric);
			valWidth = (cell.getNumericCellValue() + "$,.").length() * 240;
		} else
		{
			cell.setCellValue(value);
			valWidth = (new StringBuffer(String.valueOf(cell.getStringCellValue()))).toString().length() * 240;
			if (valWidth < 1920)
				valWidth = 1920;
		}
		if (valWidth > sheet.getColumnWidth(cell.getCellNum()))
			sheet.setColumnWidth(cell.getCellNum(), (short)valWidth);
	}

	private Map initStyles(HSSFWorkbook wb)
	{
		return initStyles(wb, (short)8);
	}

	private Map initStyles(HSSFWorkbook wb, short fontHeight)
	{
		Map result = new HashMap();
		HSSFCellStyle titleStyle = wb.createCellStyle();
		HSSFCellStyle textStyle = wb.createCellStyle();
		HSSFCellStyle boldStyle = wb.createCellStyle();
		HSSFCellStyle numericStyle = wb.createCellStyle();
		HSSFCellStyle numericStyleBold = wb.createCellStyle();
		HSSFCellStyle moneyStyle = wb.createCellStyle();
		HSSFCellStyle moneyStyleBold = wb.createCellStyle();
		HSSFCellStyle percentStyle = wb.createCellStyle();
		HSSFCellStyle percentStyleBold = wb.createCellStyle();
		HSSFCellStyle moneyStyle_Totals = wb.createCellStyle();
		HSSFCellStyle naStyle_Totals = wb.createCellStyle();
		HSSFCellStyle numericStyle_Totals = wb.createCellStyle();
		HSSFCellStyle percentStyle_Totals = wb.createCellStyle();
		HSSFCellStyle textStyle_Totals = wb.createCellStyle();
		result.put("titleStyle", titleStyle);
		result.put("textStyle", textStyle);
		result.put("boldStyle", boldStyle);
		result.put("numericStyle", numericStyle);
		result.put("numericStyleBold", numericStyleBold);
		result.put("moneyStyle", moneyStyle);
		result.put("moneyStyleBold", moneyStyleBold);
		result.put("percentStyle", percentStyle);
		result.put("percentStyleBold", percentStyleBold);
		result.put("moneyStyle_Totals", moneyStyle_Totals);
		result.put("naStyle_Totals", naStyle_Totals);
		result.put("numericStyle_Totals", numericStyle_Totals);
		result.put("percentStyle_Totals", percentStyle_Totals);
		result.put("textStyle_Totals", textStyle_Totals);
		HSSFDataFormat format = wb.createDataFormat();
		HSSFFont font = wb.createFont();
		font.setBoldweight((short)400);
		font.setColor((short)8);
		font.setFontName("Arial");
		font.setFontHeightInPoints(fontHeight);
		HSSFFont fontBold = wb.createFont();
		fontBold.setBoldweight((short)700);
		fontBold.setColor((short)8);
		fontBold.setFontName("Arial");
		fontBold.setFontHeightInPoints(fontHeight);
		moneyStyle.setFont(font);
		moneyStyle.setAlignment((short)3);
		moneyStyle.setDataFormat(format.getFormat(moneyFormat));
		moneyStyleBold.setFont(fontBold);
		moneyStyleBold.setAlignment((short)3);
		moneyStyleBold.setDataFormat(format.getFormat(moneyFormat));
		percentStyle.setFont(font);
		percentStyle.setAlignment((short)3);
		percentStyle.setDataFormat(format.getFormat(percentFormat));
		percentStyleBold.setFont(fontBold);
		percentStyleBold.setAlignment((short)3);
		percentStyleBold.setDataFormat(format.getFormat(percentFormat));
		numericStyle.setFont(font);
		numericStyle.setAlignment((short)3);
		numericStyleBold.setFont(fontBold);
		numericStyleBold.setAlignment((short)3);
		titleStyle.setFont(font);
		titleStyle.setFillForegroundColor((short)22);
		titleStyle.setFillPattern((short)1);
		titleStyle.setBorderBottom((short)1);
		titleStyle.setBottomBorderColor((short)8);
		titleStyle.setBorderLeft((short)1);
		titleStyle.setLeftBorderColor((short)8);
		titleStyle.setBorderRight((short)1);
		titleStyle.setRightBorderColor((short)8);
		titleStyle.setBorderTop((short)1);
		titleStyle.setTopBorderColor((short)8);
		titleStyle.setAlignment((short)2);
		titleStyle.setVerticalAlignment((short)1);
		textStyle.setFont(font);
		textStyle.setWrapText(true);
		boldStyle.setFont(fontBold);
		boldStyle.setWrapText(true);
		moneyStyle_Totals.setFont(fontBold);
		moneyStyle_Totals.setFillForegroundColor((short)22);
		moneyStyle_Totals.setFillPattern((short)1);
		moneyStyle_Totals.setBorderBottom((short)1);
		moneyStyle_Totals.setBottomBorderColor((short)8);
		moneyStyle_Totals.setBorderTop((short)1);
		moneyStyle_Totals.setTopBorderColor((short)8);
		moneyStyle_Totals.setAlignment((short)3);
		moneyStyle_Totals.setVerticalAlignment((short)1);
		moneyStyle_Totals.setDataFormat(format.getFormat(moneyFormat));
		naStyle_Totals.setFont(fontBold);
		naStyle_Totals.setFillForegroundColor((short)22);
		naStyle_Totals.setFillPattern((short)1);
		naStyle_Totals.setBorderBottom((short)1);
		naStyle_Totals.setBottomBorderColor((short)8);
		naStyle_Totals.setBorderTop((short)1);
		naStyle_Totals.setTopBorderColor((short)8);
		naStyle_Totals.setAlignment((short)3);
		naStyle_Totals.setVerticalAlignment((short)1);
		numericStyle_Totals.setFont(fontBold);
		numericStyle_Totals.setFillForegroundColor((short)22);
		numericStyle_Totals.setFillPattern((short)1);
		numericStyle_Totals.setBorderBottom((short)1);
		numericStyle_Totals.setBottomBorderColor((short)8);
		numericStyle_Totals.setBorderTop((short)1);
		numericStyle_Totals.setTopBorderColor((short)8);
		numericStyle_Totals.setAlignment((short)3);
		numericStyle_Totals.setVerticalAlignment((short)1);
		percentStyle_Totals.setFont(fontBold);
		percentStyle_Totals.setFillForegroundColor((short)22);
		percentStyle_Totals.setFillPattern((short)1);
		percentStyle_Totals.setBorderBottom((short)1);
		percentStyle_Totals.setBottomBorderColor((short)8);
		percentStyle_Totals.setBorderTop((short)1);
		percentStyle_Totals.setTopBorderColor((short)8);
		percentStyle_Totals.setAlignment((short)3);
		percentStyle_Totals.setVerticalAlignment((short)1);
		percentStyle_Totals.setDataFormat(format.getFormat(percentFormat));
		textStyle_Totals.setFont(fontBold);
		textStyle_Totals.setFillForegroundColor((short)22);
		textStyle_Totals.setFillPattern((short)1);
		textStyle_Totals.setBorderBottom((short)1);
		textStyle_Totals.setBottomBorderColor((short)8);
		textStyle_Totals.setBorderTop((short)1);
		textStyle_Totals.setTopBorderColor((short)8);
		textStyle_Totals.setAlignment((short)1);
		textStyle_Totals.setVerticalAlignment((short)1);
		return result;
	}

	public void totals(TableModel model)
	{
		Column firstCalcColumn = model.getColumnHandler().getFirstCalcColumn();
		if (firstCalcColumn != null)
		{
			int rows = firstCalcColumn.getCalc().length;
			for (int i = 0; i < rows; i++)
			{
				rownum++;
				HSSFRow row = sheet.createRow(rownum);
				cellnum = 0;
				for (Iterator iter = model.getColumnHandler().getColumns().iterator(); iter.hasNext();)
				{
					Column column = (Column)iter.next();
					if (column.isFirstColumn())
					{
						String calcTitle = CalcUtils.getFirstCalcColumnTitleByPosition(model, i);
						HSSFCell cell = row.createCell(cellnum);
						setCellEncoding(cell);
						if (column.isEscapeAutoFormat())
							writeToCellAsText(cell, calcTitle, "_Totals");
						else
							writeToCellFormatted(cell, calcTitle, "_Totals");
						cellnum++;
					} else
					if (column.isCalculated())
					{
						CalcResult calcResult = CalcUtils.getCalcResultsByPosition(model, column, i);
						Number value = calcResult.getValue();
						HSSFCell cell = row.createCell(cellnum);
						setCellEncoding(cell);
						if (value != null)
						{
							if (column.isEscapeAutoFormat())
								writeToCellAsText(cell, value.toString(), "_Totals");
							else
								writeToCellFormatted(cell, ExtremeUtils.formatNumber(column.getFormat(), value, model.getLocale()), "_Totals");
						} else
						{
							cell.setCellStyle((HSSFCellStyle)styles.get("naStyle_Totals"));
							cell.setCellValue("n/a");
						}
						cellnum++;
					} else
					{
						HSSFCell cell = row.createCell(cellnum);
						setCellEncoding(cell);
						writeToCellFormatted(cell, "", "_Totals");
						cellnum++;
					}
				}

			}

		}
	}

	private void setCellEncoding(HSSFCell cell)
	{
	}

	static 
	{
		logger = LogFactory.getLog(org.extremecomponents.table.view.XlsView.class);
	}
}
