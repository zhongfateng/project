package com.nbw.hygl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.nbw.common.GenericDAO;
import com.nbw.common.util.PageBean;
import com.nbw.common.util.PageBeanUtil;
import com.nbw.docToDB.util.DBUtil;
import com.nbw.hygl.domain.FmptJf;

public class FmptJfDAO extends GenericDAO<FmptJf, String> {

	
	
	/**
	 * 
	 * @param page
	 * @param pageSize
	 * @param name
	 * @return 查看用户积分信息
	 */
	public PageBean findByTime(int page, int pageSize,String name) {

		String hql = "select t from FmptJf t where t.username = '"+name+"' order by timestamp desc";

		int currentPage = PageBean.countCurrentPage(page);
		int offset = PageBean.countOffset(pageSize, currentPage);

		List<FmptJf> tDList = (List<FmptJf>) findPagerByHQL(hql, offset,
				pageSize);

		String hqlAllRow = "select count(t.id) from FmptJf t where t.username ='"+name+"' order by timestamp desc";
		List li = findByHQL(hqlAllRow);
		int allRowSize = ((Long) li.get(0)).intValue();
		PageBean pageBean = PageBeanUtil.queryForPage(tDList, allRowSize, page,
				pageSize);

		return pageBean;
	}
	
	
	/**
	 * 
	 * @param name
	 * @return 用户的总积分
	 */
	public int findSumSorce(String name){
		int sum = 0;
		String sql = "select sum(jfcount) from fmpt_jf where username = '"+name+"'";
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				sum = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return sum;
	}
	public int findSumSorceByUserId(String userID){
		int sum = 0;
		String sql = "select sum(jfcount) from fmpt_jf where userid = '"+userID+"'";
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				sum = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return sum;
	}
	
	/**
	 * 
	 * @param page
	 * @param pageSize
	 * @param name
	 * @param fclick
	 * @return 上传下载查询
	 */
	public PageBean findByUploadAndDownloadTime(int page, int pageSize,String name,String fclick) {

		String hql = "select t from FmptJf t where t.username = '"+name+"' and t.ftype = '"+fclick+"' order by timestamp desc";

		int currentPage = PageBean.countCurrentPage(page);
		int offset = PageBean.countOffset(pageSize, currentPage);

		List<FmptJf> tDList = (List<FmptJf>) findPagerByHQL(hql, offset,
				pageSize);

		String hqlAllRow = "select count(t.id) from FmptJf t where t.username ='"+name+"' and t.ftype = '"+fclick+"' order by timestamp desc";
		List li = findByHQL(hqlAllRow);
		int allRowSize = ((Long) li.get(0)).intValue();
		PageBean pageBean = PageBeanUtil.queryForPage(tDList, allRowSize, page,
				pageSize);

		return pageBean;
	}
	
	
	
	
	

}
