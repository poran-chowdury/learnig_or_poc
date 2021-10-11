Feature: I want to place an order

    Scenario Outline: I will create an order
        Given Login with customer "<email>" and "<password>"
        Then Check login response for status code "200"
        Then Prepare a new order request dto
        Then Create an order using previous created order request dto
        Then Verify create order response with status code "201"
        Then Verify created order in DB
        Examples:
            | email                | password   |
            | towfiq.106@gmail.com | towfiq@123 |
