Feature: Get User Single Article

  Scenario Outline: Get Single Article With Article Slug
    Given Login with user "<email>" and "<password>"
    Then Call Article Get Single Article API with articleSlug "<articleSlug>"
    Then Check response for status code "200" and success "true"
    Examples:
      | email                | password | articleSlug                           |
      | towfiq.106@gmail.com | Test@123 | business-sub-2-title-sample-5c5aeedad |
