package com.nbw.docToDB.domain;

/**
 * CdsId entity. @author MyEclipse Persistence Tools
 */

public class Protein implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 581478560138239132L;
	private int id;
	private String pid;
	private String ptype;
	private String chromosomeRef;
	private String chromosomeStart;
	private String chromosomeEnd;
	private String ncbiProteinRef;
	private String note;
	private String geneId;
	private String speciesId;
	private String ncbiSpeciesId;
	private String sequence;
	private String organism;
	private String speciesName;
	
	
	
	

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

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPtype() {
		return this.ptype;
	}

	public void setPtype(String ptype) {
		this.ptype = ptype;
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

	public String getNcbiProteinRef() {
		return this.ncbiProteinRef;
	}

	public void setNcbiProteinRef(String ncbiProteinRef) {
		this.ncbiProteinRef = ncbiProteinRef;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getGeneId() {
		return this.geneId;
	}

	public void setGeneId(String geneId) {
		this.geneId = geneId;
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