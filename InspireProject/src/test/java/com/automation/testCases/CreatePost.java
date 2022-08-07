package com.automation.testCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreatePost {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
//		System.setProperty("webdriver.gecko.driver", "./Drivers/geckodriver.exe");
//		WebDriver driver = new FirefoxDriver();
		
		//loading web url
		driver.get("https://inspire.com/");
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		//clicking on signIn button
		driver.findElement(By.id("logIn")).click();
		
		//Some times email and password field is not present in the overlay instead has singin 
		try {
			driver.findElement(By.xpath("//p[@class='login__form__prescreen__email']/a[text()='Sign in']")).click();
		} catch (Exception e) {
			
		}
		
		driver.findElement(By.id("email")).sendKeys("vbalaji131@gmail.com");
		driver.findElement(By.id("pw")).sendKeys("tester@123");
		driver.findElement(By.id("login_submit")).click();	
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		//
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("startPostButton"))));
		driver.findElement(By.id("startPostButton")).click();

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("header_text"))));
		
//		Thread.sleep(500);
		
		driver.findElement(By.xpath("//span[text()=' Choose Topic ']")).click();
		driver.findElement(By.xpath("//div[text()=' Ask Your Questions ']")).click();	
		
		String postTitle = "Sample post2";
		driver.findElement(By.id("post-title-textbox")).sendKeys(postTitle);
		
//		driver.findElement(By.xpath("//div[@class='pfw-editor']//p")).sendKeys("Hi all inpire frinds");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		
		jse.executeScript("document.getElementsByClassName('ck-placeholder')[0].innerHTML='Hi all inspire friends';");
		driver.findElement(By.id("dropdown_wrapper")).click();
		driver.findElement(By.xpath("//div[text()=' Inspire Friends ']")).click();
		
		driver.findElement(By.id("submit-post-button")).click();
		
		wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.className("fullscreen-loading"))));
		
		WebElement logo = driver.findElement(By.className("un-logo"));
		jse.executeScript("arguments[0].click();", logo);
		
		String listTitle = driver.findElement(By.xpath("(//div[@id='page-0']/div)[1]//h3")).getText();
		
		if (postTitle.equals(listTitle)) {
			System.out.println("Post shows up at the top of the lists of posts");
		}else {
			System.out.println("Post NOT shows up at the top of the lists of posts");
		}
		
		driver.quit();

	}

}
