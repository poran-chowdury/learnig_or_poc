Feature: Post Blog Create Trending Blog

  Scenario Outline: Create A New Blog
    Given Login with user "<email>" and "<password>"
    Then Call Blog Create New Blog API with name "<name>" and description "<description>"
    Then Check created blog response for status code "201" and success "true"
    Examples:
      | email                | password | name           | description           |
      | towfiq.106@gmail.com | Test@123 | test_blog_name | test_blog_description |
