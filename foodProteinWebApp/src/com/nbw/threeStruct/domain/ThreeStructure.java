package com.nbw.threeStruct.domain;

public class ThreeStructure implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4981081729034876551L;
	private int id;
	private String sname;
	private String taxonomy;
	private String proteins;
	private String chemicals;
	private String mmdbId;
	private String pdbId;
	private String selfId;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getTaxonomy() {
		return taxonomy;
	}
	public void setTaxonomy(String taxonomy) {
		this.taxonomy = taxonomy;
	}
	public String getProteins() {
		return proteins;
	}
	public void setProteins(String proteins) {
		this.proteins = proteins;
	}
	public String getChemicals() {
		return chemicals;
	}
	public void setChemicals(String chemicals) {
		this.chemicals = chemicals;
	}
	public String getMmdbId() {
		return mmdbId;
	}
	public void setMmdbId(String mmdbId) {
		this.mmdbId = mmdbId;
	}
	public String getPdbId() {
		return pdbId;
	}
	public void setPdbId(String pdbId) {
		this.pdbId = pdbId;
	}
	public String getSelfId() {
		return selfId;
	}
	public void setSelfId(String selfId) {
		this.selfId = selfId;
	}
	
	
}
