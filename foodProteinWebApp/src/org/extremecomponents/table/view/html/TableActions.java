// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TableActions.java

package org.extremecomponents.table.view.html;

import org.apache.commons.lang.StringUtils;
import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.limit.Sort;

// Referenced classes of package org.extremecomponents.table.view.html:
//			BuilderUtils

public class TableActions
{

	private TableModel model;

	public TableActions(TableModel model)
	{
		this.model = model;
	}

	protected TableModel getTableModel()
	{
		return model;
	}

	public String getOnInvokeAction()
	{
		String onInvokeAction = model.getTableHandler().getTable().getOnInvokeAction();
		if (StringUtils.isNotBlank(onInvokeAction))
			return onInvokeAction;
		else
			return getSubmitAction();
	}

	public String getSubmitAction()
	{
		StringBuffer result = new StringBuffer();
		String form = BuilderUtils.getForm(model);
		String action = model.getTableHandler().getTable().getAction();
		result.append("document.forms.").append(form).append(".setAttribute('action','").append(action).append("');");
		result.append("document.forms.").append(form).append(".target=").append("'';");
		String method = model.getTableHandler().getTable().getMethod();
		result.append("document.forms.").append(form).append(".setAttribute('method','").append(method).append("');");
		result.append("document.forms.").append(form).append(".submit()");
		return result.toString();
	}

	public String getFormParameter(String name, String value)
	{
		StringBuffer result = new StringBuffer();
		String form = BuilderUtils.getForm(model);
		result.append("document.forms.").append(form).append(".");
		result.append(model.getTableHandler().prefixWithTableId()).append(name);
		result.append(".value='").append(value).append("';");
		return result.toString();
	}

	public String getExportTableIdParameter(String value)
	{
		StringBuffer result = new StringBuffer();
		String form = BuilderUtils.getForm(model);
		result.append("document.forms.").append(form).append(".");
		result.append("ec_eti");
		result.append(".value='").append(value).append("';");
		return result.toString();
	}

	private String getSubmitAction1()
	{
		StringBuffer result = new StringBuffer();
		String form = BuilderUtils.getForm(model);
		String action = model.getTableHandler().getTable().getAction();
		result.append("document.forms.").append(form).append(".setAttribute('action','").append(action).append("');");
		result.append("document.forms.").append(form).append(".setAttribute('target','").append(model.getTableHandler().prefixWithTableId() + "iframeExport").append("');");
		String method = model.getTableHandler().getTable().getMethod();
		result.append("document.forms.").append(form).append(".setAttribute('method','").append(method).append("');");
		result.append("document.forms.").append(form).append(".submit()");
		return result.toString();
	}

	public String getExportAction(String exportView, String exportFileName)
	{
		StringBuffer action = new StringBuffer("javascript:");
		action.append(getExportTableIdParameter(model.getTableHandler().getTable().getTableId()));
		action.append(getFormParameter("ev", exportView));
		action.append(getFormParameter("efn", exportFileName));
		action.append(getSubmitAction1());
		return action.toString();
	}

	public String getPageAction(int page)
	{
		StringBuffer action = new StringBuffer("javascript:");
		action.append(getClearedExportTableIdParameters());
		action.append(getFormParameter("p", String.valueOf(page)));
		action.append(getOnInvokeAction());
		return action.toString();
	}

	public String getFilterAction()
	{
		StringBuffer action = new StringBuffer("javascript:");
		action.append(getClearedExportTableIdParameters());
		action.append(getFormParameter("f_a", "fa"));
		action.append(getFormParameter("p", "1"));
		action.append(getOnInvokeAction());
		return action.toString();
	}

	public String getClearAction()
	{
		StringBuffer action = new StringBuffer("javascript:");
		action.append(getClearedExportTableIdParameters());
		action.append(getFormParameter("f_a", "ca"));
		action.append(getFormParameter("p", "1"));
		action.append(getOnInvokeAction());
		return action.toString();
	}

	public String getSortAction(Column column, String sortOrder)
	{
		StringBuffer action = new StringBuffer("javascript:");
		Sort sort = model.getLimit().getSort();
		if (sort.isSorted())
			action.append(getFormParameter("s_" + sort.getAlias(), ""));
		action.append(getClearedExportTableIdParameters());
		action.append(getFormParameter("s_" + column.getAlias(), sortOrder));
		action.append(getFormParameter("p", "1"));
		action.append(getOnInvokeAction());
		return action.toString();
	}

	public String getRowsDisplayedAction()
	{
		StringBuffer action = new StringBuffer("javascript:");
		action.append(getClearedExportTableIdParameters());
		action.append(getRowsDisplayedFormParameter("crd"));
		action.append(getOnInvokeAction());
		return action.toString();
	}

	public String getClearedExportTableIdParameters()
	{
		if (BuilderUtils.showExports(model))
			return getExportTableIdParameter("");
		else
			return "";
	}

	protected String getRowsDisplayedFormParameter(String name)
	{
		StringBuffer result = new StringBuffer();
		String form = BuilderUtils.getForm(model);
		String selectedOption = "this.options[this.selectedIndex].value";
		result.append("document.forms.").append(form).append(".");
		result.append(model.getTableHandler().prefixWithTableId()).append(name);
		result.append(".value=").append(selectedOption).append(";");
		return result.toString();
	}

	public String getFormParameter(String name)
	{
		StringBuffer result = new StringBuffer();
		String form = BuilderUtils.getForm(model);
		result.append("document.forms.").append(form).append(".");
		result.append(model.getTableHandler().prefixWithTableId()).append(name);
		result.append(".value");
		return result.toString();
	}

	public String getPageGoToAction()
	{
		String form = BuilderUtils.getForm(model);
		StringBuffer result = new StringBuffer("function " + model.getTableHandler().prefixWithTableId() + "pagegoto" + "_onblur(){");
		int tempnum = 0;
		if (model.getLimit().getTotalRows() % model.getLimit().getCurrentRowsDisplayed() > 0)
			tempnum = 1;
		int pagenum = model.getLimit().getTotalRows() / model.getLimit().getCurrentRowsDisplayed() + tempnum;
		int curpage = model.getLimit().getPage();
		result.append("if(" + getFormParameter("pagegoto") + "!=\"\"){");
		result.append("if(parseInt(" + getFormParameter("pagegoto"));
		result.append(")>" + pagenum + "){alert('输入的页数不能大于总页数');");
		result.append("document.forms.").append(form).append(".");
		result.append(model.getTableHandler().prefixWithTableId()).append("pagegoto");
		result.append(".focus();return false;}");
		result.append("if(" + getFormParameter("pagegoto") + ".substring(0,1)==\"0\"){");
		result.append("alert('输入的数字不正确');");
		result.append("document.forms.").append(form).append(".");
		result.append(model.getTableHandler().prefixWithTableId()).append("pagegoto");
		result.append(".focus();return false;}");
		result.append("if(" + getFormParameter("pagegoto") + "==\"" + curpage + "\"){return false;}");
		result.append(getClearedExportTableIdParameters());
		result.append(getFormParameter("p") + "=" + getFormParameter("pagegoto") + ";");
		result.append(getOnInvokeAction());
		result.append("}else{alert('页数不能为空!');");
		result.append("document.forms.").append(form).append(".");
		result.append(model.getTableHandler().prefixWithTableId()).append("pagegoto");
		result.append(".focus();return false;}}");
		return result.toString();
	}
}
