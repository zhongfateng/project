package com.nbw.docToDB.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Query;

import com.nbw.common.GenericDAO;
import com.nbw.common.util.PageBean;
import com.nbw.common.util.PageBeanUtil;
import com.nbw.docToDB.domain.Protein;
import com.nbw.docToDB.util.DBUtil;
import com.nbw.lucene.domain.Doctxt;

/**
 * 
 * ProteinDAO
 * 
 * @author wangshaobin
 * 
 */
public class ProteinDAO extends GenericDAO<Protein, String> {

	public int findBySpeciesName(Integer sid) {
		String hqlAllRow = "select count(p.id) from Protein p where p.speciesId = "
				+ sid;
		List li = findByHQL(hqlAllRow);
		int proteinSize = ((Long) li.get(0)).intValue();
		return proteinSize;
	}

	/**
	 * 
	 * @param info
	 * @param page
	 * @param pageSize
	 * @return 标准检索
	 */
	public PageBean findByInfo(String info, int page, int pageSize) {
//		String speid = "";
//		Connection conn = DBUtil.getConnection();
//		try {
//			PreparedStatement pre = conn
//					.prepareStatement("select node_id from fmpt_strain where ename = ?");
//			pre.setString(1, info);
//			ResultSet rs = pre.executeQuery();
//			while (rs.next()) {
//				speid = rs.getInt(1) + "";
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		if("".equals(speid)){
//			PageBean pageBean = new PageBean();
//			pageBean.setAllRow(0);
//			pageBean.setCurrentPage(0);
//			pageBean.setList(null);
//			pageBean.setOffset(0);
//			pageBean.setPageSize(0);
//			pageBean.setTotalPage(0);
//			return pageBean;
//		}else{
			
		
		int currentPage = PageBean.countCurrentPage(page);
		int offset = PageBean.countOffset(pageSize, currentPage);
		String hql = "select p from Protein p where p.speciesName like '" + info
				+ "%'";

		List<Protein> proteinList = (List<Protein>) findPagerByHQL(hql, offset,
				pageSize);

		String hqlAllRow = "select count(p.id) from Protein p where p.speciesName like '"
				+ info + "%'";
		List li = findByHQL(hqlAllRow);
		int allRowSize = ((Long) li.get(0)).intValue();
		PageBean pageBean = PageBeanUtil.queryForPage(proteinList, allRowSize,
				page, pageSize);

		return pageBean;
//	}
}
	
	
	
	
	
	public PageBean findByProteinId(String speid, int page, int pageSize) {

		int currentPage = PageBean.countCurrentPage(page);
		int offset = PageBean.countOffset(pageSize, currentPage);
		String hql = "select p from Protein p where p.speciesId = '" + speid
				+ "'";

		List<Protein> proteinList = (List<Protein>) findPagerByHQL(hql, offset,
				pageSize);

		String hqlAllRow = "select count(p.id) from Protein p where p.speciesId = '"
				+ speid + "'";
		List li = findByHQL(hqlAllRow);
		int allRowSize = ((Long) li.get(0)).intValue();
		PageBean pageBean = PageBeanUtil.queryForPage(proteinList, allRowSize,
				page, pageSize);

		return pageBean;
	}

	/**
	 * 
	 * @param id
	 * @return 查找某个具体蛋白信息
	 */
	public Protein findByProteinId(String id) {

		String sql = "select p from Protein p where p.ncbiProteinRef='" + id
				+ "'";

		Query query = getSession().createQuery(sql);
		return (Protein) query.list().get(0);

	}
	
	
	public String findByProteinIds(String id){
		String speciesName = "";
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("select ename from fmpt_strain where node_id = '"+id+"'");
			rs = ps.executeQuery();
			while(rs.next()){
				speciesName = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				rs.close();
				ps.close();
				DBUtil.close(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return speciesName;
	}

	/**
	 * 
	 * @param id
	 * @param page
	 * @param pageSize
	 * @return 查找文献
	 */
	public PageBean findDocByProteinId(String id, int page, int pageSize) {

		PageBean pageBean = new PageBean();

		// 通过id获得protein对象
		String sql = "select p from Protein p where p.ncbiProteinRef='" + id
				+ "'";
		Query query = getSession().createQuery(sql);
		Protein pro = (Protein) query.list().get(0);
		String speciesId = pro.getSpeciesId();// 获得对应的种id

		// 通过种的id获得其英文名称
		Connection conn = DBUtil.getConnection();
		String speciesName = "";
		try {
			PreparedStatement pre = conn
					.prepareStatement("select ename from fmpt_strain where node_id = ?");
			pre.setString(1, speciesId);
			ResultSet rs = pre.executeQuery();
			while (rs.next()) {
				speciesName = rs.getString(1);
			}

			// 通过种名查找相关题目文献
			String hql = "select d from Doctxt d where d.title like '%"
					+ speciesName + "%'";
			int currentPage = PageBean.countCurrentPage(page);
			int offset = PageBean.countOffset(pageSize, currentPage);
			List<Doctxt> docList = (List<Doctxt>) findPagerByHQL(hql, offset,
					pageSize);

			String hqlAllRow = "select count(d.did) from Doctxt d where d.title like '%"
					+ speciesName + "%'";
			List li = findByHQL(hqlAllRow);
			int allRowSize = ((Long) li.get(0)).intValue();
			pageBean = PageBeanUtil.queryForPage(docList, allRowSize, page,
					pageSize);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pageBean;
	}

	/**
	 * 
	 * @param ncbiSpeciesId
	 * @param proteinId
	 * @param ncbiProteinRef
	 * @param chref
	 * @param page
	 * @param pageSize
	 * @param key
	 * @return 蛋白质高级检索
	 */
	public PageBean adFindByInfo(String ncbiSpeciesId, String proteinId,
			String ncbiProteinRef, String chref, int page, int pageSize,
			String[] key) {

		String key_1 = key[0];
		String key_2 = key[1];
		String key_3 = key[2];

		boolean flag = false;
		String index = "0";

		String sql = "select p from Protein p where ";
		String hqlAllRow = "select count(p.id) from Protein p where ";
		if (ncbiSpeciesId != null && !ncbiSpeciesId.isEmpty()) {
			if (flag == true) {
				if (index.equals("1")) {
					sql = sql + key_1 + "  p.ncbiSpeciesId = '" + ncbiSpeciesId
							+ "'";
					hqlAllRow = hqlAllRow + key_1 + "  p.ncbiSpeciesId = '"
							+ ncbiSpeciesId + "'";
					index = "2";
				} else if (index.equals("2")) {
					sql = sql + key_2 + "  p.ncbiSpeciesId = '" + ncbiSpeciesId
							+ "'";
					hqlAllRow = hqlAllRow + key_2 + "  p.ncbiSpeciesId = '"
							+ ncbiSpeciesId + "'";
					index = "3";
				} else if (index.equals("3")) {
					sql = sql + key_3 + "  p.ncbiSpeciesId = '" + ncbiSpeciesId
							+ "'";
					hqlAllRow = hqlAllRow + key_3 + "  p.ncbiSpeciesId = '"
							+ ncbiSpeciesId + "'";
				}
				// sql = sql + " and g.ncbiSpeciesId = '"+speName + "'";
				// hqlAllRow = hqlAllRow + " and g.ncbiSpeciesId = '"+speName +
				// "'";
			} else {
				sql = sql + " p.ncbiSpeciesId = '" + ncbiSpeciesId + "'";
				hqlAllRow = hqlAllRow + " p.ncbiSpeciesId = '" + ncbiSpeciesId
						+ "'";
				flag = true;
				index = "1";
			}
		}

		if (proteinId != null && !proteinId.isEmpty()) {
			if (flag == true) {
				if (index.equals("1")) {
					sql = sql + key_1 + " p.pid = '" + proteinId + "'";
					hqlAllRow = hqlAllRow + key_1 + "  p.pid = '" + proteinId
							+ "'";
					index = "2";
				} else if (index.equals("2")) {
					sql = sql + key_2 + " p.pid = '" + proteinId + "'";
					hqlAllRow = hqlAllRow + key_2 + "  p.pid = '" + proteinId
							+ "'";
					index = "3";
				} else if (index.equals("3")) {
					sql = sql + key_3 + " p.pid = '" + proteinId + "'";
					hqlAllRow = hqlAllRow + key_3 + "  p.pid = '" + proteinId
							+ "'";
				}
				// sql = sql + " and g.gid = '" +geneId +"'";
				// hqlAllRow = hqlAllRow + " and g.gid = '" +geneId +"'";
			} else {
				sql = sql + "  p.pid = '" + proteinId + "'";
				hqlAllRow = hqlAllRow + "  p.pid = '" + proteinId + "'";
				flag = true;
				index = "1";
			}
		}

		if (ncbiProteinRef != null && !ncbiProteinRef.isEmpty()) {
			if (flag == true) {
				if (index.equals("1")) {
					sql = sql + key_1 + "  p.ncbiProteinRef = '"
							+ ncbiProteinRef + "'";
					hqlAllRow = hqlAllRow + key_1 + "  p.ncbiProteinRef = '"
							+ ncbiProteinRef + "'";
					index = "2";
				} else if (index.equals("2")) {
					sql = sql + key_2 + "  p.ncbiProteinRef = '"
							+ ncbiProteinRef + "'";
					hqlAllRow = hqlAllRow + key_2 + "  p.ncbiProteinRef = '"
							+ ncbiProteinRef + "'";
					index = "3";
				} else if (index.equals("3")) {
					sql = sql + key_3 + "  p.ncbiProteinRef = '"
							+ ncbiProteinRef + "'";
					hqlAllRow = hqlAllRow + key_3 + "  p.ncbiProteinRef = '"
							+ ncbiProteinRef + "'";

				}
				// sql = sql + " and g.gname = '"+geneName+"'";
				// hqlAllRow = hqlAllRow + " and g.gname = '"+geneName+"'";
			} else {
				sql = sql + " p.ncbiProteinRef = '" + ncbiProteinRef + "'";
				hqlAllRow = hqlAllRow + " p.ncbiProteinRef = '"
						+ ncbiProteinRef + "'";
				flag = true;
				index = "1";
			}
		}

		if (chref != null && !chref.isEmpty()) {
			if (flag == true) {
				if (index.equals("1")) {
					sql = sql + key_1 + "  p.chromosomeRef = '" + chref + "'";
					hqlAllRow = hqlAllRow + key_1 + "  p.chromosomeRef = '"
							+ chref + "'";
					index = "2";
				} else if (index.equals("2")) {
					sql = sql + key_2 + "  p.chromosomeRef = '" + chref + "'";
					hqlAllRow = hqlAllRow + key_2 + "  p.chromosomeRef = '"
							+ chref + "'";
					index = "3";
				} else if (index.equals("3")) {
					sql = sql + key_3 + "  p.chromosomeRef = '" + chref + "'";
					hqlAllRow = hqlAllRow + key_3 + "  p.chromosomeRef = '"
							+ chref + "'";
				}
				// sql = sql + " and g.chromosomeRef = '"+chref+"'";
				// hqlAllRow = hqlAllRow + " and g.chromosomeRef = '"+chref+"'";
			} else {
				sql = sql + " p.chromosomeRef = '" + chref + "'";
				hqlAllRow = hqlAllRow + " p.chromosomeRef = '" + chref + "'";
				flag = true;
				index = "1";
			}

		}

		int currentPage = PageBean.countCurrentPage(page);
		int offset = PageBean.countOffset(pageSize, currentPage);
		// String hql = "select g from Gene g where g.speciesId = '" + info +
		// "'";

		List<Protein> proteinList = (List<Protein>) findPagerByHQL(sql, offset,
				pageSize);

		List li = findByHQL(hqlAllRow);
		int allRowSize = ((Long) li.get(0)).intValue();
		PageBean pageBean = PageBeanUtil.queryForPage(proteinList, allRowSize,
				page, pageSize);

		return pageBean;

	}

}
