package utility;


import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static utility.ComplexReportFactory.getTest;

public class WebBasePage {

    private WebDriver driver;

    public WebBasePage(WebDriver webDriver) {

        this.driver = webDriver;
    }


    public void enterElementVisible(By by, String value, String name, int time) {
        WebElement element = findElementVisibility(by, time);
        if (element != null) {
            element.clear();
            element.sendKeys(value);
            getTest().log(LogStatus.PASS, name + " entered with value - " + value);
        } else {
            getTest().log(LogStatus.FAIL, name + " entered with value - " + value);
        }
    }


    public void clickElementVisible(By by, String name, int time) {
        WebElement element = findElementVisibility(by, time);
        if (element != null) {
            element.click();
            getTest().log(LogStatus.PASS, name + " clicked");
        } else {
            getTest().log(LogStatus.FAIL, name + " not clicked");
            Assert.fail(name + " - element not present");
        }
    }


    public void verifyElement(By by, String name, int time) {
        WebElement element = findElementVisibility(by, time);
        if (element != null) {
            getTest().log(LogStatus.PASS, name + " element visible");
        } else {
            getTest().log(LogStatus.FAIL, name + " element not visible");
            Assert.fail(name + " -  element not present");
        }
    }

    public void staticWait(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void clickByJavascript(By by) {

        WebElement element = driver.findElement(by);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);

    }

    public void scrollToElement(By by) {
        WebElement element = findElementVisibility(by, 10);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView();", element);
    }

    public WebElement findElementVisibility(final By by, int time) {

        WebDriverWait wait = new WebDriverWait(driver, time);

        try {

            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            wait.until((ExpectedCondition<Boolean>) _webDriver -> (_webDriver.findElement(by) != null));
        } catch (Exception e) {
            System.out.println();
            return null;
        }
        return driver.findElement(by);

    }
}
