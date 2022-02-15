Feature: Buyer Login
  Scenario Outline: Login from the buyer module

    Given Login with buyer "<email>" and "<password>"
    Then Check login response for status code "200"

    Examples:
    |email| password|
    |test@domain.com|test@123|
