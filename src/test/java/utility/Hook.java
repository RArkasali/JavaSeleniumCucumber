package utility;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static utility.ComplexReportFactory.closeTest;
import static utility.ComplexReportFactory.getTest;


public class Hook {


    private static WebDriver driver;
    private ExtentTest test;


    @Before()
    public void setUp(Scenario scenario) {
        String FILE_NAME ;
        Properties prop ;
        String browser = "";

          if(Os.isMac()){
              FILE_NAME = System.getProperty("user.dir") + "/src/main/resources/config.properties";
              prop = new PropertiesLoader(FILE_NAME).load();
              browser = prop.getProperty("browser").trim();
          }else if(Os.isWindows()){
              FILE_NAME = System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties";
              prop = new PropertiesLoader(FILE_NAME).load();
              browser = prop.getProperty("browser").trim();
          }

        test = getTest(scenario.getName());
        ChromeOptions options = new ChromeOptions();
        if (!scenario.getId().contains("api")) {
            switch (browser) {
                case "firefox":
                    if (Os.isMac()) {

                        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/drivers/geckodriver");
                    } else if (Os.isWindows()) {

                        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\drivers\\geckodriver.exe");
                    }
                    driver = new FirefoxDriver();
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
                    break;

                case "chrome":
                    options.addArguments("disable-infobars");
                    if (Os.isMac()) {
                        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");
                    } else if (Os.isWindows()) {
                        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
                    }
                    driver = new ChromeDriver(options);
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
                    break;

                default:
                    options = new ChromeOptions();
                    options.addArguments("disable-infobars");
                    if (Os.isMac()) {
                        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");
                    } else if (Os.isWindows()) {

                        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
                    }
                    driver = new ChromeDriver(options);
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
                    break;
            }
        }

    }

    @After
    public void tearDown(Scenario scenario) {

        if (!scenario.getId().contains("api")) {
            if (scenario.getStatus().equals("failed")) {

                String imagpath = System.getProperty("user.dir") + "\\reports\\" + scenario.getName();

                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                try {
                    org.apache.commons.io.FileUtils.copyFile(scrFile, new File(imagpath + ".png"));
                    System.out.println(imagpath + ".png");
                } catch (Exception e) {

                }
                getTest().log(LogStatus.FAIL, getTest().addScreenCapture(imagpath + ".png"));

            }
            driver.quit();
        }
        closeTest(test);

    }

    public static WebDriver getDriver() {
        return driver;
    }
}
