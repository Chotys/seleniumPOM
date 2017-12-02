/**
 * 
 */
package com.wikipedia.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.framework.util.Locators;

/**
 * This class contains all the elements needed to manage home page's web elements and methods
 * and methods that handle the interaction with said page.
 * @author omar
 *
 */
public class HomePage {
	
	private WebDriver driver;
	
	/**
	 * Constructs a new HomePage object, using the driver created in <class>com.wikipedia.tests.Page</class>
	 * It also initializes its web elements 
	 * @param driver WebDriver used to run the tests.
	 */
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	
	/* Web Element declaration section*/
	
	@FindBy(how = How.LINK_TEXT, using = Locators.CREATE_BOOK_LINK_TEXT)
	WebElement createBookLnk;
	
	@FindBy(how = How.ID, using = Locators.SEARCH_INPUT_ID)
	WebElement searchInputTxt;
	
	@FindBy(how = How.ID, using = Locators.DID_YOU_KNOW_ID)
	WebElement didYouKnowLbl;
	
	@FindBy(how = How.XPATH, using = Locators.ADD_PAGE_TO_BOOK_XPATH)
	WebElement addPageToBookLnk;
	
	/**
	 * Searches a topic in wikipedia by inputting the topic String into the search webedit.
	 * @param topic topic to be searched.
	 * @return ArticlePage
	 */
	
	public ArticlePage searchTopic(String topic) {
		searchInputTxt.clear();
		searchInputTxt.sendKeys(topic);
		searchInputTxt.sendKeys(Keys.ENTER);
			
	    return new ArticlePage(this.driver);
	}
	
	/**
	 * creates a book by clicking on the "Create Book" link.
	 * @return
	 */
	
	public BookCreatorPage createBook() {
		createBookLnk.click();
		
		return new BookCreatorPage(this.driver);
	}
	
	/**
	 * Indicates whether the browser is currently at this page.  It does so by checkin whether the
	 * "Did you Know" Label is displayed.
	 * @return boolean, true if currently at the BookCreatorPage marked by the topic header, false otherwise
	 */
	
	public boolean isAt() {
		return didYouKnowLbl.isDisplayed();
	}
	
	/**
	 * Indicates whether the current page has an "Add Page to Book" link.
	 * @return true if link is present, false otherwise.
	 */
	
	public boolean hasAddPageToBookLink() {
		return addPageToBookLnk.isDisplayed();
	}
	
	
	

}
