package com.nbw.threeStruct.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.nbw.common.GenericDAO;
import com.nbw.common.util.PageBean;
import com.nbw.common.util.PageBeanUtil;
import com.nbw.docToDB.util.DBUtil;
import com.nbw.threeStruct.domain.ThreeStructure;

public class StructDAO extends GenericDAO<ThreeStructure, String>{
	
	
	/**
	 * 
	 * @param speciesName
	 * @param page
	 * @param pageSize
	 * @return 查找菌株的详细信息
	 */
	public PageBean findByName(String speciesName , int page , int pageSize){
		
		int currentPage = PageBean.countCurrentPage(page);
		int offset = PageBean.countOffset(pageSize, currentPage);
		String hql = "select t from ThreeStructure t where t.taxonomy like '" + speciesName + "%'";
		
		List<ThreeStructure> threeStructList = (List<ThreeStructure>) findPagerByHQL(hql, offset, pageSize);
		
		String hqlAllRow = "select count(t.id) from ThreeStructure t where t.taxonomy like '"+speciesName+"%'";
		List li = findByHQL(hqlAllRow);
		int allRowSize = ((Long) li.get(0)).intValue();
		PageBean pageBean = PageBeanUtil.queryForPage(threeStructList, allRowSize, page, pageSize);
		
		return pageBean;
	}
	
	
	/**
	 * 
	 * @param speciesName
	 * @param page
	 * @param pageSize
	 * @return 查找所有的
	 */
public PageBean findByNameSum(String speciesName , int page , int pageSize){
		
		int currentPage = PageBean.countCurrentPage(page);
		int offset = PageBean.countOffset(pageSize, currentPage);
		String hql = "select t from ThreeStructure t where t.taxonomy like '" + speciesName + "%'";
		
		List<ThreeStructure> threeStructList = (List<ThreeStructure>) findPagerByHQL(hql, offset, pageSize);
		
		String hqlAllRow = "select count(t.id) from ThreeStructure t where t.taxonomy like '"+speciesName+"%'";
		List li = findByHQL(hqlAllRow);
		int allRowSize = ((Long) li.get(0)).intValue();
		PageBean pageBean = PageBeanUtil.queryForPage(threeStructList, allRowSize, page, pageSize);
		
		return pageBean;
	}
	
	
	/**
	 * 
	 * @param speciesName
	 * @return 查找相关菌株的列表信息
	 */
	public List<String[]> findBySpeciesName(String speciesName){
		
		String sql = "SELECT taxonomy , COUNT(*) FROM threeStructure WHERE taxonomy LIKE '" + speciesName + "%' and taxonomy <> '"+speciesName+"' GROUP BY taxonomy";
		Connection conn  = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<String[]> ls = new ArrayList<String[]>();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				String[] ary = new String[2];
				ary[0] = rs.getString(1);
				ary[1] = rs.getString(2);
				ls.add(ary);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				rs.close();
				rs.close();
				DBUtil.close(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return ls;
	}
	
	/**
	 * 
	 * @param speciesName
	 * @return 总数
	 */
	public int findBySpeciesNameSum(String speciesName){
		
		String sql = "SELECT COUNT(*) FROM threeStructure WHERE taxonomy LIKE '" + speciesName + "%'";
		Connection conn  = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int sum = 0;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				sum = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				rs.close();
				rs.close();
				DBUtil.close(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
		return sum;
	}
	

}
