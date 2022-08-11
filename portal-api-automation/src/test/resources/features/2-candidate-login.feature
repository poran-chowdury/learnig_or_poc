Feature: Candidate Login

    Scenario Outline: Login in candidate module
        Given Login with candidate "<email>" and "<password>"
        Then Check login response for status code "200" and success is "true"
        Examples:
            | email                   | password     |
            | murshalin98@outlook.com | Murshalin98@ |
