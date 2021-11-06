Feature: Create Product using Admin Module

    Scenario Outline: Create a new product
        Given Login a admin user using "<email>" and "<password>"
        Then Check login response for status code "200"
        Then Create Product JSON payload
        Then request with created JSON payload
        Then check for product create response with status code "201"
        Then Verify created new product in DB
        Examples:
            | email         | password |
            | demo@demo.com | 123456   |
