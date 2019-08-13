Feature: Validation of state code , whether , timestamp for the entries in Rest API

  Scenario Outline: API test to verify and validate the latitude and longitude
    Given I make a GET request with "<Latitutde>" and "<Longitute>"
    And I validate status code
    And I get the value of latitude and longitude response
    Examples:
      | Latitutde | Longitute |
      | 40.730610 | 73.935242 |

  Scenario Outline: API test to verify and validate the post code
    Given I make a GET request with "<Postcode>"
    And I validate status code
    And I get the value of postcode
    Examples:
      | Postcode |
      | 2145     |