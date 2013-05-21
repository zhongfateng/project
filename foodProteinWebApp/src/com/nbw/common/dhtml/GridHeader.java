package com.nbw.common.dhtml;

public class GridHeader {
	private String width;
	private String type;
	private String align;
	private String sort;
	private String color;
	private String value;
	private String format;
	private boolean hidden;

	public GridHeader() {
		super();
		this.width = "100";
		this.type = "ed";
		this.align = "left";
//		this.sort = "str";
	}

	public GridHeader(String value) {
		super();
		this.width = "100";
		this.type = "ed";
		this.align = "left";
		this.sort = "str";
		this.value = value;
	}

	public GridHeader(String width, String type, String align, String sort,
			String value) {
		super();
		this.width = width;
		this.type = type;
		this.align = align;
		this.sort = sort;
		this.value = value;
	}

	public GridHeader(String width, String type, String align, String sort,
			String color, String value, String format, boolean hidden) {
		super();
		this.width = width;
		this.type = type;
		this.align = align;
		this.sort = sort;
		this.color = color;
		this.value = value;
		this.format = format;
		this.hidden = hidden;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("<column ");
		if (null != this.getWidth() && !"".equals(this.getWidth())) {
			sb.append(" width='");
			sb.append(this.getWidth());
		}
		if (null != this.getType() && !"".equals(this.getType())) {
			sb.append("' type='");
			sb.append(this.getType());
		}
		if (null != this.getFormat() && !"".equals(this.getFormat())) {
			sb.append("' format='");
			sb.append(this.getFormat());
		}
		if (null != this.getAlign() && !"".equals(this.getAlign())) {
			sb.append("' align='");
			sb.append(this.getAlign());
		}
		if (null != this.getSort() && !"".equals(this.getSort())) {
			sb.append("' sort='");
			sb.append(this.getSort());
		}
		if (null != this.getColor() && !"".equals(this.getColor())) {
			sb.append("' color='");
			sb.append(this.getColor());
		}
		if (this.isHidden()) {
			sb.append("' hidden='");
			sb.append(this.isHidden());
		}
		sb.append("'>");
		sb.append(this.getValue());
		sb.append("</column>");
		return sb.toString();
	}
}
