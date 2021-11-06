Feature: Admin Login

    Scenario Outline: Login in admin module
        Given Login a admin user using "<email>" and "<password>"
        Then Check login response for status code "200"
        Examples:
            | email         | password |
            | demo@demo.com | 123456   |
