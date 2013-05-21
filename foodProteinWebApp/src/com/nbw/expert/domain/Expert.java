package com.nbw.expert.domain;

import java.io.Serializable;

public class Expert implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6488194887532978155L;
	/**
	 * 
	 */

	private Integer eid;
	private String ename;
	private String professional;
	private String affiation;
	private String researchField;
	private String resume;
	private String researchFinding;
	private String jpgname;
	public String getJpgname() {
		return jpgname;
	}
	public void setJpgname(String jpgname) {
		this.jpgname = jpgname;
	}
	public Integer getEid() {
		return eid;
	}
	public void setEid(Integer eid) {
		this.eid = eid;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getProfessional() {
		return professional;
	}
	public void setProfessional(String professional) {
		this.professional = professional;
	}
	public String getAffiation() {
		return affiation;
	}
	public void setAffiation(String affiation) {
		this.affiation = affiation;
	}
	public String getResearchField() {
		return researchField;
	}
	public void setResearchField(String researchField) {
		this.researchField = researchField;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public String getResearchFinding() {
		return researchFinding;
	}
	public void setResearchFinding(String researchFinding) {
		this.researchFinding = researchFinding;
	}

}
