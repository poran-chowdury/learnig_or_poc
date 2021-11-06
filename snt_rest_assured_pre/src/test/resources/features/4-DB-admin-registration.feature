Feature: Admin Registration

    Scenario Outline: Register a admin in system
        Given I connect to DB
        Then Check with collection email "<email>" with Response "true" and I delete DB data
        Then create a admin user using "<name>" "<email>" and "<hashPassword>"
        Then I close DB
        Examples:
            | name       | email         | hashPassword                                                 |
            | test admin | demo@demo.com | $2a$12$jOVetvcEG3UY4Bh5B5oyP.bb2xH48mptaytDFljQeFKQ5r6NXllGm |
