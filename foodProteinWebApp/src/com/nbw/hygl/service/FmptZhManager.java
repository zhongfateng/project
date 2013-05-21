package com.nbw.hygl.service;

import java.util.ArrayList;
import java.util.List;


import com.nbw.hygl.dao.FmptUserDao;
import com.nbw.hygl.dao.FmptZhDAO;
import com.nbw.hygl.domain.FmptZh;
import com.nbw.hygl.domain.ZhJbDTD;
import com.nbw.sys.dao.SysUsersDAO;
import com.nbw.sys.domain.SysUsers;

/**
 * 
 * BzptZhManager
 * 
 * @author
 * 
 */
public class FmptZhManager {

	private FmptZhDAO fmptzhdao;
	private SysUsersDAO sysUsersDAO;
	private FmptUserDao fmptUserDao;
	
	
	public FmptUserDao getFmptUserDao() {
		return fmptUserDao;
	}

	public void setFmptUserDao(FmptUserDao fmptUserDao) {
		this.fmptUserDao = fmptUserDao;
	}

	public SysUsersDAO getSysUsersDAO() {
		return sysUsersDAO;
	}

	public void setSysUsersDAO(SysUsersDAO sysUsersDAO) {
		this.sysUsersDAO = sysUsersDAO;
	}

	/**
	 * 通过id得到对象
	 * 
	 * @param id
	 * @return pObject
	 */
	public FmptZh findById(String id) {
		if (id == null || "".equals(id)) {
			return null;
		} else {
			FmptZh pObject = fmptzhdao.findByID(id);
			return pObject;
		}
	}

	/**
	 * 创建充值账户或者充值 id 是用户 id
	 * 
	 * @param id
	 * @return
	 */
	public FmptZh saveOrUpdetaZh(String id) {
		if (this.isZhExist(id) == false) {// true 表存在 false 表示不存在
			FmptZh zh = new FmptZh();
			SysUsers user = sysUsersDAO.findByID(id);
			zh.setGlid(id);
			// zh.setJbid(Constants.pthyID);
			zh.setZhm(user.getLoginCode());
			zh.setPtmoney(0.0);
			zh.setType(0);
			this.saveBzptZh(zh);
			return zh;
		} else {
			FmptZh zh2 = this.findByUserId(id);
			return zh2;
		}
	}

	/**
	 *  不开具发票，保存在 充值记录
	 */
	/*@SuppressWarnings("unchecked")
	public void updateNoFpZh(String czrId, String czdId, FmptZh command) {
		BzptZhJl bzptzhjl = new BzptZhJl();
		FmptZh oldZh = bzptzhdao.findByID(command.getId());
		Double czje = command.getPtmoney() - oldZh.getPtmoney();
		bzptzhjl.setCzr(czrId);
		bzptzhjl.setCzsj(new Date());
		bzptzhjl.setCzzhid(command.getGlid());
		bzptzhjl.setCzje(czje);
		bzptzhjldao.save(bzptzhjl);
		bzptzhdao.update(command);
		BzptCzd czd = bzptczddao.findByID(czdId);
		czd.setIscl("1");
		bzptczddao.update(czd);

	}*/

	/**
	 * 跳转到开发票
	 * 
	 * @param isInvoice
	 * @param czrId
	 * @param czdId
	 * @param command
	 * @return
	 */
	@SuppressWarnings( { "unused", "unchecked" })
	/*public Map toFpZh(String czrId, String czdId, FmptZh command) {
		Map map = new HashMap();
		String glId = command.getGlid();
		FmptZh oldZh = bzptzhdao.findByID(command.getId());
		Double czje = command.getPtmoney() - oldZh.getPtmoney();
		map.put("user", bzptUserDao.getBzptUsersDetail(glId));
		map.put("czje", czje);
		map.put("glId", glId);
		map.put("czdId", czdId);
		map.put("fp",bzptfpdao.findBzptFpByGmrId(czrId,czdId) );
		return map;
	}*/
	
	/**
	 * 更新账户，保存账户发票记录，
	 */
	/*public void updateFpZh(String czrId, String glId, String czdId, BzptFp fp) {

		FmptZh zh = findByUserId(glId);
		BzptZhJl bzptzhjl = new BzptZhJl();
		BzptFp bzptfp = new BzptFp();
		// 更新操作 账号，与记录，
		Double czje = fp.getJe();

		bzptzhjl.setCzr(czrId);
		bzptzhjl.setCzsj(new Date());
		bzptzhjl.setCzzhid(zh.getGlid());
		bzptzhjl.setCzje(czje);

		bzptfp.setIsinvoice("0");
		
		bzptfp.setGmrId(glId);
		bzptfp.setJe(czje);
		bzptfp.setInvoicecontent(fp.getInvoicecontent());
		bzptfp.setAddress(fp.getAddress());
		bzptfp.setFptt(fp.getFptt());
		bzptfp.setMobile(fp.getMobile());
		
		bzptfp.setCzdID(czdId);
		bzptfpdao.save(bzptfp);

		bzptzhjldao.save(bzptzhjl);
		zh.setPtmoney(zh.getPtmoney() + czje);
		bzptzhdao.update(zh);

		BzptCzd pObject = bzptczddao.findByID(czdId);
		pObject.setIscl("1");//充值过
		pObject.setIsXYFP("1");//需要开具发票
		bzptczddao.update(pObject);
	}*/

	/**
	 * 通过userId得到对象
	 * 
	 * @param id
	 * @return pObject
	 */
	public FmptZh findByUserId(String glId) {

		if (glId == null || "".equals(glId)) {
			return null;
		} else {
			FmptZh zh = new FmptZh();
			List zhList = fmptzhdao.findByGLID(glId);
			if (zhList != null && zhList.size() > 0) {
				zh = (FmptZh) zhList.get(0);
				return zh;
			} else {
				zh.setZhm((sysUsersDAO.findByID(glId).getName()));
				zh.setPtmoney(0.0);
				return zh;
			}

		}

	}

	/**
	 * 调整账号
	 * 
	 * @param zhId
	 * @param tzje
	 * @param adminId
	 * @return
	 */
	/*public boolean updatetzZhInfo(String zhId, Double tzje, String adminId) {

		try {
			FmptZh zh = saveOrUpdetaZh(zhId);
			Double newJe = zh.getPtmoney() + tzje;
			zh.setPtmoney(newJe);
			fmptzhdao.update(zh);
			BzptZhJl bzptzhjl = new BzptZhJl();
			bzptzhjl.setCzr(adminId);
			bzptzhjl.setCzsj(new Date());
			bzptzhjl.setCzzhid(zh.getGlid());
			bzptzhjl.setCzje(tzje);
			bzptzhjldao.save(bzptzhjl);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}*/

	/**
	 * 获得该对象所有数据的列表
	 * 
	 * @return list
	 */
	public List getAll() {
		return fmptzhdao.findAll();
	}

	/**
	 * 查询全部的用户明成与其账号信息
	 * 
	 * @return
	 */
	public List getsysUsersWithZh() {

		return fmptzhdao.findsysUsersWithZh();

	}

	/**
	 * 根具部門ID获得全部用户金额
	 * 
	 * @param organId
	 * @return
	 */
	public List getsysUsersWithZhByOrg(String organId) {
		if ("ROOT".equals(organId)) {
			return fmptzhdao.findsysUsersWithZh();
		} else {
			return fmptzhdao.findsysUsersWithZhByOrgan(organId);
		}

	}

	/**
	 * 根据部门ID获得全部用户级别
	 * 
	 * @param orgId
	 * @return
	 */
	public List<ZhJbDTD> getAllUserJb(String orgId) {
		List list = fmptzhdao.findUserJbByOrg(orgId);
		List<ZhJbDTD> zhJbList = new ArrayList<ZhJbDTD>();
		for (int i = 0; i < list.size(); i++) {
			Object[] data = (Object[]) list.get(i);
			ZhJbDTD jb = new ZhJbDTD();
			jb.setZhID((String) data[0]);
			jb.setUserName((String) data[1]);
			jb.setZhm((String) data[2]);
			jb.setZhJbID((String) data[3]);
			jb.setZhJbName((String) data[4]);
			jb.setLoginCode((String) data[5]);
			jb.setUserID((String) data[6]);
			zhJbList.add(jb);
		}
		return zhJbList;

	}

	/**
	 * 判断是否存在
	 * 
	 * @param glid
	 * @return
	 */
	public Boolean isZhExist(String glid) {

		return fmptzhdao.isZhExist(glid);

	}

	/**
	 * 保存，inser1
	 * 
	 * @param bzptzh
	 */
	public void saveBzptZh(FmptZh bzptzh) {

		fmptzhdao.save(bzptzh);

	}

	/**
	 * 编辑对象
	 * 
	 * @param bzptzh
	 */
	public void editBzptZh(FmptZh bzptzh) {
		
		fmptzhdao.saveOrUpdate(bzptzh);
	}

	/**
	 * 删除对象
	 * 
	 * @param id
	 */
	public void deleteBzptZh(String id) {
		fmptzhdao.delete(id);
	}

	public FmptZhDAO getFmptzhdao() {
		return fmptzhdao;
	}

	public void setFmptzhdao(FmptZhDAO fmptzhdao) {
		this.fmptzhdao = fmptzhdao;
	}

	// *******************************************************************************set
	// and get
	

}
