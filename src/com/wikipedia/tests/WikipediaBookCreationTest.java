/**
 * 
 */
package com.wikipedia.tests;



import com.framework.util.Properties;
import com.wikipedia.pages.ArticlePage;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * This class tests creating wikipedia books and downloading a PDF file from it.
 * @author omar
 *
 */
@RunWith(JUnitParamsRunner.class)
public class WikipediaBookCreationTest extends BaseTest {
	
	/**
	 * creates a book with two topics, and downloads the created PDF. It also validates the PDF
	 * was downloaded succesfully.
	 * @param topic1
	 * @param topic2
	 */
	@Test
	@FileParameters("./data/data.csv")
	public void createTopicBook(String topic1, String topic2, String browser, String url) {
		
		this.setUp(browser, url);
		
		assertTrue(homePage.isAt());
		
		bookCreatorPage = homePage.createBook();
		
		assertTrue(bookCreatorPage.isAt());
		
		homePage = bookCreatorPage.startBookCreator();
		
		assertTrue(homePage.isAt());
		
		assertTrue(homePage.hasAddPageToBookLink());
		
		articlePage = homePage.searchTopic(topic1);
		
		assertTrue(articlePage.hasAddPageToBookLink());
		
		assertTrue(articlePage.isAt(topic1));
		
		articlePage = articlePage.addPageToBook();
		
		articlePage = articlePage.searchTopic(topic2);
		
		assertTrue(articlePage.hasAddPageToBookLink());
		
		assertTrue(articlePage.isAt(topic2));
		
		articlePage = articlePage.addPageToBook();
		
		bookManagementPage = articlePage.showBook();
		
		assertTrue(bookManagementPage.isAt());
		
		renderPage = bookManagementPage.nameBookAndDownload(Properties.BOOK_TITLE, Properties.BOOK_SUBTITLE);
		
		assertTrue(renderPage.isAt());
		
		exportPdfPage = renderPage.downloadFile();
		
		assertTrue(exportPdfPage.isAt());
		
		exportPdfPage.downloadPdf();		
		
	}
	
	
	
	

}
