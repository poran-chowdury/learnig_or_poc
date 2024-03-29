Feature: Customer Registration

#    API CHECK
    Scenario Outline: Register customer and check isOtpSend
        Given Register a customer with "<name>" "<email>" "<password>"
        Then Check the customer is created with status code "200" and isOtpSend "true" with this "<email>" or status code "400" and message "User already registered"
        Then Make otp isVerified "true" for email "<email>"
        Examples:
            | name   | email                | password   |
        #    | Towfiq | towfiq.106@gmail.com | towfiq@123 |
            | Towfiq | towfiq-test.106@gmail.com | towfiq@123 |
