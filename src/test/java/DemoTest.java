import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import resources.Base;

import java.io.IOException;

public class DemoTest extends Base {
    public WebDriver driver;


  @BeforeMethod
    public void setup() throws IOException {
        driver = initializer();
        driver.get("https://jsonformatter.org/xml-formatter");
    }


    @Test public void demo(){
        Assert.assertEquals(false,true);

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}
