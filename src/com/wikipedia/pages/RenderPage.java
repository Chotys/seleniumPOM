/**
 * 
 */
package com.wikipedia.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.framework.util.Locators;
import com.framework.util.Properties;

/**
 * This class contains all the elements needed to manage render page's web elements and methods
 * and methods that handle the interaction with said page.
 * @author omar
 *
 */
public class RenderPage {
	
	private WebDriver driver;
	
	/**
	 * Constructs a new ArticlePage object, using the driver created in <class>com.wikipedia.tests.Page</class>
	 * It also initializes its web elements 
	 * @param driver WebDriver used to run the tests.
	 */
	
	public RenderPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	
	/* Web Element declaration section*/
	
	@FindBy(how = How.XPATH, using = Locators.DOWNLOAD_FILE_XPATH)
	WebElement downloadFileLnk;
	
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
	    return new ArticlePage(this.driver);
	}
	
	/**
	 * clicks on the "Download the file" link once the page finishes rendering.
	 * @return ExportPdfPage
	 */
	
	public ExportPdfPage downloadFile() {
		//TO DO  first wait for render to finish
		WebDriverWait wait = new WebDriverWait(driver, Properties.TIMEOUT);         
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(Properties.DOWNLOAD_FILE_LINK_TEXT)));
	     
		downloadFileLnk.click();
		return new ExportPdfPage(this.driver);
	}

	/**
	 * Indicates whether the browser is currently at this page.  
	 * @return boolean, true if currently at the RenderPage marked by the topic header, false otherwise
	 */
	
	public boolean isAt() {
		// TODO Auto-generated method stub
		return headerLbl.getText().equals(Properties.RENDER_PAGE_HEADER1) || headerLbl.getText().equals(Properties.RENDER_PAGE_HEADER2);
	}
	
	//driver.findElement(By.linkText("Download the file")).click();

}
