Feature: Get Blog All

  Scenario Outline: Get All Blogs
    Given Login with user "<email>" and "<password>"
    Then Call Blog Get All Blog API
    Then Check response for status code "200" and success "true"
    Examples:
      | email                | password |
      | towfiq.106@gmail.com | Test@123 |
