package com.nbw.jz.domain;

/**
 * FmptStrain entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class FmptStrain extends AbstractFmptStrain implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public FmptStrain() {
	}

	/** minimal constructor */
	public FmptStrain(String id) {
		super(id);
	}

	/** full constructor */
	public FmptStrain(String id, Integer nodeId, String ename, String cname,
			Integer parentid, String updatedate, Integer dbz, Integer jy,
			Integer rna, Integer tp, Integer wx) {
		super(id, nodeId, ename, cname, parentid, updatedate, dbz, jy, rna, tp,
				wx);
	}

}
