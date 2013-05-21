package com.nbw.lucene.domain;

/**
 * AbstractDoctxt entity provides the base persistence definition of the Doctxt
 * entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractDoctxt implements java.io.Serializable {

	// Fields

	private Integer did;
	private String pmid;
	private String firAuthor;
	private String affiation;
	private String author;
	private String corporateAuthor;
	private String authorFull;
	private String endAuthor;
	private String book;
	private String isbn;
	private String journal;
	private String issue;
	private String completionDate;
	private String createDate;
	private String entrezDate;
	private String meShdate;
	private String modificationDate;
	private String publicationDate;
	private String rnnumber;
	private String editor;
	private String filter;
	private String grantNumber;
	private String investigator;
	private String investigatorFull;
	private String language;
	private String contry;
	private String locationId;
	private String meShmajorTopic;
	private String meShsubheading;
	private String meShterms;
	private String pagination;
	private String pharmacologicalAction;
	private String publicationType;
	private String publisher;
	private String secondarySourceId;
	private String supplementaryConcept;
	private String textWord;
	private String title;
	private String transliteratedTitle;
	private String abstract_;
	private String volume;
	private String substanceName;
	private String issn;
	private String cn;
	private String keyWord;
	private String subjectTerm;
	private Integer featureWord;
	private String apec;
	private String textFull;
	private Integer gid;
	private String gname;
	private Integer speid;
	private String speName;

	// Constructors

	/** default constructor */
	public AbstractDoctxt() {
	}

	/** minimal constructor */
	public AbstractDoctxt(Integer did) {
		this.did = did;
	}

	/** full constructor */
	public AbstractDoctxt(Integer did, String pmid, String firAuthor,
			String affiation, String author, String corporateAuthor,
			String authorFull, String endAuthor, String book, String isbn,
			String journal, String issue, String completionDate,
			String createDate, String entrezDate, String meShdate,
			String modificationDate, String publicationDate, String rnnumber,
			String editor, String filter, String grantNumber,
			String investigator, String investigatorFull, String language,
			String contry, String locationId, String meShmajorTopic,
			String meShsubheading, String meShterms, String pagination,
			String pharmacologicalAction, String publicationType,
			String publisher, String secondarySourceId,
			String supplementaryConcept, String textWord, String title,
			String transliteratedTitle, String abstract_, String volume,
			String substanceName, String issn, String cn, String keyWord,
			String subjectTerm, Integer featureWord, String apec,
			String textFull, Integer gid, String gname, Integer speid,
			String speName) {
		this.did = did;
		this.pmid = pmid;
		this.firAuthor = firAuthor;
		this.affiation = affiation;
		this.author = author;
		this.corporateAuthor = corporateAuthor;
		this.authorFull = authorFull;
		this.endAuthor = endAuthor;
		this.book = book;
		this.isbn = isbn;
		this.journal = journal;
		this.issue = issue;
		this.completionDate = completionDate;
		this.createDate = createDate;
		this.entrezDate = entrezDate;
		this.meShdate = meShdate;
		this.modificationDate = modificationDate;
		this.publicationDate = publicationDate;
		this.rnnumber = rnnumber;
		this.editor = editor;
		this.filter = filter;
		this.grantNumber = grantNumber;
		this.investigator = investigator;
		this.investigatorFull = investigatorFull;
		this.language = language;
		this.contry = contry;
		this.locationId = locationId;
		this.meShmajorTopic = meShmajorTopic;
		this.meShsubheading = meShsubheading;
		this.meShterms = meShterms;
		this.pagination = pagination;
		this.pharmacologicalAction = pharmacologicalAction;
		this.publicationType = publicationType;
		this.publisher = publisher;
		this.secondarySourceId = secondarySourceId;
		this.supplementaryConcept = supplementaryConcept;
		this.textWord = textWord;
		this.title = title;
		this.transliteratedTitle = transliteratedTitle;
		this.abstract_ = abstract_;
		this.volume = volume;
		this.substanceName = substanceName;
		this.issn = issn;
		this.cn = cn;
		this.keyWord = keyWord;
		this.subjectTerm = subjectTerm;
		this.featureWord = featureWord;
		this.apec = apec;
		this.textFull = textFull;
		this.gid = gid;
		this.gname = gname;
		this.speid = speid;
		this.speName = speName;
	}

	public AbstractDoctxt(String author,String title,String journal,String abstract_)
	{
		this.abstract_ = abstract_;
		this.journal = journal;
		this.title = title;
		this.author = author;
	}
	
	
	
	// Property accessors

	public Integer getDid() {
		return this.did;
	}

	public void setDid(Integer did) {
		this.did = did;
	}

	public String getPmid() {
		return this.pmid;
	}

	public void setPmid(String pmid) {
		this.pmid = pmid;
	}

	public String getFirAuthor() {
		return this.firAuthor;
	}

	public void setFirAuthor(String firAuthor) {
		this.firAuthor = firAuthor;
	}

	public String getAffiation() {
		return this.affiation;
	}

	public void setAffiation(String affiation) {
		this.affiation = affiation;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCorporateAuthor() {
		return this.corporateAuthor;
	}

	public void setCorporateAuthor(String corporateAuthor) {
		this.corporateAuthor = corporateAuthor;
	}

	public String getAuthorFull() {
		return this.authorFull;
	}

	public void setAuthorFull(String authorFull) {
		this.authorFull = authorFull;
	}

	public String getEndAuthor() {
		return this.endAuthor;
	}

	public void setEndAuthor(String endAuthor) {
		this.endAuthor = endAuthor;
	}

	public String getBook() {
		return this.book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getJournal() {
		return this.journal;
	}

	public void setJournal(String journal) {
		this.journal = journal;
	}

	public String getIssue() {
		return this.issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public String getCompletionDate() {
		return this.completionDate;
	}

	public void setCompletionDate(String completionDate) {
		this.completionDate = completionDate;
	}

	public String getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getEntrezDate() {
		return this.entrezDate;
	}

	public void setEntrezDate(String entrezDate) {
		this.entrezDate = entrezDate;
	}

	public String getMeShdate() {
		return this.meShdate;
	}

	public void setMeShdate(String meShdate) {
		this.meShdate = meShdate;
	}

	public String getModificationDate() {
		return this.modificationDate;
	}

	public void setModificationDate(String modificationDate) {
		this.modificationDate = modificationDate;
	}

	public String getPublicationDate() {
		return this.publicationDate;
	}

	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getRnnumber() {
		return this.rnnumber;
	}

	public void setRnnumber(String rnnumber) {
		this.rnnumber = rnnumber;
	}

	public String getEditor() {
		return this.editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public String getFilter() {
		return this.filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getGrantNumber() {
		return this.grantNumber;
	}

	public void setGrantNumber(String grantNumber) {
		this.grantNumber = grantNumber;
	}

	public String getInvestigator() {
		return this.investigator;
	}

	public void setInvestigator(String investigator) {
		this.investigator = investigator;
	}

	public String getInvestigatorFull() {
		return this.investigatorFull;
	}

	public void setInvestigatorFull(String investigatorFull) {
		this.investigatorFull = investigatorFull;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getContry() {
		return this.contry;
	}

	public void setContry(String contry) {
		this.contry = contry;
	}

	public String getLocationId() {
		return this.locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getMeShmajorTopic() {
		return this.meShmajorTopic;
	}

	public void setMeShmajorTopic(String meShmajorTopic) {
		this.meShmajorTopic = meShmajorTopic;
	}

	public String getMeShsubheading() {
		return this.meShsubheading;
	}

	public void setMeShsubheading(String meShsubheading) {
		this.meShsubheading = meShsubheading;
	}

	public String getMeShterms() {
		return this.meShterms;
	}

	public void setMeShterms(String meShterms) {
		this.meShterms = meShterms;
	}

	public String getPagination() {
		return this.pagination;
	}

	public void setPagination(String pagination) {
		this.pagination = pagination;
	}

	public String getPharmacologicalAction() {
		return this.pharmacologicalAction;
	}

	public void setPharmacologicalAction(String pharmacologicalAction) {
		this.pharmacologicalAction = pharmacologicalAction;
	}

	public String getPublicationType() {
		return this.publicationType;
	}

	public void setPublicationType(String publicationType) {
		this.publicationType = publicationType;
	}

	public String getPublisher() {
		return this.publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getSecondarySourceId() {
		return this.secondarySourceId;
	}

	public void setSecondarySourceId(String secondarySourceId) {
		this.secondarySourceId = secondarySourceId;
	}

	public String getSupplementaryConcept() {
		return this.supplementaryConcept;
	}

	public void setSupplementaryConcept(String supplementaryConcept) {
		this.supplementaryConcept = supplementaryConcept;
	}

	public String getTextWord() {
		return this.textWord;
	}

	public void setTextWord(String textWord) {
		this.textWord = textWord;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTransliteratedTitle() {
		return this.transliteratedTitle;
	}

	public void setTransliteratedTitle(String transliteratedTitle) {
		this.transliteratedTitle = transliteratedTitle;
	}

	public String getAbstract_() {
		return this.abstract_;
	}

	public void setAbstract_(String abstract_) {
		this.abstract_ = abstract_;
	}

	public String getVolume() {
		return this.volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getSubstanceName() {
		return this.substanceName;
	}

	public void setSubstanceName(String substanceName) {
		this.substanceName = substanceName;
	}

	public String getIssn() {
		return this.issn;
	}

	public void setIssn(String issn) {
		this.issn = issn;
	}

	public String getCn() {
		return this.cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public String getKeyWord() {
		return this.keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getSubjectTerm() {
		return this.subjectTerm;
	}

	public void setSubjectTerm(String subjectTerm) {
		this.subjectTerm = subjectTerm;
	}

	public Integer getFeatureWord() {
		return this.featureWord;
	}

	public void setFeatureWord(Integer featureWord) {
		this.featureWord = featureWord;
	}

	public String getApec() {
		return this.apec;
	}

	public void setApec(String apec) {
		this.apec = apec;
	}

	public String getTextFull() {
		return this.textFull;
	}

	public void setTextFull(String textFull) {
		this.textFull = textFull;
	}

	public Integer getGid() {
		return this.gid;
	}

	public void setGid(Integer gid) {
		this.gid = gid;
	}

	public String getGname() {
		return this.gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public Integer getSpeid() {
		return this.speid;
	}

	public void setSpeid(Integer speid) {
		this.speid = speid;
	}

	public String getSpeName() {
		return this.speName;
	}

	public void setSpeName(String speName) {
		this.speName = speName;
	}

}