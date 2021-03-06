
public class DocumentContainer 
{
	private TagNode ee;
	private TagNode url;
	private TagNode year;
	private TagNode isbn;
	private TagNode note;
	private TagNode month;
	private TagNode genre;
	private TagNode	title;
	private TagNode pages;
	private TagNode cdrom;
	private TagNode volume;
	private TagNode number;
	private TagNode series;
	private TagNode editor;
	private TagNode school;
	private TagNode address;
	private TagNode journal;
	private TagNode chapter;
	private TagNode publisher;
	private TagNode booktitle;
	
	private NodeCollection authors;
	private NodeCollection citations;
	private NodeCollection crossrefs;
	
	
	
	public DocumentContainer()
	{
		ee			= new TagNode();
		url			= new TagNode();
		year		= new TagNode();
		isbn		= new TagNode();
		note		= new TagNode();
		month		= new TagNode();
		genre		= new TagNode();
		title		= new TagNode();
		pages		= new TagNode();
		cdrom		= new TagNode();
		volume		= new TagNode();
		number		= new TagNode();
		series		= new TagNode();
		editor		= new TagNode();
		school		= new TagNode();
		address		= new TagNode();
		journal		= new TagNode();
		chapter		= new TagNode();
		publisher	= new TagNode();
		booktitle	= new TagNode();
		
		authors		= new NodeCollection();
		citations	= new NodeCollection();
		crossrefs	= new NodeCollection();
		
		
	}
	public DocumentContainer(DocumentContainer doc)
	{
		this.ee			= doc.ee;
		this.url		= doc.url;
		this.year		= doc.year;
		this.isbn		= doc.isbn;
		this.note		= doc.note;
		this.month		= doc.month;
		this.genre		= doc.genre;
		this.title		= doc.title;
		this.pages		= doc.pages;
		this.cdrom		= doc.cdrom;
		this.volume		= doc.volume;
		this.number		= doc.number;
		this.series		= doc.series;
		this.editor		= doc.editor;
		this.school		= doc.school;
		this.address	= doc.address;
		this.journal	= doc.journal;
		this.chapter	= doc.chapter;
		this.publisher	= doc.publisher;
		this.booktitle	= doc.booktitle;
		this.authors	= doc.authors.copy();
		this.citations	= doc.citations.copy();
		this.crossrefs	= doc.crossrefs.copy();

	}
	
	public DocumentContainer(
			TagNode ee,		TagNode url,	TagNode year,	TagNode isbn,
			TagNode note,	TagNode month,	TagNode genre,	TagNode title,	
			TagNode pages,	TagNode cdrom,	TagNode volume,	TagNode number,	
			TagNode series,	TagNode editor,	TagNode school, TagNode address,
			TagNode journal,TagNode chapter,TagNode publisher,	TagNode booktitle,	
			NodeCollection authors,	NodeCollection citations, NodeCollection crossrefs) {
		
		this.ee			= ee;
		this.url		= url;
		this.year		= year;
		this.isbn		= isbn;
		this.note		= note;
		this.month		= month;
		this.genre		= genre;
		this.title		= title;
		this.pages		= pages;
		this.cdrom		= cdrom;
		this.volume		= volume;
		this.number		= number;
		this.series		= series;
		this.editor		= editor;
		this.school		= school;
		this.address	= address;
		this.journal	= journal;
		this.chapter	= chapter;
		this.publisher	= publisher;
		this.booktitle	= booktitle;
		this.authors	= authors;
		this.citations	= citations;
		this.crossrefs	= crossrefs;
	}


	public void clear()
	{
		ee.clear();
		url.clear();
		year.clear();
		isbn.clear();
		note.clear();
		month.clear();
		genre.clear();	
		title.clear();
		pages.clear();
		cdrom.clear();
		volume.clear();
		number.clear();		
		series.clear();
		editor.clear();
		school.clear();
		address.clear();
		chapter.clear();
		publisher.clear();
		booktitle.clear();
		
		citations.clear();
		crossrefs.clear();
		authors.clear();
	}
	
	public DocumentContainer copy()
	{		
		return new DocumentContainer(ee.copy(), url.copy(), year.copy(), isbn.copy(), 
				note.copy(), month.copy(), genre.copy(), title.copy(), pages.copy(), cdrom.copy(), 
				volume.copy(), number.copy(), series.copy(), editor.copy(), 
				school.copy(), address.copy(), journal.copy(), chapter.copy(), publisher.copy(), 
				booktitle.copy(), authors.copy(), citations.copy(), crossrefs.copy());
	}
	
	


	public void detectAddress()
	{
		address.detectAttribute();
	}


	public void detectAuthor()
	{
		authors.detectAttribute();
	}


	public void detectBooktitle()
	{
		booktitle.detectAttribute();
	}


	public void detectCdrom()
	{
		cdrom.detectAttribute();
	}


	public void detectChapter()
	{
		chapter.detectAttribute();
	}


	public void detectCitation()
	{
		citations.detectAttribute();
	}


	public void detectCrossref()
	{
		crossrefs.detectAttribute();
	}


	public void detectEditor()
	{
		editor.detectAttribute();
	}


	public void detectEe()
	{
		ee.detectAttribute();
	}


	public void detectGenre()
	{
		genre.detectAttribute();
	}


	public void detectIsbn()
	{
		isbn.detectAttribute();
	}


	public void detectJournal()
	{
		journal.detectAttribute();
	}

	public void detectMonth()
	{
		month.detectAttribute();
	}


	public void detectNote()
	{
		note.detectAttribute();
	}

	public void detectNumber()
	{
		number.detectAttribute();
	}




	public void detectPages()
	{
		pages.detectAttribute();
	}


	public void detectPublisher()
	{
		publisher.detectAttribute();
	}


	public void detectSchool()
	{
		school.detectAttribute();
	}


	public void detectSeries()
	{
		series.detectAttribute();
	}


	public void detectTitle()
	{
		title.detectAttribute();
	}


	public void detectUrl()
	{
		url.detectAttribute();
	}


	public void detectVolume()
	{
		volume.detectAttribute();
	}


	public void detectYear()
	{
		year.detectAttribute();
	}


	/**
	 * @return the address
	 */
	public TagNode getAddress() {
		return address;
	}


	/**
	 * @return the authors
	 */
	public NodeCollection getAuthors() {
		return authors;
	}


	/**
	 * @return the booktitle
	 */
	public TagNode getBooktitle() {
		return booktitle;
	}


	/**
	 * @return the cdrom
	 */
	public TagNode getCdrom() {
		return cdrom;
	}


	/**
	 * @return the chapter
	 */
	public TagNode getChapter() {
		return chapter;
	}


	/**
	 * @return the citations
	 */
	public NodeCollection getCitations() {
		return citations;
	}


	/**
	 * @return the crossrefs
	 */
	public NodeCollection getCrossrefs() {
		return crossrefs;
	}


	/**
	 * @return the editor
	 */
	public TagNode getEditor() {
		return editor;
	}


	/**
	 * @return the ee
	 */
	public TagNode getEe() {
		return ee;
	}


	/**
	 * @return the genre
	 */
	public TagNode getGenre() {
		return genre;
	}


	/**
	 * @return the isbn
	 */
	public TagNode getIsbn() {
		return isbn;
	}
	
	
	/**
	 * @return the journal
	 */
	public TagNode getJournal() {
		return journal;
	}


	/**
	 * @return the month
	 */
	public TagNode getMonth() {
		return month;
	}


	/**
	 * @return the note
	 */
	public TagNode getNote() {
		return note;
	}


	/**
	 * @return the number
	 */
	public TagNode getNumber() {
		return number;
	}


	/**
	 * @return the pages
	 */
	public TagNode getPages() {
		return pages;
	}


	/**
	 * @return the publisher
	 */
	public TagNode getPublisher() {
		return publisher;
	}


	/**
	 * @return the school
	 */
	public TagNode getSchool() {
		return school;
	}


	/**
	 * @return the series
	 */
	public TagNode getSeries() {
		return series;
	}


	/**
	 * @return the title
	 */
	public TagNode getTitle() {
		return title;
	}


	/**
	 * @return the url
	 */
	public TagNode getUrl() {
		return url;
	}


	/**
	 * @return the volume
	 */
	public TagNode getVolume() {
		return volume;
	}
	/**
	 * @return the year
	 */
	public TagNode getYear() {
		return year;
	}
	
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address.set(address);
	}
	/**
	 * @param authors the authors to set
	 */
	public void setAuthor(String author) {
		this.authors.set(author);
	}
	/**
	 * @param booktitle the booktitle to set
	 */
	public void setBooktitle(String booktitle) {
		this.booktitle.set(booktitle);
	}
	/**
	 * @param cdrom the cdrom to set
	 */
	public void setCdrom(String cdrom) {
		this.cdrom.set(cdrom);
	}
	/**
	 * @param chapter the chapter to set
	 */
	public void setChapter(String chapter) {
		this.chapter.set(chapter);
	}
	/**
	 * @param citations the citations to set
	 */
	public void setCitation(String citation) {
		this.citations.set(citation);
	}
	/**
	 * @param crossrefs the crossrefs to set
	 */
	public void setCrossref(String crossref) {
		this.crossrefs.set(crossref);
	}
	/**
	 * @param editor the editor to set
	 */
	public void setEditor(String editor) {
		this.editor.set(editor);
	}
	/**
	 * @param ee the ee to set
	 */
	public void setEe(String ee) {
		this.ee.set(ee);
	}
	/**
	 * @param genre the genre to set
	 */
	public void setGenre(String genre) {
		this.genre.set(genre);
	}
	/**
	 * @param isbn the isbn to set
	 */
	public void setIsbn(String isbn) {
		this.isbn.set(isbn);
	}
	/**
	 * @param journal the journal to set
	 */
	public void setJournal(String journal) {
		this.journal.set(journal);
	}
	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month.set(month);
	}
	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note.set(note);
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number.set(number);
	}
	/**
	 * @param pages the pages to set
	 */
	public void setPages(String pages) {
		this.pages.set(pages);
	}
	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(String publisher) {
		this.publisher.set(publisher);
	}
	/**
	 * @param school the school to set
	 */
	public void setSchool(String school) {
		this.school.set(school);
	}
	/**
	 * @param series the series to set
	 */
	public void setSeries(String series) {
		this.series.set(series);
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title.set(title);
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url.set(url);
	}
	/**
	 * @param volume the volume to set
	 */
	public void setVolume(String volume) {
		this.volume.set(volume);
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year.set(year);
	}
	
	public String toString()
	{
		String document = new String();
		if (this.getGenre().contentSet())		{document+=outputStr("Genre",		this.getGenre().getContent());		}
		if (this.getAddress().contentSet())		{document+=outputStr("Address", 	this.getAddress().getContent());	}
		if (this.getAuthors().contentSet())		{document+=outputStr("Authors", 	this.getAuthors().toString());		}
		if (this.getBooktitle().contentSet())	{document+=outputStr("Booktitle",	this.getBooktitle().getContent());	}
		if (this.getCdrom().contentSet())		{document+=outputStr("CDROM",		this.getCdrom().getContent());		}
		if (this.getChapter().contentSet())		{document+=outputStr("Chapter",		this.getChapter().getContent());	}
		if (this.getCitations().contentSet())	{document+=outputStr("Citations",	this.getCitations().toString());	}
		if (this.getCrossrefs().contentSet())	{document+=outputStr("Crossrefs",	this.getCrossrefs().toString());	}
		if (this.getEditor().contentSet())		{document+=outputStr("Editor",		this.getEditor().getContent());		}
		if (this.getEe().contentSet())			{document+=outputStr("EE",			this.getEe().getContent());			}
		if (this.getIsbn().contentSet())		{document+=outputStr("ISBN",		this.getIsbn().getContent());		}
		if (this.getJournal().contentSet())		{document+=outputStr("Journal",		this.getJournal().getContent());	}
		if (this.getMonth().contentSet())		{document+=outputStr("Month",		this.getMonth().getContent());		}
		if (this.getNote().contentSet())		{document+=outputStr("Note",		this.getNote().getContent());		}
		if (this.getNumber().contentSet())		{document+=outputStr("Number",		this.getNumber().getContent());		}
		if (this.getPages().contentSet())		{document+=outputStr("Pages",		this.getPages().getContent());		}
		if (this.getPublisher().contentSet())	{document+=outputStr("Publisher",	this.getPublisher().getContent());	}
		if (this.getSchool().contentSet())		{document+=outputStr("School",		this.getSchool().getContent());		}
		if (this.getSeries().contentSet())		{document+=outputStr("Series",		this.getSeries().getContent());		}
		if (this.getTitle().contentSet())		{document+=outputStr("Title",		this.getTitle().getContent());		}
		if (this.getUrl().contentSet())			{document+=outputStr("URL",			this.getUrl().getContent());		}
		if (this.getVolume().contentSet())		{document+=outputStr("Volume",		this.getVolume().getContent());		}
		if (this.getYear().contentSet())		{document+=outputStr("Year",		this.getYear().getContent());		}
				
		return document;
	}
	
	private String outputStr(String title, String content)
	{
		return (title+":\t"+this.getGenre().getContent()+"\n");
	}
	
}
