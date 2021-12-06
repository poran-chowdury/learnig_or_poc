Feature: Get User Single Post

  Scenario Outline: Get Single Post With Post Slug
    Given Login with user "<email>" and "<password>"
    Then Call Post Get Single Post API with postSlug "<postSlug>"
    Then Check response for status code "200" and success "true"
    Examples:
      | email                | password | postSlug       |
      | towfiq.106@gmail.com | Test@123 | test-bhhnvf_t3 |
