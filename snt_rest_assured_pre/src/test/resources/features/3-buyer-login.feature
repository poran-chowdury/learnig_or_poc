Feature: Customer Login

    Scenario Outline: Login in customer module
        Given Login with customer "<email>" and "<password>"
        Then Check login response for status code "200"
        Examples:
            | email           | password |
            | test@domain.com | test@123 |
