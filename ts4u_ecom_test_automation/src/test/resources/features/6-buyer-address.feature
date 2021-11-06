Feature: Create Address for a given customer

    Scenario Outline: Login customer and create a address
        Given Login with customer "<email>" and "<password>"
        Then Check login response for status code "200"
        Then Prepare address request data
        Then Request to create address API
        Then Verify create address response with status code "201"
        Examples:
            | email                | password   |
            | towfiq.106@gmail.com | towfiq@123 |
