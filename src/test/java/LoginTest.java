import objectrepository.AccountPage;
import objectrepository.LandingPage;
import objectrepository.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import resources.Base;

import java.io.IOException;


public class LoginTest extends Base  {
    Logger logger;
    LandingPage landingPage;
    LoginPage loginPage;
    AccountPage accountPage;
   public WebDriver driver;



    @BeforeMethod
    public void setUp() throws IOException {
        logger=LogManager.getLogger(LoginTest.class.getName());
        driver = initializer();
        logger.debug("Browser has opened and maximized");

        // Pass WebDriver instance to Listeners
        driver.get(prop.getProperty("url"));
        logger.debug("Navigated to the application");
    }



   @Test(dataProvider ="data")
   public void login(String email,String password,String expectedResult) throws IOException {

       landingPage = new LandingPage(driver);
       landingPage.myAccount().click();
       landingPage.login().click();
       logger.debug("Navigated to login page");
       loginPage = new LoginPage(driver);
       loginPage.emailField().sendKeys(email);
       loginPage.passwordField().sendKeys(password);
       loginPage.loginButton().click();

       accountPage = new AccountPage(driver);
       System.out.println(".....................gggggg");
       


       String actualResult = null;
        try {
            if(accountPage.editAccountOption().isDisplayed());
            logger.info("User is able to login with valid credentials");
            actualResult= "successfull";

        }catch (Exception e){
            logger.error("User is not able to login with invalid credentials");
            actualResult ="failure";
            Assert.fail();



        }

        Assert.assertEquals(actualResult,expectedResult);
        logger.debug("Test got passed");
    }

   @DataProvider
   public Object[][] data(){

       Object [][] dataSet = {{"mansi10@gmail.com","12345","successfull"}, {"mansi101@gmail.com", "12345","failure"}};
       return dataSet;
   }

   @AfterMethod
    public void tearDown(){
       driver.quit();
   }

}
