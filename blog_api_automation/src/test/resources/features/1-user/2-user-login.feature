Feature: User Login

  Scenario Outline: Login in user module
    Given Login with user "<email>" and "<password>"
    Then Check login response for status code "200"
    Examples:
      | email                | password |
      | towfiq.106@gmail.com | Test@123 |
