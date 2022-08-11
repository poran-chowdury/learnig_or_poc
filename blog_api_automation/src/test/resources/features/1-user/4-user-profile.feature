Feature: Get User Profile

  Scenario Outline: Get User Profile
    Given Login with user "<email>" and "<password>"
    Then Call User Profile API and Get Profile JSON
    Then Check response for status code "200" and success "true"
    Examples:
      | email                | password |
      | towfiq.106@gmail.com | Test@123 |
