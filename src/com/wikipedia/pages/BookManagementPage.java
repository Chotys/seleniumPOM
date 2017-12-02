package com.wikipedia.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.framework.util.Locators;
import com.framework.util.Properties;

/**
 * This class contains all the elements needed to manage book management page's web elements and methods
 * and methods that handle the interaction with said page.
 * @author omar
 *
 */

public class BookManagementPage {
	
	private WebDriver driver;
	
	/**
	 * Constructs a new BookManagementPage object, using the driver created in <class>com.wikipedia.tests.Page</class>
	 * It also initializes its web elements 
	 * @param driver WebDriver used to run the tests.
	 */
	
	public BookManagementPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	
	/* Web Element declaration section*/
	
	@FindBy(how = How.ID, using = Locators.BOOK_TITLE_ID)
	WebElement titleTxt;
	
	@FindBy(how = How.ID, using = Locators.BOOK_SUBTITLE_ID)
	WebElement subtitleTxt;
	
	@FindBy(how = How.ID, using = Locators.DOWNLOAD_BUTTON_ID)
	WebElement downloadBtn;
	
	@FindBy(how = How.ID, using = Locators.SEARCH_INPUT_ID)
	WebElement searchInputTxt;
	
	@FindBy(how = How.ID, using = Locators.HEADER_ID)
	WebElement headerLbl;
	
	/**
	 * Searches a topic in wikipedia by inputting the topic String into the search webedit.
	 * @param topic topic to be searched.
	 * @return ArticlePage
	 */
	
	public ArticlePage searchTopic(String topic) {
		searchInputTxt.clear();
		searchInputTxt.sendKeys(topic);
		//todo
		//in case a list of topics appears, click on the first one.
	    return new ArticlePage(this.driver);
	}
	
	/**
	 * inputs both title and subtitle in the appropriate webedits, and then clicks the download 
	 * button.
	 * @param title book title
	 * @param subtitle book subtitle
	 * @return RenderPage
	 */
	
	public RenderPage nameBookAndDownload(String title, String subtitle) {
		titleTxt.clear();
		titleTxt.sendKeys(title);
		subtitleTxt.clear();
		subtitleTxt.sendKeys(subtitle);
		downloadBtn.click();		
		return new RenderPage(this.driver);
	}

	/**
	 * Indicates whether the browser is currently at this page.
	 * @param topic
	 * @return boolean, true if currently at the BookManagement marked by the topic header, 
	 * false otherwise
	 */
	
	public boolean isAt() {
		// TODO Auto-generated method stub
		return headerLbl.getText().equals(Properties.BOOK_MGMT_HEADER);
	}

}
