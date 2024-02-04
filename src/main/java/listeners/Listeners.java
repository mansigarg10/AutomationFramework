package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import resources.Base;
import utilities.ExtentReporter;

public class Listeners extends Base implements ITestListener {
    public WebDriver driver;
    ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<ExtentTest>();
    ExtentReports extentReports = ExtentReporter.getExtentReporter();
    ExtentTest extentTest;

    @Override
    public void onTestStart(ITestResult result) {
        extentTest = extentReports.createTest(result.getName());
        extentTestThread.set(extentTest);

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTestThread.get().log(Status.PASS,result.getName()+"got passed");

    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getName();


        extentTestThread.get().fail(result.getThrowable());
        try {
            driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        takeScreenshot(result.getName(),
                driver);
        String screenshotFile = takeScreenshot(testName, driver);
        extentTestThread.get().addScreenCaptureFromPath(screenshotFile,testName);


    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();

    }



}
