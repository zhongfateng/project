package com.nbw.hygl.domain;

/**
 * FmptHyJb entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class FmptHyJb extends AbstractFmptHyJb implements java.io.Serializable {

	public static Integer VALIDFLAG_TRUE = 1;
	public static Integer VALIDFLAG_FALSE = -1;
	// Constructors

	/** default constructor */
	public FmptHyJb() {
	}

	/** full constructor */
	public FmptHyJb(String id, Double jbjf, String jbmc, Double jbzk,
			Integer validFlag) {
		super(id, jbjf, jbmc, jbzk, validFlag);
	}

}
