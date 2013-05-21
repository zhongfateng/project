package com.nbw.docToDB.domain;

/**
 * JiyinId entity. @author MyEclipse Persistence Tools
 */

public class Gene implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7140580724330358718L;
	
	
	
	private int id;
	private String gid;
	private String gtype;
	private String gname;
	private String chromosomeRef;
	private String chromosomeStart;
	private String chromosomeEnd;
	private String ncbiGeneRef;
	private String locusTag;
	private String proteinId;
	private String speciesId;
	private String ncbiSpeciesId;
	private String sequence;
	private String organism;
	private String speciesName;

	// Property accessors
	
	
	

	public String getGid() {
		return gid;
	}

	public String getSpeciesName() {
		return speciesName;
	}

	public void setSpeciesName(String speciesName) {
		this.speciesName = speciesName;
	}

	public String getOrganism() {
		return organism;
	}

	public void setOrganism(String organism) {
		this.organism = organism;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getGtype() {
		return this.gtype;
	}

	public void setGtype(String gtype) {
		this.gtype = gtype;
	}

	public String getGname() {
		return this.gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getChromosomeRef() {
		return this.chromosomeRef;
	}

	public void setChromosomeRef(String chromosomeRef) {
		this.chromosomeRef = chromosomeRef;
	}

	public String getChromosomeStart() {
		return this.chromosomeStart;
	}

	public void setChromosomeStart(String chromosomeStart) {
		this.chromosomeStart = chromosomeStart;
	}

	public String getChromosomeEnd() {
		return this.chromosomeEnd;
	}

	public void setChromosomeEnd(String chromosomeEnd) {
		this.chromosomeEnd = chromosomeEnd;
	}

	public String getNcbiGeneRef() {
		return this.ncbiGeneRef;
	}

	public void setNcbiGeneRef(String ncbiGeneRef) {
		this.ncbiGeneRef = ncbiGeneRef;
	}

	public String getLocusTag() {
		return this.locusTag;
	}

	public void setLocusTag(String locusTag) {
		this.locusTag = locusTag;
	}

	public String getProteinId() {
		return this.proteinId;
	}

	public void setProteinId(String proteinId) {
		this.proteinId = proteinId;
	}

	public String getSpeciesId() {
		return this.speciesId;
	}

	public void setSpeciesId(String speciesId) {
		this.speciesId = speciesId;
	}

	public String getNcbiSpeciesId() {
		return this.ncbiSpeciesId;
	}

	public void setNcbiSpeciesId(String ncbiSpeciesId) {
		this.ncbiSpeciesId = ncbiSpeciesId;
	}

	public String getSequence() {
		return this.sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}


}