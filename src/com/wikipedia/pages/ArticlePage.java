/**
 * 
 */
package com.wikipedia.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.framework.util.Locators;
import com.framework.util.Properties;

/**
 * This class contains all the elements needed to manage article page's web elements and methods
 * and methods that handle the interaction with said page.
 * @author omar
 *
 */
public class ArticlePage {
	
	private WebDriver driver;
	
	/**
	 * Constructs a new ArticlePage object, using the driver created in <class>com.wikipedia.tests.Page</class>
	 * It also initializes its web elements 
	 * @param driver WebDriver used to run the tests.
	 */
	
	public ArticlePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	
	/* Web Element declaration section*/
	
	@FindBy(how = How.XPATH, using = Locators.ADD_PAGE_TO_BOOK_XPATH)
	WebElement addPageToBookLnk;
	
	@FindBy(how = How.PARTIAL_LINK_TEXT, using = Locators.SHOW_BOOK_LINK_PARTIAL)
	WebElement showBookLnk;
	
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
		searchInputTxt.sendKeys(Keys.ENTER);
	    return new ArticlePage(this.driver);
	}
	
	/**
	 * adds a topic page to a book.  returns an ArticlePage object.
	 * @return ArticlePage
	 */
	
	public ArticlePage addPageToBook() {
		addPageToBookLnk.click();
		return this;
	}
	
	/**
	 * clicks the "show book(* Page(s))" link.  
	 * @return BookManagementPage
	 */
	public BookManagementPage showBook() {
		try {
			showBookLnk.click();
		}catch(StaleElementReferenceException sere) {
			driver.findElement(By.partialLinkText(Locators.SHOW_BOOK_LINK_PARTIAL)).click();
		}catch(NoSuchElementException nsee)
		{
			driver.findElement(By.partialLinkText(Locators.SHOW_BOOK_LINK_PARTIAL)).click();			
		}
		catch(WebDriverException wde){
			driver.findElement(By.partialLinkText(Locators.SHOW_BOOK_LINK_PARTIAL)).click();
		}
		return new BookManagementPage(this.driver);
	}

	/**
	 * Indicates whether the browser is currently at this page.
	 * @param topic
	 * @return boolean, true if currently at the ArticlePage marked by the topic header, false otherwise
	 */
	public boolean isAt(String topic) {
		return headerLbl.getText().equals(topic);
	}

	/**
	 * Indicates whether the current page has an "Add Page to Book" link.
	 * @return true if link is present, false otherwise.
	 */
	public boolean hasAddPageToBookLink() {
		return addPageToBookLnk.isDisplayed();
	}
}
