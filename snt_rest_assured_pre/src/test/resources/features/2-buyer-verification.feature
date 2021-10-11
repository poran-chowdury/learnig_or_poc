Feature: OTP Verification

    Scenario : Verify OTP from given OTP in email
        Then Check the response with status code "200" and success "true" for verified OTP or status code "401" and message "OTP expired"
