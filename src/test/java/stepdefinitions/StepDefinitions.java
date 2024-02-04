package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import objectrepository.AccountPage;
import objectrepository.LandingPage;
import objectrepository.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import resources.Base;

import java.io.IOException;

public class StepDefinitions extends Base {
    WebDriver driver;
    LandingPage landingPage;
    LoginPage loginPage;
    AccountPage accountPage;


    @Given("^I navigated to the Login Page$")
    public void firstStep() throws IOException {
        driver = initializer();
        driver.get(prop.getProperty("url"));
        landingPage = new LandingPage(driver);
        landingPage.myAccount().click();
        landingPage.login().click();
    }

    @When("^I enter the email as (.*) and password as (.*)$")
    public void secondStep(String email, String password) {
        loginPage = new LoginPage(driver);
        loginPage.emailField().sendKeys(email);
        loginPage.passwordField().sendKeys(password);

    }

    @And("^I click on Login button$")
    public void thirdStep() {
        loginPage.loginButton().click();
    }

    @Then("^I should get the result as (.*)$")
    public void lastStep(String expected) {
        String actual = null;
        accountPage = new AccountPage(driver);
        if(accountPage.editAccountOption().isDisplayed()) {
            actual="success";
        }else {
            actual="failure";
        }
        Assert.assertEquals(actual,expected);





    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
