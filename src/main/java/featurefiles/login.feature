Feature: Login to tutorials Ninja Application
  Scenario Outline: Try to login with valid and invalid credentials
    Given I navigated to the Login Page
    When I enter the email as <emailaddress> and password as <password>
    And I click on Login button
    Then I should get the result as <expected>
    Examples:
      |emailaddress|password|expected|
      |mansi10@gmail.com|12345|success|
      |mansi102@gmail.com|4567|failure|