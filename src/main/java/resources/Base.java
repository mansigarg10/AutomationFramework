package resources;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {
    public Properties prop = new Properties();
    public WebDriver driver;

    public WebDriver initializer() throws IOException {
        String propertiesPath = System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.properties";
        FileInputStream fis = new FileInputStream(propertiesPath);
        prop.load(fis);
        String browserName = prop.getProperty("browser");

        if (browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();

        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("IE")) {
            driver = new InternetExplorerDriver();
        } else {
            driver = new EdgeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return driver;
    }

    public String takeScreenshot(String testMethod, WebDriver driver){
        File sourceFile =((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destinationPath = "D:/Study/Java/Workspace/Framework/src/screenshots/" +testMethod+ "_"+".jpg";
        try {
            FileHandler.copy(sourceFile,new File(destinationPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destinationPath;


    }

}
