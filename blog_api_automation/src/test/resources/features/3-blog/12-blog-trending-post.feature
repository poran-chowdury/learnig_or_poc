Feature: Get User Trending Blog

  Scenario Outline: Get Trending Blog
    Given Login with user "<email>" and "<password>"
    Then Call Blog Trending Blog API
    Then Check response for status code "200" and success "true"
    Examples:
      | email                | password |
      | towfiq.106@gmail.com | Test@123 |
