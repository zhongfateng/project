package com.nbw.hygl.service;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.nbw.bookshop.dao.BzptBookDAO;
import com.nbw.bookshop.domain.BzptBook;
import com.nbw.common.SysParameter;
import com.nbw.common.util.PageBean;
import com.nbw.hygl.dao.BzptHyGmjlDAO;
import com.nbw.hygl.domain.BzptHyGmjl;
import com.nbw.resources.dao.BzptSecondMlDAO;
import com.nbw.resources.domain.BzptItem;
import com.nbw.resources.service.StandardResourcesManager;

/**
 * 
 * BzptHyGmjlManager
 * 
 * @author 張帆
 * 
 */
public class BzptHyGmjlManager {

	private BzptHyGmjlDAO bzpthygmjldao;
	private BzptSecondMlDAO bzptsecondmldao;
	private StandardResourcesManager standardResourcesManager;
	private BzptBookDAO bzptbookdao;

	/**
	 * 通过id得到对象
	 * 
	 * @param id
	 * @return pObject
	 */
	public BzptHyGmjl findById(String id) {
		if (id == null || "".equals(id)) {
			return null;
		} else {
			BzptHyGmjl pObject = bzpthygmjldao.findByID(id);
			return pObject;
		}
	}

	/**
	 * 获得该对象所有数据的列表
	 * 
	 * @return list
	 */
	public List getAll() {
		return bzpthygmjldao.findAll();
	}

	/**
	 * 保存对象
	 * 
	 * @param bzpthygmjl
	 */
	public void saveBzptHyGmjl(BzptHyGmjl bzpthygmjl) {
		bzpthygmjldao.save(bzpthygmjl);
	}

	/**
	 * 编辑对象
	 * 
	 * @param bzpthygmjl
	 */
	public void editBzptHyGmjl(BzptHyGmjl bzpthygmjl) {
		bzpthygmjldao.update(bzpthygmjl);
	}

	/**
	 * 删除对象
	 * 
	 * @param id
	 */
	public void deleteBzptHyGmjl(String id) {
		bzpthygmjldao.delete(id);
	}

	/**
	 * 查看该用戶是否已购买了该标准
	 */
	public boolean isGm(String userid, String bztlId) {
		return this.bzpthygmjldao.isGmjlExist(userid, bztlId);
	}

	public List findMost(String value) {
		return this.bzpthygmjldao.findByBztlId(value);
	}

	/**
	 * 根据userid 分页显示购买记录
	 */
	@SuppressWarnings("unchecked")
	public PageBean findByUser(String userid, int page, int pageSize) {
		PageBean pageBean = this.bzpthygmjldao.findByUser(userid, page,
				pageSize);
		List<BzptHyGmjl> dlist = pageBean.getList();
		this.findHyGmjl(dlist);
		return pageBean;
	}

	/**
	 * 根据用户id和订单号查看订单详细(分页显示)
	 */
	@SuppressWarnings("unchecked")
	public PageBean findByUAndO(String userid, String ordernum, int page,
			int pageSize) {
		PageBean pageBean = this.bzpthygmjldao.findByUAndO(userid, ordernum,
				page, pageSize);
		List<BzptHyGmjl> dlist = pageBean.getList();
		this.findHyGmjl(dlist);
		return pageBean;
	}

	/**
	 * 查看订单详细
	 * 
	 * @param userid
	 * @param ordernum
	 * @return
	 */
	public List<BzptHyGmjl> findByUAndOrder(String userid, String ordernum) {
		List<BzptHyGmjl> dlist = this.bzpthygmjldao.findByUAndOrder(userid,
				ordernum);
		this.findHyGmjl(dlist);
		return dlist;
	}
	public List<BzptHyGmjl> findGmjuById(String orderNum){
		List<BzptHyGmjl> list=this.bzpthygmjldao.findGmjlById(orderNum);
		if(list.size()>0&&list!=null){
			return list;
		}else{
			try {
				throw new Exception("系统出现异常，请稍后再试");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public void findHyGmjl(List<BzptHyGmjl> dlist) {
		BzptItem bzptItem = new BzptItem();
		String tableName = "";
		for (BzptHyGmjl g : dlist) {
			if (g.getType().equals("1")) {
				tableName = bzptsecondmldao.findBySid(g.getSid()).get(0)
						.getTableName();
				bzptItem = standardResourcesManager.findResourceById(tableName, g
						.getBztlId());
				g.setTableName(tableName);
				if (bzptItem == null) {
				    	g.setDflag("0");// 已删除
					    g.setPath("");
				} else {
					g.setDflag("1");
					g.setPath(bzptItem.getPath());
				}
			}
			if (g.getType().equals("2")) {
				BzptBook bzptBook = bzptbookdao.findByID(g.getBztlId());
				g.setBzName(bzptBook.getBt());
			}
			//设置资源下载超时
            String fistDate = g.getFirstdownload();
            if(fistDate!=null&&!"".equals(fistDate)){
	            double downtime = new Double(SysParameter.getParameter("downloadTime"));
	            Calendar c = new GregorianCalendar();
	            Date d = c.getTime();
	            java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					Date endDate = format.parse(format.format(d));
					 Date beginDate;
			            double day = 0;
			            if(fistDate != null && !"".equals(fistDate)) {
			                 beginDate = format.parse(fistDate);
			                 day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000.0);
			                 if (day > downtime) {
			                	 g.setTimeOut(true);
			                 }
			            }
				} catch (ParseException e) {
					e.printStackTrace();
				}
            }
		}
	}
	/**
	 * 根据用户id和订单号查看资源子订单详细
	 */
	@SuppressWarnings("unchecked")
	public List<BzptHyGmjl> findResourcesAll(String userid, String ordernum) {
		List<BzptHyGmjl> list = this.bzpthygmjldao.findResourcesAll(userid, ordernum);
		this.findHyGmjl(list);
		return list;
	}
	/**
	 * 根据用户id和订单号查看图书子订单详细
	 */
	@SuppressWarnings("unchecked")
	public List<BzptHyGmjl> findBookAll(String userid, String ordernum) {
		List<BzptHyGmjl> list = this.bzpthygmjldao.findBookAll(userid, ordernum);
		this.findHyGmjl(list);
		return list;
	}

	// *******************************************************************************set
	// and get
	public BzptHyGmjlDAO getBzpthygmjldao() {
		return bzpthygmjldao;
	}

	public void setBzpthygmjldao(BzptHyGmjlDAO bzpthygmjldao) {
		this.bzpthygmjldao = bzpthygmjldao;
	}

	public BzptSecondMlDAO getBzptsecondmldao() {
		return bzptsecondmldao;
	}

	public void setBzptsecondmldao(BzptSecondMlDAO bzptsecondmldao) {
		this.bzptsecondmldao = bzptsecondmldao;
	}

	public StandardResourcesManager getStandardResourcesManager() {
		return standardResourcesManager;
	}

	public void setStandardResourcesManager(
			StandardResourcesManager standardResourcesManager) {
		this.standardResourcesManager = standardResourcesManager;
	}

	public BzptBookDAO getBzptbookdao() {
		return bzptbookdao;
	}

	public void setBzptbookdao(BzptBookDAO bzptbookdao) {
		this.bzptbookdao = bzptbookdao;
	}

}
