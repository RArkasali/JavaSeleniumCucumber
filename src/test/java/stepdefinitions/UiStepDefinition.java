package stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;
import pageobjectweb.NswPage;
import utility.Hook;

public class UiStepDefinition {
    private WebDriver webDriver;
    private NswPage nswPage;


    public UiStepDefinition() {
        this.webDriver = Hook.getDriver();
    }

    @Given("^open the service nsw Url$")
    public void openTheNswUrl() {
        nswPage = new NswPage(webDriver);
        nswPage.openUrl();
    }

    @Then("^search the given keyword \"([^\"]*)\"$")
    public void search_the_given_keyword(String keyword) {

        nswPage.searchThegivenWord(keyword);
    }

    @Then("^validate the result availability$")
    public void validate_the_result_availability() {
        nswPage.validatetheresult();
    }

    @Then("^click find location$")
    public void click_find_location() {
        nswPage.clickFindlocation();
    }

    @Then("^enter the location name\"([^\"]*)\"$")
    public void enterTheLocationName(String locationName) {
        nswPage.enterLocationName(locationName);
    }

    @Then("^verification the result\"([^\"]*)\"$")
    public void verification_the_result(String result) {
        nswPage.validateFilteredresult(result);
    }


}





