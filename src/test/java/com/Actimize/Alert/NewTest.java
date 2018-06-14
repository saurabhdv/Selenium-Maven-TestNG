package com.Actimize.Alert;

import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;

public class NewTest {

	private WebDriver driver;
	WebDriverWait wait;

	
	// change URL as per Test machine
	String URL = "http://172.30.21.63:8082/RCM/Alerts.jsp";
	
	/*                                                    */
	
	
	@Test
	public void AlertPresent() {
		
		String alertTableRowXp = " //*[@id=\"section_AlertSummary\"]/tbody/tr";
		String alertTableColumnXp = " //*[@id=\"section_AlertSummary\"]/tbody/tr[1]/td";
		
		
		//Enter the Iframe and then verify for alert details 	
		driver.switchTo().frame(driver.findElement(By.id("frmDetails")));
		String alertsetails = driver.findElement(By.id("section_AlertSummary")).getText();
		int rows = driver.findElements(By.xpath(alertTableRowXp)).size();
		int coloumns= driver.findElements(By.xpath(alertTableColumnXp)).size();
		
		System.out.println("AlertSummary has total of :"+rows*coloumns );
		System.out.println(alertsetails);
		Assert.assertTrue(alertsetails.contains("Payee Name: David Wai"));
	}

	@BeforeTest
	public void login() {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\saverma\\softwares\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(URL);
		// wait for login page to load
		wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("j_username_1")));
		driver.findElement(By.id("j_username_1")).sendKeys("admin");
		driver.findElement(By.id("j_password")).sendKeys("password");
		driver.findElement(By.id("textButton_btnLogin")).click();
		// wait for Workbench- Home page
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[contains(@id,'titleArrow')]")));
		// click DD and choose All Items
		driver.findElement(By.xpath("//*[contains(@id,'titleArrow')]")).click();
		driver.findElement(By.xpath("//*[@title='All Open Items']")).click();
		driver.findElement(By.xpath("//tr[@id=\"3\"]//a[contains (@onclick, \"displayDetails\")]")).click();
		// wait for Alert summary
		
		
	}
	
	@AfterSuite
	public void tearDown() {
		//driver.close();
	}

}
