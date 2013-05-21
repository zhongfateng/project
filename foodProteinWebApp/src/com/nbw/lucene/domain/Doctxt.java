package com.nbw.lucene.domain;

/**
 * Doctxt entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Doctxt extends AbstractDoctxt implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Doctxt() {
	}

	/** minimal constructor */
	public Doctxt(Integer did) {
		super(did);
	}

	/** full constructor */
	public Doctxt(Integer did, String pmid, String firAuthor, String affiation,
			String author, String corporateAuthor, String authorFull,
			String endAuthor, String book, String isbn, String journal,
			String issue, String completionDate, String createDate,
			String entrezDate, String meShdate, String modificationDate,
			String publicationDate, String rnnumber, String editor,
			String filter, String grantNumber, String investigator,
			String investigatorFull, String language, String contry,
			String locationId, String meShmajorTopic, String meShsubheading,
			String meShterms, String pagination, String pharmacologicalAction,
			String publicationType, String publisher, String secondarySourceId,
			String supplementaryConcept, String textWord, String title,
			String transliteratedTitle, String abstract_, String volume,
			String substanceName, String issn, String cn, String keyWord,
			String subjectTerm, Integer featureWord, String apec,
			String textFull, Integer gid, String gname, Integer speid,
			String speName) {
		super(did, pmid, firAuthor, affiation, author, corporateAuthor,
				authorFull, endAuthor, book, isbn, journal, issue,
				completionDate, createDate, entrezDate, meShdate,
				modificationDate, publicationDate, rnnumber, editor, filter,
				grantNumber, investigator, investigatorFull, language, contry,
				locationId, meShmajorTopic, meShsubheading, meShterms,
				pagination, pharmacologicalAction, publicationType, publisher,
				secondarySourceId, supplementaryConcept, textWord, title,
				transliteratedTitle, abstract_, volume, substanceName, issn,
				cn, keyWord, subjectTerm, featureWord, apec, textFull, gid,
				gname, speid, speName);
	}
	
	public Doctxt(String author,String title,String journal,String abstract_)
	{
		super(author,title,journal,abstract_);
		
		
	}
	
	
	

}
