Feature: Get User Post

  Scenario Outline: Get User Post
    Given Login with user "<email>" and "<password>"
    Then Call Post MyPost API
    Then Check response for status code "200" and success "true"
    Examples:
      | email                | password |
      | towfiq.106@gmail.com | Test@123 |
