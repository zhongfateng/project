package com.nbw.hygl.dao;

import java.util.ArrayList;
import java.util.List;




import com.nbw.common.GenericDAO;
import com.nbw.hygl.domain.FmptHyJb;
import com.nbw.hygl.domain.FmptZh;
import com.nbw.hygl.domain.ZhJbDTD;
import com.nbw.sys.domain.SysUsers;



import org.apache.commons.collections.Transformer;
import org.hibernate.transform.Transformers;
import org.springframework.jdbc.core.JdbcTemplate;
/**
 * 
 * BzptZhDAO
 * 
 * @author
 * 
 */
public class FmptZhDAO extends GenericDAO<FmptZh, String> {

	/**
	 * 判断是否有账号 true 有 false表示没有
	 * 
	 * @param userid
	 * @return
	 */
	public boolean isZhExist(String userid) {
		String hql = "select zh from FmptZh zh where zh.glid= ?";
		List list = this.findByHQL(hql, userid);
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * T通过 glId 查找账号
	 * 
	 * @param glId
	 * @return
	 */
	public List<FmptZh> findByGLID(String glId) {
		String hql = "select zh from FmptZh zh where zh.glid= ?";
		return (List<FmptZh>) super.findByHQL(hql, glId);

	}

	/**
	 * 右外连接查询
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SysUsers> findsysUsersWithZh() {

		String sql = "select {u.*},{zh.*} from sys_users u,fmpt_zh zh where u.id=zh.glid(+) and u.valid_flag <> '-1' order by zh.ptmoney ";
		List sysList = getSession().createSQLQuery(sql).addEntity("u",
				SysUsers.class).addEntity("zh", FmptZh.class).list();
		List<SysUsers> returnList = new ArrayList();
		for (int i = 0; i < sysList.size(); i++) {
			Object[] ss = (Object[]) sysList.get(i);
			FmptZh zh;
			if (ss[1] == null) {
				zh = new FmptZh();
				zh.setPtmoney(0.0);
			} else {
				zh = (FmptZh) ss[1];
			}
			((SysUsers) ss[0]).setZh(zh);
			returnList.add(((SysUsers) ss[0]));
		}
		return returnList;

	}

	/**
	 * 右外连接查询
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SysUsers> findsysUsersWithZhByOrgan(String organId) {

		String sql = "select {u.*},{zh.*} from sys_users u,fmpt_zh zh where u.id=zh.glid(+) and u.valid_flag <> '-1' and u.org_id='"
				+ organId + "' order by zh.ptmoney ";
		List sysList = getSession().createSQLQuery(sql).addEntity("u",
				SysUsers.class).addEntity("zh", FmptZh.class).list();
		List<SysUsers> returnList = new ArrayList();
		for (int i = 0; i < sysList.size(); i++) {
			Object[] ss = (Object[]) sysList.get(i);
			FmptZh zh;
			if (ss[1] == null) {
				zh = new FmptZh();
				zh.setPtmoney(0.0);
			} else {
				zh = (FmptZh) ss[1];
			}
			((SysUsers) ss[0]).setZh(zh);
			returnList.add(((SysUsers) ss[0]));
		}
		return returnList;

	}

	public void updateZHje(String gmrId, double je) {
		String hql = "update FmptZh zh set zh.ptmoney = '" + je + "'"
				+ " where zh.glid='" + gmrId + "'";
		super.executeHQL(hql);
	}
/**
 * 通过组织机构查找用户fm
 * @param orgId
 * @return
 */
	public List findUserJbByOrg(String orgId) {
		//根据账户表关联
		/*String sql = "select '' zid, u.su_name, zh.zhm,'' jbid,jb.jbmc,u.login_code,u.id " +
				"from sys_users u, fmpt_zh zh,fmpt_hy_jb jb where  u.id = zh.glid and u.valid_flag!=-1 and u.jbid = jb.id ";*/
		//根据积分表关联
		String sql = "select '' zid, u.su_name, jf.username,'' jbid,jb.jbmc,u.login_code,u.id " +
		"from sys_users u left join fmpt_jf_sum jf on u.id = jf.userid  left join fmpt_hy_jb jb on u.jbid = jb.id  where u.valid_flag!=-1  ";
		if ("ROOT".equals(orgId) == false) {// 不是 ROOT时候执行
			sql += " and u.ORG_ID='" + orgId + "'";
		}
		return this.findBySQL(sql);
	}
	
}
