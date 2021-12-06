Feature: Post User Share Post

  Scenario Outline: Share A Post With Post ID
    Given Login with user "<email>" and "<password>"
    Then Call Post Get Share Post API with postId "<postId>"
    Then Check response for status code "200" and success "true"
    Examples:
      | email                | password | postId       |
      | towfiq.106@gmail.com | Test@123 | 61980a0c576966720faabcdc |
