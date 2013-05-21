package com.nbw.common.util;

import java.util.List;

public class PageBeanUtil{
	
	public static PageBean queryForPage(List pojoList,int allRow,int page,int pageSize) {
		int totalPage = PageBean.countTotalPage(pageSize, allRow); // 总页数
		final int currentPage = PageBean.countCurrentPage(page);
		final int offset = PageBean.countOffset(pageSize, currentPage); // 当前页开始记录
		final int length = pageSize; // 每页记录数
		
		PageBean pageBeanDaoImpl = new PageBean();
		pageBeanDaoImpl.setPageSize(pageSize);
		pageBeanDaoImpl.setCurrentPage(currentPage);
		pageBeanDaoImpl.setAllRow(allRow);
		pageBeanDaoImpl.setTotalPage(totalPage);
		pageBeanDaoImpl.setList(pojoList);
		pageBeanDaoImpl.init();
		return pageBeanDaoImpl;
	}
}
