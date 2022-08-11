Feature: Get User Group Post

  Scenario Outline: Get All Group Post With Group ID
    Given Login with user "<email>" and "<password>"
    Then Call Post Get Group Post API with groupId "<groupId>"
    Then Check response for status code "200" and success "true"
    Examples:
      | email                | password | groupId       |
      | towfiq.106@gmail.com | Test@123 | 618031897e38f8623016259c |
