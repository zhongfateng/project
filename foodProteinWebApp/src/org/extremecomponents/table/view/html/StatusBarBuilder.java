// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   StatusBarBuilder.java

package org.extremecomponents.table.view.html;

import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.util.HtmlBuilder;

// Referenced classes of package org.extremecomponents.table.view.html:
//			TableActions, BuilderUtils

public class StatusBarBuilder
{

	private HtmlBuilder html;
	private TableModel model;

	public StatusBarBuilder(TableModel model)
	{
		this(new HtmlBuilder(), model);
	}

	public StatusBarBuilder(HtmlBuilder html, TableModel model)
	{
		this.html = html;
		this.model = model;
	}

	public HtmlBuilder getHtmlBuilder()
	{
		return html;
	}

	protected TableModel getTableModel()
	{
		return model;
	}

	public void statusMessage()
	{
		if (model.getLimit().getTotalRows() == 0)
		{
			html.append(model.getMessages().getMessage("statusbar.noResultsFound"));
		} else
		{
			Integer total = new Integer(model.getLimit().getTotalRows());
			int tempnum = 0;
			if (model.getLimit().getTotalRows() % model.getLimit().getCurrentRowsDisplayed() > 0)
				tempnum = 1;
			int pagenum = model.getLimit().getTotalRows() / model.getLimit().getCurrentRowsDisplayed() + tempnum;
			Integer from = new Integer(pagenum);
			Object messageArguments[] = {
				total, from
			};
			html.append(model.getMessages().getMessage("statusbar.resultsFound", messageArguments));
			html.append("当前第");
			textPage();
			html.append("页");
			html.input("text").name(model.getTableHandler().prefixWithTableId() + "tmp_aa_bb");
			html.readonly();
			html.style("border:0px;background:transparent;filter:Alpha(Opacity=0);opacity:0;width=1;cursor:default");
			html.close();
		}
	}

	public void textPage()
	{
		TableActions action = new TableActions(model);
		StringBuffer result = new StringBuffer();
		String form = BuilderUtils.getForm(model);
		result.append("document.forms.").append(form).append(".");
		result.append(model.getTableHandler().prefixWithTableId()).append("tmp_aa_bb");
		result.append(".focus();");
		html.input("text").name(model.getTableHandler().prefixWithTableId() + "pagegoto");
		html.onkeyup("value=value.replace(/[^\\d]/g,'')");
		html.value((new StringBuffer(String.valueOf(model.getLimit().getPage()))).toString());
		html.onblur(model.getTableHandler().prefixWithTableId() + "pagegoto" + "_onblur()");
		html.onkeydown("if(event.keyCode==13)" + result.toString());
		html.close();
		html.script(action.getPageGoToAction());
		html.scriptEnd();
	}

	public String toString()
	{
		return html.toString();
	}
}
