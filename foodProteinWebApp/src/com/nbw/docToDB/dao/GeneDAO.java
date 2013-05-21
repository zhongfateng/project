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
import com.nbw.docToDB.domain.Gene;
import com.nbw.docToDB.domain.Protein;
import com.nbw.docToDB.util.DBUtil;
import com.nbw.lucene.domain.Doctxt;

/**
 * 
 * GeneDAO
 * 
 * @author wangshaobin
 * 
 */
public class GeneDAO extends GenericDAO<Gene, String> {
	
	
	
	public int findBySpeciesName(Integer sid){
		
		String hqlAllRow = "select count(g.id) from Gene g where g.speciesId ="+sid;
		List li = findByHQL(hqlAllRow);
		int geneSize = ((Long) li.get(0)).intValue();
		return geneSize;
	}
	
	
	/**
	 * 
	 * @param info
	 * @param page
	 * @param pageSize
	 * @return 标准检索gene
	 */
	public PageBean findByInfo(String info,int page ,int pageSize){
//		String speid = "";
//		Connection conn = DBUtil.getConnection();
//		String sql = "select node_id from fmpt_strain where ename like '%"+info+"%'";
//		try {
//			PreparedStatement pre = conn.prepareStatement(sql);
//			ResultSet rs = pre.executeQuery();
//			while(rs.next()){
//				speid = rs.getInt(1)+"";
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
		String hql = "select g from Gene g where g.speciesName like '" + info + "%'";
		
		List<Gene> geneList = (List<Gene>) findPagerByHQL(hql, offset, pageSize);
		
		String hqlAllRow = "select count(g.id) from Gene g where g.speciesName like '"+info+"%'";
		List li = findByHQL(hqlAllRow);
		int allRowSize = ((Long) li.get(0)).intValue();
		PageBean pageBean = PageBeanUtil.queryForPage(geneList, allRowSize, page, pageSize);
		
		return pageBean;
//	}
}
	
	
	
	public PageBean findByGeneId(String speid,int page ,int pageSize){
		
		int currentPage = PageBean.countCurrentPage(page);
		int offset = PageBean.countOffset(pageSize, currentPage);
		String hql = "select g from Gene g where g.speciesId = '" + speid + "'";
		
		List<Gene> geneList = (List<Gene>) findPagerByHQL(hql, offset, pageSize);
		
		String hqlAllRow = "select count(g.id) from Gene g where g.speciesId = '"+speid+"'";
		List li = findByHQL(hqlAllRow);
		int allRowSize = ((Long) li.get(0)).intValue();
		PageBean pageBean = PageBeanUtil.queryForPage(geneList, allRowSize, page, pageSize);
		
		return pageBean;
	}
	
	
	/**
	 * 
	 * @param id
	 * @return 通过gene的id查出唯一对象
	 */
	public Gene findByGeneId(String id){
		
		String sql = "select g from Gene g where g.ncbiGeneRef='"+id+"'";
		
		Query query = getSession().createQuery(sql);
		return (Gene) query.list().get(0);
		
	}
	
	
	public String findByGeneIds(String id){
		String speciesName = "";
		String sql = "select ename from fmpt_strain where node_id = '"+id+"'";
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			 ps = conn.prepareStatement(sql);
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
	
	
	
public PageBean findDocByGeneId(String id ,int page , int pageSize){
		
		PageBean pageBean = new PageBean();
		
		//通过id获得gene对象
		String sql = "select g from Gene g where g.ncbiGeneRef='" + id + "'";
		Query query = getSession().createQuery(sql);
		Gene gene = (Gene)query.list().get(0);
		String speciesId = gene.getSpeciesId();//获得对应的种id

		//通过种的id获得其英文名称
		Connection conn = DBUtil.getConnection();
		String speciesName = "";
		try {
			PreparedStatement pre = conn.prepareStatement("select ename from fmpt_strain where node_id = ?");
			pre.setString(1, speciesId);
			ResultSet rs = pre.executeQuery();
			while(rs.next()){
				speciesName = rs.getString(1);
			}
			
			//通过种名查找相关题目文献
			String hql = "select d from Doctxt d where d.title like '%"+speciesName+"%'";
			int currentPage = PageBean.countCurrentPage(page);
			int offset = PageBean.countOffset(pageSize, currentPage);
			List<Doctxt> docList = (List<Doctxt>) findPagerByHQL(hql, offset,
					pageSize);

			String hqlAllRow = "select count(d.did) from Doctxt d where d.title like '%"+speciesName+"%'";
			List li = findByHQL(hqlAllRow);
			int allRowSize = ((Long) li.get(0)).intValue();
			pageBean = PageBeanUtil.queryForPage(docList, allRowSize,
					page, pageSize);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pageBean;
	}
	
	
	
		/**
		 * 
		 * @param ncbiSpeciesId
		 * @param geneId
		 * @param geneName
		 * @param chref
		 * @param page
		 * @param pageSize
		 * @param key
		 * @return  标准检索 gene
		 */
		public PageBean adFindByInfo(String ncbiSpeciesId,String geneId,String geneName,String chref,int page,int pageSize,String[] key){
		
		String key_1 = key[0];
		String key_2 = key[1];
		String key_3 = key[2];
		
		boolean flag = false;
		String index = "0";
		
		String sql = "select g from Gene g where ";
		String hqlAllRow = "select count(g.id) from Gene g where ";
		if(ncbiSpeciesId!=null && !ncbiSpeciesId.isEmpty()){
			if(flag == true){
				if(index.equals("1")){
					sql = sql + key_1+"  g.ncbiSpeciesId = '"+ncbiSpeciesId + "'";
					hqlAllRow = hqlAllRow + key_1+"  g.ncbiSpeciesId = '"+ncbiSpeciesId + "'";
					index="2";
				}else if(index.equals("2")){
					sql = sql + key_2+"  g.ncbiSpeciesId = '"+ncbiSpeciesId + "'";
					hqlAllRow = hqlAllRow + key_2+"  g.ncbiSpeciesId = '"+ncbiSpeciesId + "'";
					index="3";
				}else if(index.equals("3")){
					sql = sql + key_3+"  g.ncbiSpeciesId = '"+ncbiSpeciesId + "'";
					hqlAllRow = hqlAllRow + key_3+"  g.ncbiSpeciesId = '"+ncbiSpeciesId + "'";
				}
				//sql = sql + " and  g.ncbiSpeciesId = '"+speName + "'";
				//hqlAllRow = hqlAllRow + " and  g.ncbiSpeciesId = '"+speName + "'";
			}else{
				sql = sql + " g.ncbiSpeciesId = '"+ncbiSpeciesId + "'";
				hqlAllRow = hqlAllRow + " g.ncbiSpeciesId = '"+ncbiSpeciesId + "'";
				flag = true;
				index = "1";
			}
		}
		
		
		if(geneId!=null && !geneId.isEmpty()){
			if(flag==true){
				if(index.equals("1")){
					sql = sql + key_1+" g.gid = '" +geneId +"'";
					hqlAllRow = hqlAllRow + key_1+"  g.gid = '" +geneId +"'";
					index="2";
				}else if(index.equals("2")){
					sql = sql + key_2+" g.gid = '" +geneId +"'";
					hqlAllRow = hqlAllRow + key_2+"  g.gid = '" +geneId +"'";
					index="3";
				}else if(index.equals("3")){
					sql = sql + key_3+" g.gid = '" +geneId +"'";
					hqlAllRow = hqlAllRow + key_3+"  g.gid = '" +geneId +"'";
				}
				//sql = sql + " and  g.gid = '" +geneId +"'";
				//hqlAllRow = hqlAllRow + " and g.gid = '" +geneId +"'";
			}else{
				sql = sql + "  g.gid = '" +geneId +"'";
				hqlAllRow = hqlAllRow + "  g.gid = '" +geneId +"'";
				flag = true;
				index = "1";
			}
		}
		
		
		if(geneName !=null && !geneName.isEmpty()){
			if(flag==true){
				if(index.equals("1")){
					sql = sql + key_1+"  g.gname = '"+geneName+"'";
					hqlAllRow = hqlAllRow + key_1+"  g.gname = '"+geneName+"'";
					index="2";
				}else if(index.equals("2")){
					sql = sql + key_2+"  g.gname = '"+geneName+"'";
					hqlAllRow = hqlAllRow + key_2+"  g.gname = '"+geneName+"'";
					index="3";
				}else if(index.equals("3")){
					sql = sql + key_3+"  g.gname = '"+geneName+"'";
					hqlAllRow = hqlAllRow + key_3+"  g.gname = '"+geneName+"'";
					
				}
				//sql = sql + " and g.gname = '"+geneName+"'";
				//hqlAllRow = hqlAllRow + " and g.gname = '"+geneName+"'";
			}else{
				sql = sql + " g.gname = '"+geneName+"'";
				hqlAllRow = hqlAllRow + " g.gname = '"+geneName+"'";
				flag = true;
				index = "1";
			}
	    }
		if(chref !=null && !chref.isEmpty()){
			if(flag==true){
				if(index.equals("1")){
					sql = sql + key_1+"  g.chromosomeRef = '"+chref+"'";
					hqlAllRow = hqlAllRow + key_1+"  g.chromosomeRef = '"+chref+"'";
					index="2";
				}else if(index.equals("2")){
					sql = sql + key_2+"  g.chromosomeRef = '"+chref+"'";
					hqlAllRow = hqlAllRow + key_2+"  g.chromosomeRef = '"+chref+"'";
					index="3";
				}else if(index.equals("3")){
					sql = sql + key_3+"  g.chromosomeRef = '"+chref+"'";
					hqlAllRow = hqlAllRow + key_3+"  g.chromosomeRef = '"+chref+"'";
				}
				//sql = sql + " and g.chromosomeRef = '"+chref+"'";
				//hqlAllRow = hqlAllRow + " and g.chromosomeRef = '"+chref+"'";
			}else{
				sql = sql + " g.chromosomeRef = '"+chref+"'";
				hqlAllRow = hqlAllRow + " g.chromosomeRef = '"+chref+"'";
				flag = true;
				index = "1";
			}
			
		}
		
		int currentPage = PageBean.countCurrentPage(page);
		int offset = PageBean.countOffset(pageSize, currentPage);
		//String hql = "select g from Gene g where g.speciesId = '" + info + "'";
		
		List<Gene> geneList = (List<Gene>) findPagerByHQL(sql, offset, pageSize);
		
		List li = findByHQL(hqlAllRow);
		int allRowSize = ((Long) li.get(0)).intValue();
		PageBean pageBean = PageBeanUtil.queryForPage(geneList, allRowSize, page, pageSize);
		
		return pageBean;

    }
}


