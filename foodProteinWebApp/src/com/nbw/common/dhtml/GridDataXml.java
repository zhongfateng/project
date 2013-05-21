package com.nbw.common.dhtml;

import javax.servlet.http.HttpServletRequest;

public class GridDataXml{
	StringBuffer xmlBuffer = new StringBuffer();

	HttpServletRequest request = null;

	public GridDataXml() {
		xmlBuffer.append("<rows>");
		xmlBuffer.append("<head>");
		xmlBuffer.append("<afterInit>");
		xmlBuffer.append("</afterInit>");
		xmlBuffer.append("</head>");
		xmlBuffer.append("</rows>");
	}

	public GridDataXml(HttpServletRequest request) {
		this.request = request;
	}

	// 插入Column
	public void setColumn(String name) {
		xmlBuffer.insert(xmlBuffer.lastIndexOf("</head>"), name);
	}

	// 插入attachHeader
	public void setAttachHeader(String attachHeader) {
		xmlBuffer.insert(xmlBuffer.lastIndexOf("</afterInit>"),
				"<call command='attachHeader'><param>" + attachHeader
						+ "</param></call>");
	}

	// 插入row
	public void setId(String id) {
		xmlBuffer.insert(xmlBuffer.lastIndexOf("</rows>"), "<row id='" + id
				+ "'></row>");
	}

	// 插入cell
	public void setXmlData(Object cell) {
		if (xmlBuffer.lastIndexOf("</row>") > 0)
			xmlBuffer.insert(xmlBuffer.lastIndexOf("</row>"), "<cell>"+cell+"</cell>");
	}

	// 插入cell
	public void setXmlData(Object cell,int rspan) {
		if (xmlBuffer.lastIndexOf("</row>") > 0)
			xmlBuffer.insert(xmlBuffer.lastIndexOf("</row>"), "<cell rowspan=\""+rspan+"\">" + cell
					+ "</cell>");
	}
	public String getXml() {
		return new String(xmlBuffer);
	}
}
