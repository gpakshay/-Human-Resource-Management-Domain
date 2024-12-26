package com.scenario1;



import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.time.Duration;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.generic.Utility;

public class orangeHRMLogic {

	WebDriver driver;
	String expUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index", actUrl;
	
	@Test(dataProvider="excelData", dataProviderClass = readDataFromExcel.class)
	public void login(String username, String password) {
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
		actUrl = driver.getCurrentUrl();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Utility.takeScreenshot(driver, username);
		AssertJUnit.assertEquals(actUrl, expUrl);
		
	}

	@AfterMethod
	public void logout() {
		if (driver.getCurrentUrl().contains("dashboard")) {
			driver.findElement(By.xpath("//span[@class=\"oxd-userdropdown-tab\"]")).click();
			driver.findElement(By.partialLinkText("Log")).click();
			System.out.println("Login successfull");
			
		} else {
			System.out.println("Invalid credentials");
		}
	}

	@BeforeTest
	public void launchBrowser() {
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}

	@AfterTest
	public void closeBrowser() {
		driver.close();
	}

}
