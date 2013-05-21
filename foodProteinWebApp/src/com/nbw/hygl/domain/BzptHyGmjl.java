package com.nbw.hygl.domain;

import java.util.Date;

import com.nbw.resources.domain.BzptFirstMl;
import com.nbw.sys.domain.SysUsers;

/**
 * BzptHyGmjl entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class BzptHyGmjl extends AbstractBzptHyGmjl implements
		java.io.Serializable {

	// Constructors
	private BzptFirstMl ml;
	private SysUsers su;
	private String bzName;
	private String tableName;
	private String path;
	private String dflag;//删除标志，0删除，1未删除
	private boolean timeOut;//true 超时 false 未超时

	/** default constructor */
	public BzptHyGmjl() {
	}

	/** full constructor */
	public BzptHyGmjl(String bztlId, Date cjsj, Double mrjg, String zffs,
			String userid, String fid, String sid, String ordernum, Double zk,
			Double jf, String zyly, String type, String encrypt, Long buycount,
			String bzname,String firstdownload) {
		super(bztlId, cjsj, mrjg, zffs, userid, fid, sid, ordernum, zk, jf,
				zyly, type, encrypt, buycount, bzname,firstdownload);
	}

	public BzptFirstMl getMl() {
		return ml;
	}

	public void setMl(BzptFirstMl ml) {
		this.ml = ml;
	}

	public SysUsers getSu() {
		return su;
	}

	public void setSu(SysUsers su) {
		this.su = su;
	}

	public String getBzName() {
		return bzName;
	}

	public void setBzName(String bzName) {
		this.bzName = bzName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDflag() {
		return dflag;
	}

	public void setDflag(String dflag) {
		this.dflag = dflag;
	}

	public boolean getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(boolean timeOut) {
		this.timeOut = timeOut;
	}

}
