package pageobjectweb;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utility.Os;
import utility.PropertiesLoader;
import utility.WebBasePage;

import java.util.Properties;

import static utility.ComplexReportFactory.getTest;

public class NswPage extends WebBasePage {
    private WebDriver webDriver;
    private static String FILE_NAME ;
    private static Properties prop ;

    public NswPage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    public void openUrl() {
        if(Os.isMac()) {
            FILE_NAME = System.getProperty("user.dir") + "/src/main/resources/config.properties";
        }else if(Os.isWindows()){
            FILE_NAME = System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties";
        }
        prop = new PropertiesLoader(FILE_NAME).load();
        webDriver.get(prop.getProperty("url"));
    }

    public void searchThegivenWord(String keyword) {
        clickElementVisible(By.xpath("(//input[@name='q'])[2]"), "click search", 10);
        enterElementVisible(By.xpath("(//input[@name='q'])[2]"), keyword, "keyword enter", 10);
        clickElementVisible(By.xpath("(//button[@type='submit'])[2]"), "click search button", 10);
    }

    public void validatetheresult() {

        if (webDriver.findElements(By.xpath("//div[@class='search__result']//a")).size() > 0) {
            System.out.println("pass");
            getTest().log(LogStatus.PASS, " Search results displayed ");
        } else {
            getTest().log(LogStatus.FAIL, " Search results not displayed ");
        }
    }

    public void clickFindlocation() {
        clickByJavascript(By.xpath("(//a[text()='Find locations'])[1]"));
    }

    public void enterLocationName(String locationname) {
        enterElementVisible(By.id("locatorTextSearch"), locationname, "enter location", 10);
        clickElementVisible(By.xpath("(//button[@type='submit'])[2]"), "click search button", 10);

    }

    public void validateFilteredresult(String servicecentre) {
        staticWait(3000);
        verifyElement(By.xpath("//div[@class='locator__results']"), "Locator results section", 20);
        scrollToElement(By.xpath("//div[@class='locator__results']//a[contains(text(),'serviceCentre')]".replace("serviceCentre", servicecentre)));
        staticWait(3000);
        String text = webDriver.findElement(By.xpath("//div[@class='locator__results']")).getAttribute("innerHTML");
        if (text.contains(servicecentre)) {
            getTest().log(LogStatus.PASS, servicecentre + " displayed as expected ");

        } else {
            getTest().log(LogStatus.FAIL, servicecentre + "Result not displayed ");
        }
    }
}
