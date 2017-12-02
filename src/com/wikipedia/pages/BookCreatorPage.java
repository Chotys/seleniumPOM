/**
 * 
 */
package com.wikipedia.pages;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.framework.util.KeyboardKeys;
import com.framework.util.Locators;
import com.framework.util.Properties;

/**
 * This class contains all the elements needed to manage book creator page's web elements and methods
 * and methods that handle the interaction with said page.
 * @author omar
 *
 */
public class BookCreatorPage {	
	
	private WebDriver driver;
	
	/**
	 * Constructs a new BookCreatorPage object, using the driver created in <class>com.wikipedia.tests.Page</class>
	 * It also initializes its web elements 
	 * @param driver WebDriver used to run the tests.
	 */
	
	public BookCreatorPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	
	/* Web Element declaration section*/
	
	@FindBy(how = How.NAME, using = Locators.START_BOOK_CREATOR_NAME)
	WebElement startBookCreator;
	
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
	 * Starts a book by clicking on the "Start Book Creator" button.
	 * @return HomePage
	 */
	public HomePage startBookCreator() {
		
		startBookCreator.click();
		return new HomePage(this.driver);
	}
	
	/**
	 * Indicates whether the browser is currently at this page.  It handles an alert that appears 
	 * occasionally, when the user has previously added pages to creator in previous sessions.
	 * @return boolean, true if currently at the BookCreatorPage marked by the topic header, false otherwise
	 */

	public boolean isAt() {
		try {
			driver.switchTo().alert().dismiss();
			KeyboardKeys kk = new KeyboardKeys();
			kk.keyPress(Properties.ESC_CHARACTER);
		}catch(NoAlertPresentException nape) {
			
			
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return headerLbl.getText().equals(Properties.BOOK_CREATOR_HEADER);
	}
	

}
