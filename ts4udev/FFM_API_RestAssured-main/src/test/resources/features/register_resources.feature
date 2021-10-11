Feature: Register Resources

  Scenario: Verify user is able to register a company
    Given I register FFM user with following details
      | firstname | lastname     | email        | password | username |
      | ffm_name  | ffm_lastname | ffm@test.com | ffm      | ffm      |
    And I login with following details
      | email        | password |
      | ffm@test.com | ffm      |
    Given I register a company with following details
      | name        | address | tin      |
      | testcompany | test    | test_tin |

  Scenario: Verify user is able to register a company and register company manager under the same company
    Given I register FFM user with following details
      | firstname | lastname     | email        | password | username |
      | ffm_name  | ffm_lastname | ffm@test.com | ffm      | ffm      |
    And I login with following details
      | email        | password |
      | ffm@test.com | ffm      |
    And I register a company with following details
      | name | address | tin       |
      | test | test    | 487646tin |
    And I register a company manager with following details
      | firstName | lastName | email           | password | username        | member_type |
      | test      | test     | david@email.com | manager  | managerusername | 2           |

  Scenario: Verify user is able to login logout as a company manager
    Given I register FFM user with following details
      | firstname | lastname     | email        | password | username |
      | ffm_name  | ffm_lastname | ffm@test.com | ffm      | ffm      |
    And I login with following details
      | email        | password |
      | ffm@test.com | ffm      |
    And I register a company with following details
      | name            | address | tin                |
      | testss223333233 | test    | 334243sss34454test |
    When I register a company manager with following details
      | firstName | lastName | email                  | password | username      | member_type |
      | tesst     | test     | davidmanager@email.com | david    | Wils31liamson | 2           |
    And I login manager with following details
      | email              | password |
      | david.davidmanager | david    |
    And I logout
    Then I should be able to logout