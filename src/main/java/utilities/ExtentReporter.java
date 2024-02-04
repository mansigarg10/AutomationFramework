package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {
    static ExtentReports reports;
    public static ExtentReports getExtentReporter(){

        ExtentSparkReporter reporter = new ExtentSparkReporter("D:\\Study\\Java\\Workspace\\Framework\\src\\main\\reports\\"+ "extentreport.html");
        reporter.config().setReportName("login test");
        reporter.config().setDocumentTitle("login test report");
        reports = new ExtentReports();
        reports.attachReporter(reporter);
        reports.setSystemInfo("Operating System","Windows 11");
        reports.setSystemInfo("Created By", "Mansi Garg");
        return reports;
    }

}
