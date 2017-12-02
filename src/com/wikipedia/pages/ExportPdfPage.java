/**
 * 
 */
package com.wikipedia.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.framework.util.KeyboardKeys;
import com.framework.util.Properties;

/**
 * This class contains all the elements needed to manage Export pdf page's web elements and methods
 * and methods that handle the interaction with said page.
 * @author omar
 *
 */

public class ExportPdfPage {
	
	private WebDriver driver;
	
	private String downloadFileNamePath;
	
	/**
	 * Constructs a new ExportPdfPage object, using the driver created in <class>com.wikipedia.tests.Page</class>
	 * It also initializes its web elements 
	 * @param driver WebDriver used to run the tests.
	 */
	
	public ExportPdfPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	
	/* Web Element declaration section*/
	
	@FindBy(how = How.CSS, using = "#plugin")
	WebElement pdfObj;
	
	/**
	 * it downloads the pdf depending on which browser is running.  On Chrome, a ExportPDF page
	 * appears, meanwhile internet explorer shows a small dialog showing options.
	 */
	
	public void downloadPdf() {
		
		if(driver instanceof ChromeDriver) {
		
			try {
				Thread.sleep(Properties.TIMEOUT/10);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println(e1);
			}
			
			// Create object of Robot class
			Robot robotObject;
			try {
				robotObject = new Robot();
				com.framework.util.KeyboardKeys kk = new KeyboardKeys();
				Thread.sleep(Properties.KEYSTROKE_WAIT);
				robotObject.keyPress(KeyEvent.VK_CONTROL);
				robotObject.keyRelease(KeyEvent.VK_S);
				Thread.sleep(Properties.KEYSTROKE_WAIT);
				robotObject.keyPress(KeyEvent.VK_S);
				robotObject.keyRelease(KeyEvent.VK_CONTROL);
				Thread.sleep(Properties.TIMEOUT/2);
				
				Date now = new java.util.Date();
				String currTime = new SimpleDateFormat(Properties.TIMESTAMP_FORMAT).format(new Date());
				downloadFileNamePath = Properties.PDF_SAVE_PATH + Properties.PDF_FILENAME + Properties.FILENAME_SEPARATOR + currTime + Properties.PDF_FILE_EXTENSION;
				for(int i = 0; i < downloadFileNamePath.length(); i++) {
					
					kk.keyPress(downloadFileNamePath.charAt(i));
				}	
				robotObject.keyPress(KeyEvent.VK_ENTER);
				Thread.sleep(Properties.KEYSTROKE_WAIT/10);
				robotObject.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(Properties.TIMEOUT/2);
			} catch (AWTException e) {
				System.out.println(e);
				e.printStackTrace();
			} catch (InterruptedException e) {
				System.out.println(e);
				e.printStackTrace();
			}
		}
		else if(driver instanceof InternetExplorerDriver) {
			//Todo click the save as dialog  and redirect file to save file path.
		}
		else if(driver instanceof FirefoxDriver){
			//Todo click the save as dialog  and redirect file to save file path.
		}
		

	}

	/**
	 * Indicates whether the browser is currently at this page.
	 * @return boolean, true if currently at the ExportPdfPage marked by the pdf object, 
	 * false otherwise
	 */ 
	public boolean isAt() {
		// TODO Auto-generated method stub
		return pdfObj.isDisplayed();
	}
	
	public boolean fileHasDownloaded() {
		File file;
		for(int i = 0; i < (int) Properties.TIMEOUT; i++) {
			try {
				
				 file = new File(downloadFileNamePath);
				if(!file.exists()) {
					Thread.sleep(Properties.FILE_DOWNLOAD_WAIT);
				}
				else return true;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return false;
	}

}
