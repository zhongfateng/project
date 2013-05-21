package com.nbw.lucene.dao;

import java.awt.Container;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.nbw.common.GenericDAO;
import com.nbw.common.util.PageBean;
import com.nbw.common.util.PageBeanUtil;
import com.nbw.lucene.domain.Doctxt;

/**
 * 
 * DoctxtDAO
 * 
 * @author
 * 
 */
public class DoctxtDAO extends GenericDAO<Doctxt, String> {

	// 根据物种id获得相关文献的条数
	public int getCountBySpeciesId(Integer sid) {
		String hql = "select count(d.id) from Doctxt d where d.gid =" + sid;
		return ((Long) (this.findByHQL(hql).get(0))).intValue();
	}
	// public List<Doctxt> getDoctxtByList(List<String> list)
	// {

	// List<Doctxt> listDocTxt =new ArrayList<Doctxt>();
	// for(String str:list)
	// {
	// try
	// {
	// Doctxt doctxt= findByProperty("pmid",str).get(0);
	// System.out.println(doctxt.getPmid());
	// listDocTxt.add(doctxt);
	// }catch(Exception e)
	// {
	// //e.printStackTrace();
	// continue;
	// }
	// }
	// return listDocTxt;

	// }
	//根据物种id获得文献列表
	
		public List<Doctxt> getListDoctxtByGid(Integer gid)
		{
			return 	this.findAllDocByGid(gid);
		}
	public Doctxt getDoctxtByPmid(String pmid)
	{
		
		Doctxt doctxt=findByProperty("pmid",pmid).get(0);
		return doctxt;
	}
	// 根据物种id获得相关文献的条数
	
	// public List<Doctxt> getDoctxtByList(List<String> list)
	// {
	// List<Doctxt> listDocTxt =new ArrayList<Doctxt>();
	// for(String str:list)
	// {
	// try
	// {
	// Doctxt doctxt= findByProperty("pmid",str).get(0);
	// System.out.println(doctxt.getPmid());
	// listDocTxt.add(doctxt);
	// }catch(Exception e)
	// {
 //e.printStackTrace();
	// continue;
	// }
	// }
	// return listDocTxt;

	// }


	// 高级检索
	public PageBean advanceSearchListDoctxt(String Title, String Author,
			String Abstract, String Journal, int page, int pageSize,
			String StartDate, String EndDate) {

		// Criteria criteria=getSession().createCriteria(Doctxt.class);
		// if(Title!=null&&!Title.equals(""))
		// {
		// criteria.add(Expression.like("title", Title));
		// }
		// if(Author!=null&&!Author.equals(""))
		// {
		// criteria.add(Expression.like("author", Author));
		//			
		// }
		// if(Abstract!=null&&!Abstract.equals(""))
		// {
		//			
		// criteria.add(Expression.like("abstract_", Abstract));
		//			
		// }
		// if(Journal!=null&&!Journal.equals(""))
		// {
		// criteria.add(Expression.like("journal", Journal));
		//			
		// }
		// int rowCount = ((Integer)
		// criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();//根据条件取出总记录数
		// criteria.setProjection(null);
		//			  
		//			  
		// criteria.setFirstResult(PageBean.countOffset(pageSize, page));
		// criteria.setMaxResults(pageSize);
		// criteria.setFirstResult(1);
		// criteria.setMaxResults(10);
		// return (List<Doctxt>)criteria.list();
		//	
		Map parameters = new HashMap();
		boolean firstClause = true;
		StringBuffer querybuf = new StringBuffer("from Doctxt d");
		if (Title != null && !Title.equals("")&&!Title.equals("null")) {
			querybuf.append(firstClause ? " where " : " and ");
			querybuf.append("d.title like '%" + Title + "%'");
			// parameters.put("Title", Title);
			firstClause = false;
		}
		if (Author != null && !Author.equals("")&&!Author.equals("null")) {
			querybuf.append(firstClause ? " where " : " and ");
			querybuf.append("d.authorFull like '%" + Author + "%'");
			// parameters.put("Author", Author);
			firstClause = false;
		}
		if (Abstract != null && !Abstract.equals("")&&!Abstract.equals("null")) {
			querybuf.append(firstClause ? " where " : " and ");
			querybuf.append("d.abstract_ like '%" + Abstract + "%'");
			// parameters.put("Abstract", Abstract);
			firstClause = false;
		}
		if (Journal != null && !Journal.equals("")&&!Journal.equals("null")) {
			querybuf.append(firstClause ? " where " : " and ");
			querybuf.append("d.journal = '" + Journal + "'");
			// parameters.put("Journal", Journal);
			firstClause = false;
		}
		if (StartDate != null && !StartDate.equals("") &&!StartDate.equals("null")&& EndDate != null
				&& !EndDate.equals("")&&!EndDate.equals("null")) {
			querybuf.append(firstClause ? " where " : " and ");
			querybuf.append("d.publicationDate between '" + StartDate + "'"
					+ " and " + "'" + EndDate + "'");
			firstClause = false;

		}
		String queryString = querybuf.toString();
		String queryString2= "select count(pmid) ".concat(queryString);
		
		int currentPage = PageBean.countCurrentPage(page);
		int offset = PageBean.countOffset(pageSize, currentPage);
		queryString=queryString.concat(" order  by publicationDate desc ");
		List<Doctxt> doctxtList = (List<Doctxt>) this.findPagerByHQL(
				queryString, offset, pageSize);
		List list = this.findByHQL(queryString2);
		int allnum = ((Long) list.get(0)).intValue();
		PageBean pageBean = PageBeanUtil.queryForPage(doctxtList, allnum, page,
				pageSize);
		return pageBean;
	}

}
