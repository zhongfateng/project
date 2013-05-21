package com.nbw.docToDB.domain;

/**
 * RnaId entity. @author MyEclipse Persistence Tools
 */

public class Rna implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7940346399463727409L;
	// Fields
	private int id;
	private String gid;
	private String chromosomeRef;
	private String chromosomeStart;
	private String chromosomeEnd;
	private String gbkey;
	private String geneId;
	private String speciesId;
	
	
	public String getSpeciesId() {
		return speciesId;
	}

	public void setSpeciesId(String speciesId) {
		this.speciesId = speciesId;
	}

	private String ncbiSpeciesId;
	private String note;
	private String sequence;
	private String organism;
	private String speciesName;
	
	
	
	
	
	// Constructors


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

	public String getGid() {
		return gid;
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

	public String getGbkey() {
		return this.gbkey;
	}

	public void setGbkey(String gbkey) {
		this.gbkey = gbkey;
	}

	public String getGeneId() {
		return this.geneId;
	}

	public void setGeneId(String geneId) {
		this.geneId = geneId;
	}

	public String getNcbiSpeciesId() {
		return this.ncbiSpeciesId;
	}

	public void setNcbiSpeciesId(String ncbiSpeciesId) {
		this.ncbiSpeciesId = ncbiSpeciesId;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getSequence() {
		return this.sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}


}