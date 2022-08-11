Feature: Get User Recent Article

  Scenario Outline: Get Recent Article
    Given Login with user "<email>" and "<password>"
    Then Call Article Get Recent Article API
    Then Check response for status code "200" and success "true"
    Examples:
      | email                | password |
      | towfiq.106@gmail.com | Test@123 |
