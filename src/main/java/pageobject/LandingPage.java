package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {

	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(linkText = "My Account")
	private WebElement myAccount;
	
	@FindBy(linkText = "Login")
	private WebElement login;
	
	public WebElement myAccount() {
		return myAccount; 
	}
	public WebElement login() {
		return login;
	}
}
