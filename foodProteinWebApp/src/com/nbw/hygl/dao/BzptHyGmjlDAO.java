package com.nbw.hygl.dao;

import java.util.*;

import com.nbw.hygl.domain.BzptHyGmjl;
import com.nbw.common.GenericDAO;
import com.nbw.common.util.PageBean;
import com.nbw.common.util.PageBeanUtil;

/**
 * 
 * BzptHyGmjlDAO
 * 
 * @author 張帆
 * 
 */
public class BzptHyGmjlDAO extends GenericDAO<BzptHyGmjl, String> {

	/**
	 * 判断用户是否已经购买了该标准
	 * 
	 * @param userid
	 * @param bztlId
	 * @return
	 */
	public boolean isGmjlExist(String userid, String bztlId) {
		String hql = "select g from BzptHyGmjl g where g.userid=? and  g.bztlId =?";
		List list = this.findByHQL(hql, userid, bztlId);
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 查看购买了该标准的用户还购买了(购买次数最多的前5条标准id) List里面存放的是标准id
	 */
	@SuppressWarnings("unchecked")
	public List<Object> findByBztlId(String bztlId) {
		String hql = "select count(t.id),t.bztlId,t.sid from BzptHyGmjl t where t.userid in (select t.userid from BzptHyGmjl t where t.bztlId ='"
				+ bztlId
				+ "') and t.bztlId <>'"
				+ bztlId
				+ "' and t.type = '1' group by t.bztlId,t.sid order by count(t.id) desc ";
		return (List<Object>) this.findPagerByHQL(hql, 0, 5, null);
	}

	/**
	 * 根据userid 分页显示购买记录
	 */
	@SuppressWarnings("unchecked")
	public PageBean findByUser(String userid, int page, int pageSize) {
		String hql = "select g from BzptHyGmjl g  where  g.userid = '" + userid
				+ "' order by g.cjsj desc";
		int currentPage = PageBean.countCurrentPage(page);
		int offset = PageBean.countOffset(pageSize, currentPage);
		List<BzptHyGmjl> list = (List<BzptHyGmjl>) findPagerByHQL(hql, offset,
				pageSize);
		String hsqlAllRow = "select count(g.id) from BzptHyGmjl g where  g.userid = '"
				+ userid + "'";
		List li = findByHQL(hsqlAllRow);
		int allRowSize = ((Long) li.get(0)).intValue();
		PageBean pageBean = PageBeanUtil.queryForPage(list, allRowSize, page,
				pageSize);
		return pageBean;
	}

	/**
	 * 根据用户id和订单号查看订单详细(分页显示)
	 */
	@SuppressWarnings("unchecked")
	public PageBean findByUAndO(String userid, String ordernum, int page,
			int pageSize) {
		String hql = "select g from BzptHyGmjl g where  g.userid = ? and g.ordernum=? order by g.type";
		int currentPage = PageBean.countCurrentPage(page);
		int offset = PageBean.countOffset(pageSize, currentPage);
		List<BzptHyGmjl> list = (List<BzptHyGmjl>) findPagerByHQL(hql, offset,
				pageSize, userid, ordernum);
		String hsqlAllRow = "select count(g.id) from BzptHyGmjl g where  g.userid = '"
				+ userid + "' and g.ordernum = '" + ordernum + "'";
		List li = findByHQL(hsqlAllRow);
		int allRowSize = ((Long) li.get(0)).intValue();
		PageBean pageBean = PageBeanUtil.queryForPage(list, allRowSize, page,
				pageSize);
		return pageBean;
	}

	/**
	 * 根据用户名和订单号查看订单详细
	 * 
	 * @param userid
	 * @param ordernum
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<BzptHyGmjl> findByUAndOrder(String userid, String ordernum) {
		String hql = "select g from BzptHyGmjl g where  g.userid = ? and g.ordernum=? order by g.type";
		return (List<BzptHyGmjl>) this.findByHQL(hql, userid, ordernum);
	}
	
	/**
	 * 根据用户id和订单号查看资源子订单详细
	 */
	@SuppressWarnings("unchecked")
	public List<BzptHyGmjl> findResourcesAll(String userid, String ordernum) {
		String hql = "select g from BzptHyGmjl g where  g.userid = ? and g.ordernum=? and g.type=1 order by g.type";
		List<BzptHyGmjl> list = (List<BzptHyGmjl>) this.findByHQL(hql, userid,ordernum);
		return list;
	}
	/**
	 * 根据用户id和订单号查看图书子订单详细
	 */
	@SuppressWarnings("unchecked")
	public List<BzptHyGmjl> findBookAll(String userid, String ordernum) {
		String hql = "select g from BzptHyGmjl g where  g.userid = ? and g.ordernum=? and g.type=2 order by g.type";
		List<BzptHyGmjl> list = (List<BzptHyGmjl>) this.findByHQL(hql, userid,ordernum);
		return list;
	}
	public List<BzptHyGmjl> findGmjlById(String OrderNum){
		String hql="select g from BzptHyGmjl g where g.ordernum=?";
		List<BzptHyGmjl> list=(List<BzptHyGmjl>) this.findByHQL(hql,OrderNum);
		return list;
	}
}
