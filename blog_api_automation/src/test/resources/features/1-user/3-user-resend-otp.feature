Feature: Resend OTP

  Scenario Outline: Resend OTP Verification Code
    Given User "<email>"
    Then Check response for status code "200" and isOtpSend "true"
    Examples:
      | email         |
      | test@demo.com |