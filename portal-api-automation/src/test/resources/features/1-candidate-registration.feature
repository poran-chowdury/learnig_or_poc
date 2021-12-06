Feature: Candidate Registration

    Scenario Outline: Register candidate and check isOtpSend
        Given Register a candidate with "<firstname>" "<lastname>" "<email>" "<phone>" "<password>"
        Then Check the customer is created with status code "200" and isOtpSend "true" with this "<email>" or status code "400" and message "User already registered"
        Then Make otp isVerified "true" for email "<email>"
        Examples:
            | firstname | lastname | email                      | phone        | password  |
            | Sharon    | May      | sharonmmay2@jourrapide.com | 907-671-8540 | Sharon123 |