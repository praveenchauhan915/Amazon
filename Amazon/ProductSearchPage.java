package Amazon;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductSearchPage {
	WebDriver driver;

	By searchField = By.id("twotabsearchtextbox");
	By searchBtn = By.id("nav-search-submit-button");
	
	By linkTxt = By.xpath(".//a[contains(@href,'/sspa/click')]");
	
	

	void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	void searchAndCaptureProduct(String productName) {
		openAmazonSite();

		waitSeconds(10);

		searchFieldFill(productName);
		searchBtnClick();

		waitSeconds(10);

		/*captureScreenshot("Screenshot1.png");*/

		scrollPage();
		waitSeconds(5);
		scrollPage();
		waitSeconds(5);
		scrollPage();
		waitSeconds(5);
		scrollPage();
		waitSeconds(5);
		/*captureScreenshot("Screenshot2.png");*/
	}

	List<Product> getProductList() {
		List<WebElement> links = driver.findElements(linkTxt);

		System.out.println(links.size());

		String[] linktext = new String[links.size()];

		int i = 0;
		for (WebElement e : links) {
			System.out.println(e);
			linktext[i] = (e).getText();
			i++;
		}
		
		System.out.println(linktext.length);
		
		List<Product> products = new ArrayList<>();

		for (String t : linktext) {
			
			Product p = new Product();
			p.productName = t;
			p.productPrice = 0;
			products.add(p);
			//waitSeconds(5);
			//System.out.println(t);
			//driver.findElement(By.linkText(t)).click();
			/*
			 * String at = driver.getTitle(); if (at.equals("Sony Bravia")) {
			 * System.out.println(t + " is sony"); } else { System.out.println(t +
			 * " is not sony "); }
			 */
			//waitSeconds(5);
			//driver.navigate().back();
		}
		return products;
	}

	void openAmazonSite() {
		driver.get("http://www.amazon.in/");
		driver.manage().window().maximize();
	}

	void waitSeconds(int seconds) {
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}

	void searchFieldFill(String value) {
		WebElement searchbar = driver.findElement(searchField);
		searchbar.sendKeys(value);
	}

	void searchBtnClick() {
		WebElement search = driver.findElement(searchBtn);
		search.click();
	}

	void scrollPage() {
		JavascriptExecutor j = (JavascriptExecutor) driver;
		j.executeScript("window.scrollBy(0,500)");
	}

	void captureScreenshot(String fileName) {

		try {
			File ss = (File) ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(ss, new File("C:\\Users\\laksh\\Documents\\amazon\\" + fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
