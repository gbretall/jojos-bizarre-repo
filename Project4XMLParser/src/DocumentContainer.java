
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
	
	


	/**
	 * @return the note
	 */
	public TagNode getNote() {
		return note;
	}


	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note.set(note);
	}


	/**
	 * @return the month
	 */
	public TagNode getMonth() {
		return month;
	}


	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month .set(month);
	}


	/**
	 * @return the school
	 */
	public TagNode getSchool() {
		return school;
	}


	/**
	 * @param school the school to set
	 */
	public void setSchool(String school) {
		this.school.set(school);
	}


	/**
	 * @return the address
	 */
	public TagNode getAddress() {
		return address;
	}


	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address.set(address);
	}


	/**
	 * @return the chapter
	 */
	public TagNode getChapter() {
		return chapter;
	}


	/**
	 * @param chapter the chapter to set
	 */
	public void setChapter(String chapter) {
		this.chapter.set(chapter);;
	}


	/**
	 * @return the title
	 */
	public TagNode getTitle() {
		return title;
	}


	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title.set(title);
	}

	/**
	 * @return the genre
	 */
	public TagNode getGenre() {
		return genre;
	}


	/**
	 * @param genre the genre to set
	 */
	public void setGenre(String genre) {
		this.genre.set(genre);
	}

	/**
	 * @return the pages
	 */
	public TagNode getPages() {
		return pages;
	}




	/**
	 * @param pages the pages to set
	 */
	public void setPages(String pages) {
		this.pages.set(pages);
	}


	/**
	 * @return the year
	 */
	public TagNode getYear() {
		return year;
	}


	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year.set(year);
	}


	/**
	 * @return the volume
	 */
	public TagNode getVolume() {
		return volume;
	}


	/**
	 * @param volume the volume to set
	 */
	public void setVolume(String volume) {
		this.volume.set(volume);
	}


	/**
	 * @return the number
	 */
	public TagNode getNumber() {
		return number;
	}


	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number.set(number);
	}


	/**
	 * @return the url
	 */
	public TagNode getUrl() {
		return url;
	}


	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url.set(url);
	}


	/**
	 * @return the ee
	 */
	public TagNode getEe() {
		return ee;
	}


	/**
	 * @param ee the ee to set
	 */
	public void setEe(String ee) {
		this.ee.set(ee);
	}


	/**
	 * @return the cdrom
	 */
	public TagNode getCdrom() {
		return cdrom;
	}


	/**
	 * @param cdrom the cdrom to set
	 */
	public void setCdrom(String cdrom) {
		this.cdrom.set(cdrom);
	}


	/**
	 * @return the isbn
	 */
	public TagNode getIsbn() {
		return isbn;
	}


	/**
	 * @param isbn the isbn to set
	 */
	public void setIsbn(String isbn) {
		this.isbn.set(isbn);
	}


	/**
	 * @return the series
	 */
	public TagNode getSeries() {
		return series;
	}


	/**
	 * @param series the series to set
	 */
	public void setSeries(String series) {
		this.series.set(series);
	}


	/**
	 * @return the editor
	 */
	public TagNode getEditor() {
		return editor;
	}


	/**
	 * @param editor the editor to set
	 */
	public void setEditor(String editor) {
		this.editor.set(editor);
	}
	
	
	/**
	 * @return the booktitle
	 */
	public TagNode getBooktitle() {
		return booktitle;
	}


	/**
	 * @param booktitle the booktitle to set
	 */
	public void setBooktitle(TagNode booktitle) {
		this.booktitle = booktitle;
	}


	/**
	 * @return the journal
	 */
	public TagNode getJournal() {
		return journal;
	}


	/**
	 * @param journal the journal to set
	 */
	public void setJournal(String journal) {
		this.journal.set(journal);
	}


	/**
	 * @return the authors
	 */
	public NodeCollection getAuthors() {
		return authors;
	}


	/**
	 * @param authors the authors to set
	 */
	public void setAuthor(String author) {
		this.authors.set(author);
	}


	/**
	 * @return the citations
	 */
	public NodeCollection getCitations() {
		return citations;
	}


	/**
	 * @param citations the citations to set
	 */
	public void setCitation(String citation) {
		this.citations.set(citation);
	}


	/**
	 * @return the crossrefs
	 */
	public NodeCollection getCrossrefs() {
		return crossrefs;
	}


	/**
	 * @param crossrefs the crossrefs to set
	 */
	public void setCrossref(String crossref) {
		this.crossrefs.set(crossref);
	}


	/**
	 * @return the publisher
	 */
	public TagNode getPublisher() {
		return publisher;
	}
	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(String publisher) {
		this.publisher.set(publisher);
	}
	
	public void detectEe()
	{
		ee.detectAttribute();
	}
	public void detectUrl()
	{
		url.detectAttribute();
	}
	public void detectIsbn()
	{
		isbn.detectAttribute();
	}
	public void detectYear()
	{
		year.detectAttribute();
	}
	public void detectGenre()
	{
		genre.detectAttribute();
	}
	public void detectTitle()
	{
		title.detectAttribute();
	}
	public void detectPages()
	{
		pages.detectAttribute();
	}
	public void detectCdrom()
	{
		cdrom.detectAttribute();
	}
	public void detectVolume()
	{
		volume.detectAttribute();
	}
	public void detectNumber()
	{
		number.detectAttribute();
	}
	public void detectSeries()
	{
		series.detectAttribute();
	}
	public void detectEditor()
	{
		editor.detectAttribute();
	}
	public void detectAddress()
	{
		address.detectAttribute();
	}
	public void detectMonth()
	{
		month.detectAttribute();
	}
	public void detectNote()
	{
		note.detectAttribute();
	}
	public void detectSchool()
	{
		school.detectAttribute();
	}
	public void detectChapter()
	{
		chapter.detectAttribute();
	}
	public void detectPublisher()
	{
		publisher.detectAttribute();
	}
	public void detectBooktitle()
	{
		booktitle.detectAttribute();
	}
	public void detectCitation()
	{
		citations.detectAttribute();
	}
	public void detectCrossref()
	{
		crossrefs.detectAttribute();
	}
	public void detectAuthor()
	{
		authors.detectAttribute();
	}
	public void detectJournal()
	{
		journal.detectAttribute();
	}
	
	public String toString()
	{
		String document = new String();
		
		
		return document;
	}
}
