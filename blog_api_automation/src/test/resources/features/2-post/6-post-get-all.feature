Feature: Get User All Post

  Scenario Outline: Get All Posts
    Given Login with user "<email>" and "<password>"
    Then Call Post Get API
    Then Check response for status code "200" and success "true"
    Examples:
      | email                | password |
      | towfiq.106@gmail.com | Test@123 |
