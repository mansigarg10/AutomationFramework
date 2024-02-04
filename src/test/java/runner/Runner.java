package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin ={"html:target/cucumber_report.html"},
        features = {"src/main/java/featurefiles"},
        glue = "stepdefinitions"
)
public class Runner extends AbstractTestNGCucumberTests {

}
