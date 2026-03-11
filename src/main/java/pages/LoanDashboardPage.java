package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoanDashboardPage {
	
	WebDriver driver;
	
	public LoanDashboardPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public boolean isLoanVisible(String loanID) {
		try {
			
			return driver.findElement(By.xpath("//td[text()='" + loanID + "']")).isDisplayed();
		} catch (Exception e) {
            return false;
	}

}
}
