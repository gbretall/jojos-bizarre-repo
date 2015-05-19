import java.util.ArrayList;


public class DocumentContainer 
{
	private TagNode ee;
	private TagNode url;
	private TagNode year;
	private TagNode isbn;
	private TagNode genre;
	private TagNode	title;
	private TagNode pages;
	private TagNode cdrom;
	private TagNode volume;
	private TagNode number;
	private TagNode series;
	private TagNode editor;
	private ArrayList<TagNode> authors;
	private ArrayList<TagNode> citations;
	private ArrayList<TagNode> crossrefs;
	
	
	
	public DocumentContainer()
	{
		ee			= new TagNode();
		url			= new TagNode();
		year		= new TagNode();
		isbn		= new TagNode();
		genre		= new TagNode();
		title		= new TagNode();
		pages		= new TagNode();
		cdrom		= new TagNode();
		volume		= new TagNode();
		number		= new TagNode();
		series		= new TagNode();
		editor		= new TagNode();
		authors		= new ArrayList<TagNode>();
		citations	= new ArrayList<TagNode>();
		crossrefs	= new ArrayList<TagNode>();
	}

	
	public DocumentContainer(TagNode ee, TagNode url, TagNode year,
			TagNode isbn, TagNode genre, TagNode title, TagNode pages,
			TagNode cdrom, TagNode volume, TagNode number, TagNode series,
			TagNode editor, ArrayList<TagNode> authors,
			ArrayList<TagNode> citations, ArrayList<TagNode> crossrefs) {
		
		this.ee = ee;
		this.url = url;
		this.year = year;
		this.isbn = isbn;
		this.genre = genre;
		this.title = title;
		this.pages = pages;
		this.cdrom = cdrom;
		this.volume = volume;
		this.number = number;
		this.series = series;
		this.editor = editor;
		this.authors = authors;
		this.citations = citations;
		this.crossrefs = crossrefs;
	}


	public void clear()
	{
		ee.clear();
		url.clear();
		year.clear();
		isbn.clear();
		genre.clear();	
		cdrom.clear();
		title.clear();
		pages.clear();
		volume.clear();
		number.clear();		
		series.clear();
		editor.clear();
		
		clearArrayList(citations);
		clearArrayList(crossrefs);
		clearArrayList(authors);
	}
	
	public DocumentContainer copy()
	{
		
		return new DocumentContainer(ee.copy(), url.copy(), year.copy(), isbn.copy(), 
				genre.copy(), title.copy(), pages.copy(), cdrom.copy(), volume.copy(), 
				number.copy(), series.copy(), editor.copy(), copyArrayList(authors), copyArrayList(citations), copyArrayList(crossrefs));
	}
	
	private void clearArrayList(ArrayList<TagNode> nodes)
	{
		for (TagNode node:nodes)
		{
			node.clear();
		}
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
	public static ArrayList<TagNode> copyArrayList(ArrayList<TagNode> list)
	{
		ArrayList<TagNode> copy  = new ArrayList<TagNode>();
		for (TagNode tn: list)
		{
			copy.add(tn.copy());
		}
		return copy;
	}
	
}
