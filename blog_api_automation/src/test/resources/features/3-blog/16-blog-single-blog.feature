Feature: Get User Single Blog

  Scenario Outline: Get Single Blog With Blog Slug
    Given Login with user "<email>" and "<password>"
    Then Call Blog Get Single Blog API with blogSlug "<blogSlug>"
    Then Check response for status code "200" and success "true"
    Examples:
      | email                | password | blogSlug     |
      | towfiq.106@gmail.com | Test@123 | islamic-talk |
