package com.nbw.jz.domain;

/**
 * AbstractFmptStrain entity provides the base persistence definition of the
 * FmptStrain entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractFmptStrain implements java.io.Serializable {

	// Fields

	private String id;
	private Integer nodeId;
	private String ename;
	private String cname;
	private Integer parentid;
	private String updatedate;
	private Integer dbz;
	private Integer jy;
	private Integer rna;
	private Integer tp;
	private Integer wx;

	// Constructors

	/** default constructor */
	public AbstractFmptStrain() {
	}

	/** minimal constructor */
	public AbstractFmptStrain(String id) {
		this.id = id;
	}

	/** full constructor */
	public AbstractFmptStrain(String id, Integer nodeId, String ename,
			String cname, Integer parentid, String updatedate, Integer dbz,
			Integer jy, Integer rna, Integer tp, Integer wx) {
		this.id = id;
		this.nodeId = nodeId;
		this.ename = ename;
		this.cname = cname;
		this.parentid = parentid;
		this.updatedate = updatedate;
		this.dbz = dbz;
		this.jy = jy;
		this.rna = rna;
		this.tp = tp;
		this.wx = wx;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getNodeId() {
		return this.nodeId;
	}

	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}

	public String getEname() {
		return this.ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Integer getParentid() {
		return this.parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public String getUpdatedate() {
		return this.updatedate;
	}

	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

	public Integer getDbz() {
		return this.dbz;
	}

	public void setDbz(Integer dbz) {
		this.dbz = dbz;
	}

	public Integer getJy() {
		return this.jy;
	}

	public void setJy(Integer jy) {
		this.jy = jy;
	}

	public Integer getRna() {
		return this.rna;
	}

	public void setRna(Integer rna) {
		this.rna = rna;
	}

	public Integer getTp() {
		return this.tp;
	}

	public void setTp(Integer tp) {
		this.tp = tp;
	}

	public Integer getWx() {
		return this.wx;
	}

	public void setWx(Integer wx) {
		this.wx = wx;
	}

}