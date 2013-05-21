package com.nbw.tupu.domain;

/**
 * AbstractFmptTp entity provides the base persistence definition of the FmptTp
 * entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractFmptTp implements java.io.Serializable {

	// Fields

	private String id;
	private String genusName;
	private String speciesName;
	private String latinName;
	private String stainName;
	private String cultrueCollection;
	private String mediumType;
	private String incubationTemperature;
	private String incubationTime;
	private String bacterialConcentration;
	private String samplePreparation;
	private String sttroma;
	private String matrixSolution;
	private String loadingMethod;
	private String instrumentModel;
	private String instrumentparameters;
	private String software;
	private String ascii;
	private Byte massSpectra;
	private Byte xml;
	private Integer speciesId;
	private String tpimage;

	// Constructors

	/** default constructor */
	public AbstractFmptTp() {
	}

	/** minimal constructor */
	public AbstractFmptTp(String id) {
		this.id = id;
	}

	/** full constructor */
	public AbstractFmptTp(String id, String genusName, String speciesName,
			String latinName, String stainName, String cultrueCollection,
			String mediumType, String incubationTemperature,
			String incubationTime, String bacterialConcentration,
			String samplePreparation, String sttroma, String matrixSolution,
			String loadingMethod, String instrumentModel,
			String instrumentparameters, String software, String ascii,
			Byte massSpectra, Byte xml, Integer speciesId, String tpimage) {
		this.id = id;
		this.genusName = genusName;
		this.speciesName = speciesName;
		this.latinName = latinName;
		this.stainName = stainName;
		this.cultrueCollection = cultrueCollection;
		this.mediumType = mediumType;
		this.incubationTemperature = incubationTemperature;
		this.incubationTime = incubationTime;
		this.bacterialConcentration = bacterialConcentration;
		this.samplePreparation = samplePreparation;
		this.sttroma = sttroma;
		this.matrixSolution = matrixSolution;
		this.loadingMethod = loadingMethod;
		this.instrumentModel = instrumentModel;
		this.instrumentparameters = instrumentparameters;
		this.software = software;
		this.ascii = ascii;
		this.massSpectra = massSpectra;
		this.xml = xml;
		this.speciesId = speciesId;
		this.tpimage = tpimage;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGenusName() {
		return this.genusName;
	}

	public void setGenusName(String genusName) {
		this.genusName = genusName;
	}

	public String getSpeciesName() {
		return this.speciesName;
	}

	public void setSpeciesName(String speciesName) {
		this.speciesName = speciesName;
	}

	public String getLatinName() {
		return this.latinName;
	}

	public void setLatinName(String latinName) {
		this.latinName = latinName;
	}

	public String getStainName() {
		return this.stainName;
	}

	public void setStainName(String stainName) {
		this.stainName = stainName;
	}

	public String getCultrueCollection() {
		return this.cultrueCollection;
	}

	public void setCultrueCollection(String cultrueCollection) {
		this.cultrueCollection = cultrueCollection;
	}

	public String getMediumType() {
		return this.mediumType;
	}

	public void setMediumType(String mediumType) {
		this.mediumType = mediumType;
	}

	public String getIncubationTemperature() {
		return this.incubationTemperature;
	}

	public void setIncubationTemperature(String incubationTemperature) {
		this.incubationTemperature = incubationTemperature;
	}

	public String getIncubationTime() {
		return this.incubationTime;
	}

	public void setIncubationTime(String incubationTime) {
		this.incubationTime = incubationTime;
	}

	public String getBacterialConcentration() {
		return this.bacterialConcentration;
	}

	public void setBacterialConcentration(String bacterialConcentration) {
		this.bacterialConcentration = bacterialConcentration;
	}

	public String getSamplePreparation() {
		return this.samplePreparation;
	}

	public void setSamplePreparation(String samplePreparation) {
		this.samplePreparation = samplePreparation;
	}

	public String getSttroma() {
		return this.sttroma;
	}

	public void setSttroma(String sttroma) {
		this.sttroma = sttroma;
	}

	public String getMatrixSolution() {
		return this.matrixSolution;
	}

	public void setMatrixSolution(String matrixSolution) {
		this.matrixSolution = matrixSolution;
	}

	public String getLoadingMethod() {
		return this.loadingMethod;
	}

	public void setLoadingMethod(String loadingMethod) {
		this.loadingMethod = loadingMethod;
	}

	public String getInstrumentModel() {
		return this.instrumentModel;
	}

	public void setInstrumentModel(String instrumentModel) {
		this.instrumentModel = instrumentModel;
	}

	public String getInstrumentparameters() {
		return this.instrumentparameters;
	}

	public void setInstrumentparameters(String instrumentparameters) {
		this.instrumentparameters = instrumentparameters;
	}

	public String getSoftware() {
		return this.software;
	}

	public void setSoftware(String software) {
		this.software = software;
	}

	public String getAscii() {
		return this.ascii;
	}

	public void setAscii(String ascii) {
		this.ascii = ascii;
	}

	public Byte getMassSpectra() {
		return this.massSpectra;
	}

	public void setMassSpectra(Byte massSpectra) {
		this.massSpectra = massSpectra;
	}

	public Byte getXml() {
		return this.xml;
	}

	public void setXml(Byte xml) {
		this.xml = xml;
	}

	public Integer getSpeciesId() {
		return this.speciesId;
	}

	public void setSpeciesId(Integer speciesId) {
		this.speciesId = speciesId;
	}

	public String getTpimage() {
		return this.tpimage;
	}

	public void setTpimage(String tpimage) {
		this.tpimage = tpimage;
	}

}