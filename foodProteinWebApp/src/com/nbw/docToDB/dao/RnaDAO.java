package com.nbw.docToDB.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Query;

import com.nbw.docToDB.domain.Gene;
import com.nbw.docToDB.domain.Protein;
import com.nbw.docToDB.domain.Rna;
import com.nbw.docToDB.util.DBUtil;
import com.nbw.lucene.domain.Doctxt;
import com.nbw.common.GenericDAO;
import com.nbw.common.util.PageBean;
import com.nbw.common.util.PageBeanUtil;

/**
 * 
 * RnaDAO
 * 
 * @author wangshaobin
 * 
 */
public class RnaDAO extends GenericDAO<Rna, String> {

	public int findBySpeciesName(Integer sid) {
		String hqlAllRow = "select count(r.id) from Rna r where r.speciesId = "
				+ sid + "";
		List li = findByHQL(hqlAllRow);
		int rnaSize = ((Long) li.get(0)).intValue( );
		return rnaSize;
	}

	/**
	 * 
	 * @param info
	 * @param page
	 * @param pageSize
	 * @return 标准检索rna
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
		String hql = "select r from Rna r where r.speciesName like '" + info + "%'";

		List<Rna> RnaList = (List<Rna>) findPagerByHQL(hql, offset, pageSize);

		String hqlAllRow = "select count(r.id) from Rna r where r.speciesName like '"
				+ info + "%'";
		List li = findByHQL(hqlAllRow);
		int allRowSize = ((Long) li.get(0)).intValue();
		PageBean pageBean = PageBeanUtil.queryForPage(RnaList, allRowSize,
				page, pageSize);

		return pageBean;
//	}
}	
	
	
	
	public PageBean findByRnaId(String speid, int page, int pageSize) {

		int currentPage = PageBean.countCurrentPage(page);
		int offset = PageBean.countOffset(pageSize, currentPage);
		String hql = "select r from Rna r where r.speciesId = '" + speid + "'";

		List<Rna> RnaList = (List<Rna>) findPagerByHQL(hql, offset, pageSize);

		String hqlAllRow = "select count(r.id) from Rna r where r.speciesId = '"
				+ speid + "'";
		List li = findByHQL(hqlAllRow);
		int allRowSize = ((Long) li.get(0)).intValue();
		PageBean pageBean = PageBeanUtil.queryForPage(RnaList, allRowSize,
				page, pageSize);

		return pageBean;
	}

	/**
	 * 
	 * @param id
	 * @return 查找某一rna
	 */
	public Rna findByRnaId(String id) {

		String sql = "select r from Rna r where r.geneId ='" + id + "'";

		Query query = getSession().createQuery(sql);
		return (Rna) query.list().get(0);

	}
	
	public String findByRnaIds(String id){
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

	public PageBean findDocByRnaId(String id, int page, int pageSize) {

		PageBean pageBean = new PageBean();

		// 通过id获得rna对象
		String sql = "select r from Rna r where r.geneId='" + id + "'";
		Query query = getSession().createQuery(sql);
		Rna rna = (Rna) query.list().get(0);
		String speciesId = rna.getSpeciesId();// 获得对应的种id

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
	 * @param rnaId
	 * @param chref
	 * @param page
	 * @param pageSize
	 * @param key
	 * @return 高级检索
	 */
	public PageBean adFindByInfo(String ncbiSpeciesId, String rnaId,
			String chref, int page, int pageSize, String[] key) {

		String key_1 = key[0];
		String key_2 = key[1];

		boolean flag = false;
		String index = "0";

		String sql = "select r from Rna r where ";
		String hqlAllRow = "select count(r.id) from Rna r where ";
		if (ncbiSpeciesId != null && !ncbiSpeciesId.isEmpty()) {
			if (flag == true) {
				if (index.equals("1")) {
					sql = sql + key_1 + "  r.ncbiSpeciesId = '" + ncbiSpeciesId
							+ "'";
					hqlAllRow = hqlAllRow + key_1 + "  r.ncbiSpeciesId = '"
							+ ncbiSpeciesId + "'";
					index = "2";
				} else if (index.equals("2")) {
					sql = sql + key_2 + "  r.ncbiSpeciesId = '" + ncbiSpeciesId
							+ "'";
					hqlAllRow = hqlAllRow + key_2 + "  r.ncbiSpeciesId = '"
							+ ncbiSpeciesId + "'";
				}
				// sql = sql + " and g.ncbiSpeciesId = '"+speName + "'";
				// hqlAllRow = hqlAllRow + " and g.ncbiSpeciesId = '"+speName +
				// "'";
			} else {
				sql = sql + " r.ncbiSpeciesId = '" + ncbiSpeciesId + "'";
				hqlAllRow = hqlAllRow + " r.ncbiSpeciesId = '" + ncbiSpeciesId
						+ "'";
				flag = true;
				index = "1";
			}
		}

		if (rnaId != null && !rnaId.isEmpty()) {
			if (flag == true) {
				if (index.equals("1")) {
					sql = sql + key_1 + " r.gid = '" + rnaId + "'";
					hqlAllRow = hqlAllRow + key_1 + "  r.gid = '" + rnaId + "'";
					index = "2";
				} else if (index.equals("2")) {
					sql = sql + key_2 + " r.gid = '" + rnaId + "'";
					hqlAllRow = hqlAllRow + key_2 + "  r.gid = '" + rnaId + "'";
				}
				// sql = sql + " and g.gid = '" +geneId +"'";
				// hqlAllRow = hqlAllRow + " and g.gid = '" +geneId +"'";
			} else {
				sql = sql + "  r.gid = '" + rnaId + "'";
				hqlAllRow = hqlAllRow + "  r.gid = '" + rnaId + "'";
				flag = true;
				index = "1";
			}
		}

		if (chref != null && !chref.isEmpty()) {
			if (flag == true) {
				if (index.equals("1")) {
					sql = sql + key_1 + "  r.chromosomeRef = '" + chref + "'";
					hqlAllRow = hqlAllRow + key_1 + "  r.chromosomeRef = '"
							+ chref + "'";
					index = "2";
				} else if (index.equals("2")) {
					sql = sql + key_2 + "  r.chromosomeRef = '" + chref + "'";
					hqlAllRow = hqlAllRow + key_2 + "  r.chromosomeRef = '"
							+ chref + "'";
				}
				// sql = sql + " and g.chromosomeRef = '"+chref+"'";
				// hqlAllRow = hqlAllRow + " and g.chromosomeRef = '"+chref+"'";
			} else {
				sql = sql + " r.chromosomeRef = '" + chref + "'";
				hqlAllRow = hqlAllRow + " r.chromosomeRef = '" + chref + "'";
				flag = true;
				index = "1";
			}

		}

		int currentPage = PageBean.countCurrentPage(page);
		int offset = PageBean.countOffset(pageSize, currentPage);
		// String hql = "select g from Gene g where g.speciesId = '" + info +
		// "'";

		List<Rna> rnaList = (List<Rna>) findPagerByHQL(sql, offset, pageSize);

		List li = findByHQL(hqlAllRow);
		int allRowSize = ((Long) li.get(0)).intValue();
		PageBean pageBean = PageBeanUtil.queryForPage(rnaList, allRowSize,
				page, pageSize);

		return pageBean;

	}

}
