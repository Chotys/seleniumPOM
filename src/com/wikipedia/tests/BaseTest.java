/**
 * 
 */
package com.wikipedia.tests;



import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.framework.util.Properties;
import com.wikipedia.pages.ArticlePage;
import com.wikipedia.pages.BookCreatorPage;
import com.wikipedia.pages.BookManagementPage;
import com.wikipedia.pages.ExportPdfPage;
import com.wikipedia.pages.HomePage;
import com.wikipedia.pages.RenderPage;




/**
 * This class implements the Parent class to all tests in the package.  It serves as a container
 * of all the instance variables to every testcase.  This way, we avoid calling the new keyword,
 * making the code in tests more readable.
 * @author omar
 *
 */
public class BaseTest {
	
	WebDriver driver;
	String url;
	ArticlePage articlePage;
	BookCreatorPage bookCreatorPage;
	BookManagementPage bookManagementPage;
	ExportPdfPage exportPdfPage;
	HomePage homePage;
	RenderPage renderPage;
	

	/**
	 * runs before every class to set up the environment by initializing the driver according to
	 * the browser and url parameter.  it also initializes the homePage object.
	 * @param browser
	 * @param url
	 */	
	public void setUp(String browser, String url) {
		switch(browser){
		case Properties.CHROME_BROWSER:
			System.setProperty(Properties.CHROME_DRIVER_PROPERTY, Properties.CHROME_DRIVER_PATH);
			driver = new ChromeDriver();
			break;
		case Properties.IE_BROWSER:
			System.setProperty(Properties.IE_DRIVER_PROPERTY, Properties.IE_DRIVER_PATH);
			driver = new InternetExplorerDriver();
			break;
		case Properties.FIREFOX_BROWSER:
			System.setProperty(Properties.FIREFOX_DRIVER_PROPERTY, Properties.CHROME_DRIVER_PATH);
			driver = new FirefoxDriver();
			break;
			
		}
		
		this.url = url;
		driver.get(this.url);
		driver.manage().timeouts().implicitlyWait(Properties.TIMEOUT, TimeUnit.SECONDS);
		homePage = new HomePage(this.driver);
		
		
	}
	
	/**
	 * cleans up the environment by closing the browser.
	 */
	@After
	public void tearDown() {
		driver.quit();
		
	}

}
