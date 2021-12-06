Feature: OTP Resend

  Scenario Outline: Resend OTP from given email
    Given Resend Otp with Registered "<email>"
    Then Check Resend Otp for status code "200" and success is "true"
    Examples:
      | email          |
      | email@mail.com |
