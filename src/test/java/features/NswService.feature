Feature: Validation of search and location

  Scenario Outline: UI test to verify and validate the service centre based on location name
    Given open the service nsw Url
    Then search the given keyword "<keyword>"
    Then validate the result availability
    Then click find location
    Then enter the location name"<location name>"
    Then verification the result"<service centre>"
    Examples:
      |keyword|location name|service centre|
      |Apply for a number plate|Sydney 2000| Marrickville Service Centre |