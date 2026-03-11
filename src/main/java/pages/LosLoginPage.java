package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LosLoginPage {
	
	WebDriver driver;
	
	public LosLoginPage(WebDriver driver) {
		this.driver=driver;
	}

	public void login (String username, String password) {
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("loginBtn")).click();
	
	}

}
