Feature: User Registration

  Scenario Outline: Register User and check isOtpSend
    Given Register a user with "<first_name>" "<last_name>" "<email>" "<password>"
    Then Check the user is created with status code "200" and isOtpSend "true"
    Examples:
      | first_name | last_name | email           | password |
      | Test_first | Test_last | test@domain.com | Test@123 |
