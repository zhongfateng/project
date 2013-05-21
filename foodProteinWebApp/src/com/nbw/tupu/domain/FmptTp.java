package com.nbw.tupu.domain;

/**
 * FmptTp entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class FmptTp extends AbstractFmptTp implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public FmptTp() {
	}

	/** minimal constructor */
	public FmptTp(String id) {
		super(id);
	}

	/** full constructor */
	public FmptTp(String id, String genusName, String speciesName,
			String latinName, String stainName, String cultrueCollection,
			String mediumType, String incubationTemperature,
			String incubationTime, String bacterialConcentration,
			String samplePreparation, String sttroma, String matrixSolution,
			String loadingMethod, String instrumentModel,
			String instrumentparameters, String software, String ascii,
			Byte massSpectra, Byte xml, Integer speciesId, String tpimage) {
		super(id, genusName, speciesName, latinName, stainName,
				cultrueCollection, mediumType, incubationTemperature,
				incubationTime, bacterialConcentration, samplePreparation,
				sttroma, matrixSolution, loadingMethod, instrumentModel,
				instrumentparameters, software, ascii, massSpectra, xml,
				speciesId, tpimage);
	}

}
