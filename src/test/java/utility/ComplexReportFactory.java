package utility;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.NetworkMode;

import java.util.HashMap;
import java.util.Map;

public class ComplexReportFactory {

    private static ExtentReports reporter;
    private static Map<Long, String> threadToExtentTestMap = new HashMap<Long, String>();
    private static Map<String, ExtentTest> nameToTestMap = new HashMap<String, ExtentTest>();

    private synchronized static ExtentReports getExtentReport() {
        if (reporter == null) {
            reporter = new ExtentReports("reports/AutomationReport.html", true, NetworkMode.ONLINE);
        }
        return reporter;
    }

    public synchronized static ExtentTest getTest(String testName, String testDescription) {

        if (!nameToTestMap.containsKey(testName)) {
            Long threadID = Thread.currentThread().getId();
            ExtentTest test = getExtentReport().startTest(testName, testDescription);
            nameToTestMap.put(testName, test);
            threadToExtentTestMap.put(threadID, testName);
        }
        return nameToTestMap.get(testName);
    }

    public synchronized static ExtentTest getTest(String testName) {
        return getTest(testName, "");
    }


    public synchronized static ExtentTest getTest() {
        Long threadID = Thread.currentThread().getId();

        if (threadToExtentTestMap.containsKey(threadID)) {
            String testName = threadToExtentTestMap.get(threadID);
            return nameToTestMap.get(testName);
        }
        return null;
    }

    public synchronized static void closeTest(String testName) {

        if (!testName.isEmpty()) {
            ExtentTest test = getTest(testName);
            getExtentReport().endTest(test);
        }
    }

    public synchronized static void closeTest(ExtentTest test) {
        if (test != null) {
            getExtentReport().endTest(test);
        }
    }

    public synchronized static void closeTest() {
        ExtentTest test = getTest();
        closeTest(test);
    }

    public synchronized static void closeReport() {

            if (reporter != null) {
                reporter.flush();
                reporter.close();
            }
    }

}
