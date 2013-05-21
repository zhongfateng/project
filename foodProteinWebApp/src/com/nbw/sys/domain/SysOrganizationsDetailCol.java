package com.nbw.sys.domain;

import javax.servlet.http.HttpServletRequest;

import com.nbw.common.util.CommonUtils;
import com.nbw.common.util.DateUtils;

/**
 * SysOrganizationsDetailCol entity. 
 * @author 狄巨礼 May 17, 2010 09:31:00 AM
 */
public class SysOrganizationsDetailCol extends
		AbstractSysOrganizationsDetailCol implements java.io.Serializable {
	
	public static int ENABLE_TRUE = 1;
	public static int ENABLE_FALSE = -1;
	public static int FILLED_TRUE = 1;
	public static int FILLED_FALSE = -1;
	public static String TYPE_STRING = "String";
	public static String TYPE_DATE = "Date";
	public static String TYPE_FLOAT = "Float";
	public static String SHOW_FORM_SELECT = "select";
	public static String SHOW_FORM_TEXT = "text";
	public static String SHOW_FORM_CHECKBOX = "checkbox";
	public static String SHOW_FORM_RADIO = "radio";
	public static String SPLIT_FLAG=",";

	// Constructors

	/** default constructor */
	public SysOrganizationsDetailCol() {
	}

	/** minimal constructor */
	public SysOrganizationsDetailCol(String colId) {
		super(colId);
	}

	public SysOrganizationsDetailCol(String colId, String colName,
			Integer enabled, Integer filled, Integer length, Integer orderNum,
			String showForm, String dataSources, String type, String colItems) {
		super(colId, colName, enabled, filled, length, orderNum, showForm, dataSources,
				type, colItems);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 获取要显示到页面的html代码
	 * @return
	 */
	public String getShowHtml(){
		StringBuffer html = new StringBuffer();
		if(this.getShowForm().equals(this.SHOW_FORM_CHECKBOX)){
			String [] items = this.getColItems().split(",");
			html.append("<table><tr><td>"+this.getColName()+"</td><td>");
			for(int i=0;i<items.length;i++){
				html.append("<input type='checkbox' name='att"+this.getDataSources()+"' value='"+items[i]+"'>"+items[i]+"<br>");
			}
			html.append("</td><tr></table>");
		}else if(this.getShowForm().equals(this.SHOW_FORM_SELECT)){
			String [] items = this.getColItems().split(",");
			html.append("<table><tr><td>"+this.getColName()+"</td><td>");
			html.append("<select name='att"+this.getDataSources()+"' id='att"+this.getDataSources()+"'>");
			for(int i=0;i<items.length;i++){
				html.append("<option value='"+items[i]+"'>"+items[i]+"</option>");
			}
			html.append("</select>");
			html.append("</td><tr></table>");
		}else if(this.getShowForm().equals(this.SHOW_FORM_RADIO)){
			String [] items = this.getColItems().split(",");
			html.append("<table><tr><td>"+this.getColName()+"</td><td>");
			for(int i=0;i<items.length;i++){
				html.append("<input type='radio' name='att"+this.getDataSources()+"' value='"+items[i]+"'>"+items[i]+"<br>");
			}
			html.append("</td><tr></table>");
		}else{
			//以文本框显示 
			if(this.getType().equals(this.TYPE_DATE)){
				html.append("<table><tr><td>"+this.getColName()+"</td><td>");
				html.append("<input type='text' name='att"+this.getDataSources()+"' id='att"+this.getDataSources()+"'  value='"+this.getColItems()+"' onfocus='WdatePicker({dateFmt:\"yyyy-MM-dd HH:mm:ss\"})' class='Wdate' style='width:150px'/>");
				html.append("</td><tr></table>");
			}else{
				html.append("<table><tr><td>"+this.getColName()+"</td><td>");
				html.append("<input type='text' name='att"+this.getDataSources()+"' id='att"+this.getDataSources()+"' size='"+this.getLength()+"' value='"+this.getColItems()+"'><br>");
				html.append("</td><tr></table>");
			}
		} 
		return html.toString();
	}

	/**
	 * 获取页面传过来的值
	 * 返回相应的object 类型可以Date Stirng Double
	 */
	public Object getValueByRequest(HttpServletRequest request) {
		StringBuffer values = new StringBuffer();
		if(this.getShowForm().equals(this.SHOW_FORM_CHECKBOX)){
			String [] items = request.getParameterValues("att"+this.getDataSources());
			if(items != null){
				for(int i=0;i<items.length;i++){
					values.append(items[i]+SPLIT_FLAG);
				}
			}
		}else{
			String value =request.getParameter("att"+this.getDataSources());
			if(this.getType().equals(this.TYPE_DATE)){
				return DateUtils.stringToDatetime(value);
			}else if(this.getType().equals(this.TYPE_FLOAT)){
				return CommonUtils.StringToDouble(value); 
			}
			values.append(value==null?"":value);
		} 
		return values.toString();
	}
	
	/**
	 * 获取js验证代码
	 * @return
	 */
	public String getJsCheck(){
		StringBuffer html = new StringBuffer();
		html.append(" var att"+this.getDataSources()+" = document.getElementById('att"+this.getDataSources()+"'); ");
		if(this.getFilled()==FILLED_TRUE){
			if(this.getShowForm().equals(this.SHOW_FORM_RADIO) || this.getShowForm().equals(this.SHOW_FORM_CHECKBOX)){
				html.append("	var flag"+this.getDataSources()+" = false;" +
						"		for(var i=0;i<att"+this.getDataSources()+".length;i++){" +
						"			if(att"+this.getDataSources()+"[i].checked){" +
						"				flag"+this.getDataSources()+" = true;break;" +
						"			}" +
						"		}" +
						"		if(flag"+this.getDataSources()+"==false){" +
						"			ymPrompt.errorInfo({title:'提示',message:'"+this.getColName()+"为必选项！'});" +
						"			return false;" +
						"		}");
			}else if(this.getShowForm().equals(this.SHOW_FORM_TEXT)){
				//以文本框显示 
				html.append(" 	if(isNotEmptyText(att"+this.getDataSources()+".value,'"+this.getColName()+"')==false){" +
						"			return false;" +
						"		} ");
			} 
		}
		//如果类型是数字验证是否是数字
		if(this.getType().equals(this.TYPE_FLOAT)){
			html.append(" 	if(isNaN(att"+this.getDataSources()+".value)){" +
					"			ymPrompt.errorInfo({title:'提示',message:'"+this.getColName()+"只能输入数字！'});" +
					"			return false;" +
					"		} ");
		}
		//如果是文本格式验证长度
		if(this.getShowForm().equals(this.SHOW_FORM_TEXT)){
			html.append(" 	if(att"+this.getDataSources()+".value.length > "+this.getLength()+"){" +
					"			ymPrompt.errorInfo({title:'提示',message:'"+this.getColName()+"长度不得大于"+this.getLength()+"！'});" +
					"			return false;" +
					"		} ");
		}
		
		
		return html.toString();
	}

}
