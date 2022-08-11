Feature: OTP Verification

    Scenario Outline: Verify OTP from given OTP in email
        Given Get the OTP that is given in email
        Then Call Verify OTP and "<email>"
        Then Check the response with status code "200" and success "true" for verified OTP or status code "401" and message "OTP expired"
        Examples:
            | email                   |
            | murshalin98@outlook.com |