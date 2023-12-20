package test;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pageobject.AccountPage;
import pageobject.LandingPage;
import pageobject.LoginPage;
import resources.Base;



public class LoginTest extends Base{

	public WebDriver driver;
	String actualResult;
	Logger log;

	@BeforeMethod
	public void openApplication() throws IOException {
		log = LogManager.getLogger("LoginTest");
		driver = initializeBrowser();
		log.debug("Browser got launched");
		driver.get(prop.getProperty("url"));
		log.debug("Navigated to application URL");
	}

	@Test(dataProvider = "getData")
	public void login(String email, String password, String result) throws IOException, InterruptedException {

		LandingPage landingPage = new LandingPage(driver);
		Thread.sleep(2000);
		landingPage.myAccount().click();
		log.debug("Clicked on My Account dropdown");
		Thread.sleep(2000);
		landingPage.login().click();
		log.debug("Clicked on login option");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.email().sendKeys(email);
		log.debug("Email addressed got entered");
		loginPage.password().sendKeys(password);
		log.debug("Password got entered");
		loginPage.loginButton().click();
		log.debug("Clicked on Login Button");

		AccountPage accountPage = new AccountPage(driver);
		Assert.assertTrue(accountPage.editYourAccountInformation().isDisplayed());
		try {

			if (accountPage.editYourAccountInformation().isDisplayed()) {
				log.debug("User got logged in");
				actualResult = "Success";
			}

		} catch (Exception e) {
			log.debug("User didn't log in");
			actualResult = "fail";
		}

		Assert.assertEquals(actualResult, result);
	}

	@DataProvider
	public Object[][] getData() {
		Object data[][] = { { "debupadhy2121@gmail.com", "Deba123@", "Success" },
				{ "dummy@123gmail.com", "12345", "fail" } };
		return data;
	}

	@AfterMethod
	public void closure() {
		driver.close();
		log.debug("Browser got closed");
	}

}
