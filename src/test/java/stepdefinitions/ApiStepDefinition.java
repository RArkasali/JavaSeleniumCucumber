package stepdefinitions;

import com.relevantcodes.extentreports.LogStatus;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import utility.Os;
import utility.PropertiesLoader;

import java.util.Properties;

import static utility.ComplexReportFactory.getTest;

public class ApiStepDefinition {

    private Response response;
    private ValidatableResponse json;
    private RequestSpecification request;
    private static String FILE_NAME;
    private static Properties prop;


    @Given("^I make a GET request with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iMakeAGETRequestWithAnd(String lat, String lon) {
        if (Os.isMac()) {
            FILE_NAME = System.getProperty("user.dir") + "/src/main/resources/config.properties";
        } else if (Os.isWindows()) {
            FILE_NAME = System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties";
        }
        prop = new PropertiesLoader(FILE_NAME).load();
        request = RestAssured.given();
        response = request.when().get(prop.getProperty("serviceUrl") + "lat=" + lat + "&lon=" + lon +"&key="+ prop.getProperty("apikey"));
        getTest().log(LogStatus.PASS, "Request - " + prop.getProperty("serviceUrl") + "lat=" + lat + "&lon=" + lon +"&key="+ prop.getProperty("apikey"));
    }

    @Given("^I make a GET request with \"([^\"]*)\"$")
    public void iMakeAGETRequestWith(String postcode) {
        request = RestAssured.given();
        response = request.when().get(prop.getProperty("serviceUrl") + "postal_code=" + postcode +"&key="+ prop.getProperty("apikey"));
        getTest().log(LogStatus.PASS, "Request - " + prop.getProperty("serviceUrl") + "postal_code=" + postcode +"&key="+ prop.getProperty("apikey"));
    }

    @And("^I validate status code$")
    public void iVaidateStatusCode() {
        json = response.then().statusCode(200);
        getTest().log(LogStatus.PASS, "Status code is - 200");
    }


    @And("^I get the value of latitude and longitude response$")
    public void iGetTheValueOfLatitudeAndLongitudeResponse() {

        String[] splitVal = prop.getProperty("statecode").split(",");
        for (String val : splitVal) {
            String responseValue = response.jsonPath().getString(val);
            if (responseValue.trim().equals("[null]")) {
                getTest().log(LogStatus.FAIL, val + " - " + responseValue + " - not displayed ");
            } else {
                getTest().log(LogStatus.PASS, val + " - " + responseValue + " - displayed ");
            }

        }
    }

    @And("^I get the value of postcode$")
    public void iGetTheValueOfPostcode() {
        String[] splitVal = prop.getProperty("postcode").split(",");
        for (String val : splitVal) {
            String responseValue = response.jsonPath().getString(val);
            if (responseValue.trim().equals("[null]")) {
                getTest().log(LogStatus.FAIL, val + " - " + responseValue + " - not displayed ");
            } else {
                getTest().log(LogStatus.PASS, val + " - " + responseValue + " - displayed ");
            }
        }
    }
}
