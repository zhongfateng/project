package com.nbw.testOrg.domain;

import java.io.Serializable;

public class TestOrg implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -977860791120052637L;
	private int tid;
	private String oname;
	private String oaddr;
	private String opeople;
	private String otel;
	private String obook;
	private String oprovince;
	private String oproject;
	private String oinstrument;
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getOname() {
		return oname;
	}
	public void setOname(String oname) {
		this.oname = oname;
	}
	public String getOaddr() {
		return oaddr;
	}
	public void setOaddr(String oaddr) {
		this.oaddr = oaddr;
	}
	public String getOpeople() {
		return opeople;
	}
	public void setOpeople(String opeople) {
		this.opeople = opeople;
	}
	public String getOtel() {
		return otel;
	}
	public void setOtel(String otel) {
		this.otel = otel;
	}
	public String getObook() {
		return obook;
	}
	public void setObook(String obook) {
		this.obook = obook;
	}
	public String getOprovince() {
		return oprovince;
	}
	public void setOprovince(String oprovince) {
		this.oprovince = oprovince;
	}
	public String getOproject() {
		return oproject;
	}
	public void setOproject(String oproject) {
		this.oproject = oproject;
	}
	public String getOinstrument() {
		return oinstrument;
	}
	public void setOinstrument(String oinstrument) {
		this.oinstrument = oinstrument;
	}
	
}
