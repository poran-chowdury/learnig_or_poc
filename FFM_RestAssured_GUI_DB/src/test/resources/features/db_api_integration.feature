Feature: API-DB integration

  Scenario: Verify ffm user is getting saved in database
    Given I register FFM user with following details
      | firstname | lastname     | email        | password | username |
      | ffm_name  | ffm_lastname | ffm@test.com | ffm      | ffm      |
    And I connect to DB
    Then I verify if ffm user exists in db with email "ffm@test.com" and memberType "1"

  Scenario: Verify company is getting saved in database
    Given I register a company with following details
      | name        | address | tin      |
      | testcompany | test    | test_tin |
    And I connect to DB
    Then I verify if company exists in db with name "testcompany"

  Scenario: Verify company admin is getting saved in database
    Given I register a company with following details
      | name        | address | tin      |
      | testcompany | test    | test_tin |
    And I register a company manager with following details
      | firstName | lastName | email           | password | username        | member_type |
      | test      | test     | david@email.com | manager  | managerusername | 2           |
    And I connect to DB
    Then I verify if company admin exists in db with username "managerusername" and memberType "2"