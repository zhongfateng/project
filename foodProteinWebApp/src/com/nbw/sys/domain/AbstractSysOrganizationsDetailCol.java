package com.nbw.sys.domain;

/**
 * AbstractSysOrganizationsDetailCol entity provides the base persistence
 * definition of the SysOrganizationsDetailCol entity. 
 * @author 狄巨礼 May 17, 2010 09:31:00 AM
 */

public abstract class AbstractSysOrganizationsDetailCol implements
		java.io.Serializable {

	// Fields

	private String colId;
	private String colName;
	private Integer enabled;
	private Integer filled;
	private Integer length;
	private Integer orderNum;
	private String showForm;
	private String dataSources;
	private String type;
	private String colItems;

	// Constructors

	/** default constructor */
	public AbstractSysOrganizationsDetailCol() {
	}

	/** minimal constructor */
	public AbstractSysOrganizationsDetailCol(String colId) {
		this.colId = colId;
	}

	public AbstractSysOrganizationsDetailCol(String colId, String colName,
			Integer enabled, Integer filled, Integer length, Integer orderNum,
			String showForm, String dataSources, String type, String colItems) {
		super();
		this.colId = colId;
		this.colName = colName;
		this.enabled = enabled;
		this.filled = filled;
		this.length = length;
		this.orderNum = orderNum;
		this.showForm = showForm;
		this.dataSources = dataSources;
		this.type = type;
		this.colItems = colItems;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public Integer getFilled() {
		return filled;
	}

	public void setFilled(Integer filled) {
		this.filled = filled;
	}

	public String getColId() {
		return this.colId;
	}

	public void setColId(String colId) {
		this.colId = colId;
	}

	public String getColName() {
		return this.colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public Integer getLength() {
		return this.length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getOrderNum() {
		return this.orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getShowForm() {
		return this.showForm;
	}

	public void setShowForm(String showForm) {
		this.showForm = showForm;
	}

	public String getDataSources() {
		return this.dataSources;
	}

	public void setDataSources(String dataSources) {
		this.dataSources = dataSources;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getColItems() {
		return colItems==null?"":colItems;
	}

	public void setColItems(String colItems) {
		this.colItems = colItems;
	}

}