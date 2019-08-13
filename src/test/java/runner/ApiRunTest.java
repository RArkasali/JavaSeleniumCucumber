package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import static utility.ComplexReportFactory.closeReport;

@CucumberOptions(features = {"src/test/java/features/RestService.feature"}
        , glue = {"stepdefinitions", "utility"}
        , plugin = {"pretty", "html:target/cucumber"}

)
@Test
public class ApiRunTest extends AbstractTestNGCucumberTests {

    @AfterSuite(alwaysRun = true)
    public void close() {
        closeReport();
    }

}
